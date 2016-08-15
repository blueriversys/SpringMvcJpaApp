<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="resources/styles/catalog.css" media="screen" />
	</head>
	<body>
			<p>
			<h1 class="centered-bg">Product Catalog</h1>
			<div class="centered-img-list">		
	        	<div class="centered"><a href='<spring:url value="/home" />'>Home Page</a></div>
				<br>
				<div class="vert-scroll">
					<table>
						<c:forEach items="${products}" var="product">
					
						  <tr>
						    <td>
								<div class="images">
								  <img src="${product.productStringPic}"/>
								</div>
							</td>
							<td>
								<div class="image-text">
								    SKU: ${product.productSku}
									<br>
									Descr: ${product.productDescr}
									<br>
									Orig. pic name: ${product.picFilename}
									<br>
									Dimens.: ${product.picWidth}x${product.picHeight}
									<br>
									Size: ${product.size}
								</div>
							</td>
						  </tr>		
						  
						  <tr><td colspan="2">&nbsp;</td></tr>
						</c:forEach>
	
					  </table>
		    	</div>			
		    </div>			
	</body>
</html>