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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
<style>
.menu_wrap .left_nav .pannel_box .pannel_item.show {
    width: 800px;
    height: 400px;
}

.title{
	width:200px; 
	white-space:nowrap; 
	overflow:hidden; 
	text-overflow:ellipsis;
}
</style>
</head>
<body ng-app="searchApp" ng-controller="searchCtrl">
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
        <section class="menu_wrap">
        <div class="top_nav">
            <div class="container">
                <div class="classify_title"><strong>商品分类</strong><img src="${pageContext.request.contextPath}/resources/images/index/menu_list.png"></div>
                <ul class="top_nav_menu clear">
					<li><a href="${pageContext.request.contextPath}/homePage">首页</a></li>
					<li><a href="javascript:;">团购商品</a></li>
					<li><a href="javascript:;">特价促销</a></li>
					<li><a href="javascript:;">新品上架</a></li>
					<li><a href="javascript:;">批发规则</a></li>
					<li><a href="javascript:;">关于我们</a></li>
                </ul>
            </div>
        </div>
        <div class="clear menu_nav">
            <div class="left_nav container">
                <div class="nav_menu_box">
                    	<ul>
							<li ng-click=checkedTypeMethod(null,secType,null) ng-repeat="produtType in produtTypes" class="nav_item" ng-mouseover="tempChecked(produtType)"><a href="javascript:;">{{produtType.name}}</a><i class="icon-angle-right"></i></li>
						</ul>
                </div>
                <div class="pannel_box">
                    <ul>
						<li ng-repeat="secTypeArray in secTypesForTwoDimensionalArray" class="pannel_item">
							<div class="item_content">
								<ul>
										<li class="sort_li" ng-repeat="secType in secTypeArray">
											<a href="javascript:;" ng-click="checkedTypeMethod(null,secType,null)"><h4>{{secType.name}}</h4></a>
											<div>
												<a ng-repeat="thirdType in secType.thirdTypes" href="javascript:;" ng-click="checkedTypeMethod(null,secType,thirdType)">{{thirdType.name}}</a>
											</div>
										</li>
								</ul>
							</div>
						</li>
					</ul>
                </div>
            </div>
        </div>
    </section>
    <div class="container search_list">
        <div class="search_location">
        	您搜索的<span class="red">{{checkedType.productTypeName}} {{checkedType.secTypeName?'&gt; '+checkedType.secTypeName:''}} {{checkedType.thirdTypeName?'&gt; '+checkedType.thirdTypeName:''}}</span>
           	 共有 <span class="red" ng-bind="pageBean.totalElements"></span> 件商品：
        </div>
        <div class="sort_warp">
            <ul><li ng-class="selected1?'select':''" ng-click="checkedTab(1)">综合排序</li><li ng-class="selected2?'select':''" ng-click="checkedTab(2)">销量从高到低</li><li ng-class="selected3?'select':''" ng-click="checkedTab(3)">价格从低到高</li></ul>
        </div>
        <section class="best_sellers_warp page_box">
            <div class="page_list">
                <ul class="clear">
                	<li ng-repeat="bean in pageBean.content" title="{{bean.name}}">
                        <div class="goods_img"><a href="${pageContext.request.contextPath}/product/detail/1"><img src="/upload{{bean.image}}"></a></div>
                        <p class="goods_name title" ng-bind="bean.name"></p>
                        <p class="goods_volume">成交{{bean.sellCount}}单</p>
                        <div class="goods_price">
                        	<shiro:notAuthenticated>
								<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
							</shiro:notAuthenticated>
							<shiro:authenticated>
								<span class="new_price">{{bean.price}}元</span>
								<span class="old_price">{{bean.marketPrice}}元</span>
							</shiro:authenticated>
                        </div>
                    </li>
                </ul>
            </div>
			<span ng-show={{$scope.totalPages==0}}>没有符合的商品！</span>
			<div class="paging_warp clear">
                <div class="paging_info left"> 每页 <i class="red">20</i>条</div>
				<div id="paging" style="float: right;"></div>
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
<script src="${pageContext.request.contextPath}/resources/js/angular1.4.6.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
	var app = angular.module('searchApp', []);
	app.controller('searchCtrl', function($scope, $http,$location,$window) {
		var laypage = layui.laypage;
		$scope.produtTypes = [];
		$scope.secTypesForTwoDimensionalArray = [];
		
		$scope.tempCheckedFirType = {productTypeId:"",productTypeName:""};
		$scope.checkedType = {productTypeId:"",productTypeName:"",secTypeId:"",secTypeName:"",thirdTypeId:"",thirdTypeName:""};
		$http.post("${pageContext.request.contextPath}/common/getAllType",null).success(function(response) {
			$scope.produtTypes = response.data;
			$scope.produtTypes.forEach(function(item) {
				$scope.secTypesForTwoDimensionalArray.push(item.productSecTypes);
			});
        });
		
		$scope.checkedTypeMethod = function(firstType,secType,thirdType){
			$scope.pageParam={"currPageNo":1};
			$scope.checkedType = {};
			
			if(firstType){
				$scope.checkedType.productTypeId = firstType.id;
				$scope.checkedType.productTypeName = firstType.name;
			}
			
			if(secType){
				$scope.checkedType.secTypeId = secType.id;
				$scope.checkedType.secTypeName = secType.name;
				$scope.pageParam.secTypeId = secType.id;
			}
			
			if(thirdType){
				$scope.checkedType.thirdTypeId = thirdType.id;
				$scope.checkedType.thirdTypeName = thirdType.name;
				$scope.pageParam.thirdTypeId = thirdType.id;
			}
			
			$scope.checkedType.productTypeId = $scope.tempCheckedFirType.productTypeId;
			$scope.checkedType.productTypeName = $scope.tempCheckedFirType.productTypeName;
			
			$scope.navHide();
			
			$scope.initLaypage();
			$scope.pageParam.currPageNo=1;
			$scope.pageParam.productTypeId = $scope.checkedType.productTypeId;
			pageRequest();
		}
		
		$scope.navHide = function(){
			angular.element(".menu_nav").hide();
		}
		
		$scope.tempChecked = function(productType){
			$scope.tempCheckedFirType.productTypeId = productType.id; 
			$scope.tempCheckedFirType.productTypeName = productType.name; 
		}

		$scope.selected1 = true;
		$scope.selected2 = false;
		$scope.selected3 = false;
		$scope.checkedTab = function(flag){
			if(1==flag){
				$scope.selected1 = true;
				$scope.selected2 = false;
				$scope.selected3 = false;	
				$scope.pageParam.selectedTab = 1;
			}else if(2==flag){
				$scope.selected1 = false;
				$scope.selected2 = true;
				$scope.selected3 = false;
				$scope.pageParam.selectedTab = 2;
			}else if(3==flag){
				$scope.selected1 = false;
				$scope.selected2 = false;
				$scope.selected3 = true;
				$scope.pageParam.selectedTab = 3;
			}else{
				
			}
			
			$scope.initLaypage();
			$scope.pageParam.currPageNo=1;
			pageRequest();
		}
		
		$scope.pageParam={"currPageNo":1};
		$scope.totalPages=0;
		$scope.pageBean={};
		$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
			$scope.pageBean = response.data;
			$scope.totalPages = response.data.totalPages;
			$scope.initLaypage();		
	    });
		
		$scope.initLaypage = function(){
			laypage({
			    cont: 'paging',
			    pages: $scope.totalPages,
			    skin: '#1E9FFF',
				skip: false,
				jump: function(obj,first){
					if(!first){
						$scope.pageParam.currPageNo=obj.curr;
						pageRequest();
					}
				}
			});			
		}
	
		
		function pageRequest(){
			$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.pageBean = response.data;
				$scope.totalPages = response.data.totalPages;
		    });
		}
		
	});

    $(function(){
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
    