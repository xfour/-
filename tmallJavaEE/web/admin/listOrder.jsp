<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
    	<div class="labelOutDiv"><div class="label label-info">订单管理</div></div>
    	<table class="table table-striped table-bordered table-hover">
            <thead>
          	    <tr class="success">
                    <th>ID</th>
                    <th>状态</th>
                    <th>金额</th>
                    <th>商品数量</th>
                    <th>买家名称</th>
                    <th>创建时间</th>
                    <th>支付时间</th>
                    <th>发货时间</th>
                    <th>确认收货时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${orders }" var="order" varStatus="st">
	            	<tr>
	            		<td>${order.id }</td> 
	            		<td>${order.statusDesc }</td> 
	            		<td>${order.total }</td> 
	            		<td>${order.totalNumber }</td> 
	            		<td>${order.user.name }</td> 
	            		<td><fmt:formatDate value="${order.createDate }" pattern="yyyy-MM-dd HH:mm"/></td> 
	            		<td><fmt:formatDate value="${order.payDate }" pattern="yyyy-MM-dd HH:mm"/></td> 
	            		<td><fmt:formatDate value="${order.deliveryDate }" pattern="yyyy-MM-dd HH:mm"/></td> 
	            		<td><fmt:formatDate value="${order.confirmDate }" pattern="yyyy-MM-dd HH:mm"/></td> 
	            		<td>
	            			<button oid="${order.id }" class="btn btn-primary btn-xs showOrderItemDetail">查看详情</button>
	            			<c:if test="${order.status=='waitDelivery' }"><a href="admin_order_delivery?id=${order.id }"><button class="btn btn-info btn-xs">发货</button></a></c:if>
	            			
	            		</td> 
	                </tr>
	                <c:forEach items="${order.orderItems }" var="orderItem" varStatus="st">
		                <tr class="hidden orderItemDetail${order.id }">
		                	<td colspan=10><div><img><a href="product?pid=${orderItem.product.id }"><span>${orderItem.product.name }</span></a><span>${orderItem.number }个</span><span>单价：￥${orderItem.product.discountPrice }</span></div></td>
		                </tr>
	                </c:forEach>
            	</c:forEach>
            </tbody>
        </table>
        <style>
			.showDetail{
				display:table-row !important;
				text-align:center;	
			}
			
			.showDetail span{
				margin:0px 20px;
			}
			
			.showDetail a{
				padding-right:50px;
			}
		</style>
		<%@include file="include/adminPage.jsp"%> 
		
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$(".showOrderItemDetail").click(function(){
					var id = $(this).attr("oid");
					$(".orderItemDetail"+id).toggleClass("showDetail");
				});
			});
		</script>
