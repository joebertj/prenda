<%@include file="common/header.jsp"%>
<script type="text/javascript" src="common/js/pawn.js"></script>
<script type="text/javascript" src="common/js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="common/js/scriptaculous.js"></script>
<script type="text/javascript" src="common/js/overlibmws.js"></script>
<script type="text/javascript" src="common/js/overlibmws_exclusive.js"></script>
<script type="text/javascript" src="common/js/ajaxtags-1.2-beta2.js"></script>
<script type="text/javascript" src="common/js/calendarmws_lang.js"></script>
<script type="text/javascript" src="common/js/sprintf.js"></script>
<script type="text/javascript" src="common/js/slider.js"></script>
<script type="text/javascript" src="common/js/pawnslide.js"></script>
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
<div id="errorMsg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 12px Arial;color:#900"></div>
			<FORM name="pawn" method="post" action="pawndetail.jsp" onSubmit="updatePawn()">
			<input type="hidden" id="branchid" value="${user.branchId}"/><!-- used by ajax only -->
			<TABLE border="1">
				<TR>
					<TH colspan="100%">Pawn</TH>
				</TR>
				<TR>
					<TD><jsp:useBean id="now" class="java.util.Date" />
					<jsp:useBean id="ldate" class="com.prenda.Loan" /> 
					<jsp:setProperty property="sdfIn" name="ldate" value="E MMM dd hh:mm:ss z yyyy"/>
					<jsp:setProperty property="sdfOut" name="ldate" value="MMM dd, yyyy"/>
					<jsp:setProperty property="value" name="ldate" value="${now}"/>
					<jsp:useBean id="uldate" class="com.prenda.Loan" /> 
					<jsp:setProperty property="sdfIn" name="uldate" value="yyyy-MM-dd"/>
					<jsp:setProperty property="sdfOut" name="uldate" value="MM/dd/yyyy"/>
					<jsp:setProperty property="value" name="uldate" value="${user.loanDate}"/>
					Branch PID</TD>
					<TD colspan="2"><fmt:formatNumber value="${user.branchId}" minIntegerDigits="2" groupingUsed="false"/>-<fmt:formatNumber value="${branches.counter+1}" minIntegerDigits="8" groupingUsed="false"/></TD>
					<TD width="200" align="right">
					<input type="button" value="Prev" onClick='document.pawn.sldate.value="<c:out value="${uldate.loan}"/>";document.pawn.loandate.focus()'/></TD>
					<TD>Date of Loan</TD>
					<TD>: 
					<input type="hidden" id="sldate" name="sldate" value="<c:out value="${now}"/>"/>
					<input type="text" name="loandate" id="loandate" value="<c:out value="${ldate.loan}"/>" size="10"/>
					<a href="javascript:ggLang='eng';show_calendar('pawn.sldate')">
					<img src="common/img/showcalendar.gif" align="top" border="0"/></a>
					<input type="hidden" id="sdfin" value="MM/dd/yyyy"/>
					<input type="hidden" id="sdfout" value="MMM dd, yyyy"/>
					</TD>
				</TR>
				<TR>
					<TD>PT Number</TD>
					<TD colspan="3"><fmt:formatNumber value="${branches.pawnTicket}" minIntegerDigits="6" groupingUsed="false"/>
					<input type="hidden" id="pt" name="pt" value='<c:out value="${branches.pawnTicket}"/>' />
					</TD>
					<TD>Maturity Date</TD>
					<TD>: <input type="text" id="maturity" value='<c:out value="${ldate.maturity}"/>' size="10"/>
					</TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Expiry Date</TD>
					<TD>: <input type="text" id="expiry" value='<c:out value="${ldate.expiry}"/>' size="10"/>
					</TD>
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
					<TD colspan="3">: <INPUT type="text" id="address" name="address" size="60"/>
					</TD>
				</TR>
				<tr>
					<td colspan="2">Pawn Type <input type="radio" name="loantype" value="1" onclick="
						document.pawn.loanamt.disabled=true;
						document.pawn.weight.disabled=false;
						document.pawn.carats.disabled=false;
						" checked/>Jewelry
					<input type="radio" name="loantype" value="2" onclick="
						document.pawn.loanamt.disabled=false;
						document.pawn.weight.disabled=true;
						document.pawn.carats.disabled=true;
						"/>Non-Jewelry</td>
				</tr>
				<tr>
					<td colspan="2">
						Weight : <INPUT type="text" name="weight" size="5" onChange="updatePawn()"/>grams
						Carats : <INPUT type="text" name="carats" id="carats" size="3" onChange="updatePawn()"/>K
					</td>
					<td colspan="3">
						Adjust Amount : <INPUT type="text" name="slide" id="slide" size="5" disabled/>
						<div id="track1" style="width: 200px; height: 9px; float: right;">
							<div id="track1-left"></div>
							<div class="selected" id="handle1" style="width: 19px; height: 20px; position: relative;">
								<img src="common/img/slider.png" alt="" style="float: left;"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">Amount per Gram Based on Carats</td>
					<td>Minimum: <INPUT type="text" name="minimum" id="minimum" size="5" disabled/></td>
					<td>Maximum: <INPUT type="text" name="maximum" id="maximum" size="5" disabled/></td>
				</tr>
				<TR>
					<TD>Loan Amount</TD>
					<TD>: <INPUT type="text" name="loanamt" id="loanamt" size="10" onChange="updatePawn()" disabled/>
					</TD>
					<TD>In Words</TD>
					<TD colspan="3">: <INPUT type="text" name="loanword" size="50" disabled/>
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
					<TD>Description</TD>
					<TD colspan="4">: <INPUT type="text" name="desc" size="78"/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Principal</TD>
					<TD>: <INPUT type="text" name="pri" size="10" disabled/></TD>
				</TR>
				<TR>
					<TD colspan="4"></TD>
					<TD>Advance Interest</TD>
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
					<INPUT type="submit" value="Pawn" onClick="
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
  source="sldate"
  target="loandate,maturity,expiry"
  action="loandate"
  parameters="sdfin={sdfin},sdfout={sdfout},date={sldate}"
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

</body>
</html>