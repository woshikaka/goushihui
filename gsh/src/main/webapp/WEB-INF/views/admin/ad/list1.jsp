<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/admin/public/taglib.jsp"%>
<html lang="zh-CN">
<head>
<title>购食汇管理平台</title>
<%@ include file="/WEB-INF/views/admin/public/reflib.jsp"%>
<%-- <link rel="stylesheet" href="${ctx}/resources/css/bean-list/bean-list.css"> --%>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="wrapper">
	<jsp:include page="/WEB-INF/views/admin/public/nav.jsp"/>
		<div id="page-wrapper">
			<%@ include file="/WEB-INF/views/admin/public/alertInfo.jsp"%>
			<%-- <div class="row">
				<div class="col-md-12">
					<form id="searchForm" class="form-inline" action="${pageContext.request.contextPath}/xxx" method="post" onsubmit="disabledButton()">
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
			</div> --%>
			<div class="row">
				<div class="col-md-12  btn-group btn-group-sm">
					<button type="button" class="btn btn-default" onclick="showChecked()">显示选中的长条广告</button>
					<button type="button" class="btn btn-default" onclick="deleteChecked()">删除</button>
				</div>
				<form action="${pageContext.request.contextPath}/a/ad/delete" id="deleteForm" method="post">
					<input id="deleteFormInput" type="hidden" name="adIds" value="">
				</form>
				<form action="${pageContext.request.contextPath}/a/ad/show" id="showForm" method="post">
					<input id="showFormInput" type="hidden" name="adIds" value="">
					<input id="showFormInput" type="hidden" name="adType" value="bar">
				</form>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<table class="table table-striped table-hover table-condensed" id="datatable">
						<thead>
							<tr>
								<th width="1%"></th>
								<th>图片</th>
								<th>超链接</th>
								<th>使用状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ads}" var="bean">
								<tr>
									<td><input type="checkbox" name="adCheckbox" value="${bean.id}"></td>
									<td><img src="/upload${bean.ossKey}" width="140"/></td>
									<td>${bean.href}</td>
									<td><c:choose>
											<c:when test="${bean.isUse}">
												<span class="label label-success">正在使用中</span>
											</c:when>
											<c:otherwise>
												<span class="label label-default">无效</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr> 
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	function deleteChecked(){
		var adIds = new Array();
		$("input[name='adCheckbox']:checked").each( function () {
			adIds.push($(this).val());
		});
		
		if(adIds.length == 0){
			$.confirm({
				title:"提醒",
			    text: "请选选择广告！",
			    confirm: function() {
			    	
			    },
			}); 
			return;
		}
		
		$.confirm({
			title:"危险",
		    text: "是否要删除选中的广告？删除后，对应的图片将永远消失！",
		    confirmButtonClass: "btn-danger",
		    confirmButton: "删除",
		    confirm: function() {
		    	$("#deleteFormInput").val(adIds.join(","));
		    	$("#deleteForm").submit();
		    },
		}); 
	}
	
	function showChecked(){
		var adIds = new Array();
		$("input[name='adCheckbox']:checked").each( function () {
			adIds.push($(this).val());
		});
		
		if(adIds.length == 0){
			$.confirm({
				title:"提醒",
			    text: "请选选择广告！",
			    confirm: function() {
			    	
			    },
			}); 
			return;
		}
		if(adIds.length > 1){
			$.confirm({
				title:"提醒",
			    text: "对多只能选择1个广告！你当前一共选了"+adIds.length+"个！",
			    confirm: function() {
			    	
			    },
			}); 
			return;
		}
		
		$.confirm({
			title:"提醒",
		    text: "点击确定后，首页的长条广告将立即切换为最新的广告，确定立即切换？",
		    confirmButtonClass: "btn-warning",
		    confirmButton: "确定",
		    confirm: function() {
		    	$("#showFormInput").val(adIds.join(","));
		    	$("#showForm").submit();
		    	/* $.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/a/ad/delete",
					dataType : "json",
					data : {
						"adIds" : adIds.join(",")
					},
					success : function() {
						console.log("删除成功!");
					}
				}); */
		    },
		}); 
	}
</script>