package com.xfour.bean;

import java.util.List;

public class Category {
	
	private int id;
	private String name;//分类名
	List<Product> products;//主页的产品分类
	List<List<Product>> productsByRow;//主页产品分类的小分类
	
	public Category() {
		
	}

	public Category(int id, String name, List<Product> products, List<List<Product>> productsByRow) {
		this.id = id;
		this.name = name;
		this.products = products;
		this.productsByRow = productsByRow;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<List<Product>> getProductsByRow() {
		return productsByRow;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setProductsByRow(List<List<Product>> productsByRow) {
		this.productsByRow = productsByRow;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
