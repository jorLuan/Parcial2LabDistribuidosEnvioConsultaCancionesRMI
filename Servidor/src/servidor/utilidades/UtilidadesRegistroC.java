/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.utilidades;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class UtilidadesRegistroC
{
	 public static Remote getObjectRemote(String addressIp, int port, String nameObjectRegistered) {
        String uriRegister;
        uriRegister = "rmi://" + addressIp + ":" + port + "/" + nameObjectRegistered;
        try {
            return Naming.lookup(uriRegister);
        } catch (Exception e) {
       System.out.println("Excepción en la obtención del objeto remoto" + e);
            return null;
        }
    }
}
