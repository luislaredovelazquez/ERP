/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;


/**
 *
 * @author luis
 */

import com.paedeias.controladores.Numero_a_Letra;
import com.paedeias.helpers.hFacturasCFDI;
import com.paedeias.identidades.Factura;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.jdom.filter.ElementFilter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdom.Namespace;


import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Text;


public class VAddendaCFDI extends JPanel {

    //Interfaz

    private JLabel     codigo1;
    private JTextField campoTexto1;
    private JLabel     descripcion2;
    private JTextField campoTexto2;
    private JLabel     descripcion3;
    private JTextField campoTexto3;
    private JTextField campoTexto4;
    private JLabel     descripcion4;
    private JTextField campoTexto5;
    private JLabel     descripcion5;
    private JTextField campoTexto6;
    private JLabel     descripcion6;
    private JLabel     descripcion7;
    private JTextField campoTexto7;
    private JLabel     descripcion8;
    private JTextField campoTexto8;
    private JLabel     descripcion9;
    private JTextField campoTexto9;
    private JLabel     descripcion10;
    private JTextField campoTexto10;
    private JLabel     descripcion11;
    private JTextField campoTexto11;
    private JLabel     descripcion12;
    private JTextField campoTexto12;
    private JLabel     descripcion13;
    private JTextField campoTexto13;
    
    
    private JButton generar;
    private JButton imprimir;
    private MyFileListener myFileListener;

    String lineaFinal="";

    //Interfaz

    /**
     * @param args the command line arguments
     */
    String c_moneda="MXN";
    String c_tipoCambio="1.000000";
    String c_formaPago="PAGO EN UNA SOLA EXHIBICION";
    
    String c_emisor_nombre="COLISION Y MECANICA S.A DE C.V.";
    String c_emisor_rfc="CME000515HX7";
    String c_emisor_calle="SALVADOR DIAZ MIRON";        
    String c_emisor_codigoPostal="50150";
    String c_emisor_colonia="SANCHEZ COLIN";
    String c_emisor_estado="ESTADO DE MÉXICO";
    String c_emisor_localidad="TOLUCA";
    String c_emisor_municipio="TOLUCA";
    String c_emisor_noExterior="0";
    String c_emisor_noInterior="0";
    String c_emisor_pais="MÉXICO";   
    
      String c_receptor_nombre="QUALITAS COMPAÑIA DE SEGUROS S.A.B.  DE C.V.";
      String c_receptor_rfc="QCS931209G49";
      String c_receptor_calle="JOSE MARIA CASTORENA";
      String c_receptor_codigoPostal="05200";
      String c_receptor_colonia="SAN JOSE DE LOS CEDROS";
      String c_receptor_estado="DISTRITO FEDERAL";
      String c_receptor_localidad="CUAJIMALPA";
      String c_receptor_municipio="CUAJIMALPA";
      String c_receptor_noExterior="426";
      String c_receptor_nrointerior="0";
      String c_receptor_pais="MÉXICO";
      
      
      String c_idArea="001";
      String c_idRevision="003";
      String c_numeroInterno="04";
      String c_estadoOr="ORIGINAL";
      String c_tipoaddenda="33";
      String c_tindlista="0";
      String c_mntdcto="0.00";
      String c_mntbase="0.00";
      
    String c_color="X";        
    String c_nroserie="X";
    String c_placa="PERMISO";  
    String c_serie="FA";
    String c_impuesto="IVA";
    String c_tasa="16.00";
    String c_tipopoliza="autos";
    String c_campNum1="0.00";
    String c_campNum2="0.00";
    String c_idioma="ES";
      
    //Esta parte se modifica
      
    //factura  
    String c_folio=""; 
    String c_fecha="2012-02-06T16:00:43";
  
    String c_formaDePago="CREDITO"; 	
    String c_subTotal="668.00"; 	
    String c_descuento="0"; 
    String c_total="774.88"; 
    String c_totalLetra="SETECIENTOS SESENTA Y CUATRO PESOS CON 88/100 M.N.";
    String c_tipoDeComprobante="INGRESO"; 	
    String c_noCertificado="00001000000102527347"; 
    String c_importe="106.88";
    String c_sello="";
    String c_certificado="";
    String c_regimenfiscal="ACTIVIDADES EMPRESARIALES REGIMEN GENERAL DE LEY";
    String c_selloSAT="";
    String c_noCertificadoSAT = "";
    String c_fechaTimbrado="";
    String c_uuid="";
       
    //factura
    
    //poliza
    String c_numero="4050356528";
    String c_inc="0001";
    String c_tpoCliente="0";
    String c_nroReporte="04120013201";
    String c_nroSint="0011457";        
    
    //poliza
    
    //auto
    String c_tipovehiculo="PARTICULAR";
    String c_marca="FORD";
    String c_modulo="EXPLORER";
    String c_ano="2001";      
    //auto
    
    //personalizados
    String c_campFec1="2012-02-06";     //fecha de emisiÃ³n de la factura
    String c_campFec2="2012-01-18";     //fecha de entrega de articulo
    //personalizados
    
    String c_metodoPago="noIdentificado";
    String c_lugarExpedicion="TOLUCA";
    String c_adfolio = "0000000000";
    String c_numCtaPago = "0000";
    // Esta parte se modifica
    


            

             
            
