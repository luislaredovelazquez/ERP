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

public class VCatalogo_Anticipos extends JPanel{
 
    hAnticipos anticipos;
    JTable tabla;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    public static JButton boton1,botonAlta,botonEliminar,botonDevolver;
    Vector vector;
    Vector<String> encabezado;
    List<Anticipos> listaAnticipos;
    DefaultTableModel dtm;
    ImageIcon imagen,imagen2,imagen3,imagenbuscar;//,imagensurtir;
    
        public VCatalogo_Anticipos(){
       tabla = new JTable();

        encabezado = new Vector<String>();
        encabezado.add("Id");
        encabezado.add("Referencia");
        encabezado.add("Fecha");
        encabezado.add("Devuelta");
        encabezado.add("Cancelado");
        encabezado.add("Surtido");
        encabezado.add("Observaciones");
        encabezado.add("Vendido");

        anticipos = new hAnticipos();
        listaAnticipos = new ArrayList<Anticipos>();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;              
                }
              
              @Override
              public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 4) {
              return Boolean.class;
               }
              if (columnIndex == 5) {
              return Boolean.class;
               }
              if (columnIndex == 6) {
              return Boolean.class;
               }
              if (columnIndex == 8) {
              return Boolean.class;
               }
               return super.getColumnClass(columnIndex);
               }
              
              };
          
           
           tabla.setModel(dtm);
           
           tabla.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
               //  botonActualizar.setEnabled(true);
            if(e.getClickCount() == 2)
            {
           
                
            VActualizarAnticipo actualizaanticipos = new VActualizarAnticipo(listaAnticipos.get(tabla.getSelectedRow()));
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            // listaVentas.get(tablaVentas.getSelectedRow())
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualizaanticipos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Anticipos",indice)); 
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
        
   /*     imagensurtir = new ImageIcon(getClass().getResource("/mainicons/surtir.jpg"));
        botonSurtir = new JButton(imagensurtir);
        botonSurtir.setToolTipText("Surtir todos los anticipos");
        botonSurtir.setBackground(Color.white);
        botonSurtir.setBorderPainted(false);
        botonSurtir.setEnabled(CConfiguracion.isSurtirAnticipos());
        botonSurtir.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
         try{
         boolean bandera=true;       
        
         hAnticipos hanticipos = new hAnticipos();
         hPartidasAnticipos hpa = new hPartidasAnticipos();
         hArticulos harticulos = new hArticulos();
         hKardex hkardex = new hKardex();
         
         List<Anticipos> lanticipos = new ArrayList<Anticipos>();
         List<Partidasanticipos> lpa = new ArrayList<Partidasanticipos>();
         List<Articulos> larticulos = new ArrayList<Articulos>();
         List<Articulos> larticulosparaactualizar = new ArrayList<Articulos>();
         
         if(!CPrincipal.getConexion().crearTransaccion())
            return;
         
         larticulos = harticulos.consultaArticulos("", "*", "");
         
         Kardex kardex = new Kardex();
         
        //buscar anticipos que no hayan sido surtidos
         lanticipos = hanticipos.consultaAnticiposNoSurtidos();
         //inicio ciclo        
         int it1 = 0;
         while(it1<lanticipos.size())
         {
             //sobre cada anticipo no surtido buscar todas sus piezas 
             lpa = hpa.consultaPartidas("anticipo", "=", String.valueOf(lanticipos.get(it1).getId()));
             // iterar sobre cada pieza
             int it2=0;
             while(it2<lpa.size())
             {
                //verificar que la pieza esté no surtida pero tenga código, sino tiene código poner bandera en false
                 if(lpa.get(it2).getSurtido()==0 && !lpa.get(it2).getCodigoarticulo().isEmpty())
                 {
                //a la pieza no surtida con código verificar en articulos si la existencia es mayor o igual a la cantidad solicitada, si es mayor la cantidad poner bandera en false                                  
              int it3=0;
              while(it3<larticulos.size())
              {
                if(larticulos.get(it3).getCodigo().equalsIgnoreCase(lpa.get(it2).getCodigoarticulo()))  
                {
                if(larticulos.get(it3).getExistencia() >= lpa.get(it2).getCantidad())  
                { 
                //si es así, la cantidad solcitada se descuenta de existencias y se agrega a anticipos en artículos y se actualiza articulos
                    
                larticulos.get(it3).setExistencia(larticulos.get(it3).getExistencia()-lpa.get(it2).getCantidad());    
                larticulos.get(it3).setAnticipos(larticulos.get(it3).getAnticipos()+lpa.get(it2).getCantidad());
                larticulos.get(it3).setBloqueado(1);
                harticulos.actualizarArticulos(larticulos.get(it3), "codigo", "=", larticulos.get(it3).getCodigo());    
                
                //se pone como surtida la partida y se actualiza la partida
                lpa.get(it2).setSurtido(1);
                hpa.actualizarPartidasLast(lpa.get(it2), "id", "=", String.valueOf(lpa.get(it2).getId()));
                
                //se registra la pieza en kardex
                kardex = hkardex.consultaUltimoRenglon("articulo", "=", lpa.get(it2).getCodigoarticulo());
                if(kardex == null)
                {
                    //Se registra la pieza en Kardex
                    kardex = new Kardex();
                    kardex.setAlmacenista("");
                    kardex.setAnticipos(larticulos.get(it3).getAnticipos());
                    kardex.setArticulo(larticulos.get(it3).getCodigo());
                    kardex.setEntrada(0);
                    kardex.setExistencias((int)larticulos.get(it3).getExistencia());
                    kardex.setIdArticulo(larticulos.get(it3).getId());
                    kardex.setModificacion("Registro");
                    kardex.setMovimiento("Registro de Artículo");
                    kardex.setNoMov("1");
                    kardex.setPrecioVenta(larticulos.get(it3).getPrecioVenta());
                    kardex.setRefFerrari("Registro");
                    kardex.setReservados((int)larticulos.get(it3).getReservado());
                    kardex.setResponsable("1");
                    kardex.setResponsable2("Registro Automático");
                    kardex.setSalida(0);
                    kardex.setUltimoCosto(larticulos.get(it3).getUltimoCosto());
                    kardex.setVendidoEn(0.0);
                    
                    hkardex.guardarEnKardex(kardex); 
                    //19
                }
                
                    Kardex renglon = new Kardex();    
                
                    renglon.setAlmacenista("");
                    renglon.setAnticipos(kardex.getAnticipos()+ lpa.get(it2).getCantidad());
                    renglon.setArticulo(kardex.getArticulo());
                    renglon.setEntrada(0);
                    renglon.setExistencias(kardex.getExistencias()-lpa.get(it2).getCantidad());
                    renglon.setIdArticulo(kardex.getIdArticulo());
                    renglon.setModificacion("Registro de Anticipo");
                    renglon.setMovimiento("Registro de Anticipo con Folio "+lanticipos.get(it1).getId());
                    renglon.setNoMov(String.valueOf(Integer.valueOf(kardex.getNoMov())+1));
                    renglon.setPrecioVenta(lpa.get(it2).getPrecioVenta());
                    renglon.setRefFerrari("Anti"+lanticipos.get(it1).getTipo().substring(0,1) +lanticipos.get(it1).getReferencia());
                    renglon.setReservados(kardex.getReservados());
                    renglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                    renglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                    renglon.setSalida(0);
                    renglon.setUltimoCosto(lpa.get(it2).getPrecioCompra());
                    renglon.setVendidoEn(lpa.get(it2).getPrecioVenta());
                    hkardex.guardarEnKardex(renglon); 
                //se lanza el alert  
                JOptionPane.showMessageDialog(null, "Se ha surtido el artículo "+lpa.get(it2).getCodigoarticulo() +"\n"+
                                                    "del anticipo "+lanticipos.get(it1).getId()+" y referencia "+lanticipos.get(it1).getReferencia());
                    
                }else{bandera = false;}      
                }else{bandera = false;}
                it3++;  
              }
                 
                 }else{bandera = false;}
                it2++; 
             }
             
             
             if(bandera)
             {
              lanticipos.get(it1).setSurtido(1);   
              hanticipos.actualizarAnticipos(lanticipos.get(it1), "id", "=", String.valueOf(lanticipos.get(it1).getId()));
             }                 
             
             //si bandera es true actualizar anticipo a surtido
             
               
             
             it1++;
         }
         
         
        
        CPrincipal.getConexion().finalizarTransaccion();
        
        
                }catch(Exception ex1)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            ex1.printStackTrace();            
        }        
                
                
            }

        });     */   

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaReservaciones());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               VRealizarAnticipo altaAnticipos = new VRealizarAnticipo();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaAnticipos), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Realizar Anticipo",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }

        });

        
        
    leyenda1 = new JLabel("Buscar ventas en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Id");
        combo1.addItem("Referencia");
        combo1.addItem("Fecha");
        combo1.addItem("Devuelta");
        combo1.addItem("Cancelado");
        combo1.addItem("Surtido");
        combo1.addItem("Observaciones");
        combo1.addItem("Vendido");

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
        boton1.setEnabled(CConfiguracion.isBuscarAnticipos());
          boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

    //    Vector<String> encabezadoArticulos = new Vector<String>();
        String valores [] = {"id","referencia","fecha","devuelta",
        "cancelado","surtido","observaciones","ticket"};
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
        
         if(combo1.getSelectedItem().toString().equals("Todos"));
         listaAnticipos = anticipos.consultaAnticipos(campo,compara,condicion);
         vector = new Vector();

         for(Object o: listaAnticipos){
             Anticipos iventas = (Anticipos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iventas.getId());
             unaFila.add(iventas.getReferencia());
             unaFila.add(iventas.getFecha());
             if(Integer.compare(iventas.getDevuelta(), 0)==0)
             unaFila.add(false);   
             else
             unaFila.add(true);       
             if(Integer.compare(iventas.getCancelado(), 0)==0)
             unaFila.add(false);   
             else
             unaFila.add(true);
             if(Integer.compare(iventas.getSurtido(), 0)==0)
             unaFila.add(false);   
             else
             unaFila.add(true);
             unaFila.add(iventas.getObservaciones());
             if(Integer.compare(iventas.getTicket(), 0)==0)
             unaFila.add(false);   
             else
             unaFila.add(true);             
             vector.add(unaFila);

         }

            dtm = new DefaultTableModel(vector,encabezado) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              @Override
              public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 3) {
              return Boolean.class;
               }
              if (columnIndex == 4) {
              return Boolean.class;
               }
              if (columnIndex == 5) {
              return Boolean.class;
               }
              if (columnIndex == 7) {
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
        botonEliminar.setToolTipText("Cancelar");
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isCancelarAnticipos());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
        try {
            if(tabla.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
                
             if(listaAnticipos.get(tabla.getSelectedRow()).getCancelado() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Este anticipo ya ha sido cancelado");
                 return;
             }
             
              if(listaAnticipos.get(tabla.getSelectedRow()).getDevuelta() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Este anticipo ya ha sido devuelto");
                 return;
             }
              
              if(listaAnticipos.get(tabla.getSelectedRow()).getTicket() == 1) 
             {
                 JOptionPane.showMessageDialog(null, "Este anticipo ya ha sido vendido, intente devolverlo, no cancelarlo");
                 return;
             }
            
             int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de realizar esta acción?", "Confirmación", JOptionPane.YES_NO_OPTION);
             
             if(confirmacion == JOptionPane.YES_OPTION) 
             {
                //en caso de que el anticipo ya haya sido surtido y se tenga que cancelar
                 
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
             hPartidasAnticipos hparanti = new hPartidasAnticipos();
             List<Partidasanticipos> paranti = new ArrayList<Partidasanticipos>();
             paranti = hparanti.consultaPartidas("anticipo", "=", String.valueOf(listaAnticipos.get(tabla.getSelectedRow()).getId()));
             int cantidadKardex=0;
             int it1 = 0;
             
                 while(it1 < paranti.size()){
                     
                 if(paranti.get(it1).getSurtido() == 1)    
                 {
                 articulos = harticulos.consultaArticulos("codigo", "=", String.valueOf(paranti.get(it1).getCodigoarticulo()));
                 articulos.get(0).setExistencia(articulos.get(0).getExistencia() + paranti.get(it1).getCantidad());
                 articulos.get(0).setAnticipos(articulos.get(0).getAnticipos() - paranti.get(it1).getCantidad());
                 
                 
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
                      
                  cantidadKardex = kardex.getExistencias() + paranti.get(it1).getCantidad();
                  renglon.setExistencias(cantidadKardex);
                  renglon.setAlmacenista("");
                  renglon.setEntrada(paranti.get(it1).getCantidad());
                  renglon.setIdArticulo(articulos.get(0).getId());
                  renglon.setArticulo(articulos.get(0).getCodigo());
                  renglon.setModificacion("Cancelación Surtido de Anticipo");
                  renglon.setMovimiento("Cancelación Surtido de Anticipo con Folio "+paranti.get(it1).getAnticipo());
                  renglon.setNoMov(String.valueOf(Long.valueOf(kardex.getNoMov())+1));    
                  renglon.setPrecioVenta(paranti.get(it1).getPrecioVenta());
                  renglon.setRefFerrari("Anti"+listaAnticipos.get(tabla.getSelectedRow()).getTipo().substring(0,1) +paranti.get(it1).getAnticipo());
                  renglon.setReservados(kardex.getReservados());
                  renglon.setAnticipos(kardex.getAnticipos()-paranti.get(it1).getCantidad());      
                  renglon.setResponsable(String.valueOf(CConfiguracion.getId()));
                  renglon.setSalida(0);
                  renglon.setUltimoCosto(paranti.get(it1).getPrecioCompra());
                  renglon.setResponsable2(CConfiguracion.getNombres()+" "+CConfiguracion.getApellidoP()+" "+CConfiguracion.getApellidoM());
                  renglon.setVendidoEn(paranti.get(it1).getPrecioVenta());
                  
                  
                  if(!CGlobalConfig.isWeb())
                  hkardex.guardarEnKardex(renglon);
                  else
                  transaccion = transaccion + hkardex.crearQueryGuardarEnKardexCompleto(renglon) + " ";    
                 }
                 
                 it1++;
             }
             
             
             
            listaAnticipos.get(tabla.getSelectedRow()).setCancelado(1);    
            if(!CGlobalConfig.isWeb())
            anticipos.actualizarAnticipos(listaAnticipos.get(tabla.getSelectedRow()), "id", "=", String.valueOf(listaAnticipos.get(tabla.getSelectedRow()).getId()));    
            else
            transaccion = transaccion + anticipos.crearQueryActualizarAnticipos(listaAnticipos.get(tabla.getSelectedRow()), "id", "=", String.valueOf(listaAnticipos.get(tabla.getSelectedRow()).getId())) + " ";             
            
            CPrincipal.getConexion().finalizarTransaccion();
            
            
            
            JOptionPane.showMessageDialog(null, "Cancelación Realizada");
            
             if(CGlobalConfig.isWeb())
               {
               transaccion = transaccion + conexionweb.finalizarTransaccion();    
               conexionweb.escribirEnWeb(transaccion);
               }
            
            boton1.doClick(); 
            
            //en caso de que el anticipo ya haya sido surtido y se tenga que cancelar
             } 
            
        }catch(Exception ex2)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            ex2.printStackTrace();            
        }
             
            }


        });
        
        imagen3 = new ImageIcon(getClass().getResource("/mainicons/lessF.jpg"));
        botonDevolver = new JButton(imagen3);
        botonDevolver.setToolTipText("Devolver o Facturar");
        botonDevolver.setBackground(Color.white);
        botonDevolver.setBorderPainted(false);
        botonDevolver.setEnabled(CConfiguracion.isCancelarVentaAnticipos());
        botonDevolver.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
         
         if(tabla.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }     
                
              if(listaAnticipos.get(tabla.getSelectedRow()).getTicket() == 1) 
             {
            List<Ventaanticipos> ventaanti = new ArrayList<Ventaanticipos>();  
            hVentaAnticipos hventaa = new hVentaAnticipos();
            ventaanti = hventaa.consultaVentas("anticipo", "=", String.valueOf(listaAnticipos.get(tabla.getSelectedRow()).getId()));
            VDetalle_VentasAnticipos actualizaVentas = new VDetalle_VentasAnticipos(ventaanti.get(0));
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            // listaVentas.get(tablaVentas.getSelectedRow())
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualizaVentas), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle de Ventas",indice)); 
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);
            
             }
                
               
                
            // listaAnticipos.get(tabla.getSelectedRow()).setDevuelta(1);    
            // anticipos.actualizarAnticipos(listaAnticipos.get(tabla.getSelectedRow()), "id", "=", String.valueOf(listaAnticipos.get(tabla.getSelectedRow())));    

            }


        });

//        panelEncabezado.add(botonSurtir);
        panelEncabezado.add(botonAlta);
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        panelEncabezado.add(botonEliminar);
        panelEncabezado.add(botonDevolver);

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
        
/*          if(CConfiguracion.getPuesto().equals("Ventas"))
        {
            botonDevolver.setEnabled(false);
            botonEliminar.setEnabled(false);
            
        }else if(CConfiguracion.getPuesto().equals("Almacén"))
        {
            botonDevolver.setEnabled(false);
            botonEliminar.setEnabled(false);
            
        }  */

     } 
}
