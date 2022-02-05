/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.helpers.hUbicacion;
import com.paedeias.identidades.Ubicacion;
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
public class VAlta_Ubicacion extends JPanel{

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1;
    JTextField campoTexto1;
    JButton boton1;
    //JButton boton3;
    ImageIcon imagen;
    JTable tablaUbicacion;
    hUbicacion hubicacion;
    Vector vector,encabezadoUbicacions;
    DefaultTableModel dtm;

     public VAlta_Ubicacion()
    {
           setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());
     panelPrincipal.setBackground(Color.white);

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelEncabezado.setBackground(Color.white);

     etiqueta1 = new JLabel("Ubicación: ");
     campoTexto1 = new JTextField(20);


     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);



   //  boton3 = new JButton("Actualizar");
   //  boton3.setBackground(Color.white);

     panelEncabezado.add(etiqueta1);
     panelEncabezado.add(campoTexto1);


     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());

     tablaUbicacion = new JTable();
     encabezadoUbicacions = new Vector<String>();
     encabezadoUbicacions.add("Ubicacion");

     hubicacion = new hUbicacion();
     vector = new Vector();

      dtm = new DefaultTableModel(vector,encabezadoUbicacions);
      tablaUbicacion.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaUbicacion));

           boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                Ubicacion lin = new Ubicacion();
                lin.setUbicacion(campoTexto1.getText());
                hUbicacion hlin = new hUbicacion();
                hlin.guardarUbicacion(lin);
                List<Ubicacion> listalin = hlin.consultaUbicaciones("ubicacion","=",lin.getUbicacion());
                Vector<Object> unaFila = new Vector<Object>();
                unaFila.add(listalin.get(0).getUbicacion());
                vector.add(unaFila);
                dtm.fireTableRowsInserted(1, 1);
        tablaUbicacion.setModel(dtm);
        campoTexto1.setText("");
            }});
     panelEncabezado.add(boton1);


  /*   boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              int arreglo[] = tablaUbicacion.getSelectedRows();
            for(int i=0; i<arreglo.length; i++){
            Ubicacion linea = new Ubicacion();
            linea.setId(Integer.parseInt(tablaUbicacion.getValueAt(arreglo[i],0).toString()));
            linea.setNombre(tablaUbicacion.getValueAt(arreglo[i], 1).toString());
            hUbicacion.actualizarUbicacions(linea);
                   }
        dtm.setDataVector(vector,encabezadoUbicacions);
        tablaUbicacion.setModel(dtm);
            }
        }); */

  
//     panelEncabezado.add(boton3);

     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     add(panelPrincipal);

    }

}
