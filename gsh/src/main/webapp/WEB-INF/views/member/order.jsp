<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>
<head>
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
    <%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
    <section class="center_header">
        <div class="container">
            <!-- <div class="logo_box"></div> -->
            <ul class="center_header_nav clear">
                <li><a href="${pageContext.request.contextPath}/homePage">首页</a></li>
                <!-- <li><a href="javascript:;">账户设置<i class="icon-chevron-down"></i></a></li>
                <li><a href="javascript:;">消息</a></li> -->
            </ul>
            <!-- <div class="search_box right">
                <form>
                    <input type="text" name="">
                    <button class="">搜索</button>
                </form>
            </div> -->
        </div>
    </section>
    <section class="center_con">
        <div class="container clear">
             <div class="center_menu left">
                <h5>我的购食汇</h5>
                <ul>
                    <li class="select"><a href="javascript:;" style="color:#f04848;">我的订单</a></li>
                    <li class="select"><a href="${pageContext.request.contextPath}/m/address">我的地址</a></li>
                    <li class="select"><a href="${pageContext.request.contextPath}/m/password">修改密码</a></li>
                </ul>
                <!-- <h5>我的级别</h5>
                <ul>
                    <li class="select"><a href="javascript:;">我的积分</a></li>
                    <li class="select"><a href="javascript:;">我的礼品</a></li>
                    <li class="select"><a href="javascript:;">积分规则</a></li>
                </ul> -->
            </div>
            <div class="center_main left">
                <div class="my_order center_border">
                    <h2 class="title">我的订单<!-- <a href="javascript:;" class="right">查看全部订单>></a> --></h2>
                    <table class="order_list">
                        <thead>
                            <tr>
                                <th width="16%">订单编号</th>
                                <th width="37%">商品名称</th>
                                <th width="8%">数量</th>
                                <!-- <th width="13%">市场价格</th> -->
                                <th width="13%">订单金额</th>
                                <th width="13%">交易状态</th>
                                <th width="13%">操作</th>
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
                                <td>
                                    <span class="red">￥1000</span>
                                </td>
                                <td class="order_staus">
                                    <p >交易成功</p>
                                </td>
                                <td>
                                    <a class="" href="javascript:;">订单详情</a>
                                </td>
                            </tr>
                            <!-- <tr>
                                <td>1234488098098</td>
                                <td>
                                    <p class="goods_name elip">我是商品名称商品名称名称我是商品名称商品名称名称门洞if奖哦i到金佛i解放门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                    <p class="goods_name elip">我是商品名称商品名称名称门洞if奖哦i到金佛i解放</p>
                                </td>
                                <td>
                                    10
                                </td>
                                <td class="market_price">￥2908</td>
                                <td>
                                    <span class="red">￥1000</span>
                                </td>
                                <td class="order_staus">
                                    <p >交易成功</p>
                                </td>
                                <td class="market_price"> <a class="" href="javascript:;">订单详情</a></td>
                            </tr> -->
                            <!-- <tr>
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
                                <td>
                                    10
                                </td>
                            </tr> -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>    
</body>
</html>

<script>
</script>

