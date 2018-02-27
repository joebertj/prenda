<c:set var="redirectURL" value="item.jsp?msg=Please provide appropriate password"/>
<c:set var="pid" value="${sessionScope.pid}"/>
<c:choose>
	<c:when test="${pid==null}">
		<c:redirect url="${redirectURL}"/>
	</c:when>
	<c:when test="${pid!=param.pid}">
		<c:redirect url="${redirectURL}"/>
	</c:when>
</c:choose>