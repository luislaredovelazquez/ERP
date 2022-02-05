/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;


import com.paedeias.controladores.Autenticar;
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.controladores.impresiones.*;
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALL
 */
public class VRealizar_Venta extends javax.swing.JPanel {

    /**
     * Creates new form FRealizar_Venta
     */
    public VRealizar_Venta() {
        initComponents();
        inicializar();
    }
    
    private void inicializar()
    {
              
        //se inicializan los helpers
        df = new DecimalFormat("0.00");
        hclientes= new hClientes();
        harticulos = new hArticulos();
        harticulolinea = new hArticulolinea();
        hkardex = new hKardex();
        hventas = new hVentas();
        hpartidas = new hPartidas();
        halmacendevoluciones = new hAlmacendevoluciones();
        
        //se inicializan las listas
        // listaClientes = hclientes.consultaClientes("nombre", "LIKE", "Público en General");
        listaClientes = hclientes.consultaClientes("id", "=", "1");
        listaArticulos = new ArrayList<Articulos>();
        listaPartidas = new ArrayList<Partidas>();
        cantArticulos = new ArrayList<Cantidades>();
        idCompletosUnicos = new ArrayList<String>();
        codigosCompletosUnicos = new ArrayList<String>();
        
        
        //se inicializan los componentes
       // jButton5.setEnabled(false); //cambiar a true para habilitar pedidos
        
        
        
        //inicializa combobox clientes
        jComboBox5.addItem(listaClientes.get(0).getNombre());
        jComboBox5.setSelectedIndex(0);
        
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil1()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil2()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil3()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil4()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil5()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil6()));
        
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc1()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc2()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc3()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc4()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc5()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc6()));
       
        
        //se agrupan los radio button
        final ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton7);
        group.add(jRadioButton3);
        group.add(jRadioButton4);
        group.add(jRadioButton5);
        group.add(jRadioButton6);
        
        //se selecciona el radio button de nada de beneficios
        jRadioButton7.setSelected(true);
        
        //action performed para deshabilitar los demás botónes y habilitar componentes
        
        jRadioButton1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                    jComboBox4.setEnabled(true);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(false);
           
            }
        
        });
        
        jRadioButton2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(true);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(false);
       

            }
        
        });
        
           jRadioButton3.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(true);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(false);


            }
        
        });
                
           jRadioButton4.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(true);
                    jTextField9.setEditable(false);
             

            }
        
        });
                        
                        
           jRadioButton5.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(true);
            

            }
        
        });
                                
           jRadioButton6.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(false);


            }
        
        });
        
         jRadioButton7.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);
                    jTextField7.setEditable(false);
                    jTextField8.setEditable(false);
                    jTextField9.setEditable(false);
           

            }
        
        });

        
        

        //se inicializa la tabla de artículos
        encabezadoArticulos = new Vector<String>();
        encabezadoArticulos.add("Código");
        encabezadoArticulos.add("Descripción");
        encabezadoArticulos.add("Precio de Venta");
        encabezadoArticulos.add("Almacén General");
        encabezadoArticulos.add("Devoluciones");
        
        
        vectorArticulos = new Vector();

           dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           jTable2.setModel(dtm);
           jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
           jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
           

           //se inicializa la tabla de partidas
           encabezadoPartidas = new Vector<String>();
           encabezadoPartidas.add("Código");
           encabezadoPartidas.add("Descripción");
           encabezadoPartidas.add("Precio de Venta");
           encabezadoPartidas.add("Unitario con beneficio");
           encabezadoPartidas.add("Cantidad");
           encabezadoPartidas.add("Subtotal");

           vectorPartidas = new Vector<String>();

           dtmPartidas = new DefaultTableModel(vectorPartidas,encabezadoPartidas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           jTable3.setModel(dtmPartidas);
           
           
           //se habilitan o deshabilitan los radiobutton dependiendo de la configuración global
           if(!CGlobalConfig.isDescuentosClientes())
           jRadioButton2.setEnabled(false);
           if(!CGlobalConfig.isUtilidadesClientes())
           jRadioButton1.setEnabled(false);    
           if(!CGlobalConfig.isCambiarPrecioArticulo())
           jRadioButton3.setEnabled(false);    
           if(!CGlobalConfig.isAplicarDescuentoArticulo())
           jRadioButton4.setEnabled(false);    
           if(!CGlobalConfig.isAplicarUtilidadArticulo())
           jRadioButton5.setEnabled(false);    
           if(!CGlobalConfig.isOfertas())
           jRadioButton6.setEnabled(false);    
           else
           jRadioButton6.setSelected(true);    
           
           
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextField8 = new javax.swing.JTextField();
        jRadioButton5 = new javax.swing.JRadioButton();
        jTextField9 = new javax.swing.JTextField();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton12 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton15 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

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

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciona un cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Nombre");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(11, 70, 119));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Nombre");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1.setText("Util. Cliente");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2.setText("Desc. Cliente");

        jComboBox2.setEnabled(false);

        jComboBox4.setEnabled(false);

        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(102, 102, 102));
        jCheckBox1.setText("Cuentas Por Cobrar");
        jCheckBox1.setToolTipText("Da click aquí para permitir vender a clientes con cuentas por cobrar pendientes");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox1))
                            .addComponent(jLabel3)))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciona Artículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descripción");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(11, 70, 119));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar");
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Línea");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(11, 70, 119));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Buscar");
        jButton4.setContentAreaFilled(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton3.setText("Modificar Precio");

        jTextField7.setEditable(false);

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton4.setText("Aplicar Descuento");

        jTextField8.setEditable(false);
        jTextField8.setText("0");

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton5.setText("Aplicar Utilidad");

        jTextField9.setEditable(false);
        jTextField9.setText("0");

        jRadioButton6.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton6.setText("Oferta");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Cantidad");

        jTextField10.setText("1");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(11, 70, 119));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Agregar como partida");
        jButton11.setContentAreaFilled(false);
        jButton11.setOpaque(true);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Código");

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(11, 70, 119));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Buscar");
        jButton13.setContentAreaFilled(false);
        jButton13.setOpaque(true);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Buscar en:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Almacén General", "Devoluciones" }));

        jButton12.setBackground(new java.awt.Color(11, 70, 119));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Eliminar Partida");
        jButton12.setContentAreaFilled(false);
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(11, 70, 119));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Aplicar descuento a partida");
        jButton16.setContentAreaFilled(false);
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(11, 70, 119));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Artículos Nuevos");
        jButton17.setContentAreaFilled(false);
        jButton17.setOpaque(true);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(11, 70, 119));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Artículos En Oferta");
        jButton18.setContentAreaFilled(false);
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(11, 70, 119));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Backorder");
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel2)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton4)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton5)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton6)
                        .addComponent(jLabel7)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton16)
                        .addComponent(jButton18)
                        .addComponent(jButton17)
                        .addComponent(jButton5))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton12))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Número de Artículos");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("0000");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Número de Partidas");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("0000");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Subtotal");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("000000.00");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Total");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("000000.00");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Observaciones de la venta");

        jButton8.setBackground(new java.awt.Color(11, 70, 119));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cotización");
        jButton8.setContentAreaFilled(false);
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(11, 70, 119));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Limpiar");
        jButton9.setContentAreaFilled(false);
        jButton9.setOpaque(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(11, 70, 119));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Vender");
        jButton10.setContentAreaFilled(false);
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton7.setText("No aplicar beneficios");

        jButton15.setBackground(new java.awt.Color(11, 70, 119));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Pedir a Almacén");
        jButton15.setContentAreaFilled(false);
        jButton15.setOpaque(true);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(11, 70, 119));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Descuento Venta General");
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jRadioButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton15)
                        .addComponent(jButton8)
                        .addComponent(jButton6))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10)
                        .addComponent(jButton9)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    //    ((JScrollPane)this.getParent().getParent()).getVerticalScrollBar().setValue(((JScrollPane)getParent()).getVerticalScrollBar().getMaximum());
        
        //se crea una lista provicional
        List<Clientes> listaProvicional = listaClientes;
        listaClientes = hclientes.consultaClientes("nombre", "LIKE", jTextField1.getText());
        if(listaClientes.isEmpty()) //valida si el resultado llegó vacío, si es así dice que no se puede encontrar a un cliente con ese nombre
        {
        JOptionPane.showMessageDialog(null, "No fue posible encontrar a un cliente con ese nombre");
        listaClientes = listaProvicional;
        return;
        } 
        
        //de llegar aquí significa que la lista no está vacía y que se encontró a alguien
        
        //se remueven los items del jComboBox5
        jComboBox5.removeAllItems();
        
        //se agregan los clientes de la lista al combobox
        for(Object o: listaClientes){
             Clientes lin = (Clientes)o;
             jComboBox5.addItem(lin.getNombre());
         }
        
        //se ponen los valores del cliente que quedó hasta arriba
        jComboBox4.removeAllItems();       
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil1()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil2()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil3()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil4()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil5()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil6()));
        
        jComboBox2.removeAllItems();
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc1()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc2()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc3()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc4()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc5()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc6()));  
       jComboBox5.setSelectedIndex(0);
       
       //se vacía el campo de cliente
       jTextField1.setText(""); 
       
       
       //itemListener para cambiar los otros dos comboboxes al seleccionar otro cliente
       jComboBox5.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
         if(jComboBox5.getItemCount()==0)
             return;
                
        jComboBox4.removeAllItems();                      
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil1()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil2()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil3()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil4()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil5()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getUtil6()));
        
        jComboBox2.removeAllItems();
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc1()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc2()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc3()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc4()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc5()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getDesc6()));          
        jCheckBox1.setSelected(false);
        hCuentasPorCobrar hcpc = new hCuentasPorCobrar();
        String id="";
        if(jComboBox5.getSelectedIndex() == -1)
        id = String.valueOf(listaClientes.get(0).getId());
        else
        id = String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
        
        if(hcpc.tieneNoPagado("cliente", "=",id))
        {
            if(id.equals("1"))
            jCheckBox1.setSelected(false);    
            else
            jCheckBox1.setSelected(true);
        }
        
            }
       
       });
       
       //se pone el radiobutton en "sin benegicios"
       jRadioButton7.doClick();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        //se limpia el vector de artículos
        vectorArticulos.clear();
        
        
        //se crea una lista provicional en caso de que no se haya encontrado ningún resultado
        List<Articulos>listaProvicional=new ArrayList<Articulos>();
        listaProvicional = listaArticulos;
        if(!jTextField5.getText().isEmpty()) //esto se hace si el campo no está vacío
        {
            //si se está buscando en almacén general
        if(jComboBox1.getSelectedItem().toString().equals("Almacén General"))
        //carga lista    
        {
        listaArticulos = harticulos.consultaArticulos("descripcion", "LIKE", jTextField5.getText());
      /*   int indice = 0;
        while(indice<listaArticulos.size())
         {
          // int existenciaAlmacen =  halmacendevoluciones.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(listaArticulos.get(indice).getAlmacenDevoluciones());
          indice++;
         } */ 
        } 
        // pregunta si se está buscando en devoluciones
        else if (jComboBox1.getSelectedItem().toString().equals("Devoluciones"))
        {
        //carga lista    
            {
        listaArticulos = halmacendevoluciones.consultaPartidasArticulos("descripcionArticulo", "LIKE", jTextField5.getText());     
         int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  harticulos.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         }    
            }
        }    
        
        //si la lista está vacía manda un mensaje de que no se obtuvo ningún resultado, y continúa la lista que estaba antes de la consulta
        if(listaArticulos.isEmpty())
        {
            listaArticulos = listaProvicional;
            JOptionPane.showMessageDialog(null, "Lo siento, la búsqueda no obtuvo resultados");
            return;
        }    
        
        //si llegó hasta aquí significa que hay algo en la lista
        //se carga en el vector los artículos que estan en la lista
        
            for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             if (iarticulos.getOferta() == 1)
             unaFila.add("OFERTA! - "+iarticulos.getDescripcion());
             else if(iarticulos.getNuevo() == 1)
             unaFila.add("NUEVO! - "+iarticulos.getDescripcion());
             else
             unaFila.add(iarticulos.getDescripcion());
             unaFila.add(iarticulos.getPrecioVenta());
             if(iarticulos.getUbicacion().equals("General"))
             {
             unaFila.add(iarticulos.getExistencia());
             unaFila.add(iarticulos.getAlmacenDevoluciones());
             }
             else if(iarticulos.getUbicacion().equals("Devoluciones"))
             {
             unaFila.add(iarticulos.getExistenciaAlmacen());
             unaFila.add(iarticulos.getExistencia());
             }
                 
             vectorArticulos.add(unaFila);          
         }
             dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
            
           jTable2.setModel(dtm);
           jTable2.getSelectionModel().setSelectionInterval(0, 0);
           // se ponen los artículos en la tabla
           
           jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
           jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
           
           //se pone el precio de venta en el campo para indicar el precio
           jTextField7.setText(String.valueOf(listaArticulos.get(0).getPrecioVenta()));
           // se borra la descripción de ese campo de texto
           jTextField5.setText("");     

        }
        else
        JOptionPane.showMessageDialog(null, "Por favor escriba la descripción del artículo en el cuadro de Texto");    //si el campo está vacío pide escribir descripción
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        // se limpia el vector
         vectorArticulos.clear();
         
         //se crea una lista provicional
         List<Articulos>listaProvicional=new ArrayList<Articulos>();
         listaProvicional = listaArticulos;
         
         //se valida que el campo de texto 6 no este vacío
        if(!jTextField6.getText().isEmpty())
        {
            //se checa si el combobox está activado en almacén general o en devoluciones
           if(jComboBox1.getSelectedItem().toString().equals("Almacén General")) 
           {
               
           listaArticulos = harticulos.consultaArticulos("lineaPrincipal","LIKE",jTextField6.getText());    
           
           if(listaArticulos.isEmpty())
           listaArticulos = harticulolinea.consultaArticuloxNombre(jTextField6.getText());
           
        /* int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  halmacendevoluciones.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         } */
           }
           //si se encuentra en devoluciones se manda un mensaje de que no existe búsqueda de líneas en devoluciones
          else if (jComboBox1.getSelectedItem().toString().equals("Devoluciones"))
        {
            listaArticulos = listaProvicional;
            JOptionPane.showMessageDialog(null, "Lo siento, no existen líneas en artículos devueltos, intente buscar por descripción o código");
            return;
        }    
           
           //se valida si la lista está vacía
             if(listaArticulos.isEmpty())
        {
            listaArticulos = listaProvicional;
            JOptionPane.showMessageDialog(null, "Lo siento, la búsqueda no obtuvo resultados");
            return;
        } 
           
             //si llegó aquí significa que hay algo en la lista y se vacía en el vector
           
            for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             if (iarticulos.getOferta() == 1)
             unaFila.add("OFERTA! - "+iarticulos.getDescripcion());
             else if(iarticulos.getNuevo() == 1)
             unaFila.add("NUEVO! - "+iarticulos.getDescripcion());
             else
             unaFila.add(iarticulos.getDescripcion());
             unaFila.add(iarticulos.getPrecioVenta());
             unaFila.add(iarticulos.getExistencia());
             unaFila.add(iarticulos.getAlmacenDevoluciones());
             vectorArticulos.add(unaFila);          
         }
             dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
            
           jTable2.setModel(dtm);
           jTable2.getSelectionModel().setSelectionInterval(0, 0);
           
           
           jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
           jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
           
           //se borra lo que haya en el campo de texto 6
           jTextField6.setText("");    
        }
        else // de estar vacio el campo de texto 6 se manda este mensaje
        JOptionPane.showMessageDialog(null, "Por favor escriba la línea del artículo en el cuadro de Texto");    
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        //valida que un artículo se encuentre seleccionado
        if(jTextField10.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Por favor agregue primero una cantidad");
            return;            
        }
        
        try
        {
            Integer.parseInt(jTextField10.getText());
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "La letra escrita en el recuadro cantidad no es un número");
            return;                        
        }
                
        
        if(jTable2.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        
        if(dtm.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero un artículo");
            return;
        }
        
        if(listaArticulos.get(jTable2.getSelectedRow()).getBloqueado() == 1)
        {   
        JOptionPane.showMessageDialog(null, "Este artículo se encuentra bloqueado y no puede ser usado en esta venta");
        return;
        }
        
        if(listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta() == 0.0)
        {   
        JOptionPane.showMessageDialog(null, "Este artículo artículo no tiene precio de venta");
        return;
        }
        
        if(Integer.valueOf(jTextField10.getText()) == 0)
        {
            JOptionPane.showMessageDialog(null, "No se pueden agregar 0 artículos en esta venta");
            return;
        }
        
        if(jCheckBox1.isSelected())
        {
            JOptionPane.showMessageDialog(null, "El cliente tiene cuentas vencidas, por favor deshabilite esta opción para permitir vender a este cliente");
            return;
        }
        
        hKardex hkar = new hKardex();
        Kardex parkar = hkar.consultaUltimoRenglon("articulo", "=", listaArticulos.get(jTable2.getSelectedRow()).getCodigo());
        
        if(parkar == null)
        {
            JOptionPane.showMessageDialog(null, "Este artículo no se encuentra registrado en Kardex");
            return;
        }
        
        if(listaArticulos.get(jTable2.getSelectedRow()).getUbicacion().equals("Devoluciones"))
        {
        if(Integer.compare(parkar.getExistencias(),(int)listaArticulos.get(jTable2.getSelectedRow()).getExistencia() + 
                (int)listaArticulos.get(jTable2.getSelectedRow()).getExistenciaAlmacen()) != 0)
        {
            JOptionPane.showMessageDialog(null, "La existencia de este artículo no coincide en Kardex, consulte a un Administrador para modificar existencia");
            return;            
        }            
        }else
        {
        if(Integer.compare(parkar.getExistencias(),(int)listaArticulos.get(jTable2.getSelectedRow()).getExistencia() + 
                (int)listaArticulos.get(jTable2.getSelectedRow()).getAlmacenDevoluciones()) != 0)
        {
            JOptionPane.showMessageDialog(null, "La existencia de este artículo no coincide en Kardex, consulte a un Administrador para modificar existencia");
            return;            
        }
        }
        
        if(Integer.compare(Integer.valueOf(jComboBox4.getSelectedItem().toString()), 0)== 0 && jRadioButton1.isSelected() && jRadioButton1.isEnabled())
        {
        int eleccion = JOptionPane.showConfirmDialog(null, "Ha seleccionado vender con utilidad 0 al cliente, ¿Está seguro de realizar esta venta?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(eleccion != JOptionPane.YES_OPTION)        
        return;
        }
  
     //      boolean bandera = false;     //se inicializa una bandera para dar paso a la segunda parte de lo de las partidas.
     //         if(idAdministrador == -1) //idAdministrador está inicializado como -1 significa que la venta no ha tenido la autorización del admin.
     //         {
                  
               //entra aquí mientras no tenga la autorización del admin  y valida si está seleccionado alguno de los radio button de mov en articulo.   
          if(jRadioButton3.isSelected()||jRadioButton4.isSelected()||jRadioButton5.isSelected())
          {   //si uno de los botones está seleccionado se pide el password y se valida mediante un JOptionPane
              String password;
              JPasswordField passwordField = new JPasswordField();
              Object[] obj = {"Por favor escriba la contraseña de algún administrador para aplicar este beneficio:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
               
               int opcion = JOptionPane.showOptionDialog(null, obj, "Contraseña requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion == 1)      
             return;
             else if(opcion == 0) //el usuario dio click en aceptar
             {
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             Autenticar autenticar = new Autenticar();
             if (!autenticar.validarPwd(password))
             {    
             JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
             return;
             }else
                  {
             Usuarios usuariogeneral=autenticar.getUsuariogeneral();
             if(jRadioButton3.isSelected())
             {
                 
             double precioTemporal = 0;
             if(Double.compare(Double.valueOf(jTextField7.getText()), listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta()) >= 0)
             precioTemporal = Double.valueOf(jTextField7.getText())-listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
             else
             precioTemporal = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta() - Double.valueOf(jTextField7.getText());
             
             if(precioTemporal > usuariogeneral.getRangoPrecio())
             {
             JOptionPane.showMessageDialog(null, "El administrador no tiene permisos para otorgar un rango de precios mayor a "+usuariogeneral.getUtilMax()+" pesos, "
             + "por favor, reduzca el rango");
              return;
             }    
             
             }
             if(jRadioButton4.isSelected())
             {
             if(Double.compare(Double.valueOf(jTextField8.getText()), usuariogeneral.getDescMax())>0)
                 {JOptionPane.showMessageDialog(null, "El administrador no tiene permisos para otorgar un descuento mayor al "+usuariogeneral.getDescMax()+"%, "
                         + "por favor, reduzca el descuento");
                 return;
                 }
                 }    
             if(jRadioButton5.isSelected())
             {
             if(Double.compare(Double.valueOf(jTextField9.getText()), usuariogeneral.getUtilMax())>0)
                 {JOptionPane.showMessageDialog(null, "El administrador no tiene permisos para otorgar una utilidad mayor al "+usuariogeneral.getUtilMax()+"%, "
                         + "por favor, reduzca la utilidad"); return;}
             }
         //    bandera=true; //las contraseñas coincidieron y el idAdministrador toma el valor del id al autenticar
             idAdministrador = autenticar.getId();
             nombreAdministrador = autenticar.getUsuariogeneral().getNombres() + " "+autenticar.getUsuariogeneral().getApellidoP()+" "+autenticar.getUsuariogeneral().getApellidoM();
             
             
             
                   }
              }
          }
       //   else   //si no está seleccionado nada hace caso omiso del if y pone la bandera en true
       //     {
       //       bandera=true;
       //     } 
        //      }else //si el administrador ya dio su permiso en la venta pone la bandera en true, lo que significa que los sig mov no necesitan autorización del admin.
        //          bandera=true;
      //     if(bandera) //si la bandera fue verdadera, ya sea pq el admin dio su autorización o no estaban seleccionadas los descuentos sobre algún artic.
      //     {
               
               /*
               
               YO CREO QUE ESTO ESTÁ MAL, NO ES NECESARIA LA ITERACIÓN Y SE PODRÍA PONER UN LISTAARTICULOS.GET(JTABLE2.GETSELECTEDROW())
                * LUEGO ITERAR ESE ARTICULO CONTRA TODO LO QUE HAY EN CANTARTICULOS, PONER UN IF, SI ENCUENTRA EL CODIGO DE LISTAARTICULOS.GET(JTABLE2).GETSELECTEDROW
                * SUMAR CANTARTICULOS.GETEXISTENCIAS() PARA IR ACUMULANDO ESE VALOR, LUEGO SALIR DEL CICLO Y SUMAR AL VALOR ACUMULADO LO DEL JTEXTFIELD5,
                * SI ES MENOR O IGUAL ENTONCES DEJAR PASAR, SINO ES MENOR O IGUAL SE PUEDE MANDAR MENSAJE DE ERROR
                * 
               while(i < cantArticulos.size()) //se van a iterar todos los articulos que estan en la lista de articulos con cada uno de los articulos
               {                               //capturados en la lista cantArticulos, la primera vez no entra porque cantArticulos.size() es igual a cero   
                   int o = 0;
                   while(o<listaArticulos.size())
                   {
                       
                       if(listaArticulos.get(o).getIdCompleto().equals(cantArticulos.get(i).getIdCompleto())) // se compara que los idsCompletos de las 2 listas sean iguales
                       {                                                                                      //cada idcompleto deberia de ser único en la lista de artículos
                                                                                                              //mas esto no pasa en la lista cantArt.
                           
                          //si son iguales 
                          banderaIndicador=false; //la bandera indicador se pone en falso
                          
                          int nuevaCantidad = Integer.parseInt(jTextField10.getText())+(int)cantArticulos.get(i).getExistencia(); 
                          //se genera una nueva cantidad sumando lo que hay en el campo de cantidades mas lo que hay en existencia
                          //esto tendría por objetivo sumar la existencia de al menor un artículo pasado, pero esto no está refinado
                          //porque puede haber más de un artículo, falta crear más bien una cantidad acumuladora o algo así para sumar
                          //todas las veces que está el mismo artículo en cantArticulos.
                          
                          //si la nueva cantidad es mayor a la que hay en la existencia de los artículos no deja continuar y manda un mensaje de error.
                          if(nuevaCantidad > listaArticulos.get(o).getExistencia())
                          {
                              JOptionPane.showMessageDialog(null, "La cantidad de artículos solicitada es mayor a la existencia, por favor reduce la cantidad de artículos");
                              return;
                          }else
                          {   //si la nueva cantidad es mejor o igual se coloca en la existencia la nueva cantidad (es decir lo que hay en el campo de texto 
                              // más lo que hay como existencia), sí se va acumulando la existencia! pero no me queda muy claro aún...
                              cantArticulos.get(i).setExistencia((long)nuevaCantidad);
                          }
                          
                       }
                       o++;
                   }
                   i++;
               }
               
               */
               
           /*
            HASTA ACÁ LLEGA EL WHILE QUE YO NO VEO QUE SIRVA MUCHO
            */
//             Cantidades cantidad = new Cantidades();
//             cantidad
               
             String cantidad = jTextField10.getText();
                                  
              int indiceCantidades=0;
              int acumulado = Integer.parseInt(cantidad);

              if(acumulado > listaArticulos.get(jTable2.getSelectedRow()).getExistencia())  //Primero se verifica si la primera entrada solicita una cantidad mayor a la existencia
              {
              JOptionPane.showMessageDialog(null, "La cantidad de artículos solicitada es mayor a la existencia, por favor reduce la cantidad de artículos");    
              return;
              }
              
             
              while(indiceCantidades<cantArticulos.size())  //Si no es la primera entrada se verifica si la suma de las entradas del mismo artículo es mayor a la existencia
              {
               if(listaArticulos.get(jTable2.getSelectedRow()).getIdCompleto().equals(cantArticulos.get(indiceCantidades).getIdCompleto()))     
               {
                   acumulado += cantArticulos.get(indiceCantidades).getCantidadPartida();
               }
                  indiceCantidades++;
              }
              
              if(acumulado > listaArticulos.get(jTable2.getSelectedRow()).getExistencia())
              {
              JOptionPane.showMessageDialog(null, "La cantidad de artículos solicitada es mayor a la existencia, por favor reduce la cantidad de artículos");    
              return;
              }
              
               if(listaArticulos.get(jTable2.getSelectedRow()).getExistencia() == 0)
              {
              JOptionPane.showMessageDialog(null, "No se pueden agregar 0 artículos a esta venta");    
              return;
              }
           
           //el mejorPrecio, precio, mejorDescuento, tipoDescuento y subtotal se inicializan para ser utilizados adelante
           Double mejorPrecio=0.0;
           Double precio = 0.0;
           Double mejorDescuento=0.0;
           String tipoDescuento = "";
           Double subtotal = 0.0;
           
           //si se seleccionó la opción de descuentos cliente se calcula cual es el mejor descuento, precio, mejor precio y tipo descuento
           if(jRadioButton2.isSelected()){
               mejorDescuento = (Double.valueOf(jComboBox2.getSelectedItem().toString())/100)*listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
               precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
               mejorPrecio = precio - mejorDescuento;
               
               double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
               double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
               
               
               tipoDescuento = "Descuento Cliente";
           }           
            else if(jRadioButton1.isSelected()) //si se seleccionó la opción de utilidad cliente se calcula cual es el mejorDescuento, precio, mejorPrecio y tipo descuento
            {
            precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();   
            mejorDescuento = (Double.valueOf(jComboBox4.getSelectedItem().toString())/100)*listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();    
            mejorPrecio = precio + mejorDescuento;
            mejorDescuento = (Double.valueOf(listaArticulos.get(jTable2.getSelectedRow()).getIva())/100)*mejorPrecio;
            mejorPrecio = mejorPrecio + mejorDescuento;
            String redondeo = df.format(mejorPrecio);  //11.76
            mejorPrecio = Double.valueOf(redondeo);
           
           double validadorCompra =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           // double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva; //11.7624
           
         //  System.out.println(mejorPrecio);
         //  System.out.println(validadorCompra);
               
              if(mejorPrecio < validadorCompra)
             // if(mejorPrecio < validadorCompra)
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
            
            
               tipoDescuento = "Utilidad Cliente";          
            }else if(jRadioButton3.isSelected()) // si se selecciona la opción de precio modificado, se calcula mejor descuento, precio, mejor precio y tipo descuento
            {
            mejorDescuento = 0.0;
            precio = Double.parseDouble(jTextField7.getText());
            mejorPrecio = precio;
            tipoDescuento = "Precio Modificado";
            
           double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           double validadorCompra = (compraIva *CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
            
            
            jRadioButton7.doClick();
            }else if(jRadioButton4.isSelected()) //si se selecciona la opción de descuento artículo, se calcula el mejor descuento, precio, mejorprecio y tipo descuento
            {
               mejorDescuento = (Double.valueOf(jTextField8.getText())/100)*listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
               precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
               mejorPrecio = precio - mejorDescuento;
               tipoDescuento = "Descuento Artículo";
           
           double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
               
               
               jRadioButton7.doClick();
            }else if(jRadioButton5.isSelected()) //si se selecciona la opción utilidad articulo, se calcula el mejor descuento, precio, mejorprecio y tipo descuento
            {
       //     mejorDescuento = (Double.valueOf(jTextField9.getText())/100)*listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();    
       //     precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
       //     mejorPrecio = precio + mejorDescuento;
            
            precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();   
            mejorDescuento = (Double.valueOf(jTextField9.getText())/100)*listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();    
            mejorPrecio = precio + mejorDescuento;
            mejorDescuento = (Double.valueOf(listaArticulos.get(jTable2.getSelectedRow()).getIva())/100)*mejorPrecio;
            mejorPrecio = mejorPrecio + mejorDescuento;
            
               tipoDescuento = "Utilidad Artículo";
               
           double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }   
               
               
               
               jRadioButton7.doClick();
            }    
           else if(jRadioButton7.isSelected()) //en caso de que el boton de no beneficios este seleccionado, se aplican los precios directos.
            {
            mejorDescuento = 0.0;
            precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
            mejorPrecio = precio;
            tipoDescuento = "Sin Beneficio";
            
           double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
            
            
            }else if(jRadioButton6.isSelected())
            {
                if(listaArticulos.get(jTable2.getSelectedRow()).getOferta() > 0)
                {
                mejorDescuento = 0.0;
                precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
                mejorPrecio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta2();
                tipoDescuento = "Oferta";
                
             double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
             double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
            }
                }
                else
                {
            mejorDescuento = 0.0;
            precio = listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta();
            mejorPrecio = precio;
            tipoDescuento = "Sin Beneficio";
            
           double compraIva =  (listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra();
           double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }    
                }
           
            }
      //     if(mejorPrecio<=listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra()) //si el precio de compra es mayor que el precio de venta se manda mensaje de error
      

           
           
           //se obtiene el subtotal al multiplicar el mejor precio por la cantidad seleccionada
           subtotal = mejorPrecio*Double.parseDouble(jTextField10.getText());
           
            if(!listaPartidas.isEmpty())
        {
            for(int i=0; i<listaPartidas.size(); i++)
            {
                if(listaArticulos.get(jTable2.getSelectedRow()).getCodigo().equals(listaPartidas.get(i).getCodigoArticulo()) &&
                   Double.compare(listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra(),listaPartidas.get(i).getPrecioCompra()) == 0 &&
                   Double.compare(mejorPrecio,listaPartidas.get(i).getConBeneficio())==0)     
                                        {
                    JOptionPane.showMessageDialog(null, "No se puede agregar el mismo artículo con el mismo precio y el mismo costo");
                    return;
                }
            }
        }
           
           
            //se carga la cantidad solicitada            
            //se crea el artículo (que se puede estar creando por primera vez...)     
            Cantidades cantidadarticulo = new Cantidades();
            cantidadarticulo.setId(listaArticulos.get(jTable2.getSelectedRow()).getId()); //un id que puede repetirse y que no sirve de mucho a la hora de las partidas
            cantidadarticulo.setIdCompleto(listaArticulos.get(jTable2.getSelectedRow()).getIdCompleto()); //el id ÚNICO de cada artículo
            cantidadarticulo.setCantidadPartida(Integer.parseInt(cantidad)); //la cantidad solicitada
            cantidadarticulo.setExistenciaCatalogo((int)listaArticulos.get(jTable2.getSelectedRow()).getExistencia()); //la cantidad que hay en existencia en lista
            cantidadarticulo.setCodigo(listaArticulos.get(jTable2.getSelectedRow()).getCodigo());  //el código del arttículo
            cantidadarticulo.setUbicacion(listaArticulos.get(jTable2.getSelectedRow()).getUbicacion());  //esta cosa para saber si el artículo es de devolución o de almacén general
            cantidadarticulo.setPrecioCompra(listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra());
            cantidadarticulo.setPrecioVenta(mejorPrecio);
            cantidadarticulo.setReservados((int)listaArticulos.get(jTable2.getSelectedRow()).getReservado());
            cantidadarticulo.setAnticipos((int)listaArticulos.get(jTable2.getSelectedRow()).getAnticipos());
             cantArticulos.add(cantidadarticulo); //se agrega el artículo con código único a la lista. (acá está mal pq se mezcla lo de almacén con lo de devoluciones y se compara
                                          //contra artículos no cotnra kardex.)
            
             // se crea la partida con las cantidades calculadas           
             Partidas partida = new Partidas();
             partida.setBeneficio(mejorDescuento);
             partida.setCantidad(Integer.parseInt(cantidad));
             partida.setConBeneficio(mejorPrecio);
             partida.setDescripcionArticulo(listaArticulos.get(jTable2.getSelectedRow()).getDescripcion());
             partida.setCodigoArticulo(listaArticulos.get(jTable2.getSelectedRow()).getCodigo());
             partida.setIdArticulo(listaArticulos.get(jTable2.getSelectedRow()).getId());
             partida.setIdCompleto(listaArticulos.get(jTable2.getSelectedRow()).getIdCompleto());
             partida.setPrecioVenta(listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta());
             partida.setSubtotal(subtotal);
             partida.setTipoBeneficio(tipoDescuento);
             partida.setPrecioCompra(listaArticulos.get(jTable2.getSelectedRow()).getPrecioCompra());
             partida.setUbicacion(listaArticulos.get(jTable2.getSelectedRow()).getUbicacion());
             
             
             //se agrega la partida
            listaPartidas.add(partida);

           //se ponen  calculan las partidas generales de la venta
           numArticulos = numArticulos+Integer.parseInt(cantidad);
           numPartidas++;
           numSubtotal = numSubtotal+precio*Double.parseDouble(cantidad);
           numTotal = numTotal+subtotal;
           
          /*QUIZÁS SERIA BUENO ACTUALIZAR LAS CANTIDADES DEL OBJETO VENTA AQUÍ YA QUE SE ESTAN CALCULANDO*/           
           
           // se actualiza en las etiquetas las cantidades
           jLabel14.setText(String.valueOf(numArticulos));
           jLabel16.setText(String.valueOf(numPartidas));
           jLabel18.setText(df.format(numSubtotal));
           jLabel20.setText(df.format(numTotal));
           
           
           // jRadioButton7.doClick();
           //Se pone uno otra vez en el campo de texto
           jTextField10.setText("1");
           
            
           //se agrega la partida a la tabla con las cantidades calculadas
            Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(listaArticulos.get(jTable2.getSelectedRow()).getCodigo());
             unaFila.add(listaArticulos.get(jTable2.getSelectedRow()).getDescripcion());
             unaFila.add(df.format(listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta()));
             unaFila.add(df.format(mejorPrecio));
             unaFila.add(cantidad);
             unaFila.add(df.format(subtotal));
                          
             vectorPartidas.add(unaFila);  
             
             dtmPartidas = new DefaultTableModel(vectorPartidas,encabezadoPartidas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
            
           jTable3.setModel(dtmPartidas);
           jTable3.getSelectionModel().setSelectionInterval(0, 0);
           
           //si es la primera partida se deshabilitan los botones del cliente
           if(numPartidas==1)
           {
           jButton1.setEnabled(false);
           jComboBox5.setEnabled(false);
          
          if(jRadioButton1.isSelected() || jRadioButton2.isSelected())
          {
          jRadioButton3.setEnabled(false);
          jRadioButton4.setEnabled(false);
          jRadioButton5.setEnabled(false);
          jRadioButton6.setEnabled(false);
          jRadioButton7.setEnabled(false);
          }
          
          jRadioButton1.setEnabled(false);
          jRadioButton2.setEnabled(false);
        //  jRadioButton7.setEnabled(false);
                  
                  
           jComboBox4.setEnabled(false);
           jComboBox2.setEnabled(false);

           }
          // }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                if(jTable2.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        
                if(dtm.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero un artículo");
            return;
        }
                
         hPedidosVentas hpv = new hPedidosVentas();
         Pedidosventas pv = new Pedidosventas();
         
         pv.setCantidad(Integer.valueOf(jTextField10.getText()));
         pv.setCodigoArticulo(listaArticulos.get(jTable2.getSelectedRow()).getCodigo());
         pv.setPrecioUnitario(listaArticulos.get(jTable2.getSelectedRow()).getPrecioVenta());
         pv.setVendedor(CConfiguracion.getId());
         jTextField10.setText("1");
         pv.setVenta((long)0);
         hpv.guardarPedidosVentas(pv);
         
        JOptionPane.showMessageDialog(null, "El artículo ha sido pedido");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
          if(jTable3.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        
        
        if(jTable3.getSelectedRow() != -1 && !listaPartidas.isEmpty())
        {
        
        int fila_seleccionada = jTable3.getSelectedRow();
        
        int cantidad = listaPartidas.get(fila_seleccionada).getCantidad();
        
        numArticulos = numArticulos - cantidad;
        numPartidas--;
        numSubtotal = numSubtotal - listaPartidas.get(fila_seleccionada).getPrecioVenta() * cantidad;
        numTotal = numTotal - listaPartidas.get(fila_seleccionada).getSubtotal();
        
        jLabel14.setText(String.valueOf(numArticulos));
        jLabel16.setText(String.valueOf(numPartidas));
        jLabel18.setText(df.format(numSubtotal));
        jLabel20.setText(df.format(numTotal));
        
        listaPartidas.remove(fila_seleccionada);
        cantArticulos.remove(fila_seleccionada);

        dtmPartidas.removeRow(fila_seleccionada);
        jTable3.setModel(dtmPartidas);
        jTable3.getSelectionModel().setSelectionInterval(0, 0); 
        }else
            JOptionPane.showMessageDialog(null, "No hay partidas que puedan eliminarse");
        
                   //si es la primera partida se deshabilitan los botones del cliente
       if(numPartidas==0)
           {
           jButton1.setEnabled(true);
           jComboBox5.setEnabled(true);
          
          if(jRadioButton1.isSelected() || jRadioButton2.isSelected())
          {
          jRadioButton3.setEnabled(true);
          jRadioButton4.setEnabled(true);
          jRadioButton5.setEnabled(true);
          jRadioButton6.setEnabled(true);
          jRadioButton7.setEnabled(true);
          }
          
          jRadioButton1.setEnabled(true);
          jRadioButton2.setEnabled(true);
        //  jRadioButton7.setEnabled(false);
                  
                  
           jComboBox4.setEnabled(true);
           jComboBox2.setEnabled(true);

           }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
  
    //limpiar variables
    numArticulos=0;
    numPartidas=0;
    numSubtotal=0.0;
    numTotal=0.0;
    idAdministrador=-1;
    
        //limpiar listas globales      
        listaArticulos.clear(); 
        listaClientes.clear();
        
        listaClientes = hclientes.consultaClientes("id", "=", "1");
        
        listaPartidas.clear();
        cantArticulos.clear();
        idCompletosUnicos.clear();
        codigosCompletosUnicos.clear();
          
        //limpiar componentes
        jComboBox5.removeAllItems();  
        jComboBox4.removeAllItems();
        jComboBox2.removeAllItems();
        jTextField1.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("0");
        jTextField9.setText("0");
        jTextField10.setText("1");
        jTextField11.setText("");
        jTextField15.setText("");
        jLabel14.setText("0000");
        jLabel16.setText("0000");
        jLabel18.setText("000000.00");
        jLabel20.setText("000000.00");
        
        //inicializar componentes
        //inicializa combobox
        listaClientes = hclientes.consultaClientes("id", "=", "1");
        jComboBox5.addItem(listaClientes.get(0).getNombre());
        jComboBox5.setSelectedIndex(0);
        
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil1()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil2()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil3()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil4()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil5()));
        jComboBox4.addItem(String.valueOf(listaClientes.get(0).getUtil6()));
        
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc1()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc2()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc3()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc4()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc5()));
        jComboBox2.addItem(String.valueOf(listaClientes.get(0).getDesc6()));

        // deshabilitar campos
        jComboBox4.setEnabled(false);
        jComboBox2.setEnabled(false);
        jTextField7.setEditable(false);
        jTextField8.setEditable(false);
        jTextField9.setEditable(false);

        
        //pone el radio button en false
        jRadioButton7.setSelected(true);
        
        //limpiavector
        vectorArticulos.clear();
        
        //inicializa dtm      
        dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           jTable2.setModel(dtm);

           
          
       //inicializa tablaPartidas
           vectorPartidas.clear();

           dtmPartidas = new DefaultTableModel(vectorPartidas,encabezadoPartidas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

           jTable3.setModel(dtmPartidas);
           
         /*  if(!CGlobalConfig.isDescuentosClientes())
           jRadioButton2.setEnabled(false);
           if(!CGlobalConfig.isUtilidadesClientes())
           jRadioButton1.setEnabled(false);    
           if(!CGlobalConfig.isCambiarPrecioArticulo())
           jRadioButton3.setEnabled(false);    
           if(!CGlobalConfig.isAplicarDescuentoArticulo())
           jRadioButton4.setEnabled(false);    
           if(!CGlobalConfig.isAplicarUtilidadArticulo())
           jRadioButton5.setEnabled(false);    
           if(!CGlobalConfig.isOfertas())
           jRadioButton6.setEnabled(false);    */
           
           //se habilita pq se habia deshabilitado al momento de jalar una partida
           jButton1.setEnabled(true);
           jComboBox5.setEnabled(true);
           jButton10.setEnabled(true);
           jButton6.setEnabled(true);
   
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         
        //se limpia el vector
         vectorArticulos.clear();
         
         //se crea una lista provicional
         List<Articulos>listaProvicional=new ArrayList<Articulos>();
         listaProvicional = listaArticulos;
        
        // se valida que el campo de texto no este vacio
        if(!jTextField11.getText().isEmpty())
        {
            
        //se checa si el combobox está seleccionado en almacén o en devoluciones    
        if(jComboBox1.getSelectedItem().toString().equals("Almacén General"))    
        {
        listaArticulos = harticulos.consultaArticulos("codigo", "LIKE", jTextField11.getText());
        /* int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  halmacendevoluciones.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         } */
        }
        else if (jComboBox1.getSelectedItem().toString().equals("Devoluciones"))
        {
        listaArticulos = halmacendevoluciones.consultaPartidasArticulos("codigoArticulo", "LIKE", jTextField11.getText());     
        int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  harticulos.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         }    
        
        }    
        
        // se valida que la consulta no haya llegado vacía
        if(listaArticulos.isEmpty())
        {
            listaArticulos = listaProvicional;
            JOptionPane.showMessageDialog(null, "Lo siento, la búsqueda no obtuvo resultados");
            return;
        }
        
        
        //si llegó aquí significa que la lista no está vacía y se llena el vector con lo que hay en la lista
            for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             if (iarticulos.getOferta() == 1)
             unaFila.add("OFERTA! - "+iarticulos.getDescripcion());
             else if(iarticulos.getNuevo() == 1)
             unaFila.add("NUEVO! - "+iarticulos.getDescripcion());
             else
             unaFila.add(iarticulos.getDescripcion());
              unaFila.add(iarticulos.getPrecioVenta());
             if(iarticulos.getUbicacion().equals("General"))
             {
             unaFila.add(iarticulos.getExistencia());
             unaFila.add(iarticulos.getAlmacenDevoluciones());
             }
             else if(iarticulos.getUbicacion().equals("Devoluciones"))
             {
             unaFila.add(iarticulos.getExistenciaAlmacen());
             unaFila.add(iarticulos.getExistencia());
             }
             vectorArticulos.add(unaFila);          
         }
             dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
            
           jTable2.setModel(dtm);
           jTable2.getSelectionModel().setSelectionInterval(0, 0);
           
           jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
           jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
           
           //se pone en el textfield el valor del precio de venta del primer artículo
           jTextField7.setText(String.valueOf(listaArticulos.get(0).getPrecioVenta()));
           
           
           // se borra el otro textfield
           jTextField11.setText("");     

        }
        else // aquí va en caso de que el campo de texto este vacio al momento de hacer click
        JOptionPane.showMessageDialog(null, "Por favor escriba el código del artículo en el cuadro de Texto");    
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
          
        ConexionWeb conexionweb = new ConexionWeb();
        String transaccion="";
        
        try
           {
                       
            double validadorTotal = 0;
            for(int i=0; i<listaPartidas.size(); i++)
            {
                validadorTotal = validadorTotal + listaPartidas.get(i).getSubtotal();
                
                if(listaPartidas.get(i).getUbicacion().equals("General"))
                {
                List<Articulos> validarArticulo = harticulos.consultaArticulos("codigo", "=", listaPartidas.get(i).getCodigoArticulo());
                if(Long.compare(validarArticulo.get(0).getExistencia(), (long)cantArticulos.get(i).getExistenciaCatalogo()) != 0)    
                {
                JOptionPane.showMessageDialog(null, "El artículo "+validarArticulo.get(0).getCodigo()+" ha sido utilizado en otra venta activa, \n"
                        + "por favor, elimine la partida que contiene este artículo y vuelva a buscarlo para actualizar la existencia");    
                return;
                }
                    
                }else if(listaPartidas.get(i).getUbicacion().equals("Devoluciones"))
                {
                List<Articulos> validarArticulo = halmacendevoluciones.consultaPartidasArticulos("codigoArticulo", "=", listaPartidas.get(i).getCodigoArticulo());
                if(Long.compare(validarArticulo.get(0).getExistencia(), (long)cantArticulos.get(i).getExistenciaCatalogo()) != 0)    
                {
                JOptionPane.showMessageDialog(null, "El artículo "+validarArticulo.get(0).getCodigo()+" ha sido utilizado en otra venta activa, \n"
                        + "por favor, elimine la partida que contiene este artículo y vuelva a buscarlo para actualizar la existencia");    
                return;
                }                
                }
                
                
                
                
            }
            
            if(Double.compare(validadorTotal, numTotal)!=0)
            {
                JOptionPane.showMessageDialog(null, "Venta incorrecta");
                return;
            }
            
            jButton10.setEnabled(false);
            if(dtmPartidas.getRowCount() != 0)
            {
                
                Calendar cal = Calendar.getInstance(); // will be equal to now  
                cal.add(Calendar.DAY_OF_YEAR, listaClientes.get(jComboBox5.getSelectedIndex()).getDiasCredito() );  
                long milisec = cal.getTimeInMillis();  
                Timestamp time= new Timestamp(milisec);    
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                String fecha = sdf.format(time);     
                            
             //Se crea la pantalla para confirmar la venta  y se inicializa la pantalla  
             VConfirmaVenta vconfirmaventa = new VConfirmaVenta((JFrame)Window.getWindows()[0],true);
             vconfirmaventa.setArticulos(numArticulos);
             vconfirmaventa.setPartidas(numPartidas);
             vconfirmaventa.setSubtotal(numSubtotal);
             vconfirmaventa.setTotal(numTotal);
             Usuarios user = new Usuarios();
             user.setApellidoM(CConfiguracion.getApellidoM());
             user.setApellidoP(CConfiguracion.getApellidoP());
             user.setContrasena(CConfiguracion.getContrasena());
             user.setCorreo(CConfiguracion.getCorreo());
             user.setFoto(CConfiguracion.getFoto());
             user.setId(CConfiguracion.getId());
             user.setNombres(CConfiguracion.getNombres());
             user.setPuesto(CConfiguracion.getPuesto());
             vconfirmaventa.setUser(user);
             vconfirmaventa.setCliente(listaClientes.get(jComboBox5.getSelectedIndex()).getNombre());
             vconfirmaventa.setUsuario(user.getNombres()+" "+user.getApellidoP()
                     +" "+user.getApellidoM());
             vconfirmaventa.inicializa();
             vconfirmaventa.setLocationRelativeTo(null);
             vconfirmaventa.setVisible(true);
             
             //si todo sucedio bien
            if(vconfirmaventa.isExito())    
                  {
                      
                  //se crea una fila en el kardex           
            Kardex fila = new Kardex();          
            String tipoVenta[] = {"Efectivo","Credito"};             
                      
            if(tipoVenta[vconfirmaventa.getIndice()].equals("Credito")&&listaClientes.get(jComboBox5.getSelectedIndex()).getCheckCredito()==0)
               {
                jButton10.setEnabled(true);
                JOptionPane.showMessageDialog(null,"Este cliente no tiene crédito habilitado, por favor revise en catálogo de Clientes");   
                return;
               }          
            
            if(!CPrincipal.getConexion().crearTransaccion())
            return;
            
            if(CGlobalConfig.isWeb())
            transaccion = conexionweb.iniciarTransaccion() + " ";
            //se crea el objeto venta ¿Pq lo hice local? con los valores globales
            /*PQ HICE EL OBJETO VENTA LOCAL*/
            Ventas venta = new Ventas();
            venta.setArticulos(numArticulos);
            venta.setIdadministrador(idAdministrador);
            venta.setIdcliente(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
            if(vconfirmaventa.getUser()==null) //si no hay un objeto en vconfirmaventa se toma el de aquél que abrio el sistema
            venta.setIdusuario(CConfiguracion.getId());
            else
            venta.setIdusuario(vconfirmaventa.getUser().getId());    
            venta.setObservaciones(jTextField15.getText());
            venta.setPartidas(numPartidas);
            venta.setSubtotal(numSubtotal);
            
            venta.setTipoDeVenta(tipoVenta[vconfirmaventa.getIndice()]);
            venta.setTotal(numTotal);
            venta.setEstado("Vivo");
            venta.setVale("0000");
            venta.setIva(0.0);
            
            long numVenta=0;
            if(!CGlobalConfig.isWeb())
            numVenta=hventas.guardarVentas(venta); //se guarda la venta para obtener el número de venta
            else            
            {
            transaccion = transaccion + hventas.crearQueryGuardar(venta) + " ";
            transaccion = transaccion + conexionweb.ultimoId() + " ";
            }
            
            if(tipoVenta[vconfirmaventa.getIndice()].equals("Credito")&&!listaClientes.get(jComboBox5.getSelectedIndex()).getNombre().equalsIgnoreCase("Público en general"))
            {
                
                hCuentasPorCobrar hcpc = new hCuentasPorCobrar();
                Cuentasporcobrar cpc = new Cuentasporcobrar();
                cpc.setCliente(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
                cpc.setFactura((long)-1);
                cpc.setFechaVencimiento(fecha);  
                if(!CGlobalConfig.isWeb())
                cpc.setObservacion("Venta Mostrador con número "+numVenta);
                else
                cpc.setObservacion("concat_ws(\'\', \'Venta Mostrador con número \',@ultimo_id)");    
                cpc.setPagado(0);
                cpc.setSaldo(numTotal);
                if(!CGlobalConfig.isWeb())
                cpc.setTipo("VC"+numVenta);
                else
                cpc.setTipo("concat_ws(\'\', \'VC\',@ultimo_id)");    
                cpc.setVenta(String.valueOf(numVenta));           
                
                if(!CGlobalConfig.isWeb())
                hcpc.guardarCtaPorCobrar(cpc);
                else
                transaccion = transaccion + hcpc.crearQueryGuardarCtaPorCobrar(cpc) + " ";        
            }
            
            int i=0;
            while(i<listaPartidas.size())
            {
                listaPartidas.get(i).setIdVenta(numVenta); // se le pone a cada partida el número de venta
                
                //Primero se resuelve el problema de guardar en catálogo
                  int acumulado = 0;              
                  int indiceCantidades=0;
                  boolean saberSiIdUnico=true;
                  int indiceIdCompletoUnico=0;
                  // int existenciaCatalogo=0;
                  while(indiceIdCompletoUnico<idCompletosUnicos.size())  //Para saber si el código del catálogo de artículos o de almácen es único y no entrar a él a cada rato
                  {
                      if(listaPartidas.get(i).getIdCompleto().equals(idCompletosUnicos.get(indiceIdCompletoUnico)))
                      saberSiIdUnico=false;    
                      indiceIdCompletoUnico++;
                  }
                  
                  if(saberSiIdUnico)
                  {
                  while(indiceCantidades<cantArticulos.size())  //Si no es la primera entrada se verifica si la suma de las entradas del mismo artículo es mayor a la existencia
                  {
                   // System.out.println(listaPartidas.get(i).getIdCompleto() + + );   
                   if(listaPartidas.get(i).getIdCompleto().equals(cantArticulos.get(indiceCantidades).getIdCompleto()))     
                   {
                       // existenciaCatalogo = cantArticulos.get(indiceCantidades).getExistenciaCatalogo();
                       acumulado += cantArticulos.get(indiceCantidades).getCantidadPartida();
                   }
                      indiceCantidades++;
                 
                  }
                 idCompletosUnicos.add(listaPartidas.get(i).getIdCompleto()); //verificar
                

                
                  }
                  
                //Ahora se Resuelve el Problema de Guardar en Kardex
                  boolean saberSiCodigoUnico=true;    
                  int acumuladoKardex=0;
                  int indiceCompletoKardex=0;
                  int indiceCantidadesKardex=0;            
                  
                  while(indiceCompletoKardex<codigosCompletosUnicos.size())  //Para saber si el código del catálogo de artículos o de almácen es único y no entrar a él a cada rato
                  {
                      String claveCompleta = listaPartidas.get(i).getIdCompleto()+listaPartidas.get(i).getPrecioCompra()+listaPartidas.get(i).getPrecioVenta();
                      if(claveCompleta.equals(codigosCompletosUnicos.get(indiceCompletoKardex)))
                      saberSiCodigoUnico=false;    
                      indiceCompletoKardex++;
                  }
                  List<Articulos> larticulos = harticulos.consultaArticulos("codigo", "=", listaPartidas.get(i).getCodigoArticulo());
                  if(saberSiCodigoUnico)
                        {
                  while(indiceCantidadesKardex<cantArticulos.size())  //Si no es la primera entrada se verifica si la suma de las entradas del mismo artículo es mayor a la existencia
                  {
                 //  System.out.println("listaPartidas codigo Articulo "+listaPartidas.get(i).getCodigoArticulo() + " ListaCantidades Código "+cantArticulos.get(indiceCantidadesKardex).getCodigo());  
                 //  System.out.println("listaPartidas precio compra "+listaPartidas.get(i).getPrecioCompra() + " ListaCantidades precio compra "+cantArticulos.get(indiceCantidadesKardex).getPrecioCompra());
                 //  System.out.println("listaPartidas precio venta "+listaPartidas.get(i).getPrecioVenta() + " ListaCantidades precio venta "+cantArticulos.get(indiceCantidadesKardex).getPrecioVenta());
                   if(listaPartidas.get(i).getCodigoArticulo().equals(cantArticulos.get(indiceCantidadesKardex).getCodigo())
                    &&Double.compare(listaPartidas.get(i).getPrecioCompra(),cantArticulos.get(indiceCantidadesKardex).getPrecioCompra())==0       
                    &&Double.compare(listaPartidas.get(i).getConBeneficio(),cantArticulos.get(indiceCantidadesKardex).getPrecioVenta())==0       
                     )     
                   {
                       acumuladoKardex += cantArticulos.get(indiceCantidadesKardex).getCantidadPartida();
                   }
                      indiceCantidadesKardex++;
                  }
                 codigosCompletosUnicos.add(listaPartidas.get(i).getIdCompleto()+listaPartidas.get(i).getPrecioCompra()+listaPartidas.get(i).getPrecioVenta());
                 
                 Kardex nuevoRenglon = new Kardex();
                 Kardex kardex =hkardex.consultaUltimoRenglon("articulo", "=", listaPartidas.get(i).getCodigoArticulo());
                 
                 
                 
                 
                 if(kardex==null)
                 {   
                        //Se registra la pieza en Kardex
                        kardex = new Kardex(); 
                        kardex.setAlmacenista("");
                        kardex.setAnticipos(larticulos.get(0).getAnticipos());
                        kardex.setArticulo(larticulos.get(0).getCodigo());
                        kardex.setEntrada(0);
                        kardex.setExistencias((int)larticulos.get(0).getExistencia());
                        kardex.setIdArticulo(larticulos.get(0).getId());
                        kardex.setModificacion("Registro");
                        kardex.setMovimiento("Registro de Artículo");
                        kardex.setNoMov("1");
                        kardex.setPrecioVenta(larticulos.get(0).getPrecioVenta());
                        kardex.setRefFerrari("Registro");
                        kardex.setReservados((int)larticulos.get(0).getReservado());
                        kardex.setResponsable("1");
                        kardex.setResponsable2("Registro Automático");
                        kardex.setSalida(0);
                        kardex.setUltimoCosto(larticulos.get(0).getUltimoCosto());
                        kardex.setVendidoEn(0.0);
                              
                if(!CGlobalConfig.isWeb())
                hkardex.guardarEnKardex(kardex); 
                else
                transaccion = transaccion + hkardex.crearQueryGuardarEnKardexCompleto(kardex) + " ";        
                        
                     
                     
                 }
                 int cantidadRestada = kardex.getExistencias() - acumuladoKardex;
                 int nomov=Integer.parseInt(kardex.getNoMov())+1;
                 nuevoRenglon.setAnticipos(cantArticulos.get(i).getAnticipos());
                 nuevoRenglon.setReservados(cantArticulos.get(i).getReservados());
                 nuevoRenglon.setExistencias(cantidadRestada); 
                 nuevoRenglon.setNoMov(String.valueOf(nomov));
                 nuevoRenglon.setAlmacenista("");
                 nuevoRenglon.setArticulo(listaPartidas.get(i).getCodigoArticulo());
                 nuevoRenglon.setEntrada(0);
                 nuevoRenglon.setSalida(acumuladoKardex);
                 nuevoRenglon.setIdArticulo(listaPartidas.get(i).getIdArticulo());
                 if(!CGlobalConfig.isWeb())
                 nuevoRenglon.setModificacion("");
                 else
                 nuevoRenglon.setModificacion("\'\'");    
                 if(!CGlobalConfig.isWeb())
                 nuevoRenglon.setMovimiento("Venta número"+listaPartidas.get(i).getIdVenta());
                 else
                 nuevoRenglon.setMovimiento("concat_ws(\'\', \'Venta número\',@ultimo_id)");    
                 nuevoRenglon.setPrecioVenta(listaPartidas.get(i).getPrecioVenta());
                 if(!CGlobalConfig.isWeb())
                 nuevoRenglon.setRefFerrari(String.valueOf("v"+listaPartidas.get(i).getIdVenta()));
                 else
                 nuevoRenglon.setRefFerrari(String.valueOf("concat_ws(\'\', \'v\',@ultimo_id)"));    
                 nuevoRenglon.setResponsable(String.valueOf(venta.getIdusuario()));
                 nuevoRenglon.setUltimoCosto(listaPartidas.get(i).getPrecioCompra());
                 nuevoRenglon.setVendidoEn(listaPartidas.get(i).getConBeneficio());
                 nuevoRenglon.setResponsable2(nombreAdministrador);    
     
                if(!CGlobalConfig.isWeb())
                hkardex.guardarEnKardex(nuevoRenglon); 
                else
                transaccion = transaccion + hkardex.crearQueryGuardarEnKardex(nuevoRenglon) + " ";                             
                        }
                
                 // se guarda la partida en el ciclo externo
                if(!CGlobalConfig.isWeb())
                hpartidas.guardarPartidas(listaPartidas.get(i));
                else
                transaccion = transaccion + hpartidas.crearQueryGuardarPartidas(listaPartidas.get(i)) + " "; 
                
                
                int cantidadADescontar = cantArticulos.get(i).getExistenciaCatalogo()-acumulado;
                //  System.out.println("Existencia "+cantArticulos.get(i).getExistenciaCatalogo() +"Acumulado "+acumulado);    
                 
                 if(listaPartidas.get(i).getUbicacion().equals("General"))
                 {
                 cantidadADescontar = (int)larticulos.get(0).getExistencia() - acumulado;    
                 
                 
                if(!CGlobalConfig.isWeb())
                harticulos.actualizarExistencias(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar);
                else
                transaccion = transaccion + harticulos.crearQueryActualizarExistencias(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar) + " "; 
                 
                 
                 
                 }else if(listaPartidas.get(i).getUbicacion().equals("Devoluciones"))
                 {
              //    System.out.println("Id del Artículo "+listaPartidas.get(i).getIdArticulo());    
                     
                
                if(!CGlobalConfig.isWeb())
                {
                halmacendevoluciones.actualizarExistencias(listaPartidas.get(i).getIdArticulo(), cantidadADescontar);
                harticulos.actualizarExistenciasDev(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar);
                }
                else
                {
                transaccion = transaccion + halmacendevoluciones.crearQueryActualizarExistencias(listaPartidas.get(i).getIdArticulo(), cantidadADescontar) + " "; 
                transaccion = transaccion + harticulos.crearQueryActualizarExistenciasDev(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar) + " "; 
                }
                 
                 
                 
                 }
                
                
                
                
                        i++;
                 }
            
               CPrincipal.getConexion().finalizarTransaccion();
               
               if(CGlobalConfig.isWeb())
               {
               transaccion = transaccion + conexionweb.finalizarTransaccion();    
               conexionweb.escribirEnWeb(transaccion);
               Ventas ultventa = hventas.consultaUltimaVenta("idusuario", "=", String.valueOf(vconfirmaventa.getUser().getId()));
               numVenta = ultventa.getId();
               }
               
                
            /*    
                
            long idArt = listaPartidas.get(i).getIdArticulo(); //se obtiene el id del artículo de cada partida (este id puede estar repetido por lo de
                                                               // almacen y devoluciones)
            
            
            int ind=0;
            
            ME PARECE QUE ESTO VA A ESTAR MAL Y TIENE QUE VER CON EL KARDEX Y CON LO QUE SE VA A REDUCIR EN LOS CATÁLOGOS HAY QUE CORREGIR Y USAR IDCOMPLETO
             ESTO NO SIRVE PORQUE ADEMAS CANTARTICULOS SÓLO ACEPTA VALORES ÚNICOS Y YA NO PUEDE SER ASÍ
             
            boolean banderaCant=true; //bandera que sirve para cortar el while y ya no terminarlo de recorrer cuando se encuentra un artículo
            while(banderaCant&&ind<cantArticulos.size()) //verificar
            {
                if(cantArticulos.get(ind).getId() == idArt) //compara si el id de la cantidad es igual al id del articulo.
                {
                  int nuevaCantidad = (int)cantArticulos.get(ind).getMaximoPzas()-(int)cantArticulos.get(ind).getExistencia(); //calcula una nueva cantidad
                                                                                                     //restando el máximo de piezas a la existencia
                              
                  if(cantArticulos.get(ind).getUbicacion().equals("General")) //si el item es general se actualiza en articulos
                  harticulos.actualizarExistencias(idArt, nuevaCantidad); 
                  else if(cantArticulos.get(ind).getUbicacion().equals("Devoluciones")) 
                  {
                  halmacendevoluciones.actualizarExistencias(idArt, nuevaCantidad);  //si el item es en devoluciones se actualiza en devoluciones
                  }
                  
                  //se termina de llenar el kardex
                fila.setAlmacenista("");
                fila.setArticulo(listaPartidas.get(i).getCodigoArticulo()); 
                fila.setEntrada(0);
                fila.setModificacion(""); 
                fila.setMovimiento("Venta Artículo");
                
                //esto está padre, se consulta el último renglón y se resta de ese resultado la existencia de cada partida...
                Kardex renglon = hkardex.consultaUltimoRenglon("articulo", "=", listaPartidas.get(i).getCodigoArticulo());
                
                //si no existe el renglon se resta del catálogo de artículos la primera partida, ya que no se puede dar de alta directamente en devoluciones...
                //por lo que todo código debería estar primero en el catálogo de artículos, aunque después estuviera vacío.
                if(renglon == null)
                {
                    fila.setId(idArt);
                    fila.setNoMov("1");
                    fila.setReservados(0);
                    
                   // if(nuevaCantidad < 0)
                   // nuevaCantidad = 0; 
                    fila.setExistencias(nuevaCantidad);
                }else
                {
                    fila.setIdArticulo(renglon.getIdArticulo());
                    int nomov = Integer.parseInt(renglon.getNoMov());
                    nomov++;
                    fila.setNoMov(String.valueOf(nomov));
                    fila.setReservados(renglon.getReservados());
                    fila.setExistencias(renglon.getExistencias() - listaPartidas.get(i).getCantidad()); 
                }    
                
                //se termina de llenar el kardex con el valor de cada partida... 
                fila.setPrecioVenta(listaPartidas.get(i).getConBeneficio());
                fila.setRefFerrari(String.valueOf(numVenta)); 
                fila.setResponsable(String.valueOf(vconfirmaventa.getUser().getId()));
                fila.setSalida(listaPartidas.get(i).getCantidad());
                fila.setUltimoCosto(listaPartidas.get(i).getPrecioCompra());
                
                //se guarda en el kardex la partida
                hkardex.guardarEnKardex(fila);
                  
                
                  banderaCant=false; //se para dejar de recorrer el ciclo interno indicando que ya se encontró el artículo y pasando a la siguiente partida
                }else
                ind++; //se sigue recorriendo hasta encontrar el artículo.
                
            }
      
            */
            /*-----------------------------------------------------*/
            // jButton9.doClick(); // se oprime el botón de cancelar después de realizar todo lo que se tiene que guardar
            
           /* try
            {
            if(vconfirmaventa.getUser()==null) //si no hay un objeto en vconfirmaventa se toma el de aquél que abrio el sistema
            vendedor = CConfiguracion.getNombres() +" "+ CConfiguracion.getApellidoP() +" "+ CConfiguracion.getApellidoM();
            else
            vendedor = vconfirmaventa.getUser().getNombres() + " "+ vconfirmaventa.getUser().getApellidoP() + " "+vconfirmaventa.getUser().getApellidoM(); 
            numeroVenta = numVenta;   
            fechaDeVenta = fecha;
            
            ImpresionVenta iv = new ImpresionVenta();
            iv.inicializar(String.valueOf(numeroVenta),listaClientes.get(jComboBox5.getSelectedIndex()).getNombre(),
                                                   String.valueOf(listaPartidas.size()),vendedor, fechaDeVenta, listaPartidas);
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(iv);
            if (job.printDialog())
                job.print();
            }catch (PrinterException ex) {
                Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            int it7=0;
            int numArtImp=0;
            while(it7<listaPartidas.size())
            {
                numArtImp = numArtImp + listaPartidas.get(it7).getCantidad();
                it7++;
            }

            hCuentasPorCobrar hcc = new hCuentasPorCobrar();    
            fechaDeVenta = hcc.generarFecha().substring(0, 19);    
            
            try
            {   

            ImpresionTicketVenta itv = new ImpresionTicketVenta();
            formaVenta = venta.getTipoDeVenta();
            iva = venta.getIva();
            itv.inicializar(String.valueOf(numVenta),listaClientes.get(jComboBox5.getSelectedIndex()).getNombre(),
                                                   String.valueOf(numArtImp),vconfirmaventa.getUser().getNombres()+" "+vconfirmaventa.getUser().getApellidoP()+" "+vconfirmaventa.getUser().getApellidoM(), fechaDeVenta, listaPartidas,
                                                   String.valueOf(venta.getTipoDeVenta()), df.format(venta.getSubtotal()),
                                                   df.format(venta.getTotal()),df.format(venta.getIva()));
            PrinterJob job = PrinterJob.getPrinterJob();
            // job.setPrintable(itv);
            // PageFormat format = job.pageDialog(job.defaultPage());
            
            PageFormat format = new PageFormat();
            
            
            Paper p = new Paper();
            
            p.setSize(CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
            p.setImageableArea(CGlobalConfig.getxImp(),CGlobalConfig.getyImp(),CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
            format.setPaper(p);
            
            job.setPageable(new PageableText(itv.generarCadenaImpresion(), format));
            
            if (job.printDialog())
                job.print();
            }       catch (IOException ex) {
                        Logger.getLogger(VRealizar_Venta.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (PrinterException ex) {
                Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
              try
            {        
            ImpresionTicketVenta itv = new ImpresionTicketVenta();
            formaVenta = venta.getTipoDeVenta();
            iva = venta.getIva();
            itv.inicializar(String.valueOf(numVenta),listaClientes.get(jComboBox5.getSelectedIndex()).getNombre(),
                                                   String.valueOf(numArtImp),vconfirmaventa.getUser().getNombres()+" "+vconfirmaventa.getUser().getApellidoP()+" "+vconfirmaventa.getUser().getApellidoM(), fechaDeVenta, listaPartidas,
                                                   String.valueOf(venta.getTipoDeVenta()), df.format(venta.getSubtotal()),
                                                   df.format(venta.getTotal()),df.format(venta.getIva()));
            PrinterJob job = PrinterJob.getPrinterJob();
          //  job.setPrintable(itv);
          //  PageFormat format = job.pageDialog(job.defaultPage());
            
            PageFormat format = new PageFormat();
            Paper p = new Paper();
            
            p.setSize(CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
            p.setImageableArea(CGlobalConfig.getxImp(),CGlobalConfig.getyImp(),CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
            format.setPaper(p);
            job.setPageable(new PageableText(itv.generarCadenaImpresion(), format));
            
            
            if (job.printDialog())
                job.print();
            }       catch (IOException ex) {
                        Logger.getLogger(VRealizar_Venta.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (PrinterException ex) {
                Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            
            /*-----------------------------------------------------*/
            
            JOptionPane.showMessageDialog(null, "Venta Realizada!");       //se para dejar de recorrer el ciclo interno indicando que ya se encontró el artículo y pasando a la siguiente partida
            
            if(CGlobalConfig.isHabilitarRed())
            {
            int elige = JOptionPane.showOptionDialog(null, "¿Desea publicar las partidas vendidas en la Red Quimera?","Publicar en Red Quimera",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            
            if(elige == JOptionPane.YES_OPTION)
             {
            ConexionWeb conexionWEB = new ConexionWeb();
            String crearCadena = "HA VENDIDO: </br>";
            for(int io=0; io<listaPartidas.size(); io++)
            {
                crearCadena = crearCadena + " "+listaPartidas.get(io).getDescripcionArticulo() + "</br>";
            }
            //Formatear Cadena
            crearCadena.replace("á", "&aacute");
            crearCadena.replace("é", "&eacute");
            crearCadena.replace("í", "&iacute");
            crearCadena.replace("ó", "&oacute");
            crearCadena.replace("ú", "&uacute");
            crearCadena.replace("Á", "&Aacute");
            crearCadena.replace("É", "&Eacute");
            crearCadena.replace("Í", "&Iacute");
            crearCadena.replace("Ó", "&Oacute");
            crearCadena.replace("Ú", "&Uacute");
            
            conexionWEB.publicarEnRed(crearCadena);
            JOptionPane.showMessageDialog(null, "Artículos Publicados");
             }
            
            }
            
            
            
            
                  }
            }else
                JOptionPane.showMessageDialog(null, "No hay partidas que guardar"); //se sigue recorriendo hasta encontrar el artículo.
            
            jButton9.doClick();
               }catch(Exception e)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            e.printStackTrace();            
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        jButton3.doClick();
        jTextField10.requestFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        jButton4.doClick();
        jTextField10.requestFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
        jButton13.doClick();
        jTextField10.requestFocus();
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
        hCuentasPorCobrar hcpc = new hCuentasPorCobrar();
        String id="";
        if(jComboBox5.getSelectedIndex() == -1)
        id = String.valueOf(listaClientes.get(0).getId());
        else
        id = String.valueOf(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
        
        if(hcpc.tieneNoPagado("cliente", "=",id))
        {
            if(id.equals("1"))
            jCheckBox1.setSelected(false);    
            else
            jCheckBox1.setSelected(true);
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
             if(jCheckBox1.isSelected())
             {
             JOptionPane.showMessageDialog(null,"Este cliente no tiene cuentas vencidas"); 
             jCheckBox1.setSelected(false);
             return;    
             }
        
              String password;
              JPasswordField passwordField = new JPasswordField();
              passwordField.setName("ignore_upper_case");
              Object[] obj = {"Por favor escriba la contraseña de algún administrador para aplicar permitir vendera este cliente:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
               
               int opcion = JOptionPane.showOptionDialog(null, obj, "Contraseña requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion != JOptionPane.YES_OPTION)   
             {
             jCheckBox1.setSelected(true);   
             return;
             }
             else if(opcion == JOptionPane.YES_OPTION) //el usuario dio click en aceptar
             {
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             Autenticar autenticar = new Autenticar();
             if (!autenticar.validarPwd(password))
             {    
             jCheckBox1.setSelected(true);    
             JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
             return;
             }else
                  {
  //           Usuarios usuariogeneral=autenticar.getUsuariogeneral();
             jCheckBox1.setSelected(false);
             idAdministrador = autenticar.getId();
                   }
        
             }
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
                if(dtmPartidas.getRowCount() != 0)
        {
            Calendar cal = Calendar.getInstance(); // will be equal to now  
            cal.add(Calendar.DAY_OF_YEAR, listaClientes.get(jComboBox5.getSelectedIndex()).getDiasCredito() );  
            long milisec = cal.getTimeInMillis();  
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);   
            
            
         //Se crea la pantalla para confirmar la venta  y se inicializa la pantalla  
         VConfirmaVenta vconfirmaventa = new VConfirmaVenta((JFrame)Window.getWindows()[0],true);
         vconfirmaventa.setArticulos(numArticulos);
         vconfirmaventa.setPartidas(numPartidas);
         vconfirmaventa.setSubtotal(numSubtotal);
         vconfirmaventa.setTotal(numTotal);
         Usuarios user = new Usuarios();
         user.setApellidoM(CConfiguracion.getApellidoM());
         user.setApellidoP(CConfiguracion.getApellidoP());
         user.setContrasena(CConfiguracion.getContrasena());
         user.setCorreo(CConfiguracion.getCorreo());
         user.setFoto(CConfiguracion.getFoto());
         user.setId(CConfiguracion.getId());
         user.setNombres(CConfiguracion.getNombres());
         user.setPuesto(CConfiguracion.getPuesto());
         vconfirmaventa.setUser(user);
         vconfirmaventa.setCliente(listaClientes.get(jComboBox5.getSelectedIndex()).getNombre());
         vconfirmaventa.setUsuario(user.getNombres()+" "+user.getApellidoP()
                 +" "+user.getApellidoM());
         vconfirmaventa.inicializa();
         vconfirmaventa.setLocationRelativeTo(null);
         vconfirmaventa.setVisible(true);
         
         //si todo sucedio bien
        if(vconfirmaventa.isExito())    
              {
        
       //se crea una fila en el kardex                   
        String tipoVenta[] = {"Efectivo","Credito"};          
        
        //se crea el objeto venta ¿Pq lo hice local? con los valores globales
        /*PQ HICE EL OBJETO VENTA LOCAL*/
        Ventas venta = new Ventas();
        venta.setArticulos(numArticulos);
        venta.setIdadministrador(idAdministrador);
        venta.setIdcliente(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
        if(vconfirmaventa.getUser()==null) //si no hay un objeto en vconfirmaventa se toma el de aquél que abrio el sistema
        venta.setIdusuario(CConfiguracion.getId());
        else
        venta.setIdusuario(vconfirmaventa.getUser().getId());    
        venta.setObservaciones(jTextField15.getText());
        venta.setPartidas(numPartidas);
        venta.setSubtotal(numSubtotal);
        
        venta.setTipoDeVenta(tipoVenta[vconfirmaventa.getIndice()]);
        venta.setTotal(numTotal);
        venta.setEstado("Vivo");
        venta.setVale("0000");
        venta.setIva(0.0);
        long numVenta = 0;
        numeroVenta = numVenta;
        
        try
        {        
        ImpresionCotizacionVenta itv = new ImpresionCotizacionVenta();
        formaVenta = venta.getTipoDeVenta();
        iva = venta.getIva();
        itv.inicializar(String.valueOf(numeroVenta),listaClientes.get(jComboBox5.getSelectedIndex()).getNombre(),
                                               String.valueOf(listaPartidas.size()),vconfirmaventa.getUser().getNombres()+" "+vconfirmaventa.getUser().getApellidoP()+" "+vconfirmaventa.getUser().getApellidoM(), fecha, listaPartidas,
                                               "COTIZACIÓN", String.valueOf(venta.getSubtotal()),
                                               String.valueOf(venta.getTotal()),String.valueOf(venta.getIva()));
        PrinterJob job = PrinterJob.getPrinterJob();
      //  job.setPrintable(itv);
       // PageFormat format = job.pageDialog(job.defaultPage());
        PageFormat format = new PageFormat();
        
        
        Paper p = new Paper();
        
        p.setSize(CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        p.setImageableArea(CGlobalConfig.getxImp(),CGlobalConfig.getyImp(),CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        format.setPaper(p);
        
        job.setPageable(new PageableText(itv.generarCadenaImpresion(), format));
        if (job.printDialog())
            job.print();
        }       catch (IOException ex) {
                    Logger.getLogger(VRealizar_Venta.class.getName()).log(Level.SEVERE, null, ex);
                }catch (PrinterException ex) {
            Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
        /*-----------------------------------------------------*/
        
        JOptionPane.showMessageDialog(null, "Cotización Realizada!");       //mensaje indicando que ya se realizó la venta
              }
        }else
            JOptionPane.showMessageDialog(null, "No hay partidas para esta cotización"); //en caso de que el dtmPartidas sea igual a cero.
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        if(jComboBox5.getSelectedIndex()==-1)
        {
        JOptionPane.showMessageDialog(null, "No existe ningún cliente seleccionado");    
        return;
        }
        
        
               if(dtmPartidas.getRowCount() != 0)
        {
          
        /*   Calendar cal = Calendar.getInstance(); // will be equal to now  
            cal.add(Calendar.DAY_OF_YEAR, listaClientes.get(jComboBox5.getSelectedIndex()).getDiasCredito() );  
            long milisec = cal.getTimeInMillis();  
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);     
            
         //Se crea la pantalla para confirmar la venta  y se inicializa la pantalla  
         VConfirmaVenta vconfirmaventa = new VConfirmaVenta((JFrame)Window.getWindows()[0],true);
         vconfirmaventa.setArticulos(numArticulos);
         vconfirmaventa.setPartidas(numPartidas);
         vconfirmaventa.setSubtotal(numSubtotal);
         vconfirmaventa.setTotal(numTotal);
         Usuarios user = new Usuarios();
         user.setApellidoM(CConfiguracion.getApellidoM());
         user.setApellidoP(CConfiguracion.getApellidoP());
         user.setContrasena(CConfiguracion.getContrasena());
         user.setCorreo(CConfiguracion.getCorreo());
         user.setFoto(CConfiguracion.getFoto());
         user.setId(CConfiguracion.getId());
         user.setNombres(CConfiguracion.getNombres());
         user.setPuesto(CConfiguracion.getPuesto());
         vconfirmaventa.setUser(user);
         vconfirmaventa.setCliente(listaClientes.get(jComboBox5.getSelectedIndex()).getNombre());
         vconfirmaventa.setUsuario(user.getNombres()+" "+user.getApellidoP()
                 +" "+user.getApellidoM());
         vconfirmaventa.inicializa();
         vconfirmaventa.setLocationRelativeTo(null);
         vconfirmaventa.setVisible(true);
         
         //si todo sucedio bien
        if(vconfirmaventa.isExito())    
              {
        
       //se crea una fila en el kardex                   
        String tipoVenta[] = {"Efectivo","Credito"};          
        
        //se crea el objeto venta ¿Pq lo hice local? con los valores globales
        PQ HICE EL OBJETO VENTA LOCAL
        Ventas venta = new Ventas();
        venta.setArticulos(numArticulos);
        venta.setIdadministrador(idAdministrador);
        venta.setIdcliente(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
        if(vconfirmaventa.getUser()==null) //si no hay un objeto en vconfirmaventa se toma el de aquél que abrio el sistema
        venta.setIdusuario(CConfiguracion.getId());
        else
        venta.setIdusuario(vconfirmaventa.getUser().getId());    
        venta.setObservaciones(jTextField15.getText());
        venta.setPartidas(numPartidas);
        venta.setSubtotal(numSubtotal);
        
        venta.setTipoDeVenta(tipoVenta[vconfirmaventa.getIndice()]);
        venta.setTotal(numTotal);
        venta.setEstado("Vivo");
        venta.setVale("0000");
        venta.setIva(0.0);
        long numVenta = 0;
        numeroVenta = numVenta;
        
        if(vconfirmaventa.getUser()==null) //si no hay un objeto en vconfirmaventa se toma el de aquél que abrio el sistema
        vendedor = CConfiguracion.getNombres() +" "+ CConfiguracion.getApellidoP() +" "+ CConfiguracion.getApellidoM();
        else
        vendedor = vconfirmaventa.getUser().getNombres() + " "+ vconfirmaventa.getUser().getApellidoP() + " "+vconfirmaventa.getUser().getApellidoM(); 
        numeroVenta = numVenta;   
        fechaDeVenta = fecha;     */
        vendedor = CConfiguracion.getNombres() + " "+CConfiguracion.getApellidoP() + " " + CConfiguracion.getApellidoM();    
        hCuentasPorCobrar hcc = new hCuentasPorCobrar();     
            
            
          try
        {        
      //  Book book = new Book();    
        ImpresionPedido ip = new ImpresionPedido();
      //  PageFormat pf = new PageFormat();
      //  Paper p = pf.getPaper();
      //  System.out.println("W "+p.getImageableWidth()+" H "+p.getImageableHeight()+" X "+p.getImageableX()+" Y "+p.getImageableY());
      //  pf.getPaper();
        
        if(CGlobalConfig.isPedidos())
        {
        for(int i =0; i<CGlobalConfig.getId_cliente().length; i++)
        {
        if(Long.compare(CGlobalConfig.getId_cliente()[i],listaClientes.get(jComboBox5.getSelectedIndex()).getId())==0)
            tituloP=CGlobalConfig.getMen_cliente()[i];
        }
        }
        
        
        ip.inicializar(String.valueOf(0), String.valueOf(listaPartidas.size()), vendedor, hcc.generarFecha().substring(0, 19), listaPartidas,tituloP);
        PrinterJob job = PrinterJob.getPrinterJob();
        // PageFormat format = job.pageDialog(job.defaultPage());
        PageFormat format = new PageFormat();
        
        
        Paper p = new Paper();
        
        p.setSize(CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        p.setImageableArea(CGlobalConfig.getxImp(),CGlobalConfig.getyImp(),CGlobalConfig.getAnchoImp(),CGlobalConfig.getAltoImp());
        format.setPaper(p);

        
        // p.setImageableArea(30,0,215.4330706464,841.8897629208);
        // p.setSize(215.4330706464,841.8897629208);
        
       // pf.getImageableWidth();
       // pf.getImageableHeight();
        // job.setPrintable(ip);
      //  book.append(ip, pf);
       // job.setPageable(book);
        
        
       // job.setPrintable(ip);
        job.setPageable(new PageableText(ip.generarCadenaImpresion(), format));
        
        if (job.printDialog())
            job.print();
        }   catch (IOException ex) {
                Logger.getLogger(VRealizar_Venta.class.getName()).log(Level.SEVERE, null, ex);
            }catch (PrinterException ex) {
            Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        /*
            try
        {        
      //  Book book = new Book();    
        ImpresionPedido ip = new ImpresionPedido();
      //  PageFormat pf = new PageFormat();
      //  Paper p = pf.getPaper();
      //  System.out.println("W "+p.getImageableWidth()+" H "+p.getImageableHeight()+" X "+p.getImageableX()+" Y "+p.getImageableY());
      //  pf.getPaper();
        
        if(CGlobalConfig.isPedidos())
        {
        for(int i =0; i<CGlobalConfig.getId_cliente().length; i++)
        {
        if(Long.compare(CGlobalConfig.getId_cliente()[i],listaClientes.get(jComboBox5.getSelectedIndex()).getId())==0)
            tituloP=CGlobalConfig.getMen_cliente()[i];
        }
        }
        
        
        ip.inicializar(String.valueOf(0), String.valueOf(listaPartidas.size()), vendedor, hcc.generarFecha().substring(0, 19), listaPartidas,tituloP);
        PrinterJob job = PrinterJob.getPrinterJob();
       // pf.getImageableWidth();
       // pf.getImageableHeight();
        // job.setPrintable(ip);
      //  book.append(ip, pf);
       // job.setPageable(book);
        job.setPrintable(ip);
        if (job.printDialog())
            job.print();
        }catch (PrinterException ex) {
            Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
        }        
           */
        /*-----------------------------------------------------*/
        
        JOptionPane.showMessageDialog(null, "Pedido Realizado!");       //mensaje indicando que ya se realizó la venta
    //          }
        }else
            JOptionPane.showMessageDialog(null, "No hay partidas para este pedido"); //en caso de que el dtmPartidas sea igual a cero.
        
        
        
      
        

        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        jButton1.doClick();
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
        jButton11.doClick();
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        
        if(jTable3.getSelectedRow() == -1)
            return;
        
        if(evt.getClickCount() == 2)
        {
               
        //se limpia el vector
         vectorArticulos.clear();
         
         //se crea una lista provicional
         List<Articulos>listaProvicional=new ArrayList<Articulos>();
         listaProvicional = listaArticulos;
        
            
        //se checa si el combobox está seleccionado en almacén o en devoluciones    
        if(listaPartidas.get(jTable3.getSelectedRow()).getUbicacion().equals("General"))    
        {
        listaArticulos = harticulos.consultaArticulos("codigo", "LIKE", listaPartidas.get(jTable3.getSelectedRow()).getCodigoArticulo());
        /* int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  halmacendevoluciones.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         } */
        }
        else if (listaPartidas.get(jTable3.getSelectedRow()).getUbicacion().equals("Devoluciones"))
        {
        listaArticulos = halmacendevoluciones.consultaPartidasArticulos("codigoArticulo", "LIKE", listaPartidas.get(jTable3.getSelectedRow()).getCodigoArticulo());     
        int indice = 0;
        while(indice<listaArticulos.size())
         {
          int existenciaAlmacen =  harticulos.consultaExistenciasCodigo(listaArticulos.get(indice).getCodigo());
          listaArticulos.get(indice).setExistenciaAlmacen(existenciaAlmacen);
          indice++;
         }    
        
        }    
        
        // se valida que la consulta no haya llegado vacía
        if(listaArticulos.isEmpty())
        {
            listaArticulos = listaProvicional;
            JOptionPane.showMessageDialog(null, "Lo siento, la búsqueda no obtuvo resultados");
            return;
        }
        
        
        //si llegó aquí significa que la lista no está vacía y se llena el vector con lo que hay en la lista
            for(Object o: listaArticulos){
             Articulos iarticulos = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(iarticulos.getCodigo());
             unaFila.add(iarticulos.getDescripcion());
              unaFila.add(iarticulos.getPrecioVenta());
             if(iarticulos.getUbicacion().equals("General"))
             {
             unaFila.add(iarticulos.getExistencia());
             unaFila.add(iarticulos.getAlmacenDevoluciones());
             }
             else if(iarticulos.getUbicacion().equals("Devoluciones"))
             {
             unaFila.add(iarticulos.getExistenciaAlmacen());
             unaFila.add(iarticulos.getExistencia());
             }
             vectorArticulos.add(unaFila);          
         }
             dtm = new DefaultTableModel(vectorArticulos,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
            
           jTable2.setModel(dtm);
           jTable2.getSelectionModel().setSelectionInterval(0, 0);
           
           jTable2.getColumnModel().getColumn(0).setPreferredWidth(70);
           jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
           
           //se pone en el textfield el valor del precio de venta del primer artículo
           jTextField7.setText(String.valueOf(listaArticulos.get(0).getPrecioVenta()));
           
           
           // se borra el otro textfield
           jTextField11.setText("");     

        
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
      //  numSubtotal
      //  numTotal
      //  listaPartidas

        
              double desc=0;
              String password;
              JPasswordField passwordField = new JPasswordField();
              Object[] obj = {"Por favor escriba la contraseña de algún administrador para aplicar este beneficio:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
               
               int opcion = JOptionPane.showOptionDialog(null, obj, "Contraseña requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion != JOptionPane.YES_OPTION )      
             return;
             else if(opcion == JOptionPane.YES_OPTION) //el usuario dio click en aceptar
             {
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             Autenticar autenticar = new Autenticar();
             if (!autenticar.validarPwd(password))
             {    
             JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
             return;
             }else
                  {
             String descuento = JOptionPane.showInputDialog(null, "¿Cuál es el descuento que desea aplicar?","0");                               
             if(descuento.isEmpty() || descuento == null)         
                 return;
             if(Double.valueOf(descuento)<0)         
                 return;
             Usuarios usuariogeneral=autenticar.getUsuariogeneral();
             if(jRadioButton4.isSelected())
             {
             if(Double.compare(Double.valueOf(descuento), usuariogeneral.getDescMax())>0)
                 {JOptionPane.showMessageDialog(null, "El administrador no tiene permisos para otorgar un descuento mayor al "+usuariogeneral.getDescMax()+"%, "
                         + "por favor, reduzca el descuento");
                 return;
                 }
              }
             numSubtotal = 0;
             numTotal = 0;    
             desc = Double.valueOf(descuento);
             desc = desc / 100;
             idAdministrador = autenticar.getId();
             nombreAdministrador = autenticar.getUsuariogeneral().getNombres() + " "+autenticar.getUsuariogeneral().getApellidoP()+" "+autenticar.getUsuariogeneral().getApellidoM();
             
             for(int i=0; i<listaPartidas.size(); i++)
             {
        double precioConDescuento = listaPartidas.get(i).getConBeneficio();         
        listaArticulos = harticulos.consultaArticulos("codigo", "=", listaPartidas.get(i).getCodigoArticulo());
        double compraIva =  (listaArticulos.get(0).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(0).getPrecioCompra();
        double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;        
        
       
        if(precioConDescuento < validadorCompra)
        {
            JOptionPane.showMessageDialog(null, "El beneficio de un artículo en la lista menos el iva es menor al costo de ese artículo, descuento general no aplicado");
                return;
        }
             }
             
                 for(int i=0; i<listaPartidas.size(); i++)
        {
        double precioConDescuento = listaPartidas.get(i).getConBeneficio();
        double beneficio = listaPartidas.get(i).getConBeneficio() * listaPartidas.get(i).getCantidad();
        precioConDescuento = precioConDescuento - (precioConDescuento * desc);
        listaPartidas.get(i).setConBeneficio(precioConDescuento);
        cantArticulos.get(i).setPrecioVenta(precioConDescuento);
        listaPartidas.get(i).setSubtotal(precioConDescuento * listaPartidas.get(i).getCantidad());
        listaPartidas.get(i).setTipoBeneficio("Descuento total sobre Venta");
        listaPartidas.get(i).setBeneficio(beneficio);
        numSubtotal = numSubtotal + (listaPartidas.get(i).getPrecioVenta() * listaPartidas.get(i).getCantidad());
        numTotal = numTotal + listaPartidas.get(i).getSubtotal();
        dtmPartidas.setValueAt(df.format(listaPartidas.get(i).getConBeneficio()), i, 3);
        dtmPartidas.setValueAt(df.format(listaPartidas.get(i).getSubtotal()), i, 5);
        }
        dtmPartidas.fireTableDataChanged();
        jLabel18.setText(df.format(numSubtotal));
        jLabel20.setText(df.format(numTotal));
        JOptionPane.showMessageDialog(null, "Descuento aplicado!");
        jButton6.setEnabled(false);
                   }
              }
        
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        if(jTable3.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        
              double desc=0;
              String password;
              JPasswordField passwordField = new JPasswordField();
              Object[] obj = {"Por favor escriba la contraseña de algún administrador para aplicar este beneficio:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
               
               int opcion = JOptionPane.showOptionDialog(null, obj, "Contraseña requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion != JOptionPane.YES_OPTION)      
             return;
             
             Autenticar autenticar = new Autenticar();
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             if (!autenticar.validarPwd(password))
             {    
             JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
             return;
             }else
                  {
             String descuento = JOptionPane.showInputDialog(null, "¿Cuál es el descuento que desea aplicar?","0");                               
             if(descuento.isEmpty() || descuento == null)         
                 return;
             if(Double.valueOf(descuento)<0)         
                 return;
             
             Usuarios usuariogeneral=autenticar.getUsuariogeneral();
             if(jRadioButton4.isSelected())
             {
             if(Double.compare(Double.valueOf(descuento), usuariogeneral.getDescMax())>0)
                 {JOptionPane.showMessageDialog(null, "El administrador no tiene permisos para otorgar un descuento mayor al "+usuariogeneral.getDescMax()+"%, "
                         + "por favor, reduzca el descuento");
                 return;
                 }
              }
             
             
             numSubtotal = numSubtotal - (listaPartidas.get(jTable3.getSelectedRow()).getPrecioVenta() * listaPartidas.get(jTable3.getSelectedRow()).getCantidad());
             numTotal = numTotal - listaPartidas.get(jTable3.getSelectedRow()).getSubtotal();


             desc = Double.valueOf(descuento);
             desc = desc / 100;
             idAdministrador = autenticar.getId();
             nombreAdministrador = autenticar.getUsuariogeneral().getNombres() + " "+autenticar.getUsuariogeneral().getApellidoP()+" "+autenticar.getUsuariogeneral().getApellidoM();
                  
        double precioConDescuento = listaPartidas.get(jTable3.getSelectedRow()).getConBeneficio();
        double beneficio = listaPartidas.get(jTable3.getSelectedRow()).getConBeneficio() * listaPartidas.get(jTable3.getSelectedRow()).getCantidad();
        precioConDescuento = precioConDescuento - (precioConDescuento * desc);
        
        listaArticulos = harticulos.consultaArticulos("codigo", "=", listaPartidas.get(jTable3.getSelectedRow()).getCodigoArticulo());        
        double compraIva =  (1 + CGlobalConfig.getIvaVenta())*listaArticulos.get(0).getPrecioCompra();
        double validadorCompra = (1+ CGlobalConfig.getUtilidad()) * compraIva;        
        
       
        if(precioConDescuento < validadorCompra)
        {
            JOptionPane.showMessageDialog(null, "El beneficio de este artículo menos el iva es menor al costo de este artículo");
                return;
        }
        
        
        listaPartidas.get(jTable3.getSelectedRow()).setConBeneficio(precioConDescuento);
        cantArticulos.get(jTable3.getSelectedRow()).setPrecioVenta(precioConDescuento);
        listaPartidas.get(jTable3.getSelectedRow()).setSubtotal(precioConDescuento * listaPartidas.get(jTable3.getSelectedRow()).getCantidad());
        listaPartidas.get(jTable3.getSelectedRow()).setTipoBeneficio("Descuento total sobre Venta");
        listaPartidas.get(jTable3.getSelectedRow()).setBeneficio(beneficio);
        numSubtotal = numSubtotal + (listaPartidas.get(jTable3.getSelectedRow()).getPrecioVenta() * listaPartidas.get(jTable3.getSelectedRow()).getCantidad());
        numTotal = numTotal + listaPartidas.get(jTable3.getSelectedRow()).getSubtotal();
        dtmPartidas.setValueAt(df.format(listaPartidas.get(jTable3.getSelectedRow()).getConBeneficio()), jTable3.getSelectedRow(), 3);
        dtmPartidas.setValueAt(df.format(listaPartidas.get(jTable3.getSelectedRow()).getSubtotal()), jTable3.getSelectedRow(), 5);
        
        dtmPartidas.fireTableDataChanged();
        jLabel18.setText(df.format(numSubtotal));
        jLabel20.setText(df.format(numTotal));
        JOptionPane.showMessageDialog(null, "Descuento aplicado!");     
                  
                  
                  }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        PSeleccionar_Articulos seleccionar = new PSeleccionar_Articulos();
        JDialog dialogo = new JDialog((JFrame) Window.getWindows()[0], "Artículos Nuevos", true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(600, 400);
        dialogo.setLocationRelativeTo((JFrame) Window.getWindows()[0]);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        VMostrarArticulosEnOferta seleccionar = new VMostrarArticulosEnOferta((JFrame) Window.getWindows()[0], true);
        seleccionar.setLocationRelativeTo((JFrame) Window.getWindows()[0]);
        seleccionar.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
    hClientes hclientes;
    hArticulos harticulos;
    hArticulolinea harticulolinea;
    hVentas hventas;
    hPartidas hpartidas;
    hAlmacendevoluciones halmacendevoluciones;
    hKardex hkardex;
    DefaultTableModel dtm,dtmPartidas;
    Vector vector,vectorArticulos,vectorPartidas;
    List<Clientes> listaClientes;
    List<Articulos> listaArticulos;
    List<Partidas>  listaPartidas;
    Vector<String> encabezadoArticulos,encabezadoPartidas;
    int numArticulos,numPartidas=0;
    double numSubtotal,numTotal=0.0;
    long idAdministrador=-1;
    List<Cantidades> cantArticulos; //mide sobrantes
    List<String> idCompletosUnicos,codigosCompletosUnicos;
    String vendedor="";
    long numeroVenta=0;
    String fechaDeVenta="";
    String formaVenta = "";
    Double iva = 0.0;
    DecimalFormat df;
    String nombreAdministrador="Sin Administrador";
    String tituloP = "A ALMACÉN";
    
    public class PSeleccionar_Articulos extends JPanel   {

    JPanel panelPrincipal, panelEncabezado, panelTabla;
    JTable tablaArticulos;
    hArticulos hartic;
    Vector vector,encabezadoArticulos;
    DefaultTableModel dtm;
   List<Articulos> listaArtic;

    public PSeleccionar_Articulos(){
     tablaArticulos = new JTable();
     encabezadoArticulos = new Vector<String>();
     encabezadoArticulos.add("Código");
     encabezadoArticulos.add("Descripción");
     encabezadoArticulos.add("Existencia");
     

     hartic = new hArticulos();
     listaArtic = hartic.consultaArticulos("nuevo", "=", "1");
     vector = new Vector();

       for(Object o: listaArtic){
             Articulos prov = (Articulos)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(prov.getCodigo());
             unaFila.add(prov.getDescripcion());
             unaFila.add(prov.getExistencia());
             vector.add(unaFila);
         }

      dtm = new DefaultTableModel(vector,encabezadoArticulos) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };

      tablaArticulos.setModel(dtm);
      tablaArticulos.addMouseListener(new MouseListener()
      {

                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() == 2)
                                    {

                                       int indice = tablaArticulos.getSelectedRow();
                                       jTextField11.setText(listaArtic.get(indice).getCodigo());
                                       jButton13.doClick();
                                  //     list1.getSize();
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

      

      add(new JScrollPane(tablaArticulos));
      setLayout(new GridLayout());
    }

}
    
}
