<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="include/top.jsp" %>
<script type="text/javascript">

	function delcommafy(num){
		if((num+"").trim()==""){
			return "";
		}
		num=num.replace(/,/gi,'');
		return num;
	}

	$(function(){
		$(".itemShowEnd").click(function() {
			$("html,body").animate({scrollTop:0}, 500);
		});
		
		$("#minPrice,#maxPrice").keyup(function(){//根据输入得价格区间来显示商品
			var minPrice = $("#minPrice").val();
			var maxPrice = $("#maxPrice").val();
			var length = $(".itemShowContent").length;
			var price =0;
			
			if(minPrice!=""&&maxPrice!=""){
				minPrice = Number(minPrice);
				maxPrice = Number(maxPrice);
				if(isNaN(minPrice)||isNaN(maxPrice)){
					$(".validPrice").html("请输入数字！");	
				}else{
					$(".validPrice").html("");
					$(".itemShowContent").css("display","block");
					if(minPrice > maxPrice){
						$(".itemShowContent").css("display","none");
					}else{
						for(var i = 0;i<length;i++){
							price = Number(delcommafy($(".itemShowContent .itemPriceNum").eq(i).html()));
							if(!(price>=minPrice&&price<=maxPrice)){
								$(".itemShowContent").eq(i).css("display","none");	
							}
						}
					}
				}	
			}else if(minPrice!=""){
				minPrice = Number(minPrice);
				if(isNaN(minPrice)){
					$(".validPrice").html("请输入数字！");
				}else{
					$(".validPrice").html("");
					$(".itemShowContent").css("display","block");
					if(minPrice>0){
						for(var i = 0;i<length;i++){
							price = Number(delcommafy($(".itemShowContent .itemPriceNum").eq(i).html()));
							if(price<minPrice){
								$(".itemShowContent").eq(i).css("display","none");	
							}
						}
					}else{
						$(".itemShowContent").css("display","block");
					}
				}
			}else if(maxPrice!=""){
				maxPrice = Number(maxPrice);
				if(isNaN(maxPrice)){
					$(".validPrice").html("请输入数字！");
				}else{
					$(".validPrice").html("");
					$(".itemShowContent").css("display","block");
					if(maxPrice>0){
						for(var i = 0;i<length;i++){
							price = Number(delcommafy($(".itemShowContent .itemPriceNum").eq(i).html()));
							if(price>maxPrice){
								$(".itemShowContent").eq(i).css("display","none");	
							}
						}
					}else{
						$(".itemShowContent").css("display","none");
					}
				}
				
			}else{
					$(".validPrice").html("");
					$(".itemShowContent").css("display","block");
			}
		});
	});
	
	
</script>

<link href="css/fore/category.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
    <%@include file="include/search.jsp" %>
    
    <div class="rankingTop">
    	<img src="img/category/${cid }.jpg">
    </div><!--rankingTop-->
    
    <div class="ranking">
        <a href="forecategory?cid=${cid }&sort=all" <c:if test="${sort=='all'||(empty sort) }">class="ranking_a"</c:if> >综合<span class="glyphicon glyphicon-arrow-down"></span></a>
		<a href="forecategory?cid=${cid }&sort=reviewNum" <c:if test="${sort=='reviewNum' }">class="ranking_a"</c:if> >人气<span class="glyphicon glyphicon-arrow-down"></span></a>
		<a href="forecategory?cid=${cid }&sort=date" <c:if test="${sort=='date' }">class="ranking_a"</c:if> >新品<span class="glyphicon glyphicon-arrow-down"></span></a>
		<a href="forecategory?cid=${cid }&sort=saleNum" <c:if test="${sort=='saleNum' }">class="ranking_a"</c:if> >销量<span class="glyphicon glyphicon-arrow-down"></span></a>
		<c:if test="${sort=='price' }"><a href="forecategory?cid=${cid }&sort=priceAsc" class="ranking_a">价格<span class="glyphicon glyphicon-arrow-down"></span></a></c:if> 
		<c:if test="${sort=='priceAsc' }"><a href="forecategory?cid=${cid }&sort=price" class="ranking_a">价格<span class="glyphicon glyphicon-arrow-up"></span></a></c:if> 
		<c:if test="${sort!='price'&&sort!='priceAsc' }"><a href="forecategory?cid=${cid }&sort=price">价格<span class="glyphicon glyphicon-resize-vertical"></span></a></c:if> 
		<div><input id="minPrice" type="text" placeholder="最低价"><span>-</span><input id="maxPrice" type="text" placeholder="最高价"></div>
        <span class="validPrice"></span>
    </div><!--ranking-->
    
    <div class="itemShowDiv">
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
