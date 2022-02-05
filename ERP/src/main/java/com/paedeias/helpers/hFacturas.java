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
import com.paedeias.identidades.Factura;
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


public class hFacturas {
    
        public hFacturas()
         {}
        
        /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Factura> consultaFacturas(String campo, String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
            if(match.equals("*")){
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura ORDER BY idFactura DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura ORDER BY idFactura DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
        public List<Factura> consultaFacturas2(String campo, String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
       // Conexion conexion = new Conexion();
       // conexion.crearConexion();
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;";
            }
            else if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturas2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
       public List<Factura> consultaFacturasVentas(String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       
          // conexion.crearConexion();
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Venta%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Venta%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturasVentas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Venta%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Venta%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
     public List<Factura> consultaFacturasReservacion(String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Reser%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Reser%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturasReservacion()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Reser%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Reser%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
 
            public List<Factura> consultaFacturasUsado(String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
      // Conexion conexion = new Conexion();
      // conexion.crearConexion();
       
                      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Usado%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Usado%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturasUsado()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Usado%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Usado%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
            
    public List<Factura> consultaFacturasNuevo(String match, String condicion)
    {
       List<Factura> facturas = new ArrayList<Factura>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
                             if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Nuevo%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Nuevo%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;";
            }
                
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturasNuevo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Nuevo%' AND numMovimiento "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE movimiento LIKE '%Nuevo%' AND numMovimiento "+match+" \'%"+condicion+"%\' ORDER BY idFactura DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
       
        /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Factura> consultaFacturasPeriodo(String fecha1, String fecha2)
    {
       List<Factura> facturas = new ArrayList<Factura>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
                                    if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
 
             query =  "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE fechaReporte BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND cancelada = 0;";               
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaFacturasPeriodo()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
	        Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);
                facturas.add(factura);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return facturas;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE fechaReporte BETWEEN"+" \'"+fecha1+" 00:00:00\' AND \'"+fecha2+" 23:59:59\' AND cancelada = 0;");

                       
            while (conjuntoResultados.next()) {
                
                Factura factura = new Factura(); 
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
     public int actualizarCantidadArticulos(int idFactura,int cantidad)
    {     
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE factura SET "+
                  "cantidadArticulos = \'" +cantidad +"\'";
            query = query + " WHERE idFactura = "+" \'"+idFactura+"\';";
            
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
    
            /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public Factura consultaUltimaFactura(String campo, String match, String condicion)
    {
       Factura factura = new Factura(); 
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
	    
            if(match.equals("*")){
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura ORDER BY idFactura DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 1;";
            }else
            {
            query = "SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY idFactura DESC LIMIT 1;";
            }
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUltimaFactura()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeFacturas.php");
		  
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
           
                factura.setIdFactura(Long.parseLong(atributos[1]));
                factura.setVersion(atributos[2]);
                factura.setSerie(atributos[3]);
                factura.setFolio(atributos[4]);
                factura.setFecha(atributos[5]);
                factura.setNoAprobacion(atributos[6]);
                factura.setAnoAprobacion(atributos[7]);
                factura.setFormaDePago(atributos[8]);
                factura.setSubtotal(atributos[9]);
                factura.setDescuento(atributos[10]);
                factura.setTotal(atributos[11]);
                factura.setTotalLetra(atributos[12]);
                factura.setTipoDeComprobante(atributos[13]);
                factura.setNoCertificado(atributos[14]);
                factura.setCertificado(atributos[15]);
                factura.setSello(atributos[16]);
                factura.setIdEmisorF(Integer.parseInt(atributos[17]));
                factura.setIdReceptorF(Integer.parseInt(atributos[18]));
                factura.setIdConceptoFactura(atributos[19]);
                factura.setImpuesto(atributos[20]);
                factura.setTasa(atributos[21]);
                factura.setImporteImp(atributos[22]);
                factura.setFolioInterno(atributos[23]);
                factura.setRegimen(atributos[24]);
                factura.setCadenaCompleta(atributos[25]);
                factura.setMotivo(atributos[26]);
                factura.setFechaReporte(atributos[27]);
                factura.setCantidadArticulos(Integer.valueOf(atributos[28]));
                factura.setMovimiento(atributos[29]);
                factura.setNumMovimiento(atributos[30]);
                factura.setCancelada(Integer.valueOf(atributos[31]));
                factura.setVersionTimbre(atributos[32]);
                factura.setUuid(atributos[33]);
                factura.setFechaTimbrado(atributos[34]);  
                factura.setSelloSAT(atributos[35]);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return factura;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura ORDER BY idFactura DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\'  ORDER BY idFactura DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT idFactura,version,serie,folio,fecha,noAprobacion,anoAprobacion,formaDePago,subtotal,descuento,total,totalLetra,tipoDeComprobante,noCertificado,certificado,sello,idEmisorF,idReceptorF,idConceptoFactura,impuesto,tasa,importeImp,folioInterno,regimen,cadenaCompleta,motivo,fechaReporte,cantidadArticulos,movimiento,numMovimiento,cancelada,versionTimbre,uuid,fechaTimbrado,selloSAT FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\'  ORDER BY idFactura DESC LIMIT 1;");
            }
            
            if(conjuntoResultados.next())
            {        
            do {
                factura.setIdFactura(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                factura.setVersion(conjuntoResultados.getObject(2).toString());
                factura.setSerie(conjuntoResultados.getObject(3).toString());
                factura.setFolio(conjuntoResultados.getObject(4).toString());
                factura.setFecha(conjuntoResultados.getObject(5).toString());
                factura.setNoAprobacion(conjuntoResultados.getObject(6).toString());
                factura.setAnoAprobacion(conjuntoResultados.getObject(7).toString());
                factura.setFormaDePago(conjuntoResultados.getObject(8).toString());
                factura.setSubtotal(conjuntoResultados.getObject(9).toString());
                factura.setDescuento(conjuntoResultados.getObject(10).toString());
                factura.setTotal(conjuntoResultados.getObject(11).toString());
                factura.setTotalLetra(conjuntoResultados.getObject(12).toString());
                factura.setTipoDeComprobante(conjuntoResultados.getObject(13).toString());
                factura.setNoCertificado(conjuntoResultados.getObject(14).toString());
                factura.setCertificado(conjuntoResultados.getObject(15).toString());
                factura.setSello(conjuntoResultados.getObject(16).toString());
                factura.setIdEmisorF(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                factura.setIdReceptorF(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                factura.setIdConceptoFactura(conjuntoResultados.getObject(19).toString());
                factura.setImpuesto(conjuntoResultados.getObject(20).toString());
                factura.setTasa(conjuntoResultados.getObject(21).toString());
                factura.setImporteImp(conjuntoResultados.getObject(22).toString());
                factura.setFolioInterno(conjuntoResultados.getObject(23).toString());
                factura.setRegimen(conjuntoResultados.getObject(24).toString());
                factura.setCadenaCompleta(conjuntoResultados.getObject(25).toString());
                factura.setMotivo(conjuntoResultados.getObject(26).toString());
                factura.setFechaReporte(conjuntoResultados.getObject(27).toString());
                factura.setCantidadArticulos(Integer.valueOf(conjuntoResultados.getObject(28).toString()));
                factura.setMovimiento(conjuntoResultados.getObject(29).toString());
                factura.setNumMovimiento(conjuntoResultados.getObject(30).toString());
                factura.setCancelada(Integer.valueOf(conjuntoResultados.getObject(31).toString()));
                factura.setVersionTimbre(conjuntoResultados.getObject(32).toString());
                factura.setUuid(conjuntoResultados.getObject(33).toString());
                factura.setFechaTimbrado(conjuntoResultados.getObject(34).toString());
                if(conjuntoResultados.getObject(35)==null)
                factura.setSelloSAT("");    
                else
                factura.setSelloSAT(conjuntoResultados.getObject(35).toString());
            } while (conjuntoResultados.next());
            return factura;
            }
            else
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(hFacturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
         public int guardarFactura(Factura a)
    {
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSZ");
            String fecha2 = sdf2.format(time); 
         
            int ultimoInsertado=-1;
            String query = " INSERT INTO factura (" 
                  + "idFactura,"  
                  + "version,"
                  + "serie,"
                  + "folio,"
                  + "fecha,"
                  + "noAprobacion," 
                  + "anoAprobacion,"
                  + "formaDePago,"
                  + "subtotal,"
                  + "descuento,"
                  + "total,"
                  + "totalLetra,"
                  + "tipoDeComprobante,"
                  + "noCertificado,"
                  + "certificado,"
                  + "sello,"
                  + "idEmisorF,"
                  + "idReceptorF,"
                  + "idConceptoFactura,"
                  + "impuesto,"
                  + "tasa,"
                  + "importeImp,"
                  + "folioInterno,"
                  + "regimen,"
                  + "cadenaCompleta,"
                  + "motivo,"  
                  + "fechaReporte,"
                    + "cantidadArticulos,"
                    + "movimiento,"
                    + "numMovimiento) VALUES" + "(\'" 
                    + a.getIdFactura()
                    + "\',\'" + a.getVersion()
                    + "\',\'" + a.getSerie()
                    + "\',\'" + a.getFolio() 
                    + "\',\'" + fecha.substring(0,19) 
                    + "\',\'" + a.getNoAprobacion()
                    + "\',\'" + a.getAnoAprobacion() 
                    + "\',\'" + a.getFormaDePago() 
                    + "\',\'" + a.getSubtotal()  
                    + "\',\'" + a.getDescuento()
                    + "\',\'" + a.getTotal()
                    + "\',\'" + a.getTotalLetra()
                    + "\',\'" + a.getTipoDeComprobante()
                    + "\',\'" + a.getNoCertificado()
                    + "\',\'" + a.getCertificado()
                    + "\',\'" + a.getSello()
                    + "\',\'" + a.getIdEmisorF()
                    + "\',\'" + a.getIdReceptorF()
                    + "\',\'" + a.getIdConceptoFactura()
                    + "\',\'" + a.getImpuesto()
                    + "\',\'" + a.getTasa()
                    + "\',\'" + a.getImporteImp()
                    + "\',\'" + a.getFolioInterno()
                    + "\',\'"+ a.getRegimen() 
                    + "\',\'"+ a.getCadenaCompleta() 
                    + "\',\'"+ a.getMotivo() 
                    + "\',\'"+ fecha2.substring(0,19) 
                    + "\',\'"+ a.getCantidadArticulos()
                    + "\',\'"+ a.getMovimiento()
                    + "\',\'"+ a.getNumMovimiento()
                    + "');";
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

    
     public int borrarFactura(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM factura WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM factura WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
     
      public int actualizarFactura(Factura a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSZ");
            String fecha2 = sdf2.format(time); 
        
     int resultado = 0;
     // Conexion conexion = new Conexion();
     //         conexion.crearConexion();



     String query = "UPDATE factura SET " +
                  "version = \'" +   a.getVersion()+"\',"+
                  "serie = \'" +   a.getSerie()+"\',"+
                  "folio = \'" +   a.getFolio()+"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "fechaReporte = \'" +   fecha2.substring(0,19)+"\',"+
                  "noaprobacion = \'" +   a.getNoAprobacion()+"\',"+
                  "anoAprobacion = \'" +    a.getAnoAprobacion()+"\',"+
                  "formaDePago = \'" +    a.getFormaDePago()+"\',"+
                  "subtotal = \'" +     a.getSubtotal()+"\',"+
                  "descuento = \'" +     a.getDescuento()+"\',"+
                  "total = \'" +     a.getTotal()+"\',"+
                  "totalLetra = \'" +     a.getTotalLetra()+"\',"+
                  "tipoDeComprobante = \'" +     a.getTipoDeComprobante()+"\',"+
                  "noCertificado = \'" +     a.getNoCertificado()+"\',"+
                  "certificado = \'" +     a.getCertificado()+"\',"+
                  "sello = \'" +     a.getSello()+"\',"+
                  "idEmisorF = \'" +     a.getIdEmisorF()+"\',"+
                  "idReceptorF = \'" +     a.getIdReceptorF()+"\',"+
                  "idConceptoFactura = \'" +     a.getIdConceptoFactura()+"\',"+
                  "impuesto = \'" +     a.getImpuesto()+"\',"+
                  "tasa = \'" +     a.getTasa()+"\',"+
                  "importeImp = \'" +     a.getImporteImp()+"\',"+
                  "folioInterno = \'" +     a.getFolioInterno()+"\',"+
                  "regimen = \'" +     a.getRegimen()+"\',"+
                  "cadenaCompleta = \'" +     a.getCadenaCompleta()+"\',"+
                  "cantidadArticulos = \'" +     a.getCantidadArticulos()+"\',"+
                  "movimiento = \'" +     a.getMovimiento()+"\',"+
                  "numMovimiento = \'" +     a.getNumMovimiento()+"\',"+
                  "motivo = \'" +    a.getMotivo()+"\'";
     
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
   
          public int actualizarFacturaCFDI(Factura a,String campoCondicion,String match,String condicion)
    {
             
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSZ");
            String fecha2 = sdf2.format(time); 
        
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();



     String query = "UPDATE factura SET " +
                  "version = \'" +   a.getVersion()+"\',"+
                  "serie = \'" +   a.getSerie()+"\',"+
                  "folio = \'" +   a.getFolio()+"\',"+
                  "fecha = \'" +   fecha.substring(0,19)+"\',"+
                  "fechaReporte = \'" +   fecha2.substring(0,19)+"\',"+
                  "noaprobacion = \'" +   a.getNoAprobacion()+"\',"+
                  "anoAprobacion = \'" +    a.getAnoAprobacion()+"\',"+
                  "formaDePago = \'" +    a.getFormaDePago()+"\',"+
                  "subtotal = \'" +     a.getSubtotal()+"\',"+
                  "descuento = \'" +     a.getDescuento()+"\',"+
                  "total = \'" +     a.getTotal()+"\',"+
                  "totalLetra = \'" +     a.getTotalLetra()+"\',"+
                  "tipoDeComprobante = \'" +     a.getTipoDeComprobante()+"\',"+
                  "noCertificado = \'" +     a.getNoCertificado()+"\',"+
                  "certificado = \'" +     a.getCertificado()+"\',"+
                  "sello = \'" +     a.getSello()+"\',"+
                  "idEmisorF = \'" +     a.getIdEmisorF()+"\',"+
                  "idReceptorF = \'" +     a.getIdReceptorF()+"\',"+
                  "idConceptoFactura = \'" +     a.getIdConceptoFactura()+"\',"+
                  "impuesto = \'" +     a.getImpuesto()+"\',"+
                  "tasa = \'" +     a.getTasa()+"\',"+
                  "importeImp = \'" +     a.getImporteImp()+"\',"+
                  "folioInterno = \'" +     a.getFolioInterno()+"\',"+
                  "regimen = \'" +     a.getRegimen()+"\',"+
                  "cadenaCompleta = \'" +     a.getCadenaCompleta()+"\',"+
                  "cantidadArticulos = \'" +     a.getCantidadArticulos()+"\',"+
                  "movimiento = \'" +     a.getMovimiento()+"\',"+
                  "numMovimiento = \'" +     a.getNumMovimiento()+"\',"+
                  "versionTimbre = \'" +     a.getVersionTimbre()+"\',"+
                  "uuid = \'" +     a.getUuid()+"\',"+
                  "fechaTimbrado = \'" +     a.getFechaTimbrado()+"\',"+
                  "selloSAT = \'" +     a.getSelloSAT()+"\',"+
                  "motivo = \'" +    a.getMotivo()+"\'";
     
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
                   
       // System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }  
      
      
    public int cancelar(String id)
    { 
     int resultado;
     resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();

     String query = "UPDATE factura SET " +
                    "cancelada = \'1\'";
             query = query + " WHERE folio = \'"+id+"\';";
      
    if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      resultado = conexionweb.escribirEnWeb(query);
      return resultado;
       }         
             
       resultado = CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return resultado;
    }
      
    public int actualizarCadenaSello(String id, String cadena, String sello)
    {
             
         
        
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE factura SET " +
                  "sello = \'" + sello+"\',"+
                  "cadenaCompleta = \'" + cadena +"\'";
            query = query + " WHERE idFactura = \'"+id+"\';";
            
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
      
      
              public static void main(String args[])
    {
        hFacturas ejemplo = new hFacturas();
       List<Factura> facturas = ejemplo.consultaFacturas("idFactura","=","1");
       int i = 0;
       while(i < facturas.size())
       {
           System.out.println(facturas.get(i).getSello());
           i++;
       } 
       System.out.println(ejemplo.borrarFactura("idFactura", "=", "2"));
       
       
       Factura fac = new Factura();
       fac.setAnoAprobacion("2012"); 
       fac.setCertificado("9328kadk20");
       fac.setDescuento("0");
       fac.setFolio("2");
       fac.setFolioInterno("v93j");
       fac.setFormaDePago("credito");
       fac.setRegimen("1");
       fac.setIdConceptoFactura("2");
       fac.setIdEmisorF(6); 
       fac.setIdReceptorF(4);
       fac.setImporteImp("2983");
       fac.setImpuesto("394");
       fac.setNoAprobacion("192");
       fac.setNoCertificado("9382");
       fac.setSello("kasjdj294828a");
       fac.setSerie("");
       fac.setSubtotal("4883.1");
       fac.setTasa("16");
       fac.setTipoDeComprobante("Factura");
       fac.setTotal("3942.01");
       fac.setTotalLetra("DOS");
       fac.setVersion("2.0");
                     

     // System.out.println(ejemplo.guardarFactura(fac));

     //   fac.setSello("Sello");
     //   System.out.println(ejemplo.actualizarFactura(fac, "idFactura", "=", "2"));
       
       
       
    }
      
         
}
