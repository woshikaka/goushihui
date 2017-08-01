<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/admin/public/nav.jsp" />
		<div id="page-wrapper">
			<form class="form-horizontal" action="/gsh/a/product/addProduct" method="post" enctype="multipart/form-data" onsubmit="return addProductPre()">
				<div class="form-group">
					<label class="col-sm-2 control-label">产品描述</label>
					<div class="col-sm-9">
						<%-- <%@ include file="/WEB-INF/views/admin/public/ckEditor.jsp"%>
						<input type="hidden" name="description" value=""> --%>
						<textarea name="description" id="editor1" rows="10" cols="80">
			            </textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">修改</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
	$(function() {
		
	})
	CKEDITOR.replace( 'editor1' );
	CKEDITOR.editorConfig = function( config ) {
		config.language = 'zh-cn';
	};
</script>