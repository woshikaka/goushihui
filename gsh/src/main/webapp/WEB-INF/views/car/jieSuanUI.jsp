<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<title>购物车</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/shop.css" />
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
	<script src="/gsh/resources/jquery/jquery.min.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<section class="rect_wrap">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
			<a href="${pageContext.request.contextPath}/homePage"><img src="${pageContext.request.contextPath}/resources/images/logo.png" style="margin-top:-35px;"/></a>
			<div class="search_wrap">
				<div class="search_bd">
					<div class="search_box">
						<div class="search_icon">
							<i class="icon-search"></i>
						</div>
						<form id="searchForm" action="${pageContext.request.contextPath}/search" method="post" onsubmit="return searchCheck()">
							<input id="keywordInput" type="text" name="keyword" value="${dto.keyword}" class="search_input">
							<button type="submit" class="search_btn right">搜索</button>
							<c:if test="${dto.productTypeId != null}">
								<input type="hidden" name="productTypeId" value="${dto.productTypeId}">
								<input type="hidden" name="productTypeName" value="${dto.productTypeName}">
							</c:if>
							<c:if test="${dto.secTypeId != null}">
								<input type="hidden" name="secTypeId" value="${dto.secTypeId}">
								<input type="hidden" name="secTypeName" value="${dto.secTypeName}">
							</c:if>
							<c:if test="${dto.thirdTypeId != null}">
								<input type="hidden" name="thirdTypeId" value="${dto.thirdTypeId}">
								<input type="hidden" name="thirdTypeName" value="${dto.thirdTypeName}">
							</c:if>
							<c:if test="${dto.salesHigh2Low != null}">
								<input type="hidden" name="salesHigh2Low" value="true">
							</c:if>
							<c:if test="${dto.priceLow2High != null}">
								<input type="hidden" name="priceLow2High" value="true">
							</c:if>
							<c:if test="${dto.defaultSort != null}">
								<input type="hidden" name="defaultSort" value="true">
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
    <section class="place_order">
        <div class="container" ng-app="jieSuanApp" ng-controller="jieSuanCtrl">
            <div class="address_warp box">
                <h3>选择收货地址<a href="${pageContext.request.contextPath}/m/address" target="_blank">我的地址>></a></h3>
                <div class="address_list">
                    <ul>
                        <li ng-repeat="item in addressList">
                            <div class="ads_box">
                                <label for="ads-1">
                                    <input type="radio" name="address" ng-click="switchAddress(item)" ng-model="$parent.selectedAddressId" value="{{item.id}}" ng-checked="item.isDefaut">
                                    <span class="user_ads" ng-bind="item.detailed"></span>
                                    <span class="name">（{{item.contact}}&nbsp;收）</span>
                                    <span class="tel" ng-bind="item.mobile"></span>
                                    <span ng-show="item.isDefaut" style="color:red">默认地址</span>
                                </label>
                            </div>
                        </li>
                    </ul>
                    <!-- <a href="javascript:;" id="add-new-ads">添加收货地址</a> -->
                </div>
            </div>
            <div class="confirm_warp box">
                <h3>订单信息确认</h3>
                <div id="confirm-pro-list" class="pro_table">
                    <table>
                        <thead>
                            <tr>
                                <th width="700px">商品</th>
                                <th width="120px">单价</th>
                                <th width="120px">数量</th>
                                <th width="200px">小计</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="carProduct in carProductList">
                                <td>
                                    <div class="prp_detail">
                                        <div class="img_box">
                                            <img src="{{carProduct.image}}">
                                        </div>
                                        <p>{{carProduct.name}}</p>
                                    </div>
                                </td>
                                <td><strong>{{carProduct.price}}</strong></td>
                                <td>{{carProduct.subCnt}}</td>
                                <td>
                                    <strong class="red">￥{{carProduct.subtotal}}</strong>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="confirm_ads_warp">
						收货地址：<span>{{receiveInfo}}</span>
                    </div>
                </div>
            </div>
            <div class="payment_warp box clear">
                <%-- <div class="method">
                    <label for="pay-wechat"><input type="radio" id="pay-wechat" name="payment" checked><img src="${pageContext.request.contextPath}/resources/images/cart/wechat.png"></label>
                </div> --%>
                <div class="method">
                    <label for="pay-alipay"><input type="radio" id="pay-alipay" checked name="payment"><img src="${pageContext.request.contextPath}/resources/images/cart/alipay.png"></label>
                </div>
                
                <button class="right" ng-click="submitAlipay()">提交订单</button>
                <span class="right">应付总额：<strong class="red">￥{{sumPayable}}</strong></span>
            </div>
        </div>
    </section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
</body>
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
	var app = angular.module('jieSuanApp', []);
	app.controller('jieSuanCtrl', function($scope, $http,$location,$window) {
		$scope.addressList=[];
		$scope.carProductList=[];
		$scope.sumPayable=0;
		$scope.receiveInfo = "";
		$scope.selectedAddressId = "";
		
		//切换地址
		$scope.switchAddress = function(item){
			$scope.receiveInfo = item.detailed+"  "+item.contact+"  "+item.mobile;
		}
		
		$http.get("${pageContext.request.contextPath}/m/addressList").success(function (response) {
			$scope.addressList = response.data;
			
			angular.forEach($scope.addressList,function(item, index){
				if(item.isDefaut){
					$scope.receiveInfo = item.detailed+"  "+item.contact+"  "+item.mobile;
					$scope.selectedAddressId = item.id;
				}
			})
		});
		
		$http.get("${pageContext.request.contextPath}/c/getTempSaveSelected").success(function (response) {
			$scope.carProductList = response.data;
			angular.forEach($scope.carProductList,function(item, index){
				$scope.sumPayable = (parseFloat($scope.sumPayable)+parseFloat(item.subtotal)).toFixed(2);
			})
		});
		
		$scope.submitAlipay = function(){
			var orderRequestDTO = {};
			orderRequestDTO.addressId = $scope.selectedAddressId;
			orderRequestDTO.orderInfos = $scope.carProductList;
			/* $window.open("${pageContext.request.contextPath}/p/sar", '_blank'); */
			$http.post("${pageContext.request.contextPath}/p/addOrder",angular.toJson(orderRequestDTO)).success(function (response) {
				
			});
		}
	});
</script>
</html>
    