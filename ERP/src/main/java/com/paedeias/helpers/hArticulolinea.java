/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Articulolinea;
import com.paedeias.identidades.Articulos;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Luis
 */
public class hArticulolinea {

    public hArticulolinea(){
    }

     public List<Articulolinea> consultaArticuloLinea(String id)
    {
       List<Articulolinea> linea = new ArrayList<Articulolinea>();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT a.id,a.claveArticulo,a.claveLinea,l.nombre, l.descripcion FROM articulolinea a, linea l WHERE "
                    +"a.claveArticulo = \'"+id+"\' AND a.claveLinea = l.id";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticuloLinea(String id)");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulolinea.php");
		  
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
                      
                Articulolinea articulo = new Articulolinea();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setClaveArticulo(Long.parseLong(atributos[2]));
                articulo.setClaveLinea(Long.parseLong(atributos[3]));
                articulo.setNombre(atributos[4]);
                articulo.setDescripcion(atributos[5]);
                linea.add(articulo);
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return linea;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.claveArticulo,a.claveLinea,l.nombre, l.descripcion FROM articulolinea a, linea l WHERE "
                    +"a.claveArticulo = \'"+id+"\' AND a.claveLinea = l.id");
            while (conjuntoResultados.next()) {
                Articulolinea articulo = new Articulolinea();

                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setClaveArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setClaveLinea(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                articulo.setNombre(conjuntoResultados.getObject(4).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(5).toString());
                linea.add(articulo);
            }
            return linea;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
          public List<Articulolinea> consultaArticuloLinea()
    {
       List<Articulolinea> linea = new ArrayList<Articulolinea>();
       
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT a.id,a.claveArticulo,a.claveLinea FROM articulolinea a;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticuloLinea()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulolinea.php");
		  
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
                      
                Articulolinea articulo = new Articulolinea();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setClaveArticulo(Long.parseLong(atributos[2]));
                articulo.setClaveLinea(Long.parseLong(atributos[3]));
                linea.add(articulo);
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return linea;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.claveArticulo,a.claveLinea FROM articulolinea a;");
            while (conjuntoResultados.next()) {
                Articulolinea articulo = new Articulolinea();

                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setClaveArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setClaveLinea(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                linea.add(articulo);
            }
            return linea;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public List<Articulolinea> consultaLineaArticulo(String id)
    {
       List<Articulolinea> linea = new ArrayList<Articulolinea>();
       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT a.id,a.claveArticulo,a.claveLinea FROM articulolinea a WHERE "
                    +"a.claveLinea = \'"+id+"\'";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaLineaArticulo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulolinea.php");
		  
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
                      
                Articulolinea articulo = new Articulolinea();
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setClaveArticulo(Long.parseLong(atributos[2]));
                articulo.setClaveLinea(Long.parseLong(atributos[3]));
                linea.add(articulo);
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return linea;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.claveArticulo,a.claveLinea FROM articulolinea a WHERE "
                    +"a.claveLinea = \'"+id+"\'");
            while (conjuntoResultados.next()) {
                Articulolinea articulo = new Articulolinea();

                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setClaveArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setClaveLinea(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                linea.add(articulo);
            }
            return linea;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
     public List<Articulos> consultaArticuloxNombre (String nombre)
     {
               List<Articulolinea> linea = new ArrayList<Articulolinea>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
               

       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT a.claveArticulo FROM articulolinea a, linea l WHERE "
                    +"l.nombre = \'"+nombre+"\' AND a.claveLinea = l.id";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArticuloxNombre()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulolinea.php");
		  
		  //Escribe el .php
		  
		  
		  
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          List articulos = new ArrayList<Articulos>();
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
           articulo.setId(Long.valueOf(atributos[1]));
           articulo.setCodigo(atributos[2]);
           articulo.setDescripcion(atributos[3]);
           articulo.setClasificacion(atributos[4]);
           articulo.setPrecioVenta(Double.valueOf(atributos[5]));
           articulo.setPrecioCompra(Double.valueOf(atributos[6]));
           articulo.setUltimoCosto(Double.valueOf(atributos[7]));
           articulo.setUnidad(atributos[8]);
           articulo.setExistencia(Integer.valueOf(atributos[9]));
           articulo.setReservado(Integer.valueOf(atributos[10]));
           articulo.setMinimoPzas(Integer.valueOf(atributos[11]));
           articulo.setMaximoPzas(Integer.valueOf(atributos[12]));
           articulo.setPromPzas(Integer.valueOf(atributos[13]));
           articulo.setIva(Integer.valueOf(atributos[14]));
           articulo.setIeps(Integer.valueOf(atributos[15]));
           articulo.setTipoEtiqueta(atributos[16]);
           articulo.setBloqueado(Integer.valueOf(atributos[17]));
           articulo.setAlmacenDevoluciones(Integer.valueOf(atributos[18]));
           articulo.setLineaPrincipal(atributos[19]);
           articulo.setAnticipos(Integer.valueOf(atributos[20]));
           articulo.setSinonimoPrincipal(atributos[21]);
           articulo.setCodigo2(atributos[22]);
           articulo.setPrecioVenta2(Double.valueOf(atributos[23]));
           articulo.setProveedor(atributos[24]);
           articulo.setParetto(Integer.valueOf(atributos[25]));
           articulo.setNuevo(Integer.valueOf(atributos[26]));
           articulo.setOferta(Integer.valueOf(atributos[27]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.claveArticulo FROM articulolinea a, linea l WHERE "
                    +"l.nombre = \'"+nombre+"\' AND a.claveLinea = l.id");
            while (conjuntoResultados.next()) {
                Articulolinea articulo = new Articulolinea();
                articulo.setClaveArticulo(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                linea.add(articulo);
            }
            
            int indice=0;
            hArticulos harticulos= new hArticulos();
            List listaArticulos = new ArrayList<Articulos>();
            List listaProvicional = new ArrayList<Articulos>();
            while(indice < linea.size())
            {
                listaProvicional = harticulos.consultaArticulos("id", "=", String.valueOf(linea.get(indice).getClaveArticulo()));
                listaArticulos.add(listaProvicional.get(0));
                listaProvicional.clear();
                indice++;
            }
            
            
            
            return listaArticulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null; 
     }

     public int guardarArticuloLinea(Articulolinea a)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO articulolinea (claveArticulo,"
                    + "claveLinea) VALUES"
                    + "(\'"+a.getClaveArticulo()+
                    "\',\'"+a.getClaveLinea()+"');";
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

         public int guardarArticuloLineaLast(Articulolinea a)
    {
       int ultimo_Insertado = 0;
       String query = " INSERT INTO articulolinea (claveArticulo,"
                    + "claveLinea) VALUES"
                    + "(\'"+a.getClaveArticulo()+
                    "\',\'"+a.getClaveLinea()+"');";
             //   Conexion conexion = new Conexion();
             //   conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      ultimo_Insertado = conexionweb.escribirEnWebLast(query);
      return ultimo_Insertado;
       }
       
                ultimo_Insertado = CPrincipal.getConexion().moverDatosLast(query);
                CPrincipal.getConexion().cerrar(1);

       return ultimo_Insertado;
    }

     public int borrarArticuloLinea(String id)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      tipo_respuesta = conexionweb.escribirEnWeb("DELETE  FROM articulolinea WHERE id ="+" \'"+id+"\';");
      return tipo_respuesta;
       }

                CPrincipal.getConexion().moverDatos("DELETE  FROM articulolinea WHERE id ="+" \'"+id+"\';");
         
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

      public int borrarArticuloLineaCompleto(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM articulolinea WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query ="DELETE  FROM articulolinea WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulolinea WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulolinea WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }


     public int actualizarArticuloLinea(Articulolinea a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articulolinea SET claveArticulo = \'" +  a.getClaveArticulo()+"\',"+
                  "claveLinea = \'" +    a.getClaveLinea()+"\'";
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

         public static void main(String args[])
    {
       hArticulolinea ejemplo = new hArticulolinea();
       List<Articulolinea> articulos = ejemplo.consultaArticuloLinea("13");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getNombre());
           i++;
       } 
    //   System.out.println(ejemplo.borrarArticuloLinea("claveLinea", "=", "15"));

       Articulolinea art = new Articulolinea();
                     art.setClaveArticulo(12);
                     art.setClaveLinea(15);

    //  System.out.println(ejemplo.guardarArticuloLinea(art));

      art.setClaveLinea(22);
    //  System.out.println(ejemplo.actualizarArticuloLinea(art, "claveArticulo", "=", "12"));


    }

}
