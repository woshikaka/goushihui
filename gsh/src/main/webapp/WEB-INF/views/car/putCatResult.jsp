<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>搜索列表</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
    <section class="menu_wrap">
        <div class="top_nav">
            <div class="container">
                <%-- <div class="classify_title"><strong>商品分类</strong><img src="${pageContext.request.contextPath}/resources/images/index/menu_list.png"></div> --%>
                <!-- <ul class="top_nav_menu clear">
                    <li><a href="javascript:;">最新上架</a></li>
                    <li><a href="javascript:;">关于我们</a></li>
                </ul> -->
            </div>
        </div>
        <div class=" clear menu_nav">
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
    </section>
	<section class="success_cart">
		<div class="container ">
			<div class="success_txt">
				<span class="check"></span><span class="txt">商品已成功加入购物车！</span>
			</div>
			<div class="info_warp">
				<div class="img_box">
					<img src="/upload${product.image}">
				</div>
				<div class="p_info">
					<a href="javascript:;">${product.name}</a>
					<p>数量：${productCnt}</p>
				</div>
				<div class="btn_box right">
					<a href="${pageContext.request.contextPath}/product/detail/${product.id}" class="btn btn-default btn-lg">查看商品详情</a> 
					<a href="${pageContext.request.contextPath}/c/carUI" class="btn btn-danger btn-lg">去购物车结算</a>
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
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<script>
	$(function() {
		$(".sort_warp li").on('click', function() {
			$(this).addClass('select').siblings().removeClass('select');
		})

		$(".classify_title").hover(
				function() {
					$(".menu_nav").show();
				},
				function() {
					$(".menu_nav").hide();
					$(".nav_menu_box .nav_item").mouseover(
							function() {
								$(".menu_nav").show();
								var index = $(this).index();
								$(".pannel_box").addClass('show');
								$(".pannel_box .pannel_item").eq(index)
										.addClass('show').siblings()
										.removeClass('show')
							}).mouseout(
							function() {
								$(".menu_nav").hide();
								$(".pannel_box").removeClass('show')
								$(".pannel_box .pannel_item").removeClass(
										'show');
								$(".pannel_box .pannel_item").on(
										'mouseover',
										function() {
											$(".menu_nav").show();
											$(".pannel_box").addClass('show');
											$(this).addClass('show');
											var i = $(this).index();
											$(".nav_menu_box .nav_item").eq(i)
													.addClass('select')
										}).mouseout(
										function() {
											$(".menu_nav").hide();
											$(".pannel_box")
													.removeClass('show')
											$(this).removeClass('show');
											$(".nav_menu_box .nav_item")
													.removeClass('select')
										})
							})

				})

	})
</script>
</html>
