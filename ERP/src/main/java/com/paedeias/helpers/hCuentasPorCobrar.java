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
import com.paedeias.identidades.Cuentasporcobrar;
import com.paedeias.identidades.ReporteCobroClientes;
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

public class hCuentasPorCobrar {
    
    public hCuentasPorCobrar(){}
    
         /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Cuentasporcobrar> consultaCtaPorCobrar(String campo, String match, String condicion)
    {
       
        List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
        
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE pagado != '-1' ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND pagado != '-1' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND pagado != '-1' ORDER BY id ASC LIMIT 1000;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCtaPorCobrar()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
	        articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
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
        
        
       
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE pagado != '-1' ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND pagado != '-1' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND pagado != '-1' ORDER BY id ASC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
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
    
    public List<Cuentasporcobrar> consultaCtaPorCobrar2(String campo, String match, String condicion)
    {
       List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE pagado != '-1';";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND pagado != '-1';";
            }else
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND pagado != '-1';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCtaPorCobrar2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
	        articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE pagado != '-1';");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND pagado != '-1';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND pagado != '-1';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
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
    
        public List<ReporteCobroClientes> consultaClientesReporte(String tipofecha, String fecha1, String fecha2)
    {
       List<ReporteCobroClientes> articulos = new ArrayList<ReporteCobroClientes>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
      String fecha = "c.fechaVencimiento";
        if(tipofecha.equals("liquidacion"))
            fecha = "c.fechaVencimiento";
        else if(tipofecha.equals("venta"))
            fecha = "v.fechaVenta";
        else
            fecha = "r.fecha";
       
       
                   if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            query = "SELECT v.id,r.id as id2,l.nombre, v.total,v.fechaVenta,c.fechaVencimiento,l.diasCredito FROM ventas v, reservaciones r, cuentasporcobrar c, clientes l "+ 
                                                        "WHERE "+fecha+" BETWEEN \'"+fecha1+"\' AND \'"+ fecha2+"\' AND v.vale = r.id  AND v.id = c.venta AND v.idcliente = l.id AND c.pagado = 1 ORDER BY l.id;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaClientesReporte()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
                ReporteCobroClientes articulo = new ReporteCobroClientes(); 
                articulo.setVenta(atributos[1]);
                articulo.setReservacion(atributos[2]);                
                articulo.setCliente(atributos[3]);
                articulo.setImporte(Double.parseDouble(atributos[4]));
                articulo.setFechaVenta(atributos[5]);
                articulo.setFechaLiquidacion(atributos[6]);
                articulo.setDiasCredito(Integer.parseInt(atributos[7]));
                articulo.setFactura(atributos[8]);
		articulo.setFechaReservacion(atributos[9]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT v.id,r.id,l.nombre, v.total,v.fechaVenta,c.fechaVencimiento,l.diasCredito FROM ventas v, reservaciones r, cuentasporcobrar c, clientes l "+ 
                                                        "WHERE "+fecha+" BETWEEN \'"+fecha1+"\' AND \'"+ fecha2+"\' AND v.vale = r.id  AND v.id = c.venta AND v.idcliente = l.id AND c.pagado = 1 ORDER BY l.id;");
           
            ResultSet conjuntoResultadosFactura;
            ResultSet conjuntoResultadosFecha;
            
            while (conjuntoResultados.next()) {
                
                ReporteCobroClientes articulo = new ReporteCobroClientes(); 
                articulo.setVenta(conjuntoResultados.getObject(1).toString());
                articulo.setReservacion(conjuntoResultados.getObject(2).toString());
                
                conjuntoResultadosFactura = CPrincipal.getConexion().crearConsulta("SELECT idFactura FROM facturacfdi WHERE idConceptoFactura LIKE \'__"+articulo.getVenta()+"\';");    

                while(conjuntoResultadosFactura.next())
                {
                articulo.setFactura(conjuntoResultadosFactura.getObject(1).toString());    
                }
                
                
                conjuntoResultadosFecha = CPrincipal.getConexion().crearConsulta("SELECT fecha FROM kardex WHERE movimiento LIKE \'Reservación con Folio "+articulo.getReservacion()+"\' OR movimiento LIKE \'Agr. Reservación con Folio "+articulo.getReservacion()+"\' ORDER BY id DESC LIMIT 1;");    

                while(conjuntoResultadosFecha.next())
                {
                articulo.setFechaReservacion(conjuntoResultadosFecha.getObject(1).toString());    
                }
                
                
                articulo.setCliente(conjuntoResultados.getObject(3).toString());
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setFechaVenta(conjuntoResultados.getObject(5).toString());
                articulo.setFechaLiquidacion(conjuntoResultados.getObject(6).toString());
                articulo.setDiasCredito(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
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
    
      public List<ReporteCobroClientes> consultaClientesReporteSRes(String tipofecha, String fecha1, String fecha2)
    {
       List<ReporteCobroClientes> articulos = new ArrayList<ReporteCobroClientes>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
            String fecha = "c.fechaVencimiento";
        if(tipofecha.equals("liquidacion"))
            fecha = "c.fechaVencimiento";
        else if(tipofecha.equals("venta"))
            fecha = "v.fechaVenta";
       
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            query = "SELECT v.id, l.nombre, v.total,v.fechaVenta,c.fechaVencimiento,l.diasCredito FROM ventas v, cuentasporcobrar c, clientes l "+ 
                                                        "WHERE "+fecha+" BETWEEN \'"+fecha1+"\' AND \'"+ fecha2+"\' AND v.tipoDeVenta LIKE 'Credito' AND v.id = c.venta AND v.idcliente = l.id AND c.pagado = 1 ORDER BY l.id;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaClientesReporteSRes()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
                ReporteCobroClientes articulo = new ReporteCobroClientes();                 
                articulo.setVenta(atributos[1]);
                articulo.setReservacion("-");
                articulo.setCliente(atributos[2]);
                articulo.setImporte(Double.parseDouble(atributos[3]));
                articulo.setFechaReservacion("-");
                articulo.setFechaVenta(atributos[4]);
                articulo.setFechaLiquidacion(atributos[5]);
                articulo.setDiasCredito(Integer.parseInt(atributos[6]));
                articulo.setFactura(atributos[7]); 
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT v.id, l.nombre, v.total,v.fechaVenta,c.fechaVencimiento,l.diasCredito FROM ventas v, cuentasporcobrar c, clientes l "+ 
                                                        "WHERE "+fecha+" BETWEEN \'"+fecha1+"\' AND \'"+ fecha2+"\' AND v.tipoDeVenta LIKE 'Credito' AND v.id = c.venta AND v.idcliente = l.id AND c.pagado = 1 ORDER BY l.id;");
           
                       
            while (conjuntoResultados.next()) {
                
                ReporteCobroClientes articulo = new ReporteCobroClientes(); 
                ResultSet conjuntoResultadosFactura;
                
                articulo.setVenta(conjuntoResultados.getObject(1).toString());
                articulo.setReservacion("-");
                
                conjuntoResultadosFactura = CPrincipal.getConexion().crearConsulta("SELECT idFactura FROM facturacfdi WHERE idConceptoFactura LIKE \'__"+articulo.getVenta()+"\';");    

                while(conjuntoResultadosFactura.next())
                {
                articulo.setFactura(conjuntoResultadosFactura.getObject(1).toString());    
                }
                
                articulo.setCliente(conjuntoResultados.getObject(2).toString());
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(3).toString()));
                articulo.setFechaReservacion("-");
                articulo.setFechaVenta(conjuntoResultados.getObject(4).toString());
                articulo.setFechaLiquidacion(conjuntoResultados.getObject(5).toString());
                articulo.setDiasCredito(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
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
        
        
        
             /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Cuentasporcobrar> consultaCtaPorCobrarReporte(String campo, String match, String condicion)
    {
       List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            if(match.equals("*")){
            query = "SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p, ventas v, clientes c WHERE  p.venta = v.id  AND p.cliente = c.id GROUP BY p.cliente;";
            }
            else if(match.equals("="))
            {
            query = "SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p,ventas v WHERE p."+campo+" "+match+" \'"+condicion+"\' AND p.venta = v.id  GROUP BY p.cliente;";
            }else
            {
            query = "SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p,ventas v WHERE p."+campo+" "+match+" \'%"+condicion+"%\' AND p.venta = v.id GROUP BY p.cliente;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCtaPorCobrarReporte()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
           
                Cuentasporcobrar articulo = new Cuentasporcobrar();
                articulo.setVenta(atributos[1]);
                articulo.setFechaVencimiento(atributos[2]);
                articulo.setPagado(Integer.parseInt(atributos[3]));
                articulo.setSaldo(Double.parseDouble(atributos[4]));
                articulo.setCliente(Long.parseLong(atributos[5]));
                articulo.setDiasCreditoCliente(Integer.parseInt(atributos[6]));
                articulo.setFechaVenta(atributos[7]);
                articulo.setNombreCliente(atributos[8]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p, ventas v, clientes c WHERE  p.venta = v.id  AND p.cliente = c.id GROUP BY p.cliente;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p,ventas v WHERE p."+campo+" "+match+" \'"+condicion+"\' AND p.venta = v.id  GROUP BY p.cliente;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.venta,p.fechaVencimiento,p.pagado,p.saldo,p.cliente,c.diasCredito,v.fechaVenta,c.nombre FROM cuentasporcobrar p,ventas v WHERE p."+campo+" "+match+" \'%"+condicion+"%\' AND p.venta = v.id GROUP BY p.cliente;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                
                articulo.setVenta(conjuntoResultados.getObject(1).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(2).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(3).toString()));
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(5).toString()));
                articulo.setDiasCreditoCliente(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                articulo.setFechaVenta(conjuntoResultados.getObject(7).toString());
                articulo.setNombreCliente(conjuntoResultados.getObject(8).toString());
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
    
      public List<Cuentasporcobrar> consultaCtaPorCobrarPagado(String campo, String match, String condicion)
    {
       List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            if(match.equals("*")){
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar  WHERE Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '1' ORDER BY id ASC LIMIT 1000;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCtaPorCobrarPagado()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
           
                Cuentasporcobrar articulo = new Cuentasporcobrar();
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar  WHERE Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND  Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '1' ORDER BY id ASC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
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
      
        public List<Cuentasporcobrar> consultaCtaPorCobrarNoPagado(String campo, String match, String condicion)
    {
       List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
          if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            if(match.equals("*")){
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }else
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '0' ORDER BY id ASC LIMIT 1000;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCtaPorCobrarNoPagado()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
           
                Cuentasporcobrar articulo = new Cuentasporcobrar();
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '0' ORDER BY id ASC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
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
        
            public boolean tieneNoPagado(String campo, String match, String condicion)
    {
       List<Cuentasporcobrar> articulos = new ArrayList<Cuentasporcobrar>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            if(match.equals("*")){
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE Pagado = '0' AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND Pagado = '0'  AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');";
            }else
            {
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '0'  AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","tieneNoPagado()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
           
                Cuentasporcobrar articulo = new Cuentasporcobrar();
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
                articulos.add(articulo);

                //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
             if(articulos.size() > 0)
                return true;
            else
                return false;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return false;
		   //Regresa nulo o 0
		   	   
        }
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE Pagado = '0' AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\' AND Pagado = '0'  AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND Pagado = '0'  AND fechaVencimiento < DATE_FORMAT(NOW(),\'%Y-%m-%d 23:59:59\');");
            }
                       
            while (conjuntoResultados.next()) {
                
                Cuentasporcobrar articulo = new Cuentasporcobrar(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                articulos.add(articulo);
            }
            
            if(articulos.size() > 0)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return false;
    }
      

        public Cuentasporcobrar consultaUltimaCtaPorCobrar()
    {
       
       Cuentasporcobrar articulo = new Cuentasporcobrar();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       
                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            
            query = "SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar ORDER BY id DESC LIMIT 1;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUltimaCtaPorCobrar()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeCuentasPorCobrar.php");
		  
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
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(atributos[2]);
                articulo.setFechaVencimiento(atributos[3]);
                articulo.setPagado(Integer.parseInt(atributos[4]));
                articulo.setObservacion(atributos[5]);
                articulo.setSaldo(Double.parseDouble(atributos[6]));
                articulo.setTipo(atributos[7]);
                articulo.setCliente(Long.parseLong(atributos[8]));
                articulo.setFactura(Long.parseLong(atributos[9]));
                
                

                //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articulo;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,fechaVencimiento,pagado,observacion,saldo,tipo,cliente,factura FROM cuentasporcobrar ORDER BY id DESC LIMIT 1;");
 
                       
            while (conjuntoResultados.next()) {
                                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(conjuntoResultados.getObject(2).toString());
                articulo.setFechaVencimiento(conjuntoResultados.getObject(3).toString());
                articulo.setPagado(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setObservacion(conjuntoResultados.getObject(5).toString());
                articulo.setSaldo(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setTipo(conjuntoResultados.getObject(7).toString());
                articulo.setCliente(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                articulo.setFactura(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                return articulo;    
                
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
            public int guardarCtaPorCobrar(Cuentasporcobrar a)
    {         
            int ultimoInsertado=-1;
            String query = " INSERT INTO cuentasporcobrar (" + 
                      "venta,"
                    + "fechaVencimiento,"
                    + "pagado,"
                    + "observacion,"
                    + "saldo,"
                    + "tipo,"
                    + "cliente,"
                    + "factura) VALUES" + "(\'" 
                    + a.getVenta() + "\',\'" 
                    + a.getFechaVencimiento() + "\',\'" 
                    + a.getPagado() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getSaldo() + "\',\'" 
                    + a.getTipo() + "\',\'"
                    + a.getCliente() + "\',\'"
                    + a.getFactura() + "');";
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
            
         public String crearQueryGuardarCtaPorCobrar(Cuentasporcobrar a)
    {         
            
            String query = " INSERT INTO cuentasporcobrar (" + 
                      "venta,"
                    + "fechaVencimiento,"
                    + "pagado,"
                    + "observacion,"
                    + "saldo,"
                    + "tipo,"
                    + "cliente,"
                    + "factura) VALUES" + "(@ultimo_id,\'" 
                    + a.getFechaVencimiento() + "\',\'" 
                    + a.getPagado() + "\'," 
                    + a.getObservacion() + ",\'" 
                    + a.getSaldo() + "\'," 
                    + a.getTipo() + ",\'"
                    + a.getCliente() + "\',\'"
                    + a.getFactura() + "');";
       //     Conexion conexion = new Conexion();
       //     conexion.crearConexion();
      
            return query;
    }        
            
         public int actualizarCtaPorCobrar(Cuentasporcobrar a,String campoCondicion,String match,String condicion)
    {       
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE cuentasporcobrar SET venta = \'" +  a.getVenta()+"\',"+
                  "fechaVencimiento = \'" +  a.getFechaVencimiento() +"\',"+
                  "pagado = \'" +   a.getPagado()+"\',"+
                  "observacion = \'" +   a.getObservacion()+"\',"+
                  "saldo = \'" +   a.getSaldo()+"\',"+
                  "cliente = \'" +   a.getCliente()+"\',"+
                  "factura = \'" +   a.getFactura()+"\',"+
                  "tipo = \'" +a.getTipo() +"\'";
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
         
          public String crearQueryActualizarCtaPorCobrar(Cuentasporcobrar a,String campoCondicion,String match,String condicion)
    {       

     String query = "UPDATE cuentasporcobrar SET venta = \'" +  a.getVenta()+"\',"+
                  "fechaVencimiento = \'" +  a.getFechaVencimiento() +"\',"+
                  "pagado = \'" +   a.getPagado()+"\',"+
                  "observacion = \'" +   a.getObservacion()+"\',"+
                  "saldo = \'" +   a.getSaldo()+"\',"+
                  "cliente = \'" +   a.getCliente()+"\',"+
                  "factura = \'" +   a.getFactura()+"\',"+
                  "tipo = \'" +a.getTipo() +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
      

     return query;
    }        
         
         
     public int pagar(int pagado,String campoCondicion,String match,String condicion,String fecha)
    {       
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();



     String query = "UPDATE cuentasporcobrar SET pagado = \'" + pagado +"\', fechaVencimiento = \'"+fecha+"\' ";
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
     
     
     public int actualizarObservacion(String observacion,String campoCondicion,String match,String condicion)
    {       
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();



     String query = "UPDATE cuentasporcobrar SET observacion = \'" + observacion +"\' ";
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
      
     public int ponerFactura(long factura,String campoCondicion,String match,String condicion)
    {       
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE cuentasporcobrar SET factura = \'" + factura+"\'";
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
     
     public String crearQueryPonerFactura(long factura,String campoCondicion,String match,String condicion)
    {       

     String query = "UPDATE cuentasporcobrar SET factura = \'" + factura+"\'";
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
     String query = "UPDATE cuentasporcobrar SET cliente = \'" + clienteNuevo+"\' WHERE cliente = \'" + clienteViejo +"\'";
     
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
     
      public int borrarCtaPorCobrar(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
      
       public String crearQueryBorrarCtaPorCobrar(String campo, String match, String condicion)
    {
      
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM cuentasporcobrar WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
      return query;
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
      
            public static void main(String args[])
    {

       hCuentasPorCobrar ejemplo = new hCuentasPorCobrar();
       List<Cuentasporcobrar> articulos = ejemplo.consultaCtaPorCobrar("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFechaVencimiento());
           i++;
       } 
         System.out.println(ejemplo.borrarCtaPorCobrar("id", "=", "1"));

                Cuentasporcobrar articulo = new Cuentasporcobrar();
                
                articulo.setVenta("2");
                articulo.setFechaVencimiento(ejemplo.generarFecha().substring(0, 19));
                articulo.setPagado(3);
                articulo.setObservacion("5");
                articulo.setSaldo(6.0);
                articulo.setTipo("7");
                     

     //  System.out.println(ejemplo.guardarCtaPorCobrar(articulo));

       articulo.setTipo("99");
       System.out.println(ejemplo.actualizarCtaPorCobrar(articulo, "id", "=", "2"));
        
        
    }
    
}
