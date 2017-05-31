<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>购食汇</title>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body ng-app="productDetailApp" ng-controller="productDetailCtrl">
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
    <section class="crumb_warp">
        <div class="container">
            <%-- <a href="${pageContext.request.contextPath}/navShop?navTypeId=${product.firstType.id}">${product.firstType.name}</a>><a href="${pageContext.request.contextPath}/navShop?navTypeId=${product.firstType.id}&navSecTypeId=${product.secType.id}&navSecTypeName=${product.secType.name}">${product.secType.name}</a>><a href="${pageContext.request.contextPath}/navShop?navTypeId=${product.firstType.id}&navSecTypeId=${product.secType.id}&navSecTypeName=${product.secType.name}&navThirdTypeId=${product.thirdType.id}&navThirdTypeName=${product.thirdType.name}">${product.thirdType.name}</a> --%>
        </div>
    </section>
    <section class="shop_detail_warp">
        <div class="container clear">
            <div class="detail_warp left">
                <div class="shop_info clear">
                    <div class="img_box left"><img ng-src="{{productDetail.image}}" style="width: 395px"></div>
                    <div class="info_warp left">
                        <h2 ng-bind="productDetail.name"></h2>
                        <table>
                            <tbody>
                                <tr><td>超市价：</td><td><i class="line_through" ng-bind="productDetail.marketPrice">￥</i></td></tr>
                                <tr><td>购食汇价格：</td><td><span class="orange" ng-bind="productDetail.price">￥</span><span class="sales">（立省￥{{productDetail.economy}}）</span></td></tr>
                                <tr><td>库存：</td><td>剩余<i class="red" ng-bind="productDetail.stockCount"></i>件</td></tr>
                                <tr><td>销量</td><td>已售<i class="red" ng-bind="productDetail.sellCount"></i>件</td></tr>
                                <!-- <tr><td>规格：</td><td>1100g</td></tr>
                                <tr><td>起订量：</td><td>1盒（不可退）</td></tr>
                                <tr><td>供应商：</td><td>福州小米百货</td></tr> -->
                                <!-- <tr><td>数量：</td><td><div class="buy_num"><button id="minus-btn"">-</button><input type="text" value="1" id="buy-num"><button id="add-btn"">+</button></div></td></tr> -->
                                <!-- <tr><td>数量：</td><td><div class="buy_num_warp"><button id="minus-btn" class="minus-btn">-</button><input type="text" value="1" class="buy-num" id="buy-num"><button id="add-btn" class="add-btn">+</button></div></td></tr> -->
                            </tbody>
                        </table>
                        <div class="btn_box">
                            <a href="javascript:;" id="add-cart" class="btn" ng-show="productDetail.isShangJia"><img src="${pageContext.request.contextPath}/resources/images/detail/shopping_cart.png">加入购物车</a>
                            <button class="layui-btn layui-btn-disabled" ng-show="!productDetail.isShangJia">已下架</button>
                            <form id="addCartForm" action="${pageContext.request.contextPath}/c/putCat" method="post">
                            	<input type="hidden" name="productId" ng-value="productDetail.id"/>
                            </form> 
                        </div>
                    </div>
                </div>
                <div class="shop_descrip">
                    <div class="title"><span class="btn">商品描述</span></div>
                    <div class="descrip" ng-bind-html="htmlDesc">
                        <%-- <p>配料：寄哦寄哦i的减肥if奖</p>
                        <p>保质期：寄哦寄哦i的减肥if奖</p>
                        <img src="${pageContext.request.contextPath}/resources/images/detail/detial_img.png"> --%>
                    </div>
                </div>
            </div>
            <div class="recommend_warp page_box right">
                <h3>看了又看</h3>
                <div class="page_list">
                    <ul class="clear">
                    	<c:forEach items="${viewLog}" var="bean">
                        <li>
                            <div class="goods_img"><a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a></div>
                            <p class="goods_name">${bean.name}</p>
                            <div class="goods_price">
                            <shiro:notAuthenticated>
								<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
							</shiro:notAuthenticated>
							<shiro:authenticated>
								<span class="new_price">${bean.price}元</span>
								<span class="old_price">${bean.marketPrice}元</span>
							</shiro:authenticated>
                            </div>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </section>
	<section class="shop_advantage">
		<ul class="clear">
			<li><img src="${pageContext.request.contextPath}/resources/images/index/quality_goods.png">优质商品</li>
			<li><img src="${pageContext.request.contextPath}/resources/images/index/faster.png">极速配送</li>
			<li><img src="${pageContext.request.contextPath}/resources/images/index/sale.png">省钱省心</li>
		</ul>
	</section>
<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
</body>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<script>
	var app = angular.module('productDetailApp', []);
	app.controller('productDetailCtrl', function($scope, $http,$location,$window,$sce) {
		var layer = layui.layer;
		
		var absUrl = $location.absUrl();
		var productId = absUrl.substring(absUrl.lastIndexOf("/")+1);
		$http.get("${pageContext.request.contextPath}/product/getDetail/"+productId,null).success(function(response) {
			$scope.productDetail = response.data;
			
			$scope.htmlDesc = $sce.trustAsHtml($scope.productDetail.htmlDesc);
	    });
	});


	shop.opptBuyNum();
    $(function(){
        $("#add-cart").on('click',function(){
	        /* var nowBuyNum = $("#buy-num").val();
			if(!nowBuyNum.trim()){
				swal("数量格式不正确！请重填！");
				return false;
			}
			if(nowBuyNum.match(/^\d+$/)){ //integer
				// do noting
			}else{
				swal("数量格式不正确！请重填！");
				return false;
			} */
			
			$("#addCartForm input[name='productCnt']").val(1);
			$("#addCartForm").submit();
			return true;
        })
            
        
    })
</script>
</html>