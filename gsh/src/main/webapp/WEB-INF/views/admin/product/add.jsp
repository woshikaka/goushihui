<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>数据采集</title>
<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/ckEditor/sample.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ckEditor/samples.css"> --%>
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
						<input type="file" class="form-control" name="file">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">价格<i style="color: red">*</i></label>
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
					<label class="col-sm-2 control-label">是否上架<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<label class="radio-inline"> <input type="radio" name="isShangJia" value="true"> 上架
						</label> <label class="radio-inline"> <input type="radio" checked="checked" name="isShangJia" value="false"> 下架
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品描述与细节</label>
					<div class="col-sm-9">
						<%-- <%@ include file="/WEB-INF/views/admin/public/ckEditor.jsp"%>
						<input type="hidden" name="description" value=""> --%>
						<textarea name="description" id="editor1" rows="10" cols="80">
			                This is my textarea to be replaced with CKEditor.
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
<script>
	function addProductPre(){
		var name = $("input[name='name']").val();
		if(!name.trim()){
			$("input[name='name']").focus();
			swal("产品名称为空！", "", "error")
			return false;
		}
		
		var firstType = $("#firstTypeChosen").val();
		if(!firstType.trim()){
			$("#firstTypeChosen").focus();
			swal("一级分类为空！", "", "error")
			return false;
		}
		
		var secType = $("#secTypeChosen").val();
		if(!secType.trim()){
			$("#secTypeChosen").focus();
			swal("二级分类为空！", "", "error")
			return false;
		}
		
		var thirdType = $("#thirdTypeChosen").val();
		if(!thirdType.trim()){
			$("#thirdTypeChosen").focus();
			swal("三级分类为空！", "", "error")
			return false;
		}
		
		var file = $("input[name='file']").val();
		if(!file.trim()){
			$("input[name='file']").focus();
			swal("产品图片为空！", "", "error")
			return false;
		}
		var priceStr = $("input[name='priceStr']").val();
		if(!priceStr.trim()){
			$("input[name='priceStr']").focus();
			swal("价格为空！", "", "error");
			return false;
		}
		if(priceStr.match(/^\d+$/)){ //integer
			// do noting
		}else if(priceStr.match(/^\d+\.\d+$/)){//float
			// do noting
		}else{
			swal("价格格式不正确！", "", "error");
			return false;
		}
		
		var marketPriceStr = $("input[name='marketPriceStr']").val();
		if(!marketPriceStr.trim()){
			$("input[name='marketPriceStr']").focus();
			swal("超市价格为空！", "", "error")
			return false;
		}
		if(marketPriceStr.match(/^\d+$/)){ //integer
			// do noting
		}else if(marketPriceStr.match(/^\d+\.\d+$/)){//float
			// do noting
		}else{
			swal("超市价格格式不正确！", "", "error");
			return false;
		}
		
		//var ue = UE.getEditor('container');
		//var description = ue.getContent();
		//var content = CKEDITOR.instances['comment'].getData();
		//alert(content);
		//$("input[name='description']").val(content);
		
		waitingDialog.show('处理中...', {dialogSize: 'sm'});
		return true;
	}
	
	$(function() {
		$("#firstTypeChosen").change(function() {
			var firTypeId = $(this).val();
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/a/product/findSecTypeByFirType",
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
				url : "${pageContext.request.contextPath}/a/product/findThirdTypeBySecType",
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