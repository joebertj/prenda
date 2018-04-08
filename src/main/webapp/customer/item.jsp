<%@include file="../public/header.jsp"%>
</head>
<body>

<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="../public/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../public/msg.jsp"%>
			<FORM method="post" action="${contextPath}/CheckItem">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Item Access</TH>
				</TR>
				<TR>
					<TD>Pawn ID</TD>
					<TD>: <INPUT type="text" name="pid"></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD>: <INPUT type="password" name="pass"></TD>
				</TR>
				<TR>
					<TD align="center" colspan="2"><INPUT type="submit" value="Access"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>