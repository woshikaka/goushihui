<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/admin/public/nav.jsp" />
		<div id="page-wrapper">
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<form class="form-horizontal" action="/gsh/a/ad/add" method="post" enctype="multipart/form-data">
				<input type="hidden" name="isForm" value="true"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">广告类型</label>
					<div class="col-sm-6">
						<select class="form-control" name="adType">
							<option value="">请选择</option>
							<option value="ROLL">轮播型广告</option>
							<option value="BAR">长条型广告</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">广告超链接</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${ad.href}" name="href" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片</label>
					<div class="col-sm-6">
						<input type="file" class="form-control" name="file" ><i style="color:red">注意：轮播广告建议上传1670*350的图片，长条广告建议上传1140*130的图片</i>
					</div>
				</div>
				<script src="//cdn.ckeditor.com/4.6.0/basic/ckeditor.js"></script>
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