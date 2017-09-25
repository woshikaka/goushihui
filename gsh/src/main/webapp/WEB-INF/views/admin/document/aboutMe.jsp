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
		<div id="page-wrapper" ng-app="wholesaleRuleApp" ng-controller="wholesaleRuleCtrl">
			<textarea name="description" id="editor1" rows="10" cols="80">
			{{ruleContent}}</textarea>
			<button type="submit" class="btn btn-primary" style="margin-top: 10px" ng-click="save()">修改关于我们</button>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
	<script>
		var app = angular.module('wholesaleRuleApp', []);	
		app.controller('wholesaleRuleCtrl', function($scope, $http,$location,$window) {
			
			layer.load(1, {shade: [0.6,'#676767']});
			$http.post("${pageContext.request.contextPath}/a/document/getTextPlan/2",null).success(function(response) {
				
				CKEDITOR.replace( 'editor1', {
				    height: '400px'
				});
				/* CKEDITOR.editorConfig = function( config ) {
					config.language = 'zh-cn';
				}; */
				
				
				$scope.ruleContent = response.data;
				layer.closeAll('loading');
		    });
			
			$scope.save = function(){
				var ruleContent = CKEDITOR.instances['editor1'].getData();
				
				var requestParam = {type:2,ruleContent:ruleContent};
				
				layer.load(1, {shade: [0.6,'#676767']});
				$http.post("${pageContext.request.contextPath}/a/document/save",angular.toJson(requestParam)).success(function(response) {
					layer.msg('关于我们修改成功！', {icon: 1});
					layer.closeAll('loading');
			    });
			}
		});
		
	</script>
</body>
</html>