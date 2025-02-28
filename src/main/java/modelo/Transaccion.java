package modelo;

import java.util.Date;

/**
 * Representa una transacción en el sistema
 */
public class Transaccion {
    public enum TipoTransaccion {
        DEPOSITO, RETIRO, CONSULTA
    }
    
    private int id;
    private TipoTransaccion tipo;
    private double monto;
    private Date fecha;
    private double saldoDespues;
    
    public Transaccion(TipoTransaccion tipo, double monto, double saldoDespues) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = new Date();
        this.saldoDespues = saldoDespues;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public TipoTransaccion getTipo() {
        return tipo;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public double getSaldoDespues() {
        return saldoDespues;
    }
    
    @Override
    public String toString() {
        return "Fecha: " + fecha + 
               ", Tipo: " + tipo + 
               ", Monto: $" + monto +
               ", Saldo final: $" + saldoDespues;
    }
}