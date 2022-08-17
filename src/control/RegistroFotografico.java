/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class RegistroFotografico implements RegistroFotograficoIF{
    private JFileChooser seleccionar;
    private File archivo;
    private byte[] imagen;
    private FileInputStream entrada;
    private FileOutputStream salida;
    private ArrayList<Image> imagenes;
   
    public RegistroFotografico(){
        seleccionar = new JFileChooser();
        imagenes = new ArrayList();
    }

    public ArrayList<Image> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Image> imagenes) {
        this.imagenes = imagenes;
    }
    
    /**
     *Método creado para seleccionar el archivo que queremos subir
     * @param archivo
     * @return 
     */
    
    public byte [] AbrirImagen(File archivo){
        byte[] imagen = new byte[1024*100];
        try{
            entrada= new FileInputStream(archivo);
            entrada.read(imagen);
            
        }catch (Exception e){
        }
            return imagen;
    }

    /**
     * Método que nos sirve para guardar la imagen que seleccionamos
     * @param archivo
     * @param byteImg
     * @return 
     */
    public String GuardarImagen(File archivo, byte[] byteImg){
        String respuesta = null;
        try {
            salida = new FileOutputStream(archivo);
            salida.write(byteImg);
            respuesta = "La imagen se guardó correctamente";
        } catch (Exception e) {
        }
        return respuesta;
        
    }
    
    /**
     *Método que agrega la imagen previamente seleccionada y la guardada al informe final
     */
    @Override
      public void agregarRegistroFotografico(){
          if(seleccionar.showDialog(null, null)==JFileChooser.APPROVE_OPTION){
              archivo=seleccionar.getSelectedFile();
              if(archivo.canRead()){
                  if(archivo.getName().endsWith("jpg")||archivo.getName().endsWith("png")||archivo.getName().endsWith("gif"));
                  imagen=AbrirImagen(archivo);                  
              }else{
                  JOptionPane.showMessageDialog(null, "Archivo no compatible");
              }
              if(seleccionar.showDialog(null, "Agregar Registro Fotográfico")==JFileChooser.APPROVE_OPTION){
                  archivo=seleccionar.getSelectedFile();
                  if(archivo.getName().endsWith("jpg")||archivo.getName().endsWith("png")||archivo.getName().endsWith("gif"));
                  String respuesta=GuardarImagen(archivo, imagen);
                  Image imagePdf = null;
                  try {
                      System.out.println(archivo.getAbsolutePath());
                      imagePdf = Image.getInstance(archivo.getAbsolutePath());
                      imagenes.add(imagePdf);
                  } catch (BadElementException | IOException e) {
                  }
                  if(respuesta!=null){
                      JOptionPane.showMessageDialog(null, respuesta);
                      
                  }else{
                      JOptionPane.showMessageDialog(null, "Archivo no guardado");
                  }
              }else{
                  JOptionPane.showMessageDialog(null, "Archivo guardado");
              }
   
          }
          
      }
      
      /**
       * Método creado para que una vez creado el pdf, el ArrayList quede vacío
       */
      @Override
      public void limpiarRegistrosFotograficos(){
          imagenes.clear();
      }
      
}