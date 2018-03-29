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
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="referer" value="admin/userlist">
			<INPUT type="hidden" name="uid" value="${param.uid}">
			<INPUT type="hidden" name="modtype" value="2">
			<jsp:useBean id="userS" class="com.prenda.service.UserService"/>
			<jsp:setProperty property="userName" name="userS" value="${param.user}"/>
			<jsp:useBean id="branchS" class="com.prenda.service.BranchService"/>
			<jsp:setProperty property="id" name="branchS" value="${userS.branchId}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit User</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD>: <INPUT type="text" name="user" value="${param.user}"></TD>
				</TR>
				<TR>
					<TD>Last Name</TD>
					<TD>: <INPUT type="text" name="lname" value="${userS.lastName}"></TD>
				</TR>
				<TR>
					<TD>First Name</TD>
					<TD>: <INPUT type="text" name="fname" value="${userS.firstName}"></TD>
				</TR>
				<TR>
					<TD>Middle Name</TD>
					<TD>: <INPUT type="text" name="mname" value="${userS.middleName}"></TD>
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
					<c:choose>
						<c:when test="${userS.level==1}">
						<OPTION value="1" selected>Encoder</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="1">Encoder</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userS.level==3}">
						<OPTION value="3" selected>Liaison</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="3">Liaison</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userS.level==7}">
						<OPTION value="7" selected>Manager</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="7">Manager</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userS.level==8}">
						<OPTION value="8" selected>Owner</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="8">Owner</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userS.level==9}">
						<OPTION value="9" selected>Admin</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="9">Admin</OPTION>
						</c:otherwise>
					</c:choose>
					</SELECT></TD>
				</TR>
				<TR>
					<TD>Branch</TD>
					<TD>: 
					<select name="branch">
					<c:forEach var="row" items="${branchS.allBranches}">
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
					<TD colspan="2" align="center"><INPUT type="submit" value="Save Changes"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>