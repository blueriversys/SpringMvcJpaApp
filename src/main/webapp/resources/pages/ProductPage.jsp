<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Product Module</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src='<spring:url value="/resources/scripts/ProductPage.js" />' ></script>
	<link rel="stylesheet" type="text/css" href='<spring:url value="/resources/styles/style.css" htmlEscape="true"/>'  media="screen" />
  </head>
  <body>

  <div id="container-large">
	<div class="success-msg-box" id="success-message-box"></div> 
	<div class="error-msg-box" id="error-message-box"></div>

    <div class="centered-bg">Product Module</div>
    <p>

    <form:form action="${pageContext.request.contextPath}/product" modelAttribute="product" name="productForm" method="POST" enctype="multipart/form-data">
    	<table>
    	  <tr>
    		<td>
		    	<table>
		    	  <tr>
		    	    <td><label class="right-just">Product SKU: </label></td>
		      		<td><form:input path="productSku" id="skuInput" value="${product.productSku}" size="6"/> <input type="button" value="Get" id="getProductButton"/>  </td>
		      	  </tr>
		          <tr>
		            <td><label class="right-just">Product Descr: </label></td>
		            <td><form:input path="productDescr" id="descrInput" value="${product.productDescr}" size="30"/></td>
		          </tr>
				  <tr>
		            <td><label class="right-just">Product Picture: </label></td>
				    <td>
				    	<input type="file" name="fileContent" accept="image/*" onchange="loadImageFile(event)">
				    </td>
				  </tr>
				</table>
    		</td>
    		
    	    <td>
				<table>
				  <tr>
				    <td>
				        <div class="proportional-img-div">
				    		<img id="imageFile" class="proportional-img" src="${product.productStringPic}" alt="Choose a product's pic to go here" />
						</div>				    		
				    </td>
				  </tr>
				</table>      
    	    </td>
    	  </tr>
    	</table>
    	
	    <div class="centered">
	        <a href='<spring:url value="/home" />'>Home Page</a>
	    	<input type="button" value="Enter New Product" onClick="validateForm()" />
	    	<input type="button" value="Update" onClick="updateButtonHandler()" />
	    	<input type="button" value="Delete" onClick="deleteButtonHandler()" />
	    </div>
      
<%-- 
      <label for="genderOptions">Gender: </label>
      <form:select path="gender" id="genderOptions">
        <form:option value="">Select Gender</form:option>
        <form:option value="MALE">Male</form:option>
        <form:option value="FEMALE">Female</form:option>
      </form:select>
      <br/>

      <label for="newsletterCheckbox">Newsletter? </label>
      <form:checkbox path="receiveNewsletter" id="newsletterCheckbox" />
      <br/>
      <label for="frequencySelect">Freq:</label>
      <form:select path="newsletterFrequency" id="frequencySelect">
        <form:option value="">Select Newsletter Frequency: </form:option>
        <c:forEach items="${frequencies}" var="frequency">
          <form:option value="${frequency}">${frequency}</form:option>
        </c:forEach>
      </form:select>
      <br/>
--%>
    </form:form>

      
	<p>
	
	<div class="vert-scroll">
		<table cellspacing="0" border="1">
		    <tr>
		        <th>Product SKU</th>
		        <th>Product Description</th>
		    </tr>
			<c:forEach items="${products}" var="product">
				<tr>
				 <td>${product.productSku}</td>
				 <td>${product.productDescr}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="centered-div">
		<div class="toggler-title"> 
		    Click for an overview of the features. 
	    	<div class="toggler-body"> 
	    	  This is the Product Module of the application.
	    	  The server side controller can accept RESTful requests from this module
	    	  in addition to the the regular Submit requests and the AJAX requests.
	    	  For example, you can type "[appname]/product/1" in the address bar and the controller
	    	  will know that you want to view the product whose SKU is 1. 
	    	</div>
		</div>		
	</div>
		
  </div>
      
  <script>
  	var contextPath = '${pageContext.request.contextPath}';
  </script>

  </body>
</html>
