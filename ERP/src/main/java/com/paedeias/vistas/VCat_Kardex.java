/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */
import com.paedeias.helpers.hArticulos;
import com.paedeias.helpers.hKardex;
import com.paedeias.identidades.Articulos;
import com.paedeias.identidades.Kardex;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class VCat_Kardex extends JPanel{
    
    hKardex kardex;
    hArticulos articulos;
    List<Articulos> listaArticulos;
    JTable tablaKardex,tablaArticulos;
    JPanel panelTabla,panelEncabezadoPrincipal,panelEncabezado,panelInfo,panelPrincipal,panelPie;
    JLabel leyenda1,leyenda2,leyenda4,leyenda5,leyenda6,leyenda7,leyenda8;
    JCheckBox leyenda3;
    JComboBox combo1,combo2;
    JTextField campoTexto1,campoTexto2,campoTexto3,campoTexto4;
    JButton boton1;
    Vector vector,vector2;
    Vector<String> encabezadoKardex,encabezadoArticulos;
    List<Kardex> listaKardex;
    DefaultTableModel dtm,dtm2;
    ImageIcon imagen,imagen2,imagenbuscar;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    DecimalFormat df;
      
    public VCat_Kardex()
    {
            ///
         df = new DecimalFormat("0.00");
         articulos = new hArticulos();
         listaArticulos = new ArrayList<Articulos>();
        
          tablaKardex = new JTable();
          tablaArticulos = new JTable();
          
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();

        encabezadoKardex = new Vector<String>();
        encabezadoArticulos = new Vector<String>();
        encabezadoKardex.add("Id");
        encabezadoKardex.add("Artículo");
        encabezadoKardex.add("Movimiento");
        encabezadoKardex.add("Referencia");
        encabezadoKardex.add("Entrada");
        encabezadoKardex.add("Salida");
        encabezadoKardex.add("Reservados");
        encabezadoKardex.add("En Anticipo");
        encabezadoKardex.add("Existencias");
        encabezadoKardex.add("Ultimo Costo");
        encabezadoKardex.add("Precio Venta");
        encabezadoKardex.add("Vendido En");
        encabezadoKardex.add("Fecha");
        encabezadoKardex.add("Responsable");
        encabezadoKardex.add("Administrador");
        encabezadoArticulos.add("Código");
        encabezadoArticulos.add("Descripción");
        
        kardex = new hKardex();
        listaKardex = new ArrayList<Kardex>();
         vector = new Vector();
         vector2 = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoKardex) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
           
           dtm2 = new DefaultTableModel(vector2,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
           
           tablaArticulos.setModel(dtm2);

           tablaKardex.setModel(dtm);
           
         tablaKardex.getColumnModel().getColumn(0).setPreferredWidth(27);  
         tablaKardex.getColumnModel().getColumn(4).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(5).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(6).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(7).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(8).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(9).setPreferredWidth(50);  
         tablaKardex.getColumnModel().getColumn(10).setPreferredWidth(50);  
         tablaKardex.getColumnModel().getColumn(11).setPreferredWidth(50);  
           
         panelEncabezado = new JPanel();
         panelEncabezado.setLayout(new FlowLayout());
         
         panelEncabezadoPrincipal = new JPanel();
         panelEncabezadoPrincipal.setLayout(new GridLayout(2,1));
         campoTexto3 = new JTextField(10);
         campoTexto4 = new JTextField(40);
         campoTexto3.setEditable(false);
         campoTexto4.setEditable(false);
         leyenda5 = new JLabel("Código");
         leyenda5.setForeground(new java.awt.Color(102,102,102));
         leyenda5.setFont(new java.awt.Font("Tahoma", 1, 11));
         
         leyenda6 = new JLabel("Descripción");
         leyenda6.setForeground(new java.awt.Color(102,102,102));
         leyenda6.setFont(new java.awt.Font("Tahoma", 1, 11));
         
         panelInfo = new JPanel();
         panelInfo.setLayout(new FlowLayout(FlowLayout.RIGHT));
         
         panelInfo.add(leyenda5);
         panelInfo.add(campoTexto3);
         panelInfo.add(leyenda6);
         panelInfo.add(campoTexto4);
         
        leyenda1 = new JLabel("Buscar:");
        
        combo1 = new JComboBox();
        combo1.addItem("Código");
        combo1.addItem("Descripción");

        leyenda2 = new JLabel("sea");
        
        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        
        
        
        campoTexto1 = new JTextField(15);
        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
              boton1.doClick();
            }
        
        });
        
        leyenda3 = new JCheckBox("Limite:");
        leyenda3.setBackground(Color.white);
        leyenda3.setSelected(true);
        
        campoTexto2 = new JTextField(4);
        campoTexto2.setText("10");
        
        leyenda4 = new JLabel(" Resultados");
        
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setToolTipText("Buscar");
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

    //    Vector<String> encabezadoArticulos = new Vector<String>();
        String valores [] = {"articulo","descripcion"};
        String comparadores [] = {"=","LIKE"};

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
        String limite = campoTexto2.getText();
        
        if(campo.isEmpty() || compara.isEmpty() || condicion.isEmpty() || limite.isEmpty())
            {
            JOptionPane.showMessageDialog(null, "Por favor verifica que todos los campos de búsqueda se encuentren llenados");
            return;
            }
        
        if(campo.equalsIgnoreCase("descripcion"))
        {

            if(!listaArticulos.isEmpty())
            listaArticulos.clear();
            listaArticulos = articulos.consultaArticulos(campo,compara,condicion);
            vector2.clear();

         for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             unaFila.add(iarticulos.getDescripcion());
             vector2.add(unaFila); 
         }
            dtm2.fireTableDataChanged();
            panelPie.setVisible(true);
            return;
        }
        
         if(compara.equalsIgnoreCase("LIKE"))
        {

            if(!listaArticulos.isEmpty())
            listaArticulos.clear();
            listaArticulos = articulos.consultaArticulos("codigo",compara,condicion);
            vector2.clear();

         for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             unaFila.add(iarticulos.getDescripcion());
             vector2.add(unaFila); 
         }
            dtm2.fireTableDataChanged();
            panelPie.setVisible(true);
            return;
        }
        
                        //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT = "dd/MM/yy";
                     final String NEW_FORMAT = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString = dateChooserCombo1.getText();
                     String newDateString;

                     SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                     Date d = null;
                      try {
                     d = sdf.parse(oldDateString);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCat_Kardex.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf.applyPattern(NEW_FORMAT);
                     newDateString = sdf.format(d);
                     
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT2 = "dd/MM/yy";
                     final String NEW_FORMAT2 = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString2 = dateChooserCombo2.getText();
                     String newDateString2;

                     SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT2);
                     Date d2 = null;
                      try {
                     d2 = sdf2.parse(oldDateString2);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCat_Kardex.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf2.applyPattern(NEW_FORMAT2);
                     newDateString2 = sdf.format(d2);
        
        
        
          if(leyenda3.isSelected())
         listaKardex = kardex.consultaKardex(campo,compara,condicion,limite);
          else
         listaKardex = kardex.consultaKardexFecha(campo,compara,condicion,newDateString,newDateString2);     
          
         vector = new Vector();

         for(Object o: listaKardex){
             Kardex ikardex = (Kardex)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(ikardex.getId());
             unaFila.add(ikardex.getArticulo());
             unaFila.add(ikardex.getMovimiento());
             unaFila.add(ikardex.getRefFerrari());
             unaFila.add(ikardex.getEntrada());
             unaFila.add(ikardex.getSalida());
             unaFila.add(ikardex.getReservados());
             unaFila.add(ikardex.getAnticipos());
             unaFila.add(ikardex.getExistencias());
             unaFila.add(df.format(ikardex.getUltimoCosto()));
             unaFila.add(df.format(ikardex.getPrecioVenta()));
             unaFila.add(ikardex.getVendidoEn());
             unaFila.add(ikardex.getFecha());
             unaFila.add(ikardex.getNombreCliente());
             unaFila.add(ikardex.getResponsable2());
             
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezadoKardex) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaKardex.setModel(dtm);
         tablaKardex.getColumnModel().getColumn(0).setPreferredWidth(27);  
         tablaKardex.getColumnModel().getColumn(4).setPreferredWidth(40);
         tablaKardex.getColumnModel().getColumn(4).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(5).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(6).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(7).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(8).setPreferredWidth(20);
         tablaKardex.getColumnModel().getColumn(9).setPreferredWidth(50);  
         tablaKardex.getColumnModel().getColumn(10).setPreferredWidth(50);  
         tablaKardex.getColumnModel().getColumn(11).setPreferredWidth(50);  
         
         if(!listaKardex.isEmpty())
         {
         tablaKardex.getSelectionModel().setSelectionInterval(0, 0);
         campoTexto3.setText(listaKardex.get(0).getArticulo());
         hArticulos harticulos = new hArticulos();
         List<Articulos> larticulos = harticulos.consultaArticulos("codigo", "=", listaKardex.get(tablaKardex.getSelectedRow()).getArticulo());
         if(!larticulos.isEmpty())
         campoTexto4.setText(larticulos.get(0).getDescripcion());
         }
         
         
            }
        });
        
        tablaArticulos.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                {
                    //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT = "dd/MM/yy";
                     final String NEW_FORMAT = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString = dateChooserCombo1.getText();
                     String newDateString;

                     SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                     Date d = null;
                      try {
                     d = sdf.parse(oldDateString);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf.applyPattern(NEW_FORMAT);
                     newDateString = sdf.format(d);
                     
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT2 = "dd/MM/yy";
                     final String NEW_FORMAT2 = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString2 = dateChooserCombo2.getText();
                     String newDateString2;

                     SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT2);
                     Date d2 = null;
                      try {
                     d2 = sdf2.parse(oldDateString2);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf2.applyPattern(NEW_FORMAT2);
                     newDateString2 = sdf.format(d2);     
                     
        vector.clear();
        if(!listaKardex.isEmpty())
        listaKardex.clear();
        
         if(leyenda3.isSelected())
         listaKardex = kardex.consultaKardex("articulo","=",listaArticulos.get(tablaArticulos.getSelectedRow()).getCodigo(),campoTexto2.getText());
          else
         listaKardex = kardex.consultaKardexFecha("articulo","=",listaArticulos.get(tablaArticulos.getSelectedRow()).getCodigo(),newDateString,newDateString2);     
          
          for(Object o: listaKardex){
             Kardex ikardex = (Kardex)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(ikardex.getId());
             unaFila.add(ikardex.getArticulo());
             unaFila.add(ikardex.getMovimiento());
             unaFila.add(ikardex.getRefFerrari());
             unaFila.add(ikardex.getEntrada());
             unaFila.add(ikardex.getSalida());
             unaFila.add(ikardex.getReservados());
             unaFila.add(ikardex.getAnticipos());
             unaFila.add(ikardex.getExistencias());
             unaFila.add(ikardex.getUltimoCosto());
             unaFila.add(ikardex.getPrecioVenta());
             unaFila.add(ikardex.getVendidoEn());
             unaFila.add(ikardex.getFecha());
             unaFila.add(ikardex.getNombreCliente());
             unaFila.add(ikardex.getResponsable2());
             
             vector.add(unaFila);

         }
                    dtm.fireTableDataChanged();
                    panelPie.setVisible(false);
                }
                
         if(!listaKardex.isEmpty())
         {
         tablaKardex.getSelectionModel().setSelectionInterval(0, 0);
         campoTexto3.setText(listaArticulos.get(tablaArticulos.getSelectedRow()).getCodigo());
         campoTexto4.setText(listaArticulos.get(tablaArticulos.getSelectedRow()).getDescripcion());
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
        
        
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        leyenda7 = new JLabel("De:");
        panelEncabezado.add(leyenda7);
        panelEncabezado.add(dateChooserCombo1);
        leyenda8 = new JLabel("Hasta:");
        panelEncabezado.add(leyenda8);
        panelEncabezado.add(dateChooserCombo2);        
        panelEncabezado.add(leyenda3);
        panelEncabezado.add(campoTexto2);
        panelEncabezado.add(boton1);
        
        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());


        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

      
           
           panelPie = new JPanel();
           panelPie.setLayout(new GridLayout());

           panelTabla.add(new JScrollPane(tablaKardex));
           panelPie.add(new JScrollPane(tablaArticulos));
           panelEncabezadoPrincipal.add(panelEncabezado);
           panelEncabezadoPrincipal.add(panelInfo);
           panelPrincipal.add(panelEncabezadoPrincipal,BorderLayout.NORTH);
           panelPrincipal.add(panelTabla,BorderLayout.CENTER);
           panelPie.setVisible(false);
           panelPrincipal.add(panelPie,BorderLayout.SOUTH);
           add(panelPrincipal);
           
         tablaKardex.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                panelPie.setVisible(false);
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                
                
            int r = tablaKardex.rowAtPoint(e.getPoint());
            if (r >= 0 && r < tablaKardex.getRowCount()) {
                tablaKardex.setRowSelectionInterval(r, r);
            } else {
                tablaKardex.clearSelection();
            }

            int rowindex = tablaKardex.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem item = new JMenuItem("Datos Artículo");
                item.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
               hArticulos harticulos = new hArticulos();
               List<Articulos> larticulos = harticulos.consultaArticulos("codigo", "=", listaKardex.get(tablaKardex.getSelectedRow()).getArticulo());
               JOptionPane.showMessageDialog(null, larticulos.get(0).getDescripcion());
                        }
                
                });
                
                JMenuItem item2 = new JMenuItem("Modificación");
                item2.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
                 if(tablaKardex.getSelectedRow()==-1)   
                     return;
                 JOptionPane.showMessageDialog(null, listaKardex.get(tablaKardex.getSelectedRow()).getModificacion());   
                        }
                
                });
                
                
                
                
                
                
                
                popup.add(item);
                popup.add(item2);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
            
            
            }

            public void mouseEntered(MouseEvent e) {
                return;
            }

            public void mouseExited(MouseEvent e) {
                return;
            }
         
         });
         
         panelTabla.setBackground(Color.white); 
         panelEncabezadoPrincipal.setBackground(Color.white);
         panelEncabezado.setBackground(Color.white);
         panelInfo.setBackground(Color.white);
         panelPrincipal.setBackground(Color.white);
         panelPie.setBackground(Color.white);



        setPreferredSize(new Dimension(865, 546));
        setBackground(Color.white);
    
    }
    
}
