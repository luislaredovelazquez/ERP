/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.helpers.hPlugins;
import com.paedeias.identidades.Plugins;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author ALL
 */
public class PCompras extends javax.swing.JPanel {

    /**
     * Creates new form PCompras
     */
    public PCompras() {
        initComponents();
        
        hPlugins hplugins = new hPlugins();
        String permisos[] = null;   
        
        if(CGlobalConfig.getRutaPlugin().length >= 1)
        {
            jButton7.setEnabled(true);
            jButton8.setEnabled(true);
            jButton7.setToolTipText(CGlobalConfig.getTooltipoPlugin()[0]);
            jButton7.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[0]+".jpg")));
            jButton8.setText(CGlobalConfig.getNombrePlugin()[0]);
            jButton8.setToolTipText(CGlobalConfig.getTooltipoPlugin()[0]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[0]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton7.setEnabled(bandera);
            jButton8.setEnabled(bandera);
            
            
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 2)
        {
            jButton9.setEnabled(true);
            jButton10.setEnabled(true);
            jButton9.setToolTipText(CGlobalConfig.getTooltipoPlugin()[1]);
            jButton9.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[1]+".jpg")));
            jButton10.setText(CGlobalConfig.getNombrePlugin()[1]);
            jButton10.setToolTipText(CGlobalConfig.getTooltipoPlugin()[1]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[1]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton9.setEnabled(bandera);
            jButton10.setEnabled(bandera);
        }
                
        if(CGlobalConfig.getRutaPlugin().length >= 3)
        {
            jButton11.setEnabled(true);
            jButton12.setEnabled(true);
            jButton11.setToolTipText(CGlobalConfig.getTooltipoPlugin()[2]);
            jButton11.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[2]+".jpg")));
            jButton12.setText(CGlobalConfig.getNombrePlugin()[2]);
            jButton12.setToolTipText(CGlobalConfig.getTooltipoPlugin()[2]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[2]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton11.setEnabled(bandera);
            jButton12.setEnabled(bandera);
            
        }
                        
           if(CGlobalConfig.getRutaPlugin().length >= 4)
        {
            jButton13.setEnabled(true);
            jButton14.setEnabled(true);
            jButton13.setToolTipText(CGlobalConfig.getTooltipoPlugin()[3]);
            jButton13.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[3]+".jpg")));
            jButton14.setText(CGlobalConfig.getNombrePlugin()[3]);
            jButton14.setToolTipText(CGlobalConfig.getTooltipoPlugin()[3]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[3]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton13.setEnabled(bandera);
            jButton14.setEnabled(bandera);
            
        }
       
        if(CGlobalConfig.getRutaPlugin().length >= 5)
        {
            jButton15.setEnabled(true);
            jButton16.setEnabled(true);
            jButton15.setToolTipText(CGlobalConfig.getTooltipoPlugin()[4]);
            jButton15.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[4]+".jpg")));
            jButton16.setText(CGlobalConfig.getNombrePlugin()[4]);
            jButton16.setToolTipText(CGlobalConfig.getTooltipoPlugin()[4]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[4]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton15.setEnabled(bandera);
            jButton16.setEnabled(bandera);            
        }
       
         if(CGlobalConfig.getRutaPlugin().length >= 6)
        {
            jButton17.setEnabled(true);
            jButton18.setEnabled(true);
            jButton17.setToolTipText(CGlobalConfig.getTooltipoPlugin()[5]);
            jButton17.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[5]+".jpg")));
            jButton18.setText(CGlobalConfig.getNombrePlugin()[5]);
            jButton18.setToolTipText(CGlobalConfig.getTooltipoPlugin()[5]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[5]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton17.setEnabled(bandera);
            jButton18.setEnabled(bandera);            
        }
               
        if(CGlobalConfig.getRutaPlugin().length >= 7)
        {
            jButton19.setEnabled(true);
            jButton20.setEnabled(true);
            jButton19.setToolTipText(CGlobalConfig.getTooltipoPlugin()[6]);
            jButton19.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[6]+".jpg")));
            jButton20.setText(CGlobalConfig.getNombrePlugin()[6]);
            jButton20.setToolTipText(CGlobalConfig.getTooltipoPlugin()[6]);
            
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[6]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton19.setEnabled(bandera);
            jButton20.setEnabled(bandera);
        }
        

        
        if(CGlobalConfig.getRutaPlugin().length >= 8)
        {
            jButton21.setEnabled(true);
            jButton22.setEnabled(true);
            jButton21.setToolTipText(CGlobalConfig.getTooltipoPlugin()[7]);
            jButton21.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[7]+".jpg")));
            jButton22.setText(CGlobalConfig.getNombrePlugin()[7]);
            jButton22.setToolTipText(CGlobalConfig.getTooltipoPlugin()[7]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[7]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton21.setEnabled(bandera);
            jButton22.setEnabled(bandera);
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 9)
        {
            jButton23.setEnabled(true);
            jButton24.setEnabled(true);
            jButton23.setToolTipText(CGlobalConfig.getTooltipoPlugin()[8]);
            jButton23.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[8]+".jpg")));
            jButton24.setText(CGlobalConfig.getNombrePlugin()[8]);
            jButton24.setToolTipText(CGlobalConfig.getTooltipoPlugin()[8]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[8]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton23.setEnabled(bandera);
            jButton24.setEnabled(bandera);            
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 10)
        {
            jButton25.setEnabled(true);
            jButton26.setEnabled(true);
            jButton25.setToolTipText(CGlobalConfig.getTooltipoPlugin()[9]);
            jButton25.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[9]+".jpg")));
            jButton26.setText(CGlobalConfig.getNombrePlugin()[9]);
            jButton26.setToolTipText(CGlobalConfig.getTooltipoPlugin()[9]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[9]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton25.setEnabled(bandera);
            jButton26.setEnabled(bandera);            
        }
        

        if(CGlobalConfig.getRutaPlugin().length >= 11)
        {
            jButton27.setEnabled(true);
            jButton28.setEnabled(true);
            jButton27.setToolTipText(CGlobalConfig.getTooltipoPlugin()[10]);
            jButton27.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[10]+".jpg")));
            jButton28.setText(CGlobalConfig.getNombrePlugin()[10]);
            jButton28.setToolTipText(CGlobalConfig.getTooltipoPlugin()[10]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[10]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton27.setEnabled(bandera);
            jButton28.setEnabled(bandera);            
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 12)
        {
            jButton29.setEnabled(true);
            jButton30.setEnabled(true);
            jButton29.setToolTipText(CGlobalConfig.getTooltipoPlugin()[11]);
            jButton29.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[11]+".jpg")));
            jButton30.setText(CGlobalConfig.getNombrePlugin()[11]);
            jButton30.setToolTipText(CGlobalConfig.getTooltipoPlugin()[11]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[11]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton29.setEnabled(bandera);
            jButton30.setEnabled(bandera);            
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 13)
        {
            jButton31.setEnabled(true);
            jButton32.setEnabled(true);
            jButton31.setToolTipText(CGlobalConfig.getTooltipoPlugin()[12]);
            jButton31.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[12]+".jpg")));
            jButton32.setText(CGlobalConfig.getNombrePlugin()[12]);
            jButton32.setToolTipText(CGlobalConfig.getTooltipoPlugin()[12]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[12]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton31.setEnabled(bandera);
            jButton32.setEnabled(bandera);
        }        
        
        if(CGlobalConfig.getRutaPlugin().length >= 14)
        {
            jButton33.setEnabled(true);
            jButton34.setEnabled(true);
            jButton33.setToolTipText(CGlobalConfig.getTooltipoPlugin()[13]);
            jButton33.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[13]+".jpg")));
            jButton34.setText(CGlobalConfig.getNombrePlugin()[13]);
            jButton34.setToolTipText(CGlobalConfig.getTooltipoPlugin()[13]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[13]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton33.setEnabled(bandera);
            jButton34.setEnabled(bandera);            
        }
        
        if(CGlobalConfig.getRutaPlugin().length >= 15)
        {
            jButton35.setEnabled(true);
            jButton36.setEnabled(true);
            jButton35.setToolTipText(CGlobalConfig.getTooltipoPlugin()[14]);
            jButton35.setIcon(new ImageIcon(getClass().getResource("/mainicons/"+CGlobalConfig.getImagenPlugin()[14]+".jpg")));
            jButton36.setText(CGlobalConfig.getNombrePlugin()[14]);
            jButton36.setToolTipText(CGlobalConfig.getTooltipoPlugin()[14]);
            
            boolean bandera = false;
            permisos = null;
            List<Plugins> listaPlug=hplugins.consultaPlugins("idPlugin", "=", CGlobalConfig.getRutaPlugin()[14]);
            if(!listaPlug.isEmpty())
            permisos = listaPlug.get(0).getPermisosPlugin().split(",");
            
            if(permisos != null)
            {
            for(int i=0; i<permisos.length; i++)
            {
             if(permisos[i].equals(String.valueOf(CConfiguracion.getId())))
             bandera=true;    
            }
            }
            jButton35.setEnabled(bandera);
            jButton36.setEnabled(bandera);
        }        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton15.setToolTipText("Plugin");
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton13.setToolTipText("Plugin");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton11.setToolTipText("Plugin");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton9.setToolTipText("Plugin");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton7.setToolTipText("Plugin");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setBackground(new java.awt.Color(11, 70, 119));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Plugin");
        jButton8.setToolTipText("Plugin");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setEnabled(false);
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(11, 70, 119));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Plugin");
        jButton10.setToolTipText("Plugin");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setEnabled(false);
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(11, 70, 119));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Plugin");
        jButton12.setToolTipText("Plugin");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setEnabled(false);
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(11, 70, 119));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Plugin");
        jButton14.setToolTipText("Plugin");
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setEnabled(false);
        jButton14.setOpaque(true);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(11, 70, 119));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Plugin");
        jButton16.setToolTipText("Plugin");
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setEnabled(false);
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton8)
                .addGap(65, 65, 65)
                .addComponent(jButton10)
                .addGap(63, 63, 63)
                .addComponent(jButton12)
                .addGap(73, 73, 73)
                .addComponent(jButton14)
                .addGap(66, 66, 66)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton17.setToolTipText("Plugin");
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setEnabled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton19.setToolTipText("Plugin");
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setEnabled(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton21.setToolTipText("Plugin");
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setEnabled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton23.setToolTipText("Plugin");
        jButton23.setBorderPainted(false);
        jButton23.setContentAreaFilled(false);
        jButton23.setEnabled(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton25.setToolTipText("Plugin");
        jButton25.setBorderPainted(false);
        jButton25.setContentAreaFilled(false);
        jButton25.setEnabled(false);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton17)
                .addGap(5, 5, 5)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton18.setBackground(new java.awt.Color(11, 70, 119));
        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Plugin");
        jButton18.setToolTipText("Plugin");
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setEnabled(false);
        jButton18.setOpaque(true);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(11, 70, 119));
        jButton20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Plugin");
        jButton20.setToolTipText("Plugin");
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setEnabled(false);
        jButton20.setOpaque(true);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(11, 70, 119));
        jButton22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Plugin");
        jButton22.setToolTipText("Plugin");
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setEnabled(false);
        jButton22.setOpaque(true);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(11, 70, 119));
        jButton24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Plugin");
        jButton24.setToolTipText("Plugin");
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.setEnabled(false);
        jButton24.setOpaque(true);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(11, 70, 119));
        jButton26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Plugin");
        jButton26.setToolTipText("Plugin");
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.setEnabled(false);
        jButton26.setOpaque(true);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jButton18)
                .addGap(66, 66, 66)
                .addComponent(jButton20)
                .addGap(62, 62, 62)
                .addComponent(jButton22)
                .addGap(72, 72, 72)
                .addComponent(jButton24)
                .addGap(72, 72, 72)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton27.setToolTipText("Plugin");
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.setEnabled(false);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton29.setToolTipText("Plugin");
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setEnabled(false);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton31.setToolTipText("Plugin");
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.setEnabled(false);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton33.setToolTipText("Plugin");
        jButton33.setBorderPainted(false);
        jButton33.setContentAreaFilled(false);
        jButton33.setEnabled(false);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainicons/plugin.jpg"))); // NOI18N
        jButton35.setToolTipText("Plugin");
        jButton35.setBorderPainted(false);
        jButton35.setContentAreaFilled(false);
        jButton35.setEnabled(false);
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton35)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton28.setBackground(new java.awt.Color(11, 70, 119));
        jButton28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Plugin");
        jButton28.setToolTipText("Plugin");
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setEnabled(false);
        jButton28.setOpaque(true);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(11, 70, 119));
        jButton30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Plugin");
        jButton30.setToolTipText("Plugin");
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.setEnabled(false);
        jButton30.setOpaque(true);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(11, 70, 119));
        jButton32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Plugin");
        jButton32.setToolTipText("Plugin");
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);
        jButton32.setEnabled(false);
        jButton32.setOpaque(true);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(11, 70, 119));
        jButton34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("Plugin");
        jButton34.setToolTipText("Plugin");
        jButton34.setBorderPainted(false);
        jButton34.setContentAreaFilled(false);
        jButton34.setEnabled(false);
        jButton34.setOpaque(true);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(11, 70, 119));
        jButton36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Plugin");
        jButton36.setToolTipText("Plugin");
        jButton36.setBorderPainted(false);
        jButton36.setContentAreaFilled(false);
        jButton36.setEnabled(false);
        jButton36.setOpaque(true);
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton28)
                .addGap(73, 73, 73)
                .addComponent(jButton30)
                .addGap(63, 63, 63)
                .addComponent(jButton32)
                .addGap(80, 80, 80)
                .addComponent(jButton34)
                .addGap(63, 63, 63)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[0]);
            Object plugin1 = clazz.newInstance();
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);
            
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[0], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[0]);
            Object plugin1 = clazz.newInstance();
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);
            
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[0], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[1]);
            Object plugin1 = clazz.newInstance();
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);     
            
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[1], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[1]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);   
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[1], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[2]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[2], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[2]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[2], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[3]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
                    Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[3], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[3]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[3], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[4]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[4], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[4]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[4], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[5]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);     
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[5], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[5]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[5], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[6]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[6], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[6]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[6], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[7]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[7], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[7]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[7], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[8]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[8], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[8]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[8], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[9]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[9], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[9]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[9], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[10]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[10], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[10]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[10], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[11]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);     
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[11], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[11]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[11], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[12]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[12], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[12]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[12], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[13]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[13], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed

        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[13]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[13], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[14]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[14], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        
         Class clazz;
        try {
            clazz = Class.forName(CGlobalConfig.getClasePlugin()[14]);
            Object plugin1 = clazz.newInstance();
       //     Method method = clazz.getMethod("getHola", null);
       //     System.out.println(method.invoke(plugin1, null));
            Class tipos[] = {Long.TYPE,String.class,String.class,String.class,String.class,
            String.class,String.class,String.class,String.class,String.class,String.class};
            Object valores[] = {CConfiguracion.getId(),CConfiguracion.getNombres(),CConfiguracion.getApellidoP(),
            CConfiguracion.getApellidoM(),CConfiguracion.getContrasena(), CConfiguracion.getPuesto(),
            CConfiguracion.getCorreo(),CConfiguracion.getFoto(),CConfiguracion.getMeta(),
            CConfiguracion.getMetaPasada(),CConfiguracion.getMetaPasada2()};
            
            Method method = clazz.getMethod("setValores", tipos);
            method.invoke(plugin1, valores);    
            
        int indice = ((JTabbedPane) getParent()).getSelectedIndex();
        indice += 1;
        ((JTabbedPane) getParent()).add(new JScrollPane((java.awt.Component)plugin1), indice);
        ((JTabbedPane) getParent()).setTabComponentAt(indice, new botonCierre(CGlobalConfig.getNombrePlugin()[14], indice));
        ((JTabbedPane)getParent()).setSelectedIndex(indice);
            
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton36ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
