<table width="160" class="menu">
	<tr>
		<th>Main</th>
	</tr>
  	<tr>
    	<td>
			<a href="${contextPath}/encoder/pawn.jsp">Pawn</a> <br/> 
			<a href="${contextPath}/encoder/redeem.jsp">Redeem</a> <br/> 
			<a href="${contextPath}/encoder/pullout.jsp">Pullout</a> <br/><br/>
			<a href="${contextPath}/encoder/finditem.jsp">Search Item</a> <br/>
			<a href="${contextPath}/encoder/findcustomer.jsp">Search Customers</a> <br/><br/>
			<a href="${contextPath}/common/changepass.jsp">Change Password</a> <br/>
		</td>
	</tr>
</table><br/>
<c:if test="${user.level>3}">
<table width="160" class="menu">
 	<tr>
 		<th>Manage</th>
 	</tr>
 	<tr>
    	<td>
			<a href="${contextPath}/common/userlist.jsp">Manage Users</a> <br/> 
			<a href="${contextPath}/common/listcustomer.jsp">Manage Customers</a> <br/>
			<a href="${contextPath}/manage/changebranch.jsp">Branch Settings</a> <br/>
			<a href="${contextPath}/BranchSettings.htm?branchid=<c:out value='${user.branchId}'/>">Rates, Carats and Page Settings</a> <br/> 
		<c:if test="${user.level>7}">
			<a href="${contextPath}/common/branchlist.jsp">Manage Branches</a> <br/>
		</c:if>
		</td>
	</tr>
</table><br/>
</c:if>
<table width="160" class="menu">
	<tr>
  		<th>Reports</th>
  	</tr>
	<tr>
    	<td>
			Pawned Items <br/>
			<a href="${contextPath}/encoder/pawnsummary.jsp?mode=99">all</a> | <a href=${contextPath}/encoder/pawnsummary.jsp?mode=3>this year</a> | <a href=${contextPath}/encoder/pawnsummary.jsp?mode=2>this month</a> | <a href=${contextPath}/encoder/pawnsummary.jsp?mode=1>today</a><br/><br/>
			Redeemed Items <br/>
			<a href="${contextPath}/encoder/redeemsummary.jsp?mode=99">all</a> | <a href=${contextPath}/encoder/redeemsummary.jsp?mode=3>this year</a> | <a href=${contextPath}/encoder/redeemsummary.jsp?mode=2>this month</a> | <a href=${contextPath}/encoder/redeemsummary.jsp?mode=1>today</a><br/><br/>
			Pulled-out Items <br/>
			<a href="${contextPath}/encoder/pulloutsummary.jsp?mode=99">all</a> | <a href=${contextPath}/encoder/pulloutsummary.jsp?mode=3>this year</a> | <a href=${contextPath}/encoder/pulloutsummary.jsp?mode=2>this month</a> | <a href=${contextPath}/encoder/pulloutsummary.jsp?mode=1>today</a><br/><br/>
			Inventory Items<br/>
			<a href="${contextPath}/encoder/inventoryitems.jsp">view list</a> | <a href="${contextPath}/inventorychecklist.txt" target="_blank">print checklist</a><br/><br/>
			<c:if test="${user.level>7}">
			Auction Items<br/> 
			<a href="${contextPath}/common/auction.jsp">view</a> | <a href="${contextPath}/common/auctionselect.jsp">select from pulled-out items</a><br/><br/>
</c:if>
<c:if test="${user.level<=7}">
			<a href="${contextPath}/common/auction.jsp">Auction Items</a><br/><br/>
</c:if>
			<a href="${contextPath}/encoder/outstandingitems.jsp">Outstanding Items</a><br/><br/>
			<a href="${contextPath}/encoder/forecloseditems.jsp">Foreclosed Items</a><br/>
		</td>
	</tr>
</table><br/>
<c:if test="${user.level>3}">
<table width="160" class="menu">
	<tr>
  		<th>Statistics</th>
	</tr>
  	<tr>
    	<td>
			Pawned Items<br/>
			<a href="${contextPath}/common/pawnsummarystat.jsp">loan value</a><br/>
			Redeemed Items<br/>
			<a href="${contextPath}/common/redeemsummarystat.jsp">loan value</a> | <a href="${contextPath}/RedeemStatistics.htm?branchid=<c:out value='${user.branchId}'/>&userid=<c:out value='${user.userId}'/>&referer=manage">Rate</a><br/>
			Pulled-out Items<br/>
			<a href="${contextPath}/common/pulloutsummarystat.jsp">loan value</a><br/>
			Inventory Items<br/>
			<a href="${contextPath}/common/inventoryitemstat.jsp">loan value</a><br/>
			Outstanding Items<br/>
			<a href="${contextPath}/common/outstandingitemstat.jsp">loan value</a><br/>
			Foreclosed Items<br/>
			<a href="${contextPath}/common/forecloseditemstat.jsp">loan value</a><br/>
		</td>
	</tr>
</table><br/>
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
</table><br/>
<table width="160" class="menu">
	<tr>
		<th>Accounting</th>
	</tr>
	<tr>
		<td>
		
			<a href="${contextPath}/common/accountlist.jsp">Chart of Accounts</a> <br/> 
			<a href="${contextPath}/common/cashposition.jsp">Cash Position</a><br/>
		<c:if test="${user.level>3}">
			<a href="${contextPath}/common/cashdisbursements.jsp">Cash Disbursements</a><br/>
		</c:if>
		<c:if test="${user.level>7}">
			<a href="${contextPath}/common/cashtransfer.jsp">Transfer Cash</a> <br/>
		</c:if>
		</td>
	</tr>
</table>
<br/>
</c:if>