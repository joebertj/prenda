<%@include file="../common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="../common/img/logo2.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="../common/msg.jsp"%>
			<jsp:setProperty property="id" name="branches" value="${param.branchid}"/>
			<FORM method="post" action="../SaveSettings">
			<INPUT type="hidden" name="bname" value='<c:out value="${branches.name}"/>'>
			<INPUT type="hidden" name="branchid" value='<c:out value="${branches.id}"/>'>
			<TABLE border="1">
				<TR>
					<TH colspan="100%"><c:out value="${branches.name}"/></TH>
				</TR>
			</TABLE>
			<BR>
			<TABLE border="1">
				<TR>
					<TH>PT Number Start</TH>
					<TH>Advance Interest</TH>
					<TH>Service Charge</TH>
					<TH>Days Left to Reserve</TH>
					<TH>Reserve Duration</TH>
				</TR>
				<TR>
					<TD><c:out value="${branches.pawnTicket}"/></TD>
					<TD><c:out value="${branches.advanceInterest}"/></TD>
					<TD><c:out value="${branches.serviceCharge}"/></TD>
					<TD><c:out value="${branches.minDaysToExtend}"/></TD>
					<TD><c:out value="${branches.reserveDuration}"/></TD>
				</TR>
			</TABLE>
			<BR>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Interest Rates</TH>
				</TR>
				<c:forEach var="i" begin="0" end="6">
				<TR>
					<TD>Day <c:out value="${i}"/>
<sql:query var="interest" dataSource="${prenda}">
SELECT rate FROM interest WHERE interestid=<c:out value="${branches.id}"/> AND day=<c:out value="${i}"/>
</sql:query>
					</TD>
					<TD><INPUT type="text" name="day<c:out value="${i}"/>" size="2" value='<c:out value="${interest.rows[0].rate + 0}"/>'>
					</TD>
					<TD>Day <c:out value="${i+7}"/>
<sql:query var="interest" dataSource="${prenda}">
SELECT rate FROM interest WHERE interestid=<c:out value="${branches.id}"/> AND day=<c:out value="${i+7}"/>
</sql:query>
					</TD>
					<TD><INPUT type="text" name="day<c:out value="${i+7}"/>" size="2" value='<c:out value="${interest.rows[0].rate + 0}"/>'>
					</TD>
					<TD>Day <c:out value="${i+14}"/>
<sql:query var="interest" dataSource="${prenda}">
SELECT rate FROM interest WHERE interestid=<c:out value="${branches.id}"/> AND day=<c:out value="${i+14}"/>
</sql:query>
					</TD>
					<TD><INPUT type="text" name="day<c:out value="${i+14}"/>" size="2" value='<c:out value="${interest.rows[0].rate + 0}"/>'>
					</TD>
					<TD>Day <c:out value="${i+21}"/>
<sql:query var="interest" dataSource="${prenda}">
SELECT rate FROM interest WHERE interestid=<c:out value="${branches.id}"/> AND day=<c:out value="${i+21}"/>
</sql:query>
					</TD>
					<TD><INPUT type="text" name="day<c:out value="${i+21}"/>" size="2" value='<c:out value="${interest.rows[0].rate + 0}"/>'>
					</TD>
					<TD>Day <c:out value="${i+28}"/>
<sql:query var="interest" dataSource="${prenda}">
SELECT rate FROM interest WHERE interestid=<c:out value="${branches.id}"/> AND day=<c:out value="${i+28}"/>
</sql:query>
					</TD>
					<TD><INPUT type="text" name="day<c:out value="${i+28}"/>" size="2" value='<c:out value="${interest.rows[0].rate}"/>'>
					</TD>
				</TR>
				</c:forEach>
				<TR>
			</TABLE>
			<BR>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Entries Per Page
<jsp:useBean id="pageS" class="com.prenda.service.PageService" />
<jsp:setProperty name="pageS" property="branchId" value="${user.branchId}" />
					</TH>
				</TR>
				<TR>
					<TD>User List</TD>
					<TD><INPUT type="text" name="user" size="2"	value='<c:out value="${pageS.user}"/>'></TD>
				</TR>
				<TR>
					<TD>Customer List</TD>
					<TD><INPUT type="text" name="customer" size="2"	value='<c:out value="${pageS.customer}"/>'></TD>
				</TR>
				<TR>
					<TD>Pawned Items</TD>
					<TD><INPUT type="text" name="pawn" size="2"	value='<c:out value="${pageS.pawn}"/>'></TD>
				</TR>
				<TR>
					<TD>Redeemed Items</TD>
					<TD><INPUT type="text" name="redeem" size="2"	value='<c:out value="${pageS.redeem}"/>'></TD>
				</TR>
				<TR>
					<TD>Pulled-out Items</TD>
					<TD><INPUT type="text" name="pullout" size="2"	value='<c:out value="${pageS.pullout}"/>'></TD>
				</TR>
				<TR>
					<TD>Inventory Items</TD>
					<TD><INPUT type="text" name="inventory" size="2"	value='<c:out value="${pageS.inventory}"/>'></TD>
				</TR>
				<TR>
					<TD>Outstanding Items</TD>
					<TD><INPUT type="text" name="outstanding" size="2"	value='<c:out value="${pageS.outstanding}"/>'></TD>
				</TR>
				<TR>
					<TD>Foreclosed Items</TD>
					<TD><INPUT type="text" name="foreclose" size="2"	value='<c:out value="${pageS.foreclose}"/>'></TD>
				</TR>
				<TR>
					<TD>Auction Items</TD>
					<TD><INPUT type="text" name="auction" size="2"	value='<c:out value="${pageS.auction}"/>'></TD>
				</TR>
			</TABLE>
			<BR>
			<INPUT type="submit" value="Save">
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>