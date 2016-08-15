package com.blueriver.beans;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Scope("request")
public class TenantIdentifier {
	private String host;
	private String userInfo;
	private String protocol;
	
	public TenantIdentifier() {
		 HttpServletRequest request = ((ServletRequestAttributes)
				 RequestContextHolder.getRequestAttributes()).getRequest();
		 
		// url based customization
	    URL url = null;
		try {
			url = new URL(request.getRequestURL().toString());
		    host  = url.getHost();
		    userInfo = url.getUserInfo();
		    protocol = url.getProtocol();
		    int port = url.getPort();
		    String path = (String)request.getAttribute("javax.servlet.forward.request_uri");
		    String query = (String)request.getAttribute("javax.servlet.forward.query_string");
		    System.out.println("host: "+host);
		    System.out.println("user info: "+userInfo);
		    System.out.println("protocol: "+protocol);
		    System.out.println("port: "+port);
		    System.out.println("path: "+path);
		    System.out.println("query: "+query);
		} 
		catch (MalformedURLException e) {
			System.out.println("Error getting request info.");
			e.printStackTrace();
		}
	}
	
	public String getHost() {
		return host;
	}
	
	public String getUserInfo() {
		return userInfo;
	}
	
	public String getScheme() {
		return protocol;
	}
	
}
