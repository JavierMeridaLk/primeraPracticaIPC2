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
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xavi
 */
public class Tarjetas {
    
    private String numeroTarjeta;
    private double limiteTarjeta;
    private double salario;
    private double saldo;
    private int codigo_solicitud;
    private String nombreTarjeta;
    private String tipoTarjeta;
    private String direccion;
    private boolean estadoTarjeta;
    private Gestor gestor;
    // Usamos AtomicInteger para asegurar que los números sean secuenciales
    private static final AtomicInteger contador = new AtomicInteger(1);
    
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
    
    public void consulta(){
        
        //metodo encargado de realizar la consulta de tarjeta
        
    }
    
    public void registrarTarjeta(int codigoSolicitud, double limiteDeCredito, String tipoTarjeta){
        
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

            // Procesar los resultados
            while (resultSet.next()) {
                String tipoTarjeta2 = resultSet.getString("tipo_tarjeta");
                cantidad = resultSet.getInt("cantidad");

                // Aquí puedes manejar los datos obtenidos
                System.out.println("Tipo de Tarjeta: " + tipoTarjeta2 + ", Cantidad: " + cantidad);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
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
            preparedStatement.setString(1, numeroTarjeta1); // número de tarjeta
            preparedStatement.setInt(2, codigoSolicitud);           // nombre del titular
            BigDecimal limiteDeCreditoBD = BigDecimal.valueOf(limiteDeCredito);
            preparedStatement.setBigDecimal(3, limiteDeCreditoBD);
            preparedStatement.setBoolean(4,true);    
            preparedStatement.setBigDecimal(5, limiteDeCreditoBD);    // límite de crédito
            
            // Ejecutar la inserción
            int filasInsertadas = preparedStatement.executeUpdate();
            
            // Comprobar si la inserción fue exitosa
            if (filasInsertadas > 0) {
                System.out.println("Un nuevo registro fue insertado exitosamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Número de tarjeta generado: " + numeroTarjeta1);
        System.out.println(codigoSolicitud);
        
    }
    public static String generarNumeroTarjeta(String prefijo, int cantidad) {
    // Obtener el número secuencial
    //int numeroSecuencial = cantidad;
    
    // Formatear el número secuencial con ceros a la izquierda
    String secuencialFormato = String.format("%08d", cantidad); // 8 dígitos

    // Dividir el número secuencial en dos bloques de 4 dígitos
    String bloque1 = secuencialFormato.substring(0, 1); // Primer bloque de 4 dígitos
    String bloque2 = secuencialFormato.substring(4);    // Segundo bloque de 4 dígitos

    // Generar el número de tarjeta con el patrón deseado
    return prefijo + bloque1 + " " + bloque2;
}
    
    public void cancelarTarjeta(){
        
        //metodo encargado de cancelar una tarjeta por medio del numero de tarjeta
        
    }
      
    // getters y setter de todos los atributos de la clase tarjetas
    
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public double getLimiteTarjeta() {
        return limiteTarjeta;
    }

    public void setLimiteTarjeta(int limiteTarjeta) {
        this.limiteTarjeta = limiteTarjeta;
    }

    public double getSalario() {
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
