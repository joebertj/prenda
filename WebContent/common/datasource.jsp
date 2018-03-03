<fmt:bundle basename="com.prenda.helper.ApplicationDb">
		 <fmt:message key = "driver"/><br/>
         <fmt:message key = "dburl"/><br/>
         <fmt:message key = "dburl"/><br/>
         <fmt:message key = "dbpass"/><br/>
</fmt:bundle>
<sql:setDataSource
	var="prenda"
	driver="${driver}"
	url="${dburl}&user=${dbuser}&password=${dbpass}"
/>