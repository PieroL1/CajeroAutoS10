package soporte;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import modelo.Transaccion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Clase para generar PDFs con la información del historial de transacciones
 */
public class PDFGenerator {

    private static final String PDF_FILE = System.getProperty("user.home") + "/Downloads/historial_transacciones.pdf";

    public void generarHistorialTransaccionesPDF(List<Transaccion> transacciones) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE));
            document.open();
            document.addTitle("Historial de Transacciones");

            Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPTable table = new PdfPTable(4); // 4 columnas: Fecha, Tipo, Monto, Saldo

            PdfPCell cell = new PdfPCell(new Phrase("Historial de Transacciones", font));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            table.addCell("Fecha");
            table.addCell("Tipo");
            table.addCell("Monto");
            table.addCell("Saldo");

            for (Transaccion transaccion : transacciones) {
                table.addCell(transaccion.getFecha().toString());
                table.addCell(transaccion.getTipo().toString());
                table.addCell(String.valueOf(transaccion.getMonto()));
                table.addCell(String.valueOf(transaccion.getSaldoDespues()));
            }

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public void generarComprobante(String tipo, String numeroCuenta, double monto, double nuevoSaldo) {
        Document document = new Document();
        String fileName = System.getProperty("user.home") + "/Downloads/comprobante_" + tipo + "_" + new Date().getTime() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.addTitle("Comprobante de " + tipo);

            Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPTable table = new PdfPTable(2); // 2 columnas: Descripción, Valor

            PdfPCell cell = new PdfPCell(new Phrase("Comprobante de " + tipo, font));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            table.addCell("Tipo de Transacción");
            table.addCell(tipo);
            table.addCell("Número de Cuenta");
            table.addCell(numeroCuenta);
            table.addCell("Monto");
            table.addCell(String.valueOf(monto));
            table.addCell("Nuevo Saldo");
            table.addCell(String.valueOf(nuevoSaldo));
            table.addCell("Fecha");
            table.addCell(new Date().toString());

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public String getPDFFilePath() {
        return PDF_FILE;
    }
}