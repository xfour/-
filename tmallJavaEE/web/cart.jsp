<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="include/top.jsp" %>
	<script type="text/javascript">
		$(function(){
			$(".changeNumber a").click(function(){//点击加减符号改变数量
				var index = $(".changeNumber a").index(this);
				if(index%2 == 0){
					var number = Number($(".changeNumber input").eq(index/2).val());
					var stock = Number($(".productStock").eq(index/2).html());
					if(number<=1) alert("数量不能再减少!");
					else $(".changeNumber input").eq(index/2).val(number-1);	
				}else{
					var number = Number($(".changeNumber input").eq((index-1)/2).val());
					var stock = Number($(".productStock").eq((index-1)/2).html());
					if(number>=stock) alert("数量不能再增加!");
					else $(".changeNumber input").eq((index-1)/2).val(number+1);
				}
				updateTotalPrice();
			});
			
			$(".changeNumber input").keyup(function(){//将数量设为1至库存数量之间
				var index = $(".changeNumber input").index(this);
				var number = Number($(".changeNumber input").eq(index).val());
				var stock = Number($(".productStock").eq(index).html());
				if(isNaN(number)){
					$(".changeNumber input").eq(index).val(1);
				}else{
					if(number < 1) $(".changeNumber input").eq(index).val(1); 
					else if(number > stock) $(".changeNumber input").eq(index).val(stock);
				}
				updateTotalPrice();
			});
			
			$(".changeNumber input").keyup(function(){//当数量改变时通过异步提交到数据库的orderItem
				var index = $(".changeNumber input").index(this);
				var number = $(".changeNumber input").eq(index).val();
				var oiid = $(".orderItemID").eq(index).val();
				var page="forechangeOrderItem";
				$.ajax({
					url:page,
					data:{"number":number,"oiid":oiid},
					success:function(result){
					}
				});
			});
			
			function updateTotalPrice(){//更新span.totalPrice的值，只显示小数点后两位
				var totalNumbers = 0;//所有订单项的所有商品的总数量
				for(var i=0;i<$(".totalPrice").length;i++){
					var totalPrice = Number($(".discountPrice span").eq(i).html()) * Number($(".changeNumber input").eq(i).val());
					$(".totalPrice span").eq(i).html(totalPrice.toFixed(2));	
					totalNumbers += Number($(".changeNumber input").eq(i).val());
				}
				$("span.pull-right strong").html(totalNumbers);
				updateCompletePurchase();
			}
			updateTotalPrice();
			
			
			$(".singleSelect").click(function(){//单选按钮点击事件
				var index = $(".singleSelect").index(this);
				if($(this).children("img").attr("selectedImg")==0){
					$(this).children("img").attr("selectedImg","1");
					$(this).parent().parent().parent().css("background","#fff8e1");
					$(this).children("img").attr("src","img/site/cartSelected.png");
				}else{
					$(this).children("img").attr("selectedImg","0");
					$(this).parent().parent().parent().css("background","#ffffff");
					$(this).children("img").attr("src","img/site/cartNotSelected.png");
				}	
				updateCompletePurchase();
			});
			
			function updateCompletePurchase(){//更新结算一栏的相关数据
				var length = $(".singleSelect img").length;
				var state = true;//判断是否全选
				var state1 = false;//判断是否有任何一个订单项被选定
				var totalPrices = 0;//所有订单项的总价格
				var totalNumbers = 0;//所有订单项的所有商品的总数量
				for(var i = 0;i<length;i++){
					if($(".singleSelect img").eq(i).attr("selectedImg") == "0"){
						state = false;
					}else{
						state1 = true;
						totalPrices += Number($(".totalPrice span").eq(i).html());
						totalNumbers += Number($(".changeNumber input").eq(i).val());
					}
				}
				if(state){//还有商品已经全选
					$(".allSelect img").attr("selectedImg","1");
					$(".allSelect img").attr("src","img/site/cartSelected.png");
				}else{
					$(".allSelect img").attr("selectedImg","0");
					$(".allSelect img").attr("src","img/site/cartNotSelected.png");
				}
				$("span.totalPrices span").html(totalPrices.toFixed(2));
				$("span.totalNumbers").html(totalNumbers);
				if(state1){
					$(".completePurchase").css("background","#dd2727");
				}else{
					$(".completePurchase").css("background","#aaa");
				}
			}
			
			$(".allSelect").click(function(){//全选按钮点击事件
				var length = $(".singleSelect img").length;
				var totalPrices = 0;
				if($(".allSelect img").attr("selectedImg") == "0"){//还有商品没选定，触发全选
					$(".allSelect img").attr("src","img/site/cartSelected.png");
					$(".allSelect img").attr("selectedImg","1");
					for(var i = 0;i<length;i++){
						$(".singleSelect img").eq(i).attr("selectedImg","1");
						$(".singleSelect img").parent().parent().parent().parent().css("background","#fff8e1");
						$(".singleSelect img").eq(i).attr("src","img/site/cartSelected.png");
					}
				}else{//所有订单项都被选定，启动全不选
					$(".allSelect img").attr("src","img/site/cartNotSelected.png");
					$(".allSelect img").attr("selectedImg","0");
					for(var i = 0;i<length;i++){
						$(".singleSelect img").eq(i).attr("selectedImg","0");
						$(".singleSelect img").parent().parent().parent().parent().css("background","#ffffff");
						$(".singleSelect img").eq(i).attr("src","img/site/cartNotSelected.png");
					}
				}	
				updateCompletePurchase();
			});
			
			$("#buyBtn").click(function(){//结算按钮的点击事件，将被选定的订单项的id发送到服务器
				var page="forebuy";
				var j=0;
				for(var i=0;i<$(".orderItemID").length;i++){
					
					if($(".singleSelect img").eq(i).attr("selectedImg")=="1"){
						if(j==0){
							page=page+"?oiid="+$(".orderItemID").eq(i).val();
							j=1;
						}
						else page=page+"&oiid="+$(".orderItemID").eq(i).val();
					}
				}
				window.location.href=page;
			});
			
			$(function(){//删除按钮弹出alert
				$("a.deleteLink").click(function(){
					if(confirm("确认要删除")){
						return true;		
					}
					return false;
				});
				
			});
			
		});
	</script>
	
	<link href="css/fore/cart.css" rel="stylesheet">
