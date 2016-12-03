<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商品首页</title>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
</head>
<style>
.rect_wrap .search_wrap .search_bd .search_box .search_icon {
	padding: 8px 14px;
}

.page_box .page_title .title {
	padding: 10px;
}

.menu_wrap .left_nav .pannel_box .pannel_item.show {
    width: 800px;
}

.shop_header {
    background-color: #ddd;
}

/* .ad_box{
	height: 139px;
	width: 1138px;
} */
</style>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
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
				<div class="search_hd">
					<a href="javascript:;">高级<br>搜索
					</a>
				</div>
				<div class="search_ft">
					<ul class="clear">
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
					</ul>
				</div>
			</div>
			<div class="shopping_cart right">
				<img
					src="${pageContext.request.contextPath}/resources/images/index/cart.png"
					class="cart_icon"> 我的购物车 <i class="shop_num">20</i>
			</div>
		</div>
	</section>
	<section class="menu_wrap">
		<div class="top_nav">
			<div class="container">
				<div class="classify_title">
					<strong>商品分类</strong><img
						src="${pageContext.request.contextPath}/resources/images/index/menu_list.png">
				</div>
				<ul class="top_nav_menu clear">
					<li class="select"><a href="javascript:;">首页</a></li>
					<li><a href="javascript:;">今日降价</a></li>
					<li><a href="javascript:;">最新上架</a></li>
					<li><a href="javascript:;">关于我们</a></li>
				</ul>
			</div>
		</div>
		<div class="banner_warp">
			<!-- 轮播begin -->
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<c:forEach items="${showAds}" varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							</c:when>
							<c:otherwise>
								<li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- <li data-target="#carousel-example-generic" data-slide-to="1"></li> -->
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${showAds}" var="ad" varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<div class="item active">
									<a href="${ad.href}" target="_blank">
									<img src="/upload${ad.ossKey}" style="height: 350px" alt="...">
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item">
									<a href="${ad.href}" target="_blank">
									<img src="/upload${ad.ossKey}" style="height: 350px" alt="...">
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> 
					<span class="glyphicon glyphicon-chevron-left"></span> 
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> 
					<span class="glyphicon glyphicon-chevron-right"></span> 
					<span class="sr-only">Next</span>
				</a>
			</div>
			<!-- 轮播end -->



			<div class=" clear">
				<div class="left_nav container">
					<div class="nav_menu_box">
						<ul>
							<c:forEach items="${productTypes}" var="bean">
								<li class="nav_item"><a href="javascript:;">${bean.name}</a><i class="icon-angle-right"></i></li>
							</c:forEach>
						</ul>
					</div>
					<div class="pannel_box">
						<ul>
							<c:forEach items="${productTypes}" var="productType">
								<li class="pannel_item">
								<div class="item_content">
									<ul>
										<c:forEach items="${productType.productSecTypes}" var="secType">
											<li class="sort_li">
												<h4>${secType.name}</h4>
												<div>
													<c:forEach items="${secType.thirdTypes}" var="thirdType">
														<a href="javascript:;">${thirdType.name}</a>
													</c:forEach>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="container">
	
		<!--推荐热销专卖商品-->
		<section class="best_sellers_warp page_box">
			<div class="page_title">
				<h3 class="title left">推荐热销专卖商品</h3>
				<a href="javascript:;" class="more right">查看更多>></a>
			</div>
			<div class="page_list">
				<ul class="clear">
					<li>
						<div class="goods_img">
							<img
								src="${pageContext.request.contextPath}/resources/images/goods/1.jpg">
						</div>
						<p class="goods_name">稻香村月饼礼盒中秋送礼</p>
						<p class="goods_volume">成交3908098盒</p>
						<div class="goods_price">
							<a class="red" href="javascript:;">登录</a>查看专属价格
						</div>
					</li>
					<li>
						<div class="goods_img">
							<img
								src="${pageContext.request.contextPath}/resources/images/goods/1.jpg">
						</div>
						<p class="goods_name">稻香村月饼礼盒中秋送礼</p>
						<p class="goods_volume">成交3908098盒</p>
						<div class="goods_price">
							<span class="new_price">180元</span><span class="old_price">320元</span>
						</div>
					</li>
					<li>
						<div class="goods_img">
							<img
								src="${pageContext.request.contextPath}/resources/images/goods/1.jpg">
						</div>
						<p class="goods_name">稻香村月饼礼盒中秋送礼</p>
						<p class="goods_volume">成交3908098盒</p>
						<div class="goods_price">
							<a class="red" href="javascript:;">登录</a>查看专属价格
						</div>
					</li>
					<li>
						<div class="goods_img">
							<img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg">
						</div>
						<p class="goods_name">稻香村月饼礼盒中秋送礼</p>
						<p class="goods_volume">成交3908098盒</p>
						<div class="goods_price">
							<span class="new_price">180元</span><span class="old_price">320元</span>
						</div>
					</li>
					<li>
						<div class="goods_img">
							<img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg">
						</div>
						<p class="goods_name">稻香村月饼礼盒中秋送礼</p>
						<p class="goods_volume">成交3908098盒</p>
						<div class="goods_price">
							<span class="new_price">180元</span><span class="old_price">320元</span>
						</div>
					</li>
				</ul>
			</div>
		</section>
		<!--结束推荐热销专卖商品-->
		<%-- <section class="ad_box"><img src="/upload${barAd.ossKey}"></section> --%>
		<img style="width:1140px" src="/upload${barAd.ossKey}">
		<!--副食零食-->
		<section class="item_wrap page_box floor_2">
			<div class="page_title">
				<h3 class="title left">副食零食</h3>
				<a href="javascript:;" class="more right">查看更多>></a>
			</div>
			<div class="page_content clear">
				<div class="left_ad left">
					<a href="javascript:;">
						<h3>副食零食</h3>
						<h4>GO></h4> <img src="${pageContext.request.contextPath}/resources/images/index/item_img_2.png">
					</a>
				</div>
				<div class="page_list left">
					<ul class="clear">
						<c:forEach items="${products1}" begin="0" end="3" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
					<div class="split_line"></div>
					<ul class="clear">
						<c:forEach items="${products1}" begin="4" end="7" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</section>
		<!--结束副食零食-->
		<!--酒水饮料-->
		<section class="item_wrap page_box floor_1">
			<div class="page_title">
				<h3 class="title left">酒水饮料</h3>
				<a href="javascript:;" class="more right">查看更多>></a>
			</div>
			<div class="page_content clear">
				<div class="left_ad left">
					<a href="javascript:;">
						<h3>酒水饮料</h3>
						<h4>GO></h4> <img src="${pageContext.request.contextPath}/resources/images/index/item_img_1.png">
					</a>
				</div>
				<div class="page_list left">
					<ul class="clear">
						<c:forEach items="${products2}" begin="0" end="3" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
					<div class="split_line"></div>
					<ul class="clear">
						<c:forEach items="${products2}" begin="4" end="7" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</section>
		<!--结束酒水饮料-->
		<!--粮油调味-->
		<section class="item_wrap page_box floor_3">
			<div class="page_title">
				<h3 class="title left">粮油调味</h3>
				<a href="javascript:;" class="more right">查看更多>></a>
			</div>
			<div class="page_content clear">
				<div class="left_ad left">
					<a href="javascript:;">
						<h3>粮油调味</h3>
						<h4>GO></h4> <img
						src="${pageContext.request.contextPath}/resources/images/index/item_img_3.png">
					</a>
				</div>
				<div class="page_list left">
					<ul class="clear">
						<c:forEach items="${products3}" begin="0" end="3" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
					<div class="split_line"></div>
					<ul class="clear">
						<c:forEach items="${products3}" begin="4" end="7" var="bean">
							<li>
								<div class="goods_img">
									<a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a>
								</div>
								<p class="goods_name">${bean.name}</p>
								<p class="goods_volume">成交${bean.sellCount}单</p>
								<div class="goods_price">
									<shiro:notAuthenticated>
										<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
									</shiro:notAuthenticated>
									<shiro:authenticated>
										<span class="new_price">${bean.price}元</span>
										<span class="old_price">${bean.marketPrice}元</span>
									</shiro:authenticated>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</section>
		<!--结束粮油调味-->
	</div>
	<section class="shop_advantage">
		<ul class="clear">
			<li><img src="${pageContext.request.contextPath}/resources/images/index/quality_goods.png">优质商品</li>
			<li><img src="${pageContext.request.contextPath}/resources/images/index/faster.png">极速配送</li>
			<li><img src="${pageContext.request.contextPath}/resources/images/index/sale.png">省钱省心</li>
		</ul>
	</section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<script>
	$(function() {
		$(".nav_menu_box .nav_item").mouseover(
				function() {
					var index = $(this).index();
					$(".pannel_box").addClass('show');
					$(".pannel_box .pannel_item").eq(index).addClass('show')
							.siblings().removeClass('show')
				}).mouseout(function() {
			$(".pannel_box").removeClass('show')
			$(".pannel_box .pannel_item").removeClass('show');
			$(".pannel_box .pannel_item").on('mouseover', function() {
				$(".pannel_box").addClass('show');
				$(this).addClass('show');
				var i = $(this).index();
				$(".nav_menu_box .nav_item").eq(i).addClass('select')
			}).mouseout(function() {
				$(".pannel_box").removeClass('show')
				$(this).removeClass('show');
				$(".nav_menu_box .nav_item").removeClass('select')
			})
		})

		/* var slidey = $('.banner').unslider({
			autoplay : true,
			infinite : true,
			speed : 500,
			dots : true,
			arrows : false,
			fluid : true
		});
		dataSlidey = slidey.data('unslider');
		$("#prev").click(function() {
			dataSlidey.prev();
		})
		$("#next").click(function() {
			dataSlidey.next();
		})
		$(".unslider").mouseover(function() {
			dataSlidey.stop();
			$(".arrow").addClass('hover')
		}).mouseout(function() {
			dataSlidey.start();
			$(".arrow").removeClass('hover')
		}) */
	})

</script>
</html>

