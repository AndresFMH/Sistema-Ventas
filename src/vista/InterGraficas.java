package vista;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import static vista.FrmMenu.jDesktopPane_menu;

/**
 *
 * @author andre
 */
public class InterGraficas extends javax.swing.JInternalFrame {

    public static String fecha_inicio = "", fecha_fin = "";

    public InterGraficas() {
        initComponents();
        this.setSize(new Dimension(450, 300));
        this.setTitle("Historial Ventas");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGraficar = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        fechaFin = new com.toedter.calendar.JDateChooser();
        Label_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione Fechas");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha inicio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 100, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha fin:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 100, -1));

        btnGraficar.setBackground(new java.awt.Color(0, 204, 204));
        btnGraficar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/historial1.png"))); // NOI18N
        btnGraficar.setText("Graficar Ventas");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGraficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 230, 50));

        fechaInicio.setDateFormatString("yyyy-MM-dd");
        fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 140, -1));

        fechaFin.setDateFormatString("yyyy-MM-dd");
        fechaFin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 140, -1));

        Label_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.png"))); // NOI18N
        Label_wallpaper.setText("jLabel1");
        getContentPane().add(Label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        fecha_inicio = ((JTextField) fechaInicio.getDateEditor().getUiComponent()).getText();
        fecha_fin = ((JTextField) fechaFin.getDateEditor().getUiComponent()).getText();
        InterGraficaVentas interGraficaVentas = new InterGraficaVentas();
        jDesktopPane_menu.add(interGraficaVentas);
        interGraficaVentas.setVisible(true);
    }//GEN-LAST:event_btnGraficarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_wallpaper;
    private javax.swing.JButton btnGraficar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}
