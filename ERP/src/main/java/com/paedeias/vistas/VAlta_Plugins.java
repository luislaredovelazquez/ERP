/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.helpers.hPlugins;
import com.paedeias.identidades.Plugins;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class VAlta_Plugins extends JPanel{
    
    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1,campoTexto2;
    JButton boton1;
    //JButton boton3;
    ImageIcon imagen;
    JTable tablaPlugins;
    hPlugins hplugins;
    Vector vector,encabezadoPlugins;
    DefaultTableModel dtm;

    public VAlta_Plugins()
    {
     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());

     etiqueta1 = new JLabel("Plugin: ");
     campoTexto1 = new JTextField(20);

     etiqueta2 = new JLabel("Permisos: ");
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

     tablaPlugins = new JTable();
     encabezadoPlugins = new Vector<String>();
     encabezadoPlugins.add("Plugin");
     encabezadoPlugins.add("Permisos");

     hplugins = new hPlugins();
     vector = new Vector();
        
      dtm = new DefaultTableModel(vector,encabezadoPlugins);
      tablaPlugins.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaPlugins));

           boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                Plugins plug = new Plugins();
                plug.setIdPlugin(campoTexto1.getText());
                plug.setPermisosPlugin(campoTexto2.getText());
                hPlugins hplug = new hPlugins();
                hplug.guardarPlugin(plug);
                List<Plugins> listaplug = hplug.consultaPlugins("idPlugin","=",plug.getIdPlugin());
                Vector<Object> unaFila = new Vector<Object>();
                unaFila.add(listaplug.get(0).getIdPlugin());
                unaFila.add(listaplug.get(0).getPermisosPlugin());
                vector.add(unaFila);
                dtm.fireTableRowsInserted(1, 1);
        tablaPlugins.setModel(dtm);
        campoTexto1.setText("");
        campoTexto2.setText("");
        VCatalogo_Plugins.botonBuscar.doClick();

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
     
     panelPrincipal.setBackground(Color.white); 
     panelEncabezado.setBackground(Color.white); 
     panelTabla.setBackground(Color.white);

     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     add(panelPrincipal);

    }

}
