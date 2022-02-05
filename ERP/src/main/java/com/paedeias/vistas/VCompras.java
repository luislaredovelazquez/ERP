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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VCompras extends JPanel{

       PCompras compras;
    
    public VCompras()
    {
        setLayout(new GridLayout());
        setBackground(Color.white);
        compras = new PCompras();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 500);
        tabbedPane.setBackground(Color.white);
       tabbedPane.addTab("Plugins",null,compras,"Plugins");
       add(tabbedPane);
    }
    
}
