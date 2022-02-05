/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores;

/**
 *
 * @author ALL
 */
public class CGlobalConfig {
    
 //     static String conexion = "jdbc:mysql://192.168.1.95:3306/llv";
 //   static String conexion = "jdbc:mysql://localhost:3306/llv";
    static boolean web=true;
    static String conexion = "jdbc:mysql://192.168.1.90:3306/llv";
    static int bgcolor1 = 188;
    static int bgcolor2 = 210;
    static int bgcolor3 = 238;
    
    static String campli1 = "COLISIÓN Y MECÁNICA S.A DE C.V";
    static String campli2 = "SALVADOR DIAZ MIRÓN NO. 306";
    static String campli3 = "COLONIA SANCHEZ COLIN C.P. 50150";
    static String campli4 = "TOLUCA, EDO. DE MÉX.";
    static String campli5 = "R.F.C CME000515HX7";
    static String campli6 = "TELS. (01722)212-15-14,";
    static String campli7 = "219-16-66,2 19-23-18";
    
    static boolean descuentosClientes=true;
    static boolean utilidadesClientes=true;
    static boolean ofertas=false;
    static boolean cambiarPrecioArticulo=true;
    static boolean aplicarDescuentoArticulo=true;
    static boolean aplicarUtilidadArticulo=true;
    static double ivaVenta = 0.16;
    static double utilidad = 0.05;
    //facturas
    
    static String direccion_certificado = "00001000000202700591.cer";
    static String direccion_key = "cme000515hx7_1212271755s.key";
    
 //   static String direccion_certificado = "emisor.cer";
 //   static String direccion_key = "emisor.key";
 //   static String direccion_jks = "emisor.jks";
      static String direccion_jks = "comsajks.jks";

     static String contrasena_key = "CME000515";
 //  static String contrasena_key = "12345678a";
 //  static String keyStorepwd = "12345678a"; 
     static String keyStorepwd = "CME000515"; 
    
    // static String direccion_certificado = "C:/Users/ALL/Documents/sellos/00001000000102527347.cer";
    // static String direccion_key = "C:/Users/ALL/Documents/sellos/cme000515hx7_1012281424s.key";
 
  //  static String wsUsuario = "CME000515HX7_56";
  //    static String wsUsuario = "CME000515HX7_1";
     static String wsUsuario = "";
    
  //  static String wsPassword = "153078292972477042058434";
  //    static String wsPassword = "614227647743163434386083";
    static String wsPassword = "";
    
    
//    static String contrasena_key = "12345678a";  
//    static String private_key_alias = "certificadoprueba";
    static String private_key_alias = "comsajks";
    static String keystore_type = "JKS";
   
   
    
    static String factura_folioInicial="1";
    static String factura_folioFinal="20000";
    static String factura_regimen="Régimen General de Ley";
    static String factura_tipoComprobante="ingreso";
    static String factura_emisorRFC="CME000515HX7";
  //  static String factura_emisorRFC="AAA010101AAA";
    static String factura_emisorNombre="COLISION Y MECANICA S.A DE C.V.";
    static String factura_emisorCalle="SALVADOR DIAZ MIRON";
    static String factura_emisorNumero="306";
    static String factura_emisorColonia="SANCHEZ COLIN";
    static String factura_emisorCP="50150";
    static String factura_emisorCiudad="TOLUCA";
    static String factura_emisorEstado="ESTADO DE MÉXICO";
    static String factura_emisorPais="MÉXICO";
    static String factura_experidoCalle="SALVADOR DIAZ MIRON";
    static String factura_experidoNumero="306";
    static String factura_experidoColonia="SANCHEZ COLIN";
    static String factura_experidoCP="50150";
    static String factura_experidoCiudad="TOLUCA";
    static String factura_experidoEstado="ESTADO DE MÉXICO";
    static String factura_experidoPais="MÉXICO";
    static String factura_serie="";
    static String factura_anoAprobacion="2012";
    static String factura_noAprobacion="106833";
    static String factura_certificado="00001000000202700591";
    static String factura_mensaje1="ESTE DOCUMENTO ES UNA IMPRESIÓN DE UN COMPROBANTE FISCAL DIGITAL";
    static String factura_mensaje2="Pago hecho en una sola exhibición";
    
