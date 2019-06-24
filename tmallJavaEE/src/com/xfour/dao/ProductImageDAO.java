package com.xfour.dao;

import java.util.List;

import com.xfour.bean.ProductImage;

public interface ProductImageDAO {
	
		//获取产品图片总数
		public int getTotal();
		
		//增加产品图片信息
		public void add(ProductImage productImage);
		
		//更新某个产品图片的信息
		public void update(ProductImage productImage);
		
		//删除某个产品图片
		public void delete(int id);
		
		//根据id获取相应产品图片对象
		public ProductImage get(int id);

		//获取相应产品和类型的图片
		public List<ProductImage> list(int pid,String type);
		
		//分页查询
		public List<ProductImage> list(int pid,String type,int start,int count);
		
}
