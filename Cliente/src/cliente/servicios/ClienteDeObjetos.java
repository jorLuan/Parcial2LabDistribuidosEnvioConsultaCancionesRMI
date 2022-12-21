package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import servidor.controladores.ControladorGestorCancionInt;


public class ClienteDeObjetos
{

	private static ControladorGestorCancionInt objRemoto;
        
	public static void main(String[] args) 
        {
            
            int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";        
               
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); 
           
            objRemoto = (ControladorGestorCancionInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioGestionCanciones");
            Menu objMenu= new Menu(objRemoto);
            objMenu.ejecutarMenuPrincipal();
            
            
	}
	
	
	
}

