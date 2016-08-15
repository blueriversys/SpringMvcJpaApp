package com.blueriver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity (name = "TENANT")
public class Tenant implements Comparable<Tenant> {
	@Id
	private String tenantId;
	
	private String tenantName;
	private String logoFilename;
	private int logoWidth;
	private int logoHeight;
	
	@Lob
	private byte[] logoPic;
	
	public Tenant() {}
	
	public Tenant(String tenantId, String tenantName, byte[] logoPic) {
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.logoPic = logoPic;
	}
	
	// get methods
	public String getTenantId() {
		return tenantId;
	}
	
	public String getTenantName() {
		return tenantName;
	}
	
	public byte[] getLogoPic() {
		return logoPic;
	}
	
	public String getLogoFilename() {
		return logoFilename;
	}

	public int getLogoWidth() {
		return logoWidth;
	}

	public int getLogoHeight() {
		return logoHeight;
	}
	
	// set methods
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	public void setLogoPic(byte[] logoPic) {
		this.logoPic = logoPic;
	}
	
	public void setLogoFilename(String logoFilename) {
		this.logoFilename = logoFilename;
	}

	public void setPicWidth(int picWidth) {
		this.logoWidth = picWidth;
	}

	public void setPicHeight(int picHeight) {
		this.logoHeight = picHeight;
	}


	@Override
	public int compareTo(Tenant productToCompare) {
		return tenantId.compareTo(productToCompare.getTenantId());
	}

}

