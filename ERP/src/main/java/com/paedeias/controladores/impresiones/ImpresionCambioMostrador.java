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
public class ImpresionCambioMostrador implements Printable{
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    
    String vendedor="";
    String fecha="";
    List<Partidas> partidas;
    Font font;
    int ancho = 240;
    int inicio=100;
    int renglon=0;
    int margen=20;
    String titulo="A MOSTRADOR";
    String cadenaImpresion = "";
    
    public ImpresionCambioMostrador()
    {}
 
    
        public void inicializar(String vendedor, List<Partidas> partidas,String fecha)
    {
        
        this.partidas = new ArrayList<Partidas>();
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));   
         i++;   
        }
        
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.titulo = titulo;
        
    }   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       font = new Font(Font.DIALOG, Font.PLAIN,10);     
       graphics.setFont(font);
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
          
         renglon=inicio; 
         FontMetrics fm   = graphics.getFontMetrics();         
         int pedidoAlmacen = fm.stringWidth("-- CAMBIO "+titulo+"--");
         int x_pedido = (ancho - pedidoAlmacen) / 2;
         graphics.drawString("-- CAMBIO "+titulo+"--", x_pedido,renglon);
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
         
         if(vendedor.length()<25)
         graphics.drawString("Responsable "+vendedor, margen,renglon); //+16
         else
         graphics.drawString("Responsable "+vendedor.substring(0,24), margen,renglon); //+16    
         renglon += 15;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
public String generarCadenaImpresion()
{
    cadenaImpresion =                       "-- CAMBIO "+titulo+"--";
    cadenaImpresion = cadenaImpresion +                "\n";
    cadenaImpresion = cadenaImpresion + "===================================";
    cadenaImpresion = cadenaImpresion +                "\n";
    for (int o=0; o<partidas.size(); o++)
    {
    if(partidas.get(o).getDescripcionArticulo().length() < 36)    
    cadenaImpresion = cadenaImpresion + partidas.get(o).getDescripcionArticulo();    
    else
    cadenaImpresion = cadenaImpresion + partidas.get(o).getDescripcionArticulo().substring(0,35);    
    cadenaImpresion = cadenaImpresion +                "\n";
    cadenaImpresion = cadenaImpresion + partidas.get(o).getCantidad()+"   "+partidas.get(o).getCodigoArticulo();    
    cadenaImpresion = cadenaImpresion +                "\n";
    }
    
     if(vendedor.length()<25)
     cadenaImpresion = cadenaImpresion + "Responsable "+vendedor;
     else
     cadenaImpresion = cadenaImpresion + "Responsable "+vendedor.substring(0,24);    
     
     cadenaImpresion = cadenaImpresion +                "\n";
     cadenaImpresion = cadenaImpresion + fecha;
     cadenaImpresion = cadenaImpresion +                "\n";
    
    return cadenaImpresion;
}

}
