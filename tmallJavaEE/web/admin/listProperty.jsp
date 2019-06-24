<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
        <ol class="breadcrumb">
  			<li><a href="admin_category_list">所有分类</a></li>
  			<li><a href="admin_property_list?cid=${category.id }">${category.name }</a></li>
  			<li class="active">属性管理</li>
		</ol><!-- breadcrumb -->
    	<table class="table table-striped table-bordered table-hover">
            <thead>
          	    <tr class="success">
                    <th>ID</th>
                    <th>属性名称</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${propertys }" var="property" varStatus="st">
	            	<tr>
		                <td>${property.id }</td> 
		                <td>${property.name }</td> 
		                <td><a href="admin_property_edit?id=${property.id }"><span class="glyphicon glyphicon-edit"></span></a></td>
		                <td><a href="admin_property_delete?id=${property.id }&cid=${category.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td> 
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
        
		<%@include file="include/adminPage.jsp"%>
		
        <div class="panel panel-warning">
        	<div class="panel-heading">新增属性</div>
            <div class="panel-body">
                <form  method="post" id="addPropertyForm" action="admin_property_add">
                    <table>
                       <tr><td>属性名称</td><td><input name="cid" type="hidden" value="${category.id }"/><input id="propertyName" autofocus="autofocus" name="name" class="textInput" type="text" /></td></tr>
                       <tr align="center"><td colspan="2"><button type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#addPropertyForm").submit(function(){
					if(!checkEmpty("propertyName","分类名称"))
						return false;
					return true;
				});
				
				
			});
		</script>
