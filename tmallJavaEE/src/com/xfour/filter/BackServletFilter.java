package com.xfour.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后端用于分析链接的基本的filter
 * 通过链接决定使用哪个Servlet的哪个方式
 * @author square
 *
 */
public class BackServletFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String uri = request.getRequestURI();//类似于admin_product_list
		uri=uri.replaceFirst(request.getServletContext().getContextPath(),"");
		if(uri.startsWith("/admin_")) {
			String userName = (String) request.getSession().getAttribute("userName");
			request.getSession().setAttribute("userName", userName);//当用户每次访问后端页面便更新一次，以防止登录状态丢失
			String userIdentify = (String) request.getSession().getAttribute("userIdentify");
			if(null==userName||"".equals(userName)) {//如果未登录即跳转到登录页面
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			if("1".equals(userIdentify)) {//如果用户标识等于1才允许进入此页面
				uri = uri.trim();
				String[] strs = uri.split("_");
				request.setAttribute("method", strs[2]);//获取list方法
				request.getRequestDispatcher("/"+strs[1]+"Servlet").forward(request, response);//获取product组成productSerlvet
				return;
			}
			//如果用户标识不为1，即该账号不是管理员账号时，则截住此链接
			res.setContentType("text/html;charset=UTF-8");
			res.setCharacterEncoding("utf-8");
			res.getWriter().print("<script>if(confirm('非管理员账号无法访问此页面,是否返回到商城主页？')==true) window.location.href='forehome' </script>");
			return;
		}else if("/admin".equals(uri)||"/admin/".equals(uri)){
			response.sendRedirect("/admin_category_list");
			return;
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
