<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="include/adminHeader.jsp"%>
<%@include file="include/adminNavigator.jsp"%>
	<div class="tableOutDiv">
        <ol class="breadcrumb">
  			<li><a href="admin_category_list">所有分类</a></li>
  			<li><a href="admin_property_list?cid=${property.category.id }">${property.category.name }</a></li>
  			<li class="active">编辑属性</li>
		</ol><!-- breadcrumb -->
    	
        <div class="panel panel-warning">
        	<div class="panel-heading">编辑属性</div>
            <div class="panel-body">
                <form  method="post" id="editPropertyForm" action="admin_property_update">
                    <table>
                       <tr><td>属性名称</td><td><input name="id" type="hidden" value="${property.id }"/><input id="propertyName" name="name" class="textInput" type="text" value="${property.name }"/></td></tr>
                       <tr align="center"><td colspan="2"><button type="button" onclick="javascript:history.back(-1);" class="btn btn-cancel">取消</button><button style="margin-left:70px" type="submit" class="btn btn-success">提交</button></td></tr>
                   </table>
               </form>
            </div>
        </div><!-- panel -->
        
		<%@include file="include/adminFooter.jsp"%>
		
		<script type="text/javascript">
			$(function(){
				$("#editPropertyForm").submit(function(){
					if(!checkEmpty("propertyName","分类名称"))
						return false;
					return true;
				});
			});
		</script>
