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
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../public/msg.jsp"%>
			<form method="post" action="pulloutlist.jsp">
			<input type="submit" value="Select From Foreclosed Items"/>
			</form>
			<FORM method="post" action="pulloutdetail.jsp">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Pullout</TH>
				</TR>
				<TR>
					<TD>PID</TD>
					<TD><INPUT type="text" name="pid"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Pullout"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>