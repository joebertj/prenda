<%@include file="common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="common/img/logo2.png" width="135" height="123"></TD>
			<TD><%@include file="common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="common/msg.jsp"%>
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<input type="hidden" name="referer" value="changepass"/>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Change Password <c:out value="${authenticated}" /></TH>
				</TR>
				<TR>
					<TD>Old Password</TD>
					<TD>: <INPUT type="password" name="pass"></TD>
				</TR>
				<TR>
					<TD>New Password</TD>
					<TD>: <INPUT type="password" name="pass1"></TD>
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