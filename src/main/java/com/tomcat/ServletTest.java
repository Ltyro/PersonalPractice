package com.tomcat;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4651221076648102133L;

	@Override  
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {  
		
        Double input = Double.parseDouble(request.getParameter("number"));  
        double result = Math.sqrt(input);  
        String message = "The result is " + result;  
        response.getOutputStream().write(message.getBytes());  
        
    }  
	
}
