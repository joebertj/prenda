<%@include file="common/header.jsp"%>
<script type="text/javascript" src="common/js/pawn.js"></script>
<script type="text/javascript" src="common/js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="common/js/scriptaculous.js"></script>
<script type="text/javascript" src="common/js/overlibmws.js"></script>
<script type="text/javascript" src="common/js/ajaxtags-1.2-beta2.js"></script>
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
<sql:query var="pawn" dataSource="${prenda}">
SELECT * FROM pawn 
LEFT JOIN customer ON pawn.nameid=customer.id
WHERE pid=<c:out value="${param.pid}"/>
</sql:query>
<c:set var="x" value="<%= new java.util.Date() %>" />
<fmt:parseDate var="cdate" value="${pawn.rows[0].create_date}" pattern="yyyy-MM-dd HH:mm:ss.S" />
<c:set var="minutelapsed" value="${(x.time-cdate.time)/(1000*60)}"/>
<c:if test="${minutelapsed>15}">
	<c:redirect url="pawn.jsp?msg=Allowable time elapsed ${minutelapsed}"/>
</c:if>
<div id="errorMsg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 12px Arial;color:#900"></div>
			<FORM name="pawn" method="post" action="pawneditdetail.jsp">
			<input type="hidden" name="pid" value='<c:out value="${pawn.rows[0].pid}"/>'/>
			<input type="hidden" name="cdate" value='<c:out value="${pawn.rows[0].create_date}"/>'/>
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Edit Pawn</TH>
				</TR>
				<TR>
					<TD>Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branches.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200"></TD>
					<TD>Date of Loan</TD>
					<TD>: <jsp:useBean id="ldate" class="com.prenda.Loan" /> 
					<jsp:setProperty property="sdfIn" name="ldate" value="yyyy-MM-dd"/>
					<jsp:setProperty property="sdfOut" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="ldate" value="${pawn.rows[0].loan_date}"/>
					<c:out value="${ldate.loan}"/>
					<input type="hidden" name="loandate" value='<c:out value="${ldate.value}"/>'/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${pawn.rows[0].pt}" minIntegerDigits="6" groupingUsed="false"/>
					<input type="hidden" id="pt" name="pt" value='<c:out value="${pawn.rows[0].pt}"/>' /></TD>
					<TD>Maturity Date</TD>
					<TD>: <c:out value="${ldate.maturity}"/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Expiry Date</TD>
					<TD>: <c:out value="${ldate.expiry}"/></TD>
				</TR>
				<TR>
					<TD colspan="5">
					Last Name<INPUT id="lname" name="lname" type="text"/> 
					<span id="indicator1" style="display:none;"><img src="common/img/indicator.gif" /></span>
					First Name<INPUT id="fname" name="fname" type="text"/>
					<span id="indicator2" style="display:none;"><img src="common/img/indicator.gif" /></span>
					Middle<INPUT id="mname" name="mname" type="text"/>
					<span id="indicator3" style="display:none;"><img src="common/img/indicator.gif" /></span>
					<input type="hidden" id="cid" name="cid"/>
					</TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD colspan="3">: <INPUT type="text" id="address" name="address" size="50"/>
					</TD>
				</TR>
				<TR>
					<TD>Appraised Amount</TD>
					<TD>: <INPUT type="text" name="appamt" size="10" disabled/>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <INPUT type="text" name="appword" size="50" disabled/></TD>
				</TR>
				<TR>
					<TD>Loan Amount</TD>
					<TD>: <INPUT type="text" name="loanamt" size="10" onKeyUp="
						document.pawn.loanword.value=AmtInWords(document.pawn.loanamt.value,'Pesos Only');
						document.pawn.appamt.value=parseFloat(this.value)+100;
						document.pawn.appword.value=AmtInWords(document.pawn.appamt.value,'Pesos Only');
						document.pawn.pri.value=this.value;
						document.pawn.net.value=this.value;
						"/>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <INPUT type="text" name="loanword" size="50" disabled/>
					</TD>
				</TR>
				<TR>
					<TD>Description</TD>
					<TD colspan="5">: <INPUT type="text" name="desc" size="78"/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Principal</TD>
					<TD>: <INPUT type="text" name="pri" size="10" disabled/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Interest</TD>
					<TD>: <INPUT type="text" name="interest" size="10" value="0.00" disabled/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Service Charge</TD>
					<TD>: <INPUT type="text" name="service" size="10" value="0.00" disabled/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Net Proceeds</TD>
					<TD>: <INPUT type="text" name="net" size="10" disabled/></TD>
				</TR>
				<TR>
					<TD colspan="6" align="center">
					<INPUT type="submit" value="Save" onClick="
						document.pawn.address.disabled=false;
						document.pawn.loanword.disabled=false;
						document.pawn.appamt.disabled=false;	
						document.pawn.appword.disabled=false;
						document.pawn.pri.disabled=false;
						document.pawn.interest.disabled=false;
						document.pawn.service.disabled=false;
						document.pawn.net.disabled=false;">
					</TD>
				</TR>
			</TABLE>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<ajax:autocomplete
  source="lname"
  target="cid"
  baseUrl="CheckName"
  parameters="lname={lname},fname={fname},mname={mname},ntype=0"
  className="autocomplete"
  indicator="indicator1"
  minimumCharacters="1"
  parser="new ResponseXmlToHtmlListParser()" />

