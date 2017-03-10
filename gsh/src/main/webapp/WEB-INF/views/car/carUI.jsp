<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ taglib prefix="gsh" uri="/WEB-INF/tlds/mul.tld"%>
<!DOCTYPE html>
<html>

<head>
	<title>购物车</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/shop.css" />
</head>
<style>
.jiesuan_btn{
	height: 74px;
    background-color: #f04848;
    padding: 0 5%;
    font-size: 22px;
    color: #fff;
    margin-left: 4%;
}
</style>

<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
    <section class="place_order">
        <div class="container" ng-app="carApp" ng-controller="carCtrl">
            <div class="pro_info_warp">
                <div class="box">
                    <h3>购物车商品信息</h3>
                    <div id="pro-list" class="pro_table">
                    	<div style="text-align: center;" ng-show="carProducts.length==0">
                    		<span><img src="${pageContext.request.contextPath}/resources/images/car_empty.png">亲，您的购物车啥都没有~</span>
                    	</div>
                    	
                        <table ng-show="carProducts.length!=0">
                            <thead>
                                <tr>
                                    <th width="6%"><input type="checkbox" ng-model="checkedAll" ng-click="checkAll()" class="check_all"></th>
                                    <th width="20%">商品</th>
                                    <th width="9%">单价</th>
                                    <th width="15%">数量</th>
                                    <th width="10%">小计</th>
                                    <th  width="10%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
	                                <tr ng-repeat="carProduct in carProducts">
	                                    <td><input type="checkbox" ng-checked="carProduct.checked" ng-model="carProduct.checked" ng-click="single(carProduct)" class="check_one"></td>
	                                    <td>
	                                        <div class="prp_detail">
	                                            <div class="img_box">
	                                                <a href="${pageContext.request.contextPath}/product/detail/{{carProduct.productId}}"><img src="{{carProduct.image}}"></a>
	                                            </div>
	                                            <p><a href="${pageContext.request.contextPath}/product/detail/{{carProduct.productId}}">{{carProduct.name}}</a></p>
	                                        </div>
	                                    </td>
	                                    <td><strong>{{carProduct.price}}</strong></td>
	                                    <td>
	                                        <div class="buy_num_warp"><button class="minus-btn" ng-click="minus(carProduct)">-</button><input type="text" ng-model="carProduct.subCnt" ng-blur="checkSubCnt(carProduct)" class="buy-num"><button class="add-btn" ng-click="add(carProduct)">+</button></div>
	                                    </td>
	                                    <td>
	                                        <strong class="red">￥</strong><strong class="red">{{carProduct.subtotal}}</strong>
	                                    </td>
	                                    <td><a href="${pageContext.request.contextPath}/c/deleteCarProduct/{{carProduct.id}}" onclick="return confirm('确定要删除吗？')" class="del_btn">删除</a></td>
	                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="other" ng-show="carProducts.length!=0" style="padding: 0 0 0 3%;">
                    <label for="has-check">已选（<i class="check_num">{{checkedCnt}}</i>）</label>
                    <!-- <a href="javascript:;">批量删除</a> -->
                    <button class="jiesuan_btn right" ng-click="quJieSuan()">去结算</button>
                    <span class="right">应付总额：<strong class="red">￥</strong><strong class="red" ng-bind="sumPayable"></strong></span>
                </div>
            </div>
        </div>
    </section>
</body>
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
	var app = angular.module('carApp', []);
	app.controller('carCtrl', function($scope, $http,$location,$window) {
		$scope.carProducts=[];
		$scope.checkedAll = false;
		$scope.checkedCnt = 0;
		$scope.sumPayable = 0;
		
		$http.get("${pageContext.request.contextPath}/c/carProduct")
		  .success(function (response) {
			  $scope.carProducts = response.data;
		  });
		
		//全选
		$scope.checkAll = function(){
			angular.forEach($scope.carProducts,function(item, index){
				if($scope.checkedAll){
					item.checked=true;
				}else{
					item.checked=false;
				}
			})
			if($scope.checkedAll){
				$scope.checkedCnt = $scope.carProducts.length;
			}else{
				$scope.checkedCnt = 0;
			}
			countSumPayable();
		}
		
		//统计应付金额
		function countSumPayable(){
			$scope.sumPayable = 0;
			angular.forEach($scope.carProducts,function(item, index){
				if(item.checked){
					$scope.sumPayable = (parseFloat($scope.sumPayable)+parseFloat(item.subtotal)).toFixed(2);
				}
			})
		}
		
		$scope.single = function(carProduct){
			if(carProduct.checked){
				$scope.checkedCnt = $scope.checkedCnt+1;
			}else{
				$scope.checkedCnt = $scope.checkedCnt-1;
			}
			countSumPayable();
		}
		
		$scope.minus = function(carProduct){
			if(carProduct.subCnt<=1){
				layer.msg('亲，商品数量最小必须是1', {icon: 5});  
				return;
			}
			carProduct.subCnt = carProduct.subCnt-1;
			carProduct.subtotal = (parseFloat(carProduct.subCnt)*parseFloat(carProduct.price)).toFixed(2);
			countSumPayable();
		}
		$scope.add = function(carProduct){
			if(carProduct.subCnt>=100){
				layer.msg('亲，商品数量最多只能选择100', {icon: 5});  
				return;
			}
			carProduct.subCnt = carProduct.subCnt+1;
			carProduct.subtotal = (parseFloat(carProduct.subCnt)*parseFloat(carProduct.price)).toFixed(2);
			countSumPayable();
		}
		
		var regExp100 = /^(100||[1-9][0-9]{0,1})$/;
		$scope.checkSubCnt = function(carProduct){
			if(carProduct.subCnt>100){
				layer.msg('亲，商品数量最多只能选择100', {icon: 5});  
				carProduct.subCnt = 100;
			}
			if(carProduct.subCnt<1){
				layer.msg('亲，商品数量最小必须是1', {icon: 5}); 
				carProduct.subCnt = 1;
			}
			if(!regExp100.test(carProduct.subCnt)){
				layer.msg('请输入1~100之间的整数', {icon: 5});
				carProduct.subCnt = 1;
			}
			carProduct.subtotal = (parseFloat(carProduct.subCnt)*parseFloat(carProduct.price)).toFixed(2);
			countSumPayable();
		}		
		
		$scope.quJieSuan = function(){
			if($scope.checkedCnt<=0){
				layer.msg('至少要选择一个产品，才能去结算', {icon: 5});
				return false;
			}
			
			var selectedCarProducts = new Array();
			angular.forEach($scope.carProducts,function(item, index){
				if(item.checked){
					selectedCarProducts.push(item);
				}
			})
			
			$http.post("${pageContext.request.contextPath}/c/tempSaveSelected",angular.toJson(selectedCarProducts)).success(function(data) {
				$window.location = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/gsh/c/jieSuanUI";
            });
			
		}
	});
</script>
</html>
    