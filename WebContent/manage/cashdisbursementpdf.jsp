<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/common/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
<sql:query var="group" dataSource="${prenda}">
SELECT * FROM journal 
LEFT JOIN accounts ON journal.accountid=accounts.accountid 
WHERE journal_group=<c:out value="${param.group}"/>
</sql:query>
			<form name="disburse" action="cashdisbursement.pdf" method="post">
			<input type="hidden" name="branch" value="${user.branchId}"/>
			<input type="hidden" name="bname" value="${branches.name}"/>
			<input type="hidden" name="baddress" value="${branches.address}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Cash Disbursement</TH>
				</TR>
				<TR>
					<TD>Code</TD>
					<TD>Account</TD>
					<TD>Particulars</TD>
					<TD>Amount</TD>
				</TR>
				<c:set var="i" value="0"/>
				<c:forEach var="row" items="${group.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.accountcode}"/></TD>
					<TD><c:out value='${row.accountname}'/></TD>
					<TD><c:out value='${row.description}'/></TD>
					<TD><c:out value='${row.amount}'/></TD>
					<c:set var="i" value="${i+1}"/>
				</TR>
				</c:forEach>
				<TR>
					<TD colspan="100%" align="center">
					<input type="hidden" name="group" value="${param.group}"/>
					<input type="submit" value="Print"/>
					</TD>
				</TR>			
			</TABLE>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>