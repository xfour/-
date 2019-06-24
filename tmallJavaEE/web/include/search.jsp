<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<div class="searchOutDiv">
    <a href="#"><img class="logo" src="img/site/logo.gif" id="logo"></a>
    
    <div class="searchDiv">
    	<form id="searchForm" action="foresearch" method="post">
    	    	<input class="searchInput" type="text" id="searchInput" name="search" placeholder="请输入关键字" value="${keyword }"><input class="searchBtn" type="submit" value="搜索">	
    	</form>
    	<div class="searchBelow">
    		<c:forEach items="${categorys }" var="category" varStatus="st">
	            <c:if test="${st.count<=7&&st.count%2==1 }">
	            	<c:if test="${st.count!=1 }"><span>|</span></c:if>
	            	<a href="forecategory?cid=${category.id }">${category.name }</a>
	            </c:if>
            </c:forEach>
        </div><!-- searchBelow -->
    </div><!-- searchDiv -->
</div><!-- 搜索框的外部div -->
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