<ajax:autocomplete
  source="fname"
  target="cid"
  baseUrl="CheckName"
  parameters="lname={lname},fname={fname},mname={mname},ntype=1"
  className="autocomplete"
  indicator="indicator2"
  minimumCharacters="1"
  parser="new ResponseXmlToHtmlListParser()" />

<ajax:autocomplete
  source="mname"
  target="cid"
  baseUrl="CheckName"
  parameters="lname={lname},fname={fname},mname={mname},ntype=2"
  className="autocomplete"
  indicator="indicator3"
  minimumCharacters="1"
  parser="new ResponseXmlToHtmlListParser()" />

<ajax:updateField
  baseUrl="GetAddress"
  source="cid"
  target="address"
  action="lname"
  parameters="nid={cid}"
  eventType="blur"
  parser="new ResponseXmlParser()" />

<ajax:updateField
  baseUrl="GetAddress"
  source="cid"
  target="address"
  action="fname"
  parameters="nid={cid}"
  eventType="blur"
  parser="new ResponseXmlParser()" />

<ajax:updateField
  baseUrl="GetAddress"
  source="cid"
  target="address"
  action="mname"
  parameters="nid={cid}"
  eventType="blur"
  parser="new ResponseXmlParser()" />
  
<ajax:updateField
  baseUrl="GetDates"
  source="sldate"
  target="loandate,maturity,expiry"
  action="loandate"
  parameters="sdfin={sdfin},sdfout={sdfout},date={sldate}"
  eventType="focus"
  parser="new ResponseXmlParser()" />

<script type="text/javascript">
	var last='<c:out value="${pawn.rows[0].last_name}"/>';
	var first='<c:out value="${pawn.rows[0].first_name}"/>';
	var mid='<c:out value="${pawn.rows[0].middle_name}"/>';
	document.forms["pawn"].lname.value=last;
	document.forms["pawn"].fname.value=first;
	document.forms["pawn"].mname.value=mid;
	document.forms["pawn"].address.value='<c:out value="${pawn.rows[0].address}"/>';
	document.forms["pawn"].desc.value='<c:out value="${pawn.rows[0].description}"/>';
	document.forms["pawn"].loanamt.value=<c:out value="${pawn.rows[0].loan}"/>;
	document.forms["pawn"].loanword.value=AmtInWords(document.forms["pawn"].loanamt.value,'Pesos Only');
	document.forms["pawn"].appamt.value=<c:out value="${pawn.rows[0].appraised}"/>;
	document.forms["pawn"].appword.value=AmtInWords(document.forms["pawn"].appamt.value,'Pesos Only');
	document.forms["pawn"].pri.value=<c:out value="${pawn.rows[0].loan}"/>;
	document.forms["pawn"].net.value=<c:out value="${pawn.rows[0].loan-pawn.rows[0].service_charge}"/>;
</script>

</body>
</html>