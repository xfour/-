package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Category;
import com.xfour.bean.Product;

public interface ProductDAO {
	
	//获取商品总数
	public int getTotal();
    
	//增加新商品
    public void add(Product product);
    
    //更新商品信息
    public void update(Product product);
    
    //删除某件商品
    public void delete(int id);
    
    //获取某件商品信息
    public Product get(int id);
    
    //获取某个分类的所有商品信息
    public List<Product> list(int cid);
    
    //分页查询某个分类的商品
    public List<Product> list(int cid, int start, int count);
    
    //获取所有商品信息
    public List<Product> list();
    
    //分页查询商品信息
    public List<Product> list(int start, int count);
    
    public void fill(List<Category> categorys);
    
    public void fill(Category category);
    
    public void fillByRow(List<Category> categorys);
    
    //设置商品默认图片
    public void setFirstProductImage(Product product);
    
    //设置某件商品评论和销售数量
    public void setSaleAndReviewNumber(Product product);
    
    //设置所有商品的评论和销售数量
    public void setSaleAndReviewNumber(List<Product> products);
    
    //通过关键字分页搜索相应商品
    public List<Product> search(String keyword, int start, int count);

    //通过分类栏里的小标题搜索相应商品
    public List<Product> searchSub(String keyword, int start, int count);
	
}
