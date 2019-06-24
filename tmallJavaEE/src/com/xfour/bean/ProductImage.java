package com.xfour.bean;

public class ProductImage {
	
	private int id;
	private Product product;//与product是多对一的关系
	private String type;//图片类型,有产品图片和产品详情图片
	
	public ProductImage() {
		
	}

	public ProductImage(int id, Product product, String type) {
		this.id = id;
		this.product = product;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public String getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductImage [id=" + id + ", product=" + product + ", type=" + type + "]";
	}
	
}
