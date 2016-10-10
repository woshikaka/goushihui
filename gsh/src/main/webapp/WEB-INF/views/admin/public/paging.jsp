<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<nav>
			<ul class="pagination pagination-sm">
				<li><a href="#" onclick="gotoPage('${pageBean.currPageNo-1}')" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				<li><a href="#" onclick="gotoPage(1)">首页</a></li>
				<c:forEach begin="${pageBean.beginPageIndex}" end="${pageBean.endPageIndex}" var="pageNo">
					<c:choose>
						<c:when test="${pageNo == pageBean.currPageNo}">
							<li class="active"><a href="#" onclick="gotoPage(${pageNo})">${pageNo}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#" onclick="gotoPage(${pageNo})">${pageNo}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="#" onclick="gotoPage('${pageBean.pageCount}')">尾页</a></li>
				<li><a href="#" onclick="gotoPage('${pageBean.currPageNo+1}')" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
		<ul class="list-inline">
			<li>每页
				<select name="pageSize" onchange="gotoPage(${pageBean.currPageNo})">
					<option value="50" ${'50'==pageBean.pageSize?'selected':''}>50</option>
					<option value="100" ${'100'==pageBean.pageSize?'selected':''}>100</option>
					<option value="200" ${'200'==pageBean.pageSize?'selected':''}>200</option>
					<option value="300" ${'300'==pageBean.pageSize?'selected':''}>300</option>
				</select>个
			</li>
			<%-- <li>每页：${pageBean.pageSize}个</li> --%>
			<li>总记录：${pageBean.recordCount}</li>
			<li>共${pageBean.pageCount}页</li>
			<li><input id="inputPageNo" type="text" style="width:38px;height:20px" placeholder="页码" /><a href="javascript:void(0)" onclick="gotoPage(-100)">跳转</a><span id='pageNoWarn' style='color: #a94442'></span></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	function gotoPage(pageNo){
		if(pageNo == -100){
			var tempPageNo = $("#inputPageNo").val();
			tempPageNo = $.trim(tempPageNo);
			if(isNaN(tempPageNo) || tempPageNo.length==0){
				$("#pageNoWarn").empty().text("　请输入数字！");
				return;
			}else{
				pageNo = tempPageNo;
			}
		}
		var pageSize = $("select[name='pageSize']").val();
		$("#searchForm").append("<input type='hidden' name='pageSize' value='" + pageSize +"'>");
		$("#searchForm").append("<input type='hidden' name='currPageNo' value='" + pageNo +"'>").submit();
	}
</script>