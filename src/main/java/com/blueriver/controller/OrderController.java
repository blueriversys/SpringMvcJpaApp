package com.blueriver.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;

import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.model.Customer;
import com.blueriver.model.Order;
import com.blueriver.model.OrderItem;
import com.blueriver.model.Product;
import com.blueriver.model.support.ConversionUtility;
import com.blueriver.model.support.OrderDataEntry;
import com.blueriver.model.support.OrderItemDataEntry;
import com.blueriver.model.support.OrderRequest;
import com.blueriver.model.support.ShortOrderDetail;

@Controller
public class OrderController {
	private Logger logger = Logger.getLogger(OrderController.class.getName());
	
	public OrderController() {
	}
	
	@RequestMapping(value="/order**", method=RequestMethod.GET)
	public String loadFormPage(Model model) {
		// the attribute "orders" contains a list of ShortOrderDetail objects
		model.addAttribute("orders", getOrders());
		return "OrderPage";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
	@ResponseBody
	public ShortOrderDetail receiveItems(@RequestBody String orderStr, Model model) {
		// convert JSON to OrderDataEntry
		ObjectMapper mapper = new ObjectMapper();
		OrderDataEntry order = null;
		try {
			order = mapper.readValue(orderStr, OrderDataEntry.class);
		}
		catch (Exception ex) {
			
		}
		
        // add OrderItem to the Order object
		Order anOrder = new Order(order.getOrderId(), order.getCustomerId(), order.getOrderDate());
		for (OrderItemDataEntry item : order.getItems()) {
			Product product = RepositoryUtilityImpl.getInstance().getProductById(item.getProductSku());
			OrderItem anItem = new OrderItem(anOrder, item.getItemId(), product, item.getQuantity(), item.getUnitPrice());
			anOrder.addItem(anItem);
		}
		
		// add Order to database
		RepositoryUtilityImpl.getInstance().addOrder(anOrder);
		logger.debug("order added to database");
        
        // add that Order to the corresponding Customer
        Customer customer = RepositoryUtilityImpl.getInstance().getCustomerById(order.getCustomerId());
        RepositoryUtilityImpl.getInstance().addOrderToCustomer(customer, anOrder);
		logger.debug("customer updated with new order");

		// build object to return to client
		ShortOrderDetail shortOrder = new ShortOrderDetail();
		shortOrder.setStatus("Success");
		shortOrder.setOrderId(order.getOrderId());
		shortOrder.setCustomerId(order.getCustomerId());
		shortOrder.setCustomerName(customer.getCustomerName());
		shortOrder.setOrderDate(ConversionUtility.getFormattedDate(order.getOrderDate()));
		double orderTotal = ConversionUtility.getOrderTotal(anOrder);
		shortOrder.setOrderTotal(ConversionUtility.getFormattedValue(orderTotal));
		return shortOrder;
	}

	@RequestMapping(value = "/orderAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public String checkOrder(@RequestBody String orderIdStr) {
		ObjectMapper mapper = new ObjectMapper();
		OrderRequest orderObj = null;
		try {
			orderObj = mapper.readValue(orderIdStr, OrderRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		Long orderId = orderObj.getOrderId();
		
		logger.debug("customer id: "+orderId);
		
		Order order = RepositoryUtilityImpl.getInstance().getOrderById(orderId);
		String ret = "found";
		
		if (order == null)
			ret = "not found";
		return ret;
	}
	
	private List<ShortOrderDetail> getOrders() {
		List<ShortOrderDetail> orderList = new ArrayList<ShortOrderDetail>();
		for (Order order : RepositoryUtilityImpl.getInstance().getOrders()) {
			ShortOrderDetail shortOrder = new ShortOrderDetail();
			shortOrder.setOrderId(order.getOrderId());
			shortOrder.setCustomerId(order.getCustomerId());
			Customer customer = RepositoryUtilityImpl.getInstance().getCustomerById(order.getCustomerId());
			shortOrder.setCustomerName(customer.getCustomerName());
			shortOrder.setOrderDate(ConversionUtility.getFormattedDate(order.getOrderDate()));
			double orderTotal = ConversionUtility.getOrderTotal(order);
			shortOrder.setOrderTotal(ConversionUtility.getFormattedValue(orderTotal));
			// add to list
			orderList.add(shortOrder);
		}
		return orderList;
	}
}
