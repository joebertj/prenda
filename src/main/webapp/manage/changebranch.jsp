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
			<FORM method="post" action="${contextPath}/BranchModify">
			<INPUT type="hidden" name="referer" value="manage/changebranch.jsp">
			<INPUT type="hidden" name="branchid" value="${branch.id}">
			<INPUT type="hidden" name="modtype" value="2">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit Branch</TH>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD>: <INPUT type="text" name="bname" size="20" value="${branch.name}"></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="address" size="50" value="${branch.address}"></TD>
				</TR>
				<TR>
					<TD>Balance</TD>
					<TD>: <INPUT type="text" name="balance" value="${branch.balance}"></TD>
				</TR>
				<TR>
					<TD>PT Number Start</TD>
					<TD>: <INPUT type="text" name="pt" value="${branch.pawnTicket}"></TD>
				</TR>
				<TR>
					<TD>Advance Interest</TD>
					<TD>: <INPUT type="text" name="ai" size="4" value="${branch.advanceInterest}"></TD>
				</TR>
				<TR>
					<TD>Service Charge</TD>
					<TD>: <INPUT type="text" name="sc" size="4" value="${branch.serviceCharge}"></TD>
				</TR>
				<TR>
					<TD>Days Left to Reserve</TD>
					<TD>: <INPUT type="text" name="extend" size="2" value="${branch.minDaysToExtend}"></TD>
				</TR>
				<TR>
					<TD>Reserve Duration</TD>
					<TD>: <INPUT type="text" name="reserve" size="3" value="${branch.reserveDuration}"></TD>
				</TR>
				<TR>
					<TD colspan="2" align="center"><INPUT type="submit" value="Save Changes"></TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>