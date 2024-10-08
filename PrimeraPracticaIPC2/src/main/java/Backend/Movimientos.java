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
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author xavi
 */
public class Movimientos {

    private Gestor gestor;
 
    public Movimientos(Gestor gestor){
        this.gestor=gestor;
        
    }
    
    public void realizarMovimiento(String numTarjeta, LocalDate fecha, String tipo, String descripcion, String establecimiento, double monto) {      
        double saldo=0;
        double total=0;
        boolean estadoTarjeta=false;
        //declaracion para hacer un nuevo movimiento        
        String insert = "INSERT INTO movimientos (num_tarjeta, fecha_movimiento, tipo_movimiento, descripcion, codigo_establecimiento, monto)"
                + "values('"+ numTarjeta + "','"+ fecha + "','"+ tipo + "','"+ descripcion + "','"+ establecimiento + "','"+ monto + "')";
        
        try{
            
            try {
                String select = "SELECT * FROM tarjeta WHERE numero_tarjeta = ?";
                PreparedStatement statement = gestor.getConnection().prepareStatement(select);
                statement.setString(1, numTarjeta);
                ResultSet resultSet = statement.executeQuery();
                //obtener el saldo y estado de la tarjeta
                while(resultSet.next()){
                    saldo =resultSet.getDouble("saldo_tarjeta");
                    estadoTarjeta = resultSet.getBoolean("estado_tarjeta");
                }
            } catch (Exception e) {
                
            }
            //se comprueba el estado de la tarjeta 
            if (estadoTarjeta) {
                //se realiza el movimiento segun la eleccion
                if (tipo.equalsIgnoreCase("cargo")) {
                    if (monto>saldo) {
                        total= saldo;
                        JOptionPane.showMessageDialog(
                        null,
                        "Movimiento no posible, saldo insuficiente.",
                        "APROBADO",
                        JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }else{
                        total= saldo-monto;
                    }
                    
                }else{
                    total= saldo+monto;
                }
                
                Statement statementInsert = gestor.getConnection().createStatement();
                int rowsAffected = statementInsert.executeUpdate(insert);
                System.out.println("Rows affected" + rowsAffected);
                String update = "UPDATE tarjeta SET saldo_tarjeta = ? WHERE numero_tarjeta = ?";
                PreparedStatement updateStatement = gestor.getConnection().prepareStatement(update);
                //se acualiza el saldo de la tarjeta        
                updateStatement.setBigDecimal(1, BigDecimal.valueOf(total));    
                updateStatement.setString(2, numTarjeta);
                int rowsUpdated = updateStatement.executeUpdate();
                       
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(
                    null,
                    "Movimiento exitoso, saldo actualizado.",
                    "APROBADO",
                    JOptionPane.INFORMATION_MESSAGE
                    );
                }
                
            }else{
                
                JOptionPane.showMessageDialog(
                    null,
                    "La tarjeta ha sido cancelada.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
                    );
            }
        }catch(SQLException e){
            System.out.println("Error al conectar a la DB");
            e.printStackTrace();
        }   
    }
}
