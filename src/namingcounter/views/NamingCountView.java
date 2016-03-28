package namingcounter.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;

import namingcounter.dto.Ore;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;


public class NamingCountView extends ViewPart {

	public static final String ID = "namingcounter.views.NamingCountView";

	private TabFolder tabFolder;
	private Table fileTable;
//	private Table					categoryTable;
	
	private TableViewer viewer;
	private Action action1;
	
	private Action doubleClickAction;
	
//	class ViewContentProvider implements IStructuredContentProvider {
//		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
//		}
//		public void dispose() {
//		}
//		public Object[] getElements(Object parent) {
//			return new String[] { "One", "Two", "Three" };
//		}
//	}
	
//	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
//		public String getColumnText(Object obj, int index) {
//			return getText(obj);
//		}
//		public Image getColumnImage(Object obj, int index) {
//			return getImage(obj);
//		}
//		public Image getImage(Object obj) {
//			return PlatformUI.getWorkbench().
//					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
//		}
//	}
//	
//	class NameSorter extends ViewerSorter {
//	}

	/**
	 * The constructor.
	 */
	public NamingCountView() {
	}

	public void yesTakasu(Ore ore) {
//		TableItem item = new TableItem(fileTable, SWT.NULL);
//		TableItem item = new TableItem(fileTable, SWT.NULL);
//		item.setText(data);
		String[] categoryData = {
				"1",
				"2",
				"3",
				"4"};

//		TableItem categoryItem = new TableItem(fileTable, SWT.NULL);
//		categoryItem.setText(categoryData);
		
	}
	
	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		System.out.println("createPartControl called");

		TabFolder tab = new TabFolder(parent, SWT.NONE);

		TabItem tabItem1 = new TabItem(tab, SWT.NULL);
		tabItem1.setText("aaaaa");

		Composite composite1 = new Composite(tab, SWT.NULL);
		composite1.setLayout(new FillLayout());
		tabItem1.setControl(composite1);
		
		//		tab.setLayoutData(new GridData(GridData.FILL_BOTH));
//		{ // 1つめのタブ
//			TabItem item = new TabItem(tab, SWT.NONE);
//			item.setText("タブ1");
//
//			Composite composite = new Composite(tab, SWT.NONE);
//			composite.setLayout(new GridLayout());
//			item.setControl(composite);
//
//			Label label = new Label(composite, SWT.NONE);
//			label.setText("ラベル1");
//		}
//		{ // 2つめのタブ
//			TabItem item = new TabItem(tab, SWT.NONE);
//			item.setText("タブ2");
//
//			Composite composite = new Composite(tab, SWT.NONE);
//			composite.setLayout(new GridLayout());
//			item.setControl(composite);
//
//			Label label = new Label(composite, SWT.NONE);
//			label.setText("ラベル2");
//		}
		
//		tabFolder = new TabFolder(parent, SWT.NULL);
//
//		TabItem tabItem1 = new TabItem(tabFolder, SWT.NULL);
//		tabItem1.setText("tabItemTest");
		
//		Table table = new Table(parent,  SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
//		table.setHeaderVisible(true);	// 見出しを表示する
//		table.setLinesVisible(true);	// 行間の線を表示する

//		tabFolder = new TabFolder(parent, SWT.NULL);
		
//		Composite composite2 = new Composite(tabFolder, SWT.NULL);
//		composite2.setLayout(new FillLayout());
//		tabItem2.setControl(composite2);

//		categoryTable = new Table(composite2, SWT.FULL_SELECTION | SWT.MULTI);
//		tabFolder = new TabFolder(parent, SWT.NULL);
//		tabFolder = new TabFolder(parent, SWT.NULL);
		
//		Composite composite1 = new Composite(tabFolder, SWT.NULL);
//		composite1.setLayout(new FillLayout());
		
//		fileTable = new Table(composite1, SWT.FULL_SELECTION | SWT.MULTI);
		
//		{
////			Table table = new Table(composite2,  SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
//			TableColumn col1 = new TableColumn(table, SWT.LEFT);
//			col1.setText("name");
//			col1.setWidth(140);
//
//			TableColumn col2 = new TableColumn(table, SWT.LEFT);
//			col2.setText("type");
//			col2.setWidth(100);
//
//			TableColumn col3 = new TableColumn(table, SWT.LEFT);
//			col3.setText("description");
//			col3.setWidth(130);
//		}
		
//		String[][] persons = {
//				{"Venjamin", "man", "20"},
//				{"Bobby", "man", "19"},
//				{"Sarry", "woman", "34"},
//				{"Anna", "woman", "27"}
//		};
		
//		Table table = new Table(this, SWT.FULL_SELECTION);
		
//		Table table = new Table(composite2,  SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		
//		TableColumn nameColumn = new TableColumn(table, SWT.LEFT);
//		nameColumn.setWidth(150);
//		nameColumn.setText("name");
//		TableColumn sexColumn = new TableColumn(table, SWT.CENTER);
//		sexColumn.setWidth(100);
//		sexColumn.setText("sex");
//		TableColumn ageColumn = new TableColumn(table, SWT.RIGHT);
//		ageColumn.setWidth(50);
//		ageColumn.setText("age");
		
//		for (byte i=0 ; i < persons.length ; i++) {
//			TableItem item = new TableItem(table, SWT.NONE);
//			item.setText(persons[i]);
//		}
		
//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter());
//		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "namingcounter.viewer");
		getSite().setSelectionProvider(viewer);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		System.out.println("hookContextMenu called");
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				NamingCountView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"NamingCountView",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
