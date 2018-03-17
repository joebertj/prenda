<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="${contextPath}/common/js/pawn.js"></script>
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
<sql:query var="page" dataSource="${prenda}">
SELECT foreclose FROM page WHERE pageid=<c:out value="${user.branchId}"/>
</sql:query>
<c:set var="perpage" value="${page.rows[0].foreclose}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
<c:if test="${user.level==8}">
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
</c:if>
WHERE redeem.pid IS NULL
<c:if test="${user.level==8}">
AND users.username="${authenticated}"
</c:if>
AND pullout.pid IS NULL
AND ADDDATE(pawn.loan_date,120+15*pawn.extend) <= NOW()
<c:if test="${user.level<8}">
AND branch=<c:out value="${user.branchId}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
</sql:query>
<c:set var="numid" value="${pageable.rows[0].numid}" />
<c:set var="pages" value="${numid/perpage}" />
<c:set var="adjust" value="${numid % perpage}" />
<c:if test="${adjust!=0}">
				<c:set var="pages" value="${pages-(adjust/perpage)+1}" />
			</c:if>
<c:set var="pagenum" value="${param.pagenum}" />
<c:if test="${pagenum==null || pagenum<1 || pagenum>pages}">
				<c:set var="pagenum" value="1" />
			</c:if>
<br/>
			Page 
<c:if test="${pagenum>1}">
				<A href='forecloseditems.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='forecloseditems.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='forecloseditems.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="foreclose" dataSource="${prenda}">
SELECT pawn.pid,pawn.branch,pawn.loan_date,loan,pawn.service_charge,bpid,first_name,last_name,middle_name,rate,
ADDDATE(pawn.loan_date,120+15*pawn.extend) AS expire 
FROM pawn
LEFT JOIN customer ON pawn.nameid=customer.id
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN pullout ON pawn.pid=pullout.pid
LEFT JOIN interest ON pawn.branch=interest.interestid
<c:if test="${user.level==8}">
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
</c:if>
WHERE (day=DATEDIFF(NOW(),pawn.loan_date) OR (day=34 AND DATEDIFF(NOW(),pawn.loan_date)>34))
<c:if test="${user.level==8}">
AND users.username="${authenticated}"
</c:if>
AND redeem.pid IS NULL
AND pullout.pid IS NULL
AND ADDDATE(pawn.loan_date,120+15*pawn.extend) <= NOW()
<c:if test="${user.level<8}">
AND pawn.branch=<c:out value="${user.branchId}"/>
</c:if>
<c:if test="${param.bcode==1}">
<c:out value="AND bcode=1" />
</c:if>
ORDER BY pawn.pid
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out value="${perpage}" />
</sql:query>
			<form name="pullout" action="pulloutlistlogin.jsp" method="post">
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Foreclosed Items</TH>
				</TR>
				<TR>
					<TH colspan="100%"><jsp:useBean id="now" class="java.util.GregorianCalendar" />
					Items Expired As of <fmt:formatDate value="${now.time}" dateStyle="long" />
					with Loan Date On or Before 
					<% now.add(java.util.GregorianCalendar.DAY_OF_MONTH, -120); %>
					<fmt:formatDate value="${now.time}" dateStyle="long" /></TH>
				</TR>
				<TR>
					<TH><input type="checkbox" name="pid" value="-1" onClick='checkAll(<c:out value="${perpage}"/>,this.form);'/></TH>
					<TH>Pawn ID</TH>
					<TH>Branch PID</TH>
					<TH>Loan Date</TH>
					<TH>Foreclosed Date</TH>
					<TH>Customer</TH>
					<TH>Loan</TH>
				</TR>
				<c:forEach var="row" items="${foreclose.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><input type="checkbox" name="pid" value="${row.pid}" onClick="uncheckAll(this.checked,this.form);"/></TD>
					<TD><fmt:formatNumber value="${row.pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
					<TD><fmt:formatNumber value="${row.branch}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${row.bpid}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD><fmt:formatDate value="${row.loan_date}" dateStyle="long" /></TD>
					<TD><fmt:formatDate value="${row.expire}" dateStyle="long" /></TD>
					<TD><c:out value="${row.last_name}"/>, <c:out value="${row.first_name}"/> <c:out value="${row.middle_name}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loan}" /></TD>
				</TR>
				</c:forEach>
				<TR>
					<TD colspan="100%"><input type="submit" value="Pullout Selected Items"/></TD>
				</TR>
			</TABLE>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>