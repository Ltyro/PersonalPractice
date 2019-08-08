package com.tomcat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TomcatStarter {

	private static Tomcat tomcat;

	public static String WEBAPP_PATH = "src/com";

	public static void main(String[] args) {
		runTomcat();
		
	}

	@Test
	public void testGet() {
		getSomeThing();
	}
	
	public static void runTomcat() {

		tomcat = new Tomcat();
		tomcat.setPort(8080);
		String tmpDirPath = System.getProperty("user.dir") + File.separator + WEBAPP_PATH;
		Context ctxt = tomcat.addContext("/sqrt", tmpDirPath);
		Tomcat.addServlet(ctxt, "servletTest", new ServletTest());
		ctxt.addServletMappingDecoded("/", "servletTest");
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
		tomcat.getServer().await();
	}

	public static void getSomeThing() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost:8080/sqrt/?number=49");
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
			assertEquals("The result is 7.0", EntityUtils.toString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
