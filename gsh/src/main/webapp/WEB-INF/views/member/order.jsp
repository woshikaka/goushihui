<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>
<head>
    <title></title>
    <!-- <link rel="icon" href="favicon.ico"> -->
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
    <style type="text/css">
    	.num_desc{
    		margin-top: 10px;
    		margin-bottom: 4px
    	}
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
    <section class="center_header">
        <div class="container">
            <!-- <div class="logo_box"></div> -->
            <ul class="center_header_nav clear">
                <li><a href="${pageContext.request.contextPath}/homePage">首页</a></li>
                <!-- <li><a href="javascript:;">账户设置<i class="icon-chevron-down"></i></a></li>
                <li><a href="javascript:;">消息</a></li> -->
            </ul>
            <!-- <div class="search_box right">
                <form>
                    <input type="text" name="">
                    <button class="">搜索</button>
                </form>
            </div> -->
        </div>
    </section>
    <section class="center_con">
        <div class="container clear">
             <div class="center_menu left">
                <h5>我的购食汇</h5>
                <ul>
                    <li class="select"><a href="javascript:;" style="color:#f04848;">我的订单</a></li>
                    <li class="select"><a href="${pageContext.request.contextPath}/m/address">我的地址</a></li>
                    <li class="select"><a href="${pageContext.request.contextPath}/m/password">修改密码</a></li>
                </ul>
                <!-- <h5>我的级别</h5>
                <ul>
                    <li class="select"><a href="javascript:;">我的积分</a></li>
                    <li class="select"><a href="javascript:;">我的礼品</a></li>
                    <li class="select"><a href="javascript:;">积分规则</a></li>
                </ul> -->
            </div>
            <div class="center_main left" ng-app="orderApp" ng-controller="orderCtrl">
                <div class="my_order center_border">
                    <h2 class="title">我的订单<!-- <a href="javascript:;" class="right">查看全部订单>></a> --></h2>
                    <table class="order_list">
                        <thead>
                            <tr>
                                <th width="16%">订单编号</th>
                                <th width="37%">商品名称</th>
                                <th width="5%">个数</th>
                                <th width="8%">总数</th>
                                <!-- <th width="13%">市场价格</th> -->
                                <th width="13%">订单金额</th>
                                <th width="13%">交易状态</th>
                                <!-- <th width="13%">操作</th> -->
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="order in orders">
                                <td ng-bind="order.outTradeNo"></td>
                                <td>
                                    <p class="goods_name elip" ng-repeat="name in order.names">{{name}}</p>
                                </td>
                                <td>
                                    <p class="goods_name elip" style="width:100px" ng-repeat="quantity in order.quantitys track by $index">{{quantity}}</p>
                                </td>
                                <td ng-bind="order.toalQuantity"></td>
                                <td>
                                    <span class="red">￥{{order.totalPrice}}</span>
                                </td>
                                <td class="order_staus">
                                    <p ng-bind="order.status"></p>
                                </td>
                                <!-- <td>
                                    <a class="" href="javascript:;">订单详情</a>
                                </td> -->
                            </tr>
                        </tbody>
                    </table>
                    <div id="paging"></div>
                </div>
            </div>
        </div>
    </section>
	<%-- <%@ include file="/WEB-INF/views/admin/public/foot.jsp"%> --%>    
</body>
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
var app = angular.module('orderApp', []);
app.controller('orderCtrl', function($scope, $http,$location,$window) {
	var laypage = layui.laypage;
	var layer = layui.layer;
	
	$scope.orders=[];
	$scope.pageParam={"currPageNo":1};
	$scope.totalPages=0;
	
	$http.post("${pageContext.request.contextPath}/m/orderPage",angular.toJson($scope.pageParam)).success(function(response) {
		$scope.orders = response.data.items;
		$scope.totalPages = response.data.totalPages;
			
		laypage({
		    cont: 'paging',
		    pages: $scope.totalPages,
		    skin: '#1E9FFF',
			skip: false,
			jump: function(obj,first){
				if(!first){
					$scope.pageParam.currPageNo=obj.curr;
					pageRequest();
				}
			}
		}); 
    });
	
	function pageRequest(){
		$http.post("${pageContext.request.contextPath}/m/orderPage",angular.toJson($scope.pageParam)).success(function(response) {
			$scope.orders = response.data.items;
			$scope.totalPages = response.data.totalPages;
	    });
	}
	


});
</script>
</html>


