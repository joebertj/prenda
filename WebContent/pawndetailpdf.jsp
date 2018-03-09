<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="${contextPath}/common/js/pawn.js"></script>
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
<sql:query var="pawn" dataSource="${prenda}">
SELECT * FROM pawn 
LEFT JOIN genkey ON pawn.pid=genkey.pid
LEFT JOIN customer ON pawn.nameid=customer.id
WHERE pawn.pid=<c:out value="${pid}"/>
</sql:query>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pawn Details</TH>
				</TR>
				<TR>
					<TD>Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branches.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200"></TD>
					<TD>Date of Loan</TD>
					<TD>:<jsp:useBean id="ldate" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="ldate" value="yyyy-MM-dd"/>
					<jsp:setProperty property="sdfOut" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="ldate" value="${pawn.rows[0].loan_date}"/>
					<c:out value="${ldate.effective}"/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${pawn.rows[0].pt}" minIntegerDigits="6" groupingUsed="false"/></TD>
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
					<TD colspan="3">: <c:out value="${pawn.rows[0].last_name}"/>, <c:out value="${pawn.rows[0].first_name}"/> <c:out value="${pawn.rows[0].middle_name}"/>
					</TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD colspan="3">: <c:out value="${pawn.rows[0].address}"/></TD>
				</TR>
				<TR>
					<TD>Appraised Amount</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].appraised}" />
					<input type="hidden" id="appamt" value="${pawn.rows[0].appraised}" />
					</TD>
					<TD colspan="4"><div id="appword"></div></TD>
				</TR>
				<TR>
					<TD>Loan Amount</TD>
					<TD width="100">: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].loan}" />
					<input type="hidden" id="loanamt" value="${pawn.rows[0].loan}" />
					</TD>
					<TD colspan="4"><div id="loanword"></div></TD>
				</TR>
				<TR>
					<TD>Description</TD>
					<TD colspan="4">: <c:out value="${pawn.rows[0].description}"/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Principal</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].loan}" /></TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Interest</TD>
					<TD>: 0.00</TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Service Charge</TD>
					<TD>: 0.00</TD>
				</TR>
				<TR>
					<TD colspan="4">
					<TD>Net Proceeds</TD>
					<TD>: <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pawn.rows[0].loan}" /></TD>
				</TR>
				<TR>
					<TD colspan="100%" align="center">
					<script type="text/javascript">
						var loaninwords="In Words: " + AmtInWords(document.getElementById("loanamt").value,'Pesos Only');
						var appinwords="In Words: " + AmtInWords(document.getElementById("appamt").value,'Pesos Only');
						document.getElementById("loanword").innerHTML=loaninwords;
						document.getElementById("appword").innerHTML=appinwords;
					</script>
					<form method="post" action="pawnticket.pdf">
						<input type="hidden" name="pid" value="${param.pid}"/>
						<input type="hidden" name="bpid" value="${pawn.rows[0].bpid}"/>
						<input type="hidden" name="pawn" value="${ldate.effective}"/>
						<input type="hidden" name="maturity" value="${ldate.maturity}"/>
						<input type="hidden" name="expire" value="${ldate.expiry}"/>
						<input type="hidden" name="name" value="${pawn.rows[0].last_name}"/>, <c:out value="${pawn.rows[0].first_name}"/> <c:out value="${pawn.rows[0].middle_name}"/>
						<input type="hidden" name="address" value="${pawn.rows[0].address}"/>
						<input type="hidden" name="appraised" value="${pawn.rows[0].appraised}"/>
						<input type="hidden" name="loan" value="${pawn.rows[0].loan}"/>
						<input type="hidden" name="ratew"/>
						<input type="hidden" name="loanw"/>
						<input type="hidden" name="appraisedw"/>
						<input type="hidden" name="rate" value='0'/>
						<input type="hidden" name="interest" value='0.00'/>
						<input type="hidden" name="sc" value='0.00'/>
						<input type="hidden" name="net" value="${pawn.rows[0].loan}"/>
						<input type="hidden" name="description" value="${pawn.rows[0].description}"/>
						<input type="hidden" name="password" value="${pawn.rows[0].password}"/>
						<input type="hidden" name="encoder" value="${pawn.rows[0].encoder}"/>
						<input type="hidden" name="branch" value="${users.rows[0].branch}"/>
						<input type="submit" value="Print on Preprinted Receipt" onClick='
							document.getElementsByName("ratew").value=AmtInWords(document.getElementsByName("rate").value,"Pesos Only");
							document.getElementsByName("loanw").value=AmtInWords(document.getElementsByName("loan").value,"Pesos Only");
							document.getElementsByName("appraisedw").value=AmtInWords(document.getElementsByName("appraised").value,"Pesos Only")'
							/>
						<input type="submit" value="Print on Empty Paper" onClick="alert('Not yet available');return false;"/>
					</form>
					</TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>