    static String notaCredito_tipoComprobante="egreso";
    
    //módulo de pedidos para pinosuarez almacén
    static boolean pedidos=true;
    static long[] id_cliente={34,3298};
    static String[] men_cliente={"SUCURSAL PINOSUAREZ","SUCURSAL SAN MATEO"};
    
    static String[] titulos_permisos=
    {"Panel Catálogos",
     "Panel Ventas",
     "Panel Pedidos",
     "Panel Reservaciones",
     "Panel Reportes",
     "Panel Aplicaciones",
     "Panel Compras",
     "Panel Anticipos",
     "Catálogo de Artículos",
     "Catálogo de Ubicaciones",
     "Catálogo de Lineas",
     "Catálogo de Usuarios",
     "Catálogo de Clientes",
     "Catálogo de Proveedores",
     "Realizar Ventas",
     "Catálogo de Ventas",
     "Catálogo de Facturas",
     "Ventas a Crédito",
     "Kardex",
     "Catálogo de Devoluciones",
     "Almacén de Devoluciones",
     "Cierres",
     "Reimpresiones",
     "Catálogo de Pedidos",
     "Backorder",
     "Catálogo de Reservaciones",
     "Artículos Reservados",
     "Reporte de Paretto",
     "Reporte de Almacén",
     "Reporte de Comisiones",
     "Reporte de Pedidos",
     "Reporte de Utilidades",
     "Addenda de Qualitas",
     "Módulo de Inventario",
     "Módulo de Etiquetas",
     "Módulo de Compras",
     "Módulo de Cuentas por cobrar",
     "Módulo de Cuentas por pagar",
     "Realizar Anticipos",
     "Catálogo de Ventas de Anticipos",
     "Catálogo de Anticipos",
     "Módulo Artículos en Anticipos",
     "Alta de Artículos",
     "Búsqueda de Artículos",
     "Eliminación de Artículos",
     "Alta de Ubicaciones",
     "Búsqueda de Ubicaciones",
     "Eliminación de Ubicaciones",
     "Alta de Líneas",
     "Búsqueda de Líneas",
     "Eliminaciónd de Líneas",
     "Alta de Usuarios",
     "Búsqueda de Usuarios",
     "Eliminación de Usuarios",
     "Alta de Clientes",
     "Búsqueda de Clientes",
     "Eliminación de Clientes",
     "Alta de Proveedores",
     "Búsqueda de Proveedores",
     "Eliminación de Proveedores",
     "Alta de Ventas",
     "Cancelar factura sin aviso a SAT",
     "Camceñación de CFDI",
     "Alta de Devoluciones",
     "Eliminación de Devoluciones",
     "Enviar a Almacén de Devoluciones",
     "Buscar Cierre",
     "Imprimir Carta en Cierre",
     "Imprimir Ticket en Cierre",
     "Alta de pedidos",
     "Búsqueda de Pedidos",
     "Eliminación de Pedidos",
     "Cancelación de Pedidos",
     "Visualizar Pedidos Cancelados",
     "Ver backorder",
     "Alta BackOrder",
     "Eliminación de BackOrder",
     "Alta de Reservaciones",
     "Búsqueda de Reservaciones",
     "Eliminación de Reservaciones",
     "Surtido Automático de Anticipos",
     "Alta de Anticipos",
     "Búsqueda de Anticipos",
     "Cancelación de Anticipos",
     "Cancelación de Ventas en Anticipos",
     "Actualización de Reservaciones",
     "Actualización de Anticipos",
     "Actualización de Artículos",
     "Actualización de Clientes",
     "Actualización de Pedidos",
     "Devolución de Ventas",
     "Devolución de Ventas en Anticipos"
    };
 
    
    static String campor1="Siniestro";
    static String campor2="Taller";
    static String campor3="Marca";
    static String campor4="No. de Serie"; 
    static String campor5="No. de Motor";
    static String campor6="Tipo";
    static String campor7="Reporte";
    static String campor8="Modelo";
    static String campor9="Color";
    static String campor10="Póliza";
    static String campor11="Puertas";
    static String campor12="Asegurado";
    static String campor13="Vale";
    
