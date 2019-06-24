package com.xfour.bean;

import java.util.Date;

public class Review {

	private int id;
	private String content;
	private Date createDate;//评论时间
	private Product product;//与产品的多对一关系
	private User user;//与用户的多对一关系
	private int oid;
	
	public Review() {
		
	}

	public Review(int id, String content, Date createDate, Product product, User user, int oid) {
		this.id = id;
		this.content = content;
		this.createDate = createDate;
		this.product = product;
		this.user = user;
		this.oid = oid;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Product getProduct() {
		return product;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", createDate=" + createDate + ", product=" + product
				+ ", user=" + user + ", oid=" + oid + "]";
	}
	
}
