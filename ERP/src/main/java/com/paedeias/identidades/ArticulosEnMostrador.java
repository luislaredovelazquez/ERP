/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class ArticulosEnMostrador {

    long id;
    String codigo;
    String descripcion;
    int cantidad;
    long responsable;
    String fecha;
    
    public ArticulosEnMostrador() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getResponsable() {
        return responsable;
    }

    public void setResponsable(long responsable) {
        this.responsable = responsable;
    }

    
    
    
}
