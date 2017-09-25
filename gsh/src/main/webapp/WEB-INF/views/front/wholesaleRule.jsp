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

.content_title{
	border-bottom: 1px solid #CCC;
	font: bold 18px/50px microsoft yahei,Helvetica,Tahoma,Arial,sans-serif;
    margin: 10px 0;
}

.content_title span{
	border-bottom: 3px solid #F30;
	display: inline-block
}
.search_list p{
	line-height: 30px;
}
</style>
</head>
<body ng-app="searchApp" ng-controller="searchCtrl" ng-init="init()">
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<section class="rect_wrap">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
			<a href="${pageContext.request.contextPath}/homePage"><img id="logoImg" src="${pageContext.request.contextPath}/resources/images/logo.png" style="margin-top:-35px;width: 160px;height: 100px"/></a>
			<div class="search_wrap">
				<div class="search_bd">
					<div class="search_box">
						<div class="search_icon">
							<i class="icon-search"></i>
						</div>
							<input type="text" ng-model="keyword" class="search_input">
							<button type="button" ng-click="search()" class="search_btn right" >搜索</button>
					</div>
				</div>
			</div>
			<div class="shopping_cart right">
				<a href="${pageContext.request.contextPath}/c/carUI" style="color: #fff"><img src="${pageContext.request.contextPath}/resources/images/index/cart.png" class="cart_icon"> 我的购物车 <i class="shop_num" ng-bind="carCnt"></i></a>
			</div>
		</div>
	</section>
        <section class="menu_wrap">
        <div class="top_nav">
            <div class="container">
                <div class="classify_title"><strong>商品分类</strong><img src="${pageContext.request.contextPath}/resources/images/index/menu_list.png"></div>
                <ul class="top_nav_menu clear">
					<li><a href="${pageContext.request.contextPath}/homePage">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/searchUI?a=1">团购商品</a></li>
					<li><a href="${pageContext.request.contextPath}/searchUI?a=2">特价促销</a></li>
					<li><a href="${pageContext.request.contextPath}/searchUI?a=3">新品上架</a></li>
					<li><a href="${pageContext.request.contextPath}/wholesaleRule">批发规则</a></li>
					<li><a href="${pageContext.request.contextPath}/aboutMe">关于我们</a></li>
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
    	<div class="content_title">
    		<span>批发规则</span>
    	</div>
    	${text}
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
			$scope.checkedType = {};
			$scope.keyword = null;
			$scope.pageParam.keyword = null;
			$scope.pageParam.activityType = null;
			$scope.showKeyword = "";
			
			if(firstType){
				$scope.checkedType.productTypeId = firstType.id;
				$scope.checkedType.productTypeName = firstType.name;
			}else{
				$scope.checkedType.productTypeId = null;
			}
			
			if(secType){
				$scope.checkedType.secTypeId = secType.id;
				$scope.checkedType.secTypeName = secType.name;
				$scope.pageParam.secTypeId = secType.id;
			}else{
				$scope.pageParam.secTypeId = null;
			}
			
			if(thirdType){
				$scope.checkedType.thirdTypeId = thirdType.id;
				$scope.checkedType.thirdTypeName = thirdType.name;
				$scope.pageParam.thirdTypeId = thirdType.id;
			}else{
				$scope.pageParam.thirdTypeId = null;
			}
			
			if(!$scope.checkedType.productTypeId){
				$scope.checkedType.productTypeId = $scope.tempCheckedFirType.productTypeId;
				$scope.checkedType.productTypeName = $scope.tempCheckedFirType.productTypeName;
			}
			
			$scope.navHide();
			
			$scope.pageParam.currPageNo=1;
			$scope.pageParam.productTypeId = $scope.checkedType.productTypeId;
			//pageRequest();
			//$scope.initLaypage();
			$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.pageBean = response.data;
				$scope.totalPages = response.data.totalPages;
				$scope.initLaypage();
		    });
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
		
		$scope.keyword = "";
		$scope.search = function(){
			$scope.pageParam={"currPageNo":1};
			$scope.checkedType=null;
			$scope.pageParam.keyword = $scope.keyword;
			$scope.showKeyword = angular.copy($scope.keyword);
			
			$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.pageBean = response.data;
				$scope.totalPages = response.data.totalPages;
				$scope.initLaypage();
		    });
		}
		
		$scope.activitySearch = function(activityType){
			$scope.pageParam={"currPageNo":1};
			$scope.checkedType=null;
			$scope.keyword = "";
			
			if(activityType==1){
				$scope.pageParam.activityType = 1;
				$scope.showKeyword = "团购商品";
			}else if(activityType==2){
				$scope.pageParam.activityType = activityType;
				$scope.showKeyword = "特价促销";
			}else if(activityType==3){
				$scope.pageParam.activityType = activityType;
				$scope.showKeyword = "新品上架";
			}
			
			$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.pageBean = response.data;
				$scope.totalPages = response.data.totalPages;
				$scope.initLaypage();
		    });
		}
		
		$http.post("${pageContext.request.contextPath}/common/getCarCnt",angular.toJson($scope.pageParam)).success(function(response) {
			$scope.carCnt = response.data;
	    });
		
		
		$scope.init = function(){
			$scope.pageParam = {currPageNo:1};
			$scope.totalPages=0;
			$scope.pageBean={};
			
			var selectedTab = getParameterByName("selectedTab",null);
			if(selectedTab){
				$scope.selected1 = false;
				$scope.selected2 = true;
				$scope.selected3 = false;	
				$scope.pageParam.selectedTab = 2;
			}
			
			var firstTypeId = getParameterByName("ti",null);
			if(firstTypeId){
				var firstType = {};
				firstType.id = firstTypeId;
				$scope.pageParam.productTypeId = firstTypeId;
				if(firstTypeId==1){
					$scope.checkedType.productTypeName = "进口商品";
				}else if(firstTypeId==2){
					$scope.checkedType.productTypeName = "副食零食";
				}else if(firstTypeId==3){
					$scope.checkedType.productTypeName = "酒水饮料";
				}else if(firstTypeId==4){
					$scope.checkedType.productTypeName = "粮油调料";
				}
			}
			
			var secTypeId = getParameterByName("sti",null);
			var secTypeName = getParameterByName("stn",null);
			if(secTypeId){
				$scope.checkedType.secTypeId = secTypeId;
				$scope.checkedType.secTypeName = secTypeName;
				$scope.pageParam.secTypeId = secTypeId;
			}
			
			var thirdTypeId = getParameterByName("tti",null);
			var thirdTypeName = getParameterByName("ttn",null);
			if(thirdTypeId){
				$scope.checkedType.thirdTypeId = thirdTypeId;
				$scope.checkedType.thirdTypeName = thirdTypeName;
				$scope.pageParam.thirdTypeId = thirdTypeId;
			}
			
			var activityType = getParameterByName("a",null);
			if(activityType==1){
				$scope.pageParam.activityType = 1;
				$scope.showKeyword = "团购商品";
			}else if(activityType==2){
				$scope.pageParam.activityType = activityType;
				$scope.showKeyword = "特价促销";
			}else if(activityType==3){
				$scope.pageParam.activityType = activityType;
				$scope.showKeyword = "新品上架";
			}
			
			var keyword = '${keyword}';
			if(keyword){
				$scope.showKeyword = keyword;
				$scope.pageParam.keyword = keyword;
			}	
			
			$http.post("${pageContext.request.contextPath}/search",angular.toJson($scope.pageParam)).success(function(response) {
				$scope.pageBean = response.data;
				$scope.totalPages = response.data.totalPages;
				$scope.initLaypage();		
		    });
		}
		
		function getParameterByName(name, url) {
		    if (!url) url = window.location.href;
		    name = name.replace(/[\[\]]/g, "\\$&");
		    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
		        results = regex.exec(url);
		    if (!results) return null;
		    if (!results[2]) return '';
		    return decodeURIComponent(results[2].replace(/\+/g, " "));
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