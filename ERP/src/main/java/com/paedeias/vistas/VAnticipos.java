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
public class VAnticipos extends JPanel{
    
     PAnticipos panticipo;
    
    public VAnticipos()
    {
        setLayout(new GridLayout());
        setBackground(Color.white);
        panticipo = new PAnticipos();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 500);
        tabbedPane.setBackground(Color.white);
       tabbedPane.addTab("Anticipos",null,panticipo,"Anticipos");
       add(tabbedPane);
    }
    
    
}
