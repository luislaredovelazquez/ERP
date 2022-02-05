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
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_Usuarios extends JPanel{

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1;
    JButton boton1,boton2,botonBuscar;
    //JButton boton3;
    JComboBox combo1,combo2;
    ImageIcon imagen,imagen2,imagenbuscar;
    JTable tablaUsuarios;
    hUsuarios husuarios;
    hPermisos hpermisos;
    Vector vector,encabezadoUsuarios;
    DefaultTableModel dtm;
    List<Usuarios> listaUsuarios;
    String idUsus="",codigoUsus="",codigoUsus2="",codigoUsus3=""; 
         

public VCatalogo_Usuarios()
    {
     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());
     panelPrincipal.setBackground(Color.white);

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelEncabezado.setBackground(Color.white);

     etiqueta1 = new JLabel("Buscar Usuarios donde: ");

     combo1 = new JComboBox();
     combo1.addItem("Id");
     combo1.addItem("Nombre");
     combo1.addItem("Apellido Materno");
     combo1.addItem("Apellido Paterno");
     combo1.addItem("Usuario");
     combo1.addItem("Puesto");
     combo1.addItem("Correo");

     etiqueta2 = new JLabel("sea ");

     combo2 = new JComboBox();
     combo2.addItem("igual a");
     combo2.addItem("como");
     combo2.addItem("Todos");

     campoTexto1 = new JTextField(20);

     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setEnabled(CConfiguracion.isAltaUsuarios());
     boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            VAlta_Usuarios altausuarios = new VAlta_Usuarios();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altausuarios), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }});
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);


     imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
     boton2 = new JButton();
     boton2.setEnabled(CConfiguracion.isEliminarUsuarios());
     boton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
             if(tablaUsuarios.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }  
            
             hAnticipos hanticipos = new hAnticipos();
             hPartidasAnticipos hpartidasanticipos = new hPartidasAnticipos();
             hArticulosEnMostrador harticulosenmostrador = new hArticulosEnMostrador();
             hPartidasCompras hpartidascompras = new hPartidasCompras();
             hComprasMayoreo hcomprasmayoreo = new hComprasMayoreo();
             hPartidasComprasMayoreo hpartidascomprasmayoreo = new hPartidasComprasMayoreo();
             hPartidasDevoluciones hpartidasdevoluciones = new hPartidasDevoluciones();
             hDevoluciones hdevoluciones = new hDevoluciones();
             hPartidasfacturasCFDI hpartidasfacturacfdi = new hPartidasfacturasCFDI();
             hFacturasCFDI hfacturacfdi = new hFacturasCFDI();
             hKardex hkardex = new hKardex();
             hPedidosVentas hpedidosventas = new hPedidosVentas();
             hPartidaVentaAnticipos hpartidaventaanticipos = new hPartidaVentaAnticipos();
             hVentaAnticipos hventaanticipos = new hVentaAnticipos();
             hPartidas hpartidas = new hPartidas();
             hVentas hventas = new hVentas();
             hUsuarios husuarios = new hUsuarios();
             hPermisos hpermisos = new hPermisos();
             
             Long id = listaUsuarios.get(tablaUsuarios.getSelectedRow()).getId();
             String cadenaID = String.valueOf(id);
             String cadenaLogin = listaUsuarios.get(tablaUsuarios.getSelectedRow()).getNombres() +" "+ listaUsuarios.get(tablaUsuarios.getSelectedRow()).getApellidoP() +" "+ listaUsuarios.get(tablaUsuarios.getSelectedRow()).getApellidoM();
             String cadenaLogin2 = listaUsuarios.get(tablaUsuarios.getSelectedRow()).getNombres() +" "+ listaUsuarios.get(tablaUsuarios.getSelectedRow()).getApellidoP() + listaUsuarios.get(tablaUsuarios.getSelectedRow()).getApellidoM();
             String cadenaLogin3 = listaUsuarios.get(tablaUsuarios.getSelectedRow()).getFoto();
             
             List<Anticipos> listUs1 = hanticipos.consultaAnticipos("idVendedor", "=", cadenaID);
             List<ComprasMayoreo> listUs4 = hcomprasmayoreo.consultaCompras("idUsuario", "=", cadenaID);
             List<Devoluciones> listUs5 = hdevoluciones.consultaVentas("idusuario", "=", cadenaID);
             List<Factura> listUs6 = hfacturacfdi.consultaFacturas("idEmisorF", "=", cadenaID);
             List<Ventaanticipos> listUs9 = hventaanticipos.consultaVentas("idusuario", "=", cadenaID);
             List<Ventas> listUs10 = hventas.consultaVentas("idusuario", "=", cadenaID);
             List<ArticulosEnMostrador> listUs2 = harticulosenmostrador.consultaArticulosEnMostrador("responsable", "=", cadenaID);
             List<Kardex> listUs7 = hkardex.consultaKardexArticulo("responsable", "=", cadenaID);
             List<Pedidosventas> listUs8 = hpedidosventas.consultaPedidosVentas("vendedor", "=", cadenaID);
             
             //agregar dobles...
             List<Devoluciones> listUsd5 = hdevoluciones.consultaVentas("idadministrador", "=", cadenaID);
             List<Ventaanticipos> listUsd9 = hventaanticipos.consultaVentas("idadministrador", "=", cadenaID);
             List<Ventas> listUsd10 = hventas.consultaVentas("idadministrador", "=", cadenaID);
             
             for(int i=0; i<listUsd5.size(); i++)
              {
               listUs5.add(listUsd5.get(i));
              }
             
              for(int i=0; i<listUsd9.size(); i++)
              {
               listUs9.add(listUsd9.get(i));
              }
             
              for(int i=0; i<listUsd10.size(); i++)
              {
               listUs10.add(listUsd10.get(i));
              }
              
             boolean banderaEliminar=false;
             
          VCatalogo_Usuarios.PanelEliminar panelEliminar = new VCatalogo_Usuarios.PanelEliminar((JFrame)Window.getWindows()[0],true);
          panelEliminar.setElemento("Usuario");
          if(listUs1.isEmpty()&&listUs2.isEmpty()&&listUs4.isEmpty()&&listUs5.isEmpty()
             &&listUs6.isEmpty()&&listUs7.isEmpty()&&listUs8.isEmpty()&&listUs9.isEmpty()&&listUs10.isEmpty())
          panelEliminar.setBoton2(0);
          else
          {
          panelEliminar.setBoton2(1);
          banderaEliminar = true;
          }
          panelEliminar.setBoton3(0);
         
          if(banderaEliminar)
          panelEliminar.setBoton1(0);
          else
          panelEliminar.setBoton1(1);
          
          panelEliminar.inicializar();
          panelEliminar.setSize(300, 200);
          panelEliminar.setLocationRelativeTo(null);
          panelEliminar.setVisible(true);  
          
           if(panelEliminar.isBandera())
          {
          
          int respuesta = JOptionPane.showConfirmDialog((JFrame)Window.getWindows()[0],
                   "Esta acción eliminará todos los registros del usuario en el sistema de modo irrecuperable \n ¿Desea Continuar?", "Eliminar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

           if(respuesta == JOptionPane.YES_OPTION)
           {
                            switch(panelEliminar.getOpcion())
              {
                                case 1: 

            husuarios.borrarUsuarios("id", "=",cadenaID);
            hpermisos.borrarPermisos("idUsuario", "=", cadenaID);
            JOptionPane.showMessageDialog(null, "Usuario Eliminado");
            listaUsuarios.remove(tablaUsuarios.getSelectedRow());
            vector.remove(tablaUsuarios.getSelectedRow());
            dtm.fireTableRowsDeleted(1, 1);
            tablaUsuarios.setModel(dtm);
                                    
                                    break;
                                case 2:
                                    
        VCatalogo_Usuarios.PSeleccionar_Cliente seleccionar = new VCatalogo_Usuarios.PSeleccionar_Cliente();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Reemplazar por",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);     
        
        hanticipos.actualizarUsuario("idVendedor", idUsus, cadenaID);
        hcomprasmayoreo.actualizarUsuario("idUsuario", idUsus, cadenaID);
        hdevoluciones.actualizarUsuario("idusuario", idUsus, cadenaID); 
        hdevoluciones.actualizarUsuario("idadministrador", idUsus, cadenaID);
        hfacturacfdi.actualizarUsuario("idEmisorF", idUsus, cadenaID);
        hventaanticipos.actualizarUsuario("idusuario", idUsus, cadenaID); 
        hventaanticipos.actualizarUsuario("idadministrador", idUsus, cadenaID);
        hventas.actualizarUsuario("idusuario", idUsus, cadenaID); 
        hventas.actualizarUsuario("idadministrador", idUsus, cadenaID);
        harticulosenmostrador.actualizarUsuario("responsable", idUsus, cadenaID);
        hkardex.actualizarUsuario("responsable", idUsus, cadenaID);
        hkardex.actualizarUsuario("responsable2",codigoUsus, cadenaLogin);
        hkardex.actualizarUsuario("responsable2",codigoUsus2, cadenaLogin2);
        hkardex.actualizarUsuario("responsable2",codigoUsus3, cadenaLogin3);
        hpedidosventas.actualizarUsuario("vendedor", idUsus, cadenaID);
             
        husuarios.borrarUsuarios("id", "=",cadenaID);
        hpermisos.borrarPermisos("idUsuario", "=", cadenaID);
        
        JOptionPane.showMessageDialog(null, "Usuario Reemplazado");
        listaUsuarios.remove(tablaUsuarios.getSelectedRow());
        vector.remove(tablaUsuarios.getSelectedRow());
        dtm.fireTableRowsDeleted(1, 1);
        tablaUsuarios.setModel(dtm);
                                    break;
        /*                        case 3:
            for(int i=0; i<listUs1.size(); i++)
              {
               hpartidasanticipos.borrarPartidas("anticipo", "=", String.valueOf(listUs1.get(i).getId()));
              }
              hanticipos.borrarAnticipos("idVendedor", "=", cadenaID); 
                                    
              for(int i=0; i<listUs3.size(); i++)
              {
               hpartidascompras.borrarPCompras("compra", "=", String.valueOf(listUs3.get(i).getId()));
              }
              hcompras.borrarCompras("idUsuario", "=", cadenaID);     
              
              for(int i=0; i<listUs4.size(); i++)
              {
               hpartidascomprasmayoreo.borrarPCompras("compra", "=", String.valueOf(listUs4.get(i).getId()));
              }
              hcomprasmayoreo.borrarCompras("idUsuario", "=", cadenaID); 
              
              for(int i=0; i<listUs5.size(); i++)
              {
               hpartidasdevoluciones.borrarPartidas("idVenta", "=", String.valueOf(listUs5.get(i).getId()));
              }
              hdevoluciones.borrarVentas("idusuario", "=", cadenaID); 
              hdevoluciones.borrarVentas("idadministrador", "=", cadenaID);
              
              for(int i=0; i<listUs6.size(); i++)
              {
               hpartidasfacturacfdi.borrarPartidas("idFactura", "=", String.valueOf(listUs6.get(i).getIdFactura()));
              }
              hfacturacfdi.borrarFactura("idEmisorF", "=", cadenaID); 
              
              for(int i=0; i<listUs9.size(); i++)
              {
               hpartidaventaanticipos.borrarPartidas("idVenta", "=", String.valueOf(listUs9.get(i).getId()));
              }
              hventaanticipos.borrarVentas("idusuario", "=", cadenaID); 
              hventaanticipos.borrarVentas("idadministrador", "=", cadenaID);
              
              for(int i=0; i<listUs10.size(); i++)
              {
               hpartidas.borrarPartidas("idVenta", "=", String.valueOf(listUs10.get(i).getId()));
              }
              hventas.borrarVentas("idusuario", "=", cadenaID); 
              hventas.borrarVentas("idadministrador", "=", cadenaID);
              
             harticulosenmostrador.borrarArticulosEnMostrador("responsable", "=", cadenaID);
             hkardex.borrarKardex("responsable", "=", cadenaID);
             hkardex.borrarKardex("responsable2", "=", cadenaLogin);
             hkardex.borrarKardex("responsable2", "=", cadenaLogin2);
             hkardex.borrarKardex("responsable2", "=", cadenaLogin3);
             hpedidosventas.borrarPedidosVentas("vendedor", "=", cadenaID);
             
            husuarios.borrarUsuarios("id", "=",cadenaID);
            hpermisos.borrarPermisos("idUsuario", "=", cadenaID);
            
            JOptionPane.showMessageDialog(null, "Usuario Eliminado");
            listaUsuarios.remove(tablaUsuarios.getSelectedRow());
            vector.remove(tablaUsuarios.getSelectedRow());
            dtm.fireTableRowsDeleted(1, 1);
            tablaUsuarios.setModel(dtm);
              
              break;    */
                                
                                
                                
              } 
           }
              
          }
        
            }


     });
     boton2.setBackground(Color.white);
     boton2.setBorderPainted(false);
     boton2.setIcon(imagen2);


     imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
     botonBuscar = new JButton(imagenbuscar);
     botonBuscar.setToolTipText("Buscar");
     botonBuscar.setBackground(Color.white);
     botonBuscar.setBorderPainted(false);
     botonBuscar.setEnabled(CConfiguracion.isBuscarUsuarios());
     botonBuscar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

        String valores [] = {"id","nombres","apellidoM","apellidoP","foto","puesto","correo"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

                listaUsuarios = husuarios.consultaUsuarios(campo,compara,condicion);
                vector = new Vector();

       for(Object o: listaUsuarios){
             Usuarios lin = (Usuarios)o;
             Vector<Object> unaFila = new Vector<Object>();
             String nombre = lin.getNombres()+ " "+lin.getApellidoP()+" "+lin.getApellidoM();
             unaFila.add(lin.getId());
             unaFila.add(nombre);
             unaFila.add(lin.getFoto());
             unaFila.add(lin.getPuesto());
             unaFila.add(lin.getCorreo());
             vector.add(unaFila);

         }
       dtm = new DefaultTableModel(vector,encabezadoUsuarios) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaUsuarios.setModel(dtm);     
            }
     });

          panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());

     tablaUsuarios = new JTable();
     encabezadoUsuarios = new Vector<String>();
     encabezadoUsuarios.add("Id");
     encabezadoUsuarios.add("Nombre");
     encabezadoUsuarios.add("Usuario");
     encabezadoUsuarios.add("Puesto");
     encabezadoUsuarios.add("Correo");

     husuarios = new hUsuarios();
     hpermisos = new hPermisos();
     vector = new Vector();

     dtm = new DefaultTableModel(vector,encabezadoUsuarios) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };




      tablaUsuarios.setModel(dtm);
            tablaUsuarios.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                {

              /*      JFrame frame = new JFrame();
                    frame.add(actualiza);
                    frame.setSize(741,582);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true); */

                 //  System.out.println(getParent().getName());
                 //  ((JTabbedPane)getParent()).addTab("Actualiza",actualiza);
                     tablaUsuarios.getSelectedRow();
                    Usuarios usuario = listaUsuarios.get(tablaUsuarios.getSelectedRow());
                     VActualiza_Usuarios2 actualiza = new VActualiza_Usuarios2(usuario);

                     int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                     indice +=1;
             //        ((JTabbedPane)getParent()).insertTab("Actualiza",null, new JScrollPane(actualiza), "Actualiza Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualiza), indice);
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


      panelTabla.add(new JScrollPane(tablaUsuarios));

     panelEncabezado.add(boton1);
     panelEncabezado.add(etiqueta1);
     panelEncabezado.add(combo1);
     panelEncabezado.add(etiqueta2);
     panelEncabezado.add(combo2);
     panelEncabezado.add(campoTexto1);
     panelEncabezado.add(botonBuscar);
     panelEncabezado.add(boton2);

     campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                botonBuscar.doClick();
            }
        });
     
     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     add(panelPrincipal);

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
                        
            jRadioButton2 = new JRadioButton("Cambiar Información a otro "+elemento);            
            jRadioButton2.setToolTipText("¿Desea pasar la información de este "+elemento+" a otro"+elemento+"?");
            
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
   
    public class PSeleccionar_Cliente extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaUsus;
    hUsuarios husus;
    Vector vector,encabezadoUsus;
    DefaultTableModel dtm;
    List<Usuarios> listaUsus;
    JTextField jtextfield1;
    JLabel jlabel1;
    JComboBox jcombobox1;
    int indice = 0;
    JButton botonbus;
    
      public PSeleccionar_Cliente(){
     husus = new hUsuarios();
     listaUsus = husus.consultaUsuarios("", "*", "");
     
     
     setLayout(new BorderLayout());   
     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());
     jlabel1 = new JLabel("Buscar por:");
     jcombobox1 = new JComboBox();
     jcombobox1.addItem("Login");
     jtextfield1 = new JTextField(20);    
   
       
        botonbus = new JButton(imagenbuscar);
        botonbus.setBackground(Color.white);
        botonbus.setBorderPainted(false);
        botonbus.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                      vector.clear();
                      String cadena = "foto";
                      
                      listaUsus = husus.consultaUsuarios(cadena,"LIKE",jtextfield1.getText());  
                    
                      for(Object o: listaUsus){
                      Usuarios lin = (Usuarios)o;
                      Vector<Object> unaFila = new Vector<Object>();
                      unaFila.add(lin.getFoto());
                      unaFila.add(lin.getNombres() + " " + lin.getApellidoP() + " " + lin.getApellidoM());
                      unaFila.add(lin.getPuesto());
                      vector.add(unaFila); 
                         }
                      
                      dtm.fireTableDataChanged();
                }
        
        
        
        });

     
     panelEncabezado.add(jlabel1);
     panelEncabezado.add(jcombobox1);
     panelEncabezado.add(jtextfield1);
     panelEncabezado.add(botonbus);
        
     tablaUsus = new JTable();
    // ubicacionesGuardadas = new ArrayList<Articuloubicacion>();
     encabezadoUsus = new Vector<String>();
     encabezadoUsus.add("Login");
     encabezadoUsus.add("Nombre");
     encabezadoUsus.add("Puesto");

     
     
     vector = new Vector();

       for(Object o: listaUsus){
             Usuarios lin = (Usuarios)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getFoto());
             unaFila.add(lin.getNombres() + " " + lin.getApellidoP() + " " + lin.getApellidoM());
             unaFila.add(lin.getPuesto());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoUsus) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaUsus.setModel(dtm);
      tablaUsus.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {
                idUsus = String.valueOf(listaUsus.get(tablaUsus.getSelectedRow()).getId());
                codigoUsus = listaUsus.get(tablaUsus.getSelectedRow()).getNombres() +" "+ listaUsus.get(tablaUsus.getSelectedRow()).getApellidoP() +" "+ listaUsus.get(tablaUsus.getSelectedRow()).getApellidoM();
                codigoUsus2 = listaUsus.get(tablaUsus.getSelectedRow()).getNombres() +" "+ listaUsus.get(tablaUsus.getSelectedRow()).getApellidoP() + listaUsus.get(tablaUsus.getSelectedRow()).getApellidoM();                    
                codigoUsus3 = listaUsus.get(tablaUsus.getSelectedRow()).getFoto();
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

      
      add(panelEncabezado,BorderLayout.NORTH);
      panelTabla.add(new JScrollPane(tablaUsus));
      add(panelTabla,BorderLayout.CENTER);
    }
}
   
   
   

}
