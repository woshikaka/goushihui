<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" /> -->
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1">  
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title></title>
    <!-- <link rel="icon" href="favicon.ico"> -->
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="/gsh/resources/css/shop.css" />
   <!--  <script src="js/jquery-1.11.3.min.js"></script> -->
   <!--  <script src="js/vue.min.js"></script>
    <script src="js/shop.js"></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
    <style type="text/css">
    	.num_desc{
    		margin-top: 10px;
    		margin-bottom: 4px
    	}
    </style>
</head>
<body>
    <header class="shop_header">
       <section class="container">
             <div class="left">
                <span>欢迎来到***</span>
                <a class="red" href="javascript:void(0);">请登录</a>
                <a href="register.html">立即注册</a>
            </div>
            <div class="header_left right">
                <ul>
                    <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/resources/images/user.png"><span>个人中心</span></a></li>
                    <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/resources/images/shopping_cart.png"><span>购物车</span></a></li>
                    <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/resources/images/collect.png"><span>收藏夹</span></a></li>
                </ul>
            </div>
       </section>
    </header>
    <section class="center_header">
        <div class="container">
            <div class="logo_box"></div>
            <ul class="center_header_nav clear">
                <li><a href="javascript:;">首页</a></li>
                <li><a href="javascript:;">账户设置<i class="icon-chevron-down"></i></a></li>
                <li><a href="javascript:;">消息</a></li>
            </ul>
            <div class="search_box right">
                <form>
                    <input type="text" name="">
                    <button class="">搜索</button>
                </form>
            </div>
        </div>
    </section>
    <section class="center_con">
        <div class="container clear">
             <div class="center_menu left">
                <h5>我的购食汇</h5>
                <ul><li class="select"><a href="javascript:;">账户浏览</a></li>
                    <li class="select"><a href="javascript:;">我的订单</a></li>
                    <li class="select"><a href="javascript:;">我的地址</a></li>
                    <li class="select"><a href="javascript:;">修改密码</a></li>
                </ul>
                <h5>我的级别</h5>
                <ul>
                    <li class="select"><a href="javascript:;">我的积分</a></li>
                    <li class="select"><a href="javascript:;">我的礼品</a></li>
                    <li class="select"><a href="javascript:;">积分规则</a></li>
                </ul>
            </div>
            <div class="center_main left">
                <div class="base_info center_border clear">
                    <div class="user_info ">
                        <div class="logo"><img src="${pageContext.request.contextPath}/resources/images/center/user_logo.png"></div>
                        <div class="info ">
                            <h3>shineon<span>(1839483088)</span></h3>
                            <p>我的收获地址</p>
                        </div>
                    </div>
                    <div class="account_info">
                        <div class="balance_box  box">
                            <img src="${pageContext.request.contextPath}/resources/images/center/balance_img.png">
                            <span class="info">
                                <h6>账户余额</h6>
                                <p class="num">￥  3500</p>
                            </span>
                        </div>
                        <div class="integral__box box ">
                            <img src="${pageContext.request.contextPath}/resources/images/center/integral_img.png">
                            <span class="info">
                                <h6>会员积分（10元=1积分）</h6>
                                <p class="num">￥  3500</p>
                            </span>
                        </div>
                    </div>
                    
                </div>
                <div class="history center_border">
                    <h2 class="title">历史消费情况</h2>
                    <ul class="clear">
                        <li>
                            <img src="${pageContext.request.contextPath}/resources/images/center/order.png">
                            <!-- <p class="label">您共下单</p>
                            <p class="num">999次</p> -->
                            <div class="num_desc">您共下单</div>
                            <div class="num">999次</div>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/resources/images/center/money.png">
                            <!-- <p class="label">消费总额</p>
                            <p class="num">9400元</p> -->
                            <div class="num_desc">消费总额</div>
                            <div class="num">999元</div>
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/resources/images/center/market.png">
                            <!-- <p class="label">市场价总额</p>
                            <p class="num">1300元</p> -->
                            <div class="num_desc">市场价总额</div>
                            <div class="num">999元</div>
                            
                        </li>
                        <li>
                            <img src="${pageContext.request.contextPath}/resources/images/center/save.png">
                            <!-- <p class="label">为您节省</p>
                            <p class="num">309</p> -->
                            <div class="num_desc">为您节省</div>
                            <div class="num">999元</div>
                        </li>
                    </ul>
                </div>
                <div class="my_order center_border">
                    <h2 class="title">我的订单<a href="javascript:;" class="right">查看全部订单>></a></h2>
                    <table class="order_list">
                        <thead>
                            <tr>
                                <th width="16%">订单编号</th>
                                <th width="37%">商品名称</th>
                                <th width="8%">数量</th>
                                <th width="13%">市场价格</th>
                                <th width="13%">订单金额</th>
                                <th width="13%">交易状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1234488098098</td>
                                <td>
                                    <p class="goods_name elip">我是商品名称商品名称名称我是商品名称商品名称名称门洞if奖哦i到金佛i解放门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                </td>
                                <td>
                                    10
                                </td>
                                <td class="market_price">
                                    ￥2908
                                </td>
                                <td>
                                    <span class="red">￥1000</span>
                                </td>
                                <td class="order_staus">
                                    <p >交易成功</p>
                                    <a class="" href="javascript:;">订单详情</a>
                                </td>
                            </tr>
                            <tr>
                                <td>1234488098098</td>
                                <td>
                                    <p class="goods_name elip">我是商品名称商品名称名称我是商品名称商品名称名称门洞if奖哦i到金佛i解放门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                </td>
                                <td>
                                    10
                                </td>
                                <td class="market_price">
                                    ￥2908
                                </td>
                                <td>
                                    <span class="red">￥1000</span>
                                </td>
                                <td class="order_staus">
                                    <p >交易成功</p>
                                    <a class="" href="javascript:;">订单详情</a>
                                </td>
                            </tr>
                            <tr>
                                <td>1234488098098</td>
                                <td>
                                    <p class="goods_name elip">我是商品名称商品名称名称我是商品名称商品名称名称门洞if奖哦i到金佛i解放门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                </td>
                                <td>
                                    10
                                </td>
                                <td class="market_price"  class="market_price">
                                    ￥2908
                                </td>
                                <td>
                                    <span class="red">￥1000</span>
                                </td>
                                <td class="order_staus">
                                    <p >交易成功</p>
                                    <a class="" href="javascript:;">订单详情</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
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
</html>

<script>
    $(function(){
        $(".header_left li a").hover(function(){
            var img_url = $(this).find('img').attr('src').replace('.png','-hover.png');
            $(this).find('img').attr('src',img_url)
        },function(){
            var img_url = $(this).find('img').attr('src').replace('-hover.png','.png');
            $(this).find('img').attr('src',img_url)
        })
    })
</script>

