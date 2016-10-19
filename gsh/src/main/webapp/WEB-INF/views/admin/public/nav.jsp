<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="container-fluid">
		<div class="navbar-header">                        
			<a class="navbar-brand" href="#"> <span class="glyphicon glyphicon-bookmark"></a> 
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
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
						<li><a href="productUSAList">产品列表</a></li>
						<li><a href="productJPNList">添加产品</a></li>
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
		//$(".confirm").confirm();
	});
</script>