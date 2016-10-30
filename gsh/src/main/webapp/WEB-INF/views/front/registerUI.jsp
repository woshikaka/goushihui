<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>

<head>
<title></title>
<link type="text/css" rel="stylesheet"
	href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
<script src="/gsh/resources/jquery/jquery-1.11.3.min.js"></script>
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
				<form class="form" method="post" action="/gsh/register">
					<h4>用户注册</h4>
					<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
					<div class="form-li">
						<div class="form-box">
							<input type="text" name="name" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-li">
						<div class="form-box">
							<input type="text" name="yzm" placeholder="请输入验证码" class="yzm"> 
							<img title="点击更换" onclick="refreshValidateCode(this)" src="/gsh/validateCode">
						</div>
					</div>
					<div class="form-li">
						<div class="form-box">
							<input type="password" name="password" placeholder="请输入登录密码">
						</div>
					</div>
					<div class="form-li">
						<div class="form-box">
							<input type="password" name="againPassword" placeholder="请再次登录密码">
						</div>
					</div>
					<div class="form-li">
						<button class="btn btn-primary rg_btn">立即注册</button>
					</div>
					<div class="form-li">
						<a href="<%=request.getContextPath()%>/loginUI" class="right text_underline">已有账号，立即登录</a>
					</div>
				</form>
			</div>
		</div>

	</section>
	<footer class="login_footer"> © 2016 Taobao.com 版权所有
		网络文化经营许可证：文网文[2010]040号|增值电信业务经营许可证：浙B2-20080224-1|信息网络传播视听节目许可证：1109364号
	</footer>
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
	
	function refreshValidateCode(img){
		$(img).attr("src","/gsh/validateCode?hehe="+Math.random());
	}
</script>
