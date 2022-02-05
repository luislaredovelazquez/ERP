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
import com.paedeias.helpers.hPedidosVentas;
import com.paedeias.identidades.Pedidosventas;
import com.paedeias.helpers.hArticulos;
import com.paedeias.identidades.Articulos;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_PedidosVentas extends JPanel{
    
    hPedidosVentas pedidosventas;
    hArticulos articulos;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonEliminar;
    Vector vector;
    Vector<String> encabezado;
    List<Pedidosventas> lista;
    List<Articulos> lista2;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    
    public VCatalogo_PedidosVentas()
    {
        
           tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Id");
        encabezado.add("Código del Artículo");
        encabezado.add("Descripción");
        encabezado.add("Cantidad");
        encabezado.add("Precio Unitario");
        encabezado.add("Fecha Pedido");

        pedidosventas = new hPedidosVentas();
        articulos = new hArticulos();
        lista = new ArrayList<Pedidosventas>();
        lista2 = new ArrayList<Articulos>();
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

      

        leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Código del Artículo");
        combo1.addItem("Cantidad");
        combo1.addItem("Precio Unitario");
        combo1.addItem("Fecha Pedido");

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");

        campoTexto1 = new JTextField(20);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setEnabled(CConfiguracion.isVerBackOrder());
        boton1.setToolTipText("Buscar");
        
         boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
        String valores [] = {"id","codigoArticulo",
        "cantidad","precioUnitario","fecha"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        

         lista = pedidosventas.consultaPedidosVentas(campo,compara,condicion);
         vector = new Vector();

         for(Object o: lista){
             
             Pedidosventas iventas = (Pedidosventas)o;
             
             lista2 = articulos.consultaArticulos("codigo", "=", iventas.getCodigoArticulo());
             
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(lista2.get(0).getCodigo());
             unaFila.add(lista2.get(0).getDescripcion());
             unaFila.add(iventas.getCantidad());
             unaFila.add(iventas.getPrecioUnitario());
             unaFila.add(iventas.getFecha());

             vector.add(unaFila);
             lista2.clear();

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
        botonEliminar.setEnabled(CConfiguracion.isEliminarBackOrder());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar esta partida del backorder?");
        if(Integer.compare(confirmacion, JOptionPane.YES_OPTION)==0)
        {
        pedidosventas.borrarPedidosVentas("id", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId()));
        boton1.doClick();        
        }
            }
        });

        
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        panelEncabezado.add(botonEliminar);

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());


        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelTabla.setBackground(Color.white); 
        panelEncabezado.setBackground(Color.white);
        panelPrincipal.setBackground(Color.white);

        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });
        
        panelTabla.add(new JScrollPane(tabla));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
           
         
         
    }
    
}
