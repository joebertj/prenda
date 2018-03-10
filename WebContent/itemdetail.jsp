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
<sql:query var="testredeem" dataSource="${prenda}">
SELECT count(pid) as numid FROM redeem
WHERE pid=<c:out value="${param.pid}" />
</sql:query>
<c:if test="${testredeem.rows[0].numid==1}">
	<c:redirect url="item.jsp?msg=Item already redeemed"></c:redirect>
</c:if>	
<sql:query var="testforeclose" dataSource="${prenda}">
SELECT count(pid) as numid FROM pawn 
WHERE pid=<c:out value="${param.pid}" />
AND ADDDATE(pawn.loan_date,120+15*extend) <= NOW()
</sql:query>
<c:if test="${testforeclose.rows[0].numid==1}">
	<c:redirect url="item.jsp?msg=Item already foreclosed"></c:redirect>
</c:if>
<sql:query var="pawn" dataSource="${prenda}">
SELECT pid,nameid,loan_date,loan,rate,pawn.service_charge,pawn.extend,first_name,middle_name,last_name,
branch.extend as bextend,
ADDDATE(loan_date,120+15*pawn.extend) AS expire,
DATEDIFF(ADDDATE(loan_date,120+15*pawn.extend),NOW()) AS dleft 
FROM pawn
LEFT JOIN customer ON pawn.nameid=customer.id
LEFT JOIN interest ON pawn.branch=interest.interestid 
LEFT JOIN branch ON pawn.branch=branch.branchid
WHERE pid=<c:out value="${param.pid}" />
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Item Details</TH>
				</TR>
				<TR>
					<TD>Pawn ID</TD>
					<TD>: <fmt:formatNumber value="${pawn.rows[0].pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
				</TR>
				<TR>
					<TD>Loan Date</TD>
					<TD>: <fmt:formatDate value="${pawn.rows[0].loan_date}"
						dateStyle="long" /></TD>
				</TR>
				<TR>
					<TD>Expiration</TD>
					<TD>: <fmt:formatDate value="${pawn.rows[0].expire}" dateStyle="long"/>
					</TD>
				</TR>
				<TR>
					<TD>Days Left</TD>
					<TD>: <c:set var="dleft" value="${pawn.rows[0].dleft}"/>
					<c:out value="${dleft}" />
					</TD>
				</TR>
				<TR>
					<TD>Extensions</TD>
					<TD>: <c:out value="${pawn.rows[0].extend}" />
					</TD>
				</TR>
				<TR>
					<TD>Customer</TD>
					<TD>: <c:out value="${pawn.rows[0].last_name}"/>, <c:out value="${pawn.rows[0].first_name}"/> <c:out value="${pawn.rows[0].middle_name}"/></TD>
				</TR>
				<TR>
					<TD>Loan</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].loan}" /></TD>
				</TR>
				<TR>
					<TD>Rate</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].rate}" /></TD>
				</TR>
				<TR>
					<TD>Interest</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].loan*pawn.rows[0].rate/100}" /></TD>
				</TR>
				<TR>
					<TD>Net</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${(1 + pawn.rows[0].rate/100 )*pawn.rows[0].loan+pawn.rows[0].service_charge}" />
					</TD>
				</TR>
				<c:set var="allow" scope="session" value="${false}"/>
				<c:if test="${dleft-1<pawn.rows[0].bextend}">
				<c:set var="allow" scope="session" value="${true}"/>
				<TR>
					<TD colspan=2 align=center>
						<FORM method=post action=ExtendItem>
						<INPUT type=hidden name=pid value="${pawn.rows[0].pid}" >
						<INPUT type=submit value=Extend>
						</FORM>
					</TD>
				</TR>
				</c:if>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>