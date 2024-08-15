/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.Date;

/**
 *
 * @author xavi
 */
public class Solicitudes {
       
    private int codigoSolicitud;
    private Date fechaSolicitud;
    private String tipoTarjeta;
    private int salarioSolicitante;
    private boolean estadoSolicitud;
    private String nombreSolicitante;
    private String direccionSolicitud;
    
    // constructor de la clase solicitudes
    public Solicitudes(){    
        
    }
    
    //getters y setters de los atributos de la clase

    public int getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(int codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public int getSalarioSolicitante() {
        return salarioSolicitante;
    }

    public void setSalarioSolicitante(int salarioSolicitante) {
        this.salarioSolicitante = salarioSolicitante;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getDireccionSolicitud() {
        return direccionSolicitud;
    }

    public void setDireccionSolicitud(String direccionSolicitud) {
        this.direccionSolicitud = direccionSolicitud;
    }
    
}


