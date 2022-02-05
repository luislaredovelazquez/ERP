/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

/**
 *
 * @author ALL
 */
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Cuentasporpagar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

public class hCuentasPorPagar {
    
    public hCuentasPorPagar(){}
    
         /*Permite consultar todos los datos, o algunos datos dependiendo de la condici?n*/
    public List<Cuentasporpagar> consultaCuentasPorPagar(String campo, String match, String condicion)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        
            if(match.equals("*")){
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id ASC LIMIT 1000;";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaCuentasPorPagar()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("0000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id ASC LIMIT 1000;");
            }
            
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("0000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
    
     public List<Cuentasporpagar> consultaCuentasPorPagar2(String campo, String match, String condicion)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
          if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
             String query = "";
        
            if(match.equals("*")){
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaCuentasPorPagar2()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("0000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("0000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
    
    
        public List<Cuentasporpagar> consultaTodo(String fecha1, String fecha2, String tipo)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
             String query = "";
        
            if(tipo.equals("2"))
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE fechaExpiracion BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id ASC;";
            else
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE fechaExpiracion BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND pagado = \'"+tipo+"\' ORDER BY id ASC;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaTodo()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            if(tipo.equals("2"))
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE fechaExpiracion BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' ORDER BY id ASC;");
            else
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE fechaExpiracion BETWEEN \'"+fecha1+"\' AND \'"+fecha2+"\' AND pagado = \'"+tipo+"\' ORDER BY id ASC;");
            
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
    
    
             /*Permite consultar todos los datos, o algunos datos dependiendo de la condici?n*/
    public List<Cuentasporpagar> consultaCuentasPorPagarFactura(String campo)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
                        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
             String query = "";
            query = "SELECT c.id as id,c.compra as compra,c.fecha as fecha,c.pagado as pagado,c.observacion as observacion,c.saldo as saldo,c.contraRecibo as contraRecibo,c.proveedor as proveedor,c.fechaExpiracion as fechaExpiracion,c.tn as tn FROM cuentasporpagar c, comprasmayoreo m WHERE m.factura = \'"+campo+"\' AND m.id = c.compra ORDER BY id DESC LIMIT 1000;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaCuentasPorPagarFactura()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.id,c.compra,c.fecha,c.pagado,c.observacion,c.saldo,c.contraRecibo,c.proveedor,c.fechaExpiracion,c.tn FROM cuentasporpagar c, comprasmayoreo m WHERE m.factura = \'"+campo+"\' AND m.id = c.compra ORDER BY id DESC LIMIT 1000;");
            
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
          /*Permite consultar todos los datos, o algunos datos dependiendo de la condici?n*/
    public List<Cuentasporpagar> consultaCuentasPorPagarPagado(String campo, String match, String condicion)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
             String query = "";
            if(match.equals("*")){
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE  Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaCuentasPorPagarPagado()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE  Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
    
          /*Permite consultar todos los datos, o algunos datos dependiendo de la condici?n*/
    public List<Cuentasporpagar> consultaCuentasPorPagarNoPagado(String campo, String match, String condicion)
    {
       List<Cuentasporpagar> articulos = new ArrayList<Cuentasporpagar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
             // conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
             String query = "";
            if(match.equals("*")){
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND  Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el m?todo
		
        datosEnv.put("metodo","consultaCuentasPorPagarNoPagado()");
		
		
		//Escribe el m?todo
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorPagar.php");
		  
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
           String[] objetos = s.split("같�");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
	   for(int l=0; l<atributos.length; l++)
           {
           if(atributos[l].equals(" "))
               atributos[l] = "";
           }
		   
		   //Arma el objeto y agrega a la lista en su caso
           
               Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setFecha(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setContraRecibo(atributos[7]);           
                articulo.setProveedor(Long.parseLong(atributos[8]));
                if(atributos[9].equals(""))
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(atributos[9]);
                articulo.setTn(atributos[10]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,fecha,pagado,observacion,saldo,contraRecibo,proveedor,fechaExpiracion,tn FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND  Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporpagar articulo = new Cuentasporpagar(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setContraRecibo(conjuntoResultados.getObject(7).toString());           
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                if(conjuntoResultados.getObject(9)==null)
                articulo.setFechaExpiracion("000-00-00");    
                else
                articulo.setFechaExpiracion(conjuntoResultados.getObject(9).toString());
                articulo.setTn(conjuntoResultados.getObject(10).toString());
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
    
     public int pagar(int pagado,String contrarecibo,String campoCondicion,String match,String condicion)
    {       
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE cuentasporpagar SET pagado = \'" + pagado +"\', contraRecibo = \'"+contrarecibo+"\'";
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
    
    public String generarFecha()
    {    
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time); 
            return fecha;
    }
    
    
             public int guardarCuentasPorPagar(Cuentasporpagar a)
    {
            int ultimoInsertado=-1;
            String query = " INSERT INTO cuentasporpagar (" + 
                      "compra,"
                    + "fecha,"
                    + "pagado,"
                    + "observacion,"
                    + "saldo,"
                    + "contraRecibo,"
                    + "proveedor,"
                    + "fechaExpiracion) VALUES" + "(\'" 
                    + a.getCompra() + "\',\'" 
                    + generarFecha().substring(0,19) + "\',\'" 
                    + a.getPagado() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getSaldo() + "\',\'" 
                    + a.getContraRecibo() + "\',\'" 
                    + a.getProveedor() + "\',\'"
                    + a.getFechaExpiracion() + "');";
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
             
         public String crearQueryguardarCuentasPorPagar(Cuentasporpagar a)
    {
            
            String query = " INSERT INTO cuentasporpagar (" + 
                      "compra,"
                    + "fecha,"
                    + "pagado,"
                    + "observacion,"
                    + "saldo,"
                    + "contraRecibo,"
                    + "proveedor,"
                    + "fechaExpiracion) VALUES" + "(\'" 
                    + a.getCompra() + "\',\'" 
                    + generarFecha().substring(0,19) + "\',\'" 
                    + a.getPagado() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getSaldo() + "\',\'" 
                    + a.getContraRecibo() + "\',\'" 
                    + a.getProveedor() + "\',\'"
                    + a.getFechaExpiracion() + "');";
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();
            
        return query;
    }         
             
             
     public int guardarCuentasPorPagarTN(Cuentasporpagar a)
    {
            int ultimoInsertado=-1;
            String query = " INSERT INTO cuentasporpagar (" + 
                      "compra,"
                    + "fecha,"
                    + "pagado,"
                    + "observacion,"
                    + "saldo,"
                    + "contraRecibo,"
                    + "proveedor,"
                    + "fechaExpiracion,"
                    + "tn) VALUES" + "(\'" 
                    + a.getCompra() + "\',\'" 
                    + generarFecha().substring(0,19) + "\',\'" 
                    + a.getPagado() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getSaldo() + "\',\'" 
                    + a.getContraRecibo() + "\',\'" 
                    + a.getProveedor() + "\',\'"
                    + a.getFechaExpiracion() + "\',\'"
                    + a.getTn() + "');";
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
             
              public int actualizarCuentasPorPagar(Cuentasporpagar a,String campoCondicion,String match,String condicion)
    {
       
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();



     String query = "UPDATE cuentasporpagar SET compra = \'" +  a.getCompra()+"\',"+
                  "fecha = \'" +  generarFecha().substring(0,19) +"\',"+
                  "pagado = \'" +   a.getPagado()+"\',"+
                  "observacion = \'" +   a.getObservacion()+"\',"+
                  "saldo = \'" +   a.getSaldo()+"\',"+
                  "proveedor = \'" +   a.getProveedor()+"\',"+
                  "fechaExpiracion = \'" +   a.getFechaExpiracion()+"\',"+
                  "tn = \'" +   a.getTn()+"\',"+
                  "contraRecibo = \'" +a.getContraRecibo() +"\'";
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
              
      public String crearQueryActualizarCuentasPorPagar(Cuentasporpagar a,String campoCondicion,String match,String condicion)
    {
       

     String query = "UPDATE cuentasporpagar SET compra = \'" +  a.getCompra()+"\',"+
                  "fecha = \'" +  generarFecha().substring(0,19) +"\',"+
                  "pagado = \'" +   a.getPagado()+"\',"+
                  "observacion = \'" +   a.getObservacion()+"\',"+
                  "saldo = \'" +   a.getSaldo()+"\',"+
                  "proveedor = \'" +   a.getProveedor()+"\',"+
                  "fechaExpiracion = \'" +   a.getFechaExpiracion()+"\',"+
                  "tn = \'" +   a.getTn()+"\',"+
                  "contraRecibo = \'" +a.getContraRecibo() +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
     return query;
    }        
              
      public int actualizarProveedor(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE cuentasporpagar SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
              
              
      public int borrarCuentasPorPagar(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
      
      public String crearQueryBorrarCuentasPorPagar(String campo, String match, String condicion)
    {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM cuentasporpagar WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
      return query;
    }
             
            public static void main(String args[])
    {

       hCuentasPorPagar ejemplo = new hCuentasPorPagar();
       List<Cuentasporpagar> articulos = ejemplo.consultaCuentasPorPagar("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFecha());
           i++;
       } 
        System.out.println(ejemplo.borrarCuentasPorPagar("id", "=", "1"));

       Cuentasporpagar articulo = new Cuentasporpagar();
       articulo.setCompra((long)1);
       articulo.setContraRecibo("TN-18");
       articulo.setObservacion("3");
       articulo.setPagado(4);
       articulo.setSaldo(5.0);
                     

  //     System.out.println(ejemplo.guardarCuentasPorPagar(articulo));

       articulo.setSaldo(99.0);
  //     System.out.println(ejemplo.actualizarCuentasPorPagar(articulo, "id", "=", "2"));
        
        
    }    
    
}
