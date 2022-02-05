/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.*;
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
public class hPartidasfacturas {

    public hPartidasfacturas(){}
    
        public List<Partidasfacturas> consultaPartidas(String campo, String match, String condicion)
    {
       List<Partidasfacturas> articulos = new ArrayList<Partidasfacturas>();
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
                    if(match.equals("*")){
            query = "SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPartidas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasfacturas.php");
		  
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
           
            Partidasfacturas articulo = new Partidasfacturas(); 
                 articulo.setId(Long.parseLong(atributos[1]));   
                 articulo.setCodigoArticulo(atributos[2]);
                 articulo.setCantidad(Integer.parseInt(atributos[3]));
                 articulo.setPrecioUnitario(atributos[4]);
                 articulo.setImporte(atributos[5]);
                 articulo.setDescripcion(atributos[6]);
                 articulo.setIdVenta(Long.parseLong(atributos[7]));
                 articulo.setIdFactura(Long.parseLong(atributos[8]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigoArticulo,cantidad,precioUnitario,importe,descripcion,idVenta,idFactura FROM partidasfacturas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {
                 Partidasfacturas articulo = new Partidasfacturas(); 
                 articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));   
                 articulo.setCodigoArticulo(conjuntoResultados.getObject(2).toString());
                 articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(3).toString()));
                 articulo.setPrecioUnitario(conjuntoResultados.getObject(4).toString());
                 articulo.setImporte(conjuntoResultados.getObject(5).toString());
                 articulo.setDescripcion(conjuntoResultados.getObject(6).toString());
                 articulo.setIdVenta(Long.parseLong(conjuntoResultados.getObject(7).toString()));
                 articulo.setIdFactura(Long.parseLong(conjuntoResultados.getObject(8).toString()));
                 articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
        
   /*    public int consultaArticulos(String factura)
    {
       int total = 0; 
       Conexion conexion = new Conexion();
                conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = conexion.crearConsulta("SELECT SUM(cantidad) AS total FROM partidasfacturas WHERE idFactura = \'"+factura+"\';");
           
            while (conjuntoResultados.next()) {
             total = Integer.parseInt(conjuntoResultados.getObject(1).toString());
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(hPartidasfacturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        conexion.cerrar(0);
        }
        return 0;
    } */
    
          public int guardarPartidas(Partidasfacturas a)
    {
           int filas_afectadas=0;
            String query = "INSERT INTO partidasfacturas (codigoArticulo," + 
                    "cantidad,precioUnitario,importe,descripcion,idVenta,idFactura" +
                    ") VALUES" + "(\'" + a.getCodigoArticulo() + "\',\'" + a.getCantidad() + "\',\'" + a.getPrecioUnitario() + "\',\'" + a.getImporte() 
                    + "\',\'" +a.getDescripcion() + "\',\'" +a.getIdVenta() + "\',\'" +a.getIdFactura() + "\');";
            
       //     Conexion conexion = new Conexion();
       //     conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      filas_afectadas = conexionweb.escribirEnWebLast(query);
      return filas_afectadas;
       }
            
            filas_afectadas = CPrincipal.getConexion().moverDatosLast(query);
            CPrincipal.getConexion().cerrar(1);
            

            return filas_afectadas;
    }

    
    public int borrarPartidas(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidasfacturas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidasfacturas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasfacturas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasfacturas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
    
     public int actualizarPartidas(Partidasfacturas a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
     

     String query = "UPDATE partidasfacturas SET codigoArticulo = \'" +  a.getCodigoArticulo()+"\',"+
                  "cantidad = \'" +   a.getCantidad()+"\',"+
                  "precioUnitario = \'" +   a.getPrecioUnitario()+"\',"+
                  "importe = \'" +   a.getImporte()+"\',"+
                  "idVenta = \'" +   a.getIdVenta()+"\',"+
                  "idFactura = \'" +   a.getIdFactura()+"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\'";     
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
     
          public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE partidasfacturas SET codigoArticulo = \'"+codigoNuevo+"\' WHERE codigoArticulo = \'"+codigoViejo+"\';";

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
     
            public static void main(String args[])
    {
       hPartidas ejemplo = new hPartidas();
       List<Partidas> articulos = ejemplo.consultaPartidas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getDescripcionArticulo());
           i++;
       } 
   //   System.out.println(ejemplo.borrarPartidas("id", "=", "2"));

       Partidas art = new Partidas();
                art.setBeneficio(12.5);
                art.setCantidad(2);
                art.setCodigoArticulo("al203");
                art.setConBeneficio(2.12);
                art.setDescripcionArticulo("Capote");
                art.setIdArticulo(Long.parseLong("2"));
                art.setIdVenta(Long.parseLong("2"));
                art.setPrecioVenta(24.0);
                art.setSubtotal(12.0);
                art.setTipoBeneficio("Venta");
                art.setPrecioCompra(22.6);
                     

      System.out.println(ejemplo.guardarPartidas(art));

      art.setDescripcionArticulo("Campo");
 //     System.out.println(ejemplo.actualizarPartidas(art, "id", "=", "2"));


    }



}
