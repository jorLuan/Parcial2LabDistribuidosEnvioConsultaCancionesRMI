package cliente.vista;

import cliente.utilidades.UtilidadesAudio;
import cliente.utilidades.UtilidadesConsola;
import java.io.File;
import java.rmi.RemoteException;
import servidor.DTO.CancionDTO;
import servidor.controladores.ControladorGestorCancionInt;


public class Menu {
    
    private final ControladorGestorCancionInt objRemoto;
    
    public Menu(ControladorGestorCancionInt objRemoto)
    {
        this.objRemoto=objRemoto;
    }
    
    public void ejecutarMenuPrincipal()
    {
        int opcion = 0;
        do
        {
            System.out.println("           ======Menu======");
            System.out.println("1. Ingresar y enviar datos de una cancion");	
            System.out.println("2. Consultar una cancion del servidor");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch(opcion)
            {
                case 1:
                        Opcion1();
                        break;
                case 2:
                        Opcion2();
                        break;
                case 3:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opción incorrecta");
            }

        }while(opcion != 3);
    }
    
    
    private void Opcion1() 
    {
        try
        {
             System.out.println("==Registro de Cancion==");
                System.out.println("Ingrese el titulo");
                String titulo = UtilidadesConsola.leerCadena();
                
                String tipo="";
                do{
                   System.out.println("Ingrese tipo ");
                    tipo = UtilidadesConsola.leerCadena();
                }while( !tipo.equals("mp3")&& !tipo.equals("FLAC"));
                
                
                CancionDTO objCancion= UtilidadesAudio.leerMetadatos2(titulo,tipo);
                byte[] arrayBytesCancion=UtilidadesAudio.obtenerBytesCancion(objCancion,objCancion.getArrayBytes());              
                objCancion.setArrayBytes(arrayBytesCancion);

                boolean valor = objRemoto.registrarCancion(objCancion);//invocación del método remoto
                if(valor)
                        System.out.println("Registro realizado satisfactoriamente...");
                else
                        System.out.println("no se pudo realizar el registro...");
        }
        catch(RemoteException e)
        {
                System.out.println("La operacion no se pudo completar, intente nuevamente...");
        } 
    }
    
    
     private void Opcion2() 
    {
        try
        {
            System.out.println("==Consulta de una Cancion==");
            System.out.println("Ingrese el titulo");
            String titulo = UtilidadesConsola.leerCadena();			

            CancionDTO objCancion= objRemoto.consultarCancion(titulo);
            if(objCancion!=null)
            {
                    System.out.println("================================");
                    System.out.println("Titulo: " + objCancion.getTitulo());
                    System.out.println("Artista: " + objCancion.getArtista());
                    System.out.println("Tipo: " + objCancion.getTipo());
                    System.out.println("Tamaño: " + objCancion.getTamKB());  
                    System.out.println("================================");
            }
            else
                    System.out.println("Cancion no encontrado");
        }
        catch(RemoteException e)
        {
                System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

}
