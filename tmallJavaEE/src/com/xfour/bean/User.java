package com.xfour.bean;

public class User {
	
	private int id;
	private String name;//用户名
	private String password;//用户密码
	private String identify;//用户标识，用于区分普通用户和特殊用户
	
	public User() {
		
	}

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	//获取匿名
	public String getAnonymousName() {
		if(null==name) return null;
		
		if(name.length()<=1) return "*";
		
		if(name.length()==2) return name.substring(0,1)+"*";
		
		char[] cs = name.toCharArray();
		for(int i=1;i<cs.length-1;i++) {
			cs[i] = '*';
		}
		
		return new String(cs);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", identify=" + identify + "]";
	}
	
}
