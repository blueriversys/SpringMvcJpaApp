package com.blueriver.model.support;

public class ProductResponse {
	private String result;
	private String productSku;
	private String productDescr;
	private byte[] productPic;
	private String productPicString;
	private String picFilename;
	private int picWidth;
	private int picHeight;
	private long size;
	
	public String getResult() {
		return result;
	}
	public String getProductSku() {
		return productSku;
	}
	public String getProductDescr() {
		return productDescr;
	}
	public byte[] getProductPic() {
		return productPic;
	}
	public String getProductStringPic() {
		return productPicString;
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
	public long getSize() {
		return size;
	}
	
	// setter methods
	public void setResult(String result) {
		this.result = result;
	}
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}
	public void setProductPic(byte[] productPic) {
		this.productPic = productPic;
	}
	public void setProductPicString(String productPicString) {
		this.productPicString = productPicString;
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
	public void setSize(long size) {
		this.size = size;
	}
}
