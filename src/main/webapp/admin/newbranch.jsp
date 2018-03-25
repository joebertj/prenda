<%@include file="../common/header.jsp"%>
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
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
			<FORM method="post" action="${contextPath}/BranchModify">
			<INPUT type="hidden" name="modtype" value="0">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Add New Branch</TH>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD>: <INPUT type="text" name="bname" size="20"></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="address" size="50"></TD>
				</TR>
				<TR>
					<TD>Balance</TD>
					<TD>: <INPUT type="text" name="balance"></TD>
				</TR>
				<TR>
					<TD>PT Number Start</TD>
					<TD>: <INPUT type="text" name="pt" size="6" value="0"></TD>
				</TR>
				<TR>
					<TD>Advance Interest</TD>
					<TD>: <INPUT type="text" name="ai" size="4" value="0.00"></TD>
				</TR>
				<TR>
					<TD>Service Charge</TD>
					<TD>: <INPUT type="text" name="sc" size="4" value="0.00"></TD>
				</TR>
				<TR>
					<TD>Days Left to Reserve</TD>
					<TD>: <INPUT type="text" name="extend" size="2" value="15"></TD>
				</TR>
				<TR>
					<TD>Reserve Duration</TD>
					<TD>: <INPUT type="text" name="reserve" size="3" value="15"></TD>
				</TR>
				<TR>
					<TD>Owner</TD>
					<TD>: 
					<sql:query var="owner" dataSource="${prenda}">
					SELECT uid,username FROM users WHERE level>=8
					</sql:query>
					<select name="uid">
					<c:forEach items="${owner.rows}" var="row">
					<option value="${row.uid}"><c:out value="${row.username}"/></option>
					</c:forEach>
					</select></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Add"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>