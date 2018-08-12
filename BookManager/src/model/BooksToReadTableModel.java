package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BooksToReadTableModel extends AbstractTableModel{
	
	public String[] columnNames = {"书名" ,"作者" ,"类型"	};
	private List<BooksToRead> bookstoread = null;
	public BooksToReadTableModel(List<BooksToRead> bookstoread) {
		this.bookstoread = bookstoread;
	}

	@Override
	public int getRowCount() {
		return bookstoread.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		BooksToRead btr = bookstoread.get(rowIndex);
		switch(columnIndex) {
			case 0:
				return btr.getBookname();
			case 1:
				return btr.getAuthor();
			default:
				return btr.getType();
		}
	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

}
