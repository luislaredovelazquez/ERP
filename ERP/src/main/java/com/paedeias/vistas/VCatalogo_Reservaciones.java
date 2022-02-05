/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.helpers.*;
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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALL
 */
public class VCatalogo_Reservaciones extends JPanel{
    
    hReservaciones reservaciones;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar,botonDevolver;
    Vector vector;
    Vector<String> encabezado;
    List<Reservaciones> lista;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagenbuscar;
    
    public VCatalogo_Reservaciones()
    {
         tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Id");
        encabezado.add("Fecha");
        encabezado.add("Cliente");
        encabezado.add(CGlobalConfig.getCampor6());
        encabezado.add(CGlobalConfig.getCampor1());
        encabezado.add(CGlobalConfig.getCampor7());
        encabezado.add("Facturada");
        encabezado.add("Devolución");
        encabezado.add("Cancelada");


        reservaciones = new hReservaciones();
        lista = new ArrayList<Reservaciones>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              
              @Override
              public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 8) {
              return Boolean.class;
               }
              if (columnIndex == 7) {
              return Boolean.class;
               }
              if (columnIndex == 6) {
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
             VActualiza_Reservacion actualizaanticipos = new VActualiza_Reservacion(lista.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent()).add(new JScrollPane(actualizaanticipos), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Reservación",indice));  
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
        botonAlta.setEnabled(CConfiguracion.isAltaReservaciones());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
           /* VRealizarAnticipo altaAnticipos = new VRealizarAnticipo();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaAnticipos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Realizar Anticipo",indice)); */
             VAlta_Reservacion altaReservacion = new VAlta_Reservacion();
                int indice = ((JTabbedPane)getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent()).add(new JScrollPane(altaReservacion), indice);
            ((JTabbedPane)getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
            }
        });
        leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Fecha");
        combo1.addItem("Cliente");
        combo1.addItem(CGlobalConfig.getCampor6());
        combo1.addItem(CGlobalConfig.getCampor1());
        combo1.addItem(CGlobalConfig.getCampor7());
        combo1.addItem("Facturada");
        combo1.addItem("Devolución");
        combo1.addItem("Cancelada");

        combo2 = new JComboBox();
        combo2.addItem("igual a");
        combo2.addItem("como");
        combo2.addItem("Todos");

        campoTexto1 = new JTextField(20);
        imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
        boton1 = new JButton(imagenbuscar);
        boton1.setBackground(Color.white);
        boton1.setBorderPainted(false);
        boton1.setEnabled(CConfiguracion.isBuscarReservaciones());
        boton1.setToolTipText("Buscar");
    
      boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
        String valores [] = {"id","fecha","nomCli","tipoAuto","siniestro","placas","facturada","devolucion",
        "cancelada"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         lista = reservaciones.consultaReservaciones(campo,compara,condicion);
         vector = new Vector();

         for(Object o: lista){
             Reservaciones iventas = (Reservaciones)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getFecha());
             unaFila.add(iventas.getNomCli());
             unaFila.add(iventas.getTipoAuto());
             unaFila.add(iventas.getSiniestro());
             unaFila.add(iventas.getPlacas());
             if(Integer.compare(iventas.getFacturada(), 1)==0)
             unaFila.add(true);
             else
             unaFila.add(false);    
             if(Integer.compare(iventas.getDevolucion(), 1)==0)
             unaFila.add(true);
             else
             unaFila.add(false);    
             if(Integer.compare(iventas.getCancelada(), 1)==0)
             unaFila.add(true);
             else
             unaFila.add(false);    
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }

            
              @Override
              public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 8) {
              return Boolean.class;
               }
              if (columnIndex == 7) {
              return Boolean.class;
               }
              if (columnIndex == 6) {
              return Boolean.class;
               }
               return super.getColumnClass(columnIndex);
                }
              };
           tabla.setModel(dtm);

            }
        });
        
          
        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEliminarReservaciones());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
             if(tabla.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             } 
               int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer cancelar esta reservación no vendida?", "Confirmar", JOptionPane.YES_NO_OPTION);    
            
            if(respuesta == JOptionPane.YES_OPTION)
            {
                
              if(lista.get(tabla.getSelectedRow()).getCancelada() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Esta reservación ya ha sido cancelada");
                 return;
             }
             
              if(lista.get(tabla.getSelectedRow()).getDevolucion() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Esta reservación ya ha sido devuelta");
                 return;
             }
              
              if(lista.get(tabla.getSelectedRow()).getFacturada() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Esta reservación ya ha sido vendida, intente devolver la venta");
                 return;
             }
            
             String transaccion="";
             ConexionWeb conexionweb = new ConexionWeb();
              
            if(!CPrincipal.getConexion().crearTransaccion())
            return;    
            
             if(CGlobalConfig.isWeb())
             transaccion = conexionweb.iniciarTransaccion() + " "; 
                 
             Kardex kardex = new Kardex(); 
             hKardex hkardex = new hKardex(); 
                
             hArticulos harticulos = new hArticulos();   
             List<Articulos> articulos = new ArrayList<Articulos>();   
             hPartidasReservaciones hparres = new hPartidasReservaciones();
             List<Partidasreservaciones> parres = new ArrayList<Partidasreservaciones>();
             parres = hparres.consultaPReservaciones("codRes", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId()));
             int cantidadKardex=0;
             int it1 = 0;
                
                 while(it1 < parres.size()){
                     

                 articulos = harticulos.consultaArticulos("codigo", "=", String.valueOf(parres.get(it1).getCodArt()));
                 articulos.get(0).setExistencia(articulos.get(0).getExistencia() + parres.get(it1).getCantidad());
                 articulos.get(0).setReservado(articulos.get(0).getReservado() - parres.get(it1).getCantidad());
                 
                 if(!CGlobalConfig.isWeb())
                 harticulos.actualizarArticulos(articulos.get(0), "codigo", "=", articulos.get(0).getCodigo());
                 else
                 transaccion = transaccion + harticulos.crearQueryActualizarArticulos(articulos.get(0), "codigo", "=", articulos.get(0).getCodigo()) + " ";    
                 
                  Kardex renglon = new Kardex();
                  kardex = hkardex.consultaUltimoRenglon("articulo", "=", articulos.get(0).getCodigo());
                  
                 if(kardex == null)
                {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(articulos.get(0).getAnticipos());
                    kardex.setArticulo(articulos.get(0).getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)articulos.get(0).getExistencia());
                    kardex.setIdArticulo(articulos.get(0).getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(articulos.get(0).getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)articulos.get(0).getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(articulos.get(0).getUltimoCosto());
                    kardex.setVendidoEn(0.0);
                    
                    if(!CGlobalConfig.isWeb())
                    hkardex.guardarEnKardex(kardex); 
                    else
                    transaccion = transaccion + hkardex.crearQueryGuardarEnKardexCompleto(kardex) + " ";    
                    //19
                }
                      
                  cantidadKardex = kardex.getExistencias() + parres.get(it1).getCantidad();
                  renglon.setExistencias(cantidadKardex);
                  renglon.setAlmacenista("");
                  renglon.setEntrada(parres.get(it1).getCantidad());
                  renglon.setIdArticulo(articulos.get(0).getId());
                  renglon.setArticulo(articulos.get(0).getCodigo());
                  renglon.setModificacion("Cancelación de Reservación");
                  renglon.setMovimiento("Cancelación de Reservación "+parres.get(it1).getCodRes());
                  renglon.setNoMov(String.valueOf(Long.valueOf(kardex.getNoMov())+1));    
                  renglon.setPrecioVenta(parres.get(it1).getPrecio());
                  renglon.setRefFerrari("CRese"+parres.get(it1).getCodRes());
                  renglon.setReservados(kardex.getReservados()-parres.get(it1).getCantidad());
                  renglon.setAnticipos(kardex.getAnticipos());      
                  renglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                  renglon.setSalida(0);
                  renglon.setUltimoCosto(parres.get(it1).getCosto());
                  renglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                  renglon.setVendidoEn(0.00);
                  
                  
                  if(!CGlobalConfig.isWeb())
                  hkardex.guardarEnKardex(renglon);
                  else
                  transaccion = transaccion + hkardex.crearQueryGuardarEnKardexCompleto(renglon) + " ";     
                 
                 
                 it1++;
             }
             
             
             
                
            lista.get(tabla.getSelectedRow()).setCancelada(1);    
            lista.get(tabla.getSelectedRow()).setDevolucion(1);
            
            if(!CGlobalConfig.isWeb())
            reservaciones.actualizarReservaciones(lista.get(tabla.getSelectedRow()), "id", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId()));       
            else
            transaccion = transaccion + reservaciones.crearQueryActualizarReservaciones(lista.get(tabla.getSelectedRow()), "id", "=", String.valueOf(lista.get(tabla.getSelectedRow()).getId())) + " ";         
            
            
            CPrincipal.getConexion().finalizarTransaccion();
            
             if(CGlobalConfig.isWeb())
               {
               transaccion = transaccion + conexionweb.finalizarTransaccion();    
               conexionweb.escribirEnWeb(transaccion);
               }
            
            boton1.doClick(); 
            JOptionPane.showMessageDialog(null,"Reservación Cancelada");       
            }else
            return;
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
        
        panelPrincipal.setBackground(Color.white);
        panelEncabezado.setBackground(Color.white);
        panelTabla.setBackground(Color.white);

        panelTabla.add(new JScrollPane(tabla));
        panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
        panelPrincipal.add(panelTabla,BorderLayout.CENTER);
        add(panelPrincipal);

        setBackground(Color.white);
        
    /*      if(CConfiguracion.getPuesto().equals("Reservaciones"))
        {
          //  jButton3.setEnabled(false);
          //  jButton4.setEnabled(false);
            botonEliminar.setEnabled(false);
            botonDevolver.setEnabled(false);
        } */
    
    
    }
    
}
