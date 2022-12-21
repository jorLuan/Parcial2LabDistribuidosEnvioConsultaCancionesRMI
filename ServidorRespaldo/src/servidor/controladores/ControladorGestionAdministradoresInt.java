package servidor.controladores;

import cliente.controladores.AdministradorCallbackInt;
import java.rmi.RemoteException;
import java.rmi.Remote;


public interface ControladorGestionAdministradoresInt extends Remote{
   public void registrarReferenciaAdministrador(AdministradorCallbackInt objReferencia) throws RemoteException;
}