    static String campoa1="Propietario del Vehículo";
    static String campoa2="Teléfono";
    static String campoa3="Año";
    static String campoa4="Puertas";
    static String campoa5="Número de Serie";
    static String campoa6="Tipo";
    static String campoa7="Lado";
    static String campoa8="Transmisión";
    static String camposelecta7[]={"Izquierdo","Derecho","Frontal","Trasero","Sin Lado"};
    static String camposelecta8[]={"Standard","Automático"};
    
    static String prov1="0";
    static String prov2="0";
    static String prov3="0";
    static String prov4="0";
    static String prov5="0";
    
    static double anchoImp=215.4330706464;
    static double altoImp=841.8897629208;
    static double xImp=20;
    static double yImp=0;
    static double caracteresMaximos = 32;
    
    static String nombreSucursal[]={"San Mateo"};
    static String esWeb[]={"false"}; 
    static String direccionSucursal[]={"jdbc:mysql://192.168.1.95:3306/llvserver"}; 
    
    static String rutaRespaldoXMLS="\\\\192.168.1.90\\Reports\\xmls\\";
    static String rutaRespaldoPDFS="\\\\192.168.1.90\\Reports\\pdfs\\";
    
    static String rutaPlugin[]={""};
    static String nombrePlugin[]={""};
    static String tooltipoPlugin[]={""};
    static String clasePlugin[]={""};
    static String imagenPlugin[]={""};
    static boolean habilitarRed=false;
    static String usuarioRed="";

    public static String[] getClasePlugin() {
        return clasePlugin;
    }

    public static void setClasePlugin(String[] clasePlugin) {
        CGlobalConfig.clasePlugin = clasePlugin;
    }

    public static String[] getNombrePlugin() {
        return nombrePlugin;
    }

    public static void setNombrePlugin(String[] nombrePlugin) {
        CGlobalConfig.nombrePlugin = nombrePlugin;
    }

    public static String[] getRutaPlugin() {
        return rutaPlugin;
    }

    public static void setRutaPlugin(String[] rutaPlugin) {
        CGlobalConfig.rutaPlugin = rutaPlugin;
    }

    public static String[] getTooltipoPlugin() {
        return tooltipoPlugin;
    }

    public static void setTooltipoPlugin(String[] tooltipoPlugin) {
        CGlobalConfig.tooltipoPlugin = tooltipoPlugin;
    }
    
    
    public static long[] getId_cliente() {
        return id_cliente;
    }

    public static void setId_cliente(long[] id_cliente) {
        CGlobalConfig.id_cliente = id_cliente;
    }

    public static String[] getMen_cliente() {
        return men_cliente;
    }

    public static void setMen_cliente(String[] men_cliente) {
        CGlobalConfig.men_cliente = men_cliente;
    }

    public static boolean isPedidos() {
        return pedidos;
    }

    public static void setPedidos(boolean pedidos) {
        CGlobalConfig.pedidos = pedidos;
    } 

    
    
    public static boolean isAplicarDescuentoArticulo() {
        return aplicarDescuentoArticulo;
    }

    public static void setAplicarDescuentoArticulo(boolean aplicarDescuentoArticulo) {
        CGlobalConfig.aplicarDescuentoArticulo = aplicarDescuentoArticulo;
    }

