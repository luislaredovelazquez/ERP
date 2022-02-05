/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

import com.paedeias.identidades.Partidas;
import com.paedeias.identidades.Partidasdevoluciones;
import com.paedeias.identidades.Partidasfacturas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mx.bigdata.sat.cfd.schema.Comprobante;
import mx.bigdata.sat.cfd.schema.ObjectFactory;
import mx.bigdata.sat.cfd.schema.TUbicacion;
import mx.bigdata.sat.cfd.schema.TUbicacionFiscal;

/**
 *
 * @author ALL
 */
public class CFDv2 {
    private Comprobante comp;
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
    private String rcolonia;
    private String rciudad;
    private String restado;
    private String rnoexterior;
    private String imptraslados;
    private String importe;
    
    
    public  Comprobante createComprobante(int year,List<Partidasfacturas>partidas) throws Exception {
             ObjectFactory of = new ObjectFactory();
    comp = of.createComprobante();
    comp.setVersion("2.0");
    //Date date = new GregorianCalendar(year, 01, 17, 17, 31, 31).getTime();
    Date date = Calendar.getInstance().getTime();
    comp.setFecha(date);
    comp.setFolio(folio);
    comp.setNoAprobacion(new BigInteger(noaprobacion));
    comp.setAnoAprobacion(new BigInteger(anoaprobacion));
    comp.setFormaDePago(formapago);
    comp.setSubTotal(new BigDecimal(subtotal));
    comp.setTotal(new BigDecimal(total));
    comp.setDescuento(new BigDecimal(descuento));
    comp.setTipoDeComprobante(tipoDeComprobante);
    comp.setEmisor(createEmisor(of));
    comp.setReceptor(createReceptor(of));
    comp.setConceptos(createConceptos(of,partidas));
    comp.setImpuestos(createImpuestos(of));
 //   AddendaQualitas aq = new AddendaQualitas();
 //   comp.setAddenda(aq.createAddenda(of));                agregar para la addenda
    return comp;
    }
    
     public  Comprobante createComprobanteNC(int year,List<Partidasdevoluciones>partidas) throws Exception {
             ObjectFactory of = new ObjectFactory();
    comp = of.createComprobante();
    comp.setVersion("2.0");
    //Date date = new GregorianCalendar(year, 01, 17, 17, 31, 31).getTime();
    Date date = Calendar.getInstance().getTime();
    comp.setFecha(date);
    comp.setFolio(folio);
    comp.setNoAprobacion(new BigInteger(noaprobacion));
    comp.setAnoAprobacion(new BigInteger(anoaprobacion));
    comp.setFormaDePago(formapago);
    comp.setSubTotal(new BigDecimal(subtotal));
    comp.setTotal(new BigDecimal(total));
    comp.setDescuento(new BigDecimal(descuento));
    comp.setTipoDeComprobante(tipoDeComprobante);
    comp.setEmisor(createEmisor(of));
    comp.setReceptor(createReceptor(of));
    comp.setConceptos(createConceptosNC(of,partidas));
    comp.setImpuestos(createImpuestos(of));
 //   AddendaQualitas aq = new AddendaQualitas();
 //   comp.setAddenda(aq.createAddenda(of));                agregar para la addenda
    return comp;
    }
        
      private Comprobante.Emisor createEmisor(ObjectFactory of) {
    Comprobante.Emisor emisor = of.createComprobanteEmisor();
    emisor.setNombre(enombre);
    emisor.setRfc(erfc);
    TUbicacion ubicacion = of.createTUbicacion();
    ubicacion.setCalle(uecalle);
    ubicacion.setCodigoPostal(uecp);
    ubicacion.setColonia(uecolonia);
    ubicacion.setEstado(ueestado);
    ubicacion.setLocalidad(uelocalidad);
    ubicacion.setMunicipio(uemunicipio);
    ubicacion.setNoExterior(uenoexterior);
    ubicacion.setNoInterior("0");
    ubicacion.setPais(uepais.replace("é","\u00C9"));
    emisor.setExpedidoEn(ubicacion);
    TUbicacionFiscal uf = of.createTUbicacionFiscal();
    uf.setCalle(eecalle);
    uf.setCodigoPostal(eecp);
    uf.setColonia(eecolonia); 
    uf.setLocalidad(eelocalidad);  
    uf.setMunicipio(eemunicipio); 
    uf.setEstado(eeestado); 
    uf.setNoExterior(eenoexterior); 
    uf.setNoInterior("0"); 
    uf.setPais(eepais.replace("é","\u00C9")); 
    emisor.setDomicilioFiscal(uf);
    return emisor;
  }
      
