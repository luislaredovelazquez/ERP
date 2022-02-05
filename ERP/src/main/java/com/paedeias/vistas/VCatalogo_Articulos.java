/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Luis
 */
public class VCatalogo_Articulos extends JPanel{

   
    hArticulos articulos;
    hAlmacendevoluciones halmacendevoluciones;
    JTable tablaArticulos;
    JPanel panelTabla,panelEncabezado,panelPrincipal,panelFooter;
    JLabel leyenda1,leyenda2,leyenda3,leyenda4,leyenda5;
    JCheckBox filtro;
    JComboBox combo1,combo2,combo3;
    JTextField campoTexto1,campoTexto2,campoTexto3,campoTexto4,campoTexto5;
    public static JButton boton1,botonAlta,botonEliminar,boton2,boton3;
    Vector vector;
    Vector<String> encabezadoArticulos;
    List<Articulos> listaArticulos;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    List<Articulolinea> lineasGuardadas;
    List <Articuloproveedor>proveedoresGuardados;
    List <Articuloubicacion>ubicacionesGuardadas;
    DecimalFormat df;  

     public VCatalogo_Articulos(){
         
        ///
         
         
         df = new DecimalFormat("0.00");
         
         halmacendevoluciones = new hAlmacendevoluciones();
          tablaArticulos = new JTable();

        encabezadoArticulos = new Vector<String>();
        encabezadoArticulos.add("Id");
        encabezadoArticulos.add("Código");
        encabezadoArticulos.add("Descripción");
        encabezadoArticulos.add("Línea");
        encabezadoArticulos.add("Venta");
        encabezadoArticulos.add("Compra");
        encabezadoArticulos.add("Existencia");
        encabezadoArticulos.add("Almacén Devoluciones");
        encabezadoArticulos.add("Reservado");
        encabezadoArticulos.add("Anticipos");

        articulos = new hArticulos();
        listaArticulos = new ArrayList<Articulos>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaArticulos.setModel(dtm);
           tablaArticulos.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
               //  botonActualizar.setEnabled(true);
            if(e.getClickCount() == 2)
            {
            VActualiza_Articulo actualizaArticulos = new VActualiza_Articulo(listaArticulos.get(tablaArticulos.getSelectedRow()));
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();

            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualizaArticulos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Actualiza",indice));
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
        panelFooter = new JPanel();
        panelFooter.setBackground(Color.white);
        panelFooter.setLayout(new FlowLayout());

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaArticulos());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               VAlta_Articulos altaArticulos = new VAlta_Articulos();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaArticulos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);


            }

        });

       
        filtro = new JCheckBox("Filtro");
        filtro.setBackground(Color.white);
        campoTexto5 = new JTextField(10);
        campoTexto5.setText("%");
        
        leyenda1 = new JLabel("Buscar articulos");
        leyenda2 = new JLabel("sea");
        leyenda3 = new JLabel("Buscar articulos con");
        leyenda4 = new JLabel("Como ");
        
        combo1 = new JComboBox();
        combo1.addItem("Código");
        combo1.addItem("Id");
        combo1.addItem("Descripción");
        combo1.addItem("Venta");
        combo1.addItem("Compra");
        combo1.addItem("Existencia");
        combo1.addItem("Reservado");
        combo1.addItem("Línea Principal");
        combo1.addItem("Sinónimo Principal");
        combo1.addItem("Código Alterno");
        combo1.addItem("Proveedor Principal");
        combo1.addItem("Clasificación Paretto");
        combo1.addItem("Sin Paretto");
        combo1.addItem("Con Paretto");
        
        combo2 = new JComboBox();
        combo2.addItem("como");
        combo2.addItem("igual a");
        
        combo3 = new JComboBox();
        combo3.addItem("Líneas");
        combo3.addItem("Locaciones");
        combo3.addItem("Sinónimos");
        // combo2.addItem("Todos"); //Habilitar para buscar todos los artículos pero esto alentaría bastante la búsqueda...
        

        campoTexto1 = new JTextField(15);
        campoTexto2 = new JTextField(15);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setToolTipText("Buscar");
        boton1.setEnabled(CConfiguracion.isBuscarArticulos());
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

    //    Vector<String> encabezadoArticulos = new Vector<String>();
        String valores [] = {"codigo","id","descripcion","precioVenta",
        "precioCompra","existencia","reservado","lineaPrincipal","sinonimoPrincipal","codigo2","proveedor","clasificacion","paretto","paretto"};
        String comparadores [] = {"LIKE","=","*"};

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
        
        if(combo1.getSelectedItem().toString().equals("Sin Paretto"))
           condicion = "1";
        if(combo1.getSelectedItem().toString().equals("Con Paretto"))
           condicion = "0";

        
        
         // if(combo1.getSelectedItem().toString().equals("Todos"));
     /*    if(campoTexto1.getText().trim().length()==0)
         {
         JOptionPane.showMessageDialog(null, "Por favor escriba su término de búsqueda en el campo de texto");
         return;
         } 
         * else */
        //  listaArticulos = articulos.consultaArticulosLimite(campo,compara,condicion,Integer.valueOf(campoTexto4.getText()));
         if(filtro.isSelected())
         listaArticulos = articulos.consultaArticulosFiltro(campo,compara,condicion,campoTexto5.getText());
          else
         listaArticulos = articulos.consultaArticulos(campo,compara,condicion);
         vector = new Vector();

         for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getId());
             unaFila.add(iarticulos.getCodigo());
             unaFila.add(iarticulos.getDescripcion());
             unaFila.add(iarticulos.getLineaPrincipal());
             unaFila.add(df.format(iarticulos.getPrecioVenta()));
             unaFila.add(df.format(iarticulos.getPrecioCompra()));
             unaFila.add(iarticulos.getExistencia());
             
             
     /*        int acumulado = 0;
             List<Almacendevoluciones> listadev = halmacendevoluciones.consultaPartidas("codigoArticulo", "=", iarticulos.getCodigo());
             int suma=0;
             while(suma<listadev.size())
             {
                 acumulado = acumulado + listadev.get(suma).getCantidad();
                 suma++;
             }*/
             unaFila.add(iarticulos.getAlmacenDevoluciones());
             unaFila.add(iarticulos.getReservado()); 
             unaFila.add(iarticulos.getAnticipos());
             vector.add(unaFila); 

         }

            dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

       tablaArticulos.setModel(dtm);
       tablaArticulos.getColumnModel().getColumn(0).setPreferredWidth(50); 
       tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150); 
       tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(600);     
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });
        
        boton2 = new JButton(imagenbuscar);
        boton2.setBackground(Color.white);
        boton2.setBorderPainted(false);
        boton2.setToolTipText("Buscar");
        boton2.setEnabled(CConfiguracion.isBuscarArticulos());
        boton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                // -------------------------------MODIFICAR MODIFICAR MODIFICAR MODIFICAR---------------------------------------------------- //
        if(combo3.getSelectedItem().toString().equals("Líneas"))
        {
        hArticulolinea harticulolinea = new hArticulolinea();
        List<Articulos> al = harticulolinea.consultaArticuloxNombre(campoTexto2.getText());
        vector.clear();
        listaArticulos.clear();
        for(Object o: al)
        {
         Articulos artlin = (Articulos)o;
          Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(artlin.getId());
             unaFila.add(artlin.getCodigo());
             unaFila.add(artlin.getDescripcion());
             unaFila.add(artlin.getLineaPrincipal());
             unaFila.add(artlin.getPrecioVenta());
             unaFila.add(artlin.getPrecioCompra());
             unaFila.add(artlin.getExistencia());
             int acumulado = 0;
             List<Almacendevoluciones> listadev = halmacendevoluciones.consultaPartidas("codigoArticulo", "=", artlin.getCodigo());
             int suma=0;
             while(suma<listadev.size())
             {
                 acumulado = acumulado + listadev.get(suma).getCantidad();
                 suma++;
             }
             unaFila.add(acumulado);
             unaFila.add(artlin.getReservado());
             unaFila.add(artlin.getAnticipos());
             vector.add(unaFila);
             listaArticulos.add(artlin);
        }
            dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaArticulos.setModel(dtm);
           
           
        }else if(combo3.getSelectedItem().toString().equals("Locaciones"))
        {
        hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
        List<Articulos> au = harticuloubicacion.consultaArticulos(campoTexto2.getText());
        vector.clear();
        listaArticulos.clear();
        for(Object o: au)
        {
          Articulos artlin = (Articulos)o;
          Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(artlin.getId());
             unaFila.add(artlin.getCodigo());
             unaFila.add(artlin.getDescripcion());
             unaFila.add(artlin.getLineaPrincipal());
             unaFila.add(artlin.getPrecioVenta());
             unaFila.add(artlin.getPrecioCompra());
             unaFila.add(artlin.getExistencia());
             int acumulado = 0;
             List<Almacendevoluciones> listadev = halmacendevoluciones.consultaPartidas("codigoArticulo", "=", artlin.getCodigo());
             int suma=0;
             while(suma<listadev.size())
             {
                 acumulado = acumulado + listadev.get(suma).getCantidad();
                 suma++;
             }
             unaFila.add(acumulado);
             unaFila.add(artlin.getReservado());
             unaFila.add(artlin.getAnticipos());
             vector.add(unaFila);
             listaArticulos.add(artlin);
        }
        dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaArticulos.setModel(dtm);
        }
        
        else if(combo3.getSelectedItem().toString().equals("Sinónimos"))
        {
        hArticuloProveedor harticuloproveedor = new hArticuloProveedor();
        List<Articulos> ap = harticuloproveedor.consultaArticulos(campoTexto2.getText());
        vector.clear();
        listaArticulos.clear();
       // System.out.println(ap.size());

        for(Object o: ap)
        {
         Articulos artlin = (Articulos)o;
          Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(artlin.getId());
             unaFila.add(artlin.getCodigo());
             unaFila.add(artlin.getDescripcion());
             unaFila.add(artlin.getLineaPrincipal());
             unaFila.add(artlin.getPrecioVenta());
             unaFila.add(artlin.getPrecioCompra());
             unaFila.add(artlin.getExistencia());
             int acumulado = 0;
             List<Almacendevoluciones> listadev = halmacendevoluciones.consultaPartidas("codigoArticulo", "=", artlin.getCodigo());
             int suma=0;
             while(suma<listadev.size())
             {
                 acumulado = acumulado + listadev.get(suma).getCantidad();
                 suma++;
             }
             unaFila.add(acumulado);
             unaFila.add(artlin.getReservado());
             unaFila.add(artlin.getAnticipos());
             vector.add(unaFila);
             listaArticulos.add(artlin); 
        }
            dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           tablaArticulos.setModel(dtm);
           }
        

           // -------------------------------MODIFICAR MODIFICAR MODIFICAR MODIFICAR---------------------------------------------------- //     
                campoTexto2.setText("");
       tablaArticulos.getColumnModel().getColumn(0).setPreferredWidth(50); 
       tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150); 
       tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(600);     
            }          
        });
        
        
        boton3 = new JButton("Navegar");
        boton3.setBorderPainted(false);
        boton3.setToolTipText("Navegar");
        boton3.setEnabled(CConfiguracion.isBuscarArticulos());
        boton3.setBackground(new java.awt.Color(11,70,119));
        boton3.setForeground(new java.awt.Color(255, 255, 255));
        boton3.setContentAreaFilled(false);
        boton3.setOpaque(true);
        boton3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                if(combo3.getSelectedItem().equals("Líneas"))
                {
        VCatalogo_Articulos.PSeleccionar_Linea seleccionar = new VCatalogo_Articulos.PSeleccionar_Linea();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Lineas",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(200, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);   
                }
                else if(combo3.getSelectedItem().equals("Locaciones"))
                 {
        VCatalogo_Articulos.PSeleccionar_Ubicacion seleccionar = new VCatalogo_Articulos.PSeleccionar_Ubicacion();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Ubicaciones",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(200, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);           
                 }
                else if(combo3.getSelectedItem().equals("Sinónimos"))
                {
        VCatalogo_Articulos.PSinonimo seleccionar = new VCatalogo_Articulos.PSinonimo();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Sinonimos",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(200, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);  
                }
                
                
                
                
                
            }});

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEliminarArticulos());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            if(tablaArticulos.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }      
         
            
         PanelEliminar panelEliminar = new PanelEliminar((JFrame)Window.getWindows()[0],true);
          panelEliminar.setElemento("Artículo");
          panelEliminar.setBoton1(0);
          panelEliminar.setBoton2(1);
          panelEliminar.setBoton3(1);
          panelEliminar.inicializar();
          panelEliminar.setSize(300, 200);
          panelEliminar.setLocationRelativeTo(null);
          panelEliminar.setVisible(true); 
          
          
              hAlmacendevoluciones halmacendevoluciones = new hAlmacendevoluciones();
              hArticulolinea harticulolinea = new hArticulolinea();
              hArticuloProveedor harticuloproveedor = new hArticuloProveedor();
              hArticulosEnMostrador harticulosenmostrador = new hArticulosEnMostrador();
              hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
              hKardex hkardex = new hKardex();
              hPartidas hpartidas = new hPartidas();
              hVentas hventas = new hVentas();
              hPartidasAnticipos hpartidasanticipos = new hPartidasAnticipos();
              hAnticipos hanticipos = new hAnticipos();
              hPartidasCompras hpartidascompras = new hPartidasCompras();
              hPartidasComprasMayoreo hpartidascomprasmayoreo = new hPartidasComprasMayoreo();
              hComprasMayoreo hcomprasmayoreo = new hComprasMayoreo();
              hPartidasDevoluciones hpartidasdevoluciones = new hPartidasDevoluciones();
              hDevoluciones hdevoluciones = new hDevoluciones();
              hPartidasfacturas hpartidasfacturas = new hPartidasfacturas();
              hFacturas hfacturas = new hFacturas();
              hPartidasfacturasCFDI hpartidasfacturacfdi = new hPartidasfacturasCFDI();
              hFacturasCFDI hfacturacfdi = new hFacturasCFDI();
              hPartidasReservaciones hpartidasreservaciones = new hPartidasReservaciones();
              hReservaciones hreservaciones = new hReservaciones();
              hPartidaVentaAnticipos hpartidaventaanticipos = new hPartidaVentaAnticipos();
              hVentaAnticipos hventaanticipos = new hVentaAnticipos();
              hPedidosVentas hpedidosventas = new hPedidosVentas();
              hArticulos harticulos = new hArticulos();
              
              Long id = listaArticulos.get(tablaArticulos.getSelectedRow()).getId();
              String cadenaID = String.valueOf(id);
              String codigoAr = listaArticulos.get(tablaArticulos.getSelectedRow()).getCodigo();
          
          if(panelEliminar.isBandera())
          {
              
              
           switch(panelEliminar.getOpcion())   
           {
               case 2:
          String nuevoCodigo = JOptionPane.showInputDialog(null, "Por favor escriba el nuevo código de este artículo");         
          if(nuevoCodigo.isEmpty()) return;       
          List<Articulos> validarArticulos = harticulos.consultaArticulos("codigo", "=", nuevoCodigo);
          if(!validarArticulos.isEmpty()){JOptionPane.showMessageDialog(null, "Este código ya existe en el catálogo de artículos"); return;}
              
              harticuloproveedor.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              harticulosenmostrador.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpedidosventas.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidascompras.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidascomprasmayoreo.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidasfacturas.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidasfacturacfdi.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidasreservaciones.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidaventaanticipos.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              halmacendevoluciones.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hkardex.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidas.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidasanticipos.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidasdevoluciones.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              hpartidaventaanticipos.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              
              
              harticulos.actualizarCodigoArticulo(nuevoCodigo, codigoAr);
              
              JOptionPane.showMessageDialog(null, "Código Actualizado");
              boton1.doClick();
              
              
                   break;
                   
               case 3:
           int continuar =   JOptionPane.showConfirmDialog(null, "Esta acción eliminará todos los registros del artículo en el sistema de modo irrecuperable","¿Desea Continuar?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
              
           if(continuar == JOptionPane.YES_OPTION)
           {

              if (CPrincipal.getConexion().crearTransaccion())
              {
              halmacendevoluciones.borrarPartidas("idArticulo", "=", cadenaID);
              harticulolinea.borrarArticuloLineaCompleto("claveArticulo", "=", cadenaID);
              harticuloproveedor.borrarArtProvCompleto("idArticulo", "=", codigoAr);
              harticulosenmostrador.borrarArticulosEnMostrador("codigo", "=", codigoAr);
              harticuloubicacion.borrarArticuloUbicacionCompleto("idarticulo", "=", cadenaID);
              hpedidosventas.borrarPedidosVentas("codigoArticulo", "=", codigoAr);
              hkardex.borrarKardex("idArticulo", "=", cadenaID);
              
              List<Partidas> listaProv = hpartidas.consultaPartidas("idArticulo", "=", cadenaID);
              for(int i=0; i<listaProv.size(); i++)
              {
               hventas.borrarVentas("id", "=", String.valueOf(listaProv.get(i).getIdVenta()));   
              }
              hpartidas.borrarPartidas("idArticulo", "=", cadenaID);
              
              List<Partidasanticipos> listaProv2 = hpartidasanticipos.consultaPartidas("idarticulo", "=", cadenaID);
              for(int i=0; i<listaProv2.size(); i++)
              {
               hanticipos.borrarAnticipos("id", "=", String.valueOf(listaProv2.get(i).getAnticipo()));   
              }
              hpartidasanticipos.borrarPartidas("idarticulo", "=", cadenaID);
                           
              List<Partidascomprasmayoreo> listaProv4 = hpartidascomprasmayoreo.consultaPCompras("articulo", "=", codigoAr);
              for(int i=0; i<listaProv4.size(); i++)
              {
               hcomprasmayoreo.borrarCompras("id", "=", String.valueOf(listaProv4.get(i).getCompra()));   
              }
              hpartidascomprasmayoreo.borrarPCompras("articulo", "=", codigoAr);
              
              List<Partidasdevoluciones> listaProv5 = hpartidasdevoluciones.consultaPartidas("idArticulo", "=", cadenaID);
              for(int i=0; i<listaProv5.size(); i++)
              {
               hdevoluciones.borrarVentas("id", "=", String.valueOf(listaProv5.get(i).getIdVenta()));   
              }
              hpartidasdevoluciones.borrarPartidas("idArticulo", "=", cadenaID);
              
              List<Partidasfacturas> listaProv6 = hpartidasfacturas.consultaPartidas("codigoArticulo", "=", codigoAr);
              for(int i=0; i<listaProv6.size(); i++)
              {
               hfacturas.borrarFactura("idFactura", "=", String.valueOf(listaProv6.get(i).getIdFactura()));   
              }
              hpartidasfacturas.borrarPartidas("codigoArticulo", "=", codigoAr);
              
              List<Partidasfacturas> listaProv7 = hpartidasfacturacfdi.consultaPartidas("codigoArticulo", "=", codigoAr);
              for(int i=0; i<listaProv7.size(); i++)
              {
               hfacturacfdi.borrarFactura("idFactura", "=", String.valueOf(listaProv7.get(i).getIdFactura()));   
              }
              hpartidasfacturacfdi.borrarPartidas("codigoArticulo", "=", codigoAr);
              
              List<Partidasreservaciones> listaProv8 = hpartidasreservaciones.consultaPReservaciones("codArt", "=", codigoAr);
              for(int i=0; i<listaProv8.size(); i++)
              {
               hreservaciones.borrarReservaciones("id", "=", listaProv8.get(i).getCodRes());   
              }
              hpartidasreservaciones.borrarPReservaciones("codArt", "=", codigoAr);
              
              List<Partidasventaanticipos> listaProv9 = hpartidaventaanticipos.consultaPartidas("codigoArticulo", "=", codigoAr);
              for(int i=0; i<listaProv9.size(); i++)
              {
               hventaanticipos.borrarVentas("id", "=", String.valueOf(listaProv9.get(i).getIdVenta()));   
              }
              hpartidaventaanticipos.borrarPartidas("codigoArticulo", "=", codigoAr);
              
              harticulos.borrarArticulos("id", "=", cadenaID);
              
              CPrincipal.getConexion().finalizarTransaccion();
       
      JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0],"Elemento Eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

           listaArticulos.remove(tablaArticulos.getSelectedRow());
           dtm.removeRow(tablaArticulos.getSelectedRow());
           tablaArticulos.setModel(dtm);

       tablaArticulos.getColumnModel().getColumn(0).setPreferredWidth(50); 
       tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150); 
       tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(600);   
              }
           }
          
          break;
          }
           
                }
          
         /*       
          int respuesta = JOptionPane.showConfirmDialog((JFrame)Window.getWindows()[0],
          "¿Estas seguro de que deseas eliminar este elemento?","Eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

          if(JOptionPane.YES_OPTION == respuesta)
          {
           hArticulos harticulos = new hArticulos();
           hArticulolinea harticulolinea = new hArticulolinea();
           hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
           hArticuloProveedor harticuloproveedor = new hArticuloProveedor();

           Long id = listaArticulos.get(tablaArticulos.getSelectedRow()).getId();

           harticulolinea.borrarArticuloLineaCompleto("claveArticulo","=",String.valueOf(id));
           harticuloubicacion.borrarArticuloUbicacionCompleto("idArticulo","=",String.valueOf(id));
           harticuloproveedor.borrarArtProvCompleto("idArticulo","=",String.valueOf(id));


           harticulos.borrarArticulos("id", "=", String.valueOf(id));
           
           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0],"Elemento Eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

           listaArticulos.remove(tablaArticulos.getSelectedRow());
           dtm.removeRow(tablaArticulos.getSelectedRow());
           tablaArticulos.setModel(dtm);

       tablaArticulos.getColumnModel().getColumn(0).setPreferredWidth(50); 
       tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150); 
       tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(600);     
           
          } */
                


            }

        });
        
        campoTexto4 = new JTextField("100");
        leyenda5 = new JLabel("Limite:");
        
        panelEncabezado.add(botonAlta);
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(leyenda5);
        panelEncabezado.add(campoTexto4);
        panelEncabezado.add(filtro);
        panelEncabezado.add(campoTexto5);
        panelEncabezado.add(boton1);
        panelEncabezado.add(botonEliminar);
        
        panelFooter.add(leyenda3);
        panelFooter.add(combo3);
        panelFooter.add(boton3);
        panelFooter.add(leyenda3);
        panelFooter.add(campoTexto2);
        panelFooter.add(boton2);
        

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());


        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

      
           
           

           panelTabla.add(new JScrollPane(tablaArticulos));
           panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
           panelPrincipal.add(panelTabla,BorderLayout.CENTER);
           panelPrincipal.add(panelFooter,BorderLayout.SOUTH);
           add(panelPrincipal);


       tablaArticulos.getColumnModel().getColumn(0).setPreferredWidth(50); 
       tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150); 
       tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(600);     

        setBackground(Color.white);
