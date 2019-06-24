package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Order;

public interface OrderDAO {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";
    
    //获取订单总数
    public int getTotal();
    
    //增加新订单
    public void add(Order order);
    
    //更新订单信息
    public void update(Order order);
    
    //删除某条订单
    public void delete(int id);
    
    //获取某条订单
    public Order get(int id);
    
    //获取所有订单
    public List<Order> list();
    
    //分页查询订单信息
    public List<Order> list(int start , int count);

    //查询指定用户的所有订单信息，不包括如已删除的订单，但是已删除的订单信息在服务器还有保存
    public List<Order> list(int uid , String excluedStatus);
    
    //分页查询指定用户的相应订单信息
    public List<Order> list(int uid , String excluedStatus , int start , int count);
    
    
}
