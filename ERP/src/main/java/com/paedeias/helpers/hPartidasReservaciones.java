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
import com.paedeias.identidades.PartidasEnReservacion;
import com.paedeias.identidades.Partidasreservaciones;
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


public class hPartidasReservaciones {
    
    public hPartidasReservaciones(){}
    
      
     /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Partidasreservaciones> consultaPReservaciones(String campo, String match, String condicion)
    {
       List<Partidasreservaciones> articulos = new ArrayList<Partidasreservaciones>();
    //   Conexion conexion = new Conexion();
    //   conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
        query = "SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones;";
        }
        else if(match.equals("="))
        {
        query = "SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
        query = "SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
	
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaPReservaciones()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasReservaciones.php");
		  
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
           
	     Partidasreservaciones articulo = new Partidasreservaciones(); 
                articulo.setId(Long.parseLong(atributos[1]));
                articulo.setCodRes(atributos[2]);
                articulo.setCodArt(atributos[3]);
                articulo.setCantidad(Integer.parseInt(atributos[4]));
                articulo.setDescripcion(atributos[5]);
                articulo.setReservado(Integer.parseInt(atributos[6]));
                articulo.setSurtido(Integer.parseInt(atributos[7]));
                articulo.setVale(atributos[8]);
                articulo.setCosto(Double.parseDouble(atributos[9]));
                articulo.setPrecio(Double.parseDouble(atributos[10]));
                articulo.setIdArt(Long.parseLong(atributos[11]));
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codRes,codArt,cantidad,descripcion,reservado,surtido,vale,costo,precio,idArt FROM partidasreservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                       
            while (conjuntoResultados.next()) {
                
                Partidasreservaciones articulo = new Partidasreservaciones(); 
                articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                articulo.setCodRes(conjuntoResultados.getObject(2).toString());
                articulo.setCodArt(conjuntoResultados.getObject(3).toString());
                articulo.setCantidad(Integer.parseInt(conjuntoResultados.getObject(4).toString()));
                articulo.setDescripcion(conjuntoResultados.getObject(5).toString());
                articulo.setReservado(Integer.parseInt(conjuntoResultados.getObject(6).toString()));
                articulo.setSurtido(Integer.parseInt(conjuntoResultados.getObject(7).toString()));
                articulo.setVale(conjuntoResultados.getObject(8).toString());
                articulo.setCosto(Double.parseDouble(conjuntoResultados.getObject(9).toString()));
                articulo.setPrecio(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                articulo.setIdArt(Long.parseLong(conjuntoResultados.getObject(11).toString()));
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
    
           public int guardarPReservaciones(Partidasreservaciones a)
    {        
            int ultimoInsertado=-1;
            String query = " INSERT INTO partidasreservaciones (" + 
                      "codRes,"
                    + "codArt,"
                    + "cantidad,"
                    + "descripcion,"
                    + "reservado,"
                    + "surtido," 
                    + "vale,"
                    + "costo,"
                    + "precio,"
                    + "idArt) VALUES" + "(\'" 
                    + a.getCodRes() + "\',\'" 
                    + a.getCodArt() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getDescripcion() + "\',\'" 
                    + a.getReservado() + "\',\'" 
                    + a.getSurtido() + "\',\'" 
                    + a.getVale() + "\',\'" 
                    + a.getCosto() + "\',\'" 
                    + a.getPrecio() + "\',\'" 
                    + a.getIdArt() + "');";
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
    
    public String crearQueryGuardarPReservaciones(Partidasreservaciones a)
    {        
            
            String query = " INSERT INTO partidasreservaciones (" + 
                      "codRes,"
                    + "codArt,"
                    + "cantidad,"
                    + "descripcion,"
                    + "reservado,"
                    + "surtido," 
                    + "vale,"
                    + "costo,"
                    + "precio,"
                    + "idArt) VALUES" + "(@ultimo_id,\'" 
                    + a.getCodArt() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getDescripcion() + "\',\'" 
                    + a.getReservado() + "\',\'" 
                    + a.getSurtido() + "\',\'" 
                    + a.getVale() + "\',\'" 
                    + a.getCosto() + "\',\'" 
                    + a.getPrecio() + "\',\'" 
                    + a.getIdArt() + "');";
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();
     
            return query;
    }       
           
        public String crearQueryGuardarPReservaciones2(Partidasreservaciones a)
    {        
            
            String query = " INSERT INTO partidasreservaciones (" + 
                      "codRes,"
                    + "codArt,"
                    + "cantidad,"
                    + "descripcion,"
                    + "reservado,"
                    + "surtido," 
                    + "vale,"
                    + "costo,"
                    + "precio,"
                    + "idArt) VALUES" + "(\'"+a.getCodRes()+"\',\'" 
                    + a.getCodArt() + "\',\'" 
                    + a.getCantidad() + "\',\'" 
                    + a.getDescripcion() + "\',\'" 
                    + a.getReservado() + "\',\'" 
                    + a.getSurtido() + "\',\'" 
                    + a.getVale() + "\',\'" 
                    + a.getCosto() + "\',\'" 
                    + a.getPrecio() + "\',\'" 
                    + a.getIdArt() + "');";
        //    Conexion conexion = new Conexion();
        //    conexion.crearConexion();
     
            return query;
    }             
           
                        
             public int actualizarPReservaciones(Partidasreservaciones a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
     // Conexion conexion = new Conexion();
     //         conexion.crearConexion();



     String query = "UPDATE partidasreservaciones SET codRes = \'" +  a.getCodRes()+"\',"+
                  "codArt = \'" +  a.getCodArt() +"\',"+
                  "cantidad = \'" +   a.getCantidad()+"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\',"+
                  "reservado = \'" +   a.getReservado()+"\',"+
                  "surtido = \'" +   a.getSurtido()+"\',"+
                  "vale = \'" +    a.getVale()+"\',"+
                  "costo = \'" +    a.getCosto()+"\',"+
                  "idArt = \'" +    a.getIdArt()+"\',"+
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
             
     public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE partidasreservaciones SET codArt = \'"+codigoNuevo+"\' WHERE codArt = \'"+codigoViejo+"\';";

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
             
             
       public List<PartidasEnReservacion> buscarPartidas(){
         List<PartidasEnReservacion> partidas = new ArrayList<PartidasEnReservacion>();    
         
         
             if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT r.id as id,r.nomCli as nomCli,r.fecha as fecha,p.precio as precio,r.tipoAuto as tipoAuto,r.modelo as modelo,p.codArt as codArt,p.descripcion as descripcion,r.novale as novale FROM articulos a,partidasreservaciones p, reservaciones r WHERE a.reservado > 0 AND a.codigo = p.codArt AND p.codRes = r.id AND r.facturada = 0 ORDER BY r.id ASC;";
		
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
          URL url=new URL("http://www.quimerasystems.com/paehelp/paePartidasReservaciones.php");
		  
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
           
	     PartidasEnReservacion partida = new PartidasEnReservacion();
            
            partida.setIdRes(atributos[1]);
            partida.setCliente(atributos[2]);
            partida.setFecha(atributos[3]);
            partida.setPrecioVenta(atributos[4]);
            partida.setTipoAuto(atributos[5]);
            partida.setModelo(atributos[6]);
            partida.setCodArt(atributos[7]);
            partida.setDescripcion(atributos[8]);
            partida.setVale(atributos[9]);
            
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
          //  Conexion conexion = new Conexion();
          //  conexion.crearConexion();  
            ResultSet conjuntoResultados;   
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT r.id,r.nomCli,r.fecha,p.precio,r.tipoAuto,r.modelo,p.codArt,p.descripcion,r.novale FROM articulos a,partidasreservaciones p, reservaciones r WHERE a.reservado > 0 AND a.codigo = p.codArt AND p.codRes = r.id AND r.facturada = 0 ORDER BY r.id ASC;");
            while (conjuntoResultados.next()) {
            PartidasEnReservacion partida = new PartidasEnReservacion();
            
            partida.setIdRes(conjuntoResultados.getObject(1).toString());
            partida.setCliente(conjuntoResultados.getObject(2).toString());
            partida.setFecha(conjuntoResultados.getObject(3).toString());
            partida.setPrecioVenta(conjuntoResultados.getObject(4).toString());
            partida.setTipoAuto(conjuntoResultados.getObject(5).toString());
            partida.setModelo(conjuntoResultados.getObject(6).toString());
            partida.setCodArt(conjuntoResultados.getObject(7).toString());
            partida.setDescripcion(conjuntoResultados.getObject(8).toString());
            partida.setVale(conjuntoResultados.getObject(9).toString());
            
            partidas.add(partida);
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(hPartidasReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         return partidas;
        }
       }
           
       public int borrarPReservaciones(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     } 
      
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }
       
    public String crearQueryBorrarPReservaciones(String campo, String match, String condicion)
    {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM partidasreservaciones WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
      return query;
    }   
       
    public String crearQueryBorrarPReservacionesAR(String campo, String match, String condicion)
    {
     String query = "DELETE  FROM partidasreservaciones WHERE codRes = @ultimo_id;";
     return query;
    }   
       
             public static void main(String args[])
    {

       hPartidasReservaciones ejemplo = new hPartidasReservaciones();
       List<Partidasreservaciones> articulos = ejemplo.consultaPReservaciones("id","=","1");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getDescripcion());
           i++;
       } 
       System.out.println(ejemplo.borrarPReservaciones("id", "=", "1"));

       Partidasreservaciones articulo = new Partidasreservaciones();
                articulo.setCodRes("1");
                articulo.setCodArt("2");
                articulo.setCantidad(3);
                articulo.setDescripcion("4");
                articulo.setReservado(5);
                articulo.setSurtido(6);
                articulo.setVale("8");
                articulo.setCosto(9.1);
                articulo.setPrecio(10.2);

                     

    //   System.out.println(ejemplo.guardarPReservaciones(articulo));

       articulo.setPrecio(15.8);
       System.out.println(ejemplo.actualizarPReservaciones(articulo, "id", "=", "2"));
        
        
    }
           
}