/*           if(CConfiguracion.getPuesto().equals("Ventas"))
        {
            botonAlta.setEnabled(false);
            botonEliminar.setEnabled(false);
        }else if(CConfiguracion.getPuesto().equals("Almacén"))
        {
            botonAlta.setEnabled(false);
            botonEliminar.setEnabled(false);
        }
         else if(CConfiguracion.getPuesto().equals("Reservaciones"))
        {
            botonAlta.setEnabled(false);
            botonEliminar.setEnabled(false);
        }
         else if(CConfiguracion.getPuesto().equals("Area"))
        {
            botonAlta.setEnabled(false);
            botonEliminar.setEnabled(false);
        } */


    }

   public class PSeleccionar_Linea extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaLineas;
    hLineas hlinea;
    Vector vector,encabezadoLineas;
    DefaultTableModel dtm;
    List<Linea> listaLineas;
 
     public PSeleccionar_Linea(){
     tablaLineas = new JTable();
     lineasGuardadas = new ArrayList<Articulolinea>();
     encabezadoLineas = new Vector<String>();
     encabezadoLineas.add("Nombre");

     hlinea = new hLineas();
     listaLineas = hlinea.consultaLineas("","*","");
     vector = new Vector();

       for(Object o: listaLineas){
             Linea lin = (Linea)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getNombre());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoLineas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaLineas.setModel(dtm);
      tablaLineas.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {

                                       int indice = tablaLineas.getSelectedRow();
                                       campoTexto2.setText(listaLineas.get(indice).getNombre());
                                       
                                      // list1.add(listaLineas.get(indice).getNombre());
                                       Articulolinea artlinea = new Articulolinea();
                                       artlinea.setClaveLinea(listaLineas.get(indice).getId());
                                       lineasGuardadas.add(artlinea);

                                      
                                  //     list1.getSize();
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

      }
              );

      

      add(new JScrollPane(tablaLineas));
      setLayout(new GridLayout());
    }

}

   public class PSeleccionar_Ubicacion extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaUbicacion;
    hUbicacion hubicacion;
    Vector vector,encabezadoUbicacion;
    DefaultTableModel dtm;
    List<Ubicacion> listaUbicacion;
    int indice = 0;

    public PSeleccionar_Ubicacion(){
     tablaUbicacion = new JTable();
     ubicacionesGuardadas = new ArrayList<Articuloubicacion>();
     encabezadoUbicacion = new Vector<String>();
     encabezadoUbicacion.add("Ubicación");

     hubicacion = new hUbicacion();
     listaUbicacion = hubicacion.consultaUbicaciones("","*","");
     vector = new Vector();

       for(Object o: listaUbicacion){
             Ubicacion lin = (Ubicacion)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getUbicacion());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoUbicacion) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaUbicacion.setModel(dtm);
      tablaUbicacion.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {
                                       indice = tablaUbicacion.getSelectedRow();
                                       campoTexto2.setText(listaUbicacion.get(indice).getUbicacion());
                                       // list2.add(listaUbicacion.get(indice).getUbicacion());
                                       Articuloubicacion artubicacion = new Articuloubicacion();
                                       artubicacion.setIdubicacion(listaUbicacion.get(indice).getId());
                                       ubicacionesGuardadas.add(artubicacion);
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

      }
              );

      

      add(new JScrollPane(tablaUbicacion));
      setLayout(new GridLayout());
    }

}

