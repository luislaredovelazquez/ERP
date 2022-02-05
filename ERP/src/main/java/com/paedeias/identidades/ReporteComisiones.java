/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class ReporteComisiones {

    public ReporteComisiones() {
    }
    long idVendedor=0;
    String nombreVendedor="";
    long idVenta=0;
    int cantidad = 1;
    double importe=0;
    int cantidadR = 1;
    double importeR=0;
    int cantidadAN = 1;
    double importeAN=0;
    int cantidadAU = 1;
    double importeAU = 0;
    int cantidadD = 1;
    double importeD = 0;
    double totalImporte=0;
    double totalImporteSIva=0;
    double totalImporteNegSIva=0;
    double comision = 0;
    double comisionNeg = 0;
    double totalNeto = 0;
    int totalVentas = 0;
    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public int getCantidadR() {
        return cantidadR;
    }

    public void setCantidadR(int cantidadR) {
        this.cantidadR = cantidadR;
    }

    public double getImporteR() {
        return importeR;
    }

    public void setImporteR(double importeR) {
        this.importeR = importeR;
    }

    public int getCantidadAN() {
        return cantidadAN;
    }

    public void setCantidadAN(int cantidadAN) {
        this.cantidadAN = cantidadAN;
    }

    public double getImporteAN() {
        return importeAN;
    }

    public void setImporteAN(double importeAN) {
        this.importeAN = importeAN;
    }

    public int getCantidadAU() {
        return cantidadAU;
    }

    public void setCantidadAU(int cantidadAU) {
        this.cantidadAU = cantidadAU;
    }

    public double getImporteAU() {
        return importeAU;
    }

    public void setImporteAU(double importeAU) {
        this.importeAU = importeAU;
    }

    public int getCantidadD() {
        return cantidadD;
    }

    public void setCantidadD(int cantidadD) {
        this.cantidadD = cantidadD;
    }

    public double getImporteD() {
        return importeD;
    }

    public void setImporteD(double importeD) {
        this.importeD = importeD;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getComisionNeg() {
        return comisionNeg;
    }

    public void setComisionNeg(double comisionNeg) {
        this.comisionNeg = comisionNeg;
    }

    public double getTotalImporte() {
        return totalImporte;
    }

    public void setTotalImporte(double totalImporte) {
        this.totalImporte = totalImporte;
    }

    public double getTotalImporteSIva() {
        return totalImporteSIva;
    }

    public void setTotalImporteSIva(double totalImporteSIva) {
        this.totalImporteSIva = totalImporteSIva;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public double getTotalImporteNegSIva() {
        return totalImporteNegSIva;
    }

    public void setTotalImporteNegSIva(double totalImporteNegSIva) {
        this.totalImporteNegSIva = totalImporteNegSIva;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }
    
    
    
}
