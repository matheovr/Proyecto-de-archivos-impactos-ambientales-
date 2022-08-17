/*
 * Interface para el encabezado del pdf
 */
package control;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public interface EncabezadoPdfIF {
    public void onStartPage(PdfWriter writer, Document document);
    public PdfPCell crearCeldaModificada(String titulo, Font fuente, int columnas, int align);
}