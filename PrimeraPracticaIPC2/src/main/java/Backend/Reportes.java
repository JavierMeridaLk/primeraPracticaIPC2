/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Fronted.JInternalFrameConsulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xavi
 */
public class Reportes {
    
    private final Gestor gestor;
    DefaultTableModel modelCon;
    JInternalFrameConsulta frameConsulta;
    
    public Reportes(Gestor gestor,JInternalFrameConsulta frameConsulta){
        this.gestor= gestor;
        this.frameConsulta= frameConsulta;
        modelCon= (DefaultTableModel)this.frameConsulta.getjTableConsulta().getModel();
    }
    
    public void consutaTarjeta(String numeroTarjeta){
        modelCon.setRowCount(0);
        String tipoTarjeta="";
        double limiteTarjeta=0;
        String nombre="";
        String direcc="";
        String estadoTarjeta="";
        boolean estado;
        String numeroDeTarjeta="";
        int codigoSolicitud=0;
        String estadoTarjetaTxt="";
        
       String obtenerCS="SELECT cod_solicitud FROM tarjeta WHERE numero_tarjeta = ?";
        try {
            PreparedStatement statement = gestor.getConnection().prepareStatement(obtenerCS);
            statement.setString(1, numeroTarjeta);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                codigoSolicitud = resultSet.getInt("cod_solicitud");
            }
        } catch (SQLException e) {
            System.out.println("no valido");
            e.printStackTrace();
        }
        
//        mysql> select * from tarjeta join solicitud on codigo_solicitud = cod_solicitud WHERE cod_solicitud='1';

        String consulta = "SELECT solicitud.tipo_tarjeta, tarjeta.limite_tarjeta, solicitud.nombre_solicitante, solicitud.direccion_solicitante, "
                + "tarjeta.estado_tarjeta FROM tarjeta JOIN solicitud ON codigo_solicitud = cod_solicitud WHERE cod_solicitud = ? ";
        try {
            PreparedStatement statementConsulta = gestor.getConnection().prepareStatement(consulta);
            statementConsulta.setInt(1, codigoSolicitud);
            ResultSet resultSetC = statementConsulta.executeQuery();
            while(resultSetC.next()){
                tipoTarjeta = resultSetC.getString("tipo_tarjeta");
                limiteTarjeta = resultSetC.getInt("limite_tarjeta");
                nombre = resultSetC.getString("nombre_solicitante");
                direcc = resultSetC.getString("direccion_solicitante");
                estado = resultSetC.getBoolean("estado_tarjeta");
                if (estado) {
                    estadoTarjetaTxt="Activa";
                }else{
                    estadoTarjetaTxt="Cacelada";
                }
                
            }
            modelCon.addRow(new Object[]{numeroTarjeta,tipoTarjeta,limiteTarjeta,nombre,direcc,estadoTarjetaTxt});
//            gestor.obtenerTableConsulta();
            System.out.println(tipoTarjeta);
            System.out.println(limiteTarjeta);
            System.out.println(nombre);
            System.out.println(estadoTarjetaTxt);
            
        } catch (SQLException e) {
            System.out.println("no valido");
            e.printStackTrace();
        }
    }
}

