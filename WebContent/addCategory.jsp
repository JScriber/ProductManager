<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="./template/language.jsp" %>
		
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>
			<fmt:message key="add_category.title"/>
		</title>
		<%@include file="./template/head.jsp" %>
	</head>
	<body>
		<%@include file="./template/header.jsp" %>
		
		<div class="card">
			  <div class="card-header">
			    <fmt:message key="add_category.title"/>
			  </div>
	
			  <div class="card-body">
				<form action="" method="POST">
				  <div class="form-group">
				    <label for="name">		
						<fmt:message key="add_category.name"/>
					</label>
	
					<input class="form-control" type="text" name="name" id="name" required/>
				  </div>

				  <button type="submit" class="btn btn-primary">
				  	<fmt:message key="add_category.submit"/>
				  </button>
				</form>
			</div>
		</div>

		<%@include file="./template/footer.jsp" %>
	</body>
</html>