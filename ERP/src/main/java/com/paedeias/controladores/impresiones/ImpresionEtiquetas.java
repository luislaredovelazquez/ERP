/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import com.paedeias.controladores.CGlobalConfig;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import javax.swing.JPanel;

/**
 *
 * @author ALL
 */
public class ImpresionEtiquetas extends JPanel implements Printable{
    
    String descripcion="";
    String descripcion1 = "";
    String descripcion2 = "";
    String queryC="";
    String codigo="";   
    Font font;
    int ancho = 200;
    int inicio=20;
    int renglon=40;
    int margen=6;
    String captura;
    String sinonimo;
    GenerarEtiqueta ge;
    
    public ImpresionEtiquetas()
    {}
    
    public void inicializar(String descripcion, String queryC, String codigo, String captura, String sinonimo){
    this.descripcion = descripcion;
    this.queryC = queryC;
    this.codigo = codigo;
    this.sinonimo = sinonimo;
    this.captura = captura;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       
//        Paper p = new Paper();
        // p.setSize(612.0, 792.0);
//        p.setSize(283.38916256157637,141.87192118226602);
//        p.setImageableArea(pageFormat.getImageableX(), pageFormat.getImageableY(), 283.38916256157637, 141.87192118226602);
//        pageFormat.setPaper(p);
        

        
       font = new Font(Font.DIALOG, Font.PLAIN,8);      
       
       graphics.setFont(font);
    
      
   //          if (pageIndex == 0) 
   //   {
        Graphics2D g2 = (Graphics2D)graphics;
                ge = new GenerarEtiqueta();
        BufferedImage imagenBarras = ge.crearCode(codigo);
   //     System.out.println(imagenBarras.getWidth() + " "+ imagenBarras.getHeight());
     
    //    g2.drawImage(imagenBarras.getSubimage(0, 0, imagenBarras.getWidth(), 30), null, 15, inicio-10);
        
        g2.setFont(new Font("SansSerif",Font.BOLD,12));
        g2.drawString("Cód: " + codigo, margen, 53);
        
        g2.setFont(new Font("SansSerif",Font.ITALIC,12));
        if(descripcion.length() > 35)
        {
            descripcion1 = descripcion.substring(0, 35);
            descripcion2 = descripcion.substring(36, descripcion.length());
            // g2.drawString(descripcion1, margen, 68);inicio
              g2.drawString(descripcion1, margen, inicio);
        //    g2.drawString(descripcion2, margen, 83);inicio + 15
              g2.drawString(descripcion2, margen, inicio + 15);
        }else
            // g2.drawString(descripcion, margen, 68); inicio
               g2.drawString(descripcion, margen, inicio); 
        
        g2.drawImage(imagenBarras.getSubimage(0, 0, imagenBarras.getWidth(), 30), null, 15, 56);
        g2.setFont(new Font("SansSerif",Font.BOLD,12));
        
        g2.drawString("Pzas: " + queryC + " Sin: "+sinonimo, margen, 98);
        
        g2.setFont(new Font("SansSerif",Font.PLAIN,6));
        
          g2.drawString(CGlobalConfig.getCampli1(), margen, 111);
        //   g2.drawString("Ana Karla Díaz Castro", margen, 103);
         g2.drawString(CGlobalConfig.getCampli2() +" "+CGlobalConfig.getCampli3(), margen, 119);
        //   g2.drawString("Av. Benito Juárez #78 Barrio la Concepción", margen, 111);
         g2.drawString(CGlobalConfig.getCampli4(), margen, 127);
              
        //   g2.drawString("San Mateo Atenco, México", margen, 119);
          g2.drawString(CGlobalConfig.getCampli6()+" "+CGlobalConfig.getCampli7(), margen, 135);
        //   g2.drawString("Tels. (722)287-37-73,NX. 52*4033*6", margen, 127);
          
           g2.setFont(new Font("SansSerif",Font.BOLD,12));
           g2.drawString("C: "+captura, 180, 127);
          
          
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        super.paintComponent(g2);  
          return PAGE_EXISTS;
     // }
      //    return NO_SUCH_PAGE;
    }
    
}
