/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.controladores.CConfiguracion;
import com.paedeias.helpers.hDevoluciones;
import com.paedeias.identidades.Devoluciones;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ALL
 */
public class VCatalogo_Devoluciones extends JPanel{
   
        JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1;
    JButton botonBuscar;
    //JButton boton3;
    JComboBox combo1,combo2;
    ImageIcon imagen,imagen2,imagenbuscar;
    JTable tablaDevoluciones;
    hDevoluciones hdevoluciones;
    Vector vector,encabezadoDevoluciones;
    DefaultTableModel dtm;
    List<Devoluciones> listaDevoluciones;
    
    public  VCatalogo_Devoluciones(){
     
     hdevoluciones = new hDevoluciones();  
     listaDevoluciones = new ArrayList<Devoluciones>();
        
     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());

     etiqueta1 = new JLabel("Buscar Devoluciones donde: ");

     combo1 = new JComboBox();
     combo1.addItem("Id");
     combo1.addItem("Código de Venta");
     combo1.addItem("Número de Artículos");
     combo1.addItem("Fecha de Devolución");
     combo1.addItem("Subtotal");
     combo1.addItem("Total");

     etiqueta2 = new JLabel("sea ");

     combo2 = new JComboBox();
     combo2.addItem("igual a");
     combo2.addItem("como");
     combo2.addItem("Todos");

     campoTexto1 = new JTextField(20);

   

    
     
     imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
     botonBuscar = new JButton(imagenbuscar);
     botonBuscar.setToolTipText("Buscar");
     botonBuscar.setBackground(Color.white);
     botonBuscar.setBorderPainted(false);
     botonBuscar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

        String valores [] = {"id","codigoVenta","articulos","fechaVenta","subtotal","total"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

                listaDevoluciones = hdevoluciones.consultaVentasCatalogo(campo,compara,condicion);
                vector = new Vector();

       for(Object o: listaDevoluciones){
             Devoluciones lin = (Devoluciones)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getId());
             unaFila.add(lin.getCodigoVenta());
             unaFila.add(lin.getArticulos());
             unaFila.add(lin.getFechaVenta());
             unaFila.add(lin.getSubtotal());
             unaFila.add(lin.getTotal());
             vector.add(unaFila);

         }
       dtm = new DefaultTableModel(vector,encabezadoDevoluciones) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaDevoluciones.setModel(dtm);     
            }
     });

          panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());

     tablaDevoluciones = new JTable();
     encabezadoDevoluciones = new Vector<String>();
 
        encabezadoDevoluciones.add("Número de Devolución");
        encabezadoDevoluciones.add("Código de Venta");
        encabezadoDevoluciones.add("Artículos Devueltos");
        encabezadoDevoluciones.add("Fecha de Devolución");
        encabezadoDevoluciones.add("Subtotal");
        encabezadoDevoluciones.add("Total");

     
     vector = new Vector();

     dtm = new DefaultTableModel(vector,encabezadoDevoluciones) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

     

            tablaDevoluciones.setModel(dtm);
            tablaDevoluciones.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                {

              /*      JFrame frame = new JFrame();
                    frame.add(actualiza);
                    frame.setSize(741,582);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true); */

                 //  System.out.println(getParent().getName());
                 //  ((JTabbedPane)getParent()).addTab("Actualiza",actualiza);
                     // tablaDevoluciones.getSelectedRow();
                    //        ((JTabbedPane)getParent()).insertTab("Actualiza",null, new JScrollPane(actualiza), "Actualiza Proveedores", indice);
                     Devoluciones devolucion = listaDevoluciones.get(tablaDevoluciones.getSelectedRow());
                     VDetalle_Devolucion detalle = new VDetalle_Devolucion(devolucion);

                     int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                     indice +=1;
             
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(detalle), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle Devolución",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);
                }
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                return;
            }

            public void mouseEntered(MouseEvent e) {
               return;
            }

            public void mouseExited(MouseEvent e) {
               return;
            }

            });
    
     panelTabla.add(new JScrollPane(tablaDevoluciones));

     
     panelEncabezado.add(etiqueta1);
     panelEncabezado.add(combo1);
     panelEncabezado.add(etiqueta2);
     panelEncabezado.add(combo2);
     panelEncabezado.add(campoTexto1);
     panelEncabezado.add(botonBuscar);
     
     
     campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                botonBuscar.doClick();
            }
        });

     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     
     panelPrincipal.setBackground(Color.white); 
     panelEncabezado.setBackground(Color.white); 
     panelTabla.setBackground(Color.white);
     
     add(panelPrincipal);
            
            
    }
    
    
    
}
