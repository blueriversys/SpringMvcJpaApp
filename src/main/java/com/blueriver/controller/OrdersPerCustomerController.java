package com.blueriver.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.model.Customer;
import com.blueriver.model.Order;
import com.blueriver.model.OrderItem;
import com.blueriver.model.support.CompleteOrderDetail;
import com.blueriver.model.support.ConversionUtility;

@Controller
public class OrdersPerCustomerController {
	
	@RequestMapping(value="/orderspercustomer**", method=RequestMethod.GET)
	public String loadFormPage(Model m) {
		m.addAttribute("ordersPerCustomer", getOrdersPerCustomer());
		return "OrdersPerCustomerPage";
	}

	private List<CompleteOrderDetail> getOrdersPerCustomer() {
		Collection<Customer> customers = RepositoryUtilityImpl.getInstance().getCustomers();
		List<CompleteOrderDetail> items = new ArrayList<CompleteOrderDetail>();
		
		for (Customer customer : customers) {
			Collection<Order> orders = customer.getOrders();
			for (Order order : orders) {
				Collection<OrderItem> orderItems = order.getItems();
				for (OrderItem orderItem : orderItems) {
					CompleteOrderDetail item = new CompleteOrderDetail(
						customer.getCustomerId(), 
						customer.getCustomerName(),
						order.getOrderId(), 
						ConversionUtility.getFormattedDate(order.getOrderDate()),
						orderItem.getItemId().toString(), 
						orderItem.getProduct().getProductDescr(),
						orderItem.getQuantity().toString(),
						ConversionUtility.getFormattedValue(orderItem.getUnitPrice())
					);
					items.add(item);
				}
			}
		}
		setCorrectValues(items);
		return items;
	}
	
	private void setCorrectValues(List<CompleteOrderDetail> items) {
		Long lastCustomerId = null;
		Long lastOrderId = null;
		
		for (CompleteOrderDetail item : items) {
			if (!item.getCustomerId().equals(lastCustomerId) ) {
				lastCustomerId = item.getCustomerId();
			}
			else {
				item.setCustomerId(null); // null means blank at the client side
				item.setCustomerName(null);
			}
			
			if (!item.getOrderId().equals(lastOrderId)) {
				lastOrderId = item.getOrderId();
			}
			else {
				item.setOrderId(null); // null means blank at the client side
				item.setOrderDate(null);
			}
			
		}
	}
	
}
