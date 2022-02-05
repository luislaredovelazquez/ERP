/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author ALL
 */
public class VReservaciones extends JPanel {
   
      PReservaciones reservacion;
    
    public VReservaciones()
    {
        setLayout(new GridLayout());
        setBackground(Color.white);
        reservacion = new PReservaciones();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 500);
        tabbedPane.setBackground(Color.white);
       tabbedPane.addTab("Contabilidad",null,reservacion,"Contabilidad");
       add(tabbedPane);
    }
    
}
