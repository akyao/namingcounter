package namingcounter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import namingcounter.dto.CountData;

/**
 * カウントするやつ
 * @author akyao
 */
public class Counter {

	private CountData countData = new CountData();
	
	private IStructuredSelection iSel;

	public static CountData count(ISelection iSel) {
		if (iSel != null && iSel instanceof IStructuredSelection) {
			Counter c = new Counter((IStructuredSelection) iSel);
			c.count();
			return c.countData;	
		}
		return new CountData();
	}
	
	private Counter(IStructuredSelection iSel){
		this.iSel = iSel;
	}
	
	private void count() {
		@SuppressWarnings("unchecked")
		Iterator<Object> ite = iSel.iterator();
		
		while (ite.hasNext()) {
			Object obj = ite.next();
			if (obj instanceof IContainer) {
				// ディレクトリ
				count((IContainer) obj);
			} else if (obj instanceof IPackageFragment) {
				// Javaパッケージ（JDT）
				try{
					IPackageFragment pkg = (IPackageFragment)obj;	
					for (ICompilationUnit cUnit : pkg.getCompilationUnits()) {
						count(cUnit);
					}
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			} else if (obj instanceof IFile) {
				// ファイル
				count((IFile) obj);
			} else if (obj instanceof ICompilationUnit) {
				// Javaソース
				count((ICompilationUnit) obj);
			}
		}
	}
	
	private void count(IContainer container) {
		try {
			for (IResource resource : container.members()) {
				if (resource instanceof IContainer) {
					count((IContainer) resource);
				} else if (resource instanceof IFile) {
					count((IFile) resource);
				} else if (resource instanceof ICompilationUnit){
					count((ICompilationUnit) resource);
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private void count(IFile file) {
		IJavaElement jEle = JavaCore.create(file);
		if (jEle != null) {
			ICompilationUnit unit = (ICompilationUnit) jEle.getAncestor(IJavaElement.COMPILATION_UNIT);
			count(unit);			
		}
	}
	
	private void count(ICompilationUnit unit) {
		
		if(unit == null) {
			return;
		}
		
		try{
			Function<IMember, List<String>> convert = x -> toSmallCaracters(splitToWord(x.getElementName()));
			
			for (IType iType : unit.getTypes()) {
				// top level class
				countData.add(convert.apply(iType), iType);
				// inner class
				for (IType iType2 : iType.getTypes()) {
					// TODO 再帰させないと、2重にネストしたクラスをカウントできない。要修正
					countData.add(convert.apply(iType2), iType2);
				}
				// methods
				for (IMethod method : iType.getMethods()) {
					countData.add(convert.apply(iType), method);
				}
				// fields
				for (IField field : iType.getFields()) {
					countData.add(convert.apply(iType), field);
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private List<String> splitToWord(String str) {
		// thanks to -> http://akisute3.hatenablog.com/entry/20111217/1324109628
		String[] strs = str.split("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[a-z])(?=[A-Z])");
		return Arrays.asList(strs);
	}
	
	private List<String> toSmallCaracters(List<String> strList){
		return strList.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
	}	
}
