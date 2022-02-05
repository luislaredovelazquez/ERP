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
import com.paedeias.identidades.Partidascompras;
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



public class hPartidasCompras {
    
    public hPartidasCompras(){}
    
      /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Partidascompras> consultaPCompras(String campo, String match, String condicion)
    {
       List<Partidascompras> articulos = new ArrayList<Partidascompras>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPCompras()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasCompras.php");
		  
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
           
           Partidascompras articulo = new Partidascompras();
                
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setArticulo(atributos[3]);
                articulo.setCantidad(Integer.parseInt(atributos[4]));
                articulo.setPrecioUni(Double.parseDouble(atributos[5]));
                articulo.setDescuento(Double.parseDouble(atributos[6]));
                articulo.setDescuentoArti(Double.parseDouble(atributos[7]));
                articulo.setCostoDesc(Double.parseDouble(atributos[8]));
                articulo.setSubtotal(Double.parseDouble(atributos[9]));
                articulo.setMontoIva(Double.parseDouble(atributos[10]));
                articulo.setTotal(Double.parseDouble(atributos[11]));
                articulo.setIva(Integer.parseInt(atributos[12]));
                articulo.setPorcCargo(Integer.parseInt(atributos[13]));
                articulo.setCargoArticulo(Double.parseDouble(atributos[14]));
                articulo.setDevuelta(Integer.parseInt(atributos[15]));
                articulo.setPrecioVenta(Double.parseDouble(atributos[16]));
                articulo.setDescripcion(atributos[17]);
                articulo.setPrecioCompra(Double.parseDouble(atributos[18]));
                articulo.setStockMin(Integer.parseInt(atributos[19]));
                articulo.setStockMax(Integer.parseInt(atributos[20]));
                articulo.setUtilidad(Double.parseDouble(atributos[21]));
                articulo.setCascada(Integer.parseInt(atributos[22]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Partidascompras articulo = new Partidascompras();
                
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setPrecioUni(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescuento(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setDescuentoArti(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                articulo.setCostoDesc(Double.parseDouble(conjuntoResultados.getObject(8).toString()));
                articulo.setSubtotal(Double.parseDouble(conjuntoResultados.getObject(9).toString()));
                articulo.setMontoIva(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                articulo.setTotal(Double.parseDouble(conjuntoResultados.getObject(11).toString()));
                articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setPorcCargo(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setCargoArticulo(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(16).toString()));
                articulo.setDescripcion(conjuntoResultados.getObject(17).toString());
                articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(18).toString()));
                articulo.setStockMin(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                articulo.setStockMax(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                articulo.setUtilidad(Double.parseDouble(conjuntoResultados.getObject(21).toString()));
                articulo.setCascada(Integer.parseInt(conjuntoResultados.getObject(22).toString()));
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
    
        public List<Partidascompras> consultaPDevueltas(String campo, String match, String condicion)
    {
       List<Partidascompras> articulos = new ArrayList<Partidascompras>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
         if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
             if(match.equals("="))
            {
            query = "SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\' AND devuelta = \'1\';";
            }else
            {
            query = "SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND devuelta = \'1\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPDevueltas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasCompras.php");
		  
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
           
           Partidascompras articulo = new Partidascompras();
                
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCompra(Long.parseLong(atributos[2]));
                articulo.setArticulo(atributos[3]);
                articulo.setCantidad(Integer.parseInt(atributos[4]));
                articulo.setPrecioUni(Double.parseDouble(atributos[5]));
                articulo.setDescuento(Double.parseDouble(atributos[6]));
                articulo.setDescuentoArti(Double.parseDouble(atributos[7]));
                articulo.setCostoDesc(Double.parseDouble(atributos[8]));
                articulo.setSubtotal(Double.parseDouble(atributos[9]));
                articulo.setMontoIva(Double.parseDouble(atributos[10]));
                articulo.setTotal(Double.parseDouble(atributos[11]));
                articulo.setIva(Integer.parseInt(atributos[12]));
                articulo.setPorcCargo(Integer.parseInt(atributos[13]));
                articulo.setCargoArticulo(Double.parseDouble(atributos[14]));
                articulo.setDevuelta(Integer.parseInt(atributos[15]));
                articulo.setPrecioVenta(Double.parseDouble(atributos[16]));
                articulo.setDescripcion(atributos[17]);
                articulo.setPrecioCompra(Double.parseDouble(atributos[18]));
                articulo.setStockMin(Integer.parseInt(atributos[19]));
                articulo.setStockMax(Integer.parseInt(atributos[20]));
                articulo.setUtilidad(Double.parseDouble(atributos[21]));
                articulo.setCascada(Integer.parseInt(atributos[22]));
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
           if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\' AND devuelta = \'1\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,compra,articulo,cantidad,precioUni,descuento,descuentoArti,costoDesc,subtotal,montoIva,total,iva,porcCargo,cargoArticulo,devuelta,precioVenta,descripcion,precioCompra,stockMin,stockMax,utilidad,cascada FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND devuelta = \'1\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Partidascompras articulo = new Partidascompras();
                
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCompra(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setPrecioUni(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setDescuento(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                articulo.setDescuentoArti(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                articulo.setCostoDesc(Double.parseDouble(conjuntoResultados.getObject(8).toString()));
                articulo.setSubtotal(Double.parseDouble(conjuntoResultados.getObject(9).toString()));
                articulo.setMontoIva(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                articulo.setTotal(Double.parseDouble(conjuntoResultados.getObject(11).toString()));
                articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(12).toString()));
                articulo.setPorcCargo(Integer.parseInt(conjuntoResultados.getObject(13).toString()));
                articulo.setCargoArticulo(Double.parseDouble(conjuntoResultados.getObject(14).toString()));
                articulo.setDevuelta(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(16).toString()));
                articulo.setDescripcion(conjuntoResultados.getObject(17).toString());
                articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(18).toString()));
                articulo.setStockMin(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                articulo.setStockMax(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                articulo.setUtilidad(Double.parseDouble(conjuntoResultados.getObject(21).toString()));
                articulo.setCascada(Integer.parseInt(conjuntoResultados.getObject(22).toString()));
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
    
  /*             public int consultaArticulos(String factura)
    {
       int total = 0; 
       Conexion conexion = new Conexion();
                conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = conexion.crearConsulta("SELECT SUM(cantidad) AS total FROM partidascompras WHERE compra = \'"+factura+"\';");
           
            while (conjuntoResultados.next()) {
             total = Integer.parseInt(conjuntoResultados.getObject(1).toString());
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        conexion.cerrar(0);
        }
        return 0;
    } */
        
        
             public int guardarPCompras(Partidascompras a)
    {        
            int ultimoInsertado=-1;
            String query = " INSERT INTO partidascompras (" + 
                      "compra,"
                    + "articulo,"
                    + "cantidad,"
                    + "precioUni,"
                    + "descuento,"
                    + "descuentoArti," 
                    + "costoDesc,"
                    + "subtotal,"
                    + "montoIva,"
                    + "total,"
                    + "iva,"
                    + "porcCargo,"
                    + "cargoArticulo,"
                    + "precioVenta,"
                    + "descripcion,"
                    + "precioCompra,"
                    + "stockMin,"
                    + "stockMax,"
                    + "utilidad,"
                    + "cascada) VALUES" + "(\'" 
                    + a.getCompra() + "\',\'" 
                    + a.getArticulo() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getPrecioUni() + "\',\'" 
                    + a.getDescuento() + "\',\'" 
                    + a.getDescuentoArti() + "\',\'" 
                    + a.getCostoDesc() + "\',\'" 
                    + a.getSubtotal() + "\',\'" 
                    + a.getMontoIva() + "\',\'" 
                    + a.getTotal() + "\',\'" 
                    + a.getIva() + "\',\'" 
                    + a.getPorcCargo() + "\',\'" 
                    + a.getCargoArticulo() + "\',\'" 
                    + a.getPrecioVenta() + "\',\'" 
                    + a.getDescripcion() + "\',\'" 
                    + a.getPrecioCompra() + "\',\'" 
                    + a.getStockMin() + "\',\'" 
                    + a.getStockMax() + "\',\'" 
                    + a.getUtilidad() + "\',\'" 
                    + a.getCascada() + "');";
      //      Conexion conexion = new Conexion();
      //      conexion.crearConexion();
            
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
             
          public int actualizarPCompras(Partidascompras a,String campoCondicion,String match,String condicion)
    {
      int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE partidascompras SET compra = \'" +  a.getCompra()+"\',"+
                  "articulo = \'" +  a.getArticulo() +"\',"+
                  "cantidad = \'" +   a.getCantidad()+"\',"+
                  "precioUni = \'" +   a.getPrecioUni()+"\',"+
                  "descuento = \'" +   a.getDescuento()+"\',"+
                  "descuentoArti = \'" +   a.getDescuentoArti()+"\',"+
                  "costoDesc = \'" +    a.getCostoDesc()+"\',"+
                  "subtotal = \'" +    a.getSubtotal()+"\',"+
                  "montoIva = \'" +    a.getMontoIva()+"\',"+
                  "total = \'" +    a.getTotal()+"\',"+
                  "iva = \'" +    a.getIva()+"\',"+
                  "porcCargo = \'" +    a.getPorcCargo()+"\',"+
                  "precioVenta = \'" +    a.getPrecioVenta()+"\',"+
                  "precioCompra = \'" +    a.getPrecioCompra()+"\',"+
                  "stockMin = \'" +    a.getStockMin()+"\',"+
                  "stockMax = \'" +    a.getStockMax()+"\',"+
                  "utilidad = \'" +    a.getUtilidad()+"\',"+
                  "descripcion = \'" +    a.getDescripcion()+"\',"+
                  "cascada = \'" +    a.getCascada()+"\',"+
                  "cargoArticulo = \'" +a.getCargoArticulo() +"\'";
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
    
    public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE partidascompras SET articulo = \'"+codigoNuevo+"\' WHERE articulo = \'"+codigoViejo+"\';";

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
          
          
      public int devolver(String campoCondicion,String match,String condicion)
    {
      int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();

     String query = "UPDATE partidascompras SET "+
                  "devuelta = \'1\'";
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
      
    public String crearQueryDevolver(String campoCondicion,String match,String condicion)
    {

     String query = "UPDATE partidascompras SET "+
                  "devuelta = \'1\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
       
     return query;
    }   
      
             
       public int borrarPCompras(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     } 
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidascompras WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidascompras WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }      
          
               public static void main(String args[])
    {

       hPartidasCompras ejemplo = new hPartidasCompras();
       List<Partidascompras> articulos = ejemplo.consultaPCompras("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getCompra());
           i++;
       } 
        System.out.println(ejemplo.borrarPCompras("id", "=", "1"));

                Partidascompras articulo = new Partidascompras();     
                articulo.setCompra((long)1);
                articulo.setArticulo("2");
                articulo.setCantidad(3);
                articulo.setPrecioUni(4.0);
                articulo.setDescuento(5.0);
                articulo.setDescuentoArti(6.0);
                articulo.setCostoDesc(7.0);
                articulo.setSubtotal(8.0);
                articulo.setMontoIva(9.0);
                articulo.setTotal(10.0);
                articulo.setIva(11);
                articulo.setPorcCargo(12);
                articulo.setCargoArticulo(13.0);
                     

      // System.out.println(ejemplo.guardarPCompras(articulo));

       articulo.setCargoArticulo(99.0);
       System.out.println(ejemplo.actualizarPCompras(articulo, "id", "=", "2"));
        
        
    }
}
