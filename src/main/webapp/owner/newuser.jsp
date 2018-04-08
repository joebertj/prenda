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
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="referer" value="owner/newuser">
			<INPUT type="hidden" name="modtype" value="0">
			<jsp:useBean id="userS" class="com.prenda.service.UserService"/>
			<jsp:setProperty property="userName" name="userS" value="${authenticated}"/>
			<jsp:useBean id="branchS" class="com.prenda.service.BranchService"/>
			<jsp:setProperty property="id" name="branchS" value="${userS.branchId}"/>
			<jsp:setProperty property="userId" name="branchS" value="${userS.userId}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Add New User</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user"></TD>
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
				<TR>
					<TD>Level</TD>
					<TD>: <SELECT name="level">
						<OPTION value="1">Encoder</OPTION>
						<OPTION value="3">Liaison</OPTION>
						<OPTION value="7">Manager</OPTION>
					</SELECT></TD>
				</TR>
				<TR>
					<TD>Branch</TD>
					<TD>: 
					<select name="branch">
					<c:forEach var="row" items="${branchS.sisterBranches}">
					<c:choose>
						<c:when test="${userS.branchId==row.id}">
						<option value="${row.id}" selected><c:out value="${row.name}"/></option>
						</c:when>
						<c:otherwise>
						<option value="${row.id}"><c:out value="${row.name}"/></option>
						</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
					</TD>
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