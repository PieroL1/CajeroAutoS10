
package dominio;

import datos.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaService {
    private CuentaDAO cuentaDAO = new CuentaDAO();
    private List<Transaccion> historial = new ArrayList<>();
    
    public boolean autenticarUsuario(String numeroCuenta, String clave) {
        Cuenta cuenta = cuentaDAO.obtenerCuenta(numeroCuenta);
        return cuenta != null && cuenta.validarClave(clave);
    }
    
    public double consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaDAO.obtenerCuenta(numeroCuenta);
        return (cuenta != null) ? cuenta.getSaldo() : -1;
    }
    
    public boolean depositar(String numeroCuenta, double monto) {
        Cuenta cuenta = cuentaDAO.obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.depositar(monto);
            historial.add(new Transaccion("Depósito", monto, numeroCuenta));
            return true;
        }
        return false;
    }
    
    public boolean retirar(String numeroCuenta, double monto) {
        Cuenta cuenta = cuentaDAO.obtenerCuenta(numeroCuenta);
        if (cuenta != null && cuenta.retirar(monto)) {
            historial.add(new Transaccion("Retiro", monto, numeroCuenta));
            return true;
        }
        return false;
    }
    
    public List<Transaccion> obtenerHistorial() {
        return historial;
    }
}
