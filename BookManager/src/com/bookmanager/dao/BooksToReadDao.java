package com.bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookmanager.util.DBUtil;

import model.BooksToRead;

public class BooksToReadDao {
	
	private DBUtil dbutil = new DBUtil();
	
	public List<BooksToRead> show() throws Exception{
		
		//创建一个返回列表的方法
		
		Connection con = dbutil.getConnection();
		List<BooksToRead> bookstoread = new ArrayList<BooksToRead>();	//实例化一个bookstoread

		//查询
		String sql = "select * from tb_bookstoread;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			BooksToRead btr = new BooksToRead();
			btr.setBookname(rs.getString("bookname"));
			btr.setAuthor(rs.getString("author"));
			btr.setType(rs.getString("type"));
			
			bookstoread.add(btr);	//读出来的一行加入到bookstoread列表中
		}
		con.close();	//关闭连接
		return bookstoread;
		
	}
	
	public void add(BooksToRead bookstoread) throws Exception {
		Connection con = dbutil.getConnection();
		String bookname = bookstoread.getBookname();
		String author = bookstoread.getAuthor();
		String type = bookstoread.getType();
		String sql = "insert into tb_bookstoread(bookname,author,type) values(?,?,?);";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		pstmt.setString(2, author);
		pstmt.setString(3, type);
		boolean rs = pstmt.execute();  //注意不是executeQuery，update,insert的更新语句，应该用statement的execute()方法
		con.close();
	}
	
	public BooksToRead search(BooksToRead bookstoread) throws Exception{
		Connection con = dbutil.getConnection();
		String bookname = bookstoread.getBookname();
		String author = bookstoread.getAuthor();
		String type = bookstoread.getType();
		String sql = "select * from tb_bookstoread where bookname = ? and author = ? and type = ?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		pstmt.setString(2, author);
		pstmt.setString(3, type);
		ResultSet rs = pstmt.executeQuery();
		
		BooksToRead btr = new BooksToRead();
		while(rs.next()) {
			btr.setBookname(rs.getString("bookname"));
			btr.setAuthor(rs.getString("author"));
			btr.setType(rs.getString("type"));
			
		}
		
		con.close();
		return btr;
		
	}
	
	public void deleteBook(BooksToRead bookstoread) throws Exception{
		Connection con = dbutil.getConnection();
		String bookname = bookstoread.getBookname();
		String author = bookstoread.getAuthor();
		String type = bookstoread.getType();
		String sql = "delete from tb_bookstoread where bookname = ? and author = ? and type = ?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		pstmt.setString(2, author);
		pstmt.setString(3, type);
		pstmt.execute();
		con.close();
	}
	

}
