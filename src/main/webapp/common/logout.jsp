<%@include file="header.jsp"%>
</head>
<body>
<% session.invalidate(); %>
<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="navipub.jsp"%></TD>
		</TR>
		<TR>
			<td width="200"></td>
			<TD align="center">
<%@include file="msg.jsp"%>
<<spring:message code="common.logout" />
<br/><br/>
<spring:message code="common.thanks" />
</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>