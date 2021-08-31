
package rmi.ejemplo;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class usuarios {
    
    public static void main(String[] args){
        
        try{
            Registry reg=LocateRegistry.getRegistry("localhost",1099);
            NewClass servidor= (NewClass) reg.lookup("rmi://localhost/service");
            Scanner entrada=new Scanner(System.in);
            
            System.out.println("Ingrese usuario: ");
            String username=entrada.nextLine();
            System.out.println("Ingrese contraseña: ");
            String password =entrada.nextLine();
            
            String response=servidor.logeo(username,password);
            System.out.println("Response form server: "+response);
            
            
            
        }catch(RemoteException | NotBoundException ex){
            
            System.out.println(ex.getMessage());
        }
        
        
    }
}

