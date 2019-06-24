package com.xfour.bean;

import java.util.Date;
import java.util.List;

import com.xfour.dao.OrderDAO;

public class Order {
	
	private int id;
	private String orderCode;//订单号
	private String address;//地址
	private String post;//邮编
	private String receiver;//收货人信息
	private String mobile;//电话号码
	private String userMessage;//用户信息
	private Date createDate;//订单创建时间
	private Date payDate;//支付时间
	private Date deliveryDate;//发货时间
	private Date confirmDate;//确认收货时间
	private String status;//订单状态,如待付款,待发货,确认收货,评价
	
	private User user;//与用户的多对一关系
	private List<OrderItem> orderItems;//与订单项的一对多关系
	private float total;//订单总金额
	private int totalNumber;//订单总商品数量

	public Order() {
		
	}

	public Order(int id, String orderCode, String address, String post, String receiver, String mobile,
			String userMessage, Date createDate, Date payDate, Date deliveryDate, Date confirmDate, String status,
			User user, List<OrderItem> orderItems, float total, int totalNumber) {
		this.id = id;
		this.orderCode = orderCode;
		this.address = address;
		this.post = post;
		this.receiver = receiver;
		this.mobile = mobile;
		this.userMessage = userMessage;
		this.createDate = createDate;
		this.payDate = payDate;
		this.deliveryDate = deliveryDate;
		this.confirmDate = confirmDate;
		this.status = status;
		this.user = user;
		this.orderItems = orderItems;
		this.total = total;
		this.totalNumber = totalNumber;
	}
	
	//根据从数据库里取出的状态返回中文版的状态信息
	public String getStatusDesc() {
		String desc = "未知";
		switch(status) {
			case OrderDAO.waitPay:
				desc="待付款";
				break;
			case OrderDAO.waitDelivery:
				desc="待发货";
				break;
			case OrderDAO.waitConfirm:
				desc="待收货";
				break;
			case OrderDAO.waitReview:
				desc="待评价";
				break;
			case OrderDAO.finish:
				desc="完成";
				break;
			case OrderDAO.delete:
				desc="删除";
				break;
			default:
				desc="未知";
		}
		return desc;
	}

	public int getId() {
		return id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getAddress() {
		return address;
	}

	public String getPost() {
		return post;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public String getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public float getTotal() {
		return total;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", address=" + address + ", post=" + post
				+ ", receiver=" + receiver + ", mobile=" + mobile + ", userMessage=" + userMessage + ", status="
				+ status + ", user=" + user + ", total=" + total + ", totalNumber=" + totalNumber + "]";
	}
	
}
