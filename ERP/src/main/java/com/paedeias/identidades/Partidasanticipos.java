package com.paedeias.identidades;
// Generated 23-may-2012 12:53:33 by Hibernate Tools 3.2.1.GA



/**
 * Partidasanticipos generated by hbm2java
 */
public class Partidasanticipos  implements java.io.Serializable {


     private Long id;
     private Long anticipo;
     private String codigoarticulo;
     private Long idarticulo;
     private String articulo;
     private Integer cantidad;
     private Double precioCompra;
     private Double precioVenta;
     private Integer surtido;
     private Integer surtidoAlmacen;
     private long cantidadAlmacen;

    public Partidasanticipos() {
    }

    public Partidasanticipos(Long anticipo, String codigoarticulo, Long idarticulo, String articulo, Integer cantidad, Double precioCompra, Double precioVenta, Integer surtido) {
       this.anticipo = anticipo;
       this.codigoarticulo = codigoarticulo;
       this.idarticulo = idarticulo;
       this.articulo = articulo;
       this.cantidad = cantidad;
       this.precioCompra = precioCompra;
       this.precioVenta = precioVenta;
       this.surtido = surtido;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAnticipo() {
        return this.anticipo;
    }
    
    public void setAnticipo(Long anticipo) {
        this.anticipo = anticipo;
    }
    public String getCodigoarticulo() {
        return this.codigoarticulo;
    }
    
    public void setCodigoarticulo(String codigoarticulo) {
        this.codigoarticulo = codigoarticulo;
    }
    public Long getIdarticulo() {
        return this.idarticulo;
    }
    
    public void setIdarticulo(Long idarticulo) {
        this.idarticulo = idarticulo;
    }
    public String getArticulo() {
        return this.articulo;
    }
    
    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecioCompra() {
        return this.precioCompra;
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
    public Integer getSurtido() {
        return this.surtido;
    }
    
    public void setSurtido(Integer surtido) {
        this.surtido = surtido;
    }

    public Integer getSurtidoAlmacen() {
        return surtidoAlmacen;
    }

    public void setSurtidoAlmacen(Integer surtidoAlmacen) {
        this.surtidoAlmacen = surtidoAlmacen;
    }

    public long getCantidadAlmacen() {
        return cantidadAlmacen;
    }

    public void setCantidadAlmacen(long cantidadAlmacen) {
        this.cantidadAlmacen = cantidadAlmacen;
    }




}


