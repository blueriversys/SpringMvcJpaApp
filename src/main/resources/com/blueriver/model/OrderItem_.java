package com.blueriver.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-27T03:18:35.363-0500")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ {
	public static volatile SingularAttribute<OrderItem, Long> orderId;
	public static volatile SingularAttribute<OrderItem, Long> itemId;
	public static volatile SingularAttribute<OrderItem, Order> order;
	public static volatile SingularAttribute<OrderItem, Product> product;
	public static volatile SingularAttribute<OrderItem, Integer> quantity;
	public static volatile SingularAttribute<OrderItem, Double> unitPrice;
}
