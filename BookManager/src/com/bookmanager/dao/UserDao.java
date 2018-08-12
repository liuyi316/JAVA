package com.bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bookmanager.util.DBUtil;

import model.User;

public class UserDao {
	
	private DBUtil dbutil = new DBUtil();
	
	public User login(User user) throws Exception{
		
		Connection con = dbutil.getConnection();
		User resultUser = null;
		String sql = "select * from tb_user where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUsername(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		con.close();
		return resultUser;
		
	}
	

}
