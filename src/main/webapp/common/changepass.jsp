<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" class="main" width="100%">
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
			<FORM method="post" action="${contextPath}/UserModify.htm">
			<INPUT type="hidden" name="modtype" value="2"> 
			<input type="hidden" name="referer" value="common/changepass"/>
			<c:choose>
			<c:when test="${param.uid==null && param.user==null}">
				<INPUT type="hidden" name="uid"	value="${user.userId}"/> 
				<INPUT type="hidden" name="user" value="${authenticated}"/>
				<c:set var="passtochange" value="${authenticated}"/>
			</c:when>
			<c:otherwise>
				<INPUT type="hidden" name="uid"	value="${param.uid}"/> 
				<INPUT type="hidden" name="user" value="${param.user}"/>
				<c:set var="passtochange" value="${param.user}"/>
			</c:otherwise>
			</c:choose>
			<INPUT type="hidden" name="branch" value="${user.branchId}">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Change Password <c:out value="${passtochange}" /></TH>
				</TR>
				<c:if test="${param.uid==null && param.user==null}">
				<TR>
					<TD>Old Password</TD>
					<TD>: <INPUT type="password" name="pass"></TD>
				</TR>
				</c:if>
				<TR>
					<TD>New Password</TD>
					<TD>: <INPUT type="password" name="pass1"></TD>
				</TR>
				<TR>
					<TD>Verify Password</TD>
					<TD>: <INPUT type="password" name="pass2"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Change">
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