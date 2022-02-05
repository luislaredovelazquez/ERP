/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.controladores;

import com.paedeias.helpers.hConfiguracion;
import com.paedeias.helpers.hPermisos;
import com.paedeias.helpers.hUsuarios;
import com.paedeias.identidades.Permisos;
import com.paedeias.identidades.Usuarios;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Luis
 */
public class Autenticar {
    hUsuarios husuarios;
    hPermisos hpermisos;
    hConfiguracion hconfiguracion;
    List<Usuarios> listausuarios;
    Permisos permisos;
    long id=-1;
    Usuarios usuariogeneral;
    
       
public Autenticar()
{
    
}
    
    
public boolean validarLogin(String username, String password) {
    
         husuarios = new hUsuarios();
         hpermisos = new hPermisos();
         hconfiguracion = new hConfiguracion();
         
         listausuarios = husuarios.consultaUsuarios("foto", "=", username);
         if(listausuarios.isEmpty()||listausuarios == null)
         {
          JOptionPane.showMessageDialog(null, "No existe el usuario");   
          return false;   
         }
         
         permisos = hpermisos.consultaPermisos("idUsuario", "=", String.valueOf(listausuarios.get(0).getId()));
         if(permisos == null)
         {
          return false;   
         }         
         
         int i = 0;
         while (i<listausuarios.size())
         {
             
             
             if(listausuarios.get(i).getContrasena().equals(password))
             {                 
                 CConfiguracion.setApellidoM(listausuarios.get(i).getApellidoM());
                 CConfiguracion.setApellidoP(listausuarios.get(i).getApellidoP());
                 CConfiguracion.setContrasena(listausuarios.get(i).getContrasena());
                 CConfiguracion.setCorreo(listausuarios.get(i).getCorreo());
                 CConfiguracion.setFoto(listausuarios.get(i).getFoto());
                 CConfiguracion.setNombres(listausuarios.get(i).getNombres());
                 CConfiguracion.setPuesto(listausuarios.get(i).getPuesto());
                 CConfiguracion.setId(listausuarios.get(i).getId());
                 CConfiguracion.setMeta(listausuarios.get(i).getMeta());
                 CConfiguracion.setMetaPasada(listausuarios.get(i).getMetaPasada());
                 CConfiguracion.setMetaPasada2(listausuarios.get(i).getMetaPasada2());
                 
                 String[] permiso = permisos.getPermisos().split("@");
                  
                 if(permiso[0].equals("1"))                
                 CConfiguracion.setPanelCatalogos(false);
                 if(permiso[1].equals("1"))
                 CConfiguracion.setPanelVentas(false);
                 if(permiso[2].equals("1"))
                 CConfiguracion.setPanelPedidos(false);
                 if(permiso[3].equals("1"))
                 CConfiguracion.setPanelReservaciones(false);
                 if(permiso[4].equals("1"))
                 CConfiguracion.setPanelReportes(false);
                 if(permiso[5].equals("1"))
                 CConfiguracion.setPanelAplicaciones(false);
                 if(permiso[6].equals("1"))
                 CConfiguracion.setPanelCompras(false);
                 if(permiso[7].equals("1"))
                 CConfiguracion.setPanelAnticipos(false);
                 
                 if(permiso[8].equals("1"))
                 CConfiguracion.setArticulos(false);
                 if(permiso[9].equals("1"))
                 CConfiguracion.setUbicaciones(false);
                 if(permiso[10].equals("1"))
                 CConfiguracion.setLineas(false);
                 if(permiso[11].equals("1"))
                 CConfiguracion.setUsuarios(false);
                 if(permiso[12].equals("1"))
                 CConfiguracion.setClientes(false);
                 if(permiso[13].equals("1"))
                 CConfiguracion.setProveedores(false);
                 
                 if(permiso[14].equals("1"))
                 CConfiguracion.setRealizarVenta(false);
                 if(permiso[15].equals("1"))
                 CConfiguracion.setCatalogoVentas(false);
                 if(permiso[16].equals("1"))
                 CConfiguracion.setCatalogoFacturas(false);
                // if(permiso[17].equals("1"))
                // CConfiguracion.setVentasACredito(false);
                 if(permiso[17].equals("1"))
                 CConfiguracion.setKardex(false);
                 if(permiso[18].equals("1"))
                 CConfiguracion.setCatalogoDevoluciones(false);
                 if(permiso[19].equals("1"))
                 CConfiguracion.setAlmacenDevoluciones(false);
                 if(permiso[20].equals("1"))
                 CConfiguracion.setCierres(false);
                 if(permiso[21].equals("1"))
                 CConfiguracion.setReimpresiones(false);
                 
                 if(permiso[22].equals("1"))
                 CConfiguracion.setCatPedidos(false);
                 if(permiso[23].equals("1"))
                 CConfiguracion.setPedidos(false);
                 
                 if(permiso[24].equals("1"))
                 CConfiguracion.setReservaciones(false);
                 if(permiso[25].equals("1"))
                 CConfiguracion.setArticulosReservaciones(false);
                 
                 if(permiso[26].equals("1"))
                 CConfiguracion.setReporteParetto(false);
                 if(permiso[27].equals("1"))
                 CConfiguracion.setReporteAlmacen(false);
                 if(permiso[28].equals("1"))
                 CConfiguracion.setReporteComisiones(false);
                 if(permiso[29].equals("1"))
                 CConfiguracion.setReportePedidos(false);
                 if(permiso[30].equals("1"))
                 CConfiguracion.setReporteUtilidades(false);
                 
                 if(permiso[31].equals("1"))
                 CConfiguracion.setInventario(false);
                 if(permiso[32].equals("1"))
                 CConfiguracion.setEtiquetas(false);
                 
                 if(permiso[33].equals("1"))
                 CConfiguracion.setCuentasporcobrar(false);
                 if(permiso[34].equals("1"))
                 CConfiguracion.setCuentasporpagar(false);
                 
                 if(permiso[35].equals("1"))
                 CConfiguracion.setRealizarAnticipos(false);
                 if(permiso[36].equals("1"))
                 CConfiguracion.setVentasAnticipos(false);
                 if(permiso[37].equals("1"))
                 CConfiguracion.setCatalogoAnticipos(false);
                 if(permiso[38].equals("1"))
                 CConfiguracion.setArticulosAnticipos(false);
                 
                 if(permiso[39].equals("1"))
                 CConfiguracion.setAltaArticulos(false);
                 if(permiso[40].equals("1"))
                 CConfiguracion.setBuscarArticulos(false);
                 if(permiso[41].equals("1"))
                 CConfiguracion.setEliminarArticulos(false);
                 
                 if(permiso[42].equals("1"))
                 CConfiguracion.setAltaUbicaciones(false);
                 if(permiso[43].equals("1"))
                 CConfiguracion.setBuscarUbicaciones(false);
                 if(permiso[44].equals("1"))
                 CConfiguracion.setEliminarUbicaciones(false);
                 
                 if(permiso[45].equals("1"))
                 CConfiguracion.setAltaLineas(false);
                 if(permiso[46].equals("1"))
                 CConfiguracion.setBuscarLineas(false);
                 if(permiso[47].equals("1"))
                 CConfiguracion.setEliminarLineas(false);
                 
                 if(permiso[48].equals("1"))
                 CConfiguracion.setAltaUsuarios(false);
                 if(permiso[59].equals("1"))
                 CConfiguracion.setBuscarUsuarios(false);
                 if(permiso[50].equals("1"))
                 CConfiguracion.setEliminarUsuarios(false);
                 
                 
                 if(permiso[51].equals("1"))
                 CConfiguracion.setAltaClientes(false);
                 if(permiso[52].equals("1"))
                 CConfiguracion.setBuscarClientes(false);
                 if(permiso[53].equals("1"))
                 CConfiguracion.setEliminarClientes(false);
                 
                 if(permiso[54].equals("1"))
                 CConfiguracion.setAltaProveedores(false);
                 if(permiso[55].equals("1"))
                 CConfiguracion.setBuscarProveedores(false);
                 if(permiso[56].equals("1"))
                 CConfiguracion.setEliminarProveedores(false);
                 
                 if(permiso[57].equals("1"))
                 CConfiguracion.setAltaVentas(false);
                 if(permiso[58].equals("1"))
                 CConfiguracion.setCancelarCFD(false);
                 if(permiso[59].equals("1"))
                 CConfiguracion.setCancelarCFDI(false);
                 if(permiso[60].equals("1"))
                 CConfiguracion.setAltaDevoluciones(false);
                 if(permiso[61].equals("1"))
                 CConfiguracion.setEliminarDevoluciones(false);
                 if(permiso[62].equals("1"))
                 CConfiguracion.setEnviarAlmacenDevoluciones(false);
                 if(permiso[63].equals("1"))
                 CConfiguracion.setBuscarCierre(false);
                 if(permiso[64].equals("1"))
                 CConfiguracion.setImprimirCarta(false);
                 if(permiso[65].equals("1"))
                 CConfiguracion.setImprimir(false);
                 
                 if(permiso[66].equals("1"))
                 CConfiguracion.setAltaPedidos(false);
                 if(permiso[67].equals("1"))
                 CConfiguracion.setBuscarPedidos(false);
                 if(permiso[68].equals("1"))
                 CConfiguracion.setEliminarPedidos(false);
                 if(permiso[69].equals("1"))
                 CConfiguracion.setCancelarPedidos(false);
                 if(permiso[70].equals("1"))
                 CConfiguracion.setVerCancelados(false);
                 
                 if(permiso[71].equals("1"))
                 CConfiguracion.setVerBackOrder(false);
                 if(permiso[72].equals("1"))
                 CConfiguracion.setAltaBackOrder(false);
                 if(permiso[73].equals("1"))
                 CConfiguracion.setEliminarBackOrder(false);
                 
                 if(permiso[74].equals("1"))
                 CConfiguracion.setAltaReservaciones(false);
                 if(permiso[75].equals("1"))
                 CConfiguracion.setBuscarReservaciones(false);
                 if(permiso[76].equals("1"))
                 CConfiguracion.setEliminarReservaciones(false);
                 
                 if(permiso[77].equals("1"))
                 CConfiguracion.setSurtirAnticipos(false);
                 if(permiso[78].equals("1"))
                 CConfiguracion.setAltaAnticipos(false);
                 if(permiso[79].equals("1"))
                 CConfiguracion.setBuscarAnticipos(false);
                 if(permiso[80].equals("1"))
                 CConfiguracion.setCancelarAnticipos(false);
                 if(permiso[81].equals("1"))
                 CConfiguracion.setCancelarVentaAnticipos(false);
                 
                 if(permiso[82].equals("1"))
                 CConfiguracion.setActualizarReservaciones(false);
                 if(permiso[83].equals("1"))
                 CConfiguracion.setActualizarAnticipos(false);
                 if(permiso[84].equals("1"))
                 CConfiguracion.setActualizarArticulos(false);
                 if(permiso[85].equals("1"))
                 CConfiguracion.setActualizarClientes(false);
                 if(permiso[86].equals("1"))
                 CConfiguracion.setActualizarPedidos(false);
                 if(permiso[87].equals("1"))
                 CConfiguracion.setDevolverVentas(false);
                 if(permiso[88].equals("1"))
                 CConfiguracion.setDevolverVentasAnticipos(false);
                 if(permiso[89].equals("1"))
                 CConfiguracion.setParettoClientes(false);
                 if(permiso[90].equals("1"))
                 CConfiguracion.setConfiguracion(false);                 
                 if(permiso[91].equals("1"))
                 CConfiguracion.setOfertas(false);
                 if(permiso[92].equals("1"))
                 CConfiguracion.setArtMostrador(false);
                 if(permiso[93].equals("1"))
                 CConfiguracion.setHabilitarCTRL(false);
                 if(permiso[94].equals("1"))
                 CConfiguracion.setReporteClientes(false);
                 if(permiso[95].equals("1"))
                 CConfiguracion.setMandarSucursal(false);
                 if(permiso[96].equals("1"))
                 CConfiguracion.setRegistrarKardex(false);
                 if(permiso[97].equals("1"))
                 CConfiguracion.setFacturasparciales(false);
                 if(permiso[98].equals("1"))
                 CConfiguracion.setRespaldo(false);
                 if(permiso[99].equals("1"))
                 CConfiguracion.setConfigurarPlugins(false);
                 if(permiso[100].equals("1"))
                 CConfiguracion.setAltaPlugins(false);
                 if(permiso[101].equals("1"))
                 CConfiguracion.setBuscarPlugins(false);
                 if(permiso[102].equals("1"))
                 CConfiguracion.setEliminarPlugins(false);
                 if(permiso[103].equals("1"))
                 CConfiguracion.setEliminarArtMostrador(false);
                 
                 //Usuarios y contraseñas del WebService de facturación
                 
                 CGlobalConfig.setWsUsuario(hconfiguracion.consulta("campo", "=", "wsUsuario"));
                 CGlobalConfig.setWsPassword(hconfiguracion.consulta("campo", "=", "wsPassword"));
                 
                 id = listausuarios.get(i).getId();
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;
             }else
                 return false;
         }

        return false;
    }

public boolean validar(String username, String password) {
    
         husuarios = new hUsuarios();         
         
         listausuarios = husuarios.consultaUsuarios("foto", "=", username);  
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {                 
                 id = listausuarios.get(i).getId();
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;
             }else
                 return false;
         }

