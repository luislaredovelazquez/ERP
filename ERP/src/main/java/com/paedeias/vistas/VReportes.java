/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

/**
 *
 * @author ALL
 */

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class VReportes extends JPanel{
 
    PReportes preportes;
    
     public VReportes(){

        setLayout(new GridLayout());
        setBackground(Color.white);
        preportes = new PReportes();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 500);
        tabbedPane.setBackground(Color.white);
       tabbedPane.addTab("Reportes",null,preportes,"Reportes");
       add(tabbedPane);


    }
    
}
