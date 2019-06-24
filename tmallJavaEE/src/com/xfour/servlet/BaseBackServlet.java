package com.xfour.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xfour.dao.CategoryDAO;
import com.xfour.dao.OrderDAO;
import com.xfour.dao.OrderItemDAO;
import com.xfour.dao.ProductDAO;
import com.xfour.dao.ProductImageDAO;
import com.xfour.dao.PropertyDAO;
import com.xfour.dao.PropertyValueDAO;
import com.xfour.dao.ReviewDAO;
import com.xfour.dao.UserDAO;
import com.xfour.impl.CategoryDAOImpl;
import com.xfour.impl.OrderDAOImpl;
import com.xfour.impl.OrderItemDAOImpl;
import com.xfour.impl.ProductDAOImpl;
import com.xfour.impl.ProductImageDAOImpl;
import com.xfour.impl.PropertyDAOImpl;
import com.xfour.impl.PropertyValueDAOImpl;
import com.xfour.impl.ReviewDAOImpl;
import com.xfour.impl.UserDAOImpl;
import com.xfour.util.PageUtil;

/**
 * 后端基本servlet
 * 对BackServletFilter传来的信息进行处理
 * 获取分页信息
 * 根据反射访问对应的方法
 * 根据对应方法的返回值进行服务端跳转，客户端跳转，或者直接输出字符串
 * @author square
 *
 */
@SuppressWarnings("serial")
public class BaseBackServlet extends HttpServlet{
	
	protected CategoryDAO categoryDAO = new CategoryDAOImpl();
	protected OrderDAO orderDAO = new OrderDAOImpl();
	protected OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	protected ProductDAO productDAO = new ProductDAOImpl();
	protected ProductImageDAO productImageDAO = new ProductImageDAOImpl();
	protected PropertyDAO propertyDAO = new PropertyDAOImpl();
	protected PropertyValueDAO propertyValueDAO = new PropertyValueDAOImpl();
	protected ReviewDAO reviewDAO = new ReviewDAOImpl();
	protected UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) {
		//获取属性method里的信息，再借助反射机制获取相应方法
		try {
			//获取分页信息
			int start = 0;
			int count = 5;
			try {
				if(request.getParameter("start")!=null) start = Integer.parseInt(request.getParameter("start"));
				if(request.getParameter("count")!=null) count = Integer.parseInt(request.getParameter("count"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			PageUtil page = new PageUtil(start,count);
			
			String methodName = (String) request.getAttribute("method");
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class,PageUtil.class);
			String methodResult = (String) method.invoke(this,request,response,page);//获取此方法的返回值
			
			/*
			 * 如果methodResult是以@开头进行相应的客户端跳转
			 * 如果是以 % 开头，则输出字符串
			 * 否则进行服务端跳转
			 */
			if(methodResult.startsWith("@")) response.sendRedirect(methodResult.substring(1));
			else if(methodResult.startsWith("%")) response.getWriter().print(methodResult.substring(1));
			else request.getRequestDispatcher(methodResult).forward(request, response);
 			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 保存request里的所有参数到params
	 * 返回request里的图片的输入流
	 */
	@SuppressWarnings("rawtypes")
	public InputStream parseUpload(HttpServletRequest request,HttpServletResponse response,Map<String,String> params) {
		InputStream inputStream = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//限制上传文件的大小10M
			factory.setSizeThreshold(10485760);//1024*1024*10
			List items = upload.parseRequest(request);
			Iterator iterator = items.iterator();
			while(iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				if(item.isFormField()) {//如果是参数
					String paramName = item.getFieldName();
					String paramValue = item.getString();
					paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
					params.put(paramName,paramValue);
				}else {//如果是文件
					inputStream = item.getInputStream();
				}
			}
		}catch(FileUploadException | IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
}
