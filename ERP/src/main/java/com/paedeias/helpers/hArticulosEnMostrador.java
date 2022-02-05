/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Anticipos;
import com.paedeias.identidades.ArticulosEnMostrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author ALL
 */
public class hArticulosEnMostrador {

    public hArticulosEnMostrador() {
    }
    
      public List<ArticulosEnMostrador> consultaArticulosEnMostrador(String campo, String match, String condicion)
    {
       List<ArticulosEnMostrador> articulos = new ArrayList<ArticulosEnMostrador>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador ORDER BY id DESC LIMIT 200;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 200;";
            }else
            {
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 200;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticulosEnMostrador()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulosEnMostrador.php");
		  
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
                 ArticulosEnMostrador articulo = new ArticulosEnMostrador();              
                articulo.setId(Long.valueOf(atributos[1]));
                articulo.setCodigo(atributos[2]);
                articulo.setDescripcion(atributos[3]);
                articulo.setCantidad(Integer.valueOf(atributos[4]));
                articulo.setResponsable(Long.valueOf(atributos[5]));
                articulo.setFecha(atributos[6]);
                articulos.add(articulo);
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articulos;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador ORDER BY id DESC LIMIT 200;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 200;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 200;");
            }
                       
            while (conjuntoResultados.next()) {
                
                ArticulosEnMostrador articulo = new ArticulosEnMostrador();              
                articulo.setId(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.valueOf(conjuntoResultados.getObject(4).toString()));
                articulo.setResponsable(Long.valueOf(conjuntoResultados.getObject(5).toString()));
                articulo.setFecha(conjuntoResultados.getObject(6).toString());
                articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
      public List<ArticulosEnMostrador> consultaArticulosEnMostrador2(String campo, String match, String condicion)
    {
       List<ArticulosEnMostrador> articulos = new ArrayList<ArticulosEnMostrador>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
             if(match.equals("*")){
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticulosEnMostrador2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulosEnMostrador.php");
		  
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
                 ArticulosEnMostrador articulo = new ArticulosEnMostrador();              
                articulo.setId(Long.valueOf(atributos[1]));
                articulo.setCodigo(atributos[2]);
                articulo.setDescripcion(atributos[3]);
                articulo.setCantidad(Integer.valueOf(atributos[4]));
                articulo.setResponsable(Long.valueOf(atributos[5]));
                articulo.setFecha(atributos[6]);
                articulos.add(articulo);
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articulos;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,cantidad,responsable,fecha FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                ArticulosEnMostrador articulo = new ArticulosEnMostrador();              
                articulo.setId(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.valueOf(conjuntoResultados.getObject(4).toString()));
                articulo.setResponsable(Long.valueOf(conjuntoResultados.getObject(5).toString()));
                articulo.setFecha(conjuntoResultados.getObject(6).toString());
                articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
      
       public int guardarArticulosEnMostrador(ArticulosEnMostrador a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO articulosenmostrador (" 
                    + "codigo,"
                    + "descripcion,"
                    + "cantidad,"
                    + "responsable,"
                    + "fecha) VALUES" + "(\'" 
                    + a.getCodigo() + "\',\'" 
                    + a.getDescripcion()+ "\',\'" 
                    + a.getCantidad()+ "\',\'" 
                    + a.getResponsable() + "\',\'"  
                    + fecha.substring(0,19) + "');";
       //     Conexion conexion = new Conexion();
       //     conexion.crearConexion();
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
      
              public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE articulosenmostrador SET codigo = \'"+codigoNuevo+"\' WHERE codigo = \'"+codigoViejo+"\';";

     //  System.out.println(query);
     if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      conexionweb.escribirEnWeb(query);
      return true;
       }
     
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return true;
    }   
       
     public int borrarArticulosEnMostrador(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulosenmostrador WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulosenmostrador WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
     
     public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE articulosenmostrador SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      resultado = conexionweb.escribirEnWeb(query);
      return resultado;
       }
       
       resultado=CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);
        return resultado;
    }  
     
        public int actualizarCantidad(String cantidad, String id)
    {
     int resultado = 0;
             
       String query = "UPDATE articulosenmostrador SET cantidad = \'"+cantidad+"\' WHERE id = \'"+id+"\';";
       
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      resultado = conexionweb.escribirEnWeb(query);
      return resultado;
       }
       
       resultado=CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);
        return resultado;
    }  
       
}
