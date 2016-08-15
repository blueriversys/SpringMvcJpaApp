package com.blueriver.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-27T03:18:35.364-0500")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, String> productSku;
	public static volatile SingularAttribute<Product, String> productDescr;
	public static volatile SingularAttribute<Product, String> picFilename;
	public static volatile SingularAttribute<Product, Integer> picWidth;
	public static volatile SingularAttribute<Product, Integer> picHeight;
	public static volatile SingularAttribute<Product, byte[]> productPic;
}
