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
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<style type="text/css">
.password_form{
	background-color: #fff;
	padding:20px
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
			</ul>
		</div>
	</section>
	<section class="center_con">
		<div class="container clear">
			<div class="center_menu left">
				<h5>我的购食汇</h5>
				<ul>
					<%-- <li class="select"><a href="${pageContext.request.contextPath}/m/center">账户浏览</a></li> --%>
					<li class="select"><a href="${pageContext.request.contextPath}/m/center">我的订单</a></li>
					<li class="select"><a href="${pageContext.request.contextPath}/m/address">我的地址</a></li>
					<li class="select"><a href="javascript:;" style="color: #f04848;">修改密码</a></li>
				</ul>
				<!-- <h5>我的级别</h5>
                <ul>
                    <li class="select"><a href="javascript:;">我的积分</a></li>
                    <li class="select"><a href="javascript:;">我的礼品</a></li>
                    <li class="select"><a href="javascript:;">积分规则</a></li>
                </ul> -->
			</div>
			<div class="center_main left">
				<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
				<div class="password_form">
				
				<form class="form-horizontal" action="${pageContext.request.contextPath}/m/mp" method="post">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">旧密码</label>
					    <div class="col-sm-4">
					      <input type="password" class="form-control" name="oldPassword">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">新密码</label>
					    <div class="col-sm-4">
					      <input type="password" class="form-control" name="newPassword">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">确认新密码</label>
					    <div class="col-sm-4">
					      <input type="password" name="configPassword" class="form-control">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-default">确认</button>
					    </div>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
	<script>
	function add(){
		
	}
</script>
</body>
</html>