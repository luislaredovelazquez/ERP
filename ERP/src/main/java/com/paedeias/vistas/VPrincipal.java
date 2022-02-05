/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paedeias.vistas;

import com.paedeias.controladores.Autenticar;
import com.paedeias.controladores.CConfiguracion;
import com.paedeias.controladores.CGlobalConfig;
import com.paedeias.controladores.CPrincipal;
import com.paedeias.helpers.Conexion;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.*;
/**
 *
 * @author Luis
 */
// public class VPrincipal extends JFrame implements ActionListener,AWTEventListener{
   public class VPrincipal extends JFrame {

    VLogin vlogin;
    boolean ctrl=false;
// private static final int TIMEOUT = 1680000; //2 minutes 
// private final Timer timer = new Timer(TIMEOUT, this); 
    
      //   JMenuBar menuBar;
      //   JMenu menuArticulos,menuMovimientos,menuAplicaciones,menuAyuda;
      //   JMenuItem menuItem;

    public VPrincipal() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
    {
        super(CGlobalConfig.getFactura_emisorNombre());
        Image logo = new ImageIcon("logo.jpg").getImage();
        setIconImage(logo);
//  timer.start();
        
        // Agregando los plugins correspondientes
     
     /*   try{
            Class.forName("Plug1.plugin1");
        }catch(ClassNotFoundException e){
            System.out.println("No se encuentra com.mysql.jdbc.Driver");
        } */
        
        try
        {
            URLClassLoader classLoader = ((URLClassLoader)ClassLoader.getSystemClassLoader());
            Method metodoAdd = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            metodoAdd.setAccessible(true);
            //La URL del jar que queremos añadir
            
            // URL url = new URL("file:////C:/Users/ALL/Documents/paedeiasERP/plugins/plugin1.jar");
        if(CGlobalConfig.getRutaPlugin().length >= 1)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[0]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
       if(CGlobalConfig.getRutaPlugin().length >= 2)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[1]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 3)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[2]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 4)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[3]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 5)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[4]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 6)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[5]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 7)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[6]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 8)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[7]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 9)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[8]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 10)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[9]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 11)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[10]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 12)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[11]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 13)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[12]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 14)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[13]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
        if(CGlobalConfig.getRutaPlugin().length >= 15)
        {
            URL url = (new java.io.File("plugins/"+CGlobalConfig.getRutaPlugin()[14]+".jar")).toURI().toURL();
            metodoAdd.invoke(classLoader, new Object[]{url});
        }
       

        }catch(Exception e){e.printStackTrace();}
        
  /*      try{
            Class.forName("Plug1.plugin1");
            System.out.println("Ya se encuentra la Interfaz!!!");
        }catch(ClassNotFoundException e){
            System.out.println("Plug1.plugin1");
        }
        
        
        Class clazz;
        try {
            clazz = Class.forName("Plug1.plugin1");
            Object plugin1 = clazz.newInstance();
            Method method = clazz.getMethod("getHola", null);
            System.out.println(method.invoke(plugin1, null));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } */
        
        //
        
      //Panel central
        
        //Se traen todos los paneles
