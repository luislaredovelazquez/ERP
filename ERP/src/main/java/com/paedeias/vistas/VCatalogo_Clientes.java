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
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.Validadores;
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_Clientes extends JPanel{
    hClientes clientes;
    JTable tablaClientes;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar,botonhistorial;
    Vector<String> encabezadoClientes;
    DefaultTableModel dtm;
    Vector vector;
    List<Clientes> listaClientes;
    ImageIcon imagen,imagen2,imagenbuscar,imagenhistorial;
    String idCliente="", codigoCliente="";

    public VCatalogo_Clientes()
    {
        tablaClientes = new JTable();

        encabezadoClientes = new Vector<String>();
        encabezadoClientes.add("Código");
        encabezadoClientes.add("Nombre");
        encabezadoClientes.add("Contacto");
        encabezadoClientes.add("Teléfono");
        encabezadoClientes.add("Días de Crédito");
        encabezadoClientes.add("Días Limite de Crédito");
        encabezadoClientes.add("Descuento");
        encabezadoClientes.add("Observaciones");



         clientes = new hClientes();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoClientes) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

            tablaClientes.setModel(dtm);

            tablaClientes.addMouseListener(new MouseListener(){

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
                     tablaClientes.getSelectedRow();
                    Clientes cliente = listaClientes.get(tablaClientes.getSelectedRow());
                     VActualiza_Clientes actualiza = new VActualiza_Clientes(cliente);

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


                    panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());
        panelEncabezado.setBackground(Color.white);

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaClientes());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               VAlta_Clientes altaClientes = new VAlta_Clientes();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
                
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaClientes), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }

        });

        leyenda1 = new JLabel("Buscar clientes en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Código");
        combo1.addItem("Nombre");
        combo1.addItem("Contacto");
        combo1.addItem("Teléfono");
        combo1.addItem("Días de Crédito");
        combo1.addItem("Días Limite de Crédito");
        combo1.addItem("Descuento");
        combo1.addItem("Observaciones");

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

        

        String valores [] = {"codigo","nombre","contacto",
        "telefono","diasCredito","diasLimiteCredito","desc1","observaciones"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

         if(combo1.getSelectedItem().toString().equals("Todos"));
         listaClientes = clientes.consultaClientes(campo,compara,condicion);
         vector.removeAllElements();

         for(Object o: listaClientes){
             Clientes clientes = (Clientes)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(String.valueOf(clientes.getCodigo()));
             unaFila.add(clientes.getNombre());
             unaFila.add(clientes.getAtencion());
             unaFila.add(clientes.getTelefono());
             unaFila.add(clientes.getDiasCredito());
             unaFila.add(clientes.getDiasCredito());
             unaFila.add(clientes.getDesc1());
             unaFila.add(clientes.getObservaciones());
             vector.add(unaFila);

         }
          /* DefaultTableModel dtm = new DefaultTableModel(vector,encabezadoProveedores) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              }; */

                 dtm.fireTableDataChanged();
                 tablaClientes.setModel(dtm);


            }
        });

                tablaClientes.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e) {
                
            }

        }
                );


        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEliminarClientes());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
              
        //Checar esto        
                
             if(tablaClientes.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }     
              hPartidasDevoluciones hpartidasdevoluciones = new hPartidasDevoluciones();
              hDevoluciones hdevoluciones = new hDevoluciones();
              hPartidasfacturasCFDI hpartidasfacturacfdi = new hPartidasfacturasCFDI();
              hFacturasCFDI hfacturacfdi = new hFacturasCFDI();
              hPartidasReservaciones hpartidasreservaciones = new hPartidasReservaciones();
              hReservaciones hreservaciones = new hReservaciones();             
              hPartidaVentaAnticipos hpartidaventaanticipos = new hPartidaVentaAnticipos();
              hVentaAnticipos hventaanticipos = new hVentaAnticipos();
              hPartidas hpartidas = new hPartidas();
              hVentas hventas = new hVentas();
              hCuentasPorCobrar hcuentasporcobrar = new hCuentasPorCobrar();
              hClientes hclientes = new hClientes();
              Long id = listaClientes.get(tablaClientes.getSelectedRow()).getId();
              String cadenaID = String.valueOf(id);
             
          List<Devoluciones> listaProv1 = hdevoluciones.consultaVentas("idcliente", "=", cadenaID);     
          List<Factura> listaProv2 = hfacturacfdi.consultaFacturas("idReceptorF", "=", cadenaID);
          List<Reservaciones> listaProv3 = hreservaciones.consultaReservaciones("codcliente", "=", cadenaID);
          List<Ventaanticipos> listaProv4 = hventaanticipos.consultaVentas("idcliente", "=", cadenaID);
          List<Ventas> listaProv5 = hventas.consultaVentas("idcliente", "=", cadenaID);
          List<Cuentasporcobrar> listaProv6 = hcuentasporcobrar.consultaCtaPorCobrar("cliente", "=", cadenaID);
          
          boolean banderaEliminar=false;
             
          VCatalogo_Clientes.PanelEliminar panelEliminar = new VCatalogo_Clientes.PanelEliminar((JFrame)Window.getWindows()[0],true);
          panelEliminar.setElemento("Cliente");
          if(listaProv1.isEmpty()&&listaProv2.isEmpty()&&listaProv3.isEmpty()&&listaProv4.isEmpty()&&listaProv5.isEmpty()&&listaProv6.isEmpty())
          panelEliminar.setBoton2(0);
          else
          {
          panelEliminar.setBoton2(1);
          banderaEliminar = true;
          }
          if(listaProv1.isEmpty()&&listaProv2.isEmpty()&&listaProv3.isEmpty()&&listaProv4.isEmpty()&&listaProv5.isEmpty()&&listaProv6.isEmpty())
          panelEliminar.setBoton3(0);
          else
          {
          panelEliminar.setBoton3(1);
          banderaEliminar = true;
          }
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
                   "Esta acción eliminará todos los registros del cliente en el sistema de modo irrecuperable \n ¿Desea Continuar?", "Eliminar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

           if(respuesta == JOptionPane.YES_OPTION)
           {
               
           if(listaClientes.get(tablaClientes.getSelectedRow()).getId() == 1)    
           {
           JOptionPane.showMessageDialog(null, "Lo sentimos, este cliente no puede ser eliminado");    
           return;    
           }
             
           
           

              
              switch(panelEliminar.getOpcion())
              {
                  case 1:
                      
           hclientes.borrarClientes("id", "=", String.valueOf(listaClientes.get(tablaClientes.getSelectedRow()).getId()));

           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);

           listaClientes.remove(tablaClientes.getSelectedRow());
           dtm.removeRow(tablaClientes.getSelectedRow());
           tablaClientes.setModel(dtm);
                      
                      break;
                      
                  case 2:
                      
        PSeleccionar_Cliente seleccionar = new PSeleccionar_Cliente();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Reemplazar por",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);    
        
        //poner todas las partidas que se van a actualizar y eliminar al final el cliente que fue reemplazado
        hdevoluciones.actualizarCliente(idCliente,cadenaID);                 
        hfacturacfdi.actualizarCliente(idCliente,cadenaID);                 
        hreservaciones.actualizarCliente(idCliente,cadenaID);
        hventaanticipos.actualizarCliente(idCliente,cadenaID);
        hventas.actualizarCliente(idCliente,cadenaID);
        hcuentasporcobrar.actualizarCliente(idCliente,cadenaID);
        hclientes.borrarClientes("id", "=", cadenaID);

           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Reemplazado","Reemplazo",JOptionPane.INFORMATION_MESSAGE);

           listaClientes.remove(tablaClientes.getSelectedRow());
           dtm.removeRow(tablaClientes.getSelectedRow());
           tablaClientes.setModel(dtm); 
           idCliente = "0";
        break;
                      
                  case 3:                               
              
              
              for(int i=0; i<listaProv1.size(); i++)
              {
               hpartidasdevoluciones.borrarPartidas("idVenta", "=", String.valueOf(listaProv1.get(i).getId()));
              }
              hdevoluciones.borrarVentas("idcliente", "=", cadenaID);   
              
              
              for(int i=0; i<listaProv2.size(); i++)
              {
              hpartidasfacturacfdi.borrarPartidas("idFactura", "=", String.valueOf(listaProv2.get(i).getIdFactura()));   
              }
              hfacturacfdi.borrarFactura("idReceptorF", "=", cadenaID); 
              
              
              
              for(int i=0; i<listaProv3.size(); i++)
              {
               
               hpartidasreservaciones.borrarPReservaciones("codRes", "=", String.valueOf(listaProv3.get(i).getId()));
              }
              hreservaciones.borrarReservaciones("codcliente", "=", cadenaID);  
              
              
              
              for(int i=0; i<listaProv4.size(); i++)
              {
              hpartidaventaanticipos.borrarPartidas("idVenta", "=", String.valueOf(listaProv4.get(i).getId()));    
              }
              hventaanticipos.borrarVentas("idcliente", "=", cadenaID);
              
              
              
              for(int i=0; i<listaProv5.size(); i++)
              {
              hpartidas.borrarPartidas("idVenta", "=", String.valueOf(listaProv5.get(i).getId()));    
              }
              hventas.borrarVentas("idcliente", "=", cadenaID);
              
              
              
              hcuentasporcobrar.borrarCtaPorCobrar("cliente", "=", cadenaID);
              
           
              
              
           hclientes.borrarClientes("id", "=", String.valueOf(listaClientes.get(tablaClientes.getSelectedRow()).getId()));

           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);

           listaClientes.remove(tablaClientes.getSelectedRow());
           dtm.removeRow(tablaClientes.getSelectedRow());
           tablaClientes.setModel(dtm); 
              break;
           
              //Checar esto          
                      
              }

           }

            }
          }

        });

       // imagenhistorial = new ImageIcon(getClass().getResource("/mainicons/historialclientes.jpg"));
        botonhistorial = new JButton("Historial");
        botonhistorial.setBackground(Color.white);
        botonhistorial.setBorderPainted(false);
        botonhistorial.setToolTipText("Historial de Clientes");
        botonhistorial.setEnabled(CConfiguracion.isBuscarClientes());
        botonhistorial.setBackground(new java.awt.Color(11,70,119));
        botonhistorial.setForeground(new java.awt.Color(255, 255, 255));
        botonhistorial.setContentAreaFilled(false);
        botonhistorial.setOpaque(true);
        botonhistorial.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
             if(tablaClientes.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }     
         
           VCatalogoHistorialClientes actualiza = new VCatalogoHistorialClientes(listaClientes.get(tablaClientes.getSelectedRow()));

            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
             //        ((JTabbedPane)getParent()).insertTab("Actualiza",null, new JScrollPane(actualiza), "Actualiza Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualiza), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Historial",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice); 

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
        panelEncabezado.add(botonhistorial);

        
        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());


        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(new BorderLayout());
        
                campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });

           panelTabla.add(new JScrollPane(tablaClientes));
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
    JTable tablaCliente;
    hClientes hcliente;
    Vector vector,encabezadoCliente;
    DefaultTableModel dtm;
    List<Clientes> listaCliente;
    JTextField jtextfield1;
    JLabel jlabel1;
    JComboBox jcombobox1;
    int indice = 0;
    JButton botonbus;
    
      public PSeleccionar_Cliente(){
     hcliente = new hClientes();
     listaCliente = hcliente.consultaClientes("","*","");  
     
     
     setLayout(new BorderLayout());   
     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());
     jlabel1 = new JLabel("Buscar por:");
     jcombobox1 = new JComboBox();
     jcombobox1.addItem("Nombre");
     jcombobox1.addItem("RFC");
     jcombobox1.addItem("Código");
     jtextfield1 = new JTextField(20);    
   
       
        botonbus = new JButton(imagenbuscar);
        botonbus.setBackground(Color.white);
        botonbus.setBorderPainted(false);
        botonbus.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                      vector.clear();
                      String cadena = "";
                      
                      if(jcombobox1.getSelectedItem().toString().equals("Nombre"))
                      {
                        cadena  = "nombre";
                      }
                      else if(jcombobox1.getSelectedItem().toString().equals("Código"))
                      {
                         cadena = "codigo";
                      }
                      else
                      {
                        cadena  = "rfc";
                      }
                      
                      listaCliente = hcliente.consultaClientes(cadena,"LIKE",jtextfield1.getText());  
                    
                      for(Object o: listaCliente){
                      Clientes lin = (Clientes)o;
                      Vector<Object> unaFila = new Vector<Object>();
                      unaFila.add(lin.getRfc());
                      unaFila.add(lin.getNombre());
                      unaFila.add(lin.getCodigo());
                      vector.add(unaFila); 
                         }
                      
                      dtm.fireTableDataChanged();
                }
        
        
        
        });

     
     panelEncabezado.add(jlabel1);
     panelEncabezado.add(jcombobox1);
     panelEncabezado.add(jtextfield1);
     panelEncabezado.add(botonbus);
        
     tablaCliente = new JTable();
    // ubicacionesGuardadas = new ArrayList<Articuloubicacion>();
     encabezadoCliente = new Vector<String>();
     encabezadoCliente.add("RFC");
     encabezadoCliente.add("Cliente");
     encabezadoCliente.add("Código");

     
     
     vector = new Vector();

       for(Object o: listaCliente){
             Clientes lin = (Clientes)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getRfc());
             unaFila.add(lin.getNombre());
             unaFila.add(lin.getCodigo());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoCliente) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaCliente.setModel(dtm);
      tablaCliente.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {
                idCliente = String.valueOf(listaCliente.get(tablaCliente.getSelectedRow()).getId());
                codigoCliente = listaCliente.get(tablaCliente.getSelectedRow()).getNombre();
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
      panelTabla.add(new JScrollPane(tablaCliente));
      add(panelTabla,BorderLayout.CENTER);
    }
}

}
