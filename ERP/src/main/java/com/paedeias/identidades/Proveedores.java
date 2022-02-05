package com.paedeias.identidades;
// Generated 12/03/2012 03:40:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Proveedores generated by hbm2java
 */
public class Proveedores  implements java.io.Serializable {


     private long id;
     private String rfc;
     private String nombre;
     private int clasificacion;
     private String telefono;
     private String atencion;
     private String calle;
     private int numero;
     private String colonia;
     private String poblacion;
     private String estado;
     private String codigoPostal;
     private String correo;
     private int diasCredito;
     private int diasLimite;
     private int descuento;
     private String observaciones;
     private String nombreCorto;
     private double limiteCredito;
     private Integer desc1;
     private Integer desc2;
     private Integer desc3;
     private Integer desc4;
     private Integer desc5;
     private Integer desc6;
    
    public Proveedores() {
    }

	
    public Proveedores(String rfc, String nombre, int clasificacion, String telefono, String atencion, String calle, int numero, String colonia, String poblacion, String estado, String codigoPostal, String correo, int diasCredito, int diasLimite, int descuento,String nombreCorto, double limiteCredito) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.telefono = telefono;
        this.atencion = atencion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.poblacion = poblacion;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.correo = correo;
        this.diasCredito = diasCredito;
        this.diasLimite = diasLimite;
        this.descuento = descuento;
        this.nombreCorto = nombreCorto;
        this.limiteCredito = limiteCredito;
    }

        public Proveedores(long id,String rfc, String nombre, int clasificacion, String telefono, String atencion, String calle, int numero, String colonia, String poblacion, String estado, String codigoPostal, String correo, int diasCredito, int diasLimite, int descuento,String observaciones, String nombreCorto, double limiteCredito) {
        this.id = id;
        this.rfc = rfc;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.telefono = telefono;
        this.atencion = atencion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.poblacion = poblacion;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.correo = correo;
        this.diasCredito = diasCredito;
        this.diasLimite = diasLimite;
        this.descuento = descuento;
        this.observaciones = observaciones;
        this.nombreCorto = nombreCorto;
        this.limiteCredito = limiteCredito;
    }

    public Proveedores( String rfc, String nombre, int clasificacion, String telefono, String atencion, String calle, int numero, String colonia, String poblacion, String estado, String codigoPostal, String correo, int diasCredito, int diasLimite, int descuento, String observaciones, String nombreCorto, double limiteCredito) {

       this.rfc = rfc;
       this.nombre = nombre;
       this.clasificacion = clasificacion;
       this.telefono = telefono;
       this.atencion = atencion;
       this.calle = calle;
       this.numero = numero;
       this.colonia = colonia;
       this.poblacion = poblacion;
       this.estado = estado;
       this.codigoPostal = codigoPostal;
       this.correo = correo;
       this.diasCredito = diasCredito;
       this.diasLimite = diasLimite;
       this.descuento = descuento;
       this.observaciones = observaciones;
       this.nombreCorto = nombreCorto;
       this.limiteCredito = limiteCredito;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getRfc() {
        return this.rfc;
    }
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getClasificacion() {
        return this.clasificacion;
    }
    
    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getAtencion() {
        return this.atencion;
    }
    
    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getPoblacion() {
        return this.poblacion;
    }
    
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCodigoPostal() {
        return this.codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getDiasCredito() {
        return this.diasCredito;
    }
    
    public void setDiasCredito(int diasCredito) {
        this.diasCredito = diasCredito;
    }
    public int getDiasLimite() {
        return this.diasLimite;
    }
    
    public void setDiasLimite(int diasLimite) {
        this.diasLimite = diasLimite;
    }
    public int getDescuento() {
        return this.descuento;
    }
    
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

        public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Integer getDesc1() {
        return desc1;
    }

    public void setDesc1(int desc1) {
        this.desc1 = desc1;
    }

    public Integer getDesc2() {
        return desc2;
    }

    public void setDesc2(int desc2) {
        this.desc2 = desc2;
    }

    public Integer getDesc3() {
        return desc3;
    }

    public void setDesc3(int desc3) {
        this.desc3 = desc3;
    }

    public Integer getDesc4() {
        return desc4;
    }

    public void setDesc4(int desc4) {
        this.desc4 = desc4;
    }

    public Integer getDesc5() {
        return desc5;
    }

    public void setDesc5(int desc5) {
        this.desc5 = desc5;
    }

    public Integer getDesc6() {
        return desc6;
    }

    public void setDesc6(int desc6) {
        this.desc6 = desc6;
    }

    
    
}

