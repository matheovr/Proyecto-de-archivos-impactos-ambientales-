/*
 * Interface para el excel
 */
package control;

import java.io.IOException;
import modelo.ImpactoAmbiental;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public interface ExcelIF {
    public void leerExcelCsv() throws IOException;
    public void exportarCsv(ImpactoAmbiental infoImpacto) throws IOException;
}
