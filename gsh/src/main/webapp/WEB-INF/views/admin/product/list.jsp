<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<style type="text/css">
	#modifyModal .modal-dialog{
    	overflow-y: initial !important
	}
	#modifyModal .modal-body{
	    height: 500px;
	    overflow-y: auto;
	}
	[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
  		display: none !important;
	}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body ng-app="productListApp" ng-controller="productListCtrl" ng-cloak>
	<div id="wrapper">
	<jsp:include page="/WEB-INF/views/admin/public/nav.jsp"/>
		<div id="page-wrapper">
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<div class="row">
				<div class="col-md-12">
					<form id="searchForm" class="form-inline">
						<div class="form-group">
							<input class="form-control" ng-model="pageParam.name" type="text" name="name" placeholder="产品名称"/>
							<label style="margin-left:20px">是否上架</label>
							<select class="form-control" ng-model="pageParam.isShangJia">
								<option value="">请选择</option>
								<option value="true">上架</option>
								<option value="false">下架</option>
							</select>
						</div>
						<div class="form-group">
							<label style="margin-left:20px">产品类型</label>
							<select class="form-control" ng-model="queryFirstType" ng-options="firstType.name for firstType in allProductTypes">
								<option value="">一级分类</option>
							</select>
							<select class="form-control" ng-model="querySecType" ng-options="secType.name for secType in queryFirstType.productSecTypes">
								<option value="">二级分类</option>
							</select>
							<select class="form-control" ng-model="queryThirdType" ng-options="thirdType.name for thirdType in querySecType.thirdTypes">
								<option value="">三级分类</option>
							</select>							
						</div>
						<button class="btn btn-primary glyphicon glyphicon-search" id="search" ng-click="pageRequest()"></button>
					</form>
				</div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" ng-click="shangJiaOrXiajia(1)">批量上架</button>
					<button type="button" class="btn btn-default" ng-click="shangJiaOrXiajia(0)">批量下架</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="1%"></th>
								<th>名称</th>
								<th>图片</th>
								<th>产品类别</th>
								<th>销售价格</th>
								<th>超市价格</th>
								<th>库存</th>
								<th>累计销售量</th>
								<th>是否上架</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="bean in products" ng-cloak>
								<td><input type="checkbox" ng-click="bean.isSelected = !bean.isSelected"></td>
								<td ng-bind="bean.name"></td>
								<td><img ng-src="{{bean.image}}" width="50"></td>
								<td>{{bean.firstTypeName}}<span class="glyphicon glyphicon-chevron-right"></span>{{bean.secTypeName}}<span class="glyphicon glyphicon-chevron-right">{{bean.thirdTypeName}}</td>
								<td ng-bind="bean.price"></td>
								<td ng-bind="bean.marketPrice"></td>
								<td ng-bind="bean.stockCount"></td>
								<td ng-bind="bean.sellCount"></td>
								<td>
									<span ng-show="bean.isShangJia" class="label label-success">上架</span>
									<span ng-show="!bean.isShangJia" class="label label-default">下架</span>
								</td>
								<td><button class="layui-btn layui-btn-primary layui-btn-mini" ng-click="openModifyModal(bean)">修改产品</button></td>
							</tr> 
						</tbody>
					</table>
					<div id="paging"></div>
					<div>共{{totalPages}}页，总记录{{totalElements}}</div>
				</div>
			</div>
		</div>
	</div>
<!-- tabindex="-1" -->
<div class="modal fade" id="modifyProductModel"  role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					修改产品
				</h4>
			</div>
			<div class="modal-body">
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
							<select class="form-control" ng-model="selectedFirstType" ng-options="firstType.name for firstType in allProductTypes">
								<option value="">一级分类</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="form-control" ng-model="selectedSecType" ng-options="secType.name for secType in selectedFirstType.productSecTypes">
								<option value="">二级分类</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="form-control" ng-model="selectedThirdType" ng-options="thirdType.name for thirdType in selectedSecType.thirdTypes">
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
								<input type="radio" ng-model="productInfo.isShangJia" ng-value="true"> 上架
							</label> 
							<label class="radio-inline"> 
								<input type="radio" ng-model="productInfo.isShangJia" ng-value="false"> 下架
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">产品描述</label>
						<div class="col-sm-9">
						<textarea name="description" id="productDescEditor" rows="10" cols="80">{{productInfo.description}}</textarea>
						</div>
						<!-- <div class="col-sm-9">
							<textarea class="layui-textarea layui-hide" name="content" id="productDescEditor">{{productInfo.description}}</textarea>
						</div> -->
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" ng-click="commitModify()">提交更改</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>

