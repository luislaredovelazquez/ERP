/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.helpers.hArticulos;
import com.paedeias.helpers.hClientes;
import com.paedeias.helpers.hVentas;
import com.paedeias.identidades.Partidas;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALL
 */
public class V_ReporteParettoClientes extends javax.swing.JPanel  implements Comparator{

    /**
     * Creates new form V_ReporteParettoClientes
     */
    public V_ReporteParettoClientes() {
        initComponents();
        inicializar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("De");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("a");

        jButton1.setBackground(new java.awt.Color(11, 70, 119));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Generar");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(11, 70, 119));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Actualizar Clasificaci�n");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                                 //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT = "dd/MM/yy";
                     final String NEW_FORMAT = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString = dateChooserCombo1.getText();
                     String newDateString;

                     SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                     Date d = null;
                      try {
                     d = sdf.parse(oldDateString);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf.applyPattern(NEW_FORMAT);
                     newDateString = sdf.format(d);
                     newDateString = newDateString + " 00:00:00";
                     
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT2 = "dd/MM/yy";
                     final String NEW_FORMAT2 = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString2 = dateChooserCombo2.getText();
                     String newDateString2;

                     SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT2);
                     Date d2 = null;
                      try {
                     d2 = sdf2.parse(oldDateString2);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf2.applyPattern(NEW_FORMAT2);
                     newDateString2 = sdf.format(d2);
                     newDateString2 = newDateString2 + " 23:59:59";
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     hVentas hventas = new hVentas();
                     List<Partidas> partidas = hventas.consultaVentasClientes(newDateString, newDateString2);
                     List<Partidas> articulosunicos = new ArrayList<Partidas>();
                     boolean bandera;
                     
                     int it1=0;
                     while(it1< partidas.size())
                     {
                       
                     int it2=0;
                     bandera = false;
                     while(it2 < articulosunicos.size())
                     {
                         
                     if(articulosunicos.get(it2).getCodigoArticulo().equals(partidas.get(it1).getCodigoArticulo()))    
                     {
                     articulosunicos.get(it2).setSubtotal(articulosunicos.get(it2).getSubtotal()+partidas.get(it1).getSubtotal());    
                     bandera = true;    
                     }
                     it2++;
                     }
                     
                     if(!bandera)
                     articulosunicos.add(partidas.get(it1));    
                     

                         it1++;
                     }
                     
                   
              /*     int it4;
                   boolean insertado_antes =false;                     
                   List<Partidas> listaOrdenada = new ArrayList<Partidas>();  
                   
                     
                   int it3=0;
                   while(it3 < articulosunicos.size())
                   {
                    System.out.println("1");
                    it4=0;
                    while(it4<listaOrdenada.size()){
                    if(articulosunicos.get(it3).getCantidad()<listaOrdenada.get(it4).getCantidad()) 
                    {
                    System.out.println("2");    
                    listaOrdenada.add(0, articulosunicos.get(it3)); 
                    insertado_antes=true;
                    }
                    it4++;    
                    }
                    
                    if(!insertado_antes)
                    listaOrdenada.add(articulosunicos.get(it3));   
                    
                    it3++;   
                   }    */
                   listaOrdenada.clear();  
                   listaOrdenada = articulosunicos; 
                   Collections.sort(listaOrdenada, this);  
                   
                       
                 
                   int it5 = 0;
                   while(it5 < listaOrdenada.size())
                   {
                   // System.out.println("3");
                   if(it5 < listaOrdenada.size() * 0.2)    
                   listaOrdenada.get(it5).setUbicacion("A");    
                   else if (it5<(listaOrdenada.size() *  0.2)+(listaOrdenada.size() * 0.3))
                   listaOrdenada.get(it5).setUbicacion("B");    
                   else
                   listaOrdenada.get(it5).setUbicacion("C");        
                   it5++;    
                   }
                   
                  //  falta agregar la lista a la tabla y ver como queda....
                  if(!vector.isEmpty())  
                  vector.clear();    
                  String[] arregloDesc;
                  int it6 = 0;
                  while(it6 < listaOrdenada.size())
                  {
                  Vector<Object> vect = new Vector();
                  vect.add(listaOrdenada.get(it6).getCodigoArticulo());    
                  vect.add(listaOrdenada.get(it6).getDescripcionArticulo());
                  vect.add(df.format(listaOrdenada.get(it6).getSubtotal()));
                  arregloDesc = listaOrdenada.get(it6).getTipoBeneficio().split("@");
                  vect.add(arregloDesc[0]);
                  vect.add(arregloDesc[1]);
                  vect.add(arregloDesc[2]);
                  vect.add(arregloDesc[3]);
                  vect.add(arregloDesc[4]);
                  vect.add(listaOrdenada.get(it6).getUbicacion());
                  vector.add(vect);
                  it6++;    
                  }
                  dtm.fireTableDataChanged();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                           //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT = "dd/MM/yy";
                     final String NEW_FORMAT = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString = dateChooserCombo1.getText();
                     String newDateString;

                     SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                     Date d = null;
                      try {
                     d = sdf.parse(oldDateString);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf.applyPattern(NEW_FORMAT);
                     newDateString = sdf.format(d);
                     
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     //------------- CAMBIO DE FORMATO -----------------
                     
                     final String OLD_FORMAT2 = "dd/MM/yy";
                     final String NEW_FORMAT2 = "yyyy/MM/dd";

                     // August 12, 2010
                     String oldDateString2 = dateChooserCombo2.getText();
                     String newDateString2;

                     SimpleDateFormat sdf2 = new SimpleDateFormat(OLD_FORMAT2);
                     Date d2 = null;
                      try {
                     d2 = sdf2.parse(oldDateString2);
                     } catch (ParseException ex) {
                     Logger.getLogger(VCatalogoCierres.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     sdf2.applyPattern(NEW_FORMAT2);
                     newDateString2 = sdf.format(d2);
        
        String paretto = "";
        
        if(jTable1.getRowCount() == 0 || listaOrdenada.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "La tabla est� vac�a, por favor genere el reporte");
            return;
        }
        
        hClientes hclientes = new hClientes();
        hclientes.limpiarParetto();

        for(int i=0; i<listaOrdenada.size(); i++)
        {
        paretto = listaOrdenada.get(i).getUbicacion()+" "+newDateString +" - "+ newDateString2+" - "+df.format(listaOrdenada.get(i).getSubtotal());
        hclientes.actualizarClasificacion(listaOrdenada.get(i).getCodigoArticulo(),paretto);   
        }
        JOptionPane.showMessageDialog(null, "La clasificaci�n de los art�culos de este reporte ha sido actualizada");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    DefaultTableModel dtm;
    Vector vector,vectorEncabezado;
    List<Partidas> listaOrdenada;
    DecimalFormat df;
    
    private void inicializar()
    {
        df = new DecimalFormat("0.00");
        vectorEncabezado = new Vector();
        vector = new Vector();
        
        listaOrdenada = new ArrayList<Partidas>();
        
        vectorEncabezado.add("Nombre Corto");
        vectorEncabezado.add("Nombre");
        vectorEncabezado.add("Cantidad Comprada");
        vectorEncabezado.add("Descuento 1");
        vectorEncabezado.add("Descuento 2");
        vectorEncabezado.add("Descuento 3");
        vectorEncabezado.add("Descuento 4");
        vectorEncabezado.add("Descuento 5");
        vectorEncabezado.add("A|B|C");
        dtm = new DefaultTableModel(vector, vectorEncabezado);
        jTable1.setModel(dtm);  
    }
    
        public int compare(Object o1, Object o2) {
           Partidas p1 = (Partidas ) o1;
           Partidas p2= (Partidas ) o2;
        
           return p2.getSubtotal().compareTo(p1.getSubtotal());
    }

}
