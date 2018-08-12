package model;

public class BooksBeReaded {

	private String bookname;
	private String author;
	private String type;
	private String date;
	private String score;
	
	public BooksBeReaded(){
		super();
	}
	public BooksBeReaded(String bookname,String author,String type,String date,String score){
		this.bookname = bookname;
		this.author = author;
		this.type = type;
		this.date = date;
		this.score = score;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
