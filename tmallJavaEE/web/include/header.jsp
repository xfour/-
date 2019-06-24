<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="nav_div">
        <nav class="top">
            <a href="forehome"><span class="redcolor glyphicon glyphicon-home"></span>天猫首页</a><span>喵，欢迎来到天猫</span>
            <c:if test="${empty sessionScope.userName }">
            	<a href="login.jsp">请登录</a><a href="register.jsp">免费注册</a>
           	</c:if>
           	<c:if test="${!(empty sessionScope.userName) }">
            	<a href="foreuserMessage" id="userName">${sessionScope.userName }</a><a href="forelogout">注销</a>
           	</c:if>
           	<c:if test="${empty sessionScope.userName }">
            	<span class="pull-right"><a href="#" data-toggle="modal" data-target="#myModal">我的订单</a><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-shopping-cart redcolor"></span>购物车<strong>0</strong>件</a></span>
           	</c:if>
           	<c:if test="${!(empty sessionScope.userName) }">
           		<span class="pull-right"><a href="foreorderform">我的订单</a><a href="forecart"><span class="glyphicon glyphicon-shopping-cart redcolor"></span>购物车<strong id="cartNumber">${sessionScope.cartNumber }</strong>件</a></span>
            </c:if>
        </nav><!-- nav top -->
    </div>

<%@include file="modal.jsp" %>
    