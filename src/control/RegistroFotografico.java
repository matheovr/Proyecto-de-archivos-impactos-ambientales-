/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    byte[] imagen;
    FileInputStream entrada;
    FileOutputStream salida;
   
    public RegistroFotografico(){
        
    }
    public static void main(String[] args) {
        
    }
    public byte [] AbrirImagen(File archivo){
        byte[] imagen = new byte[1024*100];
        try{
            entrada= new FileInputStream(archivo);
            entrada.read(imagen);
            
        }catch (Exception e){
        }
            return imagen;
    }

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
    
      private void btnAgregarRegistro(java.awt.Event evt){
          if(seleccionar.showDialog(null, null)==JFileChooser.APPROVE_OPTION){
              archivo=seleccionar.getSelectedFile();
              if(archivo.canRead()){
                  if(archivo.getName().endsWith("jpg")||archivo.getName().endsWith("png")||archivo.getName().endsWith("gif"));
                  imagen=AbrirImagen(archivo);                  
              }else{
                  JOptionPane.showMessageDialog(null, "Archivo no compatible");
              }
              if(seleccionar.showDialog(null, "Agregar Registro Fotográfico")==JFileChooser.APPROVE_OPTION){
                  ArrayList<File> imagenes = new ArrayList();
                  archivo=seleccionar.getSelectedFile();
                  if(archivo.getName().endsWith("jpg")||archivo.getName().endsWith("png")||archivo.getName().endsWith("gif"));
                  String respuesta=GuardarImagen(archivo, imagen);
                  imagenes.add(archivo);
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
}