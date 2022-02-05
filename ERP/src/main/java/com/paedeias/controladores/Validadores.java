/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores;

import javax.swing.JOptionPane;

/**
 *
 * @author ALL
 */
public class Validadores {
    
        
     public static String validadorTranferencia(String cadena){
            if(cadena.equals("0000")){
            cadena = "No Identificado"; 
            return cadena;         
        }
	try {
		Integer.parseInt(cadena);
		return cadena;
	} catch (NumberFormatException nfe){
		return "No Identificado";
	}
}
    
     public static boolean validarEntero(String campo, String numero)
     {
                 try
        {
            Integer.valueOf(numero);
            return true;
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Por favor verifique que en el campo "+ campo+ " exista un número");
            return false;
        }
                
     }
     
          public static boolean validarDoble(String campo, String numero)
     {
                 try
        {
            Double.valueOf(numero);
            return true;
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Por favor verifique que en el campo "+ campo+ " exista un número");
            return false;
        }
                
     }
          
          
     
}
