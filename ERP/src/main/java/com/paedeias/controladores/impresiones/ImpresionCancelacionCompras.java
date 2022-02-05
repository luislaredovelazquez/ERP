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
public class ImpresionCancelacionCompras implements Printable{
    
    String noCompra="";
    String observaciones="";
    String cancelacion="";
    String encargado="";
    String fecha="";
    String almacenista="";
    String noFactura="";
    String proveedor="";
    private String titularCancelacion="";
    private String observacionCancelacion="";
    private String fechaCancelacion="";
    private String subtotal;
    private String iva;
    private String importe;
    List<Partidas> partidas;
    Font font;
    Font font2;
    int ancho = 200;
    int inicio=100;
    int renglon=0;
    int margen=23;
    String cadenaImpresion="";

    
    public ImpresionCancelacionCompras()
    {}
    
        public void inicializar(String cancelacion,
            String observaciones,String encargado, String almacenista, String fecha,
            List<Partidas> partidas,String proveedor, String noCompra, String noFactura, 
            String subtotal, String iva, String importe,String titularCancelacion,
            String observacionCancelacion, String fechaCancelacion)
    {
        
        this.partidas = new ArrayList<Partidas>();
        this.noCompra = noCompra;        
        this.observaciones = observaciones;
        this.cancelacion = cancelacion;
        this.encargado = encargado;
        this.fecha = fecha;
        this.almacenista = almacenista;
        this.proveedor = proveedor;
        this.noFactura = noFactura;
        this.subtotal = subtotal;
        this.iva = iva;
        this.importe = importe;
        this.titularCancelacion = titularCancelacion;
        this.observacionCancelacion = observacionCancelacion;
        this.fechaCancelacion = fechaCancelacion;
        
        int i=0;
        while(i<partidas.size())
        {
         this.partidas.add(partidas.get(i));   
         i++;   
        }
        
    }
    
   
    
    
       public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

       font = new Font(Font.DIALOG, Font.PLAIN,10);     
       font2 = new Font(Font.DIALOG, Font.PLAIN,8);  
       graphics.setFont(font);
      
      
           
      if (pageIndex == 0) 
      {
         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
         FontMetrics fm   = graphics.getFontMetrics();
         renglon=inicio; 
         graphics.drawString("** CANCELACION **", margen+20,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 17;
         graphics.drawString("No. Compra  "+noCompra, margen,renglon);//+17
         renglon += 16;
         graphics.drawString("Observaciones "+observaciones, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("Realizada por  "+encargado, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("Almacenista  "+almacenista, margen,renglon); //+16
         renglon += 16;
         graphics.drawString(fecha, margen,renglon); //15
         renglon += 16;
         graphics.drawString("===================================", margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("*** DEVOLUCIÓN DE COMPRAS ***", margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("------------------------------------------------", margen,renglon); //empieza en 100
         renglon += 16;
         graphics.drawString("Proveedor  "+proveedor, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("Compra  "+noCompra+"Factura "+noFactura, margen,renglon); //+16
         renglon += 16;
         graphics.drawString("------------------------------------------------", margen,renglon); //empieza en 100
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
         
         graphics.drawString("Subtotal  "+subtotal, margen,renglon); //+16
         renglon += 17;
         graphics.drawString("IVA  "+iva, margen,renglon); //+16
         renglon += 17;
         graphics.drawString("Importe  "+importe, margen,renglon); //+16
         renglon += 17;
         
         graphics.drawString("===================================", margen,renglon); //+14  
         renglon += 17;         
         graphics.drawString("Realizada por  "+titularCancelacion, margen,renglon); //+16
         renglon += 17;
         graphics.drawString("Obs.  "+observacionCancelacion, margen,renglon); //+16
         renglon += 17;
         graphics.drawString("Fecha  "+fechaCancelacion, margen,renglon); //+16
         renglon += 17;
         graphics.drawString("===================================", margen,renglon); //+14  
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
    }
    
    public String generarCadenaImpresion()
    {
        cadenaImpresion =                             "** CANCELACION **";
        cadenaImpresion = cadenaImpresion +                "\n";
        cadenaImpresion = cadenaImpresion +  "===================================";
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +     "No. Compra  "+noCompra;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +    "Observaciones "+observaciones;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +     "Realizada por  "+encargado;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +    "Almacenista  "+almacenista;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                fecha;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +  "===================================";
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion + "*** DEVOLUCIÓN DE COMPRAS ***";
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +  "------------------------------------------------";
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +  "Proveedor  "+proveedor;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +  "Compra  "+noCompra+"Factura "+noFactura;
        cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +   "------------------------------------------------";
        cadenaImpresion = cadenaImpresion +                "\n";      
        
     for (int o=0; o<partidas.size(); o++)
    {
         if(partidas.get(o).getDescripcionArticulo().length() < 36)    
         cadenaImpresion = cadenaImpresion +                partidas.get(o).getDescripcionArticulo();      
         else
         cadenaImpresion = cadenaImpresion +                partidas.get(o).getDescripcionArticulo().substring(0,35);      
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "Cantidad: "+partidas.get(o).getCantidad()+"    Código: "+partidas.get(o).getCodigoArticulo()+" ";      
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "Precio: "+partidas.get(o).getConBeneficio();      
         cadenaImpresion = cadenaImpresion +                "\n"; 
    }
        
         cadenaImpresion = cadenaImpresion +                "Subtotal  "+subtotal; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "IVA  "+iva; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "Importe  "+importe; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         
         cadenaImpresion = cadenaImpresion +                "==================================="; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "Realizada por  "+titularCancelacion; 
         cadenaImpresion = cadenaImpresion +                "\n";
         cadenaImpresion = cadenaImpresion +                "Obs.  "+observacionCancelacion; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "Fecha  "+fechaCancelacion; 
         cadenaImpresion = cadenaImpresion +                "\n"; 
         cadenaImpresion = cadenaImpresion +                "==================================="; 
         
        return cadenaImpresion;
    }
       
       
}
