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
<c:set var="perpage" value="${pageS.user}"/>
<sql:query var="owner" dataSource="${prenda}">
SELECT uid FROM users
WHERE username="${authenticated}"
</sql:query>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(uid) as numid FROM users
LEFT JOIN branch ON users.branch=branch.branchid 
WHERE branch.owner=<c:out value="${owner.rows[0].uid}"/>
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
				<A href='userlist.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='userlist.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='userlist.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="userlist" dataSource="${prenda}">
SELECT uid,username,level,branch,users.archive,name FROM users
LEFT JOIN branch ON users.branch=branch.branchid 
WHERE branch.owner=<c:out value="${owner.rows[0].uid}"/>
ORDER BY username
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out value="${perpage}" />
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">User List</TH>
				</TR>
				<TR>
					<TH>Username</TH>
					<TH>Level</TH>
					<TH>Branch</TH>
					<TH>Archived</TH>
				</TR>
				<c:forEach var="row" items="${userlist.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#3366FF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.username}"/></TD>
					<TD><c:set var="lvl" value="${row.level}"/> 
					<c:choose>
						<c:when test="${lvl==1}">
							Encoder
						</c:when>
						<c:when test="${lvl==3}">
							Liaison
						</c:when>
						<c:when test="${lvl==7}">
							Manager
						</c:when>
						<c:when test="${lvl==8}">
							Owner
						</c:when>
						<c:when test="${lvl==9}">
							Admin
						</c:when>
						<c:otherwise>
							Unknown
						</c:otherwise>
					</c:choose> 
					</TD>
					<TD><c:out value="${row.name}"/></TD>
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
						<FORM method="post" action="changeuser.jsp">
							<INPUT name="uid" type="hidden" value="${row.uid}"> 
							<INPUT name="user" type="hidden" value="${row.username}">
							<INPUT name="password" type="hidden" value="${row.password}">
							<INPUT name="level" type="hidden" value="${row.level}">
							<INPUT name="branch" type="hidden" value="${row.branch}">
							<INPUT type="submit" value="Edit">
						</FORM>
					</TD>
					<TD>
						<FORM method="post" action="deluser.jsp">
							<INPUT name="uid" type="hidden" value="${row.uid}"> 
							<INPUT name="user" type="hidden" value="${row.username}">
							<INPUT type="submit" value="Archive">
						</FORM>
					</TD>
				</TR>
				</c:forEach>
				<TR><TD>
				<FORM method="post" action="newuser.jsp">
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
