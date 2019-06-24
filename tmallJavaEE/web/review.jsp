<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		function checkEmpty(id,name){
			var value = $("#"+id).val();
			if(value==""){
				alert(name+"不能为空！");
				$("#"+id).focus();
				return false;
			}
			return true;
		}
	
		$(function(){
			$("#addReviewForm").submit(function(){
				if(!checkEmpty("pid","谁叫你瞎改！"))
					return false;
				if(!checkEmpty("oid","谁叫你瞎改！"))
					return false;
				if(!checkEmpty("content","评论内容"))
					return false;
				return true;
			});
		});
	</script>
	
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
        <form id="addReviewForm" action="foreaddReview" method="post">
	        <div class="reviewDiv">
	        	<div class="reviewInfo">其他买家需要您的建议哦！</div><!-- reviewInfo -->
	            <div class="review">
	            	<div class="reviewLeft">评价商品</div>
	                <div class="reviewRight">
	                	<input type="hidden" name="pid" value="${product.id }">
	                	<input type="hidden" name="oid" value="${order.id }">
	                	<textarea name="content"></textarea>
	                </div>
	            </div><!-- review -->
	        </div><!--reviewDiv-->
	        <div class="reviewBtn"><button type="submit">提交评论</button></div><!-- reviewBtn -->
        </form>
    </div><!-- reviewOutDiv -->
    
<%@include file="include/footer.jsp"%>