package com.blueriver.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-27T03:18:35.321-0500")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, Long> customerId;
	public static volatile SingularAttribute<Customer, String> customerName;
	public static volatile SingularAttribute<Customer, Address> customerAddress;
	public static volatile SingularAttribute<Customer, Phone> customerPhone;
	public static volatile CollectionAttribute<Customer, Order> orders;
}
