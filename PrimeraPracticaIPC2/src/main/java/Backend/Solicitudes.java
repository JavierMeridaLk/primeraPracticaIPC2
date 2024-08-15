/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class Solicitudes {
       
    private int codigoSolicitud;
    private Date fechaSolicitud;
    private String tipoTarjeta;
    private int salarioSolicitante;
    //private boolean estadoSolicitud;
    private String nombreSolicitante;
    private String direccionSolicitud;
    private Gestor gestor;
    
    // constructor de la clase solicitudes
    public Solicitudes(Gestor gestor){    
        
        this.gestor=gestor;
    }
    
    //metodos de la clase solicitudes
    
    public void nuevaSolicitud(int noSoli, String nombre, LocalDate fecha, int salario, String direcc, String tipo){
        
        //boolean estadoSolicitud=false;   
        int estadoSolicitud = 0;
        String insert = "INSERT INTO solicitud (codigo_solicitud,nombre_solicitante,salario_solicitante, "
                + "Fecha_solicitud,tipo_tarjeta,direccion_solicitante,estado_solicitud)"
                + "values('"+ noSoli + "','"+ nombre + "','"+ salario + "','"+ fecha + "','"+ tipo + "','"+ direcc + "','"+ estadoSolicitud + "')";
        
        try{
            Statement statementInsert = gestor.getConnection().createStatement();
            int rowsAffected = statementInsert.executeUpdate(insert);
            System.out.println("Rows affected" + rowsAffected);
        }catch(SQLException e){
            System.out.println("Error al conectar a la DB");
            e.printStackTrace();
        }
        
        
    }
    
    public int numeroDeSolicitud(){
        int numDeSoli=0;
        
        try {
            // Crear una declaración
            Statement statement = gestor.getConnection().createStatement();
            
            // Consulta para contar el número de filas en la tabla "empleados"
            String countQuery = "SELECT COUNT(*) AS codigo_solicitud FROM solicitud";
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery(countQuery);
            
            // Obtener el resultado
            if (resultSet.next()) {
                numDeSoli = resultSet.getInt("codigo_solicitud");
            }
            
            // Cerrar los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return numDeSoli;   
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

    //public boolean isEstadoSolicitud() {
        //return estadoSolicitud;
   // }

    //public void setEstadoSolicitud(boolean estadoSolicitud) {
    //    this.estadoSolicitud = estadoSolicitud;
    //}

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


