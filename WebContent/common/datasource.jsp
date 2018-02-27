<fmt:bundle basename="application">
<fmt:message key="mainDataSource.driverClass" var="driver"/>
</fmt:bundle>
<fmt:bundle basename="application">
<fmt:message key="mainDataSource.jdbcUrl" var="dburl"/>
</fmt:bundle>
<fmt:bundle basename="application">
<fmt:message key="mainDataSource.user" var="dbuser"/>
</fmt:bundle>
<fmt:bundle basename="application">
<fmt:message key="mainDataSource.password" var="dbpass"/>
</fmt:bundle>
<sql:setDataSource
	var="prenda"
	driver="${driver}"
	url="${dburl}?useUnicode=true&characterEncoding=UTF-8&user=${dbuser}&password=${dbpass}"
/>