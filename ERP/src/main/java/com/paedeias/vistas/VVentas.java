/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 *
 * @author ALL
 */
public class VVentas extends JPanel{
    
    PVentas pventa;
    
    public VVentas()
    {
         setLayout(new GridLayout());
        setBackground(Color.white);
        pventa = new PVentas();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.white);
        tabbedPane.setSize(500, 500);
       tabbedPane.addTab("Ventas",null,pventa,"Ventas");
       add(tabbedPane);
    }
    
}
