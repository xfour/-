package com.xfour.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Category;
import com.xfour.util.ImageUtil;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class CategoryServlet extends BaseBackServlet{
	
	//分页显示分类信息
	public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		List<Category> categorys = categoryDAO.list(page.getStart(),page.getCount());
		int total = categoryDAO.getTotal();
		page.setTotal(total);//给page对象的total赋值
		
		request.setAttribute("categorys", categorys);
		request.setAttribute("page", page);
		
		return "admin/listCategory.jsp";
	}
	
	//新增分类信息
	public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		Map<String,String> params = new HashMap<>();
		InputStream inputStream = parseUpload(request, response, params);
		
		String name = params.get("imgName");
		Category category = new Category();
		category.setName(name);
		categoryDAO.add(category);
		File filePath = new File(request.getSession().getServletContext().getRealPath("img/category/"));
		File file = new File(filePath,category.getId()+".jpg");
		try {
			if(null!=inputStream && 0!=inputStream.available()) {
				try(FileOutputStream fos = new FileOutputStream(file)){
					int length = inputStream.available();
					byte b[] = new byte[length];
					inputStream.read(b);
					fos.write(b, 0, length);
					fos.flush();
					BufferedImage img = ImageUtil.change2jpg(file);
					ImageIO.write(img, "jpg", file);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "@admin_category_list";
	}
	
	//更新分类信息
	public String update(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		Map<String,String> params = new HashMap<>();
		InputStream inputStream = parseUpload(request, response, params);
		
		int id = Integer.parseInt(params.get("id"));
		String name = params.get("imgName");
		Category category = new Category();
		category.setName(name);
		category.setId(id);
		categoryDAO.update(category);
		
		try {
			if(null!=inputStream && 0!=inputStream.available()) {//如果没有更新图片则不进行此操作
				File filePath = new File(request.getSession().getServletContext().getRealPath("img/category/"));
				File file = new File(filePath,category.getId()+".jpg");
				try(FileOutputStream fos = new FileOutputStream(file)){
					int length = inputStream.available();
					byte b[] = new byte[length];
					inputStream.read(b);
					fos.write(b, 0, length);
					fos.flush();
					BufferedImage img = ImageUtil.change2jpg(file);
					ImageIO.write(img, "jpg", file);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "@admin_category_list";
	}
	
	//编辑分类信息
	public String edit(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = categoryDAO.get(id);
			request.setAttribute("category", category);
			return "admin/editCategory.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
	
	//删除某个分类
	public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			categoryDAO.delete(id);
			
			//当删除某个分类时，同时删除其对应的文件夹中的图片
			File filePath = new File(request.getSession().getServletContext().getRealPath("img/category/"));
			File file = new File(filePath,id+".jpg");
			if(file.exists()) file.delete();
			
			return "@admin_category_list";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
}
