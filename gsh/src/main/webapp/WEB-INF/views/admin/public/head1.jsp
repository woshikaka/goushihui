<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<style>
.shop_header {
    background-color: #ddd;
}
</style>
<section class="rect_wrap">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
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
			<div class="shopping_cart right">
				<a href="${pageContext.request.contextPath}/c/carUI" style="color: #fff"><img src="${pageContext.request.contextPath}/resources/images/index/cart.png" class="cart_icon"> 我的购物车 <i class="shop_num">${carCnt}</i></a>
			</div>
		</div>
	</section>
	<script>
		$("#logoImg").attr("src","${pageContext.request.contextPath}/resources/images/logo.png?temp="+Date.now());
</script>