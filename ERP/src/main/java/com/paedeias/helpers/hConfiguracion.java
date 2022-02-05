/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALL
 */
public class hConfiguracion {
     public String consulta(String campo, String match, String condicion)
    {
        
String contraseña="";

  if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT contrasena FROM configuracion WHERE "+campo+" "+match+" \'"+condicion+"\';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consulta()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeConfiguracion.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
          String s=recv.readLine();
           ConexionWeb web = new ConexionWeb();
           s=web.cambiarCadena(s);
           
           
            while(s!=null)
           {
           String[] objetos = s.split("°°°");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
           contraseña =  atributos[1];
           }
           s=recv.readLine();
           }
           
            return contraseña;
           
        }catch (Exception e){
           e.printStackTrace();
       }
           
           return null;
        }




       
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT contrasena FROM configuracion WHERE "+campo+" "+match+" \'"+condicion+"\';");
            
                       
            while (conjuntoResultados.next()) {
                Blob blob =  conjuntoResultados.getBlob("contrasena");
                byte[] bdata = blob.getBytes(1, (int) blob.length());
                contraseña = new String(bdata);
            }
            return contraseña;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
}
