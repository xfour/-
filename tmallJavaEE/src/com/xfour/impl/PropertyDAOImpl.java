package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Category;
import com.xfour.bean.Property;
import com.xfour.dao.CategoryDAO;
import com.xfour.dao.PropertyDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyDAOImpl implements PropertyDAO{

	@Override
	public int getTotal(int cid) {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from property where cid="+cid;
			
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
	public void add(Property property) {
		// TODO Auto-generated method stub
		String sql = "insert into property values(null,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, property.getCategory().getId());
			ps.setString(2, property.getName());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				property.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Property property) {
		// TODO Auto-generated method stub
		String sql = "update property set cid = ?, name = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, property.getCategory().getId());
			ps.setString(2, property.getName());
			ps.setInt(3, property.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from property where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Property get(int id) {
		// TODO Auto-generated method stub
		Property property = new Property();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from property where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				property.setId(id);
				property.setName(rs.getString("name"));
				Category category = new CategoryDAOImpl().get(rs.getInt("cid"));
				property.setCategory(category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}

	@Override
	public List<Property> list(int cid) {
		// TODO Auto-generated method stub
		return list(cid,0,Short.MAX_VALUE);
	}

	@Override
	public List<Property> list(int cid,int start, int count) {
		// TODO Auto-generated method stub
		List<Property> propertys = new ArrayList();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from property where cid = "+cid+" order by id desc limit "+start+","+count;
			
			ResultSet rs = statement.executeQuery(sql);
			Category category = null;
			
			CategoryDAO categoryDAO = new CategoryDAOImpl();
			
			while(rs.next()) {
				Property property = new Property();
				property.setId(rs.getInt("id"));
				property.setName(rs.getString("name"));
				category = categoryDAO.get(rs.getInt("cid"));
				property.setCategory(category);
				propertys.add(property);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertys;
	}

}
