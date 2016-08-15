package com.blueriver.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity (name = "CUSTOMER")
public class Customer implements Comparable<Customer> {
	@Id
	private Long customerId;
	
	private String customerName;
    private Address customerAddress;
    private Phone customerPhone;
     
    @OneToMany
    @JoinTable(name = "ORDER_BY_CUSTOMER", joinColumns = @JoinColumn(name = "CUSTOMER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
    private Collection<Order> orders;
	
	public Customer() {}
	
	public Customer(Long clientId, String customerName) {
		this.customerId = clientId;
		this.customerName = customerName;
	}
	
	// get methods
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Collection<Order> getOrders() {
		return orders;
	}
	
	public Address getCustomerAddress() {
		return customerAddress;
	}

	public Phone getCustomerPhone() {
		return customerPhone;
	}

	
	// set methods
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void addOrder(Order order) {
		if (orders==null) {
			orders = new ArrayList<Order>();
		}
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setCustomerPhone(Phone customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public int compareTo(Customer compareCustomer) {
		return this.customerId.compareTo(compareCustomer.getCustomerId());
	}
}

