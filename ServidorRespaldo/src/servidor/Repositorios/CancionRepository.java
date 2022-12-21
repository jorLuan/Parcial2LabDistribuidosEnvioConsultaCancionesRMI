package servidor.Repositorios;

import java.io.File;
import cliente.utilidades.UtilidadesAudio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.text.html.HTML.Tag;
import servidor.DTO.CancionDTO;
import servidor.DTO.NotifyDTO;



public class CancionRepository implements CancionRepositoryInt
{  
    private final ArrayList<CancionDTO> listaCanciones;
    int cuentaMp3;
    int cuentaFlac;
    int espacioKB;
    
    public CancionRepository()
    {
        this.listaCanciones= new ArrayList();
    }

    private boolean almacenarArchivo(byte array[],CancionDTO objCancion)
    { 
        
        //guardamos la cancion en la carpeta Canciones
        
       if(objCancion.getTipo().equals("mp3")|| objCancion.getTipo().equals("FLAC")){
           
           System.out.println(" ===Entrando a Almacenar Archivo=== ");
           String nombreCancion="CopiaSeguridad"+objCancion.getTitulo()+ "."+objCancion.getTipo();
            boolean bandera=UtilidadesAudio.almacenarCancion(nombreCancion,objCancion.getArrayBytes());
            if(bandera){

                this.listaCanciones.add(objCancion);
                System.out.println("Archivo creado en el servidor de canciones");
            }
            return bandera;
       }
        return false;
    }
   
    @Override
    public boolean registrarCancion(CancionDTO objCancion) {

        if(objCancion.getTipo().equals("mp3")|| objCancion.getTipo().equals("FLAC")){
            boolean bandera;
            bandera=this.almacenarArchivo(objCancion.getArrayBytes(),objCancion);
            this.listaCanciones.add(objCancion);
            System.out.println("Archivo creado en el servidor");
            System.out.println("Metadatos del archivo: ");
            System.out.println("titulo: " + objCancion.getTitulo());
            System.out.println("Artista: " + objCancion.getArtista());
            System.out.println("tipo: " + objCancion.getTipo());
            System.out.println("tamaño en KB: " + objCancion.getTamKB());
            
            /*
            LocalTime horaActual=LocalTime.now();
            LocalDate fechaActual=LocalDate.now();
            System.out.println("Fecha y Hora del registro de la cancion: "+fechaActual+horaActual);
            if(objCancion.getTipo().equals("mp3")){
                cuentaMp3++;
            }
            if(objCancion.getTipo().equals("FLAC")){
                cuentaFlac++;
            }
            System.out.println("cantidad de canciones tipo .mp3 resgistradas: "+cuentaMp3);
            System.out.println("cantidad de canciones tipo .FLAC resgistradas: "+cuentaFlac);
            espacioKB=espacioKB+objCancion.getTamKB();
            System.out.println("Espacio total: "+espacioKB);
            //NotifyDTO notificacion=new NotifyDTO(objCancion, fechaActual, horaActual, cuentaMp3, cuentaMp3, cuentaFlac, espacioKB);
            */
            return bandera;
        }
        return false;
    }
    @Override
    public int consultarCantidadCanciones()
    {
        System.out.println("Entrando a Cantidad");
        return listaCanciones.size();
    }

    
    @Override
    public CancionDTO consultarCancion(String titulo)
    {
        CancionDTO objCancion=null;
        
        for (int i = 0; i < this.listaCanciones.size(); i++) {
            if(this.listaCanciones.get(i).getTitulo().equals(titulo))
            {
                objCancion=this.listaCanciones.get(i);
                break;
            }
        }
        return objCancion;    
    }
    
    @Override
    public ArrayList<CancionDTO> listarCanciones()
    {
        return this.listaCanciones;
    }
       
}
