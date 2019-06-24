package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Order;
import com.xfour.bean.OrderItem;

public interface OrderItemDAO {
	
	//获取订单项总数
	public int getTotal();
    
	//新增订单项
    public void add(OrderItem orderItem);
    
    //更新订单项
    public void update(OrderItem orderItem);
    
    //删除订单项
    public void delete(int id);
    
    //获取订单项
    public OrderItem get(int id);
    
    //查询某个用户的订单项
    public List<OrderItem> listByUser(int uid);

    //分页查询某个用户的订单项
    public List<OrderItem> listByUser(int uid,int start,int count);

    //查询某个订单的订单项
    public List<OrderItem> listByOrder(int oid);

    //分页查询某个订单的订单项
    public List<OrderItem> listByOrder(int oid,int start,int count);

    //查询某个产品的所有订单项
    public List<OrderItem> listByProduct(int pid);

    //分页查询某个产品的订单项
    public List<OrderItem> listByProduct(int pid,int start,int count);

    //给相关订单设置相应属性
    public void fill(Order order);

    //给相关订单设置相应属性
    public void fill(List<Order> orders);
    
    //获取某个产品的总销量
    public int getSaleCount(int pid);

    //根据用户id获取购物车商品数量
	int getTotalByUser(int uid);
}
