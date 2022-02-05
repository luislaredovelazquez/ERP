/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import com.paedeias.identidades.Partidas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALL
 */
public class ImpresionCancelacionAlmacen implements Printable{
    
    String noVenta="";
    String cliente="";
    String totalArt="";
    String vendedor="";
    String fecha="";
    String almacen="";
    String tipoPago="";
    int cantidad=0;
    private String almacenista="";
    private String observaciones="";
    List<Partidas> partidas;
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String tipoDevolucion="";
    String cadenaImpresion="";

    
    public ImpresionCancelacionAlmacen()
    {}
    
        public void inicializar(String noVenta,String cliente, 
            String totalArt, String vendedor, String fecha,List<Partidas> partidas,String tipoPago,
            String observaciones, String almacenista,String tipoDevolucion)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.noVenta = noVenta;
        this.cliente = cliente;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i)); 
         this.cantidad = this.cantidad + partidas.get(i).getCantidad();
         i++;   
        }
        
        
        this.totalArt = totalArt;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.almacen = "--A"+this.noVenta+"--";
        this.tipoPago = tipoPago;
        this.observaciones = observaciones;
        this.almacenista = almacenista;
        this.tipoDevolucion = tipoDevolucion;
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

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
         graphics.drawString("VENTA NO. A"+noVenta, margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("** "+tipoDevolucion+" **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("Tipo Pago: "+tipoPago, margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 16;
         if(cliente.length()<26)
         graphics.drawString(cliente, margen,renglon); //+13
         else
         graphics.drawString(cliente.substring(0, 25), margen,renglon); //+13    
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
         
         graphics.drawString("Observaciones "+observaciones, margen,renglon); //+17
         renglon += 17;
         graphics.drawString("***********************************", margen,renglon); //+17
         renglon += 17;
         graphics.drawString("No. total de articulos devueltos = "+cantidad, margen,renglon);//+17
         renglon += 16;
         if(vendedor.length()<25)
         graphics.drawString("Realizada por "+vendedor, margen,renglon); //+16
         else
         graphics.drawString("Realizada por "+vendedor.substring(0,24), margen,renglon); //+16    
         renglon += 15;
         if(almacenista.length()<25)
         graphics.drawString("Almacenista "+almacenista, margen,renglon); //+16
         else
         graphics.drawString("Almacenista "+almacenista.substring(0,24), margen,renglon); //+16    
         renglon += 15;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 15;
         graphics.drawString(almacen, x,renglon); //centrar + 15
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
 
         
         
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
       
    public String generarCadenaImpresion()
    {
        cadenaImpresion =                       "VENTA NO. A"+noVenta;
        cadenaImpresion = cadenaImpresion +                "\n";
        cadenaImpresion = cadenaImpresion +    "** "+tipoDevolucion+" **";
        cadenaImpresion = cadenaImpresion +                "\n";
        cadenaImpresion = cadenaImpresion +       "Tipo Pago: "+tipoPago;
        cadenaImpresion = cadenaImpresion +                "\n";
        cadenaImpresion = cadenaImpresion + "===================================";
        cadenaImpresion = cadenaImpresion +                "\n";
         if(cliente.length()<26)
         cadenaImpresion = cadenaImpresion +  cliente;
         else
         cadenaImpresion = cadenaImpresion +  cliente.substring(0, 25);
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "***********************************";
         cadenaImpresion = cadenaImpresion +                "\n";
             for (int o=0; o<partidas.size(); o++)
    {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         cadenaImpresion = cadenaImpresion +       partidas.get(o).getDescripcionArticulo();
         else
         cadenaImpresion = cadenaImpresion +       partidas.get(o).getDescripcionArticulo().substring(0,35);
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "    "+partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo() + " $ "+partidas.get(o).getSubtotal();
         cadenaImpresion = cadenaImpresion +                "\n";        
    }
         cadenaImpresion = cadenaImpresion +       "Observaciones "+observaciones;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "***********************************";
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "No. total de articulos devueltos = "+cantidad;
         cadenaImpresion = cadenaImpresion +                "\n";
         if(vendedor.length()<25)
         cadenaImpresion = cadenaImpresion +       "Realizada por "+vendedor;
         else
         cadenaImpresion = cadenaImpresion +       "Realizada por "+vendedor.substring(0,24);
         cadenaImpresion = cadenaImpresion +                "\n";
         if(almacenista.length()<25)
         cadenaImpresion = cadenaImpresion +       "Almacenista "+almacenista;
         else
         cadenaImpresion = cadenaImpresion +       "Almacenista "+almacenista.substring(0,24);
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       fecha;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       almacen;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "===================================";
         
        return cadenaImpresion;
    }
       
    
}
