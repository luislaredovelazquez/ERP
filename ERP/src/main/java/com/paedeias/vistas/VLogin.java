/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.*;
import com.paedeias.controladores.Autenticar;
import javax.swing.*;

/**
 *
 * @author Luis
 */
public class VLogin extends JDialog {



     private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbpie;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    public  String usuario;


    public VLogin(Frame padre)
    {
     super(padre, "Login", true);
     
     
     JPanel panel = new JPanel(new GridBagLayout());
     panel.setBackground(Color.white);

        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        
        ImageIcon img_home = new ImageIcon(getClass().getResource("/mainicons/bienvenida.jpg"));
        final JLabel home = new JLabel(img_home);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(home, cs);         
        lbUsername = new JLabel("Usuario: ");
        lbUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(102, 102, 102));
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
        tfUsername = new JTextField(20);
        tfUsername.setName("ignore_upper_case");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(tfUsername, cs);
        lbPassword = new JLabel("Contraseña: ");
        lbPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(102, 102, 102));
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
        pfPassword = new JPasswordField(20);
        pfPassword.setName("ignore_upper_case");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        panel.add(pfPassword, cs);
        
        
        tfUsername.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
             btnLogin.doClick();
            } });
        
         pfPassword.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
             btnLogin.doClick();
            } });

        btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Autenticar autenticar = new Autenticar();
                
                if (autenticar.validarLogin(getUsername(), getPassword())) {
                   /* JOptionPane.showMessageDialog(VLogin.this,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE); */
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VLogin.this,
                            "Nombre de Usuario o Contraseña Inválido",
                            "Login",

                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
                }
            }
        });
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.setBackground(Color.white);
        
                
        btnLogin.setBackground(new java.awt.Color(11,70,119));
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setOpaque(true);
        
        btnCancel.setBackground(new java.awt.Color(11,70,119));
        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setOpaque(true);
        
        bp.add(btnLogin);
        bp.add(btnCancel);
        
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 2;
        panel.add(bp, cs);
        
        
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JPanel panelPie = new JPanel();
        panelPie.setLayout(new FlowLayout());
        panelPie.setBackground(Color.white);
        
        lbpie = new JLabel("Quimera Systems");
        lbpie.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        //lbpie.setForeground(new java.awt.Color(102, 102, 102));
        panelPie.add(lbpie);
        
        getContentPane().add(panelPie, BorderLayout.PAGE_END);
        
        // getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        
        



    }


  public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    public boolean isSucceeded() {
        return succeeded;
    }



}
