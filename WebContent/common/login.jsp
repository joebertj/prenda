<%@include file="header.jsp"%>
</head>
<body>
<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/common/img/logo2.png" width="135" height="123"></TD>
			<TD><%@include file="navi.jsp"%></TD>
		</TR>
		<TR>
			<TD width=200></TD>
			<TD align=center>
<%@include file="msg.jsp"%>
			<FORM method="post" action="${contextPath}/j_spring_security_check">
			<TABLE border="1">
				<TR>
					<TH colspan="2">
					Login
 					</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user"></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD>: <INPUT type="password" name="pass"></TD>
				</TR>
				<tr>
					<td colspan="2" align="center"><input type='checkbox' name='_spring_security_remember_me' value="true"/>Remember Me</td>
				</tr>
				<TR>
					<TD align="center" colspan="2"><INPUT type="submit" value="Login"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>