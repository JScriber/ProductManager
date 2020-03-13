<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="./template/language.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<%@include file="./template/head.jsp" %>
	</head>
	
	<body>	
		<%@include file="./template/header.jsp"%>

		<div class="card">
			<div class="card-header">
				<fmt:message key="login.title"/>
		  	</div>

		  	<div class="card-body">
		  		<form action="" method="POST">
				  <div class="form-group">
				    <label for="name">
				    	<fmt:message key="login.username"/>
					</label>
	
					<input class="form-control" type="text" name="username" id="name" required/>
				  </div>
				  
				  <div class="form-group">
				    <label for="language">
				    	<fmt:message key="login.language"/>
					</label>
					
					<select id="language" name="language" class="form-control">
						<option value="en" selected>
							<fmt:message key="login.language.english"/>
						</option>
						<option value="fr">
							<fmt:message key="login.language.french"/>
						</option>
					</select>
				  </div>

				  <button type="submit" class="btn btn-primary">
				  	<fmt:message key="login.submit"/>
				  </button>
				</form>
		  	</div>
	  	</div>
	</body>
</html>