//      Toolkit.getDefaultToolkit().addAWTEventListener(VPrincipal.this, AWTEvent.FOCUS_EVENT_MASK); 
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher(){

            public boolean dispatchKeyEvent(KeyEvent e) {
                
                if(!CConfiguracion.isHabilitarCTRL())
                {
                if(e.getID() == KeyEvent.KEY_PRESSED)
                {
                    if(e.isControlDown()&&ctrl==false)
                    {
                    ctrl = true;    
                    JOptionPane.showMessageDialog(null, "Opción deshabilitada");
                    return false;
                    }else if(e.isControlDown()&&ctrl==true)
                    {
                    JOptionPane.showMessageDialog(null, "Opción deshabilitada");
                    return false;                        
                    }
                }
                
                if(e.getID() == KeyEvent.KEY_RELEASED)
                {
                    if(!e.isControlDown()&&ctrl==true)
                    {
                    ctrl = false;
                    return false;
                    }else if(e.isControlDown()&&ctrl==false)
                    {
                    return false;                        
                    }
                }
                }
                
                
                if(e.getID() == KeyEvent.KEY_TYPED){
                
             /*   if(e.getSource() instanceof JComponent 
                && ((JComponent)e.getSource()).getName() != null
                && ((JComponent)e.getSource()).getName().startsWith("ignore_upper_case")){
                  return false;
}    */
               //    System.out.println(((JComponent)e.getSource()));                   
                   try{
                   if((JComponent)e.getSource() instanceof JPasswordField)
                    return false;
                   }catch(ClassCastException ex)
                   {
                       // Ignorar
                   }
                            
                if(e.getSource() instanceof JComponent 
                && ((JComponent)e.getSource()).getName() != null
                && ((JComponent)e.getSource()).getName().startsWith("ignore_upper_case")){
                  return false;
}
                    if(e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')
                    {
                        e.setKeyChar((char)((int)e.getKeyChar() - 32));   
                    }else if (e.getKeyChar() == 'ñ')
                    {
                        e.setKeyChar('Ñ');
                    }else if(e.getKeyChar() == '\'')
                    {
                        e.setKeyChar('´');
                    }else if(e.getKeyChar() == '~')
                    {
                        e.setKeyChar('Ñ');
                    }else if(e.getKeyChar() == 'á')
                    {
                        e.setKeyChar('Á');
                    }else if(e.getKeyChar() == 'é')
                    {
                        e.setKeyChar('É');
                    }else if(e.getKeyChar() == 'í')
                    {
                        e.setKeyChar('Í');
                    }else if(e.getKeyChar() == 'ó')
                    {
                        e.setKeyChar('Ó');
                    }else if(e.getKeyChar() == 'ú')
                    {
                        e.setKeyChar('Ú');
                    }
                        
                    
                }
                return false;
            }
        
        });
        
        
                        vlogin = new VLogin(VPrincipal.this);
                        vlogin.setSize(400, 500);
                        vlogin.setLocationRelativeTo(null);
                        vlogin.setVisible(true);
                        // if logon successfully
                        if(vlogin.isSucceeded()){
                            
        Color color = new Color(CGlobalConfig.getBgcolor1(),CGlobalConfig.getBgcolor2(),CGlobalConfig.getBgcolor3());                 
        VArticulos varticulos = new VArticulos();
        varticulos.setBackground(color);
        VKardex vkardex = new VKardex();
        vkardex.setBackground(color);
        VPedidos vpedidos = new VPedidos();
        vpedidos.setBackground(color);
        VVentas vventas = new VVentas();
        vventas.setBackground(color);
        VAnticipos vanticipos = new VAnticipos();
        vanticipos.setBackground(color);
        VReservaciones vreservaciones = new VReservaciones();
        vreservaciones.setBackground(color);
        VCompras vcompras = new VCompras();
        vcompras.setBackground(color);
        VReportes preportes = new VReportes();
        preportes.setBackground(color);
        VAplicaciones vaplicaciones = new VAplicaciones();
        vaplicaciones.setBackground(color);
        FHome fhome = new FHome();
        //Se traen los paneles

        final JPanel cartas;
        cartas = new JPanel(new CardLayout());
        cartas.add(fhome,"fHome");
        cartas.add(varticulos,"pArticulos");
        cartas.add(vkardex,"pKardex");
        cartas.add(vventas,"pVentas");
        cartas.add(vpedidos,"pPedidos");
        cartas.add(vaplicaciones,"pAplicaciones");
        cartas.add(vanticipos,"pAnticipos");
        cartas.add(vreservaciones,"pReservaciones");
        cartas.add(preportes,"pReportes");
        cartas.add(vcompras,"pCompras");
        final CardLayout cl = (CardLayout)(cartas.getLayout());

//Fin de panel central

   /*     final JButton btnLogin = new JButton("Da click para comenzar");
        
               btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        vlogin = new VLogin(VPrincipal.this);
                        vlogin.setVisible(true);
                        // if logon successfully
                        if(vlogin.isSucceeded()){
                            btnLogin.setText("Hola " + vlogin.getUsername() + "!");
                        }
                    }
                }); */
        setLayout(new BorderLayout());
   /*     menuBar = new JMenuBar();
        menuArticulos = new JMenu("Catálogos");
        menuMovimientos = new JMenu("Movimientos");
        menuAplicaciones = new JMenu("Aplicaciones");
        menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuArticulos);
        menuBar.add(menuMovimientos);
        menuBar.add(menuAplicaciones);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar); */

        //Botones de cabecera

        ImageIcon img_home = new ImageIcon(getClass().getResource("/mainicons/home.jpg"));
        final JButton home = new JButton(img_home);
        home.setToolTipText("Principal");
        home.setBackground(Color.white);
        home.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                           cl.show(cartas, "fHome");
                    }
                });

        ImageIcon img_arti = new ImageIcon(getClass().getResource("/mainicons/catalogos.jpg"));
        final JButton articulos = new JButton(img_arti);
        articulos.setToolTipText("Catálogos");
        articulos.setBackground(Color.white);
        articulos.setEnabled(CConfiguracion.isPanelCatalogos());
        articulos.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                           cl.show(cartas, "pArticulos");
                    }
                });



        ImageIcon img_ventas = new ImageIcon(getClass().getResource("/mainicons/wallet.jpg"));
        final JButton ventas = new JButton(img_ventas);
        ventas.setToolTipText("Ventas");
        ventas.setBackground(Color.white);
        ventas.setEnabled(CConfiguracion.isPanelVentas());
        ventas.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                           cl.show(cartas, "pVentas");
                    }
                });

        ImageIcon img_compras = new ImageIcon(getClass().getResource("/mainicons/plugins.jpg"));
        final JButton compras = new JButton(img_compras);
        compras.setToolTipText("Plugins");
        compras.setBackground(Color.white);
        compras.setEnabled(CConfiguracion.isPanelCompras());
        compras.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                 cl.show(cartas, "pCompras");
            }

        });

        
        ImageIcon img_pedidos = new ImageIcon(getClass().getResource("/mainicons/van.jpg"));
        final JButton pedidos = new JButton(img_pedidos);
        pedidos.setToolTipText("Pedidos");
        pedidos.setBackground(Color.white);
        pedidos.setEnabled(CConfiguracion.isPanelPedidos());
        pedidos.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                 cl.show(cartas, "pPedidos");
            }

        });


        ImageIcon img_reportes = new ImageIcon(getClass().getResource("/mainicons/basquet.jpg"));
        final JButton reportes = new JButton(img_reportes);
        reportes.setToolTipText("Reportes");
        reportes.setBackground(Color.white);
        reportes.setEnabled(CConfiguracion.isPanelReportes());
        reportes.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                 cl.show(cartas, "pReportes");
            }

        });

        ImageIcon img_aplicaciones = new ImageIcon(getClass().getResource("/mainicons/gift.jpg"));
        final JButton aplicaciones = new JButton(img_aplicaciones);
        aplicaciones.setToolTipText("Aplicaciones");
        aplicaciones.setBackground(Color.white);
        aplicaciones.setEnabled(CConfiguracion.isPanelAplicaciones());
        aplicaciones.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                 cl.show(cartas, "pAplicaciones");
            }

        });


        ImageIcon img_credit = new ImageIcon(getClass().getResource("/mainicons/credit.jpg"));
        final JButton reservaciones = new JButton(img_credit);
        reservaciones.setToolTipText("Contabilidad");
        reservaciones.setBackground(Color.white);
        reservaciones.setEnabled(CConfiguracion.isPanelReservaciones());
        reservaciones.addActionListener(new ActionListener()
        {

                public void actionPerformed(ActionEvent e) {
                    cl.show(cartas, "pReservaciones");
                }
            
        }
                );

        ImageIcon img_cliente = new ImageIcon(getClass().getResource("/mainicons/star.jpg"));
        final JButton clientes = new JButton(img_cliente);
        clientes.setToolTipText("Anticipos");
        clientes.setBackground(Color.white);
        clientes.setEnabled(CConfiguracion.isPanelAnticipos());
        clientes.addActionListener(new ActionListener()
        {

                public void actionPerformed(ActionEvent e) {
                    cl.show(cartas, "pAnticipos");
                }
            
        }
                );


