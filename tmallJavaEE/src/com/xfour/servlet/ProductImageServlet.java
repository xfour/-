package com.xfour.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Product;
import com.xfour.bean.ProductImage;
import com.xfour.impl.ProductImageDAOImpl;
import com.xfour.util.ImageUtil;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class ProductImageServlet extends BaseBackServlet{
	
	//删除某个产品图片
	public String delete(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			
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
			
			productImageDAO.delete(id);
			
			return "@admin_productImage_list?pid="+pid;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("空指针异常或类型转换错误！");
		}
		return null;
	}
	
	//分页显示产品图片信息
	public String list(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String pidStr = request.getParameter("pid");
		if(null!=pidStr) {
			int pid = Integer.parseInt(request.getParameter("pid"));
			Product product = productDAO.get(pid);
			
			List<ProductImage> productSingleImages = productImageDAO.list(pid, ProductImageDAOImpl.type_single);//获取产品单个图片集合
			List<ProductImage> productDetailImages = productImageDAO.list(pid, ProductImageDAOImpl.type_detail);//获取产品详情图片集合
			
			int total = productImageDAO.getTotal();
			page.setTotal(total);//给page对象的total赋值
			page.setParams("&pid="+pid);//给params赋值
			
			request.setAttribute("productSingleImages", productSingleImages);
			request.setAttribute("productDetailImages", productDetailImages);
			request.setAttribute("product", product);//用于导航栏的面包屑显示
			request.setAttribute("page", page);
			
			return "admin/listProductImage.jsp";
		}
		return null;
	}

	//新增产品图片信息
	public String add(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		Map<String,String> params = new HashMap<>();
		InputStream inputStream = parseUpload(request, response, params);
		
		String type = params.get("imageType");
		int pid = Integer.parseInt(params.get("pid"));
		ProductImage productImage = new ProductImage();
		productImage.setProduct(productDAO.get(pid));
		productImage.setType(type);
		productImageDAO.add(productImage);
		
		File filePath = null;
		File filePathSmall = null;
		File filePathMiddle = null;
		
		if("type_single".equals(type)) {
			filePath = new File(request.getSession().getServletContext().getRealPath("img/product/productSingle/"));
			filePathSmall = new File(request.getSession().getServletContext().getRealPath("img/product/productSingleSmall/"));
			filePathMiddle = new File(request.getSession().getServletContext().getRealPath("img/product/productSingleMiddle/"));
					
		}else if("type_detail".equals(type)){
			filePath = new File(request.getSession().getServletContext().getRealPath("img/product/productDetail/"));
		}else {
			return "%There has an error!";
		}
		File file = new File(filePath,productImage.getId()+".jpg");
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
					if("type_single".equals(type)) {
						File fileSmall = new File(filePathSmall,productImage.getId()+".jpg");
						File fileMiddle = new File(filePathMiddle,productImage.getId()+".jpg");
						Image imgSmall = ImageUtil.resizeImage(img, 40, 40);
						Image imgMiddle = ImageUtil.resizeImage(img, 188, 188);
						ImageIO.write((RenderedImage) imgSmall, "jpg", fileSmall);
						ImageIO.write((RenderedImage) imgMiddle, "jpg", fileMiddle);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "@admin_productImage_list?pid="+pid;
	}
}
