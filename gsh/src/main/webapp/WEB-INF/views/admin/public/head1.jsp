<%@ page contentType="text/html;charset=UTF-8"%>
<style>
/* .rect_wrap .search_wrap .search_bd .search_box .search_icon {
	padding: 8px 14px;
} */
</style>
<section class="rect_wrap">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
			<a href="${pageContext.request.contextPath}/homePage"><img src="${pageContext.request.contextPath}/resources/images/logo.png" style="margin-top:-35px;"/></a>
			<div class="search_wrap">
				<div class="search_bd">
					<div class="search_box">
						<div class="search_icon">
							<i class="icon-search"></i>
						</div>
						<input type="text" name="" placeholder="请输入条码/商品名称/类别查询"
							class="search_input">
						<button class="search_btn right">搜索</button>
					</div>
				</div>
				<!-- <div class="search_hd">
					<a href="javascript:;">高级<br>搜索
					</a>
				</div> -->
				<!-- <div class="search_ft">
					<ul class="clear">
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
					</ul>
				</div> -->
			</div>
			<div class="shopping_cart right">
				<a href="${pageContext.request.contextPath}/c/carUI" style="color: #fff"><img src="${pageContext.request.contextPath}/resources/images/index/cart.png" class="cart_icon"> 我的购物车 <i class="shop_num">${carCnt}</i></a>
			</div>
		</div>
	</section>