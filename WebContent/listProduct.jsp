<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="./template/language.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>
			<fmt:message key="all_products.title"/>
		</title>
		<%@include file="./template/head.jsp"%>
	</head>

	<body>
		<%@include file="./template/header.jsp"%>
		
		<div class="container">
		    <c:choose>
			  <c:when test="${requestScope.products.size() > 0}">
			    <table class="table">
				    <thead>
				        <tr>
				            <th>
				            	<fmt:message key="all_products.table.name"/>
				            </th>
				            <th>
				            	<fmt:message key="all_products.table.description"/>
				            </th>
				            <th>
				            	<fmt:message key="all_products.table.price"/>
				            </th>
				            <th>
				            	<fmt:message key="all_products.table.category"/>
				            </th>
				            <th></th>
				        </tr>
				    </thead>
					<tbody>
						<c:forEach items="${requestScope.products}" var="product">
							<tr>
				     			<td><c:out value="${product.name}"/></td>
				     			<td><c:out value="${product.description}"/></td>
				     			<td><c:out value="${product.price}"/></td>
				     			<td><c:out value="${product.category.name}"/></td>
				     			<td class="actions">
				     				<form action="<c:out value="${pageContext.request.contextPath}/showProduct"/>" method="GET">
				     					<input type="hidden" name="id" value="<c:out value="${product.id}"/>"/>
				     					<button class="btn btn-info" type="submit">
				     						<i class="fa fa-eye"></i>
				     					</button>
				     				</form>
			
				     				<form action="<c:out value="${pageContext.request.contextPath}/deleteProduct"/>" method="POST">
				     					<input type="hidden" name="id" value="<c:out value="${product.id}"/>"/>
				     					<button class="btn btn-danger" type="submit">
				     						<i class="fa fa-trash"></i>
				            			</button>
				     				</form>
			     				</td>
				     		</tr>
						</c:forEach>
					</tbody>
				</table>
			  </c:when>
	
			  <c:when test="${products.size() == 0}">
			    <h3 class="all-products">
			    	<fmt:message key="all_products.empty_list"/>
			    </h3>
			  </c:when>
			</c:choose>
		  </div>

		<%@include file="./template/footer.jsp" %>
	</body>
</html>
