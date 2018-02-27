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
<jsp:setProperty property="id" name="branches" value="${param.branchid}"/>
			<FORM method="post" action="../BranchModify">
			<INPUT type="hidden" name="branchid" value="${branches.id}">
			<INPUT type="hidden" name="modtype" value="2">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Edit Branch</TH>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD>: <INPUT type="text" name="bname" size="20" value='<c:out value="${branches.name}"/>'></TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD>: <INPUT type="text" name="address" size="50" value='<c:out value="${branches.address}"/>'></TD>
				</TR>
				<TR>
					<TD>Balance</TD>
					<TD>: <INPUT type="text" name="balance" value='<c:out value="${branches.balance}"/>'></TD>
				</TR>
				<TR>
					<TD>PT Number Start</TD>
					<TD>: <INPUT type="text" name="pt" value='<c:out value="${branches.pawnTicket}"/>'></TD>
				</TR>
				<TR>
					<TD>Advance Interest</TD>
					<TD>: <INPUT type="text" name="ai" size="4" value='<c:out value="${branches.advanceInterest}"/>'></TD>
				</TR>
				<TR>
					<TD>Service Charge</TD>
					<TD>: <INPUT type="text" name="sc" size="4" value='<c:out value="${branches.serviceCharge}"/>'></TD>
				</TR>
				<TR>
					<TD>Days Left to Reserve</TD>
					<TD>: <INPUT type="text" name="extend" size="2" value='<c:out value="${branches.minDaysToExtend}"/>'></TD>
				</TR>
				<TR>
					<TD>Reserve Duration</TD>
					<TD>: <INPUT type="text" name="reserve" size="3" value='<c:out value="${branches.reserveDuration}"/>'></TD>
				</TR>
				<TR>
					<TD>Owner</TD>
					<TD>: 
					<sql:query var="owner" dataSource="${prenda}">
					SELECT uid,username FROM users WHERE level>=8
					</sql:query>
					<select name="uid">
					<c:forEach items="${owner.rows}" var="row">
					<c:choose>
					<c:when test="${branch.rows[0].owner==row.uid}">
					<option value='<c:out value="${row.uid}"/>' selected><c:out value="${row.username}"/></option>
					</c:when>
					<c:otherwise>
					<option value='<c:out value="${row.uid}"/>'><c:out value="${row.username}"/></option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</select></TD>
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