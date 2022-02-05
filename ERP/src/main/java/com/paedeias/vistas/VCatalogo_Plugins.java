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
import com.paedeias.helpers.hPlugins;
import com.paedeias.identidades.Plugins;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class VCatalogo_Plugins extends JPanel{

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1;
    public static JButton boton1,boton2,botonBuscar;
    //JButton boton3;
    JComboBox combo1,combo2;
    ImageIcon imagen,imagen2,imagenbuscar;
    JTable tablaPlugins;
    hPlugins hplugins;
    Vector vector,encabezadoPlugins;
    DefaultTableModel dtm;
    List<Plugins> listaPlugins;

    public VCatalogo_Plugins()
    {

     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());

     etiqueta1 = new JLabel("Buscar plugins donde: ");

     combo1 = new JComboBox();
     combo1.addItem("Plugin");
     combo1.addItem("Permisos");

     etiqueta2 = new JLabel("sea ");
    
     combo2 = new JComboBox();
     combo2.addItem("igual a");
     combo2.addItem("como");
     combo2.addItem("Todos");

     campoTexto1 = new JTextField(20);

     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setEnabled(CConfiguracion.isAltaPlugins()); //modificar
     boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            VAlta_Plugins altaplugins = new VAlta_Plugins();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaplugins), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane) getParent().getParent().getParent()).setSelectedIndex(indice);

            }});
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);

     imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
     boton2 = new JButton();
     boton2.setEnabled(CConfiguracion.isEliminarPlugins()); // modificar
     boton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
             if(tablaPlugins.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }
                
              int valor = tablaPlugins.getSelectedRow();

            Plugins plugin = new Plugins();
            plugin.setId(listaPlugins.get(tablaPlugins.getSelectedRow()).getId());
            
            hplugins.borrarPlugin("id", "=",String.valueOf(plugin.getId()));

            vector.remove(valor);
            dtm.fireTableRowsDeleted(1, 1);
            tablaPlugins.setModel(dtm);
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
     botonBuscar.setEnabled(CConfiguracion.isBuscarPlugins()); //modficar
     botonBuscar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
        String valores [] = {"idPlugin","permisosPlugin"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

                listaPlugins = hplugins.consultaPlugins(campo,compara,condicion);
                vector = new Vector();

       for(Object o: listaPlugins){
             Plugins lin = (Plugins)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getIdPlugin());
             unaFila.add(lin.getPermisosPlugin());
             vector.add(unaFila);

         }
      dtm = new DefaultTableModel(vector,encabezadoPlugins);
      tablaPlugins.setModel(dtm);

        tablaPlugins.getModel().addTableModelListener(new TableModelListener()
     {

            public void tableChanged(TableModelEvent e) {

                  switch(e.getType()){

                  case TableModelEvent.UPDATE:
                  

                  int row = e.getFirstRow();


                  TableModel model = (TableModel)e.getSource();
                  Object id = listaPlugins.get(row).getId();
                  Object idPlugin = model.getValueAt(row, 0);
                  Object permisosPlugin = model.getValueAt(row, 1);

                 Plugins plugin = new Plugins();
                 plugin.setId(Long.parseLong(id.toString()));
                 plugin.setIdPlugin(idPlugin.toString());
                 plugin.setPermisosPlugin(permisosPlugin.toString());
                 hplugins.actualizarPlugin(plugin);
                 System.out.println("Datos actualizados");
                  break;

                   case TableModelEvent.INSERT:
                   System.out.println("Datos insertados");
                   break;

                   case TableModelEvent.DELETE:
                   System.out.println("Datos borrados");
                   break;
                }
            }

     }
             );


            }


     });




   //  boton3 = new JButton("Actualizar");
   //  boton3.setBackground(Color.white);




     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());

     tablaPlugins = new JTable();
     encabezadoPlugins = new Vector<String>();
     encabezadoPlugins.add("Plugin");
     encabezadoPlugins.add("Permisos");

     hplugins = new hPlugins();
     vector = new Vector();

         
             
      dtm = new DefaultTableModel(vector,encabezadoPlugins);
      tablaPlugins.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaPlugins));

   





//     panelEncabezado.add(boton3);
      campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                botonBuscar.doClick();
            }
        });
      
     panelEncabezado.add(boton1);
     panelEncabezado.add(etiqueta1);
     panelEncabezado.add(combo1);
     panelEncabezado.add(etiqueta2);
     panelEncabezado.add(combo2);
     panelEncabezado.add(campoTexto1);
     panelEncabezado.add(botonBuscar);
     panelEncabezado.add(boton2);

     panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
     panelPrincipal.add(panelTabla,BorderLayout.CENTER);
     
     panelPrincipal.setBackground(Color.white); 
     panelEncabezado.setBackground(Color.white); 
     panelTabla.setBackground(Color.white);
     
     add(panelPrincipal);
     
/*       if(!CConfiguracion.getPuesto().equals("Administración"))
        {
            boton1.setEnabled(false);
            boton2.setEnabled(false);
        } */
    }


}
