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
        // This was commented out because 
        // it would make a server request even on the "view" mode, 
        // which is something undesired.
        
		// blue() means out of focus in jQuery
		$('#skuInput').blur(function(e) {
			e.preventDefault();
	    	var product = new Object();
	    	product.productSku = document.getElementById('skuInput').value;
	    	if (product.productSku == '')
	    		return;
			var prodResponse = getProduct(product);
			
			if (prodResponse.result == 'found') {
				displayFadingMessage(ERROR, 'Product already in the system.');
			}
		});
*/		
		
		$("#getProductButton").click(function(e) {
			e.preventDefault();
	    	var product = new Object();
	    	product.productSku = document.getElementById('skuInput').value;
	    	if (product.productSku == '')
	    		return;
			var prodResponse = getProduct(product);
			
			if (prodResponse.result == 'found') {
				if (prodResponse.productStringPic == '')  
					document.getElementById('imageFile').src = "resources/images/img_not_available.png";
				else
					document.getElementById('imageFile').src = prodResponse.productStringPic;
				document.getElementById("descrInput").value = prodResponse.productDescr;
            }
			else {
				displayFadingMessage(ERROR, 'Product not found in the system.');
			}
		});
		
    });
    
    function updateButtonHandler() {
    	var product = new Object();
    	product.productSku   = document.getElementById('skuInput').value;
    	product.productDescr = document.getElementById('descrInput').value;
		product.productPic   = getImageData();
    	
		if (product.productSku == '') {
			displayFadingMessage(ERROR, 'Product SKU cannot be blank.');
			return false;
		}
		
		if (product.productDescr == '') {
			displayFadingMessage(ERROR, 'Product description cannot be blank.');
			return false;
		}
		
		var result = updateProductAjax(product);
		
		if (result === true) {
			clearForm();
			displayFadingMessage(SUCCESS, 'Product successfully updated.');
		}
		else {
			displayFadingMessage(ERROR, 'Product update failed.');
		}
		return false;
    }
    
    function deleteButtonHandler() {
    	var product = new Object();
    	product.productSku = document.getElementById('skuInput').value;
    	
		if (product.productSku == '') {
			displayFadingMessage(ERROR, 'Product SKU cannot be blank.');
			return false;
		}
		
		var prodResponse = getProduct(product);
		
		if (prodResponse.result == 'not found') {
			displayFadingMessage(ERROR, 'Product not found in the system.');
			return false;
		}
		
		prodResponse = deleteProductAjax(product);
		
		if (prodResponse.result == 'success') {
			clearForm();
			displayFadingMessage(SUCCESS, 'Product successfully deleted.');
		}
		else
		if (prodResponse.result == 'has items') {
			displayFadingMessage(ERROR, 'Product ref. by items, cannot be deleted.');
		}
		else {
			displayFadingMessage(ERROR, 'Product deletion failed.');
		}
		return false;
    }
    
    function validateForm() {
    	var prodSku = document.getElementById('skuInput').value;
		if (prodSku == '') {
			displayFadingMessage(ERROR, 'Product SKU cannot be blank.');
			return false;
		}
    	var product = new Object();
    	product.productSku = prodSku;
		var prodexists = checkProduct(product);
		if (prodexists === true) {
			displayFadingMessage(ERROR, 'This product already in the system.');
			return false;
		}
    	var prodDescr = document.getElementById('descrInput').value;
		if (prodDescr == '') {
			displayFadingMessage(ERROR, 'Product description cannot be blank.');
			return false;
		}
		
		document.forms["productForm"].submit(); //first submit
//			document.forms["productForm"].reset(); //and then reset the form values
//			document.getElementById('imageFile').src = "";
//			document.getElementById("descrInput").value = "";
//			document.getElementById("skuInput").value = "";
/*
			var myform = document.forms["productForm"];
			var frm_elements = myform.elements;
			for(i=0; i<frm_elements.length; i++)
			{
				frm_elements[i].value = "";
			}
			document.getElementById("skuInput").focus();
*/		
		displayFadingMessage(SUCCESS, 'Product successfully entered.');
		return false;
    }
    
    // This responds to the picture's Browse button 
    function loadImageFile(event) {
        var output = document.getElementById('imageFile');
        output.src = URL.createObjectURL(event.target.files[0]);
    }
    
    function clearForm() {
    	document.getElementById('skuInput').value = '';
    	document.getElementById('descrInput').value = '';
    	document.getElementById('imageFile').src = '';
    }
    
    function getImageData() {
		var img = document.getElementById("imageFile");
	    var c = document.createElement("canvas");
	    var ctx = c.getContext("2d");
	    ctx.canvas.width  = img.naturalWidth;
	    ctx.canvas.height = img.naturalHeight;
	    ctx.drawImage(img,0,0);
	    var imgInfo = c.toDataURL("image/png");
	    imgInfo.replace(/^data:image\/(png|jpg);base64,/, "");
		return imgInfo;
    }
    
/*
		function getProduct(product) {
			$.get('${pageContext.request.contextPath}/api/person/' + personId, function(person) {
        $('#personIdResponse').text(person.name + ', age ' + person.age);
      });	
		}
*/		
    function checkProduct(product) {
    	var result = false;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/checkProductAjax',
		    data        : JSON.stringify(product), // Note: this is important
		    contentType : 'application/json',
		    crossDomain : true,
		    async       : false,
		    success     : function(response) {
				if (response.result == 'found') {
					result = true;
	            }
				else {
					result = false;
				}
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
				result = true;
			}
		});
		return result;
    }
    
	function getProduct(product) {
		var prodResponse;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/getProductAjax',
		    data        : JSON.stringify(product), // Note: this is important
		    contentType : 'application/json',
		    crossDomain : true,
		    async       : false,
		    success     : function(response) {
				prodResponse = response;	
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return prodResponse;
	}

    function updateProductAjax(product) {
    	var result = false;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/updateProductAjax',
		    data        : JSON.stringify(product), // Note: this is important
		    contentType : 'application/json',
		    async       : false, 
		    success     : function(prodResponse) {
				if (prodResponse.result == 'success') {
					result = true;
	            }
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return result;
    }
    
    function deleteProductAjax(product) {
    	var prodResponse;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/deleteProductAjax',
		    data        : JSON.stringify(product), 
		    contentType : 'application/json',
		    async       : false, 
		    success     : function(response) {
		    	prodResponse = response;
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return prodResponse;
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
