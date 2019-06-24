package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Order;
import com.xfour.bean.OrderItem;
import com.xfour.dao.OrderItemDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderItemDAOImpl implements OrderItemDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from orderitem";
			
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
	public void add(OrderItem orderItem) {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values(null,?,?,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			//新建订单项时，还没生成相应订单
			if(null == orderItem.getOrder())
				ps.setInt(2, -1);
			else
				ps.setInt(2, orderItem.getOrder().getId());
			ps.setInt(1, orderItem.getProduct().getId());
			ps.setInt(3, orderItem.getUser().getId());
			ps.setInt(4, orderItem.getNumber());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				orderItem.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		String sql = "update orderitem set pid=?,oid=?,uid=?,number=? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			//如果更新时还没有生成订单项，则将oid的值保存为-1
			if(null == orderItem.getOrder())
				ps.setInt(2, -1);
			else
				ps.setInt(2, orderItem.getOrder().getId());
			ps.setInt(1, orderItem.getProduct().getId());
			ps.setInt(3, orderItem.getUser().getId());
			ps.setInt(4, orderItem.getNumber());
			ps.setInt(5, orderItem.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from orderitem where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem get(int id) {
		// TODO Auto-generated method stub
		OrderItem orderItem = new OrderItem();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from orderitem where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				orderItem.setId(id);
				orderItem.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				if(-1!=rs.getInt("oid"))
					orderItem.setOrder(new OrderDAOImpl().get(rs.getInt("oid")));
				orderItem.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				orderItem.setNumber(rs.getInt("number"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public List<OrderItem> listByUser(int uid) {
		// TODO Auto-generated method stub
		return listByUser(uid,0,Short.MAX_VALUE);
	}

	@Override
	public List<OrderItem> listByUser(int uid, int start, int count) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItems = new ArrayList();
		String sql = "select * from orderitem where uid=? and oid=-1 order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, uid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("id"));
				orderItem.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				
				int oid = rs.getInt("oid");
				if(-1!=oid) {
					orderItem.setOrder(new OrderDAOImpl().get(oid));	
				}
				orderItem.setUser(new UserDAOImpl().get(uid));
				orderItem.setNumber(rs.getInt("number"));
				orderItems.add(orderItem);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}
	
	@Override
	public int getTotalByUser(int uid) {
		// TODO Auto-generated method stub
		String sql = "select sum(number) from orderitem where uid=? and oid=-1";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<OrderItem> listByOrder(int oid) {
		// TODO Auto-generated method stub
		return listByOrder(oid,0,Short.MAX_VALUE);
	}

	@Override
	public List<OrderItem> listByOrder(int oid, int start, int count) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItems = new ArrayList();
		String sql = "select * from orderitem where oid=? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, oid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("id"));
				orderItem.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				if(oid!=-1)
					orderItem.setOrder(new OrderDAOImpl().get(oid));
				orderItem.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				orderItem.setNumber(rs.getInt("number"));
				orderItems.add(orderItem);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public List<OrderItem> listByProduct(int pid) {
		// TODO Auto-generated method stub
		return listByProduct(pid,0,Integer.MAX_VALUE);
	}

	@Override
	public List<OrderItem> listByProduct(int pid, int start, int count) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItems = new ArrayList();
		String sql = "select * from orderitem where pid=? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, pid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("id"));
				orderItem.setProduct(new ProductDAOImpl().get(pid));
				int oid = rs.getInt("oid");
				if(-1!=oid) {
					orderItem.setOrder(new OrderDAOImpl().get(oid));	
				}
				orderItem.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				orderItem.setNumber(rs.getInt("number"));
				
				orderItems.add(orderItem);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public void fill(Order order) {
		// TODO Auto-generated method stub
		int total = 0;
		int totalNumer = 0;
		List<OrderItem> orderItems = listByOrder(order.getId());
		for(OrderItem orderItem : orderItems) {
			int count = orderItem.getNumber();
			totalNumer += count;//获取该订单的产品总数量
			total += count*orderItem.getProduct().getDiscountPrice();//获取该订单的总金额
		}
		order.setTotal(total);
		order.setTotalNumber(totalNumer);
		order.setOrderItems(orderItems);
	}

	@Override
	public void fill(List<Order> orders) {
		// TODO Auto-generated method stub
		for(Order order:orders) {
			fill(order);
		}
	}

	@Override
	public int getSaleCount(int pid) {
		// TODO Auto-generated method stub
		int saleCount = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select sum(number) from orderitem where pid="+pid;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				saleCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saleCount;
	}
	
}
