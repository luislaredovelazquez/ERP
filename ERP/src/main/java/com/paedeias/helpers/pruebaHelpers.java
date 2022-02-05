/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.helpers;

import com.paedeias.controladores.CGlobalConfig;

/**
 *
 * @author ALL
 */
public class pruebaHelpers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    //    CGlobalConfig.setConexion("quimera1_bdprueba");
    //    CGlobalConfig.setWeb(true);
        
    //    ConexionWeb conexionweb = new ConexionWeb();
  hCuentasPorCobrar hcc = new hCuentasPorCobrar();
                              String fecha = hcc.generarFecha().substring(0, 19);
                              fecha = fecha.replace(":", "_");
                              fecha = fecha.replace("-", "_");
                              System.out.println(fecha);
        
      //  hVentas h = new hVentas();
      /*  System.out.println("UNO");   
         h.consultaVentas("","*","");//"c.fechaVencimiento", );
         System.out.println("DOS");   
        h.consultaVentas2("", "*", "");
         System.out.println("TRES");   
         h.consultaVentasPeriodo("2013-01-01", "2013-12-12"); 
        System.out.println("CUATRO");   
        h.consultaVentasHistorial("","*","","2013-01-01", "2013-12-12");
        System.out.println("CINCO");   
        h.consultaVentasReservaciones("","*",""); 
        System.out.println("SEIS");   
        h.consultaVentasEfectivo("", "*", "");
        System.out.println("SIETE");   
        h.consultaVentasCredito("", "*", "");
         System.out.println("OCHO");   
        h.consultaVentasEfeCre("", "*", ""); */
      //   System.out.println("NUEVE");   
      //  h.consultaVentasFechas("2013-01-01", "2013-12-12"); 
        /* System.out.println("DIEZ");   
        h.consultaVentasFechaPorCliente("2013-01-01", "2013-12-12",(long)1);
        System.out.println("ONCE");   
        h.consultaVentasClientes("2013-01-01", "2013-12-12");
        System.out.println("DOCE");   
        h.consultaVentasFechasAlmacen("2013-01-01", "2013-12-12");
        System.out.println("TRECE");   
        h.consultaVentasFechasUtilidades("2013-01-01", "2013-12-12");
        System.out.println("CATORCE");   
        h.consultaVentasPeriodo("2013-01-01", "2013-12-12");
        System.out.println("QUINCE");   
        h.consultaVentasPeriodoVendendor("2013-01-01", "2013-12-12","3");
        System.out.println("DIECISEIS");   
        h.consultaVentasPeriodoVendendorReservacion("2013-01-01", "2013-12-12","2");
        System.out.println("DIECISIETE");   
        h.consultaDevolucionesPeriodoVendendor("2013-01-01", "2013-12-12","1");
        System.out.println("DIECIOCHO");   
        h.consultaVentasPeriodoReservacion("2013-01-01", "2013-12-12");
        System.out.println("DIECINUEVE");   
        h.consultaReservacion("1");
        System.out.println("VEINTE");   
        h.consultaUltimaVenta("", "*","");
        System.out.println("VEITIUNO");   
        h.consultaVentasTotalVendendor("2013-01-01", "2013-12-12");
        System.out.println("VEINTIDOS");   
        h.consultaVentasTotalVendendorReservacion("2013-01-01", "2013-12-12");
        System.out.println("VENTITRES");   
        h.consultaDevolucionesTotalVendendor("2013-01-01", "2013-12-12"); */
        // System.out.println(objetos[u]);    
    }
}
