package com.lnstark.pp.basic;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

public class TestVM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = ManagementFactory.getRuntimeMXBean().getName();

        String[] items = name.split("@");

        int pid = Integer.parseInt(items[0]);
        String host = items[1];
        Object ip = getLocalIPAddress().getHostAddress();
        getProperties();
//        configFromProperty(System.getProperties());
	}

	public static InetAddress getLocalIPAddress() {
        try {
            Enumeration<?> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration<?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    inetAddress = (InetAddress) e2.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.getHostAddress().contains(":")) {
                        return inetAddress;
                    }
                }
            }
        } catch (Exception e) {
            
        }

        return null;
    }
	
	public static void getProperties() {
		Properties p = System.getProperties();
		for(Object key:p.keySet()){
			System.out.println(key+":"+p.getProperty((String) key));
		}
		System.out.println();
 
	}

}
