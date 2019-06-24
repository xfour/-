package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Category;

public interface CategoryDAO {
	
	//获取分类总数
	public int getTotal();
	
	//增加分类信息
	public void add(Category category);
	
	//更新某个分类的信息
	public void update(Category category);
	
	//删除某个分类
	public void delete(int id);
	
	//根据id获取相应分类对象
	public Category get(int id);
	
	//获取所有分类信息
	public List<Category> list();
	
	//分页查询分类信息
	public List<Category> list(int start,int count);
	
}