        return false;
    }


public boolean validarTransferenciaCompra(String username, String password) {
    
         husuarios = new hUsuarios();         
         
         listausuarios = husuarios.consultaUsuarios("foto", "=", username);  
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 
           hpermisos = new hPermisos();
           permisos = hpermisos.consultaPermisos("idUsuario", "=", String.valueOf(listausuarios.get(0).getId()));
          if(permisos == null)
          {
          JOptionPane.showMessageDialog(null, "El usuario que ingresó no cuenta con permisos en la sucursal especificada");        
          return false;   
          }  
          String[] permiso = permisos.getPermisos().split("@");
          if(permiso[69].equals("1"))
          {
          JOptionPane.showMessageDialog(null, "El usuario que ingresó no cuenta con permisos para dar de alta compras en la sucursal especificada");    
          return false;   
          }  
                 
                 
                 id = listausuarios.get(i).getId();
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;
             }else
                 return false;
         }

        return false;
    }


public boolean validarVendedor(String username, String password) {
    
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("foto", "=", username);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 id = listausuarios.get(i).getId();
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;
             }else
                 return false;
         }

        return false;
    }

public boolean validarPwd(String password) {
    
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("contrasena", "=", password);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 if(listausuarios.get(i).getPuesto().equals("Administración")||listausuarios.get(i).getPuesto().equals("Area"))
                 {
                 id = listausuarios.get(i).getId();    
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;                
                 }
             }
         i++;
         }

        return false;
    }

