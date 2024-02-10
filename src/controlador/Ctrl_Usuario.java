package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Usuario;

/**
 * Clase que maneja las operaciones relacionadas con la entidad Usuario en la
 * base de datos. Incluye métodos para guardar, verificar existencia, iniciar
 * sesión, actualizar y eliminar usuarios. Utiliza la clase Conexion para
 * establecer la conexión con la base de datos.
 *
 * @author andre
 */
public class Ctrl_Usuario {

    /**
     * Método para guardar un nuevo usuario en la base de datos.
     *
     * @param objeto Objeto de la clase Usuario que contiene la información del
     * usuario a guardar.
     * @return true si el usuario se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar un nuevo usuario.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_usuario values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//ID
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getUsuario());
            consulta.setString(5, objeto.getPassword());
            consulta.setString(6, objeto.getTelefono());
            consulta.setInt(7, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    /**
     * Método para verificar si ya existe un usuario con el mismo nombre de
     * usuario en la base de datos.
     *
     * @param usuario Nombre de usuario a verificar.
     * @return true si el usuario ya existe, false si no existe.
     */
    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        // Consulta SQL para verificar la existencia del usuario.
        String sql = "select usuario from tb_usuario where usuario = '" + usuario + "';";
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
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }

    /**
     * Método para iniciar sesión de un usuario.
     *
     * @param objeto Objeto de la clase Usuario con el nombre de usuario y
     * contraseña.
     * @return true si la sesión se inicia correctamente, false en caso
     * contrario.
     */
    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        // Consulta SQL para verificar las credenciales del usuario.
        String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "'";
        Statement st;

        try {
            // Ejecutar la consulta y verificar si se obtuvieron resultados.
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion");
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
        }
        return respuesta;
    }

    /**
     * Método para actualizar la información de un usuario existente en la base
     * de datos.
     *
     * @param objeto Objeto de la clase Usuario con la información actualizada.
     * @param idUsuario Identificador único del usuario a actualizar.
     * @return true si la actualización se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar la información del usuario.
            PreparedStatement consulta = cn.prepareStatement("update tb_usuario set nombre = ?, apellido = ?, usuario = ?, password = ?, telefono = ?, estado = ? where idUsuario = '" + idUsuario + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
        return respuesta;
    }

    /**
     * Método para eliminar un usuario de la base de datos.
     *
     * @param idUsuario Identificador único del usuario a eliminar.
     * @return true si la eliminación se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para eliminar el usuario.
            PreparedStatement consulta = cn.prepareStatement(
                    " delete from tb_usuario where idUsuario = '" + idUsuario + "'");
            // Ejecutar la consulta y verificar si se eliminó correctamente.
            consulta.executeUpdate();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }
        return respuesta;
    }
}
