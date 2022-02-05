/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class Cantidades {
    
    public Cantidades(){}
    
    private long id;
    private String idCompleto;
    private String codigo;
    private String ubicacion;
    private int cantidadPartida;
    private int existenciaCatalogo;
    private double precioCompra;
    private double precioVenta;
    private int reservados;
    private int anticipos;

    public int getReservados() {
        return reservados;
    }

    public void setReservados(int reservados) {
        this.reservados = reservados;
    }
    
    

    public int getCantidadPartida() {
        return cantidadPartida;
    }

    public void setCantidadPartida(int cantidadPartida) {
        this.cantidadPartida = cantidadPartida;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getExistenciaCatalogo() {
        return existenciaCatalogo;
    }

    public void setExistenciaCatalogo(int existenciaCatalogo) {
        this.existenciaCatalogo = existenciaCatalogo;
    }

    public long getId() {
        return id;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(int anticipos) {
        this.anticipos = anticipos;
    }
    
    
    
}
