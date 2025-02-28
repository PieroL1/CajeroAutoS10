
package datos;

import java.util.HashMap;
import java.util.Map;
import dominio.*;

public class CuentaDAO {
    private Map<String, Cuenta> cuentas = new HashMap<>();
    
    public CuentaDAO() {
        // Agregar cuentas de prueba
        cuentas.put("12345", new Cuenta("12345", "clave123", 500.0));
        cuentas.put("67890", new Cuenta("67890", "clave456", 1000.0));
    }
    
    public Cuenta obtenerCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }
}