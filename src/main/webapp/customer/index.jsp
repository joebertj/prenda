<%@include file="../public/header.jsp"%>
</head>
<body>

<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../public/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
<%@include file="../public/credits.jsp"%>
</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>