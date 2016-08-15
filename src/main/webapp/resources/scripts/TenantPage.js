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
		
    });
    
    // This responds to the Update button on the screen
    function updateButtonHandler() {
    	var tenant = new Object();
    	tenant.tenantId   = document.getElementById('idInput').value;
    	tenant.tenantName = document.getElementById('nameInput').value;
    	tenant.logoPic   = getImageData();
    	
		if (tenant.tenantId == '') {
			displayFadingMessage(ERROR, 'Tenant Id cannot be blank.');
			return false;
		}
		
		// check if tenant already exists
		var tenantExists = checkTenant(tenant);
		if (tenantExists === false) {
			displayFadingMessage(ERROR, 'Tenant not found in the system.');
			return false;
		}
		
		if (tenant.tenantName == '') {
			displayFadingMessage(ERROR, 'Tenant Name cannot be blank.');
			return false;
		}
		
		var result = updateTenantAjax(tenant);
		
		if (result === true) {
			displayFadingMessage(SUCCESS, 'Tenant successfully updated.');
		}
		else {
			displayFadingMessage(ERROR, 'Tenant update failed.');
		}
		return false;
    }
    
    // This responds to the Insert button on the screen
    function insertButtonHandler() {
    	var tenantId = document.getElementById('idInput').value;
		if (tenantId == '') {
			displayFadingMessage(ERROR, 'Tenant Id cannot be blank.');
			return false;
		}
		
		// check if tenant already exists
    	var tenant = new Object();
    	tenant.tenantId = tenantId;
		var tenantExists = checkTenant(tenant);
		if (tenantExists === true) {
			displayFadingMessage(ERROR, 'This tenant is already in the system.');
			return false;
		}
		
    	var tenantName = document.getElementById('nameInput').value;
		if (tenantName == '') {
			displayFadingMessage(ERROR, 'Tenant name cannot be blank.');
			return false;
		}
		
		// submit tenant form to the Spring controller
		document.forms["tenantForm"].submit(); //first submit
		displayFadingMessage(SUCCESS, 'Tenant successfully inserted.');
		return false;
    }
    
    // This responds to the picture's Browse button 
    function loadImageFile(event) {
        var output = document.getElementById('imageFile');
        output.src = URL.createObjectURL(event.target.files[0]);
    }
    
    function clearForm() {
    	document.getElementById('idInput').value = '';
    	document.getElementById('nameInput').value = '';
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
    
    function updateTenantAjax(tenant) {
    	var result = false;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/updateTenantAjax',
		    data        : JSON.stringify(tenant), // Note: this is important
		    contentType : 'application/json',
		    async       : false, 
		    success     : function(tenantResponse) {
				if (tenantResponse.result == 'success') {
					result = true;
	            }
			},
			error       : function(xhr, status, error) {
				alert('Error: '+error);
			}
		});
		return result;
    }
    
    function checkTenant(tenant) {
    	var result = false;
		$.ajax({
		    type        : 'POST',
		    url         : contextPath + '/checkTenantAjax',
		    data        : JSON.stringify(tenant), // Note: this is important
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
