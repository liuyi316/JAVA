package model;

import javax.swing.table.AbstractTableModel;

//待看书单表格模型，分析是什么模型，封装起来

public class BooksToRead  {
	
	private String bookname;
	private String author;
	private String type;
	
	public BooksToRead(){
		super();
	}
	public BooksToRead(String bookname,String author,String type){
		this.bookname = bookname;
		this.author = author;
		this.type = type;
	}
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
