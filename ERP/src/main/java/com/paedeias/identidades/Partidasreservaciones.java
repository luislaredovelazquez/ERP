package com.paedeias.identidades;
// Generated 30-may-2012 11:23:10 by Hibernate Tools 3.2.1.GA



/**
 * Partidasreservaciones generated by hbm2java
 */
public class Partidasreservaciones  implements java.io.Serializable {


     private Long id;
     private String codRes;
     private String codArt;
     private Integer cantidad;
     private String descripcion;
     private Integer reservado;
     private Integer surtido;
     private String vale;
     private Double costo;
     private Double precio;
     private Long idArt;
     private Long cantidadAlmacen;

    public Partidasreservaciones() {
    }

    public Partidasreservaciones(String codRes, String codArt, Integer cantidad, String descripcion, Integer reservado, Integer surtido, String vale, Double costo, Double precio) {
       this.codRes = codRes;
       this.codArt = codArt;
       this.cantidad = cantidad;
       this.descripcion = descripcion;
       this.reservado = reservado;
       this.surtido = surtido;
       this.vale = vale;
       this.costo = costo;
       this.precio = precio;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getCodRes() {
        return this.codRes;
    }
    
    public void setCodRes(String codRes) {
        this.codRes = codRes;
    }
    public String getCodArt() {
        return this.codArt;
    }
    
    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getReservado() {
        return this.reservado;
    }
    
    public void setReservado(Integer reservado) {
        this.reservado = reservado;
    }
    public Integer getSurtido() {
        return this.surtido;
    }
    
    public void setSurtido(Integer surtido) {
        this.surtido = surtido;
    }
    public String getVale() {
        return this.vale;
    }
    
    public void setVale(String vale) {
        this.vale = vale;
    }
    public Double getCosto() {
        return this.costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getIdArt() {
        return idArt;
    }

    public void setIdArt(Long idArt) {
        this.idArt = idArt;
    }

    public long getCantidadAlmacen() {
        return cantidadAlmacen;
    }

    public void setCantidadAlmacen(long cantidadAlmacen) {
        this.cantidadAlmacen = cantidadAlmacen;
    }




}


