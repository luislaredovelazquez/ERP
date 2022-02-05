package com.paedeias.identidades;
// Generated 23-abr-2012 18:15:52 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Ventas generated by hbm2java
 */
public class Devoluciones  implements java.io.Serializable {


     private Long id;
     private Long idusuario;
     private Long idcliente;
     private Long idadministrador;
     private Long idVenta;
     private String codigoVenta;
     private Integer articulos;
     private Integer partidas;
     private Double subtotal;
     private Double total;
     private String observaciones;
     private String tipoDeVenta;
     private String fechaVenta;
     private String estado;
     private String estadoFactura;

    public Devoluciones() {
    }

    public Devoluciones(Long idusuario, Long idcliente, Long idadministrador, Long idVenta, String codigoVenta, Integer articulos, Integer partidas, Double subtotal, Double total, String observaciones, String tipoDeVenta, String fechaVenta, String estado, String estadoFacturada) {
       this.idusuario = idusuario;
       this.idcliente = idcliente;
       this.idadministrador = idadministrador;
       this.idVenta = idVenta;
       this.codigoVenta = codigoVenta;
       this.articulos = articulos;
       this.partidas = partidas;
       this.subtotal = subtotal;
       this.total = total;
       this.observaciones = observaciones;
       this.tipoDeVenta = tipoDeVenta;
       this.fechaVenta = fechaVenta;
       this.estado = estado;
       this.estadoFactura=estadoFacturada;
    }

       public Devoluciones(Long id,Long idusuario, Long idcliente, Long idadministrador,Long idVenta, String codigoVenta, Integer articulos, Integer partidas, Double subtotal, Double total, String observaciones, String tipoDeVenta, String fechaVenta,String estado, String estadoFacturada) {
       this.id = id;
       this.idusuario = idusuario;
       this.idcliente = idcliente;
       this.idadministrador = idadministrador;
       this.idVenta = idVenta;
       this.codigoVenta = codigoVenta;
       this.articulos = articulos;
       this.partidas = partidas;
       this.subtotal = subtotal;
       this.total = total;
       this.observaciones = observaciones;
       this.tipoDeVenta = tipoDeVenta;
       this.fechaVenta = fechaVenta;
       this.estado = estado;
       this.estadoFactura=estadoFacturada;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
    public Long getIdcliente() {
        return this.idcliente;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }
    
    
    
    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }
    public Long getIdadministrador() {
        return this.idadministrador;
    }
    
    public void setIdadministrador(Long idadministrador) {
        this.idadministrador = idadministrador;
    }
    public Integer getArticulos() {
        return this.articulos;
    }
    
    public void setArticulos(Integer articulos) {
        this.articulos = articulos;
    }
    public Integer getPartidas() {
        return this.partidas;
    }
    
    public void setPartidas(Integer partidas) {
        this.partidas = partidas;
    }
    public Double getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getTipoDeVenta() {
        return this.tipoDeVenta;
    }
    
    public void setTipoDeVenta(String tipoDeVenta) {
        this.tipoDeVenta = tipoDeVenta;
    }
    public String getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFacturada) {
        this.estadoFactura = estadoFacturada;
    }




}

