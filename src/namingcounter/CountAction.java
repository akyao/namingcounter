package namingcounter;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import namingcounter.dto.NamingData;
import namingcounter.views.NamingCountView;

/**
 * 「カウント」メニュー
 */
public class CountAction implements IObjectActionDelegate {

//	private IWorkbenchPart targetPart;
	private ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO なにこれ？
//		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			// 計算
			NamingData ore = Counter.count(selection);

			// 表示
			IViewPart viewPart = window.getActivePage().showView("namingcounter.views.NamingCountView", null, IWorkbenchPage.VIEW_VISIBLE);
			if (viewPart instanceof NamingCountView) {
				((NamingCountView) viewPart).render(ore);
			}
		} catch(Exception ex){
			// TODO エラー処理
			ex.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
