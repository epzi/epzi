package rmi.ejemplo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewClass extends Remote{
   
    
    public String logeo(String username,String password) throws RemoteException;
    
    


}