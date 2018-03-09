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
			<FORM method="post" action="CustomerModify">
			<INPUT type="hidden" name="modtype" value="2"> 
			<INPUT type="hidden" name="cid" value="${param.cid}">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit Customer</TH>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD>: <INPUT type="text" name="clname" value="${param.clname}"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD>: <INPUT type="text" name="cfname" value="${param.cfname}"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD>: <INPUT type="text" name="cmname" value="${param.cmname}"></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="cadd" size="50" value="${param.cadd}"></TD>
				</TR>
				<TR>
					<TD align="center" colspan="2">
					<INPUT type="submit" value="Save Changes">
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