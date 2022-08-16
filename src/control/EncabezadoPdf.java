/*
* Clase dedicada al encabezado de las páginas del documento Pdf
*/
package control;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
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
    private Font fuenteCeldas = FontFactory.getFont(BaseFont.HELVETICA, 9f);
    private Font fuenteCeldasTitulo = FontFactory.getFont(BaseFont.HELVETICA_BOLD, 12f);
    
    public EncabezadoPdf(LocalDate fechaLocal) {
        fecha = fechaLocal.getDayOfMonth() + "/"  + fechaLocal.getMonthValue() +  "/" + fechaLocal.getYear();
    }
    
    public EncabezadoPdf(){
    }
    
    /**
     * Sobreescribe el método asociado al evento de crear una nueva página, agregando un contenido como encabezado cada que se crea una página nueva
     * 
     * @param writer
     * @param document 
     */
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
       
        PdfPCell celdaTitulo = crearCeldaModificada(TITULO, fuenteCeldasTitulo, 2, Paragraph.ALIGN_CENTER);
        PdfPCell celdaCodigo = crearCeldaModificada("Código: " + CODIGO, fuenteCeldas, 1, Paragraph.ALIGN_LEFT);
        PdfPCell celdaVersion = crearCeldaModificada("Versión: " + VERSION, fuenteCeldas, 1, Paragraph.ALIGN_LEFT);
        PdfPCell celdaFecha = crearCeldaModificada("Fecha: " + fecha, fuenteCeldas, 1, Paragraph.ALIGN_LEFT);
        PdfPCell celdaPagina = crearCeldaModificada("Página: " + paginaActual, fuenteCeldas, 1, Paragraph.ALIGN_LEFT);
        
        celdaTitulo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        celdaCodigo.setBorder(0);
        celdaVersion.setBorder(0);
        celdaFecha.setBorder(0);
        celdaPagina.setBorder(0);
        
        celdaCodigo.setBorderWidthBottom(1);
        celdaVersion.setBorderWidthBottom(1);
        celdaFecha.setBorderWidthBottom(1);
                
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
            document.add(new Paragraph(" "));  
        
        } catch (DocumentException ex) {
            Logger.getLogger(EncabezadoPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Este método permite modificar celdas con unas características como su contenido, su fuente, tamaño y alineación.
     * 
     * @param titulo
     * @param fuente
     * @param columnas
     * @param align
     * @return Retorna una PdfPCell con modificaciones de estilo según lo requerido en el pdf del IDEAM y los valores pasados como parámetos
     */
    public PdfPCell crearCeldaModificada(String titulo, Font fuente, int columnas, int align) {
        PdfPCell celda = new PdfPCell();
        Paragraph texto = new Paragraph(titulo);
        texto.setFont(fuente);
        texto.setAlignment(align);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
}