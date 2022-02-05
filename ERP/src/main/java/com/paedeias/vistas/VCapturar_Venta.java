/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;


import com.paedeias.controladores.Autenticar;
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.controladores.facturacion.DOMUtils;
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
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.bouncycastle.util.encoders.Hex;
import org.w3c.dom.Document;

/**
 *
 * @author ALL
 */
public class VCapturar_Venta extends javax.swing.JPanel {

    /**
     * Creates new form FRealizar_Venta
     */
    public VCapturar_Venta() {
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
        myfilelister = new VCapturar_Venta.MyFileListener(this);
       jButton19.addActionListener(myfilelister);
        
        //se inicializan las listas
        listaClientes = hclientes.consultaClientes("nombre", "LIKE", "Público en General");
        listaArticulos = new ArrayList<Articulos>();
        listaPartidas = new ArrayList<Partidas>();
        cantArticulos = new ArrayList<Cantidades>();
        idCompletosUnicos = new ArrayList<String>();
        codigosCompletosUnicos = new ArrayList<String>();
        
         jTable4.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                        if(jTable4.getSelectedRow() ==-1)
                            return;
                        
                        jTextField3.setText(String.valueOf(cantidad[jTable4.getSelectedRow()]));
                            
    
            }
             
         });
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

        
        //se selecciona el radio button de nada de beneficios
        jRadioButton7.setSelected(true);
        
        //action performed para deshabilitar los demás botónes y habilitar componentes
        
        jRadioButton1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                    jComboBox4.setEnabled(true);
                    jComboBox2.setEnabled(false);           
            }
        
        });
        
        jRadioButton2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(true);     

            }
        
        });
        
                    

        
         jRadioButton7.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                    jComboBox4.setEnabled(false);
                    jComboBox2.setEnabled(false);         

            }
        
        });

        
        

     
           

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
           
           encabezadoPartidasPedido = new Vector<String>();
           encabezadoPartidasPedido.add("Válido");
           encabezadoPartidasPedido.add("Código");
           encabezadoPartidasPedido.add("Descripción Ped.");
           encabezadoPartidasPedido.add("Descripción Sis.");
           encabezadoPartidasPedido.add("Precio de Venta");
           encabezadoPartidasPedido.add("Solicitados");
           encabezadoPartidasPedido.add("Almacén General");
           encabezadoPartidasPedido.add("Devoluciones");
           
           vectorPartidasPedido = new Vector<String>();

           dtmPartidasPedido = new DefaultTableModel(vectorPartidasPedido,encabezadoPartidasPedido) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              
             @Override
             public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 0) {
              return Boolean.class;
               }
               return super.getColumnClass(columnIndex);
                }   
              
              };

           jTable4.setModel(dtmPartidasPedido);
           
           //se habilitan o deshabilitan los radiobutton dependiendo de la configuración global
           if(!CGlobalConfig.isDescuentosClientes())
           jRadioButton2.setEnabled(false);
           if(!CGlobalConfig.isUtilidadesClientes())
           jRadioButton1.setEnabled(false);    
         
           
           
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
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton15 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecciona un cliente"));

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(11, 11, 11)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton2)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox1)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Partidas"));

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
        jButton15.setText("Pedido Almacén");
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(jButton6))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        jLabel9.setText("Ruta del Archivo:");

        jTextField2.setEditable(false);

        jButton19.setBackground(new java.awt.Color(11, 70, 119));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Seleccionar");
        jButton19.setContentAreaFilled(false);
        jButton19.setOpaque(true);

        jButton20.setBackground(new java.awt.Color(11, 70, 119));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Descargar Venta");
        jButton20.setContentAreaFilled(false);
        jButton20.setEnabled(false);
        jButton20.setOpaque(true);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(11, 70, 119));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Agregar todas las partidas");
        jButton21.setContentAreaFilled(false);
        jButton21.setOpaque(true);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Cantidad a Eliminar:");

        jTextField3.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(jButton20))
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton12)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
          if(jTable4.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
        }
        
        
        if(jTable4.getSelectedRow() != -1 && !listaArticulos.isEmpty())
        {
        
            
            
            
            
            
            
        int fila_seleccionada = jTable4.getSelectedRow();
        
        int cantidades = Integer.valueOf(cantidad[fila_seleccionada]);
        
        if(Integer.valueOf(jTextField3.getText()) < cantidades)
        {
            
         cantidad[fila_seleccionada] = String.valueOf(cantidades - Integer.valueOf(jTextField3.getText()));   
        dtmPartidasPedido.setValueAt(cantidad[fila_seleccionada],fila_seleccionada,5);
        jTable4.setModel(dtmPartidasPedido);
        jTable4.getSelectionModel().setSelectionInterval(0, 0); 
         
        }
        else if(Integer.valueOf(jTextField3.getText()) == cantidades)
        {
   //     numArticulos = numArticulos - cantidad;
   //     numPartidas--;
   //     numSubtotal = numSubtotal - listaPartidas.get(fila_seleccionada).getPrecioVenta() * cantidad;
   //     numTotal = numTotal - listaPartidas.get(fila_seleccionada).getSubtotal();
        
   //     jLabel14.setText(String.valueOf(numArticulos));
   //     jLabel16.setText(String.valueOf(numPartidas));
   //     jLabel18.setText(df.format(numSubtotal));
   //     jLabel20.setText(df.format(numTotal));
        
        listaArticulos.remove(fila_seleccionada);
          List result = new ArrayList<String>();
          for(int i=0; i<cantidad.length; i++)
          {
              result.add(cantidad[i]);
          }
          result.remove(fila_seleccionada);
          cantidad = Arrays.copyOf(result.toArray(), result.toArray().length, String[].class);

        //cantArticulos.remove(fila_seleccionada);

        dtmPartidasPedido.removeRow(fila_seleccionada);
        jTable4.setModel(dtmPartidasPedido);
        jTable4.getSelectionModel().setSelectionInterval(0, 0); 
        
        
                   //si es la primera partida se deshabilitan los botones del cliente
       if(numPartidas==0)
           {
           jButton1.setEnabled(true);
           jComboBox5.setEnabled(true);
          
          if(jRadioButton1.isSelected() || jRadioButton2.isSelected())
          {
          jRadioButton7.setEnabled(true);
          }
          
          jRadioButton1.setEnabled(true);
          jRadioButton2.setEnabled(true);
        //  jRadioButton7.setEnabled(false);
                  
                  
           jComboBox4.setEnabled(true);
           jComboBox2.setEnabled(true);

           }
       }
        }else
            JOptionPane.showMessageDialog(null, "No hay partidas que puedan eliminarse");
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
        
        listaClientes = hclientes.consultaClientes("nombre", "LIKE", "Público en General");
        
        listaPartidas.clear();
        cantArticulos.clear();
        idCompletosUnicos.clear();
        codigosCompletosUnicos.clear();
          
        //limpiar componentes
        jComboBox5.removeAllItems();  
        jComboBox4.removeAllItems();
        jComboBox2.removeAllItems();
        jTextField1.setText("");
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


        
        //pone el radio button en false
        jRadioButton7.setSelected(true);
        
        //limpiavector
        vectorPartidasPedido.clear();
        

           
          
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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
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
            
            long numVenta=hventas.guardarVentas(venta); //se guarda la venta para obtener el número de venta
            
            if(tipoVenta[vconfirmaventa.getIndice()].equals("Credito")&&!listaClientes.get(jComboBox5.getSelectedIndex()).getNombre().equalsIgnoreCase("Público en general"))
            {
                
                hCuentasPorCobrar hcpc = new hCuentasPorCobrar();
                Cuentasporcobrar cpc = new Cuentasporcobrar();
                cpc.setCliente(listaClientes.get(jComboBox5.getSelectedIndex()).getId());
                cpc.setFactura((long)-1);
                cpc.setFechaVencimiento(fecha);  
                cpc.setObservacion("Venta Mostrador con número "+numVenta);
                cpc.setPagado(0);
                cpc.setSaldo(numTotal);
                cpc.setTipo("VC"+numVenta);
                cpc.setVenta(String.valueOf(numVenta));           
                hcpc.guardarCtaPorCobrar(cpc);
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
                        
                        hkardex.guardarEnKardex(kardex); 
                     
                     
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
                 nuevoRenglon.setModificacion("");
                 nuevoRenglon.setMovimiento("Venta número"+listaPartidas.get(i).getIdVenta());
                 nuevoRenglon.setPrecioVenta(listaPartidas.get(i).getPrecioVenta());
                 nuevoRenglon.setRefFerrari(String.valueOf("v"+listaPartidas.get(i).getIdVenta()));
                 nuevoRenglon.setResponsable(String.valueOf(venta.getIdusuario()));
                 nuevoRenglon.setUltimoCosto(listaPartidas.get(i).getPrecioCompra());
                 nuevoRenglon.setVendidoEn(listaPartidas.get(i).getConBeneficio());
                 nuevoRenglon.setResponsable2(nombreAdministrador);    
     
                 
                 hkardex.guardarEnKardex(nuevoRenglon);
                 
                 
                        }
                
                hpartidas.guardarPartidas(listaPartidas.get(i)); // se guarda la partida en el ciclo externo
                
                
                int cantidadADescontar = cantArticulos.get(i).getExistenciaCatalogo()-acumulado;
                //  System.out.println("Existencia "+cantArticulos.get(i).getExistenciaCatalogo() +"Acumulado "+acumulado);    
                 
                 if(listaPartidas.get(i).getUbicacion().equals("General"))
                 {
                 cantidadADescontar = (int)larticulos.get(0).getExistencia() - acumulado;    
                 harticulos.actualizarExistencias(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar);
                 }else if(listaPartidas.get(i).getUbicacion().equals("Devoluciones"))
                 {
              //    System.out.println("Id del Artículo "+listaPartidas.get(i).getIdArticulo());    
                 halmacendevoluciones.actualizarExistencias(listaPartidas.get(i).getIdArticulo(), cantidadADescontar);    
                 harticulos.actualizarExistenciasDev(listaPartidas.get(i).getCodigoArticulo(), cantidadADescontar);
                 }
                
                
                
                
                        i++;
                 }
            
               CPrincipal.getConexion().finalizarTransaccion();
                
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
                        Logger.getLogger(VCapturar_Venta.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(VCapturar_Venta.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (PrinterException ex) {
                Logger.getLogger(ImpresionVenta.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            
            /*-----------------------------------------------------*/
            
            JOptionPane.showMessageDialog(null, "Venta Realizada!");       //se para dejar de recorrer el ciclo interno indicando que ya se encontró el artículo y pasando a la siguiente partida
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
                Logger.getLogger(VCapturar_Venta.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            

           
           //se pone en el textfield el valor del precio de venta del primer artículo
           


        
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

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
            
            // TODO add your handling code here:
             if(jTextField2.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione primero un archivo");
                return;
            }
           
           listaArticulos.clear();
           vectorPartidasPedido.clear();
                       
           Document pedido = DOMUtils.cargarArchivoConfiguracion(new File(jTextField2.getText()));
           List<Articulos> listaArticulos2 = new ArrayList<Articulos>();
           
                String cadenaOriginal = ""; //pedido.getElementsByTagName("cantidad").item(0).getTextContent()+pedido.getElementsByTagName("codigo").item(0).getTextContent()+pedido.getElementsByTagName("descripcion").item(0).getTextContent();

           
                    validado = new boolean[pedido.getElementsByTagName("cantidad").getLength()];
                
                    cantidad = new String[pedido.getElementsByTagName("cantidad").getLength()];
                    for(int i=0; i<cantidad.length; i++)
                    {
                        cantidad[i] = String.valueOf(pedido.getElementsByTagName("cantidad").item(i).getTextContent());
                    }
                    
                    String[] codigo = new String[pedido.getElementsByTagName("codigo").getLength()];
                    for(int i=0; i<codigo.length; i++)
                    {
                        codigo[i] = String.valueOf(pedido.getElementsByTagName("codigo").item(i).getTextContent());
                    }
                    
                    String[] descripcion = new String[pedido.getElementsByTagName("descripcion").getLength()];
                    for(int i=0; i<descripcion.length; i++)
                    {
                        descripcion[i] = String.valueOf(pedido.getElementsByTagName("descripcion").item(i).getTextContent());
                    }
                    
              for(int avalidar=0; avalidar < cantidad.length; avalidar++)
              {
                  cadenaOriginal = cadenaOriginal + cantidad[avalidar] + codigo[avalidar]+ descripcion[avalidar];
              }
                    
                    
             MessageDigest cript = MessageDigest.getInstance("SHA-1");
                cript.reset();
                cript.update(cadenaOriginal.getBytes("utf8"));
                String sha1 = new String(Hex.encode(cript.digest()), 
                Charset.forName("UTF-8")); 
                
             if(!sha1.equals(pedido.getElementsByTagName("sha1").item(0).getTextContent()))
            {
             JOptionPane.showMessageDialog(null, "El contenido de este archivo no coincide con la información original que se encontraba en él", "Contenido inválido", JOptionPane.ERROR_MESSAGE);   
             return;   
            }
                    
                    for(int i=0; i<codigo.length; i++)
                    {
                    listaArticulos2 = harticulos.consultaArticulos("codigo", "=", codigo[i]); 
                    Vector<Object> unaFila = new Vector<Object>();
                    if(!listaArticulos2.isEmpty())
                     {
                     if(descripcion[i].equals(listaArticulos2.get(0).getDescripcion())
                        &&Integer.valueOf(cantidad[i])<=(int)listaArticulos2.get(0).getExistencia())
                     {
                     unaFila.add(true);
                     validado[i] = true;
                     }
                     else
                     {  
                     unaFila.add(false);
                     validado[i] = false;
                     }
                     unaFila.add(listaArticulos2.get(0).getCodigo());
                     unaFila.add(descripcion[i]);
                     unaFila.add(listaArticulos2.get(0).getDescripcion());
                     unaFila.add(listaArticulos2.get(0).getPrecioVenta());
                     unaFila.add(cantidad[i]);
                     unaFila.add(listaArticulos2.get(0).getExistencia());
                     unaFila.add(listaArticulos2.get(0).getExistenciaAlmacen());   
                    
                     
                     Articulos articulo = new Articulos();
                     articulo.setABC(listaArticulos2.get(0).getABC());
                     articulo.setAlmacenDevoluciones(listaArticulos2.get(0).getAlmacenDevoluciones());
                     articulo.setAnticipos(listaArticulos2.get(0).getAnticipos());
                     articulo.setBloqueado(listaArticulos2.get(0).getBloqueado());
                     articulo.setClasificacion(listaArticulos2.get(0).getClasificacion());
                     articulo.setCodigo(listaArticulos2.get(0).getCodigo());
                     articulo.setCodigo2(listaArticulos2.get(0).getDescripcion());
                     articulo.setDescripcion(listaArticulos2.get(0).getDescripcion());
                     articulo.setExistencia(listaArticulos2.get(0).getExistencia());
                     articulo.setExistenciaAlmacen(listaArticulos2.get(0).getExistenciaAlmacen());
                     articulo.setId(listaArticulos2.get(0).getId());
                     articulo.setIdCompleto(listaArticulos2.get(0).getIdCompleto());
                     articulo.setIeps(listaArticulos2.get(0).getIeps());
                     articulo.setIva(listaArticulos2.get(0).getIva());
                     articulo.setLineaPrincipal(listaArticulos2.get(0).getLineaPrincipal());
                     articulo.setMaximoPzas(listaArticulos2.get(0).getMaximoPzas());
                     articulo.setMinimoPzas(listaArticulos2.get(0).getMinimoPzas());
                     articulo.setNuevo(listaArticulos2.get(0).getNuevo());
                     articulo.setOferta(listaArticulos2.get(0).getOferta());
                     articulo.setParetto(listaArticulos2.get(0).getParetto());
                     articulo.setPedidosPrecio1(listaArticulos2.get(0).getPedidosPrecio1());
                     articulo.setPedidosPrecio2(listaArticulos2.get(0).getPedidosPrecio2());
                     articulo.setPrecioCompra(listaArticulos2.get(0).getPrecioCompra());
                     articulo.setPrecioVenta(listaArticulos2.get(0).getPrecioVenta());
                     articulo.setPrecioVenta2(listaArticulos2.get(0).getPrecioVenta2());
                     articulo.setPromPzas(listaArticulos2.get(0).getPromPzas());
                     articulo.setProveedor(listaArticulos2.get(0).getProveedor());
                     articulo.setReportePedidos(listaArticulos2.get(0).getReportePedidos());
                     articulo.setReservado(listaArticulos2.get(0).getReservado());
                     articulo.setSinonimoPrincipal(listaArticulos2.get(0).getSinonimoPrincipal());
                     articulo.setTipoEtiqueta(listaArticulos2.get(0).getTipoEtiqueta());
                     articulo.setUbicacion(listaArticulos2.get(0).getUbicacion());
                     articulo.setUltimoCosto(listaArticulos2.get(0).getUltimoCosto());
                     articulo.setUnidad(listaArticulos2.get(0).getUnidad());
                     
                     listaArticulos.add(articulo);
                     vectorPartidasPedido.add(unaFila);    
                     }else
                    {
                     unaFila.add(false);
                     validado[i] = false;
                     unaFila.add(codigo[i]);
                     unaFila.add(descripcion[i]);
                     unaFila.add("");
                     unaFila.add("");
                     unaFila.add(cantidad[i]);
                     unaFila.add("");
                     unaFila.add("");                     
                     vectorPartidasPedido.add(unaFila);    
                    }
                    }
                    if(!listaArticulos.isEmpty())
                    {
                    jTable4.getSelectionModel().setSelectionInterval(0, 0); 
                    jTextField3.setText(String.valueOf(listaArticulos.get(0).getExistencia()));
                    }
                    dtmPartidasPedido.fireTableDataChanged();
                    jButton19.setEnabled(false);
                    jButton20.setEnabled(false);    
                
        } catch (Exception ex) {
            Logger.getLogger(VCapturar_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
           boolean banderavalida=false;
           Double mejorPrecio=0.0;
           Double precio = 0.0;
           Double mejorDescuento=0.0;
           String tipoDescuento = "";
           Double subtotal = 0.0;
 
 for(int i=0; i<validado.length; i++)
 {
 System.out.println(validado[i]);    
 if(validado[i]==false)       
 {
 banderavalida = true;
 i = validado.length;
 }
 }
 
 if(banderavalida)
     return;
        
 for(int a=0; a<listaArticulos.size(); a++)
 {
if(listaArticulos.get(a).getBloqueado() == 1)
        {   
        JOptionPane.showMessageDialog(null, "Este artículo se encuentra bloqueado y no puede ser usado en esta venta");
        return;
        }
		
if(jCheckBox1.isSelected())
        {
            JOptionPane.showMessageDialog(null, "El cliente tiene cuentas vencidas, por favor deshabilite esta opción para permitir vender a este cliente");
            return;
        }
		
if(Integer.compare(Integer.valueOf(jComboBox4.getSelectedItem().toString()), 0)== 0 && jRadioButton1.isSelected() && jRadioButton1.isEnabled())
        {
        int eleccion = JOptionPane.showConfirmDialog(null, "Ha seleccionado vender con utilidad 0 al cliente, ¿Está seguro de realizar esta venta?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(eleccion != JOptionPane.YES_OPTION)        
        return;
        }		
		
if(listaArticulos.get(a).getExistencia() == 0)
        {
        JOptionPane.showMessageDialog(null, "No se pueden agregar 0 artículos a esta venta");    
        return;
        }


   hKardex hkar = new hKardex();
        Kardex parkar = hkar.consultaUltimoRenglon("articulo", "=", listaArticulos.get(a).getCodigo());
        
        if(parkar == null)
        {
            JOptionPane.showMessageDialog(null, "Este artículo no se encuentra registrado en Kardex");
            return;
        }
    
        
      if(jRadioButton2.isSelected()){
               mejorDescuento = (Double.valueOf(jComboBox2.getSelectedItem().toString())/100)*listaArticulos.get(a).getPrecioVenta();
               precio = listaArticulos.get(a).getPrecioVenta();
               mejorPrecio = precio - mejorDescuento;
               
               double compraIva =  (listaArticulos.get(a).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(a).getPrecioCompra();
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
            precio = listaArticulos.get(a).getPrecioCompra();   
            mejorDescuento = (Double.valueOf(jComboBox4.getSelectedItem().toString())/100)*listaArticulos.get(a).getPrecioCompra();    
            mejorPrecio = precio + mejorDescuento;
            mejorDescuento = (Double.valueOf(listaArticulos.get(a).getIva())/100)*mejorPrecio;
            mejorPrecio = mejorPrecio + mejorDescuento;
            String redondeo = df.format(mejorPrecio);  //11.76
            mejorPrecio = Double.valueOf(redondeo);
           
           double validadorCompra =  (listaArticulos.get(a).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(a).getPrecioCompra();
               
              if(mejorPrecio < validadorCompra)
             // if(mejorPrecio < validadorCompra)
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
            
            
               tipoDescuento = "Utilidad Cliente";          
            } 
           else if(jRadioButton7.isSelected()) //en caso de que el boton de no beneficios este seleccionado, se aplican los precios directos.
            {
            mejorDescuento = 0.0;
            precio = listaArticulos.get(a).getPrecioVenta();
            mejorPrecio = precio;
            tipoDescuento = "Sin Beneficio";
            
           double compraIva =  (listaArticulos.get(a).getPrecioCompra() * CGlobalConfig.getIvaVenta())+listaArticulos.get(a).getPrecioCompra();
           double validadorCompra = (compraIva * CGlobalConfig.getUtilidad()) + compraIva;
               
              if(mejorPrecio < validadorCompra && !jRadioButton1.isSelected())
              {
                  JOptionPane.showMessageDialog(null, "El beneficio de este artículo sin iva es menor al precio de compra");
                  return;
              }
            
            
            }   
        
           subtotal = mejorPrecio*Integer.valueOf(cantidad[a]);
           
                     
            if(!listaPartidas.isEmpty())
        {
            for(int i=0; i<listaPartidas.size(); i++)
            {
                if(listaArticulos.get(a).getCodigo().equals(listaPartidas.get(i).getCodigoArticulo()) &&
                   Double.compare(listaArticulos.get(a).getPrecioCompra(),listaPartidas.get(i).getPrecioCompra()) == 0 &&
                   Double.compare(mejorPrecio,listaPartidas.get(i).getConBeneficio())==0)     
                                        {
                    JOptionPane.showMessageDialog(null, "No se puede agregar el mismo artículo con el mismo precio y el mismo costo");
                    return;
                }
            }
        }
        
            Cantidades cantidadarticulo = new Cantidades();
            cantidadarticulo.setId(listaArticulos.get(a).getId()); //un id que puede repetirse y que no sirve de mucho a la hora de las partidas
            cantidadarticulo.setIdCompleto(listaArticulos.get(a).getIdCompleto()); //el id ÚNICO de cada artículo
            cantidadarticulo.setCantidadPartida(Integer.parseInt(cantidad[a])); //la cantidad solicitada
            cantidadarticulo.setExistenciaCatalogo((int)listaArticulos.get(a).getExistencia()); //la cantidad que hay en existencia en lista
            cantidadarticulo.setCodigo(listaArticulos.get(a).getCodigo());  //el código del arttículo
            cantidadarticulo.setUbicacion(listaArticulos.get(a).getUbicacion());  //esta cosa para saber si el artículo es de devolución o de almacén general
            cantidadarticulo.setPrecioCompra(listaArticulos.get(a).getPrecioCompra());
            cantidadarticulo.setPrecioVenta(mejorPrecio);
            cantidadarticulo.setReservados((int)listaArticulos.get(a).getReservado());
            cantidadarticulo.setAnticipos((int)listaArticulos.get(a).getAnticipos());
             cantArticulos.add(cantidadarticulo); //se agrega el artículo con código único a la lista. (acá está mal pq se mezcla lo de almacén con lo de devoluciones y se compara
                                          //contra artículos no cotnra kardex.)
            
             // se crea la partida con las cantidades calculadas           
             Partidas partida = new Partidas();
             partida.setBeneficio(mejorDescuento);
             partida.setCantidad(Integer.parseInt(cantidad[a]));
             partida.setConBeneficio(mejorPrecio);
             partida.setDescripcionArticulo(listaArticulos.get(a).getDescripcion());
             partida.setCodigoArticulo(listaArticulos.get(a).getCodigo());
             partida.setIdArticulo(listaArticulos.get(a).getId());
             partida.setIdCompleto(listaArticulos.get(a).getIdCompleto());
             partida.setPrecioVenta(listaArticulos.get(a).getPrecioVenta());
             partida.setSubtotal(subtotal);
             partida.setTipoBeneficio(tipoDescuento);
             partida.setPrecioCompra(listaArticulos.get(a).getPrecioCompra());
             partida.setUbicacion(listaArticulos.get(a).getUbicacion()); 
            
              //se agrega la partida
            listaPartidas.add(partida);

           //se ponen  calculan las partidas generales de la venta
           numArticulos = numArticulos+Integer.parseInt(cantidad[a]);
           numPartidas++;
           numSubtotal = numSubtotal+precio*Double.parseDouble(cantidad[a]);
           numTotal = numTotal+subtotal;
           
          /*QUIZÁS SERIA BUENO ACTUALIZAR LAS CANTIDADES DEL OBJETO VENTA AQUÍ YA QUE SE ESTAN CALCULANDO*/           
           
           // se actualiza en las etiquetas las cantidades
           jLabel14.setText(String.valueOf(numArticulos));
           jLabel16.setText(String.valueOf(numPartidas));
           jLabel18.setText(df.format(numSubtotal));
           jLabel20.setText(df.format(numTotal));
             
          
     //se agrega la partida a la tabla con las cantidades calculadas
            Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(listaArticulos.get(a).getCodigo());
             unaFila.add(listaArticulos.get(a).getDescripcion());
             unaFila.add(df.format(listaArticulos.get(a).getPrecioVenta()));
             unaFila.add(df.format(mejorPrecio));
             unaFila.add(cantidad[a]);
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
          
  
          
          jRadioButton1.setEnabled(false);
          jRadioButton2.setEnabled(false);
        //  jRadioButton7.setEnabled(false);
                  
                  
           jComboBox4.setEnabled(false);
           jComboBox2.setEnabled(false);

           }       
           
        
    mejorPrecio=0.0;
    precio = 0.0;
    mejorDescuento=0.0;
    tipoDescuento = "";
    subtotal = 0.0;    

 }	


        
        
    }//GEN-LAST:event_jButton21ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    hClientes hclientes;
    hArticulos harticulos;
    hArticulolinea harticulolinea;
    hVentas hventas;
    hPartidas hpartidas;
    hAlmacendevoluciones halmacendevoluciones;
    hKardex hkardex;
    DefaultTableModel dtm,dtmPartidas,dtmPartidasPedido;
    Vector vector,vectorArticulos,vectorPartidas,vectorPartidasPedido;
    List<Clientes> listaClientes;
    List<Articulos> listaArticulos;
    List<Partidas>  listaPartidas;
    Vector<String> encabezadoArticulos,encabezadoPartidas,encabezadoPartidasPedido;
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
    MyFileListener myfilelister;
    boolean[] validado;
    String[] cantidad;
    


   

    class MyFileListener implements ActionListener{
private JFileChooser chooser = new JFileChooser();
private JFrame frame;
public MyFileListener(JFrame frame){
this.frame = frame;
}

        private MyFileListener(VCapturar_Venta aThis) {
        }


    @Override
public void actionPerformed(ActionEvent ae) {
String textButton = ae.getActionCommand();
String dialogTitle = "Abrir un fichero";

if (textButton.equals("Guardar"))
dialogTitle = "Guardar un fichero";

chooser.setDialogTitle(dialogTitle);
chooser.setMultiSelectionEnabled(false);
chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

int sel = chooser.showOpenDialog(frame);
if (sel == JFileChooser.APPROVE_OPTION){
File selectedFile = chooser.getSelectedFile();

jTextField2.setText(selectedFile.getAbsolutePath());
jButton20.setEnabled(true);
}else{
return;
}
}
    }
    
    }

