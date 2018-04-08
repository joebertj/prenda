<c:choose>
	<c:when test="${param.msg!=null}">
		<div class="msg">
			<c:out value="${param.msg}" />
		</div>
	</c:when>
	<c:when test="${msg!=null}">
		<div class="msg">
			<c:out value="${msg}" />
		</div>
	</c:when>
</c:choose>