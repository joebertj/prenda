<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}"/>
<c:set var="fullPath" scope="request" value="${pageContext.request.requestURI}"/>
<c:choose>
	<c:when test="${param.lang!=null}">
		<fmt:setLocale value="${param.lang}"/>
	</c:when>
	<c:when test="${cookie['prenda-locale-cookie']!=null}">
		<fmt:setLocale value="${cookie['prenda-locale-cookie'].value}"/>
	</c:when>
</c:choose>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prenda 0.1.4 BETA</title>
<link rel="icon" type="image/x-icon" href="${contextPath}/common/img/favicon.ico">
<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/style.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/ajaxtags.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/displaytag.css" />