package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Cuenta;
import modelo.Transaccion;
import modelo.Transaccion.TipoTransaccion;

/**
 * Clase para manejar la conexión con la base de datos MySQL
 */
public class ConexionBD {
    
    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/cajeroautomatico";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // Cambia esto por tu contraseña
    
    private static ConexionBD instancia;
    
    private ConexionBD() {
        // Constructor privado para patrón Singleton
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver MySQL: " + e.getMessage());
        }
    }
    
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    /**
     * Obtiene una conexión a la base de datos
     */
    public Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
    
    /**
     * Cierra los recursos de la base de datos
     */
    public void cerrarRecursos(Connection conn, CallableStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    /**
     * Valida las credenciales de un usuario
     */
    public boolean validarCredenciales(String numeroCuenta, String clave) {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean esValido = false;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL ValidarCredenciales(?, ?, ?)}");
            stmt.setString(1, numeroCuenta);
            stmt.setString(2, clave);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            
            stmt.execute();
            esValido = stmt.getBoolean(3);
            
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
        
        return esValido;
    }
    
    /**
     * Obtiene los datos de una cuenta
     */
    public Cuenta obtenerCuenta(String numeroCuenta) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Cuenta cuenta = null;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("SELECT numeroCuenta, clave, titular, saldo FROM Cuentas WHERE numeroCuenta = ?");
            stmt.setString(1, numeroCuenta);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                cuenta = new Cuenta(
                    rs.getString("numeroCuenta"),
                    rs.getString("clave"),
                    rs.getString("titular"),
                    rs.getDouble("saldo")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener cuenta: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, rs);
        }
        
        return cuenta;
    }
    
    /**
     * Consulta el saldo de una cuenta
     */
    public double consultarSaldo(String numeroCuenta) {
        Connection conn = null;
        CallableStatement stmt = null;
        double saldo = -1;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL ConsultarSaldo(?, ?)}");
            stmt.setString(1, numeroCuenta);
            stmt.registerOutParameter(2, Types.DECIMAL);
            
            stmt.execute();
            saldo = stmt.getDouble(2);
            
        } catch (SQLException e) {
            System.err.println("Error al consultar saldo: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
        
        return saldo;
    }
    
    /**
     * Realiza un depósito en una cuenta
     */
    public boolean realizarDeposito(String numeroCuenta, double monto) {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean exitoso = false;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL RealizarDeposito(?, ?, ?)}");
            stmt.setString(1, numeroCuenta);
            stmt.setDouble(2, monto);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            
            stmt.execute();
            exitoso = stmt.getBoolean(3);
            
        } catch (SQLException e) {
            System.err.println("Error al realizar depósito: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
        
        return exitoso;
    }
    
    /**
     * Realiza un retiro de una cuenta
     */
    public boolean realizarRetiro(String numeroCuenta, double monto) {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean exitoso = false;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL RealizarRetiro(?, ?, ?)}");
            stmt.setString(1, numeroCuenta);
            stmt.setDouble(2, monto);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            
            stmt.execute();
            exitoso = stmt.getBoolean(3);
            
        } catch (SQLException e) {
            System.err.println("Error al realizar retiro: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
        
        return exitoso;
    }
    
    /**
     * Registra un mensaje en el log del sistema
     */
    public void registrarLog(String mensaje) {
        Connection conn = null;
        CallableStatement stmt = null;
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL RegistrarLog(?)}");
            stmt.setString(1, mensaje);
            
            stmt.execute();
            
        } catch (SQLException e) {
            System.err.println("Error al registrar log: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
    }
    
    /**
     * Obtiene el historial de transacciones de una cuenta
     */
    public List<Transaccion> obtenerHistorialTransacciones(String numeroCuenta) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<Transaccion> listaTransacciones = new ArrayList<>();
        
        try {
            conn = obtenerConexion();
            stmt = conn.prepareCall("{CALL ObtenerHistorialTransacciones(?)}");
            stmt.setString(1, numeroCuenta);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoTransaccion tipo = TipoTransaccion.valueOf(rs.getString("tipo"));
                double monto = rs.getDouble("monto");
                double saldoDespues = rs.getDouble("saldoDespues");
                
                Transaccion transaccion = new Transaccion(tipo, monto, saldoDespues);
                transaccion.setFecha(rs.getTimestamp("fecha"));
                transaccion.setId(rs.getInt("idTransaccion"));
                
                listaTransacciones.add(transaccion);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener historial: " + e.getMessage());
        } finally {
            cerrarRecursos(conn, stmt, rs);
        }
        
        return listaTransacciones;
    }
}