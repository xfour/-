package com.xfour.dao;

import java.util.List;

import com.xfour.bean.User;

public interface UserDAO {
	
	//获得用户总数
	public int getTotal();
	
	//增加用户
	public void add(User user);
	
	//更新用户信息
	public void update(User user);
	
	//根据id删除指定用户
	public void delete(int id);
	
	//根据id获取相应用户
	public User get(int id);
	
	//查询所有用户
	public List<User> list();
	
	//分页查询用户信息
	public List<User> list(int start,int count);
	
	//判断此用户名的用户是否存在
	public boolean isExist(String name);
	
	//根据用户名获取相应用户
	public User get(String name);
	
	//获取为此用户名和密码的用户
	public User get(String name,String password);
	
}
