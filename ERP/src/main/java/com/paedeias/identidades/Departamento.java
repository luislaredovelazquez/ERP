package com.paedeias.identidades;
// Generated 12/03/2012 03:40:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Departamento generated by hbm2java
 */
public class Departamento  implements java.io.Serializable {


     private long id;
     private String nombre;

    public Departamento() {
    }

    public Departamento(String nombre) {
       this.nombre = nombre;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}


