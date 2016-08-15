package com.blueriver.beans;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import com.blueriver.model.Customer;
import com.blueriver.model.Order;
import com.blueriver.model.Product;
import com.blueriver.model.Tenant;
import com.blueriver.model.support.ProductRequest;
import com.blueriver.model.support.TenantRequest;

public interface RepositoryUtility {
	public void addCustomer(Customer customer);
	public List<Customer> getCustomers();	
	public Customer getCustomerById(long customerId);
	public void updateCustomer(Customer customer) throws IllegalArgumentException;
	public void deleteCustomer(long customerId) throws IllegalArgumentException, PersistenceException, EntityNotFoundException;
	public void addOrder(Order order);
	public void addOrderToCustomer(Customer customer, Order order);
	public Order getOrderById(long orderId);
	public List<Order> getOrders();
	public void addProduct(Product product);
	public List<Product> getProducts();
	public Product getProductById(String productSku);
	public void updateProduct(ProductRequest prodRequest) throws IllegalArgumentException;
	public void deleteProduct(String productSku) throws IllegalArgumentException, PersistenceException, EntityNotFoundException;
	
	public void addTenant(Tenant tenant);
	public Tenant getTenantById(String id);
	public Tenant getTenant();
	public void updateTenant(TenantRequest tenantRequest) throws IllegalArgumentException;
}
