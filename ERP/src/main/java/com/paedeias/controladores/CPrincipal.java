/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.controladores;

import com.paedeias.controladores.facturacion.DOMUtils;
import com.paedeias.helpers.Conexion;
import com.paedeias.helpers.hCuentasPorCobrar;
import com.paedeias.vistas.VPrincipal;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.w3c.dom.Document;

/**
 *
 * @author Luis
 */
public class CPrincipal{
    VPrincipal principal;
    public static Conexion conexion;

    public CPrincipal()
    {
        try {
            Handler handler = new Handler();
            Thread.setDefaultUncaughtExceptionHandler(handler);

           
           
                      try {
                       Document configuracion = DOMUtils.cargarArchivoConfiguracion(new File("configuracion.xml"));
                       // System.out.println(configuracion.getElementsByTagName("conexion").item(0).getTextContent());
                       CGlobalConfig.setWeb(Boolean.valueOf(configuracion.getElementsByTagName("web").item(0).getTextContent()));
                       CGlobalConfig.setConexion(configuracion.getElementsByTagName("conexion").item(0).getTextContent());
                       CGlobalConfig.setBgcolor1(Integer.valueOf(configuracion.getElementsByTagName("bgcolor1").item(0).getTextContent()));
                       CGlobalConfig.setBgcolor2(Integer.valueOf(configuracion.getElementsByTagName("bgcolor2").item(0).getTextContent()));
                       CGlobalConfig.setBgcolor3(Integer.valueOf(configuracion.getElementsByTagName("bgcolor3").item(0).getTextContent()));
                       CGlobalConfig.setDescuentosClientes(Boolean.valueOf(configuracion.getElementsByTagName("descuentosClientes").item(0).getTextContent()));
                       CGlobalConfig.setUtilidadesClientes(Boolean.valueOf(configuracion.getElementsByTagName("utilidadesClientes").item(0).getTextContent()));
                       CGlobalConfig.setOfertas(Boolean.valueOf(configuracion.getElementsByTagName("ofertas").item(0).getTextContent()));
                       CGlobalConfig.setCambiarPrecioArticulo(Boolean.valueOf(configuracion.getElementsByTagName("cambiarPrecioArticulo").item(0).getTextContent()));
                       CGlobalConfig.setAplicarDescuentoArticulo(Boolean.valueOf(configuracion.getElementsByTagName("aplicarDescuentoArticulo").item(0).getTextContent()));
                       CGlobalConfig.setAplicarUtilidadArticulo(Boolean.valueOf(configuracion.getElementsByTagName("aplicarUtilidadArticulo").item(0).getTextContent()));
                       CGlobalConfig.setIvaVenta(Double.valueOf(configuracion.getElementsByTagName("ivaVenta").item(0).getTextContent()));
                       CGlobalConfig.setUtilidad(Double.valueOf(configuracion.getElementsByTagName("utilidad").item(0).getTextContent()));
                       
                       CGlobalConfig.setDireccion_certificado(configuracion.getElementsByTagName("direccion_certificado").item(0).getTextContent());
                       CGlobalConfig.setDireccion_key(configuracion.getElementsByTagName("direccion_key").item(0).getTextContent());
                       CGlobalConfig.setDireccion_jks(configuracion.getElementsByTagName("direccion_jks").item(0).getTextContent());
                       CGlobalConfig.setContrasena_key(configuracion.getElementsByTagName("contrasena_key").item(0).getTextContent());
                       CGlobalConfig.setKeyStorepwd(configuracion.getElementsByTagName("keyStorepwd").item(0).getTextContent());
                       CGlobalConfig.setPrivate_key_alias(configuracion.getElementsByTagName("private_key_alias").item(0).getTextContent());
                       CGlobalConfig.setKeystore_type(configuracion.getElementsByTagName("keystore_type").item(0).getTextContent());
                       CGlobalConfig.setFactura_folioInicial(configuracion.getElementsByTagName("factura_folioInicial").item(0).getTextContent());
                       CGlobalConfig.setFactura_folioFinal(configuracion.getElementsByTagName("factura_folioFinal").item(0).getTextContent());
                       CGlobalConfig.setFactura_regimen(configuracion.getElementsByTagName("factura_regimen").item(0).getTextContent());
                       CGlobalConfig.setFactura_tipoComprobante(configuracion.getElementsByTagName("factura_tipoComprobante").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorRFC(configuracion.getElementsByTagName("factura_emisorRFC").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorNombre(configuracion.getElementsByTagName("factura_emisorNombre").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorCalle(configuracion.getElementsByTagName("factura_emisorCalle").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorNumero(configuracion.getElementsByTagName("factura_emisorNumero").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorColonia(configuracion.getElementsByTagName("factura_emisorColonia").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorCP(configuracion.getElementsByTagName("factura_emisorCP").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorCiudad(configuracion.getElementsByTagName("factura_emisorCiudad").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorEstado(configuracion.getElementsByTagName("factura_emisorEstado").item(0).getTextContent());
                       CGlobalConfig.setFactura_emisorPais(configuracion.getElementsByTagName("factura_emisorPais").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoCalle(configuracion.getElementsByTagName("factura_experidoCalle").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoNumero(configuracion.getElementsByTagName("factura_experidoNumero").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoColonia(configuracion.getElementsByTagName("factura_experidoColonia").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoCP(configuracion.getElementsByTagName("factura_experidoCP").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoCiudad(configuracion.getElementsByTagName("factura_experidoCiudad").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoEstado(configuracion.getElementsByTagName("factura_experidoEstado").item(0).getTextContent());
                       CGlobalConfig.setFactura_experidoPais(configuracion.getElementsByTagName("factura_experidoPais").item(0).getTextContent());
                       CGlobalConfig.setFactura_serie(configuracion.getElementsByTagName("factura_serie").item(0).getTextContent());
                       CGlobalConfig.setFactura_anoAprobacion(configuracion.getElementsByTagName("factura_anoAprobacion").item(0).getTextContent());
                       CGlobalConfig.setFactura_noAprobacion(configuracion.getElementsByTagName("factura_noAprobacion").item(0).getTextContent());
                       CGlobalConfig.setFactura_certificado(configuracion.getElementsByTagName("factura_certificado").item(0).getTextContent());
                       CGlobalConfig.setFactura_mensaje1(configuracion.getElementsByTagName("factura_mensaje1").item(0).getTextContent());
                       CGlobalConfig.setFactura_mensaje2(configuracion.getElementsByTagName("factura_mensaje2").item(0).getTextContent());
                       CGlobalConfig.setNotaCredito_tipoComprobante(configuracion.getElementsByTagName("notaCredito_tipoComprobante").item(0).getTextContent());
                       
                       CGlobalConfig.setPedidos(Boolean.valueOf(configuracion.getElementsByTagName("pedidos").item(0).getTextContent()));
                       
                       long[] idCliente = new long[configuracion.getElementsByTagName("id_cliente").getLength()];
                       for(int i=0; i<idCliente.length; i++)
                       {
                           idCliente[i] = Long.valueOf(configuracion.getElementsByTagName("id_cliente").item(i).getTextContent());
                       }
                       CGlobalConfig.setId_cliente(idCliente);
                       String[] menCliente = new String[configuracion.getElementsByTagName("men_cliente").getLength()];
                       for(int i=0; i<menCliente.length; i++)
                       {
                           menCliente[i] = configuracion.getElementsByTagName("men_cliente").item(i).getTextContent();
                       }
                       CGlobalConfig.setMen_cliente(menCliente);
                       String[] permiso = new String[configuracion.getElementsByTagName("permiso").getLength()];
                       for(int i=0; i<permiso.length; i++)
                       {
                           permiso[i] = configuracion.getElementsByTagName("permiso").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setTitulos_permisos(permiso);
                       
                       CGlobalConfig.setCampor1(configuracion.getElementsByTagName("campor1").item(0).getTextContent());
                       CGlobalConfig.setCampor2(configuracion.getElementsByTagName("campor2").item(0).getTextContent());
                       CGlobalConfig.setCampor3(configuracion.getElementsByTagName("campor3").item(0).getTextContent());
                       CGlobalConfig.setCampor4(configuracion.getElementsByTagName("campor4").item(0).getTextContent());
                       CGlobalConfig.setCampor5(configuracion.getElementsByTagName("campor5").item(0).getTextContent());
                       CGlobalConfig.setCampor6(configuracion.getElementsByTagName("campor6").item(0).getTextContent());
                       CGlobalConfig.setCampor7(configuracion.getElementsByTagName("campor7").item(0).getTextContent());
                       CGlobalConfig.setCampor8(configuracion.getElementsByTagName("campor8").item(0).getTextContent());
                       CGlobalConfig.setCampor9(configuracion.getElementsByTagName("campor9").item(0).getTextContent());
                       CGlobalConfig.setCampor10(configuracion.getElementsByTagName("campor10").item(0).getTextContent());
                       CGlobalConfig.setCampor11(configuracion.getElementsByTagName("campor11").item(0).getTextContent());
                       CGlobalConfig.setCampor12(configuracion.getElementsByTagName("campor12").item(0).getTextContent());
                       CGlobalConfig.setCampor13(configuracion.getElementsByTagName("campor13").item(0).getTextContent());
                       
                       CGlobalConfig.setCampoa1(configuracion.getElementsByTagName("campoa1").item(0).getTextContent());
                       CGlobalConfig.setCampoa2(configuracion.getElementsByTagName("campoa2").item(0).getTextContent());
                       CGlobalConfig.setCampoa3(configuracion.getElementsByTagName("campoa3").item(0).getTextContent());
                       CGlobalConfig.setCampoa4(configuracion.getElementsByTagName("campoa4").item(0).getTextContent());
                       CGlobalConfig.setCampoa5(configuracion.getElementsByTagName("campoa5").item(0).getTextContent());
                       CGlobalConfig.setCampoa6(configuracion.getElementsByTagName("campoa6").item(0).getTextContent());
                       CGlobalConfig.setCampoa7(configuracion.getElementsByTagName("campoa7").item(0).getTextContent());
                       CGlobalConfig.setCampoa8(configuracion.getElementsByTagName("campoa8").item(0).getTextContent());
                       
                       CGlobalConfig.setCampli1(configuracion.getElementsByTagName("campli1").item(0).getTextContent());
                       CGlobalConfig.setCampli2(configuracion.getElementsByTagName("campli2").item(0).getTextContent());
                       CGlobalConfig.setCampli3(configuracion.getElementsByTagName("campli3").item(0).getTextContent());
                       CGlobalConfig.setCampli4(configuracion.getElementsByTagName("campli4").item(0).getTextContent());
                       CGlobalConfig.setCampli5(configuracion.getElementsByTagName("campli5").item(0).getTextContent());
                       CGlobalConfig.setCampli6(configuracion.getElementsByTagName("campli6").item(0).getTextContent());
                       CGlobalConfig.setCampli7(configuracion.getElementsByTagName("campli7").item(0).getTextContent());
                       
                       CGlobalConfig.setProv1(configuracion.getElementsByTagName("Prov1").item(0).getTextContent());
                       CGlobalConfig.setProv2(configuracion.getElementsByTagName("Prov2").item(0).getTextContent());
                       CGlobalConfig.setProv3(configuracion.getElementsByTagName("Prov3").item(0).getTextContent());
                       CGlobalConfig.setProv4(configuracion.getElementsByTagName("Prov4").item(0).getTextContent());
                       CGlobalConfig.setProv5(configuracion.getElementsByTagName("Prov5").item(0).getTextContent());
                       CGlobalConfig.setRutaRespaldoXMLS(configuracion.getElementsByTagName("rutaRespaldoXMLS").item(0).getTextContent());
                       CGlobalConfig.setRutaRespaldoPDFS(configuracion.getElementsByTagName("rutaRespaldoPDFS").item(0).getTextContent());
                       
                       String[] camposelecta7 = new String[configuracion.getElementsByTagName("camposelecta7").getLength()];
                       for(int i=0; i<camposelecta7.length; i++)
                       {
                           camposelecta7[i] = configuracion.getElementsByTagName("camposelecta7").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setCamposelecta7(camposelecta7);
                       
                       String[] camposelecta8 = new String[configuracion.getElementsByTagName("camposelecta8").getLength()];
                       for(int i=0; i<camposelecta8.length; i++)
                       {
                           camposelecta8[i] = configuracion.getElementsByTagName("camposelecta8").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setCamposelecta8(camposelecta8);    
                       
                       
                       String[] nombreSucursal = new String[configuracion.getElementsByTagName("nombreSucursal").getLength()];
                       for(int i=0; i<nombreSucursal.length; i++)
                       {
                           nombreSucursal[i] = configuracion.getElementsByTagName("nombreSucursal").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setNombreSucursal(nombreSucursal);   
                       
                       String[] esWeb = new String[configuracion.getElementsByTagName("esWeb").getLength()];
                       for(int i=0; i<esWeb.length; i++)
                       {
                           esWeb[i] = configuracion.getElementsByTagName("esWeb").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setEsWeb(esWeb);  
                       
                       String[] direccionSucursal = new String[configuracion.getElementsByTagName("direccionSucursal").getLength()];
                       for(int i=0; i<direccionSucursal.length; i++)
                       {
                           direccionSucursal[i] = configuracion.getElementsByTagName("direccionSucursal").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setDireccionSucursal(direccionSucursal);      
                       
                       String[] rutaPlugin = new String[configuracion.getElementsByTagName("rutaPlugin").getLength()];
                       String[] nombrePlugin = new String[configuracion.getElementsByTagName("nombrePlugin").getLength()];
                       String[] tooltipPlugin = new String[configuracion.getElementsByTagName("tooltipPlugin").getLength()];
                       String[] clasePlugin = new String[configuracion.getElementsByTagName("clasePlugin").getLength()];
                       String[] imagenPlugin = new String[configuracion.getElementsByTagName("imagenPlugin").getLength()];
                       
                       for(int i=0; i<rutaPlugin.length; i++)
                       {
                           rutaPlugin[i] = configuracion.getElementsByTagName("rutaPlugin").item(i).getTextContent();
                           nombrePlugin[i] = configuracion.getElementsByTagName("nombrePlugin").item(i).getTextContent();
                           tooltipPlugin[i] = configuracion.getElementsByTagName("tooltipPlugin").item(i).getTextContent();
                           clasePlugin[i] = configuracion.getElementsByTagName("clasePlugin").item(i).getTextContent();
                           imagenPlugin[i] = configuracion.getElementsByTagName("imagenPlugin").item(i).getTextContent();
                       }                    
                       CGlobalConfig.setRutaPlugin(rutaPlugin);    
                       CGlobalConfig.setNombrePlugin(nombrePlugin);    
                       CGlobalConfig.setTooltipoPlugin(tooltipPlugin);    
                       CGlobalConfig.setClasePlugin(clasePlugin);    
                       CGlobalConfig.setImagenPlugin(imagenPlugin);    
                       
                       CGlobalConfig.setHabilitarRed(Boolean.valueOf(configuracion.getElementsByTagName("habilitarRed").item(0).getTextContent()));
                       CGlobalConfig.setUsuarioRed(configuracion.getElementsByTagName("usuarioRed").item(0).getTextContent());
                       
                   } catch (Exception ex) {
                       Logger.getLogger(Autenticar.class.getName()).log(Level.SEVERE, null, ex);
                   }
          conexion = new Conexion();
          conexion.crearConexion();
          principal = new VPrincipal();
        } catch (InstantiationException ex) {
            Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        public static void main(String args[])
    {        
         CPrincipal principal = new CPrincipal();
    }

    public static Conexion getConexion() {
        return conexion;
    }

    public static void setConexion(Conexion conexion) {
        CPrincipal.conexion = conexion;
    }
        
       class Handler implements Thread.UncaughtExceptionHandler {
  public void uncaughtException(Thread t, Throwable e) {
                
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String stacktrace = sw.toString();
                
    Logger logger = Logger.getLogger("MyLog");  
    FileHandler fh;  

    try {  

        // This block configure the logger with handler and formatter  
        hCuentasPorCobrar reloj = new hCuentasPorCobrar();
        String titulo = reloj.generarFecha().substring(0, 19).replace("-", "_");
        titulo = titulo.replace(":", "_");
        System.out.println(titulo);
        fh = new FileHandler("logs/"+titulo+".log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

        // the following statement is used to log any messages  
        logger.info(stacktrace);  

    }       catch (IOException ex) {
                Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException se) {  
        e.printStackTrace();  
    } 
  }
}
 
        
}