    public static boolean isAplicarUtilidadArticulo() {
        return aplicarUtilidadArticulo;
    }

    public static void setAplicarUtilidadArticulo(boolean aplicarUtilidadArticulo) {
        CGlobalConfig.aplicarUtilidadArticulo = aplicarUtilidadArticulo;
    }

    public static boolean isCambiarPrecioArticulo() {
        return cambiarPrecioArticulo;
    }

    public static void setCambiarPrecioArticulo(boolean cambiarPrecioArticulo) {
        CGlobalConfig.cambiarPrecioArticulo = cambiarPrecioArticulo;
    }

    public static boolean isDescuentosClientes() {
        return descuentosClientes;
    }

    public static void setDescuentosClientes(boolean descuentosClientes) {
        CGlobalConfig.descuentosClientes = descuentosClientes;
    }

    public static boolean isOfertas() {
        return ofertas;
    }

    public static void setOfertas(boolean ofertas) {
        CGlobalConfig.ofertas = ofertas;
    }

    public static boolean isUtilidadesClientes() {
        return utilidadesClientes;
    }

    public static void setUtilidadesClientes(boolean utilidadesClientes) {
        CGlobalConfig.utilidadesClientes = utilidadesClientes;
    }

    public static String getFactura_anoAprobacion() {
        return factura_anoAprobacion;
    }

    public static void setFactura_anoAprobacion(String factura_anoAprobacion) {
        CGlobalConfig.factura_anoAprobacion = factura_anoAprobacion;
    }

    public static String getFactura_certificado() {
        return factura_certificado;
    } 

    public static void setFactura_certificado(String factura_certificado) {
        CGlobalConfig.factura_certificado = factura_certificado;
    }

    public static String getFactura_emisorCP() {
        return factura_emisorCP;
    }

    public static void setFactura_emisorCP(String factura_emisorCP) {
        CGlobalConfig.factura_emisorCP = factura_emisorCP;
    }

    public static String getFactura_emisorCalle() {
        return factura_emisorCalle;
    }

    public static void setFactura_emisorCalle(String factura_emisorCalle) {
        CGlobalConfig.factura_emisorCalle = factura_emisorCalle;
    }

    public static String getFactura_emisorCiudad() {
        return factura_emisorCiudad;
    }

    public static void setFactura_emisorCiudad(String factura_emisorCiudad) {
        CGlobalConfig.factura_emisorCiudad = factura_emisorCiudad;
    }

    public static String getFactura_emisorColonia() {
        return factura_emisorColonia;
    }

    public static void setFactura_emisorColonia(String factura_emisorColonia) {
        CGlobalConfig.factura_emisorColonia = factura_emisorColonia;
    }

    public static String getFactura_emisorEstado() {
        return factura_emisorEstado;
    }

    public static void setFactura_emisorEstado(String factura_emisorEstado) {
        CGlobalConfig.factura_emisorEstado = factura_emisorEstado;
    }

    public static String getFactura_emisorNombre() {
        return factura_emisorNombre;
    }

    public static void setFactura_emisorNombre(String factura_emisorNombre) {
        CGlobalConfig.factura_emisorNombre = factura_emisorNombre;
    }

    public static String getFactura_emisorNumero() {
        return factura_emisorNumero;
    }

    public static void setFactura_emisorNumero(String factura_emisorNumero) {
        CGlobalConfig.factura_emisorNumero = factura_emisorNumero;
    }

    public static String getFactura_emisorPais() {
        return factura_emisorPais;
    }

    public static void setFactura_emisorPais(String factura_emisorPais) {
        CGlobalConfig.factura_emisorPais = factura_emisorPais;
    }

    public static String getFactura_emisorRFC() {
        return factura_emisorRFC;
    }

    public static void setFactura_emisorRFC(String factura_emisorRFC) {
        CGlobalConfig.factura_emisorRFC = factura_emisorRFC;
    }

