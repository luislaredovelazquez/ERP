package com.paedeias.identidades;
// Generated 23-abr-2012 18:15:52 by Hibernate Tools 3.2.1.GA



/**
 * Partidas generated by hbm2java
 */
public class Blog implements java.io.Serializable {


     private long id;
     private String titulo;
     private String texto;
     private String autor;

    public Blog() {
    }

     
     
    public Blog(long id, String titulo, String texto,String autor) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    
}


