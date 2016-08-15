package com.blueriver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "ORDER")
public class Order {
	@Id
	private Long orderId;
	private Long customerId;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
    //@OneToMany
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @JoinTable(name = "ITEMS_BY_ORDER", 
    	joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private Collection<OrderItem> items;
	
	public Order() {}
	
	public Order(Long orderId, Long customerId, Date orderDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
	}
	
	// all my getters
	public Long getOrderId() {
		return orderId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public Collection<OrderItem> getItems() {
		return items;
	}


	// all my setters
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setClientId(Long customerId) {
		this.customerId = customerId;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setItems(Collection<OrderItem> items) {
		this.items = items;
	}
	public void addItem(OrderItem item) {
		if (items==null) {
			items = new ArrayList<OrderItem>();
		}
		if (!items.contains(item)) {
			items.add(item);
		}
	}

}