    public static String getFactura_experidoCP() {
        return factura_experidoCP;
    }

    public static void setFactura_experidoCP(String factura_experidoCP) {
        CGlobalConfig.factura_experidoCP = factura_experidoCP;
    }

    public static String getFactura_experidoCalle() {
        return factura_experidoCalle;
    }

    public static void setFactura_experidoCalle(String factura_experidoCalle) {
        CGlobalConfig.factura_experidoCalle = factura_experidoCalle;
    }

    public static String getFactura_experidoCiudad() {
        return factura_experidoCiudad;
    }

    public static void setFactura_experidoCiudad(String factura_experidoCiudad) {
        CGlobalConfig.factura_experidoCiudad = factura_experidoCiudad;
    }

    public static String getFactura_experidoColonia() {
        return factura_experidoColonia;
    }

    public static void setFactura_experidoColonia(String factura_experidoColonia) {
        CGlobalConfig.factura_experidoColonia = factura_experidoColonia;
    }

    public static String getFactura_experidoEstado() {
        return factura_experidoEstado;
    }

    public static void setFactura_experidoEstado(String factura_experidoEstado) {
        CGlobalConfig.factura_experidoEstado = factura_experidoEstado;
    }

    public static String getFactura_experidoNumero() {
        return factura_experidoNumero;
    }

    public static void setFactura_experidoNumero(String factura_experidoNumero) {
        CGlobalConfig.factura_experidoNumero = factura_experidoNumero;
    }

    public static String getFactura_experidoPais() {
        return factura_experidoPais;
    }

    public static void setFactura_experidoPais(String factura_experidoPais) {
        CGlobalConfig.factura_experidoPais = factura_experidoPais;
    }

    public static String getFactura_mensaje1() {
        return factura_mensaje1;
    }

    public static void setFactura_mensaje1(String factura_mensaje1) {
        CGlobalConfig.factura_mensaje1 = factura_mensaje1;
    }

    public static String getFactura_mensaje2() {
        return factura_mensaje2;
    }

    public static void setFactura_mensaje2(String factura_mensaje2) {
        CGlobalConfig.factura_mensaje2 = factura_mensaje2;
    }

    public static String getFactura_noAprobacion() {
        return factura_noAprobacion;
    }

    public static void setFactura_noAprobacion(String factura_noAprobacion) {
        CGlobalConfig.factura_noAprobacion = factura_noAprobacion;
    }

    public static String getFactura_serie() {
        return factura_serie;
    }

    public static void setFactura_serie(String factura_serie) {
        CGlobalConfig.factura_serie = factura_serie;
    }

    public static String getFactura_folioFinal() {
        return factura_folioFinal;
    }

    public static void setFactura_folioFinal(String factura_folioFinal) {
        CGlobalConfig.factura_folioFinal = factura_folioFinal;
    }

    public static String getFactura_folioInicial() {
        return factura_folioInicial;
    }

    public static void setFactura_folioInicial(String factura_folioInicial) {
        CGlobalConfig.factura_folioInicial = factura_folioInicial;
    }

    public static String getFactura_regimen() {
        return factura_regimen;
    }

    public static void setFactura_regimen(String factura_regimen) {
        CGlobalConfig.factura_regimen = factura_regimen;
    }

    public static String getFactura_tipoComprobante() {
        return factura_tipoComprobante;
    }

    public static void setFactura_tipoComprobante(String factura_tipoComprobante) {
        CGlobalConfig.factura_tipoComprobante = factura_tipoComprobante;
    }

    public static String getContrasena_key() {
        return contrasena_key;
    }

    public static void setContrasena_key(String contrasena_key) {
        CGlobalConfig.contrasena_key = contrasena_key;
    }

    public static String getDireccion_certificado() {
        return direccion_certificado;
    }

