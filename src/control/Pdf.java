/*
 * 
 */
package control;

import com.itextpdf.text.BaseColor;
import modelo.ImpactoAmbiental;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class Pdf {
    private final String RESPONSABLE = "Ana Milena Alvarez";
    private final String PROCESO = "SGA" ;
    private final String OBJETIVO = "Realizar la identificación de los aspectos y valoración de los impactos ambientales que se generan por el desarrollo de las actividades del IDEAM, en todas sus sedes, con el fin de determinar su significancia y establecer acciones de control para prevenirlos, mitigarlos, corregirlos y/o compensarlos.";
    private final String FECHAAACTUALIZACION = "30/09/2021";
    private int numeroPaginas;
    private String fecha;
    private String titulo;
            
    public Pdf(String titulo) {
        LocalDate fechaLocal = LocalDate.now();
        fecha = String.valueOf(fechaLocal.getDayOfMonth())+ "-" + String.valueOf(fechaLocal.getMonthValue()) + "-" + String.valueOf(fechaLocal.getYear());
        this.titulo = titulo + fecha;
    }
    
    public Pdf() {
    }
    
    public void crearReporte(ImpactoAmbiental reporte){
        LocalDate fechaLocal = LocalDate.now();
        String ruta = System.getProperty("user.home");
        Document documento = new Document();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Documents/" + titulo + ".pdf"));
            EncabezadoPdf encabezado = new EncabezadoPdf(fechaLocal);
            writer.setPageEvent(encabezado);

            documento.open();
            agregarInformacionInicial(documento);
            agregarCuerpoPdf(documento, reporte);
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado\nRuta: " + ruta + "\\Documents\\" + titulo + ".pdf");            
        } catch (DocumentException | HeadlessException | IOException e) {

        }
    }
    
    public void agregarInformacionInicial(Document documento) {
        try {
            PdfPTable tablaInformacionInicial = new PdfPTable(4);
            PdfPTable tablaInformacionObjetivo = new PdfPTable(1);

            PdfPCell celdaResponsable = crearCeldaModificada("RESPONSABLE DEL DILIGENCIAMIENTO: " + RESPONSABLE, FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1);
            PdfPCell celdaFechaAct = crearCeldaModificada("FECHA ÚLTIMA ACTUALIZACIÓN (dd/mm/aaaa): " + FECHAAACTUALIZACION, FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1);
            PdfPCell celdaProceso = crearCeldaModificada("PROCESO: " + PROCESO, FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1);
            celdaResponsable.setColspan(2);
            celdaResponsable.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            celdaFechaAct.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            celdaProceso.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

            tablaInformacionInicial.addCell(celdaFechaAct);
            tablaInformacionInicial.addCell(celdaResponsable);
            tablaInformacionInicial.addCell(celdaProceso);

            tablaInformacionObjetivo.addCell(crearCeldaModificada("Objetivo: " + OBJETIVO, FontFactory.getFont(BaseFont.HELVETICA, 10f), 1));

            tablaInformacionInicial.setWidthPercentage(100);
            tablaInformacionObjetivo.setWidthPercentage(100);

            documento.add(tablaInformacionInicial);
            documento.add(new Paragraph(" "));
            documento.add(tablaInformacionObjetivo);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }
    }
    
    public void agregarCuerpoPdf(Document documento, ImpactoAmbiental reporte) {
        
        Font fuenteCeldas = FontFactory.getFont(BaseFont.HELVETICA, 9f);
        BaseColor colorAzulCeldas = new BaseColor(73, 215, 255);
        
        PdfPTable tabla1 = new PdfPTable(8);
        tabla1.addCell(crearCeldaModificada("PROCESO", colorAzulCeldas, fuenteCeldas, 2));
        tabla1.addCell("SEDE");
        tabla1.addCell("ACTIVIDAD ASOCIADA AL ASPECTO");
        tabla1.addCell("CICLO DE VIDA DEL SERVICIO");
        tabla1.addCell("SITUACION");
        tabla1.addCell(crearCeldaModificada("ASPECTO AMBIENTAL ASOCIADO", colorAzulCeldas, fuenteCeldas, 2));
        
        PdfPTable tabla2 = new PdfPTable(6);
        tabla2.addCell("IMPACTO AMBIENTAL"); //Ocupa 2
        tabla2.addCell("RECURSO AFECTADO"); // Ocupa 1
        tabla2.addCell("OBSERVACIONES"); //Ocupa 3
        
        PdfPTable tabla3 = new PdfPTable(11);
        
        PdfPTable tabla4 = new PdfPTable(8);
        tabla4.addCell("LEGISLACIÓN AMBIENTAL RELACIONADA"); //Ocupa 4
        tabla4.addCell("CUMPLE LA NORMATIVIDAD"); //Ocupa 1
        tabla4.addCell("SIGNIFICANCIA"); //Ocupa 3
        
        PdfPTable tabla5 = new PdfPTable(2);
        tabla5.addCell("CONTROL OPERACIONAL");
        tabla5.addCell("ACCIONES DE MEJORA DEL CONTROL OPERACIONAL");
        
        tabla1.setWidthPercentage(100);
        tabla2.setWidthPercentage(100);
        tabla3.setWidthPercentage(100);
        tabla4.setWidthPercentage(100);
        tabla5.setWidthPercentage(100);
        
        try {
            documento.add(new Paragraph(""));
            documento.add(tabla1);
            documento.add(new Paragraph(""));
            documento.add(tabla2);
            documento.add(new Paragraph(""));
            documento.add(tabla3);
            documento.add(new Paragraph(""));
            documento.add(tabla4);
            documento.add(new Paragraph(""));
            documento.add(tabla5);
        } catch (DocumentException e) {
            System.out.println("Ocurrió un error al agregar el cuerpo del documento Pdf");
        }

    }
    
    public PdfPCell crearCeldaModificada(String titulo, BaseColor colorFondo, Font fuente, int columnas){
        PdfPCell celda = new PdfPCell();
        Phrase texto = new Phrase(titulo);
        texto.setFont(fuente);
        celda.setBackgroundColor(colorFondo);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
    
    public PdfPCell crearCeldaModificada(String titulo, Font fuente, int columnas){
        PdfPCell celda = new PdfPCell();
        Phrase texto = new Phrase(titulo);
        texto.setFont(fuente);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
    
    public PdfPCell crearCeldaModificada(String titulo,BaseColor colorFondo, int columnas){
        PdfPCell celda = new PdfPCell();
        Phrase texto = new Phrase(titulo);
        celda.setBackgroundColor(colorFondo);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
}
