/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

/**
 *
 * @author ALL
 */
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Partidasfacturas;
import java.io.File;  
import java.io.FileOutputStream;  
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
  
/** 
 * Clase de utilidad para el ejemplo 
 * @author Carlos García. Autentia. 
 * @see http://www.mobiletest.es 
 */  
public class DOMUtils {  
      
    /** 
     * Serializa un objeto Document en un archivo 
     */  
    public static void outputDocToFile(Document doc, File file) throws Exception {  
        FileOutputStream    f              = new FileOutputStream(file);  
        TransformerFactory factory         = TransformerFactory.newInstance();  
        Transformer        transformer     = factory.newTransformer();  
          
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");    
        transformer.transform(new DOMSource(doc), new StreamResult(f));  
  
        f.close();  
    }  
    
        public static void nuevaConfig(Document doc, File file) throws Exception {  
        FileOutputStream    f              = new FileOutputStream(file);  
        TransformerFactory factory         = TransformerFactory.newInstance();  
        Transformer        transformer     = factory.newTransformer();  
        
        
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");    
        transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");    
        transformer.setOutputProperty(OutputKeys.VERSION,"1.0");    
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(f));  
  
        f.close();  
    }  
      
    /** 
     * Lee un Document desde un archivo 
     */  
    public static Document loadDocumentFromFile(java.io.File file) throws Exception {  
        DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder         builder = null;  
          
        factory.setNamespaceAware(true);  
          
        builder = factory.newDocumentBuilder();  
          
        return builder.parse(file);  
    }   
    
        public static Document cargarArchivoConfiguracion(java.io.File file) throws Exception {  
        DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder         builder = null;  
          
      //  factory.setNamespaceAware(true);  <
          
        builder = factory.newDocumentBuilder();  
          
        return builder.parse(file);  
    }   
      
    /** 
     * @return Crea un elemento <tag>value</tag> 
     */  
    public static Element createNode(Document document, String tag, String value){  
        Element node = document.createElement(tag);  
        if (value != null){  
            node.appendChild(document.createTextNode(value));  
        }  
        return node;  
    }    
      
    /** 
     * @return Devuelve un Document a firmar 
     * @throws Exception Cualquier incidencia 
     */  
    public static Document createSampleDocument() throws Exception {  
        DocumentBuilderFactory  factory  = DocumentBuilderFactory.newInstance();  
        DocumentBuilder         builder  = factory.newDocumentBuilder();  
        Document                document = builder.newDocument();  
          
        Element cancelacion = document.createElement("Cancelacion");  
        cancelacion.setAttribute("RfcEmisor", "AAA010101AAA");  
        cancelacion.setAttribute("Fecha", "2012-12-06T10:14:56");

        cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://cancelacfd.sat.gob.mx");
  
        
        Element folios = document.createElement("Folios");
        Element UUID = document.createElement("UUID");
        UUID.appendChild(document.createTextNode("DC1C391D-8A77-4C71-C5D1-34A780BBF986"));
        folios.appendChild(UUID);
        cancelacion.appendChild(folios);
          
      /*  person.appendChild(DOMUtils.createNode(document, "Folios",   "Pepito"));  
        person.appendChild(DOMUtils.createNode(document, "apellidos", "Pérez Luna"));  
        person.appendChild(DOMUtils.createNode(document, "email",    "pepito.perez@servidor.com"));  */
  
        document.appendChild(cancelacion);  
  
        return document;  
    }     
    
    public static Document construirCancelacion(List<String> uuid)
    {
        try {
     DocumentBuilderFactory  factory  = DocumentBuilderFactory.newInstance();  
     DocumentBuilder         builder  = factory.newDocumentBuilder();  
     Document                document = builder.newDocument();  
     
     java.util.Date utilDate = new java.util.Date();
     long milisec = utilDate.getTime();
     Timestamp time= new Timestamp(milisec);    
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
     String fecha = sdf.format(time);  
       
     Element cancelacion = document.createElement("Cancelacion");  
     cancelacion.setAttribute("RfcEmisor", CGlobalConfig.getFactura_emisorRFC());  
     cancelacion.setAttribute("Fecha", fecha);

     cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
     cancelacion.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://cancelacfd.sat.gob.mx");

     
     Element folios = document.createElement("Folios");
     for(int i=0; i<uuid.size(); i++)
     {
     Element UUID = document.createElement("UUID");
     UUID.appendChild(document.createTextNode(uuid.get(i)));
     folios.appendChild(UUID);
     }
     cancelacion.appendChild(folios);
       
   /*  person.appendChild(DOMUtils.createNode(document, "Folios",   "Pepito"));  
     person.appendChild(DOMUtils.createNode(document, "apellidos", "Pérez Luna"));  
     person.appendChild(DOMUtils.createNode(document, "email",    "pepito.perez@servidor.com"));  */

     document.appendChild(cancelacion);  

     return document;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public static Document construirCFDI(CFactura doc,List <Partidasfacturas> partidas) throws Exception {  
        DocumentBuilderFactory  factory  = DocumentBuilderFactory.newInstance();  
        DocumentBuilder         builder  = factory.newDocumentBuilder();  
        Document                document = builder.newDocument();  
          
        Element comprobante = document.createElement("Comprobante");  
        comprobante.setAttribute("version", doc.getVersion());  
     //   comprobante.setAttribute("folio", doc.getFolio());
        comprobante.setAttribute("fecha", doc.getFecha()); //FECHA
        comprobante.setAttribute("sello", doc.getSello());
        comprobante.setAttribute("certificado", doc.getCertificado());
        comprobante.setAttribute("noCertificado", doc.getNoaprobacion());
        comprobante.setAttribute("formaDePago", doc.getFormapago());
        comprobante.setAttribute("subTotal", doc.getSubtotal());
        comprobante.setAttribute("descuento", doc.getDescuento());
        comprobante.setAttribute("Moneda", "MXN");
        comprobante.setAttribute("total", doc.getTotal());
        comprobante.setAttribute("tipoDeComprobante", doc.getTipoDeComprobante());
        comprobante.setAttribute("metodoDePago", doc.getFormapago().toLowerCase()); 
        comprobante.setAttribute("LugarExpedicion", "Toluca"); //lugar de expedición
        comprobante.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3  http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd");
        comprobante.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cfdi", "http://www.sat.gob.mx/cfd/3");
        comprobante.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:tfd", "http://www.sat.gob.mx/TimbreFiscalDigital");
        comprobante.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//        comprobante.setAttributeNS("http://www.w3.org/2000/xmlns/", "xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3  http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd");
  
        
        Element emisor = document.createElement("Emisor");
        emisor.setAttribute("nombre", doc.getEnombre());
        emisor.setAttribute("rfc", doc.getErfc());
        
        Element domicilioFiscal  = document.createElement("DomicilioFiscal");
        domicilioFiscal.setAttribute("codigoPostal", doc.getEecp());
        domicilioFiscal.setAttribute("pais", doc.getEepais());
        domicilioFiscal.setAttribute("estado", doc.getEeestado());
        domicilioFiscal.setAttribute("municipio", doc.getEemunicipio());
        domicilioFiscal.setAttribute("localidad", doc.getEelocalidad());
        domicilioFiscal.setAttribute("colonia", doc.getEecolonia());
        domicilioFiscal.setAttribute("noInterior", "0");
        domicilioFiscal.setAttribute("noExterior", doc.getEenoexterior());
        domicilioFiscal.setAttribute("calle", doc.getEecalle());
        
        Element expedidoEn  = document.createElement("ExpedidoEn");
        expedidoEn.setAttribute("codigoPostal", doc.getEecp());
        expedidoEn.setAttribute("pais", doc.getEepais());
        expedidoEn.setAttribute("estado", doc.getEeestado());
        expedidoEn.setAttribute("municipio", doc.getEemunicipio());
        expedidoEn.setAttribute("localidad", doc.getEelocalidad());
        expedidoEn.setAttribute("colonia", doc.getEecolonia());
        expedidoEn.setAttribute("noInterior", "0");
        expedidoEn.setAttribute("noExterior", doc.getEenoexterior());
        expedidoEn.setAttribute("calle", doc.getEecalle());
        
        Element regimenFiscal  = document.createElement("RegimenFiscal");
        regimenFiscal.setAttribute("Regimen", doc.getRegimenfiscal());

        emisor.appendChild(domicilioFiscal);
        emisor.appendChild(expedidoEn);
        emisor.appendChild(regimenFiscal);
        
        Element receptor  = document.createElement("Receptor");
        receptor.setAttribute("nombre", doc.getRnombre());
        receptor.setAttribute("rfc", doc.getRrfc());
       
        Element domicilio  = document.createElement("Domicilio");
        domicilio.setAttribute("codigoPostal", doc.getRcp());
        domicilio.setAttribute("pais", doc.getRpais());
        domicilio.setAttribute("estado", doc.getRestado());
        domicilio.setAttribute("colonia", doc.getRcolonia());
        domicilio.setAttribute("noExterior", doc.getRnoexterior());
        domicilio.setAttribute("calle", doc.getRcalle());
        
        receptor.appendChild(domicilio);
        
        Element conceptos  = document.createElement("Conceptos");
        for(int i=0; i<partidas.size(); i++)
        {
        Element concepto = document.createElement("Concepto");
        concepto.setAttribute("importe", String.valueOf(partidas.get(i).getImporte()));
        concepto.setAttribute("valorUnitario", String.valueOf(partidas.get(i).getPrecioUnitario()));
        concepto.setAttribute("descripcion", partidas.get(i).getDescripcion());
        concepto.setAttribute("noIdentificacion", partidas.get(i).getCodigoArticulo());
        concepto.setAttribute("unidad", "PZA");
        concepto.setAttribute("cantidad", String.valueOf(partidas.get(i).getCantidad()));
        
        conceptos.appendChild(concepto);
        }
        
        Element impuestos  = document.createElement("Impuestos");
        impuestos.setAttribute("totalImpuestosTrasladados", doc.getImptraslados());
        Element traslados = document.createElement("Traslados");
        Element traslado = document.createElement("Traslado");
        
        traslado.setAttribute("importe", doc.getImptraslados());
        traslado.setAttribute("tasa", "16.00");
        traslado.setAttribute("impuesto", "IVA");

        traslados.appendChild(traslado);
        impuestos.appendChild(traslados);
        
        Element complemento = document.createElement("Complemento");
        Element timbreFiscalDigital = document.createElement("TimbreFiscalDigital");
        // complemento.setAttributeNS("http://www.w3.org/2000/xmlns/", "xsi:schemaLocation", "http://www.sat.gob.mx/TimbreFiscalDigital TimbreFiscalDigital.xsd");
        timbreFiscalDigital.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/TimbreFiscalDigital TimbreFiscalDigital.xsd");
        timbreFiscalDigital.setAttribute("version", "1.0");
        timbreFiscalDigital.setAttribute("UUID", doc.getUuid());
        timbreFiscalDigital.setAttribute("noCertificadoSAT", CGlobalConfig.getFactura_certificado());
        timbreFiscalDigital.setAttribute("FechaTimbrado", doc.getFechaTimbrado());
        timbreFiscalDigital.setAttribute("selloCFD", doc.getSello());
        timbreFiscalDigital.setAttribute("selloSAT", doc.getSelloSAT());

        complemento.appendChild(timbreFiscalDigital);
        
        comprobante.appendChild(emisor);
        comprobante.appendChild(receptor);
        comprobante.appendChild(conceptos);
        comprobante.appendChild(impuestos);
        comprobante.appendChild(complemento);
          
  
        document.appendChild(comprobante);  
  
        return document;  
    }     
    
    
}  