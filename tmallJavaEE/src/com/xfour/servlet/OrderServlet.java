package com.xfour.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Order;
import com.xfour.impl.OrderDAOImpl;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class OrderServlet extends BaseBackServlet{
	
	//发货方法
	public String delivery(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Order order = orderDAO.get(id);
			order.setStatus(OrderDAOImpl.waitConfirm);
			
			orderDAO.update(order);
			
			return "@admin_order_list";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
	
	//分页显示订单信息
	public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		List<Order> orders = orderDAO.list(page.getStart(),page.getCount());
		int total = orderDAO.getTotal();
		page.setTotal(total);//给page对象的total赋值
		orderItemDAO.fill(orders);
		
		request.setAttribute("orders", orders);
		request.setAttribute("page", page);
		
		return "admin/listOrder.jsp";
	}
	
	//新增订单信息
	public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		
		return null;
	}
	
	//删除某个订单
	public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			orderDAO.delete(id);
			return "@admin_order_list";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
}
