<%@include file="header.jsp"%>
</head>
<body>
<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="navi.jsp"%></TD>
		</TR>
		<TR>
			<td width="200"></td>
			<TD align="center">
<%@include file="msg.jsp"%>
			<FORM method="post" action="${contextPath}/j_spring_security_check">
			<TABLE border="1">
				<TR>
					<TH colspan="2">
					<spring:message code="common.login" text="Login"/>
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
					<td colspan="2" align="center"><input type='checkbox' name='_spring_security_remember_me'/>
						<spring:message code="common.rememberMe" />
					</td>
				</tr>
				<TR>
					<TD align="center" colspan="2"><INPUT type="submit" value="<spring:message code="common.login" text="Login"/>"></TD>
				</TR>
			</TABLE>
			</FORM>
			<a href="${contextPath}/register/forgot.jsp"><spring:message code="common.forgot" text="Forgot Password?"/></a>
			<a href="${contextPath}/register/newuser.jsp"><spring:message code="common.register" text="New User?"/></a>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>