package com.paedeias.identidades;
// Generated 12/03/2012 03:40:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Linea generated by hbm2java
 */
public class Linea  implements java.io.Serializable {


     private long id;
     private String nombre;
     private String descripcion;



    public Linea(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Linea() {
    }

    public Linea(String nombre,String descripcion) {
       this.nombre = nombre;
       this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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


