package com.blueriver.model.support;

import java.util.Collection;
import java.util.Date;

public class OrderDataEntry {
	private Long customerId;
	private Long orderId;
	private Date orderDate;
	private Collection<OrderItemDataEntry> items;
	
	public OrderDataEntry() {}
	
	public OrderDataEntry(Long customerId, Long orderId, Date orderDate, Collection<OrderItemDataEntry> items) {
		this.customerId = customerId;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.items = items;
	}
	
	// all my getters
	public Long getCustomerId() {
		return customerId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public Collection<OrderItemDataEntry> getItems() {
		return items;
	}


	// all my setters
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setItems(Collection<OrderItemDataEntry> items) {
		this.items = items;
	}
}

