package namingcounter;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import namingcounter.dto.Ore;
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
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			//window.getActivePage().showView("namingcounter.NamingCountView");
//			IViewReference[] views = window.getActivePage().getViewReferences();
//			IWorkbenchPage page = part.getSite().getWorkbenchWindow().getActivePage();

			// 計算
			Ore ore = Counter.count(selection);
//			System.out.println(ore);

			IViewPart viewPart = window.getActivePage().showView("namingcounter.views.NamingCountView", null, IWorkbenchPage.VIEW_VISIBLE);
			if (viewPart instanceof NamingCountView) {
				((NamingCountView) viewPart).yesTakasu(ore);
			}
			
			System.out.println(viewPart.getClass());

			// 表示
//			for(int i = 0; i < views.length; i++){
//				IViewPart view = views[i].getView(false);
//				if(view instanceof NamingCountView){
////					((NamingCountView)view)
//				}
//			}
		} catch(Exception ex){
			// TODO エラー処理
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
