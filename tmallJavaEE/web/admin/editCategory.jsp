<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
        <ol class="breadcrumb">
  			<li><a href="admin_category_list">所有分类</a></li>
  			<li><a href="admin_category_list">${category.name }</a></li>
  			<li class="active">编辑分类</li>
		</ol><!-- breadcrumb -->
    	
        <div class="panel panel-warning">
        	<div class="panel-heading">编辑分类</div>
            <div class="panel-body">
                <form  method="post" id="editCategoryForm" action="admin_category_update" enctype="multipart/form-data">
                    <table>
                       <tr><td>分类名称</td><td><input name="id" type="hidden" value="${category.id }"/><input id="categoryName" name="imgName" class="textInput" type="text" value="${category.name }"/></td></tr>
                       <tr><td>分类图片</td><td><input id="categoryImg" name="imgPath" accept="image/*" type="file"/></td></tr>
                       <tr class="previewImg"><td></td><td><div><img src="../img/category/${category.id }.jpg" height="38px"></div></td><tr>
                       <tr align="center"><td colspan="2"><button type="button" onclick="javascript:history.back(-1);" class="btn btn-cancel">取消</button><button style="margin-left:70px" type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#editCategoryForm").submit(function(){
					if(!checkEmpty("categoryName","分类名称"))
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
