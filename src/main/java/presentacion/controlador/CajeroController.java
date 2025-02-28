package presentacion.controlador;

import java.util.List;
import modelo.Transaccion;
import negocio.CajeroService;
import presentacion.vista.InterfazCajero;

/**
 * Controlador principal que coordina la interacción entre la vista y el modelo
 */
public class CajeroController {
    
    private CajeroService cajeroService;
    private String cuentaActual;
    private InterfazCajero vista;
    
    public CajeroController() {
        cajeroService = new CajeroService();
        cuentaActual = null;
    }
    
    public void setVista(InterfazCajero vista) {
        this.vista = vista;
    }
    
    public boolean iniciarSesion(String numeroCuenta, String clave) {
        boolean autenticado = cajeroService.autenticarUsuario(numeroCuenta, clave);
        if (autenticado) {
            cuentaActual = numeroCuenta;
            return true;
        }
        return false;
    }
    
    public void cerrarSesion() {
        cuentaActual = null;
    }
    
    public double consultarSaldo() {
        if (cuentaActual != null) {
            return cajeroService.consultarSaldo(cuentaActual);
        }
        return -1;
    }
    
    public boolean realizarDeposito(String numeroCuentaDestino, double monto) {
        return cajeroService.realizarDeposito(numeroCuentaDestino, monto);
    }
    
    public boolean realizarRetiro(double monto) {
        if (cuentaActual != null) {
            return cajeroService.realizarRetiro(cuentaActual, monto);
        }
        return false;
    }
    
    public String getCuentaActual() {
        return cuentaActual;
    }
}