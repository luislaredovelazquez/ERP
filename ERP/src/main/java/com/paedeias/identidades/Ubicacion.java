package com.paedeias.identidades;
// Generated 23/03/2012 02:23:43 PM by Hibernate Tools 3.2.1.GA



/**
 * Ubicacion generated by hbm2java
 */
public class Ubicacion  implements java.io.Serializable {


     private Long id;
     private String ubicacion;

    public Ubicacion() {
    }

    public Ubicacion(String ubicacion) {
       this.ubicacion = ubicacion;
    }

     public Ubicacion(Long id,String ubicacion) {
       this.id = id;
       this.ubicacion = ubicacion;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }




}

