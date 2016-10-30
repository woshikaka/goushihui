<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>

<head>
<title></title>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
</head>
<body>

	<section class="login_header">
		<div class="logo">
			<img src="">
		</div>
	</section>
	<section class="login_con">
		<div class="container">
			<div class="login_box right">
				<form class="form" method="post" action="<%=request.getContextPath()%>/login">
					<h4>用户登录</h4>
					${isDangerShow}xxxxxxxxxxxx
					<%-- <%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%> --%>
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
	<footer class="login_footer"> © 2016 Taobao.com 版权所有 网络文化经营许可证：文网文[2010]040号|增值电信业务经营许可证：浙B2-20080224-1|信息网络传播视听节目许可证：1109364号 </footer>
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
