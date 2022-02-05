/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

/**
 *
 * @author Luis
 */
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.Validadores;
import com.paedeias.helpers.hArticulosEnMostrador;
import com.paedeias.helpers.hKardex;
import com.paedeias.helpers.hUsuarios;
import com.paedeias.identidades.ArticulosEnMostrador;
import com.paedeias.identidades.Kardex;
import com.paedeias.identidades.Usuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class VCatalogoArticulosEnMostrador extends JPanel{
    hArticulosEnMostrador harticulosenmostrador;
    JTable tablaArtMos;
    Kardex kardex,renglon;
    hKardex hkardex;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar;
    Vector<String> encabezadoArtMos;
    DefaultTableModel dtm;
    Vector vector;
    List<ArticulosEnMostrador> listaArMos;
    ImageIcon imagen,imagen2,imagenbuscar;
    hUsuarios husuarios;
    List<Usuarios>listaUsu;

    public VCatalogoArticulosEnMostrador()
    {
        hkardex = new hKardex();
        kardex = new Kardex();
        renglon = new Kardex();
        tablaArtMos = new JTable();
        listaUsu = new ArrayList<Usuarios>();
        husuarios = new hUsuarios();
        encabezadoArtMos = new Vector<String>();
        encabezadoArtMos.add("Código");
        encabezadoArtMos.add("Descripcion");
        encabezadoArtMos.add("Cantidad");
        encabezadoArtMos.add("Responsable");
        encabezadoArtMos.add("Fecha");

        listaUsu = husuarios.consultaUsuarios("", "*", "");

         harticulosenmostrador = new hArticulosEnMostrador();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoArtMos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

            tablaArtMos.setModel(dtm);
            tablaArtMos.setCellSelectionEnabled(true);
            

        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaClientes());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               AltaArticulosMostrador altaArt = new AltaArticulosMostrador();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
                
            ((JTabbedPane)getParent()).add(new JScrollPane(altaArt), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent()).setSelectedIndex(indice);

            }

        });

        leyenda1 = new JLabel("Buscar articulos en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Código");
        combo1.addItem("Descripción");
        combo1.addItem("Cantidad");
        combo1.addItem("Responsable");

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");

        campoTexto1 = new JTextField(20);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setToolTipText("Buscar");
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setEnabled(CConfiguracion.isBuscarClientes());
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

        String valores [] = {"codigo","descripcion","cantidad",
        "responsable"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

         if(combo1.getSelectedItem().toString().equals("Todos"));
         listaArMos = harticulosenmostrador.consultaArticulosEnMostrador(campo,compara,condicion);
         vector.removeAllElements();

         for(Object o: listaArMos){
             ArticulosEnMostrador art = (ArticulosEnMostrador)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(String.valueOf(art.getCodigo()));
             unaFila.add(art.getDescripcion());
             unaFila.add(art.getCantidad());
             for(int u=0; u < listaUsu.size(); u++)
             {
             if(listaUsu.get(u).getId() == art.getResponsable())    
             {    
             unaFila.add(listaUsu.get(u).getNombres()+" "+listaUsu.get(u).getApellidoP()+" "+listaUsu.get(u).getApellidoM());
             u = listaUsu.size();
             }
             }
             unaFila.add(art.getFecha());
             vector.add(unaFila);

         }
          /* DefaultTableModel dtm = new DefaultTableModel(vector,encabezadoProveedores) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              }; */

                 dtm.fireTableDataChanged();
                 tablaArtMos.setModel(dtm);


            }
        });

                tablaArtMos.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e) {
                System.out.println("Cambio");
            }

        }
                );


        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEliminarArtMostrador());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
             if(tablaArtMos.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }     

           int respuesta = JOptionPane.showConfirmDialog((JFrame)Window.getWindows()[0],
                   "¿Estas seguro de que deseas cambiar de lugar este elemento?", "Eliminar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

           if(respuesta == JOptionPane.YES_OPTION)
           {
               
           String cantidad = JOptionPane.showInputDialog(null, "¿Qué cantidad deseas sacar de mostrador?", "Escribe una cantidad", JOptionPane.QUESTION_MESSAGE);    
           boolean bandera = Validadores.validarEntero("cantidad", cantidad);
           if(!bandera)
               return;
           
           int cantidadEntera = Integer.valueOf(cantidad);
           
           if(cantidadEntera > listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad())
           {
            JOptionPane.showMessageDialog(null, "La cantidad seleccionada es mayor a la cantidad en mostrador", "Error", JOptionPane.ERROR_MESSAGE);
            return;   
           }else if(cantidadEntera == listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad())
           {

           harticulosenmostrador.borrarArticulosEnMostrador("id", "=", String.valueOf(listaArMos.get(tablaArtMos.getSelectedRow()).getId()));
        
        renglon = hkardex.consultaUltimoRenglon("articulo", "=", listaArMos.get(tablaArtMos.getSelectedRow()).getCodigo()); 
        
        kardex.setAlmacenista(" ");
        kardex.setAnticipos(renglon.getAnticipos());
        kardex.setArticulo(renglon.getArticulo());
        kardex.setEntrada(0);
        kardex.setExistencias(renglon.getExistencias()); 
        kardex.setIdArticulo(renglon.getIdArticulo());
        kardex.setModificacion("Eliminado de Mostrador /"+listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad() + " PZA");
        kardex.setMovimiento("Eliminado de Mostrador /"+listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad() + " PZA");
        kardex.setNoMov(String.valueOf(Integer.valueOf(renglon.getNoMov())+1));
        kardex.setPrecioVenta(renglon.getPrecioVenta());
        kardex.setRefFerrari("Elm. Mostrador");
        kardex.setReservados(renglon.getReservados());
        kardex.setResponsable(String.valueOf(CConfiguracion.getId()));
        kardex.setResponsable2(CConfiguracion.getCorreo());
        kardex.setSalida(0);
        kardex.setUltimoCosto(renglon.getUltimoCosto());
        kardex.setVendidoEn(0);
        hkardex.guardarEnKardex(kardex);

           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);

           listaArMos.remove(tablaArtMos.getSelectedRow());
           dtm.removeRow(tablaArtMos.getSelectedRow());
           dtm.fireTableDataChanged();
           }else if(cantidadEntera < listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad() && cantidadEntera > 0)
           {
               
           int diferenciaEntera = listaArMos.get(tablaArtMos.getSelectedRow()).getCantidad() - cantidadEntera;
           String diferencia = String.valueOf(diferenciaEntera);
               
           harticulosenmostrador.actualizarCantidad(diferencia, String.valueOf(listaArMos.get(tablaArtMos.getSelectedRow()).getId()));
        
        renglon = hkardex.consultaUltimoRenglon("articulo", "=", listaArMos.get(tablaArtMos.getSelectedRow()).getCodigo()); 
        
        kardex.setAlmacenista(" ");
        kardex.setAnticipos(renglon.getAnticipos());
        kardex.setArticulo(renglon.getArticulo());
        kardex.setEntrada(0);
        kardex.setExistencias(renglon.getExistencias()); 
        kardex.setIdArticulo(renglon.getIdArticulo());
        kardex.setModificacion("Salida de Mostrador /"+cantidad + " PZA");
        kardex.setMovimiento("Salida de Mostrador /"+cantidad + " PZA");
        kardex.setNoMov(String.valueOf(Integer.valueOf(renglon.getNoMov())+1));
        kardex.setPrecioVenta(renglon.getPrecioVenta());
        kardex.setRefFerrari("Sal. Mostrador");
        kardex.setReservados(renglon.getReservados());
        kardex.setResponsable(String.valueOf(CConfiguracion.getId()));
        kardex.setResponsable2(CConfiguracion.getCorreo());
        kardex.setSalida(0);
        kardex.setUltimoCosto(renglon.getUltimoCosto());
        kardex.setVendidoEn(0);
        hkardex.guardarEnKardex(kardex);

           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Cantidad Removida","Remover",JOptionPane.INFORMATION_MESSAGE);

           
           listaArMos.get(tablaArtMos.getSelectedRow()).setCantidad(diferenciaEntera);
           dtm.setValueAt(diferencia, tablaArtMos.getSelectedRow(), 2);
           dtm.fireTableDataChanged();
           
           }
            }
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

           panelTabla.add(new JScrollPane(tablaArtMos));
           panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
           panelPrincipal.add(panelTabla,BorderLayout.CENTER);
           add(panelPrincipal);
           setBackground(Color.white);

/*            if(CConfiguracion.getPuesto().equals("Ventas"))
        {
            botonEliminar.setEnabled(false);
        }else if(CConfiguracion.getPuesto().equals("Almacén"))
        {
            botonEliminar.setEnabled(false);
        }else if(CConfiguracion.getPuesto().equals("Area"))
        {
            botonEliminar.setEnabled(false);
        }*/
    }


}
