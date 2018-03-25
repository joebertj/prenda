<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navipub.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
			<FORM method="post" action="ActivateOwner.htm">
				<INPUT type="hidden" name="pass" value="">
				<INPUT type="hidden" name="modtype" value="0">
				<INPUT type="hidden" name="level" value="8">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Activate User</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user"></TD>
				</TR>
				<TR>
					<TD>Key</TD>
					<TD>: <INPUT type="password" name="key" size="128"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Activate"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>