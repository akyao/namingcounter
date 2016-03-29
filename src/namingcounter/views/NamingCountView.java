package namingcounter.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import namingcounter.dto.CountData;
import namingcounter.dto.CountData.CountDataItem;
import namingcounter.dto.CountData.SortBy;

import java.util.List;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Viewのところ
 * 
 * @author akyao
 */
public class NamingCountView extends ViewPart {

	private static final CountData.SortBy DEFAULT_SORT = CountData.SortBy.WORD;
	
	public static final String ID = "namingcounter.views.NamingCountView";

	private TableViewer viewer;
	
	private Table table;
	
	private CountData countData;
	
	private String[] sumData;
	
	// sort用 TODO 名前がなげえ
	private String lastSortColumnHeaderText;
	private boolean lastTimeSortIsDesc = false;

	public NamingCountView() {
	}

	/**
	 * 与えられたデータをViewに表示します
	 * @param countData
	 */
	public void render(CountData countData) {
		List<CountDataItem> dataItems = countData.getItems(DEFAULT_SORT, false);
		
		this.countData = countData;
		this.lastSortColumnHeaderText = "word";
		this.sumData = new String[]{
				"-",
				String.valueOf(dataItems.stream().mapToInt(x -> x.countInClass).sum()),
				String.valueOf(dataItems.stream().mapToInt(x -> x.countInMethod).sum()),
				String.valueOf(dataItems.stream().mapToInt(x -> x.countInField).sum()),
				String.valueOf(dataItems.stream().mapToInt(x -> x.total()).sum())
		};
		
		render(dataItems);
	}
	
	private void render(List<CountDataItem> dataItems) {
		
		// 表示を初期化
		table.removeAll();
		
		for (CountDataItem dataItem : dataItems) {
			// 行分のデータ
			String[] data ={
					dataItem.word,
					Integer.toString(dataItem.countInClass),
					Integer.toString(dataItem.countInMethod),
					Integer.toString(dataItem.countInField),
					Integer.toString(dataItem.total())
			};
			
			TableItem tableItem = new TableItem(table, SWT.NULL);
			tableItem.setText(data);
			
			if ("fuck".equals(dataItem.word)) {
				tableItem.setBackground(new Color(table.getDisplay(), new RGB(255,0,0)));	
			}
		}
		
		// 合計
		TableItem tableItem = new TableItem(table, SWT.NULL);
		tableItem.setText(sumData);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
	    Composite container = new Composite(parent, SWT.NONE);
	    container.setLayout(new FillLayout());

	    viewer = new TableViewer(container, SWT.FULL_SELECTION | SWT.BORDER);
	    viewer.setContentProvider(new ArrayContentProvider());
	    viewer.setInput(getViewSite());
	    
	    initTable(viewer);
	}

	private void initTable(final TableViewer viewer) {
		TableLayout layout = new TableLayout();
		
	    table = viewer.getTable();
	    table.setLayout(layout);
	    table.setHeaderVisible(true); 
	    table.setLinesVisible(true);

	    // カラムをセット
	    initColumn(layout, new ColumnWeightData(3), "word", 0, false);
	    initColumn(layout, new ColumnWeightData(2), "class", 1, true);
	    initColumn(layout, new ColumnWeightData(2), "method", 2, true);
	    initColumn(layout, new ColumnWeightData(2), "field", 3, true);
	    initColumn(layout, new ColumnWeightData(2), "total", 4, true);
	}
	
	private void initColumn(TableLayout layout, ColumnLayoutData layoutData,
			String header, int index, boolean paddingRight) 
	{
		int padding = paddingRight ? SWT.RIGHT : SWT.LEFT;
		
		layout.addColumnData(layoutData);
		
		TableColumn tc = new TableColumn(table, padding, index);
        tc.setText(header);
        tc.addSelectionListener(new TableHeaderListener());
	}
	
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	/**
	 * テーブルのカラムをクリックされた時のソート処理
	 */
	private class TableHeaderListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			
			// ソート方法を取得する
			TableColumn column = (TableColumn) e.getSource();
			SortBy sortBy = getSortBy(column.getText());
			// descするのは、 (前と同じカラムをクリック)かつ(前のソートがdescじゃない)の場合
			boolean isDesc = lastSortColumnHeaderText.equals(column.getText()) && !lastTimeSortIsDesc;

			// データを再表示
			render(countData.getItems(sortBy, isDesc));
			
			// 次回descソートするために記録しておく
			lastSortColumnHeaderText = column.getText();
			lastTimeSortIsDesc = isDesc;
		}
	}
	
	private static SortBy getSortBy(String colHeaderTxt) {
		return SortBy.valueOf(colHeaderTxt.toUpperCase());
	}
	
	// SWG系のサブクラスを使おうとすると実行時エラーになる。これ豆知識な
//	private class TableColumnWithSortBy extends TableColumn{
//		private SortBy sortBy;
//		public TableColumnWithSortBy(Table table, int style, int index, SortBy sortBy) {
//			super(table, style, index);
//			this.sortBy = sortBy;
//		}
//	}
}
