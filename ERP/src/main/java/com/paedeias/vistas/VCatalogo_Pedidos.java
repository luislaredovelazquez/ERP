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
import com.paedeias.helpers.hComprasMayoreo;
import com.paedeias.helpers.hPartidasComprasMayoreo;
import com.paedeias.identidades.ComprasMayoreo;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_Pedidos extends JPanel{
    
    hComprasMayoreo pedidos;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    public static JButton boton1;
    JButton botonAlta,botonEliminar,botonDevolver,botonRevisar;
    Vector vector;
    Vector<String> encabezado;
    List<ComprasMayoreo> lista;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagen3,imagenbuscar,imagenrevisar;
    DecimalFormat df;
    
    public VCatalogo_Pedidos()
    {
        df = new DecimalFormat("0.00");
           tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Devolución");
        encabezado.add("Compra");
        encabezado.add("Factura");
        encabezado.add("Proveedor");
        encabezado.add("Fecha");
        encabezado.add("Importe");
        encabezado.add("Tipo de Pago");
        encabezado.add("Dias de Crédito");
        encabezado.add("Observaciones");
        encabezado.add("Comprada");
        encabezado.add("TN");

        pedidos = new hComprasMayoreo();
        lista = new ArrayList<ComprasMayoreo>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              
                @Override
             public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 9) {
              return Boolean.class;
               }
              if (columnIndex == 0) {
              return Boolean.class;
               }
               return super.getColumnClass(columnIndex);
                }
              
              };
          
           
           tabla.setModel(dtm);
           tabla.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2)
            {
             VActualiza_Pedidos actualizapedidos = new VActualiza_Pedidos(lista.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent()).add(new JScrollPane(actualizapedidos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Pedidos",indice));  
            ((JTabbedPane)getParent()).setSelectedIndex(indice);
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
        botonAlta.setEnabled(CConfiguracion.isAltaPedidos());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

              VAlta_Pedidos altaPedidos = new VAlta_Pedidos();
                int indice = ((JTabbedPane)getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent()).add(new JScrollPane(altaPedidos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent()).setSelectedIndex(indice);
            }
        }); 

        leyenda1 = new JLabel("Buscar pedidos en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Devolución");
        combo1.addItem("Compra");
        combo1.addItem("Factura");
        combo1.addItem("Proveedor");
        combo1.addItem("Fecha");
        combo1.addItem("Importe");
        combo1.addItem("Tipo de Pago");
        combo1.addItem("Dias de Crédito");
        combo1.addItem("Observaciones");
        combo1.addItem("TN");
              

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");

        campoTexto1 = new JTextField(20);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setEnabled(CConfiguracion.isBuscarPedidos());
        boton1.setToolTipText("Buscar");
        
         boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                
        String valores [] = {"devolucion","id","factura","codigoProveedor",
        "fechaCompra","importe","tipoPago","diasCred","observacion","TEN"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         lista = pedidos.consultaComprasProv(campo,compara,condicion);
         vector = new Vector();

         
         for(Object o: lista){
             ComprasMayoreo iventas = (ComprasMayoreo)o;
             Vector<Object> unaFila = new Vector<Object>();             
             if(Integer.compare(iventas.getDevolucion(), 0)==0)
             unaFila.add(false);
             else
             unaFila.add(true);               
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getFactura());
             unaFila.add(iventas.getNombreProveedor());
             unaFila.add(iventas.getFechaCompra());
             unaFila.add(df.format(iventas.getImporte()));
             unaFila.add(iventas.getTipoPago());
             unaFila.add(iventas.getDiasCred());
             unaFila.add(iventas.getObservacion());
             if(Integer.compare(iventas.getCompra(), 0)==0)
             unaFila.add(false);
             else
             unaFila.add(true);   
             unaFila.add(iventas.getTn());
             vector.add(unaFila);             
         }       

            dtm.setDataVector(vector,encabezado);

           // tabla.setModel(dtm);

            }
        });
           
            
        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setToolTipText("Elimina la compra no vendida");
        botonEliminar.setEnabled(CConfiguracion.isEliminarPedidos());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
        
          if(tabla.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }       
                
          if(lista.get(tabla.getSelectedRow()).getCompra() == 1)
          {
             JOptionPane.showMessageDialog(null, "Este pedido ya fue comprado y no puede eliminarse"); 
             return; 
          }
          
         pedidos.borrarCompras("id", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId()));        
         hPartidasComprasMayoreo hpcm = new hPartidasComprasMayoreo();
         hpcm.borrarPCompras("compra", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId()));
         
         JOptionPane.showMessageDialog(null, "Eliminación efectuada"); 
         
            }
        });
        
        imagen3 = new ImageIcon(getClass().getResource("/mainicons/devolver.jpg"));
        botonDevolver = new JButton(imagen3);
        botonDevolver.setBackground(Color.white);
        botonDevolver.setToolTipText("Devolver");
        botonDevolver.setBorderPainted(false);
        botonDevolver.setEnabled(CConfiguracion.isCancelarPedidos());
        botonDevolver.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                   if (tabla.getSelectedRow() == -1)
               {
                   JOptionPane.showMessageDialog(null, "Por favor seleccione la compra que desea devolver");
                   return;
               }
                
               if(lista.get(tabla.getSelectedRow()).getCompra() == 0)
               {
                   JOptionPane.showMessageDialog(null, "Este pedido aún no ha sido comprado y por lo tanto no puede ser devuelto");
                   return;
               }
               
            VDevolver_Pedidos devolverpedidos = new VDevolver_Pedidos(lista.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent()).add(new JScrollPane(devolverpedidos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Devolver Compra",indice)); 
            ((JTabbedPane)getParent()).setSelectedIndex(indice);   
            }
        });
        
        imagenrevisar = new ImageIcon(getClass().getResource("/mainicons/revisar.jpg"));
        botonRevisar = new JButton(imagenrevisar);
        botonRevisar.setBackground(Color.white);
        botonRevisar.setToolTipText("Revisar");
        botonRevisar.setBorderPainted(false);
        botonRevisar.setEnabled(CConfiguracion.isVerCancelados());
        botonRevisar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                   if (tabla.getSelectedRow() == -1)
               {
                   JOptionPane.showMessageDialog(null, "Por favor seleccione la compra que desea visualizar");
                   return;
               }
                
               if(lista.get(tabla.getSelectedRow()).getCompra() == 0)
               {
                   JOptionPane.showMessageDialog(null, "Este pedido aún no ha sido comprado y por lo tanto no pueden visualizar devoluciones");
                   return;
               }
               
               if(lista.get(tabla.getSelectedRow()).getDevolucion() == 0)
               {
                   JOptionPane.showMessageDialog(null, "Este pedido aún no ha sido devuelto y por lo tanto no pueden visualizar devoluciones");
                   return;
               }               
               
            VConsultar_DevPedidos consultardevpedidos = new VConsultar_DevPedidos(lista.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent()).add(new JScrollPane(consultardevpedidos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Consulta Dev. Compra",indice)); 
            ((JTabbedPane)getParent()).setSelectedIndex(indice);   
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
        panelEncabezado.add(botonDevolver);
        panelEncabezado.add(botonRevisar);

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
           
    /*      if(CConfiguracion.getPuesto().equals("Almacén"))
        {
            botonEliminar.setEnabled(false);
        } else if(CConfiguracion.getPuesto().equals("Reservaciones"))
        {
            botonEliminar.setEnabled(false);
        } */
         
    }
    
}


