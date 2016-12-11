<%@ page contentType="text/html;charset=UTF-8"%>
<style>
.shop_header .header_left li img {
    margin-right: 2px;
    margin-left:10px;
}
</style>
<header class="shop_header">
   <section class="container">
         <div class="left">
         	<shiro:authenticated>
            	<span>欢迎<shiro:principal property="name"/></span>
         	</shiro:authenticated>
            <shiro:notAuthenticated>
				<a class="red" href="${pageContext.request.contextPath}/loginUI">请登录</a>
				或
				<a href="${pageContext.request.contextPath}/registerUI">立即注册</a>
			</shiro:notAuthenticated>
        </div>
        <div class="header_left right">
			<ul>
				<shiro:hasRole name="admin">  
					<li><a href="${pageContext.request.contextPath}/a/product/listUI"><span>管理员后台</span></a></li>
				</shiro:hasRole> 
				<shiro:authenticated>
				<shiro:lacksRole name="admin">
					<li><a href="${pageContext.request.contextPath}/m/center"><img src="${pageContext.request.contextPath}/resources/images/user.png"><span>个人中心</span></a></li>
				</shiro:lacksRole>
				<li><a href="${pageContext.request.contextPath}/c/carUI"><img src="${pageContext.request.contextPath}/resources/images/shopping_cart.png"><span>购物车</span></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out" style="margin-left: 9px;"></span><span>退出</span></a></li>
				</shiro:authenticated>
			</ul>
        </div>
   </section>
</header>
<script>
    $(function(){
        $(".header_left li a").hover(function(){
            var img_url = $(this).find('img').attr('src').replace('-hover','').replace('.png','-hover.png');
            $(this).find('img').attr('src',img_url)
        },function(){
            var img_url = $(this).find('img').attr('src').replace('-hover.png','.png');
            $(this).find('img').attr('src',img_url)
        })
    })
</script>