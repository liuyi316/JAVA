package com.bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookmanager.util.DBUtil;

import model.BooksBeReaded;
import model.BooksToRead;

public class BooksReadedDao {
	
	private DBUtil dbutil = new DBUtil();
	
	public List<BooksBeReaded> show() throws Exception{
		
		//创建一个返回列表的方法
		
		Connection con = dbutil.getConnection();
		List<BooksBeReaded> booksreaded = new ArrayList<BooksBeReaded>();	//实例化一个bookstoread

		//查询
		String sql = "select * from tb_booksbereaded order by date;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			BooksBeReaded btr = new BooksBeReaded();
			btr.setBookname(rs.getString("bookname"));
			btr.setAuthor(rs.getString("author"));
			btr.setType(rs.getString("type"));
			btr.setDate(rs.getString("date"));
			btr.setScore(rs.getString("score"));
			
			booksreaded.add(btr);	//读出来的一行加入到bookstoread列表中
		}
		con.close();	//关闭连接
		return booksreaded;
		
	}
	public void add(BooksBeReaded booksbereaded) throws Exception {
		Connection con = dbutil.getConnection();
		String bookname = booksbereaded.getBookname();
		String author = booksbereaded.getAuthor();
		String type = booksbereaded.getType();
		String date = booksbereaded.getDate();
		String score = booksbereaded.getScore();
		
		String sql = "insert into tb_booksbereaded(bookname,author,type,date,score) values(?,?,?,?,?);";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		pstmt.setString(2, author);
		pstmt.setString(3, type);
		pstmt.setString(4, date);
		pstmt.setString(5, score);
		boolean rs = pstmt.execute();  //注意不是executeQuery，update,insert的更新语句，应该用statement的execute()方法
		con.close();
	}
	

}
