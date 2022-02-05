/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class Inventario {
   
    String codigo = "";
    String descripcion = "";
    double precioCompra = 0;
    int almacenG = 0;
    int almacenD = 0;
    int reservados = 0;
    int anticipos = 0;
    int sistema = 0;
    int fisico = 0;
    int diferencia = 0;
    double costoUnitario = 0;
    double costoSobrante = 0;
    double costoFaltante = 0;
    double costoTotal = 0;
    String id;
    String fila;

    public int getAlmacenD() {
        return almacenD;
    }

    public void setAlmacenD(int almacenD) {
        this.almacenD = almacenD;
    }

    public int getAlmacenG() {
        return almacenG;
    }

    public void setAlmacenG(int almacenG) {
        this.almacenG = almacenG;
    }

    public int getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(int anticipos) {
        this.anticipos = anticipos;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getReservados() {
        return reservados;
    }

    public void setReservados(int reservados) {
        this.reservados = reservados;
    }

    
    
    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
    }

    public int getFisico() {
        return fisico;
    }

    public void setFisico(int fisico) {
        this.fisico = fisico;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }


    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
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

    public double getCostoFaltante() {
        return costoFaltante;
    }

    public void setCostoFaltante(double costoFaltante) {
        this.costoFaltante = costoFaltante;
    }

    public double getCostoSobrante() {
        return costoSobrante;
    }

    public void setCostoSobrante(double costoSobrante) {
        this.costoSobrante = costoSobrante;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }
    
    
}
