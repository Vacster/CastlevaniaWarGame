/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Visual;

/**
 *
 * @author juans_000
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jugar = new javax.swing.JButton();
        miCuenta = new javax.swing.JButton();
        reportes = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jugar.setFont(new java.awt.Font("Pristina", 3, 24)); // NOI18N
        jugar.setText("Jugar");
        jugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jugarMouseClicked(evt);
            }
        });
        getContentPane().add(jugar);
        jugar.setBounds(400, 260, 120, 37);

        miCuenta.setFont(new java.awt.Font("Pristina", 3, 24)); // NOI18N
        miCuenta.setText("Mi Cuenta");
        miCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miCuentaMouseClicked(evt);
            }
        });
        getContentPane().add(miCuenta);
        miCuenta.setBounds(390, 320, 140, 37);

        reportes.setFont(new java.awt.Font("Pristina", 3, 24)); // NOI18N
        reportes.setText("Reportes");
        reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportesMouseClicked(evt);
            }
        });
        getContentPane().add(reportes);
        reportes.setBounds(400, 380, 120, 37);

        logout.setFont(new java.awt.Font("Pristina", 3, 24)); // NOI18N
        logout.setText("Log Out");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        getContentPane().add(logout);
        logout.setBounds(720, 590, 120, 37);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/Visual/Menu.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 844, 629);

        setSize(new java.awt.Dimension(860, 668));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        this.dispose();
        new MenuInicial().setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void jugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugarMouseClicked
        this.dispose();
        new Jugar().setVisible(true);
    }//GEN-LAST:event_jugarMouseClicked

    private void miCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCuentaMouseClicked
        this.dispose();
        new MiCuenta().setVisible(true);
    }//GEN-LAST:event_miCuentaMouseClicked

    private void reportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesMouseClicked
        this.dispose();
        new Reportes().setVisible(true);
    }//GEN-LAST:event_reportesMouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jugar;
    private javax.swing.JButton logout;
    private javax.swing.JButton miCuenta;
    private javax.swing.JButton reportes;
    // End of variables declaration//GEN-END:variables
}