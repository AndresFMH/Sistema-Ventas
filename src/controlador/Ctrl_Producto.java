package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Producto;

/**
 * Clase que maneja las operaciones relacionadas con la entidad Producto en la
 * base de datos. Incluye métodos para guardar, verificar existencia,
 * actualizar, eliminar y actualizar el stock de productos. Utiliza la clase
 * Conexion para establecer la conexión con la base de datos.
 *
 * @author andre
 */
public class Ctrl_Producto {

    /**
     * Método para guardar un nuevo producto en la base de datos.
     *
     * @param objeto Objeto de la clase Producto que contiene la información del
     * producto a guardar.
     * @return true si el producto se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar un nuevo producto.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//ID
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIva());
            consulta.setInt(7, objeto.getIdCategoria());
            consulta.setInt(8, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }
        return respuesta;
    }

    /**
     * Método para verificar si ya existe un producto con el mismo nombre en la
     * base de datos.
     *
     * @param producto Nombre del producto a verificar.
     * @return true si el producto ya existe, false si no existe.
     */
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        // Consulta SQL para verificar la existencia del producto.
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";
        Statement st;

        try {
            // Establecer conexión y ejecutar la consulta.
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // Verificar si se obtuvieron resultados.
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    /**
     * Método para actualizar la información de un producto existente en la base
     * de datos.
     *
     * @param objeto Objeto de la clase Producto con la información actualizada.
     * @param idProducto Identificador único del producto a actualizar.
     * @return true si la actualización se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean actualizar(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar la información del producto.
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set nombre = ?, cantidad = ?, precio = ?, descripcion = ?, porcentajeIva = ?, idCategoria = ?, estado = ? where idProducto = '" + idProducto + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getPorcentajeIva());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }

    /**
     * Método para eliminar un producto de la base de datos.
     * 
     * @param idProducto Identificador único del producto a eliminar.
     * @return true si la eliminación se realiza exitosamente, false en caso contrario.
     */
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para eliminar el producto.
            PreparedStatement consulta = cn.prepareStatement(
                    " delete from tb_producto where idProducto = '" + idProducto + "'");
            // Ejecutar la consulta y verificar si se eliminó correctamente.
            consulta.executeUpdate();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }

    /**
     * Método para actualizar el stock de un producto en la base de datos.
     * 
     * @param object Objeto de la clase Producto con la cantidad actualizada.
     * @param idProducto Identificador único del producto a actualizar.
     * @return true si la actualización del stock se realiza exitosamente, false en caso contrario.
     */
    public boolean actualizarStock(Producto object, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar el stock del producto.
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto = '" + idProducto + "'");
            consulta.setInt(1, object.getCantidad());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e);
        }
        return respuesta;
    }
}
