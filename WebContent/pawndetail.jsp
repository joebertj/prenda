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
			<FORM name="pawn" method="post" action="CheckPawn.htm">
			<input type="hidden" name="referer" value="pawndetail.jsp"/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pawn Details</TH>
				</TR>
				<TR>
					<TD>Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branches.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200"></TD>
					<TD>Date of Loan</TD>
					<TD>: <jsp:useBean id="ldate" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="sdfOut" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="ldate" value="${param.loandate}"/>
					<jsp:useBean id="uldate" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="uldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="sdfOut" name="uldate" value="MM/dd/yyyy"/>
					<jsp:setProperty property="value" name="uldate" value="${param.loandate}"/>
					<input type="hidden" name="loandate" value='<c:out value="${uldate.effective}"/>'/>
					<c:out value="${ldate.effective}"/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${branches.pawnTicket}" minIntegerDigits="6" groupingUsed="false"/>
					<input type="hidden" id="pt" name="pt" value='<c:out value="${branches.pawnTicket}"/>' /></TD>
					<TD>Maturity Date</TD>
					<TD>: <c:out value="${ldate.maturity}"/>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Expiry Date</TD>
					<TD>: <c:out value="${ldate.expiry}"/>
				</TR>
				<TR>
					<TD>Name</TD>
					<TD colspan="5">: <c:out value="${param.lname}"/>, <c:out value="${param.fname}"/> <c:out value="${param.mname}"/>
					<INPUT type="hidden" name="cid" value='<c:out value="${param.cid}"/>'/>
					<INPUT type="hidden" name="lname" value='<c:out value="${param.lname}"/>'/>
					<INPUT type="hidden" name="fname" value='<c:out value="${param.fname}"/>'/>
					<INPUT type="hidden" name="mname" value='<c:out value="${param.mname}"/>'/>
					</TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD colspan="3">: <c:out value="${param.address}"/>
					<INPUT type="hidden" name="address" value='<c:out value="${param.address}"/>'/>
					</TD>
				</TR>
				<TR>
					<TD>Appraised Amount</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.appamt}" />
					<INPUT type="hidden" name="appamt" size="10" value='<c:out value="${param.appamt}"/>'>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <c:out value="${param.appword}"/></TD>
				</TR>
				<TR>
					<TD>Loan Amount</TD>
					<TD width="100">: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.loanamt}" />
					<INPUT type="hidden" name="loanamt" value='<c:out value="${param.loanamt}"/>'>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <c:out value="${param.loanword}"/></TD>
				</TR>
				<TR>
					<TD>Description</TD>
					<TD colspan="4">: <c:out value="${param.desc}"/>
					<INPUT type="hidden" name="desc" size="10" value='<c:out value="${param.desc}"/>'>
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
					<INPUT type="hidden" name="interest" size="10" value='<c:out value="${param.interest}"/>'></TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Service Charge</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${param.service}" />
					<INPUT type="hidden" name="service" size="10" value='<c:out value="${param.service}"/>'>
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