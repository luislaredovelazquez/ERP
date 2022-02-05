/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import com.paedeias.helpers.hCuentasPorCobrar;
import com.paedeias.identidades.Cierre;
import com.paedeias.identidades.Partidas;
import java.awt.Graphics;
import java.awt.print.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ALL
 */
public class Impresion {
    
    
      
    public static void main (String args[]) throws IOException
    {
            try {
        //    ImpresionVenta impresionventa = new ImpresionVenta(); 
        //    ImpresionPedido impresionpedido = new ImpresionPedido();    
        //    ImpresionTicketVenta impresiontv = new ImpresionTicketVenta();     
        //    ImpresionCancelacionAlmacen impresionca = new ImpresionCancelacionAlmacen();     
        //    ImpresionDevolucionCaja impresiondc = new ImpresionDevolucionCaja();       
        //    ImpresionSalida impresions = new ImpresionSalida();  
        //    ImpresionCancelacionAnticipoAlmacen impresioncaa = new ImpresionCancelacionAnticipoAlmacen();  
        //    ImpresionCancelacionCFD impresionccfd = new ImpresionCancelacionCFD();    
        //    ImpresionCancelacionCompras impresioncc = new ImpresionCancelacionCompras();  
        //    ImpresionCuentasCobrar impresioncpc = new ImpresionCuentasCobrar();  
        //    ImpresionCuentasPagar impresioncpp = new ImpresionCuentasPagar();  
        //    ImpresionEtiquetas impresione = new ImpresionEtiquetas(); 
              ImpresionCierre impresioncierre = new ImpresionCierre();  
            List<Partidas> partidas = new ArrayList<Partidas>();
            Partidas partida1 = new Partidas();
            Partidas partida2 = new Partidas();
            Partidas partida3 = new Partidas();
            partida1.setCantidad(6);
            partida1.setDescripcionArticulo("Cuenta 12024 Venta 93234 Referencia 12759");
            partida1.setCodigoArticulo("AS921");
            partida1.setCantidad(6); 
            partida1.setConBeneficio(85.56); //para pedidos y cuentas por cobrar/pagar
            partida1.setPrecioVenta(85.56);
            partida1.setSubtotal(0.00);
            partida2.setDescripcionArticulo("Cuenta 12023 Venta 93225 Referencia 10000");
            partida2.setCodigoArticulo("AS921");
            partida2.setCantidad(6);
            partida2.setConBeneficio(240.04);  //para pedidos
            partida2.setConBeneficio(437.10); //para pedidos y cuentas por cobrar/pagar
            partida2.setPrecioVenta(437.10);
            partida2.setSubtotal(0.00);
            partida3.setDescripcionArticulo("Cuenta 12024 Venta 92890 Referencia 10000");
            partida3.setCodigoArticulo("AS921");
            partida3.setCantidad(6);
            partida3.setConBeneficio(120.04);  //para pedidos
            partida3.setConBeneficio(186.00); //para pedidos y cuentas por cobrar/pagar
            partida3.setPrecioVenta(186.00);
            partida3.setSubtotal(0.00);
            partidas.add(partida1);
            partidas.add(partida2);
            partidas.add(partida3); 
            
            List<Cierre> lcierre = new ArrayList<Cierre>();
            Cierre cierre = new Cierre();
            cierre.setArticulos("7");
            cierre.setFechaVenta("2010-01-01 12:00:00");
            cierre.setId("1");
            cierre.setIdCliente("2");
            cierre.setIdUsuario("3");
            cierre.setTipoCierre("Compras");
            cierre.setTipoCliente("Cliente");
            cierre.setTotal("2313.92");
            lcierre.add(cierre);
         //   impresionventa.inicializar("105756", "Público en General", "1","VICTOR ESCALONA", "14/06/2012",partidas);
         // impresionpedido.inicializar("105756", "1","VICTOR ESCALONA", "14/06/2012",partidas);
       //   impresiontv.inicializar("105756","Público en General","1","VICTOR ESCALONA", "14/06/2012",partidas,"EFECTIVO",
        //             "1000.00","160.00","1160.00");
        //    impresiondc.inicializar("105756","Público en General","1","VICTOR ESCALONA", "14/06/2012",partidas,"EFECTIVO",
         //            "1000.00","160.00","1160.00","SOLICITO ORIGINAL");
          //  impresionca.inicializar("105756","Público en General","1","VICTOR ESCALONA", "14/06/2012",partidas,"EFECTIVO",
         //            "SOLICITO ORIGINAL","JAVIER SANCHEZ");
         //     impresions.inicializar("105756","1","VICTOR ESCALONA", "14/06/2012",partidas,
         //           "JAVIER SANCHEZ");  
        //   impresioncaa.inicializar("105756","NO SON LAS PIEZAS","394829", "ZITA ESTRADA",
        //            "15/06/2012 06:23:31 pm","JAVIER SANCHEZ",partidas);
        //    impresioncpc.inicializar("JAIME OMAR MORENO CASTRO", "ZITA ESTRADA RIOS", "18/06/2012 12:54:24 p.m.", partidas);
        //      impresioncpp.inicializar("JAIME OMAR MORENO CASTRO", "ZITA ESTRADA RIOS", "18/06/2012 12:54:24 p.m.", partidas);
        //        impresione.inicializar("SALPICADERA MUSTANG COBRA JL 29", "1 Captura C02938 Fecha:"+new hCuentasPorCobrar().generarFecha().substring(0,10)+" C.Prov 39U293", "0620RU");  
        //      impresionccfd.inicializar("101862", "101862", "15/06/2012 06:23:31 pm");
            impresioncierre.inicializar(lcierre);
            PrinterJob job = PrinterJob.getPrinterJob();
         //    job.setPrintable(impresionventa);
         //    job.setPrintable(impresionpedido);
         //    job.setPrintable(impresiontv);
         //    job.setPrintable(impresiondc);   
         //    job.setPrintable(impresionca);   
         //    job.setPrintable(impresions);   
        //     job.setPrintable(impresioncaa);   
         //    job.setPrintable(impresionccfd);  
          //   job.setPrintable(impresioncc);
           //  job.setPrintable(impresioncpc);
           //    job.setPrintable(impresioncpp);
            job.setPrintable(impresioncierre);  
            
           //  PageFormat pf = new PageFormat();
             //sólo para impresione
             
           //  Paper p = new Paper();
           //  p.setSize(283.38916256157637,141.87192118226602);
           //  p.setImageableArea(0,0,283.38916256157637, 141.87192118226602);
           //  pf.setPaper(p);
             //sólo para impresione
             
            if (job.printDialog())
            {
                //sólo para impresione
           // job.setPrintable(impresione,pf);    
            //sólo para impresione
            job.print(); 
            }
        } catch (PrinterException ex) {
            Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
