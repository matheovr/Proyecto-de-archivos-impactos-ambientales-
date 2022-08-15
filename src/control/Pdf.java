/*
 * 
 */
package control;

import com.itextpdf.text.BadElementException;
import modelo.ImpactoAmbiental;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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

            PdfPTable tabla = new PdfPTable(6);
            tabla.addCell(new Paragraph("PROCESO" + reporte.getProceso()));
            tabla.addCell("SEDE");
            tabla.addCell("ACTIVIDAD ASOCIADA AL ASPECTO");
            tabla.addCell("CICLO DE VIDA DEL SERVICIO");
            tabla.addCell("SITUACION");
            tabla.addCell("ASPECTO AMBIENTAL ASOCIADO");
            tabla.addCell("IMPACTO AMBIENTAL");
            tabla.addCell("RECURSO AFECTADO");
            tabla.addCell("OBSERVACIONES");
            tabla.addCell("LEGISLACIÓN AMBIENTAL RELACIONADA");
            tabla.addCell("CONTROL OPERACIONAL");
            tabla.addCell("ACCIONES DE MEJORA DEL CONTROL OPERACIONAL");
            
            JOptionPane.showMessageDialog(null, "Reporte creado\nRuta: " + ruta + "\\Documents\\" + titulo + ".pdf");
            documento.close();
            
        } catch (DocumentException | HeadlessException | IOException e) {

        }
    }
    
    public void agregarInformacionInicial(Document documento) {
        try {
            PdfPTable tablaInformacionInicial = new PdfPTable(4);
            PdfPTable tablaInformacionObjetivo = new PdfPTable(1);

            PdfPCell celdaResponsable = new PdfPCell(new Phrase("RESPONSABLE DEL DILIGENCIAMIENTO: " + RESPONSABLE));
            PdfPCell celdaFechaAct = new PdfPCell(new Phrase("FECHA ÚLTIMA ACTUALIZACIÓN(dd/mm/aaaa): " + FECHAAACTUALIZACION));
            PdfPCell celdaProceso = new PdfPCell(new Phrase("PROCESO: " + PROCESO));
            celdaResponsable.setColspan(2);
            celdaResponsable.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            celdaFechaAct.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            celdaProceso.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

            tablaInformacionInicial.addCell(celdaFechaAct);
            tablaInformacionInicial.addCell(celdaResponsable);
            tablaInformacionInicial.addCell(celdaProceso);

            tablaInformacionObjetivo.addCell(new Paragraph("Objetivo: " + OBJETIVO));

            tablaInformacionInicial.setWidthPercentage(100);
            tablaInformacionObjetivo.setWidthPercentage(100);

            documento.add(tablaInformacionInicial);
            documento.add(new Paragraph(" "));
            documento.add(tablaInformacionObjetivo);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }
    }
}
