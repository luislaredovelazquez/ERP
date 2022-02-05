/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.controladores.Autenticar;
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.controladores.impresiones.ImpresionCambioMostrador;
import com.paedeias.controladores.impresiones.ImpresionVenta;
import com.paedeias.controladores.impresiones.PageableText;
import com.paedeias.helpers.hArticulos;
import com.paedeias.helpers.hArticulosEnMostrador;
import com.paedeias.helpers.hCuentasPorPagar;
import com.paedeias.helpers.hKardex;
import com.paedeias.identidades.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALL
 */
public class AltaArticulosMostrador extends javax.swing.JPanel {

    /**
     * Creates new form AltaArticulosMostrador
     */
    public AltaArticulosMostrador() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Buscar art�culo con");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C�digo", "Descripci�n" }));

        jButton1.setBackground(new java.awt.Color(11, 70, 119));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.setContentAreaFilled(false);
        jButton1.setEnabled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Responsable:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Contrase�a:");

        jTextField3.setEditable(false);
        jTextField3.setText("1");

        jButton2.setBackground(new java.awt.Color(11, 70, 119));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Validar");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Como", "Igual a" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("C�digo");

        jTextField4.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descripci�n");

        jTextField5.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Cantidad");

        jButton3.setBackground(new java.awt.Color(11, 70, 119));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Solicitar");
        jButton3.setContentAreaFilled(false);
        jButton3.setEnabled(false);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton5.setBackground(new java.awt.Color(11, 70, 119));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Agregar");
        jButton5.setContentAreaFilled(false);
        jButton5.setEnabled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(11, 70, 119));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Eliminar");
        jButton6.setContentAreaFilled(false);
        jButton6.setEnabled(false);
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean bandera = false;
        Usuarios usuario = autenticar.validarCambio(jTextField2.getText(),String.valueOf(jPasswordField1.getPassword()));    
         if(usuario!=null)
         bandera=true;
         else
         {
         JOptionPane.showMessageDialog(null, "Contrase�a Incorrecta");    
         return;
         }    
        if(bandera)
        {
        jButton2.setEnabled(false);    
        jButton1.setEnabled(true);    
        jButton3.setEnabled(true);    
        jButton6.setEnabled(true);    
        jButton5.setEnabled(true);    
        jTextField2.setEditable(false);
        jTextField1.setEditable(true);
        jTextField3.setEditable(true);
        jPasswordField1.setEditable(false);
        idUsuario = usuario.getId();
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        String campo = valores[jComboBox1.getSelectedIndex()];
        String compara = comparadores[jComboBox2.getSelectedIndex()];
        String condicion = jTextField1.getText();
        
        
         listaArticulos = harticulos.consultaArticulos(campo,compara,condicion);
         vector.clear();

         for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             unaFila.add(iarticulos.getDescripcion());
             unaFila.add(iarticulos.getExistencia()+iarticulos.getAlmacenDevoluciones());
             vector.add(unaFila); 
         }
         dtm.fireTableDataChanged();
         if(!vector.isEmpty())
        jTable1.getSelectionModel().setSelectionInterval(0, 0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        jButton1.doClick();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
       if(jTable2.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        listaArMos.remove(jTable2.getSelectedRow());
        
        dtm2.removeRow(jTable2.getSelectedRow());
        dtm2.fireTableStructureChanged();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        int exist = (int)listaArticulos.get(jTable1.getSelectedRow()).getExistencia() + listaArticulos.get(jTable1.getSelectedRow()).getExistenciaAlmacen();
        if(Integer.valueOf(jTextField3.getText()) > exist)
        {
            JOptionPane.showMessageDialog(null, "La cantidad solicitada es mayor a la que se encuentra en almac�n");
            return;
        }
        listaArtMos2 = harticulosenmostrador.consultaArticulosEnMostrador("codigo", "=", listaArticulos.get(jTable1.getSelectedRow()).getCodigo());
        if(!listaArtMos2.isEmpty())
        {
         JOptionPane.showMessageDialog(null, "Este art�culo ya se encuentra en mostrador");   
         return;   
        }
        
        if(!listaArMos.isEmpty())
        {
        for(int a=0; a<listaArMos.size(); a++)
        {
            if(listaArMos.get(a).getCodigo().equals(listaArticulos.get(jTable1.getSelectedRow()).getCodigo()))
            {
         JOptionPane.showMessageDialog(null, "Este art�culo ya se encuentra en la lista actual");   
         return;                
            }
        }
        }
        
        ArticulosEnMostrador articulo = new ArticulosEnMostrador();
        articulo.setCodigo(listaArticulos.get(jTable1.getSelectedRow()).getCodigo());
        articulo.setDescripcion(listaArticulos.get(jTable1.getSelectedRow()).getDescripcion());
        articulo.setCantidad(Integer.valueOf(jTextField3.getText()));
        articulo.setResponsable(idUsuario);
        listaArMos.add(articulo);
        
           jTextField3.setText("1");
        
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(articulo.getCodigo());
             unaFila.add(articulo.getDescripcion());
             unaFila.add(articulo.getCantidad());
             unaFila.add(jTextField2.getText());
             vector2.add(unaFila); 


             
              dtm2.fireTableStructureChanged();

//              jTable2.setModel(dtm2);
              jTable2.getSelectionModel().setSelectionInterval(0, 0);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
        if(!listaArMos.isEmpty())
        {
            hCuentasPorPagar hora = new hCuentasPorPagar();
            String fecha = hora.generarFecha();
            List<Partidas> listaPartidas= new ArrayList<Partidas>();
            
            if(!CPrincipal.getConexion().crearTransaccion())
            return;
            
            for(int i=0; i<listaArMos.size(); i++)
            {
             listaArMos.get(i).setFecha(fecha);   
             harticulosenmostrador.guardarArticulosEnMostrador(listaArMos.get(i));
             
        renglon = hkardex.consultaUltimoRenglon("articulo", "=", listaArMos.get(i).getCodigo()); 
        
        kardex.setAlmacenista(" ");
        kardex.setAnticipos(renglon.getAnticipos());
        kardex.setArticulo(renglon.getArticulo());
        kardex.setEntrada(0);
        kardex.setExistencias(renglon.getExistencias()); 
        kardex.setIdArticulo(renglon.getIdArticulo());
        kardex.setModificacion("Cambio a Mostrador /"+listaArMos.get(i).getCantidad() + " PZA");
        kardex.setMovimiento("Cambio a Mostrador /"+listaArMos.get(i).getCantidad() + " PZA");
        kardex.setNoMov(String.valueOf(Integer.valueOf(renglon.getNoMov())+1));
        kardex.setPrecioVenta(renglon.getPrecioVenta());
        kardex.setRefFerrari("C.Mostrador");
        kardex.setReservados(renglon.getReservados());
        kardex.setResponsable(String.valueOf(idUsuario));
        kardex.setResponsable2(jTextField2.getText());
        kardex.setSalida(0);
        kardex.setUltimoCosto(renglon.getUltimoCosto());
        kardex.setVendidoEn(0);
        hkardex.guardarEnKardex(kardex);
         
        Partidas partida = new Partidas();
        partida.setCodigoArticulo(listaArMos.get(i).getCodigo());
        partida.setDescripcionArticulo(listaArMos.get(i).getDescripcion());
        partida.setCantidad(listaArMos.get(i).getCantidad());
        listaPartidas.add(partida);
            }
       CPrincipal.getConexion().finalizarTransaccion();   
                   try{
        ImpresionCambioMostrador ip = new ImpresionCambioMostrador();
        ip.inicializar(jTextField2.getText(),listaPartidas,fecha.substring(0,19));
        PrinterJob job = PrinterJob.getPrinterJob();
        // PageFormat format = job.pageDialog(job.defaultPage());
        
        PageFormat format = new PageFormat();
        
        
        Paper p = new Paper();
        
        p.setSize(CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        p.setImageableArea(CGlobalConfig.getxImp(),CGlobalConfig.getyImp(),CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        format.setPaper(p);
        
        job.setPageable(new PageableText(ip.generarCadenaImpresion(), format));
        // job.setPrintable(ip);
        if (job.printDialog())
            job.print();
        }   catch (IOException ex) {
                Logger.getLogger(AltaArticulosMostrador.class.getName()).log(Level.SEVERE, null, ex);
            }catch (PrinterException ex) {
            Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
        JOptionPane.showMessageDialog(null, "Por favor, agregue un art�culo");    
        return;
        }
        jButton3.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Solicitud completada");
         } catch(Exception e)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            e.printStackTrace();            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
        jButton2.doClick();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
    hArticulosEnMostrador harticulosenmostrador;
    hArticulos harticulos;
    hKardex hkardex;
    Kardex kardex;
    Kardex renglon;
    Vector<String> encabezadoArtMos,encabezadoArtMos2;
    DefaultTableModel dtm,dtm2;
    Vector vector,vector2;
    List<ArticulosEnMostrador> listaArMos,listaArtMos2;
    List<Articulos> listaArticulos;
    Autenticar autenticar;    
    Long idUsuario=(long)0;
    String valores [] = {"codigo","descripcion"};
    String comparadores [] = {"LIKE","=","*"};
    
    
    private void inicializar() {
    vector = new Vector();
    autenticar = new Autenticar();
    harticulos = new hArticulos();
    hkardex = new hKardex();
    kardex = new Kardex();
    harticulosenmostrador = new hArticulosEnMostrador();
    encabezadoArtMos = new Vector<String>();
    encabezadoArtMos.add("C�digo");
    encabezadoArtMos.add("Descripci�n");
    encabezadoArtMos.add("Cantidad");
    dtm = new DefaultTableModel(vector,encabezadoArtMos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
    
    jTable1.setModel(dtm);
    listaArMos = new ArrayList<ArticulosEnMostrador>();
    vector2 = new Vector();
    encabezadoArtMos2 = new Vector<String>();
    encabezadoArtMos2.add("C�digo");
    encabezadoArtMos2.add("Descripci�n");
    encabezadoArtMos2.add("Cantidad");
    encabezadoArtMos2.add("Responsable");
    dtm2 = new DefaultTableModel(vector2,encabezadoArtMos2) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };    
    jTable2.setModel(dtm2);
    
           jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
        if(jTable1.getSelectedRow() ==-1)
            return;
        jTextField4.setText(listaArticulos.get(jTable1.convertRowIndexToModel(jTable1.getSelectedRow())).getCodigo());
        jTextField5.setText(listaArticulos.get(jTable1.convertRowIndexToModel(jTable1.getSelectedRow())).getDescripcion());
            }
        });
    
    
    }
    
    
}