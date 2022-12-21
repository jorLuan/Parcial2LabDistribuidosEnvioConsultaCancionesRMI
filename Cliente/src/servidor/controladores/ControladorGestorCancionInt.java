package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;
import servidor.DTO.NotifyDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorCancionInt extends Remote
{
    //Definicion del primer método remoto
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    public boolean registrarCancion(CancionDTO objCancion)  throws RemoteException;  
     public ArrayList<CancionDTO> listarCanciones() throws RemoteException;
     public CancionDTO consultarCancion(String titulo) throws RemoteException;
  
}
