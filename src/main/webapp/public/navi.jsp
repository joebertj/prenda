<%@include file="datasource.jsp"%>
<table class="navi" style="width:100%;">
	<tr>
		<td align="right">
			<a href="${contextPath}/common/Language.htm?lang=en_US&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/usa.png"/></a>
			<a href="${contextPath}/common/Language.htm?lang=es_ES&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/spain.png"/></a>
			<a href="${contextPath}/common/Language.htm?lang=fr_FR&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/france.png"/></a>
		</td>
	</tr>
	<tr>
    	<td align="center">
		<a href="${contextPath}/customer/index.jsp" onmouseover="document['fpAnimswapImgFP1'].imgRolln=document['fpAnimswapImgFP1'].src;document['fpAnimswapImgFP1'].src=document['fpAnimswapImgFP1'].lowsrc;" onmouseout="document['fpAnimswapImgFP1'].src=document['fpAnimswapImgFP1'].imgRolln">
    	<img align="absbottom" border="0" src="${contextPath}/resources/img/customer.png" id="fpAnimswapImgFP1" name="fpAnimswapImgFP1" dynamicanimation="fpAnimswapImgFP1" lowsrc="${contextPath}/resources/img/customer-mo.png"></a>
   		<a href="${contextPath}/index.jsp" onmouseover="document['fpAnimswapImgFP2'].imgRolln=document['fpAnimswapImgFP2'].src;document['fpAnimswapImgFP2'].src=document['fpAnimswapImgFP2'].lowsrc;" onmouseout="document['fpAnimswapImgFP2'].src=document['fpAnimswapImgFP2'].imgRolln">
    	<img align="absbottom" border="0" src="${contextPath}/resources/img/encoder.png" id="fpAnimswapImgFP2" name="fpAnimswapImgFP2" dynamicanimation="fpAnimswapImgFP2" lowsrc="${contextPath}/resources/img/encoder-mo.png"></a>
		<a href="${contextPath}/manage/index.jsp" onmouseover="document['fpAnimswapImgFP3'].imgRolln=document['fpAnimswapImgFP3'].src;document['fpAnimswapImgFP3'].src=document['fpAnimswapImgFP3'].lowsrc;" onmouseout="document['fpAnimswapImgFP3'].src=document['fpAnimswapImgFP3'].imgRolln">
    	<img align="absbottom" border="0" src="${contextPath}/resources/img/manager.png" id="fpAnimswapImgFP3" name="fpAnimswapImgFP3" dynamicanimation="fpAnimswapImgFP3" lowsrc="${contextPath}/resources/img/manager-mo.png"></a>
		<a href="${contextPath}/owner/index.jsp" onmouseover="document['fpAnimswapImgFP4'].imgRolln=document['fpAnimswapImgFP4'].src;document['fpAnimswapImgFP4'].src=document['fpAnimswapImgFP4'].lowsrc;" onmouseout="document['fpAnimswapImgFP4'].src=document['fpAnimswapImgFP4'].imgRolln">
    	<img align="absbottom" border="0" src="${contextPath}/resources/img/owner.png" id="fpAnimswapImgFP4" name="fpAnimswapImgFP4" dynamicanimation="fpAnimswapImgFP4" lowsrc="${contextPath}/resources/img/owner-mo.png"></a>
		<a href="${contextPath}/admin/index.jsp" onmouseover="document['fpAnimswapImgFP5'].imgRolln=document['fpAnimswapImgFP5'].src;document['fpAnimswapImgFP5'].src=document['fpAnimswapImgFP5'].lowsrc;" onmouseout="document['fpAnimswapImgFP5'].src=document['fpAnimswapImgFP5'].imgRolln">
    	<img align="absbottom" border="0" src="${contextPath}/resources/img/admin.png" id="fpAnimswapImgFP5" name="fpAnimswapImgFP5" dynamicanimation="fpAnimswapImgFP5" lowsrc="${contextPath}/resources/img/admin-mo.png"></a>
		</td>
	
  	</tr>
</table>