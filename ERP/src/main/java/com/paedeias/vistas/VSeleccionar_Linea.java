/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.helpers.hLineas;
import com.paedeias.identidades.Linea;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class VSeleccionar_Linea extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaLineas;
    hLineas hlinea;
    Vector vector,encabezadoLineas;
    DefaultTableModel dtm;
   List<Linea> listaLineas;

    public VSeleccionar_Linea(){
     tablaLineas = new JTable();
     
     encabezadoLineas = new Vector<String>();
     encabezadoLineas.add("Nombre");

     hlinea = new hLineas();
     listaLineas = hlinea.consultaLineas("","*","");
     vector = new Vector();

       for(Object o: listaLineas){
             Linea lin = (Linea)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getNombre());
             vector.add(unaFila);
         }


      dtm = new DefaultTableModel(vector,encabezadoLineas);
      tablaLineas.setModel(dtm);
      add(new JScrollPane(tablaLineas));
      setLayout(new GridLayout());  
    }

}
