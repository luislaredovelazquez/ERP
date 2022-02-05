/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
/**
 *
 * @author ALL
 */
public class GenerarEtiqueta {

    public GenerarEtiqueta() {
    }
    
       public BufferedImage crearCode(String codigo)
    {


      JBarcode jbcode = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());

      String code = codigo;
        try {
            BufferedImage img = jbcode.createBarcode(code);
          //  System.out.println(img);
            return img;
        } catch (InvalidAtributeException ex) {
            Logger.getLogger(GenerarEtiqueta.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e)
        {
           e.printStackTrace();
        }


        return null;

    }
}
