package com.paedeias.identidades;
// Generated 12/03/2012 03:40:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Concepto generated by hbm2java
 */
public class Concepto  implements java.io.Serializable {


     private long idConcepto;
     private long noFactura;
     private String codigo;
     private String importe;
     private String valorUnitario;
     private String descripcion;
     private String noIdentificacion;
     private String unidad;
     private String cantidad;

    public Concepto() {
    }

    public Concepto(Integer noFactura, String codigo, String importe, String valorUnitario, String descripcion, String noIdentificacion, String unidad, String cantidad) {
       this.noFactura = noFactura;
       this.codigo = codigo;
       this.importe = importe;
       this.valorUnitario = valorUnitario;
       this.descripcion = descripcion;
       this.noIdentificacion = noIdentificacion;
       this.unidad = unidad;
       this.cantidad = cantidad;
    }
   
    public long getIdConcepto() {
        return this.idConcepto;
    }
    
    public void setIdConcepto(long idConcepto) {
        this.idConcepto = idConcepto;
    }
    public long getNoFactura() {
        return this.noFactura;
    }
    
    public void setNoFactura(long noFactura) {
        this.noFactura = noFactura;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getImporte() {
        return this.importe;
    }
    
    public void setImporte(String importe) {
        this.importe = importe;
    }
    public String getValorUnitario() {
        return this.valorUnitario;
    }
    
    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNoIdentificacion() {
        return this.noIdentificacion;
    }
    
    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }
    public String getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public String getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }




}


