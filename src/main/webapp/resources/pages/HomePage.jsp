<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Enterprise Ordering App</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
  </head>
  
  <body>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>


  <div id="container">
    <div class="centered-bg">Home Page</div>
	<p>	
	<a href="customer">Customer</a><br></br>
	<a href="product">Product</a><br></br>
	<a href="order">Order Entry</a><br></br>
	<a href="orderspercustomer">List of Orders Per Customer</a><br></br>
	<a href="catalog">Catalog</a><br></br>
	<a href="tenant">Tenant Info</a><br></br>
	<a href="about">About</a><br></br>
	<a href="javascript:formSubmit()">Logout</a>
		
	<div class="centered">
<!--  			
      <img src="<c:url value="/resources/images/blueriver-logo-smaller.png" />" />
-->       
    <form:form action="${pageContext.request.contextPath}/tenant" modelAttribute="tenant" name="tenantForm" method="POST" enctype="multipart/form-data">
   		<img src="${tenant.tenantLogoString}" />
    </form:form>

    </div>			
  </div>


  </body>
</html>
