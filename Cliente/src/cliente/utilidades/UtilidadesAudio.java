/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.utilidades;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import servidor.DTO.CancionDTO;

/**
 *
 * @author LENOVO
 */
public class UtilidadesAudio {
    private static File file;
    
    
    public static CancionDTO leerMetadatos2(String Titulo,String Tipo)
    {
      CancionDTO objCancion = null;
        try {
            String ruta = Titulo+"."+Tipo;
            PrintStream obj;
            obj = new PrintStream(new File("archivoSalida.txt"));
            System.setErr(obj);
            File file2=new File(ruta);
            AudioFile f = AudioFileIO.read(file2);
            Tag tag = f.getTag();
            int tamMB= (int) (file2.length()/1024);           
            String artista=tag.getFirst(FieldKey.ARTIST);            
            //String titulo= tag.getFirst(FieldKey.TITLE);
            objCancion=  new CancionDTO(artista, Titulo,Tipo, tamMB);
            if(validarExistencias(objCancion)!=0){
                return null;
            }
            
        } catch (Exception ex) {
            System.out.println("Error al leer los métadatos del archivo");
           
        }
            
        return objCancion;
    }
    public static CancionDTO leerMetadatos(CancionDTO objCancion)
    {
      
        try {
            PrintStream obj;
            obj = new PrintStream(new File("archivoSalida.txt"));
            System.setErr(obj);
            
            //Validar que la cancion existe
            
            //no hace
            if(validarExistencia(objCancion)==0){
            System.out.println("LA BANDERA ES: "+validarExistencia(objCancion));
              
            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();
            int tamMB= (int) (file.length()/1024);           
            String artista=tag.getFirst(FieldKey.ARTIST);    
            String titulo= tag.getFirst(FieldKey.TITLE);
            String tipo= tag.getFirst(FieldKey.MEDIA);
            objCancion=new CancionDTO(artista,titulo,tipo,tamMB);

            }
        } catch (Exception ex) {
            System.out.println("Error al leer los métadatos del archivo");
           
        }
            
        return objCancion;
    }
    
    public  static byte[] obtenerBytesCancion(CancionDTO objCancion, byte [] arrayBytes)
    {
        byte[] arrayBytesCancion=null;
        try {
                File file=new File(objCancion.getTitulo()+"."+objCancion.getTipo());
                FileInputStream objFileInputStream = new FileInputStream(file);
                BufferedInputStream objBuffer= new BufferedInputStream(objFileInputStream);
                arrayBytesCancion = new byte[(int) file.length()];
                objBuffer.read(arrayBytesCancion, 0, arrayBytesCancion.length); 
        } catch (FileNotFoundException ex) {
                System.out.println("Error, archivo no encontrado");
        } catch (IOException ex) {
                 System.out.println("Error al leer los métadatos del archivo");
        }
          
        return arrayBytesCancion;
    }
    public static int validarExistencias(CancionDTO objCancion){
        int bandera=0;
       file=new File(objCancion.getTitulo()+"."+objCancion.getTipo());
        if(!file.exists()){
            System.out.println("La cancion no existe!!");
            bandera++;
        }
        
        
        return bandera;
    }
    public static int validarExistencia(CancionDTO objCancion){
        int bandera=0;
       file=new File(objCancion.getTitulo()+"."+objCancion.getTipo());
        if(!file.exists()){
            System.out.println("La cancion no existe!!");
            bandera++;
        }
        else{
            
        if(!objCancion.getTipo().equals("mp3")|| !objCancion.getTipo().equals("FLAC")){
             System.out.println("La cancion tiene una extension válida");
              bandera++;
            }
        
        }         
        
        return bandera;
    }
    
    public static boolean almacenarCancion(String tituloCancion,byte[] arrayBytes){
         boolean bandera = true;

         try {
            String nombreArchivo = tituloCancion;
            File objFile = new File(nombreArchivo.replaceAll("\\\\", "").replace(" ", ""));// archivo donde se almacenara la canción
            OutputStream output = new FileOutputStream(objFile);
            output.write(arrayBytes);// escribiendo la canción en el archivo
        } catch (FileNotFoundException e) {
            bandera = false;
        } catch (IOException ex) {
            bandera = false;
        }
         return bandera;
    }

}
