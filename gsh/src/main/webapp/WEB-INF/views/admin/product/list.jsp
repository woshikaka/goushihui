<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>数据采集</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/css/bean-list/bean-list.css">
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
					<form id="searchForm" class="form-inline" action="${ctx}/xxx" method="post" onsubmit="disabledButton()">
						<div class="form-group">
							<input class="form-control" value="" type="text" name="beanCoding" placeholder="编码"/>
							<select class="form-control" name="isExport" id="isExportChosen">
								<option value="">是否上架</option>
								<option value="0">上架</option>
								<option value="1">下架</option>
							</select>
						</div>
						<div class="form-group">
							<input class="form-control" type="text" name="keyWord" value="" placeholder="请输入关键字" />
						</div>
						<button type="submit" class="btn btn-primary glyphicon glyphicon-search" id="search"></button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" onclick="bean.delChecked('${pQueryTDO.country}')">批量上架</button>
					<button type="button" class="btn btn-default" onclick="bean.exportExcel('0','${pQueryTDO.country}')">批量下架</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="20px"><input type="checkbox" onclick="bean.allChecked(this)" disabled="disabled"></th>
								<th>名称</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageBean.recordList}" var="bean">
								<tr>
									<td>${bean.name}</td>
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