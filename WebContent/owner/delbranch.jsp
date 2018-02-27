<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="../common/img/logo2.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
			<FORM method="post" action="../BranchModify">Archive Branch <c:out value="${param.bname}" /> <BR>
			<BR>
			Are you sure? 
			<INPUT type="hidden" name="modtype" value="1"> 
			<INPUT type="hidden" name="branchid" value='<c:out value="${param.branchid}"/>'> 
			<INPUT type="hidden" name="bname" value='<c:out value="${param.bname}"/>'> 
			<INPUT name="delresp" type="submit" value="Cancel"> 
			<INPUT name="delresp" type="submit" value="Confirm"></FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>