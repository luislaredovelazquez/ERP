/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores;

/**
 *
 * @author ALL
 */
public class CConfiguracion {
    
    static String nombres;
    static String apellidoP;
    static String apellidoM;
    static String contrasena;
    static String puesto;
    static String correo;
    static String foto;
    static long id;
    static String meta;
    static String metaPasada;
    static String metaPasada2;
    
    static boolean  panelCatalogos=true;
    static boolean  panelVentas=true;
    static boolean  panelPedidos=true;
    static boolean  panelReservaciones=true;
    static boolean  panelReportes=true;;
    static boolean  panelAplicaciones=true;
    static boolean  panelCompras=true;
    static boolean  panelAnticipos=true;
    
    //panel 1
    
    static boolean articulos=true;
    static boolean ubicaciones=true;
    static boolean lineas=true;
    static boolean usuarios=true;
    static boolean clientes=true;
    static boolean proveedores=true;
    
    // panel 2
    
    static boolean realizarVenta=true;
    static boolean catalogoVentas=true;
    static boolean catalogoFacturas=true;
    static boolean kardex=true;
    static boolean catalogoDevoluciones=true;
    static boolean AlmacenDevoluciones=true;
    static boolean cierres=true;
    static boolean reimpresiones=true;
    
    //panel 3
    
    static boolean catPedidos=true;
    static boolean pedidos=true;
    
    //panel 4
    
    static boolean reservaciones=true;
    static boolean articulosReservaciones=true;
    
    //panel 5
    
    static boolean reporteParetto=true;
    static boolean reporteAlmacen=true;
    static boolean reporteComisiones=true;
    static boolean reportePedidos=true;
    static boolean reporteUtilidades=true;
    static boolean reporteClientes=true;
            
    //panel 6
    static boolean inventario=true;
    static boolean etiquetas=true;
    static boolean respaldo=true;
    
    //panel 7
    static boolean cuentasporcobrar=true;
    static boolean cuentasporpagar=true;
    
    //panel 8
    
    static boolean realizarAnticipos=true;
    static boolean ventasAnticipos=true;
    static boolean catalogoAnticipos=true;
    static boolean articulosAnticipos=true;
    
    //panel 1 - articulos
    static boolean altaArticulos=true;
    static boolean buscarArticulos=true;
    static boolean eliminarArticulos=true;
    
    //panel 1 - ubicaciones
    static boolean altaUbicaciones=true;
    static boolean buscarUbicaciones=true;
    static boolean eliminarUbicaciones=true;    
    
    //panel 1 - lineas
    static boolean altaLineas=true;
    static boolean buscarLineas=true;
    static boolean eliminarLineas=true;        
    
    
    //panel 1 - usuarios
    static boolean altaUsuarios=true;
    static boolean buscarUsuarios=true;
    static boolean eliminarUsuarios=true;    
    
    //panel 1 - clientes
    static boolean altaClientes=true;
    static boolean buscarClientes=true;
    static boolean eliminarClientes=true;    
    
   //panel 1 - proveedores
    static boolean altaProveedores=true;
    static boolean buscarProveedores=true;
    static boolean eliminarProveedores=true;        
    
    //panel 2 
    static boolean altaVentas=true;
    static boolean cancelarCFD=true;
    static boolean cancelarCFDI=true;
    static boolean altaDevoluciones=true;
    static boolean eliminarDevoluciones=true;
    static boolean enviarAlmacenDevoluciones=true;
    static boolean buscarCierre=true;
    static boolean imprimirCarta=true;
    static boolean imprimir=true;
    
    //panel 3
    static boolean altaPedidos=true;
    static boolean buscarPedidos=true;
    static boolean eliminarPedidos=true;
    static boolean cancelarPedidos=true;
    static boolean verCancelados=true;
    
    static boolean verBackOrder=true;
    static boolean altaBackOrder=true;
    static boolean eliminarBackOrder=true;
    
    //panel 4
    static boolean altaReservaciones=true;
    static boolean buscarReservaciones=true;
    static boolean eliminarReservaciones=true;
    
