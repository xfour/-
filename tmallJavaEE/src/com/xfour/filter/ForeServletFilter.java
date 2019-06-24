package com.xfour.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Category;
import com.xfour.impl.CategoryDAOImpl;

public class ForeServletFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String[] methods = new String[]{"updateUserMessage","userMessage","addReview","review","confirm","delivery","alipay","deleteOrder","orderform","payed","createOrder","cart","logout","buyNow","buy","addToCart","deleteOrderItem"}; 
		
		String uri = request.getRequestURI();//类似于admin_product_list
		uri=uri.replaceFirst(request.getServletContext().getContextPath(),"");
		if(uri.startsWith("/fore")&&!"/foreServlet".equals(uri)) {
			uri = uri.trim();
			String method = uri.replaceFirst("/fore", "");
			String userName = (String) request.getSession().getAttribute("userName");
			request.getSession().setAttribute("userName", userName);//当用户每次访问前端页面便更新一次，以防止登录状态丢失
			if(null==userName||"".equals(userName)) {
				for(int i=0;i<methods.length;i++) {
					if(method.equals(methods[i])) {
						request.getRequestDispatcher("/login.jsp").forward(request, response);
						return;
					}
				}
			}

			//使session里的购物车商品数量保存时间延长
			if(request.getSession().getAttribute("cartNumber")!=null) {
				int cartNumber = (int) request.getSession().getAttribute("cartNumber");
				request.getSession().setAttribute("cartNumber", cartNumber);
			}
			
			
			request.setAttribute("method", method);//获取list方法
			request.getRequestDispatcher("/foreServlet").forward(request, response);//获取product组成productSerlvet
			return;
		}else if("".equals(uri)||"/".equals(uri)) {
			request.setAttribute("method", "home");
			request.getRequestDispatcher("/foreServlet").forward(request, response);//获取product组成productSerlvet
			return;
		}
		

		List<Category> categorys = (List<Category>) request.getAttribute("categorys");
		if(null==categorys) {
			categorys = new CategoryDAOImpl().list();
			request.setAttribute("categorys", categorys);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
