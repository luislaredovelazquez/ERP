/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.controladores.*;
import com.paedeias.controladores.facturacion.CFactura;
import com.paedeias.controladores.facturacion.ReporteFactura;
import com.paedeias.controladores.facturacion.ReporteFacturaCFDI;
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ALL
 */
public class VDetalle_Factura extends javax.swing.JPanel {

    /**
     * Creates new form VDetalle_Factura
     */
    
    
    public VDetalle_Factura(Factura factura, String tipo) {
        initComponents();
        this.factura = factura;
        this.tipo = tipo;
        inicializar();
    }
    
     public VDetalle_Factura(Factura factura, String tipo, String reservacion) {
        initComponents();
        this.factura = factura;
        this.tipo = tipo;
        this.reservacion = reservacion;
        inicializar();
    }

     
    private void inicializar()
    {
           df = new DecimalFormat("0.00");
           hfacturas = new hFacturas();
           
            java.util.Date utilDate = new java.util.Date();
            long milisec = utilDate.getTime();
            Timestamp time= new Timestamp(milisec);    
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String fecha = sdf.format(time);  
         
         hcliente = new hClientes();   
         cliente = new Clientes();    
         cliente = hcliente.consultaUltimoRenglon("id", "=", String.valueOf(factura.getIdReceptorF()));
         
         hpartidas = new hPartidas();
         hpvanticipos = new hPartidaVentaAnticipos();
         pvanticipos = new ArrayList<Partidasventaanticipos>();
         partidas = new ArrayList<Partidas>();
         iniciopartidas = new ArrayList<Partidas>();
         double subtotal = 0.0;
         double iva = 0.0;
         double total = 0.0;
         jTextField48.setText(factura.getTotal());
         
         if(tipo.equals("venta")||tipo.equals("reservacion"))
         {
         partidas = hpartidas.consultaPartidas("idVenta", "=", String.valueOf(factura.getIdConceptoFactura().substring(2)));
         if(cliente.getNombre().equalsIgnoreCase("P�blico en general"))
        {
            iva = 0;
            subtotal = Double.valueOf(jTextField48.getText());
            total = Double.valueOf(jTextField48.getText());
            
            int it3 = 0;
            while(it3 < partidas.size())
            {
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it3).getBeneficio());
                partida.setCantidad(partidas.get(it3).getCantidad());
                partida.setCodigoArticulo(partidas.get(it3).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it3).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it3).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it3).getIdArticulo());
                partida.setIdCompleto(partidas.get(it3).getIdCompleto());
                partida.setIdVenta(partidas.get(it3).getIdVenta());
                partida.setPrecioCompra(partidas.get(it3).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it3).getPrecioVenta());
                partida.setSubtotal(partidas.get(it3).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it3).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it3).getUbicacion());
                iniciopartidas.add(partida);
                it3++;
            }
                        
        }
        //caso cliente con RFC
        else
         {
            int it1 = 0;
            while(it1 < partidas.size())
            {
                double partidatotal = partidas.get(it1).getConBeneficio() / (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it1).getCantidad();
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it1).getBeneficio());
                partida.setCantidad(partidas.get(it1).getCantidad());
                partida.setCodigoArticulo(partidas.get(it1).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it1).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it1).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it1).getIdArticulo());
                partida.setIdCompleto(partidas.get(it1).getIdCompleto());
                partida.setIdVenta(partidas.get(it1).getIdVenta());
                partida.setPrecioCompra(partidas.get(it1).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it1).getPrecioVenta());
                partida.setSubtotal(partidas.get(it1).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it1).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it1).getUbicacion());
                iniciopartidas.add(partida);
                partidas.get(it1).setConBeneficio(partidatotal);
                partidas.get(it1).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;

                it1++;
            }

            
            iva =   subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;    
         
          }
         }
         else
         {
         pvanticipos = hpvanticipos.consultaPartidas("idVenta", "=", String.valueOf(factura.getIdConceptoFactura().substring(2)));   
         
         int it1=0;
         while(it1<pvanticipos.size())
         {
             Partidas partida = new Partidas();
             partida.setBeneficio(pvanticipos.get(it1).getBeneficio());
             partida.setCantidad(pvanticipos.get(it1).getCantidad());
             partida.setCodigoArticulo(pvanticipos.get(it1).getCodigoArticulo());
             partida.setConBeneficio(pvanticipos.get(it1).getConBeneficio());
             partida.setDescripcionArticulo(pvanticipos.get(it1).getDescripcionArticulo());
             partida.setId(pvanticipos.get(it1).getId());
             partida.setIdArticulo(pvanticipos.get(it1).getIdArticulo());
             partida.setIdCompleto(pvanticipos.get(it1).getIdCompleto());
             partida.setIdVenta(pvanticipos.get(it1).getIdVenta());
             partida.setPrecioCompra(pvanticipos.get(it1).getPrecioCompra());
             partida.setPrecioVenta(pvanticipos.get(it1).getPrecioVenta());
             partida.setSubtotal(pvanticipos.get(it1).getSubtotal());
             partida.setTipoBeneficio(pvanticipos.get(it1).getTipoBeneficio());
             partida.setUbicacion(pvanticipos.get(it1).getUbicacion());
             partidas.add(partida);
             
             it1++;
         }
         
        if(tipo.equals("aUsado"))
        //caso p�blico en general
        {
        if(cliente.getNombre().equalsIgnoreCase("P�blico en general"))
        {            
            int it3 = 0;
            while(it3 < partidas.size())
            {
                double partidatotal = partidas.get(it3).getConBeneficio() * (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it3).getCantidad();
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it3).getBeneficio());
                partida.setCantidad(partidas.get(it3).getCantidad());
                partida.setCodigoArticulo(partidas.get(it3).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it3).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it3).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it3).getIdArticulo());
                partida.setIdCompleto(partidas.get(it3).getIdCompleto());
                partida.setIdVenta(partidas.get(it3).getIdVenta());
                partida.setPrecioCompra(partidas.get(it3).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it3).getPrecioVenta());
                partida.setSubtotal(partidas.get(it3).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it3).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it3).getUbicacion());
                partidas.get(it3).setConBeneficio(partidatotal);
                partidas.get(it3).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;
                
                
                iniciopartidas.add(partida);
                // iniciopartidas.add(partida);
                it3++;
            }
            iva = 0;
            total = subtotal;
        }
        //caso cliente con RFC
        else
        {
            iva =   subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;
            

            
            int it3 = 0;
            while(it3 < partidas.size())
            {
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it3).getBeneficio());
                partida.setCantidad(partidas.get(it3).getCantidad());
                partida.setCodigoArticulo(partidas.get(it3).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it3).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it3).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it3).getIdArticulo());
                partida.setIdCompleto(partidas.get(it3).getIdCompleto());
                partida.setIdVenta(partidas.get(it3).getIdVenta());
                partida.setPrecioCompra(partidas.get(it3).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it3).getPrecioVenta());
                partida.setSubtotal(partidas.get(it3).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it3).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it3).getUbicacion());
                subtotal = subtotal + partida.getSubtotal();
                iniciopartidas.add(partida);
                it3++;
            }
            iva = subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;
        }    
        }else
        {
            if(cliente.getNombre().equals("P�blico en general"))
        {
            iva = 0;
            subtotal = Double.valueOf(jTextField48.getText());
            total = Double.valueOf(jTextField48.getText());
            
            int it3 = 0;
            while(it3 < partidas.size())
            {
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it3).getBeneficio());
                partida.setCantidad(partidas.get(it3).getCantidad());
                partida.setCodigoArticulo(partidas.get(it3).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it3).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it3).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it3).getIdArticulo());
                partida.setIdCompleto(partidas.get(it3).getIdCompleto());
                partida.setIdVenta(partidas.get(it3).getIdVenta());
                partida.setPrecioCompra(partidas.get(it3).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it3).getPrecioVenta());
                partida.setSubtotal(partidas.get(it3).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it3).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it3).getUbicacion());
                iniciopartidas.add(partida);
                it3++;
            }
            
        }
        //caso cliente con RFC
        else
        {
          int it2 = 0;
            while(it2 < partidas.size())
            {
                double partidatotal = partidas.get(it2).getConBeneficio() / (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it2).getCantidad();
                Partidas partida = new Partidas();
                partida.setBeneficio(partidas.get(it2).getBeneficio());
                partida.setCantidad(partidas.get(it2).getCantidad());
                partida.setCodigoArticulo(partidas.get(it2).getCodigoArticulo());
                partida.setConBeneficio(partidas.get(it2).getConBeneficio());
                partida.setDescripcionArticulo(partidas.get(it2).getDescripcionArticulo());
                partida.setIdArticulo(partidas.get(it2).getIdArticulo());
                partida.setIdCompleto(partidas.get(it2).getIdCompleto());
                partida.setIdVenta(partidas.get(it2).getIdVenta());
                partida.setPrecioCompra(partidas.get(it2).getPrecioCompra());
                partida.setPrecioVenta(partidas.get(it2).getPrecioVenta());
                partida.setSubtotal(partidas.get(it2).getSubtotal());
                partida.setTipoBeneficio(partidas.get(it2).getTipoBeneficio());
                partida.setUbicacion(partidas.get(it2).getUbicacion());
                iniciopartidas.add(partida);
                partidas.get(it2).setConBeneficio(partidatotal);
                partidas.get(it2).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;

                it2++;
            }

            
            iva =   subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;    
            
        }    
        }  
         
         }
         
         hfacturas = new hFacturas();
         Factura numfac = hfacturas.consultaUltimaFactura("", "*", "");
         
        jTextField1.setText(CGlobalConfig.getFactura_emisorRFC());
        jTextField2.setText(CGlobalConfig.getFactura_emisorNombre());
        jTextField3.setText(CGlobalConfig.getFactura_emisorCalle());
        jTextField4.setText(CGlobalConfig.getFactura_emisorNumero());
        jTextField5.setText(CGlobalConfig.getFactura_emisorColonia());
        jTextField6.setText(CGlobalConfig.getFactura_experidoCP());
        jTextField7.setText(CGlobalConfig.getFactura_emisorCiudad());
        jTextField8.setText(CGlobalConfig.getFactura_emisorEstado());
        jTextField9.setText(CGlobalConfig.getFactura_emisorPais());
        
        jTextField19.setText(cliente.getRfc());
        jTextField20.setText(cliente.getNombre());
        jTextField21.setText(CGlobalConfig.getFactura_experidoCalle());
        jTextField22.setText(CGlobalConfig.getFactura_experidoNumero());
        jTextField23.setText(CGlobalConfig.getFactura_experidoColonia());
        jTextField24.setText(CGlobalConfig.getFactura_experidoCP());
        jTextField25.setText(CGlobalConfig.getFactura_experidoCiudad());
        jTextField26.setText(CGlobalConfig.getFactura_experidoEstado());
        jTextField27.setText(CGlobalConfig.getFactura_experidoPais());
        jTextField28.setText(cliente.getCalle());
        jTextField29.setText(String.valueOf(cliente.getNumeroExterior()));
        jTextField30.setText(String.valueOf(cliente.getColonia()));
        jTextField31.setText(String.valueOf(cliente.getCodigoPostal()));
        jTextField32.setText(String.valueOf(cliente.getPoblacion()));
        jTextField33.setText(cliente.getEstado());
        jTextField34.setText(String.valueOf(cliente.getPais()));
        jTextField10.setText(Validadores.validadorTranferencia(cliente.getTransferencia()));
        jTextField11.setText(cliente.getBanco());
        
        jTextField35.setText(fecha.substring(0,19));
        jTextField36.setText(CGlobalConfig.getFactura_serie());
        jTextField37.setText(CGlobalConfig.getFactura_anoAprobacion());
        jComboBox2.addItem("efectivo".toUpperCase());        
        jComboBox2.addItem("cheque".toUpperCase());
        jComboBox2.addItem("transferencia".toUpperCase());
        jComboBox2.addItem("tarjeta de cr�dito".toUpperCase());
        jComboBox2.addItem("tarjeta de d�bito".toUpperCase());
                
                
        jTextField40.setText(CGlobalConfig.getFactura_tipoComprobante()); 
        jTextField41.setText(factura.getIdConceptoFactura());
        jTextField42.setText(CGlobalConfig.getFactura_regimen());
        jTextField43.setText(CGlobalConfig.getFactura_noAprobacion());
        jTextField52.setText(factura.getMotivo());
        
        if(numfac == null)
        {
        jTextField44.setText(CGlobalConfig.getFactura_folioInicial());
        }
        else
        {
        System.out.println(Integer.parseInt(numfac.getFolio()));    
        int numfolio = Integer.parseInt(numfac.getFolio()) + 1;
        jTextField44.setText(String.valueOf(numfolio));        
        }
        jTextField38.setText(CGlobalConfig.getFactura_certificado());
        jTextField45.setText(df.format(subtotal));
        jTextField46.setText("0.00");   //Descuento
        jTextField47.setText(df.format(iva));
        jTextField48.setText(df.format(total));
        jTextField49.setText(factura.getTotalLetra());
        jTextField50.setText(CGlobalConfig.getFactura_mensaje1());
        jTextField51.setText(CGlobalConfig.getFactura_mensaje2());
        
      encabezadoPartidas = new Vector<String>();
      encabezadoPartidas.add("C�digo");
      encabezadoPartidas.add("Cantidad");
      encabezadoPartidas.add("Descripci�n");
      encabezadoPartidas.add("Unidad");
      encabezadoPartidas.add("Precio Unitario");
      encabezadoPartidas.add("Importe");

      
          vector = new Vector();
          for(Object o: partidas){
             Partidas ipartidas = (Partidas)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(ipartidas.getCodigoArticulo());
             unaFila.add(ipartidas.getCantidad());
             unaFila.add(ipartidas.getDescripcionArticulo());
             unaFila.add("PZA");
             unaFila.add(df.format(ipartidas.getConBeneficio()));
             unaFila.add(df.format(ipartidas.getSubtotal()));
             vector.add(unaFila);

         }
           dtm = new DefaultTableModel(vector,encabezadoPartidas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              };
                    
          jTable1.setModel(dtm);
          jTable1.getSelectionModel().setSelectionInterval(0, 0);
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
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextField35 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField36 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField37 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextField38 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jTextField40 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jTextField41 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jTextField42 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jTextField45 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jTextField46 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jTextField47 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jTextField48 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jTextField49 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        jTextField52 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Emisor"));

        jLabel1.setText("RFC");

        jTextField1.setEditable(false);

        jLabel2.setText("Nombre");

        jTextField2.setEditable(false);

        jLabel3.setText("Calle");

        jTextField3.setEditable(false);

        jLabel4.setText("N�mero");

        jTextField4.setEditable(false);

        jLabel5.setText("Colonia");

        jTextField5.setEditable(false);

        jLabel6.setText("C�digo Postal");

        jTextField6.setEditable(false);

        jLabel7.setText("Ciudad");

        jTextField7.setEditable(false);

        jLabel8.setText("Estado");

        jTextField8.setEditable(false);

        jLabel9.setText("Pa�s");

        jTextField9.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(1, 1, 1)
                                .addComponent(jTextField7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField8)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Facturado a"));

        jLabel19.setText("RFC");

        jTextField19.setEditable(false);

        jLabel20.setText("Nombre");

        jTextField20.setEditable(false);

        jLabel28.setText("Calle");

        jTextField28.setEditable(false);

        jLabel29.setText("N�mero");

        jTextField29.setEditable(false);

        jLabel30.setText("Colonia");

        jTextField30.setEditable(false);

        jLabel31.setText("C�digo Postal");

        jTextField31.setEditable(false);

        jLabel32.setText("Ciudad");

        jTextField32.setEditable(false);

        jLabel33.setText("Estado");

        jTextField33.setEditable(false);

        jLabel34.setText("Pa�s");

        jTextField34.setEditable(false);

        jButton3.setText("Cambiar Receptor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setText("D�gitos");

        jTextField10.setEditable(false);

        jLabel12.setText("Banco");

        jTextField11.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField32))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField30, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField33))
                            .addComponent(jTextField11)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Expedida en:"));

        jLabel21.setText("Calle");

        jTextField21.setEditable(false);

        jLabel22.setText("N�mero");

        jTextField22.setEditable(false);

        jLabel23.setText("Colonia");

        jTextField23.setEditable(false);

        jLabel24.setText("C�digo Postal");

        jTextField24.setEditable(false);

        jLabel25.setText("Ciudad");

        jTextField25.setEditable(false);

        jLabel26.setText("Estado");

        jTextField26.setEditable(false);

        jLabel27.setText("Pa�s");

        jTextField27.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField26)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Factura Electr�nica"));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Emisi�n"));

        jTextField35.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Serie - Folio"));

        jTextField36.setEditable(false);

        jTextField44.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("A�o y N�mero de Aprobaci�n"));

        jTextField37.setEditable(false);

        jTextField43.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("No. de Certificado"));

        jTextField38.setEditable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("M�todo de Pago"));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Comprobante"));

        jTextField40.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Folio Interno"));

        jTextField41.setEditable(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("R�gimen"));

        jTextField42.setEditable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Partidas"));

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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales e Impuestos"));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Subtotal"));

        jTextField45.setEditable(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Descuento"));

        jTextField46.setEditable(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("IVA"));

        jTextField47.setEditable(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));

        jTextField48.setEditable(false);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad con letra"));

        jTextField49.setEditable(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField49)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensajes P�gina"));

        jTextField50.setEditable(false);

        jTextField51.setEditable(false);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField50, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));

        jLabel10.setText("Version CFD");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2.2", "2.0" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Motivo"));

        jTextField52.setEditable(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField52)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jButton1.setText("Facturar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField12.setEditable(false);
        jTextField12.setText("Listo!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jTextField12))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        VDetalle_Factura.PSeleccionar_Cliente seleccionar = new VDetalle_Factura.PSeleccionar_Cliente();
        JDialog dialogo = new JDialog((JFrame)Window.getWindows()[0],"Clientes",true);
        dialogo.getContentPane().add(seleccionar);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo((JFrame)Window.getWindows()[0]);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          String password;
              JPasswordField passwordField = new JPasswordField();
              Object[] obj = {"Por favor escriba la contrase�a de alg�n administrador para modificar los campos:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
               
               int opcion = JOptionPane.showOptionDialog(null, obj, "Contrase�a requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion == 1)      
             return;
             else if(opcion == 0) //el usuario dio click en aceptar
             {
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             Autenticar autenticar = new Autenticar();
             if (!autenticar.validarPwd(password))
             {    
             JOptionPane.showMessageDialog(null, "Contrase�a incorrecta");
             return;
             }else
                  {
        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);
        jTextField6.setEditable(true);
        jTextField7.setEditable(true);
        jTextField8.setEditable(true);
        jTextField9.setEditable(true);
        jTextField19.setEditable(true);
        jTextField20.setEditable(true);
        jTextField21.setEditable(true);
        jTextField22.setEditable(true);
        jTextField23.setEditable(true);
        jTextField24.setEditable(true);
        jTextField25.setEditable(true);
        jTextField26.setEditable(true);
        jTextField27.setEditable(true);
        jTextField28.setEditable(true);
        jTextField29.setEditable(true);
        jTextField30.setEditable(true);
        jTextField31.setEditable(true);
        jTextField32.setEditable(true); // Ciudad!!!!
        jTextField33.setEditable(true);
        jTextField34.setEditable(true);
        jTextField35.setEditable(true); // Fecha de Emisi�n
        jTextField36.setEditable(true); // Serie
        jTextField37.setEditable(true);
        jTextField38.setEditable(true); // N�mero de Certificado
        jTextField10.setEditable(true);
        jComboBox2.setEnabled(true);
        jTextField11.setEditable(true);
        jTextField40.setEditable(true);
        jTextField41.setEditable(true); // Folio Interno
        jTextField42.setEditable(true); // R�gimen Fiscal
        jTextField43.setEditable(true);
        jTextField45.setEditable(true);
        jTextField46.setEditable(true);
        jTextField47.setEditable(true);
        jTextField48.setEditable(true);
        jTextField49.setEditable(true); // Cantidad con letra
        jTextField50.setEditable(true); // Mensaje
        jTextField51.setEditable(true); // Mensaje
                  }
             }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /*M�todo que tiene la funci�n de validar el rfc*/
public boolean validarRfc(String rfc){
rfc=rfc.toUpperCase().trim();
return rfc.toUpperCase().matches("[A-Z]{4}[0-9]{6}[A-Z0-9]{3}");
}//Cierra m�todo validarRFC


public boolean ValidaRfc(String rfcStr) {
	String strCorrecta;
        String valid;
	strCorrecta = rfcStr;	
	if (rfcStr.length() == 12){
	valid = "^(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))";
	}else{
	valid = "^(([A-Z]|[a-z]|\\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))";
	}
        return rfcStr.toUpperCase().matches(valid);
}

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
           Factura factemporal=hfacturas.consultaUltimaFactura("idConceptoFactura", "=", this.factura.getIdConceptoFactura());
           if(factemporal != null)
           {
               JOptionPane.showMessageDialog(null, "Esta venta ya ha sido facturada, por favor consulta el cat�logo de facturas");
               return;
           } 
           
            if(jTextField19.getText().isEmpty())
           {
               JOptionPane.showMessageDialog(null, "El cliente no tiene RFC, por favor corrija esta informaci�n antes de realizar la factura");
               return;               
           }
        
           if(!ValidaRfc(jTextField19.getText()))
           {
               JOptionPane.showMessageDialog(null, "La estructura del RFC del receptor no es v�lida, por favor verifique esto antes de realizar la factura");
               return;     
           }
        
           if(!CPrincipal.getConexion().crearTransaccion())
            return;
           
        hfacturas = new hFacturas();
/*      Factura numfac = hfacturas.consultaUltimaFactura("", "*", "");
        String numfolio = "";
        if(numfac == null)
        {
        numfolio = CGlobalConfig.getFactura_folioInicial();
        }
        else
        {
        int numeroFolio = Integer.parseInt(numfac.getFolio()) + 1;    
        numfolio = String.valueOf(numeroFolio);
        }   */
        int cantidadArticulos = 0;
        Factura facbd = new Factura();
      //  facbd.setIdFactura(Long.valueOf(numfolio));
        facbd.setAnoAprobacion(jTextField37.getText());
        facbd.setCertificado(""); 
        facbd.setDescuento(jTextField46.getText());
        facbd.setFecha(jTextField35.getText());
        facbd.setFolio("0"); 
        facbd.setFolioInterno(jTextField41.getText().substring(1));
        facbd.setFormaDePago(jComboBox2.getSelectedItem().toString());
        facbd.setRegimen(jTextField42.getText());
        facbd.setIdConceptoFactura(jTextField41.getText()); 
        facbd.setIdEmisorF((int)CConfiguracion.getId()); 
        facbd.setIdReceptorF((int)cliente.getId()); 
        facbd.setImporteImp(df.format(Double.valueOf(jTextField47.getText())));
        facbd.setImpuesto(df.format(Double.valueOf(jTextField47.getText())));
        facbd.setNoAprobacion(jTextField43.getText());
        facbd.setNoCertificado(jTextField38.getText());
        facbd.setSello("");  
        facbd.setSerie("FA");
        facbd.setSubtotal(df.format(Double.valueOf(jTextField45.getText())));
        facbd.setTasa(df.format(Double.valueOf(jTextField47.getText())));
        facbd.setTipoDeComprobante(jTextField40.getText());
        facbd.setTotal(df.format(Double.valueOf(jTextField48.getText())));
        facbd.setTotalLetra(jTextField49.getText());
        facbd.setVersion(jComboBox1.getSelectedItem().toString());
        facbd.setMotivo(jTextField52.getText());
        facbd.setCadenaCompleta(""); 
        facbd.setCantidadArticulos(cantidadArticulos);
        facbd.setMovimiento(this.factura.getMovimiento());
        facbd.setNumMovimiento(this.factura.getNumMovimiento());
        int folio=hfacturas.guardarFactura(facbd); 
           //poner numero folio
        hPartidasfacturas hpf = new hPartidasfacturas();
        
        List<Partidasfacturas> conceptos = new ArrayList<Partidasfacturas>();
        
        int it1 = 0;
        while (it1 < partidas.size())
        {
        Partidasfacturas pf = new Partidasfacturas();       
        pf.setCantidad(partidas.get(it1).getCantidad());
        pf.setCodigoArticulo(partidas.get(it1).getCodigoArticulo());
        pf.setDescripcion(partidas.get(it1).getDescripcionArticulo());
        // pf.setIdFactura(Long.valueOf(numfolio));
        pf.setIdFactura((long)folio);
        pf.setIdVenta(partidas.get(it1).getIdVenta());
        pf.setImporte(df.format(partidas.get(it1).getSubtotal()));
        pf.setPrecioUnitario(df.format(partidas.get(it1).getConBeneficio()));
        cantidadArticulos = cantidadArticulos + partidas.get(it1).getCantidad();
        conceptos.add(pf);
        hpf.guardarPartidas(pf); 
        //quitar tmb esto despues
       //  hpf.actualizarPartidas(pf, "id", "=", String.valueOf(partidas.get(it1).getId()));
        it1++;
        }
        
        
        CFactura facturaCFD = new CFactura();
        facturaCFD.setAnoaprobacion(jTextField37.getText());
        facturaCFD.setDescuento(jTextField46.getText());
        facturaCFD.setEecalle(jTextField21.getText());
        facturaCFD.setEecolonia(jTextField23.getText());
        facturaCFD.setEecp(jTextField24.getText());
        facturaCFD.setEeestado(jTextField26.getText());
        facturaCFD.setEelocalidad(jTextField25.getText());
        facturaCFD.setEemunicipio(jTextField25.getText());
        facturaCFD.setEenoexterior(jTextField22.getText());
        facturaCFD.setEepais(jTextField27.getText().toUpperCase());
        facturaCFD.setEnombre(jTextField2.getText());
        facturaCFD.setErfc(jTextField1.getText());
        // facturaCFD.setFolio(numfolio);
        facturaCFD.setFolio(String.valueOf(folio));
        facturaCFD.setFormapago(jComboBox2.getSelectedItem().toString());
        facturaCFD.setImporte(df.format(Double.valueOf(jTextField47.getText())));               //////
        facturaCFD.setImptraslados(df.format(Double.valueOf(jTextField47.getText())));
        facturaCFD.setNoaprobacion(jTextField43.getText());
        facturaCFD.setRcalle(jTextField28.getText());
        facturaCFD.setRcolonia(jTextField30.getText());
        facturaCFD.setRcp(jTextField31.getText());
        facturaCFD.setRestado(jTextField33.getText());
        facturaCFD.setRnoexterior(jTextField29.getText());
        facturaCFD.setRnombre(jTextField20.getText());
        facturaCFD.setRpais(jTextField34.getText().toUpperCase());
        facturaCFD.setRrfc(jTextField19.getText());
        facturaCFD.setRciudad(jTextField32.getText());
        facturaCFD.setSubtotal(df.format(Double.valueOf(jTextField45.getText())));
        facturaCFD.setTipoDeComprobante(jTextField40.getText());
        facturaCFD.setTotal(df.format(Double.valueOf(jTextField48.getText())));
        facturaCFD.setUecalle(jTextField3.getText());
        facturaCFD.setUecolonia(jTextField5.getText());
        facturaCFD.setUecp(jTextField6.getText());
        facturaCFD.setUeestado(jTextField8.getText());
        facturaCFD.setUelocalidad(jTextField7.getText());
        facturaCFD.setUemunicipio(jTextField7.getText());
        facturaCFD.setUenoexterior(jTextField4.getText());
        facturaCFD.setUepais(jTextField9.getText().toUpperCase());
        facturaCFD.setRegimenfiscal(jTextField42.getText());
        facturaCFD.setVersion(jComboBox1.getSelectedItem().toString());
        facturaCFD.setNumCtaPago(jTextField10.getText());
        facturaCFD.Sellar(conceptos);
        
        jTextField12.setText("Sellando...");
        
        facbd.setCertificado(facturaCFD.getCertificado()); //
        facbd.setFolio(String.valueOf(folio)); //
        facbd.setSello(facturaCFD.getSello());  //
        facbd.setCadenaCompleta(facturaCFD.getCadenaOriginal()); //
        jTextField12.setText("Guardando..."); 
        //quitar esto de abajo tmb...
         hfacturas.actualizarFactura(facbd, "idFactura", "=", String.valueOf(folio));
         
        
              
        jTextField12.setText("Obteniendo Reporte...");
        ReporteFactura reportefactura = new ReporteFactura();
        if(jComboBox2.getSelectedItem().toString().toLowerCase().equals("efectivo"))
        reportefactura.crearReporte((long)folio,jTextField50.getText(),jTextField51.getText(),"","",0);     
        else
        reportefactura.crearReporte((long)folio,jTextField50.getText(),jTextField51.getText(),jTextField11.getText(),jTextField10.getText(),0); 
        
        if(tipo.equals("venta"))
         {
        hVentas hventas = new hVentas();
        hventas.cambiarAFacturada(String.valueOf(jTextField41.getText().substring(2)));
        // VDetalle_Ventas.jButton17.setEnabled(false);
        }
        
        else if(tipo.equals("reservacion"))
         {
        hReservaciones hr = new hReservaciones();
        hr.facturar(reservacion);  
        hVentas hventas = new hVentas();
        hventas.cambiarAFacturadaR(reservacion);
        try
        {
        VActualiza_Reservacion.jButton12.setEnabled(false);
        }catch(NullPointerException e)
        {
         System.out.println("Pantalla reser no abierta =)");   
         try
        {
//        VDetalle_Ventas.jButton17.setEnabled(false);
        }catch(NullPointerException f)
        {
         System.out.println("Pantalla ventas no abierta =)");   
         
        }
         
        }
         }else if(tipo.equals("aUsado") || tipo.equals("aNuevo"))
         {
             hVentaAnticipos hventasa = new hVentaAnticipos();
             hventasa.cambiarAFacturada(String.valueOf(jTextField41.getText().substring(2)));
             VDetalle_VentasAnticipos.jButton10.setEnabled(false);
         }
        
        
        if(jTextField41.getText().startsWith("R")||jTextField41.getText().startsWith("VC"))
        {
         hCuentasPorCobrar hcpc = new hCuentasPorCobrar();
         hcpc.ponerFactura((long)folio, "tipo", "=", jTextField41.getText());
        }
        
        CPrincipal.getConexion().finalizarTransaccion();
        
        jTextField12.setText("Generando Respaldo..."); 
        facturaCFD.generarRespaldo("xmls/fac"+folio+".xml", "\\\\192.168.1.90\\Reports\\xmls\\fac"+folio+".xml");
        facturaCFD.generarRespaldo("pdfs/factura"+folio+".pdf", "\\\\192.168.1.90\\Reports\\pdfs\\factura"+folio+".pdf");
        try {
            Desktop.getDesktop().open(new File("pdfs/factura"+folio+".pdf"));
        } catch (IOException ex) {
            Logger.getLogger(VDetalle_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jTextField12.setText("Factura Completa!"); 
        jButton1.setEnabled(false);
                }catch(Exception e)
        {
            CPrincipal.getConexion().cancelarTransaccion();
            e.printStackTrace();            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
    private Factura factura;
    private Clientes cliente;
    private hClientes hcliente;
    private hPartidas hpartidas;
    private hPartidaVentaAnticipos hpvanticipos;
    private List<Partidas> partidas,iniciopartidas;
    private List<Partidasventaanticipos> pvanticipos;
    private Vector<String> encabezadoPartidas;
    private DefaultTableModel dtm;
    private Vector vector;
    private hFacturas hfacturas;
    private String tipo="";
    private String reservacion="";
    DecimalFormat df;
  
    
    
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

    public PSeleccionar_Cliente(){
     hcliente = new hClientes();
     listaCliente = hcliente.consultaClientes("","*","");  
     
     
     setLayout(new BorderLayout());   
     panelEncabezado = new JPanel();
     panelEncabezado.setLayout(new FlowLayout());
     panelTabla = new JPanel();
     panelTabla.setLayout(new GridLayout());
     jlabel1 = new JLabel("Filtrar por:");
     jcombobox1 = new JComboBox();
     jcombobox1.addItem("Nombre");
     jcombobox1.addItem("RFC");
     jcombobox1.addItem("C�digo");
     jtextfield1 = new JTextField(20);    
     
     panelEncabezado.add(jlabel1);
     panelEncabezado.add(jcombobox1);
     panelEncabezado.add(jtextfield1);
        
     tablaCliente = new JTable();
    // ubicacionesGuardadas = new ArrayList<Articuloubicacion>();
     encabezadoCliente = new Vector<String>();
     encabezadoCliente.add("RFC");
     encabezadoCliente.add("Cliente");
     encabezadoCliente.add("C�digo");

     
     
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
        indice = tablaCliente.getSelectedRow();
        jTextField19.setText(listaCliente.get(indice).getRfc());
        jTextField20.setText(listaCliente.get(indice).getNombre());
        jTextField28.setText(listaCliente.get(indice).getCalle());
        jTextField29.setText(String.valueOf(listaCliente.get(indice).getNumeroExterior()));
        jTextField30.setText(String.valueOf(listaCliente.get(indice).getColonia()));
        jTextField31.setText(String.valueOf(listaCliente.get(indice).getCodigoPostal()));
        jTextField32.setText(String.valueOf(listaCliente.get(indice).getPoblacion()));
        jTextField33.setText(listaCliente.get(indice).getEstado());
        jTextField34.setText(String.valueOf(listaCliente.get(indice).getPais()));
        jTextField10.setText(Validadores.validadorTranferencia(listaCliente.get(indice).getTransferencia()));
        jTextField11.setText(listaCliente.get(indice).getBanco());
        
        double iva = 0;
        double subtotal = 0;
        double total = 0;

        
         if(tipo.equals("venta")||tipo.equals("reservacion"))
         {
            if(listaCliente.get(indice).getNombre().equalsIgnoreCase("P�blico en general"))
        {
            iva = 0;
            subtotal = Double.valueOf(jTextField48.getText());
            total = Double.valueOf(jTextField48.getText());
            
            int it1 = 0;
            while(it1 < partidas.size())
            {
                jTable1.getModel().setValueAt(df.format(iniciopartidas.get(it1).getSubtotal()), it1, 4); 
                jTable1.getModel().setValueAt(df.format(iniciopartidas.get(it1).getSubtotal()), it1, 5); 
                it1++;
            }
               dtm.fireTableDataChanged();
                        
        }
        //caso cliente con RFC
        else
        {
            int it1 = 0;
            while(it1 < partidas.size())
            {
                partidas.get(it1).setConBeneficio(iniciopartidas.get(it1).getConBeneficio());
                partidas.get(it1).setSubtotal(iniciopartidas.get(it1).getSubtotal());
                double partidatotal = partidas.get(it1).getConBeneficio() / (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it1).getCantidad();
                partidas.get(it1).setConBeneficio(partidatotal);
                partidas.get(it1).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;
                jTable1.getModel().setValueAt(df.format(partidatotal), it1, 4); 
                jTable1.getModel().setValueAt(df.format(totalarticulos), it1, 5); 
                it1++;
            }
               dtm.fireTableDataChanged();
            
            iva =   subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;    
        }    
         }else //anticipos
         {
                if(tipo.equals("aUsado"))
        //caso p�blico en general
        {
        if(listaCliente.get(indice).getNombre().equalsIgnoreCase("P�blico en general"))
        {
            
            int it1 = 0;
            while(it1 < partidas.size())
            {
                partidas.get(it1).setConBeneficio(iniciopartidas.get(it1).getConBeneficio());
                partidas.get(it1).setSubtotal(iniciopartidas.get(it1).getSubtotal());
                
                double partidatotal = partidas.get(it1).getConBeneficio() * (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it1).getCantidad();
                
                partidas.get(it1).setConBeneficio(partidatotal);
                partidas.get(it1).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;
                
                jTable1.getModel().setValueAt(df.format(partidas.get(it1).getSubtotal()), it1, 4); 
                jTable1.getModel().setValueAt(df.format(partidas.get(it1).getSubtotal()), it1, 5); 
                it1++;
            }
            
              total = subtotal;
              iva = 0;
            
               dtm.fireTableDataChanged();
            
        }
        //caso cliente con RFC
        else
        {
            iva = 0;
            subtotal = 0;
            total = 0;
            
            int it1 = 0;
            while(it1 < partidas.size())
            {
                partidas.get(it1).setConBeneficio(iniciopartidas.get(it1).getConBeneficio());
                partidas.get(it1).setSubtotal(iniciopartidas.get(it1).getSubtotal());
                subtotal = subtotal +  partidas.get(it1).getSubtotal();
                
                jTable1.getModel().setValueAt(df.format(partidas.get(it1).getSubtotal()), it1, 4); 
                jTable1.getModel().setValueAt(df.format(partidas.get(it1).getSubtotal()), it1, 5); 
                it1++;
            }
               iva =   subtotal * CGlobalConfig.getIvaVenta();
               total = subtotal + iva;   
               dtm.fireTableDataChanged();
            
        }    
        }else
        {
            if(listaCliente.get(indice).getNombre().equalsIgnoreCase("P�blico en general"))
        {
            iva = 0;
            subtotal = Double.valueOf(jTextField48.getText());
            total = Double.valueOf(jTextField48.getText());
            
            int it1 = 0;
            while(it1 < partidas.size())
            {
                jTable1.getModel().setValueAt(df.format(iniciopartidas.get(it1).getSubtotal()), it1, 4); 
                jTable1.getModel().setValueAt(df.format(iniciopartidas.get(it1).getSubtotal()), it1, 5); 
                it1++;
            }
               dtm.fireTableDataChanged();
        }
        //caso cliente con RFC
        else
        {
             int it1 = 0;
            while(it1 < partidas.size())
            {
                partidas.get(it1).setConBeneficio(iniciopartidas.get(it1).getConBeneficio());
                partidas.get(it1).setSubtotal(iniciopartidas.get(it1).getSubtotal());
                double partidatotal = partidas.get(it1).getConBeneficio() / (1 +  CGlobalConfig.getIvaVenta());
                double totalarticulos = partidatotal * partidas.get(it1).getCantidad();
                partidas.get(it1).setConBeneficio(partidatotal);
                partidas.get(it1).setSubtotal(totalarticulos);
                subtotal = subtotal + totalarticulos;
                jTable1.getModel().setValueAt(df.format(partidatotal), it1, 4); 
                jTable1.getModel().setValueAt(df.format(totalarticulos), it1, 5); 
                it1++;
            }
               dtm.fireTableDataChanged();
            
            iva =   subtotal * CGlobalConfig.getIvaVenta();
            total = subtotal + iva;    
            
        }    
        }  
         }
        
        
        jTextField45.setText(String.valueOf(df.format(subtotal)));
        jTextField47.setText(String.valueOf(df.format(iva)));       
        jTextField48.setText(String.valueOf(df.format(total)));       
        
        cliente.setId(listaCliente.get(indice).getId());
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

           jtextfield1.addKeyListener(new KeyListener(){
                public void keyTyped(KeyEvent e) {
                    if(jtextfield1.getText().length() < 4)
                        return;
                    
                    if(jcombobox1.getSelectedIndex() == 0)                
                    {
                    for (int i = listaCliente.size()-1; i >= 0; i--) {
                    if (!listaCliente.get(i).getNombre().contains(jtextfield1.getText())) {
                    listaCliente.remove(i);                   
                          }
                       }
                    }else if(jcombobox1.getSelectedIndex() == 1)                
                    {
                    for (int i = listaCliente.size()-1; i >= 0; i--) {
                    if (!listaCliente.get(i).getRfc().contains(jtextfield1.getText())) {
                    listaCliente.remove(i);                   
                          }
                       }
                    } else
                    {
                    for (int i = listaCliente.size()-1; i >= 0; i--) {
                    if (!listaCliente.get(i).getCodigo().contains(jtextfield1.getText())) {
                    listaCliente.remove(i);                   
                          }
                       }
                    }    
                      vector.clear();
                      

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

                public void keyPressed(KeyEvent e) {
                  return;
                }

                public void keyReleased(KeyEvent e) {
                  return;
                }
     
     });
      
      add(panelEncabezado,BorderLayout.NORTH);
      panelTabla.add(new JScrollPane(tablaCliente));
      add(panelTabla,BorderLayout.CENTER);
    }

}
    
}

