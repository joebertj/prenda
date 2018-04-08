<%@include file="../public/header.jsp"%>
</head>
<body>


<TABLE border="1" width=100% class=main>
	<TBODY>
		<TR>
			<TD><IMG border="0" src="${contextPath}/resources/img/logo.png" width="135"
				height="123"></TD>
			<TD><%@include file="../common/navi.jsp"%></TD>
		</TR>
		<TR>
			<TD valign=top><%@include file="menu.jsp"%></TD>
			<TD align=center>

<%@include file="../public/msg.jsp"%>
			<FORM name="pawn" method="post" action="EditPawn">
			<input type="hidden" name="pid" value="${param.pid}"/>
			<input type="hidden" name="cdate" value="${param.cdate}"/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pawn Details</TH>
				</TR>
				<TR>
					<TD>Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branch.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200"></TD>
					<TD>Date of Loan</TD>
					<TD>: <jsp:useBean id="ldate" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="ldate" value="yyyy-MM-dd"/>
					<jsp:setProperty property="sdfOut" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="ldate" value="${param.loandate}"/>
					<c:out value="${ldate.effective}"/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${pawn.rows[0].pt}" minIntegerDigits="6" groupingUsed="false"/>
					<input type="hidden" id="pt" name="pt" value="${pawn.rows[0].pt}" /></TD>
					<TD>Maturity Date</TD>
					<TD>: <c:out value="${ldate.maturity}"/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Expiry Date</TD>
					<TD>: <c:out value="${ldate.expiry}"/></TD>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD colspan="5">: <c:out value="${param.lname}"/>, <c:out value="${param.fname}"/> <c:out value="${param.mname}"/>
					<INPUT type="hidden" name="cid" value="${param.cid}"/>
					<INPUT type="hidden" name="lname" value="${param.lname}"/>
					<INPUT type="hidden" name="fname" value="${param.fname}"/>
					<INPUT type="hidden" name="mname" value="${param.mname}"/>
					</TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD colspan="3">: <INPUT type="hidden" name="address" value="${param.address}"/>
					<c:out value="${param.address}"/></TD>
				</TR>
				<TR>
					<TD>Appraised Amount</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.appamt}" />
					<INPUT type="hidden" name="appamt" size="10" value="${param.appamt}">
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <c:out value="${param.appword}"/></TD>
				</TR>
				<TR>
					<TD>Loan Amount</TD>
					<TD width="100">: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.loanamt}" />
					<INPUT type="hidden" name="loanamt" value="${param.loanamt}">
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <c:out value="${param.loanword}"/></TD>
				</TR>
				<TR>
					<TD>Description</TD>
					<TD colspan="4">: <c:out value="${param.desc}"/>
					<INPUT type="hidden" name="desc" size="10" value="${param.desc}">
					</TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Principal</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.pri}" /></TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Interest</TD>
					<TD>: <c:out value="${param.interest}"/>
					<INPUT type="hidden" name="interest" size="10" value="${param.interest}"></TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Service Charge</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.service}" />
					<INPUT type="hidden" name="service" size="10" value="${param.service}">
					</TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Net Proceeds</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.net}" /></TD>
				</TR>
				<TR>
					<TD colspan="100%" align="center">
					<INPUT type="button" value=Cancel onClick="location.href='pawn.jsp'"/>
					<INPUT type="submit" value="Continue"/>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>