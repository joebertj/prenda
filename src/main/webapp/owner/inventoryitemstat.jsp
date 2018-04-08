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
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
<sql:query var="all" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamount" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allA" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan<=500
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountA" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan<=500
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allB" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>500 AND loan<=1000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountB" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>500 AND loan<=1000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allC" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>1000 AND loan<=2000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountC" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>1000 AND loan<=2000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allD" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>2000 AND loan<=5000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountD" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>2000 AND loan<=5000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allE" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>5000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<sql:query var="allamountE" dataSource="${prenda}">
SELECT sum(loan) as sumloan FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
WHERE users.username="${authenticated}"
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND loan>5000
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
			<form method="post" action="inventorystat.pdf">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="bname" value="${branch.name}"/>
				<input type="hidden" name="baddress" value="${branch.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Inventory Items Statistics</TH>
				</TR>
				<TR>
					<TH></TH>
					<TH>Count</TH>					
					<TH>Loan Amount</TH>
				</TR>
				<TR>
					<TD>Loan At Most Php 500</TD>
					<TD align="right"><c:out value="${allA.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountA.rows[0].sumloan}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Loan Greater Than Php 500 And At Most Php 1000</TD>
					<TD align="right"><c:out value="${allB.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountB.rows[0].sumloan}"/></TD>
				</TR>
				<TR>
					<TD>Loan Greater Than Php 1000 And At Most Php 2000</TD>
					<TD align="right"><c:out value="${allC.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountC.rows[0].sumloan}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Loan Greater Than Php 2000 And At Most Php 5000</TD>
					<TD align="right"><c:out value="${allD.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountD.rows[0].sumloan}"/></TD>
				</TR>
				<TR>
					<TD>Loan Greater Than Php 5000</TD>
					<TD align="right"><c:out value="${allE.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamountE.rows[0].sumloan}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Total</TD>
					<TD align="right"><c:out value="${all.rows[0].numid}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${allamount.rows[0].sumloan}"/></TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>