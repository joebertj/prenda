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
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${branch.id}" />
<c:set var="perpage" value="${pageS.pullout}"/>
<c:set var="pagenum" value="${param.pagenum}" />
<c:if test="${pagenum==null || pagenum<1 || pagenum>pages}">
	<c:set var="pagenum" value="1" />
</c:if>
<c:set var="mode" value="${param.mode}" />
<c:if test="${mode==null}">
	<c:set var="mode" value="1" />
</c:if>
<jsp:useBean id="pullout" class="com.prenda.service.PulloutService"/>
<jsp:setProperty name="pullout" property="level" value="${user.level}"/>
<jsp:setProperty name="pullout" property="branchId" value="${user.branchId}"/>
<jsp:setProperty name="pullout" property="userId" value="${user.userId}"/>
<jsp:setProperty name="pullout" property="mode" value="${mode}"/>
<jsp:setProperty name="pullout" property="sort" value="pullout.pid"/>
<jsp:setProperty name="pullout" property="order" value="1"/>
<jsp:setProperty name="pullout" property="pageNum" value="${pagenum}"/>
<jsp:setProperty name="pullout" property="pageSize" value="${perpage}"/>
<c:set var="numid" value="${pullout.allPulloutCount}" />
<c:set var="pages" value="${numid/perpage}" />
<c:set var="adjust" value="${numid % perpage}" />
<c:if test="${adjust>0}">
				<c:set var="pages" value="${pages-(adjust/perpage)+1}" />
			</c:if>
<br/>
			Page 
<c:if test="${pagenum>1}">
				<A href='pulloutsummary.jsp?pagenum=<c:out value="${pagenum-1}"/>&mode=<c:out	value="${mode}" />'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='pulloutsummary.jsp?pagenum=<c:out value="${i}"/>&mode=<c:out	value="${mode}" />'><c:out	value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<pages-(adjust/perpage)}">
				<A href='pulloutsummary.jsp?pagenum=<c:out value="${pagenum+1}"/>&mode=<c:out	value="${mode}" />'>next</A>
			</c:if>
			<form method="post" action="pullout.pdf" target="_blank">
				<input type="hidden" name="branch" value="${user.branchId}"/>
				<input type="hidden" name="bname" value="${branch.name}"/>
				<input type="hidden" name="baddress" value="${branch.address}"/>
				<input type="submit" value="Generate PDF"/>
			</form>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pulled-out Items</TH>
				</TR>
				<TR>
					<TH>Pawn ID</TH>
					<TH>Branch PID</TH>
					<TH>PT Number</TH>
					<TH>Loan Date</TH>
					<TH>Pullout Date</TH>
					<TH>Customer</TH>
					<TH>Loan Amount</TH>
					<TH>Rate</TH>
					<TH>Interest</TH>
					<TH>Net</TH>
				</TR>
				<c:forEach var="row" items="${pullout.allPullout}" varStatus="line">
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
					<TD><fmt:formatDate value="${row.pulloutDate}" dateStyle="long" /></TD>
					<TD><jsp:useBean id="name" class="com.prenda.service.NameService"/>
					<jsp:setProperty name="name" property="id" value="${row.nameId}"/>
					<c:out value="${name.lastName}"/>, <c:out value="${name.firstName}"/> <c:out value="${name.middleName}"/></TD>
					<TD><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.interestRate}" /> %</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.loanAmount*row.interestRate/100}" /></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${(1 + row.interestRate/100 )*row.loanAmount+row.serviceCharge}" /></TD>
				</TR>
				</c:forEach>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>