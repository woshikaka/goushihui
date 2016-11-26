<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="wrapper">
	<jsp:include page="/WEB-INF/views/admin/public/nav.jsp"/>
		<div id="page-wrapper">
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<div class="row">
				<div class="col-md-12">
					<form id="searchForm" class="form-inline" action="${pageContext.request.contextPath}//a/product/listUI" method="post" onsubmit="disabledButton()">
						<div class="form-group">
							<input class="form-control" value="${requestParam.name}" type="text" name="name" placeholder="产品名称"/>
						</div>
						<button type="submit" class="btn btn-primary glyphicon glyphicon-search" id="search"></button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" onclick="batchTop()">批量top</button>
					<button type="button" class="btn btn-default" onclick="batchCancelTop()">批量取消top</button>
				</div>
				<form action="${pageContext.request.contextPath}/a/top/batchTop" id="batchTopForm" method="post">
					<input id="batchTopFormInput" type="hidden" name="productIds" value="">
					<input type="hidden" name="productTypeId" value="${productTypeId}">
				</form>
				<form action="${pageContext.request.contextPath}/a/top/batchCancelTop" id="batchCancelTopForm" method="post">
					<input id="batchCancelTopFormInput" type="hidden" name="productIds" value="">
					<input type="hidden" name="productTypeId" value="${productTypeId}">
				</form>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="1%"></th>
								<th>名称</th>
								<th>是否上架</th>
								<th>是否top</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageBean.recordList}" var="bean">
								<tr>
									<td><input type="checkbox" name="productCheckbox" value="${bean.id}"></td>
									<td>${bean.name}</td>
									<td>
										<c:choose>
											<c:when test="${bean.isShangJia}">
												<span class="label label-success">上架</span>
											</c:when>
											<c:otherwise>
												<span class="label label-default">下架</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${bean.isTop}">
												<span class="label label-success">已经top</span>
											</c:when>
											<c:otherwise>
												<span class="label label-default">无top</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr> 
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/views/admin/public/paging.jsp" %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	$(function(){
	})

	function batchTop(){
		var ids = new Array();
		$("input[name='productCheckbox']:checked").each( function () {
			ids.push($(this).val());
		});
		if(ids.length == 0){
			swal("请先选择产品！", "", "error")
			return;
		}
		if(ids.length > 8){
			swal("最多只能选择8个产品！", "", "error")
			return;
		}
		$("#batchTopFormInput").val(ids.join(","));
    	$("#batchTopForm").submit(); 
    	
    	waitingDialog.show('处理中...', {dialogSize: 'sm'});
	}
	
	function batchCancelTop(){
		var ids = new Array();
		$("input[name='productCheckbox']:checked").each( function () {
			ids.push($(this).val());
		});
		if(ids.length == 0){
			swal("请先选择产品！", "", "error")
			return;
		}
		$("#batchCancelTopFormInput").val(ids.join(","));
    	$("#batchCancelTopForm").submit(); 
    	
    	waitingDialog.show('处理中...', {dialogSize: 'sm'});
	}
</script>