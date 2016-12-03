<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>

<head>
<title></title>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
<style type="text/css">
.login_con{
	height:490px
}
</style>
</head>
<body>

	<section class="login_header">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/homePage"><img src="${pageContext.request.contextPath}/resources/images/logo.png" style="margin-left: 68px"></a>
		</div>
	</section>
	<section class="login_con">
		<div class="container">
			<div class="login_box right">
				<form class="form" method="post" action="<%=request.getContextPath()%>/login">
					<h4>用户登录</h4>
					<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
					<div class="form-li">
						<div class="form-box">
							<input type="text" name="name" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-li">
						<div class="form-box">
							<input type="password" name="password" placeholder="请输入登录密码">
						</div>
					</div>
					<div class="form-li">
						<button class="btn btn-primary rg_btn">立即登录</button>
					</div>
					<div class="form-li">
						<a href="<%=request.getContextPath()%>/registerUI" class="right text_underline">还没有账号？立即注册</a>
					</div>
				</form>
			</div>
		</div>

	</section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
</body>
</html>
<script>
	$(function() {
		var loginBoxMT = ($(".login_con").height() - $(".login_box")
				.outerHeight()) / 2
		$(".login_box").css('margin-top', loginBoxMT)
		var loginFootHeight = $(window).height() - $(".login_header").height()
				- $(".login_con").height() + 'px';
		$(".login_footer").css({
			'height' : loginFootHeight,
			'line-height' : loginFootHeight
		})
	})
</script>
