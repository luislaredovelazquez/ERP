/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.helpers.hLineas;
import com.paedeias.identidades.Linea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Luis
 */
public class VAlta_Lineas extends JPanel{
    
    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1,campoTexto2;
    JButton boton1;
    //JButton boton3;
    ImageIcon imagen;
    JTable tablaLineas;
    hLineas hlinea;
    Vector vector,encabezadoLineas;
    DefaultTableModel dtm;

    public VAlta_Lineas()
    {
     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());
     panelPrincipal.setBackground(Color.white);

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelEncabezado.setBackground(Color.white);

     etiqueta1 = new JLabel("Línea: ");
     campoTexto1 = new JTextField(20);

     etiqueta2 = new JLabel("Descripción: ");
     campoTexto2 = new JTextField(40);

     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);



   //  boton3 = new JButton("Actualizar");
   //  boton3.setBackground(Color.white);

     panelEncabezado.add(etiqueta1);
     panelEncabezado.add(campoTexto1);
     panelEncabezado.add(etiqueta2);
     panelEncabezado.add(campoTexto2);

     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());

     tablaLineas = new JTable();
     encabezadoLineas = new Vector<String>();
     encabezadoLineas.add("Nombre");
     encabezadoLineas.add("Descripción");

     hlinea = new hLineas();
     vector = new Vector();
        
      dtm = new DefaultTableModel(vector,encabezadoLineas);
      tablaLineas.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaLineas));

           boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                Linea lin = new Linea();
                lin.setNombre(campoTexto1.getText());
                lin.setDescripcion(campoTexto2.getText());
                hLineas hlin = new hLineas();
                hlin.guardarLineas(lin);
                List<Linea> listalin = hlin.consultaLineas("nombre","=",lin.getNombre());
                Vector<Object> unaFila = new Vector<Object>();
                unaFila.add(listalin.get(0).getNombre());
                unaFila.add(listalin.get(0).getDescripcion());
                vector.add(unaFila);
                dtm.fireTableRowsInserted(1, 1);
        tablaLineas.setModel(dtm);
        campoTexto1.setText("");
        campoTexto2.setText("");
        VCatalogo_Lineas.botonBuscar.doClick();

            }});
     panelEncabezado.add(boton1);

    
  /*   boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              int arreglo[] = tablaLineas.getSelectedRows();
            for(int i=0; i<arreglo.length; i++){
            Linea linea = new Linea();
            linea.setId(Integer.parseInt(tablaLineas.getValueAt(arreglo[i],0).toString()));
            linea.setNombre(tablaLineas.getValueAt(arreglo[i], 1).toString());
            hlinea.actualizarLineas(linea);
                   }
        dtm.setDataVector(vector,encabezadoLineas);
        tablaLineas.setModel(dtm);
            }
        }); */

//     panelEncabezado.add(boton3);

     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     add(panelPrincipal);

    }

}
