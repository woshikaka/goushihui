<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
<style type="text/css">
	#modifyModal .modal-dialog{
    	overflow-y: initial !important
	}
	#modifyModal .modal-body{
	    height: 500px;
	    overflow-y: auto;
	}
</style>
</head>
<body ng-app="orderListApp" ng-controller="orderListCtrl">
	<div id="wrapper">
	<jsp:include page="/WEB-INF/views/admin/public/nav.jsp"/>
		<div id="page-wrapper">
			<%-- <%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%> --%>
			<div class="row">
				<div class="col-md-12">
					<form id="searchForm" class="form-inline">
						<div class="form-group">
							<input class="form-control" type="text" ng-model="pageParam.outTradeNo" placeholder="订单号"/>
							<label style="margin-left:20px">订单状态</label>
							<select class="form-control" ng-model="pageParam.status">
								<option value="">请选择</option>
								<option value="WAIT_PAY">待付款</option>
								<option value="PAY_SUCCESS_WAIT_SEND">付款成功等待发货</option>
								<option value="PAY_SUCCESS_ALREADY_SEND">付款成功已经发货</option>
								<option value="END">交易结束</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary glyphicon glyphicon-search" id="search" ng-click="pageRequest()"></button>
					</form>
				</div>
			</div>
			<div class="row">
				<!-- <div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" onclick="batchShangJia()">批量上架</button>
					<button type="button" class="btn btn-default" onclick="batchXiaJia()">批量下架</button>
				</div> -->
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="1%"></th>
								<th>订单号</th>
								<th>买家账号</th>
								<th>商品名称</th>
								<th>购买总数</th>
								<th>总价</th>
								<th>收货人</th>
								<th>收货人电话</th>
								<th>收货人地址</th>
								<th>订单状态</th>
								<th>物流信息</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
								<tr ng-repeat="order in orders">
									<td><input type="checkbox" ng-value="order.id"></td>
									<td ng-bind="order.outTradeNo"></td>
									<td ng-bind="order.userName"></td>
									<td ng-bind="order.subject"></td>
									<td ng-bind="order.toalQuantity"></td>
									<td ng-bind="order.totalPrice"></td>
									<td ng-bind="order.contact"></td>
									<td ng-bind="order.mobile"></td>
									<td ng-bind="order.receiveAddress"></td>
									<td ng-bind="order.status"></td>
									<td>{{order.express==null?'无':order.express}}</td>
									<td>
										<button ng-show="" class="layui-btn layui-btn-primary layui-btn-mini" ng-click="sendGoods(order)">发货</button>
									</td>
								</tr> 
						</tbody>
					</table>
					<%-- <%@ include file="/WEB-INF/views/admin/public/paging.jsp" %> --%>
					<div id="paging"></div>
					<div>共{{totalPages}}页</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
var app = angular.module('orderListApp', []);
app.controller('orderListCtrl', function($scope, $http,$location,$window) {
	var laypage = layui.laypage;
	var layer = layui.layer;
	
	$scope.pageParam={"currPageNo":1};
	$scope.totalPages=0;
	
	page();
	
	$scope.pageRequest = function(){
		$scope.pageParam.currPageNo=1;
		page();
	}
	
	function page(){
		$http.post("${pageContext.request.contextPath}/a/order/page",angular.toJson($scope.pageParam)).success(function(response) {
			$scope.orders = response.data.orders;
			$scope.totalPages = response.data.totalPages;
			
			laypage({
			    cont: 'paging',
			    curr: $scope.pageParam.currPageNo,
			    pages: $scope.totalPages,
			    skin: '#1E9FFF',
				skip: false,
				jump: function(obj,first){
					if(!first){
						$scope.pageParam.currPageNo=obj.curr;
						page();
					}
				}
			});
	    });
	}
	
	$scope.sendGoods = function(order){
		layer.confirm(order.subject+'<br/>确定已经发货？', {
		  btn: ['是','否']
		}, function(){
			$http.get("${pageContext.request.contextPath}/a/order/sendGoods/"+order.id,null).success(function(response) {
				page();
			  	layer.msg('操作成功', {icon: 1});
		    });
		}, function(){
			
		});
	}
});
</script>
</html>