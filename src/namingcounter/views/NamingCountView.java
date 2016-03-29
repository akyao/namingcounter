package namingcounter.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import namingcounter.dto.Ore;
import namingcounter.dto.Ore.OreOre;

import java.util.Map.Entry;
import java.util.function.IntFunction;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Viewのところ
 * 
 * @author akyao
 */
public class NamingCountView extends ViewPart {

	public static final String ID = "namingcounter.views.NamingCountView";

	private TableViewer viewer;
	
	private Table table;

	public NamingCountView() {
	}

	public void yesTakasu(Ore ore) {
		
		for (OreOre oreore : ore.getOreList()) {
			TableItem item = new TableItem(table, SWT.NULL);
			String[] data ={
					oreore.word,
					Integer.toString(oreore.countInClass),
					Integer.toString(oreore.countInMethod),
					Integer.toString(oreore.countInField),
					Integer.toString(oreore.total())
			};
			item.setText(data);	
			if ("fuck".equals(oreore.word)) {
				item.setBackground(new Color(table.getDisplay(), new RGB(255,0,0)));	
			}
		}
	}
	
	public void createPartControl(Composite parent) {
		
	    Composite container = new Composite(parent, SWT.NONE);
	    container.setLayout(new FillLayout());

	    viewer = new TableViewer(container, SWT.FULL_SELECTION | SWT.BORDER);
	    viewer.setContentProvider(new ArrayContentProvider());
	    viewer.setInput(getViewSite());
	    
	    initTable(viewer);
	}

	private void initTable(final TableViewer viewer) {
	    table = viewer.getTable();
	    TableLayout layout = new TableLayout();
	    table.setLayout(layout);
	    table.setHeaderVisible(true); //ヘッダを表示する 
	    table.setLinesVisible(true);  //ラインを表示する

	    // カラムをセット
	    initColumn(layout, table, new ColumnWeightData(3), "word", 0);
	    initColumn(layout, table, new ColumnWeightData(2), "class", 1);
	    initColumn(layout, table, new ColumnWeightData(2), "method", 2);
	    initColumn(layout, table, new ColumnWeightData(2), "field", 3);
	    initColumn(layout, table, new ColumnWeightData(2), "total", 4);
	}
	
	private static void initColumn(TableLayout layout, Table table, ColumnLayoutData layoutData, String header, int index) {
		layout.addColumnData(layoutData);
        TableColumn tc = new TableColumn(table, SWT.NONE, index);
        tc.setText(header);
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
