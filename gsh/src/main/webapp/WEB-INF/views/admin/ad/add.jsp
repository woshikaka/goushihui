<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>数据采集</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/admin/public/nav.jsp" />
		<div id="page-wrapper">
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<form class="form-horizontal" action="/gsh/a/ad/add" method="post" enctype="multipart/form-data">
				<input type="hidden" name="isForm" value="true"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">广告超链接</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${ad.href}" name="href" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片</label>
					<div class="col-sm-6">
						<input type="file" class="form-control" name="file" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">添加</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>