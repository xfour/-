<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/top.jsp" %>

<script type="text/javascript">
	var x=3;
	function go(){ 
		x--; 
		if(x>0) $("b").html(x); 
		else location.href='login.jsp'; 
	}
		
	$(function(){
		setInterval(go,1000);
	});
	
</script>

<link href="css/fore/registerSuccess.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
	<%@include file="include/simpleSearch.jsp" %>
    
    <div class="registerSuccessDiv">
    	<img src="img/site/registerSuccess.png">恭喜注册成功,<b>3</b>秒后将自动跳转到登录页面！
    </div><!-- receiveSuccessDiv -->

<%@include file="include/footer.jsp"%>