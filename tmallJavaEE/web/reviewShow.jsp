<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/top.jsp" %>

	<link href="css/fore/review.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
	<%@include file="include/simpleSearch.jsp" %>
    
    <div class="goodsOutDiv">
    	<div class="goodsImg"><img src="img/product/productSingle/${product.firstProductImage }.jpg"></div>
        <div class="goodsDetail">
            <div class="goodsName">${product.name }</div>
        	<table>
                <tr><td>价格：</td><td><span class="goodsPrice">￥<span>${product.discountPrice }</span></span></td></tr>
                <tr><td>配送：</td><td><span class="goodsFreight">快递：￥<span>0.00</span></span></td></tr>
                <tr><td>月销量：</td><td><span class="goodsSellNum"><span>${product.saleCount }</span>件</span></td></tr>
            </table>
            <div class="goodsInfoDiv">
            	<div class="goodsInfo">
                	<img width="23px" height="38px" src="img/site/reviewLight.png">
                    <div>现在查看的是 您所购买商品的信息 于<span class="goodsInfoSpan"><fmt:formatDate value="${order.payDate }" pattern="yy年MM月dd日"/></span>下单购买了此商品
                    </div>
                </div>
            </div>
        </div><!-- goodsDetail -->
    </div><!-- goodsDetail -->
    
    
    <div class="reviewOutDiv">
    	<div class="reviewNumber"><div></div>累计评论<span>${product.reviewCount }</span></div>
        <div class="reviewNumberBottom"></div>
    	<c:forEach items="${reviews }" var="review" varStatus="st">
	    	<div class="reviewShowDiv">
	    		<b style="float:left"><fmt:formatDate value="${review.createDate }" pattern="yy-MM-dd hh:mm:ss"/></b>
	    		<span style="float:left">${review.content }</span>
	    		<span style="float:right">${review.user.anonymousName }<b style="color:#ccc"> (匿名)</b></span>
	    		<div style="clear:both"></div>
	    	</div>
    	</c:forEach>
    </div><!-- reviewOutDiv -->
    
<%@include file="include/footer.jsp"%>