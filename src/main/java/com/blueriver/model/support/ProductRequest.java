package com.blueriver.model.support;

public class ProductRequest {
	private String productSku;
	private String productDescr;
	private String productPic;
	private byte[] productPicBinary;
	
	public ProductRequest() {}
	
	public ProductRequest(String productSku, String productDescr, String productPic) {
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
	public String getProductPic() {
		return productPic;
	}
	
	// set methods
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public byte[] getProductPicBinary() {
		return productPicBinary;
	}

	public void setProductPicBinary(byte[] productPicBinary) {
		this.productPicBinary = productPicBinary;
	}
}

