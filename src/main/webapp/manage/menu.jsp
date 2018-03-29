<table width="160" class="menu">
  <tr>
 		<th>Main</th>
 	</tr>
 	<tr>
    <td>

<a href="${contextPath}/manage/userlist.jsp">Manage Users</a> <br/> 
<a href="${contextPath}/manage/listcustomer.jsp">Manage Customers</a> <br/>
<a href="${contextPath}/manage/changebranch.jsp">Branch Settings</a> <br/>
<a href="${contextPath}/BranchSettings.htm?branchid=<c:out value='${user.branchId}'/>">Rates, Carats and Page Settings</a> <br/> 
<a href="${contextPath}/manage/auction.jsp">Auction Items</a><br/> 
<a href="${contextPath}/manage/accountlist.jsp">Chart of Accounts</a> <br/> 
   </td>
  </tr>
</table>
<br/>
<table width="160" class="menu">
  <tr>
  	<th>Statistics</th>
  </tr>
  <tr>
    <td>
Pawned Items<br/>
<a href="${contextPath}/manage/pawnsummarystat.jsp">loan value</a><br/>
Redeemed Items<br/>
<a href="${contextPath}/manage/redeemsummarystat.jsp">loan value</a> | <a href="${contextPath}/RedeemStatistics.htm?branchid=<c:out value='${user.branchId}'/>&userid=<c:out value='${user.userId}'/>&referer=manage">Rate</a><br/>
Pulled-out Items<br/>
<a href="${contextPath}/manage/pulloutsummarystat.jsp">loan value</a><br/>
Inventory Items<br/>
<a href="${contextPath}/manage/inventoryitemstat.jsp">loan value</a><br/>
Outstanding Items<br/>
<a href="${contextPath}/manage/outstandingitemstat.jsp">loan value</a><br/>
Foreclosed Items<br/>
<a href="${contextPath}/manage/forecloseditemstat.jsp">loan value</a><br/><br/>

<a href="${contextPath}/manage/cashdisbursements.jsp">Cash Disbursements</a><br/>
<a href="${contextPath}/manage/cashposition.jsp">Cash Position</a><br/>
   </tr>
</table>


