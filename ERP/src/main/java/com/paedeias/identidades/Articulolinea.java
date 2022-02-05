package com.paedeias.identidades;
// Generated 21/03/2012 01:31:37 PM by Hibernate Tools 3.2.1.GA



/**
 * Articulolinea generated by hbm2java
 */
public class Articulolinea  implements java.io.Serializable {


     private Long id;
     private long claveArticulo;
     private long claveLinea;
     private String nombre;   
     private String descripcion;

    public Articulolinea() {
    }

    public Articulolinea(long claveArticulo, long claveLinea) {
       this.claveArticulo = claveArticulo;
       this.claveLinea = claveLinea;
    }

        public Articulolinea(long id, long claveArticulo, long claveLinea) {
       this.claveArticulo = claveArticulo;
       this.claveLinea = claveLinea;
       this.id = id;
    }
   
    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setId(Long id) {
        this.id = id;
    }
    public long getClaveArticulo() {
        return this.claveArticulo;
    }
    
    public void setClaveArticulo(long claveArticulo) {
        this.claveArticulo = claveArticulo;
    }
    public long getClaveLinea() {
        return this.claveLinea;
    }
    
    public void setClaveLinea(long claveLinea) {
        this.claveLinea = claveLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


