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
			<TD width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="referer" value="owner/userlist">
			<INPUT type="hidden" name="uid" value="${param.uid}">
			<INPUT type="hidden" name="modtype" value="2">
			<jsp:useBean id="userS" class="com.prenda.service.UserService"/>
			<jsp:setProperty property="userName" name="userS" value="${param.user}"/>
			<jsp:useBean id="branchS" class="com.prenda.service.BranchService"/>
			<jsp:setProperty property="id" name="branchS" value="${userS.branchId}"/>
			<jsp:setProperty property="userId" name="branchS" value="${param.uid}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit User <c:out value="${param.user}"/>
					<INPUT type="hidden" name="user" value="${param.user}">
					</TH>
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
					<c:if test="${userS.level==8}">
						<OPTION value="8" selected>Owner</OPTION>
					</c:if>
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