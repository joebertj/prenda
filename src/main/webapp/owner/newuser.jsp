<%@include file="../common/header.jsp"%>
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
<%@include file="../common/msg.jsp"%>
			<FORM method="post" action="${contextPath}/UserModify.htm">
				<INPUT type="hidden" name="referer" value="owner/newuser">
				<INPUT type="hidden" name="pass" value="">
				<INPUT type="hidden" name="modtype" value="0">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Add New User</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user"></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD>: <INPUT type="password" name="pass1"></TD>
				</TR>
				<TR>
					<TD>Verify Password</TD>
					<TD>: <INPUT type="password" name="pass2"></TD>
				</TR>
				<TR>
					<TD>Level</TD>
					<TD>: <SELECT name="level">
						<OPTION value="1">Encoder</OPTION>
						<OPTION value="3">Liaison</OPTION>
						<OPTION value="7">Manager</OPTION>
					</SELECT></TD>
				</TR>
				<TR>
					<TD>Branch</TD>
					<TD>: 
<sql:query var="branches" dataSource="${prenda}">
SELECT branchid,name FROM branch
LEFT JOIN users ON branch.owner=users.uid
WHERE username="${authenticated}"
</sql:query>
					<select name="branch">
					<c:forEach var="row" items="${branches.rows}">
					<option value="${row.branchid}"><c:out value="${row.name}"/></option>
					</c:forEach>
					</select>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Add"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>