<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
		});
	</script>
	
	<link href="css/fore/confirm.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
	<%@include file="include/simpleSearch.jsp" %>
    
    <div class="confirmOutDiv">
    	<div class="purchaseProcess">
        	<div><img src="img/site/comformPayFlow.png"></div>
            <span class="purchaseTime"><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            <span class="payedTime"><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            <span class="deliveryTime"><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </div><!-- purchaseProcess -->
        
        <div class="confirmDiv">
        	<div class="confirmTitle">我已收到货,同意支付宝付款</div><!-- confirmTitle -->
            <div class="orderFormTitle">订单信息</div>
            
            <table class="orderForm">
                <thead>
                    <tr>
                        <th>宝贝</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>商品总价</th>
                        <th>运费</th>
                    </tr>
                </thead>
                
                <tbody>
                
                	<c:forEach items="${order.orderItems }" var="orderItem" varStatus="st">
	                	<tr>
	                        <td class="itemDetail">
	                            <img src="img/product/productSingleSmall/${orderItem.product.firstProductImage }.jpg"><a href="foreproduct?pid=${orderItem.product.id }">${orderItem.product.name }</a>
	                        </td>
	                        <td class="itemPrice">￥<span>${orderItem.product.discountPrice }</span></td>
	                        <td class="itemNumber">${orderItem.number }</td>
	                        <td class="itemSum">￥<span>${orderItem.product.discountPrice*orderItem.number }</span></td>
	                        <td class="freight">快递：<span>0.00</span></td>
	                    </tr>
                	</c:forEach>
                	
                </tbody>
            </table><!-- orderForm -->
            
            <div class="allItemsSum">实付款：<b>￥<span>${order.total }</span></b></div><!-- allItemsSum -->
            
            <table class="orderFormDetail">
            	<tr><td>订单编号：</td><td class="orderFormId">${order.orderCode }<img src="img/site/orderItemTmall.png" height="21px"></td></tr>
            	<tr><td>卖家昵称：</td><td class="sellerNameTd"><span class="sellerName">天猫商铺</span><span class="wangwang"></span></td></tr>
            	<tr><td>收货信息：</td><td class="orderFormAddress">${order.address }</td></tr>
            	<tr><td class="orderFormTime">成交时间：</td><td><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
            </table><!-- orderFormDetail -->
        </div><!-- confirmDiv -->
        
        <div class="confirmPayDiv">
        	<div>请收到货后，再确认收获！否则您可能钱财两空！</div>
            <a href="forereceiveSuccess?oid=${order.id }"><button>确认收货</button></a>
        </div>
    </div><!-- confirmOutDiv -->
    
<%@include file="include/footer.jsp"%>