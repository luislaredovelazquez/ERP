package com.paedeias.identidades;
// Generated 23-abr-2012 18:15:52 by Hibernate Tools 3.2.1.GA



/**
 * Partidas generated by hbm2java
 */
public class Partidasdevoluciones  implements java.io.Serializable {


     private long id;
     private String idCompleto;
     private Long idVenta;
     private Long idArticulo;
     private String codigoArticulo;
     private String descripcionArticulo;
     private Double precioCompra;
     private Double precioVenta;
     private String ubicacion;
     private String tipoBeneficio;
     private Double beneficio;
     private Double conBeneficio;
     private Integer cantidad;
     private Double subtotal;

    public Partidasdevoluciones() {
    }

	
    public Partidasdevoluciones(long id) {
        this.id = id;
    }
    public Partidasdevoluciones(long id, Long idVenta, Long idArticulo,String codigoArticulo, String descripcionArticulo,Double precioCompra, Double precioVenta, String tipoBeneficio, Double beneficio, Double conBeneficio, Integer cantidad, Double subtotal) {
       this.id = id;
       this.idVenta = idVenta;
       this.idArticulo = idArticulo;
       this.codigoArticulo = codigoArticulo;
       this.descripcionArticulo = descripcionArticulo;
       this.precioCompra = precioCompra;
       this.precioVenta = precioVenta;
       this.tipoBeneficio = tipoBeneficio;
       this.beneficio = beneficio;
       this.conBeneficio = conBeneficio;
       this.cantidad = cantidad;
       this.subtotal = subtotal;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getIdCompleto() {
        return idCompleto;
    }

    public void setIdCompleto(String idCompleto) {
        this.idCompleto = idCompleto;
    }
    
    
    
    public Long getIdVenta() {
        return this.idVenta;
    }
    
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
    public Long getIdArticulo() {
        return this.idArticulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }
    
    
    
    public String getDescripcionArticulo() {
        return this.descripcionArticulo;
    }
    
    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    
    
    public Double getPrecioVenta() {
        return this.precioVenta;
    }
    
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }
    public String getTipoBeneficio() {
        return this.tipoBeneficio;
    }
    
    public void setTipoBeneficio(String tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }
    public Double getBeneficio() {
        return this.beneficio;
    }
    
    public void setBeneficio(Double beneficio) {
        this.beneficio = beneficio;
    }
    public Double getConBeneficio() {
        return this.conBeneficio;
    }
    
    public void setConBeneficio(Double conBeneficio) {
        this.conBeneficio = conBeneficio;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }




}


