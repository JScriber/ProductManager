<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="./template/language.jsp" %>
		
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>
			<fmt:message key="add_product.title"/>
		</title>
		<%@include file="./template/head.jsp" %>
	</head>
	<body>
		<%@include file="./template/header.jsp" %>
		
		<div class="card">
			  <div class="card-header">
			    <fmt:message key="add_product.title"/>
			  </div>
	
			  <div class="card-body">
				<form action="" method="POST">
				  <div class="form-group">
				    <label for="name">		
						<fmt:message key="add_product.name"/>
					</label>
	
					<input class="form-control" type="text" name="name" id="name" required/>
				  </div>

				  <div class="form-group">
				    <label for="description">		
						<fmt:message key="add_product.description"/>
					</label>
	
					<textarea class="form-control" name="description" id="description" required></textarea>
				  </div>
				  
				  <div class="form-group">
				  	<label for="price">		
						<fmt:message key="add_product.price"/>
					</label>
	
					<input class="form-control" type="number" name="price" id="price" required/>
				  </div>
				  
				  
				  <div class="form-group">
				  	<label for="category">
				  		<fmt:message key="add_product.category"/>
				  	</label>
				  	
					<select id="category" name="category" class="form-control">
						<c:forEach items="${requestScope.categories}" var="category">
							<option value="${category.id}" selected>
								${category.name}
							</option>
						</c:forEach>
					</select>
				  </div>

				  <button type="submit" class="btn btn-primary">
				  	<fmt:message key="add_product.submit"/>
				  </button>
				</form>
			</div>
		</div>

		<%@include file="./template/footer.jsp" %>
	</body>
</html>
