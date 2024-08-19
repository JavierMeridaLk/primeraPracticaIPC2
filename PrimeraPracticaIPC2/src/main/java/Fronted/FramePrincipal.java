/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fronted;

import Backend.Gestor;
import java.awt.Dimension;

/**
 *
 * @author xavi
 */
public class FramePrincipal extends javax.swing.JFrame {
    
    private Gestor gestor;
    //private JInternalFrameSolicitud soli;
    //private JInternalFrameAutorizacion autorizacion;

    //constructor del frame principal
    public FramePrincipal(Gestor gestor) {
        
        this.gestor=gestor;
        initComponents();
        //Dar un size al frame principal
        this.setSize(760, 700);
        //centrar el frame al centro
        this.setLocationRelativeTo(null);
        //asignar un titulo al frame principal
        this.setTitle("GESTION DE TARJETAS");
        //bloquear el tamaño del frame
        this.setResizable(false);
        
        
        //soli = new JInternalFrameSolicitud(gestor);
        //autorizacion = new JInternalFrameAutorizacion(gestor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDprincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        jMenuSolicitudes = new javax.swing.JMenu();
        jMenuMovimientos = new javax.swing.JMenu();
        jMenuConsultas = new javax.swing.JMenu();
        jMenuAutorizacion = new javax.swing.JMenu();
        jMenuCancelacion = new javax.swing.JMenu();
        jMenuReportes = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(760, 700));
        setResizable(false);

        JDprincipal.setPreferredSize(new java.awt.Dimension(580, 605));
        JDprincipal.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout JDprincipalLayout = new javax.swing.GroupLayout(JDprincipal);
        JDprincipal.setLayout(JDprincipalLayout);
        JDprincipalLayout.setHorizontalGroup(
            JDprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JDprincipalLayout.setVerticalGroup(
            JDprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Century Gothic", 3, 24)); // NOI18N
        jLabel1.setText("Gestion de Tarjetas de Credito");

        jMenuSolicitudes.setText("Solicitudes");
        jMenuSolicitudes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jMenuSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSolicitudesMouseClicked(evt);
            }
        });
        barraMenu.add(jMenuSolicitudes);

        jMenuMovimientos.setText("Movimientos");
        jMenuMovimientos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jMenuMovimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuMovimientosMouseClicked(evt);
            }
        });
        barraMenu.add(jMenuMovimientos);

        jMenuConsultas.setText("Consultar tarjeta");
        jMenuConsultas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jMenuConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuConsultasMouseClicked(evt);
            }
        });
        barraMenu.add(jMenuConsultas);

        jMenuAutorizacion.setText("Atoruzación");
        jMenuAutorizacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jMenuAutorizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAutorizacionMouseClicked(evt);
            }
        });
        barraMenu.add(jMenuAutorizacion);

        jMenuCancelacion.setText("Cancelación");
        jMenuCancelacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        barraMenu.add(jMenuCancelacion);

        jMenuReportes.setText("Reportes");
        jMenuReportes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        barraMenu.add(jMenuReportes);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JDprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JDprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSolicitudesMouseClicked
        // TODO add your handling code here:
        //limpiar();
        JInternalFrameSolicitud soli = new JInternalFrameSolicitud(gestor);
        JDprincipal.add(soli);
        soli.setVisible(true);
        
        JDprincipal.setPreferredSize(new Dimension(soli.getWidth(), soli.getHeight()));
         this.pack();
    }//GEN-LAST:event_jMenuSolicitudesMouseClicked

    private void jMenuAutorizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAutorizacionMouseClicked
        //limpiar();
        
        JInternalFrameAutorizacion autorizacion = new JInternalFrameAutorizacion(gestor);
        JDprincipal.add(autorizacion);
        autorizacion.setVisible(true);
        
        JDprincipal.setPreferredSize(new Dimension(autorizacion.getWidth(), autorizacion.getHeight()));
         this.pack();
    }//GEN-LAST:event_jMenuAutorizacionMouseClicked

    private void jMenuMovimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuMovimientosMouseClicked
        // TODO add your handling code here:
        InternalFrameMovimientos movi = new InternalFrameMovimientos(gestor);
        JDprincipal.add(movi);
        movi.setVisible(true);
        
        JDprincipal.setPreferredSize(new Dimension(movi.getWidth(), movi.getHeight()));
         this.pack();
    }//GEN-LAST:event_jMenuMovimientosMouseClicked

    private void jMenuConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuConsultasMouseClicked
        // TODO add your handling code here:
        JInternalFrameConsulta consulta = new JInternalFrameConsulta(gestor);
        JDprincipal.add(consulta);
        consulta.setVisible(true);
        
        JDprincipal.setPreferredSize(new Dimension(consulta.getWidth(), consulta.getHeight()));
         this.pack();
        
    }//GEN-LAST:event_jMenuConsultasMouseClicked
    
   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane JDprincipal;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenuAutorizacion;
    private javax.swing.JMenu jMenuCancelacion;
    private javax.swing.JMenu jMenuConsultas;
    private javax.swing.JMenu jMenuMovimientos;
    private javax.swing.JMenu jMenuReportes;
    private javax.swing.JMenu jMenuSolicitudes;
    // End of variables declaration//GEN-END:variables
}
