/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

/**
 *
 * @author ALL
 */

import com.paedeias.identidades.Plugins;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Linea;


public class hPlugins {

    public hPlugins() {
    }
    
          /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Plugins> consultaPlugins(String campo, String match, String condicion)
    {
       List<Plugins> plugins = new ArrayList<Plugins>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,idPlugin,permisosPlugin FROM plugins ORDER BY id;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idPlugin,permisosPlugin FROM plugins WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id;";
            }else
            {
            query = "SELECT id,idPlugin,permisosPlugin FROM plugins WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPlugins()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePlugins.php");
		  
		  //Escribe el .php
		  
		  
		  
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
		   
		   //Arma el objeto y agrega a la lista en su caso
           
	    Plugins plugin = new Plugins();
            plugin.setId(Long.parseLong(atributos[1]));
            plugin.setIdPlugin(atributos[2]);
            plugin.setPermisosPlugin(atributos[3]);                
            plugins.add(plugin);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return plugins;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }
       
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idPlugin,permisosPlugin FROM plugins ORDER BY id;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idPlugin,permisosPlugin FROM plugins WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idPlugin,permisosPlugin FROM plugins WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id;");
            }

            while (conjuntoResultados.next()) {

                Plugins plugin = new Plugins ();
                plugin.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                plugin.setIdPlugin(conjuntoResultados.getObject(2).toString());
                plugin.setPermisosPlugin(conjuntoResultados.getObject(3).toString());
                
                plugins.add(plugin);
            }
            return plugins;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
      public int guardarPlugin(Plugins l)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO plugins (idPlugin,permisosPlugin) VALUES"
                    + "(\'"+l.getIdPlugin()+"\',\'"+l.getPermisosPlugin()+"\');";
           //     Conexion conexion = new Conexion();
           //     conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      filas_afectadas = conexionweb.escribirEnWeb(query);
      return filas_afectadas;
       }
       
                filas_afectadas = CPrincipal.getConexion().moverDatos(query);
                CPrincipal.getConexion().cerrar(1);

       return filas_afectadas;
    }
    
     public int borrarPlugin(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM plugins WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM plugins WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }  
      

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM plugins WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM plugins WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
      
     
     public int actualizarPlugin(Plugins l)
    {
     int resultado = 0;
 //    Conexion conexion = new Conexion();
 //             conexion.crearConexion();


     String query = "UPDATE plugins SET "
             + "idPlugin = \'" +  l.getIdPlugin()+"\',"+
               "permisosPlugin = \'"+l.getPermisosPlugin()+"\'"+
               " WHERE id = \'"+l.getId()+"\'";

   //    System.out.println(query);
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      resultado = conexionweb.escribirEnWeb(query);
      return resultado;
       }
     
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }  
    
    
}
