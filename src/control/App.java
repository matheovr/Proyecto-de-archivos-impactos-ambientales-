/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class App {
    public static void main(String[] args) throws IOException {
        Excel archivoCsv = new Excel("data/input/dataOptions.csv");
        System.out.println(Arrays.toString(archivoCsv.getOpcionesRecursoAfectado()));
    }
}
