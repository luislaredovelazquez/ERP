/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Ubicacion;
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
 * @author Luis
 */
public class hUbicacion {

        public hUbicacion()
    {

    }

     public List<Ubicacion> consultaUbicaciones(String campo, String match, String condicion)
    {
       List<Ubicacion> lubicacion = new ArrayList<Ubicacion>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
        if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,ubicacion FROM ubicacion;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,ubicacion FROM ubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,ubicacion FROM ubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUbicaciones()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeUbicacion.php");
		  
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
           
	   Ubicacion ubicacion = new Ubicacion(
                        Long.parseLong(atributos[1]),
                        atributos[2]
                        );

                lubicacion.add(ubicacion);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return lubicacion;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,ubicacion FROM ubicacion;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,ubicacion FROM ubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,ubicacion FROM ubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }

            while (conjuntoResultados.next()) {

                Ubicacion ubicacion = new Ubicacion(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString()
                        );

                lubicacion.add(ubicacion);
            }
            return lubicacion;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

          public int guardarUbicacion(Ubicacion u)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO ubicacion (ubicacion) VALUES"
                    + "(\'"+u.getUbicacion()+"\');";
         //       Conexion conexion = new Conexion();
         //       conexion.crearConexion();
       
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

       public int borrarUbicacion(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
     if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM ubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM ubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }  

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ubicacion WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM ubicacion WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

 public int actualizarUbicacion(Ubicacion u)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE ubicacion SET "
             + "ubicacion = \'" +  u.getUbicacion()+"\'"+
               " WHERE id = \'"+u.getId()+"\'";

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

  public static void main(String args[])
    {
       hUbicacion ejemplo = new hUbicacion();
       List<Ubicacion> lineas = ejemplo.consultaUbicaciones("id","=","1");
       int i = 0;
       while(i < lineas.size())
       {
           System.out.println(lineas.get(i).getUbicacion());
           i++;
       }
       System.out.println(ejemplo.borrarUbicacion("ubicacion", "LIKE", "almacen1"));

      Ubicacion lin = new Ubicacion("Lavanderia");
       System.out.println(ejemplo.guardarUbicacion(lin));

      lin.setId(Long.parseLong("1"));
      lin.setUbicacion("Almacen2");
      System.out.println(ejemplo.actualizarUbicacion(lin));  


    }

}
