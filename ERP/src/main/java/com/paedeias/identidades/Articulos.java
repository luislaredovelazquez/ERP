package com.paedeias.identidades;
// Generated 12/03/2012 03:40:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Articulos generated by hbm2java
 */
public class Articulos  implements java.io.Serializable {


     private long id;
     private String idCompleto;
     private String codigo;
     private String descripcion;
     private String clasificacion;
     private double precioVenta;
     private double precioCompra;
     private double ultimoCosto;
     private String unidad;
     private String ubicacion;
     private long existencia;
     private long reservado;
     private long minimoPzas;
     private long maximoPzas;
     private long promPzas;
     private int iva;
     private int ieps;
     private String tipoEtiqueta;
     private int existenciaAlmacen;
     private int bloqueado;
     private int almacenDevoluciones;
     private String lineaPrincipal;
     private int anticipos;
     private String sinonimoPrincipal;
     private String reportePedidos="";
     private String ABC="";
     private String codigo2;
     private double precioVenta2;
     private String proveedor;
     private int paretto;
     private int nuevo;
     private int oferta;
     private String pedidosPrecio1;
     private String pedidosPrecio2;

    public Articulos() {
    }

    public Articulos(long id,
            String codigo,
            String descripcion,
            String clasificacion, 
            double precioVenta,
            double precioCompra,
            double ultimoCosto,
            String unidad,
            long existencia,
            long reservado,
            long minimoPzas,
            long maximoPzas,
            long promPzas,
            int iva,
            int ieps,
            String tipoEtiqueta) {
       this.id = id;
       this.codigo = codigo;
       this.descripcion = descripcion;
       this.clasificacion = clasificacion;
       this.precioVenta = precioVenta;
       this.precioCompra = precioCompra;
       this.ultimoCosto = ultimoCosto;
       this.unidad = unidad;
       this.ubicacion = ubicacion;
       this.existencia = existencia;
       this.reservado = reservado;
       this.minimoPzas = minimoPzas;
       this.maximoPzas = maximoPzas;
       this.promPzas = promPzas;
       this.iva = iva;
       this.ieps = ieps;
       this.tipoEtiqueta = tipoEtiqueta;
    }

    public String getPedidosPrecio2() {
        return pedidosPrecio2;
    }

    public void setPedidosPrecio2(String pedidosPrecio2) {
        this.pedidosPrecio2 = pedidosPrecio2;
    }
   
        
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdCompleto() {
        return idCompleto;
    }

    public void setIdCompleto(String idCompleto) {
        this.idCompleto = idCompleto;
    }

    public int getExistenciaAlmacen() {
        return existenciaAlmacen;
    }

    public void setExistenciaAlmacen(int existenciaAlmacen) {
        this.existenciaAlmacen = existenciaAlmacen;
    }
   
    
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getClasificacion() {
        return this.clasificacion;
    }
    
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public double getPrecioVenta() {
        return this.precioVenta;
    }
    
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    public double getPrecioCompra() {
        return this.precioCompra;
    }
    
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    public double getUltimoCosto() {
        return this.ultimoCosto;
    }
    
    public void setUltimoCosto(double ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }
    public String getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public long getExistencia() {
        return this.existencia;
    }
    
    public void setExistencia(long existencia) {
        this.existencia = existencia;
    }
    public long getReservado() {
        return this.reservado;
    }
    
    public void setReservado(long reservado) {
        this.reservado = reservado;
    }
    public long getMinimoPzas() {
        return this.minimoPzas;
    }
    
    public void setMinimoPzas(long minimoPzas) {
        this.minimoPzas = minimoPzas;
    }
    public long getMaximoPzas() {
        return this.maximoPzas;
    }
    
    public void setMaximoPzas(long maximoPzas) {
        this.maximoPzas = maximoPzas;
    }
    public long getPromPzas() {
        return this.promPzas;
    }
    
    public void setPromPzas(long promPzas) {
        this.promPzas = promPzas;
    }
    public int getIva() {
        return this.iva;
    }
    
    public void setIva(int iva) {
        this.iva = iva;
    }
    public int getIeps() {
        return this.ieps;
    }
    
    public void setIeps(int ieps) {
        this.ieps = ieps;
    }
    public String getTipoEtiqueta() {
        return this.tipoEtiqueta;
    }
    
    public void setTipoEtiqueta(String tipoEtiqueta) {
        this.tipoEtiqueta = tipoEtiqueta;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getAlmacenDevoluciones() {
        return almacenDevoluciones;
    }

    public void setAlmacenDevoluciones(int almacenDevoluciones) {
        this.almacenDevoluciones = almacenDevoluciones;
    }

    public int getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(int anticipos) {
        this.anticipos = anticipos;
    }

    public String getLineaPrincipal() {
        return lineaPrincipal;
    }

    public void setLineaPrincipal(String lineaPrincipal) {
        this.lineaPrincipal = lineaPrincipal;
    }

    public String getSinonimoPrincipal() {
        return sinonimoPrincipal;
    }

    public void setSinonimoPrincipal(String sinonimoPrincipal) {
        this.sinonimoPrincipal = sinonimoPrincipal;
    }

    public String getReportePedidos() {
        return reportePedidos;
    }

    public void setReportePedidos(String reportePedidos) {
        this.reportePedidos = reportePedidos;
    }

    public String getABC() {
        return ABC;
    }

    public void setABC(String ABC) {
        this.ABC = ABC;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }

    public int getParetto() {
        return paretto;
    }

    public void setParetto(int paretto) {
        this.paretto = paretto;
    }

    public double getPrecioVenta2() {
        return precioVenta2;
    }

    public void setPrecioVenta2(double precioVenta2) {
        this.precioVenta2 = precioVenta2;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    public String getPedidosPrecio1() {
        return pedidosPrecio1;
    }

    public void setPedidosPrecio1(String pedidosPrecio1) {
        this.pedidosPrecio1 = pedidosPrecio1;
    }




}


