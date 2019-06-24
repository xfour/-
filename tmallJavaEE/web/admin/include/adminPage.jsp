<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
        <nav style="text-align:center;">
          <ul class="pagination">
            <li <c:if test="${!page.hasPrevious }">class="disabled"</c:if>>
              <a href="?start=0${page.params }" aria-label="Previous">
                <span aria-hidden="true">«</span>
              </a>
            </li>
            <li <c:if test="${!page.hasPrevious }">class="disabled"</c:if>>
              <a href="?start=${page.start-page.count }${page.params }" aria-label="Previous">
                <span aria-hidden="true">‹</span>
              </a>
            </li>
            <!-- 显示当前页面的前后三个页面的链接 -->
            <c:forEach begin="${page.start/page.count-3 ge 0?(page.start/page.count-3):0}"
            		   end="${(page.start/page.count+3) le page.lastPageStart/page.count?(page.start/page.count+3):(page.lastPageStart/page.count)}"
            		   varStatus="status">
            		<li <c:if test="${status.index*page.count==page.start }">class="disabled"</c:if>>
            			<a href="?start=${status.index*page.count}${page.params }" <c:if test="${status.index*page.count==page.start }">style="color:#333;font-weight:bold;" </c:if>>${status.index+1}</a>
            		</li>
            </c:forEach>
            <li <c:if test="${!page.hasNext }">class="disabled"</c:if>>
              <a href="?start=${page.start+page.count }${page.params }" aria-label="Next">
                <span aria-hidden="true">›</span>
              </a>
            </li>
            <li <c:if test="${!page.hasNext }">class="disabled"</c:if>>
              <a href="?start=${page.lastPageStart }${page.params }" aria-label="Next">
                <span aria-hidden="true">»</span>
              </a>
            </li>
          </ul>
          <script type="text/javascript">
              $(function(){
            	  $("ul.pagination li.disabled a").click(function(){
            		 return false;//当li标签已经有disabled属性时则使得其子元素a也无法点击 
            	  });
              });
          </script>
        </nav>
