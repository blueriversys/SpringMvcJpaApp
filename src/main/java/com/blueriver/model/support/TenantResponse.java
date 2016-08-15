package com.blueriver.model.support;

public class TenantResponse {
	private String result;
	private String tenantId;
	private String tenantName;
	private byte[] tenantPic;
	private String tenantLogoString;
	private String logoFilename;
	private int picWidth;
	private int picHeight;
	private long size;
	
	public String getResult() {
		return result;
	}
	public String getTenantId() {
		return tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public byte[] getTenantPic() {
		return tenantPic;
	}
	public String getTenantLogoString() {
		return tenantLogoString;
	}
	public String getLogoFilename() {
		return logoFilename;
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
	public void setTenantId(String productSku) {
		this.tenantId = productSku;
	}
	public void setTenantName(String productDescr) {
		this.tenantName = productDescr;
	}
	public void setTenantPic(byte[] productPic) {
		this.tenantPic = productPic;
	}
	public void setTenantLogoString(String tenantLogoString) {
		this.tenantLogoString = tenantLogoString;
	}
	public void setLogoFilename(String logoFilename) {
		this.logoFilename = logoFilename;
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
