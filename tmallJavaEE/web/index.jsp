<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
			$(".itemShowEnd").click(function() {
				$("html,body").animate({scrollTop:0}, 500);
			}); 
			$(".headbar a").mouseenter(function(){//猫耳朵效果
				var position = $(".headbar a").index(this);
				var length = 200;
				for(var i=0;i<position;i++){
					length+=$(".headbar a").eq(i).width()+40;	
				}
				//alert("position:"+position+" length:"+length+" width:"+$(".headbar a").eq(position).outerWidth(true));
				length=length+$(".headbar a").eq(position).outerWidth(true)/2-14.5;
				$(".catEar img").css("left",length);
				$(".catEar img").fadeIn(500);
			});
			$(".headbar a").mouseleave(function(){
				$(".catEar img").hide();
			});
			
			$(".shopItemList a").mouseenter(function(){//鼠标进入标签则显示相应shopItemsList
				$(".shopItemList a").children("li").css("background","#e2e2e3");
				var cid = $(this).attr("cid");
				var length = $(".shopItemsList").length;
				$(".shopItemsList").hide();
				$(this).children("li").css("background","#fff");
				for(var i=0;i<length;i++){
					if(cid == $(".shopItemsList").eq(i).attr("cid")){
						$(".shopItemsList").eq(i).show();
						break;
					}
				}
			});
			
			$(".shopItemList a").mouseleave(function(){//鼠标离开标签则隐藏相应shopItemsList
				$(this).children("li").css("background","#e2e2e3");
				$(".shopItemsList").hide();	
			});
			
			$(".shopItemsList").mouseenter(function(){//鼠标进入相应shopItemsList后则不隐藏该div
				var cid = $(this).attr("cid");
				
				for(var i=0;i<$(".shopItemList a").length;i++){
					$(".shopItemList a").eq(i).children("li").css("background","#e2e2e3");
					if($(".shopItemList a").eq(i).attr("cid")==cid) $(".shopItemList a").eq(i).children("li").css("background","#fff");
				}
				$(this).show();
			});
			
			
		});
	</script>
	<link href="css/fore/index.css" rel="stylesheet">
</head>
<body>   

	<%@include file="include/header.jsp"%>
	<%@include file="include/search.jsp"%>
    
    <div class="homepageDiv">
        <div class="catEar">
            <img height="15px" src="img/site/catear.png">
        </div>
    	<div class="headbarDiv">
            <div class="headbar">
                <span class="category"><span class="glyphicon glyphicon-list"></span>商品分类</span>
                <a href="#nowhere"><img height="30px" src="img/site/chaoshi.png"></a>
                <a href="#nowhere"><img height="30px" src="img/site/guoji.png"></a>
                
                <c:forEach items="${categorys }" var="category" varStatus="st">
	                <c:if test="${st.count<=12&&st.count%2==0 }">
	                	<a href="forecategory?cid=${category.id }"><span>${category.name }</span></a>
	                </c:if>
                </c:forEach>
            </div><!--headbar-->
        </div>
        
    	<div class="focusimgDiv">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="3000">
              <!-- Indicators -->
              <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
              </ol>
             
              <!-- Wrapper for slides -->
              <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="img/site/1.jpg" >
                </div>
                <div class="item">
                    <img src="img/site/2.jpg" >
                </div>
                <div class="item">
                    <img src="img/site/3.jpg" >
                </div>
             
                <div class="item">
                    <img src="img/site/4.jpg" >
                </div>
              </div><!-- carousel-inner -->
             
            </div><!--focusimg-->
        </div><!-- focusimgDiv -->
        
        <div class="shopItemListDiv">
            <div class="shopItemList">
                <ul>
                	<c:forEach items="${categorys }" var="category" varStatus="st">
		                <c:if test="${st.count<=15 }">
		                	<a href="forecategory?cid=${category.id }" cid="${st.count }">
		                		<li><span class="glyphicon glyphicon-link"></span>${category.name }</li>
		                	</a>
		                </c:if>
	                </c:forEach>
                </ul>
            </div><!--shopItemList-->
            
            <c:forEach items="${categorys }" var="category" varStatus="st">
                <c:if test="${st.count<=15 }">
                	<div class="shopItemsList" cid="${st.count }">
                		<c:forEach items="${category.productsByRow }" var="products" varStatus="st1">
                			<div class="itemStyle">
                				<c:forEach items="${products }" var="product" varStatus="st2">
                					<c:forEach items="${fn:split(product.subTitle,' ') }" var="subTitle" varStatus="st3">
                						<c:if test="${st3.count==1 }">
                							<a href="foresearchSub?search=${subTitle }">${subTitle }</a>
                						</c:if>
                					</c:forEach>
                				</c:forEach>
                			</div><!--itemStyle-->
	                	</c:forEach>
                	</div><!--shopItemsList-->
                </c:if>
            </c:forEach>
            
        </div><!--shopItemListDiv-->
        
    </div><!--homepageDiv-->
    
    <div class="itemShowDiv">
    	<c:forEach items="${categorys }" var="category" varStatus="st">
    		<div class="itemShow">
    			<div class="itemShowTitle">
	            	<span></span><b>${category.name }</b>
	            </div><!--itemShowTitle-->
	            <c:forEach items="${category.products }" var="product" varStatus="st1">
	            	<c:if test="${st1.count<=5 }">
	            		<a href="foreproduct?pid=${product.id }">
		            		<div class="itemShowContent">
		            			<div class="itemImg"><img src="img/product/productSingleMiddle/${product.firstProductImage }.jpg"></div>
				                <div class="itemDetail">[热销] ${fn:substring(product.name, 0, 20)}...</div>
				                <div class="itemPrice">
				                	<fmt:formatNumber type="number" value="${product.discountPrice}" minFractionDigits="2"/>
				                </div>
		            		</div><!--itemShowContent--><!--一件商品-->
	            		</a>
	            	</c:if>
	            </c:forEach>
        	</div><!--itemShow--><!--一种商品栏-->
    	</c:forEach>
    	
        
        <div class="itemShowEnd">
        	<a href="#self"><img width="82px" height="53px" src="img/site/end.png"></a>
        </div><!--itemShowEnd-->
    </div><!-- itemShowDiv -->
    
<%@include file="include/footer.jsp"%> 