package com.blueriver.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-27T03:18:35.361-0500")
@StaticMetamodel(Order.class)
public class Order_ {
	public static volatile SingularAttribute<Order, Long> orderId;
	public static volatile SingularAttribute<Order, Long> customerId;
	public static volatile SingularAttribute<Order, Date> orderDate;
	public static volatile CollectionAttribute<Order, OrderItem> items;
}
