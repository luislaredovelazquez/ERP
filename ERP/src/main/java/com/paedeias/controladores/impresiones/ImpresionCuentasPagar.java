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
public class ImpresionCuentasPagar implements Printable{
    
    String cliente="";
    String vendedor="";
    String fecha="";
    List<Partidas> partidas;
    Font font;
    int ancho = 244;
    int inicio=70;
    int renglon=0;
    int margen=10;
    String cadenaImpresion = "";
    String total;
    DecimalFormat df;
    
    public ImpresionCuentasPagar()
    {}
    
        public void inicializar(String cliente, 
             String vendedor, String fecha,List<Partidas> partidas, String total)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.cliente = cliente;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));   
         i++;   
        }
        
        
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.total = total;
        df = new DecimalFormat("0.00");
        
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
         graphics.drawString("COLISIÓN Y MECÁNICA S.A DE C.V.", margen,renglon); //+16
         renglon += 16;
         graphics.drawString("COMPROBANTE CUENTAS POR PAGAR", margen,renglon); //+16
         renglon += 16;
         graphics.drawString("***********************************", margen,renglon); //+16
         renglon += 13;
         graphics.drawString("Cliente: "+cliente, margen,renglon); //+13
         renglon += 13;
         graphics.drawString("***********************************", margen,renglon); //+13
         renglon += 17;
         int o = 0;
         while(o<partidas.size())
         {
         graphics.drawString(partidas.get(o).getDescripcionArticulo(), margen,renglon); //+17
         renglon += 12;
         graphics.drawString("Importe "+partidas.get(o).getPrecioVenta()+" Abono  "+partidas.get(o).getConBeneficio(), margen,renglon); //+12
         renglon += 17;
         graphics.drawString("Nuevo Saldo $ "+partidas.get(o).getSubtotal(), margen,renglon); //+12
         renglon += 17;
         graphics.drawString("--------------------------------------------------------------------", margen,renglon); //+12
         renglon += 17;
         o++;
         }
         graphics.drawString("***********************************", margen,renglon); //+17
         renglon += 16;
         graphics.drawString("Atendido por "+vendedor, margen,renglon); //+16  
         renglon += 15;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 15;
         graphics.drawString("===================================", margen,renglon); //+14
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
       
     public String generarCadenaImpresion(){
         
         cadenaImpresion = "===================================";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "COLISIÓN Y MECÁNICA S.A DE C.V.";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "COMPROBANTE CUENTAS POR PAGAR";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Cliente: "+cliente;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         
         for (int o=0; o<partidas.size(); o++)
         {
         cadenaImpresion = cadenaImpresion +                 partidas.get(o).getDescripcionArticulo();
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Importe "+df.format(partidas.get(o).getPrecioVenta())+" Abono  "+df.format(partidas.get(o).getConBeneficio());
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Nuevo Saldo $ "+df.format(partidas.get(o).getSubtotal());
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "TN "+partidas.get(o).getUbicacion();
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "--------------------------------------------------------------------";
         cadenaImpresion = cadenaImpresion +                 "\n";
         }
         
         cadenaImpresion = cadenaImpresion +                 "***********************************";
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Atendido por "+vendedor;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 fecha.substring(0, 19);
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "Total "+total;
         cadenaImpresion = cadenaImpresion +                 "\n";
         cadenaImpresion = cadenaImpresion +                 "===================================";
         
         return cadenaImpresion;
     }  
       
       
}
