package com.blueriver.model.support;

import com.blueriver.model.Address;
import com.blueriver.model.Phone;

public class CustomerResponse {
	private String result;
	private String customerId;
	private String customerName;
	private Address customerAddress;
	private Phone customerPhone;
	
	// all getter methods
	public String getResult() {
		return result;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public Address getCustomerAddress() {
		return customerAddress;
	}
	public Phone getCustomerPhone() {
		return customerPhone;
	}
	
	// all setter methods
	public void setResult(String result) {
		this.result = result;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	public void setCustomerPhone(Phone customerPhone) {
		this.customerPhone = customerPhone;
	}
	
}
