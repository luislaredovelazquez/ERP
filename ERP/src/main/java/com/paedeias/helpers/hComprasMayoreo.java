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
import com.paedeias.identidades.Articulos;
import com.paedeias.identidades.ComprasMayoreo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

public class hComprasMayoreo {
    
    public hComprasMayoreo(){}
    
        /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<ComprasMayoreo> consultaCompras(String campo, String match, String condicion)
    {
       List<ComprasMayoreo> articulos = new ArrayList<ComprasMayoreo>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            if(match.equals("*")){
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCompras()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           
             ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCodigoPedido(Long.parseLong(atributos[2]));
                articulo.setCodigoProveedor(Long.parseLong(atributos[3]));
                articulo.setImporte(Double.parseDouble(atributos[4]));
                articulo.setCargos(Double.parseDouble(atributos[5]));
                articulo.setDescAdic(atributos[6]);
                articulo.setCompra(Integer.valueOf(atributos[7]));
                articulo.setDevolucion(Integer.parseInt(atributos[8]));
                articulo.setPedido(Integer.parseInt(atributos[9]));
                articulo.setNumRefComp(atributos[10]);
                articulo.setObservacion(atributos[11]);
                articulo.setTipoPago(atributos[12]);
                articulo.setCheque(atributos[13]);
                articulo.setBanco(atributos[14]);
                articulo.setDiasCred(Integer.parseInt(atributos[15]));
                articulo.setFechaCompra(atributos[16]);
                articulo.setFactura(atributos[17]);
                articulo.setIdUsuario(atributos[18]);
                articulo.setCantidadArticulos(atributos[19]);
                articulo.setTn(atributos[20]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigoPedido(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoProveedor(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setCargos(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescAdic(conjuntoResultados.getObject(6).toString());
                articulo.setCompra(Integer.valueOf(conjuntoResultados.getObject(7).toString()));
                articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                articulo.setPedido(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                articulo.setNumRefComp(conjuntoResultados.getObject(10).toString());
                articulo.setObservacion(conjuntoResultados.getObject(11).toString());
                articulo.setTipoPago(conjuntoResultados.getObject(12).toString());
                articulo.setCheque(conjuntoResultados.getObject(13).toString());
                articulo.setBanco(conjuntoResultados.getObject(14).toString());
                articulo.setDiasCred(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setFechaCompra(conjuntoResultados.getObject(16).toString());
                articulo.setFactura(conjuntoResultados.getObject(17).toString());
                articulo.setIdUsuario(conjuntoResultados.getObject(18).toString());
                articulo.setCantidadArticulos(conjuntoResultados.getObject(19).toString());
                articulo.setTn(conjuntoResultados.getObject(20).toString());
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
    
    public List<ComprasMayoreo> consultaCompras2(String campo, String match, String condicion)
    {
       List<ComprasMayoreo> articulos = new ArrayList<ComprasMayoreo>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
          if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            if(match.equals("*")){
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaCompras2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           
             ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCodigoPedido(Long.parseLong(atributos[2]));
                articulo.setCodigoProveedor(Long.parseLong(atributos[3]));
                articulo.setImporte(Double.parseDouble(atributos[4]));
                articulo.setCargos(Double.parseDouble(atributos[5]));
                articulo.setDescAdic(atributos[6]);
                articulo.setCompra(Integer.valueOf(atributos[7]));
                articulo.setDevolucion(Integer.parseInt(atributos[8]));
                articulo.setPedido(Integer.parseInt(atributos[9]));
                articulo.setNumRefComp(atributos[10]);
                articulo.setObservacion(atributos[11]);
                articulo.setTipoPago(atributos[12]);
                articulo.setCheque(atributos[13]);
                articulo.setBanco(atributos[14]);
                articulo.setDiasCred(Integer.parseInt(atributos[15]));
                articulo.setFechaCompra(atributos[16]);
                articulo.setFactura(atributos[17]);
                articulo.setIdUsuario(atributos[18]);
                articulo.setCantidadArticulos(atributos[19]);
                articulo.setTn(atributos[20]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigoPedido(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoProveedor(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setCargos(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescAdic(conjuntoResultados.getObject(6).toString());
                articulo.setCompra(Integer.valueOf(conjuntoResultados.getObject(7).toString()));
                articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                articulo.setPedido(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                articulo.setNumRefComp(conjuntoResultados.getObject(10).toString());
                articulo.setObservacion(conjuntoResultados.getObject(11).toString());
                articulo.setTipoPago(conjuntoResultados.getObject(12).toString());
                articulo.setCheque(conjuntoResultados.getObject(13).toString());
                articulo.setBanco(conjuntoResultados.getObject(14).toString());
                articulo.setDiasCred(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setFechaCompra(conjuntoResultados.getObject(16).toString());
                articulo.setFactura(conjuntoResultados.getObject(17).toString());
                articulo.setIdUsuario(conjuntoResultados.getObject(18).toString());
                articulo.setCantidadArticulos(conjuntoResultados.getObject(19).toString());
                articulo.setTn(conjuntoResultados.getObject(20).toString());
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
    
    public List<ComprasMayoreo> consultaComprasProv(String campo, String match, String condicion)
    {
       List<ComprasMayoreo> articulos = new ArrayList<ComprasMayoreo>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            if(campo.equals("codigoProveedor"))
            query = "SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE p.nombre LIKE \'%"+condicion+"%\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;";                
            else if(match.equals("*")){
            query = "SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;";
            }
            else if(match.equals("="))
            {
            query = "SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE c."+campo+" "+match+" \'"+condicion+"\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;";
            }else
            {
            query = "SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE c."+campo+" "+match+" \'%"+condicion+"%\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaComprasProv()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           
             ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCodigoPedido(Long.parseLong(atributos[2]));
                articulo.setCodigoProveedor(Long.parseLong(atributos[3]));
                articulo.setImporte(Double.parseDouble(atributos[4]));
                articulo.setCargos(Double.parseDouble(atributos[5]));
                articulo.setDescAdic(atributos[6]);
                articulo.setCompra(Integer.valueOf(atributos[7]));
                articulo.setDevolucion(Integer.parseInt(atributos[8]));
                articulo.setPedido(Integer.parseInt(atributos[9]));
                articulo.setNumRefComp(atributos[10]);
                articulo.setObservacion(atributos[11]);
                articulo.setTipoPago(atributos[12]);
                articulo.setCheque(atributos[13]);
                articulo.setBanco(atributos[14]);
                articulo.setDiasCred(Integer.parseInt(atributos[15]));
                articulo.setFechaCompra(atributos[16]);
                articulo.setFactura(atributos[17]);
                articulo.setIdUsuario(atributos[18]);
                articulo.setCantidadArticulos(atributos[19]);
                articulo.setNombreProveedor(atributos[20]);
                articulo.setTn(atributos[21]);
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
            if(campo.equals("codigoProveedor"))
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE p.nombre LIKE \'%"+condicion+"%\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;");                
            else if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE c."+campo+" "+match+" \'"+condicion+"\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT c.id,c.codigoPedido,c.codigoProveedor,c.importe,c.cargos,c.descAdic,c.compra,c.devolucion,c.pedido,c.numRefComp,c.observacion,c.tipoPago,c.cheque,c.banco,c.diasCred,c.fechaCompra,c.factura,c.idUsuario,c.cantidadArticulos,p.nombre,c.TEN FROM comprasmayoreo c,proveedores p WHERE c."+campo+" "+match+" \'%"+condicion+"%\' AND p.id = c.codigoProveedor ORDER BY id DESC LIMIT 1000;");
            }
                       
            while (conjuntoResultados.next()) {
                
                ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigoPedido(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoProveedor(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setCargos(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescAdic(conjuntoResultados.getObject(6).toString());
                articulo.setCompra(Integer.valueOf(conjuntoResultados.getObject(7).toString()));
                articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                articulo.setPedido(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                articulo.setNumRefComp(conjuntoResultados.getObject(10).toString());
                articulo.setObservacion(conjuntoResultados.getObject(11).toString());
                articulo.setTipoPago(conjuntoResultados.getObject(12).toString());
                articulo.setCheque(conjuntoResultados.getObject(13).toString()); //
                articulo.setBanco(conjuntoResultados.getObject(14).toString());
                articulo.setDiasCred(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setFechaCompra(conjuntoResultados.getObject(16).toString());
                articulo.setFactura(conjuntoResultados.getObject(17).toString());
                articulo.setIdUsuario(conjuntoResultados.getObject(18).toString());
                articulo.setCantidadArticulos(conjuntoResultados.getObject(19).toString());
                articulo.setNombreProveedor(conjuntoResultados.getObject(20).toString());
                articulo.setTn(conjuntoResultados.getObject(21).toString());
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
    
     public List<ComprasMayoreo> consultaComprasPeriodo(String fecha1, String fecha2)
    {
       List<ComprasMayoreo> articulos = new ArrayList<ComprasMayoreo>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
            //  conexion.crearConexion();
                 if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE compra = \'1\' AND fechaCompra BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND devolucion = \'0\';";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaComprasPeriodo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           
             ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCodigoPedido(Long.parseLong(atributos[2]));
                articulo.setCodigoProveedor(Long.parseLong(atributos[3]));
                articulo.setImporte(Double.parseDouble(atributos[4]));
                articulo.setCargos(Double.parseDouble(atributos[5]));
                articulo.setDescAdic(atributos[6]);
                articulo.setCompra(Integer.valueOf(atributos[7]));
                articulo.setDevolucion(Integer.parseInt(atributos[8]));
                articulo.setPedido(Integer.parseInt(atributos[9]));
                articulo.setNumRefComp(atributos[10]);
                articulo.setObservacion(atributos[11]);
                articulo.setTipoPago(atributos[12]);
                articulo.setCheque(atributos[13]);
                articulo.setBanco(atributos[14]);
                articulo.setDiasCred(Integer.parseInt(atributos[15]));
                articulo.setFechaCompra(atributos[16]);
                articulo.setFactura(atributos[17]);
                articulo.setIdUsuario(atributos[18]);
                articulo.setCantidadArticulos(atributos[19]);
                articulo.setTn(atributos[20]);
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
           conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoPedido,codigoProveedor,importe,cargos,descAdic,compra,devolucion,pedido,numRefComp,observacion,tipoPago,cheque,banco,diasCred,fechaCompra,factura,idUsuario,cantidadArticulos,TEN FROM comprasmayoreo WHERE compra = \'1\' AND fechaCompra BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND devolucion = \'0\';");
                       
            while (conjuntoResultados.next()) {
                
                ComprasMayoreo articulo = new ComprasMayoreo(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCodigoPedido(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoProveedor(Long.parseLong(conjuntoResultados.getObject(3).toString()));
                articulo.setImporte(Double.parseDouble(conjuntoResultados.getObject(4).toString()));
                articulo.setCargos(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescAdic(conjuntoResultados.getObject(6).toString());
                articulo.setCompra(Integer.valueOf(conjuntoResultados.getObject(7).toString()));
                articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(8).toString()));
                articulo.setPedido(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                articulo.setNumRefComp(conjuntoResultados.getObject(10).toString());
                articulo.setObservacion(conjuntoResultados.getObject(11).toString());
                articulo.setTipoPago(conjuntoResultados.getObject(12).toString());
                articulo.setCheque(conjuntoResultados.getObject(13).toString());
                articulo.setBanco(conjuntoResultados.getObject(14).toString());
                articulo.setDiasCred(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setFechaCompra(conjuntoResultados.getObject(16).toString());
                articulo.setFactura(conjuntoResultados.getObject(17).toString());
                articulo.setIdUsuario(conjuntoResultados.getObject(18).toString());
                articulo.setCantidadArticulos(conjuntoResultados.getObject(19).toString());
                articulo.setTn(conjuntoResultados.getObject(20).toString());
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
    public long consultaComprasUltima()
    {
       
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
        long articuloId = 0;
        
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT id FROM comprasmayoreo ORDER BY id DESC LIMIT 1;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaComprasUltima()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           articuloId   = Long.parseLong(atributos[1]);
		   //Arma el objeto y agrega a la lista en su caso	
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articuloId;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return 0;
		   //Regresa nulo o 0
		   	   
        }
        
        
        
        
        
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id FROM comprasmayoreo ORDER BY id DESC LIMIT 1;");
         
                       
            while (conjuntoResultados.next()) {                
             articuloId   = Long.parseLong(conjuntoResultados.getObject(1).toString());
            }
                
            
            return articuloId;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return (long)0;
    }
    
        public long consultaComprasComprada(long id)
    {
       
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
        long articuloId = 0;
        
               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT compra FROM comprasmayoreo WHERE id = \'"+id+"\';";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaComprasComprada()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           articuloId   = Long.parseLong(atributos[1]);
		   //Arma el objeto y agrega a la lista en su caso	
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articuloId;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return (long) -1;
		   //Regresa nulo o 0
		   	   
        } 
        
        
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT compra FROM comprasmayoreo WHERE id = \'"+id+"\';");
         
                       
            while (conjuntoResultados.next()) {                
             articuloId   = Long.parseLong(conjuntoResultados.getObject(1).toString());
            }
                
            
            return articuloId;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return (long)-1;
    }
    
    
        public boolean consultaComprasFactura(long idProveedor, String factura)
    {
       
   //    Conexion conexion = new Conexion();
   //    conexion.crearConexion();
        boolean bandera = false;
        
                       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT id FROM comprasmayoreo WHERE codigoProveedor = \'"+idProveedor+"\' AND factura = \'"+ factura+"\'  ORDER BY id DESC LIMIT 1;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaComprasFactura()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
		  //Escribe el .php
		  
		  
		  
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));
          
           String s=recv.readLine();

            while(s!=null)
           {
           String[] objetos = s.split("°°°");
           for(int u=0; u<objetos.length; u++)
           {
           bandera = true;
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return bandera;
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

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id FROM comprasmayoreo WHERE codigoProveedor = \'"+idProveedor+"\' AND factura = \'"+ factura+"\'  ORDER BY id DESC LIMIT 1;");
         
                       
            while (conjuntoResultados.next()) {                
             bandera = true;
            }
                
            
            return bandera;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return false;
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
    
     public int guardarComprasLast(ComprasMayoreo a)
    {
            int ultimoInsertado=-1;
            String query = " INSERT INTO comprasmayoreo (" + 
                      "codigoPedido,"
                    + "codigoProveedor,"
                    + "importe,"
                    + "cargos,"
                    + "descAdic,"
                    + "compra," 
                    + "devolucion,"
                    + "pedido,"
                    + "numRefComp,"
                    + "observacion,"
                    + "tipoPago,"
                    + "cheque,"
                    + "banco,"
                    + "diasCred,"
                    + "fechaCompra,"
                    + "factura,"
                    + "idUsuario,"
                    + "cantidadArticulos,"
                    + "TEN) VALUES" + "(\'" 
                    + a.getCodigoPedido() + "\',\'" 
                    + a.getCodigoProveedor() + "\',\'" 
                    + a.getImporte() + "\',\'" 
                    + a.getCargos() + "\',\'" 
                    + a.getDescAdic() + "\',\'" 
                    + a.getCompra() + "\',\'" 
                    + a.getDevolucion() + "\',\'" 
                    + a.getPedido() + "\',\'" 
                    + a.getNumRefComp() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getTipoPago() + "\',\'" 
                    + a.getCheque() + "\',\'" 
                    + a.getBanco() + "\',\'"
                    + a.getDiasCred() + "\',\'"
                    + generarFecha().substring(0,19) + "\',\'"
                    + a.getFactura() + "\',\'" 
                    + a.getIdUsuario() + "\',\'"
                    + a.getCantidadArticulos() + "\',\'"
                    + a.getTn() + "');";
          //  Conexion conexion = new Conexion();
          //  conexion.crearConexion();
            
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
     
     
     public String crearQueryGuardar(ComprasMayoreo a)
    {
            
            
            String query = " INSERT INTO comprasmayoreo (" + 
                      "codigoPedido,"
                    + "codigoProveedor,"
                    + "importe,"
                    + "cargos,"
                    + "descAdic,"
                    + "compra," 
                    + "devolucion,"
                    + "pedido,"
                    + "numRefComp,"
                    + "observacion,"
                    + "tipoPago,"
                    + "cheque,"
                    + "banco,"
                    + "diasCred,"
                    + "fechaCompra,"
                    + "factura,"
                    + "idUsuario,"
                    + "cantidadArticulos,"
                    + "TEN) VALUES" + "(\'" 
                    + a.getCodigoPedido() + "\',\'" 
                    + a.getCodigoProveedor() + "\',\'" 
                    + a.getImporte() + "\',\'" 
                    + a.getCargos() + "\',\'" 
                    + a.getDescAdic() + "\',\'" 
                    + a.getCompra() + "\',\'" 
                    + a.getDevolucion() + "\',\'" 
                    + a.getPedido() + "\',\'" 
                    + a.getNumRefComp() + "\',\'" 
                    + a.getObservacion() + "\',\'" 
                    + a.getTipoPago() + "\',\'" 
                    + a.getCheque() + "\',\'" 
                    + a.getBanco() + "\',\'"
                    + a.getDiasCred() + "\',\'"
                    + generarFecha().substring(0,19) + "\',\'"
                    + a.getFactura() + "\',\'" 
                    + a.getIdUsuario() + "\',\'"
                    + a.getCantidadArticulos() + "\',\'"
                    + a.getTn() + "');";
          //  Conexion conexion = new Conexion();
          //  conexion.crearConexion();
            
            return query;
    }
     
              public int actualizarComprasLast(ComprasMayoreo a,String campoCondicion,String match,String condicion)
    {     
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE comprasmayoreo SET codigoPedido = \'" +  a.getCodigoPedido()+"\',"+
                  "codigoProveedor = \'" +  a.getCodigoProveedor() +"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "cargos = \'" +   a.getCargos()+"\',"+
                  "descAdic = \'" +   a.getDescAdic()+"\',"+
                  "compra = \'" +   a.getCompra()+"\',"+
                  "devolucion = \'" +    a.getDevolucion()+"\',"+
                  "pedido = \'" +    a.getPedido()+"\',"+
                  "numRefComp = \'" +    a.getNumRefComp()+"\',"+
                  "observacion = \'" +    a.getObservacion()+"\',"+
                  "tipoPago = \'" + a.getTipoPago()+"\',"+
                  "cheque = \'" +    a.getCheque()+"\',"+
                  "banco = \'" +    a.getBanco()+"\',"+ 
                  "diasCred = \'" +    a.getDiasCred()+"\',"+
                  "factura = \'" +    a.getFactura()+"\',"+
                  "idUsuario = \'" +    a.getIdUsuario()+"\',"+
                  "cantidadArticulos = \'" +    a.getCantidadArticulos()+"\',"+
                  "TEN = \'" +    a.getTn()+"\',"+
                  "fechaCompra = \'" +generarFecha().substring(0,19) +"\'";
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
              
    public String crearQueryActualizarComprasLast(ComprasMayoreo a,String campoCondicion,String match,String condicion)
    {     



     String query = "UPDATE comprasmayoreo SET codigoPedido = \'" +  a.getCodigoPedido()+"\',"+
                  "codigoProveedor = \'" +  a.getCodigoProveedor() +"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "cargos = \'" +   a.getCargos()+"\',"+
                  "descAdic = \'" +   a.getDescAdic()+"\',"+
                  "compra = \'" +   a.getCompra()+"\',"+
                  "devolucion = \'" +    a.getDevolucion()+"\',"+
                  "pedido = \'" +    a.getPedido()+"\',"+
                  "numRefComp = \'" +    a.getNumRefComp()+"\',"+
                  "observacion = \'" +    a.getObservacion()+"\',"+
                  "tipoPago = \'" + a.getTipoPago()+"\',"+
                  "cheque = \'" +    a.getCheque()+"\',"+
                  "banco = \'" +    a.getBanco()+"\',"+ 
                  "diasCred = \'" +    a.getDiasCred()+"\',"+
                  "factura = \'" +    a.getFactura()+"\',"+
                  "idUsuario = \'" +    a.getIdUsuario()+"\',"+
                  "cantidadArticulos = \'" +    a.getCantidadArticulos()+"\',"+
                  "TEN = \'" +    a.getTn()+"\',"+
                  "fechaCompra = \'" +generarFecha().substring(0,19) +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
     
     return query;
    }              
              
    
       public String crearQueryComprasLast(ComprasMayoreo a,String campoCondicion,String match,String condicion)
    {     

     String query = "UPDATE comprasmayoreo SET codigoPedido = \'" +  a.getCodigoPedido()+"\',"+
                  "codigoProveedor = \'" +  a.getCodigoProveedor() +"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "cargos = \'" +   a.getCargos()+"\',"+
                  "descAdic = \'" +   a.getDescAdic()+"\',"+
                  "compra = \'" +   a.getCompra()+"\',"+
                  "devolucion = \'" +    a.getDevolucion()+"\',"+
                  "pedido = \'" +    a.getPedido()+"\',"+
                  "numRefComp = @ultimo_id,"+
                  "observacion = \'" +    a.getObservacion()+"\',"+
                  "tipoPago = \'" + a.getTipoPago()+"\',"+
                  "cheque = \'" +    a.getCheque()+"\',"+
                  "banco = \'" +    a.getBanco()+"\',"+ 
                  "diasCred = \'" +    a.getDiasCred()+"\',"+
                  "factura = \'" +    a.getFactura()+"\',"+
                  "idUsuario = \'" +    a.getIdUsuario()+"\',"+
                  "cantidadArticulos = \'" +    a.getCantidadArticulos()+"\',"+
                  "TEN = \'" +    a.getTn()+"\',"+
                  "fechaCompra = \'" +generarFecha().substring(0,19) +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
                   
     return query;
    }             
              
              
    public int borrarCompras(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM comprasmayoreo WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM comprasmayoreo WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
    
    public List<Articulos> consultarArticulosE(String campo)
    {
         List<Articulos> articulos = new ArrayList<Articulos>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
         
                       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT a.id,a.codigo,a.descripcion,a.clasificacion,a.precioVenta,a.precioCompra,a.ultimoCosto,a.unidad,a.existencia,a.reservado,a.minimoPzas,a.maximoPzas,a.promPzas,a.iva,a.ieps,a.tipoEtiqueta,a.bloqueado,a.almacenDevoluciones,a.lineaPrincipal,a.anticipos,a.sinonimoPrincipal,p.cantidad, r.nombre,c.fechaCompra,c.id FROM articulos a, comprasmayoreo c, partidascomprasmayoreo p, proveedores r WHERE"
                                                      + " c.id = \'"+campo+"\' AND c.compra = \'1\' AND c.id = p.compra  AND p.articulo = a.codigo AND r.id = c.codigoProveedor;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultarArticulosE()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
                        articulo.setClasificacion(atributos[25]);
                        articulo.setPrecioVenta(Double.parseDouble(atributos[5]));
                        articulo.setPrecioCompra(Double.parseDouble(atributos[6]));
                        articulo.setUltimoCosto(Double.parseDouble(atributos[7]));
                        articulo.setUnidad(atributos[24]);
                        articulo.setExistencia(Long.parseLong(atributos[22]));
                        articulo.setReservado(Long.parseLong(atributos[10]));
                        articulo.setMinimoPzas(Long.parseLong(atributos[11]));
                        articulo.setMaximoPzas(Long.parseLong(atributos[12]));
                        articulo.setPromPzas(Long.parseLong(atributos[13]));
                        articulo.setIva(Integer.parseInt(atributos[14]));
                        articulo.setIeps(Integer.parseInt(atributos[15]));
                        articulo.setTipoEtiqueta(atributos[23]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.codigo,a.descripcion,a.clasificacion,a.precioVenta,a.precioCompra,a.ultimoCosto,a.unidad,a.existencia,a.reservado,a.minimoPzas,a.maximoPzas,a.promPzas,a.iva,a.ieps,a.tipoEtiqueta,a.bloqueado,a.almacenDevoluciones,a.lineaPrincipal,a.anticipos,a.sinonimoPrincipal,p.cantidad, r.nombre,c.fechaCompra,c.id  as id2 FROM articulos a, comprasmayoreo c, partidascomprasmayoreo p, proveedores r WHERE"
                                                      + " c.id = \'"+campo+"\' AND c.compra = \'1\' AND c.id = p.compra  AND p.articulo = a.codigo AND r.id = c.codigoProveedor;");
                       
            while (conjuntoResultados.next()) {
                
                Articulos articulo = new Articulos(); 
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(25).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(24).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(22).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(23).toString());
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
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
     public List<Articulos> consultarArticulosEF(String campo)
    {
         List<Articulos> articulos = new ArrayList<Articulos>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
                         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT a.id,a.codigo,a.descripcion,a.clasificacion,a.precioVenta,a.precioCompra,a.ultimoCosto,a.unidad,a.existencia,a.reservado,a.minimoPzas,a.maximoPzas,a.promPzas,a.iva,a.ieps,a.tipoEtiqueta,a.bloqueado,a.almacenDevoluciones,a.lineaPrincipal,a.anticipos,a.sinonimoPrincipal,p.cantidad, r.nombre,c.fechaCompra,c.id as id2 FROM articulos a, comprasmayoreo c, partidascomprasmayoreo p, proveedores r WHERE"
                                                      + " c.factura = \'"+campo+"\' AND c.compra = \'1\' AND c.id = p.compra  AND p.articulo = a.codigo AND r.id = c.codigoProveedor;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultarArticulosEF()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
                        articulo.setClasificacion(atributos[25]);
                        articulo.setPrecioVenta(Double.parseDouble(atributos[5]));
                        articulo.setPrecioCompra(Double.parseDouble(atributos[6]));
                        articulo.setUltimoCosto(Double.parseDouble(atributos[7]));
                        articulo.setUnidad(atributos[24]);
                        articulo.setExistencia(Long.parseLong(atributos[22]));
                        articulo.setReservado(Long.parseLong(atributos[10]));
                        articulo.setMinimoPzas(Long.parseLong(atributos[11]));
                        articulo.setMaximoPzas(Long.parseLong(atributos[12]));
                        articulo.setPromPzas(Long.parseLong(atributos[13]));
                        articulo.setIva(Integer.parseInt(atributos[14]));
                        articulo.setIeps(Integer.parseInt(atributos[15]));
                        articulo.setTipoEtiqueta(atributos[23]);
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.id,a.codigo,a.descripcion,a.clasificacion,a.precioVenta,a.precioCompra,a.ultimoCosto,a.unidad,a.existencia,a.reservado,a.minimoPzas,a.maximoPzas,a.promPzas,a.iva,a.ieps,a.tipoEtiqueta,a.bloqueado,a.almacenDevoluciones,a.lineaPrincipal,a.anticipos,a.sinonimoPrincipal,p.cantidad, r.nombre,c.fechaCompra,c.id FROM articulos a, comprasmayoreo c, partidascomprasmayoreo p, proveedores r WHERE"
                                                      + " c.factura = \'"+campo+"\' AND c.compra = \'1\' AND c.id = p.compra  AND p.articulo = a.codigo AND r.id = c.codigoProveedor;");
                       
            while (conjuntoResultados.next()) {
                
                Articulos articulo = new Articulos(); 
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(25).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(24).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(22).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(23).toString());
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
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
             public String consultaFactura(String id)
    {
       
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
        String articuloId = "";
        
        
                    if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
        
            query = "SELECT factura FROM comprasmayoreo WHERE id = \'"+id+"\';";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFactura()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeComprasMayoreo.php");
		  
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
           articuloId   = atributos[1];
		   //Arma el objeto y agrega a la lista en su caso	
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return articuloId;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return "";
	   //Regresa nulo o 0
		   	   
        } 
        
        
       try {
            ResultSet conjuntoResultados;

            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT factura FROM comprasmayoreo WHERE id = \'"+id+"\';");
         
                       
            while (conjuntoResultados.next()) {                
             articuloId   = conjuntoResultados.getObject(1).toString();
            }
                
            
            return articuloId;
        } catch (SQLException ex) {
            Logger.getLogger(hAnticipos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return "";
    }  
             
    public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE comprasmayoreo SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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
    
    
        public int actualizarProveedor(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE comprasmayoreo SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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

       hComprasMayoreo ejemplo = new hComprasMayoreo();
       List<ComprasMayoreo> articulos = ejemplo.consultaCompras("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getObservacion());
           i++;
       } 
         System.out.println(ejemplo.borrarCompras("id", "=", "1"));

          ComprasMayoreo articulo = new ComprasMayoreo();
                articulo.setCodigoPedido((long)1);
                articulo.setCodigoProveedor((long)2);
                articulo.setImporte(3.0);
                articulo.setCargos(4.0);
                articulo.setDescAdic("5");
                articulo.setCompra(6);
                articulo.setDevolucion(7);
                articulo.setPedido(8);
                articulo.setNumRefComp("9");
                articulo.setObservacion("10");
                articulo.setTipoPago("11");
                articulo.setCheque("12");
                articulo.setBanco("13");
                articulo.setDiasCred(14);
                articulo.setFechaCompra(ejemplo.generarFecha());
                     

        System.out.println(ejemplo.guardarComprasLast(articulo));

       articulo.setObservacion("99");
       System.out.println(ejemplo.actualizarComprasLast(articulo, "id", "=", "2"));
        
        
    }
         
}
