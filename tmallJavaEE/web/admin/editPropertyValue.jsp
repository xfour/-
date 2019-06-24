<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>

	<div class="tableOutDiv">
        <ol class="breadcrumb">
  			<li><a href="admin_category_list">所有分类</a></li>
  			<li><a href="admin_product_list?cid=${product.category.id }">${product.category.name }</a></li>
  			<li class="active">${product.name }</li>
  			<li class="active">编辑产品属性</li>
		</ol><!-- breadcrumb -->
    </div>
    <style>
		.propertyValueDiv{
			width:700px;
			padding-left:10px;
			height:auto;
			margin:0px auto 0px auto;
		}
		
		.propertyDiv{
			text-align:right;
			width:170px;
			margin-bottom:20px;
			height:20px;
			line-height:20px;
			float:left;
		}
		
		.valueDiv{
			margin-right:10px;
			margin-bottom:20px;
			width:150px;
			height:20px;
			float:left;
		}
		
		.valueDiv input{
			width:150px;
			height:20px;
			border:1px solid #e8e8e8;
		}
	</style>
    <div class="propertyValueDiv">
    	<c:forEach items="${ptvs }" var="ptv" varStatus="st">
    		<div class="propertyDiv">${ptv.property.name }：</div><div class="valueDiv"><input type="text" ptvid="${ptv.id }" value="${ptv.value }"/></div>
    	</c:forEach>
        <div style="clear:both;"></div>
    </div>
    <script>
    	$("div.valueDiv input").keyup(function(){
			var id=$(this).attr("ptvid");
			var page="/admin_product_updatePropertyValue?id="+id;
			var value=$(this).val();
			var thisInput = $(this);
			$.ajax({
				url:page,
				data:{"value":value},
				success:function(result){
					if(result=="success") thisInput.css("border","1px solid green");
					else thisInput.css("border","1px solid red");
				}
			});
		});
    </script>
	<%@include file="include/adminFooter.jsp"%>
		
