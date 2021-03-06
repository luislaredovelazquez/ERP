package com.paedeias.identidades;
// Generated 22-may-2012 13:02:59 by Hibernate Tools 3.2.1.GA



/**
 * Anticipos generated by hbm2java
 */
public class Anticipos  implements java.io.Serializable {


     private long id;
     private String referencia;
     private String fecha;
     private String idVendedor;
     private String fechaFin;
     private String acuenta;
     private String importe;
     private String resta;
     private String tipo;
     private String observaciones;
     private Integer devuelta;
     private Integer cancelado;
     private Integer ticket;
     private Integer factura;
     private Integer surtido;
     private String propietario;
     private String telefono;
     private String anio;
     private String puertas;
     private String noserie;
     private String modelo;
     private String lado;
     private String transmision;
     

    public Anticipos() {
    }

	
    public Anticipos(long id) {
        this.id = id;
    }
    public Anticipos(long id, String referencia, String fecha, String idVendedor, String fechaFin, String acuenta, String importe, String resta, String tipo, String observaciones, Integer devuelta, Integer cancelado, Integer ticket, Integer factura, Integer surtido) {
       this.id = id;
       this.referencia = referencia;
       this.fecha = fecha;
       this.idVendedor = idVendedor;
       this.fechaFin = fechaFin;
       this.acuenta = acuenta;
       this.importe = importe;
       this.resta = resta;
       this.tipo = tipo;
       this.observaciones = observaciones;
       this.devuelta = devuelta;
       this.cancelado = cancelado;
       this.ticket = ticket;
       this.factura = factura;
       this.surtido = surtido;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getReferencia() {
        return this.referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getIdVendedor() {
        return this.idVendedor;
    }
    
    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    public String getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getAcuenta() {
        return this.acuenta;
    }
    
    public void setAcuenta(String acuenta) {
        this.acuenta = acuenta;
    }
    public String getImporte() {
        return this.importe;
    }
    
    public void setImporte(String importe) {
        this.importe = importe;
    }
    public String getResta() {
        return this.resta;
    }
    
    public void setResta(String resta) {
        this.resta = resta;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Integer getDevuelta() {
        return this.devuelta;
    }
    
    public void setDevuelta(Integer devuelta) {
        this.devuelta = devuelta;
    }
    public Integer getCancelado() {
        return this.cancelado;
    }
    
    public void setCancelado(Integer cancelado) {
        this.cancelado = cancelado;
    }
    public Integer getTicket() {
        return this.ticket;
    }
    
    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }
    public Integer getFactura() {
        return this.factura;
    }
    
    public void setFactura(Integer factura) {
        this.factura = factura;
    }
    public Integer getSurtido() {
        return this.surtido;
    }
    
    public void setSurtido(Integer surtido) {
        this.surtido = surtido;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNoserie() {
        return noserie;
    }

    public void setNoserie(String noserie) {
        this.noserie = noserie;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }


}


