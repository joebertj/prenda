<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="${contextPath}/common/js/pawn.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/scriptaculous.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/overlibmws.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/overlibmws_exclusive.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/ajaxtags-1.2-beta2.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/calendarmws_lang.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/sprintf.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/slider.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/pawnslide.js"></script>
</head>
<body>

<TABLE border="1" class="main" style="width:100%;">
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
			<FORM name="pawn" method="post" action="pawndetail.jsp" onSubmit="updatePawn()">
			<input type="hidden" name="modtype" value="2"/>
			<input type="hidden" name="pid" value="${param.pid}"/>
			<input type="hidden" id="branchid" value="${user.branchId}"/><!-- used by ajax only -->
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Edit Pawn</TH>
				</TR>
				<TR>
					<TD><jsp:useBean id="now" class="java.util.Date" />
					<jsp:useBean id="javaToHtml" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="javaToHtml" value="E MMM dd hh:mm:ss z yyyy"/>
					<jsp:setProperty property="sdfOut" name="javaToHtml" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="javaToHtml" value="${now}"/>
					<jsp:useBean id="htmlToJs" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="htmlToJs" value="MMM dd, yyyy"/>
					<jsp:setProperty property="sdfOut" name="htmlToJs" value="MM/dd/yyyy"/>
					<jsp:setProperty property="value" name="htmlToJs" value="${now}"/>
					<jsp:useBean id="sqlToJs" class="com.prenda.helper.DateUtil" /> 
					<jsp:setProperty property="sdfIn" name="sqlToJs" value="yyyy-MM-dd"/>
					<jsp:setProperty property="sdfOut" name="sqlToJs" value="MM/dd/yyyy"/>
					<jsp:setProperty property="value" name="sqlToJs" value="${user.loanDate}"/>
					Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branch.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200" align="right">
					<input class="revert" type="button" value="  " style="background: url(common/img/revert.png) no-repeat; cursor:pointer; border: none;" 
					onClick='document.pawn.dateInJs.value="<c:out value="${sqlToJs.effective}"/>";document.pawn.loandate.focus();'/></TD>
					<TD>Date of Loan</TD>
					<TD> 
					<input type="hidden" id="dateInJs" name="dateInJs" value="${htmlToJs.effective}" onChange="document.pawn.loandate.focus();"/>
					<input type="text" name="loandate" id="loandate" value="${javaToHtml.effective}" size="10" readonly/>
					<a href="javascript:ggLang='eng';show_calendar('pawn.dateInJs',pawn.month.value,pawn.year.value);">
							<img src="${contextPath}/common/img/showcalendar.gif" align="top" border="0"/></a>
					<input type="hidden" id="sdfin" value="MM/dd/yyyy"/>
					<input type="hidden" id="sdfout" value="MMM dd, yyyy"/>
					<input type="hidden" id="month" value="${htmlToJs.month}"/>
					<input type="hidden" id="year" value="${htmlToJs.year}"/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${branch.pawnTicket}" minIntegerDigits="6" groupingUsed="false"/>
					<input type="hidden" id="pt" name="pt" value="${branch.pawnTicket}" />
					</TD>
					<TD>Maturity Date</TD>
					<TD><input type="text" id="maturity" value="${javaToHtml.maturity}" size="10" disabled/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Expiry Date</TD>
					<TD><input type="text" id="expiry" value="${javaToHtml.expiry}" size="10" disabled/>
					</TD>
				</TR>
				<TR>
					<TD colspan="5">
					Last Name <INPUT id="lname" name="lname" type="text" value="${param.lname}"/> 
					<span id="indicator1" style="display:none;"><img src="${contextPath}/common/img/indicator.gif" /></span>
					First Name <INPUT id="fname" name="fname" type="text" value="${param.fname}"/>
					<span id="indicator2" style="display:none;"><img src="${contextPath}/common/img/indicator.gif" /></span>
					Middle <INPUT id="mname" name="mname" type="text" value="${param.mname}"/>
					<span id="indicator3" style="display:none;"><img src="${contextPath}/common/img/indicator.gif" /></span>
					<input type="hidden" id="cid" name="cid" value="${param.cid}"/>
					</TD>
				</TR>
				<TR>
					<TD>Address</TD>
					<TD colspan="3"><INPUT type="text" id="address" name="address" size="60" value="${param.cadd}"/>
					</TD>
				</TR>
				<tr>
					<td colspan="2">Pawn Type <input type="radio" name="loantype" value="1" onclick="modeJewelry()" checked/>Jewelry
					<input type="radio" name="loantype" value="2" onclick="modeNonJewel()"/>Non-Jewelry</td>
				</tr>
				<tr id="jewelry1">
					<td colspan="2">
						Weight<INPUT type="text" name="weight" size="5" onChange="updatePawn()"/>grams
						Carats
						<select name="carats" id="carats" onChange="updatePawn()">
							<option value="10">10K</option>
							<option value="14">14K</option>
							<option value="18" selected="selected">18K</option>
							<option value="22">22K</option>
							<option value="24">24K</option>
						</select>
					</td>
					<td align="right">
						Adjust Amount 
					</td>
					<td colspan="2">
						<div id="track1" class="slider">
							<div class="handle" id="handle1"/>
						</div>
					</td>
					<td>
						<INPUT type="text" name="slide" id="slide" size="5" disabled/>
					</td>
				</tr>
				<tr id="jewelry2">
					<td colspan="3" align="center">Carats Price per Gram</td>
					<td>Minimum<INPUT type="text" name="minimum" id="minimum" size="5" disabled/></td>
					<td>Maximum<INPUT type="text" name="maximum" id="maximum" size="5" disabled/></td>
				</tr>
				<TR>
					<TD>Loan Amount</TD>
					<TD><INPUT type="text" name="loanamt" id="loanamt" size="10" onChange="updatePawn()" disabled/>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3"><INPUT type="text" name="loanword" size="50" disabled/>
					</TD>
				</TR>
				<TR>
					<TD>Appraised Amount</TD>
					<TD><INPUT type="text" name="appamt" id="appamt" size="10" readonly/>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3"><INPUT type="text" name="appword" size="50" disabled/></TD>
				</TR>
				<TR>
					<TD>Description</TD>
					<TD colspan="4"><INPUT type="text" name="desc" id="desc" size="78" onChange="updatePawn()"/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Principal</TD>
					<TD><INPUT type="text" name="pri" size="10" disabled/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Advance Interest</TD>
					<TD><INPUT type="hidden" name="interest" id="interest" size="10" value="${branch.advanceInterest}"/>
						<INPUT type="text" name="loaninterest" id="loaninterest" size="10" value="0" readonly/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Service Charge</TD>
					<TD><INPUT type="text" name="service" id="service" size="10" value="${branch.serviceCharge}" readonly/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Net Proceeds</TD>
					<TD><INPUT type="text" name="net" id="net" size="10" readonly/></TD>
				</TR>
				<TR>
					<TD colspan="6" align="center">
					<INPUT type="submit" value="Update" onClick="
						document.pawn.loanamt.disabled=false;
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
  source="dateInJs"
  target="loandate,maturity,expiry,month,year"
  action="loandate"
  parameters="sdfin={sdfin},sdfout={sdfout},dateString={dateInJs}"
  eventType="focus"
  parser="new ResponseXmlParser()" />
  
