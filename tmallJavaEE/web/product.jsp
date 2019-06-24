<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
			$(".productImgs img").hover(function(){//移动到小图片上则在大的显示框的显示该图片
				$(".productImgs img").css("border","2px solid #fff");
				$(this).css("border","2px solid #dd2727");
				$(".productImg img").attr("src",$(this).attr("src"));
			});
			
			$(".productDetailTag a").click(function(){//点击切换商品详情和商品评论
				$(".productDetail,.productComment").hide();
				if($(".productDetailTag a").index(this) == 0||$(".productDetailTag a").index(this) == 2){
					$(".productDetail").show();
				}else $(".productComment").show();
			});
			
			$(".productNumber .inputSpan input").keyup(function(){//将数量设为1至库存数量之间
				var number = Number($(".productNumber .inputSpan input").val());
				var stock = Number($(".realNumber").html());
				if(isNaN(number)){
					$(".productNumber .inputSpan input").val(1);
				}else{
					if(number<=0) $(".productNumber .inputSpan input").val(1); 
					else if(number > stock) $(".productNumber .inputSpan input").val(stock);
				}
			});
			
			$(".changeNumber a").click(function(){//点击增加或减少按钮可以相应改变数量
				var number = Number($(".productNumber .inputSpan input").val());
				var stock = Number($(".realNumber").html());
				if($(".changeNumber a").index(this) == 0){
					if(number<stock) $(".productNumber .inputSpan input").val(number+1);
					else alert("数量不能再增加!");
				}
				else{
					if(number>1) $(".productNumber .inputSpan input").val(number-1);
					else alert("数量不能再减少!");
				}
			});
			
			if($(".productComments").length>999){//设置评论数量
				$(".productDetailTag span").html(999+"+");
			}else{
				$(".productDetailTag span").html($(".productComments").length);
			}
			
			$("#buyNowBtn").click(function(){//给立即购买按钮设置点击事件
				var num=$(".inputSpan input").val();
				var pid=$("#productID").val();
				var userName=$("#userName").html();
				window.location.href="forebuyNow?pid="+pid+"&num="+num;
			});
			
			$("#addToCart").click(function(){
				var num=$(".inputSpan input").val();
				var pid=$("#productID").val();
				var page="foreaddToCart";
				$(this).addClass("addedToCart");
				$(this).html("已加入购物车");
				$(this).removeAttr("id");
				$.ajax({
					url:page,
					data:{"num":num,"pid":pid},
					success:function(result){
						$("#cartNumber").html(result);
					}
				});
				
			});
		});
	</script>

	<link href="css/fore/product.css" rel="stylesheet">
</head>

