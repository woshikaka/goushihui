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
			<form class="form-horizontal" action="/gsh/a/product/addProduct">
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="name" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">价格</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="price" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">超市价格</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="marketPrice" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否上架</label>
					<div class="col-sm-6">
						<label class="radio-inline"> <input type="radio" name="isVisible" value="true"> 上架
						</label> <label class="radio-inline"> <input type="radio" name="isVisible" value="false"> 下架
						</label>
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