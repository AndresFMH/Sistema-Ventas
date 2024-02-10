package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.InterFacturacion;

/**
 * Clase para generar un documento PDF de factura de venta. Utiliza la
 * biblioteca iTextPDF para la generación de documentos PDF. Utiliza la clase
 * Conexion para establecer la conexión con la base de datos. Se asume que
 * existe una interfaz InterFacturacion con un componente JTable llamado
 * tableProductos.
 *
 * @author andre
 */
public class VentaPDF {

    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    //Método para obtener datos del cliente
    public void DatosCliente(int idCliente) {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
                cedulaCliente = rs.getString("cedula");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cliente: " + e);
        }
    }

    //Método para generar factura en formato PDF
    public void generarFacturaPDF() {
        try {
            // Cargar fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            //Cambiar formato fecha de / a _
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }
            nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Cargar imagen
            Image img = Image.getInstance("src/img/Logotipo1.png");

            // Obtener el último ID de la cabecera de venta
            Ctrl_RegistrarVenta ctrlRegistrarVenta = new Ctrl_RegistrarVenta();
            int ultimoId = ctrlRegistrarVenta.obtenerUltimoIdCabeceraVenta();

            // Incrementar el ID para obtener el nuevo número de factura
            int nuevoNumeroFactura = ultimoId + 1;

            // Crear párrafo para el número de factura
            Paragraph numeroFactura = new Paragraph();
            numeroFactura.add(Chunk.NEWLINE);
            numeroFactura.add("Número de Factura: " + String.format("Factura%03d", nuevoNumeroFactura) + "\n\n");
            doc.add(numeroFactura);

            // Crear párrafo para la fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE); //Agregar nueva linea
            fecha.add("Fecha: " + fechaActual + "\n\n");

            // Crear párrafo para la fecha
            //Paragraph fecha = new Paragraph();
            //Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            //fecha.add(Chunk.NEWLINE); //Agregar nueva linea
            //fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");
            // Crear tabla para el encabezado
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0); //Quitar borde de la tabla

            // Tamaño de las celdas
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            // Agregar celdas al encabezado
            Encabezado.addCell(img);
            String rut = "0987654321-001";
            String nombre = "Universal Trade";
            String telefono = "3876443210";
            String direccion = "Yumbo";
            String razon = "Donde Cada Comercio Encuentra su Lugar";
            Encabezado.addCell("");//Celda vacia
            Encabezado.addCell("RUT: " + rut + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nRAZON SOCIAL: " + razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            // Cuerpo
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE); //Nueva linea
            cliente.add("Datos del cliente: " + "\n\n");
            doc.add(cliente);

            // Datos del cliente
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0); //Quitar bordes

            // Tamaño de las celdas
            float[] ColumnaCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(ColumnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("Cedula/RUT: ", negrita));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono: ", negrita));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion: ", negrita));

            //Quitar bordes
            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);

            // Agregar datos al cuerpo de la tablaCliente
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(cedulaCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);

            // Agregar al documento
            doc.add(tablaCliente);

            //Espacio en blanco
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            //Agregar los productos
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);

            //Tamaño de celdas
            float[] ColumnaProducto = new float[]{15f, 50f, 15f, 20f};
            tablaProducto.setWidths(ColumnaProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: ", negrita));

            //Quitar Bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);

            //Agregar Color Encabezado
            producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);

            // Agregar celda a la tablaProducto
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);

            // Agregar productos al cuerpo de la tablaProducto
            for (int i = 0; i < InterFacturacion.tableProductos.getRowCount(); i++) {
                String producto = InterFacturacion.tableProductos.getValueAt(i, 1).toString();
                String cantidad = InterFacturacion.tableProductos.getValueAt(i, 2).toString();
                String precio = InterFacturacion.tableProductos.getValueAt(i, 3).toString();
                String total = InterFacturacion.tableProductos.getValueAt(i, 7).toString();

                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }

            // Agregar al documento
            doc.add(tablaProducto);

            //Agregar total pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + InterFacturacion.txtTotalPagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            //Firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion y firma\n\n");
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            //Mensaje
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡GRACIAS POR SU COMPRA!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            //Cerrar documento y archivo
            doc.close();
            archivo.close();

            //Abrir el documento al ser generado
            Desktop.getDesktop().open(file);
            
            
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }
}
