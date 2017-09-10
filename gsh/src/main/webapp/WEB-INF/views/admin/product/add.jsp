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
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<form class="form-horizontal" action="/gsh/a/product/addProduct" method="post" enctype="multipart/form-data" onsubmit="return addProductPre()">
				<input type="hidden" name="isForm" value="true"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${productVO.name}" name="name" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品类型<i style="color: red">*</i></label>
					<div class="col-sm-2">
						<select class="form-control" name="firstTypeId" id="firstTypeChosen">
								<option value="">一级分类</option>
								<c:forEach items="${productTypes}" var="bean">
									<option value="${bean.id}">${bean.name}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="secTypeId" id="secTypeChosen">
								<option value="">二级分类</option>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="thirdTypeId" id="thirdTypeChosen">
								<option value="">三级分类</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品图片<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="file" class="form-control" name="file"><i style="color:red">产品图片建议上传400*400的图片</i>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">销售价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${productVO.priceStr}" name="priceStr" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">超市价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="marketPriceStr" value="${productVO.marketPriceStr}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">库存<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="stockCount" value="${productVO.stockCount}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否上架<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<label class="radio-inline"> <input type="radio" name="isShangJia" value="true"> 上架
						</label> <label class="radio-inline"> <input type="radio" checked="checked" name="isShangJia" value="false"> 下架
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">活动类型<i style="color: red">*</i></label>
					<div class="col-sm-6 checkbox">
						<label><input type="checkbox" name="activityType" value="1">团购商品&nbsp;&nbsp;&nbsp;</label>
						<label><input type="checkbox" name="activityType" value="2">特价促销&nbsp;&nbsp;&nbsp;</label>
						<label><input type="checkbox" name="activityType" value="3">新品上架&nbsp;&nbsp;&nbsp;</label>
					</div>
				</div>
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
						<button type="submit" class="btn btn-default">添加</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/layui/lay/dest/layui.all.js"></script>
<script>
	function addProductPre(){
		var name = $("input[name='name']").val();
		if(!name.trim()){
			$("input[name='name']").focus();
			layer.msg('产品名称不能为空！');
			return false;
		}
		
		var firstType = $("#firstTypeChosen").val();
		if(!firstType.trim()){
			$("#firstTypeChosen").focus();
			layer.msg('一级分类不能为空！');
			return false;
		}
		
		var secType = $("#secTypeChosen").val();
		if(!secType.trim()){
			$("#secTypeChosen").focus();
			layer.msg('二级分类不能为空！');
			return false;
		}
		
		var thirdType = $("#thirdTypeChosen").val();
		if(!thirdType.trim()){
			$("#thirdTypeChosen").focus();
			layer.msg('三级分类不能为空！');
			return false;
		}
		
		var file = $("input[name='file']").val();
		if(!file.trim()){
			$("input[name='file']").focus();
			layer.msg('产品图片不能为空！');
			return false;
		}
		var priceStr = $("input[name='priceStr']").val();
		if(!priceStr.trim()){
			$("input[name='priceStr']").focus();
			layer.msg('价格不能为空！');
			return false;
		}
		if(priceStr.match(/^\d+$/)){ //integer
			// do nothing
		}else if(priceStr.match(/^\d+\.\d+$/)){//float
			// do nothing
		}else{
			layer.msg('价格格式不正确！');
			return false;
		}
		
		var marketPriceStr = $("input[name='marketPriceStr']").val();
		if(!marketPriceStr.trim()){
			$("input[name='marketPriceStr']").focus();
			layer.msg('超市价格不能为空！');
			return false;
		}
		if(marketPriceStr.match(/^\d+$/)){ //integer
			// do nothing
		}else if(marketPriceStr.match(/^\d+\.\d+$/)){//float
			// do nothing
		}else{
			layer.msg('超市价格格式不正确！');
			return false;
		}
		
		var stockCount = $("input[name='stockCount']").val();
		if(!stockCount.trim()){
			$("input[name='stockCount']").focus();
			layer.msg('库存不能为空！');
			return false;
		}
		if(stockCount.match(/^\d+$/)){ //integer
			// do nothing
		}else{
			layer.msg('库存格式不正确！');
			return false;
		}
		layer.load(1, {shade: [0.6,'#676767']});
		return true;
	}
	
	$(function() {
		$("#firstTypeChosen").change(function() {
			var firTypeId = $(this).val();
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/a/productType/findSecTypeByFirType",
				data : {
					"firTypeId" : firTypeId
				},
				dataType : 'html',
				success : function(html) {
					$("#secTypeChosen").empty().append(html);
				}
			});
		});
		
		$("#secTypeChosen").change(function() {
			var secTypeId = $(this).val();
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/a/productType/findThirdTypeBySecType",
				data : {
					"secTypeId" : secTypeId
				},
				dataType : 'html',
				success : function(html) {
					$("#thirdTypeChosen").empty().append(html);
				}
			});
		});
	})
	CKEDITOR.replace( 'editor1' );
	CKEDITOR.editorConfig = function( config ) {
		config.language = 'zh-cn';
	};
</script>