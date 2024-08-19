/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Fronted.JInternalFrameConsulta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author xavi
 */
public class Gestor {
    
    private static final String URL_MYSQL="jdbc:mysql://localhost:3306/gestor_tarjetas";
    private static final String USER="root";
    private static final String PASSWORD="41962209lk";
    private JInternalFrameConsulta consulta;
    
    private Connection connection;
    
    public Gestor(){
        //conexion a la base de datos
        try{
            connection = DriverManager.getConnection(URL_MYSQL,USER,PASSWORD);
        }catch(SQLException e){
            System.out.println("Error al conectar a la DB");
            e.printStackTrace();
        }
        
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
