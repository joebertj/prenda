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
<sql:query var="branch" dataSource="${prenda}">
SELECT name,branchid FROM branch
</sql:query>
			<FORM method="post" action="cashtransferdetail.jsp">
			<INPUT type="hidden" name="modtype" value="0">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Transfer Cash</TH>
				</TR>
				<TR>
					<TD>From Branch</TD>
					<TD>To Branch</TD>
				</TR>
				<TR>
					<TD>
					<select size="10" name="frombranch">
					<c:forEach var="row" items="${branch.rows}">
					<option value="${row.branchid}"><c:out value="${row.name}"/></option>
					</c:forEach>
					</select>
					</TD>
					<TD>
					<select size="10" name="tobranch">
					<c:forEach var="row" items="${branch.rows}">
					<option value="${row.branchid}"><c:out value="${row.name}"/></option>
					</c:forEach>
					</select>
					</TD>
				</TR>
				<TR>
					<TD>Amount</TD>
					<TD><input type="text" value="0" name="amount"/></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Continue"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>