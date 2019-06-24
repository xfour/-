package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.ProductImage;
import com.xfour.dao.ProductImageDAO;
import com.xfour.util.DBUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductImageDAOImpl implements ProductImageDAO{

	public static final String type_single = "type_single";//商品单个图片
	public static final String type_detail = "type_detail";//商品详情图片
	
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from productimage";
			
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
	public void add(ProductImage productImage) {
		// TODO Auto-generated method stub
		String sql = "insert into productimage values(null,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, productImage.getProduct().getId());
			ps.setString(2, productImage.getType());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				productImage.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(ProductImage productImage) {
		// TODO Auto-generated method stub
		String sql = "update productimage set pid = ?, type = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, productImage.getProduct().getId());
			ps.setString(2, productImage.getType());
			ps.setInt(3, productImage.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from productimage where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ProductImage get(int id) {
		// TODO Auto-generated method stub
		ProductImage productImage = new ProductImage();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from productimage where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				productImage.setId(id);
				productImage.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				productImage.setType(rs.getString("type"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productImage;
	}

	@Override
	public List<ProductImage> list(int pid, String type) {
		// TODO Auto-generated method stub
		return list(pid,type,0,Short.MAX_VALUE);
	}

	@Override
	public List<ProductImage> list(int pid, String type, int start, int count) {
		// TODO Auto-generated method stub
		List<ProductImage> productImages = new ArrayList();
		String sql = "select * from productimage where pid = ? and type = ? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, pid);
			ps.setString(2, type);
			ps.setInt(3, start);
			ps.setInt(4, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductImage productImage = new ProductImage();
				productImage.setId(rs.getInt("id"));
				productImage.setProduct(new ProductDAOImpl().get(rs.getInt("pid")));
				productImage.setType(rs.getString("type"));
				productImages.add(productImage);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productImages;
	}

}
