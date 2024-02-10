package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;

/**
 * Clase que maneja las operaciones relacionadas con la entidad Cliente en la
 * base de datos. Incluye métodos para guardar, verificar existencia, actualizar
 * y eliminar clientes. Utiliza la clase Conexion para establecer la conexión
 * con la base de datos.
 *
 * @author andre
 */
public class Ctrl_Cliente {

    /**
     * Método para guardar un nuevo cliente en la base de datos.
     *
     * @param objeto Objeto de la clase Cliente que contiene la información del
     * cliente a guardar.
     * @return true si el cliente se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar un nuevo cliente.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//ID
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getCedula());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            // Cerrar la conexión.
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

    /**
     * Método para verificar si ya existe un cliente con la misma cédula en la
     * base de datos.
     *
     * @param cedula Cédula del cliente a verificar.
     * @return true si el cliente ya existe, false si no existe.
     */
    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        // Consulta SQL para verificar la existencia del cliente.
        String sql = "select cedula from tb_cliente where cedula = '" + cedula + "';";
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
            System.out.println("Error al consultar cliente: " + e);
        }
        // Devolver el resultado de la operación.
        return respuesta;
    }

    /**
     * Método para actualizar la información de un cliente existente en la base
     * de datos.
     *
     * @param objeto Objeto de la clase Cliente con la información actualizada.
     * @param idCliente Identificador único del cliente a actualizar.
     * @return true si la actualización se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean actualizar(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar la información del cliente.
            PreparedStatement consulta = cn.prepareStatement("update tb_cliente set nombre = ?, apellido = ?, cedula = ?, telefono = ?, direccion = ?, estado = ? where idCliente = '" + idCliente + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    }

    /**
     * Método para eliminar un cliente de la base de datos.
     *
     * @param idCliente Identificador único del cliente a eliminar.
     * @return true si la eliminación se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para eliminar el cliente.
            PreparedStatement consulta = cn.prepareStatement(
                    " delete from tb_cliente where idCliente = '" + idCliente + "'");
            // Ejecutar la consulta y verificar si se eliminó correctamente.
            consulta.executeUpdate();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }

}
