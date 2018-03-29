<%@include file="../common/header.jsp"%>
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
<%@include file="../common/msg.jsp"%>
			<form method="post" action="redeemstatrate.pdf">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="userid" value="${user.userId}"/>
				<input type="hidden" name="bname" value="${branch.name}"/>
				<input type="hidden" name="baddress" value="${branch.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Redeemed Items By Rate Statistics</TH>
				</TR>
				<TR>
					<TH>Rate</TH>					
					<TH colspan="4">Count</TH>
				</TR>
				<TR>
					<TH></TH>
					<TH>All</TH>	
					<TH>This Year</TH>
					<TH>This Month</TH>					
					<TH>Today</TH>
				</TR>
				<c:forEach var="i" begin="1" end="20" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<td><c:out value="${i}"/>%</td>
					<td><c:out value="${redeemstat[i-1][0]}"/></td>
					<td><c:out value="${redeemstat[i-1][1]}"/></td>
					<td><c:out value="${redeemstat[i-1][2]}"/></td>
					<td><c:out value="${redeemstat[i-1][3]}"/></td>
				</tr>
				</c:forEach>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>