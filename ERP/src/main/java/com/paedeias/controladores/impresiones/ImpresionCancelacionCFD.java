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
public class ImpresionCancelacionCFD implements Printable{
    
    String noVenta="";
    String noReferencia="";
    String fecha="";
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String cadenaImpresion="";

    
    public ImpresionCancelacionCFD()
    {}
    
        public void inicializar(String noVenta,
            String noReferencia, String fecha)
    {
        this.noReferencia = noReferencia;
        this.noVenta = noVenta;
        this.fecha = fecha;
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       font = new Font(Font.DIALOG, Font.PLAIN,10);     
       font2 = new Font(Font.DIALOG, Font.PLAIN,8);  
       graphics.setFont(font);
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
          
         renglon=inicio; 
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("** CANCELACIÓN DE CFD **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 17;
         graphics.drawString("CÓDIGO DE VENTA: "+noVenta, margen,renglon);//+17
         renglon += 17;
         graphics.drawString("NO. DE REFERENCIA: "+noVenta, margen,renglon);//+17
         renglon += 16;
         graphics.drawString("FECHA: "+fecha, margen,renglon); //15
         renglon += 15;
         graphics.drawString("===================================", margen,renglon); //+14  
         
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
       
       public String generarCadenaImpresion()
       {
         
         cadenaImpresion =                       "===================================";
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "** CANCELACIÓN DE CFD **";
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +      "===================================";
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "CÓDIGO DE VENTA: "+noVenta;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "NO. DE REFERENCIA: "+noVenta;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "FECHA: "+fecha;
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +       "===================================";
                               
           return cadenaImpresion;
       }
    
}
