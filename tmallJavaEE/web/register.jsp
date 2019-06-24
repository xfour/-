<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
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
			$("#registerForm").submit(function(){
				if(!checkEmpty("registerName","会员名"))
					return false;
				if(!checkEmpty("registerPassword","密码"))
					return false;
				if(!checkEmpty("registerRepassword","确认密码"))
					return false;
				return true;
			});
			
			$("#registerRepassword").keyup(function(){
				$(".validRePassword").hide();
				var repassword=$(this).val();
				var password=$("#registerPassword").val();
				if(repassword!=password){
					$(".validRePassword").show();
				}
			});
			
			$("#registerName").keyup(function(){
				$(".registerMsg").hide();
				$(".registerMsg").html("");
			});
		});
	</script>
	
	<link href="css/fore/register.css" rel="stylesheet">
</head>

<body>
	<%@include file="include/header.jsp" %>    
    <%@include file="include/simpleSearch.jsp" %>
    
     <c:if test="${!(empty registermsg) }">
	 	<div class="registerMsg">${registermsg }</div>
	 </c:if>
	 <div class="validRePassword" style="display:none;">密码与确认密码不一致!</div>
	 <div class="registerDiv">
	 	<form id="registerForm" action="foreregister" method="post">
	         <table>
	             <tr><td class="leftTd tdTitle">设置会员名</td></tr>
	             <tr><td class="leftTd">会员名</td><td><input name="name" id="registerName" type="text" placeholder="会员名一旦设置成功，无法修改"></td></tr>
	             <tr><td class="leftTd tdTitle">设置登录密码</td><td>登录时验证，保护账号信息</td></tr>
	             <tr><td class="leftTd">登录密码</td><td><input name="password" id="registerPassword" type="password" placeholder="设置你的登录密码"></td></tr>
	             <tr><td class="leftTd">密码确认</td><td><input name="repassword" type="password" id="registerRepassword" placeholder="请再次输入你的密码"></td></tr>
	             <tr><td class="tdButton" colspan="2"><button >提交</button></td></tr>
	         </table>
	     </form>
	 </div><!-- registerDiv -->
    
<%@include file="include/footer.jsp" %>