</head>
<body>
	<%@include file="include/header.jsp" %>
    <%@include file="include/simpleSearch.jsp" %>
    
    <div class="productListDiv">
    	<table>				
        	<thead class="selectedAllTh">
            	<tr>
                	<th><a href="#nowhere" class="allSelect"><img src="img/site/cartNotSelected.png" selectedImg="0" class="checkBoxByMySelf"></a>全选</th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            
            	<c:forEach items="${orderItems }" var="orderItem" varStatus="st">
            		<tr>
	                	<td><div class="selectedAllTd"><input type="hidden" class="orderItemID" value="${orderItem.id }">
	                    	<a class="singleSelect" href="#nowhere"><img src="img/site/cartNotSelected.png" selectedImg="0" class="checkBoxByMySelf"></a><img src="img/product/productSingleMiddle/${orderItem.product.firstProductImage }.jpg" class="goodsImg"></div>
	                    </td>
	                    <td><div class="goodsMessage"><a href="foreproduct?pid=${orderItem.product.id }"><p>${orderItem.product.name }</p></a><img src="img/site/creditcard.png" title="支持信用卡支付" width="16px"><img src="img/site/promise.png" width="16px" title="消费者保障服务,承诺如实描述"><img src="img/site/7day.png" width="16px" title="消费者保障服务,承诺7天退货"></div></td>
	                    <td><div class="originalPrice">￥<span>${orderItem.product.orignalPrice }</span></div><div class="discountPrice">￥<span>${orderItem.product.discountPrice }</span></div></td>
	                    <td><span class="hidden productStock">${orderItem.product.stock }</span><div class="changeNumber"><a href="#nowhere">-</a><input type="text" value="${orderItem.number }"><a href="#nowhere">+</a></div></td>
	                    <td><div class="totalPrice">￥<span>${orderItem.product.stock }</span></div></td>
	                    <td><div class="delete"><a href="foredeleteOrderItem?oiid=${orderItem.id }" class="deleteLink">删除</a></div></td>
	                </tr>
            	</c:forEach>
            	
            </tbody>
        </table>
        <div class="completePurchaseDiv">
        	<span class="spanLeft"><a href="#nowhere" class="allSelect"><img src="img/site/cartNotSelected.png" selectedImg="0" class="checkBoxByMySelf"></a>全选</span>
            <span class="spanRight">已选商品<span class="totalNumbers">0</span>件&nbsp;合计(不含运费): <span class="totalPrices">￥<span>0.00</span></span><a href="#nowhere" id="buyBtn"><span class="completePurchase">结算</span></a></span>
        </div><!-- completePurchaseDiv -->
    </div><!--productListDiv-->

<%@include file="include/footer.jsp"%>