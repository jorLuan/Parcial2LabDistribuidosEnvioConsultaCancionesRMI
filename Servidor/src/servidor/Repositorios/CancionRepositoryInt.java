
package servidor.Repositorios;

import java.rmi.RemoteException;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;

public interface CancionRepositoryInt
{    
    public boolean registrarCancion(CancionDTO objCancion);
    public ArrayList<CancionDTO> listarCanciones();
    public int consultarCantidadCanciones();
    public CancionDTO consultarCancion(String titulo);
}