    private Comprobante.Receptor createReceptor(ObjectFactory of) {
    Comprobante.Receptor receptor = of.createComprobanteReceptor();
    receptor.setNombre(rnombre);
    receptor.setRfc(rrfc);
    TUbicacion uf = of.createTUbicacion();
    uf.setCalle(rcalle);
    uf.setCodigoPostal(rcp);
    uf.setColonia(rcolonia); 
    uf.setEstado(restado); 
    uf.setNoExterior(rnoexterior); 
    uf.setMunicipio(rciudad);
    uf.setPais(rpais.replace("é","\u00C9")); 
    receptor.setDomicilio(uf);
    return receptor;
  }

    
    
    private Comprobante.Conceptos createConceptos(ObjectFactory of, List<Partidasfacturas>partidas) {
    Comprobante.Conceptos cps = of.createComprobanteConceptos();
    List<Comprobante.Conceptos.Concepto> list = cps.getConcepto(); 
    
    int i = 0;
    while(i<partidas.size())
    {
    Comprobante.Conceptos.Concepto c = of.createComprobanteConceptosConcepto();
    c.setUnidad("PZA");
    c.setNoIdentificacion(partidas.get(i).getCodigoArticulo());
    c.setImporte(new BigDecimal(partidas.get(i).getImporte()));
    c.setCantidad(new BigDecimal(partidas.get(i).getCantidad()));
    c.setDescripcion(partidas.get(i).getDescripcion());
    c.setValorUnitario(new BigDecimal(partidas.get(i).getPrecioUnitario()));
    list.add(c);
        i++;
    }
        return cps;
  }
  
      private Comprobante.Conceptos createConceptosNC(ObjectFactory of, List<Partidasdevoluciones>partidas) {
    Comprobante.Conceptos cps = of.createComprobanteConceptos();
    List<Comprobante.Conceptos.Concepto> list = cps.getConcepto(); 
    
    int i = 0;
    while(i<partidas.size())
    {
    Comprobante.Conceptos.Concepto c = of.createComprobanteConceptosConcepto();
    c.setUnidad("PZA");
    c.setNoIdentificacion(partidas.get(i).getCodigoArticulo());
    c.setImporte(new BigDecimal(partidas.get(i).getSubtotal()));
    c.setCantidad(new BigDecimal(partidas.get(i).getCantidad()));
    c.setDescripcion(partidas.get(i).getDescripcionArticulo());
    c.setValorUnitario(new BigDecimal(partidas.get(i).getConBeneficio()));
    list.add(c);
        i++;
    }
        return cps;
  }
      private  Comprobante.Impuestos createImpuestos(ObjectFactory of) {
    Comprobante.Impuestos imps = of.createComprobanteImpuestos();
    imps.setTotalImpuestosTrasladados(new BigDecimal(imptraslados));
    Comprobante.Impuestos.Traslados trs = of.createComprobanteImpuestosTraslados();
    List<Comprobante.Impuestos.Traslados.Traslado> list = trs.getTraslado(); 
    Comprobante.Impuestos.Traslados.Traslado t1 = of.createComprobanteImpuestosTrasladosTraslado();
    t1.setImporte(new BigDecimal(importe));
    t1.setImpuesto("IVA");
    t1.setTasa(new BigDecimal("16.00"));
    list.add(t1);
    imps.setTraslados(trs);
    return imps;
  }
    

    public Comprobante getComp() {
        return comp;
    }

    public void setComp(Comprobante comp) {
        this.comp = comp;
    }

    public String getEnombre() {
        return enombre;
    }

    public void setEnombre(String enombre) {
        this.enombre = enombre;
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

        public String getNoaprobacion() {
            return noaprobacion;
        }

        public void setNoaprobacion(String noaprobacion) {
            this.noaprobacion = noaprobacion;
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

    public String getErfc() {
        return erfc;
    }

    public void setErfc(String erfc) {
        this.erfc = erfc;
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

    public String getRciudad() {
        return rciudad;
    }

    public void setRciudad(String rciudad) {
        this.rciudad = rciudad;
    }
      
    
    
    
        
}
