<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/top.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".orderFormSaying input").focus(function(){//留言框效果
			$(this).hide();
			$(".orderFormSaying textarea").show();	
			$(".orderFormSaying textarea").focus();	
		});
		
		$(".orderFormSaying textarea").blur(function(){//留言框效果
			
			if($(this).val()==""){
				$(this).hide();
				$(".orderFormSaying input").show();	
			}
		});
		
		$(".orderItemNumber").keyup(function(){
			var stock = 66;
			var number = $(this).val();
			if(isNaN(number)){
				$(this).val(1);	
			}else{
				if(number < 1){
					$(this).val(1);
				}else if(number > stock){
					$(this).val(stock);	
				}
			}
			updateOrderItemSum();
			updateOrderFormSum();
		});
		
		function updateOrderItemSum(){//更新每个订单项的总金额
			for(var i = 0;i<$(".orderFormMessageContent").length;i++){
				var price = Number($(".orderFormMessageContent .orderItemPrice span").eq(i).html());
				var number = Number($(".orderFormMessageContent .orderItemNumber").eq(i).val());
				$(".orderFormMessageContent .orderItemSum span").eq(i).html((price*number).toFixed(2));
			}
		}
		
		function updateOrderFormSum(){//更新订单的总金额
			var sum = 0;
			for(var i = 0;i<$(".orderFormMessageContent").length;i++){
				var price = Number($(".orderFormMessageContent .orderItemSum span").eq(i).html());
				sum += price;
			}
			
			$(".orderFormSaying span span").html(sum);
			$(".orderFormSum").html(sum);
		}
		
		updateOrderItemSum();
		updateOrderFormSum();
		
		$(".orderItemNumber").keyup(function(){//当数量改变时通过异步提交到数据库的orderItem
			var index = $(".orderItemNumber").index(this);
			var number = $(".orderItemNumber").eq(index).val();
			var oiid = $(".orderItemID").eq(index).val();
			var page="forechangeOrderItem";
			$.ajax({
				url:page,
				data:{"number":number,"oiid":oiid},
				success:function(result){
					$("#cartNumber").html(result);
				}
			});
		});
		
	});
</script>

<link href="css/fore/purchase.css" rel="stylesheet">
</head>
<body>
   <%@include file="include/header.jsp" %>
    
   <div class="purchaseFlowImg">
        <a href="#"><img class="simpleLogo" width="190px" height="27px" src="img/site/simpleLogo.png" id="simpleLogo"></a>
        
        <img class="purchaseFlow" src="img/site/buyflow.png">
    </div><!-- 顶部进度显示图片 -->
    
    <div class="purchaseOutDiv">
    	<form action="forecreateOrder" method="post">
            <div class="deliveryAddressDiv">
                <div class="deliveryAddressTitle">输入收货地址</div>
                <table>
                	<tr>
                    	<td width="20%">详细地址<span>*</span></td><td><textarea name="address" placeholder="建议您如实填写详细收获地址,例如街道名称,门牌号码,楼层和房间名等信息">${userMessage.address }</textarea></td>
                    </tr>
                    <tr>
                    	<td>邮政编码</td><td><input name="post" placeholder="如果您不清楚邮政编码,请填写00000" value="${userMessage.post }"></td>
                    </tr>
                    <tr>
                    	<td>收货人姓名<span>*</span></td><td><input name="receiver" placeholder="长度不超过25个字符" value="${userMessage.userName }"></td>
                    </tr>
                    <tr>
                    	<td>手机号码<span>*</span></td><td><input name="mobile" placeholder="请输入11位手机号码" value="${userMessage.mobile }"></td>
                    </tr>
                    
                </table>
            </div><!-- deliveryAddressDiv -->
            
            <div class="orderFormMessageDiv">
                <div class="orderFormMessageTitle">确认订单信息</div>
            	<table class="orderFormMessageTable" >
                	<tr class="orderFormMessageTop" align="center">
                    	<td align="left"><img src="img/site/tmallbuy.png" width="14px" height="14px"> 店铺：天猫店铺<span></span></td>
                        <td>单价</td>
                        <td>数量</td>
                        <td>小计</td>
                        <td>配送方式</td>
                    </tr>
                    
                    <c:forEach items="${orderItems }" var="orderItem" varStatus="st">
                    	<tr class="orderFormMessageContent" align="center">
	                    	<td align="left">
	                        	<img class="orderItemImg" src="img/product/productSingleSmall/${orderItem.product.firstProductImage }.jpg">
	                        	<div class="orderItemDetail"><span>${orderItem.product.name }</span><br>
	                        <img width="16px" src="img/site/creditcard.png" title="支持信用卡支付"><img width="16px" src="img/site/promise.png" title="消费者保障服务,承诺如实描述"><img width="16px" src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
	                    		</div>
	                    	</td>
	                        <td><span class="orderItemPrice">￥<span>${orderItem.product.discountPrice }</span></span></td>
	                        <td><input class="orderItemNumber" value="${orderItem.number }"></td>
	                        <td><span class="orderItemSum">￥<span>0</span></span></td>
	                        <td><input type="hidden" name="oiids" class="orderItemID" value="${orderItem.id }">
	                        	<span class="deliveryWay"><input type="radio" checked="checked">普通配送
	                            <select>
	                            	<option>快递 免邮</option>
	                            </select></span>
	                        </td>
	                    </tr>
                    </c:forEach>
                    
                </table>
                
                <div class="orderFormSaying">
                	给卖家留言：
                	<input type="text" placeholder="选填：对本次交易的说明(建议填写已和卖家协商好的...)">
                    <textarea style="display:none;" name="userMessage"></textarea>
                    <span>店铺合计(含运费): ￥<span>0</span></span>
                </div>
                <div class="orderFormSumDiv">
                	<span>实付款：</span>￥<b class="orderFormSum">0</b>
                </div>
                
                <div class="submitOrderForm"><input type="submit" value="提交订单"></div>
            </div><!-- orderFormMessageDiv -->
        </form>
    </div><!-- purchaseOutDiv -->
    
<%@include file="include/footer.jsp"%>