<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <title>搜索列表</title>
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
	<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
        <section class="menu_wrap">
        <div class="top_nav">
            <div class="container">
                <div class="classify_title"><strong>商品分类</strong><img src="${pageContext.request.contextPath}/resources/images/index/menu_list.png"></div>
                <ul class="top_nav_menu clear">
                    <li><a href="javascript:;">最新上架</a></li>
                    <li><a href="javascript:;">关于我们</a></li>
                </ul>
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
    <div class="container search_list">
        <div class="search_location">
            您搜索的“<span class="red">粮油调料&gt;调味品&gt;醋</span>”，共有 <span class="red">7</span> 件商品：
        </div>
        <div class="sort_warp">
            <!-- <h4>综合排序</h4> -->
            <ul><li>综合排序</li><li>销量从高到低</li><li>价格从低到高</li><li>上架时间</li></ul>
        </div>
        <section class="best_sellers_warp page_box">
            <div class="page_list">
                <ul class="clear">
                	<c:forEach items="${pageBean.recordList}" var="bean">
                    <li>
                        <div class="goods_img"><img src="/upload${bean.image}"></div>
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
            <div class="paging_warp clear">
                <div class="paging_info left">共有<i class="red">35</i>条查询记录 每页 <i class="red">35</i>条</div>
                <div class="paging_num right">
                    <a href="javascript:void(0);" class="page-prev" page="1"><span>上一页</span></a>
                    <a href="javascript:void(0);" class="page-span" page="1">1</a>  <span class="page-selected">2</span>  
                    <a href="javascript:void(0);" class="page-span" page="3">3</a>  
                    <a href="javascript:void(0);" class="page-next" page="3"><span>下一页</span></a>
                    共3页到第<input type="text" value="1">页
                    <span class="confirm btn btn-primary">确定</span>
                </div>
            </div>
        </section>
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
    $(function(){
        $(".sort_warp li").on('click',function(){
            $(this).addClass('select').siblings().removeClass('select');
        })

        $(".classify_title").hover(function(){
            $(".menu_nav").show();
        },function(){
            $(".menu_nav").hide();
            $(".nav_menu_box .nav_item").mouseover(function(){
                $(".menu_nav").show();
                var index = $(this).index();
                $(".pannel_box").addClass('show');
                $(".pannel_box .pannel_item").eq(index).addClass('show').siblings().removeClass('show')
            }).mouseout(function(){
                $(".menu_nav").hide();
                $(".pannel_box").removeClass('show')
                $(".pannel_box .pannel_item").removeClass('show');
                $(".pannel_box .pannel_item").on('mouseover',function(){
                    $(".menu_nav").show();
                    $(".pannel_box").addClass('show');
                    $(this).addClass('show');
                    var i = $(this).index();
                    $(".nav_menu_box .nav_item").eq(i).addClass('select')
                }).mouseout(function(){
                    $(".menu_nav").hide();
                    $(".pannel_box").removeClass('show')
                    $(this).removeClass('show');
                    $(".nav_menu_box .nav_item").removeClass('select')
                })
            })


        })
        

    })
</script>
</html>
    