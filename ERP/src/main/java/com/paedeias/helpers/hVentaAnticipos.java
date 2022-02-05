/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.ReporteComisiones;
import com.paedeias.identidades.ReporteDAlmacen;
import com.paedeias.identidades.ReporteUtilidades;
import com.paedeias.identidades.Ventaanticipos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
public class hVentaAnticipos {
    
    public hVentaAnticipos()
    {}
    
   /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Ventaanticipos> consultaVentas(String campo, String match, String condicion)
    {
       List<Ventaanticipos> articulos = new ArrayList<Ventaanticipos>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    
                Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Integer.parseInt(atributos[5]),
                        Integer.parseInt(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        Double.parseDouble(atributos[8]),
                        atributos[9],
                        atributos[10],                        
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        atributos[14],
                        Double.parseDouble(atributos[15])
                        ); 
                
               
                articulo.setAnticipo(Long.valueOf(atributos[16]));

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(5).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(8).toString()),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),                        
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(15).toString())
                        ); 
                
               
                articulo.setAnticipo(Long.valueOf(conjuntoResultados.getObject(16).toString()));

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
    
     public List<Ventaanticipos> consultaVentas2(String campo, String match, String condicion)
    {
       List<Ventaanticipos> articulos = new ArrayList<Ventaanticipos>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentas2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    
                   Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Integer.parseInt(atributos[5]),
                        Integer.parseInt(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        Double.parseDouble(atributos[8]),
                        atributos[9],
                        atributos[10],                        
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        atributos[14],
                        Double.parseDouble(atributos[15])
                        ); 
                
               
                articulo.setAnticipo(Long.valueOf(atributos[16]));

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(5).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(8).toString()),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),                        
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(15).toString())
                        ); 
                
               
                articulo.setAnticipo(Long.valueOf(conjuntoResultados.getObject(16).toString()));

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
    
     public List<Ventaanticipos> consultaVentasPeriodo(String fecha2, String fecha1)
    {
       List<Ventaanticipos> articulos = new ArrayList<Ventaanticipos>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
           query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha2+" 00:00:00\' AND \'"+fecha1+" 23:59:59\' AND estado NOT LIKE 'Devuelta' AND estadoFactura NOT LIKE 'Facturada';";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    
                   Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Integer.parseInt(atributos[5]),
                        Integer.parseInt(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        Double.parseDouble(atributos[8]),
                        atributos[9],
                        atributos[10],                        
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        atributos[14],
                        Double.parseDouble(atributos[15])
                        ); 
                
               
                articulo.setAnticipo(Long.valueOf(atributos[16]));

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha2+" 00:00:00\' AND \'"+fecha1+" 23:59:59\' AND estado NOT LIKE 'Devuelta' AND estadoFactura NOT LIKE 'Facturada';");
          //  System.out.println("SELECT * FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND estado NOT LIKE 'Devuelta' AND estadoFactura NOT LIKE 'Facturada';");
                       
            while (conjuntoResultados.next()) {
                
                Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(5).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(8).toString()),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),                        
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(15).toString())
                        ); 
                articulo.setAnticipo(Long.parseLong(conjuntoResultados.getObject(16).toString()));
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
    
         public List<ReporteDAlmacen> consultaVentasFechasAlmacen(String fecha1, String fecha2)
    {
          List<ReporteDAlmacen> articulos = new ArrayList<ReporteDAlmacen>();
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
          
                                if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
           query = "SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,v.tipoDeVenta as tipoDeVenta,v.id as id,p.cantidad as cantidad FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta  ORDER BY v.tipoDeVenta ASC;";
           
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechasAlmacen()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    
                    ReporteDAlmacen articulo = new ReporteDAlmacen(); 
                
                articulo.setCantidad(atributos[5]);
                articulo.setCodigo(atributos[1]);
                articulo.setDescripcion(atributos[2]);
                articulo.setNumeracion(String.valueOf(u++));
                articulo.setReferencia(atributos[4]);
                articulo.setTipoDoc(atributos[3]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo,descripcionArticulo,v.tipoDeVenta,v.id,p.cantidad FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta  ORDER BY v.tipoDeVenta ASC;");
           

            int it1=0;
            while (conjuntoResultados.next()) {
                ReporteDAlmacen articulo = new ReporteDAlmacen(); 
                
                articulo.setCantidad(conjuntoResultados.getObject(5).toString());
                articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                articulo.setNumeracion(String.valueOf(it1++));
                articulo.setReferencia(conjuntoResultados.getObject(4).toString());
                articulo.setTipoDoc(conjuntoResultados.getObject(3).toString());
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

    public List<ReporteUtilidades> consultaVentasFechasUtilidadesN(String fecha1, String fecha2)
    {
          List<ReporteUtilidades> articulos = new ArrayList<ReporteUtilidades>();
     //   Conexion conexion = new Conexion();
     //           conexion.crearConexion();
          
                                          if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
           query =  "SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'aNuevo\';";
           
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechasUtilidadesN()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    
                    ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(atributos[5]);
                articulo.setCodigo(atributos[1]);
                articulo.setDescripcion(atributos[2]);
                articulo.setNumeracion(String.valueOf(u));
                articulo.setCosto(atributos[4]);
                articulo.setPrecio(atributos[3]);
                
                Double precio = Double.valueOf(atributos[3]) 
                         / (1 + CGlobalConfig.getIvaVenta());
                        
                
                articulo.setPrecio(String.valueOf(precio));
                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo,descripcionArticulo,p.conBeneficio,p.precioCompra,p.cantidad  FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'aNuevo\';");
           

            int it1=0;
            while (conjuntoResultados.next()) {
                ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(conjuntoResultados.getObject(5).toString());
                articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                articulo.setNumeracion(String.valueOf(it1++));
                articulo.setCosto(conjuntoResultados.getObject(4).toString());
                articulo.setPrecio(conjuntoResultados.getObject(3).toString());
                
                Double precio = Double.valueOf(conjuntoResultados.getObject(3).toString()) 
                         / (1 + CGlobalConfig.getIvaVenta());
                        
                
                articulo.setPrecio(String.valueOf(precio));
                
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
    
         public List<ReporteComisiones> consultaVentasPeriodoVendendorN(String fecha1, String fecha2,String vendedor)
    {
       List<ReporteComisiones> articulos = new ArrayList<ReporteComisiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
           query =  "SELECT id,idusuario,total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta = \'aNuevo\';";
    
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodoVendendorN()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(atributos[1]));
                articulo.setIdVendedor(Long.valueOf(atributos[2]));
                articulo.setImporteAN(Double.valueOf(atributos[3]));
                articulo.setCantidadAN(1);

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta = \'aNuevo\';");

                       
            while (conjuntoResultados.next()) {
                
                ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setIdVendedor(Long.valueOf(conjuntoResultados.getObject(2).toString()));
                articulo.setImporteAN(Double.valueOf(conjuntoResultados.getObject(3).toString()));
                articulo.setCantidadAN(1);

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
         
    public List<ReporteComisiones> consultaVentasPeriodoVendendorU(String fecha1, String fecha2,String vendedor)
    {
       List<ReporteComisiones> articulos = new ArrayList<ReporteComisiones>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
                                                        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
           query =  "SELECT id,idusuario,total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta = \'aUsado\';";
    
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodoVendendorU()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(atributos[1]));
                articulo.setIdVendedor(Long.valueOf(atributos[2]));
                articulo.setImporteAU(Double.valueOf(atributos[3]));
                articulo.setCantidadAU(1);

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta = \'aUsado\';");

                       
            while (conjuntoResultados.next()) {
                
                ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setIdVendedor(Long.valueOf(conjuntoResultados.getObject(2).toString()));
                articulo.setImporteAU(Double.valueOf(conjuntoResultados.getObject(3).toString()));
                articulo.setCantidadAU(1);

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
    
                public List<ReporteComisiones> consultaDevolucionesPeriodoVendendor(String fecha1, String fecha2,String vendedor)
    {
       List<ReporteComisiones> articulos = new ArrayList<ReporteComisiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            query = "SELECT d.total FROM devoluciones d, ventaanticipos v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND d.codigoVenta LIKE 'v%' AND d.idVenta = v.id AND v.idusuario = \'"+vendedor+"\';";
    
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaDevolucionesPeriodoVendendor()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	     ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setImporteD(Double.valueOf(atributos[1]));
                articulo.setIdVendedor(0);  
                articulo.setCantidadD(1);
                articulo.setIdVenta(0);
                
                
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
            
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT d.total FROM devoluciones d, ventaanticipos v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND d.codigoVenta LIKE 'v%' AND d.idVenta = v.id AND v.idusuario = \'"+vendedor+"\';");
            
            

                       
            while (conjuntoResultados.next()) {
                
                ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setImporteD(Double.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setIdVendedor(0);  
                articulo.setCantidadD(1);
                articulo.setIdVenta(0);
                
                
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
                
          public double consultaVentasTotalVendendor(String fecha1, String fecha2)
    {
       double total=0;
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT SUM(total) as total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+CConfiguracion.getId()+"\';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaVentasTotalVendendor()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           
          String s=recv.readLine();
          if(s.equals("~~°°°")) return 0;
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
               atributos[l] = "0";
           }
            total = Double.valueOf(atributos[1]);   
           }
           s=recv.readLine();
           }
           
           
           
            return total;
           
        }catch (Exception e){
           System.out.println(e.getMessage());
       }
           
           return 0;
        }
       
       
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT SUM(total) as total FROM ventaanticipos WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE \'Devuelta\' AND idusuario = \'"+CConfiguracion.getId()+"\';");   
            while (conjuntoResultados.next()) {
                if(conjuntoResultados.getObject(1) != null)
                total = Double.valueOf(conjuntoResultados.getObject(1).toString());
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return 0;
    }          
          
      public double consultaDevolucionesTotalVendedor(String fecha1, String fecha2)
    {
       double total=0;
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT SUM(d.total) as total FROM devoluciones d, ventaanticipos v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND d.codigoVenta LIKE 'v%' AND d.idVenta = v.id AND v.idusuario = \'"+CConfiguracion.getId()+"\';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaDevolucionesTotalVendedor()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
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
               atributos[l] = "0";
           }
            total = Double.valueOf(atributos[1]);   
           }
           s=recv.readLine();
           }
           
           
           
            return total;
           
        }catch (Exception e){
           System.out.println(e.getMessage());
       }
           
           return 0;
        }
       
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT SUM(d.total) as total FROM devoluciones d, ventaanticipos v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND d.codigoVenta LIKE 'v%' AND d.idVenta = v.id AND v.idusuario = \'"+CConfiguracion.getId()+"\';");
            while (conjuntoResultados.next()) {
                if(conjuntoResultados.getObject(1) != null)
                total = Double.valueOf(conjuntoResultados.getObject(1).toString());
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return 0;
    }       
    
    public List<ReporteUtilidades> consultaVentasFechasUtilidadesU(String fecha1, String fecha2)
    {
          List<ReporteUtilidades> articulos = new ArrayList<ReporteUtilidades>();
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
          
                                                                         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            query = "SELECT p.codigoArticulo,descripcionArticulo,p.conBeneficio,p.precioCompra,p.cantidad  FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'aUsado\';";
    
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechasUtilidadesU()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	    ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(atributos[5]);
                articulo.setCodigo(atributos[1]);
                articulo.setDescripcion(atributos[2]);
                articulo.setNumeracion(String.valueOf(u));
                articulo.setCosto(atributos[4]);
                
                Double precio = Double.valueOf(atributos[3]); 
                         

                
                articulo.setPrecio(String.valueOf(precio));
                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo,descripcionArticulo,p.conBeneficio,p.precioCompra,p.cantidad  FROM partidasventaanticipos p, ventaanticipos v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'aUsado\';");
           

            int it1=0;
            while (conjuntoResultados.next()) {
                ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(conjuntoResultados.getObject(5).toString());
                articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                articulo.setNumeracion(String.valueOf(it1++));
                articulo.setCosto(conjuntoResultados.getObject(4).toString());
                
                Double precio = Double.valueOf(conjuntoResultados.getObject(3).toString()); 
                         

                
                articulo.setPrecio(String.valueOf(precio));
                
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
         
        public int guardarVentas(Ventaanticipos a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO ventaanticipos (" + 
                    "idusuario,idcliente,idadministrador,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado,vale,iva,anticipo) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" + a.getArticulos() + "\',\'" + a.getPartidas() 
                    + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal() + "\',\'" + a.getObservaciones() 
                    + "\',\'" + a.getTipoDeVenta()  + "\',\'" + fecha.substring(0,19) + "\',\'" + a.getEstado() 
                    + "\',\'" + a.getVale() + "\',\'"+ a.getIva()+ "\',\'"+ a.getAnticipo() + "\');";
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
        
            public String crearQueryGuardarVentas(Ventaanticipos a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            String fecha = sdf.format(time);  
         
            String query = " INSERT INTO ventaanticipos (" + 
                    "idusuario,idcliente,idadministrador,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado,vale,iva,anticipo) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" + a.getArticulos() + "\',\'" + a.getPartidas() 
                    + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal() + "\',\'" + a.getObservaciones() 
                    + "\',\'" + a.getTipoDeVenta()  + "\',\'" + fecha.substring(0,19) + "\',\'" + a.getEstado() 
                    + "\',\'" + a.getVale() + "\',\'"+ a.getIva()+ "\',\'"+ a.getAnticipo() + "\');";
       //     Conexion conexion = new Conexion();
       //     conexion.crearConexion();
     
            return query;
    }
        
        
         public int borrarVentas(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
      
    if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }   
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

    public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE ventaanticipos SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
         
         
             /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarVentas(Ventaanticipos a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();



     String query = "UPDATE ventaanticipos SET idusuario = \'" +  a.getIdusuario()+"\',"+
                  "idcliente = \'" +   a.getIdcliente()+"\',"+
                  "idadministrador = \'" +   a.getIdadministrador()+"\',"+
                  "articulos = \'" +   a.getArticulos()+"\',"+
                  "partidas = \'" +   a.getPartidas()+"\',"+
                  "subtotal = \'" +   a.getSubtotal()+"\',"+
                  "total = \'" +    a.getTotal()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "tipoDeVenta = \'" +     a.getTipoDeVenta()+"\',"+
                  "estado = \'" +     a.getEstado()+"\',"+
                  "vale = \'" +     a.getVale()+"\',"+
                  "iva = \'" +     a.getIva()+"\',"+
                  "anticipo = \'" +     a.getAnticipo()+"\',"+
                  "fechaVenta = \'" +    fecha.substring(0,19)+"\'";
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
      conexionweb.escribirEnWeb(query);
      return resultado;
       }
             
   //    System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }
    
        public int actualizarVentasDev(Ventaanticipos a,String campoCondicion,String match,String condicion)
    {
             
        
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();



     String query = "UPDATE ventaanticipos SET idusuario = \'" +  a.getIdusuario()+"\',"+
                  "idcliente = \'" +   a.getIdcliente()+"\',"+
                  "idadministrador = \'" +   a.getIdadministrador()+"\',"+
                  "articulos = \'" +   a.getArticulos()+"\',"+
                  "partidas = \'" +   a.getPartidas()+"\',"+
                  "subtotal = \'" +   a.getSubtotal()+"\',"+
                  "total = \'" +    a.getTotal()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "tipoDeVenta = \'" +     a.getTipoDeVenta()+"\',"+
                  "estado = \'" +     a.getEstado()+"\',"+
                  "vale = \'" +     a.getVale()+"\',"+
                  "iva = \'" +     a.getIva()+"\',"+
                  "anticipo = \'" +     a.getAnticipo()+"\',"+
                  "fechaVenta = \'" +    a.getFechaVenta()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
   //    System.out.println(query);
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      conexionweb.escribirEnWeb(query);
      return resultado;
       }            
                   
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }
        
        public String crearQueryActualizarVentasDev(Ventaanticipos a,String campoCondicion,String match,String condicion)
    {
             

     String query = "UPDATE ventaanticipos SET idusuario = \'" +  a.getIdusuario()+"\',"+
                  "idcliente = \'" +   a.getIdcliente()+"\',"+
                  "idadministrador = \'" +   a.getIdadministrador()+"\',"+
                  "articulos = \'" +   a.getArticulos()+"\',"+
                  "partidas = \'" +   a.getPartidas()+"\',"+
                  "subtotal = \'" +   a.getSubtotal()+"\',"+
                  "total = \'" +    a.getTotal()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "tipoDeVenta = \'" +     a.getTipoDeVenta()+"\',"+
                  "estado = \'" +     a.getEstado()+"\',"+
                  "vale = \'" +     a.getVale()+"\',"+
                  "iva = \'" +     a.getIva()+"\',"+
                  "anticipo = \'" +     a.getAnticipo()+"\',"+
                  "fechaVenta = \'" +    a.getFechaVenta()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
   
     return query;
    }    
        
    public int actualizarCliente(String clienteNuevo,String clienteViejo)
    {
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //  conexion.crearConexion();
     String query = "UPDATE ventaanticipos SET idcliente = \'" + clienteNuevo+"\' WHERE idcliente = \'" + clienteViejo +"\'";
     
     if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
     conexionweb.escribirEnWeb(query);
      return resultado;
       }
     
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }        

    public int cambiarAFacturada(String id)
    {
     int resultado = 0;
 //    Conexion conexion = new Conexion();
 //             conexion.crearConexion();
              
              String query = "UPDATE ventaanticipos SET estadoFactura = \'Facturada\' WHERE id = \'"+id+"\';";
      
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
    
    public String crearQueryCambiarAFacturada(String id)
    {      
        String query = "UPDATE ventaanticipos SET estadoFactura = \'Facturada\' WHERE id = \'"+id+"\';";
        return query;
    }
    
        public int cambiarANoFacturada(String id)
    {
     int resultado = 0;
 //    Conexion conexion = new Conexion();
 //             conexion.crearConexion();
              
              String query = "UPDATE ventaanticipos SET estadoFactura = \'noFacturada\' WHERE id = \'"+id+"\';";
       
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
    
    public Ventaanticipos consultaUltimaVenta (String campo, String match, String condicion)
    {
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
        
                                                                                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos ORDER BY id DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 1;";
            }
                   
    
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUltimaVenta()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentaAnticipos.php");
		  
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
        //   atributos[1];
	      Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Integer.parseInt(atributos[5]),
                        Integer.parseInt(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        Double.parseDouble(atributos[8]),
                        atributos[9],
                        atributos[10],                        
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        atributos[14],
                        Double.parseDouble(atributos[15])
                        ); 
                       articulo.setAnticipo(Long.parseLong(atributos[16]));
		   //Arma el objeto y agrega a la lista en su caso
		   
	return articulo;	   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos ORDER BY id DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva,anticipo FROM ventaanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 1;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventaanticipos articulo = new Ventaanticipos(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(5).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(8).toString()),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),                        
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(15).toString())
                        ); 
                       articulo.setAnticipo(Long.parseLong(conjuntoResultados.getObject(16).toString()));
                       return articulo;
            }

        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
    
    
    
        public static void main(String args[])
    {
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
  
            
        //    System.out.println(fecha);
            
            
       hVentaAnticipos ejemplo = new hVentaAnticipos();
       List<Ventaanticipos> articulos = ejemplo.consultaVentas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFechaVenta());
           i++;
       } 
       System.out.println(ejemplo.borrarVentas("id", "=", "2"));

       Ventaanticipos art = new Ventaanticipos();
                     art.setArticulos(1);
                     art.setIdadministrador(Long.parseLong("2"));
                     art.setIdcliente(Long.parseLong("3"));
                     art.setIdusuario(Long.parseLong("4"));
                     art.setObservaciones("Observación 1");
                     art.setPartidas(9);
                     art.setSubtotal(Double.parseDouble("5"));
                     art.setTipoDeVenta("Venta 1");
                     art.setTotal(Double.parseDouble("100.00"));
                     

    //  System.out.println(ejemplo.guardarVentas(art));

      art.setPartidas(22);
    //  System.out.println(ejemplo.actualizarVentas(art, "id", "=", "3"));
        
        
    }
    
}
