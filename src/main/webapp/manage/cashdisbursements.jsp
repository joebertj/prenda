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
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
<c:set var="perpage" value="${pageS.disburse}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(journal.journalid) as numid FROM journal WHERE journal_date=CURDATE()
<c:if test="${user.level<9}">
AND branchid=<c:out value="${user.branchId}"/>
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
				<A href='cashdisbursements.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='cashdisbursements.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='cashdisbursements.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
			<jsp:useBean id="cd" class="com.prenda.service.CashDisbursementService"/>
			<form name="disburse" action="cashdisbursements.pdf" method="post">
			<input type="hidden" name="branch" value="${user.branchId}"/>
			<TABLE>
				<TR>
					<TH colspan="100%">Cash Disbursements</TH>
				</TR>
				<TR>
					<TD>Group</TD>
					<TD>Code</TD>
					<TD>Account</TD>
					<TD>Particulars</TD>
					<TD>Amount</TD>
				</TR>
				<c:forEach var="row" items="${cd.disbursement}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.journalGroup}"/></TD>
					<TD><c:out value="${row.accountCode}"/></TD>
					<TD><c:out value="${row.accountName}"/></TD>
					<TD><c:out value="${row.description}"/></TD>
					<TD><c:out value="${row.amount}"/></TD>
				</c:forEach>	
				</TR>
				<TR>
					<TD colspan="100%">
					<input type="submit" value="Print"/>
					</TD>
				</TR>
			</TABLE>	
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>