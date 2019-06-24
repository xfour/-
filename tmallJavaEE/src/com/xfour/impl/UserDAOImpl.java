package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.User;
import com.xfour.dao.UserDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserDAOImpl implements UserDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from user";
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user values(null,?,?,0)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				user.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql = "update user set name = ?, password = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from user where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from user where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				User user = new User();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from user order by id desc";
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> list(int start, int count) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from user order by id desc limit "+start+","+count;
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		User user = get(name);
		return user!=null;
	}

	@Override
	public User get(String name) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from user where name = '" + name +"'";
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(name);
				user.setPassword(rs.getString("password"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User get(String name, String password) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from user where name = '" + name + "' and password = '" + password +"'";
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(name);
				user.setPassword(password);
				user.setIdentify(rs.getString("identify"));
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
