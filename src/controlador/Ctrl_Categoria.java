package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import modelo.Categoria;

/**
 * Clase que maneja las operaciones relacionadas con la entidad Categoria en la
 * base de datos. Incluye métodos para guardar, verificar existencia, actualizar
 * y eliminar categorías. Utiliza la clase Conexion para establecer la conexión
 * con la base de datos.
 *
 * @author andre
 */
public class Ctrl_Categoria {

    /**
     * Método para registrar una nueva categoría en la base de datos.
     *
     * @param objeto Objeto de la clase Categoria que contiene la información de
     * la categoría a guardar.
     * @return true si la categoría se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar una nueva categoría.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            // Cerrar la conexión.
            cn.close();
        } catch (SQLException e) {
            // Imprimir mensaje de error en caso de fallo en la operación.
            System.out.println("Error al guardar categoria: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

    /**
     * Método para verificar si ya existe una categoría con el mismo nombre en la base de datos.
     * 
     * @param categoria Nombre de la categoría a verificar.
     * @return true si la categoría ya existe, false si no existe.
     */
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        // Consulta SQL para verificar la existencia de la categoría.
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
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
            // Imprimir mensaje de error en caso de fallo en la operación.
            System.out.println("Error al consultar categoria: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

    /**
     * Método para actualizar una categoría existente en la base de datos.
     * 
     * @param objeto Objeto de la clase Categoria con la información actualizada.
     * @param idCategoria Identificador único de la categoría a actualizar.
     * @return true si la actualización se realiza exitosamente, false en caso contrario.
     */
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar la descripción de la categoría.
            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria = '" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            // Cerrar la conexión.
            cn.close();
        } catch (SQLException e) {
            // Imprimir mensaje de error en caso de fallo en la operación.
            System.out.println("Error al actualizar categoria: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

    /**
     * Método para eliminar una categoría de la base de datos.
     * 
     * @param idCategoria Identificador único de la categoría a eliminar.
     * @return true si la eliminación se realiza exitosamente, false en caso contrario.
     */
    public boolean eliminar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para eliminar la categoría.
            PreparedStatement consulta = cn.prepareStatement(
                    " delete from tb_categoria where idCategoria = '" + idCategoria + "'");
             // Ejecutar la consulta y verificar si se eliminó correctamente.
            consulta.executeUpdate();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            // Cerrar la conexión.
            cn.close();
        } catch (SQLException e) {
            // Imprimir mensaje de error en caso de fallo en la operación.
            System.out.println("Error al eliminar categoria: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

}
