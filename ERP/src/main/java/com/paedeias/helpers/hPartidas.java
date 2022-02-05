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
public class hPartidas {

    public hPartidas(){}
    
        public List<Partidas> consultaPartidas(String campo, String match, String condicion)
    {
       List<Partidas> articulos = new ArrayList<Partidas>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
         if(match.equals("*")){
            query = "SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidas.php");
		  
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
           
	     Partidas articulo = new Partidas(
                        Long.parseLong(atributos[1]),
                        Long.parseLong(atributos[2]),
                        Long.parseLong(atributos[3]),                                       
                        atributos[4],
                        atributos[5],
                        Double.parseDouble(atributos[6]),
                        Double.parseDouble(atributos[7]),
                        atributos[8],
                        Double.parseDouble(atributos[9]),
                        Double.parseDouble(atributos[10]),
                        Integer.parseInt(atributos[11]),        
                        Double.parseDouble(atributos[12])                   
                        ); 
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,idVenta,idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio,cantidad,subtotal FROM partidas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {
                Partidas articulo = new Partidas(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        Long.parseLong(conjuntoResultados.getObject(2).toString()),
                        Long.parseLong(conjuntoResultados.getObject(3).toString()),                                       
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(6).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(7).toString()),
                        conjuntoResultados.getObject(8).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(11).toString()),        
                        Double.parseDouble(conjuntoResultados.getObject(12).toString())                   
                        ); 
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
    
          public int guardarPartidas(Partidas a)
    {
           int filas_afectadas=0;
            String query = "INSERT INTO partidas (idVenta," + 
                    "idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio," +
                    "cantidad,subtotal) VALUES" + "(\'" + a.getIdVenta() + "\',\'" + a.getIdArticulo() + "\',\'" + a.getCodigoArticulo() + "\',\'" + a.getDescripcionArticulo() 
                    + "\',\'" +a.getPrecioCompra()+ "\',\'" + a.getPrecioVenta() + "\',\'" + a.getTipoBeneficio() + "\',\'" + a.getBeneficio() + "\',\'" 
                    + a.getConBeneficio() + "\',\'" + a.getCantidad() + "\',\'" + a.getSubtotal() + "\');";
            
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      filas_afectadas = conexionweb.escribirEnWeb(query);
      return filas_afectadas;
       }
                 
            filas_afectadas = CPrincipal.getConexion().moverDatos(query);
            CPrincipal.getConexion().cerrar(1);
            

            return filas_afectadas;
    }
          
          public String crearQueryGuardarPartidas(Partidas a)
    {
            String query = "INSERT INTO partidas (idVenta," + 
                    "idArticulo,codigoArticulo,descripcionArticulo,precioCompra,precioVenta,tipoBeneficio,beneficio,conBeneficio," +
                    "cantidad,subtotal) VALUES" + "(@ultimo_id,\'" + a.getIdArticulo() + "\',\'" + a.getCodigoArticulo() + "\',\'" + a.getDescripcionArticulo() 
                    + "\',\'" +a.getPrecioCompra()+ "\',\'" + a.getPrecioVenta() + "\',\'" + a.getTipoBeneficio() + "\',\'" + a.getBeneficio() + "\',\'" 
                    + a.getConBeneficio() + "\',\'" + a.getCantidad() + "\',\'" + a.getSubtotal() + "\');";
            
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();
          

     return query;
    }      

    public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE partidas SET codigoArticulo = \'"+codigoNuevo+"\' WHERE codigoArticulo = \'"+codigoViejo+"\';";

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
     query = "DELETE  FROM partidas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidas WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidas WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
    
        public String crearQueryBorrarPartidas(String campo, String match, String condicion)
    {

     String query = ""; 
     if(match.equals("="))
            {
     query = "DELETE  FROM partidas WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidas WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
    
      return query;
      
    }
    
     public int actualizarPartidas(Partidas a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();
     

     String query = "UPDATE partidas SET idVenta = \'" +  a.getIdVenta()+"\',"+
                  "idArticulo = \'" +   a.getIdArticulo()+"\',"+
                  "codigoArticulo = \'" +   a.getCodigoArticulo()+"\',"+
                  "descripcionArticulo = \'" +   a.getDescripcionArticulo()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+     
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "tipoBeneficio = \'" +   a.getTipoBeneficio()+"\',"+
                  "beneficio = \'" +   a.getBeneficio()+"\',"+
                  "conBeneficio = \'" +    a.getConBeneficio()+"\',"+
                  "cantidad = \'" +    a.getCantidad()+"\',"+
                  "subtotal = \'" +    a.getSubtotal()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
    //   System.out.println(query);
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
     
       public String crearQueryActualizarPartidas(Partidas a,String campoCondicion,String match,String condicion)
    {
 

     String query = "UPDATE partidas SET idVenta = \'" +  a.getIdVenta()+"\',"+
                  "idArticulo = \'" +   a.getIdArticulo()+"\',"+
                  "codigoArticulo = \'" +   a.getCodigoArticulo()+"\',"+
                  "descripcionArticulo = \'" +   a.getDescripcionArticulo()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+     
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "tipoBeneficio = \'" +   a.getTipoBeneficio()+"\',"+
                  "beneficio = \'" +   a.getBeneficio()+"\',"+
                  "conBeneficio = \'" +    a.getConBeneficio()+"\',"+
                  "cantidad = \'" +    a.getCantidad()+"\',"+
                  "subtotal = \'" +    a.getSubtotal()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
   

     return query;
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
