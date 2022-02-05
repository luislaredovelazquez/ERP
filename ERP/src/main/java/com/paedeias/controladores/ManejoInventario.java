/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores;

import com.paedeias.identidades.Inventario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALL
 */
public class ManejoInventario {
        public List<Inventario> leerArchivo(String URLarchivo){
    
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
    
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         List<Inventario> listaarticulos = new ArrayList<Inventario>();
         archivo = new File (URLarchivo);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
         {
             Inventario articulo1 = new Inventario();
             articulo1.setCodigo(linea);
             articulo1.setFisico(1);
             
             boolean bandera = false;
             int it1 = 0;
             while(it1 < listaarticulos.size())
             {
             if(articulo1.getCodigo().equals(listaarticulos.get(it1).getCodigo()))    
             {
               listaarticulos.get(it1).setFisico(listaarticulos.get(it1).getFisico()+1);  
               bandera = true;
             }
             it1++;
             }
             if(bandera == false)
                 listaarticulos.add(articulo1);
         }
         return listaarticulos;
      }
      catch(Exception e){
         e.printStackTrace();
         return null;
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        
    }
}
