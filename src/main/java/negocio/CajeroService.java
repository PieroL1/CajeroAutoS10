package negocio;

import datos.ConexionBD;
import java.util.List;
import modelo.Cuenta;
import modelo.Transaccion;
import soporte.PDFGenerator;

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
    
    public boolean realizarDeposito(String numeroCuenta, double monto) {
        if (monto <= 0) {
            conexionBD.registrarLog("Intento de depósito inválido (monto negativo o cero) en cuenta: " + numeroCuenta);
            return false;
        }
        
        boolean depositoExitoso = conexionBD.realizarDeposito(numeroCuenta, monto);
        if (depositoExitoso) {
            double nuevoSaldo = conexionBD.consultarSaldo(numeroCuenta);
            conexionBD.registrarLog("Depósito exitoso en cuenta: " + numeroCuenta + 
                            ", Monto: $" + monto + ", Nuevo saldo: $" + nuevoSaldo);
            generarComprobante("Depósito", numeroCuenta, monto, nuevoSaldo);
        }
        return depositoExitoso;
    }
    
    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double monto) {
        if (monto <= 0) {
            conexionBD.registrarLog("Intento de transferencia inválido (monto negativo o cero) desde cuenta: " + cuentaOrigen);
            return false;
        }

        boolean retiroExitoso = conexionBD.realizarRetiro(cuentaOrigen, monto);
        if (retiroExitoso) {
            boolean depositoExitoso = conexionBD.realizarDeposito(cuentaDestino, monto);
            if (depositoExitoso) {
                double nuevoSaldoOrigen = conexionBD.consultarSaldo(cuentaOrigen);
                double nuevoSaldoDestino = conexionBD.consultarSaldo(cuentaDestino);
                conexionBD.registrarLog("Transferencia exitosa desde cuenta: " + cuentaOrigen + " a cuenta: " + cuentaDestino + 
                                ", Monto: $" + monto + ", Nuevo saldo origen: $" + nuevoSaldoOrigen + 
                                ", Nuevo saldo destino: $" + nuevoSaldoDestino);
                generarComprobante("Transferencia", cuentaOrigen, monto, nuevoSaldoOrigen);
                return true;
            } else {
                // Revertir retiro si el depósito falla
                conexionBD.realizarDeposito(cuentaOrigen, monto);
                conexionBD.registrarLog("Transferencia fallida. Reverso de retiro en cuenta: " + cuentaOrigen);
            }
        } else {
            conexionBD.registrarLog("Transferencia fallida desde cuenta: " + cuentaOrigen + " por fondos insuficientes.");
        }
        return false;
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
            generarComprobante("Retiro", numeroCuenta, monto, nuevoSaldo);
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
    
    public void generarHistorialTransaccionesPDF(String numeroCuenta) {
        List<Transaccion> transacciones = obtenerHistorialTransacciones(numeroCuenta);
        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generarHistorialTransaccionesPDF(transacciones);
    }

    private void generarComprobante(String tipo, String numeroCuenta, double monto, double nuevoSaldo) {
        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generarComprobante(tipo, numeroCuenta, monto, nuevoSaldo);
    }
}