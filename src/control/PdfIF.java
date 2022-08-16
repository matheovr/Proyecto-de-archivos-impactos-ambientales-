/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import modelo.ImpactoAmbiental;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */

public interface PdfIF {
    
    public void crearReporte(ImpactoAmbiental reporte);
    public void agregarInformacionInicial(Document documento);
    public void agregarInformacionControlCambios(Document documento);
    public void agregarInformacionFinal(Document documento);
    public void agregarCuerpoPdf(Document documento, ImpactoAmbiental reporte);
    public PdfPCell crearCeldaModificada(String titulo, BaseColor colorFondo, Font fuente, int columnas);
    public PdfPCell crearCeldaModificada(String titulo, Font fuente, int columnas);
    public PdfPCell crearCeldaModificada(String titulo, BaseColor colorFondo, int columnas);
    
    
}
