/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class PartidasEnReservacion {

    String idRes;
    String cliente;
    String fecha;
    String precioVenta;
    String tipoAuto;
    String modelo;
    String codArt;
    String descripcion;
    String vale;
    
    public PartidasEnReservacion() {
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
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

    public String getIdRes() {
        return idRes;
    }

    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getVale() {
        return vale;
    }

    public void setVale(String vale) {
        this.vale = vale;
    }
    
    
    
}