    //panel 8
    static boolean surtirAnticipos=true;
    static boolean altaAnticipos=true;
    static boolean buscarAnticipos=true;
    static boolean cancelarAnticipos=true;
    static boolean cancelarVentaAnticipos=true;
    static boolean facturasparciales=true;
    
    //otros
    static boolean actualizarReservaciones=true;
    static boolean actualizarAnticipos=true;
    static boolean actualizarArticulos=true;
    static boolean actualizarClientes=true;
    static boolean actualizarPedidos=true;
    static boolean devolverVentas=true;
    static boolean devolverVentasAnticipos=true;
    
    static boolean parettoClientes=true;
    static boolean configuracion=true;
    static boolean ofertas=true;
    static boolean artMostrador=true;
    static boolean habilitarCTRL=true;
    
    static boolean mandarSucursal=true;
    static boolean registrarKardex=true;
    
    static boolean configurarPlugins=true;
    static boolean altaPlugins=true;
    static boolean buscarPlugins=true;
    static boolean eliminarPlugins=true;
    static boolean eliminarArtMostrador=true;
    
    
    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        CConfiguracion.id = id;
    }

    public static String getApellidoM() {
        return apellidoM;
    }

    public static void setApellidoM(String apellidoM) {
        CConfiguracion.apellidoM = apellidoM;
    }

    public static String getApellidoP() {
        return apellidoP;
    }

    public static void setApellidoP(String apellidoP) {
        CConfiguracion.apellidoP = apellidoP;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        CConfiguracion.contrasena = contrasena;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        CConfiguracion.correo = correo;
    }

    public static String getFoto() {
        return foto;
    }

    public static void setFoto(String foto) {
        CConfiguracion.foto = foto;
    }

    public static String getNombres() {
        return nombres;
    }

    public static void setNombres(String nombres) {
        CConfiguracion.nombres = nombres;
    }

    public static String getPuesto() {
        return puesto;
    }

    public static void setPuesto(String puesto) {
        CConfiguracion.puesto = puesto;
    }

    public static boolean isAlmacenDevoluciones() {
        return AlmacenDevoluciones;
    }

    public static void setAlmacenDevoluciones(boolean AlmacenDevoluciones) {
        CConfiguracion.AlmacenDevoluciones = AlmacenDevoluciones;
    }


    public static boolean isAltaAnticipos() {
        return altaAnticipos;
    }

    public static void setAltaAnticipos(boolean altaAnticipos) {
        CConfiguracion.altaAnticipos = altaAnticipos;
    }

    public static boolean isAltaArticulos() {
        return altaArticulos;
    }

    public static void setAltaArticulos(boolean altaArticulos) {
        CConfiguracion.altaArticulos = altaArticulos;
    }

    public static boolean isAltaBackOrder() {
        return altaBackOrder;
    }

    public static void setAltaBackOrder(boolean altaBackOrder) {
        CConfiguracion.altaBackOrder = altaBackOrder;
    }

    public static boolean isAltaClientes() {
        return altaClientes;
    }

    public static void setAltaClientes(boolean altaClientes) {
        CConfiguracion.altaClientes = altaClientes;
    }

    public static boolean isAltaDevoluciones() {
        return altaDevoluciones;
    }

    public static void setAltaDevoluciones(boolean altaDevoluciones) {
        CConfiguracion.altaDevoluciones = altaDevoluciones;
    }

    public static boolean isAltaLineas() {
        return altaLineas;
    }

    public static void setAltaLineas(boolean altaLineas) {
        CConfiguracion.altaLineas = altaLineas;
    }

    public static boolean isAltaPedidos() {
        return altaPedidos;
    }

    public static void setAltaPedidos(boolean altaPedidos) {
        CConfiguracion.altaPedidos = altaPedidos;
    }

    public static boolean isAltaProveedores() {
        return altaProveedores;
    }

    public static void setAltaProveedores(boolean altaProveedores) {
        CConfiguracion.altaProveedores = altaProveedores;
    }

    public static boolean isAltaReservaciones() {
        return altaReservaciones;
    }

    public static void setAltaReservaciones(boolean altaReservaciones) {
        CConfiguracion.altaReservaciones = altaReservaciones;
    }

    public static boolean isAltaUbicaciones() {
        return altaUbicaciones;
    }

    public static void setAltaUbicaciones(boolean altaUbicaciones) {
        CConfiguracion.altaUbicaciones = altaUbicaciones;
    }

    public static boolean isAltaUsuarios() {
        return altaUsuarios;
    }

    public static void setAltaUsuarios(boolean altaUsuarios) {
        CConfiguracion.altaUsuarios = altaUsuarios;
    }

    public static boolean isAltaVentas() {
        return altaVentas;
    }

    public static void setAltaVentas(boolean altaVentas) {
        CConfiguracion.altaVentas = altaVentas;
    }

    public static boolean isArticulos() {
        return articulos;
    }

    public static void setArticulos(boolean articulos) {
        CConfiguracion.articulos = articulos;
    }

    public static boolean isArticulosAnticipos() {
        return articulosAnticipos;
    }

    public static void setArticulosAnticipos(boolean articulosAnticipos) {
        CConfiguracion.articulosAnticipos = articulosAnticipos;
    }

    public static boolean isArticulosReservaciones() {
        return articulosReservaciones;
    }

    public static void setArticulosReservaciones(boolean articulosReservaciones) {
        CConfiguracion.articulosReservaciones = articulosReservaciones;
    }

    public static boolean isBuscarAnticipos() {
        return buscarAnticipos;
    }

    public static void setBuscarAnticipos(boolean buscarAnticipos) {
        CConfiguracion.buscarAnticipos = buscarAnticipos;
    }

    public static boolean isBuscarArticulos() {
        return buscarArticulos;
    }

    public static void setBuscarArticulos(boolean buscarArticulos) {
        CConfiguracion.buscarArticulos = buscarArticulos;
    }

    public static boolean isBuscarCierre() {
        return buscarCierre;
    }

    public static void setBuscarCierre(boolean buscarCierre) {
        CConfiguracion.buscarCierre = buscarCierre;
    }

    public static boolean isBuscarClientes() {
        return buscarClientes;
    }

    public static void setBuscarClientes(boolean buscarClientes) {
        CConfiguracion.buscarClientes = buscarClientes;
    }

    public static boolean isBuscarLineas() {
        return buscarLineas;
    }

    public static void setBuscarLineas(boolean buscarLineas) {
        CConfiguracion.buscarLineas = buscarLineas;
    }

    public static boolean isBuscarPedidos() {
        return buscarPedidos;
    }

    public static void setBuscarPedidos(boolean buscarPedidos) {
        CConfiguracion.buscarPedidos = buscarPedidos;
    }

    public static boolean isBuscarProveedores() {
        return buscarProveedores;
    }

    public static void setBuscarProveedores(boolean buscarProveedores) {
        CConfiguracion.buscarProveedores = buscarProveedores;
    }

    public static boolean isBuscarReservaciones() {
        return buscarReservaciones;
    }

    public static void setBuscarReservaciones(boolean buscarReservaciones) {
        CConfiguracion.buscarReservaciones = buscarReservaciones;
    }

    public static boolean isBuscarUbicaciones() {
        return buscarUbicaciones;
    }

    public static void setBuscarUbicaciones(boolean buscarUbicaciones) {
        CConfiguracion.buscarUbicaciones = buscarUbicaciones;
    }

    public static boolean isBuscarUsuarios() {
        return buscarUsuarios;
    }

    public static void setBuscarUsuarios(boolean buscarUsuarios) {
        CConfiguracion.buscarUsuarios = buscarUsuarios;
    }

    public static boolean isCancelarAnticipos() {
        return cancelarAnticipos;
    }

    public static void setCancelarAnticipos(boolean cancelarAnticipos) {
        CConfiguracion.cancelarAnticipos = cancelarAnticipos;
    }

    public static boolean isCancelarCFD() {
        return cancelarCFD;
    }

    public static void setCancelarCFD(boolean cancelarCFD) {
        CConfiguracion.cancelarCFD = cancelarCFD;
    }

    public static boolean isCancelarCFDI() {
        return cancelarCFDI;
    }

    public static void setCancelarCFDI(boolean cancelarCFDI) {
        CConfiguracion.cancelarCFDI = cancelarCFDI;
    }

    public static boolean isCancelarPedidos() {
        return cancelarPedidos;
    }

    public static void setCancelarPedidos(boolean cancelarPedidos) {
        CConfiguracion.cancelarPedidos = cancelarPedidos;
    }

    public static boolean isCancelarVentaAnticipos() {
        return cancelarVentaAnticipos;
    }

    public static void setCancelarVentaAnticipos(boolean cancelarVentaAnticipos) {
        CConfiguracion.cancelarVentaAnticipos = cancelarVentaAnticipos;
    }

    public static boolean isCatPedidos() {
        return catPedidos;
    }

    public static void setCatPedidos(boolean catPedidos) {
        CConfiguracion.catPedidos = catPedidos;
    }

    public static boolean isCatalogoAnticipos() {
        return catalogoAnticipos;
    }

    public static void setCatalogoAnticipos(boolean catalogoAnticipos) {
        CConfiguracion.catalogoAnticipos = catalogoAnticipos;
    }

    public static boolean isCatalogoDevoluciones() {
        return catalogoDevoluciones;
    }

    public static void setCatalogoDevoluciones(boolean catalogoDevoluciones) {
        CConfiguracion.catalogoDevoluciones = catalogoDevoluciones;
    }

    public static boolean isCatalogoFacturas() {
        return catalogoFacturas;
    }

    public static void setCatalogoFacturas(boolean catalogoFacturas) {
        CConfiguracion.catalogoFacturas = catalogoFacturas;
    }

    public static boolean isCatalogoVentas() {
        return catalogoVentas;
    }

    public static void setCatalogoVentas(boolean catalogoVentas) {
        CConfiguracion.catalogoVentas = catalogoVentas;
    }

    public static boolean isCierres() {
        return cierres;
    }

    public static void setCierres(boolean cierres) {
        CConfiguracion.cierres = cierres;
    }

    public static boolean isClientes() {
        return clientes;
    }

    public static void setClientes(boolean clientes) {
        CConfiguracion.clientes = clientes;
    }


    public static boolean isCuentasporcobrar() {
        return cuentasporcobrar;
    }

    public static void setCuentasporcobrar(boolean cuentasporcobrar) {
        CConfiguracion.cuentasporcobrar = cuentasporcobrar;
    }

    public static boolean isCuentasporpagar() {
        return cuentasporpagar;
    }

    public static void setCuentasporpagar(boolean cuentasporpagar) {
        CConfiguracion.cuentasporpagar = cuentasporpagar;
    }

    public static boolean isEliminarArticulos() {
        return eliminarArticulos;
    }

    public static void setEliminarArticulos(boolean eliminarArticulos) {
        CConfiguracion.eliminarArticulos = eliminarArticulos;
    }

    public static boolean isEliminarBackOrder() {
        return eliminarBackOrder;
    }

    public static void setEliminarBackOrder(boolean eliminarBackOrder) {
        CConfiguracion.eliminarBackOrder = eliminarBackOrder;
    }

    public static boolean isEliminarClientes() {
        return eliminarClientes;
    }

    public static void setEliminarClientes(boolean eliminarClientes) {
        CConfiguracion.eliminarClientes = eliminarClientes;
    }

    public static boolean isEliminarDevoluciones() {
        return eliminarDevoluciones;
    }

    public static void setEliminarDevoluciones(boolean eliminarDevoluciones) {
        CConfiguracion.eliminarDevoluciones = eliminarDevoluciones;
    }

    public static boolean isEliminarLineas() {
        return eliminarLineas;
    }

    public static void setEliminarLineas(boolean eliminarLineas) {
        CConfiguracion.eliminarLineas = eliminarLineas;
    }

    public static boolean isEliminarPedidos() {
        return eliminarPedidos;
    }

    public static void setEliminarPedidos(boolean eliminarPedidos) {
        CConfiguracion.eliminarPedidos = eliminarPedidos;
    }

    public static boolean isEliminarProveedores() {
        return eliminarProveedores;
    }

    public static void setEliminarProveedores(boolean eliminarProveedores) {
        CConfiguracion.eliminarProveedores = eliminarProveedores;
    }

    public static boolean isEliminarReservaciones() {
        return eliminarReservaciones;
    }

    public static void setEliminarReservaciones(boolean eliminarReservaciones) {
        CConfiguracion.eliminarReservaciones = eliminarReservaciones;
    }

    public static boolean isEliminarUbicaciones() {
        return eliminarUbicaciones;
    }

    public static void setEliminarUbicaciones(boolean eliminarUbicaciones) {
        CConfiguracion.eliminarUbicaciones = eliminarUbicaciones;
    }

    public static boolean isEliminarUsuarios() {
        return eliminarUsuarios;
    }

    public static void setEliminarUsuarios(boolean eliminarUsuarios) {
        CConfiguracion.eliminarUsuarios = eliminarUsuarios;
    }

    public static boolean isEnviarAlmacenDevoluciones() {
        return enviarAlmacenDevoluciones;
    }

    public static void setEnviarAlmacenDevoluciones(boolean enviarAlmacenDevoluciones) {
        CConfiguracion.enviarAlmacenDevoluciones = enviarAlmacenDevoluciones;
    }

    public static boolean isEtiquetas() {
        return etiquetas;
    }

    public static void setEtiquetas(boolean etiquetas) {
        CConfiguracion.etiquetas = etiquetas;
    }

    public static boolean isImprimir() {
        return imprimir;
    }

    public static void setImprimir(boolean imprimir) {
        CConfiguracion.imprimir = imprimir;
    }

    public static boolean isImprimirCarta() {
        return imprimirCarta;
    }

    public static void setImprimirCarta(boolean imprimirCarta) {
        CConfiguracion.imprimirCarta = imprimirCarta;
    }

    public static boolean isInventario() {
        return inventario;
    }

    public static void setInventario(boolean inventario) {
        CConfiguracion.inventario = inventario;
    }

    public static boolean isKardex() {
        return kardex;
    }

    public static void setKardex(boolean kardex) {
        CConfiguracion.kardex = kardex;
    }

    public static boolean isLineas() {
        return lineas;
    }

    public static void setLineas(boolean lineas) {
        CConfiguracion.lineas = lineas;
    }

    public static boolean isPanelAnticipos() {
        return panelAnticipos;
    }

    public static void setPanelAnticipos(boolean panelAnticipos) {
        CConfiguracion.panelAnticipos = panelAnticipos;
    }

    public static boolean isPanelAplicaciones() {
        return panelAplicaciones;
    }

    public static void setPanelAplicaciones(boolean panelAplicaciones) {
        CConfiguracion.panelAplicaciones = panelAplicaciones;
    }

    public static boolean isPanelCatalogos() {
        return panelCatalogos;
    }

    public static void setPanelCatalogos(boolean panelCatalogos) {
        CConfiguracion.panelCatalogos = panelCatalogos;
    }

    public static boolean isPanelCompras() {
        return panelCompras;
    }

    public static void setPanelCompras(boolean panelCompras) {
        CConfiguracion.panelCompras = panelCompras;
    }

    public static boolean isPanelPedidos() {
        return panelPedidos;
    }

    public static void setPanelPedidos(boolean panelPedidos) {
        CConfiguracion.panelPedidos = panelPedidos;
    }

    public static boolean isPanelReportes() {
        return panelReportes;
    }

    public static void setPanelReportes(boolean panelReportes) {
        CConfiguracion.panelReportes = panelReportes;
    }

    public static boolean isPanelReservaciones() {
        return panelReservaciones;
    }

    public static void setPanelReservaciones(boolean panelReservaciones) {
        CConfiguracion.panelReservaciones = panelReservaciones;
    }

    public static boolean isPanelVentas() {
        return panelVentas;
    }

    public static void setPanelVentas(boolean panelVentas) {
        CConfiguracion.panelVentas = panelVentas;
    }

    public static boolean isPedidos() {
        return pedidos;
    }

    public static void setPedidos(boolean pedidos) {
        CConfiguracion.pedidos = pedidos;
    }

    public static boolean isProveedores() {
        return proveedores;
    }

    public static void setProveedores(boolean proveedores) {
        CConfiguracion.proveedores = proveedores;
    }

    public static boolean isRealizarAnticipos() {
        return realizarAnticipos;
    }

    public static void setRealizarAnticipos(boolean realizarAnticipos) {
        CConfiguracion.realizarAnticipos = realizarAnticipos;
    }

    public static boolean isRealizarVenta() {
        return realizarVenta;
    }

    public static void setRealizarVenta(boolean realizarVenta) {
        CConfiguracion.realizarVenta = realizarVenta;
    }

    public static boolean isReimpresiones() {
        return reimpresiones;
    }

    public static void setReimpresiones(boolean reimpresiones) {
        CConfiguracion.reimpresiones = reimpresiones;
    }

    public static boolean isReporteAlmacen() {
        return reporteAlmacen;
    }

    public static void setReporteAlmacen(boolean reporteAlmacen) {
        CConfiguracion.reporteAlmacen = reporteAlmacen;
    }

    public static boolean isReporteComisiones() {
        return reporteComisiones;
    }

    public static void setReporteComisiones(boolean reporteComisiones) {
        CConfiguracion.reporteComisiones = reporteComisiones;
    }

    public static boolean isReporteParetto() {
        return reporteParetto;
    }

    public static void setReporteParetto(boolean reporteParetto) {
        CConfiguracion.reporteParetto = reporteParetto;
    }

    public static boolean isReportePedidos() {
        return reportePedidos;
    }

    public static void setReportePedidos(boolean reportePedidos) {
        CConfiguracion.reportePedidos = reportePedidos;
    }

    public static boolean isReporteUtilidades() {
        return reporteUtilidades;
    }

    public static void setReporteUtilidades(boolean reporteUtilidades) {
        CConfiguracion.reporteUtilidades = reporteUtilidades;
    }

    public static boolean isReservaciones() {
        return reservaciones;
    }

    public static void setReservaciones(boolean reservaciones) {
        CConfiguracion.reservaciones = reservaciones;
    }

    public static boolean isSurtirAnticipos() {
        return surtirAnticipos;
    }

    public static void setSurtirAnticipos(boolean surtirAnticipos) {
        CConfiguracion.surtirAnticipos = surtirAnticipos;
    }

    public static boolean isUbicaciones() {
        return ubicaciones;
    }

    public static void setUbicaciones(boolean ubicaciones) {
        CConfiguracion.ubicaciones = ubicaciones;
    }

    public static boolean isUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(boolean usuarios) {
        CConfiguracion.usuarios = usuarios;
    }


    public static boolean isVentasAnticipos() {
        return ventasAnticipos;
    }

    public static void setVentasAnticipos(boolean ventasAnticipos) {
        CConfiguracion.ventasAnticipos = ventasAnticipos;
    }

    public static boolean isVerBackOrder() {
        return verBackOrder;
    }

    public static void setVerBackOrder(boolean verBackOrder) {
        CConfiguracion.verBackOrder = verBackOrder;
    }

    public static boolean isVerCancelados() {
        return verCancelados;
    }

    public static void setVerCancelados(boolean verCancelados) {
        CConfiguracion.verCancelados = verCancelados;
    }

    public static boolean isActualizarAnticipos() {
        return actualizarAnticipos;
    }

    public static void setActualizarAnticipos(boolean actualizarAnticipos) {
        CConfiguracion.actualizarAnticipos = actualizarAnticipos;
    }

    public static boolean isActualizarArticulos() {
        return actualizarArticulos;
    }

    public static void setActualizarArticulos(boolean actualizarArticulos) {
        CConfiguracion.actualizarArticulos = actualizarArticulos;
    }

    public static boolean isActualizarClientes() {
        return actualizarClientes;
    }

    public static void setActualizarClientes(boolean actualizarClientes) {
        CConfiguracion.actualizarClientes = actualizarClientes;
    }

    public static boolean isActualizarPedidos() {
        return actualizarPedidos;
    }

    public static void setActualizarPedidos(boolean actualizarPedidos) {
        CConfiguracion.actualizarPedidos = actualizarPedidos;
    }

    public static boolean isActualizarReservaciones() {
        return actualizarReservaciones;
    }

    public static void setActualizarReservaciones(boolean actualizarReservaciones) {
        CConfiguracion.actualizarReservaciones = actualizarReservaciones;
    }

    public static boolean isDevolverVentas() {
        return devolverVentas;
    }

    public static void setDevolverVentas(boolean devolverVentas) {
        CConfiguracion.devolverVentas = devolverVentas;
    }

    public static boolean isDevolverVentasAnticipos() {
        return devolverVentasAnticipos;
    }

    public static void setDevolverVentasAnticipos(boolean devolverVentasAnticipos) {
        CConfiguracion.devolverVentasAnticipos = devolverVentasAnticipos;
    }

    public static String getMeta() {
        return meta;
    }

    public static void setMeta(String meta) {
        CConfiguracion.meta = meta;
    }

    public static String getMetaPasada() {
        return metaPasada;
    }

    public static void setMetaPasada(String metaPasada) {
        CConfiguracion.metaPasada = metaPasada;
    }

    public static String getMetaPasada2() {
        return metaPasada2;
    }

    public static void setMetaPasada2(String metaPasada2) {
        CConfiguracion.metaPasada2 = metaPasada2;
    }

    public static boolean isArtMostrador() {
        return artMostrador;
    }

    public static void setArtMostrador(boolean artMostrador) {
        CConfiguracion.artMostrador = artMostrador;
    }

    public static boolean isConfiguracion() {
        return configuracion;
    }

    public static void setConfiguracion(boolean configuracion) {
        CConfiguracion.configuracion = configuracion;
    }

    public static boolean isOfertas() {
        return ofertas;
    }

    public static void setOfertas(boolean ofertas) {
        CConfiguracion.ofertas = ofertas;
    }

    public static boolean isParettoClientes() {
        return parettoClientes;
    }

    public static void setParettoClientes(boolean parettoClientes) {
        CConfiguracion.parettoClientes = parettoClientes;
    }

    public static boolean isHabilitarCTRL() {
        return habilitarCTRL;
    }

    public static void setHabilitarCTRL(boolean habilitarCTRL) {
        CConfiguracion.habilitarCTRL = habilitarCTRL;
    }

    public static boolean isReporteClientes() {
        return reporteClientes;
    }

    public static void setReporteClientes(boolean reporteClientes) {
        CConfiguracion.reporteClientes = reporteClientes;
    }

    public static boolean isMandarSucursal() {
        return mandarSucursal;
    }

    public static void setMandarSucursal(boolean mandarSucursal) {
        CConfiguracion.mandarSucursal = mandarSucursal;
    }

    public static boolean isRegistrarKardex() {
        return registrarKardex;
    }

    public static void setRegistrarKardex(boolean registrarKardex) {
        CConfiguracion.registrarKardex = registrarKardex;
    }

    public static boolean isFacturasparciales() {
        return facturasparciales;
    }

    public static void setFacturasparciales(boolean facturasparciales) {
        CConfiguracion.facturasparciales = facturasparciales;
    }

    public static boolean isRespaldo() {
        return respaldo;
    }

    public static void setRespaldo(boolean respaldo) {
        CConfiguracion.respaldo = respaldo;
    }

    public static boolean isAltaPlugins() {
        return altaPlugins;
    }

    public static void setAltaPlugins(boolean altaPlugins) {
        CConfiguracion.altaPlugins = altaPlugins;
    }

    public static boolean isBuscarPlugins() {
        return buscarPlugins;
    }

    public static void setBuscarPlugins(boolean buscarPlugins) {
        CConfiguracion.buscarPlugins = buscarPlugins;
    }

    public static boolean isConfigurarPlugins() {
        return configurarPlugins;
    }

    public static void setConfigurarPlugins(boolean configurarPlugins) {
        CConfiguracion.configurarPlugins = configurarPlugins;
    }

    public static boolean isEliminarPlugins() {
        return eliminarPlugins;
    }

    public static void setEliminarPlugins(boolean eliminarPlugins) {
        CConfiguracion.eliminarPlugins = eliminarPlugins;
    }

    public static boolean isEliminarArtMostrador() {
        return eliminarArtMostrador;
    }

    public static void setEliminarArtMostrador(boolean eliminarArtMostrador) {
        CConfiguracion.eliminarArtMostrador = eliminarArtMostrador;
    }
    
    
    
    
}
