/*
    * Descripción de la clase
 */
package control;

//import com.csvreader.CsvWriter;
import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Constructor vacio
     */
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
    
    public void llamarExportacionCVS() throws IOException{
        List<ImpactoAmbiental> infoImpacto = new ArrayList<ImpactoAmbiental>();
        infoImpacto.add(new ImpactoAmbiental());
        exportarCSV(infoImpacto);
    }
    
    public void exportarCSV(List<ImpactoAmbiental> infoImpacto) throws IOException{
        String salidaArchivo = "InformacionDeImpactoAmbiental.csv";
        boolean existe = new File(salidaArchivo).exists();
        if(existe){
            File archivoImpactoAmbiental = new File(salidaArchivo);
            archivoImpactoAmbiental.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true),';');
            salidaCSV.write("Proceso;"+Matriz.txtProceso.getText());
            salidaCSV.write("Sede;"+Matriz.txtSede.getText());
            salidaCSV.write("Actividad asociada al aspecto;"+Matriz.txtActividadAsociada.getText());
            salidaCSV.write("Ciclo de vida del servicio;"+Matriz.txtCicloVida.getText());
            salidaCSV.write("Observaciones;"+Matriz.txtObservaciones.getText());
            salidaCSV.write("Legislacion ambiental relacionada;"+Matriz.txtLegislacion.getText());
            salidaCSV.write("Control operacional;"+Matriz.txtControlOperacional.getText());
            salidaCSV.write("Acciones de mejora del control operacional;"+Matriz.txtAccionMejora.getText());
            salidaCSV.endRecord();
            for(ImpactoAmbiental impacto : infoImpacto){
                salidaCSV.write(impacto.getProceso());
                salidaCSV.write(impacto.getSede());
                salidaCSV.write(impacto.getActividadAsociada());
                salidaCSV.write(impacto.getCicloDeVida());
                salidaCSV.write(impacto.getObservaciones());
                salidaCSV.write(impacto.getLegislacionAsociada());
                salidaCSV.write(impacto.getControlOperacional());
                salidaCSV.write(impacto.getAccionesDeMejora());
                salidaCSV.endRecord();
            }
            salidaCSV.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}