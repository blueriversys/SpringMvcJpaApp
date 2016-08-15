package com.blueriver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "ORDER_ITEM")
@IdClass (OrderItemPK.class)
public class OrderItem {
	@Id
	private Long orderId;
	
	@Id
	private Long itemId;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable=false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable=false)
	private Product product;
	
	private Integer quantity;
	private Double unitPrice;
	
	public OrderItem() {}
	
	public OrderItem(Order order, Long itemId, Product product, Integer quantity, Double unitPrice) {
		this.orderId = order.getOrderId();
		this.order = order;
		this.itemId = itemId;
		this.product = product;
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
	public Product getProduct() {
		return product;
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
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
