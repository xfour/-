<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		function checkEmpty(id,name){
			var value = $("#"+id).val();
			if(value==""){
				alert(name+"不能为空");
				$("#"+id).focus();
				return false;
			}
			return true;
		}
		
		$(function(){
			$("#loginForm").submit(function(){
				if(!checkEmpty("name","用户名"))
					return false;
				if(!checkEmpty("password","密码"))
					return false;
				return true;
			});
		});
	</script>
	<link href="css/fore/login.css" rel="stylesheet">
</head>

<body>
    <div class="simpleLogoDiv">
        <a href="#"><img class="simpleLogo" width="190px" height="27px" src="img/site/simpleLogo.png" id="simpleLogo"></a>
    </div><!-- 简单搜索框的外部div -->
    
    <div class="loginOutDiv">
    	<div class="loginDiv">
        	<div class="loginInputDiv">
        		<form id="loginForm" action="forelogin" method="post">
        			<c:if test="${empty loginmsg}">
						<div class="loginMsg"></div>
					 </c:if>
        			<c:if test="${!(empty loginmsg) }">
						<div class="loginMsg" style="visibility:visible">${loginmsg }</div>
					</c:if>
	            	<div class="loginTop">账户登录</div>
	                <div class="loginInput"><span class="glyphicon glyphicon-user"></span><input id="name" name="name" type="text"></div>
	                <div class="loginInput"><span class="glyphicon glyphicon-lock"></span><input id="password" name="password" type="password"></div>
	                <div class="loginWarning">不要输入真实的天猫账号密码</div>
	                <div class="loginOther"><a href="#">忘记登录密码</a><span><a href="register.jsp">免费注册</a></span></div>
	                <div class="loginBtn"><input type="submit" value="登录"></div>
                </form>
            </div><!-- loginInputDiv -->
        </div><!-- loginDiv -->
    </div><!-- loginOutDiv -->
    
<%@include file="/include/footer.jsp" %>
