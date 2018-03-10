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
<c:set var="perpage" value="${pageS.branch}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(branchid) as numid FROM branch
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
				<A href='branchlist.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='branchlist.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='branchlist.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="branchlist" dataSource="${prenda}">
SELECT branchid,name,address,balance,branch.archive,username FROM branch
LEFT JOIN users ON branch.owner=users.uid
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out value="${perpage}" />
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Branch List</TH>
				</TR>
				<TR>
					<TH>Name</TH>
					<TH>Address</TH>
					<TH>Owner</TH>
					<TH>Balance</TH>
					<TH>Archived</TH>
				</TR>
				<c:forEach var="row" items="${branchlist.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.name}"/></TD>
					<TD><c:out value="${row.address}"/></TD>
					<TD><c:out value="${row.username}"/></TD>
					<TD><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${row.balance}"/></TD>
					<TD>
					<c:choose>
						<c:when test="${row.archive==false}">
						No
						</c:when>
						<c:otherwise>
						Yes
						</c:otherwise>
					</c:choose>
					</TD>
					<TD>
						<FORM method="post" action="../BranchSettings.htm">
							<INPUT name="branchid" type="hidden" value="${row.branchid}"> 
							<INPUT type="submit" value="Details">
						</FORM>
					</TD>
					<TD>
						<FORM method="post" action="changebranch.jsp">
							<INPUT name="branchid" type="hidden" value="${row.branchid}"> 
							<INPUT type="submit" value="Edit">
						</FORM>
					</TD>
					<TD>
						<FORM method="post" action="delbranch.jsp">
							<INPUT name="branchid" type="hidden" value="${row.branchid}"> 
							<INPUT name="bname" type="hidden" value="${row.name}"> 
							<INPUT type="submit" value="Archive">
						</FORM>
					</TD>
				</TR>
				</c:forEach>
				<TR><TD>
				<FORM method="post" action="newbranch.jsp">
					<INPUT type="submit" value="New">
				</FORM>
				</TD></TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>