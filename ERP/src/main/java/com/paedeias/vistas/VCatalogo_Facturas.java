/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.Numero_a_Letra;
import com.paedeias.controladores.facturacion.CrearFirma;
import com.paedeias.controladores.facturacion.DOMUtils;
import com.paedeias.controladores.facturacion.ReporteFacturaCFDI;
import com.paedeias.helpers.*;
import com.paedeias.identidades.*;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import mx.bigdata.sat.cfdi.schema.Comprobante;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.tsp.interconecta.ws.InterconectaWs;
import com.tsp.interconecta.ws.InterconectaWsService;
import com.tsp.interconecta.ws.WsArregloCancelacion;
import com.tsp.interconecta.ws.WsGenericResp;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import org.w3c.dom.Document;

public class VCatalogo_Facturas extends JPanel{
    
    hFacturas facturas;
    hFacturasCFDI facturasCFDI;
    JTable tablaFacturas;
    JPanel panelTabla,panelEncabezado,panelPrincipal;
    JLabel leyenda1,leyenda2;
    JComboBox combo1,combo2;
    JTextField campoTexto1;
    JButton boton1,botonEliminarCFDI;
    Vector<String> encabezadoFacturas;
    DefaultTableModel dtm;
    Vector vector;
    List<Factura> listaFacturas;
    List<Factura> listaFacturas2;
    ImageIcon imagen,imagen2,imagenbuscar;
    
    
        public VCatalogo_Facturas()
    {
    
            tablaFacturas = new JTable();

        encabezadoFacturas = new Vector<String>();
        encabezadoFacturas.add("Folio");
        encabezadoFacturas.add("Fecha");
        encabezadoFacturas.add("Tipo de Comprobante");
        encabezadoFacturas.add("Subtotal");
        encabezadoFacturas.add("Total");
        encabezadoFacturas.add("Código Interno");
        encabezadoFacturas.add("Impuestos");
        encabezadoFacturas.add("Cancelada");
        encabezadoFacturas.add("Cancelar");



         facturas = new hFacturas();
         facturasCFDI = new hFacturasCFDI();
         vector = new Vector();

           dtm = new DefaultTableModel(vector,encabezadoFacturas) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              
              @Override
              public Class<?> getColumnClass(int columnIndex) {
              if (columnIndex == 7) {
              return Boolean.class;
               }
              if (columnIndex == 8) {
              return Boolean.class;
               }
               return super.getColumnClass(columnIndex);
                }
              
              
              };

            tablaFacturas.setModel(dtm);

