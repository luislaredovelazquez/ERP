/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Partidas;
import com.paedeias.identidades.Partidasdevoluciones;
import com.paedeias.identidades.Partidasfacturas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.bigdata.sat.security.KeyLoader;

/**
 *
 * @author ALL
 */
public class CFactura {
    private String version;
    private String folio = "";
    private String noaprobacion = "";
    private String anoaprobacion = "";
    private String formapago = "";
    private String subtotal = "";
    private String total = "";
    private String descuento = "";
    private String tipoDeComprobante="";
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
    private String fecha;
    private String selloSAT;
    private String uuid;
    private String fechaTimbrado;

    
    
    public void Sellar(List<Partidasfacturas> partidas){
               // TODO add your handling code here:
        
        if(version.equals("2.0"))
        {
  try {
          Date date = new Date(); 
          Calendar calendario = Calendar.getInstance();
          CFDv2 cfdfac = new CFDv2();  //necesitamos llenar este objeto en caso de ser cfdv2
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
          
          
          
   mx.bigdata.sat.cfd.schema.Comprobante comp = cfdfac.createComprobante(calendario.get(Calendar.YEAR),partidas);  
   comp.setVersion("2.0");

    mx.bigdata.sat.cfd.CFDv2 cfd = new mx.bigdata.sat.cfd.CFDv2(comp,"com.paedeias.controladores.facturacion"); // Crea un CFD a partir de un InputStream
    X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(CGlobalConfig.getDireccion_certificado()));
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(CGlobalConfig.getDireccion_key()),  CGlobalConfig.getContrasena_key());
    
    mx.bigdata.sat.cfd.schema.Comprobante sellado = cfd.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
    cfd.validar(); // Valida el XML, que todos los elementos estén presentes
    cfd.verificar(); // Verifica un CFD ya firmado
    FileOutputStream fos = new FileOutputStream("xmls/fac"+folio+".xml");
    cfd.guardar(fos); // Serializa el CFD a un OutputStream
    fos.close();
    setSello(sellado.getSello());
    setCertificado(sellado.getCertificado());
    // System.out.println(cfd.getCadenaOriginal());
    setCadenaOriginal(cfd.getCadenaOriginal());   

        } catch (Exception ex) {
            Logger.getLogger(mx.bigdata.sat.cfd.CFDv2.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(version.equals("2.2"))
        {
    try {
          Date date = new Date(); 
          Calendar calendario = Calendar.getInstance();
          CFDv22 cfdfac = new CFDv22();  //necesitamos llenar este objeto en caso de ser cfdv22
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
          cfdfac.setRegimenfiscal(regimenfiscal);
          cfdfac.setNumCtaPago(numCtaPago);
          
   mx.bigdata.sat.cfd.v22.schema.Comprobante comp = cfdfac.createComprobante(calendario.get(Calendar.YEAR),partidas);  
   comp.setVersion("2.2");

    mx.bigdata.sat.cfd.CFDv22 cfd = new mx.bigdata.sat.cfd.CFDv22(comp,"com.paedeias.controladores.facturacion"); // Crea un CFD a partir de un InputStream
    X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(CGlobalConfig.getDireccion_certificado()));
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(CGlobalConfig.getDireccion_key()),  CGlobalConfig.getContrasena_key());
    cfd.addNamespace("http://www.bigdata.mx/cfdi/example", "");
    mx.bigdata.sat.cfd.v22.schema.Comprobante sellado = cfd.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
    cfd.validar(); // Valida el XML, que todos los elementos estén presentes
    cfd.verificar(); // Verifica un CFD ya firmado
    FileOutputStream fos = new FileOutputStream("xmls/fac"+folio+".xml");
    cfd.guardar(fos); // Serializa el CFD a un OutputStream
    fos.close();
    setSello(sellado.getSello());
    setCertificado(sellado.getCertificado());
    // System.out.println(cfd.getCadenaOriginal());
    setCadenaOriginal(cfd.getCadenaOriginal());

        } catch (Exception ex) {
            Logger.getLogger(mx.bigdata.sat.cfd.CFDv2.class.getName()).log(Level.SEVERE, null, ex);
        }        
        }
    }
    
      public void SellarNC(List<Partidasdevoluciones> partidas){
               // TODO add your handling code here:
        
        if(version.equals("2.0"))
        {
  try {
          Date date = new Date(); 
          Calendar calendario = Calendar.getInstance();
          CFDv2 cfdfac = new CFDv2();  //necesitamos llenar este objeto en caso de ser cfdv2
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
          
          
          
   mx.bigdata.sat.cfd.schema.Comprobante comp = cfdfac.createComprobanteNC(calendario.get(Calendar.YEAR),partidas);  
   comp.setVersion("2.0");

    mx.bigdata.sat.cfd.CFDv2 cfd = new mx.bigdata.sat.cfd.CFDv2(comp,"com.paedeias.controladores.facturacion"); // Crea un CFD a partir de un InputStream
    X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(CGlobalConfig.getDireccion_certificado()));
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(CGlobalConfig.getDireccion_key()),  CGlobalConfig.getContrasena_key());
    
    mx.bigdata.sat.cfd.schema.Comprobante sellado = cfd.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
    cfd.validar(); // Valida el XML, que todos los elementos estén presentes
    cfd.verificar(); // Verifica un CFD ya firmado
    FileOutputStream fos = new FileOutputStream("xmls/fac"+folio+".xml");
    cfd.guardar(fos); // Serializa el CFD a un OutputStream
    fos.close();
    setSello(sellado.getSello());
    setCertificado(sellado.getCertificado());
    // System.out.println(cfd.getCadenaOriginal());
    setCadenaOriginal(cfd.getCadenaOriginal());
    

        } catch (Exception ex) {
            Logger.getLogger(mx.bigdata.sat.cfd.CFDv2.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(version.equals("2.2"))
        {
    try {
          Date date = new Date(); 
          Calendar calendario = Calendar.getInstance();
          CFDv22 cfdfac = new CFDv22();  //necesitamos llenar este objeto en caso de ser cfdv22
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
          cfdfac.setRegimenfiscal(regimenfiscal);
          cfdfac.setNumCtaPago(numCtaPago);
          
   mx.bigdata.sat.cfd.v22.schema.Comprobante comp = cfdfac.createComprobanteNC(calendario.get(Calendar.YEAR),partidas);  
   comp.setVersion("2.2");

    mx.bigdata.sat.cfd.CFDv22 cfd = new mx.bigdata.sat.cfd.CFDv22(comp,"com.paedeias.controladores.facturacion"); // Crea un CFD a partir de un InputStream
    X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(CGlobalConfig.getDireccion_certificado()));
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(CGlobalConfig.getDireccion_key()),  CGlobalConfig.getContrasena_key());
    
    mx.bigdata.sat.cfd.v22.schema.Comprobante sellado = cfd.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
    cfd.validar(); // Valida el XML, que todos los elementos estén presentes
    cfd.verificar(); // Verifica un CFD ya firmado
    FileOutputStream fos = new FileOutputStream("xmls/fac"+folio+".xml");
    cfd.guardar(fos); // Serializa el CFD a un OutputStream
    fos.close();
    setSello(sellado.getSello());
    setCertificado(sellado.getCertificado());
    // System.out.println(cfd.getCadenaOriginal());
    setCadenaOriginal(cfd.getCadenaOriginal());

        } catch (Exception ex) {
            Logger.getLogger(mx.bigdata.sat.cfd.CFDv2.class.getName()).log(Level.SEVERE, null, ex);
        }        
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getFechaTimbrado() {
        return fechaTimbrado;
    }

    public void setFechaTimbrado(String fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    
     
 
    public boolean generarRespaldo(String entrada,String salida)
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
			out.close();
                        return true;
		} catch(IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
                        e.printStackTrace();
                        return false;
		}    
    }
    
    
}
