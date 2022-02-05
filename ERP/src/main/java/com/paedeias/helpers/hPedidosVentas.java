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
import com.paedeias.identidades.Pedidosventas;
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

public class hPedidosVentas {
    
    public hPedidosVentas(){}
    
         /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Pedidosventas> consultaPedidosVentas(String campo, String match, String condicion)
    {
       List<Pedidosventas> articulos = new ArrayList<Pedidosventas>();
  //     Conexion conexion = new Conexion();
  //     conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas ORDER BY id DESC LIMIT 100;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;";
            }else
            {
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;";
            }
                 
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPedidosVentas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePedidosVentas.php");
		  
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
           
	                  Pedidosventas articulo = new Pedidosventas(); 
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(Long.parseLong(atributos[2]));
                articulo.setCodigoArticulo(atributos[3]);
                articulo.setCantidad(Integer.parseInt(atributos[4]));
                articulo.setPrecioUnitario(Double.parseDouble(atributos[5]));
                articulo.setVendedor(Long.parseLong(atributos[6]));
                articulo.setFecha(atributos[7]);
                
                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas ORDER BY id DESC LIMIT 100;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 100;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC LIMIT 100;");
            }
                       
            while (conjuntoResultados.next()) {
                
                Pedidosventas articulo = new Pedidosventas(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setPrecioUnitario(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setVendedor(Long.parseLong(conjuntoResultados.getObject(6).toString()));
                articulo.setFecha(conjuntoResultados.getObject(7).toString());
                
                
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
    
      public List<Pedidosventas> consultaPedidosVentas2(String campo, String match, String condicion)
    {
       List<Pedidosventas> articulos = new ArrayList<Pedidosventas>();
  //     Conexion conexion = new Conexion();
  //     conexion.crearConexion();
              if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
         if(match.equals("*")){
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
                 
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPedidosVentas2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePedidosVentas.php");
		  
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
           
	       Pedidosventas articulo = new Pedidosventas(); 
                
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setVenta(Long.parseLong(atributos[2]));
                articulo.setCodigoArticulo(atributos[3]);
                articulo.setCantidad(Integer.parseInt(atributos[4]));
                articulo.setPrecioUnitario(Double.parseDouble(atributos[5]));
                articulo.setVendedor(Long.parseLong(atributos[6]));
                articulo.setFecha(atributos[7]);
                
                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,venta,codigoArticulo,cantidad,precioUnitario,vendedor,fecha FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Pedidosventas articulo = new Pedidosventas(); 
                
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setVenta(Long.parseLong(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoArticulo(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setPrecioUnitario(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                articulo.setVendedor(Long.parseLong(conjuntoResultados.getObject(6).toString()));
                articulo.setFecha(conjuntoResultados.getObject(7).toString());
                
                
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
    
    
             public int guardarPedidosVentas(Pedidosventas a)
    {
            int ultimoInsertado=-1;
            String query = " INSERT INTO pedidosventas (" + 
                      "venta,"
                    + "codigoArticulo,"
                    + "cantidad,"
                    + "precioUnitario,"
                    + "vendedor,"
                    + "fecha) VALUES" + "(\'" 
                    + a.getVenta() + "\',\'" 
                    + a.getCodigoArticulo() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getPrecioUnitario() + "\',\'" 
                    + a.getVendedor() + "\',\'" 
                    + generarFecha().substring(0, 19) + "');";
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
    
                 public int actualizarPedidosVentas(Pedidosventas a,String campoCondicion,String match,String condicion)
    {

        
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();



     String query = "UPDATE pedidosventas SET venta = \'" +  a.getVenta()+"\',"+
                  "codigoArticulo = \'" +  a.getCodigoArticulo() +"\',"+
                  "cantidad = \'" +   a.getCantidad()+"\',"+
                  "precioUnitario = \'" +   a.getPrecioUnitario()+"\',"+
                  "vendedor = \'" +   a.getVendedor()+"\',"+
                  "fecha = \'" +generarFecha().substring(0,19) +"\'";
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
     String query = "UPDATE pedidosventas SET codigoArticulo = \'"+codigoNuevo+"\' WHERE codigoArticulo = \'"+codigoViejo+"\';";

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
     
      public int actualizarUsuario(String campo, String UsuarioNuevo, String UsuarioViejo)
    {
     int resultado = 0;
             
       String query = "UPDATE pedidosventas SET "+ campo +" = \'"+UsuarioNuevo+"\' WHERE "+campo+ " = \'"+UsuarioViejo+"\';";
       
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

     public int borrarPedidosVentas(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }  
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM pedidosventas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM pedidosventas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }               

         public static void main(String args[])
    {

       hPedidosVentas ejemplo = new hPedidosVentas();
       List<Pedidosventas> articulos = ejemplo.consultaPedidosVentas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getFecha());
           i++;
       } 
        System.out.println(ejemplo.borrarPedidosVentas("id", "=", "1"));

       Pedidosventas articulo = new Pedidosventas();
               articulo.setCantidad(1); 
               articulo.setCodigoArticulo("2");
               articulo.setPrecioUnitario(4.0);
               articulo.setVendedor((long)5);
               articulo.setVenta((long)6);
       
    //   System.out.println(ejemplo.guardarPedidosVentas(articulo));

       articulo.setVenta((long)99);
    //   System.out.println(ejemplo.actualizarPedidosVentas(articulo, "id", "=", "2"));
        
        
    }
     
    
}
