<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body ng-app="modifyApp" ng-controller="modifyCtrl" style="margin-top: 50px">
	<form class="form-horizontal">
				<input type="hidden" name="isForm" value="true"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" ng-model="productInfo.name" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品类型<i style="color: red">*</i></label>
					<div class="col-sm-2">
						<select class="form-control" name="firstTypeId" id="firstTypeChosen">
								<option value="">一级分类</option>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="secTypeId" id="secTypeChosen">
								<option value="">二级分类</option>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="thirdTypeId" id="thirdTypeChosen">
								<option value="">三级分类</option>
						</select>
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="col-sm-2 control-label">产品图片<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="file" class="form-control" name="file"><i style="color:red">产品图片建议上传400*400的图片</i>
					</div>
				</div> -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">销售价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" ng-model="productInfo.price" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">超市价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" ng-model="productInfo.marketPrice"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">库存<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" ng-model="productInfo.stockCount"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否上架<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<label class="radio-inline"> 
							<input type="radio" ng-model="productInfo.isShangJia" value="1"> 上架
						</label> 
						<label class="radio-inline"> 
							<input type="radio" ng-model="productInfo.isShangJia" value="0"> 下架
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品描述</label>
					<div class="col-sm-9">
						<textarea class="layui-textarea layui-hide" name="content" id="LAY_demo_editor"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-default" ng-click="commitModify()">确认修改</button>
					</div>
				</div>
			</form>
</body>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script type="text/javascript">
var app = angular.module('modifyApp', []);
app.controller('modifyCtrl', function($scope, $http,$location,$window) {
	//var form = layui.form();
	var layer = layui.layer;
	var layedit = layui.layedit;
	
	//创建一个编辑器
	var editIndex = layedit.build('LAY_demo_editor');
	
	var productId = getParameterByName('productId',$location.absUrl());	
	$http.get("${pageContext.request.contextPath}/a/product/getProductInfoById/"+productId,null).success(function(response) {
		$scope.productInfo = response.data;
    });
	
	$scope.commitModify = function(){
		console.log($scope.$parent);
		alert($scope.$parent.modifyTime);
		//parent.modifyTimeTest = 100;
	}
});



function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
</script>
</html>
/* $scope.modifyModal = layer.open({
			  offset: 't',
			  type: 1,
			  title:'修改产品',
			  area: ['1000px', '800px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: angular.element('#modifyModel'),
			  cancel:function(){
				  $scope.isModifyModalShow = false;
				  alert($scope.isModifyModalShow);
			  }
			}); */