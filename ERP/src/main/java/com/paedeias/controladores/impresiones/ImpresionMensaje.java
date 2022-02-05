/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ALL
 */
public class ImpresionMensaje implements Printable{

    Font font;
    int ancho = 240;
    int inicio=100;
    int renglon=0;
    int margen=20;
    String mensaje="";
    
    public ImpresionMensaje() {
    }
    
    public void inicializar(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
    return 0;    
    }
    
           public String generarCadenaImpresion()
       {
           return wrapText(mensaje,16);
       }
           
    static String wrapText (String text, int len)
{
  // return empty array for null text
  if (text == null)
  return "";

  // return text if len is zero or less
  if (len <= 0)
  return "";

  // return text if less than length
  if (text.length() <= len)
  return "";

  char [] chars = text.toCharArray();
  Vector lines = new Vector();
  StringBuffer line = new StringBuffer();
  StringBuffer word = new StringBuffer();

  for (int i = 0; i < chars.length; i++) {
    word.append(chars[i]);

    if (chars[i] == ' ') {
      if ((line.length() + word.length()) > len) {
        lines.add(line.toString());
        line.delete(0, line.length());
      }

      line.append(word);
      word.delete(0, word.length());
    }
  }

  // handle any extra chars in current word
  if (word.length() > 0) {
    if ((line.length() + word.length()) > len) {
      lines.add(line.toString());
      line.delete(0, line.length());
    }
    line.append(word);
  }

  // handle extra line
  if (line.length() > 0) {
    lines.add(line.toString());
  }

  String [] ret = new String[lines.size()];
  String men = "";
  int c = 0; // counter
  for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
    ret[c] = (String) e.nextElement();
    men = men + ret[c] + "\n";
  }

  return men;
}
}
