/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Luis
 */
public class VArticulos extends JPanel {
  //  VAlta_Articulos alta;
//    VCatalogo_Articulos catalogo;
  //  VMovimientos_Articulos movimientos;
  //  VElimina_Articulos elimina;
//    VCatalogo_Lineas lineas;
//    VCatalogo_Ubicacion ubicacion;
      FArticulos farticulos;


    public VArticulos()
    {
        setLayout(new GridLayout());
        setBackground(Color.white);
     //   alta = new VAlta_Articulos();
//        catalogo = new VCatalogo_Articulos();
     //   movimientos = new VMovimientos_Articulos();
     //   elimina = new VElimina_Articulos();
//        lineas = new VCatalogo_Lineas();

//        ubicacion = new VCatalogo_Ubicacion();
          farticulos = new FArticulos();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.white);
        tabbedPane.setSize(500, 500);

//     tabbedPane.addTab("Catálogo",null,catalogo,"Catálogo de Artículos");
     //   tabbedPane.addTab("Alta",null,alta,"Alta de Artículos");
     //   tabbedPane.addTab("Modificación",null,movimientos,"Modificación de Artículos");
     //   tabbedPane.addTab("Eliminar",null,elimina,"Elimina un Artículo");
//        tabbedPane.addTab("Líneas",null,lineas,"Catálogo de Líneas");

//        tabbedPane.addTab("Ubicacion",null,ubicacion,"Catálogo de Ubicaciones");
          tabbedPane.addTab("Catálogos",null,farticulos,"Vista de Catálogos");

        add(tabbedPane);

    }

 
}
