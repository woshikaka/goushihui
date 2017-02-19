<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<title>购物车</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/col.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/shop.css" />
</head>
<style>
.jiesuan_btn{
	height: 74px;
    background-color: #f04848;
    padding: 0 5%;
    font-size: 22px;
    color: #fff;
    margin-left: 4%;
}
</style>

<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
    <section class="place_order">
        <div class="container">
            <div class="pro_info_warp">
                <div class="box">
                    <h3>商品信息</h3>
                    <div id="pro-list" class="pro_table">
                        <table>
                            <thead>
                                <tr>
                                    <th width="6%"><input type="checkbox" name="" class="check_all"></th>
                                    <th width="40%">商品</th>
                                    <th width="9%">单价</th>
                                    <th width="15%">数量</th>
                                    <th width="10%">小计</th>
                                    <th  width="10%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" name="" class="check_one"></td>
                                    <td>
                                        <div class="prp_detail">
                                            <div class="img_box">
                                                <img src="../images/detail/detial_img.png">
                                            </div>
                                            <p>我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称</p>
                                        </div>
                                    </td>
                                    <td><strong>1000</strong></td>
                                    <td>
                                        <div class="buy_num_warp"><button class="minus-btn">-</button><input type="text" value="1" class="buy-num"><button class="add-btn">+</button></div>
                                    </td>
                                    <td>
                                        <strong class="red">￥1000</strong>
                                    </td>
                                    <td><a class="del_btn">删除</a></td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" name="" class="check_one"></td>
                                    <td>
                                        <div class="prp_detail">
                                            <div class="img_box">
                                                <img src="../images/detail/detial_img.png">
                                            </div>
                                            <p>我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称</p>
                                        </div>
                                    </td>
                                    <td><strong>1000</strong></td>
                                    <td>
                                        <div class="buy_num_warp"><button class="minus-btn">-</button><input type="text" value="1" class="buy-num"><button class="add-btn">+</button></div>
                                    </td>
                                    <td>
                                        <strong class="red">￥1000</strong>
                                    </td>
                                    <td><a class="del_btn">删除</a></td>
                                </tr>
                                <tr class="outof_stock">
                                    <td class="red">缺货</td>
                                    <td>
                                        <div class="prp_detail">
                                            <div class="img_box">
                                                <img src="../images/detail/detial_img.png">
                                            </div>
                                            <p>我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称</p>
                                        </div>
                                    </td>
                                    <td><strong>1000</strong></td>
                                    <td>
                                        <div class="buy_num_warp"><button class="minus-btn">-</button><input type="text" value="1" class="buy-num"><button class="add-btn">+</button></div>
                                    </td>
                                    <td>
                                        <strong class="red">￥1000</strong>
                                    </td>
                                    <td><a class="del_btn">删除</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="other" style="padding: 0 0 0 3%;">
                    <label for="has-check"><input id="has-check" type="checkbox" name="">已选（<i class="check_num">0</i>）</label>
                    <a href="javascript:;">批量删除</a>
                    <button class="jiesuan_btn right">去结算</button>
                    <span class="right">应付总额：<strong class="red">￥2333</strong></span>
                </div>
            </div>
            <div class="address_warp box">
                <h3>收货地址<a href="javascript:;">我的地址>></a></h3>
                <div class="address_list">
                    <ul>
                        <li class="select">
                            <div class="ads_box">
                                <label for="ads-1">
                                    <input type="radio" id="ads-1"  name="address" checked>
                                    <span class="user_ads">福建省福州市鼓楼区**小区13栋8层203</span>
                                    <span class="name">（王晶晶&nbsp;收）</span>
                                    <span class="tel">1830598475</span>
                                    <span id="default-ads">默认地址</span>
                                </label>
                            </div>
                        </li>
                        <li>
                            <div class="ads_box">
                                <label for="ads-2">
                                    <input type="radio" id="ads-2"  name="address">
                                    <span class="user_ads">福建省福州市鼓楼区**小区13栋8层203</span>
                                    <span class="name">（王晶晶&nbsp;收）</span>
                                    <span class="tel">1830598475</span>
                                </label>
                            </div>
                        </li>
                        <li>
                            <div class="ads_box">
                                <label for="ads-3">
                                    <input type="radio" id="ads-3"  name="address">
                                    <span class="user_ads">福建省福州市鼓楼区**小区13栋8层203</span>
                                    <span class="name">（王晶晶&nbsp;收）</span>
                                    <span class="tel">1830598475</span>
                                </label>
                            </div>
                        </li>
                    </ul>
                    <a href="javascript:;" id="add-new-ads">添加收货地址</a>
                </div>
            </div>
            <div class="confirm_warp box">
                <h3>订单信息确认</h3>
                <div id="confirm-pro-list" class="pro_table">
                    <table>
                        <thead>
                            <tr>
                                <th width="55%">商品</th>
                                <th width="15%">单价</th>
                                <th width="15%">数量</th>
                                <th width="15%">小计</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="prp_detail">
                                        <div class="img_box">
                                            <img src="../images/detail/detial_img.png">
                                        </div>
                                        <p>我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称</p>
                                    </div>
                                </td>
                                <td><strong>1000</strong></td>
                                <td>
                                    2
                                </td>
                                <td>
                                    <strong class="red">￥1000</strong>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="prp_detail">
                                        <div class="img_box">
                                            <img src="../images/detail/detial_img.png">
                                        </div>
                                        <p>我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称我是商品名称</p>
                                    </div>
                                </td>
                                <td><strong>1000</strong></td>
                                <td>
                                    2
                                </td>
                                <td>
                                    <strong class="red">￥1000</strong>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="confirm_ads_warp">
                        收货地址：<span id="confirm-ads-name"></span>

                    </div>
                </div>
            </div>
            <div class="payment_warp box clear">
                <div class="method">
                    <label for="pay-wechat"><input type="radio" id="pay-wechat" name="payment" checked><img src="${pageContext.request.contextPath}/resources/images/cart/wechat.png"></label>
                </div>
                <div class="method">
                    <label for="pay-alipay"><input type="radio" id="pay-alipay" name="payment"><img src="${pageContext.request.contextPath}/resources/images/cart/alipay.png"></label>
                </div>
                
                <button class="right"> 提交订单</button>
                <span class="right">应付总额：<strong class="red">￥390988</strong></span>
            </div>
        </div>
    </section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
</body>
</html>
	<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<script>
shop.opptBuyNum();
$(function(){
    $('input[name="address"]').on('click',function(){
        $(this).parents('li').addClass('select').siblings().removeClass('select');
    });
    $(".pro_table .check_all").on('click',function(){
        $checkOne = $(this).parents('table').find('.check_one');
        
        if($(this).prop('checked')){
            $checkOne.prop('checked','checked') ;
            $("#has-check").prop('checked','checked');
            $(".check_num").html($checkOne.length);  
        }else{
            $checkOne.removeProp('checked');
            $("#has-check").removeProp('checked');
            $(".check_num").html('0');  
        }
    })
    $(".pro_table .check_one").on('click',function(){
        $checkOne = $(this).parents('table').find('.check_one');
        var num = 0;
        $.each($checkOne,function(i,v){
            console.log(v)
            if($(v).prop('checked')){
                num++;
            }
        })
        $("#has-check").prop('checked','checked');
        $(".check_num").html(num);
    })
})
</script>
    