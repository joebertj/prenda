<%@include file="../public/header.jsp"%>
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
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
			<FORM method="GET" action="findcustomerdetail.jsp">
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