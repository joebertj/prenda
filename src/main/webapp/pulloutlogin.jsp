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
<sql:query var="testpawn" dataSource="${prenda}">
SELECT count(pid) as numid 
FROM pawn 
WHERE pid=<c:out value="${param.pid}"/> 
AND branch=<c:out value="${user.branchId}"/>
</sql:query>
<c:if test="${testpawn.rows[0].numid==0}">
	<c:redirect url="redeem.jsp?msg=Item does not exist or does not belong to branch"></c:redirect>
</c:if>	
<sql:query var="testredeem" dataSource="${prenda}">
SELECT count(pid) as numid 
FROM redeem
WHERE pid=<c:out value="${param.pid}" />
</sql:query>
<c:if test="${testredeem.rows[0].numid==1}">
	<c:redirect url="pullout.jsp?msg=Item already redeemed"></c:redirect>
</c:if>
<sql:query var="testforeclose" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE redeem.pid IS NULL
AND pawn.pid=<c:out value="${param.pid}" />
AND ADDDATE(pawn.loan_date,120+15*extend) > NOW()
	<c:if test="${param.bcode==1}">
		<c:out value="AND bcode=1" />
	</c:if>
</sql:query>	
<c:if test="${testforeclose.rows[0].numid==1}">
	<c:redirect url="pullout.jsp?msg=Item not yet foreclosed"></c:redirect>
</c:if>
<sql:query var="testpullout" dataSource="${prenda}">
SELECT count(pid) as numid FROM pullout
WHERE pid=<c:out value="${param.pid}" />
</sql:query>
<c:if test="${testpullout.rows[0].numid==1}">
	<c:redirect url="pullout.jsp?msg=Item already pulled-out"></c:redirect>
</c:if>
<sql:query var="pawn" dataSource="${prenda}">
SELECT pid,loan,loan_date,first_name,last_name,middle_name,service_charge,
ADDDATE(loan_date,120+15*extend) AS expire 
FROM pawn 
LEFT JOIN customer ON pawn.nameid=customer.id
WHERE pid=<c:out value="${param.pid}" />
</sql:query>
			<FORM method="post" action="CheckPullout">
			<TABLE border="1">
				<TR>
					<TH colspan="2">Pullout Details</TH>
				</TR>
				<TR>
					<TD>Pawn ID</TD>
					<TD>: <fmt:formatNumber value="${pawn.rows[0].pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
				</TR>
				<TR>
					<TD>Loan Date</TD>
					<TD>: <fmt:formatDate value="${pawn.rows[0].loan_date}" dateStyle="long"/></TD>
				</TR>
				<TR>
					<TD>Expiry Date</TD>
					<TD>: <fmt:formatDate value="${pawn.rows[0].expire}" dateStyle="long" />
				</TR>
				<TR>
					<TD>Pullout Date</TD>
					<TD>: <jsp:useBean id="now" class="java.util.Date" /> 
					<fmt:formatDate	value="${now}" dateStyle="long" /> 
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
					<TH colspan="100%">Liaison Login</TH>
				</TR>
				<TR>
					<TD>Username</TD>
					<TD><input type="text" name="username"/></TD>
				</TR>
				<TR>
					<TD>Password</TD>
					<TD><input type="password" name="password"/></TD>
				</TR>
				<TR>
					<TD colspan="2" align=center>
							<INPUT type="hidden" name="pid" value="${pawn.rows[0].pid}"> 
							<INPUT type="hidden" name="pulloutdate" value="<%= now.getTime() %>"> 
							<INPUT type="button" value="Cancel" onclick="location.href='pullout.jsp'">
							<INPUT type="submit" value="Continue">
					</TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>