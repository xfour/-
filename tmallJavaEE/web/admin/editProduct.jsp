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
  			<li class="active">编辑产品</li>
		</ol><!-- breadcrumb -->
    	
        <div class="panel panel-warning">
        	<div class="panel-heading">编辑产品</div>
            <div class="panel-body">
                <form  method="post" id="editProductForm" action="admin_product_update">
                    <table>
                       <tr><td>产品名称</td><td><input name="id" type="hidden" value="${product.id }"/><input id="name" name="name" class="textInput" type="text" value="${product.name }"/></td></tr>
                       <tr><td>产品小标题</td><td><input id="subTitle" name="subTitle" class="textInput" type="text" value="${product.subTitle }"/></td></tr>
                       <tr><td>原价格</td><td><input id="orignalPrice" name="orignalPrice" class="textInput" type="text" value="${product.orignalPrice }"/></td></tr>
                       <tr><td>折扣价格</td><td><input id="discountPrice" name="discountPrice" class="textInput" type="text" value="${product.discountPrice }"/></td></tr>
                       <tr><td>库存</td><td><input id="stock" name="stock" class="textInput" type="text" value="${product.stock }"/></td></tr>
                       <tr align="center"><td colspan="2"><button type="button" onclick="javascript:history.back(-1);" class="btn btn-cancel">取消</button><button style="margin-left:70px" type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#editProductForm").submit(function(){
					if(!checkEmpty("name","产品名称")) return false;
					if(!checkEmpty("subTitle","产品小标题")) return false;
					if(!checkEmpty("orignalPrice","原价格")) return false;
					if(!checkEmpty("discountPrice","折扣价格")) return false;
					if(!checkEmpty("stock","库存")) return false;
					return true;
				});
			});
		</script>
