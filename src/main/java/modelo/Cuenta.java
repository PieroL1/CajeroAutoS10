package modelo;

import java.util.Date;

/**
 * Representa una cuenta bancaria en el sistema
 */
public class Cuenta {
    private String numeroCuenta;
    private String clave;
    private String titular;
    private double saldo;
    
    public Cuenta(String numeroCuenta, String clave, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.clave = clave;
        this.titular = titular;
        this.saldo = saldo;
    }
    
    // Getters y setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public String getClave() {
        return clave;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}