/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.impresiones;

/**
 *
 * @author ALL
 */
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.identidades.Partidas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import  org.codehaus.groovy.control.CompilationFailedException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

 
public class ReporteVentaHojaCarta { 
    
      static final String CONTROLADOR = "com.mysql.jdbc.Driver";
  //    static final String URL_BASEDATOS="jdbc:mysql://192.168.1.90:3306/llv";
      static final String URL_BASEDATOS=CGlobalConfig.getConexion();
    
    /**
     * @param args the command line arguments
     */

    public HashMap resultSetToHashMap(ResultSet rs) throws SQLException
    {
        HashMap row = new HashMap();
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        while(rs.next())
        {
            for (int i=1; i<=columns; i++){
            row.put(md.getColumnName(i), rs.getObject(i));
            }
        }
        return row;
    }

    public void crearReporte(String idventa)
    {
          JasperReport jasperReport;
    JasperPrint jasperPrint;
    try
    {

        Connection conexion = null;
   //     Statement instruccion = null;
        ResultSet conjuntoResultados = null;

                 Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL_BASEDATOS,"root","cheshire");
   /*         instruccion = conexion.createStatement();

            conjuntoResultados = instruccion.executeQuery("SELECT * FROM factura"); */
            

       /*     ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroDeColumnas = metaDatos.getColumnCount();
            System.out.println("Tabla de claves: \n");

            for (int i=1; i<=numeroDeColumnas; i++)
                System.out.printf("%-8s\t",metaDatos.getColumnName(i));
                System.out.println(); */

    /*        while (conjuntoResultados.next())
            {
                for(int i=1; i<=numeroDeColumnas; i++)
                    System.out.printf("%-8s\t",conjuntoResultados.getObject(i));
                    System.out.println();
            }
           */

   /*           HashMap row = new HashMap();
        ResultSetMetaData md = conjuntoResultados.getMetaData();
        int columns = md.getColumnCount();
        while(conjuntoResultados.next())
        {
            for (int i=1; i<=columns; i++){
            row.put(md.getColumnName(i), conjuntoResultados.getObject(i));
            System.out.println(md.getColumnName(i)+" "+conjuntoResultados.getObject(i));
            }
        } */
          System.out.println(idventa);
          HashMap row = new HashMap();
          row.put("venta",idventa); //tenemos que conseguir el parámetro 1 del sistema
          // row.put("logoFerrari",getClass().getResource("/mainicons/ferrari.gif"));
          row.put("logoFerrari","logo.jpg");
      jasperReport = JasperCompileManager.compileReport("ticketVentaCarta.jrxml");
      jasperPrint = JasperFillManager.fillReport(
          jasperReport, row, conexion); //la parte del hashmap son los parámetros que se envian:
      

     /* jasperPrint = JasperFillManager.fillReport(
          jasperReport, row, new JREmptyDataSource()); */


      JasperExportManager.exportReportToPdfFile(
          jasperPrint, "ventas/venta"+idventa+".pdf"); 
    }
        catch (SQLException ex) {
            Logger.getLogger(ReporteVentaHojaCarta.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (ClassNotFoundException ex) {
            Logger.getLogger(ReporteVentaHojaCarta.class.getName()).log(Level.SEVERE, null, ex);
        }    catch (JRException e)
    {
      e.printStackTrace();
    } 

    }
    
}