package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Order;
import com.xfour.dao.OrderDAO;
import com.xfour.util.DBUtil;
import com.xfour.util.DateUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderDAOImpl implements OrderDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from order_";
			
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
	public void add(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into order_ values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, order.getUser().getId());
			ps.setString(2,order.getOrderCode());
			ps.setString(3, order.getAddress());
			ps.setString(4, order.getPost());
			ps.setString(5, order.getReceiver());
			ps.setString(6, order.getMobile());
			ps.setString(7, order.getUserMessage());
			ps.setTimestamp(8, DateUtil.d2t(order.getCreateDate()));
			ps.setTimestamp(9, DateUtil.d2t(order.getPayDate()));
			ps.setTimestamp(10, DateUtil.d2t(order.getDeliveryDate()));
			ps.setTimestamp(11, DateUtil.d2t(order.getConfirmDate()));
			ps.setString(12, order.getStatus());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				order.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		String sql = "update order_ set uid=?,orderCode=?,address=?,post=?,receiver=?,mobile=?,userMessage=?,createDate=?,payDate=?,deliveryDate=?,confirmDate=?,status=? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, order.getUser().getId());
			ps.setString(2,order.getOrderCode());
			ps.setString(3, order.getAddress());
			ps.setString(4, order.getPost());
			ps.setString(5, order.getReceiver());
			ps.setString(6, order.getMobile());
			ps.setString(7, order.getUserMessage());
			ps.setTimestamp(8, DateUtil.d2t(order.getCreateDate()));
			ps.setTimestamp(9, DateUtil.d2t(order.getPayDate()));
			ps.setTimestamp(10, DateUtil.d2t(order.getDeliveryDate()));
			ps.setTimestamp(11, DateUtil.d2t(order.getConfirmDate()));
			ps.setString(12, order.getStatus());
			ps.setInt(13, order.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from order_ where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		Order order = new Order();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from order_ where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				order.setId(id);
				order.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order.setStatus(rs.getString("status"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		return list(0,Short.MAX_VALUE);
	}

	@Override
	public List<Order> list(int start, int count) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList();
		String sql = "select * from order_ order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order.setStatus(rs.getString("status"));
				orders.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> list(int uid, String excluedStatus) {
		// TODO Auto-generated method stub
		return list(uid,excluedStatus,0,Short.MAX_VALUE);
	}

	@Override
	public List<Order> list(int uid, String excluedStatus, int start, int count) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList();
		String sql = "select * from order_ where uid=? and status!=? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, uid);
			ps.setString(2, excluedStatus);
			ps.setInt(3, start);
			ps.setInt(4, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				order.setOrderCode(rs.getString("orderCode"));
				order.setAddress(rs.getString("address"));
				order.setPost(rs.getString("post"));
				order.setReceiver(rs.getString("receiver"));
				order.setMobile(rs.getString("mobile"));
				order.setUserMessage(rs.getString("userMessage"));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				order.setCreateDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				order.setStatus(rs.getString("status"));
				orders.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
}
