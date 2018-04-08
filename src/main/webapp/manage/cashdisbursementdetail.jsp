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
							<TR bgcolor="#99CCFF">
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