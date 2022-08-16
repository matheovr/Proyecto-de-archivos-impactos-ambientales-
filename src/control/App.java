/*
 * Descripción de la clase
 */
package control;

import java.io.IOException;
import modelo.ImpactoAmbiental;
import vista.Matriz;

/**
 * @author Ana Vega
 * @author Nicolás Carmona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class App {
    public static void main(String[] args) throws IOException {
        Pdf pdf = new Pdf("Un Pdf de prueba");
        //pdf.crearReporte(new ImpactoAmbiental());
        Matriz ventana = new Matriz();
        ventana.setVisible(true);
    }
}
