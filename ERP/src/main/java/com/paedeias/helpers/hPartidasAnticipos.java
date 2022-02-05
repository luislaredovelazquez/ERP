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

public class hPartidasAnticipos {
    
     public hPartidasAnticipos(){}
     
          public List<Partidasanticipos> consultaPartidas(String campo, String match, String condicion)
    {
       List<Partidasanticipos> articulos = new ArrayList<Partidasanticipos>();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
                    if(match.equals("*")){
            query = "SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasAnticipos.php");
		  
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
           
	        Partidasanticipos articulo = new Partidasanticipos();
                articulo.setId(Long.valueOf(atributos[1]));
                articulo.setAnticipo(Long.valueOf(atributos[2]));
                articulo.setCodigoarticulo(atributos[3]);
                articulo.setIdarticulo(Long.valueOf(atributos[4]));
                articulo.setArticulo(atributos[5]);
                articulo.setCantidad(Integer.parseInt(atributos[6]));
                articulo.setPrecioCompra(Double.parseDouble(atributos[7]));
                articulo.setPrecioVenta(Double.parseDouble(atributos[8]));
                articulo.setSurtido(Integer.parseInt(atributos[9]));
                articulo.setSurtidoAlmacen(Integer.parseInt(atributos[10]));                
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,anticipo,codigoarticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido,surtidoAlmacen FROM partidasanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {
                Partidasanticipos articulo = new Partidasanticipos();
                articulo.setId(Long.valueOf(conjuntoResultados.getObject(1).toString()));
                articulo.setAnticipo(Long.valueOf(conjuntoResultados.getObject(2).toString()));
                articulo.setCodigoarticulo(conjuntoResultados.getObject(3).toString());
                articulo.setIdarticulo(Long.valueOf(conjuntoResultados.getObject(4).toString()));
                articulo.setArticulo(conjuntoResultados.getObject(5).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(8).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(9).toString()));
                articulo.setSurtidoAlmacen(Integer.parseInt(conjuntoResultados.getObject(10).toString()));                
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
    
     public List<PartidasEnAnticipos> buscarPartidas(){
         List<PartidasEnAnticipos> partidas = new ArrayList<PartidasEnAnticipos>();  
         
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT p.codigoarticulo,p.articulo,p.cantidad,r.id,r.fecha,r.referencia,r.propietario,r.modelo FROM articulos a,partidasanticipos p, anticipos r WHERE a.anticipos > 0 AND a.codigo = p.codigoarticulo AND p.anticipo = r.id AND r.ticket = 0 ORDER BY p.articulo ASC;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","buscarPartidas()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasAnticipos.php");
		  
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
           
            PartidasEnAnticipos partida = new PartidasEnAnticipos();
            
            partida.setCodigo(atributos[1]);
            partida.setDescripcion(atributos[2]);
            partida.setCantidad(atributos[3]);
            partida.setAnticipo(atributos[4]);
            partida.setFecha(atributos[5]);
            partida.setReferencia(atributos[6]);
            partida.setPropietario(atributos[7]);
            partida.setModelo(atributos[8]);
            
            partidas.add(partida);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return partidas;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }
         
         
         
           try {
      //      Conexion conexion = new Conexion();
      //      conexion.crearConexion();  
            ResultSet conjuntoResultados;   
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoarticulo,p.articulo,p.cantidad,r.id,r.fecha,r.referencia,r.propietario,r.modelo FROM articulos a,partidasanticipos p, anticipos r WHERE a.anticipos > 0 AND a.codigo = p.codigoarticulo AND p.anticipo = r.id AND r.ticket = 0 ORDER BY p.articulo ASC;");
            while (conjuntoResultados.next()) {
            PartidasEnAnticipos partida = new PartidasEnAnticipos();
            
            partida.setCodigo(conjuntoResultados.getObject(1).toString());
            partida.setDescripcion(conjuntoResultados.getObject(2).toString());
            partida.setCantidad(conjuntoResultados.getObject(3).toString());
            partida.setAnticipo(conjuntoResultados.getObject(4).toString());
            partida.setFecha(conjuntoResultados.getObject(5).toString());
            partida.setReferencia(conjuntoResultados.getObject(6).toString());
            partida.setPropietario(conjuntoResultados.getObject(7).toString());
            partida.setModelo(conjuntoResultados.getObject(8).toString());
            
            partidas.add(partida);
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(hPartidasReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         return partidas;
        }
       }      
     
          public List<PartidasEnAnticipos> buscarPartidasSinSurtir(){
         List<PartidasEnAnticipos> partidas = new ArrayList<PartidasEnAnticipos>();    
         
               if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        query = "SELECT p.codigoarticulo,p.articulo,p.cantidad,r.id,r.fecha,r.referencia,r.propietario,r.modelo FROM partidasanticipos p, anticipos r WHERE p.codigoarticulo LIKE '' AND p.anticipo = r.id AND r.ticket = 0 ORDER BY p.articulo ASC;";
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","buscarPartidasSinSurtir()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasAnticipos.php");
		  
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
           
           PartidasEnAnticipos partida = new PartidasEnAnticipos();
            
            partida.setCodigo(atributos[1]);
            partida.setDescripcion(atributos[2]);
            partida.setCantidad(atributos[3]);
            partida.setAnticipo(atributos[4]);
            partida.setFecha(atributos[5]);
            partida.setReferencia(atributos[6]);
            partida.setPropietario(atributos[7]);
            partida.setModelo(atributos[8]);
            
            partidas.add(partida);   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return partidas;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return null;
		   //Regresa nulo o 0
		   	   
        }
         
           try {
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();  
            ResultSet conjuntoResultados;   
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT p.codigoarticulo,p.articulo,p.cantidad,r.id,r.fecha,r.referencia,r.propietario,r.modelo FROM partidasanticipos p, anticipos r WHERE p.codigoarticulo LIKE '' AND p.anticipo = r.id AND r.ticket = 0 ORDER BY p.articulo ASC;");
            while (conjuntoResultados.next()) {
            PartidasEnAnticipos partida = new PartidasEnAnticipos();
            
            partida.setCodigo(conjuntoResultados.getObject(1).toString());
            partida.setDescripcion(conjuntoResultados.getObject(2).toString());
            partida.setCantidad(conjuntoResultados.getObject(3).toString());
            partida.setAnticipo(conjuntoResultados.getObject(4).toString());
            partida.setFecha(conjuntoResultados.getObject(5).toString());
            partida.setReferencia(conjuntoResultados.getObject(6).toString());
            partida.setPropietario(conjuntoResultados.getObject(7).toString());
            partida.setModelo(conjuntoResultados.getObject(8).toString());
            
            partidas.add(partida);
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(hPartidasReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         return partidas;
        }
       }   
          
           public int guardarPartidas(Partidasanticipos a)
    {          
           int filas_afectadas=0;
            String query = "INSERT INTO partidasanticipos (anticipo," + 
                    "codigoArticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido"+
                    ") VALUES" + "(\'" + a.getAnticipo() + "\',\'" + a.getCodigoarticulo() + "\',\'" + a.getIdarticulo() + "\',\'" + a.getArticulo() 
                    + "\',\'" +a.getCantidad()+ "\',\'" + a.getPrecioCompra() + "\',\'" + a.getPrecioVenta() + "\',\'" + a.getSurtido() + "\');";
                    
            
      //      Conexion conexion = new Conexion();
      //      conexion.crearConexion();
            
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
           
          public String crearQueryGuardarPartidas(Partidasanticipos a)
    {          
           
            String query = "INSERT INTO partidasanticipos (anticipo," + 
                    "codigoArticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido"+
                    ") VALUES" + "(@ultimo_id,\'" + a.getCodigoarticulo() + "\',\'" + a.getIdarticulo() + "\',\'" + a.getArticulo() 
                    + "\',\'" +a.getCantidad()+ "\',\'" + a.getPrecioCompra() + "\',\'" + a.getPrecioVenta() + "\',\'" + a.getSurtido() + "\');";
                               

            return query;
    }      
          
     public String crearQueryGuardarPartidas2(Partidasanticipos a)
    {          
           
            String query = "INSERT INTO partidasanticipos (anticipo," + 
                    "codigoArticulo,idarticulo,articulo,cantidad,precioCompra,precioVenta,surtido"+
                    ") VALUES" + "(\'" + a.getAnticipo() + "\',\'" + a.getCodigoarticulo() + "\',\'" + a.getIdarticulo() + "\',\'" + a.getArticulo() 
                    + "\',\'" +a.getCantidad()+ "\',\'" + a.getPrecioCompra() + "\',\'" + a.getPrecioVenta() + "\',\'" + a.getSurtido() + "\');";
                    
            
           

            return query;
    }      
          
     public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE partidasanticipos SET codigoarticulo = \'"+codigoNuevo+"\' WHERE codigoarticulo = \'"+codigoViejo+"\';";

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
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
        
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
      
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }             
         
         public String crearQueryborrarPartidas(String campo, String match, String condicion)
    {
        

     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidasanticipos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
   

      return query;
    }             
         
           
            public int actualizarPartidasLast(Partidasanticipos a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();
     

     String query = "UPDATE partidasanticipos SET anticipo = \'" +  a.getAnticipo()+"\',"+
                  "codigoArticulo = \'" +   a.getCodigoarticulo()+"\',"+
                  "idArticulo = \'" +   a.getIdarticulo()+"\',"+
                  "articulo = \'" +   a.getArticulo()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+     
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "cantidad = \'" +    a.getCantidad()+"\',"+
                  "surtidoAlmacen = \'" +    a.getSurtidoAlmacen()+"\',"+
                  "surtido = \'" +    a.getSurtido()+"\'";
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
            
        public String  crearQueryActualizarPartidasLast(Partidasanticipos a,String campoCondicion,String match,String condicion)
    {
  
     String query = "UPDATE partidasanticipos SET anticipo = \'" +  a.getAnticipo()+"\',"+
                  "codigoArticulo = \'" +   a.getCodigoarticulo()+"\',"+
                  "idArticulo = \'" +   a.getIdarticulo()+"\',"+
                  "articulo = \'" +   a.getArticulo()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+     
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "cantidad = \'" +    a.getCantidad()+"\',"+
                  "surtidoAlmacen = \'" +    a.getSurtidoAlmacen()+"\',"+
                  "surtido = \'" +    a.getSurtido()+"\'";
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
       hPartidasAnticipos ejemplo = new hPartidasAnticipos();
       List<Partidasanticipos> articulos = ejemplo.consultaPartidas("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getArticulo());
           i++;
       } 
      // System.out.println(ejemplo.borrarPartidas("id", "=", "2"));

       Partidasanticipos art = new Partidasanticipos();
                art.setCantidad(2);
                art.setCodigoarticulo("al203");
                art.setArticulo("Capote");
                art.setIdarticulo(Long.parseLong("2"));
                art.setAnticipo(Long.parseLong("2"));
                art.setPrecioVenta(24.0);
                art.setSurtido(1);
                art.setPrecioCompra(22.6);
                     

     // System.out.println(ejemplo.guardarPartidas(art));

      art.setArticulo("Campo");
       System.out.println(ejemplo.actualizarPartidasLast(art, "id", "=", "2"));


    }
            
    
}
