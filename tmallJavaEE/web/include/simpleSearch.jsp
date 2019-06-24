<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div class="simpleSearchOutDiv">
     <a href="#"><img class="simpleLogo" width="190px" height="27px" src="img/site/simpleLogo.png" id="simpleLogo"></a>
     
     <div class="simpleSearchDiv">
    	<form id="searchForm" action="foresearch" method="post">
     		<input class="simpleSearchInput" type="text" id="searchInput" name="search" placeholder="请输入关键字"><input class="simpleSearchBtn" type="submit" value="搜天猫">
    	</form>
     	<div class="simpleSearchBelow">
     		<c:forEach items="${categorys }" var="category" varStatus="st">
	            <c:if test="${st.count<=7&&st.count%2==1 }">
	            	<c:if test="${st.count!=1 }"><span>|</span></c:if>
	            	<a href="forecategory?cid=${category.id }">${category.name }</a>
	            </c:if>
            </c:forEach>
         </div>
     </div><!-- simpleSearchDiv -->
 </div><!-- 简单搜索框的外部div -->
 <script type="text/javascript">
	
	$(function(){
		$("#searchForm").submit(function(){
			var value = $("#searchInput").val();
			if(value==""){
				alert("搜索框输入不能为空！");
				$("#searchInput").focus();
				return false;
			}
			return true;
		});
	});
	
</script>