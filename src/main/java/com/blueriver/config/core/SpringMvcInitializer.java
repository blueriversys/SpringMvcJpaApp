package com.blueriver.config.core;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.blueriver.config.AppConfig;
 
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(new SessionListener());
/*        
        HttpServletRequest request = ((ServletRequestAttributes)
        		RequestContextHolder.getRequestAttributes()).getRequest();
        
        // url based customization
        URL url = null;
        if (request == null) {
        	System.out.println("Unable to get the HttpServletRequest object");
        }
        else {
	        try {
	        	url = new URL(request.getRequestURL().toString());
	        	String host  = url.getHost();
	        	String userInfo = url.getUserInfo();
	        	String scheme = url.getProtocol();
	        	int port = url.getPort();
	        	String path = (String)request.getAttribute("javax.servlet.forward.request_uri");
	        	String query = (String)request.getAttribute("javax.servlet.forward.query_string");
	        	System.out.println("host: "+host);
	        	System.out.println("user info: "+userInfo);
	        	System.out.println("scheme: "+scheme);
	        	System.out.println("port: "+port);
	        	System.out.println("path: "+path);
	        	System.out.println("query: "+query);
	        } 
	        catch (MalformedURLException e) {
	        	System.out.println("Error getting request info.");
	        	e.printStackTrace();
	        }
        }
*/        
    }	
}