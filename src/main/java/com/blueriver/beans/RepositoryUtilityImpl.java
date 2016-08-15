package com.blueriver.beans;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blueriver.model.Customer;
import com.blueriver.model.Order;
import com.blueriver.model.Product;
import com.blueriver.model.Tenant;
import com.blueriver.model.support.ProductRequest;
import com.blueriver.model.support.TenantRequest;

public class RepositoryUtilityImpl implements RepositoryUtility {
	private EntityManagerFactory emf;
	private EntityManager em;
	public static String PERSISTENCE_PROP_FILE = "db.properties";
	private static RepositoryUtility repo;
	
	private RepositoryUtilityImpl() {}
	
	/*
	 * This is a singleton type object
	 */
	private RepositoryUtilityImpl(String tenantName) {
		Properties props = new Properties();
		try {
			props.load(RepositoryUtilityImpl.class.getClassLoader().getResourceAsStream("/"+PERSISTENCE_PROP_FILE));
		} 
		catch (IOException e) {
			System.out.println("Error trying to read the Properties file "+PERSISTENCE_PROP_FILE);
			e.printStackTrace();
		}

		// compose jdb.url 
		String db_url = props.getProperty("db.url");
		String db_port_number = props.getProperty("db.port.number");
		String db_name = props.getProperty("db.name");
		props.setProperty("javax.persistence.jdbc.url", db_url+":"+db_port_number+"/"+db_name); 
		props.setProperty(PersistenceUnitProperties.SESSION_CUSTOMIZER, TenantSessionCustomizer.class.getName());		
		emf = Persistence.createEntityManagerFactory("MyPersistenceUnit", props);
		TenantSessionCustomizer.setSchemaName(tenantName);
		emf = Persistence.createEntityManagerFactory("MyPersistenceUnit", props);
		em = emf.createEntityManager();
	}
	
	public static synchronized RepositoryUtility getInstance() {
		if (repo != null) {
			return repo;
		}
		else {
			HttpServletRequest request = ((ServletRequestAttributes)
					 RequestContextHolder.getRequestAttributes()).getRequest();
		    URL url = null;
		    String host = null;
			try {
				url = new URL(request.getRequestURL().toString());
			    host  = url.getHost();
			    String userInfo = url.getUserInfo();
			    String protocol = url.getProtocol();
			    int port = url.getPort();
			    String path = (String)request.getAttribute("javax.servlet.forward.request_uri");
			    String query = (String)request.getAttribute("javax.servlet.forward.query_string");
			    String urlStr = request.getRequestURL().toString();
			    System.out.println("url: "+urlStr);
			    System.out.println("host: "+host);
			    System.out.println("user info: "+userInfo);
			    System.out.println("protocol: "+protocol);
			    System.out.println("port: "+port);
			    System.out.println("path: "+path);
			    System.out.println("query: "+query);
			} 
			catch (MalformedURLException e) {
				System.out.println("Error getting request info.");
				e.printStackTrace();
			}
			repo = new RepositoryUtilityImpl(getHost(host));
			return repo;
		}
	}

	private static String getHost(String host) {
		int i = host.indexOf('.');
		String hostName = host.substring(0,i);
		System.out.println("calculated hostname: "+hostName);
		return hostName;
	}
	