public class PSinonimo extends JPanel   {

    JPanel panelPrincipal, panelTabla;
    JTable tablaProveedores;
    Vector vector,encabezadoUbicacion;
    DefaultTableModel dtm;
    List<Articuloproveedor> listaProveedores;
    hArticuloProveedor proveedores;

    public PSinonimo(){

     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());
     proveedores = new hArticuloProveedor();

     tablaProveedores = new JTable();
     proveedoresGuardados = new ArrayList<Articuloproveedor>();

     encabezadoUbicacion = new Vector<String>();
     encabezadoUbicacion.add("Sinónimo");

     
     listaProveedores = new ArrayList<Articuloproveedor>();
     listaProveedores = proveedores.consultaArtProv2("","*","");
     vector = new Vector();

       for(Object o: listaProveedores){
             Articuloproveedor lin = (Articuloproveedor)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getSinonimo());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoUbicacion) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              }; 

      tablaProveedores.setModel(dtm);
      tablaProveedores.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {

                    int indice = tablaProveedores.getSelectedRow();
                    campoTexto2.setText(listaProveedores.get(indice).getSinonimo());

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

      }
              );



       tablaProveedores.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e) {
            }

        }
                );


      panelPrincipal.add(new JScrollPane(tablaProveedores), BorderLayout.CENTER);
      add(panelPrincipal);

    }

}

