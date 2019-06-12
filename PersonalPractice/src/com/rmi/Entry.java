package com.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Entry {
    public static void main(String []args) throws AlreadyBoundException, RemoteException{
    	
        RmiInterfaceImpl rmiInterfaceImpl = new RmiInterfaceImpl();
        RmiInterface rmiInterface = (RmiInterface) UnicastRemoteObject.exportObject(rmiInterfaceImpl, 0);
        
        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.createRegistry(2002);
       
        registry.rebind("rmiInterfaceImpl", rmiInterface);
        System.out.println("server is ready");
        
    }
}