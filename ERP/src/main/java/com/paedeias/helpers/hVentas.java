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
import com.paedeias.identidades.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
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
public class hVentas {
    
    public hVentas()
    {}
    
   /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Ventas> consultaVentas(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
	     Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    
    public List<Ventas> consultaVentas2(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
   
                Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Ventas> consultaVentasHistorial(String campo, String match, String condicion, String fecha1, String fecha2)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
                      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN "+fecha1+" AND "+fecha2+" ORDER BY id DESC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' AND  fechaVenta BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;";
            
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND fechaVenta BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;";
            
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasHistorial()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
   
                Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN "+fecha1+" AND "+fecha2+" ORDER BY id DESC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' AND  fechaVenta BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;");
            
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND fechaVenta BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;");
            
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Ventas> consultaVentasReservaciones(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
                             if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Reservación\' ORDER BY id DESC LIMIT 100;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasReservaciones()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
      Ventas articulo = new Ventas(
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
            
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Reservación\' ORDER BY id DESC LIMIT 100;");
            
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Ventas> consultaVentasEfectivo(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
                                    if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Efectivo\' ORDER BY id DESC LIMIT 100;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasEfectivo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Efectivo\' ORDER BY id DESC LIMIT 100;");
          
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Ventas> consultaVentasCredito(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                           if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Credito\' ORDER BY id DESC LIMIT 100;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasCredito()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Credito\' ORDER BY id DESC LIMIT 100;");
            
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Ventas> consultaVentasEfeCre(String campo, String match, String condicion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
                                                  if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Credito\' OR tipoDeVenta LIKE \'Efectivo\' ORDER BY id DESC LIMIT 100;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasEfeCre()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE tipoDeVenta LIKE \'Credito\' OR tipoDeVenta LIKE \'Efectivo\' ORDER BY id DESC LIMIT 100;");
   
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    public List<Partidas> consultaVentasFechas(String fecha1, String fecha2)
    {
  List<Partidas> articulos = new ArrayList<Partidas>();
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
  
                                                    if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT p.id as id,p.idVenta as idVenta,p.idArticulo as idArticulo,p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.precioCompra as precioCompra,p.precioVenta as precioVenta,p.tipoBeneficio as tipoBeneficio,p.beneficio as beneficio,p.conBeneficio as conBeneficio,p.cantidad as cantidad,p.subtotal as subtotal,a.existencia as existencia,a.almacenDevoluciones as almacenDevoluciones FROM partidas p, ventas v, articulos a WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;";
            
	
        String query2 = "";        
        query2 = "SELECT p.id as id,p.idVenta as idVenta,p.idArticulo as idArticulo,p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.precioCompra as precioCompra,p.precioVenta as precioVenta,p.tipoBeneficio as tipoBeneficio,p.beneficio as beneficio,p.conBeneficio as conBeneficio,p.cantidad as cantidad,p.subtotal as subtotal,a.existencia as existencia,a.almacenDevoluciones as almacenDevoluciones FROM partidasventaanticipos p, ventaanticipos v, articulos a WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;";      
		//Escribe tu query
		
	
        datosEnv.put("consulta",query);
        datosEnv.put("consulta2",query2);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                 Partidas articulo = new Partidas(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),                                       
                        atributos[4],
                        atributos[5],
                        Double.parseDouble(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        atributos[8],
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        Integer.parseInt(atributos[11]),        
                        Double.parseDouble(atributos[12])                   
                        ); 
                articulo.setExistenciaAlmacen(Integer.valueOf(atributos[13])+Integer.valueOf(atributos[14]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.id as id,p.idVenta as idVenta,p.idArticulo as idArticulo,p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.precioCompra as precioCompra,p.precioVenta as precioVenta,p.tipoBeneficio as tipoBeneficio,p.beneficio as beneficio,p.conBeneficio as conBeneficio,p.cantidad as cantidad,p.subtotal as subtotal,a.existencia as existencia,a.almacenDevoluciones as almacenDevoluciones FROM partidas p, ventas v, articulos a WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;");
           

            while (conjuntoResultados.next()) {
                Partidas articulo = new Partidas(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),                                       
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        conjuntoResultados.getObject(8).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(11).toString()),        
                        Double.parseDouble(conjuntoResultados.getObject(12).toString())                   
                        ); 
                articulo.setExistenciaAlmacen(Integer.valueOf(conjuntoResultados.getObject(13).toString())+Integer.valueOf(conjuntoResultados.getObject(14).toString()));
                articulos.add(articulo);
                
                
                
                
                
            }
            
              conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.id,p.idVenta,p.idArticulo,p.codigoArticulo,p.descripcionArticulo,p.precioCompra,p.precioVenta,p.tipoBeneficio,p.beneficio,p.conBeneficio,p.cantidad,p.subtotal,a.existencia,a.almacenDevoluciones FROM partidasventaanticipos p, ventaanticipos v, articulos a WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;");
           

            while (conjuntoResultados.next()) {
                Partidas articulo = new Partidas(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),                                       
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        conjuntoResultados.getObject(8).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(11).toString()),        
                        Double.parseDouble(conjuntoResultados.getObject(12).toString())                   
                        ); 
                articulo.setExistenciaAlmacen(Integer.valueOf(conjuntoResultados.getObject(13).toString())+Integer.valueOf(conjuntoResultados.getObject(14).toString()));
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
    public List<Partidas> consultaVentasFechaPorCliente(String fecha1, String fecha2, Long cliente)
    {
  List<Partidas> articulos = new ArrayList<Partidas>();
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
  
                                                      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT p.id as id,p.idVenta as idVenta,p.idArticulo as idArticulo,p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.precioCompra as precioCompra,p.precioVenta as precioVenta,p.tipoBeneficio as tipoBeneficio,p.beneficio as beneficio,p.conBeneficio as conBeneficio,p.cantidad as cantidad,p.subtotal as subtotal,a.existencia as existencia,a.almacenDevoluciones as almacenDevoluciones, a.clasificacion as clasificacion FROM partidas p, ventas v, articulos a WHERE v.idcliente = \'"+cliente+"\' AND v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechaPorCliente()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                Partidas articulo = new Partidas(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),                                       
                        atributos[4],
                        atributos[5],
                        Double.parseDouble(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        atributos[8],
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        Integer.parseInt(atributos[11]),        
                        Double.parseDouble(atributos[12])                   
                        ); 
                articulo.setExistenciaAlmacen(Integer.valueOf(atributos[13])+Integer.valueOf(atributos[14]));
                articulo.setClasificacion(atributos[15]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.id as id,p.idVenta as idVenta,p.idArticulo as idArticulo,p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.precioCompra as precioCompra,p.precioVenta as precioVenta,p.tipoBeneficio as tipoBeneficio,p.beneficio as beneficio,p.conBeneficio as conBeneficio,p.cantidad as cantidad,p.subtotal as subtotal,a.existencia as existencia,a.almacenDevoluciones as almacenDevoluciones, a.clasificacion as clasificacion FROM partidas p, ventas v, articulos a WHERE v.idcliente = \'"+cliente+"\' AND v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND p.codigoArticulo = a.codigo "
                                                      + "AND a.paretto = 0 ORDER BY a.codigo ASC;");
           

            while (conjuntoResultados.next()) {
                Partidas articulo = new Partidas(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),                                       
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        conjuntoResultados.getObject(8).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(11).toString()),        
                        Double.parseDouble(conjuntoResultados.getObject(12).toString())                   
                        ); 
                articulo.setExistenciaAlmacen(Integer.valueOf(conjuntoResultados.getObject(13).toString())+Integer.valueOf(conjuntoResultados.getObject(14).toString()));
                articulo.setClasificacion(conjuntoResultados.getObject(15).toString());
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
    public List<Partidas> consultaVentasClientes(String fecha1, String fecha2)
    {
  List<Partidas> articulos = new ArrayList<Partidas>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
  
                                                        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            query = "SELECT c.codigo as codigo,c.nombre as nombre,c.desc1 as desc1,c.desc2 as desc2,c.desc3 as desc3,c.desc4 as desc4,c.desc5 as desc5,v.total as total FROM clientes c, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.idcliente = c.id "
                                                      + "ORDER BY c.codigo ASC;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasClientes()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                Partidas articulo = new Partidas();
                articulo.setCodigoArticulo(atributos[1]);
                articulo.setDescripcionArticulo(atributos[2]);
                articulo.setTipoBeneficio(atributos[3]+"@"+atributos[4]+"@"+atributos[5]+"@"+atributos[6]+"@"+atributos[7]);
                articulo.setSubtotal(Double.parseDouble(atributos[8]));

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.codigo,c.nombre,c.desc1,c.desc2,c.desc3,c.desc4,c.desc5,v.total FROM clientes c, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.idcliente = c.id "
                                                      + "ORDER BY c.codigo ASC;");
           

            while (conjuntoResultados.next()) {
                Partidas articulo = new Partidas();
                articulo.setCodigoArticulo(conjuntoResultados.getObject(1).toString());
                articulo.setDescripcionArticulo(conjuntoResultados.getObject(2).toString());
                articulo.setTipoBeneficio(conjuntoResultados.getObject(3).toString()+"@"+conjuntoResultados.getObject(4).toString()+"@"+conjuntoResultados.getObject(5).toString()+"@"+conjuntoResultados.getObject(6).toString()+"@"+conjuntoResultados.getObject(7).toString());
                articulo.setSubtotal(Double.parseDouble(conjuntoResultados.getObject(8).toString()));

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
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
          
                                                                  if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
       query = "SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,v.tipoDeVenta as tipoDeVenta,v.id as id,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,v.tipoDeVenta as tipoDeVenta,v.id as id,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
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
    
    
    public List<ReporteUtilidades> consultaVentasFechasUtilidades(String fecha1, String fecha2)
    {
          List<ReporteUtilidades> articulos = new ArrayList<ReporteUtilidades>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
          
                                                                            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            query = "SELECT p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta NOT LIKE 'Reservación';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechasUtilidades()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo as codigoArticulo,p.descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta NOT LIKE 'Reservación';");
           

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
    
        public List<ReporteUtilidades> consultaVentasFechasUtilidadesR(String fecha1, String fecha2)
    {
          List<ReporteUtilidades> articulos = new ArrayList<ReporteUtilidades>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
                                                                                      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            query = "SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'Reservación\';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasFechasUtilidadesR()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoArticulo as codigoArticulo,descripcionArticulo as descripcionArticulo,p.conBeneficio as conBeneficio,p.precioCompra as precioCompra,p.cantidad as cantidad FROM partidas p, ventas v WHERE v.fechaVenta "
                                                      + "BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND v.id = p.idVenta AND v.tipoDeVenta LIKE \'Reservación\';");
           

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
    
        public List<Ventas> consultaVentasPeriodo(String fecha1, String fecha2)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                                                             if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND estado NOT LIKE 'Devuelta' AND estadoFactura LIKE 'noFacturada' AND tipoDeVenta NOT LIKE 'Reservación';";
            
		
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
               Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND estado NOT LIKE 'Devuelta' AND estadoFactura LIKE 'noFacturada' AND tipoDeVenta NOT LIKE 'Reservación';");

                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
    
     public List<ReporteComisiones> consultaVentasPeriodoVendendor(String fecha1, String fecha2,String vendedor)
    {
       List<ReporteComisiones> articulos = new ArrayList<ReporteComisiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                                                                    if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta NOT LIKE 'Reservación';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodoVendendor()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
               ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(atributos[1]));
                articulo.setIdVendedor(Long.valueOf(atributos[2]));
                articulo.setImporte(Double.valueOf(atributos[3]));
                articulo.setCantidad(1);

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta NOT LIKE 'Reservación';");

                       
            while (conjuntoResultados.next()) {
                
                ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setIdVendedor(Long.valueOf(conjuntoResultados.getObject(2).toString()));
                articulo.setImporte(Double.valueOf(conjuntoResultados.getObject(3).toString()));
                articulo.setCantidad(1);

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
     
       public List<ReporteComisiones> consultaVentasPeriodoVendendorReservacion(String fecha1, String fecha2,String vendedor)
    {
       List<ReporteComisiones> articulos = new ArrayList<ReporteComisiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                                                                           if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta LIKE 'Reservación';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodoVendendorReservacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
             ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(atributos[1]));
                articulo.setIdVendedor(Long.valueOf(atributos[2]));
                articulo.setImporteR(Double.valueOf(atributos[3]));
                articulo.setCantidadR(1);

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+vendedor+"\' AND tipoDeVenta LIKE 'Reservación';");

                       
            while (conjuntoResultados.next()) {
                
                ReporteComisiones articulo = new ReporteComisiones(); 
                articulo.setIdVenta(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setIdVendedor(Long.valueOf(conjuntoResultados.getObject(2).toString()));
                articulo.setImporteR(Double.valueOf(conjuntoResultados.getObject(3).toString()));
                articulo.setCantidadR(1);

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
            query = "SELECT d.total FROM devoluciones d, ventas v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
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
            
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT d.total FROM devoluciones d, ventas v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
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
     
       public double consultaVentasTotalVendendorReservacion(String fecha1, String fecha2)
    {
       double total=0;
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT SUM(total) as total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+CConfiguracion.getId()+"\' AND tipoDeVenta LIKE 'Reservación';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaVentasTotalVendendorReservacion()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT SUM(total) as total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+CConfiguracion.getId()+"\' AND tipoDeVenta LIKE 'Reservación';");     
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
            
       public double consultaVentasTotalVendendor(String fecha1, String fecha2)
    {
       double total=0;
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT SUM(total) as total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+CConfiguracion.getId()+"\' AND tipoDeVenta NOT LIKE 'Reservación';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaVentasTotalVendendor()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();
           if(s.equals("~~°°°"))return 0;
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
            e.printStackTrace();
       }
           
           return 0;
        }

       
       
       
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT SUM(total) as total FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND estado NOT LIKE 'Devuelta' AND idusuario = \'"+CConfiguracion.getId()+"\' AND tipoDeVenta NOT LIKE 'Reservación';");     
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
       
           public double consultaDevolucionesTotalVendendor(String fecha1, String fecha2)
    {
       double total = 0;
 //      Conexion conexion = new Conexion();
 //      conexion.crearConexion();
       
           if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "SELECT SUM(d.total) as total FROM devoluciones d, ventas v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
                    + "AND d.codigoVenta LIKE 'v%' AND d.idVenta = v.id AND v.idusuario = \'"+CConfiguracion.getId()+"\';";
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaDevolucionesTotalVendendor()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();
           if(s.equals("~~°°°"))return 0;
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
            
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT SUM(d.total) as total FROM devoluciones d, ventas v WHERE d.fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' "
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
       
       
       
     public List<Ventas> consultaVentasPeriodoReservacion(String fecha1, String fecha2)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                                                                                                                         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND tipoDeVenta = \'Reservación\' AND estado NOT LIKE 'Devuelta' AND estadoFactura LIKE 'noFacturada';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaVentasPeriodoReservacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE fechaVenta BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND tipoDeVenta = \'Reservación\' AND estado NOT LIKE 'Devuelta' AND estadoFactura LIKE 'noFacturada';");

                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
     
       public List<Ventas> consultaReservacion(String reservacion)
    {
       List<Ventas> articulos = new ArrayList<Ventas>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
                                                                                                                                if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query =  "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE vale = \'"+reservacion+"\' AND tipoDeVenta = \'Reservación\';";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReservacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
       
                 Ventas articulo = new Ventas(
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE vale = \'"+reservacion+"\' AND tipoDeVenta = \'Reservación\';");

                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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

        
        public int guardarVentas(Ventas a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO ventas (" + 
                    "idusuario,idcliente,idadministrador,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado,vale,iva) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" + a.getArticulos() + "\',\'" + a.getPartidas() 
                    + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal() + "\',\'" + a.getObservaciones() 
                    + "\',\'" + a.getTipoDeVenta()  + "\',\'" + fecha.substring(0,19) + "\',\'" + a.getEstado() + "\',\'" + a.getVale() + "\',\'"+ a.getIva() + "');";
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
        
     public String crearQueryGuardar(Ventas a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            String fecha = sdf.format(time);  
         
            String query = " INSERT INTO ventas (" + 
                    "idusuario,idcliente,idadministrador,articulos,partidas,subtotal," +
                    "total,observaciones,tipoDeVenta,fechaVenta,estado,vale,iva) VALUES" + "(\'" + a.getIdusuario() + "\',\'" + a.getIdcliente() 
                    + "\',\'" + a.getIdadministrador() + "\',\'" + a.getArticulos() + "\',\'" + a.getPartidas() 
                    + "\',\'" + a.getSubtotal() + "\',\'" + a.getTotal() + "\',\'" + a.getObservaciones() 
                    + "\',\'" + a.getTipoDeVenta()  + "\',\'" + fecha.substring(0,19) + "\',\'" + a.getEstado() + "\',\'" + a.getVale() + "\',\'"+ a.getIva() + "');";
      

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
     query = "DELETE  FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
         
    public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE ventas SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
    public int actualizarVentas(Ventas a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE ventas SET idusuario = \'" +  a.getIdusuario()+"\',"+
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
                  "fechaVenta = \'" +    fecha.substring(0,19)+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
     //  System.out.println(query);
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
    
                 /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarVentasDev(Ventas a,String campoCondicion,String match,String condicion)
    { 
        
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();



     String query = "UPDATE ventas SET idusuario = \'" +  a.getIdusuario()+"\',"+
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
                  "fechaVenta = \'" +    a.getFechaVenta()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
  //     System.out.println(query);
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
    
           
        public String crearQueryActualizarVentasDev(Ventas a,String campoCondicion,String match,String condicion)
    { 

     String query = "UPDATE ventas SET idusuario = \'" +  a.getIdusuario()+"\',"+
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

    public int cambiarAFacturada(String id)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
              
              String query = "UPDATE ventas SET estadoFactura = \'Facturada\' WHERE id = \'"+id+"\';";
              
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
             
              String query = "UPDATE ventas SET estadoFactura = \'Facturada\' WHERE id = \'"+id+"\';";
              return query;
    }
    
     public int cambiarANoFacturada(String id)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
              
              String query = "UPDATE ventas SET estadoFactura = \'noFacturada\' WHERE id = \'"+id+"\';";
              
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
    
        public int cambiarAFacturadaR(String id)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
              
              String query = "UPDATE ventas SET estadoFactura = \'Facturada\' WHERE tipoDeVenta = \'Reservación\' AND vale = \'"+id+"\';";
       
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
        
    public String crearQueryCambiarAFacturadaR(String id)
    {
              
        String query = "UPDATE ventas SET estadoFactura = \'Facturada\' WHERE tipoDeVenta = \'Reservación\' AND vale = \'"+id+"\';";
        return query;
    }    
        
   public int actualizarCliente(String clienteNuevo,String clienteViejo)
    {
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //  conexion.crearConexion();
     String query = "UPDATE ventas SET idcliente = \'" + clienteNuevo+"\' WHERE idcliente = \'" + clienteViejo +"\'";
     
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
    
    public Ventas consultaUltimaVenta (String campo, String match, String condicion)
    {
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
        
     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas ORDER BY id DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;";
            }else
            {
            query = "SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 1;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeVentas.php");
		  
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
          // atributos[1];
        Ventas articulo = new Ventas(
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

                       return articulo;
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas ORDER BY id DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idusuario,idcliente,idadministrador,articulos,partidas,subtotal,total,observaciones,tipoDeVenta,fechaVenta,estado,estadoFactura,vale,iva FROM ventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 1;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Ventas articulo = new Ventas(
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
            
            
       hVentas ejemplo = new hVentas();
       List<Ventas> articulos = ejemplo.consultaVentas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFechaVenta());
           i++;
       } 
       System.out.println(ejemplo.borrarVentas("id", "=", "2"));

       Ventas art = new Ventas();
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
