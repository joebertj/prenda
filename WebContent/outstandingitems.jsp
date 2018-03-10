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
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
<c:set var="perpage" value="${pageS.outstanding}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(pawn.pid) as numid FROM pawn
LEFT JOIN redeem ON pawn.pid=redeem.pid
<c:if test="${users.rows[0].level==8}">
LEFT JOIN branch ON pawn.branch=branch.branchid 
LEFT JOIN users ON branch.owner=users.uid 
</c:if>
WHERE redeem.pid IS NULL
<c:if test="${users.rows[0].level==8}">
AND users.username="${authenticated}"
</c:if>
AND NOW() < ADDDATE(pawn.loan_date,120+15*pawn.extend) 
<c:if test="${users.rows[0].level<8}">
AND pawn.branch=<c:out value="${users.rows[0].branch}"/>
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
				<A href='outstandingitems.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='outstandingitems.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='outstandingitems.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="pawn" dataSource="${prenda}">
SELECT pawn.pid,pawn.branch,pawn.loan_date,nameid,loan,pawn.service_charge,bpid,genkey.password,first_name,last_name,middle_name,rate,pt_number,pt,
pawn.create_date AS cdate,
ADDDATE(pawn.loan_date,120+15*pawn.extend) AS expire
FROM pawn
LEFT JOIN genkey ON pawn.pid=genkey.pid
LEFT JOIN customer ON pawn.nameid=customer.id
LEFT JOIN redeem ON pawn.pid=redeem.pid
LEFT JOIN interest ON pawn.branch=interest.interestid
LEFT JOIN branch ON pawn.branch=branch.branchid
<c:if test="${users.rows[0].level==8}">
LEFT JOIN users ON branch.owner=users.uid 
</c:if>
WHERE redeem.pid IS NULL
<c:if test="${users.rows[0].level==8}">
AND users.username="${authenticated}"
</c:if>
AND NOW() < ADDDATE(pawn.loan_date,120+15*pawn.extend) 
AND (day=DATEDIFF(NOW(),pawn.loan_date) OR (day=34 AND DATEDIFF(NOW(),pawn.loan_date)>34))
<c:if test="${users.rows[0].level<8}">
AND pawn.branch=<c:out value="${users.rows[0].branch}"/>
</c:if>
	<c:if test="${param.bcode==1}">
		<c:out value="AND bcode=1" />
	</c:if>
ORDER BY pawn.pid
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out
					value="${perpage}" />
			</sql:query>
			<form method="post" action="outstanding.pdf">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="bname" value="${branches.name}"/>
				<input type="hidden" name="baddress" value="${branches.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Outstanding Items 
					</TH>
				</TR>
				<TR>
					<TH colspan="8"></TH>
					<TH colspan="3">If Redeemed Now</TH>
				</TR>
				<TR>
					<TH>Pawn ID</TH>
					<TH>Branch PID</TH>
					<TH>PT Number</TH>
					<TH>Password</TH>
					<TH>Loan Date</TH>
					<TH>Expiry Date</TH>
					<TH>Customer</TH>
					<TH>Loan Amount</TH>
					<TH>Rate</TH>
					<TH>Interest</TH>
					<TH>Net</TH>
				</TR>
				<c:forEach var="row" items="${pawn.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#3366FF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><fmt:formatNumber value="${row.pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
					<TD><fmt:formatNumber value="${row.branch}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${row.bpid}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD><fmt:formatNumber value="${row.pt}" minIntegerDigits="6" groupingUsed="false"/></TD>
					<TD><c:out value="${row.password}" /></TD>
					<TD><fmt:formatDate value="${row.loan_date}" dateStyle="long"/></TD>
					<TD><fmt:formatDate value="${row.expire}" dateStyle="long" /></TD>
					<TD><c:out value="${row.last_name}"/>, <c:out value="${row.first_name}"/> <c:out value="${row.middle_name}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loan}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.rate}" />%					</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loan*row.rate/100}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${(1 + row.rate/100 )*row.loan+row.service_charge}" /></TD>
					<%@include file="editminute.jsp"%>
				</TR>
				</c:forEach>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>