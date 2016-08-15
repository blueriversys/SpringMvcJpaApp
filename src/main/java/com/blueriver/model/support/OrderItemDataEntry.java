package com.blueriver.model.support;

public class OrderItemDataEntry {
	private Long orderId;
	private Long itemId;
	private String productSku;
	private Integer quantity;
	private Double unitPrice;
	
	public OrderItemDataEntry() {}
	
	public OrderItemDataEntry(Long orderId, Long itemId, String productSku, Integer quantity, Double unitPrice) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.productSku = productSku;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	// all my getters
	public Long getOrderId() {
		return orderId;
	}
	public Long getItemId() {
		return itemId;
	}
	public String getProductSku() {
		return productSku;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	
	// all my setters
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
