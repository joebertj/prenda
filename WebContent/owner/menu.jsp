<table width="160" class="menu">
 	<tr>
 		<th>Main</th>
 	</tr>
  <tr>
    <td>

<a href="${contextPath}/owner/userlist.jsp">Manage Users</a> <br/> 
<a href="${contextPath}/owner/branchlist.jsp">Manage Branches</a> <br/> 

<a href="${contextPath}/owner/cashtransfer.jsp">Transfer Cash</a> <br/><br/> 
Auction Items<br/> 
<a href="${contextPath}/owner/auction.jsp">view</a> | <a href="auctionselect.jsp">select from pulled-out</a><br/><br/>
<a href="${contextPath}/owner/accountlist.jsp">Chart of Accounts</a> <br/> 
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
<a href="${contextPath}/owner/pawnsummarystat.jsp">loan value</a><br/>
Redeemed Items<br/>
<a href="${contextPath}/owner/redeemsummarystat.jsp">loan value</a> | <a href="${contextPath}/RedeemStatistics.htm?branchid=<c:out value='${user.branchId}'/>&userid=<c:out value='${user.id}'/>&referer=owner">interest rate</a><br/>
Pulled-out Items<br/>
<a href="${contextPath}/owner/pulloutsummarystat.jsp">loan value</a><br/>
Inventory Items<br/>
<a href="${contextPath}/owner/inventoryitemstat.jsp">loan value</a><br/>
Outstanding Items<br/>
<a href="${contextPath}/owner/outstandingitemstat.jsp">loan value</a><br/>
Foreclosed Items<br/>
<a href="${contextPath}/owner/forecloseditemstat.jsp">loan value</a><br/>

   </tr>
</table>


