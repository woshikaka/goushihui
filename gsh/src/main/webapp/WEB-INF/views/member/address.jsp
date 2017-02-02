<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<html>
<head>
<title></title>
<!-- <link rel="icon" href="favicon.ico"> -->
<link type="text/css" rel="stylesheet"
	href="/gsh/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsh/resources/css/col.css" />
<link type="text/css" rel="stylesheet"
	href="/gsh/resources/css/shop.css" />
<!--  <script src="js/jquery-1.11.3.min.js"></script> -->
<!--  <script src="js/vue.min.js"></script>
    <script src="js/shop.js"></script> -->
<script src="${pageContext.request.contextPath}/resources/js/shop.js"></script>
<style type="text/css">
.num_desc {
	margin-top: 10px;
	margin-bottom: 4px
}
.address{
	background-color: #fff;
	padding:20px
}
.address_list{
	
}

.address_iteam{
	margin: 0 0 10px;
	margin-top: 10px;
	border: 2px solid #e6e6e6;
}
.address_label{
	float: left;
    width: 70px;
    color: #999;
    text-align: right;
    margin-left: 20px;
}
.del-btn{
    width: 13px;
    height: 13px;
	background-image: url("${pageContext.request.contextPath}/resources/images/close.png");
	display:block;
	float: right;
	margin:10px 10px 0px 0px;
}
.address_operate{
	float: right;
	color: #005ea7;
	margin-right: 20px
}
.address_operate a:hover {
    color: #C81623;
}
</style>
</head>
<body>
	<%-- <header class="shop_header">
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
    </header> --%>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<section class="center_header">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
			<ul class="center_header_nav clear">
				<li><a href="javascript:;">首页</a></li>
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
					<li class="select"><a href="${pageContext.request.contextPath}/m/center">账户浏览</a></li>
					<li class="select"><a href="javascript:;">我的订单</a></li>
					<li class="select"><a href="javascript:;" style="color: #f04848;">我的地址</a></li>
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
				<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
				<div class="address">
					<button class="btn btn-primary" style="height:30px" data-toggle="modal" data-target="#addModal"">新增收货地址</button><span style="color: #999;margin-left:20px">您已创建${fn:length(address)}收货地址，最多可创建5个</span>
					<div class="address_list">
						<c:forEach items="${address}" var="bean">
							<div class="address_iteam">
								<div>
									<c:if test="${bean.isDefaut}">
										<span style="background-color: #ffaa45;color: #fff;font-weight: 400;padding: 2px;">默认地址</span>
									</c:if>
									<a class="del-btn" href="${pageContext.request.contextPath}/m/deleteAddress/${bean.id}" onclick="return confirm('确定要删除这个收货地址吗？${bean.detailed}')"></a>
								</div>
								<span class="address_label">收货人：</span><div>${bean.contact}</div>
								<span class="address_label">收货地址：</span><div>${bean.detailed}</div>
								<span class="address_label">手机：</span>
								<div>${bean.mobile}
									<c:if test="${!bean.isDefaut}">
										<span class="address_operate"><a href="${pageContext.request.contextPath}/m/setDefaut/${bean.id}">设为默认</a></span>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/views/admin/public/foot.jsp"%>
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="exampleModalLabel">新增收货地址</h4>
		      </div>
		      <div class="modal-body">
		          <form class="form-horizontal" action="${pageContext.request.contextPath}/m/addAddress" method="post">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">收货人</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="contact">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">联系手机</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="mobile">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">详细地址</label>
					    <div class="col-sm-10">
					      <input type="text" name="detailed" class="form-control">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <div class="checkbox">
					        <label>
					          <input type="checkbox" name="isDefaut">默认地址
					        </label>
					      </div>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-default">确认</button>
					    </div>
					  </div>
					</form>
		      </div>
		    </div>
		  </div>
	</div>
	<script>
	function add(){
		
	}
</script>
</body>
</html>