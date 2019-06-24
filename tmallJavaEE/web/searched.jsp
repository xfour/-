<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="include/top.jsp" %>

<link href="css/fore/category.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
    <%@include file="include/search.jsp" %>
    
    <div class="itemShowDiv" style="padding-top:30px">
    	<div class="itemShow">
    	
    		<c:forEach items="${products }" var="product" varStatus="st">
    			<div class="itemShowContent">
	            	<div class="itemImg"><img src="img/product/productSingleMiddle/${product.firstProductImage }.jpg" width="188px" height="188px"></div>
	                <div class="itemPrice">￥<span class="itemPriceNum"><fmt:formatNumber type="number" value="${product.discountPrice}" minFractionDigits="2"/></span></div>
	                <div class="itemDetail"><a href="foreproduct?pid=${product.id }">[热销] ${fn:substring(product.name, 0, 30)}...</a></div>
	                <div class="itemTmall"><a href="foreproduct?pid=${product.id }">天猫专卖</a></div>
	                <div class="itemMessage">
	                	<span class="monthSellNum">月成交<b>${product.saleCount }笔</b></span>
	                	<span class="itemComment">评价<b>${product.reviewCount }</b></span>
	                	<span class="customerService"><img src="img/site/wangwang.png" width="16px" height="16px"></span>
	                </div>
	            </div><!--itemShowContent--><!--一件商品-->
    		</c:forEach>
    		
            <div style="clear:both;"></div>
        </div><!--itemShow--><!--一种商品栏-->
        
        <div class="itemShowEnd">
        	<a href="#self"><img width="82px" height="53px" src="img/site/end.png"></a>
        </div><!--itemShowEnd-->
    </div>
    
<%@include file="include/footer.jsp" %>
