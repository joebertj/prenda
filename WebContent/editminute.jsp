<c:set var="now" value="<%= new java.util.Date() %>" />
<fmt:parseDate var="cdate" value="${row.cdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
<c:set var="minutelapsed" value="${(now.time-cdate.time)/(1000*60)}"/>
	<c:if test="${minutelapsed<15}">
		<TD>
		<form method="post" action="pawnedit.jsp">
			<input type="hidden" name="pid" value='<c:out value="${row.pid}"/>'/>
			<input type="submit" value="Edit"/>
		</form>
		</TD>
	</c:if>	