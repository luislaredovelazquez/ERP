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

//     tabbedPane.addTab("Cat�logo",null,catalogo,"Cat�logo de Art�culos");
     //   tabbedPane.addTab("Alta",null,alta,"Alta de Art�culos");
     //   tabbedPane.addTab("Modificaci�n",null,movimientos,"Modificaci�n de Art�culos");
     //   tabbedPane.addTab("Eliminar",null,elimina,"Elimina un Art�culo");
//        tabbedPane.addTab("L�neas",null,lineas,"Cat�logo de L�neas");

//        tabbedPane.addTab("Ubicacion",null,ubicacion,"Cat�logo de Ubicaciones");
          tabbedPane.addTab("Cat�logos",null,farticulos,"Vista de Cat�logos");

        add(tabbedPane);

    }

 
}
