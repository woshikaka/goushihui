<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>购食汇商城</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<section class="rect_wrap">
		<div class="container">
			<a href="${pageContext.request.contextPath}/homePage"><img id="logoImg" src="" style="margin-top:-35px;width: 160px;height: 100px"/></a>
			<div class="search_wrap">
				<div class="search_bd">
					<div class="search_box">
						<div class="search_icon">
							<i class="icon-search"></i>
						</div>
						<form id="searchForm" action="${pageContext.request.contextPath}/searchUI" method="post" onsubmit="return searchCheck()">
							<input id="keywordInput" type="text" name="keyword" class="search_input">
							<button type="submit" class="search_btn right">搜索</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="success_cart">
		<div class="container ">
			<div class="success_txt">
				<span class="check"></span><span class="txt">您已成功付款！</span>
			</div>
			<div class="info_warp">
				<div class="p_info">
					<p>实付款：<font style="color:#b10000;font-weight: bolder;">￥${totalAmount}</font></p>
				</div>
				<div class="btn_box right">
					<a href="${pageContext.request.contextPath}/m/center" class="btn btn-default btn-lg">去个人中心查看订单</a> 
				</div>
			</div>
		</div>
	</section>
	<section class="shop_advantage">
		<ul class="clear">
			<li><img
				src="${pageContext.request.contextPath}/resources/images/index/quality_goods.png">优质商品</li>
			<li><img
				src="${pageContext.request.contextPath}/resources/images/index/faster.png">极速配送</li>
			<li><img
				src="${pageContext.request.contextPath}/resources/images/index/sale.png">省钱省心</li>
		</ul>
	</section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
</body>
</html>
