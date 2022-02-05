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
import com.paedeias.identidades.Clientes;
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
import com.paedeias.controladores.CGlobalConfig;

public class hClientes {

public hClientes(){}

          /*Permite consultar todos los datos, o algunos datos dependiendo de la condición*/
    public List<Clientes> consultaClientes(String campo, String match, String condicion)
    {
       List<Clientes> clientes = new ArrayList<Clientes>();
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";            
            if(match.equals("*")){
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else
            {
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaClientes()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeClientes.php");
		  
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
                        Clientes cliente = new Clientes(
                        Long.parseLong(atributos[1]),
                        atributos[2],
                        atributos[3],
                        atributos[4],
                        atributos[5],
                        atributos[6],
                        atributos[7],
                        atributos[8],
                        atributos[9],
                        atributos[10],
                        atributos[11],
                        atributos[12],
                        atributos[13],
                        atributos[14],
                        atributos[15],
                        Integer.parseInt(atributos[16]),
                        Integer.parseInt(atributos[17]),
                        Integer.parseInt(atributos[18]),
                        Integer.parseInt(atributos[19]),
                        Integer.parseInt(atributos[20]),
                        Integer.parseInt(atributos[21]),
                        Integer.parseInt(atributos[22]),
                        Integer.parseInt(atributos[23]),
                        Integer.parseInt(atributos[24]),
                        Integer.parseInt(atributos[25]),
                        Integer.parseInt(atributos[26]),
                        Integer.parseInt(atributos[27]),
                        Integer.parseInt(atributos[28]),
                        atributos[29],
                        Integer.parseInt(atributos[30]),
                        atributos[31],
                        Double.parseDouble(atributos[32]),
                        atributos[33],
                        atributos[34],
                        Integer.parseInt(atributos[35]),
                        Integer.parseInt(atributos[36]),
                        Integer.parseInt(atributos[37]),
                        Integer.parseInt(atributos[38]),
                        Integer.parseInt(atributos[39]),
                        Integer.parseInt(atributos[40]),
                        Integer.parseInt(atributos[41]),
                        atributos[42],
                        atributos[43]
                        );
                cliente.setCheckCredito(Integer.parseInt(atributos[44]));
                clientes.add(cliente);	   
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return clientes;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
            
            
            
            while (conjuntoResultados.next()) {

                Clientes cliente = new Clientes(
                        Long.parseLong(conjuntoResultados.getObject(1).toString()),
                        conjuntoResultados.getObject(2).toString(),
                        conjuntoResultados.getObject(3).toString(),
                        conjuntoResultados.getObject(4).toString(),
                        conjuntoResultados.getObject(5).toString(),
                        conjuntoResultados.getObject(6).toString(),
                        conjuntoResultados.getObject(7).toString(),
                        conjuntoResultados.getObject(8).toString(),
                        conjuntoResultados.getObject(9).toString(),
                        conjuntoResultados.getObject(10).toString(),
                        conjuntoResultados.getObject(11).toString(),
                        conjuntoResultados.getObject(12).toString(),
                        conjuntoResultados.getObject(13).toString(),
                        conjuntoResultados.getObject(14).toString(),
                        conjuntoResultados.getObject(15).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(16).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(17).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(18).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(19).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(20).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(21).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(22).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(23).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(24).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(25).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(26).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(27).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(28).toString()),
                        conjuntoResultados.getObject(29).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(30).toString()),
                        conjuntoResultados.getObject(31).toString(),
                        Double.parseDouble(conjuntoResultados.getObject(32).toString()),
                        conjuntoResultados.getObject(33).toString(),
                        conjuntoResultados.getObject(34).toString(),
                        Integer.parseInt(conjuntoResultados.getObject(35).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(36).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(37).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(38).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(39).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(40).toString()),
                        Integer.parseInt(conjuntoResultados.getObject(41).toString()),
                        conjuntoResultados.getObject(42).toString(),
                        conjuntoResultados.getObject(43).toString()
                        );
                cliente.setCheckCredito(Integer.parseInt(conjuntoResultados.getObject(44).toString()));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(hClientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }

       public Clientes consultaUltimoRenglon(String campo, String match, String condicion)
    {
       Clientes cliente = new Clientes();
    //   Conexion conexion = new Conexion();
    //            conexion.crearConexion();
            if(CGlobalConfig.isWeb())  
       {
           
            Map <String,String> datosEnv=new HashMap<String,String>();
			
		//Escribe tu query	
            String query = "";            
            if(match.equals("*")){
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes ORDER BY Fecha DESC LIMIT 1;";
            }
            else if(match.equals("="))
            {
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;";
            }else
            {
            query = "SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\'   ORDER BY id DESC LIMIT 1;";
            }
        
		
		//Escribe tu query
		
		
        datosEnv.put("consulta",query);
		
		//Escribe el método
		
        datosEnv.put("metodo","consultaUltimoRenglon()");
		
		
		//Escribe el método
		
        datosEnv.put("base",CGlobalConfig.getConexion());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonOutput = gson.toJson(datosEnv);
        try{
            
          jsonOutput = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(jsonOutput, "UTF-8");

          //Escribe el .php
          URL url=new URL("http://www.quimerasystems.com/paehelp/paeClientes.php");
		  
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
                       cliente.setId(Long.parseLong(atributos[1]));
                       cliente.setCodigo(atributos[2]);
                       cliente.setSucursal(atributos[3]);
                       cliente.setNombre(atributos[4]);
                       cliente.setClasificacion(atributos[5]);
                       cliente.setTelefono(atributos[6]);
                       cliente.setAtencion(atributos[7]);
                       cliente.setCalle(atributos[8]);
                       cliente.setNumeroExterior(atributos[9]);
                       cliente.setNumeroInterior(atributos[10]);
                       cliente.setColonia(atributos[11]);
                       cliente.setPoblacion(atributos[12]);
                       cliente.setEstado(atributos[13]);
                       cliente.setCodigoPostal(atributos[14]);
                       cliente.setEmail(atributos[15]);
                       cliente.setUtilGlob(Integer.parseInt(atributos[16]));
                       cliente.setDesc5(Integer.parseInt(atributos[17]));
                       cliente.setDesc1(Integer.parseInt(atributos[18]));
                       cliente.setDesc2(Integer.parseInt(atributos[19]));
                       cliente.setDesc3(Integer.parseInt(atributos[20]));
                       cliente.setDesc4(Integer.parseInt(atributos[21]));
                       cliente.setDescVol(Integer.parseInt(atributos[22]));
                       cliente.setDescGlobal(Integer.parseInt(atributos[23]));
                       cliente.setBloqueo(Integer.parseInt(atributos[24]));
                       cliente.setOfertas(Integer.parseInt(atributos[25]));
                       cliente.setDiasCredito(Integer.parseInt(atributos[26]));
                       cliente.setLimiteDiasCredito(Integer.parseInt(atributos[27]));
                       cliente.setCredito(Integer.parseInt(atributos[28]));
                       cliente.setObservaciones(atributos[29]);
                       cliente.setUtilVol(Integer.parseInt(atributos[30]));
                       cliente.setFechaNacimiento(atributos[31]);
                       cliente.setSaldo(Double.parseDouble(atributos[32]));
                       cliente.setPais(atributos[33]);
                       cliente.setRfc(atributos[34]);
                       cliente.setDesc6(Integer.parseInt(atributos[35]));
                       cliente.setUtil1(Integer.parseInt(atributos[36]));
                       cliente.setUtil2(Integer.parseInt(atributos[37]));
                       cliente.setUtil3(Integer.parseInt(atributos[38]));
                       cliente.setUtil4(Integer.parseInt(atributos[39]));
                       cliente.setUtil5(Integer.parseInt(atributos[40]));
                       cliente.setUtil6(Integer.parseInt(atributos[41]));
                       cliente.setTransferencia(atributos[42]);
                       cliente.setBanco(atributos[43]);
                       cliente.setCheckCredito(Integer.parseInt(atributos[44]));         
		   //Arma el objeto y agrega a la lista en su caso
		   
		   
           }
           s=recv.readLine();
           }
           
		   //Regresa el objeto o lista
            return cliente;
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
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes ORDER BY Fecha DESC LIMIT 1;");
            }
            else if(match.equals("="))
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\' ORDER BY id DESC LIMIT 1;");
            }else
            {
            conjuntoResultados = CPrincipal.getConexion().crearConsulta("SELECT id,codigo,sucursal,nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,limiteDiasCredito,credito,observaciones,utilVol,fechaNacimiento,saldo,pais,rfc,desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\'   ORDER BY id DESC LIMIT 1;");
            }
            
            if(conjuntoResultados.next())
            {
            do {
                       
                       cliente.setId(Long.parseLong(conjuntoResultados.getObject(1).toString()));
                       cliente.setCodigo(conjuntoResultados.getObject(2).toString());
                       cliente.setSucursal(conjuntoResultados.getObject(3).toString());
                       cliente.setNombre(conjuntoResultados.getObject(4).toString());
                       cliente.setClasificacion(conjuntoResultados.getObject(5).toString());
                       cliente.setTelefono(conjuntoResultados.getObject(6).toString());
                       cliente.setAtencion(conjuntoResultados.getObject(7).toString());
                       cliente.setCalle(conjuntoResultados.getObject(8).toString());
                       cliente.setNumeroExterior(conjuntoResultados.getObject(9).toString());
                       cliente.setNumeroInterior(conjuntoResultados.getObject(10).toString());
                       cliente.setColonia(conjuntoResultados.getObject(11).toString());
                       cliente.setPoblacion(conjuntoResultados.getObject(12).toString());
                       cliente.setEstado(conjuntoResultados.getObject(13).toString());
                       cliente.setCodigoPostal(conjuntoResultados.getObject(14).toString());
                       cliente.setEmail(conjuntoResultados.getObject(15).toString());
                       cliente.setUtilGlob(Integer.parseInt(conjuntoResultados.getObject(16).toString()));
                       cliente.setDesc5(Integer.parseInt(conjuntoResultados.getObject(17).toString()));
                       cliente.setDesc1(Integer.parseInt(conjuntoResultados.getObject(18).toString()));
                       cliente.setDesc2(Integer.parseInt(conjuntoResultados.getObject(19).toString()));
                       cliente.setDesc3(Integer.parseInt(conjuntoResultados.getObject(20).toString()));
                       cliente.setDesc4(Integer.parseInt(conjuntoResultados.getObject(21).toString()));
                       cliente.setDescVol(Integer.parseInt(conjuntoResultados.getObject(22).toString()));
                       cliente.setDescGlobal(Integer.parseInt(conjuntoResultados.getObject(23).toString()));
                       cliente.setBloqueo(Integer.parseInt(conjuntoResultados.getObject(24).toString()));
                       cliente.setOfertas(Integer.parseInt(conjuntoResultados.getObject(25).toString()));
                       cliente.setDiasCredito(Integer.parseInt(conjuntoResultados.getObject(26).toString()));
                       cliente.setLimiteDiasCredito(Integer.parseInt(conjuntoResultados.getObject(27).toString()));
                       cliente.setCredito(Integer.parseInt(conjuntoResultados.getObject(28).toString()));
                       cliente.setObservaciones(conjuntoResultados.getObject(29).toString());
                       cliente.setUtilVol(Integer.parseInt(conjuntoResultados.getObject(30).toString()));
                       cliente.setFechaNacimiento(conjuntoResultados.getObject(31).toString());
                       cliente.setSaldo(Double.parseDouble(conjuntoResultados.getObject(32).toString()));
                       cliente.setPais(conjuntoResultados.getObject(33).toString());
                       cliente.setRfc(conjuntoResultados.getObject(34).toString());
                       cliente.setDesc6(Integer.parseInt(conjuntoResultados.getObject(35).toString()));
                       cliente.setUtil1(Integer.parseInt(conjuntoResultados.getObject(36).toString()));
                       cliente.setUtil2(Integer.parseInt(conjuntoResultados.getObject(37).toString()));
                       cliente.setUtil3(Integer.parseInt(conjuntoResultados.getObject(38).toString()));
                       cliente.setUtil4(Integer.parseInt(conjuntoResultados.getObject(39).toString()));
                       cliente.setUtil5(Integer.parseInt(conjuntoResultados.getObject(40).toString()));
                       cliente.setUtil6(Integer.parseInt(conjuntoResultados.getObject(41).toString()));
                       cliente.setTransferencia(conjuntoResultados.getObject(42).toString());
                       cliente.setBanco(conjuntoResultados.getObject(43).toString());
                       cliente.setCheckCredito(Integer.parseInt(conjuntoResultados.getObject(44).toString()));           
            }while (conjuntoResultados.next());
            return cliente;  
          }else 
             return null;
        } catch (SQLException ex) {
            Logger.getLogger(hKardex.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
        CPrincipal.getConexion().cerrar(0);
        }
        return null;
    }
    
    
         public int guardarClientes(Clientes c)
    {
       int filas_afectadas = 0;
       String query = "INSERT INTO clientes (codigo,sucursal,"
                    + "nombre,clasificacion,telefono,atencion,calle,numeroExterior,numeroInterior,"
                    + "colonia,poblacion,estado,codigoPostal,email,utilGlob,desc5,"
                    + "desc1,desc2,desc3,desc4,descVol,descGlobal,bloqueo,ofertas,diasCredito,"
                    +"limiteDiasCredito,credito,utilVol,fechaNacimiento,saldo,pais,observaciones,rfc,"
                    + "desc6,util1,util2,util3,util4,util5,util6,transferencia,banco,checkCredito"
                    +") VALUES"
                    + "(\'"+c.getCodigo()+
                    "\',\'"+c.getSucursal()+
                    "\',\'"+c.getNombre()+
                    "\',\'"+c.getClasificacion()+
                    "\',\'"+c.getTelefono()+
                    "\',\'"+c.getAtencion()+
                    "\',\'"+c.getCalle()+
                    "\',\'"+c.getNumeroExterior()+
                    "\',\'"+c.getNumeroInterior()+
                    "\',\'"+c.getColonia()+
                    "\',\'"+c.getPoblacion()+
                    "\',\'"+c.getEstado()+
                    "\',\'"+c.getCodigoPostal()+
                    "\',\'"+c.getEmail()+
                    "\',\'"+c.getUtilGlob()+
                    "\',\'"+c.getDesc5()+
                    "\',\'"+c.getDesc1()+
                    "\',\'"+c.getDesc2()+
                    "\',\'"+c.getDesc3()+
                    "\',\'"+c.getDesc4()+
                    "\',\'"+c.getDescVol()+
                    "\',\'"+c.getDescGlobal()+
                    "\',\'"+c.getBloqueo()+
                    "\',\'"+c.getOfertas()+
                    "\',\'"+c.getDiasCredito()+
                    "\',\'"+c.getLimiteDiasCredito()+
                    "\',\'"+c.getCredito()+
                    "\',\'"+c.getUtilVol()+
                    "\',\'"+c.getFechaNacimiento()+
                    "\',\'"+c.getSaldo()+
                    "\',\'"+c.getPais()+
                    "\',\'"+c.getObservaciones()+
                    "\',\'"+c.getRfc()+
                    "\',\'"+c.getDesc6()+
                    "\',\'"+c.getUtil1()+
                    "\',\'"+c.getUtil2()+
                    "\',\'"+c.getUtil3()+
                    "\',\'"+c.getUtil4()+
                    "\',\'"+c.getUtil5()+
                    "\',\'"+c.getUtil6()+
                    "\',\'"+c.getTransferencia() +
                    "\',\'"+c.getBanco() +
                    "\',\'"+c.getCheckCredito()
                    +"');";
             //   Conexion conexion = new Conexion();
             //   conexion.crearConexion();
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


            public int borrarClientes(String campo, String match, String condicion)
    {
      int tipo_respuesta = 0;
     //  Conexion conexion = new Conexion();
     //           conexion.crearConexion();
      if(CGlobalConfig.isWeb())
      {
     String query = "";
     
     if(match.equals("="))
            {
     query = "DELETE  FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\';";
            }else  if(match.equals("LIKE"))
            {
     query = "DELETE  FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\';";
            }
     
     ConexionWeb conexionweb = new ConexionWeb();
     tipo_respuesta = conexionweb.escribirEnWeb(query);
     return tipo_respuesta;
     }
      

        if(match.equals("="))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM clientes WHERE "+campo+" "+match+" \'"+condicion+"\';");
            }else  if(match.equals("LIKE"))
            {
            CPrincipal.getConexion().moverDatos("DELETE  FROM clientes WHERE "+campo+" "+match+" \'%"+condicion+"%\';");
            }
                CPrincipal.getConexion().cerrar(1);

      return tipo_respuesta;
    }

