/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author PC
 */
public class NotifyDTO implements Serializable{
    
    private CancionDTO cancion;
    private LocalDate date;
    private LocalTime hora;
    private int tamano; 
    private int cuentaMp3;
    private int cuentaFlac;
    private int espacioKB;

    public NotifyDTO(CancionDTO cancion, LocalDate date, LocalTime hora, int tamano, int cuentaMp3, int cuentaFlac, int espacioKB) {
        this.cancion = cancion;
        this.date = date;
        this.hora = hora;
        this.tamano = tamano;
        this.cuentaMp3 = cuentaMp3;
        this.cuentaFlac = cuentaFlac;
        this.espacioKB = espacioKB;
    }

    public CancionDTO getCancion() {
        return cancion;
    }

    public void setCancion(CancionDTO cancion) {
        this.cancion = cancion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getCuentaMp3() {
        return cuentaMp3;
    }

    public void setCuentaMp3(int cuentaMp3) {
        this.cuentaMp3 = cuentaMp3;
    }

    public int getCuentaFlac() {
        return cuentaFlac;
    }

    public void setCuentaFlac(int cuentaFlac) {
        this.cuentaFlac = cuentaFlac;
    }

    public int getEspacioKB() {
        return espacioKB;
    }

    public void setEspacioKB(int espacioKB) {
        this.espacioKB = espacioKB;
    }
    
  
}