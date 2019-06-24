package com.xfour.dao;

import java.util.List;

import com.xfour.bean.Product;
import com.xfour.bean.PropertyValue;

public interface PropertyValueDAO {
	
		//获取属性值总数
		public int getTotal();
		
		//增加属性值信息
		public void add(PropertyValue propertyValue);
		
		//更新某个属性值的信息
		public void update(PropertyValue propertyValue);
		
		//删除某个属性值
		public void delete(int id);
		
		//根据id获取相应属性值对象
		public PropertyValue get(int id);

		//获取所有属性值
		public List<PropertyValue> list();
		
		//分页查询
		public List<PropertyValue> list(int start,int count);
		
		//获取为此属性id和产品id的属性值对象
		public PropertyValue get(int ptid,int pid);
		
		//获取为此pid的所有属性值
		public List<PropertyValue> list(int pid);
		
		//分页查询特定pid的相应属性值
		public List<PropertyValue> list(int pid,int start,int count);
		
		//初始化propertyValue对象
		public void init(Product product);

}
