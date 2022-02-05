package com.paedeias.identidades;
// Generated 30-may-2012 11:23:10 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Compras generated by hbm2java
 */
public class Compras  implements java.io.Serializable {


     private Long id;
     private Long codigoPedido;
     private Long codigoProveedor;
     private Double importe;
     private Double cargos;
     private String descAdic;
     private Integer compra;
     private Integer devolucion;
     private Integer pedido;
     private String numRefComp;
     private String observacion;
     private String tipoPago;
     private String cheque;
     private String banco;
     private Integer diasCred;
     private String fechaCompra;
     private Long idUsuario;
     private Integer cantidadArticulos;

    public Compras() {
    }

    public Compras(Long codigoPedido, Long codigoProveedor, Double importe, Double cargos, String descAdic, Integer compra, Integer devolucion, Integer pedido, String numRefComp, String observacion, String tipoPago, String cheque, String banco, Integer diasCred, String fechaCompra) {
       this.codigoPedido = codigoPedido;
       this.codigoProveedor = codigoProveedor;
       this.importe = importe;
       this.cargos = cargos;
       this.descAdic = descAdic;
       this.compra = compra;
       this.devolucion = devolucion;
       this.pedido = pedido;
       this.numRefComp = numRefComp;
       this.observacion = observacion;
       this.tipoPago = tipoPago;
       this.cheque = cheque;
       this.banco = banco;
       this.diasCred = diasCred;
       this.fechaCompra = fechaCompra;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCodigoPedido() {
        return this.codigoPedido;
    }
    
    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    public Long getCodigoProveedor() {
        return this.codigoProveedor;
    }
    
    public void setCodigoProveedor(Long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }
    public Double getImporte() {
        return this.importe;
    }
    
    public void setImporte(Double importe) {
        this.importe = importe;
    }
    public Double getCargos() {
        return this.cargos;
    }
    
    public void setCargos(Double cargos) {
        this.cargos = cargos;
    }
    public String getDescAdic() {
        return this.descAdic;
    }
    
    public void setDescAdic(String descAdic) {
        this.descAdic = descAdic;
    }
    public Integer getCompra() {
        return this.compra;
    }
    
    public void setCompra(Integer compra) {
        this.compra = compra;
    }
    public Integer getDevolucion() {
        return this.devolucion;
    }
    
    public void setDevolucion(Integer devolucion) {
        this.devolucion = devolucion;
    }
    public Integer getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }
    public String getNumRefComp() {
        return this.numRefComp;
    }
    
    public void setNumRefComp(String numRefComp) {
        this.numRefComp = numRefComp;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getTipoPago() {
        return this.tipoPago;
    }
    
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public String getCheque() {
        return this.cheque;
    }
    
    public void setCheque(String cheque) {
        this.cheque = cheque;
    }
    public String getBanco() {
        return this.banco;
    }
    
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public Integer getDiasCred() {
        return this.diasCred;
    }
    
    public void setDiasCred(Integer diasCred) {
        this.diasCred = diasCred;
    }
    public String getFechaCompra() {
        return this.fechaCompra;
    }
    
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(Integer cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }




}

