<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Customer Module</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src='<spring:url value="/resources/scripts/CustomerPage.js" />' ></script>
	<link rel="stylesheet" type="text/css" href='<spring:url value="/resources/styles/style.css" htmlEscape="true"/>'  media="screen" />
  </head>
  <body>

  <div id="container">
	<div class="success-msg-box" id="success-message-box"></div> 
	<div class="error-msg-box" id="error-message-box"></div>

    <div class="centered-bg">Customer Module</div>
    <p>
    
    <!--  
    	<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
	-->
	
    <form:form action="${pageContext.request.contextPath}/customer" modelAttribute="customer" name="customerForm" method="POST">
		<label class="right-just">Customer Id: </label>
		<form:input class="number" path="customerId" id="idInput" value="${customer.customerId}" size="6"/>
		<input type="button" value="Get" id="getCustomerButton"/>
		<br/>
		
		<label class="right-just">Customer Name: </label>
		<form:input path="customerName" id="nameInput" value="${customer.customerName}" size="30"/>
		<br/>
		
		<label class="right-just">Street: </label>
		<form:input path="customerAddress.street" id="streetInput" value="${customer.customerAddress.street}" size="30"/>
		<br/>
		
		<label class="right-just">City: </label>
		<form:input path="customerAddress.city" id="cityInput" value="${customer.customerAddress.city}" size="30"/>
		<br/>
		
		<label class="right-just">State: </label>
		<form:input path="customerAddress.state" id="stateInput" value="${customer.customerAddress.state}" size="3"/>
		<br/>
		
		<label class="right-just">ZIP: </label>
		<form:input path="customerAddress.zip" id="zipInput" value="${customer.customerAddress.zip}" size="5"/>
		<br/>
		
		<label class="right-just">Phone: </label>
		(
		<form:input path="customerPhone.areaCode" id="areaCodeInput" value="${customer.customerPhone.areaCode}" size="3"/>
		)
		<form:input path="customerPhone.prefix" id="prefixInput" value="${customer.customerPhone.prefix}" size="3"/>
		-
		<form:input path="customerPhone.suffix" id="suffixInput" value="${customer.customerPhone.suffix}" size="5"/>
		
		<p>
      
	    <div class="centered">
	        <a href='<spring:url value="/home" />'>Home Page</a>
	    	<input type="button" value="Enter New Customer" onClick="validateForm()" />
	    	<input type="button" value="Update" onClick="updateButtonHandler()" />
	    	<input type="button" value="Delete" onClick="deleteButtonHandler()" />
	    </div>
    </form:form>
      
	<p>
	
	<div class="vert-scroll">		
		<table cellspacing="0" border="1">
		    <tr>
		        <th>Customer Id</th>
		        <th>Customer Name</th>
		    </tr>
			<c:forEach items="${customers}" var="customer">
				<tr>
				 <td>${customer.customerId}</td>
				 <td>${customer.customerName}</td>
				</tr>
			</c:forEach>
		</table>
    </div>
      
	<div class="centered-div">
		<div class="toggler-title"> 
		    Click for an overview of the features. 
	    	<div class="toggler-body"> 
	    	  This is the Customer Module of the application.
	    	  The Customer controller - as it's called in Spring terminology the server-side 
	    	  component that responds to the browser's request -
	    	  is capable of detecting when a form was submitted or when an AJAX call was made.
	    	  For example, filling a customer number and pushing the Get button will trigger
	    	  an AJAX call, which avoids a full page reload. 
	    	</div>
		</div>		
	</div>
      
  </div>

  <script>
  	var contextPath = '${pageContext.request.contextPath}';
  </script>

  </body>
</html>
