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
import com.paedeias.controladores.CPrincipal;
import com.paedeias.helpers.hAlmacendevoluciones;
import com.paedeias.helpers.hArticulos;
import com.paedeias.helpers.hKardex;
import com.paedeias.identidades.*;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_AlmacenDevoluciones extends JPanel {
    
    hAlmacendevoluciones almacendevoluciones;
    hArticulos harticulos;
    JTable tablaADevoluciones;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2,leyenda3;
    JComboBox combo1,combo2;
    JTextField campoTexto1,campoTexto2;
    JButton boton1,botonEliminar;
    Vector vector;
    Vector<String> encabezadoADevoluciones;
    List<Almacendevoluciones> listaADevoluciones;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    hKardex hkardex;
    
    public VCatalogo_AlmacenDevoluciones()
    {
         harticulos = new hArticulos();
         tablaADevoluciones = new JTable();

        encabezadoADevoluciones = new Vector<String>();
        encabezadoADevoluciones.add("Código del Artículo");
        encabezadoADevoluciones.add("Descripción del Artículo");
        encabezadoADevoluciones.add("Precio de Compra");
        encabezadoADevoluciones.add("Precio de Venta");
        encabezadoADevoluciones.add("Cantidad");

        almacendevoluciones = new hAlmacendevoluciones();
        listaADevoluciones = new ArrayList<Almacendevoluciones>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoADevoluciones) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
          
           
           tablaADevoluciones.setModel(dtm);
           
        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());
        
        
        leyenda1 = new JLabel("Buscar artículos en donde:");
        leyenda2 = new JLabel("sea");
        leyenda3 = new JLabel("Seleccione: ");
        combo1 = new JComboBox();
        combo1.addItem("Código");
        combo1.addItem("Descripción");
        combo1.addItem("Precio Compra");
        combo1.addItem("Precio Venta");
        combo1.addItem("Cantidad");

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");
        
        campoTexto1 = new JTextField(20);
        campoTexto2 = new JTextField(4);
        campoTexto2.setText("0");
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setToolTipText("Buscar");
          boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

    //    Vector<String> encabezadoArticulos = new Vector<String>();
        String valores [] = {"codigoArticulo","descripcionArticulo","precioCompra","conBeneficio",
        "cantidad"};
        String comparadores [] = {"=","LIKE","*"};



        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         listaADevoluciones = almacendevoluciones.consultaPartidasAlmacen(campo,compara,condicion);
         vector = new Vector();

         for(Object o: listaADevoluciones){
             Almacendevoluciones iventas = (Almacendevoluciones)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getCodigoArticulo());
             unaFila.add(iventas.getDescripcionArticulo());
             unaFila.add(iventas.getPrecioCompra());
             unaFila.add(iventas.getConBeneficio());
             unaFila.add(iventas.getCantidad());
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezadoADevoluciones) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaADevoluciones.setModel(dtm);
           if(dtm.getRowCount() != 0)
           tablaADevoluciones.setRowSelectionInterval(0, 0);
            }
        });
        
          
          
             
        botonEliminar = new JButton("Enviar");
        botonEliminar.setToolTipText("Enviar el artículo seleccionado a Almacén General");
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEnviarAlmacenDevoluciones());
        botonEliminar.setBackground(new java.awt.Color(11,70,119));
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setContentAreaFilled(false);
        botonEliminar.setOpaque(true);        
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {   
                try {
        if(tablaADevoluciones.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }   
                
             List<Articulos> lart= harticulos.consultaArticulos("codigo", "=", listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());    
             if(lart.isEmpty())
             {
                
                                    ////////////// 
                String codigo =  JOptionPane.showInputDialog(null, "No se ha encontrado un artículo con este código, \n"
                                                                 + "Escriba el código del artículo nuevo para agregarlo al \n"
                                                                 + "catálogo de artículos o deje este campo en blanco para cancelar");
                
                 List<Articulos> tempart = harticulos.consultaArticulos("codigo", "=", codigo);
                 
                 if(!tempart.isEmpty())
                 {
                     JOptionPane.showMessageDialog(null, "Ya existe este código en el catálogo de artículos, por favor escriba uno que no exista");
                     return;
                 }
                 
                
                if(codigo == null)
                    return;
                
                if(codigo.length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "El código del artículo no puede tener más de 30 caracteres");
                    return;
                }
                
                if(!CPrincipal.getConexion().crearTransaccion())
                return;
             //    int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                int existencias = Integer.valueOf(campoTexto2.getText());
                
                Articulos articulo = new Articulos();
                articulo.setBloqueado(0);
                articulo.setClasificacion("");
                articulo.setCodigo(codigo);
                articulo.setDescripcion(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getDescripcionArticulo());
                articulo.setExistencia(Integer.parseInt(campoTexto2.getText()));
                articulo.setExistenciaAlmacen(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.parseInt(campoTexto2.getText()));
                articulo.setIeps(0);
                articulo.setIva(0);
                articulo.setMaximoPzas(existencias);
                articulo.setMinimoPzas(existencias);
                articulo.setPrecioCompra(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setPrecioVenta(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioVenta());
                articulo.setPromPzas(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                articulo.setReservado(0);
                articulo.setTipoEtiqueta("C");   
                articulo.setUbicacion("");        
                articulo.setUltimoCosto(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setUnidad("PZA");
                articulo.setAnticipos(0);
                articulo.setLineaPrincipal("Devoluciones");
                articulo.setSinonimoPrincipal("Devoluciones");
                articulo.setCodigo2("Sin código");
                articulo.setParetto(0);
                articulo.setNuevo(1);
                articulo.setPrecioVenta2(0.0);
                articulo.setProveedor("Sin proveedor");
                articulo.setOferta(0);
                
                harticulos.guardarArticulosCatalogo(articulo, new ArrayList<Articulolinea>(), 
                        new ArrayList<Articuloproveedor>(), new ArrayList<Articuloubicacion>());        
                
                
                //    harticulos.actualizarExistencias(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo(), existencias);
                almacendevoluciones.borrarPartidas("id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));                               
                
                  hkardex = new hKardex();
                  Kardex nuevoRenglon = new Kardex();
                  Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", codigo);
                  if(kardex==null)
                    {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(articulo.getAnticipos());
                    kardex.setArticulo(articulo.getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)articulo.getExistencia());
                    kardex.setIdArticulo(articulo.getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(articulo.getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)articulo.getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(articulo.getUltimoCosto());
                    kardex.setVendidoEn(0.0);
                    
                    hkardex.guardarEnKardex(kardex); 
                   }
                   //int cantidad = renglon.getExistencias() + listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad();
                   int cantidad = kardex.getExistencias();
                   int nomov=Integer.parseInt(kardex.getNoMov())+1;
                   nuevoRenglon.setReservados(kardex.getReservados());
                   nuevoRenglon.setAnticipos(kardex.getAnticipos());
                   nuevoRenglon.setExistencias(cantidad); 
                   nuevoRenglon.setNoMov(String.valueOf(nomov));            
                   nuevoRenglon.setAlmacenista("");
                   nuevoRenglon.setArticulo(codigo);
                   nuevoRenglon.setEntrada(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                   nuevoRenglon.setSalida(0);
                   nuevoRenglon.setIdArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                   nuevoRenglon.setModificacion("Artículo "+ codigo + " de almacén a Kardex" );
                   nuevoRenglon.setMovimiento("Adición de "+ listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() +" pzas");
                   nuevoRenglon.setPrecioVenta(lart.get(0).getPrecioVenta());
                   nuevoRenglon.setRefFerrari(String.valueOf("c"));
                   nuevoRenglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                   nuevoRenglon.setUltimoCosto(lart.get(0).getUltimoCosto());
                   nuevoRenglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                   nuevoRenglon.setVendidoEn(0.00);
             
                   hkardex.guardarEnKardex(nuevoRenglon);
                   
                   
                   CPrincipal.getConexion().finalizarTransaccion();
                   
                   listaADevoluciones.remove(tablaADevoluciones.getSelectedRow());
                   dtm.removeRow(tablaADevoluciones.getSelectedRow());
                   dtm.fireTableDataChanged();
                
                   
                   
                   
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return; 
                 
                
             }
             
             
            if(campoTexto2.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una cantidad en el cuadro de texto");
                return;
            }
              if(tablaADevoluciones.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione el artículo que desea mandar al Almacén General en la Tabla");
                return;
            }
              
              if(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() < Integer.parseInt(campoTexto2.getText()))
            {
                JOptionPane.showMessageDialog(null, "La cantidad que desea mandar es mayor a la disponible en el artículo seleccionado");
                return;
            }  
              
               if(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() == Integer.parseInt(campoTexto2.getText()))
            {   
                
                if(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getDescripcionArticulo().equals(lart.get(0).getDescripcion()))
                {
                
                int eleccion = JOptionPane.showConfirmDialog(null, 
                        "Los artículos enviados tomarán el precio y costo actual del item con ese código, ¿Desea Continuar?", 
                        "Confirmar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if(eleccion == JOptionPane.NO_OPTION)
                    return;
                
                if(!CPrincipal.getConexion().crearTransaccion())
                return;
                
                int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                existencias = existencias + Integer.valueOf(campoTexto2.getText());
                
              //  System.out.println(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                
                harticulos.actualizarExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), existencias);
                harticulos.disminuyeAlmacenDevoluciones(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), Integer.valueOf(campoTexto2.getText()));
                
                almacendevoluciones.borrarPartidas("id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));                               
                
                  hkardex = new hKardex();
                  Kardex nuevoRenglon = new Kardex();
                  Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                  if(kardex==null)
                    {
 
                                      //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(lart.get(0).getAnticipos());
                    kardex.setArticulo(lart.get(0).getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)lart.get(0).getExistencia());
                    kardex.setIdArticulo(lart.get(0).getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(lart.get(0).getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)lart.get(0).getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(lart.get(0).getUltimoCosto());
                    kardex.setVendidoEn(0.0);
                    
                    hkardex.guardarEnKardex(kardex); 
                  
                  
                   }
                   //int cantidad = renglon.getExistencias() + listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad();
                   int cantidad = kardex.getExistencias();
                   int nomov=Integer.parseInt(kardex.getNoMov())+1;
                   nuevoRenglon.setReservados(kardex.getReservados());
                   nuevoRenglon.setAnticipos(kardex.getAnticipos());
                   nuevoRenglon.setExistencias(cantidad); 
                   nuevoRenglon.setNoMov(String.valueOf(nomov));
                   nuevoRenglon.setAlmacenista("");
                   nuevoRenglon.setArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                   nuevoRenglon.setEntrada(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                   nuevoRenglon.setSalida(0);
                   nuevoRenglon.setIdArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                   nuevoRenglon.setModificacion("Artículo "+listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo() + "de almacén a Kardex" );
                   nuevoRenglon.setMovimiento("Cambio de "+ listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() +" pzas");
                   nuevoRenglon.setPrecioVenta(lart.get(0).getPrecioVenta());
                   nuevoRenglon.setRefFerrari(String.valueOf("c"));
                   nuevoRenglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                   nuevoRenglon.setUltimoCosto(lart.get(0).getUltimoCosto());
                   nuevoRenglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                   nuevoRenglon.setVendidoEn(0.00);
             
                   hkardex.guardarEnKardex(nuevoRenglon);
                   
                   CPrincipal.getConexion().finalizarTransaccion();
                   
                   listaADevoluciones.remove(tablaADevoluciones.getSelectedRow());
                   dtm.removeRow(tablaADevoluciones.getSelectedRow());
                   dtm.fireTableDataChanged();
                
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return;
                }else
                {
                    ////////////// 
                String codigo =  JOptionPane.showInputDialog(null, "No se ha encontrado un artículo con esta descripción, \n"
                                                                 + "Escriba la clave del artículo nuevo para agregarlo al \n"
                                                                 + "catálogo de artículos o deje este campo en blanco para cancelar");
                
                 List<Articulos> tempart = harticulos.consultaArticulos("codigo", "=", codigo);
                 
                 if(!tempart.isEmpty())
                 {
                     JOptionPane.showMessageDialog(null, "Ya existe este código en el catálogo de artículos, por favor escriba uno que no exista");
                     return;
                 }
                 
                
                if(codigo == null)
                    return;
                
                if(codigo.length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "El código del artículo no puede tener más de 30 caracteres");
                    return;
                }
                
             //    int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                
                
                if(!CPrincipal.getConexion().crearTransaccion())
                return;
                
                int existencias = Integer.valueOf(campoTexto2.getText());
                
                Articulos articulo = new Articulos();
                articulo.setBloqueado(0);
                articulo.setClasificacion("");
                articulo.setCodigo(codigo);
                articulo.setDescripcion(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getDescripcionArticulo());
                articulo.setExistencia(Integer.parseInt(campoTexto2.getText()));
                articulo.setExistenciaAlmacen(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.parseInt(campoTexto2.getText()));
                articulo.setIeps(0);
                articulo.setIva(0);
                articulo.setMaximoPzas(existencias);
                articulo.setMinimoPzas(existencias);
                articulo.setPrecioCompra(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setPrecioVenta(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioVenta());
                articulo.setPromPzas(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                articulo.setReservado(0);
                articulo.setTipoEtiqueta("C");   
                articulo.setUbicacion("");        
                articulo.setUltimoCosto(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setUnidad("PZA");        
                articulo.setAnticipos(0);
                articulo.setLineaPrincipal("Devoluciones");
                articulo.setSinonimoPrincipal("Devoluciones");
                articulo.setOferta(0);
                        
                harticulos.guardarArticulos(articulo, new ArrayList<Articulolinea>(), 
                        new ArrayList<Articuloproveedor>(), new ArrayList<Articuloubicacion>());        
                
                
                //    harticulos.actualizarExistencias(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo(), existencias);
                almacendevoluciones.borrarPartidas("id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));                               
                
                  hkardex = new hKardex();
                  Kardex nuevoRenglon = new Kardex();
                  Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", codigo);
                  if(kardex==null)
                    {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(articulo.getAnticipos());
                    kardex.setArticulo(articulo.getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)articulo.getExistencia());
                    kardex.setIdArticulo(articulo.getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(articulo.getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)articulo.getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(articulo.getUltimoCosto());
                    kardex.setVendidoEn(0.0);  
                    
                    hkardex.guardarEnKardex(kardex); 
                   }
                   //int cantidad = renglon.getExistencias() + listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad();
                   int cantidad = kardex.getExistencias();
                   int nomov=Integer.parseInt(kardex.getNoMov())+1;
                   nuevoRenglon.setReservados(kardex.getReservados());
                   nuevoRenglon.setExistencias(cantidad); 
                   nuevoRenglon.setNoMov(String.valueOf(nomov));
                   nuevoRenglon.setAnticipos(kardex.getAnticipos());
                   nuevoRenglon.setAlmacenista("");
                   nuevoRenglon.setArticulo(codigo);
                   nuevoRenglon.setEntrada(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                   nuevoRenglon.setSalida(0);
                   nuevoRenglon.setIdArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                   nuevoRenglon.setModificacion("Artículo "+ codigo + " de almacén a Kardex" );
                   nuevoRenglon.setMovimiento("Adición de "+ listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() +" pzas");
                   nuevoRenglon.setPrecioVenta(articulo.getPrecioVenta());
                   nuevoRenglon.setRefFerrari(String.valueOf("c"));
                   nuevoRenglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                   nuevoRenglon.setUltimoCosto(articulo.getUltimoCosto());
                   nuevoRenglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                   nuevoRenglon.setVendidoEn(0.00);
             
                   hkardex.guardarEnKardex(nuevoRenglon);
                
                   CPrincipal.getConexion().finalizarTransaccion();
                   
                   dtm.removeRow(tablaADevoluciones.getSelectedRow());
                   dtm.fireTableDataChanged();
                
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return;
                    
                    ////////////
                }
            }
                            
              else if(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() > Integer.parseInt(campoTexto2.getText()))
            {               
             
          /*     int eleccion = JOptionPane.showConfirmDialog(null, 
                        "Los artículos enviados tomarán el precio y costo actual del item con ese código, ¿Desea Continuar?", 
                        "Confirmar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if(eleccion == JOptionPane.NO_OPTION)
                    return;
                
                int restante = listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.valueOf(campoTexto2.getText());
                almacendevoluciones.actualizarExistencias(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId(), restante);
                
                
                int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                existencias = existencias + Integer.valueOf(campoTexto2.getText());
                harticulos.actualizarExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), existencias);
                
                
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return; */
                
                   if(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getDescripcionArticulo().equals(lart.get(0).getDescripcion()))
                {
                
                int eleccion = JOptionPane.showConfirmDialog(null, 
                        "Los artículos enviados tomarán el precio y costo actual del item con ese código, ¿Desea Continuar?", 
                        "Confirmar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if(eleccion == JOptionPane.NO_OPTION)
                    return;
              
                if(!CPrincipal.getConexion().crearTransaccion())
                  return;
                
                int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                existencias = existencias + Integer.valueOf(campoTexto2.getText());
                
                int existenciasalmacen = listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.valueOf(campoTexto2.getText());
                
                harticulos.actualizarExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), existencias);
                harticulos.disminuyeAlmacenDevoluciones(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), Integer.valueOf(campoTexto2.getText()));
                
                listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).setCantidad(existenciasalmacen);
                almacendevoluciones.actualizarPartidas(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()), 
                        "id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));
                
                
                  hkardex = new hKardex();
                  Kardex nuevoRenglon = new Kardex();
                  Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                  if(kardex==null)
                    {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(lart.get(0).getAnticipos());
                    kardex.setArticulo(lart.get(0).getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)lart.get(0).getExistencia());
                    kardex.setIdArticulo(lart.get(0).getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(lart.get(0).getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)lart.get(0).getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(lart.get(0).getUltimoCosto());
                    kardex.setVendidoEn(0.0);
                    
                    hkardex.guardarEnKardex(kardex); 
                    
                   }
                   int cantidad = kardex.getExistencias();
                   int nomov=Integer.parseInt(kardex.getNoMov())+1;
                   nuevoRenglon.setReservados(kardex.getReservados());
                   nuevoRenglon.setExistencias(cantidad); 
                   nuevoRenglon.setNoMov(String.valueOf(nomov));
                   nuevoRenglon.setAnticipos(kardex.getAnticipos());
                   nuevoRenglon.setAlmacenista("");
                   nuevoRenglon.setArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                   nuevoRenglon.setEntrada(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                   nuevoRenglon.setSalida(0);
                   nuevoRenglon.setIdArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                   nuevoRenglon.setModificacion("Artículo "+listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo() + "de almacén a Kardex" );
                   nuevoRenglon.setMovimiento("Cambio de "+ listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() +" pzas");
                   nuevoRenglon.setPrecioVenta(lart.get(0).getPrecioVenta());
                   nuevoRenglon.setRefFerrari(String.valueOf("c"));
                   nuevoRenglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                   nuevoRenglon.setUltimoCosto(lart.get(0).getUltimoCosto());
                   nuevoRenglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                   nuevoRenglon.setVendidoEn(0.00);
                   dtm.setValueAt(String.valueOf(existenciasalmacen), tablaADevoluciones.getSelectedRow(), 4);
             
                   hkardex.guardarEnKardex(nuevoRenglon);
                   CPrincipal.getConexion().finalizarTransaccion();
                   
                   
                   
                   dtm.fireTableDataChanged();
                
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return;
                }
                   else
                {
                    ////////////// 
                String codigo =  JOptionPane.showInputDialog(null, "No se ha encontrado un artículo con esta descripción, \n"
                                                                 + "Escriba la clave del artículo nuevo para agregarlo al \n"
                                                                 + "catálogo de artículos o deje este campo en blanco para cancelar");
                
                 List<Articulos> tempart = harticulos.consultaArticulos("codigo", "=", codigo);
                 
                 if(!tempart.isEmpty())
                 {
                     JOptionPane.showMessageDialog(null, "Ya existe este código en el catálogo de artículos, por favor escriba uno que no exista");
                     return;
                 }
                 
                int existenciasalmacen = listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.valueOf(campoTexto2.getText());
                if(codigo == null)
                    return;
                
                if(codigo.length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "El código del artículo no puede tener más de 30 caracteres");
                    return;
                }
                
                
               if(!CPrincipal.getConexion().crearTransaccion())
               return;
                
             //    int existencias = harticulos.consultaExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo());
                int existencias = Integer.valueOf(campoTexto2.getText());
                
                Articulos articulo = new Articulos();
                articulo.setBloqueado(0);
                articulo.setClasificacion("");
                articulo.setCodigo(codigo);
                articulo.setDescripcion(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getDescripcionArticulo());
                articulo.setExistencia(Integer.parseInt(campoTexto2.getText()));
                articulo.setExistenciaAlmacen(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() - Integer.parseInt(campoTexto2.getText()));
                articulo.setIeps(0);
                articulo.setIva(0);
                articulo.setMaximoPzas(existencias);
                articulo.setMinimoPzas(existencias);
                articulo.setPrecioCompra(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setPrecioVenta(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioVenta());
                articulo.setPromPzas(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                articulo.setReservado(0);
                articulo.setTipoEtiqueta("C");   
                articulo.setUbicacion("");        
                articulo.setUltimoCosto(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getPrecioCompra());
                articulo.setUnidad("PZA");    
                articulo.setAnticipos(0);
                articulo.setLineaPrincipal("Devoluciones");
                articulo.setSinonimoPrincipal("Devoluciones");
                articulo.setOferta(0);
                        
                harticulos.guardarArticulos(articulo, new ArrayList<Articulolinea>(), 
                        new ArrayList<Articuloproveedor>(), new ArrayList<Articuloubicacion>());        
                
                
                //    harticulos.actualizarExistencias(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo(), existencias);
                // almacendevoluciones.borrarPartidas("id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));                               
                // harticulos.actualizarExistenciasCodigo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCodigoArticulo(), existencias);
                
                listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).setCantidad(existenciasalmacen);
                almacendevoluciones.actualizarPartidas(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()), 
                        "id", "=", String.valueOf(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getId()));
                
                  hkardex = new hKardex();
                  Kardex nuevoRenglon = new Kardex();
                  Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", codigo);
                  if(kardex==null)
                    {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(articulo.getAnticipos());
                    kardex.setArticulo(articulo.getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)articulo.getExistencia());
                    kardex.setIdArticulo(articulo.getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(articulo.getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)articulo.getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(articulo.getUltimoCosto());
                    kardex.setVendidoEn(0.0);  
                    
                    hkardex.guardarEnKardex(kardex);
                   }
                   int cantidad = kardex.getExistencias();
                   int nomov=Integer.parseInt(kardex.getNoMov())+1;
                   nuevoRenglon.setReservados(kardex.getReservados());
                   nuevoRenglon.setExistencias(cantidad); 
                   nuevoRenglon.setNoMov(String.valueOf(nomov));
                   nuevoRenglon.setAnticipos(kardex.getAnticipos());           
                   nuevoRenglon.setAlmacenista("");
                   nuevoRenglon.setArticulo(codigo);
                   nuevoRenglon.setEntrada(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad());
                   nuevoRenglon.setSalida(0);
                   nuevoRenglon.setIdArticulo(listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getIdArticulo());
                   nuevoRenglon.setModificacion("Artículo "+ codigo + " de almacén a Kardex" );
                   nuevoRenglon.setMovimiento("Adición de "+ listaADevoluciones.get(tablaADevoluciones.getSelectedRow()).getCantidad() +" pzas");
                   nuevoRenglon.setPrecioVenta(lart.get(0).getPrecioVenta());
                   nuevoRenglon.setRefFerrari(String.valueOf("c"));
                   nuevoRenglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                   nuevoRenglon.setUltimoCosto(lart.get(0).getUltimoCosto());
                   nuevoRenglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                   nuevoRenglon.setVendidoEn(0.00);
                   dtm.setValueAt(String.valueOf(existenciasalmacen), tablaADevoluciones.getSelectedRow(), 4);
                   
                   hkardex.guardarEnKardex(nuevoRenglon);
                   CPrincipal.getConexion().finalizarTransaccion();
                   
                   
                   dtm.fireTableDataChanged();
                
                JOptionPane.showMessageDialog(null, "Los Artículos fueron enviados correctamente");
                return;
                    
                    ////////////
                }
                
                
            }
         }catch(Exception ex1)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            ex1.printStackTrace();            
        }
            }
        });
        
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        panelEncabezado.add(leyenda3);
        panelEncabezado.add(campoTexto2);
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
        
        panelTabla.setBackground(Color.white);
        panelEncabezado.setBackground(Color.white);
        panelPrincipal.setBackground(Color.white);

        panelTabla.add(new JScrollPane(tablaADevoluciones));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
       
        
        
    }
    

    
}
