/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Partidas;
import com.paedeias.identidades.Partidasdevoluciones;
import com.paedeias.identidades.Partidasfacturas;
import com.tsp.interconecta.ws.InterconectaWs;
import com.tsp.interconecta.ws.InterconectaWsService;
import com.tsp.interconecta.ws.WsGenericResp;
import java.io.*;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.bigdata.sat.security.KeyLoader;

/**
 *
 * @author ALL
 */


/*
 * CREAR CERTIFICADO .JKS (JAVA KEY STORE)
 * openssl x509 -inform DER -in certificado.cer -out certificado.pem
 * openssl pkcs8 -inform DER -in llave.key -passin pass:a0123456789 -out llave.pem
 * Luego los unes en un paquete pkcs12 con clave:
 * openssl pkcs12 -export -out archivopfx.pfx -inkey llave.pem -in certificado.pem -passout pass:clavedesalida
 * 
 * openssl pkcs12 -in cert.pfx -out cert.pem
 * openssl pkcs12 -export -in cert.pem -out cert.p12 -name "MiCertificado"
 * keytool -importkeystore -deststorepass 123456 -destkeypass 123456 -destkeystore MiAlmacen.jks -srckeystore cert.p12 -srcstoretype PKCS12 -srcstorepass 123456 -alias "MiCertificado"
 * 
 * 
 * http://www.javamexico.org/foros/java_enterprise/importar_clave_privada_y_certificado_en_la_java_keystore_mediante_keytool
 * http://www.validacfd.com/phpbb3/viewtopic.php?f=5&t=553
 * 
 */
public class CFacturaCFDI {
    private String version;
    private String folio = "";
    private String noaprobacion = "";
    private String anoaprobacion = "";
    private String formapago = "";
    private String subtotal = "";
    private String total = "";
    private String descuento = "";
    private String tipoDeComprobante="";
    private String metodoDePago="";
    private String enombre;
    private String erfc;
    private String uecalle;
    private String uecp;
    private String uecolonia;
    private String ueestado;
    private String uelocalidad;
    private String uenoexterior;
    private String uemunicipio;
    private String eecalle;
    private String eecp;
    private String eecolonia;
    private String eelocalidad;
    private String eemunicipio;
    private String eeestado;
    private String eenoexterior;
    private String uepais;
    private String eepais;
    private String rpais;
    private String rnombre;
    private String rrfc;
    private String rcalle;
    private String rcp;
    private String rciudad;
    private String rcolonia;
    private String restado;
    private String rnoexterior;
    private String imptraslados;
    private String importe;
    private String regimenfiscal;
    private String certificado;
    private String sello;
    private String cadenaOriginal;
    private String numCtaPago;
    private String versionTimbre;
    private String uuid;
    private String fechaTimbrado;
    private String nocerSAT;
    private String selloSAT;
    private int numError;
    private String mensajeError;
    private byte[] respuestaPAC;
    
    public void Sellar(List<Partidasfacturas> partidas,InterconectaWsService wss){
               // TODO add your handling code here:
          try {
          CFDI32 cfdfac = new CFDI32();  //necesitamos llenar este objeto en caso de ser cfdv2
                                      //necesitamos la lista de las partidas
          cfdfac.setAnoaprobacion(anoaprobacion);
          cfdfac.setDescuento(descuento);
          cfdfac.setEecalle(eecalle);
          cfdfac.setEecolonia(eecolonia);
          cfdfac.setEecp(eecp);
          cfdfac.setEeestado(eeestado);
          cfdfac.setEelocalidad(eelocalidad);
          cfdfac.setEemunicipio(eemunicipio);
          cfdfac.setEenoexterior(eenoexterior);
          cfdfac.setEepais(eepais);
          cfdfac.setEnombre(enombre);
          cfdfac.setErfc(erfc);
          cfdfac.setFolio(folio);
          cfdfac.setMetodoDePago(metodoDePago);
          cfdfac.setNumCtaPago(numCtaPago);
          cfdfac.setFormapago(formapago);
          cfdfac.setImporte(importe);
          cfdfac.setImptraslados(imptraslados);
          cfdfac.setNoaprobacion(noaprobacion);
          cfdfac.setRcalle(rcalle);
          cfdfac.setRcolonia(rcolonia);
          cfdfac.setRcp(rcp);
          cfdfac.setRestado(restado);
          cfdfac.setRnoexterior(rnoexterior);
          cfdfac.setRnombre(rnombre);
          cfdfac.setRpais(rpais);
          cfdfac.setRrfc(rrfc);
          cfdfac.setSubtotal(subtotal);
          cfdfac.setTipoDeComprobante(tipoDeComprobante);
          cfdfac.setTotal(total);
          cfdfac.setUecalle(uecalle);
          cfdfac.setUecolonia(uecolonia);
          cfdfac.setUecp(uecp);
          cfdfac.setUeestado(ueestado);
          cfdfac.setUelocalidad(uelocalidad);
          cfdfac.setUemunicipio(uemunicipio);
          cfdfac.setUenoexterior(uenoexterior);
          cfdfac.setUepais(uepais);
          mx.bigdata.sat.cfdi.v32.schema.Comprobante comp = cfdfac.createComprobante(partidas); 
          comp.setVersion("3.2");
          mx.bigdata.sat.cfdi.CFDv32 cfdi = new mx.bigdata.sat.cfdi.CFDv32(comp,"com.paedeias.controladores.facturacion"); // Crea un CFDI a partir de un InputStream
          X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(CGlobalConfig.getDireccion_certificado()));
          PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(CGlobalConfig.getDireccion_key()),  CGlobalConfig.getContrasena_key());
    