<body>
	<%@include file="include/header.jsp" %>
    <%@include file="include/simpleSearch.jsp" %>
    
    <div class="productPageImg">
    	<img src="img/category/${product.category.id }.jpg" width="1024px">
    </div>
    
   	<div class="product">
    	<div class="productImgDiv">
        	<div class="productImg">
        		<c:forEach items="${product.productSingleImages }" var="image" varStatus="st">
            		<c:if test="${st.count==1 }">
            			<img src="img/product/productSingle/${image.id }.jpg" width="358px" height="358px">
            		</c:if>
            	</c:forEach>
            </div>
            <div class="productImgs">
            	<c:forEach items="${product.productSingleImages }" var="image" varStatus="st">
            		<c:if test="${st.count<=5 }">
            			<img src="img/product/productSingle/${image.id }.jpg">
            		</c:if>
            	</c:forEach>
            </div>
        </div><!--productImgDiv-->
        
        <div class="productMessage">
        	<div class="productTitle">${product.name }</div><!--productTitle-->
            <div class="productSubTitle">${product.subTitle }</div><!--productSubTitle-->
            <div class="productPrice">
            	<div class="juhuasuan"><b>聚划算</b> 此商品即将参加聚划算，<span>1天19小时</span>后开始</div><!--juhuasuan-->
                <div class="productPriceDiv">
                    <div class="shopCoupon"><img height="16px" src="img/site/gouwujuan.png">全天猫商品通用</div><!--shopCoupon-->
                    <div class="rawPrice">价格<span>￥<span>${product.orignalPrice }</span></span></div><!--rawPrice-->
                    <div class="discountPrice">促销价<span>￥<span>${product.discountPrice }</span></span></div><!--discountPrice-->
                </div>
            </div><!--productPrice-->
        	<div class="productSellAndCommentNum">
            	<span>销量 <span>${product.saleCount }</span></span><a>|</a>
            	<span>累计评价 <span>${product.reviewCount }</span></span>
            </div><!--productSellAndCommentNum-->
            
            <div class="productNumber">
            	数量
                <span class="inputSpan"><input type="text" value="1"></span>
                <span class="changeNumber">
                	<a href="#nowhere"><span class="glyphicon glyphicon-chevron-up"></span></a>
                    <a href="#nowhere"><span class="glyphicon glyphicon-chevron-down"></span></a>
                </span>件&nbsp;&nbsp;库存<span class="realNumber">${product.stock }</span>件
            </div><!--productNumber-->
            <div class="serviceCommitment">
            	服务承诺
                <a href="#nowhere">正品保证</a>
                <a href="#nowhere">极速退款</a>
                <a href="#nowhere">赠运费险</a>
                <a href="#nowhere">七天无理由退换</a>
            </div><!--serviceCommitment-->
            <div class="productPurchase">
            	<c:if test="${empty sessionScope.userName }">
            		<a href="#nowhere" class="purchaseNow" data-toggle="modal" data-target="#myModal">立即购买</a><a href="#nowhere" class="addToCart" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</a>
	           	</c:if>
	           	<c:if test="${!(empty sessionScope.userName) }">
	           		<input type="hidden" id="productID" value="${product.id }">
	           		<a href="#nowhere" id="buyNowBtn" class="purchaseNow">立即购买</a><a href="#nowhere" id="addToCart" class="addToCart"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</a>
	           	</c:if>
            </div><!--productPurchase-->
        </div><!--productMassage-->
        
        <div style="clear:both;"></div>
        
        <div class="productDetail">
        	<div class="productDetailTag">
            	<a href="#nowhere" class="tagSelected"><div></div>商品详情</a><a href="#nowhere" class="productCommentTagStyle"><div></div>累计评论<span>${product.reviewCount }</span></a></div><!--productDetailTag-->
            <div class="productDetails">
            	<div>产品参数</div>
            	<c:forEach items="${propertyValues }" var="propertyValue" varStatus="st">
            		<span>${propertyValue.property.name }：${propertyValue.value }</span>
            	</c:forEach>
            </div><!--productDetails-->
            
            <div class="productDetailImg">
            	<c:forEach items="${product.productDetailImages }" var="image" varStatus="st">
            		<img src="img/product/productDetail/${image.id }.jpg" width="904px">
            	</c:forEach>
            </div>
        </div><!--productDetail-->
        
        <div class="productComment" style="display:none;">
        	<div class="productDetailTag">
            	<a href="#nowhere"><div></div>商品详情</a><a href="#nowhere" class="productCommentTagStyle tagSelected"><div></div>累计评论<span>${product.reviewCount }</span></a>
            </div><!--productDetailTag-->
            
            <div class="productCommentDiv">
            
	            <c:forEach items="${reviews }" var="review" varStatus="st">
	            	<div class="productComments">
		            	<div class="commentLeft">
	                        <div class="commentContent">${review.content }</div>
	                        <div class="commentTime"><fmt:formatDate value="${review.createDate }" pattern="yy-MM-dd hh:mm:ss"/></div>
	                    </div><!--commentLeft-->
	                	<div class="commentUser">${review.user.anonymousName }<div>（匿名）</div></div>
	            	</div><!--productComments-->
	            </c:forEach>
            	
                <div style="clear:both"></div>
            </div><!--productCommentDiv-->
        </div><!--productComment-->
        
    </div><!--product-->
<%@include file="include/footer.jsp"%>