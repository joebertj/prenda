<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../public/msg.jsp"%>
<c:set var="perpage" value="10"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(journalid) as numid FROM journal
LEFT JOIN ledger on journal.journalid=ledger.ledgerid
WHERE ledgerid IS NULL
<c:if test="${user.level<9}">
AND branchid=<c:out value="${user.branchId}"/>
</c:if>
</sql:query>
<c:set var="numid" value="${pageable.rows[0].numid}" />
<c:set var="pages" value="${numid/perpage}" />
<c:set var="adjust" value="${numid % perpage}" />
<c:if test="${adjust!=0}">
				<c:set var="pages" value="${pages-(adjust/perpage)+1}" />
			</c:if>
<c:set var="pagenum" value="${param.pagenum}" />
<c:if test="${pagenum==null || pagenum<1 || pagenum>pages}">
				<c:set var="pagenum" value="1" />
			</c:if>
<br/>
			Page 
<c:if test="${pagenum>1}">
				<A href='pawnsummary.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='pawnsummary.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='pawnsummary.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="transfer" dataSource="${prenda}">
SELECT journalid,journal_date,accountname,description,amount
FROM journal
LEFT JOIN ledger on journal.journalid=ledger.ledgerid
LEFT JOIN accounts on journal.accountid=accounts.accountid
WHERE ledgerid IS NULL
<c:if test="${user.level<9}">
AND branchid=<c:out value="${user.branchId}"/>
</c:if>
ORDER BY journalid
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out
					value="${perpage}" />
			</sql:query>
			<form action="CashTransfers" method="post">
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Cash Transfers</TH>
				</TR>
				<TR>
					<TD>Journal Date</TD>
					<TD>Account</TD>
					<TD>Description</TD>
					<TD>Amount</TD>
				</TR>
				<c:forEach var="row" items="${transfer.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><fmt:formatDate value="${row.journal_date}" dateStyle="long" /></TD>
					<TD><c:out value="${row.accountname}"/></TD>
					<TD><c:out value="${row.description}"/></TD>
					<TD><c:out value="${row.amount}"/></TD>
					<TD><input type="hidden" name="journalid" value="${row.journalid}"/>
					<input type="submit" value="Post"/></TD>
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