<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
	</head>
	<body>
  		<div id="container">
			<div class="centered">		
				<h1 class="centered-bg">About</h1>
				
			    <p>This is a Java web application at the core level which uses some modules of the Spring 
			    framework - and its Dependency Injection capabilities - to do the work.
			    <p>Instead of using XML configuration, I decided to show how to use Java-only 
			    configuration style for both Security and DI, through annotations.
			    
				<p>
				The main goal of the application is to showcase how a multi-tenant web application can be 
				implemented. A multi-tenant application is one that allows a software firm (developers)
				to deploy a single application to be used by a whole group of user-clients, minimizing
				development efforts (SaaS architecture) yet keeping their data completely separate and 
				giving each of them a sense of ownership.
				
				<p>
				This application uses the Spring Framework and a JPA implementation (EclipseLink) to 
				illustrate how the one-to-many relationship works at both Java and database level. 
				
				<p>
				Let me know if I can help further. I'm a Software Architect and Developer and am open to 
				contract work or employment if the opportunity is right.
				
				<p>
				Deployment date ${buildDate}. 
				<p><img src="<c:url value="/resources/images/java_logo.png" />" /> + 
				<img src="<c:url value="/resources/images/spring_mini_logo.png" />" /> +
				<img src="<c:url value="/resources/images/eclipselink_logo.png" />" /> +
				<img src="<c:url value="/resources/images/postgresql_logo.png" />" />
				<p><a href="home">Home Page</a>
		    </div>			
		</div>			
	</body>
</html>