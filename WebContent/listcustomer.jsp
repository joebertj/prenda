<%@include file="common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="common/img/logo2.png" width="135"
				height="123"></TD>
			<TD><%@include file="common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="common/msg.jsp"%>
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
<c:set var="perpage" value="${pageS.customer}"/>
<sql:query var="pageable" dataSource="${prenda}">
SELECT count(id) as numid FROM customer
<c:if test="${users.rows[0].level<7}">
WHERE archive=0
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
<BR>
			Page 
<c:if test="${pagenum>1}">
				<A href='listcustomer.jsp?pagenum=<c:out value="${pagenum-1}"/>'>prev</A>
			</c:if>
<c:forEach var="i" begin="1" end="${pages}">
				<c:choose>
					<c:when test="${i!=pagenum}">
						<A href='listcustomer.jsp?pagenum=<c:out value="${i}"/>'><c:out
							value="${i}" /></A>
					</c:when>
					<c:otherwise>
						<c:out value="${i}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
<c:if test="${pagenum<(pages-(adjust/perpage))}">
				<A href='listcustomer.jsp?pagenum=<c:out value="${pagenum+1}"/>'>next</A>
			</c:if>
<sql:query var="customer" dataSource="${prenda}">
SELECT * FROM customer 
<c:if test="${users.rows[0].level<7}">
WHERE archive=0
</c:if>
ORDER BY last_name
LIMIT <c:out value="${(pagenum-1)*perpage}" />,<c:out
					value="${perpage}" />
			</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Customers</TH>
				</TR>
				<TR>
					<TH>Last Name</TH>
					<TH>First Name</TH>
					<TH>Middle Name</TH>
					<TH>Address</TH>
					<c:if test="${users.rows[0].level>=7}">
					<TH>Archived</TH>
					</c:if>
				</TR>
				<c:forEach var="row" items="${customer.rows}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#3366FF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.last_name}" /></TD>
					<TD><c:out value="${row.first_name}" /></TD>
					<TD><c:out value="${row.middle_name}" /></TD>
					<TD><c:out value="${row.address}" /></TD>
					<c:if test="${users.rows[0].level>=7}">
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
					</c:if>
					<TD>
					<FORM method="post" action="editcustomer.jsp">
					<INPUT name="cid" type="hidden" value='<c:out value="${row.id}"/>'>
					<INPUT name="clname" type="hidden" value='<c:out value="${row.last_name}"/>'>
					<INPUT name="cfname" type="hidden" value='<c:out value="${row.first_name}"/>'>
					<INPUT name="cmname" type="hidden" value='<c:out value="${row.middle_name}"/>'> 
					<INPUT name="cadd" type="hidden" value='<c:out value="${row.address}"/>'>
					<INPUT name="modtype" type="submit" value="Edit">
					</FORM>
					</TD>
					<c:if test="${users.rows[0].level>=7}">
					<TD>
					<FORM method="post" action="delcustomer.jsp">
					<INPUT name="cid" type="hidden" value='<c:out value="${row.id}"/>'>
					<INPUT name="clname" type="hidden" value='<c:out value="${row.last_name}"/>'>
					<INPUT name="cfname" type="hidden" value='<c:out value="${row.first_name}"/>'>
					<INPUT name="cmname" type="hidden" value='<c:out value="${row.middle_name}"/>'> 
					<INPUT name="cadd" type="hidden" value='<c:out value="${row.address}"/>'>
					<INPUT name="modtype" type="submit" value="Archive">
					</FORM>
					</TD>
					</c:if>
				</c:forEach>
				<TR><TD>
				<FORM method="post" action="newcustomer.jsp"><INPUT type="submit"
				value="New"></FORM>
				</TD></TR>
			</TABLE>
			
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>