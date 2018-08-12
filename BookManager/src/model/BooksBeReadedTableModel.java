package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BooksBeReadedTableModel extends AbstractTableModel{
	
	public String[] columnNames = {"书名" ,"作者" ,"类型", "看完日期", "评分"};
	private List<BooksBeReaded> booksbereaded = null;
	public BooksBeReadedTableModel(List<BooksBeReaded> booksbereaded) {
		this.booksbereaded = booksbereaded;
	}

	@Override
	public int getRowCount() {
		return booksbereaded.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		BooksBeReaded brd = booksbereaded.get(rowIndex);
		switch(columnIndex) {
			case 0:
				return brd.getBookname();
			case 1:
				return brd.getAuthor();
			case 2:
				return brd.getType();
			case 3:
				return brd.getDate();
			default:
				return brd.getScore();
		}
	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

}
