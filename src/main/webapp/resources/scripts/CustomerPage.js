	var SUCCESS = 0;
	var ERROR = 1;  
	
    $(document).ready(function() {
  		$("#success-message-box").hide();
		$("#error-message-box").hide();

	    $('#newsletterCheckbox').change(function() {
	        toggleFrequencySelectBox();
	    });
	    
		$(function() {
		    $('.toggler-title').click(function() {
		        $(this).find('.toggler-body').slideToggle();
		    });
		});		
		
/*	
			// blue() means out of focus in jQuery
			$('#idInput').blur(function() {
		    	var customer = new Object();
		    	customer.customerId = document.getElementById('idInput').value;
		    	if (customer.customerId != '')
		    		checkCustomerAjax(customer);
			});
*/	
		$("#getCustomerButton").click(function(e) {
			e.preventDefault();
	    	var customer = new Object();
	    	customer.customerId = document.getElementById('idInput').value;
	    	
	    	if (customer.customerId == '')
	    		return;
	    	
	    	var custResponse = getCustomerAjax(customer);
	    	
	    	if (custResponse.result == 'not found') {
				displayFadingMessage(ERROR, 'Customer not found.');
				return;
	    	}
	    	
			document.getElementById("nameInput").value = custResponse.customerName;
			document.getElementById("streetInput").value = custResponse.customerAddress.street;
			document.getElementById("cityInput").value = custResponse.customerAddress.city;
			document.getElementById("stateInput").value = custResponse.customerAddress.state;
			document.getElementById("zipInput").value = custResponse.customerAddress.zip;
			document.getElementById("areaCodeInput").value = custResponse.customerPhone.areaCode;
			document.getElementById("prefixInput").value = custResponse.customerPhone.prefix;
			document.getElementById("suffixInput").value = custResponse.customerPhone.suffix;
		});
		
    });
    
    function updateButtonHandler() {
    	var customer = new Object();
    	customer.customerId = document.getElementById('idInput').value;
    	customer.customerName = document.getElementById('nameInput').value;
    	customer.customerAddress = new Object();
    	customer.customerAddress.street = document.getElementById('streetInput').value;
    	customer.customerAddress.city = document.getElementById('cityInput').value;
    	customer.customerAddress.state = document.getElementById('stateInput').value;
    	customer.customerAddress.zip = document.getElementById('zipInput').value;
    	customer.customerPhone = new Object();
    	customer.customerPhone.areaCode = document.getElementById('areaCodeInput').value;
    	customer.customerPhone.prefix = document.getElementById('prefixInput').value;
    	customer.customerPhone.suffix = document.getElementById('suffixInput').value;
    	
		if (customer.customerId == '') {
			displayFadingMessage(ERROR, 'Customer Id cannot be blank.');
			return false;
		}
		
		if (customer.customerName == '') {
			displayFadingMessage(ERROR, 'Customer name cannot be blank.');
			return false;
		}
		
		var result = updateCustomerAjax(customer);
		
		if (result === true) {
			clearForm();
			displayFadingMessage(SUCCESS, 'Customer successfully updated.');
		}
		else {
			displayFadingMessage(ERROR, 'Customer update failed.');
		}
		return false;
    }
    
    function deleteButtonHandler() {
    	var customer = new Object();
    	customer.customerId = document.getElementById('idInput').value;
    	
		if (customer.customerId == '') {
			displayFadingMessage(ERROR, 'Customer Id cannot be blank.');
			return false;
		}
		
		var custResponse = getCustomerAjax(customer);
		
		if (custResponse.result == 'not found') {
			displayFadingMessage(ERROR, 'Customer not found in the system.');
			return false;
		}
		
		var custResponse = deleteCustomerAjax(customer);
		
		if (custResponse.result == 'success') {
			clearForm();
			displayFadingMessage(SUCCESS, 'Customer successfully deleted.');
		}
		else
		if (custResponse.result == 'has orders') {
			displayFadingMessage(ERROR, 'Customer has orders, cannot be deleted.');
		}
		else {
			displayFadingMessage(ERROR, 'Customer deletion failed.');
		}
		return false;
    }
    
    function validateForm() {
    	var customer = new Object();
    	customer.customerId = document.getElementById('idInput').value;
    	customer.customerName = document.getElementById('nameInput').value;
    	
		if (customer.customerId == '') {
			displayFadingMessage(ERROR, 'Customer Id cannot be blank.');
			return false;
		}
		
		if (customer.customerName == '') {
			displayFadingMessage(ERROR, 'Customer name cannot be blank.');
			return false;
		}
		
		var custResponse = getCustomerAjax(customer);
		
		if (custResponse.result == 'found') {
			displayFadingMessage(ERROR, 'This customer already in the system.');
			return false;
		}
		
		document.forms["customerForm"].submit(); //first submit
		displayFadingMessage(SUCCESS, 'Customer successfully entered.');
		return false;
    }
    
    function clearForm() {
    	document.getElementById('idInput').value = '';
    	document.getElementById('nameInput').value = '';
    	document.getElementById('streetInput').value = '';
    	document.getElementById('cityInput').value = '';
    	document.getElementById('stateInput').value = '';
    	document.getElementById('zipInput').value = '';
    	document.getElementById('areaCodeInput').value = '';
    	document.getElementById('prefixInput').value = '';
    	document.getElementById('suffixInput').value = '';
    }
    
    /*
     	This function makes a SYNCHRONOUS call returns true or false.
     	The controller returns the short version of the customer object.
    */
/*	    
	    function checkCustomerAjax(customer) {
	    	var result = false;
			$.ajax({
			    type        : 'POST',
			    url         : 'checkCustomerAjax',
			    data        : JSON.stringify(customer), // Note: this is important
			    contentType : 'application/json',
			    async       : false, // NOTE: this is important for the validation process
			    success     : function(custResponse) {
					if (custResponse.result == 'found') {
						result = true;
		            }
				},
				error       : function(xhr, status, error) {
					alert('Error: '+error);
				}
			});
			return result;
	    }
*/	    
    function updateCustomerAjax(customer) {
    	var result = false;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/updateCustomerAjax',
		    data        : JSON.stringify(customer), // Note: this is important
		    contentType : 'application/json',
		    async       : false, 
		    success     : function(custResponse) {
				if (custResponse.result == 'success') {
					result = true;
	            }
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return result;
    }
    
    function deleteCustomerAjax(customer) {
    	var custResponse;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/deleteCustomerAjax',
		    data        : JSON.stringify(customer), 
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
    
    function getCustomerAjax(customer) {
    	var customerResponse;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/getCustomerAjax',
		    data        : JSON.stringify(customer), // Note: this is important
		    contentType : 'application/json',
		    async       : false, 
		    success     : function(response) {
				customerResponse = response;
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return customerResponse;
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
	
    function toggleFrequencySelectBox() {
	    if(!$('#newsletterCheckbox').is(':checked')) {
	        $('#frequencySelect').val('');
	        $('#frequencySelect').prop('disabled', true);
	    } 
	    else {
	        $('#frequencySelect').prop('disabled', false);
	    }
    }
