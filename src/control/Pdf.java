/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import modelo.ImpactoAmbiental;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Pdf {
    private int numeroPaginas;
    private String fecha;
    private String titulo;
    
    public Pdf() {
    }

    public Pdf(String titulo) {
        LocalDate fechaLocal = LocalDate.now();
        fecha = String.valueOf(fechaLocal.getDayOfMonth())+ "-" + String.valueOf(fechaLocal.getMonthValue()) + "-" + String.valueOf(fechaLocal.getYear());
        this.titulo = titulo + fecha;
    }
   
    public void crearEncabezado(Document documento) throws DocumentException, BadElementException, IOException{
        Image encabezado = Image.getInstance("src/imagenes/icono_IDEAM 1.png");
        encabezado.scaleToFit(650, 1000);
        encabezado.setAlignment(Chunk.ALIGN_CENTER);
        documento.add(encabezado);
    }
    
    public void crearReporte(ImpactoAmbiental reporte){
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Documents/" + titulo + ".pdf"));
            
            documento.open();
            crearEncabezado(documento);
            PdfPTable tabla = new PdfPTable(6);
            tabla.addCell(new Paragraph("PROCESO" + reporte.getProposito()));
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
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado");
        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }
}
