package com.xfour.bean;

public class OrderItem {

	private int id;
	private int number;//订单项的商品数量
	private Product product;//与产品的多对一关系
	private Order order;//与订单的多对一关系
	private User user;//与用户的多对一关系
	
	public OrderItem() {
		
	}

	public OrderItem(int id, int number, Product product, Order order, User user) {
		this.id = id;
		this.number = number;
		this.product = product;
		this.order = order;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public Product getProduct() {
		return product;
	}

	public Order getOrder() {
		return order;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", number=" + number + ", product=" + product + ", order=" + order + ", user="
				+ user + "]";
	}
	
}
