/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paedeias.identidades.Articulos;
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
/**
 *
 * @author Luis
 */
public class hArticulos {

    public hArticulos()
    {

    }

    /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Articulos> consultaArticulos(String campo, String match, String condicion)
    {
        
          if(CGlobalConfig.isWeb())  
       {
            Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "";
        if(match.equals("*")){
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos ORDER BY descripcion ASC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY descripcion ASC;";
            }else
            {
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY descripcion ASC;";
            }    
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("metodo","consultaArticulos()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
          List<Articulos> articulos = new ArrayList<Articulos>();
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
           Articulos articulo = new Articulos();
           articulo.setId(Long.valueOf(atributos[1]));
           articulo.setCodigo(atributos[2]);
           articulo.setDescripcion(atributos[3]);
           articulo.setClasificacion(atributos[4]);
           articulo.setPrecioVenta(Double.valueOf(atributos[5]));
           articulo.setPrecioCompra(Double.valueOf(atributos[6]));
           articulo.setUltimoCosto(Double.valueOf(atributos[7]));
           articulo.setUnidad(atributos[8]);
           articulo.setExistencia(Integer.valueOf(atributos[9]));
           articulo.setReservado(Integer.valueOf(atributos[10]));
           articulo.setMinimoPzas(Integer.valueOf(atributos[11]));
           articulo.setMaximoPzas(Integer.valueOf(atributos[12]));
           articulo.setPromPzas(Integer.valueOf(atributos[13]));
           articulo.setIva(Integer.valueOf(atributos[14]));
           articulo.setIeps(Integer.valueOf(atributos[15]));
           articulo.setTipoEtiqueta(atributos[16]);
           articulo.setUbicacion("General");
           articulo.setIdCompleto("av"+articulo.getId());
           articulo.setBloqueado(Integer.valueOf(atributos[17]));
           articulo.setAlmacenDevoluciones(Integer.valueOf(atributos[18]));
           articulo.setLineaPrincipal(atributos[19]);
           articulo.setAnticipos(Integer.valueOf(atributos[20]));
           articulo.setSinonimoPrincipal(atributos[21]);
           articulo.setCodigo2(atributos[22]);
           articulo.setPrecioVenta2(Double.valueOf(atributos[23]));
           articulo.setProveedor(atributos[24]);
           articulo.setParetto(Integer.valueOf(atributos[25]));
           articulo.setNuevo(Integer.valueOf(atributos[26]));
           articulo.setOferta(Integer.valueOf(atributos[27]));
           articulos.add(articulo);
               }
           s = recv.readLine();
          }
           return articulos;
             }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return null;
       }
        
        
       List<Articulos> articulos = new ArrayList<Articulos>();
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos ORDER BY descripcion ASC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY descripcion ASC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY descripcion ASC;");
            }

            while (conjuntoResultados.next()) {
                        Articulos articulo = new Articulos();
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(4).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(8).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(16).toString());
                        articulo.setUbicacion("General");
                        articulo.setIdCompleto("av"+articulo.getId());
                        articulo.setBloqueado(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                        articulo.setAlmacenDevoluciones(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                        articulo.setLineaPrincipal(conjuntoResultados.getObject(19).toString());
                        articulo.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                        articulo.setSinonimoPrincipal(conjuntoResultados.getObject(21).toString());
                        articulo.setCodigo2(conjuntoResultados.getObject(22).toString());
                        articulo.setPrecioVenta2(Double.parseDouble(conjuntoResultados.getObject(23).toString()));
                        articulo.setProveedor(conjuntoResultados.getObject(24).toString());
                        articulo.setParetto(Integer.parseInt(conjuntoResultados.getObject(25).toString()));
                        articulo.setNuevo(Integer.parseInt(conjuntoResultados.getObject(26).toString()));
                        articulo.setOferta(Integer.parseInt(conjuntoResultados.getObject(27).toString()));
                        articulos.add(articulo);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
        CPrincipal.getConexion().cerrar(0);
        } 
        return null;
    }
    
    public List<Articulos> consultaArticulosFiltro(String campo, String match, String condicion,String condicion2)
    {
       List<Articulos> articulos = new ArrayList<Articulos>();
       
            if(CGlobalConfig.isWeb())  
       {
            Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "";
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND codigo LIKE \'"+condicion2+"\'  ORDER BY descripcion ASC;";   
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion()); 
        datosEnv.put("metodo","consultaArticulosFiltro()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
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
           Articulos articulo = new Articulos();
           articulo.setId(Long.valueOf(atributos[1]));
           articulo.setCodigo(atributos[2]);
           articulo.setDescripcion(atributos[3]);
           articulo.setClasificacion(atributos[4]);
           articulo.setPrecioVenta(Double.valueOf(atributos[5]));
           articulo.setPrecioCompra(Double.valueOf(atributos[6]));
           articulo.setUltimoCosto(Double.valueOf(atributos[7]));
           articulo.setUnidad(atributos[8]);
           articulo.setExistencia(Integer.valueOf(atributos[9]));
           articulo.setReservado(Integer.valueOf(atributos[10]));
           articulo.setMinimoPzas(Integer.valueOf(atributos[11]));
           articulo.setMaximoPzas(Integer.valueOf(atributos[12]));
           articulo.setPromPzas(Integer.valueOf(atributos[13]));
           articulo.setIva(Integer.valueOf(atributos[14]));
           articulo.setIeps(Integer.valueOf(atributos[15]));
           articulo.setUbicacion("General");
           articulo.setIdCompleto("av"+articulo.getId());
           articulo.setTipoEtiqueta(atributos[16]);
           articulo.setBloqueado(Integer.valueOf(atributos[17]));
           articulo.setAlmacenDevoluciones(Integer.valueOf(atributos[18]));
           articulo.setLineaPrincipal(atributos[19]);
           articulo.setAnticipos(Integer.valueOf(atributos[20]));
           articulo.setSinonimoPrincipal(atributos[21]);
           articulo.setCodigo2(atributos[22]);
           articulo.setPrecioVenta2(Double.valueOf(atributos[23]));
           articulo.setProveedor(atributos[24]);
           articulo.setParetto(Integer.valueOf(atributos[25]));
           articulo.setNuevo(Integer.valueOf(atributos[26]));
           articulo.setOferta(Integer.valueOf(atributos[27]));
           articulos.add(articulo);
               }
           s = recv.readLine();
          }
           return articulos;
             }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return null;
       } 
       
       
       
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\' AND codigo LIKE \'"+condicion2+"\'  ORDER BY descripcion ASC;");
            
            
            while (conjuntoResultados.next()) {
                        Articulos articulo = new Articulos();
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(4).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(8).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(16).toString());
                        articulo.setUbicacion("General");
                        articulo.setIdCompleto("av"+articulo.getId());
                        articulo.setBloqueado(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                        articulo.setAlmacenDevoluciones(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                        articulo.setLineaPrincipal(conjuntoResultados.getObject(19).toString());
                        articulo.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                        articulo.setSinonimoPrincipal(conjuntoResultados.getObject(21).toString());
                        articulo.setCodigo2(conjuntoResultados.getObject(22).toString());
                        articulo.setPrecioVenta2(Double.parseDouble(conjuntoResultados.getObject(23).toString()));
                        articulo.setProveedor(conjuntoResultados.getObject(24).toString());
                        articulo.setParetto(Integer.parseInt(conjuntoResultados.getObject(25).toString()));
                        articulo.setNuevo(Integer.parseInt(conjuntoResultados.getObject(26).toString()));
                        articulo.setOferta(Integer.parseInt(conjuntoResultados.getObject(27).toString()));
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
    
       /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Articulos> consultaArticulosLimite(String campo, String match, String condicion, int limite)
    {
       List<Articulos> articulos = new ArrayList<Articulos>();
      // Conexion conexion = new Conexion();
      //          conexion.crearConexion();
                   if(CGlobalConfig.isWeb())  
       {
            Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "";
            if(match.equals("*")){
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos LIMIT "+limite+";";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\'  LIMIT "+limite+";";
            }else
            {
            query = "SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\'  LIMIT "+limite+";";
            }
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("metodo","consultaArticulosLimite()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
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
           Articulos articulo = new Articulos();
           articulo.setId(Long.valueOf(atributos[1]));
           articulo.setCodigo(atributos[2]);
           articulo.setDescripcion(atributos[3]);
           articulo.setClasificacion(atributos[4]);
           articulo.setPrecioVenta(Double.valueOf(atributos[5]));
           articulo.setPrecioCompra(Double.valueOf(atributos[6]));
           articulo.setUltimoCosto(Double.valueOf(atributos[7]));
           articulo.setUnidad(atributos[8]);
           articulo.setExistencia(Integer.valueOf(atributos[9]));
           articulo.setReservado(Integer.valueOf(atributos[10]));
           articulo.setMinimoPzas(Integer.valueOf(atributos[11]));
           articulo.setMaximoPzas(Integer.valueOf(atributos[12]));
           articulo.setPromPzas(Integer.valueOf(atributos[13]));
           articulo.setUbicacion("General");
           articulo.setIdCompleto("av"+articulo.getId());
           articulo.setIva(Integer.valueOf(atributos[14]));
           articulo.setIeps(Integer.valueOf(atributos[15]));
           articulo.setTipoEtiqueta(atributos[16]);
           articulo.setBloqueado(Integer.valueOf(atributos[17]));
           articulo.setAlmacenDevoluciones(Integer.valueOf(atributos[18]));
           articulo.setLineaPrincipal(atributos[19]);
           articulo.setAnticipos(Integer.valueOf(atributos[20]));
           articulo.setSinonimoPrincipal(atributos[21]);
           articulo.setCodigo2(atributos[22]);
           articulo.setPrecioVenta2(Double.valueOf(atributos[23]));
           articulo.setProveedor(atributos[24]);
           articulo.setParetto(Integer.valueOf(atributos[25]));
           articulo.setNuevo(Integer.valueOf(atributos[26]));
           articulo.setOferta(Integer.valueOf(atributos[27]));
           articulos.add(articulo);
               }
           s = recv.readLine();
          }
           return articulos;
             }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return null;
       } 
       
       
       
       
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos LIMIT "+limite+";");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\'  LIMIT "+limite+";");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad,existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,bloqueado,almacenDevoluciones,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\'  LIMIT "+limite+";");
            }

            while (conjuntoResultados.next()) {
                        Articulos articulo = new Articulos();
                        articulo.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                        articulo.setCodigo(conjuntoResultados.getObject(2).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(3).toString());
                        articulo.setClasificacion(conjuntoResultados.getObject(4).toString());
                        articulo.setPrecioVenta(Double.parseDouble(conjuntoResultados.getObject(5).toString()));
                        articulo.setPrecioCompra(Double.parseDouble(conjuntoResultados.getObject(6).toString()));
                        articulo.setUltimoCosto(Double.parseDouble(conjuntoResultados.getObject(7).toString()));
                        articulo.setUnidad(conjuntoResultados.getObject(8).toString());
                        articulo.setExistencia(Long.parseLong(conjuntoResultados.getObject(9).toString()));
                        articulo.setReservado(Long.parseLong(conjuntoResultados.getObject(10).toString()));
                        articulo.setMinimoPzas(Long.parseLong(conjuntoResultados.getObject(11).toString()));
                        articulo.setMaximoPzas(Long.parseLong(conjuntoResultados.getObject(12).toString()));
                        articulo.setPromPzas(Long.parseLong(conjuntoResultados.getObject(13).toString()));
                        articulo.setIva(Integer.parseInt(conjuntoResultados.getObject(14).toString()));
                        articulo.setIeps(Integer.parseInt(conjuntoResultados.getObject(15).toString()));
                        articulo.setTipoEtiqueta(conjuntoResultados.getObject(16).toString());
                        articulo.setUbicacion("General");
                        articulo.setIdCompleto("av"+articulo.getId());
                        articulo.setBloqueado(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                        articulo.setAlmacenDevoluciones(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                        articulo.setLineaPrincipal(conjuntoResultados.getObject(19).toString());
                        articulo.setAnticipos(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                        articulo.setSinonimoPrincipal(conjuntoResultados.getObject(21).toString());
                        articulo.setCodigo2(conjuntoResultados.getObject(22).toString());
                        articulo.setPrecioVenta2(Double.parseDouble(conjuntoResultados.getObject(23).toString()));
                        articulo.setProveedor(conjuntoResultados.getObject(24).toString());
                        articulo.setParetto(Integer.parseInt(conjuntoResultados.getObject(25).toString()));
                        articulo.setNuevo(Integer.parseInt(conjuntoResultados.getObject(26).toString()));
                        articulo.setOferta(Integer.parseInt(conjuntoResultados.getObject(27).toString()));
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
    
    public int consultaExistenciasCodigo(String condicion)
    {
       int existenciaArt = 0;
       
                   if(CGlobalConfig.isWeb())  
       {
            Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "";
            query = "SELECT existencia FROM articulos WHERE codigo = "+" \'"+condicion+"\';";   
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("metodo","consultaExistenciasCodigo()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
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
          existenciaArt = Integer.parseInt(atributos[1]);
               }
           s = recv.readLine();
          }
           return existenciaArt;
             }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return 0;
       } 
       
       
       
       
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT existencia FROM articulos WHERE codigo = "+" \'"+condicion+"\';");
            while (conjuntoResultados.next()) {         
            existenciaArt = Integer.parseInt(conjuntoResultados.getObject(1).toString());
            }
            return existenciaArt;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return 0;
    }
    
        public List<Articulos> consultaReportePedidos(String condicion,String nomProv1,String nomProv2,String nomProv3,String nomProv4,String nomProv5)
    {
        List<Articulos> listaarticulos = new ArrayList<Articulos>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
        
        if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(condicion.equals("*"))
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE existencia <= minimoPzas OR existencia = 0 ORDER BY descripcion;";
            else
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND existencia <= minimoPzas ORDER BY descripcion;";                
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
        
        datosEnv.put("prov1",nomProv1);
        datosEnv.put("prov2",nomProv2);
        datosEnv.put("prov3",nomProv3);
        datosEnv.put("prov4",nomProv4);
        datosEnv.put("prov5",nomProv5);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReportePedidos()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
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
            Articulos articulos = new Articulos();
            articulos.setCodigo(atributos[1]);
            articulos.setDescripcion(atributos[2]);
            articulos.setExistencia(Long.valueOf(atributos[3]));
            articulos.setPrecioCompra(Double.valueOf(atributos[4]));
            articulos.setABC(atributos[5]);
            articulos.setMinimoPzas(Long.valueOf(atributos[6]));
            if(atributos[7].equals("Chica")||atributos[7].equals("Mediana")||atributos[7].equals("Grande")||atributos[7].equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(atributos[7]); 
           //segundas consultas...
           articulos.setClasificacion(atributos[8]);
           articulos.setIdCompleto(atributos[9]);
           articulos.setLineaPrincipal(atributos[10]);
           articulos.setSinonimoPrincipal(atributos[11]);
           articulos.setProveedor(atributos[12]);
           listaarticulos.add(articulos); 
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return listaarticulos;
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
            ResultSet conjuntoResultadosSin;
            if(condicion.equals("*"))
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE existencia <= minimoPzas OR existencia = 0 ORDER BY descripcion;");
            else
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND existencia <= minimoPzas ORDER BY descripcion;");        
            //conjuntoResultados = conexion.crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas FROM articulos  WHERE existencia <= minimoPzas AND  codigo LIKE \'%"+condicion+"\' ORDER BY descripcion;");    
            //conjuntoResultados = conexion.crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion FROM articulos  WHERE existencia <= minimoPzas AND codigo NOT LIKE \'%R_\' AND codigo LIKE \'%"+condicion+"\' ORDER BY descripcion;");    
            while (conjuntoResultados.next()) {  
            Articulos articulos = new Articulos();
            articulos.setCodigo(conjuntoResultados.getObject(1).toString());
            articulos.setDescripcion(conjuntoResultados.getObject(2).toString());
            articulos.setExistencia(Long.valueOf(conjuntoResultados.getObject(3).toString()));
            articulos.setPrecioCompra(Double.valueOf(conjuntoResultados.getObject(4).toString()));
            articulos.setABC(conjuntoResultados.getObject(5).toString());
            articulos.setMinimoPzas(Long.valueOf(conjuntoResultados.getObject(6).toString()));
            if(conjuntoResultados.getObject(7).toString().equals("Chica")||conjuntoResultados.getObject(7).toString().equals("Mediana")||conjuntoResultados.getObject(7).toString().equals("Grande")||conjuntoResultados.getObject(7).toString().equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(conjuntoResultados.getObject(7).toString()); 
                    
           String prov1=""; 
           String prov2="";
           String prov3="";
           String prov4="";
           String prov5="";
           
           if(!nomProv1.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv1+"\';");            
           while (conjuntoResultadosSin.next()) { prov1 = conjuntoResultadosSin.getObject(1).toString();  }
           }
           if(!nomProv2.isEmpty())
           {           
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv2+"\';");            
           while (conjuntoResultadosSin.next()) { prov2 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv3.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv3+"\';");            
           while (conjuntoResultadosSin.next()) { prov3 = conjuntoResultadosSin.getObject(1).toString(); }
           
           }
           if(!nomProv4.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv4+"\';");            
           while (conjuntoResultadosSin.next()) { prov4 = conjuntoResultadosSin.getObject(1).toString(); }
           
           }
           if(!nomProv5.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv5+"\';");            
           while (conjuntoResultadosSin.next()) { prov5 = conjuntoResultadosSin.getObject(1).toString(); }
           }
                      
           articulos.setClasificacion(prov1);
           articulos.setIdCompleto(prov2);
           articulos.setLineaPrincipal(prov3);
           articulos.setSinonimoPrincipal(prov4);
           articulos.setProveedor(prov5);
           
            listaarticulos.add(articulos);
            }
            return listaarticulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return listaarticulos;
    }
   
        public List<Articulos> consultaReportePedidos2(String condicion,String nomProv1,String nomProv2,String nomProv3,String nomProv4, String nomProv5)
    {
        List<Articulos> listaarticulos = new ArrayList<Articulos>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
        
        
              if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(condicion.equals("*"))
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE clasificacion LIKE \'A\' OR clasificacion LIKE 'B' OR clasificacion LIKE 'C' ORDER BY descripcion;";
            else
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND clasificacion REGEXP \'A|B|C\'  ORDER BY descripcion;";                
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
        
        datosEnv.put("prov1",nomProv1);
        datosEnv.put("prov2",nomProv2);
        datosEnv.put("prov3",nomProv3);
        datosEnv.put("prov4",nomProv4);
        datosEnv.put("prov5",nomProv5);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReportePedidos2()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
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
            Articulos articulos = new Articulos();
            articulos.setCodigo(atributos[1]);
            articulos.setDescripcion(atributos[2]);
            articulos.setExistencia(Long.valueOf(atributos[3]));
            articulos.setPrecioCompra(Double.valueOf(atributos[4]));
            articulos.setABC(atributos[5]);
            articulos.setMinimoPzas(Long.valueOf(atributos[6]));
            if(atributos[7].equals("Chica")||atributos[7].equals("Mediana")||atributos[7].equals("Grande")||atributos[7].equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(atributos[7]); 
           //segundas consultas...
           articulos.setClasificacion(atributos[8]);
           articulos.setIdCompleto(atributos[9]);
           articulos.setLineaPrincipal(atributos[10]);
           articulos.setSinonimoPrincipal(atributos[11]);
           articulos.setProveedor(atributos[12]);
           listaarticulos.add(articulos); 
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return listaarticulos;
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
            ResultSet conjuntoResultadosSin;
            if(condicion.equals("*"))
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE clasificacion LIKE \'A\' OR clasificacion LIKE 'B' OR clasificacion LIKE 'C' ORDER BY descripcion;");
            else
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND clasificacion REGEXP \'A|B|C\'  ORDER BY descripcion;");    
            //conjuntoResultados = conexion.crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion FROM articulos  WHERE existencia <= minimoPzas AND codigo NOT LIKE \'%R_\' AND codigo LIKE \'%"+condicion+"\' ORDER BY descripcion;");    
            while (conjuntoResultados.next()) {  
            Articulos articulos = new Articulos();
            articulos.setCodigo(conjuntoResultados.getObject(1).toString());
            articulos.setDescripcion(conjuntoResultados.getObject(2).toString());
            articulos.setExistencia(Long.valueOf(conjuntoResultados.getObject(3).toString()));
            articulos.setPrecioCompra(Double.valueOf(conjuntoResultados.getObject(4).toString()));
            articulos.setABC(conjuntoResultados.getObject(5).toString());
            articulos.setMinimoPzas(Long.valueOf(conjuntoResultados.getObject(6).toString()));
            if(conjuntoResultados.getObject(7).toString().equals("Chica")||conjuntoResultados.getObject(7).toString().equals("Mediana")||conjuntoResultados.getObject(7).toString().equals("Grande")||conjuntoResultados.getObject(7).toString().equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(conjuntoResultados.getObject(7).toString()); 
            
           String prov1=""; 
           String prov2="";
           String prov3="";
           String prov4="";
           String prov5="";
           
           if(!nomProv1.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv1+"\';");            
           while (conjuntoResultadosSin.next()) { prov1 = conjuntoResultadosSin.getObject(1).toString();  }
           }
           if(!nomProv2.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv2+"\';");            
           while (conjuntoResultadosSin.next()) { prov2 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv3.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv3+"\';");            
           while (conjuntoResultadosSin.next()) { prov3 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv4.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv4+"\';");            
           while (conjuntoResultadosSin.next()) { prov4 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv5.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv5+"\';");            
           while (conjuntoResultadosSin.next()) { prov5 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           
           articulos.setClasificacion(prov1);
           articulos.setIdCompleto(prov2);
           articulos.setLineaPrincipal(prov3);
           articulos.setSinonimoPrincipal(prov4);
           articulos.setProveedor(prov5);
           
            listaarticulos.add(articulos);
            }
            return listaarticulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return listaarticulos;
    }     
        
    public List<Articulos> consultaReportePedidos3(String condicion,String nomProv1,String nomProv2,String nomProv3,String nomProv4,String nomProv5)
    {
        List<Articulos> listaarticulos = new ArrayList<Articulos>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
        
               if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";
            if(condicion.equals("*"))
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE existencia <= minimoPzas AND clasificacion LIKE \'A\' OR clasificacion LIKE 'B' OR clasificacion LIKE 'C' ORDER BY descripcion;";
            else
            query = "SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND existencia <= minimoPzas AND clasificacion REGEXP \'A|B|C\' ORDER BY descripcion;";                
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
        
        
        datosEnv.put("prov1",nomProv1);
        datosEnv.put("prov2",nomProv2);
        datosEnv.put("prov3",nomProv3);
        datosEnv.put("prov4",nomProv4);
        datosEnv.put("prov5",nomProv5);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaReportePedidos3()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
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
            Articulos articulos = new Articulos();
            articulos.setCodigo(atributos[1]);
            articulos.setDescripcion(atributos[2]);
            articulos.setExistencia(Long.valueOf(atributos[3]));
            articulos.setPrecioCompra(Double.valueOf(atributos[4]));
            articulos.setABC(atributos[5]);
            articulos.setMinimoPzas(Long.valueOf(atributos[6]));
            if(atributos[7].equals("Chica")||atributos[7].equals("Mediana")||atributos[7].equals("Grande")||atributos[7].equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(atributos[7]); 
           //segundas consultas...
           articulos.setClasificacion(atributos[8]);
           articulos.setIdCompleto(atributos[9]);
           articulos.setLineaPrincipal(atributos[10]);
           articulos.setSinonimoPrincipal(atributos[11]);
           articulos.setProveedor(atributos[12]);
           listaarticulos.add(articulos); 
		   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return listaarticulos;
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
            ResultSet conjuntoResultadosSin;
            if(condicion.equals("*"))
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE existencia <= minimoPzas AND clasificacion LIKE \'A\' OR clasificacion LIKE 'B' OR clasificacion LIKE 'C' ORDER BY descripcion;");
            else
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion,minimoPzas,tipoEtiqueta FROM articulos  WHERE SUBSTRING(codigo, -1) IN (\'"+condicion+"\') AND existencia <= minimoPzas AND clasificacion REGEXP \'A|B|C\' ORDER BY descripcion;");    
            //conjuntoResultados = conexion.crearConsulta("SELECT codigo,descripcion,existencia,precioCompra,clasificacion FROM articulos  WHERE existencia <= minimoPzas AND codigo NOT LIKE \'%R_\' AND codigo LIKE \'%"+condicion+"\' ORDER BY descripcion;");    
            while (conjuntoResultados.next()) {  
            Articulos articulos = new Articulos();
            articulos.setCodigo(conjuntoResultados.getObject(1).toString());
            articulos.setDescripcion(conjuntoResultados.getObject(2).toString());
            articulos.setExistencia(Long.valueOf(conjuntoResultados.getObject(3).toString()));
            articulos.setPrecioCompra(Double.valueOf(conjuntoResultados.getObject(4).toString()));
            articulos.setABC(conjuntoResultados.getObject(5).toString());
            articulos.setMinimoPzas(Long.valueOf(conjuntoResultados.getObject(6).toString()));
            if(conjuntoResultados.getObject(7).toString().equals("Chica")||conjuntoResultados.getObject(7).toString().equals("Mediana")||conjuntoResultados.getObject(7).toString().equals("Grande")||conjuntoResultados.getObject(7).toString().equals("1"))
            articulos.setTipoEtiqueta(""); 
            else
            articulos.setTipoEtiqueta(conjuntoResultados.getObject(7).toString()); 
            
           String prov1=""; 
           String prov2="";
           String prov3="";
           String prov4="";
           String prov5="";
           
           
           if(!nomProv1.isEmpty())
           {            
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv1+"\';");            
           while (conjuntoResultadosSin.next()) { prov1 = conjuntoResultadosSin.getObject(1).toString();  }
           }
           if(!nomProv2.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv2+"\';");            
           while (conjuntoResultadosSin.next()) { prov2 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv3.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv3+"\';");            
           while (conjuntoResultadosSin.next()) { prov3 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv4.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv4+"\';");            
           while (conjuntoResultadosSin.next()) { prov4 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           if(!nomProv5.isEmpty())
           {
           conjuntoResultadosSin = CPrincipal.getConexion().crearConsulta("SELECT sinonimo FROM articuloproveedor  WHERE idArticulo = \'"+articulos.getCodigo()+"\' AND idProveedor = \'"+nomProv5+"\';");            
           while (conjuntoResultadosSin.next()) { prov5 = conjuntoResultadosSin.getObject(1).toString(); }
           }
           
           articulos.setClasificacion(prov1);
           articulos.setIdCompleto(prov2);
           articulos.setLineaPrincipal(prov3);
           articulos.setSinonimoPrincipal(prov4);
           articulos.setProveedor(prov5);
           
            listaarticulos.add(articulos);
            }
            return listaarticulos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return listaarticulos;
    }
        
        
    public int guardarArticulos(Articulos a, List<Articulolinea> lineasGuardadas, List <Articuloproveedor>proveedoresGuardados, List <Articuloubicacion>ubicacionesGuardadas)
    {
 
            int ultimoInsertado=-1;
            String query = " INSERT INTO articulos (codigo," + 
                    "descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad," +
                    "existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,lineaPrincipal,anticipos,sinonimoPrincipal,oferta) VALUES" 
                    + "(\'" + a.getCodigo() 
                    + "\',\'" + a.getDescripcion() 
                    + "\',\'" + a.getClasificacion() 
                    + "\',\'" + a.getPrecioVenta() 
                    + "\',\'" + a.getPrecioCompra() 
                    + "\',\'" + a.getUltimoCosto() 
                    + "\',\'" + a.getUnidad() 
                    + "\',\'" + a.getExistencia() 
                    + "\',\'" + a.getReservado() 
                    + "\',\'" + a.getMinimoPzas() 
                    + "\',\'" + a.getMaximoPzas() 
                    + "\',\'" + a.getPromPzas() 
                    + "\',\'" + a.getIva() 
                    + "\',\'" + a.getIeps() 
                    + "\',\'" + a.getTipoEtiqueta() 
                    + "\',\'" + a.getLineaPrincipal()
                    + "\',\'" + a.getAnticipos()
                    + "\',\'" + a.getSinonimoPrincipal()
                    + "\',\'" + a.getOferta()
                    + "');";
         //   Conexion conexion = new Conexion();
         //   conexion.crearConexion();
            
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      ultimoInsertado = conexionweb.escribirEnWebLast(query);
      return ultimoInsertado;
       }else    
       {     
            ultimoInsertado = CPrincipal.getConexion().moverDatosLast(query);
            CPrincipal.getConexion().cerrar(1);
       }     
            // conexion.crearConexion();

            //para grabar las otras 3 tablas...


            if(ultimoInsertado != -1)
            {
              //  Articulolinea articulolinea = new Articulolinea();
                hArticulolinea hali = new hArticulolinea();
                int indlineas = 0;
                if(lineasGuardadas != null && !lineasGuardadas.isEmpty())
                      {
                while(indlineas < lineasGuardadas.size())
                {
                lineasGuardadas.get(indlineas).setClaveArticulo(ultimoInsertado);
                hali.guardarArticuloLinea(lineasGuardadas.get(indlineas));
                indlineas++;
                }
                      }
                // indlineas=0;
                hArticuloProveedor harticuloproveedor = new hArticuloProveedor();
                int indprov = 0;
                if(proveedoresGuardados != null && !proveedoresGuardados.isEmpty())
                      {
                while(indprov < proveedoresGuardados.size())
                {
                proveedoresGuardados.get(indprov).setIdArticulo(a.getCodigo());
                harticuloproveedor.guardarArtProv(proveedoresGuardados.get(indprov));
                indprov++;
                }
                      }
              //  indprov = 0;
                hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
                int indubi = 0;
                if(ubicacionesGuardadas != null && !ubicacionesGuardadas.isEmpty())
                {
                while(indubi < ubicacionesGuardadas.size())
                {
                ubicacionesGuardadas.get(indubi).setIdarticulo(ultimoInsertado);
                harticuloubicacion.guardarArticuloUbicacion(ubicacionesGuardadas.get(indubi));
                indubi++;
                }
                }
             //   indubi = 0;
            }


            return ultimoInsertado;
    }

      public int guardarArticulosCatalogo(Articulos a, List<Articulolinea> lineasGuardadas, List <Articuloproveedor>proveedoresGuardados, List <Articuloubicacion>ubicacionesGuardadas)
    {
 
            int ultimoInsertado=-1;
            String query = " INSERT INTO articulos (codigo," + 
                    "descripcion,clasificacion,precioVenta,precioCompra,ultimoCosto,unidad," +
                    "existencia,reservado,minimoPzas,maximoPzas,promPzas,iva,ieps,tipoEtiqueta,lineaPrincipal,anticipos,sinonimoPrincipal,codigo2,precioVenta2,proveedor,paretto,nuevo,oferta) VALUES" 
                    + "(\'" + a.getCodigo() 
                    + "\',\'" + a.getDescripcion() 
                    + "\',\'" + a.getClasificacion() 
                    + "\',\'" + a.getPrecioVenta() 
                    + "\',\'" + a.getPrecioCompra() 
                    + "\',\'" + a.getUltimoCosto() 
                    + "\',\'" + a.getUnidad() 
                    + "\',\'" + a.getExistencia() 
                    + "\',\'" + a.getReservado() 
                    + "\',\'" + a.getMinimoPzas() 
                    + "\',\'" + a.getMaximoPzas() 
                    + "\',\'" + a.getPromPzas() 
                    + "\',\'" + a.getIva() 
                    + "\',\'" + a.getIeps() 
                    + "\',\'" + a.getTipoEtiqueta() 
                    + "\',\'" + a.getLineaPrincipal()
                    + "\',\'" + a.getAnticipos()
                    + "\',\'" + a.getSinonimoPrincipal()
                    + "\',\'" + a.getCodigo2()
                    + "\',\'" + a.getPrecioVenta2()
                    + "\',\'" + a.getProveedor()
                    + "\',\'" + a.getParetto()
                    + "\',\'" + a.getNuevo()
                    + "\',\'" + a.getOferta()
                    + "');";
         //   Conexion conexion = new Conexion();
         //   conexion.crearConexion();
       if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      ultimoInsertado = conexionweb.escribirEnWebLast(query);
       }else
       {    
            ultimoInsertado = CPrincipal.getConexion().moverDatosLast(query);
            CPrincipal.getConexion().cerrar(1);
       }
          //  conexion.crearConexion();

            //para grabar las otras 3 tablas...


            if(ultimoInsertado != -1)
            {
              //  Articulolinea articulolinea = new Articulolinea();
                hArticulolinea hali = new hArticulolinea();
                int indlineas = 0;
                if(lineasGuardadas != null && !lineasGuardadas.isEmpty())
                      {
                while(indlineas < lineasGuardadas.size())
                {
                lineasGuardadas.get(indlineas).setClaveArticulo(ultimoInsertado);
                hali.guardarArticuloLinea(lineasGuardadas.get(indlineas));
                indlineas++;
                }
                      }
                // indlineas=0;
                hArticuloProveedor harticuloproveedor = new hArticuloProveedor();
                int indprov = 0;
                if(proveedoresGuardados != null && !proveedoresGuardados.isEmpty())
                      {
                while(indprov < proveedoresGuardados.size())
                {
                proveedoresGuardados.get(indprov).setIdArticulo(a.getCodigo());
                harticuloproveedor.guardarArtProv(proveedoresGuardados.get(indprov));
                indprov++;
                }
                      }
                // indprov = 0;
                hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
                int indubi = 0;
                if(ubicacionesGuardadas != null && !ubicacionesGuardadas.isEmpty())
                {
                while(indubi < ubicacionesGuardadas.size())
                {
                ubicacionesGuardadas.get(indubi).setIdarticulo(ultimoInsertado);
                harticuloubicacion.guardarArticuloUbicacion(ubicacionesGuardadas.get(indubi));
                indubi++;
                }
                }
                // indubi = 0;
            }


            return ultimoInsertado;
    }
    
    public int borrarArticulos(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
        
        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulos WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM articulos WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

    /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarArticulos(Articulos a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articulos SET codigo = \'" +  a.getCodigo()+"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\',"+
                  "clasificacion = \'" +   a.getClasificacion()+"\',"+
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+
                  "ultimoCosto = \'" +   a.getUltimoCosto()+"\',"+
                  "unidad = \'" +    a.getUnidad()+"\',"+
                  "existencia = \'" +    a.getExistencia()+"\',"+
                  "reservado = \'" +     a.getReservado()+"\',"+
                  "minimoPzas = \'" +    a.getMinimoPzas()+"\',"+
                  "maximoPzas = \'" +    a.getMaximoPzas()+"\',"+
                  "promPzas = \'" +    a.getPromPzas()+"\',"+
                  "iva = \'" +    a.getIva()+"\',"+
                  "ieps = \'" +    a.getIeps()+"\',"+
                  "lineaPrincipal = \'" +    a.getLineaPrincipal()+"\',"+
                  "sinonimoPrincipal = \'" +    a.getSinonimoPrincipal()+"\',"+
                  "anticipos = \'" +    a.getAnticipos()+"\',"+
                  "oferta = \'" +    a.getOferta()+"\',"+
                  "tipoEtiqueta = \'" +    a.getTipoEtiqueta()+"\'";
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
    
     public String crearQueryActualizarArticulos(Articulos a,String campoCondicion,String match,String condicion)
    {
     
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articulos SET codigo = \'" +  a.getCodigo()+"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\',"+
                  "clasificacion = \'" +   a.getClasificacion()+"\',"+
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+
                  "ultimoCosto = \'" +   a.getUltimoCosto()+"\',"+
                  "unidad = \'" +    a.getUnidad()+"\',"+
                  "existencia = \'" +    a.getExistencia()+"\',"+
                  "reservado = \'" +     a.getReservado()+"\',"+
                  "minimoPzas = \'" +    a.getMinimoPzas()+"\',"+
                  "maximoPzas = \'" +    a.getMaximoPzas()+"\',"+
                  "promPzas = \'" +    a.getPromPzas()+"\',"+
                  "iva = \'" +    a.getIva()+"\',"+
                  "ieps = \'" +    a.getIeps()+"\',"+
                  "lineaPrincipal = \'" +    a.getLineaPrincipal()+"\',"+
                  "sinonimoPrincipal = \'" +    a.getSinonimoPrincipal()+"\',"+
                  "anticipos = \'" +    a.getAnticipos()+"\',"+
                  "oferta = \'" +    a.getOferta()+"\',"+
                  "tipoEtiqueta = \'" +    a.getTipoEtiqueta()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }
                   
    

     return query;
    }
    
    
        public boolean actualizarCodigoArticulo(String codigoNuevo, String codigoViejo)
    {
     String query = "UPDATE articulos SET codigo = \'"+codigoNuevo+"\' WHERE codigo = \'"+codigoViejo+"\';";
     
     if(CGlobalConfig.isWeb())
       {
      ConexionWeb conexionweb = new ConexionWeb();
      conexionweb.escribirEnWeb(query);
      return true;
       }  

     //  System.out.println(query);
       CPrincipal.getConexion().moverDatos(query);
       CPrincipal.getConexion().cerrar(1);

     return true;
    }  
    
        /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarArticulosCatalogo(Articulos a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE articulos SET codigo = \'" +  a.getCodigo()+"\',"+
                  "descripcion = \'" +   a.getDescripcion()+"\',"+
                  "clasificacion = \'" +   a.getClasificacion()+"\',"+
                  "precioVenta = \'" +   a.getPrecioVenta()+"\',"+
                  "precioCompra = \'" +   a.getPrecioCompra()+"\',"+
                  "ultimoCosto = \'" +   a.getUltimoCosto()+"\',"+
                  "unidad = \'" +    a.getUnidad()+"\',"+
                  "existencia = \'" +    a.getExistencia()+"\',"+
                  "reservado = \'" +     a.getReservado()+"\',"+
                  "minimoPzas = \'" +    a.getMinimoPzas()+"\',"+
                  "maximoPzas = \'" +    a.getMaximoPzas()+"\',"+
                  "promPzas = \'" +    a.getPromPzas()+"\',"+
                  "iva = \'" +    a.getIva()+"\',"+
                  "ieps = \'" +    a.getIeps()+"\',"+
                  "lineaPrincipal = \'" +    a.getLineaPrincipal()+"\',"+
                  "sinonimoPrincipal = \'" +    a.getSinonimoPrincipal()+"\',"+
                  "anticipos = \'" +    a.getAnticipos()+"\',"+
                  "codigo2 = \'" +    a.getCodigo2()+"\',"+
                  "precioVenta2 = \'" +    a.getPrecioVenta2()+"\',"+
                  "proveedor = \'" +    a.getProveedor()+"\',"+
                  "paretto = \'" +    a.getParetto()+"\',"+
                  "nuevo = \'" +    a.getNuevo()+"\',"+
                  "oferta = \'" +    a.getOferta()+"\',"+
                  "tipoEtiqueta = \'" +    a.getTipoEtiqueta()+"\'";
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
    
        /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int bloquearArticulo(Articulos a,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();


     String query = "UPDATE articulos SET bloqueado = \'" +  a.getBloqueado()+"\'";
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
    
     public String crearQuerybloquearArticulo(Articulos a,String campoCondicion,String match,String condicion)
    {
     
     String query = "UPDATE articulos SET bloqueado = \'" +  a.getBloqueado()+"\'";
                   if(match.equals("="))
            {
            query = query + " WHERE "+campoCondicion+" "+match+" \'"+condicion+"\';";
            } else if(match.equals("LIKE"))
               {
            query = query + " WHERE "+campoCondicion+" "+match+" \'%"+condicion+"%\';";
               }

     
     return query;
    }
    
     /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarExistencias(String codigo,int cantidad)
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
       String query = "";       
       if(cantidad == 0)
       query = "UPDATE articulos SET existencia = \'"+cantidad +"\',nuevo = \'0\' WHERE codigo = \'"+codigo+"\';";
       else
       query = "UPDATE articulos SET existencia = \'"+cantidad +"\' WHERE codigo = \'"+codigo+"\';";
       
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
    
    
    
      public String crearQueryActualizarExistencias(String codigo,int cantidad)
    {
       String query = "";       
       if(cantidad == 0)
       query = "UPDATE articulos SET existencia = \'"+cantidad +"\',nuevo = \'0\' WHERE codigo = \'"+codigo+"\';";
       else
       query = "UPDATE articulos SET existencia = \'"+cantidad +"\' WHERE codigo = \'"+codigo+"\';";
       
       return query;
    }
    
    
         /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarOferta(String codigo,String precioVenta2, int oferta)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       
       String query = "UPDATE articulos SET precioVenta2 = \'"+precioVenta2 +"\',oferta = \'"+oferta+"\' WHERE codigo = \'"+codigo+"\';";
       
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
    
    /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int quitarOfertas()
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
       
       String query = "UPDATE articulos SET oferta = \'0\' WHERE oferta = \'1\';";
       
       
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
    
        public int actualizarClasificacion(String codigo,String clasificacion,String paretto)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       String query = "UPDATE articulos "
                    + "SET clasificacion = \'"+clasificacion +"\',"
                    + "tipoEtiqueta = \'"+paretto+"\' WHERE codigo = \'"+codigo+"\';";
       
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
    
     public int actualizarExistenciasDev(String codigo,int cantidad)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       String query = "UPDATE articulos SET almacenDevoluciones = \'"+cantidad +"\' WHERE codigo = \'"+codigo+"\';";
       
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
     
      public String crearQueryActualizarExistenciasDev(String codigo,int cantidad)
    {
     
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
     String query = "UPDATE articulos SET almacenDevoluciones = \'"+cantidad +"\' WHERE codigo = \'"+codigo+"\';";
     return query;
    } 
    
         /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarExistenciasCodigo(String codigo,int cantidad)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       String query = "UPDATE articulos SET existencia = \'"+cantidad +"\' WHERE codigo = \'"+codigo+"\';";
       
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
    
     public int limpiarParetto()
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       String query = "UPDATE articulos SET clasificacion = \'1\', tipoEtiqueta = \'1\' "
                  // + "WHERE clasificacion = \'A\' OR clasificacion = \'B\' OR clasificacion = \'C\';";
                   + "WHERE clasificacion NOT LIKE \'1\';";
       
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

       public boolean aumentaAlmacenDevoluciones(String campo, int cantidad)
    {
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
       try {
               
           if(CGlobalConfig.isWeb())
           {
               
         Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "SELECT almacenDevoluciones FROM articulos WHERE codigo = "+" \'"+campo+"\';";  
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("metodo","aumentaAlmacenDevoluciones()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
          int catalogo = 0;
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
            catalogo = Integer.parseInt(atributos[1]);
            cantidad = catalogo + cantidad;
               }
           s = recv.readLine();
          }
             }catch (Exception e){
           System.out.println(e.getMessage());
          }              
               
           }
           else{
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT almacenDevoluciones FROM articulos WHERE codigo = "+" \'"+campo+"\';");
            while (conjuntoResultados.next()) {
            int catalogo = Integer.parseInt(conjuntoResultados.getObject(1).toString());
            cantidad = catalogo + cantidad;
            }
                }
            
             String query = "UPDATE articulos SET almacenDevoluciones = \'"+cantidad +"\' WHERE codigo = \'"+campo+"\';";
             
            if(CGlobalConfig.isWeb())
             {
           ConexionWeb conexionweb = new ConexionWeb();
           conexionweb.escribirEnWeb(query);
           return true;
           } 
             
             CPrincipal.getConexion().moverDatos(query);
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(1);
        }
        return false;
    }
       
       
       public String crearQueryAumentaAlmacenDevoluciones(String campo, int cantidad)
    {
//            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT almacenDevoluciones FROM articulos WHERE codigo = "+" \'"+campo+"\';");            
             String query = "UPDATE articulos SET almacenDevoluciones = \'"+cantidad +"\' WHERE codigo = \'"+campo+"\';";          
        return query;
    }       
       
    
       public boolean disminuyeAlmacenDevoluciones(String campo, int cantidad)
    {
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
       try {
           
              if(CGlobalConfig.isWeb())
           {
               
         Map <String,String> datosEnv=new HashMap<String,String>();
            
         String query = "SELECT almacenDevoluciones FROM articulos WHERE codigo = "+" \'"+campo+"\';";  
            
        datosEnv.put("consulta",query);
        datosEnv.put("base",CGlobalConfig.getConexion());
        datosEnv.put("metodo","disminuyeAlmacenDevoluciones()");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
       try{
          //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
          URLConnection con = (URLConnection) url.openConnection();
          con.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
          wr.write(jsonOutput);
          wr.flush();
          //Recibimos los datos
          BufferedReader recv = new BufferedReader(new InputStreamReader(con.getInputStream()));


          //Cargamos la lista de objetos
          int catalogo = 0;
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
           catalogo =  Integer.parseInt(atributos[1]);
           cantidad = catalogo - cantidad;
            if(Integer.compare(cantidad, 0)<0)
                cantidad=0;
               }
           s = recv.readLine();
          }
             }catch (Exception e){
           System.out.println(e.getMessage());
          }              
               
           }
           
           else{
            ResultSet conjuntoResultados;
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT almacenDevoluciones FROM articulos WHERE codigo = "+" \'"+campo+"\';");
            while (conjuntoResultados.next()) {
            int catalogo = Integer.parseInt(conjuntoResultados.getObject(1).toString());
            cantidad = catalogo - cantidad;
            if(Integer.compare(cantidad, 0)<0)
                cantidad=0;
            }
               }
             String query = "UPDATE articulos SET almacenDevoluciones = \'"+cantidad +"\' WHERE codigo = \'"+campo+"\';";
             
             if(CGlobalConfig.isWeb())
             {
           ConexionWeb conexionweb = new ConexionWeb();
           conexionweb.escribirEnWeb(query);
           return true;
           } 
             
             CPrincipal.getConexion().moverDatos(query);
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(1);
        }
        return false;
    }
       
    public List<Inventario> inicializaInventario()
    {
       List<Inventario> articulos = new ArrayList<Inventario>();
       List<Inventario> articulosunicos = new ArrayList<Inventario>();
       // Conexion conexion = new Conexion();
       //         conexion.crearConexion();
       
       if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
        String query = "SELECT a.codigo as codigo,a.descripcion as descripcion,a.precioCompra as precioCompra,a.existencia as existencia,"
                + "a.almacenDevoluciones as almacenDevoluciones,a.reservado as reservado, a.anticipos as anticipos,a.id as id, u.ubicacion as ubicacion FROM articulos a left join articuloubicacion au on a.id = au.idarticulo left join ubicacion u on au.idubicacion = u.id ORDER BY a.descripcion ASC;";
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","inicializaInventario()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeArticulos.php");
		  
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
           
	                Inventario articulo = new Inventario();
                        articulo.setCodigo(atributos[1]);
                        articulo.setDescripcion(atributos[2]);
                        articulo.setPrecioCompra(Double.valueOf(atributos[3]));
                        articulo.setAlmacenG(Integer.valueOf(atributos[4]));
                        articulo.setAlmacenD(Integer.valueOf(atributos[5]));
                        articulo.setReservados(Integer.valueOf(atributos[6]));
                        articulo.setAnticipos(Integer.valueOf(atributos[7]));
                        articulo.setSistema(articulo.getAlmacenG()+articulo.getAlmacenD()+articulo.getReservados()+articulo.getAnticipos());
                        articulo.setId(atributos[8]);
                        articulo.setFila(atributos[9]);
                        articulos.add(articulo);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
             boolean bandera = false;
            for(int i=0; i<articulos.size(); i++)
            {
                bandera = false;
                for(int o=0; o<articulosunicos.size(); o++)
                {
                 if(articulos.get(i).getCodigo().equals(articulosunicos.get(o).getCodigo()))   
                 bandera = true;    
                }
                if(!bandera)
                articulosunicos.add(articulos.get(i));    
            }
            
            return articulosunicos;
        } catch (Exception ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } 
           return null;
		   //Regresa nulo o 0
		   	   
        }
       
       
       
       
       
       
       try {
            ResultSet conjuntoResultados;
//            conjuntoResultados = conexion.crearConsulta("SELECT codigo,descripcion,precioCompra,existencia,almacenDevoluciones,reservado, anticipos,id FROM articulos a ORDER BY descripcion ASC;");
//              conjuntoResultados = conexion.crearConsulta("SELECT a.codigo,a.descripcion,a.precioCompra,a.existencia,a.almacenDevoluciones,a.reservado, a.anticipos,a.id, u.ubicacion FROM ubicacion u, articulos a left join articuloubicacion au on a.id = au.idarticulo WHERE au.idubicacion = u.id ORDER BY descripcion ASC;");
              conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT a.codigo,a.descripcion,a.precioCompra,a.existencia,a.almacenDevoluciones,a.reservado, a.anticipos,a.id, u.ubicacion FROM articulos a left join articuloubicacion au on a.id = au.idarticulo left join ubicacion u on au.idubicacion = u.id ORDER BY a.descripcion ASC;");
              

            while (conjuntoResultados.next()) {
                        Inventario articulo = new Inventario();
                        articulo.setCodigo(conjuntoResultados.getObject(1).toString());
                        articulo.setDescripcion(conjuntoResultados.getObject(2).toString());
                        articulo.setPrecioCompra(Double.valueOf(conjuntoResultados.getObject(3).toString()));
                        articulo.setAlmacenG(Integer.valueOf(conjuntoResultados.getObject(4).toString()));
                        articulo.setAlmacenD(Integer.valueOf(conjuntoResultados.getObject(5).toString()));
                        articulo.setReservados(Integer.valueOf(conjuntoResultados.getObject(6).toString()));
                        articulo.setAnticipos(Integer.valueOf(conjuntoResultados.getObject(7).toString()));
                        articulo.setSistema(articulo.getAlmacenG()+articulo.getAlmacenD()+articulo.getReservados()+articulo.getAnticipos());
                        articulo.setId(conjuntoResultados.getObject(8).toString());
                        if(conjuntoResultados.getObject(9)==null)
                        articulo.setFila("NO IDENTIFICADA");
                        else
                        articulo.setFila(conjuntoResultados.getObject(9).toString());
                        articulos.add(articulo);
            }
            boolean bandera = false;
            for(int i=0; i<articulos.size(); i++)
            {
                bandera = false;
                for(int o=0; o<articulosunicos.size(); o++)
                {
                 if(articulos.get(i).getCodigo().equals(articulosunicos.get(o).getCodigo()))   
                 bandera = true;    
                }
                if(!bandera)
                articulosunicos.add(articulos.get(i));    
            }
            
            return articulosunicos;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
       
       
       
    public static void main(String args[])
    {
       hArticulos ejemplo = new hArticulos();
       List<Articulos> articulos = ejemplo.consultaArticulos("id","LIKE","musta");
       int i = 0;
       while(i < articulos.size())
       {
           System.out.println(articulos.get(i).getDescripcion());
           i++;
       }
       System.out.println(ejemplo.borrarArticulos("codigo", "LIKE", "003"));

       Articulos art = new Articulos();
       art.setCodigo("a003");
       art.setClasificacion("grande");
       art.setDescripcion("SALPICADERA MUSTANG");
       art.setExistencia(6);
       art.setIeps(15);
       art.setIva(16);
       art.setMaximoPzas(2);
       art.setMinimoPzas(3);
       art.setPrecioCompra(40.5);
       art.setPrecioVenta(60.0);
       art.setPromPzas(2);
       art.setReservado(8);
       art.setTipoEtiqueta("9");
       art.setUltimoCosto(40.5);
       art.setUnidad("7");
       art.setSinonimoPrincipal("");
       art.setLineaPrincipal("");

      System.out.println(ejemplo.guardarArticulos(art,null,null,null));

      art.setUnidad("PZA");
      art.setIva(16);
      System.out.println(ejemplo.actualizarArticulos(art, "codigo", "=", "a003"));


    }

}
