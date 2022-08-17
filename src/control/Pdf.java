/*
 * Clase encargada de la creación del reporte final de cada impacto ambiental
 * en formato pdf
 */
package control;

import com.itextpdf.text.BaseColor;
import modelo.ImpactoAmbiental;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class Pdf implements PdfIF{

    private final String RESPONSABLE = "Ana Milena Alvarez";
    private final String REVISOR = "Telly de Jesús Month";
    private final String CARGOREVISOR = "Jefe Oficina Asesora de Planeación";
    private final String PROCESO = "SGA";
    private final String OBJETIVO = "Realizar la identificación de los aspectos y valoración de los impactos ambientales que se generan por el desarrollo de las actividades del IDEAM, en todas sus sedes, con el fin de determinar su significancia y establecer acciones de control para prevenirlos, mitigarlos, corregirlos y/o compensarlos.";
    private final String FECHAAACTUALIZACION = "30/09/2021";
    private final String VERSION = "3";
    private final String DESCRIPCION = "Cambio de metodología de valoración teniendo como base el ciclo de vida del servicio o producto. Se incluye el cumplimiento normativo, como variable fundamental para la valoración de impacto ambiental.";
    private String fecha;
    private String titulo;
    private Font fuenteCeldas = FontFactory.getFont(BaseFont.HELVETICA, 9f);
    private Font fuenteCeldasTitulo = FontFactory.getFont(BaseFont.HELVETICA_BOLD, 9f);
    private BaseColor colorAzulCeldas = new BaseColor(154, 202, 234);

    public Pdf(String titulo) {
        LocalDate fechaLocal = LocalDate.now();
        fecha = String.valueOf(fechaLocal.getDayOfMonth())+ "-" + String.valueOf(fechaLocal.getMonthValue()) + "-" + String.valueOf(fechaLocal.getYear());
        this.titulo = titulo + fecha;
    }

    public Pdf() {
    }
    
    /**
     * Crea el reporte se guarda en la ruta respectiva y se le agrega el encabezdo, el cuerpo y la informacion al PDF.
     * 
     * @param reporte
     */
    
    @Override
    public void crearReporte(ImpactoAmbiental reporte) {
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
            documento.add(new Paragraph(" "));
            agregarRegistrosFotograficos(documento, reporte.getRegistrosFotograficos());
            documento.add(new Paragraph(" "));
            agregarInformacionControlCambios(documento);
            documento.add(new Paragraph(" "));
            agregarInformacionFinal(documento);

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado\nRuta: " + ruta + "\\Documents\\" + titulo + ".pdf");
        } catch (DocumentException | HeadlessException | IOException e) {

        }
    }
    
    /**
     * Se crean las dos primeras tablas del PDF diferentes del encabezado que son de contenido estatico.
     * 
     * @param documento
     */
    @Override
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
    
    /**
     * Este metodo crea la tabla de control de cambios que se ve al final del PDF y es de contenido estatico.
     * 
     * @param documento
     */

    @Override
    public void agregarInformacionControlCambios(Document documento) {
        try {

            PdfPTable tablaInternaControl = new PdfPTable(12);
            tablaInternaControl.setWidthPercentage(100);
            PdfPCell celdaControlDeCambios = crearCeldaModificada("CONTROL DE CAMBIOS", fuenteCeldasTitulo, 12);
            PdfPCell celdaInternaVersion = crearCeldaModificada("VERSION", fuenteCeldasTitulo, 3);
            PdfPCell celdaInternaFecha = crearCeldaModificada("FECHA", fuenteCeldasTitulo, 2);
            PdfPCell celdaInternaDescripcion = crearCeldaModificada("DESCRIPCIÓN", fuenteCeldasTitulo, 7);

            PdfPCell celdaIngresoVersion = crearCeldaModificada(VERSION, fuenteCeldas, 3);
            PdfPCell celdaIngresoFecha = crearCeldaModificada(FECHAAACTUALIZACION, fuenteCeldas, 2);
            PdfPCell celdaIngresoDescripcion = crearCeldaModificada(DESCRIPCION, fuenteCeldas, 7);

            tablaInternaControl.addCell(celdaControlDeCambios);
            tablaInternaControl.addCell(celdaInternaVersion);
            tablaInternaControl.addCell(celdaInternaFecha);
            tablaInternaControl.addCell(celdaInternaDescripcion);
            tablaInternaControl.addCell(celdaIngresoVersion);
            tablaInternaControl.addCell(celdaIngresoFecha);
            tablaInternaControl.addCell(celdaIngresoDescripcion);

            documento.add(tablaInternaControl);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }

    }
    
    /**
     * Este metodo crea la ultima tabla del pdf, y esta contiene informacion de tipo estatica.
     * 
     * @param documento
     */

    @Override
    public void agregarInformacionFinal(Document documento) {
        try {

            PdfPTable tablaFinal = new PdfPTable(12);
            tablaFinal.setWidthPercentage(100);
            PdfPCell celdaElaboro = crearCeldaModificada("ELABORÓ:", fuenteCeldasTitulo, 3);
            PdfPCell celdaReviso = crearCeldaModificada("REVISÓ:", fuenteCeldasTitulo, 2);
            PdfPCell celdaAprobo = crearCeldaModificada("APROBÓ:", fuenteCeldasTitulo, 7);
            PdfPCell celdaIngresoElaboro = crearCeldaModificada(RESPONSABLE + "\n" + "Contratista" + "\n" + "Oficina Asesora de Planeación", fuenteCeldas, 3);
            PdfPCell celdaIngresoReviso = crearCeldaModificada(REVISOR + "\n" + CARGOREVISOR, fuenteCeldas, 2);
            PdfPCell celdaIngresoAprobo = crearCeldaModificada(REVISOR + "\n" + CARGOREVISOR, fuenteCeldas, 7);

            tablaFinal.addCell(celdaElaboro);
            tablaFinal.addCell(celdaReviso);
            tablaFinal.addCell(celdaAprobo);
            tablaFinal.addCell(celdaIngresoElaboro);
            tablaFinal.addCell(celdaIngresoReviso);
            tablaFinal.addCell(celdaIngresoAprobo);

            documento.add(tablaFinal);

        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error con el documento pdf");
        }
    }
    
     /**
     * Este metodo crea todas las tablas que contienen la informacion de entrada por el usuario que se 
     * encuentra en la instacia reporte de la clase ImpactoAmbiental. 
     * 
     * @param documento
     * @param reporte
     */
    
    @Override
    public void agregarCuerpoPdf(Document documento, ImpactoAmbiental reporte) {

        PdfPTable tablaInfoCuerpo1 = new PdfPTable(8);
        tablaInfoCuerpo1.addCell(crearCeldaModificada("PROCESO", colorAzulCeldas, fuenteCeldasTitulo, 2));
        tablaInfoCuerpo1.addCell(crearCeldaModificada("SEDE", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada("ACTIVIDAD ASOCIADA AL ASPECTO", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada("CICLO DE VIDA DEL SERVICIO", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada("SITUACIÓN", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada("ASPECTO AMBIENTAL ASOCIADO", colorAzulCeldas, fuenteCeldasTitulo, 2));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getProceso(), fuenteCeldas, 2));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getSede(), fuenteCeldas, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getActividadAsociada(), fuenteCeldas, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getCicloDeVida(), fuenteCeldas, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getSituacion(), fuenteCeldas, 1));
        tablaInfoCuerpo1.addCell(crearCeldaModificada(reporte.getAspectoAmbiental(), fuenteCeldas, 2));

        PdfPTable tablaInfoCuerpo2 = new PdfPTable(6);
        tablaInfoCuerpo2.addCell(crearCeldaModificada("IMPACTO AMBIENTAL", colorAzulCeldas, fuenteCeldasTitulo, 2));
        tablaInfoCuerpo2.addCell(crearCeldaModificada("RECURSO AFECTADO", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo2.addCell(crearCeldaModificada("OBSERVACIONES", colorAzulCeldas, fuenteCeldasTitulo, 3));
        tablaInfoCuerpo2.addCell(crearCeldaModificada(reporte.getImpactoAmbiental(), fuenteCeldas, 2));
        tablaInfoCuerpo2.addCell(crearCeldaModificada(reporte.getRecursoAfectado(), fuenteCeldas, 1));
        tablaInfoCuerpo2.addCell(crearCeldaModificada(reporte.getObservaciones(), fuenteCeldas, 3));

        PdfPTable tablaInfoCuerpoValoracion = new PdfPTable(11);
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("VALORACIÓN DE IMPACTOS AMBIENTALES", colorAzulCeldas, FontFactory.getFont(BaseFont.HELVETICA_BOLD, 12f), 11));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("TIPO DE IMPACTO", colorAzulCeldas, fuenteCeldasTitulo, 2));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("ALCANCE", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("PROBABILIDAD", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("DURACIÓN", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("RECUPERABILIDAD", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("MAGNITUD", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("NORMATIVIDAD", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada("IMPORTANCIA DEL IMPACTO\nI = A*P*D*R*M*N", colorAzulCeldas, fuenteCeldasTitulo, 3));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getTipoDelImpacto(), fuenteCeldas, 2));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getAlcance() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getProbabilidad() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getDuracion() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getRecuperabilidad() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getMagnitud() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getNormatividad() + "", fuenteCeldas, 1));
        tablaInfoCuerpoValoracion.addCell(crearCeldaModificada(reporte.getImportanciaDelImpacto() + "", fuenteCeldas, 3));

        PdfPTable tablaInfoCuerpo3 = new PdfPTable(8);
        PdfPTable tabla4Interna = new PdfPTable(3);
        tabla4Interna.setWidthPercentage(100);
        tabla4Interna.addCell(crearCeldaModificada("SIGNIFICANCIA", colorAzulCeldas, fuenteCeldasTitulo, 3));
        tabla4Interna.addCell(crearCeldaModificada("VALOR", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tabla4Interna.addCell(crearCeldaModificada("CALIFICACION", colorAzulCeldas, fuenteCeldasTitulo, 2));
        PdfPCell celda4Interna = new PdfPCell(tabla4Interna);
        celda4Interna.setColspan(3);
        tablaInfoCuerpo3.addCell(crearCeldaModificada("LEGISLACIÓN AMBIENTAL RELACIONADA", colorAzulCeldas, fuenteCeldasTitulo, 4));
        tablaInfoCuerpo3.addCell(crearCeldaModificada("CUMPLE NORMATIVIDAD", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpo3.addCell(celda4Interna);
        tablaInfoCuerpo3.addCell(crearCeldaModificada(reporte.getLegislacionAsociada(), fuenteCeldas, 4));
        tablaInfoCuerpo3.addCell(crearCeldaModificada(reporte.isCumpleNormatividad() ? "SI" : "NO", fuenteCeldas, 1));
        tablaInfoCuerpo3.addCell(crearCeldaModificada(reporte.isValor(), fuenteCeldas, 1));
        tablaInfoCuerpo3.addCell(crearCeldaModificada(reporte.getCalificacion(), fuenteCeldas, 2));

        PdfPTable tablaInfoCuerpoCtrlOperacional = new PdfPTable(2);
        tablaInfoCuerpoCtrlOperacional.addCell(crearCeldaModificada("CONTROL OPERACIONAL", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoCtrlOperacional.addCell(crearCeldaModificada("ACCIONES DE MEJORA DEL CONTROL OPERACIONAL", colorAzulCeldas, fuenteCeldasTitulo, 1));
        tablaInfoCuerpoCtrlOperacional.addCell(crearCeldaModificada(reporte.getControlOperacional(), fuenteCeldas, 1));
        tablaInfoCuerpoCtrlOperacional.addCell(crearCeldaModificada(reporte.getAccionesDeMejora(), fuenteCeldas, 1));

        tablaInfoCuerpo1.setWidthPercentage(100);
        tablaInfoCuerpo2.setWidthPercentage(100);
        tablaInfoCuerpoValoracion.setWidthPercentage(100);
        tablaInfoCuerpo3.setWidthPercentage(100);
        tablaInfoCuerpoCtrlOperacional.setWidthPercentage(100);

        try {
            documento.add(tablaInfoCuerpo1);
            documento.add(new Paragraph(" "));
            documento.add(tablaInfoCuerpo2);
            documento.add(new Paragraph(" "));
            documento.add(tablaInfoCuerpoValoracion);
            documento.add(new Paragraph(" "));
            documento.add(tablaInfoCuerpo3);
            documento.add(new Paragraph(" "));
            documento.add(tablaInfoCuerpoCtrlOperacional);
        } catch (DocumentException e) {
            System.out.println("Ocurrió un error al agregar el cuerpo del documento Pdf");
        }

    }
    /**
     * Metodo el cual crea una tabla donde se va a anadir cada registro fotograficos en una celda.
     * 
     * @param documento
     * @param registrosFotograficos
     */
    
    @Override
    public void agregarRegistrosFotograficos(Document documento, ArrayList<Image> registrosFotograficos){
        PdfPTable tablaImagenes = new PdfPTable(1);
        tablaImagenes.setWidthPercentage(100);
        if(!registrosFotograficos.isEmpty()){
            tablaImagenes.addCell(crearCeldaModificada("REGISTROS FOTOGRÁFICOS", colorAzulCeldas, fuenteCeldasTitulo, 1));
        }
        for (Image registroFotografico : registrosFotograficos) {
            registroFotografico.scaleAbsolute(50, 50);
            tablaImagenes.addCell(registroFotografico);
        }
        try {
            documento.add(tablaImagenes);
        } catch (DocumentException e) {
            System.out.println("Ocurrió un error al agregar los registros fotográficos al documento");
        }
    }
    
     /**
     * El siguiente método permite modificar celdas con unas características como su contenido, su fuente, tamaño y color.
     * 
     * @param titulo
     * @param fuente
     * @param columnas
     * @param colorFondo
     * @return Retorna una PdfPCell con modificaciones de estilo según lo requerido en el pdf del IDEAM y los valores pasados como parámetos
     */
    
    @Override
    public PdfPCell crearCeldaModificada(String titulo, BaseColor colorFondo, Font fuente, int columnas) {
        PdfPCell celda = new PdfPCell();
        Paragraph texto = new Paragraph(titulo);
        texto.setAlignment(Paragraph.ALIGN_CENTER);
        texto.setFont(fuente);
        celda.setBackgroundColor(colorFondo);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
    
     /**
     * Sobreescribe el metodo crear celda modificada haciendo uso del polimorfismo parametrico que 
     * permite modificar celdas con unas características como su contenido, su fuente y su tamaño.
     * 
     * @param titulo
     * @param fuente
     * @param columnas
     * @return Retorna una PdfPCell con modificaciones de estilo según lo requerido en el pdf del IDEAM y los valores pasados como parámetos
     */

    @Override
    public PdfPCell crearCeldaModificada(String titulo, Font fuente, int columnas) {
        PdfPCell celda = new PdfPCell();
        Paragraph texto = new Paragraph(titulo);
        texto.setFont(fuente);
        texto.setAlignment(Paragraph.ALIGN_CENTER);
        celda.addElement(texto);
        celda.setColspan(columnas);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
    
     /**
     * Sobreescribe el metodo crear celda modificada haciendo uso del polimorfismo parametrico que 
     * permite modificar celdas con unas características como su contenido, su color y su tamaño.
     * 
     * @param titulo
     * @param columnas
     * @param colorFondo
     * @return Retorna una PdfPCell con modificaciones de estilo según lo requerido en el pdf del IDEAM y los valores pasados como parámetos
     */

    @Override
    public PdfPCell crearCeldaModificada(String titulo, BaseColor colorFondo, int columnas) {
        PdfPCell celda = new PdfPCell();
        Paragraph texto = new Paragraph(titulo);
        texto.setAlignment(Paragraph.ALIGN_CENTER);
        celda.addElement(texto);
        celda.setBackgroundColor(colorFondo);
        celda.setColspan(columnas);
        celda.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return celda;
    }
}
