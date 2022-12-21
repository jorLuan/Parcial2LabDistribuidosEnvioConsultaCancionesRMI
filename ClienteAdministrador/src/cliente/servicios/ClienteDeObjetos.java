package cliente.servicios;


import cliente.controladores.AdminitradorCallbackImpl;
import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestionAdministradoresInt;


public class ClienteDeObjetos
{

	private static ControladorGestionAdministradoresInt objRemoto;
        
	public static void main(String[] args) throws RemoteException 
        {
            
            int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";        
               
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); 
           
            objRemoto = (ControladorGestionAdministradoresInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioGestionAdministradores");
            AdminitradorCallbackImpl objRemotoAdministrador =new AdminitradorCallbackImpl();
            objRemoto.registrarReferenciaAdministrador(objRemotoAdministrador);
           
            
	}
	
	
	
}

