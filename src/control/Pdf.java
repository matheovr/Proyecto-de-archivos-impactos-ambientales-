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
    private String FECHAAACTUALIZACION;
    private final String CONTROLCAMBIOS = "CONTROL DE CAMBIOS";
    private final String VERSION = "3";
    private final String DESCRIPCION = "Cambio de metodología de valoración teniendo como base el ciclo de vida del servicio o producto. Se incluye el cumplimiento normativo, como variable fundamental para la valoración de impacto ambiental.";  
    private int numeroPaginas;
    private String fecha;
    private String titulo;

    public Pdf(String titulo) {
        LocalDate fechaLocal = LocalDate.now();
        fecha = String.valueOf(fechaLocal.getDayOfMonth())+ "-" + String.valueOf(fechaLocal.getMonthValue()) + "-" + String.valueOf(fechaLocal.getYear());
        FECHAAACTUALIZACION = String.valueOf(fechaLocal.getDayOfMonth())+ "/" + String.valueOf(fechaLocal.getMonthValue()) + "/" + String.valueOf(fechaLocal.getYear());
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
            documento.add(new Paragraph(" "));
            agregarCuerpoPdf(documento, reporte);
            documento.newPage();
            agregarInformacionControlCambios(documento);
            documento.add(new Paragraph(" "));
            agregarInformacionFinal(documento);
            
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
    
    public void agregarInformacionControlCambios(Document documento){
        try {
        
        PdfPTable tablaControlDeCambios = new PdfPTable(1);
        PdfPCell celdaControlDeCambios = crearCeldaModificada(CONTROLCAMBIOS, BaseColor.WHITE, 1) ;
        tablaControlDeCambios.addCell(celdaControlDeCambios);
        
        tablaControlDeCambios.setWidthPercentage(100);
            
        
        PdfPTable tablaInternaControl = new PdfPTable(3);
        PdfPCell celdaInternaVersion = crearCeldaModificada("VERSION", BaseColor.WHITE, 1);
        PdfPCell celdaInternaFecha = crearCeldaModificada("FECHA", BaseColor.WHITE, 1);
        PdfPCell celdaInternaDescripcion = crearCeldaModificada("DESCRIPCIÓN", BaseColor.WHITE, 1);
        
        tablaInternaControl.addCell(celdaInternaVersion);
        tablaInternaControl.addCell(celdaInternaFecha);
        tablaInternaControl.addCell(celdaInternaDescripcion);
        
        tablaInternaControl.setWidthPercentage(100);
        
        PdfPTable tablaIngresoControl = new PdfPTable(3);
        PdfPCell celdaIngresoVersion = crearCeldaModificada(VERSION, BaseColor.WHITE, 1);
        PdfPCell celdaIngresoFecha = crearCeldaModificada(FECHAAACTUALIZACION, BaseColor.WHITE, 1);
        PdfPCell celdaIngresoDescripcion = crearCeldaModificada(DESCRIPCION, BaseColor.WHITE, 1);
        
        tablaIngresoControl.addCell(celdaIngresoVersion);
        tablaIngresoControl.addCell(celdaIngresoFecha);
        tablaIngresoControl.addCell(celdaIngresoDescripcion);
        tablaIngresoControl.setWidthPercentage(100);
        
        documento.add(tablaControlDeCambios);
        documento.add(celdaControlDeCambios);
        documento.add(tablaInternaControl);
        documento.add(tablaIngresoControl);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }
        
    }
    
    public void agregarInformacionFinal(Document documento){
        try{
            
        PdfPTable tablaFinal = new PdfPTable(3);
        PdfPCell celdaElaboro = crearCeldaModificada("ELABORÓ:", BaseColor.WHITE, 1);
        PdfPCell celdaReviso = crearCeldaModificada("REVISÓ:", BaseColor.WHITE, 1);
        PdfPCell celdaAprobo = crearCeldaModificada("APROBÓ:", BaseColor.WHITE, 1);
        
        tablaFinal.addCell(celdaElaboro);
        tablaFinal.addCell(celdaReviso);
        tablaFinal.addCell(celdaAprobo);
        tablaFinal.setWidthPercentage(100);
        
        PdfPTable tablaIngresoFinal = new PdfPTable(3);
        PdfPCell celdaIngresoElaboro = crearCeldaModificada(RESPONSABLE + "\n" + "Contratista" + "\n" + "Oficina Asesora de Planeación", BaseColor.WHITE, 1);
        PdfPCell celdaIngresoReviso = crearCeldaModificada("Telly de Jesús" + "\n" + "Month" + "\n" + "Jefe Oficina Asesora de Planeación", BaseColor.WHITE, 1);
        PdfPCell celdaIngresoAprobo = crearCeldaModificada("Telly de Jesus Month" + "\n" + "Jefe Oficina Asesora de Planeación", BaseColor.WHITE, 1);
        
        tablaIngresoFinal.addCell(celdaIngresoElaboro);
        tablaIngresoFinal.addCell(celdaIngresoReviso);
        tablaIngresoFinal.addCell(celdaIngresoAprobo);
        tablaIngresoFinal.setWidthPercentage(100);
        
        documento.add(tablaFinal);
        documento.add(tablaIngresoFinal);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }
    }
    
    public void agregarCuerpoPdf(Document documento, ImpactoAmbiental reporte) {
        
        Font fuenteCeldas = FontFactory.getFont(BaseFont.HELVETICA, 9f);
        BaseColor colorAzulCeldas = new BaseColor(73, 215, 255);
        
        PdfPTable tabla1 = new PdfPTable(8);
        tabla1.addCell(crearCeldaModificada("PROCESO", colorAzulCeldas, fuenteCeldas, 2));
        tabla1.addCell(crearCeldaModificada("SEDE", colorAzulCeldas, fuenteCeldas, 1));
        tabla1.addCell(crearCeldaModificada("ACTIVIDAD ASOCIADA AL ASPECTO", colorAzulCeldas, fuenteCeldas, 1));
        tabla1.addCell(crearCeldaModificada("CICLO DE VIDA DEL SERVICIO", colorAzulCeldas, fuenteCeldas, 1));
        tabla1.addCell(crearCeldaModificada("SITUACIÓN", colorAzulCeldas, fuenteCeldas, 1));
        tabla1.addCell(crearCeldaModificada("ASPECTO AMBIENTAL ASOCIADO", colorAzulCeldas, fuenteCeldas, 2));
        tabla1.addCell(crearCeldaModificada(reporte.getProceso(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 2));
        tabla1.addCell(crearCeldaModificada(reporte.getSede(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla1.addCell(crearCeldaModificada(reporte.getActividadAsociada(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla1.addCell(crearCeldaModificada(reporte.getCicloDeVida(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla1.addCell(crearCeldaModificada(reporte.getSituacion(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla1.addCell(crearCeldaModificada(reporte.getAspectoAmbiental(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 2));

        PdfPTable tabla2 = new PdfPTable(6);
        tabla2.addCell(crearCeldaModificada("IMPACTO AMBIENTAL", colorAzulCeldas, fuenteCeldas, 2));
        tabla2.addCell(crearCeldaModificada("RECURSO AFECTADO", colorAzulCeldas, fuenteCeldas, 1));
        tabla2.addCell(crearCeldaModificada("OBSERVACIONES", colorAzulCeldas, fuenteCeldas, 3));
        tabla2.addCell(crearCeldaModificada(reporte.getImpactoAmbiental(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 2));
        tabla2.addCell(crearCeldaModificada(reporte.getRecursoAfectado(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla2.addCell(crearCeldaModificada(reporte.getObservaciones(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 3));
        
        PdfPTable tabla3 = new PdfPTable(11);
        tabla3.addCell(crearCeldaModificada("VALORACIÓN DE IMPACTOS AMBIENTALES", colorAzulCeldas, fuenteCeldas, 11));
        tabla3.addCell(crearCeldaModificada("TIPO DE IMPACTO", colorAzulCeldas, fuenteCeldas, 2));
        tabla3.addCell(crearCeldaModificada("ALCANCE", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("PROBABILIDAD", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("DURACIÓN", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("RECUPERABILIDAD", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("MAGNITUD", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("NORMATIVIDAD", colorAzulCeldas, fuenteCeldas, 1));
        tabla3.addCell(crearCeldaModificada("IMPORTANCIA DEL IMPACTO\nI = A*P*D*R*M*N", colorAzulCeldas, fuenteCeldas, 3));
        tabla3.addCell(crearCeldaModificada(reporte.getTipoDelImpacto(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 2));
        tabla3.addCell(crearCeldaModificada(reporte.getAlcance() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getProbabilidad() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getDuracion() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getRecuperabilidad() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getMagnitud() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getNormatividad() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla3.addCell(crearCeldaModificada(reporte.getImportanciaDelImpacto() + "", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 3));
        
        PdfPTable tabla4 = new PdfPTable(8);
        tabla4.addCell(crearCeldaModificada("LEGISLACIÓN AMBIENTAL RELACIONADA", colorAzulCeldas, fuenteCeldas, 4));
        tabla4.addCell(crearCeldaModificada("CUMPLE NORMATIVIDAD", colorAzulCeldas, fuenteCeldas, 1));
        tabla4.addCell(crearCeldaModificada("SIGNIFICANCIA", colorAzulCeldas, fuenteCeldas, 3));
        tabla4.addCell(crearCeldaModificada(reporte.getLegislacionAsociada(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 4));
        tabla4.addCell(crearCeldaModificada(reporte.isCumpleNormatividad() ? "SI" : "NO", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla4.addCell(crearCeldaModificada(" ", FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 3));
        
        PdfPTable tabla5 = new PdfPTable(2);
        tabla5.addCell(crearCeldaModificada("CONTROL OPERACIONAL", colorAzulCeldas, fuenteCeldas, 1));
        tabla5.addCell(crearCeldaModificada("ACCIONES DE MEJORA DEL CONTROL OPERACIONAL", colorAzulCeldas, fuenteCeldas, 1));
        tabla5.addCell(crearCeldaModificada(reporte.getControlOperacional(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        tabla5.addCell(crearCeldaModificada(reporte.getAccionesDeMejora(), FontFactory.getFont(BaseFont.HELVETICA, 8.5f), 1));
        
        tabla1.setWidthPercentage(100);
        tabla2.setWidthPercentage(100);
        tabla3.setWidthPercentage(100);
        tabla4.setWidthPercentage(100);
        tabla5.setWidthPercentage(100);
        
        try {
            documento.add(tabla1);
            documento.add(new Paragraph(" "));
            documento.add(tabla2);
            documento.add(new Paragraph(" "));
            documento.add(tabla3);
            documento.add(new Paragraph(" "));
            documento.add(tabla4);
            documento.add(new Paragraph(" "));
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
