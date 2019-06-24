package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Product;
import com.xfour.bean.Property;
import com.xfour.bean.PropertyValue;
import com.xfour.dao.PropertyValueDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyValueDAOImpl implements PropertyValueDAO{

	/*
	 * 首先根据所属分类获取所有属性
	 * 遍历所有属性判断该属性下产品的属性值对象是否存在
	 * 不存在则根据产品和属性新建属性值对象
	 */
	@Override
	public void init(Product product) {
		List<Property> propertys = new PropertyDAOImpl().list(product.getCategory().getId());
		for(Property property : propertys) {
			PropertyValue propertyValue = get(property.getId(),product.getId());
			if(null==propertyValue) {
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				add(propertyValue);
			}
		}
	}
	
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from productvalue";
			
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
	public void add(PropertyValue propertyValue) {
		// TODO Auto-generated method stub
		String sql = "insert into propertyvalue values(null,?,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, propertyValue.getProduct().getId());
			ps.setInt(2, propertyValue.getProperty().getId());
			ps.setString(3, propertyValue.getValue());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				propertyValue.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(PropertyValue propertyValue) {
		// TODO Auto-generated method stub
		String sql = "update propertyvalue set pid = ?, ptid = ?, value = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, propertyValue.getProduct().getId());
			ps.setInt(2, propertyValue.getProperty().getId());
			ps.setString(3, propertyValue.getValue());
			ps.setInt(4, propertyValue.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from propertyvalue where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PropertyValue get(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from propertyvalue where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				PropertyValue propertyValue = new PropertyValue();
				propertyValue.setId(id);
				propertyValue.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				propertyValue.setProperty(new PropertyDAOImpl().get(rs.getInt("ptid")));
				propertyValue.setValue(rs.getString("value"));
				return propertyValue;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PropertyValue> list() {
		// TODO Auto-generated method stub
		return list(0,Short.MAX_VALUE);
	}

	@Override
	public List<PropertyValue> list(int start, int count) {
		// TODO Auto-generated method stub
		List<PropertyValue> propertyValues = new ArrayList();
		String sql = "select * from propertyvalue order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			
			PropertyValue propertyValue = new PropertyValue();
			while(rs.next()) {
				propertyValue.setId(rs.getInt("id"));
				propertyValue.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				propertyValue.setProperty(new PropertyDAOImpl().get(rs.getInt("ptid")));
				propertyValue.setValue(rs.getString("value"));
				propertyValues.add(propertyValue);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyValues;
	}

	@Override
	public PropertyValue get(int ptid, int pid) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from propertyvalue where ptid=" + ptid+" and pid="+pid;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				PropertyValue propertyValue = new PropertyValue();
				propertyValue.setId(rs.getInt("id"));
				propertyValue.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				propertyValue.setProperty(new PropertyDAOImpl().get(rs.getInt("ptid")));
				propertyValue.setValue(rs.getString("value"));
				return propertyValue;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PropertyValue> list(int pid) {
		// TODO Auto-generated method stub
		return list(pid,0,Short.MAX_VALUE);
	}

	@Override
	public List<PropertyValue> list(int pid, int start, int count) {
		// TODO Auto-generated method stub
		List<PropertyValue> propertyValues = new ArrayList();
		String sql = "select * from propertyvalue where pid = ? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, pid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				PropertyValue propertyValue = new PropertyValue();
				propertyValue.setId(rs.getInt("id"));
				propertyValue.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				propertyValue.setProperty(new PropertyDAOImpl().get(rs.getInt("ptid")));
				propertyValue.setValue(rs.getString("value"));
				propertyValues.add(propertyValue);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyValues;
	}
	
}
