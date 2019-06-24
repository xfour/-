package com.xfour.dao;

import com.xfour.bean.User;
import com.xfour.bean.UserMessage;

public interface UserMessageDAO {
	
	//通过用户信息id查询对应信息
	public UserMessage get(int id);
	
	//通过会员名查询相应用户信息
	public UserMessage get(String username);
	
	//增加用户信息
	public void add(UserMessage userMessage);
	
	//更新用户信息
	public void update(UserMessage userMessage);
	
	//初始化用户信息数据
	public void init(User user);

}
