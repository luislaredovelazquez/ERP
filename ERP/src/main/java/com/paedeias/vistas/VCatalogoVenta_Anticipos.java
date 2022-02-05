/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */
import com.paedeias.helpers.hVentaAnticipos;
import com.paedeias.identidades.Ventaanticipos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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

public class VCatalogoVenta_Anticipos extends JPanel{
    
 
    hVentaAnticipos anticipos;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1;
    Vector vector;
    Vector<String> encabezado;
    List<Ventaanticipos> lista;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    DecimalFormat df;
    
     public VCatalogoVenta_Anticipos(){
     
      df = new DecimalFormat("0.00");   
      tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Id");
        encabezado.add("Anticipo");
        encabezado.add("Subtotal");
        encabezado.add("IVA");
        encabezado.add("Devolución");
        encabezado.add("Fecha");
        encabezado.add("Total");
        encabezado.add("Del Anticipo");
        

        anticipos = new hVentaAnticipos();
        lista = new ArrayList<Ventaanticipos>();
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
                
            VDetalle_VentasAnticipos actualizaVentas = new VDetalle_VentasAnticipos(lista.get(tabla.getSelectedRow()));
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
        
        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());

       
        leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Importe");
        combo1.addItem("Subtotal");
        combo1.addItem("Devolución");
        combo1.addItem("Fecha");
        combo1.addItem("Total");
        combo1.addItem("Remisión");
        combo1.addItem("Anticipo");

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
                
        String valores [] = {"id","importe","subtotal","devolucion",
        "fecha","total","remision","anticipo"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         lista = anticipos.consultaVentas(campo,compara,condicion);
         vector = new Vector();

         for(Object o: lista){
             Ventaanticipos iventas = (Ventaanticipos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getTipoDeVenta());
             unaFila.add(df.format(iventas.getSubtotal()));
             unaFila.add(df.format(iventas.getIva()));
             unaFila.add(iventas.getEstado());
             unaFila.add(iventas.getFechaVenta());
             unaFila.add(df.format(iventas.getTotal()));
             unaFila.add(iventas.getVale());
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
        
        

        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        

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
        
        panelTabla.setBackground(Color.white); 
        panelEncabezado.setBackground(Color.white); 
        panelPrincipal.setBackground(Color.white);

        panelTabla.add(new JScrollPane(tabla));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
         
         
     }
}
