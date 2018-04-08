<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" class="main" width="100%">
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
<sql:query var="foreclose" dataSource="${prenda}">
SELECT pawn.pid,branch,loan_date,loan,service_charge,bpid,first_name,last_name,middle_name,rate,
ADDDATE(pawn.loan_date,120+15*extend) AS expire 
FROM pawn
LEFT JOIN customer ON pawn.nameid=customer.id
LEFT JOIN interest ON pawn.branch=interest.interestid
WHERE (day=DATEDIFF(NOW(),loan_date) OR (day=34 AND DATEDIFF(NOW(),loan_date)>34))
AND ADDDATE(pawn.loan_date,120+15*extend) <= NOW()
AND (
<c:forEach var="pid" items="${paramValues.pid}" varStatus="line">
<c:choose>
	<c:when test="${line.count == 1}">
		pawn.pid=<c:out value="${pid}"/>
	</c:when>
	<c:otherwise>
		OR pawn.pid=<c:out value="${pid}"/>
	</c:otherwise>
</c:choose>
</c:forEach>
)
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
			<form name="pullout" action="CheckMassPullout" method="post">
			<table>
				<TR>
					<TH colspan="100%">Liaison Login</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD><input type="text" name="username"/></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD><input type="password" name="password"/></TD>
				</TR>
				<TR>
					<TD colspan="100%" align="center"><input type="submit" value="Pullout Items"/></TD>
				</TR>
			</table>
			<br/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pullout Items</TH>
				</TR>
				<TR>
					<TH>Pawn ID</TH>
					<TH>Branch PID</TH>
					<TH>Loan Date</TH>
					<TH>Foreclosed Date</TH>
					<TH>Customer</TH>
					<TH>Loan</TH>
				</TR>
				<c:forEach var="row" items="${foreclose.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><input type="hidden" name="pid" value="${row.pid}"/>
					<fmt:formatNumber value="${row.pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
					<TD><fmt:formatNumber value="${row.branch}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${row.bpid}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD><fmt:formatDate value="${row.loan_date}" dateStyle="long" /></TD>
					<TD><fmt:formatDate value="${row.expire}" dateStyle="long" /></TD>
					<TD><c:out value="${row.last_name}"/>, <c:out value="${row.first_name}"/> <c:out value="${row.middle_name}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loan}" /></TD>
				</TR>
				</c:forEach>
			</TABLE>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>