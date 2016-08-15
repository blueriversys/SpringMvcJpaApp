package com.blueriver.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blueriver.beans.RepositoryUtilityImpl;
//import com.blueriver.main.JpaDriver;
import com.blueriver.model.Customer;
import com.blueriver.model.support.CustomerRequest;
import com.blueriver.model.support.CustomerResponse;

@Controller
public class CustomerController {
	private Logger logger = Logger.getLogger(CustomerController.class.getName());
	
	@RequestMapping(value="/customer**", method=RequestMethod.GET)
	public String loadFormPage(Model m) {
		m.addAttribute("customer", new Customer());
		m.addAttribute("customers", getOrderedCustomers());
		return "CustomerPage";
	}

	/*
	 * This is the method called when the client clicks on the "Submit" button
	 */
	@RequestMapping(value="customer", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute Customer customer, Model m) {
		//m.addAttribute("message", "Successfully saved customer: " + customer.toString());
		
		// add Customer to the database
		//JpaDriver.addCustomer(customer);
		RepositoryUtilityImpl.getInstance().addCustomer(customer);
		
		
		// add all customers to the returning list
		m.addAttribute("customer", new Customer());
		m.addAttribute("customers", getOrderedCustomers());
		return "CustomerPage";
	}

	/*
	 * This is called when the request URL is for example http://appname/product/1 
	 */
	@RequestMapping("/customer/{customerId}")
	public String getCustomerById(@PathVariable long customerId, Model model)  {
		logger.debug("in getCustomerById()");
		Customer customer = RepositoryUtilityImpl.getInstance().getCustomerById(customerId);
		CustomerResponse custResponse = new CustomerResponse();
		custResponse.setCustomerId(customerId+"");
		
		if (customer == null) {
			custResponse.setCustomerName("Customer not found in the system.");
		}
		else {
			custResponse.setCustomerName(customer.getCustomerName());
			custResponse.setCustomerAddress(customer.getCustomerAddress());
			custResponse.setCustomerPhone(customer.getCustomerPhone());
		}
		
		model.addAttribute("customer", custResponse);
		model.addAttribute("customers", getOrderedCustomers());
		return "CustomerPage";
	}
	
	
	/*
	 * This method will be called when the client just wants to check whether or not
	 * a customer is already in the system.
	 */
/*	
	@RequestMapping(value = "/checkCustomerAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public CustomerResponse checkCustomerAjax(@RequestBody String custIdStr) {
		ObjectMapper mapper = new ObjectMapper();
		CustomerRequest custRequest = null;
		try {
			custRequest = mapper.readValue(custIdStr, CustomerRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		Long customerId = custRequest.getCustomerId();
		Customer customer = JpaDriver.getCustomerById(customerId);
		CustomerResponse custResponse = new CustomerResponse();
		
		if (customer != null) {
			custResponse.setResult("found");
			custResponse.setCustomerId(customer.getCustomerId().toString());
			custResponse.setCustomerName(customer.getCustomerName());
		}
		else {
			custResponse.setResult("not found");
		}
		return custResponse;
	}
*/	

	/*
	 * This method will be called when the client needs to retrieve all the 
	 * data of 1 customer.
	 */
	@RequestMapping(value = "/getCustomerAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public CustomerResponse getCustomerAjax(@RequestBody String customerIdStr) {
		ObjectMapper mapper = new ObjectMapper();
		CustomerRequest customerRequest = null;
		try {
			customerRequest = mapper.readValue(customerIdStr, CustomerRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		Long custId = customerRequest.getCustomerId();
		Customer customer = RepositoryUtilityImpl.getInstance().getCustomerById(custId);
		CustomerResponse custResponse = new CustomerResponse();
		
		if (customer != null) {
			custResponse.setResult("found");
			custResponse.setCustomerId(customer.getCustomerId().toString());
			custResponse.setCustomerName(customer.getCustomerName());
			custResponse.setCustomerAddress(customer.getCustomerAddress());
			custResponse.setCustomerPhone(customer.getCustomerPhone());
		}
		else {
			custResponse.setResult("not found");
		}
		return custResponse;
	}

	
	/*
	 * This is the method called when the client clicks on the "Update" button
	 */
	@RequestMapping(value = "/updateCustomerAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public CustomerResponse updateCustomerAjax(@RequestBody String stringCustomerReq) {
		ObjectMapper mapper = new ObjectMapper();
		CustomerRequest customerReq = null;
		try {
			customerReq = mapper.readValue(stringCustomerReq, CustomerRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerReq.getCustomerId());
		customer.setCustomerName(customerReq.getCustomerName());
		customer.setCustomerAddress(customerReq.getCustomerAddress());
		customer.setCustomerPhone(customerReq.getCustomerPhone());
		
		CustomerResponse custResponse = new CustomerResponse();
		
		// update database
		try {
			//JpaDriver.updateCustomer(customer);
			RepositoryUtilityImpl.getInstance().updateCustomer(customer);
			custResponse.setResult("success");
		}
		catch (IllegalArgumentException ex) {
			custResponse.setResult("failure");
		}
		
		return custResponse;
	}
	
	/*
	 * This is the method called when the client clicks on the "Delete" button
	 */
	@RequestMapping(value = "/deleteCustomerAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public CustomerResponse deleteCustomerAjax(@RequestBody String stringCustomerReq) {
		ObjectMapper mapper = new ObjectMapper();
		CustomerRequest customerReq = null;
		try {
			customerReq = mapper.readValue(stringCustomerReq, CustomerRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		CustomerResponse custResponse = new CustomerResponse();
		//Customer customer = JpaDriver.getCustomerById(customerReq.getCustomerId());
		Customer customer = RepositoryUtilityImpl.getInstance().getCustomerById(customerReq.getCustomerId());

		if (customer.getOrders().size() > 0) {
			custResponse.setResult("has orders");
		}
		else {
			// update database
			try {
				//JpaDriver.deleteCustomer(customerReq.getCustomerId());
				RepositoryUtilityImpl.getInstance().deleteCustomer(customerReq.getCustomerId());
				custResponse.setResult("success");
			}
			catch (Exception ex) {
				custResponse.setResult("failure");
			}
		}
		
		return custResponse;
	}
	
	private List<Customer> getOrderedCustomers() {
		// order customers by Id
		//List<Customer> customerList = JpaDriver.getCustomers();
		List<Customer> customerList = RepositoryUtilityImpl.getInstance().getCustomers();
		Collections.sort(customerList);
		return customerList;
	}
}
