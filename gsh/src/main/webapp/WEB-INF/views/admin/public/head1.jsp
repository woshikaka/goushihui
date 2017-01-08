<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<style>
/* .rect_wrap .search_wrap .search_bd .search_box .search_icon {
	padding: 8px 14px;
} */
.shop_header {
    background-color: #ddd;
}
</style>
<section class="rect_wrap">
		<div class="container">
			<!-- <div class="logo_box"></div> -->
			<a href="${pageContext.request.contextPath}/homePage"><img src="${pageContext.request.contextPath}/resources/images/logo.png" style="margin-top:-35px;"/></a>
			<div class="search_wrap">
				<div class="search_bd">
					<div class="search_box">
						<div class="search_icon">
							<i class="icon-search"></i>
						</div>
						<form id="searchForm" action="${pageContext.request.contextPath}/search" method="post" onsubmit="return searchCheck()">
							<input id="keywordInput" type="text" name="keyword" value="${dto.keyword}" class="search_input">
							<button type="submit" class="search_btn right">搜索</button>
							<c:if test="${dto.productTypeId != null}">
								<input type="hidden" name="productTypeId" value="${dto.productTypeId}">
								<input type="hidden" name="productTypeName" value="${dto.productTypeName}">
							</c:if>
							<c:if test="${dto.secTypeId != null}">
								<input type="hidden" name="secTypeId" value="${dto.secTypeId}">
								<input type="hidden" name="secTypeName" value="${dto.secTypeName}">
							</c:if>
							<c:if test="${dto.thirdTypeId != null}">
								<input type="hidden" name="thirdTypeId" value="${dto.thirdTypeId}">
								<input type="hidden" name="thirdTypeName" value="${dto.thirdTypeName}">
							</c:if>
							<c:if test="${dto.salesHigh2Low != null}">
								<input type="hidden" name="salesHigh2Low" value="true">
							</c:if>
							<c:if test="${dto.priceLow2High != null}">
								<input type="hidden" name="priceLow2High" value="true">
							</c:if>
							<c:if test="${dto.defaultSort != null}">
								<input type="hidden" name="defaultSort" value="true">
							</c:if>
						</form>
					</div>
				</div>
				<!-- <div class="search_hd">
					<a href="javascript:;">高级<br>搜索
					</a>
				</div> -->
				<!-- <div class="search_ft">
					<ul class="clear">
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
						<li><a href="javascript:;">今日特惠</a></li>
					</ul>
				</div> -->
			</div>
			<div class="shopping_cart right">
				<a href="${pageContext.request.contextPath}/c/carUI" style="color: #fff"><img src="${pageContext.request.contextPath}/resources/images/index/cart.png" class="cart_icon"> 我的购物车 <i class="shop_num">${carCnt}</i></a>
			</div>
		</div>
	</section>
	<script>
		function searchCheck(){
			var keyword = $("#keywordInput").val();
			if(!keyword.trim()){
				//swal("请输入需要查找的关键字！");
				//$("#keywordInput").focus();
				//return false;
			}
			return true;
		}
</script>