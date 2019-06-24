package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Property;

public interface PropertyDAO {

	//获得属性总数
	public int getTotal(int cid);
	
	//增加属性
	public void add(Property property);
	
	//更新属性信息
	public void update(Property property);
	
	//根据id删除指定属性
	public void delete(int id);
	
	//根据id获取相应属性
	public Property get(int id);
	
	//查询所有属性
	public List<Property> list(int cid);

	//分页查询属性
	public List<Property> list(int cid,int start,int count);
	
}
