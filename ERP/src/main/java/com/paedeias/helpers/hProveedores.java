/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

/**
 *
 * @author Luis
 */
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Proveedores;
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

public class hProveedores {

    public hProveedores(){}

          /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Proveedores> consultaProveedores(String campo, String match, String condicion)
    {
       List<Proveedores> proveedores = new ArrayList<Proveedores>();
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "";
                    if(match.equals("*")){
            query = "SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores ORDER BY nombre ASC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY nombre ASC;";
            }else
            {
            query = "SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY nombre ASC;";
            }
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaProveedores()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeProveedores.php");
		  
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
           
	  Proveedores proveedor = new Proveedores(
                        Long.parseLong(atributos[1]),
                        atributos[2],
                        atributos[3],
                        Integer.parseInt(atributos[4]),
                        atributos[5],
                        atributos[6],
                        atributos[7],
                        Integer.parseInt(atributos[8]),
                        atributos[9],
                        atributos[10],
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        Integer.parseInt(atributos[14]),
                        Integer.parseInt(atributos[15]),
                        Integer.parseInt(atributos[16]),
                        atributos[17],
                        atributos[18],
                        Double.parseDouble(atributos[19])
                        );
                
                proveedor.setDesc1(Integer.parseInt(atributos[20]));
                proveedor.setDesc2(Integer.parseInt(atributos[21]));
                proveedor.setDesc3(Integer.parseInt(atributos[22]));
                proveedor.setDesc4(Integer.parseInt(atributos[23]));
                proveedor.setDesc5(Integer.parseInt(atributos[24]));
                proveedor.setDesc6(Integer.parseInt(atributos[25]));
                proveedores.add(proveedor);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return proveedores;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores ORDER BY nombre ASC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY nombre ASC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,rfc,nombre,clasificacion,telefono,atencion,calle,numero,colonia,poblacion,estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,desc1,desc2,desc3,desc4,desc5,desc6 FROM proveedores WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY nombre ASC;");
            }

