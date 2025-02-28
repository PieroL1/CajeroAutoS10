package soporte;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase para registrar operaciones y eventos del sistema
 */
public class Logger {
    
    private static final String LOG_FILE = "cajero_log.txt";
    private SimpleDateFormat dateFormat;
    
    public Logger() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public void log(String mensaje) {
        String timestamp = dateFormat.format(new Date());
        String logEntry = timestamp + " - " + mensaje;
        
        System.out.println(logEntry); // Mostrar en consola
        
        // Guardar en archivo
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(logEntry);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}