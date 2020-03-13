<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="./template/language.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>
			<fmt:message key="error.title"/>
		</title>
		<%@include file="./template/head.jsp"%>
	</head>

	<body>
		<%@include file="./template/header.jsp"%>
		
		<div class="container">
		    <p>${requestScope.errorMessage}</p>
		</div>

		<%@include file="./template/footer.jsp" %>
	</body>
</html>
