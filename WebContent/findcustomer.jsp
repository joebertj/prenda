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
			<FORM method="post" action="FindCustomer.htm">
				<INPUT type="hidden" name="referer" value="findcustomerdetail">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Find Customer</TH>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD><INPUT type="text" name="lastName"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD><INPUT type="text" name="firstName"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD><INPUT type="text" name="middleName"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Find"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>