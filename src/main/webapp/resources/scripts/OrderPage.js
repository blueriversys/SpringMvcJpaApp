	var SUCCESS = 0;
	var ERROR = 1;  
	
	$(document).ready(function() {
  		$("#success-message-box").hide();
		$("#error-message-box").hide();
		
		// blue() means out of focus in jQuery
		$('#customerIdInput').blur(function() {
	    	var customer = new Object();
	    	customer.customerId = document.getElementById('customerIdInput').value;
	    	var custResponse;
	    	
	    	if (customer.customerId != '')
	    		custResponse = getCustomerAjax(customer);
	    	else
	    		return;
	    	
	    	if (custResponse.result == 'found') {
	    		document.getElementById('customerName').value = custResponse.customerName;
				document.getElementById("orderDateInput").focus();
	    	}
	    	else {
				displayFadingMessage(ERROR, 'Customer not found.');
	    		document.getElementById('customerName').value = '';
				document.getElementById("orderDateInput").focus();
	    	}
	    	
		});
		
		// blue() means out of focus in jQuery
		$('#orderIdInput').blur(function() {
	    	var order = new Object();
	    	order.orderId = document.getElementById('orderIdInput').value;
	    	if (order.orderId != '')
	    		ajaxCheckOrder(order);
		});
		
		// blue() means out of focus in jQuery
		$('#productSku0').blur(function() {
			var id = $(this).attr("id");
	    	var product = new Object();
	    	product.productSku = document.getElementById(id).value;
	    	if (product.productSku != '') {
	    		var nameId = replaceIdForName(id);
	    		checkProductAjax(product, nameId);
	    	}
		});
		
		$("#btnSubmit").click(function(e) {
			e.preventDefault();
			var order = getJsonObject('itemsTable');

			$.ajax({
			    type        : 'POST',
			    url         : contextPath + '/order',
			    data        : JSON.stringify(order), // Note: this is important
			    contentType : 'application/json',
			    success     : function(result) {
			    	if (result.status == 'Success') {
						displayFadingMessage(SUCCESS, 'Order was added successfully.');
						addOrderRow('ordersTable', result);
			    	}
			    	else {
						displayFadingMessage(ERROR, 'A problem happened adding the order.');
			       		alert("Server's response: "+result);
			    	}
				},
				error       : function(xhr, status, error) {
					alert('Error: '+error);
				}
			});
   
			deleteAndCleanItems('itemsTable');
			cleanOrderFields();
		});
	});

    function getCustomerAjax(customer) {
    	var custResponse;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/getCustomerAjax',
		    data        : JSON.stringify(customer), // Note: this is important
		    contentType : 'application/json',
		    async       : false,
		    success     : function(response) {
		    	custResponse = response;
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return custResponse;
    }
    
    function ajaxCheckOrder(order) {
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/orderAjax',
		    data        : JSON.stringify(order), // Note: this is important
		    contentType : 'application/json',
		    success     : function(result) {
				if (result == 'found') {
					displayFadingMessage(ERROR, 'This Order Id is already in the system.');
	            }
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
    }
    
    function checkProductAjax(product, nameId) {
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/checkProductAjax',
		    data        : JSON.stringify(product), // Note: this is important
		    contentType : 'application/json',
		    success     : function(productResponse) {
				if (productResponse.result == 'not found') {
					displayFadingMessage(ERROR, 'Product not found in the system.');
	            }
				else {
					document.getElementById(nameId).value = productResponse.productDescr;
//					document.getElementById("orderDateInput").focus();
				}
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
    }
    
	function displayFadingMessage(type, text) {
		if (type == SUCCESS) {
			document.getElementById("success-message-box").innerHTML = text;
			$("#success-message-box").show();
			$("#success-message-box").fadeOut(3000);
		}
		else {
			document.getElementById("error-message-box").innerHTML = text;
			$("#error-message-box").show();
			$("#error-message-box").fadeOut(3000);
		}
	}
	
	function getJsonObject(tableName) {
		var obj = new Object();
		obj.orderId = document.getElementById('orderIdInput').value;
		obj.customerId = document.getElementById('customerIdInput').value;
		var strDate = document.getElementById('orderDateInput').value;
		obj.orderDate = new Date(strDate).toISOString();
		obj.items = [];
	
		var table = document.getElementById(tableName);
		var ind = 0;
		for (var r = 0, n = table.rows.length; r < n; r++) {
			if (table.rows[r].id == 'dataitem') {
					obj.items[ind] = new Object();
					obj.items[ind].itemId      = table.rows[r].cells[1].children[0].value;
					obj.items[ind].productSku  = table.rows[r].cells[2].children[0].value;
					obj.items[ind].quantity    = table.rows[r].cells[4].children[0].value;
					obj.items[ind].unitPrice   = table.rows[r].cells[5].children[0].value;
					ind++;
			}
		}
		return obj;
	}

	function addOrderRow(tableId, orderObj) {
	    var table = document.getElementById(tableId);
	    var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount);
		row.id = "dataitem";

	    var orderIdCell = row.insertCell(0);
	    orderIdCell.innerHTML = orderObj.orderId;

	    var customerIdCell = row.insertCell(1);
	    customerIdCell.innerHTML = orderObj.customerId;

	    var custNameCell = row.insertCell(2);
	    custNameCell.innerHTML = orderObj.customerName;

	    var orderDateCell = row.insertCell(3);
		orderDateCell.innerHTML = orderObj.orderDate;
		
	    var orderTotalCell = row.insertCell(4);
	    orderTotalCell.align = "right";
	    orderTotalCell.innerHTML = orderObj.orderTotal; 
	}
	
	function addItemRow(tableId) {
	    var table = document.getElementById(tableId);

	    var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount);
		row.id = "dataitem";
	    var counts = rowCount-1;

	    var checkBoxCell = row.insertCell(0);
	    var checkbox = document.createElement("input");
	    checkbox.id = "checkbox";
	    checkbox.type = "checkbox";
	    checkBoxCell.appendChild(checkbox);
		
	    var itemIdCell = row.insertCell(1);
	    var itemId = document.createElement("input");
	    itemId.id = "itemId";
	    itemId.type = "text";
	    itemId.size = "5";
		itemId.value = rowCount;
	    itemIdCell.appendChild(itemId);

	    var productSkuCell = row.insertCell(2);
	    var prodSku = document.createElement("input");
	    prodSku.id = "productSku"+rowCount;
	    prodSku.type = "text";
	    prodSku.size = "5";
	    productSkuCell.appendChild(prodSku);
	    var skuId = "#"+prodSku.id; 
		// blue() means out of focus in jQuery
		$(skuId).blur(function() {
			var id = $(this).attr("id");
	    	var product = new Object();
	    	product.productSku = document.getElementById(id).value;
	    	if (product.productSku != '') {
	    		var nameId = replaceIdForName(id);
	    		checkProductAjax(product, nameId);
	    	}
		});
	    
	    var cell2 = row.insertCell(3);
	    var prodName = document.createElement("input");
	    prodName.id = "productName"+rowCount;
	    prodName.type = "text";
	    prodName.size = "40";
	    prodName.readOnly = true;
	    cell2.appendChild(prodName);

	    var cell3 = row.insertCell(4);
	    var quant = document.createElement("input");
		quant.setAttribute('class', 'number');
	    quant.id = "quantity";
	    quant.type = "text";
	    quant.size = "5";
	    cell3.appendChild(quant);

	    var cell4 = row.insertCell(5);
	    var price = document.createElement("input");
		price.setAttribute('class', 'number');
	    price.id = "unitPrice";
	    price.type = "text";
	    price.size = "5";
	    cell4.appendChild(price);
	}
	
	function replaceIdForName(id) {
		var res = id.replace("productSku","productName");
		return res;
	}
	
	function deleteItemRow(tableId){
		try{
			var table = document.getElementById(tableId);		
			var rowCount = table.rows.length;
			for(var i=0; i<rowCount; i++){
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if(null!=chkbox && true==chkbox.checked){
					table.deleteRow(i);
					rowCount--;
					i--;
				}
			}
		}
		catch(e){
			alert(e);
		}
	}

	function cleanOrderFields() {
		document.getElementById("orderIdInput").value = '';
		document.getElementById("customerIdInput").value = '';
		document.getElementById("orderDateInput").value = '';
	}
	
	function deleteAndCleanItems(tableId){
		try{
			var table = document.getElementById(tableId);		
			var rowCount = table.rows.length;
			for(var i=2; i<rowCount; i++){
				table.deleteRow(i);
				rowCount--;
				i--;
			}
			
			var row = table.rows[1];
			row.cells[1].childNodes[0].value = '1'; // item number
			row.cells[2].childNodes[0].value = ''; // product sku
			row.cells[3].childNodes[0].value = ''; // product name
			row.cells[4].childNodes[0].value = ''; // quantity
			row.cells[5].childNodes[0].value = ''; // unit price
		}
		catch(e){
			alert(e);
		}
	}
