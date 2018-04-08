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
			<jsp:useBean id="service" class="com.prenda.service.StatisticsService"/>
			<form method="post" action="redeemstatrate.pdf">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="bname" value="${branch.name}"/>
				<input type="hidden" name="baddress" value="${branch.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Redeemed Items By Interest Rate Statistics</TH>
				</TR>
				<TR>
					<TH>Interest Rate</TH>					
					<TH colspan="4">Count</TH>
				</TR>
				<TR>
					<TH></TH>
					<TH>All</TH>	
					<TH>This Year</TH>
					<TH>This Month</TH>					
					<TH>Today</TH>
				</TR>
				<c:forEach var="i" begin="0" end="20" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<jsp:setProperty name="service" property="rate" value="${i}"/>
					<jsp:setProperty name="service" property="branchId" value="${user.branchId}"/>
					<jsp:setProperty name="service" property="userId" value="${user.userId}"/>
					<td><c:out value="${i}"/>%</td>
					<td><jsp:setProperty name="service" property="mode" value="99"/><c:out value="${service.redeemCountByInterestRate}"/></td>
					<td><jsp:setProperty name="service" property="mode" value="3"/><c:out value="${service.redeemCountByInterestRate}"/></td>
					<td><jsp:setProperty name="service" property="mode" value="2"/><c:out value="${service.redeemCountByInterestRate}"/></td>
					<td><jsp:setProperty name="service" property="mode" value="1"/><c:out value="${service.redeemCountByInterestRate}"/></td>
				</tr>
				</c:forEach>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>