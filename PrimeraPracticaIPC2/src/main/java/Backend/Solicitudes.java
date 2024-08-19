/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author xavi
 */
public class Solicitudes {

    private Gestor gestor;
    
    private final int LIMITE_NACIONAL=5000;
    private final int LIMITE_REGIONAL=10000;
    private final int LIMITE_INTERNACIONAL=20000;
    private final String TIPO_NACIONAL="NACIONAL";
    private final String TIPO_REGIONAL="REGIONAL";
    private final String TIPO_INTERNACIONAL="INTERNACIONAL";
    
    // constructor de la clase solicitudes
    public Solicitudes(Gestor gestor){    
        
        this.gestor=gestor;
    }
    
    //metodos de la clase solicitudes
    
    public void autorizacionTarjeta(int codigo){
        
        try{
            
            String select = "SELECT * FROM solicitud WHERE codigo_solicitud = ?";
            PreparedStatement statement = gestor.getConnection().prepareStatement(select);
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int salario =resultSet.getInt("salario_solicitante");
                String tipoTarjeta=resultSet.getString("tipo_tarjeta");
                
                if (resultSet.getBoolean("estado_solicitud")) {
                    JOptionPane.showMessageDialog(
                    null,
                    "Tarjeta ya aprobada",
                    "APORBADO",
                    JOptionPane.INFORMATION_MESSAGE
                    );

                }else{
                    
                    double limite = salario * 0.60;
                    int limiteTarjeta=0;
                    if (tipoTarjeta.equals(TIPO_NACIONAL)) {
                        limiteTarjeta=LIMITE_NACIONAL;
                    }else if (tipoTarjeta.equals(TIPO_REGIONAL)) {
                        limiteTarjeta=LIMITE_REGIONAL;
                    }else if (tipoTarjeta.equals(TIPO_INTERNACIONAL)) {
                        limiteTarjeta=LIMITE_INTERNACIONAL;
                    }
                    
                    if (limite>limiteTarjeta) {
                        //se aprueba
                        String update = "UPDATE solicitud SET estado_solicitud = ? WHERE codigo_solicitud = ?";
                        PreparedStatement updateStatement = gestor.getConnection().prepareStatement(update);
                        updateStatement.setBoolean(1, true); // true para aprobado
                        updateStatement.setInt(2, codigo);
                        int rowsUpdated = updateStatement.executeUpdate();
                        
                        if (rowsUpdated > 0) {
                            
                            Tarjetas tarjeta = new Tarjetas(gestor);
                            String noTarjeta = tarjeta.registrarTarjeta(codigo, limite,tipoTarjeta);
                            
                            JOptionPane.showMessageDialog(
                                null,
                                "Solicitud aprobada exitosamente, su numero de Tarjeta es: "+ noTarjeta,
                                "APROBADO",
                                JOptionPane.INFORMATION_MESSAGE
                            );

                            
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "No se pudo actualizar el estado de la solicitud.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                        
                        
                    }else{
                        //no se aprueba
                        JOptionPane.showMessageDialog(
                            null,
                            "No comple con los requisitos minimos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
  
            }
        }catch(SQLException e){
        System.out.println("Error al consultar a la DB");
        e.printStackTrace();
        }
    }
    
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
    
}