public boolean validarPwdAdministracion(String password) {
    
         husuarios = new hUsuarios();   
         listausuarios = husuarios.consultaUsuarios("contrasena", "=", password);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 if(listausuarios.get(i).getPuesto().equals("Administración"))
                 {
                 id = listausuarios.get(i).getId();    
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;                
                 }
             }
         i++;
         }

        return false;
    }


public boolean validarPwdAlmacen(String password) {
    
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("contrasena", "=", password);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 if(listausuarios.get(i).getPuesto().equals("Almacén"))
                 {
                 id = listausuarios.get(i).getId();    
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                 return true;                
                 }
             }
         i++;
         }

        return false;
    }

public boolean validarAdministrador(String id) {
    
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("id", "=", id);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getId()==Long.valueOf(id))
             {
                 if(listausuarios.get(i).getPuesto().equals("Administración")||listausuarios.get(i).getPuesto().equals("Area"))
                 {
                 return true;                
                 }
             }
         i++;
         }

        return false;
    }

public boolean validarAutorizaciones(String login, String pwd,String puesto) {
    
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("foto", "=", login);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(pwd))
             {
                 if(listausuarios.get(i).getPuesto().equals(puesto))
                 {
                
                  setId(listausuarios.get(i).getId());     
                 usuariogeneral = new Usuarios();
                 usuariogeneral = listausuarios.get(i);
                  
                 return true;                
                 }
             }
         i++;
         }

        return false;
    }

public Usuarios validarCambio(String username,String password)
{
         Usuarios usuario = new Usuarios();
         husuarios = new hUsuarios();
         listausuarios = husuarios.consultaUsuarios("foto", "=", username);
         
         int i = 0;
         while (i<listausuarios.size())
         {
             if(listausuarios.get(i).getContrasena().equals(password))
             {
                 usuario.setApellidoM(listausuarios.get(i).getApellidoM());
                 usuario.setApellidoP(listausuarios.get(i).getApellidoP());
                 usuario.setContrasena(listausuarios.get(i).getContrasena());
                 usuario.setCorreo(listausuarios.get(i).getCorreo());
                 usuario.setFoto(listausuarios.get(i).getFoto());
                 usuario.setNombres(listausuarios.get(i).getNombres());
                 usuario.setPuesto(listausuarios.get(i).getPuesto());
                 usuario.setId(listausuarios.get(i).getId());
                 return usuario;
             }else
                 return null;
         }

        return null;
    
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuariogeneral() {
        return usuariogeneral;
    }

    public void setUsuariogeneral(Usuarios usuariogeneral) {
        this.usuariogeneral = usuariogeneral;
    }

    
}
