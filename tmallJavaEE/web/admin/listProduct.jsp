<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
        <ol class="breadcrumb">
  			<li><a href="admin_category_list">所有分类</a></li>
  			<li><a href="admin_product_list?cid=${category.id }">${category.name }</a></li>
  			<li class="active">产品管理</li>
		</ol><!-- breadcrumb -->
    	<table class="table table-striped table-bordered table-hover">
            <thead>
          	    <tr class="success">
                    <th>ID</th>
                    <th>图片</th>
                    <th>产品名称</th>
                    <th>产品小标题</th>
                    <th>原价格</th>
                    <th>折扣价格</th>
                    <th>库存数量</th>
                    <th>创建时间</th>
                    <th>图片管理</th>
                    <th>设置属性</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${products }" var="product" varStatus="st">
	            	<tr>
		                <td>${product.id }</td> 
		                <td><img src="../img/product/productSingleSmall/${product.firstProductImage }.jpg" width="40px"></td> 
		                <td>${product.name }</td>
		                <td>${product.subTitle }</td>
		                <td>${product.orignalPrice }</td>
		                <td>${product.discountPrice }</td>
		                <td>${product.stock }</td>
		                <td><fmt:formatDate value="${product.createDate }" pattern="yyyy-MM-dd HH:mm"/></td>
		                <td><a href="admin_productImage_list?pid=${product.id }"><span class="glyphicon glyphicon-picture"></span></a></td>
		                <td><a href="admin_product_editPropertyValue?pid=${product.id }"><span class="glyphicon glyphicon-th-list"></span></a></td>
		                <td><a href="admin_product_edit?id=${product.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
		                <td><a href="admin_product_delete?id=${product.id }&cid=${category.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td> 
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
        
		<%@include file="include/adminPage.jsp"%>
		
        <div class="panel panel-warning">
        	<div class="panel-heading">新增产品</div>
            <div class="panel-body">
                <form  method="post" id="addProductForm" action="admin_product_add">
                    <table>
                       <tr><td>产品名称</td><td><input name="cid" type="hidden" value="${category.id }"/><input id="name" name="name" class="textInput" type="text" /></td></tr>
                       <tr><td>产品小标题</td><td><input id="subTitle" name="subTitle" class="textInput" type="text" /></td></tr>
                       <tr><td>原价格</td><td><input id="orignalPrice" name="orignalPrice" class="textInput" type="text" /></td></tr>
                       <tr><td>折扣价格</td><td><input id="discountPrice" name="discountPrice" class="textInput" type="text" /></td></tr>
                       <tr><td>库存</td><td><input id="stock" name="stock" class="textInput" type="text" /></td></tr>
                       <tr align="center"><td colspan="2"><button type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#addProductForm").submit(function(){
					if(!checkEmpty("name","产品名称")) return false;
					if(!checkEmpty("subTitle","产品小标题")) return false;
					if(!checkEmpty("orignalPrice","原价格")) return false;
					if(!checkEmpty("discountPrice","折扣价格")) return false;
					if(!checkEmpty("stock","库存")) return false;
					return true;
				});
			});
		</script>
