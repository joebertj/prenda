<%@include file="../public/header.jsp"%>
</head>
<body>

<TABLE border="1" class="main" width="100%">
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
			<FORM method="post" action="${contextPath}/public/CheckItem">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Item Access</TH>
				</TR>
				<TR>
					<TD>Pawn ID</TD>
					<TD>: <INPUT type="text" name="pid"></TD>
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