package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

/**
 * Clase que maneja las operaciones relacionadas con el registro de ventas en la
 * base de datos. Incluye métodos para guardar la cabecera de venta, guardar el
 * detalle de venta y actualizar la cabecera de venta. Utiliza la clase Conexion
 * para establecer la conexión con la base de datos.
 *
 * @author andre
 */
public class Ctrl_RegistrarVenta {

    /**
     * Variable estática que guarda el último ID de la cabecera de venta
     * registrada.
     */
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

    /**
     * Método para guardar la cabecera de una venta en la base de datos.
     *
     * @param objeto Objeto de la clase CabeceraVenta que contiene la
     * información de la cabecera de venta.
     * @return true si la cabecera se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar la cabecera de venta.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cabecera_venta values(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0);//ID
            consulta.setInt(2, objeto.getIdCliente());
            consulta.setDouble(3, objeto.getValorPagar());
            consulta.setString(4, objeto.getFechaVenta());
            consulta.setInt(5, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            // Obtener el ID generado para la cabecera de venta.
            ResultSet rs = consulta.getGeneratedKeys();
            while (rs.next()) {
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera: " + e);
        }
        return respuesta;
    }

    /**
     * Método para guardar el detalle de una venta en la base de datos.
     *
     * @param objeto Objeto de la clase DetalleVenta que contiene la información
     * del detalle de venta.
     * @return true si el detalle se guarda exitosamente, false en caso
     * contrario.
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // Preparar la consulta SQL para insertar el detalle de venta.
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//ID
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubTotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getIva());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());
            // Ejecutar la consulta y verificar si se insertó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }

    /**
     * Método para actualizar la información de la cabecera de una venta en la
     * base de datos.
     *
     * @param objeto Objeto de la clase CabeceraVenta con la información
     * actualizada.
     * @param idCabeceraVenta Identificador único de la cabecera de venta a
     * actualizar.
     * @return true si la actualización se realiza exitosamente, false en caso
     * contrario.
     */
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Preparar la consulta SQL para actualizar la cabecera de venta.
            PreparedStatement consulta = cn.prepareStatement("update tb_cabecera_venta set idCliente = ?,  estado = ? where idCabeceraVenta = '" + idCabeceraVenta + "'");
            consulta.setInt(1, objeto.getIdCliente());
            consulta.setInt(2, objeto.getEstado());
            // Ejecutar la consulta y verificar si se actualizó correctamente.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        }
        return respuesta;
    }
    
    public int obtenerUltimoIdCabeceraVenta() {
    int ultimoId = 0;
    Connection cn = Conexion.conectar();
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(idCabeceraVenta) AS ultimoId FROM tb_cabecera_venta");
        if (rs.next()) {
            ultimoId = rs.getInt("ultimoId");
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener último ID de cabecera de venta: " + e);
    }
    return ultimoId;
}
}