public class PanelEliminar extends javax.swing.JDialog
{
    JLabel jLabel;
    JRadioButton jRadioButton1,jRadioButton2,jRadioButton3;
    ButtonGroup buttonGroup1;
    GridLayout gridlayout;
    FlowLayout flowlayout;
    JPanel jPanel1,jPanel2;
    String elemento="";
    JButton jButton1,jButton2;
    boolean bandera = false;
    int opcion=0;
    int boton1,boton2,boton3=0;
    
    
        public PanelEliminar (java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

        private void inicializar() {
            this.setTitle("¿Qué deseas hacer?");
            
            
            jPanel1 = new JPanel();           
            jPanel2 = new JPanel();
            
            flowlayout = new FlowLayout();
            jPanel2.setLayout(flowlayout);
            
            gridlayout = new GridLayout(4,1);
            jPanel1.setLayout(gridlayout);
            
            jButton1 = new JButton("Aceptar");
            jButton2 = new JButton("Cancelar");
            
            buttonGroup1 = new ButtonGroup();
            jRadioButton1 = new JRadioButton("Eliminar registro");
            jRadioButton1.setToolTipText("El "+elemento+" no ha sido utilizado aún. ¿Desea eliminarlo?");
                        
            jRadioButton2 = new JRadioButton("Cambiar código de este "+elemento);            
            jRadioButton2.setToolTipText("¿Desea cambiar el código de este "+elemento+"?");
            
            jRadioButton3 = new JRadioButton("Eliminar todos los registros de este "+elemento);            
            jRadioButton3.setToolTipText("¿Desea eliminar la información de este elemento en todas las secciones en donde ha sido usado?");
                    
            jButton1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setBandera(true);
                    if(jRadioButton1.isSelected()) opcion = 1;
                    else if(jRadioButton2.isSelected()) opcion = 2;
                    else opcion = 3;
                    dispose();
                }
            });
            
            jButton2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                  dispose();  
                }
            });
            
            if(boton1 == 1)
            jRadioButton1.setSelected(true);
            else if(boton2 == 1)
            jRadioButton2.setSelected(true);
            else if(boton3 == 1)
            jRadioButton3.setSelected(true);
            
            if(boton1 == 0)
            jRadioButton1.setEnabled(false);
            if(boton2 == 0)
            jRadioButton2.setEnabled(false);
            if(boton3 == 0)
            jRadioButton3.setEnabled(false);
                
            buttonGroup1.add(jRadioButton1);    
            buttonGroup1.add(jRadioButton2);    
            buttonGroup1.add(jRadioButton3);
            
            jPanel1.add(jRadioButton1);
            jPanel1.add(jRadioButton2);
            jPanel1.add(jRadioButton3);
            
            jPanel2.add(jButton1);
            jPanel2.add(jButton2);
            jPanel1.add(jPanel2);
            
            add(jPanel1);
            
            
        }

        public int getOpcion() {
            return opcion;
        }

        public void setOpcion(int opcion) {
            this.opcion = opcion;
        }

        public int getBoton1() {
            return boton1;
        }

        public void setBoton1(int boton1) {
            this.boton1 = boton1;
        }

        public int getBoton2() {
            return boton2;
        }

        public void setBoton2(int boton2) {
            this.boton2 = boton2;
        }

        public int getBoton3() {
            return boton3;
        }

        public void setBoton3(int boton3) {
            this.boton3 = boton3;
        }

        public String getElemento() {
            return elemento;
        }

        public void setElemento(String elemento) {
            this.elemento = elemento;
        }

        public boolean isBandera() {
            return bandera;
        }

        public void setBandera(boolean bandera) {
            this.bandera = bandera;
        }
        
        
        
        
}
     
}
