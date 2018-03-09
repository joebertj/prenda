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
			<form name="disburse" action="CashDisbursement" method="post">
			<input type="hidden" name="branch" value="${user.branchId}"/>
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
				<c:forEach var="code" items="${paramValues.code}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#3366FF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="code" value="${code}"/>
					<input type="hidden" name="particulars" value="${paramValues.particulars[i]}"/>
					<input type="hidden" name="amount" value="${paramValues.amount[i]}"/>
					<TD><c:out value="${code}"/></TD>
					<TD><c:out value='${paramValues.account[i]}'/></TD>
					<TD><c:out value='${paramValues.particulars[i]}'/></TD>
					<TD><c:out value='${paramValues.amount[i]}'/></TD>
					<c:set var="i" value="${i+1}"/>
				</TR>
				</c:forEach>
				<TR>
					<TD colspan="100%" align="center"><input type="submit" value="Post"/></TD>
				</TR>			
			</TABLE>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>