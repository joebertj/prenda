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
<sql:query var="users" dataSource="${prenda}">
SELECT branch FROM users WHERE username="${authenticated}"
</sql:query>
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="modtype" value="2"> 
			<input type="hidden" name="referer" value="changepass"/>
			<INPUT type="hidden" name="uid"	value="${param.uid}"> 
			<INPUT type="hidden" name="user" value="${param.user}">
			<INPUT type="hidden" name="branch" value="${user.branchId}">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Change Password <c:out value="${param.user}" /></TH>
				</TR>
				<TR>
					<TD>New Password</TD>
					<TD>: <INPUT type="password" name="pass"></TD>
				</TR>
				<TR>
					<TD>Verify Password</TD>
					<TD>: <INPUT type="password" name="pass2"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Change">
					</TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>