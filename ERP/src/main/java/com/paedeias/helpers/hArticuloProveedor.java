/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

/**
 *
 * @author Luis
 */
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Articuloproveedor;
import com.paedeias.identidades.Articulos;
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


public class hArticuloProveedor {

public hArticuloProveedor() {}


   public List<Articulos> consultaArticulos(String campo)
    {
       List<Articulos> articulos = new ArrayList<Articulos>();
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
       
       
       if(CGlobalConfig.isWeb())  
       {
    
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT e.id as id,e.codigo as codigo,e.descripcion as descripcion,e.clasificacion as clasificacion,e.precioVenta as precioVenta,e.precioCompra as precioCompra,e.ultimoCosto as ultimoCosto,e.unidad as unidad,e.existencia as existencia,e.reservado as reservado,e.minimoPzas as minimoPzas,e.maximoPzas as maximoPzas,e.promPzas as promPzas,e.iva as iva,e.ieps as ieps,e.tipoEtiqueta as tipoEtiqueta,e.bloqueado as bloqueado,e.almacenDevoluciones as almacenDevoluciones,e.lineaPrincipal as lineaPrincipal,e.anticipos as anticipos,e.sinonimoPrincipal as sinonimoPrincipal,e.codigo2 as codigo2,e.precioVenta2 as precioVenta2,e.proveedor as proveedor,e.paretto as paretto,e.nuevo as nuevo FROM articuloproveedor a, articulos e WHERE "
                    +"a.sinonimo LIKE \'%"+campo+"%\' AND a.idArticulo = e.codigo;";
        
		
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloProveedor.php");
		  
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
       
       
       
       
       
       try {
            ResultSet conjuntoResultados;
             conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT e.id,e.codigo,e.descripcion,e.clasificacion,e.precioVenta,e.precioCompra,e.ultimoCosto,e.unidad,e.existencia,e.reservado,e.minimoPzas,e.maximoPzas,e.promPzas,e.iva,e.ieps,e.tipoEtiqueta,e.bloqueado,e.almacenDevoluciones,e.lineaPrincipal,e.anticipos,e.sinonimoPrincipal,e.codigo2,e.precioVenta2,e.proveedor,e.paretto,e.nuevo FROM articuloproveedor a, articulos e WHERE "
                    +"a.sinonimo LIKE \'%"+campo+"%\' AND a.idArticulo = e.codigo;");

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

 public List<Articuloproveedor> consultaArtProv(String campo, String match, String condicion)
    {
       List<Articuloproveedor> artprov = new ArrayList<Articuloproveedor>();
       // Conexion conexion = new Conexion();
       //         conexion.crearConexion();
       
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        
            if(match.equals("*")){
            query = "SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query ="SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArtProv()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloProveedor.php");
		  
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
           
	                Articuloproveedor linea = new Articuloproveedor(
                        Long.parseLong(atributos[1]),
                        atributos[2],
                        atributos[3],
                        atributos[4]
                        );

                artprov.add(linea);
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return artprov;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {

                Articuloproveedor linea = new Articuloproveedor(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString()
                        );

                artprov.add(linea);
            }
            return artprov;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
 
  public List<Articuloproveedor> consultaArtProv3(String campo, String match, String condicion, String campo2, String match2, String condicion2)
    {
       List<Articuloproveedor> artprov = new ArrayList<Articuloproveedor>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
          if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        
         if(match.equals("*")){
            query = "SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor a, comprasmayoreo c;";
            }
            else if(match.equals("="))
            {
            query = "SELECT codigoProveedor FROM comprasmayoreo c WHERE "+campo+" "+match+" \'"+condicion+"\';";           
            
            }else
            {
            query = "SELECT a.id,a.idArticulo,a.idProveedor,a.sinonimo FROM articuloproveedor a, comprasmayoreo c, proveedores p WHERE "+campo+" "+match+" \'%"+condicion+"%\' "
                    + "AND c.codigoProveedor = p.id AND p.nombreCorto = a.idProveedor  AND " + 
                    campo2+" "+match2+" \'%"+condicion2+"%\';";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
	datosEnv.put("token",match);	
        datosEnv.put("cond1",campo2);
        datosEnv.put("cond2",match2);
        datosEnv.put("cond3",condicion2);
        
		//Escribe el método
		
        datosEnv.put("metodo","consultaArtProv3()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloProveedor.php");
		  
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
           
                Articuloproveedor linea = new Articuloproveedor(
                        Long.parseLong(atributos[1]),
                        atributos[2],
                        atributos[3],
                        atributos[4]
                        );

                artprov.add(linea);          
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return artprov;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor a, comprasmayoreo c;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigoProveedor FROM comprasmayoreo c WHERE "+campo+" "+match+" \'"+condicion+"\';");
          //  System.out.println("SELECT codigoProveedor FROM comprasmayoreo c WHERE "+campo+" "+match+" \'"+condicion+"\';");
            while(conjuntoResultados.next())
            {

            String proveedor = conjuntoResultados.getObject(1).toString();
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT nombreCorto FROM proveedores WHERE id = \'"+proveedor+"\';");
          //  System.out.println("SELECT nombreCorto FROM proveedores WHERE id = \'"+proveedor+"\';");
                 while(conjuntoResultados.next())
            {

            String idProveedor = conjuntoResultados.getObject(1).toString();
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,idProveedor,sinonimo FROM articuloproveedor WHERE idProveedor = \'"+idProveedor+"\' "
                    + "AND "+campo2+" "+match2+" \'"+condicion2+"\';");            
          //  System.out.println("SELECT * FROM articuloproveedor WHERE idProveedor = \'"+idProveedor+"\' "
            //        + "AND "+campo2+" "+match2+" \'"+condicion2+"\';");
            
                             while(conjuntoResultados.next())
            {

                Articuloproveedor linea = new Articuloproveedor(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString()
                        );

                artprov.add(linea);          
            
            
            }
            
            
            
            }
            
            
            }
            
            
            
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.idArticulo,a.idProveedor,a.sinonimo FROM articuloproveedor a, comprasmayoreo c, proveedores p WHERE "+campo+" "+match+" \'%"+condicion+"%\' "
                    + "AND c.codigoProveedor = p.id AND p.nombreCorto = a.idProveedor  AND " + 
                    campo2+" "+match2+" \'%"+condicion2+"%\';");
            }

            while (conjuntoResultados.next()) {

                Articuloproveedor linea = new Articuloproveedor(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString()
                        );

                artprov.add(linea);
            }
            return artprov;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
 
 public List<Articuloproveedor> consultaArtProv2(String campo, String match, String condicion)
    {
       List<Articuloproveedor> artprov = new ArrayList<Articuloproveedor>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        
            if(match.equals("*")){
            query = "SELECT DISTINCT sinonimo as sinonimo FROM articuloproveedor;";
            }
            else if(match.equals("="))
            {
            query = "SELECT DISTINCT sinonimo as sinonimo  FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT DISTINCT sinonimo as sinonimo  FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaArtProv2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticuloProveedor.php");
		  
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
             Articuloproveedor linea = new Articuloproveedor();
                       linea.setSinonimo(atributos[1]);
                        

                artprov.add(linea);
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return artprov;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT DISTINCT sinonimo as sinonimo FROM articuloproveedor;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT DISTINCT sinonimo as sinonimo  FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT DISTINCT sinonimo as sinonimo  FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {

                Articuloproveedor linea = new Articuloproveedor();
                       linea.setSinonimo(conjuntoResultados.getObject(1).toString());
                        

                artprov.add(linea);
            }
            return artprov;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

       public int guardarArtProv(Articuloproveedor l)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO articuloproveedor (idArticulo,idProveedor,sinonimo) VALUES"
                    + "(\'"+l.getIdArticulo()+"\',\'"+l.getIdProveedor()+"\',\'"+l.getSinonimo()+"\');";
       //         Conexion conexion = new Conexion();
       //         conexion.crearConexion();
       
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

              public int guardarArtProvLast(Articuloproveedor l)
    {
       int ultimoInsertado = -1;
       String query = " INSERT INTO articuloproveedor (idArticulo,idProveedor,sinonimo) VALUES"
                    + "(\'"+l.getIdArticulo()+"\',\'"+l.getIdProveedor()+"\',\'"+l.getSinonimo()+"\');";
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

      public int borrarArtProvCompleto(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
      

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloproveedor WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloproveedor WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

        public int borrarArtProv(String id)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      tipo_respuesta = conexionweb.escribirEnWeb("DELETE  FROM articuloproveedor WHERE id =\'"+id+"\';");
      return tipo_respuesta;
       }

            CPrincipal.getConexion().moverDatos("DELETE  FROM articuloproveedor WHERE id =\'"+id+"\';");
            CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

        /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarArtProv(Articuloproveedor l)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE articuloproveedor SET "
             + "idArticulo = \'" +  l.getIdArticulo()+"\',"+
               "idProveedor = \'"+l.getIdProveedor()+"\',"+
               "sinonimo = \'"+l.getSinonimo()+"\'"+
               " WHERE id = \'"+l.getId()+"\'";

     //  System.out.println(query);
     
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

    public boolean actualizoProveedor(String nombreCorto, String nuevoNombre)
    {
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE articuloproveedor SET "+
               "idProveedor = \'"+nuevoNombre+"\'"+
               " WHERE idProveedor = \'"+nombreCorto+"\';";

       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      conexionweb.escribirEnWeb(query);
      return true;
       }
     
     //  System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return true;
    }    
    
    public int actualizarProveedor(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE articuloproveedor SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
    
        public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE articuloproveedor SET idArticulo = \'"+codigoNuevo+"\' WHERE idArticulo = \'"+codigoViejo+"\';";

       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      conexionweb.escribirEnWeb(query);
      return true;
       }
     
     //  System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return true;
    }    
    
  public static void main(String args[])
    {
       hArticuloProveedor ejemplo = new hArticuloProveedor();
       List<Articuloproveedor> lineas = ejemplo.consultaArtProv("id","=","1");
       int i = 0;
       while(i < lineas.size())
       {
           System.out.println(lineas.get(i).getSinonimo());
           i++;
       }
       System.out.println(ejemplo.borrarArtProv("2"));

       Articuloproveedor lin = new Articuloproveedor("1","2","AXJFJE09");
       System.out.println(ejemplo.guardarArtProv(lin));

      lin.setId(Long.parseLong("1"));
      lin.setIdArticulo("2");
      lin.setIdProveedor("1");
      lin.setSinonimo("XJSWIS09");
      System.out.println(ejemplo.actualizarArtProv(lin));


    }



}
