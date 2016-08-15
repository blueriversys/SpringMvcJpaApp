<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Tenant Module</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src='<spring:url value="/resources/scripts/TenantPage.js" />' ></script>
	<link rel="stylesheet" type="text/css" href='<spring:url value="/resources/styles/style.css" htmlEscape="true"/>'  media="screen" />
  </head>
  <body>

  <div id="container-large">
	<div class="success-msg-box" id="success-message-box"></div> 
	<div class="error-msg-box" id="error-message-box"></div>

    <div class="centered-bg">Tenant Module</div>
    <p>

    <form:form action="${pageContext.request.contextPath}/tenant" modelAttribute="tenant" name="tenantForm" method="POST" enctype="multipart/form-data">
    	<table>
    	  <tr>
    		<td>
		    	<table>
		    	  <tr>
		    	    <td><label class="right-just">Tenant Id: </label></td>
		      		<td><form:input path="tenantId" id="idInput" value="${tenant.tenantId}" size="6" /> </td>
		      	  </tr>
		          <tr>
		            <td><label class="right-just">Tenant Name: </label></td>
		            <td><form:input path="tenantName" id="nameInput" value="${tenant.tenantName}" size="30"/></td>
		          </tr>
				  <tr>
		            <td><label class="right-just">Tenant Logo: </label></td>
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
				    		<img id="imageFile" class="proportional-img" src="${tenant.tenantLogoString}" alt="Choose a tenant logo to go here" />
						</div>				    		
				    </td>
				  </tr>
				</table>      
    	    </td>
    	  </tr>
    	</table>
    	
	    <div class="centered">
	        <a href='<spring:url value="/home" />'>Home Page</a>
	    	<input type="button" value="Enter New Tenant" onClick="insertButtonHandler()" />
	    	<input type="button" value="Update" onClick="updateButtonHandler()" />
	    </div>
      
    </form:form>

      
	<p>
	
	<div class="centered-div">
		<div class="toggler-title"> 
		    Click for an overview of the features. 
	    	<div class="toggler-body"> 
	    	  This is the Tenant Module of the application.
	    	  There shall be only one single tenant record per domain.
	    	  Therefore, once that first tenant is entered here, the user
	    	  can only update it.
	    	</div>
		</div>		
	</div>
		
  </div>
      
  <script>
  	var contextPath = '${pageContext.request.contextPath}';
  </script>

  </body>
</html>
