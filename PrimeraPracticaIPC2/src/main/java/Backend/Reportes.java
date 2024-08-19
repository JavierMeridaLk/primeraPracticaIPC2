/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Fronted.JInternalFrameConsulta;
import Fronted.JInternalFrameListadoSoli;
import Fronted.JInternalFrameListadoTarjetas;
import Fronted.JInternalFrameReportesConsultas;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xavi
 */
public class Reportes {
    
    private final Gestor gestor;
    DefaultTableModel modelCon;
    DefaultTableModel modelRepo;
    JInternalFrameConsulta frameConsulta;
    JInternalFrameReportesConsultas frameReCo;
    JInternalFrameListadoTarjetas frameReportesLis;
    JInternalFrameListadoSoli frameSoli;
    
    public Reportes(Gestor gestor,JInternalFrameConsulta frameConsulta,JInternalFrameReportesConsultas frameReCo,JInternalFrameListadoTarjetas frameReportesLis,
    JInternalFrameListadoSoli frameSoli){
        
        this.gestor= gestor;
        this.frameConsulta= frameConsulta;
        this.frameReCo=frameReCo;
        this.frameReportesLis=frameReportesLis;
        this.frameSoli=frameSoli;

    }
    
    public void reporteListadoSoliSimple(){
        
        modelRepo= (DefaultTableModel)this.frameSoli.getjTable1().getModel();
        //declaracion para la busqueda del reporte
        String busqueda="SELECT * from solicitud";
        try {
            
            PreparedStatement statement = gestor.getConnection().prepareStatement(busqueda);
            ResultSet resultSet = statement.executeQuery();
            // Limpiar el modelo existente
            modelRepo.setRowCount(0);

            // Rellenar el modelo con los datos
            while (resultSet.next()) {
                String estado=resultSet.getString("estado_solicitud");
                String estadoDescripcion = (estado != null && estado.equals("1")) ? "Aprobada" : "Desaprobada";
                
                modelRepo.addRow(new Object[]{
                    resultSet.getInt("codigo_solicitud"),
                    resultSet.getString("nombre_solicitante"),
                    resultSet.getBigDecimal("salario_solicitante"),
                    resultSet.getDate("Fecha_solicitud"),
                    resultSet.getString("tipo_tarjeta"),
                    resultSet.getString("direccion_solicitante"),
                    estadoDescripcion
                });
            }       
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void reporteListadoSimple(){
        
        modelRepo= (DefaultTableModel)this.frameReportesLis.getjTable1().getModel();
        //declaracion para el reporte de listado de tarjetas
        String busqueda="SELECT \n" +
                        "         t.numero_tarjeta,\n" +
                        "         s.tipo_tarjeta,\n" +
                        "         t.limite_tarjeta AS limite,\n" +
                        "         s.nombre_solicitante AS nombre,\n" +
                        "         s.direccion_solicitante AS direccion,\n" +
                        "         s.Fecha_solicitud AS fecha,\n" +
                        "         t.estado_tarjeta AS estado\n" +
                        "     FROM \n" +
                        "         tarjeta t\n" +
                        "     JOIN \n" +
                        "         solicitud s ON t.cod_solicitud = s.codigo_solicitud\n" +
                        "     ORDER BY \n" +
                        "         t.numero_tarjeta;";
        
        try {
            
            PreparedStatement statement = gestor.getConnection().prepareStatement(busqueda);
             ResultSet resultSet = statement.executeQuery();
            // Limpiar el modelo existente
            modelRepo.setRowCount(0);
            // Rellenar el modelo con los datos
            while (resultSet.next()) {
                String estado=resultSet.getString("estado");
                String estadoDescripcion = (estado != null && estado.equals("1")) ? "Activa" : "Cancelada";
                
                modelRepo.addRow(new Object[]{
                    resultSet.getString("numero_tarjeta"),
                    resultSet.getString("tipo_tarjeta"),
                    resultSet.getBigDecimal("limite"),
                    resultSet.getString("nombre"),
                    resultSet.getString("direccion"),
                    resultSet.getDate("fecha"),
                    estadoDescripcion
                });
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    public void reporteEstadoCuentasFiltrado(JTable table, JRadioButton RBNoTarjeta, JRadioButton RBTipo, JRadioButton RBbSaldo, JRadioButton RBInteres,
                                         JTextField TextNumeroTarjeta, JComboBox<String> TiposTar, JTextField TextSaldo, JTextField TextInteres) {
    
    StringBuilder query = new StringBuilder();
    query.append("SELECT t.numero_tarjeta, s.tipo_tarjeta AS tipo, s.nombre_solicitante AS nombre, s.direccion_solicitante AS direccion, ");
    query.append("m.fecha_movimiento, m.tipo_movimiento, m.descripcion, m.codigo_establecimiento AS establecimiento, m.monto, ");
    query.append("SUM(m.monto) OVER (PARTITION BY t.numero_tarjeta) AS monto_total, ");
    query.append("CASE ");
    query.append("WHEN s.tipo_tarjeta = 'NACIONAL' THEN (t.saldo_tarjeta * 0.012) ");
    query.append("WHEN s.tipo_tarjeta = 'REGIONAL' THEN (t.saldo_tarjeta * 0.023) ");
    query.append("WHEN s.tipo_tarjeta = 'INTERNACIONAL' THEN (t.saldo_tarjeta * 0.0375) ");
    query.append("ELSE 0 ");
    query.append("END AS intereses, ");
    query.append("t.saldo_tarjeta AS saldo ");
    query.append("FROM tarjeta t ");
    query.append("JOIN solicitud s ON t.cod_solicitud = s.codigo_solicitud ");
    query.append("LEFT JOIN movimientos m ON t.numero_tarjeta = m.num_tarjeta ");
    query.append("WHERE 1=1 ");

    // Agregar filtros según selección
    if (RBNoTarjeta.isSelected() && !TextNumeroTarjeta.getText().trim().isEmpty()) {
        query.append("AND t.numero_tarjeta = ? ");
    }
    if (RBTipo.isSelected() && TiposTar.getSelectedIndex() > 0) { // "Ninguno" está en el índice 0
        query.append("AND s.tipo_tarjeta = ? ");
    }
    if (RBbSaldo.isSelected() && !TextSaldo.getText().trim().isEmpty()) {
        query.append("AND t.saldo_tarjeta > ? ");
    }
    if (RBInteres.isSelected() && !TextInteres.getText().trim().isEmpty()) {
        query.append("AND CASE ");
        query.append("WHEN s.tipo_tarjeta = 'NACIONAL' THEN (t.saldo_tarjeta * 0.012) ");
        query.append("WHEN s.tipo_tarjeta = 'REGIONAL' THEN (t.saldo_tarjeta * 0.023) ");
        query.append("WHEN s.tipo_tarjeta = 'INTERNACIONAL' THEN (t.saldo_tarjeta * 0.0375) ");
        query.append("ELSE 0 ");
        query.append("END > ? ");
    }

    query.append("ORDER BY t.numero_tarjeta, m.fecha_movimiento");

    System.out.println("Consulta SQL: " + query.toString());

    try (PreparedStatement ps = gestor.getConnection().prepareStatement(query.toString())) {
        int index = 1;

        if (RBNoTarjeta.isSelected() && !TextNumeroTarjeta.getText().trim().isEmpty()) {
            ps.setString(index++, TextNumeroTarjeta.getText().trim());
        }
        if (RBTipo.isSelected() && TiposTar.getSelectedIndex() > 0) {
            ps.setString(index++, TiposTar.getSelectedItem().toString());
        }
        if (RBbSaldo.isSelected() && !TextSaldo.getText().trim().isEmpty()) {
            ps.setBigDecimal(index++, new BigDecimal(TextSaldo.getText().trim()));
        }
        if (RBInteres.isSelected() && !TextInteres.getText().trim().isEmpty()) {
            ps.setBigDecimal(index++, new BigDecimal(TextInteres.getText().trim()));
        }

        ResultSet rs = ps.executeQuery();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos

        // Crear una instancia de DecimalFormat para formatear los intereses
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        int rowCount = 0; // Para contar el número de filas recuperadas
        while (rs.next()) {
            // Formatear el valor de interés a dos decimales
            String formattedInterest = decimalFormat.format(rs.getBigDecimal("intereses"));

            model.addRow(new Object[]{
                rs.getString("numero_tarjeta"),
                rs.getString("tipo"),
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getDate("fecha_movimiento") != null ? rs.getDate("fecha_movimiento") : "", // Manejo de NULL
                rs.getString("tipo_movimiento") != null ? rs.getString("tipo_movimiento") : "", // Manejo de NULL
                rs.getString("descripcion") != null ? rs.getString("descripcion") : "", // Manejo de NULL
                rs.getString("establecimiento") != null ? rs.getString("establecimiento") : "", // Manejo de NULL
                rs.getBigDecimal("monto") != null ? rs.getBigDecimal("monto") : BigDecimal.ZERO, // Manejo de NULL
                rs.getBigDecimal("monto_total") != null ? rs.getBigDecimal("monto_total") : BigDecimal.ZERO, // Manejo de NULL
                formattedInterest, // Interés formateado a dos decimales
                rs.getBigDecimal("saldo") != null ? rs.getBigDecimal("saldo") : BigDecimal.ZERO // Manejo de NULL
            });
            rowCount++;
        }

        System.out.println("Total de filas recuperadas: " + rowCount); // Depuración

        if (rowCount == 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron datos que coincidan con los filtros seleccionados.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void reporteEstadoCuentasSimple(){
        
        modelRepo= (DefaultTableModel)this.frameReCo.getjTable1().getModel();
        //declaracion para la  busqueda del reporte de estado de cuenta
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
            // Limpiar el modelo existente
            modelRepo.setRowCount(0);

            // Rellenar el modelo con los datos
            while (resultSet.next()) {
                modelRepo.addRow(new Object[]{
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
        //declaraciones de variables temporales
        String tipoTarjeta="";
        double limiteTarjeta=0;
        String nombre="";
        String direcc="";
        String estadoTarjeta="";
        boolean estado;
        String numeroDeTarjeta="";
        int codigoSolicitud=0;
        String estadoTarjetaTxt="";
        //declaracion para pbtener le codigo de solicitud
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
        //declaracion para la consulta de tarjetas
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
        } catch (SQLException e) {
            System.out.println("no valido");
            e.printStackTrace();
        }
    }
}

