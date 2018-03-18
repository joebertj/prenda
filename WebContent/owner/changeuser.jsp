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
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="referer" value="manage/userlist">
			<INPUT type="hidden" name="modtype" value="2">
			<INPUT type="hidden" name="uid" value="${param.uid}">
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
					<TD>Password</TD>
					<TD>: <INPUT type="password" name="pass" value="${param.password}"></TD>
				</TR>
				<TR>
					<TD>Verify Password</TD>
					<TD>: <INPUT type="password" name="pass2" value="${param.password}">
					<INPUT type="hidden" name="pass3" value="${param.password}"></TD>
				</TR>
				<TR>
					<TD>Level</TD>
					<TD>: <SELECT name="level">
					<c:choose>
						<c:when test="${param.level==1}">
						<OPTION value="1" selected>Encoder</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="1">Encoder</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${param.level==3}">
						<OPTION value="3" selected>Liaison</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="3">Liaison</OPTION>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${param.level==7}">
						<OPTION value="7" selected>Manager</OPTION>
						</c:when>
						<c:otherwise>
						<OPTION value="7">Manager</OPTION>
						</c:otherwise>
					</c:choose>
					<c:if test="${param.level==8}">
						<OPTION value="8" selected>Owner</OPTION>
					</c:if>
					</SELECT></TD>
				</TR>
				<TR>
					<TD>Branch</TD>
					<TD>: 
<sql:query var="branches" dataSource="${prenda}">
SELECT branchid,name FROM branch
LEFT JOIN users ON branch.owner=users.uid
WHERE users.username="${authenticated}"
</sql:query>
					<select name="branch">
					<c:forEach var="row" items="${branches.rows}">
					<c:choose>
						<c:when test="${param.branch==row.branchid}">
						<option value="${row.branchid}" selected><c:out value="${row.name}"/></option>
						</c:when>
						<c:otherwise>
						<option value="${row.branchid}"><c:out value="${row.name}"/></option>
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