    public int limpiarParetto()
    {
     int resultado = 0;
    // Conexion conexion = new Conexion();
    //          conexion.crearConexion();
       String query = "UPDATE clientes SET clasificacion = \'1\'"
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
           public int actualizarClasificacion(String codigo,String clasificacion)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();
       String query = "UPDATE clientes "
                    + "SET clasificacion = \'"+clasificacion +"\'"
                    + " WHERE codigo = \'"+codigo+"\';";
       
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
    public int actualizarClientes(Clientes c,String campoCondicion,String match,String condicion)
    {
     int resultado = 0;
   //  Conexion conexion = new Conexion();
   //           conexion.crearConexion();

     String query = "UPDATE clientes SET "+
                  "codigo = \'" +  c.getCodigo()+"\',"+
                  "sucursal = \'" +   c.getSucursal()+"\',"+
                  "calle = \'" +   c.getCalle()+"\',"+
                  "nombre = \'" +   c.getNombre()+"\',"+
                  "clasificacion = \'" +   c.getClasificacion()+"\',"+
                  "telefono = \'" +   c.getTelefono()+"\',"+
                  "atencion = \'" +   c.getAtencion()+"\',"+
                  "numeroExterior = \'" +   c.getNumeroExterior()+"\',"+
                  "numeroInterior = \'" +    c.getNumeroInterior()+"\',"+
                  "colonia = \'" +    c.getColonia()+"\',"+
                  "poblacion = \'" +    c.getPoblacion()+"\',"+
                  "estado = \'" +     c.getEstado()+"\',"+
                  "codigoPostal = \'" +    c.getCodigoPostal()+"\',"+
                  "email = \'" +    c.getEmail()+"\',"+
                  "utilGlob = \'" +    c.getUtilGlob()+"\',"+
                  "desc5 = \'" +    c.getDesc5()+"\',"+
                  "pais = \'" +    c.getPais()+"\',"+
                  "fechaNacimiento = \'" +    c.getFechaNacimiento()+"\',"+
                  "utilVol = \'" +    c.getUtilVol()+"\',"+
                  "desc1 = \'" +    c.getDesc1()+"\',"+
                  "desc2 = \'" +    c.getDesc2()+"\',"+
                  "desc3 = \'" +    c.getDesc3()+"\',"+
                  "desc4 = \'" +    c.getDesc4()+"\',"+
                  "descVol = \'" +    c.getDescVol()+"\',"+
                  "descGlobal = \'" +    c.getDescGlobal()+"\',"+
                  "saldo = \'" +    c.getSaldo()+"\',"+
                  "bloqueo = \'" +    c.getBloqueo()+"\',"+
                  "ofertas = \'" +    c.getOfertas()+"\',"+
                  "diasCredito = \'" +    c.getDiasCredito()+"\',"+
                  "limiteDiasCredito = \'" +    c.getLimiteDiasCredito()+"\',"+
                  "credito = \'" +    c.getCredito()+"\',"+
                  "rfc = \'" +    c.getRfc()+"\',"+
                  "desc6 = \'" +    c.getDesc6()+"\',"+
                  "util1 = \'" +    c.getUtil1()+"\',"+
                  "util2 = \'" +    c.getUtil2()+"\',"+
                  "util3 = \'" +    c.getUtil3()+"\',"+
                  "util4 = \'" +    c.getUtil4()+"\',"+
                  "util5 = \'" +    c.getUtil5()+"\',"+
                  "util6 = \'" +    c.getUtil6()+"\',"+
                  "transferencia = \'" +    c.getTransferencia()+"\',"+
                  "banco = \'" +    c.getBanco()+"\',"+
                  "checkCredito = \'" +    c.getCheckCredito()+"\',"+
                  "observaciones = \'" +    c.getObservaciones()+"\'";
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


    public static void main(String args [])
    {
       hClientes ejemplo = new hClientes();
       List<Clientes> clientes = ejemplo.consultaClientes("id","=","1");
       int i = 0;
       while(i <clientes.size())
       {
           System.out.println(clientes.get(i).getNombre());
           i++;
       } 
       System.out.println(ejemplo.borrarClientes("id", "=", "2"));

       Clientes clie = new Clientes();
       clie.setAtencion("Lucia Fuentes Salazar");
       clie.setBloqueo(1);
       clie.setCalle("Rosario Central");
       clie.setClasificacion("1");
       clie.setCodigo("72839");
       clie.setCodigoPostal("34521");
       clie.setColonia("Centro");
       clie.setCredito(1);
       clie.setDesc1(10);
       clie.setDesc2(15);
       clie.setDesc3(20);
       clie.setDesc4(25);
       clie.setDescGlobal(10);
       clie.setDescVol(10);
       clie.setDesc5(10);
       clie.setDesc6(10);
       clie.setDiasCredito(10);
       clie.setEmail("lucia@hotmail.com");
       clie.setEstado("México");
       clie.setFechaNacimiento("03/04/2012");
       clie.setUtilVol(12);
       clie.setLimiteDiasCredito(12);
       clie.setNombre("Lucia Fuentes Salazar");
       clie.setNumeroExterior("1");
       clie.setNumeroInterior("2");
       clie.setObservaciones("Autopartes Salazar");
       clie.setOfertas(2);
       clie.setPais("México");
       clie.setPoblacion("Toluca");
       clie.setSaldo(1000);
       clie.setSucursal("Diaz Mirón");
       clie.setTelefono("2039281");
       clie.setUtilGlob(2);
       clie.setUtil1(1);
       clie.setUtil2(2);
       clie.setUtil3(3);
       clie.setUtil4(4);
       clie.setUtil5(5);
       clie.setUtil6(6);

  //     System.out.println(ejemplo.guardarClientes(clie));

      clie.setId(Long.parseLong("1"));
      clie.setCalle("Santa Isabel 23");
      clie.setCodigo("2");
      clie.setEmail("rosariolucia@hotmail.com");
   //   System.out.println(ejemplo.actualizarClientes(clie,"id","=","2"));
    }

}
