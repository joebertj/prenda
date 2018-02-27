<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="../common/img/logo2.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
<sql:query var="branch" dataSource="${prenda}">
SELECT name FROM branch
WHERE branchid=<c:out value="${param.frombranch}"/> OR branchid=<c:out value="${param.tobranch}"/>
</sql:query>
			<FORM method="post" action="../TransferCash">
			<INPUT type="hidden" name="modtype" value="0">
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Transfer Cash</TH>
				</TR>
				<TR>
					<TH>From Branch</TH>
					<TH>To Branch</TH>
					<TH>Amount</TH>
				</TR>
				<TR>
					<TD><c:out value="${branch.rows[0].name}"/>
						<input type="hidden" name="frombranch" value='<c:out value="${param.frombranch}"/>'/>
					</TD>
					<TD><c:out value="${branch.rows[1].name}"/>
						<input type="hidden" name="tobranch" value='<c:out value="${param.tobranch}"/>'/>
					</TD>
					<TD><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.amount}" />
						<input type="hidden" name="amount" value='<c:out value="${param.amount}"/>'/>
					</TD>
				</TR>
				<TR>
					<TD colspan="100%" align="center"><INPUT type="submit" value="Transfer"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>