/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.*;
import com.paedeias.controladores.Autenticar;
/**
 *
 * @author ALL
 */
public class VAutenticarPersona extends JDialog{
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded=false;

    public VAutenticarPersona(Frame padre) {
        super(padre,"Autenticar",true);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        lbUsername = new JLabel("Usuario: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
        tfUsername = new JTextField(20);
        tfUsername.setName("ignore_upper_case");
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
        lbPassword = new JLabel("Contraseña: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
        pfPassword = new JPasswordField(20);
        pfPassword.setName("ignore_upper_case");
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
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
                
                if (autenticar.validarAutorizaciones(getUsername(), getPassword(),"Administración")) {
                    succeeded = true;
                    dispose();
                } else {
                if (autenticar.validarAutorizaciones(getUsername(), getPassword(),"Area")) {
                    succeeded = true;
                    dispose();                
                }else
                {
                    succeeded = false;
                    dispose();
                }
                    
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
        bp.add(btnLogin);
        bp.add(btnCancel);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocationRelativeTo(padre);



        
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
