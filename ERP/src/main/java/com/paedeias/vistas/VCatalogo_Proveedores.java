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
import com.paedeias.identidades.Articuloproveedor;
import com.paedeias.identidades.ComprasMayoreo;
import com.paedeias.identidades.Cuentasporpagar;
import com.paedeias.identidades.Proveedores;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class VCatalogo_Proveedores extends JPanel{

    hProveedores proveedores;
    JTable tablaProveedores;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonAlta,botonEliminar;
    Vector<String> encabezadoProveedores;
    DefaultTableModel dtm;
    Vector vector;
    List<Proveedores> listaProveedores;
    ImageIcon imagen,imagen2,imagenbuscar;
    String idProvs="",codigoProvs="";
    

    public VCatalogo_Proveedores()
    {
        tablaProveedores = new JTable();

        encabezadoProveedores = new Vector<String>();
        encabezadoProveedores.add("Id");
        encabezadoProveedores.add("RFC");
        encabezadoProveedores.add("Nombre");
        encabezadoProveedores.add("Dirección");
        encabezadoProveedores.add("Días de Crédito");
        encabezadoProveedores.add("Días Limite de Crédito");
        encabezadoProveedores.add("Descuento");
        encabezadoProveedores.add("Observaciones");



         proveedores = new hProveedores();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoProveedores) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

            tablaProveedores.setModel(dtm);

            tablaProveedores.addMouseListener(new MouseListener(){

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
                     tablaProveedores.getSelectedRow();
                    Proveedores proveedor = listaProveedores.get(tablaProveedores.getSelectedRow());
                     VActualiza_Proveedores actualiza = new VActualiza_Proveedores(proveedor);

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

             ///

        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout());
        panelEncabezado.setBackground(Color.white);

        imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
        botonAlta = new JButton(imagen);
        botonAlta.setBackground(Color.white);
        botonAlta.setBorderPainted(false);
        botonAlta.setEnabled(CConfiguracion.isAltaProveedores());
        botonAlta.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               VAlta_Proveedores altaProveedores = new VAlta_Proveedores();
              // ((JTabbedPane)getParent()).addTab("Alta",null,new JScrollPane(altaProveedores),"Alta de Proveedores");
                int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                indice +=1;
            //   ((JTabbedPane)getParent()).insertTab("Alta",null,altaProveedores, "Alta de Proveedores", indice);
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaProveedores), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }

        });

        leyenda1 = new JLabel("Buscar proveedores en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("RFC");
        combo1.addItem("Nombre");
        combo1.addItem("Calle");
        combo1.addItem("Colonia");
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
        boton1.setEnabled(CConfiguracion.isBuscarProveedores());
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

        

        String valores [] = {"rfc","nombre","calle",
        "colonia","diasCredito","diasLimite","descuento","observaciones"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

         if(combo1.getSelectedItem().toString().equals("Todos"));
         listaProveedores = proveedores.consultaProveedores(campo,compara,condicion);
         vector.removeAllElements();

         for(Object o: listaProveedores){
             Proveedores proveedores = (Proveedores)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(String.valueOf(proveedores.getId()));
             unaFila.add(proveedores.getRfc());
             unaFila.add(proveedores.getNombre());
             unaFila.add(proveedores.getCalle()+" "+proveedores.getNumero()+" "+proveedores.getColonia());
             unaFila.add(proveedores.getDiasCredito());
             unaFila.add(proveedores.getDiasLimite());
             unaFila.add(proveedores.getDescuento());
             unaFila.add(proveedores.getObservaciones());
             vector.add(unaFila);

         }
          /* DefaultTableModel dtm = new DefaultTableModel(vector,encabezadoProveedores) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              }; */

                 dtm.fireTableDataChanged();
                 tablaProveedores.setModel(dtm);
        

            }
        });

        tablaProveedores.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e) {
            }

        }
                );


        imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
        botonEliminar = new JButton(imagen2);
        botonEliminar.setBackground(Color.white);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setEnabled(CConfiguracion.isEliminarProveedores());
        botonEliminar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
             if(tablaProveedores.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }     

             
             hComprasMayoreo hcomprasmayoreo = new hComprasMayoreo();
             hPartidasComprasMayoreo hpartidascomprasmayoreo = new hPartidasComprasMayoreo();
             hCuentasPorPagar hcuentasporpagar = new hCuentasPorPagar();
             hArticuloProveedor harticuloproveedor = new hArticuloProveedor();
             hProveedores hproveedores = new hProveedores();
             
             Long id = listaProveedores.get(tablaProveedores.getSelectedRow()).getId();
             String cadenaID = String.valueOf(id);
             String cadenaCodigo = listaProveedores.get(tablaProveedores.getSelectedRow()).getNombreCorto();

          List<ComprasMayoreo> listaProv1 = hcomprasmayoreo.consultaCompras("codigoProveedor", "=", cadenaID);
          List<Cuentasporpagar> listaProv2 = hcuentasporpagar.consultaCuentasPorPagar("proveedor", "=", cadenaID);
          List<Articuloproveedor> listaProv3 = harticuloproveedor.consultaArtProv("idProveedor", "=", cadenaCodigo);

            boolean banderaEliminar=false;
             
          VCatalogo_Proveedores.PanelEliminar panelEliminar = new VCatalogo_Proveedores.PanelEliminar((JFrame)Window.getWindows()[0],true);
          panelEliminar.setElemento("Proveedor");
          if(listaProv1.isEmpty()&&listaProv2.isEmpty()&&listaProv3.isEmpty())
          panelEliminar.setBoton2(0);
          else
          {
          panelEliminar.setBoton2(1);
          banderaEliminar = true;
          }
          if(listaProv1.isEmpty()&&listaProv2.isEmpty()&&listaProv3.isEmpty())
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
          panelEliminar.setSize(400, 200);
          panelEliminar.setLocationRelativeTo(null);
          panelEliminar.setVisible(true);  
         
          int indice = tablaProveedores.getSelectedRow();
          
          if(panelEliminar.isBandera())
          {
            
                int respuesta = JOptionPane.showConfirmDialog((JFrame)Window.getWindows()[0],
                   "Esta acción eliminará todos los registros del proveedor en el sistema de modo irrecuperable \n ¿Desea Continuar?", "Eliminar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

           if(respuesta == JOptionPane.YES_OPTION)
           {
                             switch(panelEliminar.getOpcion())
              {
                  case 1:
            hproveedores.borrarProveedores("id", "=", cadenaID);
            JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);


           listaProveedores.remove(indice);
           dtm.removeRow(tablaProveedores.getSelectedRow());
           tablaProveedores.setModel(dtm);
                      break;
                  case 2:
                      
        PSeleccionar_Proveedor seleccionar = new PSeleccionar_Proveedor();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Reemplazar por",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);   
        
                     
           hcomprasmayoreo.actualizarProveedor("codigoProveedor",idProvs, cadenaID);                       
           harticuloproveedor.actualizarProveedor("idProveedor",codigoProvs, cadenaCodigo);
           hcuentasporpagar.actualizarProveedor("proveedor",idProvs, cadenaID);
           hproveedores.borrarProveedores("id", "=", cadenaID);        
                      
           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Reemplazado","Eliminar",JOptionPane.INFORMATION_MESSAGE);           
           listaProveedores.remove(indice);
           dtm.removeRow(tablaProveedores.getSelectedRow());
           tablaProveedores.setModel(dtm);           
                      break;
                  case 3:
            for(int i=0; i<listaProv1.size(); i++)
              {
              hpartidascomprasmayoreo.borrarPCompras("compra", "=", String.valueOf(listaProv1.get(i).getId()));   
              }
              hcomprasmayoreo.borrarCompras("codigoProveedor", "=", cadenaID);            
                      
           harticuloproveedor.borrarArtProvCompleto("idProveedor", "=", cadenaCodigo);
           hcuentasporpagar.borrarCuentasPorPagar("proveedor", "=", cadenaID);
           hproveedores.borrarProveedores("id", "=", cadenaID);
           JOptionPane.showMessageDialog((JFrame)Window.getWindows()[0], "Elemento Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);          
           listaProveedores.remove(indice);
           dtm.removeRow(tablaProveedores.getSelectedRow());
           tablaProveedores.setModel(dtm);             
                      break;    
              }
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
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(new BorderLayout());


        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });


           panelTabla.add(new JScrollPane(tablaProveedores));
           panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
           panelPrincipal.add(panelTabla,BorderLayout.CENTER);
           add(panelPrincipal);
        setBackground(Color.white);

/*         if(!CConfiguracion.getPuesto().equals("Administración"))
        {
            botonAlta.setEnabled(false);
            botonEliminar.setEnabled(false);
        } */

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
    
    
    public class PSeleccionar_Proveedor extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaProvs;
    hProveedores hprovs;
    Vector vector,encabezadoProvs;
    DefaultTableModel dtm;
    List<Proveedores> listaProvs;
    JTextField jtextfield1;
    JLabel jlabel1;
    JComboBox jcombobox1;
    int indice = 0;
    JButton botonbus;
    
      public PSeleccionar_Proveedor(){
     hprovs = new hProveedores();
     listaProvs = hprovs.consultaProveedores("","*","");  
     
     
     setLayout(new BorderLayout());   
     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());
     jlabel1 = new JLabel("Buscar por:");
     jcombobox1 = new JComboBox();
     jcombobox1.addItem("Nombre");
     jcombobox1.addItem("RFC");
     jcombobox1.addItem("Nombre Corto");
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
                      else if(jcombobox1.getSelectedItem().toString().equals("RFC"))
                      {
                         cadena = "rfc";
                      }
                      else
                      {
                        cadena  = "nombreCorto";
                      }
                      
                      listaProvs = hprovs.consultaProveedores(cadena,"LIKE",jtextfield1.getText());  
                    
                      for(Object o: listaProvs){
                      Proveedores lin = (Proveedores)o;
                      Vector<Object> unaFila = new Vector<Object>();
                      unaFila.add(lin.getNombre());
                      unaFila.add(lin.getRfc());
                      unaFila.add(lin.getNombreCorto());
                      vector.add(unaFila); 
                         }
                      
                      dtm.fireTableDataChanged();
                }
        
        
        
        });

     
     panelEncabezado.add(jlabel1);
     panelEncabezado.add(jcombobox1);
     panelEncabezado.add(jtextfield1);
     panelEncabezado.add(botonbus);
        
     tablaProvs = new JTable();
    // ubicacionesGuardadas = new ArrayList<Articuloubicacion>();
     encabezadoProvs = new Vector<String>();
     encabezadoProvs.add("Nombre");
     encabezadoProvs.add("RFC");
     encabezadoProvs.add("Nombre Corto");

     
     
     vector = new Vector();

       for(Object o: listaProvs){
             Proveedores lin = (Proveedores)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getNombre());
             unaFila.add(lin.getRfc());
             unaFila.add(lin.getNombreCorto());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoProvs) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaProvs.setModel(dtm);
      tablaProvs.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {
                idProvs = String.valueOf(listaProvs.get(tablaProvs.getSelectedRow()).getId());
                codigoProvs = listaProvs.get(tablaProvs.getSelectedRow()).getNombreCorto();
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
      panelTabla.add(new JScrollPane(tablaProvs));
      add(panelTabla,BorderLayout.CENTER);
    }
}
    

}