<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
<script>
	var modifyTimeTest = 0;
	var app = angular.module('productListApp', []);
	app.controller('productListCtrl', function($scope, $http,$location,$window) {
		var form = layui.form;
		var laypage = layui.laypage;
		var layer = layui.layer;
		var layedit = layui.layedit;
		
		$scope.pageParam={"currPageNo":1};
		$scope.totalPages=0;
		$scope.totalElements=0;
		
		$scope.productInfo = {};
		$scope.allProductTypes = [];
		$scope.selectedFirstType={id:0,name:""};
		$scope.queryFirstType={id:""};
		$scope.querySecType={id:""};
		$scope.queryThirdType={id:""};
		
		page();
		
		$scope.pageRequest = function(){
			$scope.pageParam.currPageNo=1;
			if($scope.queryFirstType){
				$scope.pageParam.firstTypeId = $scope.queryFirstType.id;
			}else{
				$scope.pageParam.firstTypeId = null;
			}
			
			if($scope.querySecType){
				$scope.pageParam.secTypeId = $scope.querySecType.id;
			}else{
				$scope.pageParam.secTypeId = null;
			}
			
			if($scope.queryThirdType){
				$scope.pageParam.thirdTypeId = $scope.queryThirdType.id;
			}else{
				$scope.pageParam.thirdTypeId = null;
			}
			
			page();
		}
		
		function page(){
			layer.load(1, {shade: [0.6,'#676767']});
			$http.post("${pageContext.request.contextPath}/a/product/page",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.products = response.data.products;
				$scope.totalPages = response.data.totalPages;
				$scope.totalElements =  response.data.totalElements;
				
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
				layer.closeAll('loading');
		    });
		}
		
		//批量上架或下架
		$scope.shangJiaOrXiajia = function(type){
			var selectedIds = new Array();
			angular.forEach($scope.products,function(item, index){
				if(item.isSelected){
					selectedIds.push(item.id);
				}
			})
			
			if(selectedIds.length==0){
				layer.msg('请先选中一个或多个产品！', {offset: 't'});
				return;
			}
			
			layer.load(1, {shade: [0.6,'#676767']});
			if(type==0){
				$http.get("${pageContext.request.contextPath}/a/product/batchXiaJia?productIds="+selectedIds.join(','),null).success(function(response) {
					page();
					layer.msg('下架成功！', {icon: 1,offset: 't'});
			    });
			}else if(type==1){
				$http.get("${pageContext.request.contextPath}/a/product/batchShangJia?productIds="+selectedIds.join(','),null).success(function(response) {
					page();
					layer.msg('上架成功！', {icon: 1,offset: 't'});
			    });
			}
		}
		
		//打开修改模态框
		$scope.openModifyModal = function(bean){
			$('#modifyProductModel').modal(); 
			$http.get("${pageContext.request.contextPath}/a/product/getProductInfoById/"+bean.id,null).success(function(response) {
				$scope.productInfo = response.data;
				initProducTypeSelected();
				
				
				
				//创建一个编辑器
				var editor = CKEDITOR.instances['productDescEditor'];
			    if (editor) { editor.destroy(true); }
				CKEDITOR.replace('productDescEditor');
				CKEDITOR.editorConfig = function( config ) {
					config.language = 'zh-cn';
				};
		    });
		}
		
		//提交修改
		$scope.commitModify = function(){
			var productDesc = CKEDITOR.instances['productDescEditor'].getData();
			$scope.productInfo.description=productDesc;
			
			$scope.productInfo.firstTypeId = $scope.selectedFirstType.id;
			$scope.productInfo.secTypeId = $scope.selectedSecType.id;
			$scope.productInfo.thirdTypeId = $scope.selectedThirdType.id;
			
			layer.load(1, {shade: [0.6,'#676767']});
			$http.post("${pageContext.request.contextPath}/a/product/updateProduct",angular.toJson($scope.productInfo)).success(function(response) {
				$('#modifyProductModel').modal('hide')
				$scope.productInfo = {};
				page();
				layer.msg('修改成功！', {icon: 1,offset: 't'});
		    });	
		}
		
		//获取全部产品类别
		$http.get("${pageContext.request.contextPath}/a/productType/getAllType",null).success(function(response) {
			$scope.allProductTypes = response.data;
	    });
		
		function initProducTypeSelected(){
			angular.forEach($scope.allProductTypes,function(item, index){
				if(item.id == $scope.productInfo.firstTypeId){
					$scope.selectedFirstType = item;
					
					angular.forEach(item.productSecTypes,function(item2, index){
						if(item2.id == $scope.productInfo.secTypeId){
							$scope.selectedSecType = item2;
							
							angular.forEach(item2.thirdTypes,function(item3, index){
								if(item3.id == $scope.productInfo.thirdTypeId){
									$scope.selectedThirdType = item3;
								}
							})	
						}
					})
				}
			})
		}
		
		/* $scope.$watch('firstType', function(newVal) {
	        if (newVal){
	        	console.log($scope.firstType);
	        	
	        	angular.forEach($scope.allProductTypes,function(item, index){
					if(item.id == newVal){
						$scope.firstType = item;
					}
				})
	        }
	    }); */
	});

	//以下是jQuery代码
	$(function(){
		/* CKEDITOR.replace('editor1');
		CKEDITOR.editorConfig = function( config ) {
			config.language = 'zh-cn';
		}; */
		
		$("#firstTypeChosen").change(function() {
			var firTypeId = $(this).val();
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/a/product/findSecTypeByFirType",
				data : {
					"firTypeId" : firTypeId
				},
				dataType : 'html',
				success : function(html) {
					$("#secTypeChosen").empty().append(html);
				}
			});
		});
		
		$("#secTypeChosen").change(function() {
			var secTypeId = $(this).val();
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/a/product/findThirdTypeBySecType",
				data : {
					"secTypeId" : secTypeId
				},
				dataType : 'html',
				success : function(html) {
					$("#thirdTypeChosen").empty().append(html);
				}
			});
		});
	})
	function modifyProductInfo(productId){
    	$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/a/product/getProductInfoById",
			dataType : "json",
			data : {
				"id" : productId
			},
			beforeSend:function(){
		    	waitingDialog.show('请稍等...', {dialogSize: 'sm'});
			},
			success : function(data) {
				waitingDialog.hide();
				$("#modifyModalProductId").val("").val(data.id);
				$("#modifyModalImage").val("").val(data.image);
				$("#modifyModalIsTop").val("").val(data.isTop);
				$("#modifyModalName").val("").val(data.name);
				$("#firstTypeChosen").val("").val(data.firstType.id);
				$("#secTypeChosen").val("").val(data.secType.id);
				$("#thirdTypeChosen").val("").val(data.thirdType.id);
				$("#modifyModalPrice").val("").val(data.price);
				$("#modifyModalMarketPrice").val("").val(data.marketPrice);
				$("#editor1").val("").val(data.description);
				if(data.isShangJia){
					$("#shangjia").prop("checked",true);
				}else{
					$("#xiajia").prop("checked",true);
				}
				CKEDITOR.instances['editor1'].setData(data.description);
				
				$('#modifyModal').modal('show');
			}
		});
	}
	
	function updateProductPre(){
		var name = $("#modifyModalName").val();
		if(!name.trim()){
			$("#modifyModalName").focus();
			swal("产品名称为空！", "", "error")
			return false;
		}
		
		var firstType = $("#firstTypeChosen").val();
		if(!firstType.trim()){
			$("#firstTypeChosen").focus();
			swal("一级分类为空！", "", "error")
			return false;
		}
		
		var secType = $("#secTypeChosen").val();
		if(!secType.trim()){
			$("#secTypeChosen").focus();
			swal("二级分类为空！", "", "error")
			return false;
		}
		
		var thirdType = $("#thirdTypeChosen").val();
		if(!thirdType.trim()){
			$("#thirdTypeChosen").focus();
			swal("三级分类为空！", "", "error")
			return false;
		}
		
		var priceStr = $("input[name='price']").val();
		if(!priceStr.trim()){
			$("input[name='priceStr']").focus();
			swal("价格为空！", "", "error");
			return false;
		}
		if(priceStr.match(/^\d+$/)){ //integer
			// do noting
		}else if(priceStr.match(/^\d+\.\d+$/)){//float
			// do noting
		}else{
			swal("价格格式不正确！", "", "error");
			return false;
		}
		
		var marketPriceStr = $("input[name='marketPrice']").val();
		if(!marketPriceStr.trim()){
			$("input[name='marketPriceStr']").focus();
			swal("超市价格为空！", "", "error")
			return false;
		}
		if(marketPriceStr.match(/^\d+$/)){ //integer
			// do noting
		}else if(marketPriceStr.match(/^\d+\.\d+$/)){//float
			// do noting
		}else{
			swal("超市价格格式不正确！", "", "error");
			return false;
		}
		
		waitingDialog.show('处理中...', {dialogSize: 'sm'});
		return true;
	}
</script>
</html>