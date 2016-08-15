package com.blueriver.model;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = -7159483171127254196L;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	// all getters
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	
	// all setters
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
