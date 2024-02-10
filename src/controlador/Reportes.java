package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Clase para generar reportes en formato PDF. Incluye métodos para crear
 * reportes de clientes, productos, categorías y ventas. Utiliza la biblioteca
 * iTextPDF para la generación de documentos PDF. Utiliza la clase Conexion para
 * establecer la conexión con la base de datos.
 *
 * @author andre
 */
public class Reportes {

    /**
     * Método para generar un reporte de clientes en formato PDF.
     */
    public void ReporteClientes() {
        Document documento = new Document();
        try {
            // Crear el documento PDF y establecer la ruta de almacenamiento.
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\andre\\OneDrive\\Documentos\\Reportes/Reporte_Clientes.pdf"));
            // Agregar una imagen al encabezado.
            Image header = Image.getInstance("src/img/header1.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            // Formato del texto del documento.
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte Generado \nUniversal Trade\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de clientes \n\n");
            // Abrir el documento.
            documento.open();
            // Agregar la imagen y el párrafo al documento.
            documento.add(header);
            documento.add(parrafo);
            // Crear una tabla para mostrar los datos de los clientes.
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                // Establecer conexión con la base de datos.
                Connection cn = Conexion.conectar();
                // Consulta SQL para obtener los datos de los clientes.
                PreparedStatement pst = cn.prepareStatement(
                        "select idCliente, concat(nombre, ' ', apellido) as nombres, cedula, telefono, direccion from tb_cliente");
                ResultSet rs = pst.executeQuery();
                // Verificar si se obtuvieron resultados.
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    // Agregar la tabla al documento.
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            // Cerrar el documento y mostrar un mensaje de éxito.
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }

    /**
     * Método para generar un reporte de productos en formato PDF.
     */
    public void ReporteProductos() {
        Document documento = new Document();
        try {
            // Crear el documento PDF y establecer la ruta de almacenamiento.
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\andre\\OneDrive\\Documentos\\Reportes/Reporte_Productos.pdf"));
            // Agregar una imagen al encabezado.
            Image header = Image.getInstance("src/img/header1.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            // Formato del texto del documento.
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte Generado \nUniversal Trade\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de productos \n\n");
            // Abrir el documento.
            documento.open();
            // Agregar la imagen y el párrafo al documento.
            documento.add(header);
            documento.add(parrafo);
            // Crear una tabla para mostrar los datos de los productos.
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};
            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("Por. IVA");
            tabla.addCell("Categoria");

            try {
                // Establecer conexión con la base de datos.
                Connection cn = Conexion.conectar();
                // Consulta SQL para obtener los datos de los productos y sus categorías.
                PreparedStatement pst = cn.prepareStatement(
                        "select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIva, c.descripcion as categoria, p.estado "
                        + "from tb_producto as p, tb_categoria as c where p.idCategoria = c.idCategoria");
                ResultSet rs = pst.executeQuery();
                // Verificar si se obtuvieron resultados.
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    // Agregar la tabla al documento.
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            // Cerrar el documento y mostrar un mensaje de éxito.
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }

    /**
     * Método para generar un reporte de categorías en formato PDF.
     */
    public void ReporteCategorias() {
        Document documento = new Document();
        try {
            // Crear el documento PDF y establecer la ruta de almacenamiento.
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\andre\\OneDrive\\Documentos\\Reportes/Reporte_Categorias.pdf"));
            // Agregar una imagen al encabezado.
            Image header = Image.getInstance("src/img/header1.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            // Formato del texto del documento.
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte Generado \nUniversal Trade\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de categorias \n\n");
            // Abrir el documento.
            documento.open();
            // Agregar la imagen y el párrafo al documento.
            documento.add(header);
            documento.add(parrafo);
            // Crear una tabla para mostrar los datos de las categorías.
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");

            try {
                // Establecer conexión con la base de datos.
                Connection cn = Conexion.conectar();
                // Consulta SQL para obtener los datos de las categorías.
                PreparedStatement pst = cn.prepareStatement(
                        "select * from tb_categoria");
                ResultSet rs = pst.executeQuery();
                // Verificar si se obtuvieron resultados.
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                    } while (rs.next());
                    // Agregar la tabla al documento.
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            // Cerrar el documento y mostrar un mensaje de éxito.
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }

    /**
     * Método para generar un reporte de ventas en formato PDF.
     */
    public void ReporteVentas() {
        Document documento = new Document();
        try {
            // Crear el documento PDF y establecer la ruta de almacenamiento.
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\andre\\OneDrive\\Documentos\\Reportes/Reporte_Ventas.pdf"));
            // Agregar una imagen al encabezado.
            Image header = Image.getInstance("src/img/header1.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            // Formato del texto del documento.
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte Generado \nUniversal Trade\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de ventas \n\n");
            // Abrir el documento.
            documento.open();
            // Agregar la imagen y el párrafo al documento.
            documento.add(header);
            documento.add(parrafo);
            // Crear una tabla para mostrar los datos de las ventas.
            float[] columnsWidths = {3, 9, 4, 5, 3};
            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Cliente");
            tabla.addCell("Total Pagar");
            tabla.addCell("Fecha Venta");
            tabla.addCell("Estado");

            try {
                // Establecer conexión con la base de datos.
                Connection cn = Conexion.conectar();
                // Consulta SQL para obtener los datos de las ventas y los clientes.
                PreparedStatement pst = cn.prepareStatement(
                        "select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                        + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                        + "from tb_cabecera_venta as cv, tb_cliente as c "
                        + "where cv.idCliente = c.idCliente");
                ResultSet rs = pst.executeQuery();
                // Verificar si se obtuvieron resultados.
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    // Agregar la tabla al documento.
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            // Cerrar el documento y mostrar un mensaje de éxito.
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");
        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
}
