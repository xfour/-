package com.xfour.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xfour.bean.Category;
import com.xfour.bean.OrderItem;
import com.xfour.bean.Product;
import com.xfour.bean.ProductImage;
import com.xfour.dao.ProductDAO;
import com.xfour.util.DBUtil;
import com.xfour.util.DateUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductDAOImpl implements ProductDAO{

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = 0;
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select count(*) from product";
			
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
	public void add(Product product) {
		// TODO Auto-generated method stub
		String sql = "insert into product values(null,?,?,?,?,?,?,?)";
		// TODO Auto-generated method stub
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			//新建订单项时，还没生成相应订单
			ps.setString(1, product.getName());
			ps.setString(2, product.getSubTitle());
			ps.setFloat(3, product.getOrignalPrice());
			ps.setFloat(4, product.getDiscountPrice());
			ps.setInt(5, product.getStock());
			ps.setTimestamp(6, DateUtil.d2t(product.getCreateDate()));
			ps.setInt(7, product.getCategory().getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		String sql = "update product set name=?,subTitle=?,orignalPrice=?,discountPrice=?,stock=?,createDate=?,cid=? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			//如果更新时还没有生成订单项，则将oid的值保存为-1
			ps.setString(1, product.getName());
			ps.setString(2, product.getSubTitle());
			ps.setFloat(3, product.getOrignalPrice());
			ps.setFloat(4, product.getDiscountPrice());
			ps.setInt(5, product.getStock());
			ps.setTimestamp(6, DateUtil.d2t(product.getCreateDate()));
			ps.setInt(7, product.getCategory().getId());
			ps.setInt(8, product.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from product where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Product get(int id) {
		// TODO Auto-generated method stub
		Product product = new Product();
		try(Connection conn = DBUtil.getConnection();Statement statement = conn.createStatement()){
			String sql = "select * from product where id = " + id;
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subTitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setDiscountPrice(rs.getFloat("discountPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setCategory(new CategoryDAOImpl().get(rs.getInt("cid")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> list(int cid) {
		// TODO Auto-generated method stub
		return list(cid,0,Integer.MAX_VALUE);
	}

	@Override
	public List<Product> list(int cid, int start, int count) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList();
		String sql = "select * from product where cid=? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subTitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setDiscountPrice(rs.getFloat("discountPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setCategory(new CategoryDAOImpl().get(rs.getInt("cid")));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		return list(0,Integer.MAX_VALUE);
	}

	@Override
	public List<Product> list(int start, int count) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList();
		String sql = "select * from product order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subTitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setDiscountPrice(rs.getFloat("discountPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setCategory(new CategoryDAOImpl().get(rs.getInt("cid")));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void fill(List<Category> categorys) {
		// TODO Auto-generated method stub
		for(Category category : categorys) {
			fill(category);
		}
	}

	@Override
	public void fill(Category category) {
		// TODO Auto-generated method stub
		List<Product> products = list(category.getId());
		for(Product product:products) {
			setFirstProductImage(product);
		}
		category.setProducts(products);
	}

	@Override
	public void fillByRow(List<Category> categorys) {
		// TODO Auto-generated method stub
		int num=8;//每8个分为一行
		for(Category category : categorys) {
			List<Product> products = category.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for(int i=0;i<products.size();i+=num) {
				int maxSize = i+num;
				maxSize = maxSize>products.size()?products.size():maxSize;
				productsByRow.add(products.subList(i, maxSize));
			}
			category.setProductsByRow(productsByRow);
		}
	}

	@Override
	public void setFirstProductImage(Product product) {
		// TODO Auto-generated method stub
		List<ProductImage> productImages = new ProductImageDAOImpl().list(product.getId(), ProductImageDAOImpl.type_single,0,1);
		if(productImages.size()!=0) {
			product.setFirstProductImage(productImages.get(0).getId());
		}
	}

	@Override
	public void setSaleAndReviewNumber(Product product) {
		// TODO Auto-generated method stub
		int saleCount = 0;
		List<OrderItem> orderItems = new OrderItemDAOImpl().listByProduct(product.getId());
		if(null!=orderItems) {
			for(OrderItem orderItem : orderItems) {
				if(orderItem.getOrder()!=null)//还在购物车中的商品不能算进以售卖的商品数中
					saleCount += orderItem.getNumber();
			}
		}
		
		int reviewCount = new ReviewDAOImpl().getTotal(product.getId());

		product.setSaleCount(saleCount);
		product.setReviewCount(reviewCount);
		
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		// TODO Auto-generated method stub
		for(Product product:products) {
			setSaleAndReviewNumber(product);
		}
	}

	@Override
	public List<Product> search(String keyword, int start, int count) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList();
		String sql = "select * from product where name like ? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subTitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setDiscountPrice(rs.getFloat("discountPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setCategory(new CategoryDAOImpl().get(rs.getInt("cid")));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> searchSub(String keyword, int start, int count) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList();
		String sql = "select * from product where subTitle like ? order by id desc limit ?,?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subTitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setDiscountPrice(rs.getFloat("discountPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setCategory(new CategoryDAOImpl().get(rs.getInt("cid")));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	

}
