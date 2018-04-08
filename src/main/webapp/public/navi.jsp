<%@include file="datasource.jsp"%>
<table class="navi" style="width:100%;">
	<tr>
		<td align="right">
			<a href="${contextPath}/public/Language.htm?lang=en_US&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/usa.png"/></a>
			<a href="${contextPath}/public/Language.htm?lang=es_ES&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/spain.png"/></a>
			<a href="${contextPath}/public/Language.htm?lang=fr_FR&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/france.png"/></a>
		</td>
	</tr>
	<tr>
    	<td align="center"><a href="${contextPath}/customer/"><spring:message code="common.customer" text="Customer?"/></a> <a href="${contextPath}/public/login.jsp"><spring:message code="common.login" text="Login"/></a></td>
  	</tr>
</table>