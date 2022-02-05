/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Devoluciones;
import com.paedeias.identidades.ReporteDAlmacen;
import com.paedeias.identidades.ReporteUtilidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
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
public class hDevoluciones {
    
    public hDevoluciones()
    {}
    
   /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Devoluciones> consultaVentas(String campo, String match, String condicion)
    {
       List<Devoluciones> articulos = new ArrayList<Devoluciones>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
           String query = "";
           if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeDevoluciones.php");
		  
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
           
	    Devoluciones articulo = new Devoluciones(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Long.parseLong(atributos[5]),
                        atributos[6],
                        Integer.parseInt(atributos[7]),
                        Integer.parseInt(atributos[8]),
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        atributos[11],
                        atributos[12],                        
                        atributos[13],
                        atributos[14],
                        atributos[15]
                        ); 

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Devoluciones articulo = new Devoluciones(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Long.parseLong(conjuntoResultados.getObject(5).toString()),
                        conjuntoResultados.getObject(6).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(7).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(8).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),                        
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        conjuntoResultados.getObject(15).toString()
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
    
    public List<Devoluciones> consultaVentas2(String campo, String match, String condicion)
    {
       List<Devoluciones> articulos = new ArrayList<Devoluciones>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
           String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeDevoluciones.php");
		  
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
           
	    Devoluciones articulo = new Devoluciones(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Long.parseLong(atributos[5]),
                        atributos[6],
                        Integer.parseInt(atributos[7]),
                        Integer.parseInt(atributos[8]),
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        atributos[11],
                        atributos[12],                        
                        atributos[13],
                        atributos[14],
                        atributos[15]
                        ); 

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Devoluciones articulo = new Devoluciones(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Long.parseLong(conjuntoResultados.getObject(5).toString()),
                        conjuntoResultados.getObject(6).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(7).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(8).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),                        
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        conjuntoResultados.getObject(15).toString()
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
    
    /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Devoluciones> consultaVentasCatalogo(String campo, String match, String condicion)
    {
       List<Devoluciones> articulos = new ArrayList<Devoluciones>();
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
              if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
           String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasCatalogo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeDevoluciones.php");
		  
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
           
	    Devoluciones articulo = new Devoluciones(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),
                        Long.parseLong(atributos[4]),
                        Long.parseLong(atributos[5]),
                        atributos[6],
                        Integer.parseInt(atributos[7]),
                        Integer.parseInt(atributos[8]),
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        atributos[11],
                        atributos[12],                        
                        atributos[13],
                        atributos[14],
                        atributos[15]
                        ); 

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFacturada FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Devoluciones articulo = new Devoluciones(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),
                        Long.parseLong(conjuntoResultados.getObject(4).toString()),
                        Long.parseLong(conjuntoResultados.getObject(5).toString()),
                        conjuntoResultados.getObject(6).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(7).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(8).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),                        
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        conjuntoResultados.getObject(15).toString()
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
    
        public int guardarVentas(Devoluciones a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         //   System.out.println("a"+a.getIdcliente());
            int ultimoInsertado=-1;
            String query = " INSERT INTO devoluciones (" + 
                    "idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" +  a.getIdVenta() + "\',\'" +  a.getCodigoVenta() + "\',\'" +
                    a.getArticulos() + "\',\'" + a.getPartidas() + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal()
                    + "\',\'" + a.getObservaciones() + "\',\'" + a.getTipoDeVenta() + "\',\'" + fecha.substring(0,19) + "\',\'"
                    + a.getEstado() + "');";
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
        
      public String crearQueryGuardarVentas(Devoluciones a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            
            String query = " INSERT INTO devoluciones (" + 
                    "idusuario,idcliente,idadministrador,idVenta,codigoVenta,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" +  a.getIdVenta() + "\',\'" +  a.getCodigoVenta() + "\',\'" +
                    a.getArticulos() + "\',\'" + a.getPartidas() + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal()
                    + "\',\'" + a.getObservaciones() + "\',\'" + a.getTipoDeVenta() + "\',\'" + fecha.substring(0,19) + "\',\'"
                    + a.getEstado() + "');";

            return query;
    }     
        
        
        
         public List<ReporteDAlmacen> consultaVentasFechasAlmacen(String fecha1, String fecha2)
    {
          List<ReporteDAlmacen> articulos = new ArrayList<ReporteDAlmacen>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
          
                        if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
           String query = "";
           query = "SELECT p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,v.tipoDeVenta as tipoDeVenta,v.id as id,p.cantidad as cantidad FROM partidasdevoluciones p, devoluciones v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta;";
		
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeDevoluciones.php");
		  
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
                ReporteDAlmacen articulo = new ReporteDAlmacen(); 
                
                articulo.setCantidad(atributos[5]);
                articulo.setCodigo(atributos[1]);
                articulo.setDescripcion(atributos[2]);
                articulo.setNumeracion(String.valueOf(u++));
                articulo.setReferencia(atributos[4]);
                articulo.setTipoDoc("Devolución");
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo,p.descripcionArticulo,v.tipoDeVenta,v.id,p.cantidad FROM partidasdevoluciones p, devoluciones v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta;");
           
            
            int it1=0;
            while (conjuntoResultados.next()) {
                ReporteDAlmacen articulo = new ReporteDAlmacen(); 
                
                articulo.setCantidad(conjuntoResultados.getObject(5).toString());
                articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                articulo.setNumeracion(String.valueOf(it1++));
                articulo.setReferencia(conjuntoResultados.getObject(4).toString());
                articulo.setTipoDoc("Devolución");
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
         
     public List<ReporteUtilidades> consultaVentasUtilidades(String fecha1, String fecha2)
    {
          List<ReporteUtilidades> articulos = new ArrayList<ReporteUtilidades>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
          
        if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
           String query = "";
           query = "SELECT p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidasdevoluciones p, devoluciones v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasUtilidades()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeDevoluciones.php");
		  
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
                
                ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(atributos[5]);
                articulo.setCodigo(atributos[1]);
                articulo.setDescripcion(atributos[2]);
                articulo.setNumeracion(String.valueOf(u));
                articulo.setCosto(atributos[4]);
                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo,p.descripcionArticulo,p.conBeneficio,p.precioCompra,p.cantidad FROM partidasdevoluciones p, devoluciones v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta;");
           

            int it1=0;
            while (conjuntoResultados.next()) {
                ReporteUtilidades articulo = new ReporteUtilidades(); 
                
                articulo.setCantidad(conjuntoResultados.getObject(5).toString());
                articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                articulo.setNumeracion(String.valueOf(it1++));
                articulo.setCosto(conjuntoResultados.getObject(4).toString());
                
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
         
        
         public int borrarVentas(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM devoluciones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM devoluciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

     public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE devoluciones SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
    public int actualizarVentas(Devoluciones a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();



     String query = "UPDATE devoluciones SET idusuario = \'" +  a.getIdusuario()+"\',"+
                  "idcliente = \'" +   a.getIdcliente()+"\',"+
                  "idadministrador = \'" +   a.getIdadministrador()+"\',"+
                  "idVenta = \'" +   a.getIdVenta()+"\',"+
                  "codigoVenta = \'" +   a.getCodigoVenta()+"\',"+
                  "articulos = \'" +   a.getArticulos()+"\',"+
                  "partidas = \'" +   a.getPartidas()+"\',"+
                  "subtotal = \'" +   a.getSubtotal()+"\',"+
                  "total = \'" +    a.getTotal()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "tipoDeVenta = \'" +     a.getTipoDeVenta()+"\',"+
                  "estado = \'" +     a.getEstado()+"\',"+
                  "fechaVenta = \'" +    fecha.substring(0,19)+"\'";
     
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

        public int actualizarAlmacenista(long devolucion,long almacenista)
    {
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //  conexion.crearConexion();
     String query = "UPDATE devoluciones SET idusuario = \'" +  almacenista+"\' WHERE id = \'" + devolucion +"\'";
     
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
        
     public int actualizarCliente(String clienteNuevo,String clienteViejo)
    {
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //  conexion.crearConexion();
     String query = "UPDATE devoluciones SET idcliente = \'" + clienteNuevo+"\' WHERE idcliente = \'" + clienteViejo +"\'";
     
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
        
     public int cambiarAFacturada(String id)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
              
              String query = "UPDATE devoluciones SET estadoFacturada = \'Facturada\' WHERE id = \'"+id+"\';";
       
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
        String query = "UPDATE devoluciones SET estadoFacturada = \'Facturada\' WHERE id = \'"+id+"\';";
        return query;
    }
     
     public int cambiarANoFacturada(String id)
    {
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();
              
              String query = "UPDATE devoluciones SET estadoFacturada = \'noFacturada\' WHERE id = \'"+id+"\';";
              
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
    
        public static void main(String args[])
    {
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
  
            
        //    System.out.println(fecha);
            
            
       hDevoluciones ejemplo = new hDevoluciones();
       List<Devoluciones> articulos = ejemplo.consultaVentas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFechaVenta());
           i++;
       } 
       System.out.println(ejemplo.borrarVentas("id", "=", "2"));

       Devoluciones art = new Devoluciones();
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