//Fin botones de cabecera
//Panel Norte


        JPanel panelNorte = new JPanel();
        panelNorte.setBackground(color);
        panelNorte.setLayout(new FlowLayout());
        
        
        panelNorte.add(home);
        panelNorte.add(articulos);
        panelNorte.add(ventas);
        panelNorte.add(pedidos);
        panelNorte.add(reservaciones);
        panelNorte.add(reportes);
        panelNorte.add(aplicaciones);
        panelNorte.add(clientes);
        panelNorte.add(compras);
        
//Fin panel norte            
        
        add(panelNorte,BorderLayout.NORTH);
        add(cartas,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            CPrincipal.getConexion().cerrarConexion();
    }
});
        setSize(1024,720);
     //   getContentPane().add(btnLogin);
        setLocationRelativeTo(null);
        setVisible(true);
                            
                        }else
                        {
                        dispose();    
                        }
     
                        
    
    }

/*    
    public void actionPerformed(ActionEvent e) {
        
        CPrincipal.getConexion().cerrarConexion();
        this.setEnabled(false);
        timer.stop();
        
        int eleccion = JOptionPane.showConfirmDialog(null, "Se ha desconectado del servidor, ¿Desea reconectar?", "¿Reconectar?", JOptionPane.YES_NO_OPTION);
        if(eleccion == JOptionPane.YES_OPTION)
        {
                    
        Conexion conexion = new Conexion();
        conexion.crearConexion();
        CPrincipal.setConexion(conexion);
        
        
        
              String password;
              JPasswordField passwordField = new JPasswordField();
              Object[] obj = {"Por favor escriba la contraseña del dueño de esta sesión:\n\n", passwordField};
              Object stringArray[] = {"OK","Cancelar"};
              int opcion = 0;  
              boolean bandera = true;
              
              while(bandera)
              {
              opcion = JOptionPane.showOptionDialog(null, obj, "Contraseña requerida",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj); 
             
             if(opcion != JOptionPane.YES_OPTION )      
             return;
             else if(opcion == JOptionPane.YES_OPTION) //el usuario dio click en aceptar
             {
             password =  new String(passwordField.getPassword()); //se toma el campo de texto del joptionpane y se autentifica
             Autenticar autenticar = new Autenticar();
             if (!autenticar.validar(CConfiguracion.getFoto(),password))
             {    
             JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
             
             }else
                  {
        timer.start();
        this.setEnabled(true);
        bandera = false;
        CPrincipal.getConexion().cerrarConexion();
        conexion.crearConexion();
        CPrincipal.setConexion(conexion);
                  }
            }
              }
        }else
        {
            System.exit(0);
        }
        
    }

    public void eventDispatched(AWTEvent event) {
  //     timer.restart();
    } 
    * 
    * 
    */
        }
