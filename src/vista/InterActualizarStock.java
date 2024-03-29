package vista;

import conexion.Conexion;
import controlador.Ctrl_Producto;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 *
 * @author andre
 */
public class InterActualizarStock extends javax.swing.JInternalFrame {

    // Variables para almacenar información sobre el producto seleccionado
    int idProducto = 0;
    int cantidad = 0;

    /**
     * Constructor de la interfaz.
     */
    public InterActualizarStock() {
        initComponents();
        this.setTitle("Actualizar Stock");
        this.setSize(new Dimension(400, 300));
        this.CargarComboProductos();// Cargar la lista de productos al iniciar la interfaz.
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
        jLabel4 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        txtStockNuevo = new javax.swing.JTextField();
        BoxProducto = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        Label_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Actualizar Stock");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Producto:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Stock Actual:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 110, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Stock Nuevo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, -1));

        txtStockActual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStockActual.setEnabled(false);
        getContentPane().add(txtStockActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 170, -1));

        txtStockNuevo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtStockNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 170, -1));

        BoxProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BoxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Producto:", "Item 2", "Item 3", "Item 4" }));
        BoxProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxProductoActionPerformed(evt);
            }
        });
        getContentPane().add(BoxProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 170, -1));

        btnActualizar.setBackground(new java.awt.Color(0, 255, 0));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 170, 30));

        Label_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.png"))); // NOI18N
        getContentPane().add(Label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoxProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxProductoActionPerformed
        this.MostrarStock();
    }//GEN-LAST:event_BoxProductoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        //validamos seleccion del producto
        if (!BoxProducto.getSelectedItem().equals("Seleccione Producto:")) {
            //Validamos campos vacios
            if (!txtStockNuevo.getText().isEmpty()) {
                //validamos que el usuario no ingrese otros caracteres no numericos
                boolean validacion = validar(txtStockNuevo.getText().trim());
                if (validacion == true) {
                    //validar que la cantidad sea mayor cero (0)
                    if (Integer.parseInt(txtStockNuevo.getText()) > 0) {
                        // Crear instancia de Producto y controlador de producto
                        Producto producto = new Producto();
                        Ctrl_Producto controlProducto = new Ctrl_Producto();
                        int stockActual = Integer.parseInt(txtStockActual.getText().trim());
                        int stockNuevo = Integer.parseInt(txtStockNuevo.getText().trim());
                        stockNuevo = stockActual + stockNuevo;
                        producto.setCantidad(stockNuevo);
                        // Actualizar el stock en la base de datos
                        if (controlProducto.actualizarStock(producto, idProducto)) {
                            JOptionPane.showMessageDialog(null, "Stock Actualizado");
                            BoxProducto.setSelectedItem("Seleccione Producto:");
                            txtStockActual.setText("");
                            txtStockNuevo.setText("");
                            this.CargarComboProductos(); // Recargar la lista de productos después de la actualización.
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar stock");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad no puede ser 0 ni negativa");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad no permite caracteres no numericos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la nueva cantidad");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxProducto;
    private javax.swing.JLabel Label_wallpaper;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtStockNuevo;
    // End of variables declaration//GEN-END:variables

    //Metodo para cargar los productos
    private void CargarComboProductos() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            BoxProducto.removeAllItems();
            BoxProducto.addItem("Seleccione Producto:");
            while (rs.next()) {
                BoxProducto.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los productos en: " + e);
        }
    }

    //Metodo mostrar Stock
    private void MostrarStock() {
        try {
            Connection cn = Conexion.conectar();
            String sql = "select * from tb_producto where nombre = '" + this.BoxProducto.getSelectedItem() + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
                cantidad = rs.getInt("cantidad");
                txtStockActual.setText(String.valueOf(cantidad));
            } else {
                txtStockActual.setText("");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener stock " + e);
        }
    }

    //Metodo de validacion de caracteres no numericos
    private boolean validar(String valor) {
        int num;
        try {
            num = Integer.parseInt(valor);
            return true; // Si la conversión a entero es exitosa, significa que la cadena es un número.
        } catch (NumberFormatException e) {
            return false; // Si ocurre una excepción, la cadena no es un número.
        }
    }
}
