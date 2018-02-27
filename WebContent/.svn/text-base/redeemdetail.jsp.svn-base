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
<c:choose>
	<c:when test="${param.pid>0}">
		<c:set var="pid" value="${param.pid}"/>
	</c:when>
	<c:otherwise>
		<sql:query var="testpt" dataSource="${prenda}">
		SELECT pid FROM pawn WHERE pt=<c:out value="${param.pt}"/>
		</sql:query>
		<c:set var="pid" value="${testpt.rows[0].pid}"/>
	</c:otherwise>
</c:choose>
<sql:query var="testpawn" dataSource="${prenda}">
SELECT count(pid) as numid FROM pawn WHERE pid=<c:out value="${pid}"/> AND branch=<c:out value="${users.rows[0].branch}"/>
</sql:query>
<c:if test="${testpawn.rows[0].numid==0}">
	<c:redirect url="redeem.jsp?msg=Item does not exist or does not belong to branch"></c:redirect>
</c:if>	
<sql:query var="testredeem" dataSource="${prenda}">
SELECT count(pid) as numid FROM redeem
WHERE pid=<c:out value="${pid}" />
</sql:query>
<c:if test="${testredeem.rows[0].numid==1}">
	<c:redirect url="redeem.jsp?msg=Item already redeemed"></c:redirect>
</c:if>	
<sql:query var="testforeclose" dataSource="${prenda}">
SELECT count(pid) as numid FROM pawn 
WHERE pid=<c:out value="${pid}" />
AND ADDDATE(pawn.loan_date,120+15*extend) <= NOW()
</sql:query>
<c:if test="${testforeclose.rows[0].numid==1}">
	<c:redirect url="redeem.jsp?msg=Item already foreclosed"></c:redirect>
</c:if>	
<sql:query var="pawn" dataSource="${prenda}">
SELECT pid,loan_date,loan,rate,first_name,last_name,middle_name,service_charge
FROM pawn 
LEFT JOIN customer ON pawn.nameid=customer.id
LEFT JOIN interest ON pawn.branch=interest.interestid
WHERE (day=DATEDIFF(NOW(),loan_date) OR (day=34 AND DATEDIFF(NOW(),loan_date)>34))
AND pid=<c:out value="${pid}" />
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="2">Redeem Details</TH>
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
					<TD>Redeem Date</TD>
					<TD>: <jsp:useBean id="now" class="java.util.Date" /> 
					<fmt:formatDate	value="${now}" dateStyle="long" /> </TD>
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
					<TD>Interest Rate</TD>
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
				<TR>
					<TD colspan="2" align=center>
					<FORM method="post" action="CheckRedeem">
						<INPUT type="hidden" name="pid" value='<c:out value="${pawn.rows[0].pid}"/>'> 
						<INPUT type="hidden" name="branch" value='<c:out value="${users.rows[0].branch}"/>'> 
						<INPUT type="hidden" name="redeemdate" value="<%= now.getTime() %>"> 
						<INPUT type="hidden" name="interest" value='<c:out value="${pawn.rows[0].loan*pawn.rows[0].rate/100}"/>'/>
						<INPUT type="hidden" name="net" value='<c:out value="${(1 + pawn.rows[0].rate/100 )*pawn.rows[0].loan+pawn.rows[0].service_charge}" />'/>
						<INPUT type="button" value="Cancel" onclick="location.href='redeem.jsp'">
						<INPUT type="submit" value="Continue">
					</FORM>
					</TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>