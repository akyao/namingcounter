package namingcounter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import namingcounter.dto.CountData;

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
	
	// TODO 何を選択すると、どのファイル、フォルダが、どうやって扱われているのかよくわかっていない
	private void count() {
		@SuppressWarnings("unchecked")
		Iterator<Object> ite = iSel.iterator();
		
		while (ite.hasNext()) {
			Object obj = ite.next();
			if (obj instanceof ICompilationUnit) {
				// Javaソース
				count((ICompilationUnit) obj);
			} else if (obj instanceof IPackageFragment) {
				// Javaパッケージ（JDT）
				try{
					IPackageFragment pkg = (IPackageFragment)obj;	
					for (ICompilationUnit file : pkg.getCompilationUnits()) {
						count(file);
					}
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			} else if (obj instanceof IFile) {
				// ファイル
				count((IFile) obj);
			} else if (obj instanceof IContainer) {
				// ディレクトリ
				count((IContainer) obj);
			}
		}
	}
	
	private void count(IContainer container) {
		try {
			for (IResource resource : container.members()) {
				if (resource instanceof IContainer) {
					count((IContainer) resource);
				} else if (resource instanceof ICompilationUnit){
					count((ICompilationUnit) resource);
				} else if (resource instanceof IFile) {
					count((IFile) resource);
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
		try{
			if(unit != null) {
				for (IType it : unit.getTypes()) {
					// top level class
					countData.add(toSmallCaracters(splitToWord(it.getElementName())), it);
					// inner class
					for (IType it2 : it.getTypes()) {
						// TODO 再帰
						countData.add(toSmallCaracters(splitToWord(it2.getElementName())), it2);
					}
					// methods
					for (IMethod method : it.getMethods()) {
						countData.add(toSmallCaracters(splitToWord(method.getElementName())), method);
					}
					// fields
					for (IField field : it.getFields()) {
						countData.add(toSmallCaracters(splitToWord(field.getElementName())), field);
					}
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
	
	private void fuck(ICompilationUnit java) {
		System.out.println("----");
		System.out.println(java.getElementName());
	}
}
