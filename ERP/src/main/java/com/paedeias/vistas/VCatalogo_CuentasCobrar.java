/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */
import com.paedeias.helpers.hCuentasPorCobrar;
import com.paedeias.identidades.Cuentasporcobrar;
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


public class VCatalogo_CuentasCobrar extends JPanel{
    
    hCuentasPorCobrar cuentasporcobrar;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar;
    Vector vector;
    Vector<String> encabezado;
    List<Cuentasporcobrar> lista;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    
    public VCatalogo_CuentasCobrar(){
    
       tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Id");
        encabezado.add("Número de Venta");
        encabezado.add("Factura");
        encabezado.add("Fecha de Vencimiento");
        encabezado.add("Observación");
        encabezado.add("Saldo");
        

        cuentasporcobrar = new hCuentasPorCobrar();
        lista = new ArrayList<Cuentasporcobrar>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
          
           
           tabla.setModel(dtm);
           tabla.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2)
            {
            /* VActualizarAnticipo actualizaanticipos = new VActualizarAnticipo(lista.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent()).add(new JScrollPane(actualizaanticipos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Anticipos",indice));  */
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
           
              panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
           /* VRealizarAnticipo altaAnticipos = new VRealizarAnticipo();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaAnticipos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Realizar Anticipo",indice)); */
            }
        });
        
        leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Número de Venta");
        combo1.addItem("Factura");
        combo1.addItem("Fecha de Vencimiento");
        combo1.addItem("Observación");
        combo1.addItem("Saldo");
        

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
                
        String valores [] = {"id","venta","fechaVencimiento","factura","observacion",
        "saldo"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         lista = cuentasporcobrar.consultaCtaPorCobrar(campo,compara,condicion);
         vector = new Vector();

         for(Object o: lista){
             Cuentasporcobrar iventas = (Cuentasporcobrar)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getVenta());
             unaFila.add(iventas.getFactura());
             unaFila.add(iventas.getFechaVencimiento());
             unaFila.add(iventas.getObservacion());
             unaFila.add(iventas.getSaldo());
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tabla.setModel(dtm);

            }
        });
        
        
        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
     /*           int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
             ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaVentas), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Realizar Venta",indice)); */
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

        panelTabla.add(new JScrollPane(tabla));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
        
        
    }
}
