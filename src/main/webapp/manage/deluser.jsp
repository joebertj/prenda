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
<c:if test="${param.level==9}">
	<sql:query var="admin" dataSource="${prenda}">
	SELECT count(level) as numlvl FROM users WHERE level=9
	</sql:query>
	<c:if test="${admin.rows[0].numlvl==1}">
		<c:redirect url="userlist.jsp?msg=At least one admin must be present">
		</c:redirect>
	</c:if>
</c:if>
			<FORM method="post" action="${contextPath}/UserModify.htm">Delete User <c:out value="${param.user}" /> <br/>
			<br/>
			Are you sure? 
			<INPUT type="hidden" name="referer" value="manage/userlist"> 
			<INPUT type="hidden" name="modtype" value="1"> 
			<INPUT type="hidden" name="uid" value="${param.uid}"> 
			<INPUT type="hidden" name="user" value="${param.user}">
			<INPUT name="delresp" type="submit" value="Cancel"> 
			<INPUT name="delresp" type="submit" value="Confirm"></FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>