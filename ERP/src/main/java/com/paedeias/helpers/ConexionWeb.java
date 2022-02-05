/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CGlobalConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author ALL
 */
public class ConexionWeb {

    /**
     * @param args the command line arguments
     */
    
    public String cambiarCadena(String s)
    {
           if(s!=null)
           {
           s = StringEscapeUtils.unescapeJava(s); //pq una no es suficiente!
           s = StringEscapeUtils.unescapeJava(s);
           
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~~", "~ ~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~~", "~ ~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~~", "~ ~ ~ ~ ~ ~");
           s=s.replace("~~~~~", "~ ~ ~ ~ ~");
           s=s.replace("~~~~", "~ ~ ~ ~");
           s=s.replace("~~~", "~ ~ ~");
           s=s.replace("~~", "~ ~");
           }
        return s;
    }
    
    public int publicarEnRed(String cadena)
    {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
	//Escribe tu query
	hCuentasPorCobrar reloj= new hCuentasPorCobrar();
        String hora = reloj.generarFecha();
       // cadena = StringEscapeUtils.escapeHtml(cadena);
        String query = "INSERT INTO publicar (usuario,texto,fecha,hora) VALUES (\'"+CGlobalConfig.getUsuarioRed()+"\',\'"+cadena+"\',\'"+hora.substring(0, 9)+"\',\'"+hora.substring(11, 16)+"\');";	
       // String query = "INSERT INTO publicar (usuario,texto,fecha,hora) VALUES (\'luis\',\'olálá\',\'2014-04-02\',\'14:45\');";
        query = StringEscapeUtils.escapeJava(query);
        datosEnv.put("consulta",query);
	
		
        //Escribe el método
		
        datosEnv.put("base","quimera1_activities");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePublicarWeb.php");
          //Escribe el .php
		  
		  
		  
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();
           return Integer.valueOf(s);

	   
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0       
        return -1;
    }

    public int escribirEnWeb(String query)
    {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
	//Escribe tu query        
//      System.out.println("L "+query);
        query = StringEscapeUtils.escapeJava(query);
        datosEnv.put("consulta",query);
	
		
        //Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePublicarWeb.php");
		  
		  //Escribe el .php
		  
		  
		  
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();
           
           
           if(s.equals("-1"))
           {
           JOptionPane.showMessageDialog(null, "Ha existido un error de conexión en el sistema o con los datos, intente realizar la operación nuevamente o consulte al administrador");           
           System.exit(0);    
           }
           
           return Integer.valueOf(s);

	   
           
        }catch (Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Ha existido un error de conexión en el sistema, intente realizar la operación nuevamente");
           System.exit(0);
       }
           //Regresa nulo o 0       
        return -1;
    }    
    
    
    
    
     public int escribirEnWebLast(String query)
    {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
	//Escribe tu query       
        
        query = StringEscapeUtils.escapeJava(query);
        datosEnv.put("consulta",query);
	
		
        //Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("quiero","ultimo");
         Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePublicarWeb.php");
		  
		  //Escribe el .php
		  
		  
		  
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();
           
           if(s.equals("-1"))
           {
           JOptionPane.showMessageDialog(null, "Ha existido un error de conexión en el sistema, intente realizar la operación nuevamente");           
           System.exit(0);    
           }
           
           return Integer.valueOf(s);

	   
           
        }catch (Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Ha existido un error de conexión en el sistema, intente realizar la operación nuevamente");
           System.exit(0);
       }
           //Regresa nulo o 0       
        return -1;
    }    
    
     public String iniciarTransaccion()
     {
         return "START TRANSACTION;";
     }
     
     public String finalizarTransaccion()
     {
         return "COMMIT;";
     }
     
     public String cancelarTransaccion()
     {
         return "ROLLBACK;";
     }
     
     public String ultimoId()
     {
         return "SET @ultimo_id = LAST_INSERT_ID();";
     }
     
    
    public static void main(String[] args) {
        // TODO code application logic here
        CGlobalConfig.setConexion("quimera1_bdprueba");
        ConexionWeb web = new ConexionWeb();
        int publica = web.escribirEnWeb("hola");
      //  int publica = web.escribirEnWeb("INSERT INTO blog (" + 
      //              "titulo,texto,autor) VALUES" + "(\'hola\',\'hola\',\'adios\');");
        System.out.println(publica);
    }
}