    public static void setDireccion_certificado(String direccion_certificado) {
        CGlobalConfig.direccion_certificado = direccion_certificado;
    }

    public static String getDireccion_key() {
        return direccion_key;
    }

    public static void setDireccion_key(String direccion_key) {
        CGlobalConfig.direccion_key = direccion_key;
    }

    public static String getNotaCredito_tipoComprobante() {
        return notaCredito_tipoComprobante;
    }

    public static void setNotaCredito_tipoComprobante(String notaCredito_tipoComprobante) {
        CGlobalConfig.notaCredito_tipoComprobante = notaCredito_tipoComprobante;
    }

    public static double getIvaVenta() {
        return ivaVenta;
    }

    public static void setIvaVenta(double ivaVenta) {
        CGlobalConfig.ivaVenta = ivaVenta;
    }

    public static String getConexion() {
        return conexion;
    }

    public static void setConexion(String conexion) {
        CGlobalConfig.conexion = conexion;
    }

    public static String getDireccion_jks() {
        return direccion_jks;
    }

    public static void setDireccion_jks(String direccion_jks) {
        CGlobalConfig.direccion_jks = direccion_jks;
    }

    public static String getKeyStorepwd() {
        return keyStorepwd;
    }

    public static void setKeyStorepwd(String keyStorepwd) {
        CGlobalConfig.keyStorepwd = keyStorepwd;
    }

    public static String getKeystore_type() {
        return keystore_type;
    }

    public static void setKeystore_type(String keystore_type) {
        CGlobalConfig.keystore_type = keystore_type;
    }

    public static String getPrivate_key_alias() {
        return private_key_alias;
    }

    public static void setPrivate_key_alias(String private_key_alias) {
        CGlobalConfig.private_key_alias = private_key_alias;
    }

    public static String getWsPassword() {
        return wsPassword;
    }

    public static void setWsPassword(String wsPassword) {
        CGlobalConfig.wsPassword = wsPassword;
    }

    public static String getWsUsuario() {
        return wsUsuario;
    }

    public static void setWsUsuario(String wsUsuario) {
        CGlobalConfig.wsUsuario = wsUsuario;
    }


    public static String[] getTitulos_permisos() {
        return titulos_permisos;
    }

    public static void setTitulos_permisos(String[] titulos_permisos) {
        CGlobalConfig.titulos_permisos = titulos_permisos;
    }

    public static String getCampoa1() {
        return campoa1;
    }

    public static void setCampoa1(String campoa1) {
        CGlobalConfig.campoa1 = campoa1;
    }

    public static String getCampoa2() {
        return campoa2;
    }

    public static void setCampoa2(String campoa2) {
        CGlobalConfig.campoa2 = campoa2;
    }

    public static String getCampoa3() {
        return campoa3;
    }

    public static void setCampoa3(String campoa3) {
        CGlobalConfig.campoa3 = campoa3;
    }

    public static String getCampoa4() {
        return campoa4;
    }

    public static void setCampoa4(String campoa4) {
        CGlobalConfig.campoa4 = campoa4;
    }

    public static String getCampoa5() {
        return campoa5;
    }

    public static void setCampoa5(String campoa5) {
        CGlobalConfig.campoa5 = campoa5;
    }

    public static String getCampoa6() {
        return campoa6;
    }

    public static void setCampoa6(String campoa6) {
        CGlobalConfig.campoa6 = campoa6;
    }

    public static String getCampoa7() {
        return campoa7;
    }

    public static void setCampoa7(String campoa7) {
        CGlobalConfig.campoa7 = campoa7;
    }

    public static String getCampoa8() {
        return campoa8;
    }

    public static void setCampoa8(String campoa8) {
        CGlobalConfig.campoa8 = campoa8;
    }

    public static String getCampor1() {
        return campor1;
    }

    public static void setCampor1(String campor1) {
        CGlobalConfig.campor1 = campor1;
    }

