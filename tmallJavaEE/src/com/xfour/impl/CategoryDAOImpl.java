package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Category;
import com.xfour.dao.CategoryDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CategoryDAOImpl implements CategoryDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from category";
			
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
	public void add(Category category) {
		String sql = "insert into category values(null,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, category.getName());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				category.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		String sql = "update category set name = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from category where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		Category category = new Category();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from category where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				category.setId(id);
				category.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return list(0,Short.MAX_VALUE);
	}

	@Override
	public List<Category> list(int start, int count) {
		// TODO Auto-generated method stub
		List<Category> categorys = new ArrayList();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from category order by id desc limit "+start+","+count;
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categorys.add(category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorys;
	}
	
}
