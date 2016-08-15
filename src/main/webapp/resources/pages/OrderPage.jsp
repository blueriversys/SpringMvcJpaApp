<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Order Data Entry</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src='<spring:url value="/resources/scripts/OrderPage.js" />' ></script>
	<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
  </head>
  <body>

  <div id="container">
	<div class="success-msg-box" id="success-message-box"></div> 
	<div class="error-msg-box" id="error-message-box"></div>
    <div class="centered-bg">Order Data Entry</div>
	<p>
	<div class="centered">
	    <form>
	    	<table>
	    	  <tr>
	    	    <td> <label class="right-just">Order Id:</label> </td>
		    	<td> <input type="text" id="orderIdInput" size="20"/> </td>
		      </tr>		
		      <tr>	
			    <td> <label class="right-just">Customer Id:</label> </td>
			    <td> <input type="text" id="customerIdInput" size="20" /> </td>
			    <td> <input type="text" id="customerName" size="30" readonly /> </td>
		      </tr>
		      <tr>			
			    <td> <label class="right-just">Order Date:</label> </td>
			    <td> <input type="text" id="orderDateInput" size="20"/> </td>
			    <td align="left"> <label>(mm/dd/yyyy)</label> </td>
			  </tr>
		    </table>
		    <p>
	
	        <div class="centered">
			<TABLE id="itemsTable" cellspacing="0" border="1">
			    <TR>
			        <TD></TD>
			        <TD>Item Id</TD>
			        <TD>Product SKU</TD>
			        <TD>Product Name</TD>
			        <TD>Quantity</TD>
			        <TD>Unit Price</TD>
			    </TR>
			    <TR id="dataitem">
				    <TD><input type="checkbox"/></TD>
			        <TD><input id="itemId" type="text" name="itemIdInput" value="1" size="5"/></TD>
			        <TD><input id="productSku0" type="text" name="productSkuInput" size="5"/></TD>
			        <TD><input id="productName0" type="text" name="productNameInput" size="40" readonly></TD>
			        <TD><input class="number" id="quantity" type="text" name="quantityInput" size="5"/></TD>
			        <TD><input class="number" id="unitPrice" type="text" name="unitPriceInput" size="5"/></TD>
			    </TR>
			</TABLE>
			</div>
			<a href="home">Home Page</a>
	        <input type="submit" id="btnSubmit" value="Submit Order"/>
			<input type="button" value="Add Item" onclick="addItemRow('itemsTable')" />
			<input type="button" value="Delete Checked Items" onclick="deleteItemRow('itemsTable')" />
	      <br/>
	    </form>
    </div>      
	<p>
	
		
	<P>

		<table id="ordersTable" cellspacing="0" border="1">
		    <tr>
		        <th>Order Id</th>
		        <th>Cust.Id</th>
		        <th>Customer Name</th>
		        <th>Order Date</th>
		        <th>Order Total</th>
		    </tr>
			<c:forEach items="${orders}" var="order">
				<tr>
				 <td>${order.orderId}</td>
				 <td>${order.customerId}</td>
				 <td>${order.customerName}</td>
				 <td>${order.orderDate}</td>
				 <td  align="right">${order.orderTotal}</td>
				</tr>
			</c:forEach>
		</table>
  </div>

  <script>
  	var contextPath = '${pageContext.request.contextPath}';
  </script>
  
  </body>
</html>
