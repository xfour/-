<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/top.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".userMessageData").keyup(function(){//当数量改变时通过异步提交到数据库的orderItem
			var address = $("#address").val();
			var post = $("#post").val();
			var receiver = $("#receiver").val();
			var mobile = $("#mobile").val();
			var thisInput=$(this);
			var page="foreupdateUserMessage";
			$.ajax({
				url:page,
				data:{"address":address,"post":post,"receiver":receiver,"mobile":mobile},
				success:function(result){
					if(result=="success") thisInput.css("border","1px solid green");
					else thisInput.css("border","1px solid red");
				}
			});
		});
		
	});
</script>

<link href="css/fore/userMessage.css" rel="stylesheet">
</head>
<body>
   <%@include file="include/header.jsp" %>
    
   <div class="purchaseFlowImg">
        <a href="#"><img class="simpleLogo" width="190px" height="27px" src="img/site/simpleLogo.png" id="simpleLogo"></a>
    </div><!-- 顶部进度显示图片 -->
    
    <div class="purchaseOutDiv">
        <div class="deliveryAddressDiv">
            <div class="deliveryAddressTitle">用户基本信息</div>
            <table>
            	<tr><td>会员名</td><td>${userMessage.userName }(会员名设置后不可修改)</td></tr>
                <tr>
                    <td width="30%">详细地址<span>*</span></td><td><textarea id="address" class="userMessageData" name="address" placeholder="建议您如实填写详细收获地址,例如街道名称,门牌号码,楼层和房间名等信息">${userMessage.address }</textarea></td>
                </tr>
                <tr>
                    <td>邮政编码</td><td><input id="post" class="userMessageData" value="${userMessage.post }" name="post" placeholder="如果您不清楚邮政编码,请填写00000"></td>
                </tr>
                <tr>
                    <td>收货人姓名<span>*</span></td><td><input id="receiver" class="userMessageData" value="${userMessage.receiver }" name="receiver" placeholder="长度不超过25个字符"></td>
                </tr>
                <tr>
                    <td>手机号码<span>*</span></td><td><input id="mobile" class="userMessageData" value="${userMessage.mobile }" name="mobile" placeholder="请输入11位手机号码"></td>
                </tr>
                
            </table>
        </div><!-- deliveryAddressDiv -->
    </div><!-- purchaseOutDiv -->
    
<%@include file="include/footer.jsp"%>