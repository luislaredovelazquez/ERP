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
import com.paedeias.identidades.Pedidos;
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

public class hPedidos {
 
    public hPedidos(){}
    
       /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Pedidos> consultaPedidos(String campo, String match, String condicion)
    {
       List<Pedidos> articulos = new ArrayList<Pedidos>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
        query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
        query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
        query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePedidos.php");
		  
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
           
	      Pedidos articulo = new Pedidos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdArticulo(Long.parseLong(atributos[2]));
                articulo.setCodigoArticulo(atributos[3]);
                articulo.setDescripcion(atributos[4]);
                articulo.setCosto(Double.parseDouble(atributos[5]));
                articulo.setCantidad(Integer.parseInt(atributos[6]));
                articulo.setFechaIni(atributos[7]);
                articulo.setFechaFin(atributos[8]);
                articulo.setProveedor(Long.parseLong(atributos[9]));
                articulo.setSurtido(Integer.parseInt(atributos[10]));
                articulo.setDe(atributos[11]);
                articulo.setReferencia(Long.parseLong(atributos[12]));
                articulo.setPrecio(Double.parseDouble(atributos[13]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Pedidos articulo = new Pedidos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(4).toString());
                articulo.setCosto(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                articulo.setFechaIni(conjuntoResultados.getObject(7).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(8).toString());
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(10).toString()));
                articulo.setDe(conjuntoResultados.getObject(11).toString());
                articulo.setReferencia(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                articulo.setPrecio(Double.parseDouble(conjuntoResultados.getObject(13).toString()));
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
    
    public List<Pedidos> consultaPedidos2(String campo, String match, String condicion)
    {
       List<Pedidos> articulos = new ArrayList<Pedidos>();
     //  Conexion conexion = new Conexion();
     //  conexion.crearConexion();
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(match.equals("*")){
            query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePedidos.php");
		  
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
           
	       
                Pedidos articulo = new Pedidos(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setIdArticulo(Long.parseLong(atributos[2]));
                articulo.setCodigoArticulo(atributos[3]);
                articulo.setDescripcion(atributos[4]);
                articulo.setCosto(Double.parseDouble(atributos[5]));
                articulo.setCantidad(Integer.parseInt(atributos[6]));
                articulo.setFechaIni(atributos[7]);
                articulo.setFechaFin(atributos[8]);
                articulo.setProveedor(Long.parseLong(atributos[9]));
                articulo.setSurtido(Integer.parseInt(atributos[10]));
                articulo.setDe(atributos[11]);
                articulo.setReferencia(Long.parseLong(atributos[12]));
                articulo.setPrecio(Double.parseDouble(atributos[13]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idArticulo,codigoArticulo,descripcion,costo,cantidad,fechaIni,fechaFin,proveedor,surtido,De,referencia,precio FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Pedidos articulo = new Pedidos(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setIdArticulo(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setDescripcion(conjuntoResultados.getObject(4).toString());
                articulo.setCosto(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                articulo.setFechaIni(conjuntoResultados.getObject(7).toString());
                articulo.setFechaFin(conjuntoResultados.getObject(8).toString());
                articulo.setProveedor(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(10).toString()));
                articulo.setDe(conjuntoResultados.getObject(11).toString());
                articulo.setReferencia(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                articulo.setPrecio(Double.parseDouble(conjuntoResultados.getObject(13).toString()));
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
    
    public String generarFecha()
    {
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
        return fecha;
    }
    
    
               public int guardarPedidos(Pedidos a)
    {
        
            int ultimoInsertado=-1;
            String query = " INSERT INTO pedidos (" + 
                      "idArticulo,"
                    + "codigoArticulo,"
                    + "descripcion,"
                    + "costo,"
                    + "cantidad,"
                    + "fechaIni," 
                    + "fechaFin,"
                    + "proveedor,"
                    + "surtido,"
                    + "De,"
                    + "referencia,"
                    + "precio) VALUES" + "(\'" 
                    + a.getIdArticulo() + "\',\'" 
                    + a.getCodigoArticulo() + "\',\'" 
                    + a.getDescripcion() + "\',\'" 
                    + a.getCosto() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getFechaIni() + "\',\'" 
                    + a.getFechaFin() + "\',\'" 
                    + a.getProveedor() + "\',\'" 
                    + a.getSurtido() + "\',\'" 
                    + a.getDe() + "\',\'" 
                    + a.getReferencia() + "\',\'" 
                    + a.getPrecio() + "');";
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
               
     public int actualizarPedidos(Pedidos a,String campoCondicion,String match,String condicion)
    {
        
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();



     String query = "UPDATE pedidos SET idArticulo = \'" +  a.getIdArticulo()+"\',"+
                  "codigoArticulo = \'" +  a.getCodigoArticulo() +"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\',"+
                  "costo = \'" +   a.getCosto()+"\',"+
                  "cantidad = \'" +   a.getCantidad()+"\',"+
                  "fechaIni = \'" +   a.getFechaIni()+"\',"+
                  "fechaFin = \'" +    a.getFechaFin()+"\',"+
                  "proveedor = \'" +    a.getProveedor()+"\',"+
                  "surtido = \'" +    a.getSurtido()+"\',"+
                  "De = \'" +    a.getDe()+"\',"+
                  "referencia = \'" + a.getReferencia()+"\',"+
                  "precio = \'" +a.getPrecio() +"\'";
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
    
        public int borrarPedidos(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     } 
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM pedidos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM pedidos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
        
             public static void main(String args[])
    {

       hPedidos ejemplo = new hPedidos();
       List<Pedidos> articulos = ejemplo.consultaPedidos("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getCantidad());
           i++;
       } 
        System.out.println(ejemplo.borrarPedidos("id", "=", "1"));

       Pedidos articulo = new Pedidos();
       articulo.setCantidad(1);
       articulo.setCodigoArticulo("2");
       articulo.setCosto(3.0);
       articulo.setDe("4");
       articulo.setDescripcion("5");
       articulo.setFechaFin(ejemplo.generarFecha().substring(0,19));
       articulo.setFechaIni(ejemplo.generarFecha().substring(0,19));
       articulo.setIdArticulo((long)6);
       articulo.setPrecio(7.0);
       articulo.setProveedor((long)8);
       articulo.setReferencia((long)9);
       articulo.setSurtido(10);
                     

    //   System.out.println(ejemplo.guardarPedidos(articulo));

       articulo.setSurtido(99);
       System.out.println(ejemplo.actualizarPedidos(articulo, "id", "=", "2"));
        
        
    }
    
     
}


