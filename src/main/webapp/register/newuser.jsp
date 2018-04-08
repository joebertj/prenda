<%@include file="../public/header.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>


<TABLE border="1" class="main" width="100%">
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../public/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
			<FORM method="post" action="RegisterOwner.htm">
				<INPUT type="hidden" name="referer" value="register/activate">
				<INPUT type="hidden" name="pass" value="">
				<INPUT type="hidden" name="modtype" value="0">
				<INPUT type="hidden" name="level" value="8">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Register New User</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user"></TD>
				</TR>
				<TR>
					<TD>Email</TD>
					<TD>: <INPUT type="text" name="email"></TD>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD>: <INPUT type="text" name="lname"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD>: <INPUT type="text" name="fname"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD>: <INPUT type="text" name="mname"></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD>: <INPUT type="password" name="pass1"></TD>
				</TR>
				<TR>
					<TD>Verify Password</TD>
					<TD>: <INPUT type="password" name="pass2"></TD>
				</TR>
				<tr>
					<td colspan="2" align="center">
					<fmt:bundle basename="env">
						 <fmt:message var="reCaptchaKey" key = "reCaptcha.key"/><br/>
 					</fmt:bundle>
					<div class="g-recaptcha" data-sitekey="${reCaptchaKey}"></div></td>
				</tr>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Register"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>