<table width="160" class="menu">
	<tr>
 		<th>Main</th>
 	</tr>
  <tr>
    <td>

<a href="${contextPath}/admin/userlist.jsp">Manage Users</a> <br/> 
<a href="${contextPath}/admin/branchlist.jsp">Manage Branches</a> <br/> 

<a href="${contextPath}/admin/cashtransfer.jsp">Transfer Cash</a> <br/><br/> 
Auction Items<br/> 
<a href="${contextPath}/admin/auction.jsp">view</a> | <a href="auctionselect.jsp">select from pulled-out</a><br/><br/>
<a href="${contextPath}/admin/accountlist.jsp">Chart of Accounts</a> <br/> 

	</td>
  </tr>
</table>
<br/>
<table width="160" class="menu">
  <tr>
  	<th>Visuals</th>
  </tr>
  <tr>
    <td>

Pawn History <br/> 
<a href="${contextPath}/PawnHistory?type=-1">last year</a> | <a href="${contextPath}/PawnHistory?type=0">this year</a><br/>
Redeem History <br/> 
<a href="${contextPath}/RedeemHistory?type=-1">last year</a> | <a href="${contextPath}/RedeemHistory?type=0">this year</a><br/>
Pullout History <br/> 
<a href="${contextPath}/PulloutHistory?type=-1">last year</a> | <a href="${contextPath}/PulloutHistory?type=0">this year</a><br/>

   </td>
  </tr>
</table>
<br/>
<table width=160 class=menu>
  <tr>
  	<th>Statistics</th>
  </tr>
  <tr>
    <td>
Pawned Items<br/>
<a href=${contextPath}/admin/pawnsummarystat.jsp>loan value</a><br/>
Redeemed Items<br/>
<a href=${contextPath}/admin/redeemsummarystat.jsp>loan value</a> | <a href="${contextPath}/admin/redeemsummarystatrate.jsp">interest rate</a><br/>
Pulled-out Items<br/>
<a href=${contextPath}/admin/pulloutsummarystat.jsp>loan value</a><br/>
Inventory Items<br/>
<a href=${contextPath}/admin/inventoryitemstat.jsp>loan value</a><br/>
Outstanding Items<br/>
<a href=${contextPath}/admin/outstandingitemstat.jsp>loan value</a><br/>
Foreclosed Items<br/>
<a href=${contextPath}/admin/forecloseditemstat.jsp>loan value</a><br/>

   </tr>
</table>


