<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/admin/public/nav.jsp" />
		<div id="page-wrapper" ng-app="logoApp" ng-controller="logoCtrl">
			正在使用中的logo：<img ng-src="{{logoUrl}}" style="width: 160px;height: 100px"/>
			
			<hr/>
			 <input type="file" name="file" class="layui-upload-file">
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/angular-upload.min.js"></script>
	<%--<script src="${pageContext.request.contextPath}/resources/js/ng-file-upload.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/ng-file-upload-shim.min.js"></script> --%>
	<script>
		var app = angular.module('logoApp', []);	
		app.controller('logoCtrl', function($scope,$http,$location,$window) {
			$scope.logoUrl="${pageContext.request.contextPath}/resources/images/logo.png?temp="+Date.now();
			layui.upload({
				url: '${pageContext.request.contextPath}/a/document/upload',
				before: function(input){
				 	layer.load(1, {shade: [0.6,'#676767']});
				},
				success: function(res){
					$window.location.reload();
				}
			});
		});
	</script>
</body>
</html>