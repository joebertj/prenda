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
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
<c:set var="perpage" value="${pageS.chart}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(accountid) as numid FROM accounts
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
				<A href='accountlist.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='accountlist.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='accountlist.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="accountlist" dataSource="${prenda}">
SELECT * FROM accounts
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out value="${perpage}" />
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Chart of Accounts</TH>
				</TR>
				<TR>
					<TH>Account Code</TH>
					<TH>Account Name</TH>
				</TR>
				<c:forEach var="row" items="${accountlist.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD align="center"><c:out value="${row.accountcode}"/></TD></TD>
					<TD><c:out value="${row.accountname}"/></TD>
				</c:forEach>	
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>