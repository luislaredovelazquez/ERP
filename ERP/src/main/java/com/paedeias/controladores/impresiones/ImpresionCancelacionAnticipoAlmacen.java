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
public class ImpresionCancelacionAnticipoAlmacen implements Printable{
    
    String noAnticipo="";
    String observaciones="";
    String cancelacion="";
    String encargado="";
    String fecha="";
    String almacenista="";
    List<Partidas> partidas;
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String tipoDevolucion;
    String cadenaImpresion = "";

    
    public ImpresionCancelacionAnticipoAlmacen()
    {}
    
        public void inicializar(String noAnticipo,
            String observaciones, String cancelacion, String encargado, String fecha,String almacenista,List<Partidas> partidas,String tipoDevolucion)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.noAnticipo = noAnticipo;        
        this.observaciones = observaciones;
        this.cancelacion = cancelacion;
        this.encargado = encargado;
        this.fecha = fecha;
        this.almacenista = almacenista;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));   
         i++;   
        }
        this.tipoDevolucion = tipoDevolucion;
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        font = new Font(Font.DIALOG, Font.PLAIN,10);     
        font2 = new Font(Font.DIALOG, Font.PLAIN,8);  
        graphics.setFont(font);
           
       /* font = new Font(Font.DIALOG, Font.PLAIN,6);     
       Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
       attributes.put(TextAttribute.TRACKING, 0.4);
       Font font2 = font.deriveFont(attributes);
       graphics.setFont(font2); */ //Impresión para San Mateo
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
         FontMetrics fm   = graphics.getFontMetrics();
         renglon=inicio; 
         graphics.drawString("** "+tipoDevolucion+" **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 17;
         graphics.drawString("No. Folio Anticipo  "+noAnticipo, margen,renglon);//+17
         renglon += 16;
         graphics.drawString("Observaciones "+observaciones, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("Realizada por  "+encargado, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("Almacenista  "+almacenista, margen,renglon); //+16
         renglon += 16;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 16;
         graphics.drawString("Detalles: ", margen,renglon); //+16
         renglon += 16;
         int o = 0;
         while(o<partidas.size())
         {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         graphics.drawString(partidas.get(o).getDescripcionArticulo(), margen,renglon); //+17
         else
         graphics.drawString(partidas.get(o).getDescripcionArticulo().substring(0,35), margen,renglon); //+17    
         renglon += 12;
         graphics.drawString("Cantidad: "+partidas.get(o).getCantidad()+"    Código: "+partidas.get(o).getCodigoArticulo()+" ", margen,renglon); //+12
         int stringWidth = fm.stringWidth("Precio: "+partidas.get(o).getConBeneficio());
         int x = (ancho - stringWidth); 
         renglon += 16;
         graphics.drawString("Precio: "+partidas.get(o).getConBeneficio(), x,renglon); //+12
         renglon += 17;
         o++;
         }
         
         graphics.drawString("===================================", margen,renglon); //+14  
         
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
      public String  generarCadenaImpresion(){
          
         cadenaImpresion =                   "** "+tipoDevolucion+" **";
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "===================================";
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "No. Folio Anticipo  "+noAnticipo;
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Observaciones "+observaciones;
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Realizada por  "+encargado;
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Almacenista  "+almacenista;
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + fecha;
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Detalles: ";
         cadenaImpresion = cadenaImpresion             + "\n";
         
             for (int o=0; o<partidas.size(); o++)
    {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         cadenaImpresion = cadenaImpresion             + partidas.get(o).getDescripcionArticulo();
         else
         cadenaImpresion = cadenaImpresion             + partidas.get(o).getDescripcionArticulo().substring(0,35);
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Cantidad: "+partidas.get(o).getCantidad()+"    Código: "+partidas.get(o).getCodigoArticulo()+" ";
         cadenaImpresion = cadenaImpresion             + "\n";
         cadenaImpresion = cadenaImpresion             + "Precio: "+partidas.get(o).getConBeneficio();
         cadenaImpresion = cadenaImpresion             + "\n";
    }
         cadenaImpresion = cadenaImpresion             + "\n";    
         cadenaImpresion = cadenaImpresion             + "===================================";    
         
          return cadenaImpresion;
      }
       
       
       
}
