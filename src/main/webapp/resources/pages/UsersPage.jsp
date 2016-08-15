<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
	</head>
	<body>
  		<div id="container">
			<div class="centered">		
				<p>
						<c:forEach items="${usersList}" var="user">
							${user}
							<br>
						</c:forEach>
				<a href="home">Home Page</a>
		    </div>			
		</div>			
	</body>
</html>