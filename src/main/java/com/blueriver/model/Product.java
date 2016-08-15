package com.blueriver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity (name = "PRODUCT")
public class Product implements Comparable<Product> {
	@Id
	private String productSku;
	
	private String productDescr;
	private String picFilename;
	private int picWidth;
	private int picHeight;
	
	@Lob
	private byte[] productPic;
	
	public Product() {}
	
	public Product(String productSku, String productDescr, byte[] productPic) {
		this.productSku = productSku;
		this.productDescr = productDescr;
		this.productPic = productPic;
	}
	
	// get methods
	public String getProductSku() {
		return productSku;
	}
	
	public String getProductDescr() {
		return productDescr;
	}
	
	public byte[] getProductPic() {
		return productPic;
	}
	
	public String getPicFilename() {
		return picFilename;
	}

	public int getPicWidth() {
		return picWidth;
	}

	public int getPicHeight() {
		return picHeight;
	}
	
	// set methods
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	
	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}
	
	public void setProductPic(byte[] productPic) {
		this.productPic = productPic;
	}
	
	public void setPicFilename(String picFilename) {
		this.picFilename = picFilename;
	}

	public void setPicWidth(int picWidth) {
		this.picWidth = picWidth;
	}

	public void setPicHeight(int picHeight) {
		this.picHeight = picHeight;
	}


	@Override
	public int compareTo(Product productToCompare) {
		return productSku.compareTo(productToCompare.getProductSku());
	}

}

