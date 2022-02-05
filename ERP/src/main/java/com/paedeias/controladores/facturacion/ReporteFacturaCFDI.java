/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

/**
 *
 * @author ALL
 */
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Factura;
import com.paedeias.identidades.Partidasfacturas;
import com.paedeias.vistas.VConfiguracion;
import com.tsp.interconecta.ws.WsArregloCancelacion;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

 
public class ReporteFacturaCFDI { 
    
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL_BASEDATOS=CGlobalConfig.getConexion();
    private static final int BLACK = 0xFF000000; // ARGB
    private static final int WHITE = 0xFFFFFFFF; // ARGB
    DecimalFormat df;
  //   static final String URL_BASEDATOS="jdbc:mysql://192.168.1.95:3306/llv";
    /**
     * @param args the command line arguments
     */

    public HashMap resultSetToHashMap(ResultSet rs) throws SQLException
    {
        HashMap row = new HashMap();
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        while(rs.next())
        {
            for (int i=1; i<=columns; i++){
            row.put(md.getColumnName(i), rs.getObject(i));
            }
        }
        return row;
    }
    
   public static BufferedImage generarQR(String contenido) throws Exception {
   QRCode q;
   q = new QRCode();

   Encoder.encode(contenido, ErrorCorrectionLevel.L, q);

   ByteMatrix m;
   m = q.getMatrix(); 

   int w = m.getWidth();
   int h = m.getHeight();

   // 4 pixels on all sides for the mandatory quiet zone.
   BufferedImage img;
   img = new BufferedImage(w + 8, h + 8, BufferedImage.TYPE_INT_ARGB);
   for (int x = 0; x < w; x++) {
    for (int y = 0; y < h; y++) {
     img.setRGB(x + 4, y + 4, m.get(x, y) != 0 ? BLACK : WHITE);
    }
   }
   return img;
  }

