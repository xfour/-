<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
			var x=3;
			function go(){ 
				x--; 
				if(x>0) $("b").html(x); 
				else location.href='foreorderform'; 
			}
				
			$(function(){
				setInterval(go,1000);
			});
		});
	</script>
	
	<link href="css/fore/receiveSuccess.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
	<%@include file="include/simpleSearch.jsp" %>
    
    <div class="receiveSuccessDiv">
    	<img src="img/site/orderFinish.png" width="35px" height="36px">交易已经成功，卖家将收到您的货款。<b>3</b>秒后将自动跳转到我的订单页面！
    </div><!-- receiveSuccessDiv -->
    
<%@include file="include/footer.jsp"%>