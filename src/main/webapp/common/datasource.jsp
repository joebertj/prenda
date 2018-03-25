<fmt:bundle basename="application">
		 <fmt:message var="driverClass" key = "mainDataSource.driverClass"/><br/>
         <fmt:message var="jdbcUrl" key = "mainDataSource.jdbcUrl"/><br/>
         <fmt:message var="jdbcuser" key = "mainDataSource.user"/><br/>
         <fmt:message var="password" key = "mainDataSource.password"/><br/>
</fmt:bundle>
<sql:setDataSource
	var="prenda"
	driver="${driverClass}"
	url="${jdbcUrl}"
	user="${jdbcuser}"
	password="${password}"
/>