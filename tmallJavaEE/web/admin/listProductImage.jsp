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
  			<li class="active">图片管理</li>
		</ol><!-- breadcrumb -->
    	
        
	</div>
    <style>
		div.imageOutDiv{
			width:1000px;
			margin:0 auto;
		}
		
		div.imageOutDiv b{
			color:#337ab7;
			margin:0px 5px;
		}
		
		div.imageOutDiv form div{
			margin:10px 0px;
			text-align:center;
		}
		
		div.imageOutDiv table{
			width:400px;
			margin-top:20px;
		}
		
		div.imageOutDiv table th,div.imageOutDiv table td{
			vertical-align:middle;
			text-align:center;
			padding:5px;
		}
		
		div.singleImageDiv{
			float:left;
			width:400px;
			margin:0px 80px 0px 20px;
		}
		
		div.detailImageDiv{
			float:left;
			width:400px;
			margin:0px 20px 0px 80px;
		}
		
		div.previewSingleImg,div.previewDetailImg{
			width:368px;
			border:1px solid #e8e8e8;
		}
		
	</style>
    <div class="imageOutDiv">
    	<div class="singleImageDiv">
        	<div class="panel panel-warning">
                <div class="panel-heading">新增产品<b>单个</b>图片</div>
                <div class="panel-body">
                    <form  method="post" id="addSingleImageForm" action="admin_productImage_add" enctype="multipart/form-data">
                    	<div>请选择本地图片&nbsp;尺寸400X400为佳</div>
                        <div><input id="productSingleImg" name="imgPath" accept="image/*" type="file"/></div>
                       	<div class="previewSingleImg">预览图片</div>
                        <input type="hidden" value="${product.id }" name="pid"/>
                        <input type="hidden" value="type_single" name="imageType"/>
                       	<div><button type="submit" class="btn btn-success">提交</button></div>
                   </form>
                </div><!-- panel-body -->
            </div><!-- panel -->
            
            <table class="table table-striped table-bordered table-hover">
            	<thead>
                	<tr class="success"><th>ID</th><th>产品单个图片缩略图</th><th>删除</th></tr>
                </thead>
                <tbody>
                	<c:forEach items="${productSingleImages }" var="productImage" varStatus="st">
                        <tr>
                            <td>${productImage.id }</td>
                            <td><a href=""><img height="50px" src="../img/product/productSingle/${productImage.id }.jpg"/></a></td>
                            <td><a href="admin_productImage_delete?id=${productImage.id }&pid=${product.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div><!-- singleImageDiv -->
        
        <div class="detailImageDiv">
            <div class="panel panel-warning">
                <div class="panel-heading">新增产品<b>详情</b>图片</div>
                <div class="panel-body">
                    <form  method="post" id="addDetailImageForm" action="admin_productImage_add" enctype="multipart/form-data">
                    	<div>请选择本地图片&nbsp;宽度790为佳</div>
                        <div><input id="productDetailImg" name="imgPath" accept="image/*" type="file"/></div>
                       	<div class="previewDetailImg">预览图片</div>
                        <input type="hidden" value="${product.id }" name="pid"/>
                        <input type="hidden" value="type_detail" name="imageType"/>
                       	<div><button type="submit" class="btn btn-success">提交</button></div>
                   </form>
                </div><!-- panel-body -->
            </div><!-- panel -->
            
            <table class="table table-striped table-bordered table-hover">
            	<thead>
                	<tr class="success"><th>ID</th><th>产品详情图片缩略图</th><th>删除</th></tr>
                </thead>
                <tbody>
                	<c:forEach items="${productDetailImages }" var="productImage" varStatus="st">
                        <tr>
                            <td>${productImage.id }</td>
                            <td><a href=""><img height="50px" src="../img/product/productDetail/${productImage.id }.jpg"/></a></td>
                            <td><a href="admin_productImage_delete?id=${productImage.id }&pid=${product.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div><!-- detailImageDiv -->
        
        <div style="clear:both"></div>
        
		<%@include file="include/adminFooter.jsp"%>
		<script type="text/javascript">
			$("#addSingleImageForm").submit(function(){
				if(!checkEmpty("productSingleImg","产品单个图片"))
					return false;
				return true;
			});
			
			$("#addDetailImageForm").submit(function(){
				if(!checkEmpty("productDetailImg","产品详情图片"))
					return false;
				return true;
			});
		
			$(function(){
				$("#productSingleImg").change(function(){//实现预览本地选定的产品单个图片
					var files = document.getElementById("productSingleImg").files;
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
						$(".previewSingleImg").html('<img width="366px" src ="'+src+'"/>');
					}	
				});
				
				$("#productDetailImg").change(function(){//实现预览本地选定的产品详情图片
					var files = document.getElementById("productDetailImg").files;
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
						$(".previewDetailImg").html('<img width="366px" src ="'+src+'"/>');
					}	
				});
			});
		</script>
