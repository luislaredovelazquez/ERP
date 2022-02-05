/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Kardex;
import com.paedeias.vistas.VRealizar_Inventario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class hKardex {
    
    public hKardex(){}
    
     public List<Kardex> consultaKardex(String campo, String match, String condicion,String limite)
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
                    if(match.equals("*")){
            query = "SELECT k.id as id,k.idArticulo as idArticulo,k.articulo as articulo,k.movimiento as movimiento,k.fecha as fecha,k.entrada as entrada,k.salida as salida,k.existencias as existencias,k.reservados as reservados,k.precioVenta as precioVenta,k.responsable as responsable,k.refFerrari as refFerrari,k.modificacion as modificacion,k.ultimoCosto as ultimoCosto,k.noMov as noMov,k.almacenista as almacenista,k.vendidoEn as vendidoEn,k.responsable2 as responsable2,k.anticipos as anticipos,u.nombres as nombres, u.apellidoP as apellidoP, u.apellidoM as apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";";
            }
            else if(match.equals("="))
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id ORDER BY id DESC LIMIT  0,"+limite+";";
            }else
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardex()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 

                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id ORDER BY id DESC LIMIT  0,"+limite+";");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";");
            }

 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(20).toString()+" "+conjuntoResultados.getObject(21).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString()); 

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public List<Kardex> consultaKardex2()
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardex2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 

                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id;");
            
 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(20).toString()+" "+conjuntoResultados.getObject(21).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString()); 

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public int truncarKardex()
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //       conexion.crearConexion();
            CPrincipal.getConexion().moverDatos("TRUNCATE TABLE kardex;");
            CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
     
      public List<Kardex> consultaKardexArticulo(String campo, String match, String condicion)
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT k.id as id,k.idArticulo as idArticulo,k.articulo as articulo,k.movimiento as movimiento,k.fecha as fecha,k.entrada as entrada,k.salida as salida,k.existencias as existencias,k.reservados as reservados,k.precioVenta as precioVenta,k.responsable as responsable,k.refFerrari as refFerrari,k.modificacion as modificacion,k.ultimoCosto as ultimoCosto,k.noMov as noMov,k.almacenista as almacenista,k.vendidoEn as vendidoEn,k.responsable2 as responsable2,k.anticipos as anticipos FROM kardex k WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY k.id DESC;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardexArticulo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));


                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos FROM kardex k WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY k.id DESC;");

 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public List<Kardex> consultaKardexFecha(String campo, String match, String condicion,String fecha1, String fecha2)
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
       
                            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            if(match.equals("*")){
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;";
            }else
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardexFecha()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 

                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, usuarios u WHERE k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id AND k.fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id DESC;");
            }

 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(20).toString()+" "+conjuntoResultados.getObject(21).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString()); 
                         

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
      
      public List<Kardex> consultaKardexDescripcion(String condicion,String limite)
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, articulos a, usuarios u  WHERE a.descripcion LIKE "+" \'%"+condicion+"%\' AND k.articulo = a.codigo AND k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";";
        
	
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardexDescripcion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 

                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM  FROM kardex k, articulos a, usuarios u  WHERE a.descripcion LIKE "+" \'%"+condicion+"%\' AND k.articulo = a.codigo AND k.responsable = u.id ORDER BY id DESC LIMIT 0,"+limite+";");
            

 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(21).toString()+" "+conjuntoResultados.getObject(22).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString());  

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public List<Kardex> consultaKardexFechaDescripcion(String campo, String match, String condicion,String fecha1, String fecha2)
    {
       List<Kardex> kardexCompleto = new ArrayList<Kardex>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
         String query = "SELECT k.id as id,k.idArticulo as idArticulo,k.articulo as articulo,k.movimiento as movimiento,k.fecha as fecha,k.entrada as entrada,k.salida as salida,k.existencias as existencias,k.reservados as reservados,k.precioVenta as precioVenta,k.responsable as responsable,k.refFerrari as refFerrari,k.modificacion as modificacion,k.ultimoCosto as ultimoCosto,k.noMov as noMov,k.almacenista as almacenista,k.vendidoEn as vendidoEn,k.responsable2 as responsable2,k.anticipos as anticipos,u.nombres as nombres, u.apellidoP as apellidoP, u.apellidoM as apellidoM FROM kardex k, articulos a, usuarios u WHERE a.descripcion LIKE "+" '%"+condicion+"%' AND fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"' AND k.articulo = a.codigo AND k.responsable = u.id ORDER BY k.id DESC;";
	
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaKardexFechaDescripcion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
	     Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 

                kardexCompleto.add(obkardex);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return kardexCompleto;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k, articulos a, usuarios u WHERE a.descripcion LIKE "+" \'%"+condicion+"%\' AND fecha BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND k.articulo = a.codigo AND k.responsable = u.id ORDER BY id DESC;");

 
            
            while (conjuntoResultados.next()) {

                Kardex obkardex = new Kardex();
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(20).toString()+" "+conjuntoResultados.getObject(21).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString());  
                         

                kardexCompleto.add(obkardex);
            }
            return kardexCompleto;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
      public Kardex consultaUltimoRenglon(String campo, String match, String condicion)
    {
       Kardex obkardex = new Kardex();
       // Conexion conexion = new Conexion();
       //         conexion.crearConexion();
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            if(match.equals("*")){
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u WHERE k.responsable = u.id ORDER BY Fecha DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u WHERE  "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id ORDER BY id DESC LIMIT 1;";
            }else
            {
            query = "SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id  ORDER BY id DESC LIMIT 1;";
            }
	
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUltimoRenglon()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeKardex.php");
		  
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
           
                        obkardex.setId(Long.parseLong(atributos[1]));
                        obkardex.setIdArticulo(Long.parseLong(atributos[2]));
                        obkardex.setArticulo(atributos[3]);
                        obkardex.setMovimiento(atributos[4]);
                        obkardex.setFecha(atributos[5]);
                        obkardex.setEntrada(Integer.parseInt(atributos[6]));
                        obkardex.setSalida(Integer.parseInt(atributos[7]));
                        obkardex.setExistencias(Integer.parseInt(atributos[8]));
                        obkardex.setReservados(Integer.parseInt(atributos[9]));
                        obkardex.setPrecioVenta(Double.parseDouble(atributos[10]));
                        obkardex.setResponsable(atributos[11]);
                        obkardex.setRefFerrari(atributos[12]);
                        obkardex.setModificacion(atributos[13]);
                        obkardex.setUltimoCosto(Double.parseDouble(atributos[14]));
                        obkardex.setNoMov(atributos[15]);
                        obkardex.setAlmacenista(atributos[16]); 
                        obkardex.setVendidoEn(Double.parseDouble(atributos[17]));
                        obkardex.setResponsable2(atributos[18]);
                        obkardex.setAnticipos(Integer.parseInt(atributos[19]));
                        obkardex.setNombreCliente(atributos[20]+" "+atributos[21]
                                                  +" "+atributos[22]); 
  
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return obkardex;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u WHERE k.responsable = u.id ORDER BY Fecha DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u WHERE  "+campo+" "+match+" \'"+condicion+"\' AND k.responsable = u.id ORDER BY id DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT k.id,k.idArticulo,k.articulo,k.movimiento,k.fecha,k.entrada,k.salida,k.existencias,k.reservados,k.precioVenta,k.responsable,k.refFerrari,k.modificacion,k.ultimoCosto,k.noMov,k.almacenista,k.vendidoEn,k.responsable2,k.anticipos,u.nombres, u.apellidoP, u.apellidoM FROM kardex k,  usuarios u  WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND k.responsable = u.id  ORDER BY id DESC LIMIT 1;");
            }
            
            if(conjuntoResultados.next())
            {
            do {
                        obkardex.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        obkardex.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                        obkardex.setArticulo(conjuntoResultados.getObject(3).toString());
                        obkardex.setMovimiento(conjuntoResultados.getObject(4).toString());
                        obkardex.setFecha(conjuntoResultados.getObject(5).toString());
                        obkardex.setEntrada(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                        obkardex.setSalida(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                        obkardex.setExistencias(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                        obkardex.setReservados(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                        obkardex.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                        obkardex.setResponsable(conjuntoResultados.getObject(11).toString());
                        obkardex.setRefFerrari(conjuntoResultados.getObject(12).toString());
                        obkardex.setModificacion(conjuntoResultados.getObject(13).toString());
                        obkardex.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                        obkardex.setNoMov(conjuntoResultados.getObject(15).toString());
                        obkardex.setAlmacenista(conjuntoResultados.getObject(16).toString()); 
                        obkardex.setVendidoEn(Double.parseDouble(conjuntoResultados.getObject(17).toString()));
                        obkardex.setResponsable2(conjuntoResultados.getObject(18).toString());
                        obkardex.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                        obkardex.setNombreCliente(conjuntoResultados.getObject(20).toString()+" "+conjuntoResultados.getObject(21).toString()
                                                  +" "+conjuntoResultados.getObject(22).toString());  
                                  
            }while (conjuntoResultados.next());
            return obkardex;  
          }else 
             return null;
        }// catch (SQLException ex) {
       //     Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
       // } 
       catch (SQLException ex) {
            try {
                
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Rollback no efectuado");
                try {
                    CPrincipal.getConexion().getConexion().setAutoCommit(true);
                } catch (SQLException ex2) {
                    JOptionPane.showMessageDialog(null, "AutoCommit en false");
                    Logger.getLogger(VRealizar_Inventario.class.getName()).log(Level.SEVERE, null, ex2);
                }
                Logger.getLogger(VRealizar_Inventario.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, "Transacción no completada");
            Logger.getLogger(VRealizar_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
     
     
          
     public int guardarEnKardex(Kardex k)
    {
       java.util.Date utilDate = new java.util.Date(); //fecha actual
       long lnMilisegundos = utilDate.getTime();
       java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
        
       int filas_afectadas = 0;
       String query = "INSERT INTO kardex (idarticulo,articulo,movimiento,"
                    + "fecha,entrada,salida,existencias,reservados,precioVenta,"
                    + "responsable,refFerrari,modificacion,ultimoCosto,noMov,almacenista,vendidoEn,responsable2,anticipos"
                    +") VALUES"
                    + "(\'"+k.getIdArticulo()+
                    "\',\'"+k.getArticulo()+
                    "\',\'"+k.getMovimiento()+
                    "\',\'"+sqlTimestamp+
                    "\',\'"+k.getEntrada()+
                    "\',\'"+k.getSalida()+
                    "\',\'"+k.getExistencias()+
                    "\',\'"+k.getReservados()+
                    "\',\'"+k.getPrecioVenta()+
                    "\',\'"+k.getResponsable()+
                    "\',\'"+k.getRefFerrari()+
                    "\',\'"+k.getModificacion()+
                    "\',\'"+k.getUltimoCosto()+
                    "\',\'"+k.getNoMov()+
                    "\',\'"+k.getAlmacenista() +
                    "\',\'"+k.getVendidoEn()+
                    "\',\'"+k.getResponsable2()+
                    "\',\'"+k.getAnticipos()
                    +"');";
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
     
     public String crearQueryGuardarEnKardexCompleto(Kardex k)
    {
       java.util.Date utilDate = new java.util.Date(); //fecha actual
       long lnMilisegundos = utilDate.getTime();
       java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
        
       
       String query = "INSERT INTO kardex (idarticulo,articulo,movimiento,"
                    + "fecha,entrada,salida,existencias,reservados,precioVenta,"
                    + "responsable,refFerrari,modificacion,ultimoCosto,noMov,almacenista,vendidoEn,responsable2,anticipos"
                    +") VALUES"
                    + "(\'"+k.getIdArticulo()+
                    "\',\'"+k.getArticulo()+
                    "\',\'"+k.getMovimiento()+
                    "\',\'"+sqlTimestamp+
                    "\',\'"+k.getEntrada()+
                    "\',\'"+k.getSalida()+
                    "\',\'"+k.getExistencias()+
                    "\',\'"+k.getReservados()+
                    "\',\'"+k.getPrecioVenta()+
                    "\',\'"+k.getResponsable()+
                    "\',\'"+k.getRefFerrari()+
                    "\',\'"+k.getModificacion()+
                    "\',\'"+k.getUltimoCosto()+
                    "\',\'"+k.getNoMov()+
                    "\',\'"+k.getAlmacenista() +
                    "\',\'"+k.getVendidoEn()+
                    "\',\'"+k.getResponsable2()+
                    "\',\'"+k.getAnticipos()
                    +"');";
            //    Conexion conexion = new Conexion();
            //    conexion.crearConexion();
          
       return query;
    } 
     
     public String crearQueryGuardarEnKardex(Kardex k)
    {
       java.util.Date utilDate = new java.util.Date(); //fecha actual
       long lnMilisegundos = utilDate.getTime();
       java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
        
       String query = "INSERT INTO kardex (idarticulo,articulo,movimiento,"
                    + "fecha,entrada,salida,existencias,reservados,precioVenta,"
                    + "responsable,refFerrari,modificacion,ultimoCosto,noMov,almacenista,vendidoEn,responsable2,anticipos"
                    +") VALUES"
                    + "(\'"+k.getIdArticulo()+
                    "\',\'"+k.getArticulo()+
                    "\',"+k.getMovimiento()+
                    ",\'"+sqlTimestamp+
                    "\',\'"+k.getEntrada()+
                    "\',\'"+k.getSalida()+
                    "\',\'"+k.getExistencias()+
                    "\',\'"+k.getReservados()+
                    "\',\'"+k.getPrecioVenta()+
                    "\',\'"+k.getResponsable()+
                    "\',"+k.getRefFerrari()+
                    ","+k.getModificacion()+
                    ",\'"+k.getUltimoCosto()+
                    "\',\'"+k.getNoMov()+
                    "\',\'"+k.getAlmacenista() +
                    "\',\'"+k.getVendidoEn()+
                    "\',\'"+k.getResponsable2()+
                    "\',\'"+k.getAnticipos()
                    +"');";
            //    Conexion conexion = new Conexion();
            //    conexion.crearConexion();
           
       return query;
    } 

     public int borrarKardex(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM kardex WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM kardex WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM kardex WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM kardex WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
     
      public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE kardex SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
     String query = "UPDATE kardex SET articulo = \'"+codigoNuevo+"\' WHERE articulo = \'"+codigoViejo+"\';";

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
     
              /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarKardex(Kardex k,String campoCondicion,String match,String condicion)
    {
       java.util.Date utilDate = new java.util.Date(); //fecha actual
       long lnMilisegundos = utilDate.getTime();
       java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
       
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();

     String query = "UPDATE kardex SET "+
                  "idArticulo = \'" +  k.getIdArticulo()+"\',"+
                  "articulo = \'" +   k.getArticulo()+"\',"+
                  "movimiento = \'" +   k.getMovimiento()+"\',"+
                  "fecha = \'" +   sqlTimestamp+"\',"+
                  "entrada = \'" +   k.getEntrada()+"\',"+
                  "salida = \'" +   k.getSalida()+"\',"+
                  "existencias = \'" +   k.getExistencias()+"\',"+
                  "reservados = \'" +    k.getReservados()+"\',"+
                  "precioVenta = \'" +    k.getPrecioVenta()+"\',"+
                  "responsable = \'" +    k.getResponsable()+"\',"+
                  "refFerrari = \'" +     k.getRefFerrari()+"\',"+
                  "modificacion = \'" +    k.getModificacion()+"\',"+
                  "ultimoCosto = \'" +    k.getUltimoCosto()+"\',"+
                  "noMov = \'" +    k.getNoMov()+"\',"+
                  "almacenista = \'" +    k.getAlmacenista()+"\'";
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
   //    System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }
    
     public static void main(String args [])
    {
       hKardex ejemplo = new hKardex();
       List<Kardex> kardex = ejemplo.consultaKardex("id","=","1","5");
       int i = 0;
       while(i <kardex.size())
       {
           System.out.println(kardex.get(i).getArticulo());
           i++;
       } 
     //   System.out.println(ejemplo.borrarKardex("id", "=", "2"));

       Kardex kar = new Kardex();
       kar.setAlmacenista(String.valueOf(i));
       kar.setArticulo("ia8s");
       kar.setEntrada(1);
       kar.setExistencias(3);
       kar.setIdArticulo(3);
       kar.setModificacion(String.valueOf(7));
       kar.setMovimiento(String.valueOf(6));
       kar.setNoMov(String.valueOf(8));
       kar.setPrecioVenta(12.00);
       kar.setRefFerrari("3sd2");
       kar.setReservados(7);
       kar.setResponsable("22");
       kar.setSalida(2);
       kar.setUltimoCosto(55.20);

      System.out.println(ejemplo.guardarEnKardex(kar));

      
      kar.setEntrada(5);
      kar.setModificacion("modificacion");
      kar.setNoMov("93");
       System.out.println(ejemplo.actualizarKardex(kar,"id","=","2"));
    }
    
}
