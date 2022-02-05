/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Cierre;
import com.paedeias.identidades.Partidas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALL
 */
public class ImpresionCierre implements Printable {


    List<Cierre> cierre;
    Font font;
    int ancho = 244;
    int inicio=70;
    int renglon=0;
    int margen=10;
    String cadenaImpresion="";
    
    public ImpresionCierre() {
    }

        public void inicializar(List<Cierre> cierre)
    {
          this.cierre = cierre;
        
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
           font = new Font(Font.DIALOG, Font.PLAIN,10);     
       graphics.setFont(font);
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
          
         renglon=inicio; 
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 16;
         // graphics.drawString("COLISIÓN Y MECÁNICA S.A DE C.V.", margen,renglon); //+16
          graphics.drawString(CGlobalConfig.getCampli1(), margen,renglon); //+16
         renglon += 16;
         graphics.drawString("COMPROBANTE DE " + cierre.get(0).getTipoCierre().toUpperCase(), margen,renglon); //+16
         renglon += 16;
         graphics.drawString("***********************************", margen,renglon); //+16
         renglon += 13;
         graphics.drawString("***********************************", margen,renglon); //+13
         renglon += 17;
         int o = 0;
         while(o<cierre.size())
         {
         graphics.drawString("Id "+cierre.get(o).getId()+" Fecha "+cierre.get(o).getFechaVenta(), margen,renglon); //+17
         renglon += 12;
         graphics.drawString(cierre.get(o).getTipoCliente()+" "+cierre.get(o).getIdCliente(), margen,renglon); //+12
         renglon += 17;
         graphics.drawString("Usuario  "+cierre.get(o).getIdUsuario(), margen,renglon); //+12
         renglon += 17;
         graphics.drawString("Artículos  "+cierre.get(o).getArticulos() + " Total $"+cierre.get(o).getTotal(), margen,renglon); //+12
         renglon += 17;
         graphics.drawString("--------------------------------------------------------------------", margen,renglon); //+12
         renglon += 17;
         o++;
         }
         graphics.drawString("===================================", margen,renglon); //+14
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
    public String generarCadenaImpresion(){
        cadenaImpresion = "===================================";
        cadenaImpresion = cadenaImpresion +                 "\n";
        cadenaImpresion = cadenaImpresion +                 CGlobalConfig.getCampli1();
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "COMPROBANTE DE " + cierre.get(0).getTipoCierre().toUpperCase();
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";

             for (int o=0; o<cierre.size(); o++)
             {
         cadenaImpresion = cadenaImpresion +                 "Id "+cierre.get(o).getId()+" Fecha "+cierre.get(o).getFechaVenta();         
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 cierre.get(o).getTipoCliente()+" "+cierre.get(o).getIdCliente();         
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Usuario  "+cierre.get(o).getIdUsuario();         
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Artículos  "+cierre.get(o).getArticulos() + " Total $"+cierre.get(o).getTotal();         
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "--------------------------------------------------------------------";         
         cadenaImpresion = cadenaImpresion +                 "\n";
              }
         cadenaImpresion = cadenaImpresion +                 "===================================";
        
        return cadenaImpresion;
    }
    
    
    
    }
    

