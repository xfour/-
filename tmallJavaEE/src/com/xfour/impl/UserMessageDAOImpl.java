package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xfour.bean.User;
import com.xfour.bean.UserMessage;
import com.xfour.dao.UserMessageDAO;
import com.xfour.util.DBUtil;

public class UserMessageDAOImpl implements UserMessageDAO{

	@Override
	public UserMessage get(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from usermessage where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				UserMessage userMessage = new UserMessage();
				userMessage.setId(id);
				userMessage.setUid(rs.getInt("uid"));
				userMessage.setUserName(rs.getString("userName"));
				userMessage.setAddress(rs.getString("address"));
				userMessage.setPost(rs.getString("post"));
				userMessage.setReceiver(rs.getString("receiver"));
				userMessage.setMobile(rs.getString("mobile"));
				return userMessage;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserMessage get(String username) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from usermessage where userName = '" + username +"'";
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				UserMessage userMessage = new UserMessage();
				userMessage.setId(rs.getInt("id"));
				userMessage.setUid(rs.getInt("uid"));
				userMessage.setUserName(username);
				userMessage.setAddress(rs.getString("address"));
				userMessage.setPost(rs.getString("post"));
				userMessage.setReceiver(rs.getString("receiver"));
				userMessage.setMobile(rs.getString("mobile"));
				return userMessage;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(UserMessage userMessage) {
		// TODO Auto-generated method stub
		String sql = "insert into usermessage values(null,?,?,null,null,null,null)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, userMessage.getUid());
			ps.setString(2, userMessage.getUserName());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				userMessage.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(UserMessage userMessage) {
		// TODO Auto-generated method stub
		String sql = "update usermessage set address=?,post=?,receiver=?,mobile=? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, userMessage.getAddress());
			ps.setString(2, userMessage.getPost());
			ps.setString(3, userMessage.getReceiver());
			ps.setString(4, userMessage.getMobile());
			ps.setInt(5, userMessage.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(User user) {
		// TODO Auto-generated method stub
		try {
			UserMessage userMessage = new UserMessage();
			userMessage.setUid(user.getId());
			userMessage.setUserName(user.getName());
			
			add(userMessage);
		}catch(Exception e) {
			
		}
	}

}
