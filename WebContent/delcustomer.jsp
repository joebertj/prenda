<%@include file="common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="common/img/logo2.png" width="135"
				height="123"></TD>
			<TD><%@include file="common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="common/msg.jsp"%>
			<FORM method="post" action="CustomerModify">Archive Customer 
				<c:out value="${param.clname}" />,  <c:out value="${param.cfname}"/> <c:out value="${param.cmname}"/><BR>
			<BR>
			Are you sure? 
			<INPUT type="hidden" name="modtype" value="1"> 
			<INPUT type="hidden" name="cid" value='<c:out value="${param.cid}"/>'> 
			<INPUT type="hidden" name="clname" value='<c:out value="${param.clname}"/>'>
			<INPUT type="hidden" name="cfname" value='<c:out value="${param.cfname}"/>'>
			<INPUT type="hidden" name="cmname" value='<c:out value="${param.cmname}"/>'>
			<INPUT name="delresp" type="submit" value="Cancel"> 
			<INPUT name="delresp" type="submit" value="Confirm">
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>