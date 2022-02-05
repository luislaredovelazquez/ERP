/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Blog;
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

/**
 *
 * @author ALL
 */
public class hBlog {

    public hBlog() {
    }
    
    /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Blog> consultaBlog(String campo, String match, String condicion)
    {
       List<Blog> articulos = new ArrayList<Blog>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "";
        if(match.equals("*")){
            query = "SELECT idblog,titulo,texto,autor FROM blog ORDER BY idblog DESC LIMIT 10;";
            }
            else if(match.equals("="))
            {
            query = "SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idblog DESC LIMIT 10;";
            }else
            {
            query = "SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY idblog DESC LIMIT 10;";
            }
        
        
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaBlog()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeBlog.php");
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
           
             Blog articulo = new Blog();
             articulo.setId(Long.parseLong(atributos[1]));
             articulo.setTitulo(atributos[2]);
             articulo.setTexto(atributos[3]);
             articulo.setAutor(atributos[4]);
                      
                articulos.add(articulo);
           }
            s=recv.readLine();
           }
          return articulos;
          
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
       }
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog ORDER BY idblog DESC LIMIT 10;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idblog DESC LIMIT 10;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY idblog DESC LIMIT 10;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Blog articulo = new Blog(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),                       
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString()
                        ); 

                articulos.add(articulo);
            }
            CPrincipal.getConexion().cerrar(0);
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
        /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Blog> consultaBlog2(String campo, String match, String condicion)
    {
       List<Blog> articulos = new ArrayList<Blog>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
           if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "";
            if(match.equals("*")){
            query = "SELECT idblog,titulo,texto,autor FROM blog;";
            }
            else if(match.equals("="))
            {
            query = "SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
        
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaBlog2()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeBlog.php");
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
           
             Blog articulo = new Blog();
             articulo.setId(Long.parseLong(atributos[1]));
             articulo.setTitulo(atributos[2]);
             articulo.setTexto(atributos[3]);
             articulo.setAutor(atributos[4]);
                      
                articulos.add(articulo);
           }
            s=recv.readLine();
           }
          return articulos;
          
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
       }
       
       
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idblog,titulo,texto,autor FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Blog articulo = new Blog(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),                       
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString()
                        ); 

                articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
    
        public int guardarBlog(Blog a)
    {
        
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO blog (" + 
                    "titulo,texto,autor) VALUES" + "(\'" + a.getTitulo() + "\',\'"+ a.getTexto() + "\',\'"+ a.getAutor() + "');";
          //  Conexion conexion = new Conexion();
          //  conexion.crearConexion();
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
        
         public int borrarBlog(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM blog WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM blog WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

             /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarBlog(Blog a,String campoCondicion,String match,String condicion)
    {

        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE blog SET "
                + "titulo = \'" +  a.getTitulo()+"\',"+
                  "autor = \'" +  a.getAutor()+"\',"+
                  "texto = \'" + a.getTexto()+"\'";
     
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

  
  
    
}
