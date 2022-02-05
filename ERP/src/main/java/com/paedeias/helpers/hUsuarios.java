/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

/**
 *
 * @author Luis
 */
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.identidades.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
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


public class hUsuarios {

     public List<Usuarios> consultaUsuarios(String campo, String match, String condicion) //En este query ya importa el orden para el historial de clientes...
    {
       List<Usuarios> usuarios = new ArrayList<Usuarios>();  
       if(CGlobalConfig.isWeb())  
       {
           
        Map <String,String> datosEnv=new HashMap<String,String>();
        String query = "";
        if(match.equals("*")){
            query = "SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios  ORDER BY id DESC;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC;";
            }else
            {
            query = "SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC;";
            }
        
        
        
        datosEnv.put("consulta",query);
        datosEnv.put("metodo","consultaUsuarios()");
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            //Usamos URLencode para poder enviar la cadena
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");
          //Establecemos la conexion y enviamos los datos
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeUsuarios.php");
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
           Usuarios usuario =new Usuarios();
                usuario.setId(Long.parseLong(atributos[1]));
                usuario.setNombres(atributos[2]);
                usuario.setApellidoP(atributos[3]);
                usuario.setApellidoM(atributos[4]);
                usuario.setContrasena(atributos[5]);
                usuario.setPuesto(atributos[6]);
                usuario.setCorreo(atributos[7]);
                usuario.setFoto(atributos[8]);
                usuario.setDescMax(Double.parseDouble(atributos[9]));
                usuario.setUtilMax(Double.parseDouble(atributos[10]));
                usuario.setRangoPrecio(Double.parseDouble(atributos[11]));
                usuario.setComision(Double.parseDouble(atributos[12]));
                usuario.setMeta(atributos[13]);
                usuario.setMetaPasada(atributos[14]);
                usuario.setMetaPasada2(atributos[15]);

                usuarios.add(usuario);
           }
           s=recv.readLine();
           }
            
           return usuarios;
          
        }catch (Exception e){
            
           e.printStackTrace();
       }
          return null; 
       }
         
         
         
         
         
       
   //    Conexion conexion = new Conexion();
   //             conexion.crearConexion();
       try {
            ResultSet conjuntoResultados;
            if(match.equals("*")){
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios  ORDER BY id DESC;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,nombres,apellidoP,apellidoM,contrasena,puesto,correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2 FROM usuarios WHERE "+campo+" "+match+" \'%"+condicion+"%\' ORDER BY id DESC;");
            }

            while (conjuntoResultados.next()) {

                Usuarios usuario =new Usuarios();
               /* Usuarios usuario = new Usuarios(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        conjuntoResultados.getObject(6).toString(),
                        conjuntoResultados.getObject(7).toString(),
                        conjuntoResultados.getObject(8).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(9).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(10).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(11).toString()),
                        Double.parseDouble(conjuntoResultados.getObject(11).toString())
                        ); */
                
                usuario.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                usuario.setNombres(conjuntoResultados.getObject(2).toString());
                usuario.setApellidoP(conjuntoResultados.getObject(3).toString());
                usuario.setApellidoM(conjuntoResultados.getObject(4).toString());
                usuario.setContrasena(conjuntoResultados.getObject(5).toString());
                usuario.setPuesto(conjuntoResultados.getObject(6).toString());
                usuario.setCorreo(conjuntoResultados.getObject(7).toString());
                usuario.setFoto(conjuntoResultados.getObject(8).toString());
                usuario.setDescMax(Double.parseDouble(conjuntoResultados.getObject(9).toString()));
                usuario.setUtilMax(Double.parseDouble(conjuntoResultados.getObject(10).toString()));
                usuario.setRangoPrecio(Double.parseDouble(conjuntoResultados.getObject(11).toString()));
                usuario.setComision(Double.parseDouble(conjuntoResultados.getObject(12).toString()));
                usuario.setMeta(conjuntoResultados.getObject(13).toString());
                usuario.setMetaPasada(conjuntoResultados.getObject(14).toString());
                usuario.setMetaPasada2(conjuntoResultados.getObject(15).toString());

                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(hArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

      public int guardarUsuarios(Usuarios u)
    {
       int filas_afectadas = 0;
       String query = " INSERT INTO usuarios (nombres,apellidoP,apellidoM,contrasena,puesto,"
                      + "correo,foto,descMax,utilMax,rangoPrecio,comision,meta,metaPasada,metaPasada2) VALUES"
                    + "(\'"+u.getNombres()+"\',\'"
                         +u.getApellidoP()+"\',\'"
                         +u.getApellidoM()+"\',\'"
                         +u.getContrasena()+"\',\'"
                         +u.getPuesto()+"\',\'"
                         +u.getCorreo()+"\',\'"
                         +u.getFoto()+"\',\'"
                         +u.getDescMax()+"\',\'"
                         +u.getUtilMax()+"\',\'"
                         +u.getRangoPrecio()+"\',\'"
                         +u.getComision()+"\',\'"
                         +u.getMeta()+"\',\'"
                         +u.getMetaPasada()+"\',\'"
                         +u.getMetaPasada2()+"\'"
                         + ");";
            //    Conexion conexion = new Conexion();
            //    conexion.crearConexion();
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

        public int borrarUsuarios(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
      
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM usuarios WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM usuarios WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     } 

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM usuarios WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM usuarios WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

                 /*Este Update permite actualizar varios datos sin necesidad de ciclar con el %LIKE%*/
    public int actualizarUsuarios(Usuarios u)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();


     String query = "UPDATE usuarios SET "
             + "nombres = \'" +  u.getNombres()+"\',"+
               "apellidoP = \'"+u.getApellidoP()+"\',"+
               "apellidoM = \'"+u.getApellidoM()+"\',"+
               "contrasena = \'"+u.getContrasena()+"\',"+
               "puesto = \'"+u.getPuesto()+"\',"+
               "correo = \'"+u.getCorreo()+"\',"+
               "foto = \'"+u.getFoto()+"\',"+
               "descMax = \'"+u.getDescMax()+"\',"+
               "utilMax = \'"+u.getUtilMax()+"\',"+
               "rangoPrecio = \'"+u.getRangoPrecio()+"\',"+
               "meta = \'"+u.getMeta()+"\',"+
               "comision = \'"+u.getComision()+"\'"+
               " WHERE id = \'"+u.getId()+"\'";

       
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
    public int actualizarUsuariosMetas(Usuarios u)
    {
     int resultado = 0;
  //   Conexion conexion = new Conexion();
  //            conexion.crearConexion();
     String query = "UPDATE usuarios SET "+
               "metaPasada = \'"+u.getMetaPasada()+"\',"+
               "metaPasada2 = \'"+u.getMetaPasada2()+"\'"+
               " WHERE id = \'"+u.getId()+"\'";       
     
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
       hUsuarios ejemplo = new hUsuarios();
       List<Usuarios> usuarios = ejemplo.consultaUsuarios("id","=","1");
       int i = 0;
       while(i < usuarios.size())
       {
           System.out.println(usuarios.get(i).getNombres());
           i++;
       }
       System.out.println(ejemplo.borrarUsuarios("nombres", "LIKE", "Rosa"));

       Usuarios usu = new Usuarios("Rosa","Alcántara","García","rosaalcantara","vendedor","rosa@hotmail.com","0",0,0,0,0.01);
       System.out.println(ejemplo.guardarUsuarios(usu));

      usu.setId(2);
      usu.setContrasena("gargaduc");
      usu.setNombres("Rocio");
      System.out.println(ejemplo.actualizarUsuarios(usu));


    }


}
