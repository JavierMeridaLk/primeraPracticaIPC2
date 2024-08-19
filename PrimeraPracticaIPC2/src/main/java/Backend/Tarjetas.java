/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;

/**
 *
 * @author xavi
 */
public class Tarjetas {

    private Gestor gestor;

    private static final String PREFIJO_NACIONAL = "4256 3102 654";
    private static final String PREFIJO_REGIONAL = "4256 3102 656";
    private static final String PREFIJO_INTERNACIONAL = "4256 3102 658";
    private final String TIPO_NACIONAL="NACIONAL";
    private final String TIPO_REGIONAL="REGIONAL";
    private final String TIPO_INTERNACIONAL="INTERNACIONAL";
    
    PreparedStatement preparedStatement = null;

    //constructor de la clase tarjetas
    public Tarjetas(Gestor gestor){
        this.gestor=gestor;
    }
    
    //metodos dentro la clase Tarjetas
       
    public String registrarTarjeta(int codigoSolicitud, double limiteDeCredito, String tipoTarjeta){
        
        //metodo encargado de autorizar una tarjeta por medio del numero de tarjeta
        String prefijo="";
        String query="";
        int cantidad=0;
        
        if (tipoTarjeta.equals(TIPO_NACIONAL)) {
            prefijo= PREFIJO_NACIONAL;
            query = "SELECT tipo_tarjeta, COUNT(*) AS cantidad FROM solicitud WHERE estado_solicitud = '1'AND tipo_tarjeta = 'NACIONAL' GROUP BY tipo_tarjeta";
        }else if (tipoTarjeta.equals(TIPO_REGIONAL)) {
            prefijo= PREFIJO_REGIONAL;
            query = "SELECT tipo_tarjeta, COUNT(*) AS cantidad FROM solicitud WHERE estado_solicitud = '1'AND tipo_tarjeta = 'REGIONAL' GROUP BY tipo_tarjeta";
        }else if (tipoTarjeta.equals(TIPO_INTERNACIONAL)) {
            prefijo= PREFIJO_INTERNACIONAL;
            query = "SELECT tipo_tarjeta, COUNT(*) AS cantidad FROM solicitud WHERE estado_solicitud = '1'AND tipo_tarjeta = 'INTERNACIONAL' GROUP BY tipo_tarjeta";
        }
                
        try {
    
            Statement statement = gestor.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                String tipoTarjeta2 = resultSet.getString("tipo_tarjeta");
                cantidad = resultSet.getInt("cantidad");

                System.out.println("Tipo de Tarjeta: " + tipoTarjeta2 + ", Cantidad: " + cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String numeroTarjeta1 = generarNumeroTarjeta(prefijo,cantidad);
        
        try {
            // Crear la sentencia SQL de inserción
            String insert = "INSERT INTO tarjeta (numero_tarjeta, cod_solicitud, limite_tarjeta, estado_tarjeta,saldo_tarjeta) VALUES (?, ?, ?, ?,?)";
            // Preparar la sentencia
            preparedStatement = gestor.getConnection().prepareStatement(insert);
            // Establecer los valores de los parámetros
            preparedStatement.setString(1, numeroTarjeta1);
            preparedStatement.setInt(2, codigoSolicitud);
            BigDecimal limiteDeCreditoBD = BigDecimal.valueOf(limiteDeCredito);
            preparedStatement.setBigDecimal(3, limiteDeCreditoBD);
            preparedStatement.setBoolean(4,true);    
            preparedStatement.setBigDecimal(5, limiteDeCreditoBD);
            // Ejecutar la inserción
            int filasInsertadas = preparedStatement.executeUpdate();        
            // Comprobar si la inserción fue exitosa
            if (filasInsertadas > 0) {
                System.out.println("Un nuevo registro fue insertado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return numeroTarjeta1;
    }
    
    public static String generarNumeroTarjeta(String prefijo, int cantidad) {
    // Obtener el número secuencial

    // Formatear el número secuencial con ceros a la izquierda
    String secuencialFormato = String.format("%08d", cantidad);
    // Dividir el número secuencial en dos bloques de 4 dígitos
    String bloque1 = secuencialFormato.substring(0, 1); // Primer bloque de 4 dígitos
    String bloque2 = secuencialFormato.substring(4);    // Segundo bloque de 4 dígitos
    // Generar el número de tarjeta con el patrón deseado
    return prefijo + bloque1 + " " + bloque2;
}
    
    public void cancelarTarjeta(String numeroTarjeta){
        
        String busqueda ="SELECT limite_tarjeta, saldo_tarjeta FROM tarjeta WHERE numero_tarjeta = ?";
        double limite=0;
        double saldo=0;
        
        try {
            PreparedStatement statement = gestor.getConnection().prepareStatement(busqueda);
            statement.setString(1, numeroTarjeta);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                limite = resultSet.getDouble("limite_tarjeta");
                saldo = resultSet.getDouble("saldo_tarjeta");
            }
        } catch (SQLException e) {
            System.out.println("no valido");
            e.printStackTrace();
        }
        if (saldo >= limite) {
            //si lo hace
            try {
                String update = "UPDATE tarjeta SET estado_tarjeta = ? WHERE numero_tarjeta = ?";
                PreparedStatement updateStatement = gestor.getConnection().prepareStatement(update);
                updateStatement.setBoolean(1, false); 
                updateStatement.setString(2, numeroTarjeta);
                int rowsUpdated = updateStatement.executeUpdate();
                     
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(
                    null,
                    "Tarjeta cancelada con exito",
                    "EXITO",
                    JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }else{
            //no lo hace
            JOptionPane.showMessageDialog(
                    null,
                    "Hay una deuda pendiente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
        } 
    }  
}
