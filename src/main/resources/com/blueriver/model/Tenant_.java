package com.blueriver.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-22T17:31:12.418-0400")
@StaticMetamodel(Tenant.class)
public class Tenant_ {
	public static volatile SingularAttribute<Tenant, String> productSku;
	public static volatile SingularAttribute<Tenant, String> productDescr;
	public static volatile SingularAttribute<Tenant, String> picFilename;
	public static volatile SingularAttribute<Tenant, Integer> picWidth;
	public static volatile SingularAttribute<Tenant, Integer> picHeight;
	public static volatile SingularAttribute<Tenant, byte[]> productPic;
}
