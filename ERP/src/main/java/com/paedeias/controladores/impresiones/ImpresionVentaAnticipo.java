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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALL
 */
public class ImpresionVentaAnticipo implements Printable{
    
    String anticipo="";
    String resta="";
    String iva="";
    String total="";
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    DecimalFormat df;
    Font font;
    Font font2;
    String cadenaImpresion = "";
    
    public ImpresionVentaAnticipo()
    {}
    
        public void inicializar(String anticipo,String resta,String iva, 
            String total)
    {
        df = new DecimalFormat("0.00");
        this.resta = resta;
        this.anticipo = anticipo;
        this.iva = iva;
        this.total = total;
        
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       font = new Font(Font.DIALOG, Font.PLAIN,10);     
       font2 = new Font(Font.DIALOG, Font.PLAIN,12);  
       graphics.setFont(font);
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
          
         renglon=inicio; 
         graphics.drawString("***********************************", margen,renglon); //+17
         renglon += 17;
         graphics.drawString("Clave "+anticipo, margen,renglon);//+17
         renglon += 16;
         graphics.drawString("Resta "+resta, margen,renglon); //centrar + 16
         renglon += 15;
         graphics.drawString("Iva "+iva, margen,renglon); //15
         renglon += 15;
         graphics.setFont(font2);
         graphics.drawString("Total "+total, margen,renglon); //+15
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
         renglon += 14;
         graphics.drawString("===================================", margen,renglon); //+14
         
         
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
   }
       
       public String generarCadenaImpresion()
       {
           cadenaImpresion = "***********************************";
           cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Clave "+anticipo;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Resta "+resta;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Iva "+iva;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Total "+total;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
           
           
           
           
           return cadenaImpresion;
       }
    
}

