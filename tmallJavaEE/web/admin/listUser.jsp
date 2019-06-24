<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
    	<div class="labelOutDiv"><div class="label label-info">用户管理</div></div>
    	<table class="table table-striped table-bordered table-hover mainTable">
            <thead>
          	    <tr class="success">
                    <th>ID</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${users }" var="user" varStatus="st">
	            	<tr>
		                <td>${user.id }</td> 
		                <td>${user.name }</td> 
		                <td>${user.password }</td> 
		                <td><a href="admin_user_delete?id=${user.id }" class="deleteLink"><span class="glyphicon glyphicon-remove-sign"></span></a></td> 
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
        
		<%@include file="include/adminPage.jsp"%>
		
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				
			});
		</script>
