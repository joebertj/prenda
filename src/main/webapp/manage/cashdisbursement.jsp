<%@include file="../public/header.jsp"%>
<script type="text/javascript" src="${contextPath}/resources/js/dynamicrow.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/scriptaculous.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/overlibmws.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/ajaxtags-1.2-beta2.js"></script>
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
			<form name="disburse" action="cashdisbursementdetail.jsp" method="post">
			<input type="hidden" name="branch" value="${user.branchId}"/>
			<TABLE>
				<TR>
					<TH colspan="100%">Cash Disbursement</TH>
				</TR>
			</TABLE>	
			<br/>
			<TABLE border="1" id="cashdisburse">
				<TR>
					<TD>Code</TD>
					<TD>Account</TD>
					<TD>Particulars</TD>
					<TD>Amount</TD>
				</TR>
				<TR>
					<TD><input type="text" name="code" id="code1" size="5"/></TD>
					<TD><input type="text" name="account" id="account1" size="30" disabled/></TD>
					<TD><input type="text" name="particulars" id="particulars1" size="40"/></TD>
					<TD><input type="text" name="amount" id="amount1"/></TD>
				</TR>
			</TABLE>
			<br/>
			<table>
				<TR>
					<TD colspan="100%"><input type="button" value="Add New Row" onclick="addRowToTable();"/>
					<input type="button" value="Remove Row" onclick="removeRowFromTable();" />
					<input type="submit" value="Continue" onclick="return validateRow(this.form);" />
					</TD>
				</TR>
			</table>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<ajax:updateField
  baseUrl="GetAccountName"
  source="code1"
  target="account1"
  action="code1"
  parameters="code={code1},limit=1"
  eventType="blur"
  parser="new ResponseXmlParser()" />
  
</body>
</html>