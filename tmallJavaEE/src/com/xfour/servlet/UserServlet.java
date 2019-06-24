package com.xfour.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.User;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class UserServlet extends BaseBackServlet{
	//分页显示用户信息
	public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		List<User> users = userDAO.list(page.getStart(),page.getCount());
		int total = userDAO.getTotal();
		page.setTotal(total);//给page对象的total赋值
		
		request.setAttribute("users", users);
		request.setAttribute("page", page);
		
		return "admin/listUser.jsp";
	}
	
	//新增用户信息
	public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		userDAO.add(user);
		return "@admin_user_list";
	}
	
	//删除某个用户
	public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(id);
			return "@admin_user_list";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
}
