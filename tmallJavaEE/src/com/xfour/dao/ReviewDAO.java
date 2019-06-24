package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Order;
import com.xfour.bean.Review;

public interface ReviewDAO {
	
		//获取总评论数
		public int getTotal();
		
		//获取某个产品的总评论数
		public int getTotal(int pid);
		
		//增加评论
		public void add(Review review);
		
		//更新评论
		public void update(Review review);
		
		//删除评论
		public void delete(int id);
		
		//根据id获取评论
		public Review get(int id);
		
		//根据产品id获取所有评论
		public List<Review> list(int pid);
		
		//分页查询相应产品的评论
		public List<Review> list(int pid,int start,int count);
		
		//给订单对象的相应产品填充相关评论信息
		public void fillOrder(Order order);
		
		//根据oid和pid查询相应订单号
		public int getByOrderAndProduct(int oid,int pid);
		
}
