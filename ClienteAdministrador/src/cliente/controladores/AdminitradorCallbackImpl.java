package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class AdminitradorCallbackImpl extends UnicastRemoteObject implements AdministradorCallbackInt{

    public AdminitradorCallbackImpl() throws RemoteException{
    super();}
    
    
    @Override
    public void notificarRegistroNuevaCancion(String mensaje) throws RemoteException {
        System.out.print("\nNueva cancion registrada: " + mensaje);
    }

   

    
}
