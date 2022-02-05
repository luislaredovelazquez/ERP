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
import com.paedeias.helpers.hArticuloUbicacion;
import com.paedeias.helpers.hUbicacion;
import com.paedeias.identidades.Articulolinea;
import com.paedeias.identidades.Articuloubicacion;
import com.paedeias.identidades.Ubicacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class VCatalogo_Ubicacion extends JPanel{

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JLabel etiqueta1,etiqueta2;
    JTextField campoTexto1;
    JButton boton1,boton2,botonBuscar;
    //JButton boton3;
    JComboBox combo1,combo2;
    ImageIcon imagen,imagen2,imagenbuscar;
    JTable tablaUbicacion;
    hUbicacion hubicacion;
    Vector vector,encabezadoUbicacion;
    DefaultTableModel dtm;
    List<Ubicacion> listaUbicacion;

    public VCatalogo_Ubicacion()
    {

     setLayout(new GridLayout());
     panelPrincipal = new JPanel();
     panelPrincipal.setBackground(Color.white);
     panelPrincipal.setLayout(new BorderLayout());

     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelEncabezado.setBackground(Color.white);

     etiqueta1 = new JLabel("Buscar ubicación como: ");

     combo1 = new JComboBox();
     combo1.addItem("Id");
     combo1.addItem("Ubicación");

     etiqueta2 = new JLabel("sea ");

     combo2 = new JComboBox();
     combo2.addItem("igual a");
     combo2.addItem("como");
     combo2.addItem("Todos");

     campoTexto1 = new JTextField(20);

     imagen = new ImageIcon(getClass().getResource("/mainicons/plus1.jpg"));
     boton1 = new JButton();
     boton1.setEnabled(CConfiguracion.isAltaUbicaciones());
     boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            VAlta_Ubicacion altaubicacion = new VAlta_Ubicacion();
            int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
            indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(altaubicacion), indice);
            ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Alta",indice));
            ((JTabbedPane)getParent().getParent().getParent()).setSelectedIndex(indice);

            }});
     boton1.setBackground(Color.white);
     boton1.setBorderPainted(false);
     boton1.setIcon(imagen);

     imagen2 = new ImageIcon(getClass().getResource("/mainicons/less1.jpg"));
     boton2 = new JButton();
     boton2.setEnabled(CConfiguracion.isEliminarUbicaciones());
     boton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
             if(tablaUbicacion.getSelectedRow() == -1)   
             {
                 JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
                 return;
             }  
                
                
              int valor = tablaUbicacion.getSelectedRow();

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(listaUbicacion.get(tablaUbicacion.getSelectedRow()).getId());
            ubicacion.setUbicacion(tablaUbicacion.getValueAt(valor, 0).toString());
            hArticuloUbicacion harticuloubicacion = new hArticuloUbicacion();
           List<Articuloubicacion>listaBorrar = harticuloubicacion.consultaUbicacionArticulo(String.valueOf(listaUbicacion.get(tablaUbicacion.getSelectedRow()).getId()));
            
            int eleccion=JOptionPane.showConfirmDialog(null, "Esta ubicación se encuentra en "+listaBorrar.size()+" ¿Estas seguro de que deseas borrarla?");
            
            if(eleccion == JOptionPane.NO_OPTION || eleccion == JOptionPane.CANCEL_OPTION)
                return;

            
            // linea.setDescripcion(tablaLineas.getValueAt(valor,2).toString());
            hubicacion.borrarUbicacion("id", "=", ubicacion.getId().toString());

            
            harticuloubicacion.borrarArticuloUbicacionCompleto("idubicacion","=",String.valueOf(listaUbicacion.get(tablaUbicacion.getSelectedRow()).getId()));

            vector.remove(valor);
            dtm.fireTableRowsDeleted(1, 1);
            tablaUbicacion.setModel(dtm);
            }


     });
     boton2.setBackground(Color.white);
     boton2.setBorderPainted(false);
     boton2.setIcon(imagen2);

     imagenbuscar = new ImageIcon(getClass().getResource("/mainicons/btn_buscar.png"));
     botonBuscar = new JButton(imagenbuscar);
     botonBuscar.setBackground(Color.white);
     botonBuscar.setToolTipText("Buscar");
     botonBuscar.setBorderPainted(false);
     botonBuscar.setEnabled(CConfiguracion.isBuscarUbicaciones());
     botonBuscar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

        String valores [] = {"id","ubicacion"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();

                listaUbicacion = hubicacion.consultaUbicaciones(campo,compara,condicion);
                vector = new Vector();

       for(Object o: listaUbicacion){
             Ubicacion lin = (Ubicacion)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(lin.getId());
             unaFila.add(lin.getUbicacion());
             vector.add(unaFila);

         }
      dtm = new DefaultTableModel(vector,encabezadoUbicacion);
      tablaUbicacion.setModel(dtm);

        tablaUbicacion.getModel().addTableModelListener(new TableModelListener()
     {

            public void tableChanged(TableModelEvent e) {

                  switch(e.getType()){

                  case TableModelEvent.UPDATE:

                  int row = e.getFirstRow();


                  TableModel model = (TableModel)e.getSource();
                  Object id = listaUbicacion.get(row).getId();
                  Object ubicacion = model.getValueAt(row, 1);

                 Ubicacion ubica = new Ubicacion();
                 ubica.setId(Long.parseLong(id.toString()));
                 ubica.setUbicacion(ubicacion.toString());
                 hubicacion.actualizarUbicacion(ubica);
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

     tablaUbicacion = new JTable();
     encabezadoUbicacion = new Vector<String>();
     encabezadoUbicacion.add("Id");
     encabezadoUbicacion.add("Ubicación");

     hubicacion = new hUbicacion();
     vector = new Vector();

         Vector<Object> unaFila = new Vector<Object>();

      dtm = new DefaultTableModel(vector,encabezadoUbicacion);
      tablaUbicacion.setModel(dtm);
      panelTabla.add(new JScrollPane(tablaUbicacion));






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
     
     /*   if(!CConfiguracion.getPuesto().equals("Administración"))
        {
            boton1.setEnabled(false);
            boton2.setEnabled(false);
        } */

    }

}
