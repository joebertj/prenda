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
<jsp:setProperty name="pageS" property="branchId" value="${branch.id}" />
<c:set var="perpage" value="${pageS.redeem}"/>
<c:set var="pagenum" value="${param.pagenum}" />
<c:if test="${pagenum==null || pagenum<1 || pagenum>pages}">
	<c:set var="pagenum" value="1" />
</c:if>
<c:set var="mode" value="${param.mode}" />
<c:if test="${mode==null}">
	<c:set var="mode" value="1" />
</c:if>
<jsp:useBean id="redeem" class="com.prenda.service.RedeemService"/>
<jsp:setProperty name="redeem" property="level" value="${user.level}"/>
<jsp:setProperty name="redeem" property="branchId" value="${user.branchId}"/>
<jsp:setProperty name="redeem" property="userId" value="${user.id}"/>
<jsp:setProperty name="redeem" property="mode" value="${mode}"/>
<jsp:setProperty name="redeem" property="sort" value="redeem.pid"/>
<jsp:setProperty name="redeem" property="order" value="1"/>
<jsp:setProperty name="redeem" property="page" value="${pagenum}"/>
<jsp:setProperty name="redeem" property="pageSize" value="${perpage}"/>
<c:set var="numid" value="${redeem.allRedeemCount}" />
<c:set var="pages" value="${numid/perpage}" />
<c:set var="adjust" value="${numid % perpage}" />
<c:if test="${adjust>0}">
				<c:set var="pages" value="${pages-(adjust/perpage)+1}" />
			</c:if>
<br/>
			Page 
<c:if test="${pagenum>1}">
				<A href='redeemsummary.jsp?pagenum=<c:out value="${pagenum-1}"/>&mode=<c:out	value="${mode}" />'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='redeemsummary.jsp?pagenum=<c:out value="${i}"/>&mode=<c:out	value="${mode}" />'><c:out	value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<pages-(adjust/perpage)}">
				<A href='redeemsummary.jsp?pagenum=<c:out value="${pagenum+1}"/>&mode=<c:out	value="${mode}" />'>next</A>
			</c:if>
			<form method="post" action="redeem.pdf" target="_blank">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="bname" value="${branch.name}"/>
				<input type="hidden" name="baddress" value="${branch.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Redeemed Items</TH>
				</TR>
				<TR>
					<TH>Pawn ID</TH>
					<TH>Branch PID</TH>
					<TH>PT Number</TH>
					<TH>Loan Date</TH>
					<TH>Redeem Date</TH>
					<TH>Customer</TH>
					<TH>Loan Amount</TH>
					<TH>Rate</TH>
					<TH>Interest</TH>
					<TH>Net</TH>
				</TR>
				<c:forEach var="row" items="${redeem.allRedeem}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD align="right"><fmt:formatNumber value="${row.pid}" minIntegerDigits="10" groupingUsed="false"/></TD>
					<TD align="right"><fmt:formatNumber value="${row.branchId}" minIntegerDigits="4" groupingUsed="false"/>-<fmt:formatNumber value="${row.branchPid}" minIntegerDigits="6" groupingUsed="false"/></TD>
					<TD align="right"><fmt:formatNumber value="${row.ptNumber}" minIntegerDigits="6" groupingUsed="false"/></TD>
					<TD><fmt:formatDate value="${row.loanDate}" dateStyle="long" /></TD>
					<TD><fmt:formatDate value="${row.redeemDate}" dateStyle="long" /></TD>
					<TD><jsp:useBean id="name" class="com.prenda.service.NameService"/>
					<jsp:setProperty name="name" property="id" value="${row.nameId}"/>
					<c:out value="${name.lastName}"/>, <c:out value="${name.firstName}"/> <c:out value="${name.middleName}"/></TD>
					<TD><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount*row.interestRate}" /> %</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount*row.loanAmount*row.interestRate/100}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount*(1+row.loanAmount*row.interestRate/100)}" /></TD>
				</c:forEach>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>