/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Partidas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ALL
 */
public class ImpresionDevolucionCaja implements Printable{
    
    String noVenta="";
    String cliente="";
    String totalArt="";
    String vendedor="";
    String fecha="";
    String almacen="";
    String tipoPago="";
    double subtotal=0;
    int cantidad = 0;
    String iva="";
    String total="";
    private String observaciones="";
    List<Partidas> partidas;
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String tipoDevolucion="";
    DecimalFormat df;
    String cadenaImpresion;
    String centrar=" ";
    
    public ImpresionDevolucionCaja()
    {}
    
        public void inicializar(String noVenta,String cliente, 
            String totalArt, String vendedor, String fecha,List<Partidas> partidas,String tipoPago,
            String subtotal, String total, String iva, String observaciones, String tipoDevolucion)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.noVenta = noVenta;
        this.cliente = cliente;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));  
         this.subtotal = this.subtotal + partidas.get(i).getSubtotal();
         this.cantidad = this.cantidad + partidas.get(i).getCantidad();
         i++;   
        }
        
        
        this.totalArt = totalArt;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.almacen = "--A"+this.noVenta+"--";
        this.tipoPago = tipoPago;
        this.iva = iva;
        this.total = total;
        this.observaciones = observaciones;
        this.tipoDevolucion = tipoDevolucion;
        
        df = new DecimalFormat("0.00");
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       /* font = new Font(Font.DIALOG, Font.PLAIN,6);     
       Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
       attributes.put(TextAttribute.TRACKING, 0.4);
       Font font2 = font.deriveFont(attributes);
       graphics.setFont(font2); */ //para san mateo
      
       font = new Font(Font.DIALOG, Font.PLAIN,10);     
       font2 = new Font(Font.DIALOG, Font.PLAIN,8);  
       graphics.setFont(font);    
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
          
         renglon=inicio; 
         FontMetrics fm   = graphics.getFontMetrics();
         int stringWidth = fm.stringWidth(almacen);
         int x = (ancho - stringWidth) / 2;
       //  int t1 = fm.stringWidth("COLISI�N Y MEC�NICA S.A DE C.V");
         int t1 = fm.stringWidth(CGlobalConfig.getCampli1());
         int xt1 = (ancho - t1) / 2;
       //  graphics.drawString("COLISI�N Y MEC�NICA S.A DE C.V", xt1,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli1(), xt1,renglon); //empieza en 100
         graphics.setFont(font2);
         renglon += 12;
        // graphics.drawString("SALVADOR DIAZ MIR�N NO. 306", margen+10,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli2(), margen+10,renglon); //empieza en 100
         renglon += 12;
        // int t3 = fm.stringWidth("COLONIA SANCHEZ COLIN C.P. 50150");
         int t3 = fm.stringWidth(CGlobalConfig.getCampli3());
         int xt3 = (ancho - t3) / 2;
       //  graphics.drawString("COLONIA SANCHEZ COLIN C.P. 50150", margen,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli3(), margen,renglon); //empieza en 100
         renglon += 12;
        // int t4 = fm.stringWidth("TOLUCA, EDO. DE M�X.");
         int t4 = fm.stringWidth(CGlobalConfig.getCampli4());
         int xt4 = (ancho - t4) / 2;
        // graphics.drawString("TOLUCA, EDO. DE M�X.", xt4,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli4(), xt4,renglon); //empieza en 100
         renglon += 12;
        // int t5 = fm.stringWidth("R.F.C CME000515HX7");
         int t5 = fm.stringWidth(CGlobalConfig.getCampli5());
         int xt5 = (ancho - t5) / 2;
        // graphics.drawString("R.F.C CME000515HX7", xt5,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli5(), xt5,renglon); //empieza en 100
         renglon += 12;
        // int t6 = fm.stringWidth("TELS. (017222)212-15-14,");
         int t6 = fm.stringWidth(CGlobalConfig.getCampli6());
         int xt6 = (ancho - t6) / 2;
        // graphics.drawString("TELS. (017222)212-15-14,", xt6,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli6(), xt6,renglon); //empieza en 100
         renglon += 12;
         // int t7 = fm.stringWidth("219-16-66,2 19-23-18");
         int t7 = fm.stringWidth(CGlobalConfig.getCampli7());
         int xt7 = (ancho - t7) / 2;
        // graphics.drawString("219-16-66,2 19-23-18", xt7,renglon); //empieza en 100
         graphics.drawString(CGlobalConfig.getCampli7(), xt7,renglon); //empieza en 100
         graphics.setFont(font);
         renglon += 16;
         graphics.drawString("** "+tipoDevolucion+" **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("Tipo Pago: "+tipoPago, margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 16;
         if(cliente.length()<26)
         graphics.drawString("Cliente: "+cliente, margen,renglon); //+13
         else
         graphics.drawString("Cliente: "+cliente.substring(0, 25), margen,renglon); //+13    
         renglon += 13;
         graphics.drawString("***********************************", margen,renglon); //+13
         renglon += 17;
         int o = 0;
         while(o<partidas.size())
         {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         graphics.drawString(partidas.get(o).getDescripcionArticulo(), margen,renglon); //+17
         else
         graphics.drawString(partidas.get(o).getDescripcionArticulo().substring(0,35), margen,renglon); //+17    
         renglon += 12;
         graphics.drawString("    "+partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo() + " $ "+partidas.get(o).getSubtotal(), margen,renglon); //+12
         renglon += 17;
         o++;
         }
         
         int tsubtotal = fm.stringWidth("Subtotal "+df.format(subtotal));
         int xsubtotal = ancho - tsubtotal;
         renglon += 16;
         graphics.drawString("Subtotal "+df.format(subtotal), margen,renglon); //+17
         int tiva = fm.stringWidth("I.V.A "+iva);
         int xiva = ancho - tiva;
         renglon += 16;
         graphics.drawString("I.V.A "+iva, margen,renglon); //+17
         int ttotal = fm.stringWidth("Total "+df.format(subtotal));
         int xtotal = ancho - ttotal;
         renglon += 16;
         graphics.drawString("Total "+df.format(subtotal), margen,renglon); //+17
         renglon += 16;
         graphics.drawString("Observaciones "+observaciones, margen,renglon); //+17
         renglon += 17;
         graphics.drawString("***********************************", margen,renglon); //+17
         renglon += 17;
         graphics.drawString("No. total de articulos = "+cantidad, margen,renglon);//+17
         renglon += 16;
         if(vendedor.length()<25)
         graphics.drawString("Realizada por "+vendedor, margen,renglon); //+16
         else
         graphics.drawString("Realizada por "+vendedor.substring(0,24), margen,renglon); //+16    
         renglon += 15;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 15;
         graphics.drawString(almacen, x,renglon); //centrar + 15
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
         renglon += 18;
         int f1 = fm.stringWidth("COLISI�N Y MEC�NICA S.A DE C.V");
         int xf1 = (ancho - f1) / 2;
         graphics.drawString("* "+tipoDevolucion+" REALIZADA *", margen+15,renglon); //+14
         
         
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
     public String generarCadenaImpresion()
     {
         double espacios = 0;  
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli1().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion =  centrar + CGlobalConfig.getCampli1();
         cadenaImpresion = cadenaImpresion +                 "\n";     
         
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli2().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion + centrar +      CGlobalConfig.getCampli2();
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli3().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion + centrar +      CGlobalConfig.getCampli3();
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli4().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion + centrar +      CGlobalConfig.getCampli4();
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli5().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion +  centrar +     CGlobalConfig.getCampli5();
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         centrar = " ";
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli6().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion +  centrar +     CGlobalConfig.getCampli6();
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         centrar = " "; 
         espacios = (CGlobalConfig.getCaracteresMaximos() - CGlobalConfig.getCampli7().length()) / 2;
         for(int i=0; i<espacios; i++)
         {
         centrar = centrar + " ";    
         }
         
         cadenaImpresion = cadenaImpresion +  centrar +     CGlobalConfig.getCampli7();
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "** "+tipoDevolucion+" **";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Tipo Pago: "+tipoPago;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         if(cliente.length()<26)
         cadenaImpresion = cadenaImpresion +                 "Cliente: "+cliente;
         else
         cadenaImpresion = cadenaImpresion +                 "Cliente: "+cliente.substring(0, 25);
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         for (int o=0; o<partidas.size(); o++)
         {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         cadenaImpresion = cadenaImpresion +                 partidas.get(o).getDescripcionArticulo();
         else
         cadenaImpresion = cadenaImpresion +                 partidas.get(o).getDescripcionArticulo().substring(0,35);
         cadenaImpresion = cadenaImpresion +                 "\n";         
         cadenaImpresion = cadenaImpresion +                 "    "+partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo() + " $ "+partidas.get(o).getSubtotal();
         cadenaImpresion = cadenaImpresion +                 "\n";
         }
         
         
         cadenaImpresion = cadenaImpresion +                 "Subtotal "+df.format(subtotal);
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "I.V.A "+iva;
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "Total "+df.format(subtotal);
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "Observaciones "+observaciones;
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         cadenaImpresion = cadenaImpresion +                 "No. total de articulos = "+cantidad;
         cadenaImpresion = cadenaImpresion +                 "\n";
         if(vendedor.length()<25)
         cadenaImpresion = cadenaImpresion +                 "Realizada por "+vendedor;
         else
         cadenaImpresion = cadenaImpresion +                 "Realizada por "+vendedor.substring(0,24);
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 fecha;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 almacen;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "* "+tipoDevolucion+" REALIZADA *";
         
         return cadenaImpresion;
     }
       
       
}
