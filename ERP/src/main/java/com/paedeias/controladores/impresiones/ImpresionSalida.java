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
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ALL
 */
public class ImpresionSalida implements Printable{
    
    String noVenta="";
    String totalArt="";
    String vendedor="";
    String fecha="";
    String almacen="";
    List<Partidas> partidas;
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String cadenaImpresion = "";

    
    public ImpresionSalida()
    {}
    
        public void inicializar(String noVenta,
            String totalArt, String vendedor, String fecha,List<Partidas> partidas,String observaciones)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.noVenta = noVenta;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));   
         i++;   
        }
        
        
        this.totalArt = totalArt;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.almacen = "--A"+this.noVenta+"--";       
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       /*    
       font = new Font(Font.DIALOG, Font.PLAIN,6);            
       Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
       attributes.put(TextAttribute.TRACKING, 0.3);
       Font font2 = font.deriveFont(attributes);
       graphics.setFont(font2); */
           
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
         graphics.drawString("** SALIDA **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 17;
         int o = 0;
         while(o<partidas.size())
         {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         graphics.drawString(partidas.get(o).getDescripcionArticulo(), margen,renglon); //+17
         else
         graphics.drawString(partidas.get(o).getDescripcionArticulo().substring(0,35), margen,renglon); //+17    
         renglon += 12;
         graphics.drawString("    "+partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo(), margen,renglon); //+12
         renglon += 17;
         o++;
         }

         graphics.drawString("***********************************", margen,renglon); //+17
         renglon += 17;
         graphics.drawString("No. total de articulos = "+totalArt, margen,renglon);//+17
         renglon += 16;
         if(vendedor.length()<25)
         graphics.drawString("Pedido por "+vendedor, margen,renglon); //+16
         else
         graphics.drawString("Pedido por "+vendedor.substring(0,24), margen,renglon); //+16    
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
         cadenaImpresion = cadenaImpresion +                 "** SALIDA **";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         for (int o=0; o<partidas.size(); o++)
         {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         cadenaImpresion = cadenaImpresion +                 partidas.get(o).getDescripcionArticulo();
         else
         cadenaImpresion = cadenaImpresion +                 partidas.get(o).getDescripcionArticulo().substring(0,35);
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "    "+partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo();
         cadenaImpresion = cadenaImpresion +                 "\n";
         }
         
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "No. total de articulos = "+totalArt;
         cadenaImpresion = cadenaImpresion +                 "\n";
         if(vendedor.length()<25)
         cadenaImpresion = cadenaImpresion +                 "Pedido por "+vendedor;
         else
         cadenaImpresion = cadenaImpresion +                 "Pedido por "+vendedor.substring(0,24);
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 fecha;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 almacen;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         
         return cadenaImpresion;
     }
    
}
