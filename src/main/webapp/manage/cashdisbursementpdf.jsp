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
<sql:query var="group" dataSource="${prenda}">
SELECT * FROM journal 
LEFT JOIN accounts ON journal.accountid=accounts.accountcode 
WHERE journal_group=<c:out value="${param.group}"/>
</sql:query>
			<form name="disburse" action="../cashdisbursement.pdf" method="post" target="_blank">
			<input type="hidden" name="branch" value="${user.branchId}"/>
			<input type="hidden" name="bname" value="${branch.name}"/>
			<input type="hidden" name="baddress" value="${branch.address}"/>
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