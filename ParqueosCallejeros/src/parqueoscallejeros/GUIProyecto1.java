
package parqueoscallejeros;

import javax.swing.*; //swing para el gui
import java.awt.*; //para component componentes 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUIProyecto1 extends javax.swing.JFrame {

    /**
     * Creates new form GUIProyecto1
     */
    public GUIProyecto1() {
        initComponents();
        fondoPanel.setLayout(null); //no se mueven las cosas automaticamente (para mejor orden)
    }
    //generado automaticamente
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoPanel = new javax.swing.JPanel();
        tituloLabel = new javax.swing.JLabel();
        usuarioButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        inspectButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoPanel.setBackground(new java.awt.Color(0, 51, 102));

        tituloLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        tituloLabel.setForeground(new java.awt.Color(255, 255, 255));
        tituloLabel.setText("Sistema Parqueos Callejeros");
        tituloLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        usuarioButton.setText("Usuario");
        usuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioButtonActionPerformed(evt);
            }
        });

        adminButton.setText("Administrador");
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });

        inspectButton.setText("Inspector");
        inspectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        volverButton.setText("Volver");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoPanelLayout = new javax.swing.GroupLayout(fondoPanel);
        fondoPanel.setLayout(fondoPanelLayout);
        fondoPanelLayout.setHorizontalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoPanelLayout.createSequentialGroup()
                .addGap(0, 278, Short.MAX_VALUE)
                .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181)
                .addComponent(volverButton)
                .addGap(34, 34, 34)
                .addComponent(exitButton)
                .addGap(31, 31, 31))
            .addGroup(fondoPanelLayout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addGroup(fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inspectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fondoPanelLayout.setVerticalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoPanelLayout.createSequentialGroup()
                .addGroup(fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitButton)
                            .addComponent(volverButton)))
                    .addGroup(fondoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inspectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void deshabilitarInicio(){ //deshabilita y pone invisibles los botones del inicio sin contar volver y exit
            inspectButton.setEnabled(false);
            inspectButton.setVisible(false);
            adminButton.setEnabled(false);
            adminButton.setVisible(false);
            usuarioButton.setEnabled(false);
            usuarioButton.setVisible(false);
    }
    
    private void habilitarInicio(){ //habilita y pone visibles los botones del inicio sin contar volver y exit
            inspectButton.setEnabled(true);
            inspectButton.setVisible(true);
            adminButton.setEnabled(true);
            adminButton.setVisible(true);
            usuarioButton.setEnabled(true);
            usuarioButton.setVisible(true);
    }
    
    private void deshabilitarComponentes(){ // deshabilita y pone invisibles los componentes ignorando exit volver y el titulo label
    Component[] listaComponentes = fondoPanel.getComponents(); //consigue los componentes del JPanel fondoPabel
        for (Component i : listaComponentes) {
            if (i != exitButton && i != tituloLabel && i != volverButton){
                i.setVisible(false);
                i.setEnabled(false);
            }
        }
    }
    
    private void esconderBoton(String nombre){ //Busca un boton con el texto que este posee
    Component[] listaComponentes = fondoPanel.getComponents(); //consigue los componentes del JPanel fondoPabel
        for (Component i : listaComponentes) {
            if (i instanceof JButton){
                if (((JButton) i).getText() == nombre){ //usando casting par sacar getText()
                    i.setVisible(false);
                    i.setEnabled(false);
                }
                
            }
        }
    }
    
   
    
    
    private void regresar(JButton boton, java.awt.event.ActionEvent evento) { //regresa al menu anterior (sin finalizar)
        Component[] listaComponentes = fondoPanel.getComponents(); //consigue los componentes del JPanel fondoPabel
        for (Component i : listaComponentes) {
            if (i instanceof JButton){
                if (((JButton) i).getText() == "Regresar"){ //usando casting par sacar getText()
                    i.setVisible(false);
                    i.setEnabled(false);
                }
                
            }
        }
        
    javax.swing.JButton regresarButton = new javax.swing.JButton(); 
    
    regresarButton.setText("Regresar");  // Sin texto inicial
                           //x   y    horiz vertical
    regresarButton.setBounds(80, 50, 100, 30); // Ajustar el número de columnas (ancho del campo)
    
    regresarButton.addActionListener(new java.awt.event.ActionListener(){ //creacion de boton de crearUsuario
       public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (boton.getText().equals("Crear Usuario")) {
                deshabilitarComponentes();
                usuarioButtonActionPerformed(evento); 
            }
       }
    });
    fondoPanel.add(regresarButton); //lo añade y recarga el panel
    fondoPanel.revalidate();
    fondoPanel.repaint();
    }
    
    
    private void usuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioButtonActionPerformed

    
    javax.swing.JButton crearUsuario = new javax.swing.JButton(); //boton de crear usuario 
    javax.swing.JButton usarUsuario = new javax.swing.JButton(); //no usado de momento
    
    crearUsuario.setText("Crear Usuario");
    crearUsuario.setBounds(50, 100, 200, 30); // Ajustar el número de columnas (x, y, horizontal y vertical)
    
    crearUsuario.addActionListener(new java.awt.event.ActionListener(){ //creacion de boton de crearUsuario
       public void actionPerformed(java.awt.event.ActionEvent evt) {
            crearUsuarioActionPerformed(evt);
            regresar(crearUsuario,evt);
            
       }
    });
    
    
    fondoPanel.add(crearUsuario); //lo añade y recarga el panel
    fondoPanel.revalidate();
    fondoPanel.repaint();
    
    tituloLabel.setText("Usuario");
    deshabilitarInicio();

    }//GEN-LAST:event_usuarioButtonActionPerformed

    private void inspectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inspectButtonActionPerformed
    
     private void crearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                              
        //desaparecer boton
        esconderBoton("Crear Usuario");
        
        //creacion de campos para escribir jtextfield y labels
        javax.swing.JTextField nombreField = new javax.swing.JTextField(); 
        javax.swing.JTextField apellidosField = new javax.swing.JTextField(); 
        javax.swing.JTextField telefonoField = new javax.swing.JTextField(); 
        javax.swing.JTextField correoField = new javax.swing.JTextField(); 
        javax.swing.JTextField direccionField = new javax.swing.JTextField(); 
        javax.swing.JTextField idField = new javax.swing.JTextField(); 
        javax.swing.JTextField pinField = new javax.swing.JTextField(); 
        
        javax.swing.JTextField tajetaField = new javax.swing.JTextField();
        javax.swing.JTextField vencimientoField = new javax.swing.JTextField();
        javax.swing.JTextField validacionField = new javax.swing.JTextField();
        
        
        javax.swing.JLabel nombreLabel = new javax.swing.JLabel();
        nombreLabel.setText("Nombre");
        javax.swing.JLabel apellidosLabel = new javax.swing.JLabel(); 
        apellidosLabel.setText("Apellidos");
        javax.swing.JLabel telefonoLabel = new javax.swing.JLabel();
        telefonoLabel.setText("Telefono");
        javax.swing.JLabel correoLabel = new javax.swing.JLabel(); 
        correoLabel.setText("Correo");
        javax.swing.JLabel direccionLabel = new javax.swing.JLabel(); 
        direccionLabel.setText("Direccion Fisica");
        javax.swing.JLabel idLabel = new javax.swing.JLabel(); 
        idLabel.setText("ID");
        javax.swing.JLabel pinLabel = new javax.swing.JLabel();
        pinLabel.setText("PIN");
        javax.swing.JLabel tajetaLabel = new javax.swing.JLabel();
        tajetaLabel.setText("Tarjeta");
        javax.swing.JLabel vencimientoLabel = new javax.swing.JLabel();
        vencimientoLabel.setText("Vencimiento Tarjeta");
        javax.swing.JLabel validacionLabel = new javax.swing.JLabel();
        validacionLabel.setText("Codigo Validacion Tarjeta");

        //listas que guardan los field y labels
        List<JTextField> listadoField = new ArrayList();
        List<JLabel> listadoLabelUser = new ArrayList();
        
        List<JLabel> listadoLabelTarjeta = new ArrayList();
        List<JTextField> listadoFieldTarjeta = new ArrayList();
        
        
        //guardar los elementos para su creacion
        Collections.addAll(listadoField, nombreField, apellidosField, telefonoField, correoField, direccionField, idField, pinField);
        Collections.addAll(listadoLabelUser, nombreLabel, apellidosLabel, telefonoLabel, correoLabel, direccionLabel, idLabel, pinLabel);
        
        Collections.addAll(listadoLabelTarjeta, tajetaLabel, vencimientoLabel, validacionLabel);
        Collections.addAll(listadoFieldTarjeta, tajetaField, vencimientoField, validacionField);
        
        
        int y = 100; //para la separacion entre componentes Y
        for (JTextField i : listadoField){

            i.setText("");  // Sin texto inicial
            i.setEditable(true);  // Para que sea editable o se pueda escribir
            i.setColumns(20);  // Ajustar el número de columnas (ancho del campo)
            
            i.setBounds(350, y, 200, 30); //(x,y,horizontal,vertical)
            y += 50;
            fondoPanel.add(i);
            fondoPanel.revalidate();
            fondoPanel.repaint();
        }
        y=100;
        for (JLabel i : listadoLabelUser){

            i.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));  // fuente, tipo (osea cursiva, negrita etc), tamaño 

            i.setForeground(java.awt.Color.WHITE);  // Color

            i.setBounds(200, y, 250, 30);  // (x,y,horizontal,vertical)
            
            
            y += 50;

            fondoPanel.add(i); //lo añade y recarga el panel
            fondoPanel.revalidate();
            fondoPanel.repaint();
        }
        y=100;
        for (JLabel i : listadoLabelTarjeta){
            i.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));  // fuente, tipo (osea cursiva, negrita etc), tamaño 

            i.setForeground(java.awt.Color.WHITE);  // Color

            i.setBounds(600, y, 200, 30);  // (x,y,horizontal,vertical)
            
            
            y += 50;

            fondoPanel.add(i); //lo añade y recarga el panel
            fondoPanel.revalidate();
            fondoPanel.repaint();
        }
        y=100;
        for (JTextField i : listadoFieldTarjeta){

            i.setText("");  // Sin texto inicial
            i.setEditable(true);  // Para que sea editable o se pueda escribir
            i.setColumns(20);  // Ajustar el número de columnas (ancho del campo)

            i.setBounds(800, y, 200, 30);  // (x,y,horizontal,vertical)
            
            
            y += 50;

            fondoPanel.add(i); //lo añade y recarga el panel
            fondoPanel.revalidate();
            fondoPanel.repaint();
        }
       
    javax.swing.JButton ingresarUsuario = new javax.swing.JButton(); //boton para ingresar datoss
    
    ingresarUsuario.setText("Ingresar"); 
    ingresarUsuario.setBounds(350, 450, 200, 30); // (x,y,horizontal,vertical)
    
    //indica si salio bien o mal el ingreso
    javax.swing.JLabel indicadorLabel = new javax.swing.JLabel();
    indicadorLabel.setText("");
    indicadorLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));  // fuente, tipo (osea cursiva, negrita etc), tamaño 
    indicadorLabel.setForeground(java.awt.Color.WHITE); 
    indicadorLabel.setBounds(700, 300, 500, 150);  // (x,y,horizontal,vertical)
    
    
    fondoPanel.add(indicadorLabel); //lo añade y recarga el panel
    fondoPanel.revalidate();
    fondoPanel.repaint();

    ingresarUsuario.addActionListener(new java.awt.event.ActionListener(){ //creacion de boton de ingresarUsuario
       public void actionPerformed(java.awt.event.ActionEvent evt) {
            listadoField.clear(); //limpia la lista
            Collections.addAll(listadoField, nombreField, apellidosField, telefonoField, correoField, direccionField, idField, pinField); //la vuelve a ingresar para actualizar las lista
            ingresarButtonActionPerformed(evt ,  listadoField,listadoFieldTarjeta, indicadorLabel); //reciibe aparte del evento y la lista el label para indicar errores o si hay exito
            
            }
        });
    
    fondoPanel.add(ingresarUsuario); //lo añade y recarga el panel
    fondoPanel.revalidate();
    fondoPanel.repaint();
    }
    
    private void ingresarButtonActionPerformed(java.awt.event.ActionEvent evt, List<JTextField> pListadoField, List<JTextField> pListadoFieldTarjeta, JLabel pIndicadorLabel ) {                                           
        //variables:
        

        
        
            try {
                String nombre = pListadoField.get(0).getText();
                String apellidos = pListadoField.get(1).getText();
                String correo= pListadoField.get(3).getText();
                String direccionFisica = pListadoField.get(4).getText();
                String idUsuario = pListadoField.get(5).getText();
                String pin = pListadoField.get(6).getText();
                String tarjetaVencimiento = pListadoFieldTarjeta.get(1).getText();
                int telefono = Integer.parseInt(pListadoField.get(2).getText());
                long tarjeta = Long.parseLong(pListadoFieldTarjeta.get(0).getText());
                int codigoVal = Integer.parseInt(pListadoFieldTarjeta.get(2).getText());
                
                usuariosParqueo nuevoUsuario = new usuariosParqueo(nombre,apellidos,telefono,correo,direccionFisica,idUsuario,pin,tarjeta,tarjetaVencimiento,codigoVal);
                System.out.println(nuevoUsuario.toString());
                System.out.println(nuevoUsuario.toStringUsuarios());
                
                
            }
            catch(NumberFormatException e){ //si el error es por el formato (telefono ya que solo recibe int)
                    pIndicadorLabel.setText("Campos vacios ''!");
                    pIndicadorLabel.setForeground(java.awt.Color.RED);
                    return;
            }

            catch(Exception e){ //cualquier otro error
                    pIndicadorLabel.setText(e.getMessage()); //solo el mensaje del error
                    pIndicadorLabel.setForeground(java.awt.Color.RED);

                    return;
            }
        
        
        for (JTextField i : pListadoField ){ //vaciar todo
            i.setText("");     
        }
        pIndicadorLabel.setText("Usuario ingresado con exito!");
        pIndicadorLabel.setForeground(java.awt.Color.GREEN);
                
    }
    
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_adminButtonActionPerformed

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
        // TODO add your handling code here:
        tituloLabel.setText("Sistema Parqueos Callejeros");
        deshabilitarComponentes();
        habilitarInicio();
        
    }//GEN-LAST:event_volverButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIProyecto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIProyecto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIProyecto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProyecto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIProyecto1().setVisible(true);
            }
        });

    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel fondoPanel;
    private javax.swing.JButton inspectButton;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JButton usuarioButton;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}
