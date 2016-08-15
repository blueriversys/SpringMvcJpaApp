package com.blueriver.config;

import java.util.HashMap;

public class Authentication {
	private static HashMap<String, String> users = new HashMap<String, String>();
	
	static {
		users.put("joe", "admin");
		users.put("guest", "password");
	}
	
	public static HashMap<String, String> getUsers() {
		return users;
	}
	
	public static boolean validate(String user, String password) {
		String pass = users.get(user);
		if (user == null || password == null)
			return false;
		
		if ( !password.equals(pass) ) {
			return false;
		}
		
		return true; // user is authenticated
	}
}
