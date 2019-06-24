<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
		});
	</script>
	
	<link href="css/fore/payed.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
    <%@include file="include/simpleSearch.jsp" %>
    
    <div class="payedOutDiv">
    	<div class="payedInfo"><img width="36px" height="33px" src="img/site/paySuccess.png">您已成功付款</div>
        <div class="payedDetail">
        	<ul>
            	<li>收获地址：<span class="payedAddress">${order.address }</span></li>
                <li>实付款：<b>￥<span class="payedSum"><fmt:formatNumber type="number" value="${order.total}" minFractionDigits="2"/></span></b></li>
                <li>预计<fmt:formatDate value="${order.payDate}" pattern="MM"/>月<fmt:formatDate value="${order.payDate}" pattern="dd"/>日送达</li>
            </ul>
            <div class="payedOperation">您可以 <a href="foreorderform" class="buyedItem">查看已买到的宝贝</a><a  href="foreorderform" class="payedRecord">查看交易记录</a></div>
        </div><!-- payedDetail -->
        <div class="payedWarning"><img width="21px" height="20px" src="img/site/warning.png"><b>安全提醒：</b>下单后,<span>用QQ给您发送链接办理退款的都是骗子！</span>天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！</div><!-- payedWarning -->
    </div><!-- payedOutDiv -->
    
<%@include file="include/footer.jsp"%>