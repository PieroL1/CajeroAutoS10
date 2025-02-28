package soporte;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import modelo.Transaccion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Clase para generar PDFs con la información del historial de transacciones
 */
public class PDFGenerator {

    private static final String PDF_FILE = System.getProperty("user.home") + "/Downloads/historial_transacciones.pdf";
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
    private static final Font TEXT_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void generarHistorialTransaccionesPDF(List<Transaccion> transacciones) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE));
            document.open();
            document.addTitle("Historial de Transacciones");

            // Cabecera
            agregarCabecera(document, "Historial de Transacciones");

            // Tabla de transacciones
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setWidths(new float[]{3, 3, 2, 2});

            // Encabezado de la tabla
            agregarEncabezadoTabla(table, "Fecha", "Tipo", "Monto", "Saldo");

            for (Transaccion transaccion : transacciones) {
                table.addCell(crearCelda(DATE_FORMAT.format(transaccion.getFecha()), Element.ALIGN_CENTER));
                table.addCell(crearCelda(transaccion.getTipo().toString(), Element.ALIGN_CENTER));

                table.addCell(crearCelda(CURRENCY_FORMAT.format(transaccion.getMonto()), Element.ALIGN_RIGHT));
                table.addCell(crearCelda(CURRENCY_FORMAT.format(transaccion.getSaldoDespues()), Element.ALIGN_RIGHT));
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

            // Cabecera
            agregarCabecera(document, "Comprobante de " + tipo);

            // Información de la transacción
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setWidths(new float[]{3, 3});

            table.addCell(crearCelda("Tipo de Transacción", Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(tipo, Element.ALIGN_LEFT));

            table.addCell(crearCelda("Número de Cuenta", Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(numeroCuenta, Element.ALIGN_LEFT));

            table.addCell(crearCelda("Monto", Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(CURRENCY_FORMAT.format(monto), Element.ALIGN_LEFT));

            table.addCell(crearCelda("Nuevo Saldo", Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(CURRENCY_FORMAT.format(nuevoSaldo), Element.ALIGN_LEFT));

            table.addCell(crearCelda("Fecha", Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(DATE_FORMAT.format(new Date()), Element.ALIGN_LEFT));

            document.add(table);

            // Pie de página con mensaje
            Paragraph footer = new Paragraph("Gracias por usar nuestro servicio.", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void agregarCabecera(Document document, String titulo) throws DocumentException {
        Paragraph header = new Paragraph(titulo, TITLE_FONT);
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingAfter(10);
        document.add(header);
    }

    private void agregarEncabezadoTabla(PdfPTable table, String... titulos) {
        for (String titulo : titulos) {
            PdfPCell headerCell = new PdfPCell(new Phrase(titulo, HEADER_FONT));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setBackgroundColor(new BaseColor(0, 123, 255)); // Azul
            headerCell.setPadding(8);
            table.addCell(headerCell);
        }
    }

    private PdfPCell crearCelda(String texto, int alineacion) {
        return crearCelda(texto, alineacion, false);
    }

    private PdfPCell crearCelda(String texto, int alineacion, boolean fondoGris) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, TEXT_FONT));
        cell.setHorizontalAlignment(alineacion);
        cell.setPadding(5);
        if (fondoGris) {
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
        }
        return cell;
    }

    public String getPDFFilePath() {
        return PDF_FILE;
    }
}
