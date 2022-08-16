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
 *
 * @author jpag0
 */
public class RegistroFotografico {
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
     * Método creado para seleccionar el archivo que queremos subir
     * @param archivo
     * @return Retorna la imagen seleccionada para luego agregarla al registro
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
     * @return Nos retorna si la imagen se guardó
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
     * Se agraga la imagen previamente seleccionada y guardada para esta agregarla al pdf
     * 
     */
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
      
      public void limpiarRegistrosFotograficos(){
          imagenes.clear();
      }
      
}