    public static String getCampor10() {
        return campor10;
    }

    public static void setCampor10(String campor10) {
        CGlobalConfig.campor10 = campor10;
    }

    public static String getCampor11() {
        return campor11;
    }

    public static void setCampor11(String campor11) {
        CGlobalConfig.campor11 = campor11;
    }

    public static String getCampor12() {
        return campor12;
    }

    public static void setCampor12(String campor12) {
        CGlobalConfig.campor12 = campor12;
    }

    public static String getCampor13() {
        return campor13;
    }

    public static void setCampor13(String campor13) {
        CGlobalConfig.campor13 = campor13;
    }

    public static String getCampor2() {
        return campor2;
    }

    public static void setCampor2(String campor2) {
        CGlobalConfig.campor2 = campor2;
    }

    public static String getCampor3() {
        return campor3;
    }

    public static void setCampor3(String campor3) {
        CGlobalConfig.campor3 = campor3;
    }

    public static String getCampor4() {
        return campor4;
    }

    public static void setCampor4(String campor4) {
        CGlobalConfig.campor4 = campor4;
    }

    public static String getCampor5() {
        return campor5;
    }

    public static void setCampor5(String campor5) {
        CGlobalConfig.campor5 = campor5;
    }

    public static String getCampor6() {
        return campor6;
    }

    public static void setCampor6(String campor6) {
        CGlobalConfig.campor6 = campor6;
    }

    public static String getCampor7() {
        return campor7;
    }

    public static void setCampor7(String campor7) {
        CGlobalConfig.campor7 = campor7;
    }

    public static String getCampor8() {
        return campor8;
    }

    public static void setCampor8(String campor8) {
        CGlobalConfig.campor8 = campor8;
    }

    public static String getCampor9() {
        return campor9;
    }

    public static void setCampor9(String campor9) {
        CGlobalConfig.campor9 = campor9;
    }

    public static String[] getCamposelecta7() {
        return camposelecta7;
    }

    public static void setCamposelecta7(String[] camposelecta7) {
        CGlobalConfig.camposelecta7 = camposelecta7;
    }

    public static String[] getCamposelecta8() {
        return camposelecta8;
    }

    public static void setCamposelecta8(String[] camposelecta8) {
        CGlobalConfig.camposelecta8 = camposelecta8;
    }

    public static double getUtilidad() {
        return utilidad;
    }

    public static void setUtilidad(double utilidad) {
        CGlobalConfig.utilidad = utilidad;
    }

    public static int getBgcolor1() {
        return bgcolor1;
    }

    public static void setBgcolor1(int bgcolor1) {
        CGlobalConfig.bgcolor1 = bgcolor1;
    }

    public static int getBgcolor2() {
        return bgcolor2;
    }

    public static void setBgcolor2(int bgcolor2) {
        CGlobalConfig.bgcolor2 = bgcolor2;
    }

    public static int getBgcolor3() {
        return bgcolor3;
    }

    public static void setBgcolor3(int bgcolor3) {
        CGlobalConfig.bgcolor3 = bgcolor3;
    }

    public static String getCampli1() {
        return campli1;
    }

    public static void setCampli1(String campli1) {
        CGlobalConfig.campli1 = campli1;
    }

    public static String getCampli2() {
        return campli2;
    }

    public static void setCampli2(String campli2) {
        CGlobalConfig.campli2 = campli2;
    }

    public static String getCampli3() {
        return campli3;
    }

    public static void setCampli3(String campli3) {
        CGlobalConfig.campli3 = campli3;
    }

    public static String getCampli4() {
        return campli4;
    }

    public static void setCampli4(String campli4) {
        CGlobalConfig.campli4 = campli4;
    }

    public static String getCampli5() {
        return campli5;
    }

    public static void setCampli5(String campli5) {
        CGlobalConfig.campli5 = campli5;
    }

    public static String getCampli6() {
        return campli6;
    }

