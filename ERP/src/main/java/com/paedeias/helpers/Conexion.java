/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.helpers;

import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Luis
 */
public class Conexion {

   static final String CONTROLADOR = "com.mysql.jdbc.Driver";
   static String URL_BASEDATOS=CGlobalConfig.getConexion()+"?zeroDateTimeBehavior=convertToNull";
//   static final String URL_BASEDATOS="jdbc:mysql://192.168.1.95:3306/llv";
   Connection conexion = null;
   Statement instruccion = null;
   ResultSet conjuntoResultados = null;

   public Conexion(){}

   public void crearConexion()
    {
        if(!CGlobalConfig.isWeb())
        {
        try {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL_BASEDATOS, "root", "cheshire");
  //          conexion = DriverManager.getConnection(URL_BASEDATOS, "quimera1_root", "cheshire");
                 sesion(1800);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

   public ResultSet crearConsulta(String query)
    {
                if(!CGlobalConfig.isWeb())
        {
        try
        {
            instruccion = conexion.createStatement();
            conjuntoResultados = instruccion.executeQuery(query);
         } catch (SQLException ex) {
            try {
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
                return conjuntoResultados;
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
                return conjuntoResultados;
            }
        }
             return conjuntoResultados;
        }else return null;
   
    }

   public int moverDatos(String query)
    {
                        if(!CGlobalConfig.isWeb())
        {
        try {
            instruccion = conexion.createStatement();
            int resultado = instruccion.executeUpdate(query);
            return resultado;
        } catch (SQLException ex) {
            try {
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
                return 3;
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                CPrincipal.getConexion().sesion(1800);
                JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
                return 3;
            }
        }
        }else
          return 0;
   }

      public void sesion(int segundos)
    {
         if(!CGlobalConfig.isWeb())
        {
        try {
            instruccion = conexion.createStatement();
            instruccion.execute("set session wait_timeout="+segundos+";");
            cerrar(1);
        } catch (SQLException ex) {
            try {
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
               JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
            }
        }
        }
        
   }
   
   
   public int moverDatosLast(String query)
    {
        if(!CGlobalConfig.isWeb())
        {
        try {
           int resultado = -1;
           instruccion = conexion.createStatement();
           instruccion.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
           conjuntoResultados = instruccion.getGeneratedKeys();
           if (conjuntoResultados.next())
           resultado = conjuntoResultados.getInt(1);
           else
           resultado = -1;
           return resultado;

         } catch (SQLException ex) {
            try {
                ex.printStackTrace();
                
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
                return -1;
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
                return -1;
            }
        }
        } return -1;
   }

   /*
   * Parámetro 0 indica que el cierre proviene de una consulta,
   *
   * parámetro 1 indica que proviene de una modificación de datos*/
   public void cerrar(int consulta)
    {
         if(!CGlobalConfig.isWeb())
        {
        try {
            if(consulta == 0){
            conjuntoResultados.close();
            }
            instruccion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
   
      public void cerrarConexion()
    {
        if(!CGlobalConfig.isWeb())
        {
        try {
            conexion.close();
            System.out.println("Cerrado");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean crearTransaccion(){
         if(!CGlobalConfig.isWeb())
        {
        try {
            if(CPrincipal.getConexion().getConexion().isValid(0))
            {               
            CPrincipal.getConexion().getConexion().setAutoCommit(false);   
            
            // ¿poner el tiempo de idle?
            return true;
            }else
            {
            return false;    
            }    
        } catch (SQLException ex) {
            try {
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
                return false;
            }
        }
        } else return true;
    }
    
    public void cancelarTransaccion()
    {
                 if(!CGlobalConfig.isWeb())
        {
        try {
            if(CPrincipal.getConexion().getConexion().isValid(0) && !CPrincipal.getConexion().getConexion().getAutoCommit())
            {
            CPrincipal.getConexion().getConexion().rollback();    
            CPrincipal.getConexion().getConexion().setAutoCommit(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cancelar la transacción, por favor consulte al Administrador del Sistema");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public boolean finalizarTransaccion(){
         if(!CGlobalConfig.isWeb())
        {
        try {
            if(CPrincipal.getConexion().getConexion().isValid(0) && !CPrincipal.getConexion().getConexion().getAutoCommit())
            {
            CPrincipal.getConexion().getConexion().commit();    
            CPrincipal.getConexion().getConexion().setAutoCommit(true);   
            return true;
            }else
            {
            return false;    
            }    
       } catch (SQLException ex) {
            try {
                CPrincipal.getConexion().getConexion().rollback();
                System.out.println("Se hizo rollback");
                CPrincipal.getConexion().getConexion().setAutoCommit(true);
                System.out.println("Autocommit true");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en operación de base de datos, operación anulada");
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                JOptionPane.showMessageDialog(null, "Error en comunicación con el servidor, no se encuentra conectado al sistema, "
                                                      + "\n El sistema se cerrará para evitar inconsistencia de información");
                System.exit(0);
                return false;
            }
        }
        }else return true;
    }

    public static String getURL_BASEDATOS() {
        return URL_BASEDATOS;
    }

    public static void setURL_BASEDATOS(String URL_BASEDATOS) {
        Conexion.URL_BASEDATOS = URL_BASEDATOS;
    }
    
      
}
