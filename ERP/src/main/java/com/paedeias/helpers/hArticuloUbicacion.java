/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Articulos;
import com.paedeias.identidades.Articuloubicacion;
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

/**
 *
 * @author Luis
 */
public class hArticuloUbicacion {

    public hArticuloUbicacion(){
    }

        public List<Articuloubicacion> consultaArticuloUbicacion(String id)
    {
       List<Articuloubicacion> ubicacion = new ArrayList<Articuloubicacion>();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "SELECT a.id as id, a.idarticulo as idarticulo, a.idubicacion as idubicacion,u.ubicacion as ubicacion FROM articuloubicacion a, ubicacion u WHERE "
                    +"a.idarticulo = \'"+id+"\' AND a.idubicacion = u.id";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticuloUbicacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloUbicacion.php");
		  
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
           
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdarticulo(Long.parseLong(atributos[2]));
                articulo.setIdubicacion(Long.parseLong(atributos[3]));                
                articulo.setUbicacion(atributos[4]);

                ubicacion.add(articulo);           
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return ubicacion;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }

       
       
       
       
       
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id, a.idarticulo, a.idubicacion,u.ubicacion FROM articuloubicacion a, ubicacion u WHERE "
                    +"a.idarticulo = \'"+id+"\' AND a.idubicacion = u.id");

            while (conjuntoResultados.next()) {
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdarticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setIdubicacion(Long.parseLong(conjuntoResultados.getObject(3).toString()));                
                articulo.setUbicacion(conjuntoResultados.getObject(4).toString());

                ubicacion.add(articulo);
            }
            return ubicacion;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

       public List<Articuloubicacion> consultaArticuloUbicacion()
    {
       List<Articuloubicacion> ubicacion = new ArrayList<Articuloubicacion>();
       
       
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "SELECT a.id as id, a.idarticulo as idarticulo, a.idubicacion as idubicacion FROM articuloubicacion a;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticuloUbicacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloUbicacion.php");
		  
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
           
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdarticulo(Long.parseLong(atributos[2]));
                articulo.setIdubicacion(Long.parseLong(atributos[3]));                
              
                ubicacion.add(articulo);           
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return ubicacion;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }
       
       
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id, a.idarticulo, a.idubicacion FROM articuloubicacion a;");

            while (conjuntoResultados.next()) {
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdarticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setIdubicacion(Long.parseLong(conjuntoResultados.getObject(3).toString()));                
                

                ubicacion.add(articulo);
            }
            return ubicacion;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
        
        
            public List<Articuloubicacion> consultaUbicacionArticulo(String id)
    {
       List<Articuloubicacion> ubicacion = new ArrayList<Articuloubicacion>();
       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "SELECT a.id, a.idarticulo, a.idubicacion FROM articuloubicacion a WHERE "
                    +"a.idubicacion = \'"+id+"\'";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUbicacionArticulo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloUbicacion.php");
		  
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
           
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdarticulo(Long.parseLong(atributos[2]));
                articulo.setIdubicacion(Long.parseLong(atributos[3]));                
              
                ubicacion.add(articulo);           
		   
		   //Arma el objeto y agrega a la lista en su caso   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return ubicacion;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }
       
       
       
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id, a.idarticulo, a.idubicacion FROM articuloubicacion a WHERE "
                    +"a.idubicacion = \'"+id+"\'");

            while (conjuntoResultados.next()) {
                Articuloubicacion articulo = new Articuloubicacion();
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdarticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setIdubicacion(Long.parseLong(conjuntoResultados.getObject(3).toString()));                
                ubicacion.add(articulo);
            }
            return ubicacion;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }    
            
               public List<Articulos> consultaArticulos(String campo)
    {
       List<Articulos> articulos = new ArrayList<Articulos>();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "SELECT e.id as id,e.codigo as codigo,e.descripcion as descripcion,e.clasificacion as clasificacion,e.precioVenta as precioVenta,e.precioCompra as precioCompra,e.ultimoCosto as ultimoCosto,e.unidad as unidad,e.existencia as existencia,e.reservado as reservado,e.minimoPzas as minimoPzas,e.maximoPzas as maximoPzas,e.promPzas as promPzas,e.iva as iva,e.ieps as ieps,"
                    + "e.tipoEtiqueta as tipoEtiqueta,e.bloqueado as bloqueado,e.almacenDevoluciones as almacenDevoluciones,e.lineaPrincipal as lineaPrincipal,e.anticipos as anticipos,e.sinonimoPrincipal as sinonimoPrincipal,e.codigo2 as codigo2,e.precioVenta2 as precioVenta2,e.proveedor as proveedor,e.paretto as paretto,e.nuevo as nuevo FROM articuloubicacion a, ubicacion u, articulos e WHERE "
                    +"a.idarticulo = e.id AND a.idubicacion = u.id AND ubicacion = \'"+campo+"\';";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticulos()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloUbicacion.php");
		  
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
           
           
           
             Articulos articulo = new Articulos();
                        articulo.setId(Long.parseLong(atributos[1]));
                        articulo.setCodigo(atributos[2]);
                        articulo.setDescripcion(atributos[3]);
                        articulo.setClasificacion(atributos[4]);
                        articulo.setPrecioVenta(Double.parseDouble(atributos[5]));
                        articulo.setPrecioCompra(Double.parseDouble(atributos[6]));
                        articulo.setUltimoCosto(Double.parseDouble(atributos[7]));
                        articulo.setUnidad(atributos[8]);
                        articulo.setExistencia(Long.parseLong(atributos[9]));
                        articulo.setReservado(Long.parseLong(atributos[10]));
                        articulo.setMinimoPzas(Long.parseLong(atributos[11]));
                        articulo.setMaximoPzas(Long.parseLong(atributos[12]));
                        articulo.setPromPzas(Long.parseLong(atributos[13]));
                        articulo.setIva(Integer.parseInt(atributos[14]));
                        articulo.setIeps(Integer.parseInt(atributos[15]));
                        articulo.setTipoEtiqueta(atributos[16]);
                        articulo.setUbicacion("General");
                        articulo.setIdCompleto("av"+articulo.getId());
                        articulo.setBloqueado(Integer.parseInt(atributos[17]));
                        articulo.setAlmacenDevoluciones(Integer.parseInt(atributos[18]));
                        articulo.setLineaPrincipal(atributos[19]);
                        articulo.setAnticipos(Integer.parseInt(atributos[20]));
                        articulo.setSinonimoPrincipal(atributos[21]);
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
       
       
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT e.id,e.codigo,e.descripcion,e.clasificacion,e.precioVenta,e.precioCompra,e.ultimoCosto,e.unidad,e.existencia,e.reservado,e.minimoPzas,e.maximoPzas,e.promPzas,e.iva,e.ieps,e.tipoEtiqueta,e.bloqueado,e.almacenDevoluciones,e.lineaPrincipal,e.anticipos,e.sinonimoPrincipal,e.codigo2,e.precioVenta2,e.proveedor,e.paretto,e.nuevo FROM articuloubicacion a, ubicacion u, articulos e WHERE "
                    +"a.idarticulo = e.id AND a.idubicacion = u.id AND ubicacion = \'"+campo+"\';");
            

            while (conjuntoResultados.next()) {
                        Articulos articulo = new Articulos();
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(4).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(8).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(16).toString());
                        articulo.setUbicacion("General");
                        articulo.setIdCompleto("av"+articulo.getId());
                        articulo.setBloqueado(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                        articulo.setAlmacenDevoluciones(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                        articulo.setLineaPrincipal(conjuntoResultados.getObject(19).toString());
                        articulo.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                        articulo.setSinonimoPrincipal(conjuntoResultados.getObject(21).toString());
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
        
     public int guardarArticuloUbicacion(Articuloubicacion a)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO articuloubicacion (idarticulo,"
                    + "idubicacion) VALUES"
                    + "(\'"+a.getIdarticulo()+
                    "\',\'"+a.getIdubicacion()+"');";
            //    Conexion conexion = new Conexion();
            //    conexion.crearConexion();
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

          public int guardarArticuloUbicacionLast(Articuloubicacion a)
    {
       int ultimoInsertado = -1;
       String query = " INSERT INTO articuloubicacion (idarticulo,"
                    + "idubicacion) VALUES"
                    + "(\'"+a.getIdarticulo()+
                    "\',\'"+a.getIdubicacion()+"');";
            //    Conexion conexion = new Conexion();
            //    conexion.crearConexion();
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

      public int borrarArticuloUbicacion(String id)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      tipo_respuesta = conexionweb.escribirEnWeb("DELETE  FROM articuloubicacion WHERE id ="+" \'"+id+"\';");
      return tipo_respuesta;
       }
      
      
            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloubicacion WHERE id ="+" \'"+id+"\';");
            CPrincipal.getConexion().cerrar(1);
            
            
            

      return tipo_respuesta;
    }

            public int borrarArticuloUbicacionCompleto(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM articuloubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM articuloubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

      
    public int actualizarArticuloLinea(Articuloubicacion a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articuloubicacion SET idarticulo = \'" +  a.getIdarticulo()+"\',"+
                  "idubicacion = \'" +    a.getIdubicacion()+"\'";
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
                   
      // System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }

        public int actualizarUbicacion(String campoCondicion,String match,String condicion,String valor)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE articuloubicacion SET "+
                  "idubicacion = \'" + valor+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
      // System.out.println(query);
                   
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
        
      public int actualizarUbicacionPorCodigo(String condicion,String valor)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articuloubicacion SET "+
                  "idubicacion = \'" + valor+"\'";
            query = query + " WHERE idarticulo = (SELECT id FROM articulos WHERE codigo = \'"+condicion+"\');";
            //       if(match.equals("="))
           // {
            // query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
          //  } else if(match.equals("LIKE"))
            //   {
           // query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
           //    }
      // System.out.println(query);
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
    
       public static void main(String args[])
    {
       hArticuloUbicacion ejemplo = new hArticuloUbicacion();
       List<Articuloubicacion> articulos = ejemplo.consultaArticuloUbicacion("14");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getUbicacion());
           i++;
       }
       System.out.println(ejemplo.borrarArticuloUbicacion("1"));

       Articuloubicacion art = new Articuloubicacion();
                     art.setIdarticulo(1);
                     art.setIdubicacion(2);

 //     System.out.println(ejemplo.guardarArticuloUbicacion(art));

      art.setIdubicacion(3);
 //     System.out.println(ejemplo.actualizarArticuloLinea(art, "idarticulo", "=", "1"));


    }

}
