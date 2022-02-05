/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Reimpresiones;
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
 * @author ALL
 */
public class hReimpresiones {

    public hReimpresiones() {
    }
    
      public List<Reimpresiones> consultaPedidos(String campo, String match, String condicion)
    {
       List<Reimpresiones> articulos = new ArrayList<Reimpresiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
                    if(match.equals("*")){
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPedidos()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeReimpresiones.php");
		  
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
           
	   Reimpresiones articulo = new Reimpresiones(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setUsuario(atributos[2]);
                articulo.setFolio(atributos[3]);
                articulo.setFecha(atributos[4]);
                articulo.setImporte(atributos[5]);
                articulo.setVendedor(atributos[6]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Reimpresiones articulo = new Reimpresiones(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setUsuario(conjuntoResultados.getObject(2).toString());
                articulo.setFolio(conjuntoResultados.getObject(3).toString());
                articulo.setFecha(conjuntoResultados.getObject(4).toString());
                articulo.setImporte(conjuntoResultados.getObject(5).toString());
                articulo.setVendedor(conjuntoResultados.getObject(6).toString());
                articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hReimpresiones.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
      
       public List<Reimpresiones> consultaPedidos2(String campo, String match, String condicion)
    {
       List<Reimpresiones> articulos = new ArrayList<Reimpresiones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPedidos2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeReimpresiones.php");
		  
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
           
	   Reimpresiones articulo = new Reimpresiones(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setUsuario(atributos[2]);
                articulo.setFolio(atributos[3]);
                articulo.setFecha(atributos[4]);
                articulo.setImporte(atributos[5]);
                articulo.setVendedor(atributos[6]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,usuario,folio,fecha,importe,vendedor FROM reimpresiones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Reimpresiones articulo = new Reimpresiones(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setUsuario(conjuntoResultados.getObject(2).toString());
                articulo.setFolio(conjuntoResultados.getObject(3).toString());
                articulo.setFecha(conjuntoResultados.getObject(4).toString());
                articulo.setImporte(conjuntoResultados.getObject(5).toString());
                articulo.setVendedor(conjuntoResultados.getObject(6).toString());
                articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hReimpresiones.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
      
      public int guardarReimpresiones(Reimpresiones a)
    {
        
            int ultimoInsertado=-1;
            String query = " INSERT INTO reimpresiones (" + 
                      "usuario,"
                    + "folio,"
                    + "fecha,"
                    + "importe,"
                    + "vendedor) VALUES" + "(\'" 
                    + a.getUsuario() + "\',\'" 
                    + a.getFolio() + "\',\'" 
                    + a.getFecha() + "\',\'" 
                    + a.getImporte() + "\',\'" 
                    + a.getVendedor() + "');";
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
    
}
