<c:if test="${isSuccessShow}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		${successMessage}
	</div>
</c:if>
<c:if test="${isWarnShow}">
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		${warnMessage}
	</div>
</c:if>
<c:if test="${isDangerShow}">
	<div class="alert alert-danger alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		${dangerMessage}
	</div>
</c:if>