    public static void setCampli6(String campli6) {
        CGlobalConfig.campli6 = campli6;
    }

    public static String getCampli7() {
        return campli7;
    }

    public static void setCampli7(String campli7) {
        CGlobalConfig.campli7 = campli7;
    }

    public static double getAltoImp() {
        return altoImp;
    }

    public static void setAltoImp(double altoImp) {
        CGlobalConfig.altoImp = altoImp;
    }

    public static double getAnchoImp() {
        return anchoImp;
    }

    public static void setAnchoImp(double anchoImp) {
        CGlobalConfig.anchoImp = anchoImp;
    }

    public static double getxImp() {
        return xImp;
    }

    public static void setxImp(double xImp) {
        CGlobalConfig.xImp = xImp;
    }

    public static double getyImp() {
        return yImp;
    }

    public static void setyImp(double yImp) {
        CGlobalConfig.yImp = yImp;
    }

    public static double getCaracteresMaximos() {
        return caracteresMaximos;
    }

    public static void setCaracteresMaximos(double caracteresMaximos) {
        CGlobalConfig.caracteresMaximos = caracteresMaximos;
    }

    public static String getProv1() {
        return prov1;
    }

    public static void setProv1(String prov1) {
        CGlobalConfig.prov1 = prov1;
    }

    public static String getProv2() {
        return prov2;
    }

    public static void setProv2(String prov2) {
        CGlobalConfig.prov2 = prov2;
    }

    public static String getProv3() {
        return prov3;
    }

    public static void setProv3(String prov3) {
        CGlobalConfig.prov3 = prov3;
    }

    public static String getProv4() {
        return prov4;
    }

    public static void setProv4(String prov4) {
        CGlobalConfig.prov4 = prov4;
    }

    public static String getProv5() {
        return prov5;
    }

    public static void setProv5(String prov5) {
        CGlobalConfig.prov5 = prov5;
    }

    public static String[] getDireccionSucursal() {
        return direccionSucursal;
    }

    public static void setDireccionSucursal(String[] direccionSucursal) {
        CGlobalConfig.direccionSucursal = direccionSucursal;
    }

    public static String[] getNombreSucursal() {
        return nombreSucursal;
    }

    public static void setNombreSucursal(String[] nombreSucursal) {
        CGlobalConfig.nombreSucursal = nombreSucursal;
    }
    
    public static String[] getEsWeb() {
        return esWeb;
    }

    public static void setEsWeb(String[] esWeb) {
        CGlobalConfig.esWeb = esWeb;
    }

    public static String getRutaRespaldoPDFS() {
        return rutaRespaldoPDFS;
    }

    public static void setRutaRespaldoPDFS(String rutaRespaldoPDFS) {
        CGlobalConfig.rutaRespaldoPDFS = rutaRespaldoPDFS;
    }

    public static String getRutaRespaldoXMLS() {
        return rutaRespaldoXMLS;
    }

    public static void setRutaRespaldoXMLS(String rutaRespaldoXMLS) {
        CGlobalConfig.rutaRespaldoXMLS = rutaRespaldoXMLS;
    }

    public static boolean isWeb() {
        return web;
    }

    public static void setWeb(boolean web) {
        CGlobalConfig.web = web;
    }

    public static String[] getImagenPlugin() {
        return imagenPlugin;
    }

    public static void setImagenPlugin(String[] imagenPlugin) {
        CGlobalConfig.imagenPlugin = imagenPlugin;
    }

    public static boolean isHabilitarRed() {
        return habilitarRed;
    }

    public static void setHabilitarRed(boolean habilitarRed) {
        CGlobalConfig.habilitarRed = habilitarRed;
    }

    public static String getUsuarioRed() {
        return usuarioRed;
    }

    public static void setUsuarioRed(String usuarioRed) {
        CGlobalConfig.usuarioRed = usuarioRed;
    }



  

   
    
    
}
