<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" class="main" width="100%">
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
			<FORM method="post" action="CustomerModify">
			<INPUT type="hidden" name="referer" value="../common/listcustomer.jsp">
			<INPUT type="hidden" name="modtype" value="0">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Add New Customer</TH>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD>: <INPUT type="text" name="clname"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD>: <INPUT type="text" name="cfname"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD>: <INPUT type="text" name="cmname"></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="cadd" size="50"></TD>
				</TR>
				<TR>
					<TD align="center" colspan="2"><INPUT type="submit" value="Add"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>