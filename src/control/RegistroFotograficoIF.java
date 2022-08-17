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
import java.io.File;
import modelo.ImpactoAmbiental;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */

public interface RegistroFotograficoIF {
          public void agregarRegistroFotografico();
          public void limpiarRegistrosFotograficos();
          public String GuardarImagen(File archivo, byte[] byteImg);
          public byte [] AbrirImagen(File archivo);
}
