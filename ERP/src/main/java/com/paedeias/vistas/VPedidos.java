/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

/**
 *
 * @author Luis
 */
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class VPedidos extends JPanel {


    PPedidos pedidos;

    public VPedidos(){

        setLayout(new GridLayout());
        setBackground(Color.white);
        pedidos = new PPedidos();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.white);
        tabbedPane.setSize(500, 500);
       tabbedPane.addTab("Pedidos",null,pedidos,"Pedidos");
       add(tabbedPane);


    }

}
