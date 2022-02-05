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
import com.paedeias.identidades.Reservaciones;
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

public class hReservaciones {
    
    public hReservaciones(){}
    
   /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Reservaciones> consultaReservaciones(String campo, String match, String condicion)
    {
       List<Reservaciones> articulos = new ArrayList<Reservaciones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
          if(match.equals("*")){
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReservaciones()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeReservaciones.php");
		  
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
           
	      Reservaciones articulo = new Reservaciones(); 
                
              articulo.setId(Long.parseLong(atributos[1]));
              articulo.setCodigo(Long.parseLong(atributos[2]));
              articulo.setFacturada(Integer.parseInt(atributos[3]));
              articulo.setFecha(atributos[4].substring(0,19));
              articulo.setCodcliente(atributos[5]);
              articulo.setCodventa(Long.parseLong(atributos[6]));
              articulo.setNomCli(atributos[7]);
              articulo.setSiniestro(atributos[8]);
              articulo.setTaller(atributos[9]);
              articulo.setMarca(atributos[10]);
              articulo.setSerie(atributos[11]);
              articulo.setTipoAuto(atributos[12]);
              articulo.setPlacas(atributos[13]);
              articulo.setModelo(atributos[14]);
              articulo.setColor(atributos[15]);
              articulo.setPoliza(atributos[16]);
              articulo.setPuertas(atributos[17]);
              articulo.setAsegurado(atributos[18]);
              articulo.setSolicito(atributos[19]);
              articulo.setDevolucion(Integer.parseInt(atributos[20]));
              articulo.setCancelada(Integer.parseInt(atributos[21]));
              articulo.setFechaDevol(atributos[22]);
              articulo.setObsCancel(atributos[23]);
              articulo.setNomotor(atributos[24]);
              articulo.setObservaciones(atributos[25]);
              articulo.setNovale(atributos[26]);
              

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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Reservaciones articulo = new Reservaciones(); 
                
              articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
              articulo.setCodigo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
              articulo.setFacturada(Integer.parseInt(conjuntoResultados.getObject(3).toString()));
              articulo.setFecha(conjuntoResultados.getObject(4).toString().substring(0,19));
              articulo.setCodcliente(conjuntoResultados.getObject(5).toString());
              articulo.setCodventa(Long.parseLong(conjuntoResultados.getObject(6).toString()));
              articulo.setNomCli(conjuntoResultados.getObject(7).toString());
              articulo.setSiniestro(conjuntoResultados.getObject(8).toString());
              articulo.setTaller(conjuntoResultados.getObject(9).toString());
              articulo.setMarca(conjuntoResultados.getObject(10).toString());
              articulo.setSerie(conjuntoResultados.getObject(11).toString());
              articulo.setTipoAuto(conjuntoResultados.getObject(12).toString());
              articulo.setPlacas(conjuntoResultados.getObject(13).toString());
              articulo.setModelo(conjuntoResultados.getObject(14).toString());
              articulo.setColor(conjuntoResultados.getObject(15).toString());
              articulo.setPoliza(conjuntoResultados.getObject(16).toString());
              articulo.setPuertas(conjuntoResultados.getObject(17).toString());
              articulo.setAsegurado(conjuntoResultados.getObject(18).toString());
              articulo.setSolicito(conjuntoResultados.getObject(19).toString());
              articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
              articulo.setCancelada(Integer.parseInt(conjuntoResultados.getObject(21).toString()));
              articulo.setFechaDevol(conjuntoResultados.getObject(22).toString());
              articulo.setObsCancel(conjuntoResultados.getObject(23).toString());
              articulo.setNomotor(conjuntoResultados.getObject(24).toString());
              articulo.setObservaciones(conjuntoResultados.getObject(25).toString());
              articulo.setNovale(conjuntoResultados.getObject(26).toString());
              

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
    
    public List<Reservaciones> consultaReservaciones2(String campo, String match, String condicion)
    {
       List<Reservaciones> articulos = new ArrayList<Reservaciones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
           if(match.equals("*")){
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReservaciones2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeReservaciones.php");
		  
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
           
	      Reservaciones articulo = new Reservaciones(); 
                
              articulo.setId(Long.parseLong(atributos[1]));
              articulo.setCodigo(Long.parseLong(atributos[2]));
              articulo.setFacturada(Integer.parseInt(atributos[3]));
              articulo.setFecha(atributos[4].substring(0,19));
              articulo.setCodcliente(atributos[5]);
              articulo.setCodventa(Long.parseLong(atributos[6]));
              articulo.setNomCli(atributos[7]);
              articulo.setSiniestro(atributos[8]);
              articulo.setTaller(atributos[9]);
              articulo.setMarca(atributos[10]);
              articulo.setSerie(atributos[11]);
              articulo.setTipoAuto(atributos[12]);
              articulo.setPlacas(atributos[13]);
              articulo.setModelo(atributos[14]);
              articulo.setColor(atributos[15]);
              articulo.setPoliza(atributos[16]);
              articulo.setPuertas(atributos[17]);
              articulo.setAsegurado(atributos[18]);
              articulo.setSolicito(atributos[19]);
              articulo.setDevolucion(Integer.parseInt(atributos[20]));
              articulo.setCancelada(Integer.parseInt(atributos[21]));
              articulo.setFechaDevol(atributos[22]);
              articulo.setObsCancel(atributos[23]);
              articulo.setNomotor(atributos[24]);
              articulo.setObservaciones(atributos[25]);
              articulo.setNovale(atributos[26]);
              
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,no   motor,observaciones,novale FROM reservaciones;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Reservaciones articulo = new Reservaciones(); 
                
              articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
              articulo.setCodigo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
              articulo.setFacturada(Integer.parseInt(conjuntoResultados.getObject(3).toString()));
              articulo.setFecha(conjuntoResultados.getObject(4).toString().substring(0,19));
              articulo.setCodcliente(conjuntoResultados.getObject(5).toString());
              articulo.setCodventa(Long.parseLong(conjuntoResultados.getObject(6).toString()));
              articulo.setNomCli(conjuntoResultados.getObject(7).toString());
              articulo.setSiniestro(conjuntoResultados.getObject(8).toString());
              articulo.setTaller(conjuntoResultados.getObject(9).toString());
              articulo.setMarca(conjuntoResultados.getObject(10).toString());
              articulo.setSerie(conjuntoResultados.getObject(11).toString());
              articulo.setTipoAuto(conjuntoResultados.getObject(12).toString());
              articulo.setPlacas(conjuntoResultados.getObject(13).toString());
              articulo.setModelo(conjuntoResultados.getObject(14).toString());
              articulo.setColor(conjuntoResultados.getObject(15).toString());
              articulo.setPoliza(conjuntoResultados.getObject(16).toString());
              articulo.setPuertas(conjuntoResultados.getObject(17).toString());
              articulo.setAsegurado(conjuntoResultados.getObject(18).toString());
              articulo.setSolicito(conjuntoResultados.getObject(19).toString());
              articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
              articulo.setCancelada(Integer.parseInt(conjuntoResultados.getObject(21).toString()));
              articulo.setFechaDevol(conjuntoResultados.getObject(22).toString());
              articulo.setObsCancel(conjuntoResultados.getObject(23).toString());
              articulo.setNomotor(conjuntoResultados.getObject(24).toString());
              articulo.setObservaciones(conjuntoResultados.getObject(25).toString());
              articulo.setNovale(conjuntoResultados.getObject(26).toString());
              

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
    
             public int guardarReservaciones(Reservaciones a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO reservaciones (" + 
                      "codigo,"
                    + "facturada,"
                    + "fecha,"
                    + "codcliente,"
                    + "codventa,"
                    + "nomCli," 
                    + "siniestro,"
                    + "taller,"
                    + "marca,"
                    + "serie,"
                    + "tipoAuto,"
                    + "placas,"
                    + "modelo,"
                    + "color,"
                    + "poliza,"
                    + "puertas,"
                    + "asegurado,"
                    + "solicito,"
                    + "devolucion,"
                    + "cancelada,"
                    + "fechaDevol,"
                    + "obsCancel,"
                    + "nomotor,"
                    + "observaciones,"
                    + "novale) VALUES" + "(\'" 
                    + a.getCodigo() + "\',\'" 
                    + a.getFacturada() + "\',\'" 
                    + fecha.substring(0,19) + "\',\'" 
                    + a.getCodcliente() + "\',\'" 
                    + a.getCodventa() + "\',\'" 
                    + a.getNomCli() + "\',\'" 
                    + a.getSiniestro() + "\',\'" 
                    + a.getTaller() + "\',\'" 
                    + a.getMarca() + "\',\'" 
                    + a.getSerie() + "\',\'" 
                    + a.getTipoAuto() + "\',\'" 
                    + a.getPlacas() + "\',\'" 
                    + a.getModelo() + "\',\'" 
                    + a.getColor() + "\',\'" 
                    + a.getPoliza() + "\',\'" 
                    + a.getPuertas() + "\',\'" 
                    + a.getAsegurado() + "\',\'" 
                    + a.getSolicito() + "\',\'" 
                    + a.getDevolucion() + "\',\'" 
                    + a.getCancelada() + "\',\'" 
                    + a.getFechaDevol() + "\',\'" 
                    + a.getObsCancel() + "\',\'" 
                    + a.getNomotor() + "\',\'" 
                    + a.getObservaciones() + "\',\'" 
                    + a.getNovale() + "');";
     //       Conexion conexion = new Conexion();
     //       conexion.crearConexion();
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
             
    public String crearQueryGuardarReservaciones(Reservaciones a)
    {
        
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO reservaciones (" + 
                      "codigo,"
                    + "facturada,"
                    + "fecha,"
                    + "codcliente,"
                    + "codventa,"
                    + "nomCli," 
                    + "siniestro,"
                    + "taller,"
                    + "marca,"
                    + "serie,"
                    + "tipoAuto,"
                    + "placas,"
                    + "modelo,"
                    + "color,"
                    + "poliza,"
                    + "puertas,"
                    + "asegurado,"
                    + "solicito,"
                    + "devolucion,"
                    + "cancelada,"
                    + "fechaDevol,"
                    + "obsCancel,"
                    + "nomotor,"
                    + "observaciones,"
                    + "novale) VALUES" + "(\'" 
                    + a.getCodigo() + "\',\'" 
                    + a.getFacturada() + "\',\'" 
                    + fecha.substring(0,19) + "\',\'" 
                    + a.getCodcliente() + "\',\'" 
                    + a.getCodventa() + "\',\'" 
                    + a.getNomCli() + "\',\'" 
                    + a.getSiniestro() + "\',\'" 
                    + a.getTaller() + "\',\'" 
                    + a.getMarca() + "\',\'" 
                    + a.getSerie() + "\',\'" 
                    + a.getTipoAuto() + "\',\'" 
                    + a.getPlacas() + "\',\'" 
                    + a.getModelo() + "\',\'" 
                    + a.getColor() + "\',\'" 
                    + a.getPoliza() + "\',\'" 
                    + a.getPuertas() + "\',\'" 
                    + a.getAsegurado() + "\',\'" 
                    + a.getSolicito() + "\',\'" 
                    + a.getDevolucion() + "\',\'" 
                    + a.getCancelada() + "\',\'" 
                    + a.getFechaDevol() + "\',\'" 
                    + a.getObsCancel() + "\',\'" 
                    + a.getNomotor() + "\',\'" 
                    + a.getObservaciones() + "\',\'" 
                    + a.getNovale() + "');";
     
            return query;
    }         
    
    public String crearQueryActualizarReservaciones(Reservaciones a, String campoCondicion,String match, String condicion)
    {
        
           java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
             String query = "UPDATE reservaciones SET codigo = \'" +  a.getCodigo()+"\',"+
                  "facturada = \'" +  a.getFacturada() +"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "codcliente = \'" +   a.getCodcliente()+"\',"+
                  "codventa = \'" +   a.getCodventa()+"\',"+
                  "nomCli = \'" +   a.getNomCli()+"\',"+
                  "siniestro = \'" +    a.getSiniestro()+"\',"+
                  "taller = \'" +    a.getTaller()+"\',"+
                  "marca = \'" +    a.getMarca()+"\',"+
                  "serie = \'" +    a.getSerie()+"\',"+
                  "tipoAuto = \'" +    a.getTipoAuto()+"\',"+
                  "placas = \'" +    a.getPlacas()+"\',"+
                  "modelo = \'" +    a.getModelo()+"\',"+
                  "color = \'" +    a.getColor()+"\',"+
                  "poliza = \'" +    a.getPoliza()+"\',"+
                  "puertas = \'" +    a.getPuertas()+"\',"+
                  "asegurado = \'" +    a.getAsegurado()+"\',"+
                  "solicito = \'" +    a.getSolicito()+"\',"+
                  "devolucion = \'" +    a.getDevolucion()+"\',"+
                  "cancelada = \'" +    a.getCancelada()+"\',"+
                  "fechaDevol = \'" +    a.getFechaDevol()+"\',"+
                  "nomotor = \'" +    a.getNomotor()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "novale = \'" +    a.getNovale()+"\',"+
                  "obsCancel = \'" +a.getObsCancel() +"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
        return query;
    }
    
     
            public int actualizarReservaciones(Reservaciones a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE reservaciones SET codigo = \'" +  a.getCodigo()+"\',"+
                  "facturada = \'" +  a.getFacturada() +"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "codcliente = \'" +   a.getCodcliente()+"\',"+
                  "codventa = \'" +   a.getCodventa()+"\',"+
                  "nomCli = \'" +   a.getNomCli()+"\',"+
                  "siniestro = \'" +    a.getSiniestro()+"\',"+
                  "taller = \'" +    a.getTaller()+"\',"+
                  "marca = \'" +    a.getMarca()+"\',"+
                  "serie = \'" +    a.getSerie()+"\',"+
                  "tipoAuto = \'" +    a.getTipoAuto()+"\',"+
                  "placas = \'" +    a.getPlacas()+"\',"+
                  "modelo = \'" +    a.getModelo()+"\',"+
                  "color = \'" +    a.getColor()+"\',"+
                  "poliza = \'" +    a.getPoliza()+"\',"+
                  "puertas = \'" +    a.getPuertas()+"\',"+
                  "asegurado = \'" +    a.getAsegurado()+"\',"+
                  "solicito = \'" +    a.getSolicito()+"\',"+
                  "devolucion = \'" +    a.getDevolucion()+"\',"+
                  "cancelada = \'" +    a.getCancelada()+"\',"+
                  "fechaDevol = \'" +    a.getFechaDevol()+"\',"+
                  "nomotor = \'" +    a.getNomotor()+"\',"+
                  "observaciones = \'" +    a.getObservaciones()+"\',"+
                  "novale = \'" +    a.getNovale()+"\',"+
                  "obsCancel = \'" +a.getObsCancel() +"\'";
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
            
        public int actualizarCliente(String clienteNuevo,String clienteViejo)
    {
        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //  conexion.crearConexion();
     String query = "UPDATE reservaciones SET codcliente = \'" + clienteNuevo+"\' WHERE codcliente = \'" + clienteViejo +"\'";
    
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
            
  
        public int borrarReservaciones(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
      
          if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }  
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }        
    
     public String crearQueryborrarReservacionesAR(String campo, String match, String condicion)
    {
      String query = "DELETE  FROM reservaciones WHERE id = @ultimo_id;";
      return query;
    }           
        
        
     public Reservaciones consultaUltimoRenglon(String campo, String match, String condicion)
    {
       Reservaciones articulo = new Reservaciones(); 
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
       
                     if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones ORDER BY id DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;";
            }else
            {
            query = "SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'   ORDER BY id DESC LIMIT 1;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeReservaciones.php");
		  
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
           String[] objetos = s.split("°");
           for(int u=0; u<objetos.length; u++)
           {
           String[] atributos = objetos[u].split("~");
		   
		   //Arma el objeto y agrega a la lista en su caso
           
                
              articulo.setId(Long.parseLong(atributos[1]));
              articulo.setCodigo(Long.parseLong(atributos[2]));
              articulo.setFacturada(Integer.parseInt(atributos[3]));
              articulo.setFecha(atributos[4]);
              articulo.setCodcliente(atributos[5]);
              articulo.setCodventa(Long.parseLong(atributos[6]));
              articulo.setNomCli(atributos[7]);
              articulo.setSiniestro(atributos[8]);
              articulo.setTaller(atributos[9]);
              articulo.setMarca(atributos[10]);
              articulo.setSerie(atributos[11]);
              articulo.setTipoAuto(atributos[12]);
              articulo.setPlacas(atributos[13]);
              articulo.setModelo(atributos[14]);
              articulo.setColor(atributos[15]);
              articulo.setPoliza(atributos[16]);
              articulo.setPuertas(atributos[17]);
              articulo.setAsegurado(atributos[18]);
              articulo.setSolicito(atributos[19]);
              articulo.setDevolucion(Integer.parseInt(atributos[20]));
              articulo.setCancelada(Integer.parseInt(atributos[21]));
              articulo.setFechaDevol(atributos[22]);
              articulo.setObsCancel(atributos[23]);
                   
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
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones ORDER BY id DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,facturada,fecha,codcliente,codventa,nomCli,siniestro,taller,marca,serie,tipoAuto,placas,modelo,color,poliza,puertas,asegurado,solicito,devolucion,cancelada,fechaDevol,obsCancel,nomotor,observaciones,novale FROM reservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\'   ORDER BY id DESC LIMIT 1;");
            }
            
            if(conjuntoResultados.next())
            {
            do {
              
              articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
              articulo.setCodigo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
              articulo.setFacturada(Integer.parseInt(conjuntoResultados.getObject(3).toString()));
              articulo.setFecha(conjuntoResultados.getObject(4).toString());
              articulo.setCodcliente(conjuntoResultados.getObject(5).toString());
              articulo.setCodventa(Long.parseLong(conjuntoResultados.getObject(6).toString()));
              articulo.setNomCli(conjuntoResultados.getObject(7).toString());
              articulo.setSiniestro(conjuntoResultados.getObject(8).toString());
              articulo.setTaller(conjuntoResultados.getObject(9).toString());
              articulo.setMarca(conjuntoResultados.getObject(10).toString());
              articulo.setSerie(conjuntoResultados.getObject(11).toString());
              articulo.setTipoAuto(conjuntoResultados.getObject(12).toString());
              articulo.setPlacas(conjuntoResultados.getObject(13).toString());
              articulo.setModelo(conjuntoResultados.getObject(14).toString());
              articulo.setColor(conjuntoResultados.getObject(15).toString());
              articulo.setPoliza(conjuntoResultados.getObject(16).toString());
              articulo.setPuertas(conjuntoResultados.getObject(17).toString());
              articulo.setAsegurado(conjuntoResultados.getObject(18).toString());
              articulo.setSolicito(conjuntoResultados.getObject(19).toString());
              articulo.setDevolucion(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
              articulo.setCancelada(Integer.parseInt(conjuntoResultados.getObject(21).toString()));
              articulo.setFechaDevol(conjuntoResultados.getObject(22).toString());
              articulo.setObsCancel(conjuntoResultados.getObject(23).toString());
                                  
            }while (conjuntoResultados.next());
            return articulo;  
          }else 
             return null;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
        
     public void facturar(String id)
     {
          int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
            String query = "UPDATE reservaciones SET "+
                  "facturada = \'1\'"+ " WHERE id = \'"+id+"\';";
            
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      resultado = conexionweb.escribirEnWeb(query);
       }     
            
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     }
     
     public String crearQueryFacturar(String id)
     {

            String query = "UPDATE reservaciones SET "+
                  "facturada = \'1\'"+ " WHERE id = \'"+id+"\';";
            
           return query;

     }
           
            public static void main(String args[])
    {
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
       hReservaciones ejemplo = new hReservaciones();
       List<Reservaciones> articulos = ejemplo.consultaReservaciones("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFecha());
           i++;
       } 
        System.out.println(ejemplo.borrarReservaciones("id", "=", "1"));

       Reservaciones articulo = new Reservaciones();
              articulo.setCodigo((long)7);
              articulo.setFacturada(1);
              articulo.setCodcliente("9");
              articulo.setCodventa((long)12);
              articulo.setNomCli("5");
              articulo.setSiniestro("2");
              articulo.setTaller("1");
              articulo.setMarca("6");
              articulo.setSerie("7");
              articulo.setTipoAuto("8");
              articulo.setPlacas("9");
              articulo.setModelo("10");
              articulo.setColor("11");
              articulo.setPoliza("12");
              articulo.setPuertas("13");
              articulo.setAsegurado("14");
              articulo.setSolicito("15");
              articulo.setDevolucion(16);
              articulo.setCancelada(17);
              articulo.setFechaDevol(fecha.substring(0,19));
              articulo.setObsCancel("19");
                     

     //  System.out.println(ejemplo.guardarReservaciones(articulo));

       articulo.setSolicito("20");
     //  System.out.println(ejemplo.actualizarReservaciones(articulo, "id", "=", "2"));
        
        
    }
        
        
}
