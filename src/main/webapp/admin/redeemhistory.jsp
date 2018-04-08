<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" class="main" width="100%">
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
<c:if test="${param.type==-1}">
<img src="${contextPath}/resources/img/redeemhistory-1.jpg">
</c:if>
<c:if test="${param.type==0}">
<img src="${contextPath}/resources/img/redeemhistory0.jpg">
</c:if>
</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>