	/**********************************************************************************
	 *   Customer methods                                                             *
	 **********************************************************************************/
	public void addCustomer(Customer customer) {
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers() {
	    Query query = em.createQuery("SELECT e FROM CUSTOMER e");
	    return (List<Customer>) query.getResultList();
	}

	public Customer getCustomerById(long customerId) {
		Customer customer = em.find(Customer.class, customerId);		
		return customer;
	}

	public void updateCustomer(Customer customer) throws IllegalArgumentException {
		em.getTransaction().begin();
		if(em.find(Customer.class, customer.getCustomerId()) == null) {
			throw new IllegalArgumentException("Invalid Customer Id");
		}
		em.merge(customer);
		em.getTransaction().commit();
	}
	
	public void deleteCustomer(long customerId) 
			throws IllegalArgumentException, PersistenceException, EntityNotFoundException {
		em.getTransaction().begin();
		Customer customer = em.find(Customer.class, customerId);

		if (customer == null) {
			throw new IllegalArgumentException("Invalid Customer Id");
		}
		
		em.remove(customer);
		em.getTransaction().commit();
	}
	
	/**********************************************************************************
	 *   Order methods                                                                *
	 **********************************************************************************/
	public void addOrder(Order order) {
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
	}

	public void addOrderToCustomer(Customer customer, Order order) {
		em.getTransaction().begin();
		customer.addOrder(order);
		em.persist(customer);
		em.getTransaction().commit();
	}

	public Order getOrderById(long orderId) {
		Order order = em.find(Order.class, orderId);		
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrders() {
	    Query query = em.createQuery("SELECT e FROM ORDER e");
	    return (List<Order>) query.getResultList();
	}

	/**********************************************************************************
	 *   Product methods                                                              *
	 **********************************************************************************/
	public void addProduct(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
	    Query query = em.createQuery("SELECT e FROM PRODUCT e");
	    return (List<Product>) query.getResultList();
	}

	public Product getProductById(String productSku) {
		Product product = em.find(Product.class, productSku);		
		return product;
	}

	public void updateProduct(ProductRequest prodRequest) throws IllegalArgumentException {
		em.getTransaction().begin();
		Product product = em.find(Product.class, prodRequest.getProductSku());
		
		if (product == null) {
			throw new IllegalArgumentException("Invalid Product SKU");
		}
		
		product.setProductDescr(prodRequest.getProductDescr());
		if (prodRequest.getProductPicBinary() != null) {
			product.setProductPic(prodRequest.getProductPicBinary());
		}
		em.merge(product);
		em.getTransaction().commit();
	}
	
	public void deleteProduct(String productSku) 
			throws IllegalArgumentException, PersistenceException, EntityNotFoundException {
		em.getTransaction().begin();
		Product product = em.find(Product.class, productSku);

		if (product == null) {
			throw new IllegalArgumentException("Invalid Product SKU");
		}
		
		em.remove(product);
		em.getTransaction().commit();
	}
	
	
	/**********************************************************************************
	 *   Tenant methods                                                              *
	 **********************************************************************************/
	public void addTenant(Tenant tenant) {
		em.getTransaction().begin();
		em.persist(tenant);
		em.getTransaction().commit();
	}

	public Tenant getTenantById(String tenantId) {
		Tenant product = em.find(Tenant.class, tenantId);		
		return product;
	}

	@SuppressWarnings("unchecked")
	public Tenant getTenant() {
	    Query query = em.createQuery("SELECT e FROM TENANT e");
	    List<Tenant> tenants = (List<Tenant>) query.getResultList();	 
	    if (tenants.size() > 0)
	    	return tenants.get(0);
	    else {
	    	return null;
	    }
	}

	public void updateTenant(TenantRequest tenantRequest) throws IllegalArgumentException {
		em.getTransaction().begin();
		Tenant tenant = em.find(Tenant.class, tenantRequest.getTenantId());
		
		if (tenant == null) {
			throw new IllegalArgumentException("Invalid Tenant Id");
		}
		
		tenant.setTenantName(tenantRequest.getTenantName());
		if (tenantRequest.getLogoPicBinary() != null) {
			tenant.setLogoPic(tenantRequest.getLogoPicBinary());
		}
		em.merge(tenant);
		em.getTransaction().commit();
	}
	
	// Order Item methods
//	public static void addOrderItem(OrderItem item) {
//		em.getTransaction().begin();
//		em.persist(item);
//		em.getTransaction().commit();
//	}

//	public static void addItemToOrder(Order order, OrderItem item) {
//		em.getTransaction().begin();
//		Order orderlocal = getOrderById(order.getOrderId());
//		orderlocal.addItem(item);
//		em.persist(orderlocal);
//		em.getTransaction().commit();
//	}

	
}
