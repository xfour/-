<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	div.loginInputDiv{
		width:350px;
		height:400px;
		background:#fff;
		padding:20px 25px 0px 25px;
	}
	
	div.loginMsg{
		visibility:hidden;
		text-align:center;
		height:30px;
		line-height:30px;
		background:#f2dede;
	    color:#a94442;
	    border:#ebccd1 1px solid;
	    border-radius:3px;
	}
	
	div.loginTop{
		color:#333;
		font-weight:bold;
		font-size:16px;
		padding-bottom:10px;
	}
	
	div.loginInput{
		width:300px;
		height:40px;
		margin-top:20px;
		border:1px solid #cbcbcb;
	}
	
	div.loginInput span{
		width:38px;
		height:38px;
		background:#cbcbcb;
		text-align:center;
		font-size:22px;
		line-height:40px;
		color:#606060;
		display:inline-block;
		top:0px;
	}
	
	div.loginInput input{
		border:none;
		width:240px;
		height:28px;
		vertical-align:top;
		margin:5px 10px;
	}
	
	div.loginWarning{
		padding:20px 0px;
		color:#c40000;
	}
	
	div.loginOther{
		width:300px;	
	}
	
	div.loginOther span{
		float:right;	
	}
	
	div.loginBtn{
		margin:20px 0px;
		width:300px;
		height:35px;
	}
	
	div.loginBtn input{
		border:none;
		font-size:14px;
		font-weight:bold;
		color:#fff;
		background:#dd2727;
		border-radius:4px;
		width:300px;
		height:35px;
	}
	
	div.loginBtn input:hover{
		background:#c40000;
	}

</style>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" style="width:350px;">
        <div class="modal-content">
            <div class="loginInputDiv">
				<div class="loginMsg"></div>
            	<div class="loginTop">账户登录</div>
                <div class="loginInput"><span class="glyphicon glyphicon-user"></span><input id="name" name="name" type="text"></div>
                <div class="loginInput"><span class="glyphicon glyphicon-lock"></span><input id="password" name="password" type="password"></div>
                <div class="loginWarning">不要输入真实的天猫账号密码</div>
                <div class="loginOther"><a href="#">忘记登录密码</a><span><a href="register.jsp">免费注册</a></span></div>
                <div class="loginBtn"><input type="submit" value="登录"></div>
            </div><!-- loginInputDiv -->
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
</div>

<script type="text/javascript">
	$(function(){
		function checkEmpty(id,name){
			var value = $("#"+id).val();
			if(value==""){
				alert(name+"不能为空");
				$("#"+id).focus();
				return false;
			}
			return true;
		}
		
		$(".loginBtn input").click(function(){
			if(!checkEmpty("name","用户名"))
				return false;
			if(!checkEmpty("password","密码"))
				return false;
			var page="foreloginAjax";
			var name=$("#name").val();
			var password = $("#password").val();
			$(".loginMsg").css("visibility","hidden");
			$.ajax({
				url:page,
				data:{"name":name,"password":password},
				success:function(result){
					if(result=="success"){
						window.location.reload();
					}else if(result=="failed"){
						$(".loginMsg").html("账号或密码错误!");
						$(".loginMsg").css("visibility","visible");
					}
				}
			});
		});
		
	});
</script>