<ajax:updateField
  baseUrl="GetMinMax"
  source="carats"
  target="minimum,maximum"
  action="carats"
  parameters="branchid={branchid},carats={carats}"
  eventType="blur"
  parser="new ResponseXmlParser()" 
  postFunction="initPawn" />
  
<ajax:updateField
  baseUrl="NetProceeds"
  source="desc"
  target="net,loaninterest"
  action="desc"
  parameters="appraised={appamt},advanceInterest={interest},serviceCharge={service},margin=100"
  eventType="focus"
  parser="new ResponseXmlParser()" />

<ajax:callout
  baseUrl="${contextPath}/common/xml/revert.xml"
  sourceClass="revert"
  parameters=""
  title="Tooltip"
  overlib="STICKY,CLOSECLICK,DELAY,100,TIMEOUT,1000,VAUTO,WRAPMAX,240" />

<script type="text/javascript">
	var last="${pawn.rows[0].last_name}";
	var first="${pawn.rows[0].first_name}";
	var mid="${pawn.rows[0].middle_name}";
	document.forms["pawn"].lname.value=last;
	document.forms["pawn"].fname.value=first;
	document.forms["pawn"].mname.value=mid;
	document.forms["pawn"].address.value="${pawn.rows[0].address}";
	document.forms["pawn"].desc.value="${pawn.rows[0].description}";
	document.forms["pawn"].loanamt.value=<c:out value="${pawn.rows[0].loan}"/>;
	document.forms["pawn"].loanword.value=AmtInWords(document.forms["pawn"].loanamt.value,'Pesos Only');
	document.forms["pawn"].appamt.value=<c:out value="${pawn.rows[0].appraised}"/>;
	document.forms["pawn"].appword.value=AmtInWords(document.forms["pawn"].appamt.value,'Pesos Only');
	document.forms["pawn"].pri.value=<c:out value="${pawn.rows[0].loan}"/>;
	document.forms["pawn"].net.value=<c:out value="${pawn.rows[0].loan-pawn.rows[0].service_charge}"/>;
	document.forms["pawn"].desc.focus();
</script>
</body>
</html>