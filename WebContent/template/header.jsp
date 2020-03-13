<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="fr.imie.productmanager.lang.Translation"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="${pageContext.request.contextPath}">ProductManager</a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
    	<li class="nav-item<c:if test="${pageContext.request.servletPath eq '/listProduct.jsp'}"> active</c:if>">
			<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/listProduct"/>">
				<fmt:message key="header.list"/>
			</a>
		</li>

    	<li class="nav-item">
			<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/cheaperProduct"/>">
				<fmt:message key="header.cheap"/>
			</a>
		</li>
		
 		<c:if test="${sessionScope.username ne null}">
			<li class="nav-item<c:if test="${pageContext.request.servletPath eq '/addProduct.jsp'}"> active</c:if>">
				<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/auth/addProduct"/>">
					<fmt:message key="header.add.product"/>
				</a>
			</li>
		</c:if>

 		<c:if test="${sessionScope.username ne null}">
			<li class="nav-item<c:if test="${pageContext.request.servletPath eq '/addCategory.jsp'}"> active</c:if>">
				<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/auth/addCategory"/>">
					<fmt:message key="header.add.category"/>
				</a>
			</li>
		</c:if>
    </ul>
    
  </div>
  
  <c:if test="${sessionScope.username ne null}">
	  <span class="navbar-text">
			<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/logout"/>">
				<fmt:message key="header.logout"/>
			</a>
	   </span>
  </c:if>

  <c:if test="${sessionScope.username eq null}">
	  <span class="navbar-text">
			<a class="nav-link" href="<c:out value="${pageContext.request.contextPath}/login"/>">
				<fmt:message key="header.login"/>
			</a>
	   </span>
  </c:if>

</nav>
