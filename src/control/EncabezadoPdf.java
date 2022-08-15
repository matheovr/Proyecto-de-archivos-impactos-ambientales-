/*
*
*/
package control;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class EncabezadoPdf extends PdfPageEventHelper{
    private final String CODIGO = "E-SGI-A-F008";
    private final String TITULO = "MATRIZ DE IDENTIFICACION DE ASPECTOS Y VALORACION DE IMPACTOS AMBIENTALES";
    private final int VERSION = 3;
    private int paginaActual = 0;
    private String fecha;
    
    public EncabezadoPdf(LocalDate fechaLocal) {
        fecha = fechaLocal.getDayOfMonth() + "/"  + fechaLocal.getMonthValue() +  "/" + fechaLocal.getYear();
    }
    
    public EncabezadoPdf(){
    }
    
    @Override
    public void onStartPage(PdfWriter writer, Document document){
        paginaActual = document.getPageNumber();
        
        Image imagen = null;
        try {
            imagen = Image.getInstance("src\\imagenes\\icono_IDEAM 1.png");
            imagen.scaleAbsolute(220,100);
            imagen.setAlignment(Image.ALIGN_CENTER);
        } catch (BadElementException | IOException e) {
        }
        
        PdfPTable tablaEncabezado = new PdfPTable(3);
        PdfPTable tablaInterna = new PdfPTable(1);
        
        tablaEncabezado.setWidthPercentage(100);
       
        PdfPCell celdaTitulo = new PdfPCell(new Phrase(TITULO));
        PdfPCell celdaCodigo = new PdfPCell(new Phrase("Código: " + CODIGO));
        PdfPCell celdaVersion = new PdfPCell(new Phrase("Versión: " + VERSION));
        PdfPCell celdaFecha = new PdfPCell(new Phrase("Fecha: " + fecha));
        PdfPCell celdaPagina = new PdfPCell(new Phrase("Página: " + paginaActual));
        
        celdaTitulo.setColspan(2);
        celdaTitulo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        celdaTitulo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        celdaCodigo.setBorder(0);
        celdaVersion.setBorder(0);
        celdaFecha.setBorder(0);
        celdaPagina.setBorder(0);
        
        celdaCodigo.setBorderWidthBottom(1);
        celdaVersion.setBorderWidthBottom(1);
        celdaFecha.setBorderWidthBottom(1);
    
        celdaCodigo.setPadding(6);
        celdaVersion.setPadding(6);
        celdaFecha.setPadding(6);
        celdaPagina.setPadding(6);
                
        tablaInterna.addCell(celdaCodigo);
        tablaInterna.addCell(celdaVersion);
        tablaInterna.addCell(celdaFecha);
        tablaInterna.addCell(celdaPagina);
        
        PdfPCell celdaInterna = new PdfPCell();
        
        celdaInterna.addElement(tablaInterna);
        tablaInterna.setWidthPercentage(100);
        
        tablaEncabezado.addCell(celdaTitulo);   
        tablaEncabezado.addCell(celdaInterna);
      
        try {
            
            document.add(imagen);
            document.add(tablaEncabezado);
            document.add(new Phrase(" "));  
        
        } catch (DocumentException ex) {
            Logger.getLogger(EncabezadoPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
