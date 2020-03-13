<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="./template/language.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>
			<fmt:message key="show_product.title"/>
		</title>
		<%@include file="./template/head.jsp" %>
	</head>

	<body>
		<%@include file="./template/header.jsp" %>
		
		<div class="card">
			<div class="card-header">
				<fmt:message key="show_product.title"/>
		  	</div>

		  	<div class="card-body">
				<p><strong><fmt:message key="show_product.name"/></strong> ${requestScope.product.name}</p>
				<p><strong><fmt:message key="show_product.description"/></strong> ${requestScope.product.description}</p>
				<p><strong><fmt:message key="show_product.price"/></strong> ${requestScope.product.price}</p>
				<p><strong><fmt:message key="show_product.category"/></strong> ${requestScope.product.category.name}</p>
		  	</div>
	  	</div>
		
		<%@include file="./template/footer.jsp" %>
	</body>
</html>