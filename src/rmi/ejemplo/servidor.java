package rmi.ejemplo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servidor extends UnicastRemoteObject implements NewClass {

    private TreeMap clients = new TreeMap<String, String>();

    public servidor() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            servidor obj = new servidor();
            reg.rebind("rmi://localhost/service", obj);
            System.out.println("Servidor corriendo");
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public String logeo(String username, String password) throws RemoteException {
        init();
        String response = search(username, password);
        return response;

    }

    private String search(String username, String password) {
        String response = "";
        Set set = clients.entrySet();
        Iterator itr = set.iterator();
        boolean flag = false;
        while (itr.hasNext()) {
            response = "";
            Map.Entry entry = (Map.Entry) itr.next();
            String user = entry.getKey().toString();
            String pass = entry.getValue().toString();
            if (username.equals(user)) {
                flag=true;
                if (password.equals(pass)) {
                    response = "INGRESASTE";
                } else {
                    response = "COTRASEÑA INCORRECTA";

                }
                break;
            }
        }

        if (!flag) {
            response = "EL usuario no existe";
        }
        return response;

    }

    private void init() {

        clients.put("usuario1", "pass1");
        clients.put("usuario2", "pass2");
    }
}
