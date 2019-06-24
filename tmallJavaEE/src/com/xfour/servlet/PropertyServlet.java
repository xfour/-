package com.xfour.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Property;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class PropertyServlet extends BaseBackServlet{
	
		//分页显示属性信息
		public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
			String cidStr = request.getParameter("cid");
			if(null!=cidStr) {
				int cid = Integer.parseInt(request.getParameter("cid"));
				List<Property> propertys = propertyDAO.list(cid,page.getStart(),page.getCount());
				int total = propertyDAO.getTotal(cid);
				page.setTotal(total);//给page对象的total赋值
				page.setParams("&cid="+cid);//给params赋值
				
				request.setAttribute("propertys", propertys);
				request.setAttribute("category", categoryDAO.get(cid));
			
				request.setAttribute("page", page);
				
				return "admin/listProperty.jsp";
			}
			return null;
		}
		
		//新增属性信息
		public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
			String name = request.getParameter("name");
			int cid = Integer.parseInt(request.getParameter("cid"));
			Property property = new Property();
			property.setCategory(categoryDAO.get(cid));
			property.setName(name);
			propertyDAO.add(property);
			return "@admin_property_list?cid="+cid;
		}
		
		//更新属性信息
		public String update(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Property property = propertyDAO.get(id);
			property.setName(name);
			propertyDAO.update(property);
			return "@admin_property_list?cid="+property.getCategory().getId();
		}
		
		//编辑属性信息
		public String edit(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Property property = propertyDAO.get(id);
				request.setAttribute("property", property);
				return "admin/editProperty.jsp";
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("空指针异常或类型转换错误！");
			}
			return null;
		}
		
		//删除某个属性
		public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				int cid = Integer.parseInt(request.getParameter("cid"));
				propertyDAO.delete(id);
				return "@admin_property_list?cid="+cid;
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("空指针异常或类型转换错误！");
			}
			return null;
		}
}
