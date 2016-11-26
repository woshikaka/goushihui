<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<style type="text/css">
	#modifyModal .modal-dialog{
    	overflow-y: initial !important
	}
	#modifyModal .modal-body{
	    height: 500px;
	    overflow-y: auto;
	}
</style>
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
							<select class="form-control" name="isShangJia" id="isShangJiaSelect">
								<option value="">是否上架</option>
								<option value="true">上架</option>
								<option value="false">下架</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary glyphicon glyphicon-search" id="search"></button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" onclick="batchShangJia()">批量上架</button>
					<button type="button" class="btn btn-default" onclick="batchXiaJia()">批量下架</button>
					<%-- <form action="${pageContext.request.contextPath}/a/product/batchShangJia" id="batchShangJiaForm" method="post">
						<input id="batchShangJiaFormInput" type="hidden" name="productIds" value="">
					</form> --%>
				</div>
				<form action="${pageContext.request.contextPath}/a/product/batchShangJia" id="batchShangJiaForm" method="post">
					<input id="batchShangJiaFormInput" type="hidden" name="productIds" value="">
				</form>
				<form action="${pageContext.request.contextPath}/a/product/batchXiaJia" id="batchXiaJiaForm" method="post">
					<input id="batchXiaJiaFormInput" type="hidden" name="productIds" value="">
				</form>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="1%"></th>
								<th>名称</th>
								<th>图片</th>
								<th>产品类别</th>
								<th>售卖价格</th>
								<th>超市价格</th>
								<th>库存</th>
								<th>累计销售量</th>
								<th>是否上架</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageBean.recordList}" var="bean">
								<tr>
									<td><input type="checkbox" name="productCheckbox" value="${bean.id}"></td>
									<td>${bean.name}</td>
									<td><img src="/upload${bean.image}" width="50"></td>
									<td>${bean.firstType.name}<span class="glyphicon glyphicon-chevron-right"></span>${bean.secType.name}<span class="glyphicon glyphicon-chevron-right">${bean.thirdType.name}</td>
									<td>${bean.price}</td>
									<td>${bean.marketPrice}</td>
									<td>${bean.stockCount}</td>
									<td>${bean.sellCount}</td>
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
									<td><a href="javascript:void(0)" onclick="modifyProductInfo(${bean.id})">修改</a></td>
								</tr> 
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/views/admin/public/paging.jsp" %>
				</div>
			</div>
		</div>
	</div>
	
	<!--修改 Modal -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改产品信息</h4>
	      </div>
	      <div class="modal-body">
	      	<form class="form-horizontal" action="/gsh/a/product/updateProduct" method="post"  onsubmit="return updateProductPre()">
				<input id="modifyModalProductId" type="hidden" name="id" value=""/>
				<input id="modifyModalImage" type="hidden" name="image" value=""/>
				<input id="modifyModalIsTop" type="hidden" name="isTop" value=""/>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input id="modifyModalName" type="text" class="form-control" value="" name="name" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品类型<i style="color: red">*</i></label>
					<div class="col-sm-2">
						<select class="form-control" name="firstType.id" id="firstTypeChosen">
								<option value="">一级分类</option>
								<c:forEach items="${productTypes}" var="bean">
									<option value="${bean.id}">${bean.name}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="secType.id" id="secTypeChosen">
								<option value="">二级分类</option>
								<c:forEach items="${secTypes}" var="bean">
									<option value="${bean.id}">${bean.name}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="thirdType.id" id="thirdTypeChosen">
								<option value="">三级分类</option>
								<c:forEach items="${thirdTypes}" var="bean">
									<option value="${bean.id}">${bean.name}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input id="modifyModalPrice" type="text" class="form-control" value="" name="price" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">超市价格<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<input id="modifyModalMarketPrice" type="text" class="form-control" name="marketPrice" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否上架<i style="color: red">*</i></label>
					<div class="col-sm-6">
						<label class="radio-inline"> <input id="shangjia" type="radio" name="isShangJia" value="true"> 上架
						</label> <label class="radio-inline"> <input id="xiajia" type="radio" name="isShangJia" value="false"> 下架
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品描述与细节</label>
					<div class="col-sm-9">
						<textarea name="description" id="editor1" rows="10" cols="80">
			            </textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">确认修改</button>
					</div>
				</div>
			</form>
	      </div>
	      <!-- <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary">确认修改</button>
	      </div> -->
	    </div>
	  </div>
	</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
<script>
	$(function(){
		CKEDITOR.replace( 'editor1' );
		CKEDITOR.editorConfig = function( config ) {
			config.language = 'zh-cn';
		};
		//下拉框初始化		
		$("#isShangJiaSelect").val('${requestParam.isShangJia}');
		
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

	function batchShangJia(){
		var ids = new Array();
		$("input[name='productCheckbox']:checked").each( function () {
			ids.push($(this).val());
		});
		if(ids.length == 0){
			swal("请先选择产品！", "", "error")
			return;
		}
		$("#batchShangJiaFormInput").val(ids.join(","));
    	$("#batchShangJiaForm").submit(); 
    	
    	waitingDialog.show('处理中...', {dialogSize: 'sm'});
	}
	
	function batchXiaJia(){
		var ids = new Array();
		$("input[name='productCheckbox']:checked").each( function () {
			ids.push($(this).val());
		});
		if(ids.length == 0){
			swal("请先选择产品！", "", "error")
			return;
		}
		$("#batchXiaJiaFormInput").val(ids.join(","));
    	$("#batchXiaJiaForm").submit(); 
    	
    	waitingDialog.show('处理中...', {dialogSize: 'sm'});
	}
	
	function modifyProductInfo(productId){
    	$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/a/product/getProductInfoById",
			dataType : "json",
			data : {
				"id" : productId
			},
			beforeSend:function(){
		    	waitingDialog.show('请稍等...', {dialogSize: 'sm'});
			},
			success : function(data) {
				waitingDialog.hide();
				$("#modifyModalProductId").val("").val(data.id);
				$("#modifyModalImage").val("").val(data.image);
				$("#modifyModalIsTop").val("").val(data.isTop);
				$("#modifyModalName").val("").val(data.name);
				$("#firstTypeChosen").val("").val(data.firstType.id);
				$("#secTypeChosen").val("").val(data.secType.id);
				$("#thirdTypeChosen").val("").val(data.thirdType.id);
				$("#modifyModalPrice").val("").val(data.price);
				$("#modifyModalMarketPrice").val("").val(data.marketPrice);
				$("#editor1").val("").val(data.description);
				if(data.isShangJia){
					$("#shangjia").prop("checked",true);
				}else{
					$("#xiajia").prop("checked",true);
				}
				CKEDITOR.instances['editor1'].setData(data.description);
				
				$('#modifyModal').modal('show');
			}
		});
	}
	
	function updateProductPre(){
		var name = $("#modifyModalName").val();
		if(!name.trim()){
			$("#modifyModalName").focus();
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
		
		var priceStr = $("input[name='price']").val();
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
		
		var marketPriceStr = $("input[name='marketPrice']").val();
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
		
		waitingDialog.show('处理中...', {dialogSize: 'sm'});
		return true;
	}
</script>