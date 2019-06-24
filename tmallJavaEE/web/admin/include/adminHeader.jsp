<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 设置浏览器不缓存 -->
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache"/> 
<meta http-equiv="Expires" content="-1"/>
<script src="../js/jquery/2.0.0/jquery.js"></script>
<link href="../css/bootstrap/3.3.7/bootstrap.css" rel="stylesheet"/>
<script src="../js/bootstrap/3.3.7/bootstrap.js"></script>
<style type="text/css">
	*{
		margin:0px;
		padding:0px;
	}
	.outNav{
		padding-left:10px;
		background:#000;
		height:50px;
	}
	.outNav a{
		vertical-align:top;
		text-decoration:none;
		color:#9d9d9d;
		padding:0px 25px 0px 5px;
		line-height:50px;
		height:50px;
		font-size:16px;
		display:inline-block;
	}
	.outNav a:hover{
		color:#fff;
	}
	
	.tableOutDiv{
		margin:0px 40px 20px 40px;
	}
	
	.labelOutDiv,.breadcrumb{
		margin:20px 0px;
	}
	
	.mainTable td{
		text-align:center;
		line-height:40px !important;
	}
	
	.mainTable th{
		text-align:center;
	}
	
	.mainTable td a{
		height:40px;
		display:inline-block;
		padding-top:13px;
		
	}
	
	.panel{
		width:400px;
		margin:0px auto;
	}
	
	.panel table{
		width:100%;
	}
	
	.panel table td{
		padding-bottom:10px;
		padding-right:10px;
	}
	
	.panel .textInput{
		width:100%;
		padding:0px 10px;
		height:30px;
		border:#e8e8e8 1px solid;
		border-radius:3px;
	}
	
	tr.previewImg{
		padding-bottom:20px;
	}
	
	tr.previewImg div{
		height:40px;
		width:292px;
		text-align:center;
		border:1px solid #e8e8e8;
	}
	
	tr.previewImg div img{
		width:290px;
		height:38px;
	}
	
</style>

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
	
	function checkNumber(id,name){
		var value = $("#"+id).val();
		if(value==""){
			alert(name+"不能为空");
			$("#"+id).focus();
			return false;
		}
		if(isNaN(value)){
			alert(name+"必须是数字");
			$("#"+id).focus();
			return false;	
		}
		return true;
	}
	
	function checkInt(id,name){
		var value = $("#"+id).val();
		if(value==""){
			alert(name+"不能为空");
			$("#"+id).focus();
			return false;
		}
		if(parseInt(value)!=value){
			alert(name+"必须是整数");
			$("#"+id).focus();
			return false;
		}
		return true;
	}
	
	$(function(){
		$("a.deleteLink").click(function(){
			if(confirm("确认要删除")){
				return true;		
			}
			return false;
		});
		
	});
	
</script>
</head>
<body>