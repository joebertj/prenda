<%@include file="../public/datasource.jsp"%>
<sec:authentication property="principal.username" var="authenticated"/>
<jsp:useBean id="user" class="com.prenda.service.UserService"/>
<jsp:setProperty property="userName" name="user" value="${authenticated}"/>
<jsp:useBean id="branch" class="com.prenda.service.BranchService"/>
<jsp:setProperty property="id" name="branch" value="${user.branchId}"/>
<table class="navi" style="width:100%;">
	<tr>
		<td align="right">
			<a href="${contextPath}/public/Language.htm?lang=en_US&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/usa.png"/></a>
			<a href="${contextPath}/public/Language.htm?lang=es_ES&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/spain.png"/></a>
			<a href="${contextPath}/public/Language.htm?lang=fr_FR&referer=${fullPath}"><img height="30px" src="${contextPath}/resources/img/lang/france.png"/></a>
		</td>
	</tr>
	<tr><td>
		<table align="center" class="branch">
			<tr><td align="center"><b><jsp:getProperty property="name" name="branch"/></b></td></tr>
			<tr><td align="center"><jsp:getProperty property="address" name="branch"/></td></tr>
		</table>
	</td></tr>
	<tr>
    	<td align="center">
    	<sec:authentication property="principal.authorities" var="role"/>
		<spring:message code="common.welcome" text="Welcome back"/> <c:out value="${authenticated}"/> <a href="${contextPath}/public/logout.jsp"><spring:message code="common.logout" text="Logout"/></a>
		</td>
  	</tr>
</table>