/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Fronted.JInternalFrameConsulta;
import Fronted.JInternalFrameReportesConsultas;
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
    DefaultTableModel modelEsTa;
    JInternalFrameConsulta frameConsulta;
    JInternalFrameReportesConsultas frameReCo;
    
    public Reportes(Gestor gestor,JInternalFrameConsulta frameConsulta,JInternalFrameReportesConsultas frameReCo){
        this.gestor= gestor;
        this.frameConsulta= frameConsulta;
        this.frameReCo=frameReCo;
        
        
    }
    public void reporteEstadoCuentasFiltrado(){
        
    }
    
    public void reporteEstadoCuentasSimple(){
        modelEsTa= (DefaultTableModel)this.frameReCo.getjTable1().getModel();
        String busqueda="SELECT \n" +
                        "    t.numero_tarjeta,\n" +
                        "    s.tipo_tarjeta,\n" +
                        "    s.nombre_solicitante,\n" +
                        "    s.direccion_solicitante,\n" +
                        "    m.fecha_movimiento,\n" +
                        "    m.tipo_movimiento,\n" +
                        "    m.descripcion,\n" +
                        "    m.codigo_establecimiento,\n" +
                        "    m.monto,\n" +
                        "    SUM(m.monto) OVER (PARTITION BY t.numero_tarjeta) AS monto_total,\n" +
                        "    CASE\n" +
                        "        WHEN s.tipo_tarjeta = 'NACIONAL' THEN (t.saldo_tarjeta * 0.012)\n" +
                        "        WHEN s.tipo_tarjeta = 'REGIONAL' THEN (t.saldo_tarjeta * 0.023)\n" +
                        "        WHEN s.tipo_tarjeta = 'INTERNACIONAL' THEN (t.saldo_tarjeta * 0.0375)\n" +
                        "    END AS intereses,\n" +
                        "    t.saldo_tarjeta\n" +
                        "FROM \n" +
                        "    tarjeta t\n" +
                        "JOIN \n" +
                        "    solicitud s ON t.cod_solicitud = s.codigo_solicitud\n" +
                        "JOIN \n" +
                        "    movimientos m ON t.numero_tarjeta = m.num_tarjeta\n" +
                        "ORDER BY \n" +
                        "    t.numero_tarjeta, m.fecha_movimiento;";
        
        try {
            
        PreparedStatement statement = gestor.getConnection().prepareStatement(busqueda);
             ResultSet resultSet = statement.executeQuery();
            // Limpiar el modelo existente (opcional)
            modelEsTa.setRowCount(0);

            // Rellenar el modelo con los datos
            while (resultSet.next()) {
                modelEsTa.addRow(new Object[]{
                    resultSet.getString("numero_tarjeta"),
                    resultSet.getString("tipo_tarjeta"),
                    resultSet.getString("nombre_solicitante"),
                    resultSet.getString("direccion_solicitante"),
                    resultSet.getDate("fecha_movimiento"),
                    resultSet.getString("tipo_movimiento"),
                    resultSet.getString("descripcion"),
                    resultSet.getString("codigo_establecimiento"),
                    resultSet.getBigDecimal("monto"),
                    resultSet.getBigDecimal("monto_total"),
                    resultSet.getBigDecimal("intereses"),
                    resultSet.getBigDecimal("saldo_tarjeta")
                });
            
            
            }
            

            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        
        
    }
    
    public void consutaTarjeta(String numeroTarjeta){
        modelCon= (DefaultTableModel)this.frameConsulta.getjTableConsulta().getModel();
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

