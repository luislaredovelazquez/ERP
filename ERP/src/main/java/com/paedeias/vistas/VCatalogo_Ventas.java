/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */

import com.paedeias.controladores.CConfiguracion;
import com.paedeias.helpers.hVentas;
import com.paedeias.identidades.Ventas;
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
import javax.swing.table.DefaultTableModel;


public class VCatalogo_Ventas extends JPanel {
    
    hVentas ventas;
    JTable tablaVentas;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar;
    Vector vector;
     Vector<String> encabezadoVentas;
     List<Ventas> listaVentas;
     DefaultTableModel dtm;
      ImageIcon imagen,imagen2,imagenbuscar;
    
      public VCatalogo_Ventas(){
      
        tablaVentas = new JTable();

        encabezadoVentas = new Vector<String>();
        encabezadoVentas.add("Id");
        encabezadoVentas.add("Número de Artículos");
        encabezadoVentas.add("Fecha de Venta");
        encabezadoVentas.add("Tipo de Venta");
        encabezadoVentas.add("Id Cliente");
        encabezadoVentas.add("Subtotal");
        encabezadoVentas.add("Total");

        ventas = new hVentas();
        listaVentas = new ArrayList<Ventas>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoVentas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
          
           
           tablaVentas.setModel(dtm);
           tablaVentas.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
               //  botonActualizar.setEnabled(true);
            if(e.getClickCount() == 2)
            {
            VDetalle_Ventas actualizaVentas = new VDetalle_Ventas(listaVentas.get(tablaVentas.getSelectedRow()));
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            // listaVentas.get(tablaVentas.getSelectedRow())
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualizaVentas), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Ventas",indice));
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

        ///
           
           
        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());
        panelEncabezado.setBackground(Color.white);

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaVentas());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               VCapturar_Venta altaVentas = new VCapturar_Venta();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaVentas), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Capturar Venta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }

        });

    leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Número de Artículos");
        combo1.addItem("Fecha de Venta");
        combo1.addItem("Tipo de Venta");
        combo1.addItem("Id Cliente");
        combo1.addItem("Subtotal");
        combo1.addItem("Total");
        combo1.addItem("Reservaciones");
        combo1.addItem("Efectivo");
        combo1.addItem("Crédito");
        combo1.addItem("Efectivo/Crédito");

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");

        campoTexto1 = new JTextField(20);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setToolTipText("Buscar");
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

    //    Vector<String> encabezadoArticulos = new Vector<String>();
        String valores [] = {"id","articulos","fechaVenta","tipoDeVenta",
        "idCliente","subtotal","total","reservaciones","efectivo","credito","crefe"};
        String comparadores [] = {"=","LIKE","*"};

     /*   encabezadoArticulos.add("Id");
        encabezadoArticulos.add("Código");
        encabezadoArticulos.add("Descripción");
        encabezadoArticulos.add("Venta");
        encabezadoArticulos.add("Compra");
        encabezadoArticulos.add("Existencia");
        encabezadoArticulos.add("Reservado"); */

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
        if(campo.equals("reservaciones"))
        listaVentas = ventas.consultaVentasReservaciones(campo,compara,condicion);    
        else if(campo.equals("efectivo"))
        listaVentas = ventas.consultaVentasEfectivo(campo,compara,condicion);    
        else if(campo.equals("credito"))    
        listaVentas = ventas.consultaVentasCredito(campo,compara,condicion);
        else if(campo.equals("crefe"))        
        listaVentas = ventas.consultaVentasEfeCre(campo,compara,condicion);
        else
       listaVentas = ventas.consultaVentas(campo,compara,condicion);
        
         vector = new Vector();

         for(Object o: listaVentas){
             Ventas iventas = (Ventas)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getArticulos());
             unaFila.add(iventas.getFechaVenta());
             unaFila.add(iventas.getTipoDeVenta());
             unaFila.add(iventas.getIdcliente());
             unaFila.add(iventas.getSubtotal());
             unaFila.add(iventas.getTotal());
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezadoVentas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaVentas.setModel(dtm);

            }
        });
        
        
           imagen2 = new ImageIcon(getClass().getResource("/mainicons/reimpresiones.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isReimpresiones());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            //        VRealizar_Venta altaVentas = new VRealizar_Venta();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
     //           int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
     //           indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
     //        ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaVentas), indice);
     //       ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Realizar Venta",indice));

            VCatalogoReimpresiones reimpresiones = new VCatalogoReimpresiones();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            //((JTabbedPane)getParent()).add(new JScrollPane(vrealizarventa), indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(reimpresiones, indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Reimpresiones",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);
                
            }


        });
        
     panelEncabezado.add(botonAlta);
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        panelEncabezado.add(botonEliminar);

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());

        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });

        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelTabla.add(new JScrollPane(tablaVentas));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
       

      
      }
      
}
