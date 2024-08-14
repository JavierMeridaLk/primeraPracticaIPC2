/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fronted;

/**
 *
 * @author xavi
 */
public class FramePrincipal extends javax.swing.JFrame {

    //constructor del frame principal
    public FramePrincipal() {
        
        initComponents();
        //Dar un size al frame principal
        this.setSize(600, 700);
        //centrar el frame al centro
        this.setLocationRelativeTo(null);
        //asignar un titulo al frame principal
        this.setTitle("GESTION DE TARJETAS");
        //bloquear el tamaño del frame
        this.setResizable(false);
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
        setPreferredSize(new java.awt.Dimension(600, 700));

        JDprincipal.setPreferredSize(new java.awt.Dimension(580, 605));
        JDprincipal.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout JDprincipalLayout = new javax.swing.GroupLayout(JDprincipal);
        JDprincipal.setLayout(JDprincipalLayout);
        JDprincipalLayout.setHorizontalGroup(
            JDprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
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
        barraMenu.add(jMenuMovimientos);

        jMenuConsultas.setText("Consultar tarjeta");
        jMenuConsultas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        barraMenu.add(jMenuConsultas);

        jMenuAutorizacion.setText("Atoruzación");
        jMenuAutorizacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JDprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
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
        JInternalFrameSolicitud soli = new JInternalFrameSolicitud();
        JDprincipal.add(soli);
        soli.setVisible(true);
        System.out.println("dddd");
    }//GEN-LAST:event_jMenuSolicitudesMouseClicked

   

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
