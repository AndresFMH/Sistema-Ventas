package vista;

import conexion.Conexion;
import controlador.Ctrl_Cliente;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Cliente;

/**
 *
 * @author andre
 */
public class InterGestionarCliente extends javax.swing.JInternalFrame {

    private int idCliente;

    public InterGestionarCliente() {
        initComponents();
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestion Clientes");
        //Cargar Tablas
        this.CargarTablaClientes();

        //Insertar imagen label
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.png");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
        Label_wallpaper.setIcon(icono);
        this.repaint();

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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        Label_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administrar Clientes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 730, 270));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(51, 204, 0));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 130, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Teléfono:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Apellido:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Dirección:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 100, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Cédula:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 100, -1));

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 170, -1));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, -1));

        txtApellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 170, -1));

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 170, -1));

        txtCedula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 100));
        getContentPane().add(Label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        if (txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && txtCedula.getText().isEmpty() && txtTelefono.getText().isEmpty() && txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa todos los campos");
        } else {
            Cliente cliente = new Cliente();
            Ctrl_Cliente controlCliente = new Ctrl_Cliente();

            cliente.setNombre(txtNombre.getText().trim());
            cliente.setApellido(txtApellido.getText().trim());
            cliente.setCedula(txtCedula.getText().trim());
            cliente.setTelefono(txtTelefono.getText().trim());
            cliente.setDireccion(txtDireccion.getText().trim());

            if (controlCliente.actualizar(cliente, idCliente)) {
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                this.CargarTablaClientes();
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Actualizar");
                this.Limpiar();
            }

        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        Ctrl_Cliente controlCliente = new Ctrl_Cliente();
        if (idCliente == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
        } else {
            if (!controlCliente.eliminar(idCliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                this.CargarTablaClientes();
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                this.Limpiar();
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_wallpaper;
    public static javax.swing.JTable TableClientes;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    //Metodo para limpiar
    private void Limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtCedula.setText("");

    }

    /**
     *
     * Metodo para mostrar todos los clientes registrados
     */
    private void CargarTablaClientes() {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select * from tb_cliente";
        Statement st;

        try {

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            InterGestionarCliente.TableClientes = new JTable(model);
            InterGestionarCliente.jScrollPane1.setViewportView(InterGestionarCliente.TableClientes);

            model.addColumn("N°");//ID
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Cedula");
            model.addColumn("Telefono");
            model.addColumn("Direccion");
            model.addColumn("Estado");

            while (rs.next()) {
                Object fila[] = new Object[7];
                for (int i = 0; i < 7; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla clientes " + e);
        }

        TableClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = TableClientes.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > - 1) {

                    idCliente = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosClientes(idCliente);//metodo
                }
            }
        });
    }

    //Metodo que envia datos seleccionados
    private void EnviarDatosClientes(int idCliente) {
        try {

            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "select * from tb_cliente where idCliente = '" + idCliente + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                txtCedula.setText(rs.getString("cedula"));
                txtTelefono.setText(rs.getString("telefono"));
                txtDireccion.setText(rs.getString("direccion"));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar cliente: " + e);
        }
    }

}
