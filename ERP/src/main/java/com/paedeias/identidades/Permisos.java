/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.identidades;

/**
 *
 * @author ALL
 */
public class Permisos {

    public Permisos() {
    }
    
    long id;
    long idUsuario;
    String permisos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
    
    
}
