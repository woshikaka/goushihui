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
<style>
.menu_wrap .left_nav .pannel_box .pannel_item.show {
    width: 800px;
    height: 400px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/public/head.jsp"%>
	<%@ include file="/WEB-INF/views/admin/public/head1.jsp"%>
        <section class="menu_wrap">
        <div class="top_nav">
            <div class="container">
                <div class="classify_title"><strong>商品分类</strong><img src="${pageContext.request.contextPath}/resources/images/index/menu_list.png"></div>
                <ul class="top_nav_menu clear">
                    <!-- <li><a href="javascript:;">最新上架</a></li>
                    <li><a href="javascript:;">关于我们</a></li> -->
                </ul>
            </div>
        </div>
        <div class=" clear menu_nav">
            <div class="left_nav container">
                <div class="nav_menu_box">
                    	<ul>
							<c:forEach items="${productTypes}" var="bean">
								<li class="nav_item"><a onclick="productTypeSearch(${bean.id},'${bean.name}')" href="javascript:;">${bean.name}</a><i class="icon-angle-right"></i></li>
							</c:forEach>
						</ul>
                </div>
                <div class="pannel_box">
                    <ul>
							<c:forEach items="${productTypes}" var="productType">
								<li class="pannel_item">
								<div class="item_content">
									<ul>
										<c:forEach items="${productType.productSecTypes}" var="secType">
											<li class="sort_li">
												<a href="javascript:;" onclick="productSecTypeSearch(${productType.id},'${productType.name}',${secType.id},'${secType.name}')"><h4>${secType.name}</h4></a>
												<div>
													<c:forEach items="${secType.thirdTypes}" var="thirdType">
														<a href="javascript:;" onclick="productThirdTypeSearch(${productType.id},'${productType.name}',${secType.id},'${secType.name}',${thirdType.id},'${thirdType.name}')">${thirdType.name}</a>
													</c:forEach>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
							</c:forEach>
						</ul>
                </div>
            </div>
        </div>
    </section>
    <div class="container search_list">
        <div class="search_location">
        	<%-- <c:if test="${dto.productTypeId != null }">
           		
        		 您搜索的“<span class="red">${dto.productTypeName}
        		 <c:if test="${dto.secTypeId != null }">
        			&gt; ${dto.secTypeName}
        		 </c:if>
        		 </span>”，
        	</c:if> --%>
        	
			<c:choose>
				<c:when test="${dto.thirdTypeId != null}">
					您搜索的“<span class="red">${dto.productTypeName} &gt; ${dto.secTypeName} &gt; ${dto.thirdTypeName}</span>”，
				</c:when>
				<c:when test="${dto.secTypeId != null}">
					您搜索的“<span class="red">${dto.productTypeName} &gt; ${dto.secTypeName}</span>”，
				</c:when>
				<c:when test="${dto.productTypeId != null}">
					您搜索的“<span class="red">${dto.productTypeName}</span>”，
				</c:when>
			</c:choose>        	
           	 共有 <span class="red">${pageBean.recordCount}</span> 件商品：
        </div>
        <div class="sort_warp">
            <!-- <h4>综合排序</h4> -->
            <ul><li id="defaultSort" class="select" onclick="defaultSort()">综合排序</li><li id="salesSort" onclick="salesHigh2Low()">销量从高到低</li><li id="priceSort" onclick="priceLow2High()">价格从低到高</li></ul>
        </div>
        <section class="best_sellers_warp page_box">
            <div class="page_list">
                <ul class="clear">
                	<c:forEach items="${pageBean.recordList}" var="bean">
                    <li>
                        <div class="goods_img"><a href="${pageContext.request.contextPath}/product/detail/${bean.id}"><img src="/upload${bean.image}"></a></div>
                        <p class="goods_name">${bean.name}</p>
                        <p class="goods_volume">成交${bean.sellCount}单</p>
                        <div class="goods_price">
                        	<shiro:notAuthenticated>
								<a class="red" href="${pageContext.request.contextPath}/loginUI">登录</a>查看专属价格
							</shiro:notAuthenticated>
							<shiro:authenticated>
								<span class="new_price">${bean.price}元</span>
								<span class="old_price">${bean.marketPrice}元</span>
							</shiro:authenticated>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <c:if test="${empty pageBean.recordList}">
				没有符合的数据！
			</c:if>
           <%--  <div class="paging_warp clear">
                <div class="paging_info left"> 每页 <i class="red">${pageBean.pageSize}</i>条</div>
                <div class="paging_num right">
                    <a href="javascript:void(0);" class="page-prev" page="1"><span>上一页</span></a>
                    <a href="javascript:void(0);" class="page-span" page="1">1</a>  <span class="page-selected">2</span>  
                    <a href="javascript:void(0);" class="page-span" page="3">3</a>  
                    <a href="javascript:void(0);" class="page-next" page="3"><span>下一页</span></a>
                    共3页到第<input type="text" value="1">页
                    <span class="confirm btn btn-primary">确定</span>
                </div>
            </div> --%>
            <c:if test="${!empty pageBean.recordList}">
            <div class="paging_warp clear">
                <div class="paging_info left"> 每页 <i class="red">${pageBean.pageSize}</i>条</div>
                <div class="paging_num right">
                    <a href="javascript:void(0);" class="page-prev" onclick="gotoPage('${pageBean.currPageNo-1}')"><span>上一页</span></a>
                    <c:forEach begin="${pageBean.beginPageIndex}" end="${pageBean.endPageIndex}" var="pageNo">
					<c:choose>
						<c:when test="${pageNo == pageBean.currPageNo}">
							<%-- <li class="active"><a href="#" onclick="gotoPage(${pageNo})">${pageNo}</a></li> --%>
							<span class="page-selected">${pageNo}</span>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" class="page-span" onclick="gotoPage(${pageNo})">${pageNo}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
                    <a href="javascript:void(0);" class="page-next" onclick="gotoPage('${pageBean.currPageNo+1}')"><span>下一页</span></a>
                    共${pageBean.pageCount}页<!-- 到第<input type="text" value="1">页
                    <span class="confirm btn btn-primary">确定</span> -->
                </div>
            </div>
            </c:if>
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
<script>
    $(function(){
        $(".sort_warp li").on('click',function(){
            $(this).addClass('select').siblings().removeClass('select');
        })

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
        
        
        /* <div class="sort_warp">
        <!-- <h4>综合排序</h4> -->
        <ul><li id="defaultSort" class="select">综合排序</li><li id="salesSort" onclick="salesHigh2Low()">销量从高到低</li><li id="priceSort">价格从低到高</li><li id="shangjiaSort">上架时间</li></ul>
    </div> */
    
    	
        var salesHigh2Low = '${dto.salesHigh2Low}';
        var priceLow2High = '${dto.priceLow2High}';
        var defaultSort = '${dto.defaultSort}';
    	if(salesHigh2Low){
    		$("div .sort_warp li").removeClass("select");
    		$("#salesSort").addClass("select");
    	}
    	if(priceLow2High){
    		$("div .sort_warp li").removeClass("select");
    		$("#priceSort").addClass("select");
    	}
    	if(defaultSort){
    		$("div .sort_warp li").removeClass("select");
    		$("#defaultSort").addClass("select");
    	}
        
    })
    
    function defaultSort(){
    	clear1();
    	$("#searchForm").append("<input type='hidden' name='defaultSort' value='true'>");
    	$("#searchForm").submit();
    }
    
    function salesHigh2Low(){
    	clear1();
    	$("#searchForm").append("<input type='hidden' name='salesHigh2Low' value='true'>");
    	$("#searchForm").submit();
    }
    
    function priceLow2High(){
    	clear1();
    	$("#searchForm").append("<input type='hidden' name='priceLow2High' value='true'>");
    	$("#searchForm").submit();
    }
    
    function clear1(){
    	$("#searchForm").find("input[name='salesHigh2Low']").remove();
    	$("#searchForm").find("input[name='priceLow2High']").remove();
    	$("#searchForm").find("input[name='defaultSort']").remove();
    }
    
    function clear(){
    	$("#searchForm").find("input[name='productTypeId']").remove();
    	$("#searchForm").find("input[name='productTypeName']").remove();
    	
    	$("#searchForm").find("input[name='secTypeId']").remove();
    	$("#searchForm").find("input[name='secTypeName']").remove();
    	
    	$("#searchForm").find("input[name='thirdTypeId']").remove();
    	$("#searchForm").find("input[name='thirdTypeName']").remove();
    }
    
    function productTypeSearch(productTypeId,productTypeName){
    	clear();
    	$("#searchForm").append("<input type='hidden' name='productTypeId' value='"+productTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='productTypeName' value='"+productTypeName+"'>");
    	$("#searchForm").submit();
    }
    function productSecTypeSearch(productTypeId,productTypeName,secTypeId,secTypeName){
    	clear();
    	$("#searchForm").append("<input type='hidden' name='productTypeId' value='"+productTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='productTypeName' value='"+productTypeName+"'>");
    	$("#searchForm").append("<input type='hidden' name='secTypeId' value='"+secTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='secTypeName' value='"+secTypeName+"'>");
    	$("#searchForm").submit();
    }
    function productThirdTypeSearch(productTypeId,productTypeName,secTypeId,secTypeName,thirdTypeId,thirdTypeName){
    	clear();
    	$("#searchForm").append("<input type='hidden' name='productTypeId' value='"+productTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='productTypeName' value='"+productTypeName+"'>");
    	$("#searchForm").append("<input type='hidden' name='secTypeId' value='"+secTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='secTypeName' value='"+secTypeName+"'>");
    	$("#searchForm").append("<input type='hidden' name='thirdTypeId' value='"+thirdTypeId+"'>");
    	$("#searchForm").append("<input type='hidden' name='thirdTypeName' value='"+thirdTypeName+"'>");
    	$("#searchForm").submit();
    }
    
    function gotoPage(pageNo){
    	if(pageNo==0){
    		return;
    	}
    	var pageCount = ${pageBean.pageCount}
    	if(pageNo==pageCount+1){
    		return;
    	}
		$("#searchForm").append("<input type='hidden' name='currPageNo' value='" + pageNo +"'>").submit();
	}
</script>
</html>
    