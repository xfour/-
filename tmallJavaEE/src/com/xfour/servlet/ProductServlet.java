package com.xfour.servlet;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Category;
import com.xfour.bean.Product;
import com.xfour.bean.ProductImage;
import com.xfour.bean.PropertyValue;
import com.xfour.util.DateUtil;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class ProductServlet extends BaseBackServlet{

	//分页显示产品信息
	public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String cidStr = request.getParameter("cid");
		if(null!=cidStr) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			Category category = categoryDAO.get(cid);
			List<Product> products = productDAO.list(cid,page.getStart(),page.getCount());
			for(Product product : products) {
				productDAO.setFirstProductImage(product);
			}
			int total = productDAO.getTotal();
			page.setTotal(total);//给page对象的total赋值
			page.setParams("&cid="+cid);//给params赋值
			
			request.setAttribute("products", products);
			request.setAttribute("category", category);
			request.setAttribute("page", page);
			
			return "admin/listProduct.jsp";
		}
		return null;
	}
	
	//新增产品信息
	public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		//获取addProductForm提交的相应数据
		String name = request.getParameter("name");
		String subTitle = request.getParameter("subTitle");
		float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
		float discountPrice = Float.parseFloat(request.getParameter("discountPrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		Timestamp createDate = DateUtil.d2t(new Date());
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		Product product = new Product();//给product各属性赋值
		product.setCategory(categoryDAO.get(cid));
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(orignalPrice);
		product.setDiscountPrice(discountPrice);
		product.setStock(stock);
		product.setCreateDate(createDate);
		
		productDAO.add(product);
		return "@admin_product_list?cid="+cid;
	}
	
	//更新产品信息
	public String update(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String subTitle = request.getParameter("subTitle");
		float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
		float discountPrice = Float.parseFloat(request.getParameter("discountPrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		Timestamp createDate = DateUtil.d2t(new Date());
		
		Product product = productDAO.get(id);
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(orignalPrice);
		product.setDiscountPrice(discountPrice);
		product.setStock(stock);
		product.setCreateDate(createDate);
		
		productDAO.update(product);
		return "@admin_product_list?cid="+product.getCategory().getId();
	}
	
	//编辑产品信息
	public String edit(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = productDAO.get(id);
			request.setAttribute("product", product);
			return "admin/editProduct.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
	
	//删除某个产品
	public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int cid = Integer.parseInt(request.getParameter("cid"));
			
			//删除所有相应图片
			ProductImage productImage = productImageDAO.get(id);
			
			if("type_single".equals(productImage.getType())) {
				File filePath = new File(request.getSession().getServletContext().getRealPath("img/product/productSingle/"));;
				File filePathSmall = new File(request.getSession().getServletContext().getRealPath("img/product/productSingleSmall/"));;
				File filePathMiddle = new File(request.getSession().getServletContext().getRealPath("img/product/productSingleMiddle/"));
				
				File fileSmall = new File(filePathSmall,productImage.getId()+".jpg");
				File fileMiddle = new File(filePathMiddle,productImage.getId()+".jpg");
				File file = new File(filePath,productImage.getId()+".jpg");
				if(fileSmall.exists())  fileSmall.delete();
				if(fileMiddle.exists()) fileMiddle.delete();
				if(file.exists()) file.delete();
				
			}else if("type_detail".equals(productImage.getType())) {
				File filePath = new File(request.getSession().getServletContext().getRealPath("img/product/productDetail/"));
				File file = new File(filePath,productImage.getId()+".jpg");
				if(file.exists()) file.delete();
				
			}
			
			productDAO.delete(id);
			return "@admin_product_list?cid="+cid;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
	
	//编辑属性值
	public String editPropertyValue(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product product = productDAO.get(pid);
		propertyValueDAO.init(product);
		List<PropertyValue> propertyValues = propertyValueDAO.list(pid);
		
		request.setAttribute("ptvs", propertyValues);
		request.setAttribute("product", product);
		
		return "admin/editPropertyValue.jsp";
	}
	
	//更新属性值
	public String updatePropertyValue(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String idStr = request.getParameter("id");
		String value = request.getParameter("value");
		if((null!=idStr||""!=idStr)&&(null!=value||""!=value)){
			int id = Integer.parseInt(idStr);
			PropertyValue propertyValue = propertyValueDAO.get(id);
			propertyValue.setValue(value);
			propertyValueDAO.update(propertyValue);
			return "%success";
		}
		return "%failed";
	}
}
