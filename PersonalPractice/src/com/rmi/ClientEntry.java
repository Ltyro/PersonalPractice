package com.rmi;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientEntry {
    
    public static void main(String []args){
        
        try {
        	
            Registry registry = LocateRegistry.getRegistry("localhost", 2002);
            RmiInterface userManager = (RmiInterface)registry.lookup("rmiInterfaceImpl");
            System.out.println(userManager.getSomething());
            
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}