/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.identidades.Articulos;
import com.paedeias.identidades.Ventas;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALL
 */
public class ResumenHistorialClientes extends javax.swing.JDialog {

    /**
     * Creates new form ResumenHistorialClientes
     */
    public ResumenHistorialClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResumenHistorialClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResumenHistorialClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResumenHistorialClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResumenHistorialClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ResumenHistorialClientes dialog = new ResumenHistorialClientes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    Vector vector,vectorEncabezado;
    DecimalFormat df;
    DefaultTableModel dtm;
    String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    double importe = 0.0;
    int cantidad = 0;
    String mes = "00";
    String anio = "0000";
    int indice=0;
    
    List<Ventas> ventas;
    
    public void inicializar() {
        df = new DecimalFormat("0.00");
        vector = new Vector();
        vectorEncabezado = new Vector<String>();
        
        vectorEncabezado.add("A�o");
        vectorEncabezado.add("Mes");
        vectorEncabezado.add("Cantidad Comprada");
        vectorEncabezado.add("Importe");
       
         dtm = new DefaultTableModel(vector,vectorEncabezado){

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
        
         String[] arreglo;
         
          boolean bandera = false;
          
          int contador=0;
          for(Object o: ventas){
             contador++;
             Ventas venta = (Ventas)o;
             
             
             arreglo = venta.getFechaVenta().split("-");
             
             
             if(bandera)
             {
             if(mes.equals(arreglo[1]))
              {
               importe = importe + venta.getTotal();
               cantidad = cantidad + venta.getArticulos();
              }else
              {
               Vector<Object> unaFila = new Vector<Object>();
               cambiarMes(mes);
               unaFila.add(anio);
               unaFila.add(meses[indice]);   
               unaFila.add(cantidad);
               unaFila.add(df.format(importe));
               vector.add(unaFila);
               
               importe = venta.getTotal();
               cantidad = venta.getArticulos();
               mes = arreglo[1];
               anio = arreglo[0];
              }
             }else
             {
             mes = arreglo[1];
             anio = arreglo[0];
             importe = venta.getTotal();
             cantidad = venta.getArticulos();
             bandera = true;
             }
             
             if(contador==ventas.size())
             {
               Vector<Object> unaFila = new Vector<Object>();
               cambiarMes(mes);
               unaFila.add(anio);
               unaFila.add(meses[indice]);   
               unaFila.add(cantidad);
               unaFila.add(df.format(importe));
               vector.add(unaFila);  
             }
             
             
         } 
        
        jTable1.setModel(dtm);
        
        
    
    }

    public List<Ventas> getVentas() {
        return ventas;
    }

    public void setVentas(List<Ventas> ventas) {
        this.ventas = ventas;
    }
    
    public void cambiarMes(String mes)
    {
         if(mes.equals("01"))
                       indice = 0;
                   else if(mes.equals("02"))
                       indice = 1;
                   else if(mes.equals("03"))
                       indice = 2;
                   else if(mes.equals("04"))
                       indice = 3;
                   else if(mes.equals("05"))
                       indice = 4;
                   else if(mes.equals("06"))
                       indice = 5;
                   else if(mes.equals("07"))
                       indice = 6;
                   else if(mes.equals("08"))
                       indice = 7;
                   else if(mes.equals("09"))
                       indice = 8;
                   else if(mes.equals("10"))
                       indice = 9;
                   else if(mes.equals("11"))
                       indice = 10;
                   else if(mes.equals("12"))
                       indice = 11;
    }
    
    
}
