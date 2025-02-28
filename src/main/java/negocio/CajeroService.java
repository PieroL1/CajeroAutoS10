package negocio;

import datos.ConexionBD;
import java.util.List;
import modelo.Cuenta;
import modelo.Transaccion;

/**
 * Maneja la lógica de negocio para las operaciones del cajero
 */
public class CajeroService {
    
    private ConexionBD conexionBD;
    
    public CajeroService() {
        conexionBD = ConexionBD.getInstancia();
    }
    
    public boolean autenticarUsuario(String numeroCuenta, String clave) {
        boolean esValido = conexionBD.validarCredenciales(numeroCuenta, clave);
        if (esValido) {
            conexionBD.registrarLog("Inicio de sesión exitoso para la cuenta: " + numeroCuenta);
        } else {
            conexionBD.registrarLog("Intento fallido de inicio de sesión para la cuenta: " + numeroCuenta);
        }
        return esValido;
    }
    
    public double consultarSaldo(String numeroCuenta) {
        double saldo = conexionBD.consultarSaldo(numeroCuenta);
        if (saldo >= 0) {
            conexionBD.registrarLog("Consulta de saldo para la cuenta: " + numeroCuenta + ", Saldo: $" + saldo);
        }
        return saldo;
    }
    
    public boolean realizarDeposito(String numeroCuentaDestino, double monto) {
        if (monto <= 0) {
            conexionBD.registrarLog("Intento de depósito inválido (monto negativo o cero) en cuenta: " + numeroCuentaDestino);
            return false;
        }
        
        boolean depositoExitoso = conexionBD.realizarDeposito(numeroCuentaDestino, monto);
        if (depositoExitoso) {
            double nuevoSaldo = conexionBD.consultarSaldo(numeroCuentaDestino);
            conexionBD.registrarLog("Depósito exitoso en cuenta: " + numeroCuentaDestino + 
                            ", Monto: $" + monto + ", Nuevo saldo: $" + nuevoSaldo);
        }
        return depositoExitoso;
    }
    
    public boolean realizarRetiro(String numeroCuenta, double monto) {
        if (monto <= 0) {
            conexionBD.registrarLog("Intento de retiro inválido (monto negativo o cero) en cuenta: " + numeroCuenta);
            return false;
        }
        
        boolean retiroExitoso = conexionBD.realizarRetiro(numeroCuenta, monto);
        if (retiroExitoso) {
            double nuevoSaldo = conexionBD.consultarSaldo(numeroCuenta);
            conexionBD.registrarLog("Retiro exitoso en cuenta: " + numeroCuenta + 
                            ", Monto: $" + monto + ", Nuevo saldo: $" + nuevoSaldo);
        } else {
            double saldoActual = conexionBD.consultarSaldo(numeroCuenta);
            conexionBD.registrarLog("Retiro rechazado en cuenta: " + numeroCuenta + 
                            " por fondos insuficientes. Saldo: $" + saldoActual + 
                            ", Intento de retiro: $" + monto);
        }
        return retiroExitoso;
    }
    
    public List<Transaccion> obtenerHistorialTransacciones(String numeroCuenta) {
        return conexionBD.obtenerHistorialTransacciones(numeroCuenta);
    }
    
    public Cuenta obtenerCuenta(String numeroCuenta) {
        return conexionBD.obtenerCuenta(numeroCuenta);
    }
}