package com.blueriver.model.support;


public class ShortOrderDetail {
	private String status;
	private Long orderId;
	private Long customerId;
	private String customerName;
	private String orderDate;
	private String orderTotal;
	
	public ShortOrderDetail() {}
	
	public ShortOrderDetail(String status, Long orderId, Long customerId, String customerName, String orderDate, String orderTotal) {
		this.status = status;
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}

	// all getters
	public String getStatus() {
		return status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	
	// all setters
	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}
	
}

	
	
	
