<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>List of Orders Per Customer</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
  </head>
  <body>

  <div id="container-large">
    <div class="centered-bg">List of Orders Per Customer</div>
	<br>
	<div class="centered"><a href='<spring:url value="/home" />'>Home Page</a></div>
	<br>
	<table cellspacing="0" border="1">
	    <tr>
	        <th>Customer Id</th>
	        <th>Customer Name</th>
	        <th>Order Id</th>
	        <th>Order Date</th>
	        <th>Item Id</th>
	        <th>Product Name</th>
	        <th>Quantity</th>
	        <th>Unit Price</th>
	    </tr>
		<c:forEach items="${ordersPerCustomer}" var="order">
			<tr>
			 <td align="center">${order.customerId}</td>
			 <td>${order.customerName}</td>
			 <td align="center">${order.orderId}</td>
			 <td>${order.orderDate}</td>
			 <td align="center">${order.itemId}</td>
			 <td>${order.productName}</td>
			 <td align="center">${order.quantity}</td>
			 <td align="right">${order.unitPrice}</td>
			</tr>
		</c:forEach>
	</table>
  </div>

  <script type="text/javascript">

    $(document).ready(function() {
      toggleFrequencySelectBox(); // show/hide box on page load

      $('#newsletterCheckbox').change(function() {
        toggleFrequencySelectBox();
      })

    });

    function toggleFrequencySelectBox() {
      if(!$('#newsletterCheckbox').is(':checked')) {
        $('#frequencySelect').val('');
        $('#frequencySelect').prop('disabled', true);
      } else {
        $('#frequencySelect').prop('disabled', false);
      }
    }

  </script>

  </body>
</html>
