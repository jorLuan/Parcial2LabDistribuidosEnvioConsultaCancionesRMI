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
           String nombreCancion="Canciones/"+objCancion.getTitulo()+"_"+objCancion.getTamKB()+
                "."+objCancion.getTipo();
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
            System.out.println("titulo: " + objCancion.getTitulo());
            System.out.println("Artista: " + objCancion.getArtista());
            System.out.println("tipo: " + objCancion.getTipo());
            System.out.println("tamaño en KB: " + objCancion.getTamKB());
            
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
