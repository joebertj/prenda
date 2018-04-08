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
			<FORM method="post" action="../UserModify.htm">
			<INPUT type="hidden" name="referer" value="manage/userlist">
			<INPUT type="hidden" name="modtype" value="2"> 
			<INPUT type="hidden" name="uid" value="${param.uid}">
			<INPUT type="hidden" name="user" value="${param.user}">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit User <c:out value="${param.user}"/></TH>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD>: <INPUT type="text" name="lname" value="${param.lname}"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD>: <INPUT type="text" name="fname" value="${param.fname}"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD>: <INPUT type="text" name="mname" value="${param.mname}"></TD>
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