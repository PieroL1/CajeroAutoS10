
package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cuenta {
    private String numeroCuenta;
    private String clave;
    private double saldo;
    
    public Cuenta(String numeroCuenta, String clave, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.clave = clave;
        this.saldo = saldo;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public boolean validarClave(String claveIngresada) {
        return this.clave.equals(claveIngresada);
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void depositar(double monto) {
        this.saldo += monto;
    }
    
    public boolean retirar(double monto) {
        if (monto > saldo) {
            return false;
        }
        this.saldo -= monto;
        return true;
    }
}
