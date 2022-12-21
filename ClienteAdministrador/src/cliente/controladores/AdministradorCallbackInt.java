

package cliente.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;


//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface AdministradorCallbackInt extends Remote
{
    public void notificarRegistroNuevaCancion(String mensaje)throws RemoteException;
}
