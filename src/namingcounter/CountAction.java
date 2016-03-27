package namingcounter;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import namingcounter.views.NamingCountView;

/**
 * 「ステップ数をカウント」メニュー
 */
public class CountAction implements IObjectActionDelegate {

//	private IWorkbenchPart targetPart;
	private ISelection selection;

	/* (非 Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
//		this.targetPart = targetPart;
	}

	/* (非 Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		System.out.println("koko");
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			//window.getActivePage().showView("namingcounter.NamingCountView");
			IViewReference[] views = window.getActivePage().getViewReferences();
			
			System.out.println("aaa" + views.length);
			for(int i=0;i<views.length;i++){
				IViewPart view = views[i].getView(false);
				System.out.println(view);
				if(view instanceof NamingCountView){
					((NamingCountView)view).count(selection);
				}
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/* (非 Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
