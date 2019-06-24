package com.xfour.bean;

public class UserMessage {
	
	private int id;//用户信息id
	private int uid;//用户id
	private String userName;//用户名
	private String address;//地址
	private String post;//邮编地址
	private String receiver;//收件人姓名
	private String mobile;//联系电话
	public int getId() {
		return id;
	}
	public int getUid() {
		return uid;
	}
	public String getUserName() {
		return userName;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Override
	public String toString() {
		return "UserName [id=" + id + ", uid=" + uid + ", userName=" + userName + ", address=" + address + ", post="
				+ post + ", receiver=" + receiver + ", mobile=" + mobile + "]";
	}
	
}
