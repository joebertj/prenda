<%@include file="header.jsp"%>
</head>
<body>
<% session.invalidate(); %>
<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/common/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="navi.jsp"%></TD>
		</TR>
		<TR>
			<td width="200"></td>
			<TD align="center">
<%@include file="msg.jsp"%>
<fmt:bundle basename="messages">
	<fmt:message key="common.logout" />
</fmt:bundle>
<br/><br/>
<fmt:bundle basename="messages">
	<fmt:message key="common.thanks" />
</fmt:bundle> 
</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>