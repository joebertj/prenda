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
<jsp:useBean id="customers" class="com.prenda.service.CustomerService" />
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
<c:set var="perpage" value="${pageS.customer}"/>
<jsp:setProperty name="customers" property="pageSize" value="${perpage}"/>
<jsp:setProperty name="customers" property="pageNum" value="${param.pagenum}"/>
<c:set var="numid" value="${customers.count}" />
<c:set var="pages" value="${numid/perpage}" />
<c:set var="adjust" value="${numid % perpage}" />
<c:if test="${adjust>0}">
	<c:set var="pages" value="${pages+1}" />
</c:if>
<c:set var="pagenum" value="${param.pagenum}" />
<c:if test="${pagenum==null || pagenum<1 || pagenum>pages}">
				<c:set var="pagenum" value="1" />
			</c:if>
<br/>
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
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Customers</TH>
				</TR>
				<TR>
					<TH>Last Name</TH>
					<TH>First Name</TH>
					<TH>Middle Name</TH>
					<TH>Address</TH>
					<c:if test="${user.level>=7}">
					<TH>Archived</TH>
					</c:if>
				</TR>
				<c:forEach var="row" items="${customers.allCustomers}" varStatus="line">
					<c:choose>
						<c:when test="${line.count % 2 == 1}">
							<TR bgcolor="#99CCFF">
						</c:when>
						<c:otherwise>
							<TR>
						</c:otherwise>
					</c:choose>
					<TD><c:out value="${row.lastName}" /></TD>
					<TD><c:out value="${row.firstName}" /></TD>
					<TD><c:out value="${row.middleName}" /></TD>
					<TD><c:out value="${row.address}" />
					<c:if test="${user.level>=7}">
					<TD>
					<c:choose>
						<c:when test="${row.isArchive()}">
						Yes
						</c:when>
						<c:otherwise>
						No
						</c:otherwise>
					</c:choose>
					</TD>
					</c:if>
					<TD>
					<FORM method="post" action="editcustomer.jsp">
					<INPUT name="cid" type="hidden" value="${row.id}">
					<INPUT name="clname" type="hidden" value="${row.lastName}">
					<INPUT name="cfname" type="hidden" value="${row.firstName}">
					<INPUT name="cmname" type="hidden" value="${row.middleName}"> 
					<INPUT name="cadd" type="hidden" value="${row.address}">
					<INPUT name="modtype" type="submit" value="Edit">
					</FORM>
					</TD>
					<c:if test="${user.level>=7}">
					<TD>
					<FORM method="post" action="delcustomer.jsp">
					<INPUT name="cid" type="hidden" value="${row.id}">
					<INPUT name="clname" type="hidden" value="${row.lastName}">
					<INPUT name="cfname" type="hidden" value="${row.firstName}">
					<INPUT name="cmname" type="hidden" value="${row.middleName}"> 
					<INPUT name="cadd" type="hidden" value="${row.address}">
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