          mx.bigdata.sat.cfdi.v32.schema.Comprobante sellado = cfdi.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
          cfdi.validar(); // Valida el XML, que todos los elementos estén presentes
          cfdi.verificar(); // Verifica un CFD ya firmado
          FileOutputStream fos = new FileOutputStream("xmls/fac.xml");
          cfdi.guardar(fos); // Serializa el CFD a un OutputStream
          setSello(sellado.getSello());
          setCertificado(sellado.getCertificado());
          setCadenaOriginal(cfdi.getCadenaOriginal());   
          
           FileInputStream fis = new FileInputStream("xmls/fac.xml");
    
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
     byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
          //      System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Logger.getLogger(CFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = bos.toByteArray();
 
    bos.flush();
    bos.close();
    fis.close();
    
     String usuario = CGlobalConfig.getWsUsuario();
     String password = CGlobalConfig.getWsPassword();    
    
    InterconectaWs ws = wss.getInterconectaWsPort();
    WsGenericResp resp = ws.timbraEnviaCFDIBytes(usuario, password, null, null, null, bytes); 
     
     
     System.out.println(resp.getNumError());  
     System.out.println(resp.getErrorMessage());  
     setNumError(resp.getNumError());
     setMensajeError(resp.getErrorMessage());
     
    
    if(resp.getNumError() == 0)
     {
    setVersionTimbre("1.0");
    setUuid(resp.getFolioUUID());
    setFechaTimbrado(resp.getFechaHoraTimbrado().getYear() +"-"+resp.getFechaHoraTimbrado().getMonth()
             + "-"+resp.getFechaHoraTimbrado().getDay() + ":"+ resp.getFechaHoraTimbrado().getHour()+":"
             +resp.getFechaHoraTimbrado().getMinute()+":"+resp.getFechaHoraTimbrado().getSecond());
    setSelloSAT(resp.getSelloDigitalTimbreSAT());
    setRespuestaPAC(resp.getXML()); 
     } 
   
    
/*    setNumError(0);
    setMensajeError("sin error");
    setVersionTimbre("0.0");
    setUuid("0000000000000");
    setFechaTimbrado("0000" +"-"+"00"
             + "-"+"00" + ":"+ "00"+":"
             +"00"+":"+"00");
    setSelloSAT("0000000000000");
    setRespuestaPAC(new byte[20]);  
 */       
          
          
        
          
          
          } catch (Exception ex) {
            setNumError(1);
            setMensajeError("Error en datos");  
            Logger.getLogger(mx.bigdata.sat.cfdi.CFDI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
  
    }

    
    public String getAnoaprobacion() {
        return anoaprobacion;
    }

    public void setAnoaprobacion(String anoaprobacion) {
        this.anoaprobacion = anoaprobacion;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getEecalle() {
        return eecalle;
    }

    public void setEecalle(String eecalle) {
        this.eecalle = eecalle;
    }

    public String getEecolonia() {
        return eecolonia;
    }

    public void setEecolonia(String eecolonia) {
        this.eecolonia = eecolonia;
    }

    public String getEecp() {
        return eecp;
    }

    public void setEecp(String eecp) {
        this.eecp = eecp;
    }

    public String getEeestado() {
        return eeestado;
    }

    public void setEeestado(String eeestado) {
        this.eeestado = eeestado;
    }

    public String getEelocalidad() {
        return eelocalidad;
    }

    public void setEelocalidad(String eelocalidad) {
        this.eelocalidad = eelocalidad;
    }

    public String getEemunicipio() {
        return eemunicipio;
    }

    public void setEemunicipio(String eemunicipio) {
        this.eemunicipio = eemunicipio;
    }

    public String getEenoexterior() {
        return eenoexterior;
    }

    public void setEenoexterior(String eenoexterior) {
        this.eenoexterior = eenoexterior;
    }

    public String getEepais() {
        return eepais;
    }

    public void setEepais(String eepais) {
        this.eepais = eepais;
    }

    public String getEnombre() {
        return enombre;
    }

    public void setEnombre(String enombre) {
        this.enombre = enombre;
    }

    public String getErfc() {
        return erfc;
    }

    public void setErfc(String erfc) {
        this.erfc = erfc;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getImptraslados() {
        return imptraslados;
    }

    public void setImptraslados(String imptraslados) {
        this.imptraslados = imptraslados;
    }

    public String getNoaprobacion() {
        return noaprobacion;
    }

    public void setNoaprobacion(String noaprobacion) {
        this.noaprobacion = noaprobacion;
    }

    public String getRcalle() {
        return rcalle;
    }

    public void setRcalle(String rcalle) {
        this.rcalle = rcalle;
    }

    public String getRcolonia() {
        return rcolonia;
    }

    public void setRcolonia(String rcolonia) {
        this.rcolonia = rcolonia;
    }

    public String getRcp() {
        return rcp;
    }

    public void setRcp(String rcp) {
        this.rcp = rcp;
    }

    public String getRestado() {
        return restado;
    }

    public void setRestado(String restado) {
        this.restado = restado;
    }

    public String getRnoexterior() {
        return rnoexterior;
    }

    public void setRnoexterior(String rnoexterior) {
        this.rnoexterior = rnoexterior;
    }

    public String getRnombre() {
        return rnombre;
    }

    public void setRnombre(String rnombre) {
        this.rnombre = rnombre;
    }

    public String getRpais() {
        return rpais;
    }

    public void setRpais(String rpais) {
        this.rpais = rpais;
    }

    public String getRrfc() {
        return rrfc;
    }

    public void setRrfc(String rrfc) {
        this.rrfc = rrfc;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUecalle() {
        return uecalle;
    }

    public void setUecalle(String uecalle) {
        this.uecalle = uecalle;
    }

    public String getUecolonia() {
        return uecolonia;
    }

    public void setUecolonia(String uecolonia) {
        this.uecolonia = uecolonia;
    }

    public String getUecp() {
        return uecp;
    }

    public void setUecp(String uecp) {
        this.uecp = uecp;
    }

    public String getUeestado() {
        return ueestado;
    }

    public void setUeestado(String ueestado) {
        this.ueestado = ueestado;
    }

    public String getUelocalidad() {
        return uelocalidad;
    }

    public void setUelocalidad(String uelocalidad) {
        this.uelocalidad = uelocalidad;
    }

    public String getUemunicipio() {
        return uemunicipio;
    }

    public void setUemunicipio(String uemunicipio) {
        this.uemunicipio = uemunicipio;
    }

    public String getUenoexterior() {
        return uenoexterior;
    }

    public void setUenoexterior(String uenoexterior) {
        this.uenoexterior = uenoexterior;
    }

    public String getUepais() {
        return uepais;
    }

    public void setUepais(String uepais) {
        this.uepais = uepais;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRciudad() {
        return rciudad;
    }

    public void setRciudad(String rciudad) {
        this.rciudad = rciudad;
    }

    public String getRegimenfiscal() {
        return regimenfiscal;
    }

    public void setRegimenfiscal(String regimenfiscal) {
        this.regimenfiscal = regimenfiscal;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getNumCtaPago() {
        return numCtaPago;
    }

    public void setNumCtaPago(String numCtaPago) {
        this.numCtaPago = numCtaPago;
    }

    public String getFechaTimbrado() {
        return fechaTimbrado;
    }

    public void setFechaTimbrado(String fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    public String getNocerSAT() {
        return nocerSAT;
    }

    public void setNocerSAT(String nocerSAT) {
        this.nocerSAT = nocerSAT;
    }

    public String getSelloSAT() {
        return selloSAT;
    }

    public void setSelloSAT(String selloSAT) {
        this.selloSAT = selloSAT;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersionTimbre() {
        return versionTimbre;
    }

    public void setVersionTimbre(String versionTimbre) {
        this.versionTimbre = versionTimbre;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public int getNumError() {
        return numError;
    }

    public void setNumError(int numError) {
        this.numError = numError;
    }

    public byte[] getRespuestaPAC() {
        return respuestaPAC;
    }

    public void setRespuestaPAC(byte[] respuestaPAC) {
        this.respuestaPAC = respuestaPAC;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

  
 
    public void generarRespaldo(String entrada,String salida)
    {
        try {
			File inFile = new File(entrada);
			File outFile = new File(salida);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while( (c = in.read() ) != -1)
				out.write(c);

			in.close();
                        out.flush();
			out.close();
		} catch(IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
                        e.printStackTrace();
		}    
    }
    
    
}
