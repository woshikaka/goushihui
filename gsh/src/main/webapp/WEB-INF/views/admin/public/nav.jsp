<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="container-fluid">
		<div class="navbar-header">                        
			<a class="navbar-brand" href="#">购食汇管理平台</a> 
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="${pageContext.request.contextPath}/homePage" target="_blank">首页</a></li>
				<li class="dropdown "><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-expanded="true"> 
				<span class="glyphicon glyphicon-user "></span>
				&nbsp;
				<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<!-- <li><a href="#">修改密码</a></li> -->
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/logout">logout <span class="glyphicon glyphicon-log-out"></span></a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li>
					<a href="#" name="oneLevel">产品管理<i class="glyphicon glyphicon-chevron-left" style="float: right;"></i></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/a/product/listUI">产品列表</a></li>
						<li><a href="${pageContext.request.contextPath}/a/product/addUI">添加产品</a></li>
					</ul>
				</li>
				<li>
					<a href="#" name="oneLevel">首页广告管理<i class="glyphicon glyphicon-chevron-left" style="float: right;"></i></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/a/ad/adListUI">轮播广告列表</a></li>
						<li><a href="${pageContext.request.contextPath}/a/ad/barAdListUI">长条广告列表</a></li>
						<li><a href="${pageContext.request.contextPath}/a/ad/addUI">添加广告</a></li>
					</ul>
				</li>
				<li>
					<a href="#" name="oneLevel">首页产品top管理<i class="glyphicon glyphicon-chevron-left" style="float: right;"></i></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/a/top/topList/1">副食零食top8</a></li>
						<li><a href="${pageContext.request.contextPath}/a/top/topList/2">酒水饮料top8</a></li>
						<li><a href="${pageContext.request.contextPath}/a/top/topList/3">粮油调料top8</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>

<script type="text/javascript">
	$(function() {
		var url = window.location;
		var element = $('ul .nav a').filter(function() {
			return this.href == url || url.href.indexOf(this.href) == 0;
		}).addClass('active').parent().parent().addClass('in').parent();
		if (element.is('li')) {
			element.addClass('active').find("i").attr("class","glyphicon glyphicon-chevron-down");
		}
		$('#side-menu').metisMenu();
		
		//动态切换三角形形态 
		$("a[name='oneLevel']").on("click", function(){
			var $i = $(this).find("i");
			if($i.hasClass("glyphicon glyphicon-chevron-left")){
				$i.attr("class","glyphicon glyphicon-chevron-down");
			}else{
				$i.attr("class","glyphicon glyphicon-chevron-left");
			}
			
			$("a i").not($i).attr("class","glyphicon glyphicon-chevron-left");
		});
		
		//全局confirm弹窗
		$(".confirm").confirm();
	});
</script>