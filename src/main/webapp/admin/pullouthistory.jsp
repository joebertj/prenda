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
<c:if test="${param.type==-1}">
<img src="${contextPath}/resources/img/pullouthistory-1.jpg">
</c:if>
<c:if test="${param.type==0}">
<img src="${contextPath}/resources/img/pullouthistory0.jpg">
</c:if>
</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>