package com.blueriver.model.support;

public class TenantRequest {
	private String tenantId;
	private String tenantName;
	private String logoPic;
	private byte[] logoPicBinary;
	
	public TenantRequest() {}
	
	public TenantRequest(String productSku, String productDescr, String productPic) {
		this.tenantId = productSku;
		this.tenantName = productDescr;
		this.logoPic = productPic;
	}
	
	// get methods
	public String getTenantId() {
		return tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public String getLogoPic() {
		return logoPic;
	}

	
	// set methods
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}
	public byte[] getLogoPicBinary() {
		return logoPicBinary;
	}
	public void setLogoPicBinary(byte[] logoPicBinary) {
		this.logoPicBinary = logoPicBinary;
	}
}