    public void crearReporte(long idFactura,String men1, String men2, String banco, String transferencia, int tipoCFD,String cadenaQR)
    {
    JasperReport jasperReport;
    JasperPrint jasperPrint;
    try
    {

        Connection conexion = null;
   //     Statement instruccion = null;
        ResultSet conjuntoResultados = null;

         Class.forName(CONTROLADOR); 
         conexion = DriverManager.getConnection(URL_BASEDATOS,"root","cheshire");
         

          HashMap row = new HashMap();
          row.put("id_factura",String.valueOf(idFactura)); //tenemos que conseguir el parámetro 1 del sistema
          row.put("logoFerrari",getClass().getResource("/mainicons/ferrari.gif"));
          row.put("emisorNombre",CGlobalConfig.getFactura_emisorNombre());
          row.put("emisorRfc",CGlobalConfig.getFactura_emisorRFC());
          row.put("emisorCalle",CGlobalConfig.getFactura_emisorCalle());
          row.put("emisorNumero",CGlobalConfig.getFactura_emisorNumero());
          row.put("emisorColonia",CGlobalConfig.getFactura_emisorColonia());
          row.put("emisorCP",CGlobalConfig.getFactura_emisorCP());
          row.put("emisorCiudad",CGlobalConfig.getFactura_emisorCiudad());
          row.put("emisorEstado",CGlobalConfig.getFactura_emisorEstado());
          row.put("emisorPais",CGlobalConfig.getFactura_emisorPais());
          
          row.put("expedidoCalle",CGlobalConfig.getFactura_experidoCalle());
          row.put("expedidoNumero",CGlobalConfig.getFactura_experidoNumero());
          row.put("expedidoColonia",CGlobalConfig.getFactura_experidoColonia());
          row.put("expedidoCP",CGlobalConfig.getFactura_experidoCP());
          row.put("expedidoCiudad",CGlobalConfig.getFactura_experidoCiudad());
          row.put("expedidoEstado",CGlobalConfig.getFactura_experidoEstado());
          row.put("expedidoPais",CGlobalConfig.getFactura_experidoPais());
          
          row.put("facturaMensaje1",men1);
          row.put("facturaMensaje2",men2);
          
          row.put("banco",banco);
          row.put("transferencia",transferencia);
 
          try {
          BufferedImage imagen = generarQR(cadenaQR);
          row.put("imagen", imagen);
              } catch (Exception ex) {
                Logger.getLogger(ReporteFactura.class.getName()).log(Level.SEVERE, null, ex);
              }

      if(tipoCFD==0)
      jasperReport = JasperCompileManager.compileReport("cfdi.jrxml");
      else
      jasperReport = JasperCompileManager.compileReport("nc_cfdi.jrxml");    
      jasperPrint = JasperFillManager.fillReport(
          jasperReport, row, conexion); //la parte del hashmap son los parámetros que se envian:
      

     /* jasperPrint = JasperFillManager.fillReport(
          jasperReport, row, new JREmptyDataSource()); */
      

      
      JasperExportManager.exportReportToPdfFile(
          jasperPrint, "pdfs/factura"+idFactura+"cfdi.pdf"); 
      
      conexion.close();          
      
    }
        catch (SQLException ex) {
            Logger.getLogger(ReporteFactura.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (ClassNotFoundException ex) {
            Logger.getLogger(ReporteFactura.class.getName()).log(Level.SEVERE, null, ex);
        }    catch (JRException e)
    {
      e.printStackTrace();
    } 

    }
    
     public void fill(String cadenaQR,int tipoCFD,int folio) throws JRException
  {
        InputStream inputStream = null;
        Reader reader = null;
        try {
            JasperPrint jasperPrint;
            long start = System.currentTimeMillis();
            Map params = new HashMap();
            
            File file = new File("reporteCFDI.xml");
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream,"ISO-8859-1");
            InputSource is = new InputSource(reader);
            is.setEncoding("ISO-8859-1");
            
            Document document = JRXmlUtils.parse(is);
            params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
            params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd");
            params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0.##");
            try {
                BufferedImage imagen = generarQR(cadenaQR);
                 params.put("imagen", imagen);
                     } catch (Exception ex) {
                       Logger.getLogger(ReporteFactura.class.getName()).log(Level.SEVERE, null, ex);
                     }
            if(tipoCFD==0)
            jasperPrint = JasperFillManager.fillReport("web_cfdi.jasper", params);
            else
            jasperPrint = JasperFillManager.fillReport("web_nc.jasper", params);
            
            JasperExportManager.exportReportToPdfFile(
                  jasperPrint, "pdfs/factura"+folio+"cfdi.pdf");
          //  System.err.println("Filling time : " + (System.currentTimeMillis() - start));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  }
     
     
        public void fillCancelacion(String fecha) throws JRException
  {
        InputStream inputStream = null;
        Reader reader = null;
        try {
            JasperPrint jasperPrint;
            long start = System.currentTimeMillis();
            Map params = new HashMap();
            
            File file = new File("reporteCancelacionCFDI.xml");
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream,"ISO-8859-1");
            InputSource is = new InputSource(reader);
            is.setEncoding("ISO-8859-1");
            
            Document document = JRXmlUtils.parse(is);
            params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
            params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd");
            params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0.##");
          
            jasperPrint = JasperFillManager.fillReport("cancelacion_cfdi.jasper", params);
            
            JasperExportManager.exportReportToPdfFile(
                  jasperPrint, "pdfs/cancelacion"+fecha+"cfdi.pdf");
          //  System.err.println("Filling time : " + (System.currentTimeMillis() - start));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  } 
     
     public void crearReporteCFDIWeb(Factura factura, List<Partidasfacturas> partidas, CFacturaCFDI xml,String nombrebanco, String nombreCantidadLetra, String nombreMotivo)
     {
        try {
            DocumentBuilderFactory  factory  = DocumentBuilderFactory.newInstance();  
            DocumentBuilder         builder  = factory.newDocumentBuilder();  
            Document                document = builder.newDocument();
            df = new DecimalFormat("0.00");
           
            
            Element configuracion = document.createElement("factura");
            Element general = document.createElement("general");
            
            Element emisorRFC = document.createElement("emisorRFC");
            emisorRFC.setTextContent(CGlobalConfig.getFactura_emisorRFC());
            general.appendChild(emisorRFC);
            
            Element emisorNombre = document.createElement("emisorNombre");
            emisorNombre.setTextContent(CGlobalConfig.getFactura_emisorNombre());
            general.appendChild(emisorNombre);
            
            Element emisorCalle = document.createElement("emisorCalle");
            emisorCalle.setTextContent(CGlobalConfig.getFactura_emisorCalle());
            general.appendChild(emisorCalle);
            
            Element emisorNumero = document.createElement("emisorNumero");
            emisorNumero.setTextContent(CGlobalConfig.getFactura_emisorNumero());
            general.appendChild(emisorNumero);
            
            Element emisorColonia = document.createElement("emisorColonia");
            emisorColonia.setTextContent(CGlobalConfig.getFactura_emisorColonia());
            general.appendChild(emisorColonia);
            
            Element emisorCP = document.createElement("emisorCP");
            emisorCP.setTextContent(CGlobalConfig.getFactura_emisorCP());
            general.appendChild(emisorCP);
            
            Element emisorCiudad = document.createElement("emisorCiudad");
            emisorCiudad.setTextContent(CGlobalConfig.getFactura_emisorCiudad());
            general.appendChild(emisorCiudad);
            
            Element emisorEstado = document.createElement("emisorEstado");
            emisorEstado.setTextContent(CGlobalConfig.getFactura_emisorEstado());
            general.appendChild(emisorEstado);
            
            Element emisorPais = document.createElement("emisorPais");
            emisorPais.setTextContent(CGlobalConfig.getFactura_emisorPais());
            general.appendChild(emisorPais);
            
            Element expedidoCalle = document.createElement("expedidoCalle");
            expedidoCalle.setTextContent(CGlobalConfig.getFactura_experidoCalle());
            general.appendChild(expedidoCalle);
            
            Element expedidoNumero = document.createElement("expedidoNumero");
            expedidoNumero.setTextContent(CGlobalConfig.getFactura_experidoNumero());
            general.appendChild(expedidoNumero);
            
            Element expedidoColonia = document.createElement("expedidoColonia");
            expedidoColonia.setTextContent(CGlobalConfig.getFactura_experidoColonia());
            general.appendChild(expedidoColonia);
            
            Element expedidoCP = document.createElement("expedidoCP");
            expedidoCP.setTextContent(CGlobalConfig.getFactura_experidoColonia());
            general.appendChild(expedidoCP);
            
            Element expedidoCiudad = document.createElement("expedidoCiudad");
            expedidoCiudad.setTextContent(CGlobalConfig.getFactura_experidoCiudad());
            general.appendChild(expedidoCiudad);
            
            Element expedidoEstado = document.createElement("expedidoEstado");
            expedidoEstado.setTextContent(CGlobalConfig.getFactura_experidoEstado());
            general.appendChild(expedidoEstado);
            
            Element expedidoPais = document.createElement("expedidoPais");
            expedidoPais.setTextContent(CGlobalConfig.getFactura_experidoPais());
            general.appendChild(expedidoPais);
            
            Element clienteNombre = document.createElement("clienteNombre");
            clienteNombre.setTextContent(xml.getRnombre());
            general.appendChild(clienteNombre);
            
            Element clienteRFC = document.createElement("clienteRFC");
            clienteRFC.setTextContent(xml.getRrfc());
            general.appendChild(clienteRFC);
            
            Element clienteCalle = document.createElement("clienteCalle");
            clienteCalle.setTextContent(xml.getRcalle());
            general.appendChild(clienteCalle);
            
            Element clienteNumeroExterior = document.createElement("clienteNumeroExterior");
            clienteNumeroExterior.setTextContent(xml.getRnoexterior());
            general.appendChild(clienteNumeroExterior);
            
            Element clienteNumeroInterior = document.createElement("clienteNumeroInterior");
            clienteNumeroInterior.setTextContent("-");
            general.appendChild(clienteNumeroInterior);
            
            Element clientePais = document.createElement("clientePais");
            clientePais.setTextContent(xml.getRpais().replace("é","\u00C9"));
            general.appendChild(clientePais);
            
            Element clienteColonia = document.createElement("clienteColonia");
            clienteColonia.setTextContent(xml.getRcolonia());
            general.appendChild(clienteColonia);
            
            Element clientePoblacion = document.createElement("clientePoblacion");
            clientePoblacion.setTextContent(xml.getRciudad());
            general.appendChild(clientePoblacion);
            
            Element clienteCodigoPostal = document.createElement("clienteCodigoPostal");
            clienteCodigoPostal.setTextContent(xml.getRcp());
            general.appendChild(clienteCodigoPostal);
            
            Element clienteEstado = document.createElement("clienteEstado");
            clienteEstado.setTextContent(xml.getRestado());
            general.appendChild(clienteEstado);
            
            Element banco = document.createElement("banco");
            banco.setTextContent(nombrebanco);
            general.appendChild(banco);
            
            Element numeroCuenta = document.createElement("numeroCuenta");
            numeroCuenta.setTextContent(xml.getNumCtaPago());
            general.appendChild(numeroCuenta);
            
            Element fechaEmision = document.createElement("fechaEmision");
            
            Date date;
            String formattedDate="";
            try {
                date = new SimpleDateFormat("yyyy-M-d:H:m:s").parse(factura.getFechaTimbrado());
                formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
            } catch (ParseException ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            fechaEmision.setTextContent(formattedDate);
            general.appendChild(fechaEmision);
            
            Element folio = document.createElement("folio");
            folio.setTextContent(factura.getFolio());
            general.appendChild(folio);
            
            Element numeroCertificadoSAT = document.createElement("numeroCertificadoSAT");
            numeroCertificadoSAT.setTextContent(factura.getCertificadoSAT());
            general.appendChild(numeroCertificadoSAT);
            
            Element numeroCertificado = document.createElement("numeroCertificado");
            numeroCertificado.setTextContent(factura.getNoCertificado());
            general.appendChild(numeroCertificado);
            
            Element metodoPago = document.createElement("metodoPago");
            metodoPago.setTextContent(xml.getMetodoDePago());
            general.appendChild(metodoPago);
            
            Element tipoComprobante = document.createElement("tipoComprobante");
            tipoComprobante.setTextContent(xml.getTipoDeComprobante());
            general.appendChild(tipoComprobante);
            
            Element folioInterno = document.createElement("folioInterno");
            folioInterno.setTextContent(factura.getFolioInterno());
            general.appendChild(folioInterno);
            
            Element regimenFiscal = document.createElement("regimenFiscal");
            regimenFiscal.setTextContent(xml.getRegimenfiscal());
            general.appendChild(regimenFiscal);
            
            Element facturaMensaje = document.createElement("facturaMensaje");
            facturaMensaje.setTextContent(CGlobalConfig.getFactura_mensaje1());
            general.appendChild(facturaMensaje);
            
            Element facturaMensaje2 = document.createElement("facturaMensaje2");
            facturaMensaje2.setTextContent(CGlobalConfig.getFactura_mensaje2());
            general.appendChild(facturaMensaje2);
            
            Element cadenaOriginal = document.createElement("cadenaOriginal");
            cadenaOriginal.setTextContent(factura.getCadenaCompleta());
            general.appendChild(cadenaOriginal);
            
            Element timbreFiscal = document.createElement("timbreFiscal");
            timbreFiscal.setTextContent(factura.getUuid());
            general.appendChild(timbreFiscal);
            
            Element cantidadLetra = document.createElement("cantidadLetra");
            cantidadLetra.setTextContent(nombreCantidadLetra);
            general.appendChild(cantidadLetra);
            
            Element selloDigital = document.createElement("selloDigital");
            selloDigital.setTextContent(factura.getSello());
            general.appendChild(selloDigital);
            
            Element selloSAT = document.createElement("selloSAT");
            selloSAT.setTextContent(factura.getSelloSAT());
            general.appendChild(selloSAT);
            
            Element motivo = document.createElement("motivo");
            motivo.setTextContent(nombreMotivo);
            general.appendChild(motivo);
            
            Element subtotal = document.createElement("subtotal");
            subtotal.setTextContent(xml.getSubtotal());
            general.appendChild(subtotal);
            
            Element descuento = document.createElement("descuento");
            descuento.setTextContent(xml.getDescuento());
            general.appendChild(descuento);
            
            Element iva = document.createElement("iva");
            iva.setTextContent(xml.getImptraslados());
            general.appendChild(iva);
            
            Element total = document.createElement("total");
            total.setTextContent(xml.getTotal());
            general.appendChild(total);
            
            configuracion.appendChild(general);
            
            for(int i=0; i<partidas.size(); i++)
            {
            Element partida = document.createElement("partidas");
            
            Element folio2 = document.createElement("folio");
            folio2.setTextContent(factura.getFolio());
            partida.appendChild(folio2);
            
            Element codigo = document.createElement("codigo");
            codigo.setTextContent(partidas.get(i).getCodigoArticulo());
            partida.appendChild(codigo);
            
            Element cantidad = document.createElement("cantidad");
            cantidad.setTextContent(String.valueOf(partidas.get(i).getCantidad()));
            partida.appendChild(cantidad);
            
            Element descripcion = document.createElement("descripcion");
            descripcion.setTextContent(partidas.get(i).getDescripcion());
            partida.appendChild(descripcion);
            
            Element unidad = document.createElement("unidad");
            unidad.setTextContent("PZA");
            partida.appendChild(unidad);
            
            Element precioUnitario = document.createElement("precioUnitario");
            precioUnitario.setTextContent(partidas.get(i).getPrecioUnitario());
            partida.appendChild(precioUnitario);
            
            Element importe = document.createElement("importe");
            importe.setTextContent(partidas.get(i).getImporte());
            partida.appendChild(importe);
                       
            
            configuracion.appendChild(partida);                
            }
            document.appendChild(configuracion);
          try {
                DOMUtils.nuevaConfig(document, new File("reporteCFDI.xml"));
            } catch (Exception ex) {
                Logger.getLogger(VConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }               

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
        public void crearCancelacionCFDIWeb(String vfecha, String vrfc, WsArregloCancelacion vuuid)
     {
        try {
            DocumentBuilderFactory  factory  = DocumentBuilderFactory.newInstance();  
            DocumentBuilder         builder  = factory.newDocumentBuilder();  
            Document                document = builder.newDocument();
            df = new DecimalFormat("0.00");
           
            
            Element configuracion = document.createElement("cancelacion");
            Element partidas = document.createElement("partidas");
            
            Element cancelaciongeneral = document.createElement("cancelar");
            cancelaciongeneral.setTextContent("1");
            configuracion.appendChild(cancelaciongeneral);
            
            Element fecha = document.createElement("fecha");
            fecha.setTextContent(vfecha);
            configuracion.appendChild(fecha);
            
            Element rfc = document.createElement("rfc");
            rfc.setTextContent(vrfc);
            configuracion.appendChild(rfc);
           
            String selloSAT = "";
            try {
                Document selloAr = DOMUtils.cargarArchivoConfiguracion(new File("xmls/acusecanceladoSAT"+ vfecha +".xml"));
                selloSAT=selloAr.getElementsByTagName("SignatureValue").item(0).getTextContent();
            } catch (Exception ex) {
                Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
            }
              
            
            Element firma = document.createElement("selloSAT");
            firma.setTextContent(selloSAT);
            configuracion.appendChild(firma);
            
                 
            for(int i=0; i<vuuid.getArreglo().size(); i++)
            {
            
            Element parti = document.createElement("partida");    
                
            Element numcan = document.createElement("cancelar");
            numcan.setTextContent("1");
            
                
                
            Element folio2 = document.createElement("uuid");
            folio2.setTextContent(vuuid.getArreglo().get(i).getUUID());
            
            
            String status = "";
            if(vuuid.getArreglo().get(i).getEstatus() == 201)
            status = "Cancelado";
            else if(vuuid.getArreglo().get(i).getEstatus() == 202)
            status = "Previamente Cancelado";    
            else if(vuuid.getArreglo().get(i).getEstatus() == 203)
            status = "No corresponde al emisor";
            else if(vuuid.getArreglo().get(i).getEstatus() == 205)
            status = "El CFDI no ha sido presentado al SAT";
            
            Element codigo = document.createElement("status");
            codigo.setTextContent(status);
            
            parti.appendChild(numcan); 
            parti.appendChild(folio2);                
            parti.appendChild(codigo);                
            partidas.appendChild(parti);
            }
            
                      
       
            
            configuracion.appendChild(partidas);
            document.appendChild(configuracion);
          try {
                DOMUtils.nuevaConfig(document, new File("reporteCancelacionCFDI.xml"));
            } catch (Exception ex) {
                Logger.getLogger(VConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }               

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReporteFacturaCFDI.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
     
     public static void main (String args[]) throws JRException
     {
         ReporteFacturaCFDI prueba = new ReporteFacturaCFDI();
                 
     //    prueba.crearCancelacionCFDIWeb("2014_10_14T11_27_28", "LAVL8707071G0", null);
         prueba.fillCancelacion("2014_10_14T11_27_28");
     }
    
}

   /*         instruccion = conexion.createStatement();

            conjuntoResultados = instruccion.executeQuery("SELECT * FROM factura"); */
            

       /*     ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroDeColumnas = metaDatos.getColumnCount();
            System.out.println("Tabla de claves: \n");

            for (int i=1; i<=numeroDeColumnas; i++)
                System.out.printf("%-8s\t",metaDatos.getColumnName(i));
                System.out.println(); */

    /*        while (conjuntoResultados.next())
            {
                for(int i=1; i<=numeroDeColumnas; i++)
                    System.out.printf("%-8s\t",conjuntoResultados.getObject(i));
                    System.out.println();
            }
           */

   /*           HashMap row = new HashMap();
        ResultSetMetaData md = conjuntoResultados.getMetaData();
        int columns = md.getColumnCount();
        while(conjuntoResultados.next())
        {
            for (int i=1; i<=columns; i++){
            row.put(md.getColumnName(i), conjuntoResultados.getObject(i));
            System.out.println(md.getColumnName(i)+" "+conjuntoResultados.getObject(i));
            }
        } */
