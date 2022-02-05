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
import com.paedeias.identidades.Anticipos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.controladores.CGlobalConfig;
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


public class hAnticipos {
   
    public hAnticipos()
    {}
    
     /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Anticipos> consultaAnticipos(String campo, String match, String condicion)
    {
       List<Anticipos> articulos = new ArrayList<Anticipos>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaAnticipos()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeAnticipos.php");
		  
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
           
            Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setReferencia(atributos[2]);
                articulo.setFecha(atributos[3]);
                articulo.setIdVendedor(atributos[4]);
                articulo.setFechaFin(atributos[5]);
                articulo.setAcuenta(atributos[6]);
                articulo.setImporte(atributos[7]);
                articulo.setResta(atributos[8]);
                articulo.setTipo(atributos[9]);
                articulo.setObservaciones(atributos[10]);
                articulo.setDevuelta(Integer.parseInt(atributos[11]));
                articulo.setCancelado(Integer.parseInt(atributos[12]));
                articulo.setTicket(Integer.parseInt(atributos[13]));
                articulo.setFactura(Integer.parseInt(atributos[14]));
                articulo.setSurtido(Integer.parseInt(atributos[15]));
                articulo.setPropietario(atributos[16]);
                articulo.setTelefono(atributos[17]);
                articulo.setAnio(atributos[18]);
                articulo.setPuertas(atributos[19]);
                articulo.setNoserie(atributos[20]);
                articulo.setModelo(atributos[21]);
                articulo.setLado(atributos[22]);
                articulo.setTransmision(atributos[23]);               
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setReferencia(conjuntoResultados.getObject(2).toString());
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setIdVendedor(conjuntoResultados.getObject(4).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(5).toString());
                articulo.setAcuenta(conjuntoResultados.getObject(6).toString());
                articulo.setImporte(conjuntoResultados.getObject(7).toString());
                articulo.setResta(conjuntoResultados.getObject(8).toString());
                articulo.setTipo(conjuntoResultados.getObject(9).toString());
                articulo.setObservaciones(conjuntoResultados.getObject(10).toString());
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(11).toString()));
                articulo.setCancelado(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setTicket(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setFactura(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPropietario(conjuntoResultados.getObject(16).toString());
                articulo.setTelefono(conjuntoResultados.getObject(17).toString());
                articulo.setAnio(conjuntoResultados.getObject(18).toString());
                articulo.setPuertas(conjuntoResultados.getObject(19).toString());
                articulo.setNoserie(conjuntoResultados.getObject(20).toString());
                articulo.setModelo(conjuntoResultados.getObject(21).toString());
                articulo.setLado(conjuntoResultados.getObject(22).toString());
                articulo.setTransmision(conjuntoResultados.getObject(23).toString());               
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
    
    
    
    
    
    public List<Anticipos> consultaAnticipos2(String campo, String match, String condicion)
    {
       List<Anticipos> articulos = new ArrayList<Anticipos>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
       
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaAnticipos2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeAnticipos.php");
		  
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
           
            Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setReferencia(atributos[2]);
                articulo.setFecha(atributos[3]);
                articulo.setIdVendedor(atributos[4]);
                articulo.setFechaFin(atributos[5]);
                articulo.setAcuenta(atributos[6]);
                articulo.setImporte(atributos[7]);
                articulo.setResta(atributos[8]);
                articulo.setTipo(atributos[9]);
                articulo.setObservaciones(atributos[10]);
                articulo.setDevuelta(Integer.parseInt(atributos[11]));
                articulo.setCancelado(Integer.parseInt(atributos[12]));
                articulo.setTicket(Integer.parseInt(atributos[13]));
                articulo.setFactura(Integer.parseInt(atributos[14]));
                articulo.setSurtido(Integer.parseInt(atributos[15]));
                articulo.setPropietario(atributos[16]);
                articulo.setTelefono(atributos[17]);
                articulo.setAnio(atributos[18]);
                articulo.setPuertas(atributos[19]);
                articulo.setNoserie(atributos[20]);
                articulo.setModelo(atributos[21]);
                articulo.setLado(atributos[22]);
                articulo.setTransmision(atributos[23]);               
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setReferencia(conjuntoResultados.getObject(2).toString());
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setIdVendedor(conjuntoResultados.getObject(4).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(5).toString());
                articulo.setAcuenta(conjuntoResultados.getObject(6).toString());
                articulo.setImporte(conjuntoResultados.getObject(7).toString());
                articulo.setResta(conjuntoResultados.getObject(8).toString());
                articulo.setTipo(conjuntoResultados.getObject(9).toString());
                articulo.setObservaciones(conjuntoResultados.getObject(10).toString());
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(11).toString()));
                articulo.setCancelado(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setTicket(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setFactura(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPropietario(conjuntoResultados.getObject(16).toString());
                articulo.setTelefono(conjuntoResultados.getObject(17).toString());
                articulo.setAnio(conjuntoResultados.getObject(18).toString());
                articulo.setPuertas(conjuntoResultados.getObject(19).toString());
                articulo.setNoserie(conjuntoResultados.getObject(20).toString());
                articulo.setModelo(conjuntoResultados.getObject(21).toString());
                articulo.setLado(conjuntoResultados.getObject(22).toString());
                articulo.setTransmision(conjuntoResultados.getObject(23).toString());               
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
    public List<Anticipos> consultaAnticiposNoSurtidos()
    {
       List<Anticipos> articulos = new ArrayList<Anticipos>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
       
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE surtido = \'0\' ORDER BY id DESC;";
            
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaAnticiposNoSurtidos()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeAnticipos.php");
		  
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
           
            Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setReferencia(atributos[2]);
                articulo.setFecha(atributos[3]);
                articulo.setIdVendedor(atributos[4]);
                articulo.setFechaFin(atributos[5]);
                articulo.setAcuenta(atributos[6]);
                articulo.setImporte(atributos[7]);
                articulo.setResta(atributos[8]);
                articulo.setTipo(atributos[9]);
                articulo.setObservaciones(atributos[10]);
                articulo.setDevuelta(Integer.parseInt(atributos[11]));
                articulo.setCancelado(Integer.parseInt(atributos[12]));
                articulo.setTicket(Integer.parseInt(atributos[13]));
                articulo.setFactura(Integer.parseInt(atributos[14]));
                articulo.setSurtido(Integer.parseInt(atributos[15]));
                articulo.setPropietario(atributos[16]);
                articulo.setTelefono(atributos[17]);
                articulo.setAnio(atributos[18]);
                articulo.setPuertas(atributos[19]);
                articulo.setNoserie(atributos[20]);
                articulo.setModelo(atributos[21]);
                articulo.setLado(atributos[22]);
                articulo.setTransmision(atributos[23]);               
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE surtido = \'0\' ORDER BY id DESC;");
            
            while (conjuntoResultados.next()) {
                
                Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setReferencia(conjuntoResultados.getObject(2).toString());
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setIdVendedor(conjuntoResultados.getObject(4).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(5).toString());
                articulo.setAcuenta(conjuntoResultados.getObject(6).toString());
                articulo.setImporte(conjuntoResultados.getObject(7).toString());
                articulo.setResta(conjuntoResultados.getObject(8).toString());
                articulo.setTipo(conjuntoResultados.getObject(9).toString());
                articulo.setObservaciones(conjuntoResultados.getObject(10).toString());
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(11).toString()));
                articulo.setCancelado(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setTicket(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setFactura(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPropietario(conjuntoResultados.getObject(16).toString());
                articulo.setTelefono(conjuntoResultados.getObject(17).toString());
                articulo.setAnio(conjuntoResultados.getObject(18).toString());
                articulo.setPuertas(conjuntoResultados.getObject(19).toString());
                articulo.setNoserie(conjuntoResultados.getObject(20).toString());
                articulo.setModelo(conjuntoResultados.getObject(21).toString());
                articulo.setLado(conjuntoResultados.getObject(22).toString());
                articulo.setTransmision(conjuntoResultados.getObject(23).toString());               
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
    public List<Anticipos> obtenerUltimo(String campo, String match, String condicion)
    {
       List<Anticipos> articulos = new ArrayList<Anticipos>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos ORDER BY fecha DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY fecha DESC LIMIT 1;";
            }else
            {
            query = "SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY fecha DESC LIMIT 1;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","obtenerUltimo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeAnticipos.php");
		  
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
           
            Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setReferencia(atributos[2]);
                articulo.setFecha(atributos[3]);
                articulo.setIdVendedor(atributos[4]);
                articulo.setFechaFin(atributos[5]);
                articulo.setAcuenta(atributos[6]);
                articulo.setImporte(atributos[7]);
                articulo.setResta(atributos[8]);
                articulo.setTipo(atributos[9]);
                articulo.setObservaciones(atributos[10]);
                articulo.setDevuelta(Integer.parseInt(atributos[11]));
                articulo.setCancelado(Integer.parseInt(atributos[12]));
                articulo.setTicket(Integer.parseInt(atributos[13]));
                articulo.setFactura(Integer.parseInt(atributos[14]));
                articulo.setSurtido(Integer.parseInt(atributos[15]));
                articulo.setPropietario(atributos[16]);
                articulo.setTelefono(atributos[17]);
                articulo.setAnio(atributos[18]);
                articulo.setPuertas(atributos[19]);
                articulo.setNoserie(atributos[20]);
                articulo.setModelo(atributos[21]);
                articulo.setLado(atributos[22]);
                articulo.setTransmision(atributos[23]);               
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos ORDER BY fecha DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY fecha DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,referencia,fecha,idVendedor,fechaFin,aCuenta,importe,resta,tipo,observaciones,devuelta,cancelado,ticket,factura,surtido,propietario,telefono,anio,puertas,noserie,modelo,lado,transmision FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY fecha DESC LIMIT 1;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Anticipos articulo = new Anticipos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setReferencia(conjuntoResultados.getObject(2).toString());
                articulo.setFecha(conjuntoResultados.getObject(3).toString());
                articulo.setIdVendedor(conjuntoResultados.getObject(4).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(5).toString());
                articulo.setAcuenta(conjuntoResultados.getObject(6).toString());
                articulo.setImporte(conjuntoResultados.getObject(7).toString());
                articulo.setResta(conjuntoResultados.getObject(8).toString());
                articulo.setTipo(conjuntoResultados.getObject(9).toString());
                articulo.setObservaciones(conjuntoResultados.getObject(10).toString());
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(11).toString()));
                articulo.setCancelado(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setTicket(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setFactura(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPropietario(conjuntoResultados.getObject(16).toString());
                articulo.setTelefono(conjuntoResultados.getObject(17).toString());
                articulo.setAnio(conjuntoResultados.getObject(18).toString());
                articulo.setPuertas(conjuntoResultados.getObject(19).toString());
                articulo.setNoserie(conjuntoResultados.getObject(20).toString());
                articulo.setModelo(conjuntoResultados.getObject(21).toString());
                articulo.setLado(conjuntoResultados.getObject(22).toString());
                articulo.setTransmision(conjuntoResultados.getObject(23).toString());   
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
    
           public int guardarAnticipos(Anticipos a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO anticipos (" + 
                      "referencia,"
                    + "fecha,"
                    + "idVendedor,"
                    + "fechaFin,"
                    + "aCuenta,"
                    + "importe," 
                    + "resta,"
                    + "tipo,"
                    + "observaciones,"
                    + "propietario,"
                    + "telefono,"
                    + "anio,"
                    + "puertas,"
                    + "noserie,"
                    + "modelo,"
                    + "lado,"
                    + "transmision) VALUES" + "(\'" 
                    + a.getReferencia() + "\',\'" 
                    + fecha.substring(0,19) + "\',\'" 
                    + a.getIdVendedor() + "\',\'" 
                    + a.getFechaFin() + "\',\'" 
                    + a.getAcuenta() + "\',\'" 
                    + a.getImporte() + "\',\'" 
                    + a.getResta() + "\',\'" 
                    + a.getTipo() + "\',\'" 
                    + a.getObservaciones() + "\',\'" 
                    + a.getPropietario() + "\',\'" 
                    + a.getTelefono() + "\',\'" 
                    + a.getAnio() + "\',\'" 
                    + a.getPuertas() + "\',\'" 
                    + a.getNoserie() + "\',\'" 
                    + a.getModelo() + "\',\'" 
                    + a.getLado() + "\',\'" 
                    + a.getTransmision() + "');";
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
           
             public String crearQueryGuardarAnticipos(Anticipos a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            
            String query = " INSERT INTO anticipos (" + 
                      "referencia,"
                    + "fecha,"
                    + "idVendedor,"
                    + "fechaFin,"
                    + "aCuenta,"
                    + "importe," 
                    + "resta,"
                    + "tipo,"
                    + "observaciones,"
                    + "propietario,"
                    + "telefono,"
                    + "anio,"
                    + "puertas,"
                    + "noserie,"
                    + "modelo,"
                    + "lado,"
                    + "transmision) VALUES" + "(\'" 
                    + a.getReferencia() + "\',\'" 
                    + fecha.substring(0,19) + "\',\'" 
                    + a.getIdVendedor() + "\',\'" 
                    + a.getFechaFin() + "\',\'" 
                    + a.getAcuenta() + "\',\'" 
                    + a.getImporte() + "\',\'" 
                    + a.getResta() + "\',\'" 
                    + a.getTipo() + "\',\'" 
                    + a.getObservaciones() + "\',\'" 
                    + a.getPropietario() + "\',\'" 
                    + a.getTelefono() + "\',\'" 
                    + a.getAnio() + "\',\'" 
                    + a.getPuertas() + "\',\'" 
                    + a.getNoserie() + "\',\'" 
                    + a.getModelo() + "\',\'" 
                    + a.getLado() + "\',\'" 
                    + a.getTransmision() + "');";
       

            return query;
    }    
           
 
        public int borrarAnticipos(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
       String query="";
            if(match.equals("="))
            {
            query = "DELETE  FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
            query = "DELETE  FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }    
          
      ConexionWeb conexionweb = new ConexionWeb();
      tipo_respuesta = conexionweb.escribirEnWeb(query);
      return tipo_respuesta;
      }
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM anticipos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM anticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

       public int actualizarPVenta(Long id,String pVenta, String pResta)
    {        
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();

           String query = "UPDATE anticipos SET " +
                  "importe = \'" + pVenta+"\',"+
                  "resta = \'" +   pResta+"\'";
            query = query + " WHERE id = "+" \'"+id+"\';";
            
            
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
        
        
          public int actualizarAnticipos(Anticipos a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     int resultado = 0;
     // Conexion conexion = new Conexion();
     //         conexion.crearConexion();



     String query = "UPDATE anticipos SET referencia = \'" +  a.getReferencia()+"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "idVendedor = \'" +   a.getIdVendedor()+"\',"+
                  "fechaFin = \'" +   a.getFechaFin()+"\',"+
                  "aCuenta = \'" +   a.getAcuenta()+"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "resta = \'" +    a.getResta()+"\',"+
                  "tipo = \'" +    a.getTipo()+"\',"+
                  "surtido = \'" +    a.getSurtido()+"\',"+
                  "devuelta = \'" +    a.getDevuelta()+"\',"+
                  "cancelado = \'" +    a.getCancelado()+"\',"+
                  "ticket = \'" +    a.getTicket()+"\',"+
                  "factura = \'" +    a.getFactura()+"\',"+
                  "propietario = \'" +    a.getPropietario()+"\',"+
                  "telefono = \'" +    a.getTelefono()+"\',"+
                  "anio = \'" +    a.getAnio()+"\',"+
                  "puertas = \'" +    a.getPuertas()+"\',"+
                  "noserie = \'" +    a.getNoserie()+"\',"+
                  "modelo = \'" +    a.getModelo()+"\',"+
                  "lado = \'" +    a.getLado()+"\',"+
                  "transmision = \'" +    a.getFactura()+"\',"+
                  "observaciones = \'" +a.getObservaciones() +"\'";
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
          
      public String crearQueryActualizarAnticipos(Anticipos a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     
     String query = "UPDATE anticipos SET referencia = \'" +  a.getReferencia()+"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "idVendedor = \'" +   a.getIdVendedor()+"\',"+
                  "fechaFin = \'" +   a.getFechaFin()+"\',"+
                  "aCuenta = \'" +   a.getAcuenta()+"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "resta = \'" +    a.getResta()+"\',"+
                  "tipo = \'" +    a.getTipo()+"\',"+
                  "surtido = \'" +    a.getSurtido()+"\',"+
                  "devuelta = \'" +    a.getDevuelta()+"\',"+
                  "cancelado = \'" +    a.getCancelado()+"\',"+
                  "ticket = \'" +    a.getTicket()+"\',"+
                  "factura = \'" +    a.getFactura()+"\',"+
                  "propietario = \'" +    a.getPropietario()+"\',"+
                  "telefono = \'" +    a.getTelefono()+"\',"+
                  "anio = \'" +    a.getAnio()+"\',"+
                  "puertas = \'" +    a.getPuertas()+"\',"+
                  "noserie = \'" +    a.getNoserie()+"\',"+
                  "modelo = \'" +    a.getModelo()+"\',"+
                  "lado = \'" +    a.getLado()+"\',"+
                  "transmision = \'" +    a.getFactura()+"\',"+
                  "observaciones = \'" +a.getObservaciones() +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
     
     return query;
    }      
          
        
              public int cambiarAFacturado(String id)
    {
     int resultado = 0;
     // Conexion conexion = new Conexion();
     //         conexion.crearConexion();
              
       String query = "UPDATE anticipos SET factura = \'1\' WHERE id = \'"+id+"\';";
       
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
              
      public int cambiarReferencia(String id, String referencia)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
              
       String query = "UPDATE anticipos SET referencia = \'"+referencia+"\' WHERE id = \'"+id+"\';";
       
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
      
          public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE anticipos SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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

       hAnticipos ejemplo = new hAnticipos();
       List<Anticipos> articulos = ejemplo.consultaAnticipos("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFechaFin());
           i++;
       } 
        System.out.println(ejemplo.borrarAnticipos("id", "=", "2"));

       Anticipos art = new Anticipos();
                     art.setIdVendedor("3");
                     art.setFechaFin("5");
                     art.setAcuenta("5");
                     art.setReferencia("org");
                     art.setImporte("5");
                     art.setResta("6");
                     art.setTipo("7");
                     art.setObservaciones("8");
                     

       System.out.println(ejemplo.guardarAnticipos(art));

      art.setImporte("22");
       System.out.println(ejemplo.actualizarAnticipos(art, "id", "=", "3"));
        
        
    }
              
}
