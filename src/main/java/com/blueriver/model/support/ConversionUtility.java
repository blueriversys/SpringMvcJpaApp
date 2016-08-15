package com.blueriver.model.support;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.blueriver.model.Order;
import com.blueriver.model.OrderItem;

public final class ConversionUtility {
	public static String getFormattedDate(Date date) {
		//date to dd-MMM-yy format e.g. "14-Sep-11"
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(date);
	}
	
	public static double getOrderTotal(Order order) {
		double orderTotal = 0;
		for (OrderItem item : order.getItems()) {
			orderTotal += item.getQuantity() * item.getUnitPrice(); 
		}
		return orderTotal;
	}
	
	public static String getFormattedValue(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}
	
	
}
