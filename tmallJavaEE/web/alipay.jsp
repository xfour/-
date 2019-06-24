<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/top.jsp" %>

	<script type="text/javascript">
		
	</script>
	
	<link href="css/fore/alipay.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
    
   <div class="simpleSearchOutDiv">
        <a href="#"><img class="simpleLogo" width="190px" height="27px" src="img/site/simpleLogo.png" id="simpleLogo"></a>
    </div><!-- 简单搜索框的外部div -->
    
    <div class="alipayOutDiv">
    	<div>扫一扫付款(元)</div>
        <div class="alipaySum">￥<span>${total }</span></div>
        <div class="alipayImg"><img width="200px" src="img/site/alipay.jpg"></div>
        <div class="alipayConfirm"><a href="forepayed?oid=${oid }&total=${total }"><button id="confirmPay" >确认支付</button></a></div>
    </div><!--alipayOutDiv-->
    
<%@include file="include/footer.jsp"%>