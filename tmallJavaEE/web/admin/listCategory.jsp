<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
    	<div class="labelOutDiv"><div class="label label-info">分类管理</div></div>
    	<table class="table table-striped table-bordered table-hover mainTable">
            <thead>
          	    <tr class="success">
                    <th>ID</th>
                    <th>图片</th>
                    <th>分类名称</th>
                    <th>属性管理</th>
                    <th>产品管理</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${categorys }" var="category" varStatus="st">
	            	<tr>
		                <td><c:out value="${category.id }" /></td> 
		                <td><img src="../img/category/${category.id }.jpg" height="40"></td> 
		                <td>${category.name }</td> 
		                <td><a href="admin_property_list?cid=${category.id }"><span class="glyphicon glyphicon-th-list"></span></a></td>
		                <td><a href="admin_product_list?cid=${category.id }"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
		                <td><a href="admin_category_edit?id=${category.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
		                <td><a href="admin_category_delete?id=${category.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td> 
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
        
		<%@include file="include/adminPage.jsp"%>
		
        <div class="panel panel-warning">
        	<div class="panel-heading">新增分类</div>
            <div class="panel-body">
                <form  method="post" id="addCategoryForm" action="admin_category_add" enctype="multipart/form-data">
                    <table>
                       <tr><td>分类名称</td><td><input id="categoryName" name="imgName" class="textInput" type="text" /></td></tr>
                       <tr><td>分类图片</td><td><input id="categoryImg" name="imgPath" accept="image/*" type="file"/></td></tr>
                       <tr class="previewImg"><td></td><td><div>预览图片</div></td><tr>
                       <tr align="center"><td colspan="2"><button type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#addCategoryForm").submit(function(){
					if(!checkEmpty("categoryName","分类名称"))
						return false;
					if(!checkEmpty("categoryImg","分类图片"))
						return false;
					return true;
				});

				$("#categoryImg").change(function(){
					var files = document.getElementById("categoryImg").files;
					if(files.length == 0){
						return;
					}
					var file = files[0];
					//把上传的图片显示出来
					var reader = new FileReader();
					// 将文件以Data URL形式进行读入页面
					reader.readAsBinaryString(file);
					reader.onload = function(){
						var src = "data:" + file.type + ";base64," + window.btoa(this.result);
						$(".previewImg td div").html('<img src ="'+src+'"/>');
					}	
				});
			});
		</script>