    public VAddendaCFDI()
    {
         

        GridLayout marco = new GridLayout(14,2);


        setLayout(marco);

    myFileListener= new MyFileListener(this);
    codigo1 = new JLabel("XML");
    campoTexto1 = new JTextField(20);
    // campoTexto1.setText("nada");
    campoTexto1.setEditable(false);

    add(codigo1);
    add(campoTexto1);


    descripcion2 = new JLabel("Número de póliza (ej. 4050356528):");
    campoTexto2 = new JTextField(40);
    add(descripcion2);
    add(campoTexto2);

    descripcion3 = new JLabel("INC (ej. 0001):");
    campoTexto3 = new JTextField(40);
    campoTexto3.setText("0001");
    add(descripcion3);
    add(campoTexto3);

    descripcion4 = new JLabel("Tipo de cliente (ej. 0 para asegurado, 1 para tercero):");
    campoTexto4 = new JTextField(40);
    campoTexto4.setText("0");
    add(descripcion4);
    add(campoTexto4);

    descripcion5 = new JLabel("Número de reporte Area(04) Año(12) reporte (ej. 04120013201):");
    campoTexto5 = new JTextField(40);
    add(descripcion5);
    add(campoTexto5);

    descripcion6 = new JLabel("Número de siniestro (04) Año(12) siniestro (ej. 04120011457):");
    campoTexto6 = new JTextField(40);
    campoTexto6.setText("0412");
    add(descripcion6);
    add(campoTexto6);

    descripcion7 = new JLabel("Tipo de vehículo (ej. PARTICULAR):");
    campoTexto7 = new JTextField(40);
    campoTexto7.setText("PARTICULAR");
    add(descripcion7);
    add(campoTexto7);

    descripcion8 = new JLabel("Marca (ej. FORD):");
    campoTexto8 = new JTextField(40);
    add(descripcion8);
    add(campoTexto8);

    descripcion9 = new JLabel("Modelo (ej. EXPLORER):");
    campoTexto9 = new JTextField(40);
    add(descripcion9);
    add(campoTexto9);

    descripcion10 = new JLabel("Año (ej. 2001):");
    campoTexto10 = new JTextField(40);
    add(descripcion10);
    add(campoTexto10);
    
    
    descripcion12 = new JLabel("Placas del vehículo (ej. LYK5580):");
    campoTexto12 = new JTextField(40);
    add(descripcion12);
    add(campoTexto12);
    
    descripcion13 = new JLabel("VIN (ej. 3VWRV09M08M622554):");
    campoTexto13 = new JTextField(40);
    add(descripcion13);
    add(campoTexto13);
    
    descripcion11 = new JLabel("Folio Electrónico (ej. 04120392765):");
    campoTexto11 = new JTextField(40);
    add(descripcion11);
    add(campoTexto11);


  //  descripcion12 = new JLabel("Total de la factura en letra (ej. SETECIENTOS SESENTA Y CUATRO PESOS CON 88/100 M.N.):");
  //  campoTexto12 = new JTextField(40);
  //  add(descripcion12);
  //  add(campoTexto12);

    generar = new JButton("Generar");
    generar.addActionListener(new ActionListener(){
            @Override
    public void actionPerformed(ActionEvent evento){
       if(campoTexto1.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(null,"No ha seleccionado ningún xml");
           return;
       }
       if(campoTexto2.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo de número de póliza está vacio"); return; }
       if(campoTexto3.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo de INC está vacio"); return;}
       if(campoTexto4.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo de tipo de cliente está vacio"); return;}
       if(campoTexto5.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo de número de reporte está vacio"); return;}
       if(campoTexto6.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo de número de siniestro está vacio"); return;}
       if(campoTexto7.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo  de tipo de vehículo está vacio"); return;}
       if(campoTexto8.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo  de marca está vacio"); return;}
       if(campoTexto9.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo  de modelo está vacio"); return;}
       if(campoTexto10.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo  de año está vacio"); return;}
        if(campoTexto11.getText().isEmpty())       {
           JOptionPane.showMessageDialog(null,"El campo  de folio está vacio"); return;}


      

       c_numero = campoTexto2.getText(); //NÃºmero de pÃ³liza
       c_inc = campoTexto3.getText(); //INC
       c_tpoCliente = campoTexto4.getText(); //Tipo de cliente
       c_nroReporte = campoTexto5.getText(); //NÃºmero de reporte
       c_nroSint = campoTexto6.getText(); //NÃºmero de siniestro
       c_tipovehiculo = campoTexto7.getText(); //Tipo de vehÃ­culo
       c_marca = campoTexto8.getText(); //Marca
       c_modulo = campoTexto9.getText(); //Modelo
       c_ano = campoTexto10.getText(); //AÃ±o
       c_adfolio = campoTexto11.getText();
       c_placa = campoTexto12.getText();
       c_nroserie = campoTexto13.getText();

     //  campoTexto1.setText("");
       campoTexto2.setText("");
       campoTexto5.setText("");
       campoTexto6.setText("0413");
       campoTexto8.setText("");
       campoTexto9.setText("");
       campoTexto10.setText("");
       campoTexto11.setText("");
       campoTexto12.setText("");
       campoTexto13.setText("");
  

       crearXML(leerXML(campoTexto1.getText()));
       campoTexto1.setText("");
    }
    });
    add(generar);

    imprimir = new JButton("XML");
    add(imprimir);
    imprimir.addActionListener(myFileListener);
    }
    
    public org.jdom.Element leerXML(String direccion)
    {
        try{
          org.jdom.input.SAXBuilder builder=new org.jdom.input.SAXBuilder(false);
          org.jdom.Document doc = builder.build(direccion);
          org.jdom.Element raiz = doc.getRootElement();
          
          this.c_fecha=raiz.getAttributeValue("fecha"); //
      
          this.c_metodoPago=raiz.getAttributeValue("metodoDePago"); //
          this.c_formaDePago=raiz.getAttributeValue("formaDePago"); //
          this.c_subTotal=raiz.getAttributeValue("subTotal"); //
          this.c_moneda=raiz.getAttributeValue("Moneda"); //
          this.c_subTotal = this.c_subTotal.replace(",","");
          //   this.c_serie = raiz.getAttributeValue("serie"); //
          this.c_descuento=raiz.getAttributeValue("descuento");
          this.c_total=raiz.getAttributeValue("total"); //
          this.c_total = this.c_total.replace(",","");
          this.c_totalLetra="SETECIENTOS SESENTA Y CUATRO PESOS CON 88/100 M.N.";
          String cambioTipoComp = raiz.getAttributeValue("tipoDeComprobante").toLowerCase(); //
          this.c_tipoDeComprobante=cambioTipoComp;
          this.c_noCertificado=raiz.getAttributeValue("noCertificado"); //
          this.c_certificado = raiz.getAttributeValue("certificado"); //
          this.c_sello = raiz.getAttributeValue("sello"); //
        //  this.c_tipoCambio = raiz.getAttributeValue("TipoCambio"); //
          this.c_lugarExpedicion = raiz.getAttributeValue("LugarExpedicion"); //
          

          
       //  this.c_selloSAT=raiz.getChild("Complemento").getChild("TimbreFiscalDigital").getAttributeValue("selloSAT");// getChild("TimbreFiscalDigital",Namespace.getNamespace("http://www.sat.gob.mx/TimbreFiscalDigital")).getAttributeValue("selloSAT");          
         this.c_selloSAT=raiz.getChild("Complemento",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("TimbreFiscalDigital",Namespace.getNamespace("http://www.sat.gob.mx/TimbreFiscalDigital")).getAttributeValue("selloSAT");          
       //   this.c_noCertificadoSAT = raiz.getChild("Complemento").getChild("TimbreFiscalDigital").getAttributeValue("noCertificadoSAT");
          this.c_noCertificadoSAT = raiz.getChild("Complemento",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("TimbreFiscalDigital",Namespace.getNamespace("http://www.sat.gob.mx/TimbreFiscalDigital")).getAttributeValue("noCertificadoSAT");
       //   this.c_fechaTimbrado=raiz.getChild("Complemento").getChild("TimbreFiscalDigital").getAttributeValue("FechaTimbrado");
          this.c_fechaTimbrado=raiz.getChild("Complemento",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("TimbreFiscalDigital",Namespace.getNamespace("http://www.sat.gob.mx/TimbreFiscalDigital")).getAttributeValue("FechaTimbrado");
       // this.c_uuid=raiz.getChild("Complemento").getChild("TimbreFiscalDigital").getAttributeValue("UUID");
          this.c_uuid=raiz.getChild("Complemento",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("TimbreFiscalDigital",Namespace.getNamespace("http://www.sat.gob.mx/TimbreFiscalDigital")).getAttributeValue("UUID");
          
          hFacturasCFDI hfacturascfdi = new hFacturasCFDI();
          Factura facturas = hfacturascfdi.consultaUltimaFactura("uuid", "=", this.c_uuid);
          
          this.c_folio=String.valueOf(facturas.getFolio());//
      //    this.c_numCtaPago = raiz.getAttributeValue("NumCtaPago");
          this.c_receptor_nombre = raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("nombre");
          this.c_receptor_rfc=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("rfc");
          this.c_receptor_calle=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("calle");
          this.c_receptor_colonia=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("colonia");
          this.c_receptor_estado=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("estado");
         //  this.c_receptor_municipio=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("municipio"); 
          // this.c_receptor_municipio="CUAJIMALPA";
          this.c_receptor_noExterior=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("noExterior");
        //  this.c_receptor_nrointerior="0";
          this.c_receptor_pais=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("pais"); 
          this.c_receptor_codigoPostal=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("codigoPostal"); 
        //  this.c_receptor_localidad=raiz.getChild("Receptor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("Domicilio",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("localidad"); 
  
          
          
          c_emisor_nombre=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("nombre");
          c_emisor_rfc=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("rfc");
          c_emisor_calle=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("calle");
          c_emisor_codigoPostal=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("codigoPostal");
          c_emisor_colonia=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("colonia");
          c_emisor_estado=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("estado");
         c_emisor_localidad=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("localidad");
          c_emisor_municipio=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("municipio");
          c_emisor_noExterior=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("noExterior");
          c_emisor_noInterior=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("noInterior");
          c_emisor_pais=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("DomicilioFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("pais");  
          c_regimenfiscal=raiz.getChild("Emisor",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getChild("RegimenFiscal",Namespace.getNamespace("http://www.sat.gob.mx/cfd/3")).getAttributeValue("Regimen");   
          
        /* for (Object element : raiz.getChildren()) {
         System.out.println(element.toString());
         }  */
          
            // this.c_importe=
            //  raiz.getChild("Traslado").getText();
           Iterator child = raiz.getDescendants(new ElementFilter("Traslado"));
            while (child.hasNext()){
           org.jdom.Element e = (org.jdom.Element)child.next();
           this.c_importe=e.getAttributeValue("importe");
           this.c_importe = this.c_importe.replace(",","");
          }
          this.c_campFec1=c_fecha.substring(0,10);

       //  Iterator listaConceptos = raiz.getDescendants(new ElementFilter("Concepto"));
      /*    Iterator i = listaConceptos;
          while (i.hasNext()){
           org.jdom.Element e = (org.jdom.Element)i.next();
           System.out.println(e.getAttributeValue("descripcion"));
          } */
         return raiz;
         
        }catch(Exception e)
        {
            System.out.println("Entro aqui");
            e.printStackTrace(); 
        }
        return null;
    }       
    
    public void crearXML(org.jdom.Element raiz)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 try{
DocumentBuilder builder = factory.newDocumentBuilder();
DOMImplementation implementation = builder.getDOMImplementation();

Document document = implementation.createDocument(null, "XML", null);
// asignamos la version de nuestro XML
document.setXmlVersion("1.0");
 // creamos el elemento raiz
Element comprobante = document.createElement("cfdi:Comprobante");
comprobante.setAttribute("xmlns:cfdi", "http://www.sat.gob.mx/cfd/3");
comprobante.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
comprobante.setAttribute("xmlns:tfd", "http://www.sat.gob.mx/TimbreFiscalDigital");
comprobante.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd");
comprobante.setAttribute("version", "3.2");
// comprobante.setAttribute("serie", c_serie);
// comprobante.setAttribute("folio", c_folio);
comprobante.setAttribute("fecha", c_fecha);
comprobante.setAttribute("Moneda", c_moneda);
// comprobante.setAttribute("TipoCambio", c_tipoCambio);;
comprobante.setAttribute("formaDePago", c_formaDePago);
comprobante.setAttribute("LugarExpedicion", c_lugarExpedicion);
comprobante.setAttribute("metodoDePago", c_metodoPago);
comprobante.setAttribute("formaDePago", c_formaDePago);
comprobante.setAttribute("subTotal", c_subTotal);
comprobante.setAttribute("descuento", c_descuento);
comprobante.setAttribute("total", c_total);
comprobante.setAttribute("tipoDeComprobante", c_tipoDeComprobante);
comprobante.setAttribute("noCertificado", c_noCertificado);
comprobante.setAttribute("certificado", c_certificado);
comprobante.setAttribute("sello", c_sello); 
// comprobante.setAttribute("NumCtaPago",c_numCtaPago);


Element emisor = document.createElement("cfdi:Emisor");
emisor.setAttribute("nombre", c_emisor_nombre);
emisor.setAttribute("rfc", c_emisor_rfc);
//creamos un nuevo elemento domicilioFiscal
Element domicilioFiscal = document.createElement("cfdi:DomicilioFiscal");
domicilioFiscal.setAttribute("calle", c_emisor_calle);
domicilioFiscal.setAttribute("codigoPostal", c_emisor_codigoPostal);
domicilioFiscal.setAttribute("colonia", c_emisor_colonia);
domicilioFiscal.setAttribute("estado", c_emisor_estado);
domicilioFiscal.setAttribute("localidad", c_emisor_localidad);
domicilioFiscal.setAttribute("municipio", c_emisor_municipio);
domicilioFiscal.setAttribute("noExterior", c_emisor_noExterior);
domicilioFiscal.setAttribute("noInterior", c_emisor_noInterior);
c_emisor_pais = "MÉXICO";
domicilioFiscal.setAttribute("pais", c_emisor_pais);
//creamos un nuevo elemento expedidoEn
Element expedidoEn = document.createElement("cfdi:ExpedidoEn");
expedidoEn.setAttribute("calle", c_emisor_calle);
expedidoEn.setAttribute("codigoPostal", c_emisor_codigoPostal);
expedidoEn.setAttribute("colonia",  c_emisor_colonia);
expedidoEn.setAttribute("estado", c_emisor_estado);
expedidoEn.setAttribute("localidad", c_emisor_localidad);
expedidoEn.setAttribute("municipio", c_emisor_municipio);
expedidoEn.setAttribute("noExterior", c_emisor_noExterior);
expedidoEn.setAttribute("noInterior", c_emisor_noInterior);
expedidoEn.setAttribute("pais", c_emisor_pais);

Element regimenFiscal = document.createElement("cfdi:RegimenFiscal");
regimenFiscal.setAttribute("Regimen", c_regimenfiscal);

//creamos nuevo nodo receptor
Element receptor = document.createElement("cfdi:Receptor");
receptor.setAttribute("nombre", c_receptor_nombre);
receptor.setAttribute("rfc", c_receptor_rfc);
//creamos un nuevo elemento domicilioFiscal
Element domicilio = document.createElement("cfdi:Domicilio");
domicilio.setAttribute("calle", c_receptor_calle);
domicilio.setAttribute("codigoPostal", c_receptor_codigoPostal);
domicilio.setAttribute("colonia", c_receptor_colonia);
domicilio.setAttribute("estado", c_receptor_estado);
// domicilio.setAttribute("localidad", c_receptor_localidad);
// domicilio.setAttribute("municipio", c_receptor_municipio);
domicilio.setAttribute("noExterior", c_receptor_noExterior);
// domicilio.setAttribute("noInterior", "0");
domicilio.setAttribute("pais", c_receptor_pais);


//creamos nuevo nodo Conceptos
Element conceptos = document.createElement("cfdi:Conceptos");

/* while (iconcepto.hasNext()){
           org.jdom.Element e = (org.jdom.Element)i.next();
           System.out.println(e.getAttributeValue("descripcion"));
          } */
Iterator iconceptos = raiz.getDescendants(new ElementFilter("Concepto"));
 while (iconceptos.hasNext()){
org.jdom.Element e = (org.jdom.Element)iconceptos.next();
Element concepto = document.createElement("cfdi:Concepto");
concepto.setAttribute("cantidad",e.getAttributeValue("cantidad"));
concepto.setAttribute("descripcion", e.getAttributeValue("descripcion"));
concepto.setAttribute("noIdentificacion", e.getAttributeValue("noIdentificacion"));
String importeTemp = e.getAttributeValue("importe").replace(",", "");
concepto.setAttribute("importe",importeTemp);
String valorUn =  e.getAttributeValue("valorUnitario").replace(",", "");
concepto.setAttribute("valorUnitario",valorUn);
concepto.setAttribute("unidad", e.getAttributeValue("unidad"));
conceptos.appendChild(concepto);
    }

Element impuestos = document.createElement("cfdi:Impuestos");
impuestos.setAttribute("totalImpuestosTrasladados", c_importe);
Element traslados = document.createElement("cfdi:Traslados");
Element traslado = document.createElement("cfdi:Traslado");
Element traslado2 = document.createElement("cfdi:Traslado");
traslado.setAttribute("impuesto", c_impuesto);
traslado.setAttribute("tasa", c_tasa);
traslado.setAttribute("importe", c_importe); 
traslado2.setAttribute("impuesto", "IEPS");
traslado2.setAttribute("tasa",  "0.00");
traslado2.setAttribute("importe", "0.00"); 


Element complemento = document.createElement("cfdi:Complemento");
Element timbreFiscal = document.createElement("tfd:TimbreFiscalDigital");
timbreFiscal.setAttribute("version", "1.0");
timbreFiscal.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/TimbreFiscalDigital TimbreFiscalDigital.xsd");
timbreFiscal.setAttribute("selloSAT", c_selloSAT);
timbreFiscal.setAttribute("noCertificadoSAT", c_noCertificadoSAT);
timbreFiscal.setAttribute("selloCFD", c_sello);
timbreFiscal.setAttribute("FechaTimbrado", c_fechaTimbrado);
timbreFiscal.setAttribute("UUID", c_uuid);
complemento.appendChild(timbreFiscal);


Element addenda = document.createElement("cfdi:Addenda");
Element ecfd = document.createElement("ECFD");
ecfd.setAttribute("version", "1.0");
Element documentoAddenda = document.createElement("Documento");
documentoAddenda.setAttribute("ID", "T"+c_tipoaddenda+c_folio);


Element encabezado = document.createElement("Encabezado"); 

Element idDoc = document.createElement("IdDoc");  
Element nroAprob = document.createElement("NroAprob"); 
Text tnroAprob = document.createTextNode("00000");
nroAprob.appendChild(tnroAprob);
Element anoAprob = document.createElement("AnoAprob");  
Text tanoAprob = document.createTextNode("0000");
anoAprob.appendChild(tanoAprob);
Element tipo = document.createElement("Tipo");  
Text ttipo = document.createTextNode(c_tipoaddenda);
tipo.appendChild(ttipo);
/* Element serie = document.createElement("Serie");  
Text tserie = document.createTextNode(c_serie);
serie.appendChild(tserie); */
Element folio = document.createElement("Folio");  
Text tfolio = document.createTextNode(c_folio);
folio.appendChild(tfolio);
Element estado = document.createElement("Estado");  
Text testado = document.createTextNode(c_estadoOr);
estado.appendChild(testado);
Element numeroInterno = document.createElement("NumeroInterno");  
Text tnumeroInterno = document.createTextNode(c_numeroInterno);
numeroInterno.appendChild(tnumeroInterno);
Element fechaEmis = document.createElement("FechaEmis");  
Text tfechaEmis = document.createTextNode(c_fecha);
fechaEmis.appendChild(tfechaEmis);
Element formaPago = document.createElement("FormaPago");  
Text tformaPago = document.createTextNode(c_formaPago);
formaPago.appendChild(tformaPago);
 /* Element medioPago = document.createElement("MedioPago");  
Text tmedioPago = document.createTextNode(c_formaDePago);
medioPago.appendChild(tmedioPago); */
Element area = document.createElement("Area");  
Element idArea = document.createElement("IdArea");  
Text tidArea = document.createTextNode(c_idArea);
idArea.appendChild(tidArea);
Element idRevision = document.createElement("IdRevision");  
Text tidRevision = document.createTextNode(c_idRevision);
idRevision.appendChild(tidRevision);
area.appendChild(idArea);
area.appendChild(idRevision);


idDoc.appendChild(nroAprob); 
idDoc.appendChild(anoAprob);
idDoc.appendChild(tipo);
// idDoc.appendChild(serie);
idDoc.appendChild(folio);
idDoc.appendChild(estado);
idDoc.appendChild(numeroInterno);
idDoc.appendChild(fechaEmis);
idDoc.appendChild(formaPago);
// idDoc.appendChild(medioPago);
idDoc.appendChild(area);

Element exEmisor = document.createElement("ExEmisor"); 
Element rfcEmisor = document.createElement("RFCEmisor"); 
Text trfcEmisor = document.createTextNode(c_emisor_rfc);
rfcEmisor.appendChild(trfcEmisor);
Element nmbEmisor = document.createElement("NmbEmisor");  
Text tnmbEmisor = document.createTextNode(c_emisor_nombre);
nmbEmisor.appendChild(tnmbEmisor);

exEmisor.appendChild(rfcEmisor);
exEmisor.appendChild(nmbEmisor);

/*
 *                                       <CodigoExEmisor>
                                      <TpoCdgIntEmisor>EXT</TpoCdgIntEmisor>
                                      <CdgIntEmisor>03870</CdgIntEmisor>
                                       </CodigoExEmisor>
 *
 */

Element codigoExEmisor = document.createElement("CodigoExEmisor");
Element tpoCdgIntEmisor = document.createElement("TpoCdgIntEmisor");
Element cdgIntExEmisor = document.createElement("CdgIntEmisor");

Text ttpoCdgIntEmisor = document.createTextNode("EXT");
Text tcdgIntExEmisor  = document.createTextNode("03870");

tpoCdgIntEmisor.appendChild(ttpoCdgIntEmisor);
cdgIntExEmisor.appendChild(tcdgIntExEmisor);

codigoExEmisor.appendChild(tpoCdgIntEmisor);
codigoExEmisor.appendChild(cdgIntExEmisor);

exEmisor.appendChild(codigoExEmisor);


Element domFiscal = document.createElement("DomFiscal"); 
Element calle = document.createElement("Calle"); 
Text tcalle = document.createTextNode(c_emisor_calle);
calle.appendChild(tcalle);
Element nroExterior = document.createElement("NroExterior");  
Text tnroExterior = document.createTextNode(c_emisor_noExterior);
nroExterior.appendChild(tnroExterior);
Element nroInterior = document.createElement("NroInterior"); 
Text tnroInterior = document.createTextNode(c_emisor_noInterior);
nroInterior.appendChild(tnroInterior);
Element colonia = document.createElement("Colonia");  
Text tcolonia = document.createTextNode(c_emisor_colonia);
colonia.appendChild(tcolonia);
Element localidad = document.createElement("Localidad"); 
Text tlocalidad = document.createTextNode(c_emisor_localidad);
localidad.appendChild(tlocalidad);
Element municipio = document.createElement("Municipio");  
Text tmunicipio = document.createTextNode(c_emisor_municipio);
municipio.appendChild(tmunicipio);
Element emiestado = document.createElement("Estado"); 
Text temiestado = document.createTextNode(c_emisor_estado);
emiestado.appendChild(temiestado);
Element pais = document.createElement("Pais");  
Text tpais = document.createTextNode(c_emisor_pais);
pais.appendChild(tpais);
Element codigoPostal = document.createElement("CodigoPostal");  
Text tcodigoPostal = document.createTextNode(c_emisor_codigoPostal);
codigoPostal.appendChild(tcodigoPostal);

domFiscal.appendChild(calle); 
domFiscal.appendChild(nroExterior);
domFiscal.appendChild(nroInterior);
domFiscal.appendChild(colonia);
// domFiscal.appendChild(localidad);
domFiscal.appendChild(municipio);
domFiscal.appendChild(emiestado);
domFiscal.appendChild(pais);
domFiscal.appendChild(codigoPostal);
exEmisor.appendChild(domFiscal);


/*

 * <ContactoEmisor>

<Tipo>matriz</Tipo>
<Nombre>Hortencia Vilchis</Nombre>
<eMail>comsatoluca@hotmail.com</eMail>
<Telefono>(722)2191666</Telefono>
</ContactoEmisor>

 */
Element contEmisor = document.createElement("ContactoEmisor");
Element contEmisorTipo = document.createElement("Tipo");
Element contEmisorNombre = document.createElement("Nombre");
Element contEmisorEmail = document.createElement("eMail");
Element contEmisorTelefono = document.createElement("Telefono");

Text tcontEmisorTipo = document.createTextNode("coordinador");
Text tcontEmisorNombre = document.createTextNode("RODOLFO PEREZ");
Text tcontEmisorEmail = document.createTextNode("autopartes_toluca@hotmail.com");
Text tcontEmisorTelefono = document.createTextNode("(722)4894003");

contEmisorTipo.appendChild(tcontEmisorTipo);
contEmisorNombre.appendChild(tcontEmisorNombre);
contEmisorEmail.appendChild(tcontEmisorEmail);
contEmisorTelefono.appendChild(tcontEmisorTelefono);

contEmisor.appendChild(contEmisorTipo);
contEmisor.appendChild(contEmisorNombre);
contEmisor.appendChild(contEmisorEmail);
contEmisor.appendChild(contEmisorTelefono);

exEmisor.appendChild(contEmisor);

Element exReceptor = document.createElement("ExReceptor"); 
Element rfcReceptor = document.createElement("RFCRecep"); 
Text trfcReceptor = document.createTextNode(c_receptor_rfc);
rfcReceptor.appendChild(trfcReceptor);
Element nmbReceptor = document.createElement("NmbRecep");  
Text tnmbReceptor = document.createTextNode(c_receptor_nombre);
nmbReceptor.appendChild(tnmbReceptor);

exReceptor.appendChild(rfcReceptor);
exReceptor.appendChild(nmbReceptor);

Element domFiscalrcp = document.createElement("DomFiscalRcp"); 
Element callercp = document.createElement("Calle"); 
Text tcallercp = document.createTextNode(c_receptor_calle);
callercp.appendChild(tcallercp);
Element nroExteriorrcp = document.createElement("NroExterior");  
Text tnroExteriorrcp = document.createTextNode(c_receptor_noExterior);
nroExteriorrcp.appendChild(tnroExteriorrcp);
Element nroInteriorrcp = document.createElement("NroInterior"); 
Text tnroInteriorrcp = document.createTextNode(c_receptor_nrointerior);
nroInteriorrcp.appendChild(tnroInteriorrcp);
Element coloniarcp = document.createElement("Colonia");  
Text tcoloniarcp = document.createTextNode(c_receptor_colonia);
coloniarcp.appendChild(tcoloniarcp);
Element localidadrcp = document.createElement("Localidad"); 
Text tlocalidadrcp = document.createTextNode(c_receptor_localidad);
localidadrcp.appendChild(tlocalidadrcp);
Element municipiorcp = document.createElement("Municipio");  
Text tmunicipiorcp = document.createTextNode(c_receptor_municipio);
municipiorcp.appendChild(tmunicipiorcp);
Element emiestadorcp = document.createElement("Estado"); 
Text temiestadorcp = document.createTextNode(c_receptor_estado);
emiestadorcp.appendChild(temiestadorcp);
Element paisrcp = document.createElement("Pais");  
Text tpaisrcp = document.createTextNode(c_receptor_pais);
paisrcp.appendChild(tpaisrcp);
Element codigoPostalrcp = document.createElement("CodigoPostal");  
Text tcodigoPostalrcp = document.createTextNode(c_receptor_codigoPostal);
codigoPostalrcp.appendChild(tcodigoPostalrcp);


domFiscalrcp.appendChild(callercp); 
domFiscalrcp.appendChild(nroExteriorrcp);
domFiscalrcp.appendChild(nroInteriorrcp);
domFiscalrcp.appendChild(coloniarcp);
domFiscalrcp.appendChild(localidadrcp);
domFiscalrcp.appendChild(municipiorcp);
domFiscalrcp.appendChild(emiestadorcp);
domFiscalrcp.appendChild(paisrcp);
domFiscalrcp.appendChild(codigoPostalrcp);
exReceptor.appendChild(domFiscalrcp);


Element totales = document.createElement("Totales");  
Element moneda = document.createElement("Moneda");  
Element indlista = document.createElement("IndLista");  
Element subtotal = document.createElement("SubTotal");  
Element mntdocto = document.createElement("MntDcto");  
Element pctdcto = document.createElement("PctDcto");  
Element mntbase = document.createElement("MntBase");  
Element mntimp = document.createElement("MntImp");  
Element vlrpagar = document.createElement("VlrPagar");  
Element vlrpalabras = document.createElement("VlrPalabras");  
 
c_moneda="MXN";
Text tmoneda = document.createTextNode(c_moneda);
Text tindlista = document.createTextNode(c_tindlista);
Text tsubtotal = document.createTextNode(c_subTotal);
Text tmntdocto = document.createTextNode(c_mntdcto);
Text tpctdcto = document.createTextNode(c_mntbase);
Text tmntbase = document.createTextNode(c_subTotal);
Text tmntimp = document.createTextNode(c_importe);
Text tvlrpagar = document.createTextNode(c_total);
Numero_a_Letra numeroaletra = new Numero_a_Letra();
// Text tvlrpalabras = document.createTextNode(c_totalLetra);
Text tvlrpalabras = document.createTextNode(numeroaletra.Convertir(c_total, true));



moneda.appendChild(tmoneda);
indlista.appendChild(tindlista);
subtotal.appendChild(tsubtotal);
mntdocto.appendChild(tmntdocto);
pctdcto.appendChild(tpctdcto);
mntbase.appendChild(tmntbase);
mntimp.appendChild(tmntimp);
vlrpagar.appendChild(tvlrpagar);
vlrpalabras.appendChild(tvlrpalabras);

totales.appendChild(moneda); 
totales.appendChild(indlista);
totales.appendChild(subtotal);
totales.appendChild(mntdocto);
totales.appendChild(pctdcto);
totales.appendChild(mntbase);
totales.appendChild(mntimp);
totales.appendChild(vlrpagar);
totales.appendChild(vlrpalabras);

Element eximpuestos = document.createElement("ExImpuestos");  
Element tipoimp = document.createElement("TipoImp");  
Element tasaimp = document.createElement("TasaImp");  
Element montoImp = document.createElement("MontoImp");  


Text ttipoimp = document.createTextNode(c_impuesto);
Text ttasaimp = document.createTextNode(c_tasa);
Text tmontoImp = document.createTextNode(c_importe);

tipoimp.appendChild(ttipoimp);
tasaimp.appendChild(ttasaimp );
montoImp.appendChild(tmontoImp);

eximpuestos.appendChild(tipoimp); 
eximpuestos.appendChild(tasaimp);
eximpuestos.appendChild(montoImp);

/*

				<Poliza>
					<Tipo>autos</Tipo>
					<Numero>4050356528</Numero>
					<INC>0001</INC>
					<TpoCliente>0</TpoCliente>
					<NroReporte>04120013201</NroReporte>
					<NroSint>0011457</NroSint>
				</Poliza>
*/

Element poliza = document.createElement("Poliza");  
Element tipopoliza = document.createElement("Tipo");  
Element numero = document.createElement("Numero");  
Element inc = document.createElement("INC");  
Element tpocliente = document.createElement("TpoCliente");  
Element nroreporte = document.createElement("NroReporte");  
Element nrosint = document.createElement("NroSint");  

Text ttipopoliza= document.createTextNode(c_tipopoliza);
Text tnumero = document.createTextNode(c_numero);
Text tinc = document.createTextNode(c_inc);
Text ttpocliente = document.createTextNode(c_tpoCliente);
Text tnroreporte = document.createTextNode(c_nroReporte);
Text trosint = document.createTextNode(c_nroSint);


tipopoliza.appendChild(ttipopoliza);
numero.appendChild(tnumero);
inc.appendChild(tinc);
tpocliente.appendChild(ttpocliente);
nroreporte.appendChild(tnroreporte);
nrosint.appendChild(trosint);

poliza.appendChild(tipopoliza); 
poliza.appendChild(numero);
poliza.appendChild(inc);
poliza.appendChild(tpocliente); 
poliza.appendChild(nroreporte);
poliza.appendChild(nrosint);

/* 
 * 				<Vehiculo>
					<Tipo>PARTICULAR</Tipo>
					<Marca>FORD</Marca>
					<Modelo>EXPLORER</Modelo>
					<Ano>2001</Ano>
					<Color>X</Color>
					<NroSerie>X</NroSerie>
					<Placa>PERMISO</Placa>
				</Vehiculo>
 */

Element vehiculo = document.createElement("Vehiculo");  
Element tipovehiculo = document.createElement("Tipo");  
Element marca = document.createElement("Marca");  
Element modelo = document.createElement("Modelo");  
Element ano = document.createElement("Ano");  
Element color = document.createElement("Color");  
Element nroserie = document.createElement("NroSerie");  
Element placa = document.createElement("Placa"); 

Text ttipovehiculo= document.createTextNode(c_tipovehiculo);
Text tmarca = document.createTextNode(c_marca);
Text tmodelo = document.createTextNode(c_modulo);
Text tano = document.createTextNode(c_ano);
Text tcolor = document.createTextNode(c_color);
Text tnroserie = document.createTextNode(c_nroserie);
Text tplaca = document.createTextNode(c_placa);

tipovehiculo.appendChild(ttipovehiculo);
marca.appendChild(tmarca);
modelo.appendChild(tmodelo);
ano.appendChild(tano);
color.appendChild(tcolor);
nroserie.appendChild(tnroserie);
placa.appendChild(tplaca);

vehiculo.appendChild(tipovehiculo); 
vehiculo.appendChild(marca);
vehiculo.appendChild(modelo);
vehiculo.appendChild(ano); 
vehiculo.appendChild(color);
vehiculo.appendChild(nroserie);
vehiculo.appendChild(placa);



Element timestamp = document.createElement("TimeStamp"); 
Text ttimestamp= document.createTextNode(c_fecha);
timestamp.appendChild(ttimestamp); 

Element personalizados = document.createElement("Personalizados"); 
Element campNum1 = document.createElement("campoString");
Element campNum2 = document.createElement("campoString");
Element campFec1 = document.createElement("campoString");
Element campFec2 = document.createElement("campoString");
Element campTex1 = document.createElement("campoString");
Element campTex2 = document.createElement("campoString");
Element campTex3 = document.createElement("campoString");
Element campTex4 = document.createElement("campoString");
Element campTex5 = document.createElement("campoString");

campNum1.setAttribute("name", "montoManoObra");
campNum2.setAttribute("name", "montoRefacciones");
campFec1.setAttribute("name", "fechaFiniquito");
campFec2.setAttribute("name", "fechaEntregaRefacciones");
campTex1.setAttribute("name", "oficinaEntregaFactura");
campTex2.setAttribute("name", "folioElectronico");
campTex5.setAttribute("name", "UUID");
campTex3.setAttribute("name", "Default1");
campTex4.setAttribute("name", "Default2");

 Text tcampNum1= document.createTextNode(c_campNum1);
 Text tcampNum2= document.createTextNode(c_subTotal);
 Text tcampFec1= document.createTextNode(c_fecha);
 Text tcampFec2= document.createTextNode(c_fecha);
 Text tcampTex1= document.createTextNode("078");
 Text tcampTex2= document.createTextNode(c_adfolio);
 Text tcampTex5= document.createTextNode(c_uuid);
 Text tcampTex3= document.createTextNode("string");
 Text tcampTex4= document.createTextNode("string");

campNum1.appendChild(tcampNum1); 
campNum2.appendChild(tcampNum2);
campFec1.appendChild(tcampFec1);
campFec2.appendChild(tcampFec2);
campTex1.appendChild(tcampTex1);
campTex2.appendChild(tcampTex2);
campTex5.appendChild(tcampTex5);
campTex3.appendChild(tcampTex3);
campTex4.appendChild(tcampTex4);

personalizados.appendChild(campNum1); 
personalizados.appendChild(campNum2);
personalizados.appendChild(campFec1);
personalizados.appendChild(campFec2);
personalizados.appendChild(campTex1);
personalizados.appendChild(campTex2);
personalizados.appendChild(campTex5);
personalizados.appendChild(campTex3);
personalizados.appendChild(campTex4);

//Ingresamos la info
/* Text text = document.createTextNode("lala@lala.com"); Este sirve despues */

 //pegamos la raiz al documento
document.getDocumentElement().appendChild(comprobante); 
comprobante.appendChild(emisor);
emisor.appendChild(domicilioFiscal); 
emisor.appendChild(expedidoEn); 
emisor.appendChild(regimenFiscal);
comprobante.appendChild(receptor);
receptor.appendChild(domicilio); 
comprobante.appendChild(conceptos);
comprobante.appendChild(impuestos);
impuestos.appendChild(traslados);
// traslados.appendChild(traslado2);    
traslados.appendChild(traslado);
comprobante.appendChild(complemento);
comprobante.appendChild(addenda);
addenda.appendChild(ecfd); 
ecfd.appendChild(documentoAddenda); 
documentoAddenda.appendChild(encabezado); 
encabezado.appendChild(idDoc); 
encabezado.appendChild(exEmisor); 
encabezado.appendChild(exReceptor); 
encabezado.appendChild(totales); 
encabezado.appendChild(eximpuestos); 
encabezado.appendChild(poliza); 
encabezado.appendChild(vehiculo);

/*
 			<Detalle>
				<NroLinDet>1</NroLinDet>
				<CdgItem>
					<TpoCodigo>INT</TpoCodigo>
					<VlrCodigo>F87Z17682CAA</VlrCodigo>
				</CdgItem>
				<DscLang>ES</DscLang>
				<DscItem>ESPEJO EXPLORER 01 DER *ENE</DscItem>
				<QtyItem>1</QtyItem>
				<UnmdItem>PZ</UnmdItem>
				<PrcNetoItem>668.00</PrcNetoItem>
				<MontoNetoItem>668.00</MontoNetoItem>
			</Detalle>
 */

 int indice=0;
Iterator idetalles = raiz.getDescendants(new ElementFilter("Concepto"));
while (idetalles.hasNext()){
indice++;
org.jdom.Element e = (org.jdom.Element)idetalles.next();
Element detalle = document.createElement("Detalle");

Element nrolindet = document.createElement("NroLinDet");
Element dsclang = document.createElement("DscLang");
Element dscitem = document.createElement("DscItem");
Element qtyitem = document.createElement("QtyItem");
Element unmditem = document.createElement("UnmdItem");
Element prcnetoitem = document.createElement("PrcNetoItem");
Element montonetoitem = document.createElement("MontoNetoItem");
String valorUnita=e.getAttributeValue("valorUnitario").replace(",", "");
String cantidadFormateada = e.getAttributeValue("cantidad").replace(",", "");
Double montoTotal=0.0;
try{
double cantidad = Double.valueOf(cantidadFormateada);
montoTotal = Double.parseDouble(valorUnita) * (int)cantidad;
}catch(NumberFormatException nfe)
{

nfe.printStackTrace();    
    
montoTotal = Double.parseDouble(valorUnita) * Double.valueOf(cantidadFormateada);
}
DecimalFormat df = new DecimalFormat("0.00");

Text tnrolindet= document.createTextNode(String.valueOf(indice));
Text tdsclang= document.createTextNode(c_idioma);
Text tdscitem= document.createTextNode(e.getAttributeValue("descripcion"));
Text tqtyitem= document.createTextNode(e.getAttributeValue("cantidad"));
Text tunmditem= document.createTextNode(e.getAttributeValue("unidad"));
String precioNetoFormateado = e.getAttributeValue("valorUnitario").replace(",", "");
Text tprcnetoitem= document.createTextNode(precioNetoFormateado);
Text tmontonetoitem= document.createTextNode(df.format(montoTotal));


nrolindet.appendChild(tnrolindet);
dsclang.appendChild(tdsclang);
dscitem.appendChild(tdscitem);
qtyitem.appendChild(tqtyitem);
unmditem.appendChild(tunmditem);
prcnetoitem.appendChild(tprcnetoitem);
montonetoitem.appendChild(tmontonetoitem);

detalle.appendChild(nrolindet);


Element cdgitem = document.createElement("CdgItem");
Element tpocodigo = document.createElement("TpoCodigo");
Element vlrcodigo = document.createElement("VlrCodigo");

Text ttpocodigo= document.createTextNode("INT");
Text tvlrcodigo= document.createTextNode("sinCodigo");

tpocodigo.appendChild(ttpocodigo);
vlrcodigo.appendChild(tvlrcodigo);

cdgitem.appendChild(tpocodigo);
cdgitem.appendChild(vlrcodigo);

detalle.appendChild(cdgitem);

detalle.appendChild(dsclang);
detalle.appendChild(dscitem);
detalle.appendChild(qtyitem);
detalle.appendChild(unmditem);
detalle.appendChild(prcnetoitem);
detalle.appendChild(montonetoitem);
documentoAddenda.appendChild(detalle);
 }


documentoAddenda.appendChild(timestamp); 
ecfd.appendChild(personalizados); 


/* domicilioFiscal.appendChild(text);    Este sirve para despues */

Source source = new DOMSource(document);
//nombre del archivo
Result result = new StreamResult(new java.io.File("qualitas/qualitas"+c_folio+".xml").toURI().getPath());
Result console= new StreamResult(System.out);
Transformer transformer = TransformerFactory.newInstance().newTransformer();
transformer.transform(source, result);
transformer.transform(source, console);

/* XMLOutputter serial = new XMLOutputter();
serial.setFormat(Format.getPrettyFormat());
serial.output((org.jdom.Document)document, System.out); */



 File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("qualitas/qualitas"+c_folio+".xml");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea; //modificar esto...
         String nuevaLinea="";
         while((linea=br.readLine())!=null){
         nuevaLinea=linea.replaceAll("<XML>","");
         lineaFinal = nuevaLinea.replaceAll("</XML>","");
          }
         

      }
      catch(Exception e){
         System.out.println("Cayo aquí 1"); 
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta
         // una excepcion.
         try{
            if( null != fr ){
               fr.close();
            }
         }catch (Exception e2){
             System.out.println("Cayo aquí 2"); 
            e2.printStackTrace();
         }
      }

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("qualitas/qualitas"+c_folio+".xml");
            pw = new PrintWriter(fichero);
            pw.println(lineaFinal);

        } catch (Exception e) {
            System.out.println("Cayo aquí 3"); 
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
               System.out.println("Cayo aquí 4"); 
              e2.printStackTrace();
           }
        }
      

 }      catch (TransformerException ex) {
            Logger.getLogger(VAddendaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ParserConfigurationException ex) {
            Logger.getLogger(VAddendaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException e){
//error
   e.printStackTrace();
  // System.err.println("Error: "+e.getMessage());
   
 }
    }
    

     class MyFileListener implements ActionListener{
private JFileChooser chooser = new JFileChooser();
private JPanel frame;
public MyFileListener(JPanel frame){
this.frame = frame;
}

        private MyFileListener(VAddenda2 aThis) {
           System.err.println("Qualitas");
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

    campoTexto1.setText(selectedFile.getAbsolutePath());
}else{
return;
}
}
    }
 }