            while (conjuntoResultados.next()) {

                Proveedores proveedor = new Proveedores(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(4).toString()),
                        conjuntoResultados.getObject(5).toString(),
                        conjuntoResultados.getObject(6).toString(),
                        conjuntoResultados.getObject(7).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(8).toString()),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(14).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(15).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(16).toString()),
                        conjuntoResultados.getObject(17).toString(),
                        conjuntoResultados.getObject(18).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(19).toString())
                        );
                
                proveedor.setDesc1(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                proveedor.setDesc2(Integer.parseInt(conjuntoResultados.getObject(21).toString()));
                proveedor.setDesc3(Integer.parseInt(conjuntoResultados.getObject(22).toString()));
                proveedor.setDesc4(Integer.parseInt(conjuntoResultados.getObject(23).toString()));
                proveedor.setDesc5(Integer.parseInt(conjuntoResultados.getObject(24).toString()));
                proveedor.setDesc6(Integer.parseInt(conjuntoResultados.getObject(25).toString()));
                proveedores.add(proveedor);
            }
            return proveedores;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

        public int guardarProveedores(Proveedores p)
    {
       int filas_afectadas = 0;
       String query = "INSERT INTO proveedores (rfc,nombre,"
                    + "clasificacion,telefono,atencion,calle,numero,colonia,poblacion,"
                    + "estado,codigoPostal,correo,diasCredito,diasLimite,descuento,observaciones,nombreCorto,limiteCredito,"
                    + "desc1,desc2,desc3,desc4,desc5,desc6"
                    +") VALUES"
                    + "(\'"+p.getRfc()+
                    "\',\'"+p.getNombre()+
                    "\',\'"+p.getClasificacion()+
                    "\',\'"+p.getTelefono()+
                    "\',\'"+p.getAtencion()+
                    "\',\'"+p.getCalle()+
                    "\',\'"+p.getNumero()+
                    "\',\'"+p.getColonia()+
                    "\',\'"+p.getPoblacion()+
                    "\',\'"+p.getEstado()+
                    "\',\'"+p.getCodigoPostal()+
                    "\',\'"+p.getCorreo()+
                    "\',\'"+p.getDiasCredito()+
                    "\',\'"+p.getDiasLimite()+
                    "\',\'"+p.getDescuento()+
                    "\',\'"+p.getObservaciones()+
                    "\',\'"+p.getNombreCorto()+
                    "\',\'"+p.getLimiteCredito()+
                    "\',\'"+p.getDesc1()+
                    "\',\'"+p.getDesc2()+
                    "\',\'"+p.getDesc3()+
                    "\',\'"+p.getDesc4()+
                    "\',\'"+p.getDesc5()+
                    "\',\'"+p.getDesc6()
                    +"');";
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

            public int borrarProveedores(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
          if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM proveedores WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM proveedores WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }  
      

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM proveedores WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM proveedores WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

            public int ultimoEntero()
    {
    int entero = 0;
 //   Conexion conexion = new Conexion();
 //   conexion.crearConexion();
    
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
       String query = "SELECT id FROM proveedores ORDER BY id DESC LIMIT 1;";		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","ultimoEntero()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeProveedores.php");
		  
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
           entero = Integer.parseInt(atributos[1]);
		   //Arma el objeto y agrega a la lista en su caso
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return entero;
			//Regresa el objeto o lista
           
        }catch (Exception e){
           e.printStackTrace();
       }
           //Regresa nulo o 0
           return entero;
		   //Regresa nulo o 0
		   	   
        }
    
    
    
    
    ResultSet conjuntoResultados;
    conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id FROM proveedores ORDER BY id DESC LIMIT 1;");
        try {
            while(conjuntoResultados.next())
            entero = Integer.parseInt(conjuntoResultados.getObject(1).toString());
        } catch (SQLException ex) {
            Logger.getLogger(hProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    entero++;
    return entero;
    }

              /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarProveedores(Proveedores p,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE proveedores SET "+
                  "rfc = \'" +  p.getRfc()+"\',"+
                  "nombre = \'" +   p.getNombre()+"\',"+
                  "clasificacion = \'" +   p.getClasificacion()+"\',"+
                  "telefono = \'" +   p.getTelefono()+"\',"+
                  "atencion = \'" +   p.getAtencion()+"\',"+
                  "calle = \'" +   p.getCalle()+"\',"+
                  "numero = \'" +   p.getNumero()+"\',"+
                  "colonia = \'" +    p.getColonia()+"\',"+
                  "poblacion = \'" +    p.getPoblacion()+"\',"+
                  "estado = \'" +    p.getEstado()+"\',"+
                  "codigoPostal = \'" +     p.getCodigoPostal()+"\',"+
                  "correo = \'" +    p.getCorreo()+"\',"+
                  "diasCredito = \'" +    p.getDiasCredito()+"\',"+
                  "diasLimite = \'" +    p.getDiasLimite()+"\',"+
                  "descuento = \'" +    p.getDescuento()+"\',"+
                  "nombreCorto = \'" +    p.getNombreCorto()+"\',"+
                  "desc1 = \'" +    p.getDesc1()+"\',"+
                  "desc2 = \'" +    p.getDesc2()+"\',"+
                  "desc3 = \'" +    p.getDesc3()+"\',"+
                  "desc4 = \'" +    p.getDesc4()+"\',"+
                  "desc5 = \'" +    p.getDesc5()+"\',"+
                  "desc6 = \'" +    p.getDesc6()+"\',"+
                  "limiteCredito = \'" +    p.getLimiteCredito()+"\',"+
                  "observaciones = \'" +    p.getObservaciones()+"\'";
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

     public static void main(String args[])
    {
       hProveedores ejemplo = new hProveedores();
       List<Proveedores> proveedores = ejemplo.consultaProveedores("id","LIKE","musta");
       int i = 0;
       while(i < proveedores.size())
       {
           System.out.println(proveedores.get(i).getObservaciones());
           i++;
       }
       System.out.println(ejemplo.borrarProveedores("clasificacion", "=", "1"));

       Proveedores pro = new Proveedores();
            pro.setAtencion("Juan López Quesada");
            pro.setCalle("Duraznos");
            pro.setClasificacion(1);
            pro.setCodigoPostal("50320");
            pro.setColonia("Los Olivos");
            pro.setCorreo("juanlopez@hotmail.com");
            pro.setDescuento(12);
            pro.setDiasCredito(15);
            pro.setDiasLimite(15);
            pro.setEstado("Estado de México");
            pro.setNombre("Juan López Quesada");
            pro.setNumero(7);
            pro.setObservaciones("Proveedor de autopartes Diaz Mirón");
            pro.setPoblacion("Toluca");
            pro.setRfc("JLQ02938291G0");
            pro.setTelefono("2198237");
            pro.setNombreCorto("akdj29");

      System.out.println(ejemplo.guardarProveedores(pro));

       pro.setObservaciones("Proveedor de autopartes de automóvil");
       pro.setPoblacion("Ixtlahuaca");

      System.out.println(ejemplo.actualizarProveedores(pro, "codigo", "=", "Prov001"));


    }



}

