package namingcounter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
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

import namingcounter.dto.Ore;

public class Counter {

	private Ore ore = new Ore();
	private IStructuredSelection iSel;

	public static Ore count(ISelection iSel) {
		if (iSel != null && iSel instanceof IStructuredSelection) {
			Counter c = new Counter((IStructuredSelection)iSel);
			c.doCount();
			return c.ore;	
		}
		return new Ore();
	}
	
	private Counter(IStructuredSelection iSel){
		this.iSel = iSel;
	}
	
	private void doCount() {
		@SuppressWarnings("unchecked")
		Iterator<Object> ite = iSel.iterator();
		
		while (ite.hasNext()) {
			Object obj = ite.next();
			if (obj instanceof ICompilationUnit) {
				// Javaソースファイル（JDT）
				ICompilationUnit file = (ICompilationUnit)obj;
				fuck(file);
			} else if (obj instanceof IPackageFragment) {
				// Javaパッケージ（JDT）
				try{
					IPackageFragment pkg = (IPackageFragment)obj;	
					for (ICompilationUnit file : pkg.getCompilationUnits()) {
						fuck(file);
					}
				}catch(Exception e){
					throw new RuntimeException(e);
				}
//			} else if (obj instanceof IFile) {
//				// ファイル
//				System.out.println(obj);
			} else if (obj instanceof IContainer) {
				try{
					IContainer dir = (IContainer) obj;
					fuckfuck(dir);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	private void fuckfuck(IContainer container) {
		try{
			for (IResource res : container.members()) {
				if (res instanceof IContainer) {
					fuckfuck((IContainer) res);
				} else if (res instanceof ICompilationUnit){
					fuck((ICompilationUnit) res);
				} else if (res instanceof File) {
					File javaFile = (File) res;
					IJavaElement jEle = JavaCore.create(javaFile);
					ICompilationUnit unit = (ICompilationUnit) jEle.getAncestor(IJavaElement.COMPILATION_UNIT);
					if(unit != null) {
						for (IType it : unit.getTypes()) {
							// top level class
							ore.add(toSmallCaracters(splitToWord(it.getElementName())), it);
							
							// inner class
							for (IType it2 : it.getTypes()) {
								// TODO 再帰
								ore.add(toSmallCaracters(splitToWord(it2.getElementName())), it2);
							}
							
							// methods
							System.out.println(it.getElementName());
							for (IMethod method : it.getMethods()) {
								ore.add(toSmallCaracters(splitToWord(method.getElementName())), method);
							}
							
							// fields
							for (IField field : it.getFields()) {
								ore.add(toSmallCaracters(splitToWord(field.getElementName())), field);
							}
						}
					}
				} else {
					System.out.println(res.getClass());
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
