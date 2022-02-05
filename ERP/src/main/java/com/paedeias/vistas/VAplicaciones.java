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
 * @author ALL
 */



public class VAplicaciones extends JPanel {
FAplicaciones faplicaciones;
    public VAplicaciones()
    {
                setLayout(new GridLayout());
                setBackground(Color.white);

          faplicaciones = new FAplicaciones();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 500);
        tabbedPane.setBackground(Color.white);
          tabbedPane.addTab("Aplicaciones",null,faplicaciones,"Vista de Aplicaciones");

        add(tabbedPane);
    }


}
