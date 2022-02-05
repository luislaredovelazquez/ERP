/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Permisos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.identidades.Articulos;
import com.paedeias.identidades.Usuarios;
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
/**
 *
 * @author ALL
 */
public class hPermisos {
          public Permisos consultaPermisos(String campo, String match, String condicion)
    {
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       Permisos articulo = new Permisos(); 
       boolean bandera=false;
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,idUsuario,permisos FROM permisos ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
        
        
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaPermisos()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePermisos.php");
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
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdUsuario(Long.parseLong(atributos[2]));
                articulo.setPermisos(atributos[3]);
                bandera = true;      
           }
           s=recv.readLine();
           }
            if(!bandera)
                return null;
            return articulo;
           
           
        }catch (Exception e){
           e.printStackTrace();
       }
           return null;
       }
       
       
       
      
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                

                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdUsuario(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setPermisos(conjuntoResultados.getObject(3).toString());
                bandera = true;
            }
            if(!bandera)
                return null;
            return articulo;
        } catch (SQLException ex) {
            Logger.getLogger(hReimpresiones.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
          
          public List<Permisos> consultaPermiso(String campo, String match, String condicion)
    {
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
        
       List<Permisos> articulos = new ArrayList<Permisos>(); 
       boolean bandera=false;
       
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,idUsuario,permisos FROM permisos;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
        
        
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaPermiso()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePermisos.php");
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
               Permisos articulo = new Permisos();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdUsuario(Long.parseLong(atributos[2]));
                articulo.setPermisos(atributos[3]);
                articulos.add(articulo);
                bandera = true;      
           }
           s=recv.readLine();
           }
            if(!bandera)
                return null;
            return articulos;
           
           
        }catch (Exception e){
           e.printStackTrace();
       }
         return null;  
       }
       
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idUsuario,permisos FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Permisos articulo = new Permisos();
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdUsuario(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setPermisos(conjuntoResultados.getObject(3).toString());
                articulos.add(articulo);
            }
            if(!bandera)
                return null;
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hReimpresiones.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
      
      public int guardarPermisos(Permisos a)
    {
        
            int ultimoInsertado=-1;
            String query = " INSERT INTO permisos (" + 
                      "idusuario,"
                    + "permisos) VALUES" + "(\'" 
                    + a.getIdUsuario() + "\',\'" 
                    + a.getPermisos() + "');";
         //   Conexion conexion = new Conexion();
         //   conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      ultimoInsertado = conexionweb.escribirEnWebLast(query);
      return ultimoInsertado;
       }
            
            ultimoInsertado = CPrincipal.getConexion().moverDatosLast(query);
            CPrincipal.getConexion().cerrar(1);

            return ultimoInsertado;
    }
      
           public int actualizarPermisos(Permisos a,String campoCondicion,String match,String condicion)
    {
        
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();



     String query = "UPDATE permisos SET idUsuario = \'" +  a.getIdUsuario()+"\',"+
                  "permisos = \'" +a.getPermisos() +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
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
           
     public int borrarPermisos(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }   
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM permisos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM permisos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
           
}
