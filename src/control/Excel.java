/*
    * Descripción de la clase
 */
package control;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.ImpactoAmbiental;
import vista.Matriz;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class Excel {
    private String urlArchivo;
    private File archivoExcel;
    private String[] opcionesAspectoAmbiental;
    private String[] opcionesImpactoAmbiental;
    private String[] opcionesRecursoAfectado;
    private ImpactoAmbiental ImpactoAmbiental;
    private Matriz Matriz;

    public Excel() {
    }
    
    /**
     * Constructor principal de la clase
     * 
     * @param urlArchivo es la ruta de donde se encuentra el archivo a crear 
     */
    public Excel(String urlArchivo) throws IOException {
        this.urlArchivo = urlArchivo;
        this.archivoExcel = new File(urlArchivo);
        leerExcelCsv();
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public File getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(File archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public String[] getOpcionesAspectoAmbiental() {
        return opcionesAspectoAmbiental;
    }

    public void setOpcionesAspectoAmbiental(String[] opcionesAspectoAmbiental) {
        this.opcionesAspectoAmbiental = opcionesAspectoAmbiental;
    }

    public String[] getOpcionesImpactoAmbiental() {
        return opcionesImpactoAmbiental;
    }

    public void setOpcionesImpactoAmbiental(String[] opcionesImpactoAmbiental) {
        this.opcionesImpactoAmbiental = opcionesImpactoAmbiental;
    }

    public String[] getOpcionesRecursoAfectado() {
        return opcionesRecursoAfectado;
    }

    public void setOpcionesRecursoAfectado(String[] opcionesRecursoAfectado) {
        this.opcionesRecursoAfectado = opcionesRecursoAfectado;
    }
    
    /**
     * Este método se encarga de leer cada linea del archivo .csv y tomar las opciones
     * para ponerlas en los arreglos correspondientes de la clase
     * 
     * @throws IOException 
     */
    public void leerExcelCsv() throws IOException{
        BufferedReader br = null;
        ArrayList<String> opciones = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(urlArchivo));
            String linea = br.readLine();
            while (linea != null) {
                opciones.add(linea);
                linea = br.readLine();
            }
        } catch (IOException expecion) {
            System.out.println(expecion);
        } finally {
            if (null != br) {
                br.close();
            }
        }
        
        if(opciones.size() >= 3){
            opcionesAspectoAmbiental = opciones.get(0).split(";");
            opcionesImpactoAmbiental = opciones.get(1).split(";");
            opcionesRecursoAfectado = opciones.get(2).split(";");
        }
    }
    
    /**
     * Método que crea el documento .csv con los datos ingresados por el usuario
     * @param infoImpacto
     * @throws IOException 
     */
    
    public void exportarCsv(ImpactoAmbiental infoImpacto) throws IOException{
        LocalDate fechaLocal = LocalDate.now();
        String fecha = String.valueOf(fechaLocal.getDayOfMonth())+ "-" + String.valueOf(fechaLocal.getMonthValue()) + "-" + String.valueOf(fechaLocal.getYear());
        String titulo = "Reporte Impacto Ambiental - Modificable - " + fecha;
        String salidaArchivo = System.getProperty("user.home");
        salidaArchivo = salidaArchivo + "/Documents/" + titulo + ".csv";
        
        boolean existe = new File(salidaArchivo).exists();
        if(existe){
            File archivoImpactoAmbiental = new File(salidaArchivo);
            archivoImpactoAmbiental.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true),';');
            salidaCSV.write("PROCESO");
            salidaCSV.write(infoImpacto.getProceso());
            salidaCSV.endRecord();
            salidaCSV.write("SEDE");
            salidaCSV.write(infoImpacto.getSede());
            salidaCSV.endRecord();
            salidaCSV.write("ACTIVIDAD ASOCIADA AL APECTO");
            salidaCSV.write(infoImpacto.getActividadAsociada());
            salidaCSV.endRecord();
            salidaCSV.write("CICLO DE VIDA DEL SERVICIO");
            salidaCSV.write(infoImpacto.getCicloDeVida());
            salidaCSV.endRecord();
            salidaCSV.write("OBSERVACIONES");
            salidaCSV.write(infoImpacto.getObservaciones());
            salidaCSV.endRecord();
            salidaCSV.write("LEGISLACION AMBIENTAL RELACIONADA");
            salidaCSV.write(infoImpacto.getLegislacionAsociada());
            salidaCSV.endRecord();
            salidaCSV.write("CONTROL OPERACIONAL");
            salidaCSV.write(infoImpacto.getControlOperacional());
            salidaCSV.endRecord();
            salidaCSV.write("ACCIONES DE MEJORA DEL CONTROL OPERACIONAL");
            salidaCSV.write(infoImpacto.getAccionesDeMejora());
            salidaCSV.endRecord();
            salidaCSV.write("SITUACION");
            salidaCSV.write(infoImpacto.getSituacion());
            salidaCSV.endRecord();
            salidaCSV.write("ASPECTO AMBIENTAL ASOCIADO");
            salidaCSV.write(infoImpacto.getAspectoAmbiental());
            salidaCSV.endRecord();
            salidaCSV.write("IMPACTO AMBIENTAL ASOCIADO");
            salidaCSV.write(infoImpacto.getImpactoAmbiental());
            salidaCSV.endRecord();
            salidaCSV.write("RECURSO AFECTADO");
            salidaCSV.write(infoImpacto.getRecursoAfectado());
            salidaCSV.endRecord();
            salidaCSV.write("TIPO DEL IMPACTO");
            salidaCSV.write(infoImpacto.getTipoDelImpacto());
            salidaCSV.endRecord();
            salidaCSV.write("CALIFICACION");
            salidaCSV.write(infoImpacto.getCalificacion());
            salidaCSV.endRecord();
            salidaCSV.write("VALOR");
            salidaCSV.write(infoImpacto.isValor());
            salidaCSV.endRecord();
            salidaCSV.write("ALCANCE");
            salidaCSV.write(String.valueOf(infoImpacto.getAlcance()));
            salidaCSV.endRecord();
            salidaCSV.write("PROBABILIDAD");
            salidaCSV.write(String.valueOf(infoImpacto.getProbabilidad()));
            salidaCSV.endRecord();
            salidaCSV.write("DURACION");
            salidaCSV.write(String.valueOf(infoImpacto.getDuracion()));
            salidaCSV.endRecord();
            salidaCSV.write("RECUPERABILIDAD");
            salidaCSV.write(String.valueOf(infoImpacto.getRecuperabilidad()));
            salidaCSV.endRecord();
            salidaCSV.write("MAGNITUD");
            salidaCSV.write(String.valueOf(infoImpacto.getMagnitud()));
            salidaCSV.endRecord();
            salidaCSV.write("NORMATIVIDAD");
            salidaCSV.write(String.valueOf(infoImpacto.getNormatividad()));
            salidaCSV.endRecord();
            salidaCSV.write("IMPORTANCIA DEL IMPACTO");
            salidaCSV.write(String.valueOf(infoImpacto.getImportanciaDelImpacto()));
            salidaCSV.endRecord();
            salidaCSV.write("CUMPLE CON LA NORMATIVIDAD");
            salidaCSV.write(String.valueOf(infoImpacto.isCumpleNormatividad()));
            salidaCSV.endRecord();
            salidaCSV.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}