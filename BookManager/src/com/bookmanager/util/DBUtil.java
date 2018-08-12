package com.bookmanager.util;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.BooksToReadTableModel;

public class DBUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/bookmanager?useSSL=false";
	private static String userName = "root";
	private static String passWord = "admin123456";
	
	public static Connection getConnection() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, userName, passWord);
		return con;
	}
//	public static void main(String[] args) {
//		DBUtil dbutil = new DBUtil();
//		try {
//			dbutil.getConnection();
//			System.out.println("连接成功");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
