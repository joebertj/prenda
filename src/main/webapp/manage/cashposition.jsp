<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" class="main" width="100%">
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135" height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign="top" width="200"><%@include file="../common/menu.jsp"%></TD>
			<TD valign="top" align="center">
<%@include file="../public/msg.jsp"%>
<jsp:useBean id="cashpos" class="com.prenda.service.CashPositionService"/>
<jsp:setProperty name="cashpos" property="level" value="${user.level}"/>
<jsp:setProperty name="cashpos" property="branchId" value="${user.branchId}"/>
<c:if test="${param.bcode==1}">
<jsp:setProperty name="cashpos" property="bcode" value="${param.bcode}"/>
</c:if>
			<FORM method="post" action="../cashposition.pdf" target="_blank">
			<input type="hidden" name="branch" value="${user.branchId}"/>
			<input type="hidden" name="name" value="${branch.name}"/>
			<input type="hidden" name="address" value="${branch.address}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Cash Position</TH>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Beginning Balance</TD>
					<TD><input name="bbalance" type="hidden" value="${cashpos.beginBalance}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.beginBalance}"/></TD>
				</TR>
				<TR>
					<TD>Pawn</TD>
					<TD align="right">(<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.pawn}"/>)</TD>
					<TD><input name="pawn" type="hidden" value="${cashpos.pawn}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Redeem</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeem}"/></TD>
					<TD><input name="redeem" type="hidden" value="${cashpos.redeem}"/></TD>
				</TR>
				<TR>
					<TD>Redeem Principal</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeemPrincipal}"/></TD>
					<TD><input name="principal" type="hidden" value="${cashpos.redeemPrincipal}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Redeem Interest</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.redeemInterest}"/></TD>
					<TD><input name="interest" type="hidden" value="${cashpos.redeemInterest}"/></TD>
				</TR>
				<TR>
					<TD>Cash Receipt</TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.receipt}"/></TD>
					<TD><input name="receipt" type="hidden" value="${cashpos.receipt}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Cash Disbursment</TD>
					<TD align="right">(<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.disburse}"/>)</TD>
					<TD><input name="disburse" type="hidden" value="${cashpos.disburse}"/></TD>
				</TR>
				<TR>
					<TD>Ending Balance</TD>
					<TD><input name="ebalance" type="hidden" value="${cashpos.endBalance}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.endBalance}"/></TD>
				</TR>
				<TR bgcolor="#99CCFF">
					<TD>Inventory</TD>
					<TD><input name="inventory" type="hidden" value="${cashpos.inventory}"/></TD>
					<TD align="right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${cashpos.inventory}"/></TD>
				</TR>
				<TR>
					<TD>Asset</TD>
					<TD><input name="asset" type="hidden" value="${cashpos.inventory + cashpos.endBalance}"/></TD>
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