            tablaFacturas.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2)
                {   
   
         if(listaFacturas.get(tablaFacturas.getSelectedRow()).getVersion().equals("3.2"))           
         {
        VDetalle_Facturacion_CFDI factura = new VDetalle_Facturacion_CFDI(listaFacturas.get(tablaFacturas.getSelectedRow()));
        int indice = ((JTabbedPane) getParent().getParent().getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent().getParent().getParent()).add(new JScrollPane(factura), indice);
        ((JTabbedPane) getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle de factura", indice));   
        ((JTabbedPane) getParent().getParent().getParent()).setSelectedIndex(indice);
        return;
         }
                    
        VDetalle_Facturacion factura = new VDetalle_Facturacion(listaFacturas.get(tablaFacturas.getSelectedRow()));
        int indice = ((JTabbedPane) getParent().getParent().getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent().getParent().getParent()).add(new JScrollPane(factura), indice);
        ((JTabbedPane) getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Detalle de factura", indice));
        ((JTabbedPane) getParent().getParent().getParent()).setSelectedIndex(indice);            
                    
/*
                     tablaFacturas.getSelectedRow();
                     Factura fac = listaFacturas.get(tablaFacturas.getSelectedRow());
                     VActualiza_Facturas actualiza = new VActualiza_Facturas(fac);

                     int indice = ((JTabbedPane)getParent().getParent().getParent()).getSelectedIndex();
                     indice +=1;
            ((JTabbedPane)getParent().getParent().getParent()).add(new JScrollPane(actualiza), indice);
                ((JTabbedPane)getParent().getParent().getParent()).setTabComponentAt(indice, new botonCierre("Consulta",indice)); */
                }
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                             int r = tablaFacturas.rowAtPoint(e.getPoint());
            if (r >= 0 && r < tablaFacturas.getRowCount()) {
                tablaFacturas.setRowSelectionInterval(r, r);
            } else {
                tablaFacturas.clearSelection();
            }

            int rowindex = tablaFacturas.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem item = new JMenuItem("Seleccionar para Cancelación CFDI");
                item.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
               
                   if(!Boolean.valueOf(dtm.getValueAt(tablaFacturas.getSelectedRow(), 8).toString()))
                    {
                  if(listaFacturas.get(tablaFacturas.getSelectedRow()).getCancelada() == 1)
                   {
                    JOptionPane.showMessageDialog(null, "No se puede seleccionar una factura previamente cancelada");       
                    return;    
                   }                        
                  if(listaFacturas.get(tablaFacturas.getSelectedRow()).getVersion().equals("2.2"))
                   {
                   JOptionPane.showMessageDialog(null, "Esta factura no puede cancelarse de este modo, ya que no es CFDI");    
                   return;
                   }      
                        
                        
                   dtm.setValueAt(true, tablaFacturas.getSelectedRow(), 8);
                   listaFacturas.get(tablaFacturas.getSelectedRow()).setSeleccionarCancelacion(true);
                    }
               
                        }
                
                });
                
                JMenuItem item2 = new JMenuItem("Deshacer selección CFDI");
                item2.addActionListener(new ActionListener(){

                        public void actionPerformed(ActionEvent e) {
                   
                    if(listaFacturas.get(tablaFacturas.getSelectedRow()).getCancelada() == 1)
                   {
                    JOptionPane.showMessageDialog(null, "No se puede seleccionar una factura previamente cancelada");       
                    return;    
                   }        

                    if(Boolean.valueOf(dtm.getValueAt(tablaFacturas.getSelectedRow(), 8).toString()))
                    {
                   dtm.setValueAt(false, tablaFacturas.getSelectedRow(), 8);
                   listaFacturas.get(tablaFacturas.getSelectedRow()).setSeleccionarCancelacion(false);
                    }
                            
                        }
                
                });
                
                
                
                
                
                
                
                popup.add(item);
                popup.add(item2);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
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


        imagen2 = new ImageIcon(getClass().getResource("/mainicons/devolver.jpg"));
       
        
        botonEliminarCFDI = new JButton(imagen2);
        botonEliminarCFDI.setToolTipText("Cancelar factura CFDI");
        botonEliminarCFDI.setBackground(Color.white);
        botonEliminarCFDI.setBorderPainted(false);
        botonEliminarCFDI.setEnabled(CConfiguracion.isCancelarCFDI());
        botonEliminarCFDI.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            
           if(tablaFacturas.getSelectedRow() == -1)
           {
            JOptionPane.showMessageDialog(null, "Por favor seleccione primero una fila");
            return;
           }              
                
            if(!listaFacturas.isEmpty())
            {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de desear cancelar esta factura?","Cancelación",JOptionPane.YES_NO_OPTION);    
            if(respuesta == JOptionPane.YES_OPTION)
            {
                        try {
                            Charset ENCODING = StandardCharsets.UTF_8;
                            List<String> listauuids = new ArrayList<String>();
                            for(int i=0; i<listaFacturas.size(); i++)    
                            {
                            if(listaFacturas.get(i).isSeleccionarCancelacion())    
                            listauuids.add(listaFacturas.get(i).getUuid());
                            }
                            if(listauuids.isEmpty())
                            {
                            JOptionPane.showMessageDialog(null, "Ninguna partida ha sido seleccionada para cancelarse");
                            return;    
                            }
                            
                            Document doc = DOMUtils.construirCancelacion(listauuids);
                            CrearFirma crearfirma = new CrearFirma();
                            crearfirma.construirFirma(doc);
                            // fis = new FileInputStream("salida.xml");
                            String cadenaxml="";
                             Path path = Paths.get("salida.xml");
                             try {
                             Scanner scanner =  new Scanner(path, ENCODING.name());  
                            while (scanner.hasNextLine()){
        //process each line in some way
                             cadenaxml = cadenaxml + scanner.nextLine();
                               }     
                             }catch(Exception ex) {}
       
    
                             System.out.println(cadenaxml);
                             
                              InterconectaWsService wss = new InterconectaWsService();
                              InterconectaWs ws = wss.getInterconectaWsPort();
     
                              WsGenericResp resp = ws.cancelaCFDIxp(CGlobalConfig.getWsUsuario(), CGlobalConfig.getWsPassword(),cadenaxml);
     
                             if(resp.isIsError())
                             {
                             System.out.println(resp.getNumError());  
                             System.out.println(resp.getErrorMessage());  
                             System.out.println(resp.getCadenaOriginal());  
                             System.out.println(resp.getFolioUUID());  
                             System.out.println(resp.getSelloDigitalEmisor());  
                             System.out.println(resp.getSelloDigitalTimbreSAT());  
                             JOptionPane.showMessageDialog(null, resp.getErrorMessage());
                             return;
                             }                                 
/*     System.out.println(resp.getFechaHoraTimbrado().getYear() +"-"+resp.getFechaHoraTimbrado().getMonth()
             + "-"+resp.getFechaHoraTimbrado().getDay() + ":"+ resp.getFechaHoraTimbrado().getHour()+":"
             +resp.getFechaHoraTimbrado().getMinute()+":"+resp.getFechaHoraTimbrado().getSecond());  */
     
                             hCuentasPorCobrar hcc = new hCuentasPorCobrar();
                              String fecha = hcc.generarFecha().substring(0, 19);
                              fecha = fecha.replace(":", "_");
                              fecha = fecha.replace("-", "_");
                              File xmlSAT = new File("xmls/acusecanceladoSAT"+ fecha +".xml");
     

                             FileOutputStream fos3 = new FileOutputStream(xmlSAT);
                              fos3.write(resp.getAcuse());
                              fos3.flush();
                              fos3.close(); 
                             
                           //  System.out.println("Folio Código Cancelación "+resp.getFolioCodCancelacion());
                             WsArregloCancelacion arr = resp.getArrayFoliosCancelacion();
                             
                          
            if(
              listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().equals("Reservacion")||
              listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().equals("Efectivo")||
              listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().equals("Credito"))
            {
                hVentas hventas = new hVentas();
                Ventas ventas = hventas.consultaUltimaVenta("id", "=", listaFacturas.get(tablaFacturas.getSelectedRow()).getIdConceptoFactura().substring(2));
                hventas.cambiarANoFacturada(String.valueOf(ventas.getId()));
            }else if(listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().equals("Anticipo Nuevo")||
                    listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().equals("Anticipo Usado"))
            {
                hVentaAnticipos hventaanticipos = new hVentaAnticipos();
                Ventaanticipos ventaanticipos = hventaanticipos.consultaUltimaVenta("id", "=", listaFacturas.get(tablaFacturas.getSelectedRow()).getIdConceptoFactura().substring(2));
                hventaanticipos.cambiarANoFacturada(String.valueOf(ventaanticipos.getId()));
            }else if(listaFacturas.get(tablaFacturas.getSelectedRow()).getMovimiento().startsWith("Devolución"))
            {
                hDevoluciones hdevoluciones = new hDevoluciones();
                List<Devoluciones> devoluciones = hdevoluciones.consultaVentas("id", "=", listaFacturas.get(tablaFacturas.getSelectedRow()).getIdConceptoFactura().substring(2));
                hdevoluciones.cambiarANoFacturada(String.valueOf(devoluciones.get(0).getId()));
            }
            
            
            listaFacturas.get(tablaFacturas.getSelectedRow()).setIdConceptoFactura("0000");
            facturasCFDI.actualizarFactura(listaFacturas.get(tablaFacturas.getSelectedRow()), "idFactura", "=", String.valueOf(listaFacturas.get(tablaFacturas.getSelectedRow()).getIdFactura()));    
            facturasCFDI.cancelar(String.valueOf(listaFacturas.get(tablaFacturas.getSelectedRow()).getIdFactura()));    
            boton1.doClick(); 
            JOptionPane.showMessageDialog(null, "Factura Cancelada");
            
        ReporteFacturaCFDI reportefactura = new ReporteFacturaCFDI();              
               reportefactura.crearCancelacionCFDIWeb(fecha, CGlobalConfig.getFactura_emisorRFC(),arr);    
                try {
                    reportefactura.fillCancelacion(fecha);
                } catch (JRException ex) {
                    Logger.getLogger(VCatalogo_Facturas.class.getName()).log(Level.SEVERE, null, ex);
                }        
            
        try {
        Desktop.getDesktop().open(new File("pdfs/cancelacion"+fecha+"cfdi.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(VDetalle_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            
            }catch (IOException ex) {
                            Logger.getLogger(VCatalogo_Facturas.class.getName()).log(Level.SEVERE, null, ex);
                        } 
            }
            

            }
            }

        });

        leyenda1 = new JLabel("Buscar proveedores en donde:");
        leyenda2 = new JLabel("sea");
        combo1 = new JComboBox();
        combo1.addItem("Folio");
        combo1.addItem("Fecha");
        combo1.addItem("Tipo de Comprobante");
        combo1.addItem("Subtotal");
        combo1.addItem("Total");
        combo1.addItem("Código Interno");
        combo1.addItem("Impuestos");
        combo1.addItem("Reservaciones");
        combo1.addItem("Ventas Mostrador");
        combo1.addItem("Ventas Crédito");
        combo1.addItem("Anticipos Usado");
        combo1.addItem("Anticipos Nuevo");

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
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

        

        String valores [] = {"folio","fecha","tipoDeComprobante",
        "subtotal","total","idConceptoFactura","impuesto","reservaciones","ventamostrador","ventacredito","anticiposusado","anticiposnuevo"};
        String comparadores [] = {"=","LIKE","*"};

        String campo = valores[combo1.getSelectedIndex()];
        String compara = comparadores[combo2.getSelectedIndex()];
        String condicion = campoTexto1.getText();
        
        
        

         if(combo1.getSelectedItem().equals("Reservaciones"))
         {
         listaFacturas2 = facturas.consultaFacturasReservacion(compara,condicion);
         listaFacturas = facturasCFDI.consultaFacturasReservacion(compara,condicion);
         }
         else if(combo1.getSelectedItem().toString().equals("Ventas Mostrador"))
         {
         listaFacturas2 = facturas.consultaFacturasVentas(compara,condicion);
         listaFacturas = facturasCFDI.consultaFacturasVentas(compara,condicion);
         }
         else if(combo1.getSelectedItem().toString().equals("Ventas Crédito"))
         {
         listaFacturas2 = facturas.consultaFacturasVentas(compara,condicion);
         listaFacturas = facturasCFDI.consultaFacturasVentas(compara,condicion);
         }
         else if(combo1.getSelectedItem().toString().equals("Anticipos Usado"))
         {
         listaFacturas2 = facturas.consultaFacturasUsado(compara,condicion);
         listaFacturas = facturasCFDI.consultaFacturasUsado(compara,condicion);
         }
         else if(combo1.getSelectedItem().toString().equals("Anticipos Nuevo"))
         {
         listaFacturas2 = facturas.consultaFacturasNuevo(compara,condicion);
         listaFacturas = facturasCFDI.consultaFacturasNuevo(compara,condicion);
         }
         else
         {
         listaFacturas2 = facturas.consultaFacturas(campo,compara,condicion);    
         listaFacturas = facturasCFDI.consultaFacturas(campo,compara,condicion);    
         }
         
         if(listaFacturas == null)
         listaFacturas = new ArrayList<Factura>();
         if(listaFacturas2 == null)
         listaFacturas2 = new ArrayList<Factura>();
         
         for(int i=0; i<listaFacturas2.size(); i++)
         {
         listaFacturas.add(listaFacturas2.get(i));    
         }
         
         vector.removeAllElements();

         for(Object o: listaFacturas){
             Factura factu = (Factura)o;
             Vector<Object> unaFila = new Vector<Object>();
             unaFila.add(String.valueOf(factu.getFolio()));
             unaFila.add(factu.getFecha());
             unaFila.add(factu.getTipoDeComprobante());
             unaFila.add(String.valueOf(factu.getSubtotal()));
             unaFila.add(String.valueOf(factu.getTotal()));
             unaFila.add(factu.getIdConceptoFactura());
             unaFila.add(factu.getImpuesto());
             if(factu.getCancelada()==1)
             unaFila.add(true);    
             else
             unaFila.add(false);        
             unaFila.add(false);
             vector.add(unaFila);
             

         }
          /* DefaultTableModel dtm = new DefaultTableModel(vector,encabezadoProveedores) {

              @Override
              public boolean isCellEditable(int row, int column) {
              return false;
                }
              }; */

                 dtm.fireTableDataChanged();
                 tablaFacturas.setModel(dtm);
        

            }
        });
        
          tablaFacturas.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e) {
            }

        }
                );


         
        panelEncabezado.add(leyenda1);
        panelEncabezado.add(combo1);
        panelEncabezado.add(leyenda2);
        panelEncabezado.add(combo2);
        panelEncabezado.add(campoTexto1);
        panelEncabezado.add(boton1);
        panelEncabezado.add(botonEliminarCFDI);

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout());


        setLayout(new GridLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.white);
        panelEncabezado.setBackground(Color.white);
        panelTabla.setBackground(Color.white);
        
        
        campoTexto1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                boton1.doClick();
            }
        });





           panelTabla.add(new JScrollPane(tablaFacturas));
           panelPrincipal.add(panelEncabezado,BorderLayout.NORTH);
           panelPrincipal.add(panelTabla,BorderLayout.CENTER);
           add(panelPrincipal);
        setBackground(Color.white);

/*          if(CConfiguracion.getPuesto().equals("Ventas"))
        {
            botonEliminar.setEnabled(false);
            botonAlta.setEnabled(false);
        }else if(CConfiguracion.getPuesto().equals("Almacén"))
        {
            botonEliminar.setEnabled(false);
            botonAlta.setEnabled(false);
        } */
        
        
    }
    
}
