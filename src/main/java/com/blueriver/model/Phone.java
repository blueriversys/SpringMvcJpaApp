package com.blueriver.model;

import java.io.Serializable;

public class Phone implements Serializable {
	private static final long serialVersionUID = -7347111165520022454L;
	private int areaCode;
	private int prefix;
	private int suffix;
	
	// all getters
	public int getAreaCode() {
		return areaCode;
	}
	public int getPrefix() {
		return prefix;
	}
	public int getSuffix() {
		return suffix;
	}
	
	// all setters
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(int suffix) {
		this.suffix = suffix;
	}
}
