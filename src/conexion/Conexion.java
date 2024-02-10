package conexion;

import java.sql.Connection; //Importa la clase Connection del paquete java.sql para manejar conexiones a bases de datos.
import java.sql.DriverManager; //Importa la clase DriverManager del paquete java.sql para manejar la administración de controladores de bases de datos.
import java.sql.SQLException; //Importa la clase SQLException del paquete java.sql para manejar excepciones relacionadas con SQL.

/**
 * Clase que maneja la conexión a la base de datos. La conexión se realiza
 * utilizando JDBC para MySQL.
 *
 * @author andre
 */
public class Conexion {

    /**
     * Método para establecer una conexión a la base de datos local.
     *
     * @return Objeto Connection que representa la conexión a la base de datos.
     */
    public static Connection conectar() {
        try {
            // Establecer la conexión con la base de datos MySQL en localhost, utilizando el usuario "root" y la contraseña "anfel31280".
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas", "root", "anfel31280");
            // Devolver la conexión establecida.
            return cn;

        } catch (SQLException e) {
            // Imprimir mensaje de error en caso de fallo en la conexión.
            System.out.println("Error en la conexion local " + e);
        }
        // En caso de error, devolver null.
        return null;
    }
}
