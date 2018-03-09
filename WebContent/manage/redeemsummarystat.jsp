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
<sql:query var="all" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldaily" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamount" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdaily" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allA" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan<=500
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldailyA" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan<=500
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountA" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan<=500
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdailyA" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan<=500
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allB" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>500 AND loan<=1000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldailyB" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>500 AND loan<=1000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountB" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>500 AND loan<=1000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdailyB" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>500 AND loan<=1000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allC" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>1000 AND loan<=2000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldailyC" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>1000 AND loan<=2000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountC" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>1000 AND loan<=2000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdailyC" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>1000 AND loan<=2000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allD" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>2000 AND loan<=5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldailyD" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>2000 AND loan<=5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountD" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>2000 AND loan<=5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdailyD" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan_date=NOW()
AND loan>2000 AND loan<=5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allE" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="alldailyE" dataSource="${prenda}">
SELECT count(pawn.pid) as numid 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountE" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet 
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND loan>5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountdailyE" dataSource="${prenda}">
SELECT sum(loan) as sumloan,sum(interest) as suminterest, sum(loan+interest) as sumnet
FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
WHERE pawn.pid=redeem.pid
AND redeem_date=NOW()
AND loan>5000
<c:if test="${users.rows[0].level<9}">
AND branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
			<form method="post" action="redeemstat.pdf">
				<input type="hidden" name="branch" value="${users.rows[0].branch}"/>
				<input type="hidden" name="bname" value="${branch.rows[0].name}"/>
				<input type="hidden" name="baddress" value="${branch.rows[0].address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Redeemed Items Statistics</TH>
				</TR>
				<TR>
					<TH></TH>
					<TH colspan="2">Count</TH>					
					<TH colspan="2">Loan Amount</TH>
					<TH colspan="2">Interest</TH>
					<TH colspan="2">Net</TH>
				</TR>
				<TR>
					<TH></TH>
					<TH>All</TH>					
					<TH>Today</TH>
					<TH>All</TH>					
					<TH>Today</TH>
					<TH>All</TH>					
					<TH>Today</TH>
					<TH>All</TH>					
					<TH>Today</TH>
				</TR>
				<TR>
					<TD>Loan At Most Php 500</TD>
					<TD align="right"><c:out value="${allA.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldailyA.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountA.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyA.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountA.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyA.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountA.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyA.rows[0].sumnet}"/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Loan Greater Than Php 500 And At Most Php 1000</TD>
					<TD align="right"><c:out value="${allB.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldailyB.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountB.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyB.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountB.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyB.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountB.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyB.rows[0].sumnet}"/></TD>
				</TR>
				<TR>
					<TD>Loan Greater Than Php 1000 And At Most Php 2000</TD>
					<TD align="right"><c:out value="${allC.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldailyC.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountC.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyC.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountC.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyC.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountC.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyC.rows[0].sumnet}"/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Loan Greater Than Php 2000 And At Most Php 5000</TD>
					<TD align="right"><c:out value="${allD.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldailyD.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountD.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyD.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountD.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyD.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountD.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyD.rows[0].sumnet}"/></TD>
				</TR>
				<TR>
					<TD>Loan Greater Than Php 5000</TD>
					<TD align="right"><c:out value="${allE.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldailyE.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountE.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyE.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountE.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyE.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountE.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdailyE.rows[0].sumnet}"/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Total</TD>
					<TD align="right"><c:out value="${all.rows[0].numid}"/></TD>
					<TD align="right"><c:out value="${alldaily.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamount.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdaily.rows[0].sumloan}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamount.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdaily.rows[0].suminterest}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamount.rows[0].sumnet}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountdaily.rows[0].sumnet}"/></TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>