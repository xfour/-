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
import com.xfour.bean.Review;
import com.xfour.dao.ReviewDAO;
import com.xfour.util.DBUtil;
import com.xfour.util.DateUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReviewDAOImpl implements ReviewDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from review";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
	}

	@Override
	public int getTotal(int pid) {
		// TODO Auto-generated method stub
		int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from review where pid = " + pid;
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
	}

	@Override
	public void add(Review review) {
		// TODO Auto-generated method stub
		String sql = "insert into review values(null,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
  
            ps.setInt(1, review.getProduct().getId());
            ps.setInt(2, review.getUser().getId());
            ps.setTimestamp(3,DateUtil.d2t(review.getCreateDate()));
            ps.setString(4, review.getContent());
            ps.setInt(5, review.getOid());
             
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                review.setId(id);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		String sql = "update review set content= ?, uid=?, pid=? , oid=?, createDate = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, review.getContent());
            ps.setInt(2, review.getUser().getId());
            ps.setInt(3, review.getProduct().getId());
            ps.setInt(4, review.getOid());
            ps.setTimestamp(5, DateUtil.d2t( review.getCreateDate()) );
            ps.setInt(6, review.getId());
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from review where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Review get(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from review where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				Review review = new Review();
				review.setId(id);
				review.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				review.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				review.setOid(rs.getInt("oid"));
				review.setCreateDate(DateUtil.d2t(rs.getDate("createDate")));
				review.setContent(rs.getString("content"));
				return  review;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void fillOrder(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem orderItem:orderItems) {
			//根据oid和pid查找评论id并保存到product中
			orderItem.getProduct().setRvid(getByOrderAndProduct(order.getId(),orderItem.getProduct().getId()));
		}
	}
	
	@Override
	public int getByOrderAndProduct(int oid,int pid) {
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from review where oid = " + oid + " and pid = " + pid ;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				return  rs.getInt("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Review> list(int pid) {
		// TODO Auto-generated method stub
		return list(pid,0,Short.MAX_VALUE);
	}

	@Override
	public List<Review> list(int pid, int start, int count) {
		// TODO Auto-generated method stub
		List<Review> reviews = new ArrayList();
		String sql = "select * from review where pid = ? order by id limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, pid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Review review = new Review();
				review.setId(rs.getInt("id"));
				review.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				review.setUser(new UserDAOImpl().get(rs.getInt("uid")));
				review.setOid(rs.getInt("oid"));
				review.setCreateDate(DateUtil.d2t(rs.getDate("createDate")));
				review.setContent(rs.getString("content"));
				reviews.add(review);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}
	
}
