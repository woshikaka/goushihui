<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>购食汇</title>
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
    <section class="crumb_warp">
        <div class="container">
            <a href="index.html">${product.firstType.name}</a>><a href="javascript:;">${product.secType.name}</a>><a>${product.thirdType.name}</a>
        </div>
    </section>
    <section class="shop_detail_warp">
        <div class="container clear">
            <div class="detail_warp left">
                <div class="shop_info clear">
                    <div class="img_box left"><img src="/upload${product.image}" style="width: 395px"></div>
                    <div class="info_warp left">
                        <h2>${product.name}</h2>
                        <table>
                            <tbody>
                                <tr><td>超市价：</td><td><i class="line_through">￥${product.marketPrice}</i></td></tr>
                                <tr><td>购食汇价格：</td><td><span class="orange">￥${product.price}</span><span class="sales">（立省￥${product.marketPrice-product.price}）</span></td></tr>
                                <tr><td>库存：</td><td>剩余<i class="red">${product.stockCount}</i>件</td></tr>
                                <tr><td>销量</td><td>已售<i class="red">${product.sellCount}</i>件</td></tr>
                                <!-- <tr><td>规格：</td><td>1100g</td></tr>
                                <tr><td>起订量：</td><td>1盒（不可退）</td></tr>
                                <tr><td>供应商：</td><td>福州小米百货</td></tr> -->
                                <tr><td>数量：</td><td><div class="buy_num"><button id="minus-btn"">-</button><input type="text" value="1" id="buy-num"><button id="add-btn"">+</button></div></td></tr>
                            </tbody>
                        </table>
                        <div class="btn_box">
                            <!-- <a href="javascript:;" id="buy-now" class="btn">立即购买</a> -->
                            <a href="javascript:;" id="add-cart" class="btn"><img src="${pageContext.request.contextPath}/resources/images/detail/shopping_cart.png">加入购物车</a>
                        </div>
                    </div>
                </div>
                <div class="shop_descrip">
                    <div class="title"><span class="btn">商品描述</span></div>
                    <div class="descrip">
                        <%-- <p>配料：寄哦寄哦i的减肥if奖</p>
                        <p>保质期：寄哦寄哦i的减肥if奖</p>
                        <img src="${pageContext.request.contextPath}/resources/images/detail/detial_img.png"> --%>
                        ${product.description}
                    </div>
                </div>
            </div>
            <div class="recommend_warp page_box right">
                <h3>看了又看</h3>
                <div class="page_list">
                    <ul class="clear">
                        <li>
                            <div class="goods_img"><img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg"></div>
                            <p class="goods_name">稻香村月饼礼盒中秋送礼</p>
                            <p class="goods_volume">成交3908098盒</p>
                            <div class="goods_price"><a class="red" href="javascript:;">登录</a>查看专属价格</div>
                        </li>
                        <li>
                            <div class="goods_img"><img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg"></div>
                            <p class="goods_name">稻香村月饼礼盒中秋送礼</p>
                            <p class="goods_volume">成交3908098盒</p>
                            <div class="goods_price"><span class="new_price">180元</span><span class="old_price">320元</span></div>
                        </li>
                        <li>
                            <div class="goods_img"><img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg"></div>
                            <p class="goods_name">稻香村月饼礼盒中秋送礼</p>
                            <p class="goods_volume">成交3908098盒</p>
                            <div class="goods_price"><a class="red" href="javascript:;">登录</a>查看专属价格</div>
                        </li>
                        <li>
                            <div class="goods_img"><img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg"></div>
                            <p class="goods_name">稻香村月饼礼盒中秋送礼</p>
                            <p class="goods_volume">成交3908098盒</p>
                            <div class="goods_price"><span class="new_price">180元</span><span class="old_price">320元</span></div>
                        </li><li>
                            <div class="goods_img"><img src="${pageContext.request.contextPath}/resources/images/goods/1.jpg"></div>
                            <p class="goods_name">稻香村月饼礼盒中秋送礼</p>
                            <p class="goods_volume">成交3908098盒</p>
                            <div class="goods_price"><span class="new_price">180元</span><span class="old_price">320元</span></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <section class="shop_advantage">
        <ul class="clear">
            <li><img src="${pageContext.request.contextPath}/resources/images/index/quality_goods.png">万种优质商品 在线直供</li>
            <li><img src="${pageContext.request.contextPath}/resources/images/index/faster.png">零时下单，次日到达</li>
            <li><img src="${pageContext.request.contextPath}/resources/images/index/sale.png">竞价保障 省时省心省钱</li>
        </ul>
    </section>

<section class="footer_main">
    <div class="container">
        <ul class="item clear">
            <li>
                <h6>平台使用说明</h6>
                <ul>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                </ul>
            </li>
            <li>
                <h6>平台使用说明</h6>
                <ul>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                </ul>
            </li>
            <li>
                <h6>平台使用说明</h6>
                <ul>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                </ul>
            </li>
            <li>
                <h6>平台使用说明</h6>
                <ul>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                </ul>
            </li>
            <li>
                <h6>平台使用说明</h6>
                <ul>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                    <li><a href="javascript:;">新手入门</a></li>
                </ul>
            </li>
        </ul>
        <div class="bottom">© 2016 Taobao.com 版权所有 网络文化经营许可证：文网文[2010]040号|增值电信业务</div>
    </div>
</section>  
</body>
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<script>
    $(function(){
        $("#minus-btn").on('click',function(){
            var nowBuyNum = $("#buy-num").val();
            if(nowBuyNum==1){
                $(this).attr('disabled')
            }else{
                $(this).removeAttr('disabled')
                nowBuyNum--;
            }
            $("#buy-num").val(nowBuyNum);
        })
        $("#add-btn").on('click',function(){
            var nowBuyNum = $("#buy-num").val();
                nowBuyNum++;
            
            $("#buy-num").val(nowBuyNum);
        })
            
    })
</script>
</html>