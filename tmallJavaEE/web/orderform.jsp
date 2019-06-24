<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
			$(".orderFormListTag li").click(function(){
				$(".orderFormListTag li").css("border-bottom","none");
				$(".orderFormListTag li a").css("color","#333");
				$(this).css("border-bottom","1px solid #dd2727");	
				$(this).children("a").css("color","#dd2727");
				var orderstatus = $(this).children("a").attr("orderstatus");
				if(orderstatus == "allOrderItem"){
					$(".orderFormListBody").show();
				}else{
					$(".orderFormListBody").hide();
					$(".orderFormListBody[orderstatus="+orderstatus+"]").show();
				}
			});
			

			$(function(){//删除按钮弹出alert
				$("a.deleteLink").click(function(){
					if(confirm("确认要删除")){
						return true;		
					}
					return false;
				});
				
			});
			
			$(".orderItemDelivery").click(function(){
				var oid = $(this).attr("oid");
				var page="foredelivery";
				$.ajax({
					url:page,
					data:{"oid":oid},
					success:function(result){
						if(result=="success"){
							alert("卖家已经收到了您的请求，将尽快为您发货！");
							window.location.reload();
						}
					}
				});
			});
		});
	</script>
	
	<link href="css/fore/orderform.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
	<%@include file="include/simpleSearch.jsp" %>
    
    <div class="orderFormListDiv">
    	<div class="orderFormListTag">
        	<ul>
            	<li style="border-bottom:1px solid #dd2727;" ><a style="color:#dd2727;"
                href="#nowhere" orderstatus="allOrderItem">所有订单</a></li>
                <li><span></span></li>
                <li><a href="#nowhere" orderstatus="waitPay">待付款</a></li>
                <li><span></span></li>
            	<li><a href="#nowhere" orderstatus="waitDelivery">待发货</a></li>
                <li><span></span></li>
                <li><a href="#nowhere" orderstatus="waitConfirm">待收货</a></li>
                <li><span></span></li>
            	<li><a href="#nowhere" orderstatus="waitReview">待评价</a></li>
            </ul>
        </div><!--orderFormListTag-->
        
        <table class="orderFormListHead">
            <tr>
                <td style="text-align:center;">宝贝</th>
                <td width="100px">单价</th>
                <td width="100px">数量</th>
                <td width="120px">实付款</th>
                <td width="100px">交易操作</th>
            </tr>
        </table>
        
        <c:forEach items="${orders }" var="order" varStatus="st">
	        <table oid="${st.count }" orderstatus="${order.status }" class="orderFormListBody">
	            <tr class="orderItemTop">
	                <td class="leftTd"><span class="orderItemTime">${order.payDate }</span>&nbsp;<span class="orderItemId">订单号:<span>${order.orderCode }</span></span></td>
	                <td width="100px"><img height="16px" style="vertical-align:text-bottom;" src="img/site/orderItemTmall.png">天猫商场</td>
	                <td width="100px"></td>
	                <td width="120px"><a href="#nowhere"><div class="wangwangGif"></div></a></td>
	                <td width="100px"><a class="deleteLink" href="foredeleteOrder?oid=${order.id }"><span class="glyphicon glyphicon-trash"></span></a></td>
	            </tr>
	            
	           	<c:forEach items="${order.orderItems }" var="orderItem" varStatus="st1">
	           		<tr class="orderItemContent">
		                <td class="leftTd">
		                    <img class="orderItemImg" width="82px" height="78px" src="img/product/productSingleMiddle/${orderItem.product.firstProductImage }.jpg">
		                    <div class="orderItemDetail">
		                        <span><a href="foreproduct?pid=${orderItem.product.id }">${orderItem.product.name }</a></span><br>
		                        <img width="16px" src="img/site/creditcard.png" title="支持信用卡支付"><img width="16px" src="img/site/promise.png" title="消费者保障服务,承诺如实描述"><img width="16px" src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
		                    </div>
		                </td>
		                <td class="borderTd" width="100px">
		                    <div class="originalPrice">￥${orderItem.product.orignalPrice }</div>
		                    <div class="discountPrice">￥${orderItem.product.discountPrice }</div>
		                </td>
			            <td class="borderTd" width="100px"><span class="orderItemNumber">${orderItem.number }</span></td>
		                <c:if test="${st1.count==1 }">
			                <td class="borderTd" width="120px" rowspan="${order.orderItems.size() }"><div class="actualPayment">￥${order.total }</div><div class="feight">(含运费：￥<span>0.00</span>)</div></td>
		                	<c:if test="${order.status!='waitReview' }">
				                <td class="borderTd" width="100px" rowspan="${order.orderItems.size() }">
				                	<c:if test="${order.status=='waitPay' }"><a href="forealipay?oid=${order.id }&total=${order.total }"><div class="orderItemPay">付款</div></a></c:if>
				                	<c:if test="${order.status=='waitDelivery' }">待发货<a href="#nowhere"><div class="orderItemDelivery" oid="${order.id }">催卖家发货</div></a></c:if>
				                	<c:if test="${order.status=='waitConfirm' }"><a href="foreconfirm?oid=${order.id }"><div class="orderItemConfirm">确认收货</div></a></c:if>
				                	<c:if test="${order.status=='finish' }"><a href="#nowhere" style="pointer-events:none"><div>交易完成</div></a></c:if>
				                </td>
			                </c:if>
			                
		                </c:if>
		                <c:if test="${order.status=='waitReview' }">
		                	<c:if test="${(empty orderItem.product.rvid)||orderItem.product.rvid=='-1' }">
		                		<td class="borderTd" width="100px"><a href="forereview?pid=${orderItem.product.id }&oid=${order.id }"><div class="orderItemReview">评价</div></a></td>
		                	</c:if>
		                	<c:if test="${(!empty orderItem.product.rvid)&&orderItem.product.rvid!='-1' }">
		                		<td class="borderTd" width="100px"><a href="#nowhere" style="pointer-events:none"><div class="orderItemReview">已评价</div></a></td>
		                	</c:if>
		                </c:if>
		            </tr>
	           	</c:forEach>
	            
	        </table>
        </c:forEach>
        
    </div><!--orderFormListDiv-->
    
<%@include file="include/footer.jsp"%>