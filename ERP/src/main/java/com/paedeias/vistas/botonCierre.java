/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Luis
 */
public class botonCierre extends JPanel implements ActionListener{

int index;

 public botonCierre( String label, int index ){

   super(new FlowLayout(FlowLayout.LEFT,0,0));
   this.index = index;

JLabel etiqueta = new JLabel( label + "    " );
etiqueta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
add(etiqueta);
Icon icono = new ImageIcon(getClass().getResource("/mainicons/close-icon.png"));
JButton button = new JButton();
button.setIcon(icono);
int size = 17;
// button.setPreferredSize(new Dimension(size,size));
button.setToolTipText("Cerrar esta pestaña");
button.setUI(new BasicButtonUI());
button.setContentAreaFilled(false);
button.setFocusable(false);
button.setBorder(BorderFactory.createEtchedBorder());
button.setBorderPainted(false);
button.setRolloverEnabled(true);

button.addActionListener( this );
button.setMargin(new Insets(0,20,0,20));

setOpaque(false);
// setBackground(getParent().getBackground());
add( button );

}

public void actionPerformed(ActionEvent ae ){

// Cerrar tab a la que se le dio click
    
    int i = ((JTabbedPane)getParent().getParent()).indexOfTabComponent(botonCierre.this);
            if (i != -1) {
                ((JTabbedPane)getParent().getParent()).remove(i);
            } 
    
}
}
