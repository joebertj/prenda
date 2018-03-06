<%@include file="common/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="common/img/logo2.png" width="135" height="123"></TD>
			<TD><%@include file="common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>
<%@include file="common/msg.jsp"%>
<jsp:useBean id="cashpos" class="com.prenda.service.CashPositionService"/>
<jsp:setProperty name="cashpos" property="level" value="${user.level}"/>
<jsp:setProperty name="cashpos" property="branchId" value="${user.branchId}"/>
<c:if test="${param.bcode==1}">
<jsp:setProperty name="cashpos" property="bcode" value="${param.bcode}"/>
</c:if>
			<FORM method="post" action="cashposition.pdf">
			<input type="hidden" name="branch" value='<c:out value="${user.branchId}"/>'/>
			<input type="hidden" name="name" value='<c:out value="${branches.name}"/>'/>
			<input type="hidden" name="address" value='<c:out value="${branches.address}"/>'/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Cash Position</TH>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Beginning Balance</TD>
					<TD><input name="bbalance" type="hidden" value='<c:out value="${cashpos.beginBalance}"/>'/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.beginBalance}"/></TD>
				</TR>
				<TR>
					<TD>Pawn</TD>
					<TD align="right">(<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.pawn}"/>)</TD>
					<TD><input name="pawn" type="hidden" value='<c:out value="${cashpos.pawn}"/>'/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Redeem</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeem}"/></TD>
					<TD><input name="redeem" type="hidden" value='<c:out value="${cashpos.redeem}"/>'/></TD>
				</TR>
				<TR>
					<TD>Redeem Principal</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeemPrincipal}"/></TD>
					<TD><input name="principal" type="hidden" value='<c:out value="${cashpos.redeemPrincipal}"/>'/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Redeem Interest</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeemInterest}"/></TD>
					<TD><input name="interest" type="hidden" value='<c:out value="${cashpos.redeemInterest}"/>'/></TD>
				</TR>
				<TR>
					<TD>Cash Receipt</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.receipt}"/></TD>
					<TD><input name="receipt" type="hidden" value='<c:out value="${cashpos.receipt}"/>'/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Cash Disbursment</TD>
					<TD align="right">(<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.disburse}"/>)</TD>
					<TD><input name="disburse" type="hidden" value='<c:out value="${cashpos.disburse}"/>'/></TD>
				</TR>
				<TR>
					<TD>Ending Balance</TD>
					<TD><input name="ebalance" type="hidden" value='<c:out value="${cashpos.endBalance}"/>'/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.endBalance}"/></TD>
				</TR>
				<TR bgcolor="#3366FF">
					<TD>Inventory</TD>
					<TD><input name="inventory" type="hidden" value='<c:out value="${cashpos.inventory}"/>'/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.inventory}"/></TD>
				</TR>
				<TR>
					<TD>Asset</TD>
					<TD><input name="asset" type="hidden" value='<c:out value="${cashpos.inventory + cashpos.endBalance}"/>'/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.inventory + cashpos.endBalance}"/></TD>
				</TR>
				<TR>
					<TD colspan="100%" align="center"><INPUT type="submit" value="Generate PDF">
					</TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>