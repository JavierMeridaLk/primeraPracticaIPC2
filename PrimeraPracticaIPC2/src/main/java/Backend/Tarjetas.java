/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author xavi
 */
public class Tarjetas {
    
    private int numeroTarjeta;
    private int limiteTarjeta;
    private int salario;
    private int codigo_solicitud;
    private String nombreTarjeta;
    private String tipoTarjeta;
    private String direccion;
    private boolean estadoTarjeta;
    private Gestor gestor;
    
    //constructor de la clase tarjetas
    public Tarjetas(Gestor gestor){
        this.gestor=gestor;
    }
    
    //metodos dentro la clase Tarjetas
    
    public void consulta(){
        
        //metodo encargado de realizar la consulta de tarjeta
        
    }
    
    public void registrarTarjeta(int codigoSolicitud, double limiteDeCredito){
        
        //metodo encargado de autorizar una tarjeta por medio del numero de tarjeta
        System.out.println(codigoSolicitud);
        
    }
    
    public void cancelarTarjeta(){
        
        //metodo encargado de cancelar una tarjeta por medio del numero de tarjeta
        
    }
      
    // getters y setter de todos los atributos de la clase tarjetas
    
    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getLimiteTarjeta() {
        return limiteTarjeta;
    }

    public void setLimiteTarjeta(int limiteTarjeta) {
        this.limiteTarjeta = limiteTarjeta;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(boolean estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public int getCodigo_solicitud() {
        return codigo_solicitud;
    }

    public void setCodigo_solicitud(int codigo_solicitud) {
        this.codigo_solicitud = codigo_solicitud;
    }
    
    
}
