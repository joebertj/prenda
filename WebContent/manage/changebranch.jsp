<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/common/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
			<FORM method="post" action="${contextPath}/BranchModify">
			<INPUT type="hidden" name="branchid" value="${branches.id}">
			<INPUT type="hidden" name="modtype" value="2">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit Branch</TH>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD>: <INPUT type="text" name="bname" size="20" value="${branches.name}"></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="address" size="50" value="${branches.address}"></TD>
				</TR>
				<TR>
					<TD>Balance</TD>
					<TD>: <INPUT type="text" name="balance" value="${branches.balance}"></TD>
				</TR>
				<TR>
					<TD>PT Number Start</TD>
					<TD>: <INPUT type="text" name="pt" value="${branches.pawnTicket}"></TD>
				</TR>
				<TR>
					<TD>Advance Interest</TD>
					<TD>: <INPUT type="text" name="ai" size="4" value="${branches.advanceInterest}"></TD>
				</TR>
				<TR>
					<TD>Service Charge</TD>
					<TD>: <INPUT type="text" name="sc" size="4" value="${branches.serviceCharge}"></TD>
				</TR>
				<TR>
					<TD>Days Left to Reserve</TD>
					<TD>: <INPUT type="text" name="extend" size="2" value="${branches.minDaysToExtend}"></TD>
				</TR>
				<TR>
					<TD>Reserve Duration</TD>
					<TD>: <INPUT type="text" name="reserve" size="3" value="${branches.reserveDuration}"></TD>
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