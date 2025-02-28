
package dominio;

class Transaccion {
    private String tipo;
    private double monto;
    private String numeroCuenta;
    
    public Transaccion(String tipo, double monto, String numeroCuenta) {
        this.tipo = tipo;
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
    }
    
    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Monto: " + monto + ", Cuenta: " + numeroCuenta;
    }
}
