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
import com.paedeias.helpers.hArticulolinea;
import com.paedeias.helpers.hLineas;
import com.paedeias.identidades.Articulolinea;
import com.paedeias.identidades.Linea;
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


public class VCatalogo_Lineas extends JPanel{

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1;
    public static JButton boton1,boton2,botonBuscar;
    //JButton boton3;
    JComboBox combo1,combo2;
    ImageIcon imagen,imagen2,imagenbuscar;
    JTable tablaLineas;
    hLineas hlinea;
    Vector vector,encabezadoLineas;
    DefaultTableModel dtm;
    List<Linea> listaLineas;

    public VCatalogo_Lineas()
    {

     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setLayout(new BorderLayout());
     panelPrincipal.setBackground(Color.white);

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelEncabezado.setBackground(Color.white);

     etiqueta1 = new JLabel("Buscar líneas donde: ");

     combo1 = new JComboBox();
     combo1.addItem("Línea");
     combo1.addItem("Descripción");

     etiqueta2 = new JLabel("sea ");
    
     combo2 = new JComboBox();
     combo2.addItem("igual a");
     combo2.addItem("como");
     combo2.addItem("Todos");

     campoTexto1 = new JTextField(20);

     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setEnabled(CConfiguracion.isAltaLineas());
     boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            VAlta_Lineas altalineas = new VAlta_Lineas();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altalineas), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane) getParent().getParent().getParent()).setSelectedIndex(indice);

            }});
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);

     imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
     boton2 = new JButton();
     boton2.setEnabled(CConfiguracion.isEliminarLineas());
     boton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
             if(tablaLineas.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }
                
              int valor = tablaLineas.getSelectedRow();

            Linea linea = new Linea();
            linea.setId(listaLineas.get(tablaLineas.getSelectedRow()).getId());
            linea.setNombre(tablaLineas.getValueAt(valor, 1).toString());

            // linea.setDescripcion(tablaLineas.getValueAt(valor,2).toString());
            
            hArticulolinea harticulolinea = new hArticulolinea();
            List<Articulolinea>listaBorrar = harticulolinea.consultaLineaArticulo(String.valueOf(listaLineas.get(tablaLineas.getSelectedRow()).getId()));
            
            int eleccion=JOptionPane.showConfirmDialog(null, "Esta línea se encuentra en "+listaBorrar.size()+" ¿Estas seguro de que deseas borrarla?");
            
            if(eleccion == JOptionPane.NO_OPTION || eleccion == JOptionPane.CANCEL_OPTION)
                return;
            
            hlinea.borrarLineas("id", "=",String.valueOf(linea.getId()));

            
            harticulolinea.borrarArticuloLineaCompleto("claveLinea","=",String.valueOf(linea.getId()));

            vector.remove(valor);
            dtm.fireTableRowsDeleted(1, 1);
            tablaLineas.setModel(dtm);
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
     botonBuscar.setEnabled(CConfiguracion.isBuscarLineas());
     botonBuscar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
        String valores [] = {"nombre","descripcion"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

                listaLineas = hlinea.consultaLineas(campo,compara,condicion);
                vector = new Vector();

       for(Object o: listaLineas){
             Linea lin = (Linea)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getNombre());
             unaFila.add(lin.getDescripcion());
             vector.add(unaFila);

         }
      dtm = new DefaultTableModel(vector,encabezadoLineas);
      tablaLineas.setModel(dtm);

        tablaLineas.getModel().addTableModelListener(new TableModelListener()
     {

            public void tableChanged(TableModelEvent e) {

                  switch(e.getType()){

                  case TableModelEvent.UPDATE:
                  System.out.println("Finito!");

                  int row = e.getFirstRow();


                  TableModel model = (TableModel)e.getSource();
                  Object id = listaLineas.get(row).getId();
                  Object nombre = model.getValueAt(row, 0);
                  Object descripcion = model.getValueAt(row, 1);

                 Linea linea = new Linea();
                 linea.setId(Integer.parseInt(id.toString()));
                 linea.setNombre(nombre.toString());
                 linea.setDescripcion(descripcion.toString());
                 hlinea.actualizarLineas(linea);
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

     tablaLineas = new JTable();
     encabezadoLineas = new Vector<String>();
     encabezadoLineas.add("Nombre");
     encabezadoLineas.add("Descripción");

     hlinea = new hLineas();
     vector = new Vector();

         Vector<Object> unaFila = new Vector<Object>();
             
      dtm = new DefaultTableModel(vector,encabezadoLineas);
      tablaLineas.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaLineas));

   




  /*   boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              int arreglo[] = tablaLineas.getSelectedRows();
            for(int i=0; i<arreglo.length; i++){
            Linea linea = new Linea();
            linea.setId(Integer.parseInt(tablaLineas.getValueAt(arreglo[i],0).toString()));
            linea.setNombre(tablaLineas.getValueAt(arreglo[i], 1).toString());
            hlinea.actualizarLineas(linea);
                   }
        dtm.setDataVector(vector,encabezadoLineas);
        tablaLineas.setModel(dtm);
            }
        }); */



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
     add(panelPrincipal);
     
/*       if(!CConfiguracion.getPuesto().equals("Administración"))
        {
            boton1.setEnabled(false);
            boton2.setEnabled(false);
        } */
    }


}
