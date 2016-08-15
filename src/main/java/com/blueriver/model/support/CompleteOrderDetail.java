package com.blueriver.model.support;


public class CompleteOrderDetail {
	private Long customerId;
	private String customerName;
	private Long orderId;
	private String orderDate;
	private String itemId;
	private String productName;
	private String quantity;
	private String unitPrice;
	
	public CompleteOrderDetail() {}
	
	public CompleteOrderDetail(Long customerId, String customerName, Long orderId, String orderDate, String itemId, String productName, String quantity, String unitPrice) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.itemId = itemId;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	// all getters
	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getItemId() {
		return itemId;
	}

	public String getProductName() {
		return productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	// all setters
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
}

	
	
	
