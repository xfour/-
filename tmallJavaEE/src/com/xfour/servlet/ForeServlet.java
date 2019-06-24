package com.xfour.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfour.bean.Category;
import com.xfour.bean.Order;
import com.xfour.bean.OrderItem;
import com.xfour.bean.Product;
import com.xfour.bean.ProductImage;
import com.xfour.bean.PropertyValue;
import com.xfour.bean.Review;
import com.xfour.bean.User;
import com.xfour.bean.UserMessage;
import com.xfour.dao.OrderDAO;
import com.xfour.impl.OrderItemDAOImpl;
import com.xfour.impl.ProductImageDAOImpl;
import com.xfour.util.PageUtil;

@SuppressWarnings("serial")
public class ForeServlet extends BaseForeServlet{
	
	//主页获取相关数据
	public String home(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		List<Category> categorys = categoryDAO.list();
		productDAO.fill(categorys);
		productDAO.fillByRow(categorys);
		request.setAttribute("categorys", categorys);
		return "index.jsp";
	}
	
	//登录验证
	public String login(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = userDAO.get(name, password);
		if(null!=user) {
			request.getSession().setAttribute("userName", user.getName());

			//每次登录都更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(user.getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			if(null!=user.getIdentify()||(!"".equals(user.getIdentify()))) {
				request.getSession().setAttribute("userIdentify", user.getIdentify());
			}
			return "@forehome";
		}
		request.setAttribute("loginmsg", "用户名或密码错误!");
		return "login.jsp";
	}

	//模态登录异步请求操作
	public String loginAjax(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = userDAO.get(name, password);
		if(null!=user) {
			request.getSession().setAttribute("userName", user.getName());

			//每次登录都更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(user.getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			if(null!=user.getIdentify()||(!"".equals(user.getIdentify()))) {
				request.getSession().setAttribute("userIdentify", user.getIdentify());
			}
			return "%success";
		}
		request.setAttribute("loginmsg", "用户名或密码错误!");
		return "%failed";
	}
	
	//注册新用户
	public String register(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if(null!=userDAO.get(name)) {
			request.setAttribute("registermsg", "该用户名已被注册!");
			return "register.jsp";
		}
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		userDAO.add(user);
		userMessageDAO.init(user);
		
		return "registerSuccess.jsp";
	}
	
	//注销当前账号
	public String logout(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		request.getSession().removeAttribute("userName");
		return "@forehome";
	}
	
	//产品详情页面相关操作
	public String product(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		int id = Integer.parseInt(request.getParameter("pid"));
		Product product = productDAO.get(id);
		List<ProductImage> productSingleImages = productImageDAO.list(id, ProductImageDAOImpl.type_single);
		List<ProductImage> productDetailImages = productImageDAO.list(id, ProductImageDAOImpl.type_detail);
		product.setProductSingleImages(productSingleImages);
		product.setProductDetailImages(productDetailImages);
		productDAO.setSaleAndReviewNumber(product);
		
		List<PropertyValue> propertyValues = propertyValueDAO.list(id);
		List<Review> reviews = reviewDAO.list(id);

		request.setAttribute("product", product);
		request.setAttribute("propertyValues", propertyValues);
		request.setAttribute("reviews", reviews);
		
		return "product.jsp";
	}
	
	//分类详情页面相关操作
	public String category(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		int id = Integer.parseInt(request.getParameter("cid"));
		List<Product> products = productDAO.list(id);
		
		productDAO.setSaleAndReviewNumber(products);//初始化每个产品的销量和评论数
		for(Product product : products) {
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
		}
		
		String sortType = request.getParameter("sort");
		if(null!=sortType) {
			switch(sortType) {//根据sortType对集合products排列
				case "all":products.sort((p1,p2)->p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount());
					break;
				case "reviewNum":products.sort((p1,p2)->p2.getReviewCount()-p1.getReviewCount());
					break;
				case "date":products.sort((p1,p2)->p2.getCreateDate().compareTo(p1.getCreateDate()));
					break;
				case "saleNum":products.sort((p1,p2)->p2.getSaleCount()-p1.getSaleCount());
					break;
				case "price":products.sort((p1,p2)->(int)(p2.getDiscountPrice()-p1.getDiscountPrice()));
					break;
				case "priceAsc":products.sort((p1,p2)->(int)(p1.getDiscountPrice()-p2.getDiscountPrice()));
				break;
				default:break;	
			}
		}else {//默认是按综合排序
			products.sort((p1,p2)->p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount());
		}
		
		request.setAttribute("sort", sortType);
		request.setAttribute("cid", id);
		request.setAttribute("products", products);
		
		return "category.jsp";
		
	}
	
	//通过关键字搜索相关产品
	public String search(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String searchStr = request.getParameter("search");
		
		List<Product> products = productDAO.search(searchStr, 0, Short.MAX_VALUE);
		productDAO.setSaleAndReviewNumber(products);//初始化每个产品的销量和评论数
		for(Product product : products) {
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
		}

		request.setAttribute("keyword", searchStr);
		request.setAttribute("products", products);
		return "searched.jsp";
	}
	
	//通过分类栏的相关小标题搜索相关产品
	public String searchSub(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		String searchStr = request.getParameter("search");
		
		List<Product> products = productDAO.searchSub(searchStr, 0, Short.MAX_VALUE);
		productDAO.setSaleAndReviewNumber(products);//初始化每个产品的销量和评论数
		for(Product product : products) {
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
		}
		
		request.setAttribute("keyword", searchStr);
		request.setAttribute("products", products);
		return "searched.jsp";
	}
	
	//立即购买按钮点击事件
	public String buyNow(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			String userName = (String) request.getSession().getAttribute("userName");
			if(null==userName) return null;
			
			List<OrderItem> ois = new ArrayList<>();;//保存相应订单项
			
			Product product = productDAO.get(pid);//获取产品对象
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
			User user = userDAO.get(userName);//获取用户对象
			
			List<OrderItem> orderItems = orderItemDAO.listByUser(user.getId());
			
			boolean isExit = true;//用于记录购物车中是否已有相应产品
			for(OrderItem orderItem : orderItems) {
				if(orderItem.getProduct().getId()==pid) {
					orderItem.setNumber(orderItem.getNumber()+num);
					isExit = false;
					orderItemDAO.update(orderItem);
					ois.add(orderItem);
					productDAO.setFirstProductImage(orderItem.getProduct());//给每个产品设置默认图片
					break;
				}
			}
			
			if(isExit) {
				OrderItem orderItem = new OrderItem();
				orderItem.setNumber(num);
				orderItem.setProduct(product);
				orderItem.setUser(user);
				
				orderItemDAO.add(orderItem);
				ois.add(orderItem);
			}

			//每次添加购物车都更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(user.getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			request.setAttribute("orderItems", ois);
			
			//获取用户相关信息，用于显示地址
			UserMessage userMessage = userMessageDAO.get(userName);
			request.setAttribute("userMessage", userMessage);
			
			return "buy.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//加入购物车相关操作
	public String addToCart(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			String userName = (String) request.getSession().getAttribute("userName");
			if(null==userName) return null;
			
			Product product = productDAO.get(pid);//获取产品对象
			User user = userDAO.get(userName);//获取用户对象
			
			
			
			List<OrderItem> orderItems = orderItemDAO.listByUser(user.getId());
			
			boolean isExit = true;//用于记录购物车中是否已有相应产品,为false时存在
			for(OrderItem orderItem : orderItems) {
				if(orderItem.getProduct().getId()==pid) {
					orderItem.setNumber(orderItem.getNumber()+num);
					isExit = false;
					orderItemDAO.update(orderItem);
					break;
				}
			}
			
			if(isExit) {
				OrderItem orderItem = new OrderItem();
				orderItem.setNumber(num);
				orderItem.setProduct(product);
				orderItem.setUser(user);
				
				orderItemDAO.add(orderItem);
				//orderItems.add(orderItem);
			}
			
			//request.setAttribute("orderItems", orderItems);
			
			//每次添加购物车都更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(user.getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			return "%"+cartNumber;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "%0";	
	}
	
	//加入购物车相关操作
	public String cart(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			if(null==userName) return null;
			
			User user = userDAO.get(userName);//获取用户对象
			
			List<OrderItem> orderItems = orderItemDAO.listByUser(user.getId());
			for(OrderItem orderItem : orderItems) {
				productDAO.setFirstProductImage(orderItem.getProduct());//给每个产品设置默认图片
			}
			request.setAttribute("orderItems", orderItems);
			
			return "cart.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//删除对应的订单项
	public String deleteOrderItem(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oiid = Integer.parseInt(request.getParameter("oiid"));
			
			orderItemDAO.delete(oiid);
			
			return "@forecart";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//改变对应的订单项的产品数量
	public String changeOrderItem(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oiid = Integer.parseInt(request.getParameter("oiid"));
			int number = Integer.parseInt(request.getParameter("number"));
			String userName = (String) request.getSession().getAttribute("userName");
			if(null==userName) return null;
			
			OrderItem orderItem = orderItemDAO.get(oiid);
			orderItem.setNumber(number);
			orderItemDAO.update(orderItem);
			
			//更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(userDAO.get(userName).getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			return "%"+cartNumber;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "%0";	
	}
	
	//购物车结算方法
	public String buy(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			String[] oiids = request.getParameterValues("oiid");
			if(null==userName) return null;
		
			List<OrderItem> orderItems = new ArrayList<>();
			for(String oiid : oiids) {
				OrderItem orderItem = orderItemDAO.get(Integer.parseInt(oiid));
				productDAO.setFirstProductImage(orderItem.getProduct());//给每个产品设置默认图片
				if(orderItem!=null) orderItems.add(orderItem);
			}

			//每次添加购物车都更新购物车商品数量
			int cartNumber = new OrderItemDAOImpl().getTotalByUser(userDAO.get(userName).getId());
			request.getSession().setAttribute("cartNumber", cartNumber);
			
			request.setAttribute("orderItems", orderItems);
			
			//获取用户相关信息，用于显示地址
			UserMessage userMessage = userMessageDAO.get(userName);
			request.setAttribute("userMessage", userMessage);
			
			return "buy.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//生成订单
	public String createOrder(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			//获取相关属性
			String address = request.getParameter("address");
			String post = request.getParameter("post");
			String receiver = request.getParameter("receiver");
			String mobile = request.getParameter("mobile");
			String userMessage = request.getParameter("userMessage");
			String userName = (String) request.getSession().getAttribute("userName");
			if(null==userName) return null;
			User user = userDAO.get(userName);
			//订单号由当前事件的年月日时分秒+用户id组成，不会生成相同订单号
			String orderCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+user.getId();
			
			//初始化订单对象的相关数据
			Order order = new Order();
			order.setAddress(address);
			order.setPost(post);
			order.setReceiver(receiver);
			order.setStatus(OrderDAO.waitPay);
			order.setOrderCode(orderCode);
			order.setCreateDate(new Date());
			order.setMobile(mobile);
			order.setUser(user);
			order.setUserMessage(userMessage);
			orderDAO.add(order);//将订单信息添加到数据库
			
			float total = 0;//用于记录该订单的总金额
			String[] oiids = request.getParameterValues("oiids");
			List<OrderItem> orderItems = new ArrayList<>();
			for(String oiid : oiids) {
				OrderItem orderItem = orderItemDAO.get(Integer.parseInt(oiid));
				if(orderItem!=null) {
					orderItem.setOrder(order);//改变订单项中的订单id，使之不再是-1
					orderItemDAO.update(orderItem);
					orderItems.add(orderItem);
					total+=orderItem.getProduct().getDiscountPrice()*orderItem.getNumber();
				}
			}
			order.setOrderItems(orderItems);
			
			return "@forealipay?oid="+order.getId()+"&total="+total;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//提交订单操作
	public String alipay(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			float total = Float.parseFloat(request.getParameter("total"));
			
			request.setAttribute("oid", oid);
			request.setAttribute("total", total);
			return "alipay.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//确认支付操作
	public String payed(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			float total = Float.parseFloat(request.getParameter("total"));
			Order order = orderDAO.get(oid);
			orderItemDAO.fill(order);
			
			
			order.setPayDate(new Date());
			order.setStatus(OrderDAO.waitDelivery);
			order.setTotal(total);
			
			//判断库存里是否还有这么多商品
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem orderItem:orderItems) {
				int stock = orderItem.getProduct().getStock();
				if(stock<orderItem.getNumber()) {
					return "%"+orderItem.getProduct().getName()+"库存不足！";
				}
				//用户购买多少物品则减少相应库存数量
				orderItem.getProduct().setStock(stock-orderItem.getNumber());
			}
			synchronized(orderItems) {//设置同步锁，当进行修改订单库存时使得其他访问用户不得使用orderItems
				for(OrderItem orderItem:orderItems) {
					productDAO.update(orderItem.getProduct());
				}
			}
			orderDAO.update(order);
			request.setAttribute("order", order);
			
			return "payed.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//查看我的订单
	public String orderform(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			if(userName==null) return null;
			User user = userDAO.get(userName);
			List<Order> orders = orderDAO.list(user.getId(),OrderDAO.delete);
			
			orderItemDAO.fill(orders);
			for(Order order : orders){
				reviewDAO.fillOrder(order);//给该订单中已评论的产品设置评论id
				for(OrderItem orderItem : order.getOrderItems()) {
					productDAO.setFirstProductImage(orderItem.getProduct());//给每个产品设置默认图片
				}
			}
			
			request.setAttribute("orders", orders);
			return "orderform.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//删除某条订单，其原理就是将该订单的状态置为已删除，而查询我的订单时会忽略状态为已删除的那些订单
	public String deleteOrder(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Order order = orderDAO.get(oid);
			order.setStatus(OrderDAO.delete);
			orderDAO.update(order);
			
			return "@foreorderform";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//为了测试所需，当用户点击了催卖家发货时即秒发该订单
	public String delivery(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Order order = orderDAO.get(oid);
			order.setStatus(OrderDAO.waitConfirm);
			System.out.println(order);
			orderDAO.update(order);
			
			return "%success";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//确认收获操作，将返回到confirm页面有用户进行确认支付操作
	public String confirm(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Order order = orderDAO.get(oid);
			orderItemDAO.fill(order);
			reviewDAO.fillOrder(order);//给该订单中已评论的产品设置评论id
			for(OrderItem orderItem : order.getOrderItems()) {
				productDAO.setFirstProductImage(orderItem.getProduct());//给每个产品设置默认图片
			}
			request.setAttribute("order", order);
			
			return "confirm.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//确认支付操作，将该订单的状态改为待评论
	public String receiveSuccess(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Order order = orderDAO.get(oid);
			order.setStatus(OrderDAO.waitReview);
			orderDAO.update(order);
			
			return "receiveSuccess.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//根据产品id和订单id跳转到评论页
	public String review(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			
			Product product = productDAO.get(pid);
			productDAO.setSaleAndReviewNumber(product);
			Order order = orderDAO.get(oid);
			
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
			
			request.setAttribute("product",product);
			request.setAttribute("order", order);
			
			return "review.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//根据用户id，产品id和订单id创建评论
	public String addReview(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			String userName = (String) request.getSession().getAttribute("userName");
			String content = request.getParameter("content");
			
			Review review = new Review();
			review.setProduct(productDAO.get(pid));
			review.setUser(userDAO.get(userName));
			review.setOid(oid);
			review.setContent(content);
			review.setCreateDate(new Date());
			reviewDAO.add(review);
			
			//用于评论页显示订单信息,产品信息,评论信息
			Product product = productDAO.get(pid);
			productDAO.setSaleAndReviewNumber(product);
			
			Order order = orderDAO.get(oid);
			orderItemDAO.fill(order);
			List<OrderItem> orderItems = order.getOrderItems();
			
			reviewDAO.fillOrder(order);//给订单的相应产品填充评论信息
			
			productDAO.setFirstProductImage(product);//给每个产品设置默认图片
			
			/*
			 * 当增加评论后
			 * 判断该订单下的每个产品是否已评论完毕
			 * 如果是，则将该订单的状态置为finish
			 */
			boolean isReview = true;
			for(OrderItem orderItem : orderItems) {
				System.out.println(orderItem.getProduct().getRvid());
				if(orderItem.getProduct().getRvid()==-1||orderItem.getProduct().getRvid()==0) {
					isReview = false;
					break;
				}
			}
			System.out.println(isReview);
			if(isReview) {
				order.setStatus(OrderDAO.finish);
				orderDAO.update(order);
			}
			
			List<Review> reviews = reviewDAO.list(pid);

			request.setAttribute("product",product );
			request.setAttribute("order", order);
			request.setAttribute("reviews", reviews);
			
			
			return "reviewShow.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//用户信息页面
	public String userMessage(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			UserMessage userMessage = userMessageDAO.get(userName);
			
			request.setAttribute("userMessage", userMessage);
			
			return "userMessage.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//异步更新用户信息
	public String updateUserMessage(HttpServletRequest request,HttpServletResponse response,PageUtil page) {
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			String address = request.getParameter("address");
			String post = request.getParameter("post");
			String receiver = request.getParameter("receiver");
			String mobile = request.getParameter("mobile");
			
			UserMessage userMessage = userMessageDAO.get(userName);
			userMessage.setAddress(address);
			userMessage.setPost(post);
			userMessage.setReceiver(receiver);
			userMessage.setMobile(mobile);
			
			userMessageDAO.update(userMessage);
			
			request.setAttribute("userMessage", userMessage);
			
			return "%success";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
