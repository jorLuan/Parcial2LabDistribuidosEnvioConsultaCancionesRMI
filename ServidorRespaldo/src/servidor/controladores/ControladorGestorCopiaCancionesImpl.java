
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;
import servidor.DTO.NotifyDTO;
import servidor.Repositorios.CancionRepositoryInt;


public class ControladorGestorCopiaCancionesImpl extends UnicastRemoteObject implements ControladorGestorCopiaCancionInt{
    
    private final CancionRepositoryInt objCancionesRepository;
    private final ControladorGestionAdministradoresImpl objReferenciaRemota;
    int cuentaMp3;
    int cuentaFlac;
    int espacioKB;

    public ControladorGestorCopiaCancionesImpl(CancionRepositoryInt objCancionesRepository,ControladorGestionAdministradoresImpl objReferenciaRemota) throws RemoteException
    {
        super(); //se asigna un puerto de escucha al OR
        this.objCancionesRepository=objCancionesRepository;
        this.objReferenciaRemota=objReferenciaRemota;
       
    }

    @Override
    public boolean registrarCancion(CancionDTO objCancion) throws RemoteException {
        boolean bandera=false;
        if(this.objCancionesRepository.registrarCancion(objCancion))
        {
            bandera=true;
            int size = this.consultarCantidadCanciones();
            LocalTime horaActual=LocalTime.now();
            LocalDate fechaActual=LocalDate.now();
            if(objCancion.getTipo().equals("mp3")){
                cuentaMp3++;
            }
            if(objCancion.getTipo().equals("FLAC")){
                cuentaFlac++;
            }
            espacioKB=espacioKB+objCancion.getTamKB();
            NotifyDTO notificacion=new NotifyDTO(objCancion, fechaActual, horaActual, cuentaMp3, cuentaMp3, cuentaFlac, espacioKB);
            this.objReferenciaRemota.notificarAdministradores("\ntitulo: "+objCancion.getTitulo()+"\nArtista: "+objCancion.getArtista()+"\nTipo: "+objCancion.getTipo()
                    +"\nTamaño: "+objCancion.getTamKB()+"\nFecha y Hora del registro: "+notificacion.getDate()+" "+notificacion.getHora()
            +"\ncantidad de canciones tipo .mp3 resgistradas: "+notificacion.getCuentaMp3()+"\ncantidad de canciones tipo .FLAC resgistradas: "+notificacion.getCuentaFlac()
            +"\nEspacio total: "+notificacion.getEspacioKB()+"\n");
        }
        return bandera;
    }

    @Override
    public ArrayList<CancionDTO> listarCanciones() throws RemoteException {
        return this.objCancionesRepository.listarCanciones();
    }

    @Override
    public CancionDTO consultarCancion(String titulo) throws RemoteException {
     return this.objCancionesRepository.consultarCancion(titulo);
    }

    @Override
    public int consultarCantidadCanciones() throws RemoteException {
        return this.objCancionesRepository.consultarCantidadCanciones();
    }

    
}
