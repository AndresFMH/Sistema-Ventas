package modelo;

/**
 * Clase que representa una categoría de productos. Contiene atributos y métodos
 * para acceder y modificar la información de la categoría.
 *
 * @author andre
 */
public class Categoria {

    // Atributos
    private int idCategoria;
    private String descripcion;
    private int estado;

    // Constructor
    public Categoria() {
        this.idCategoria = 0;
        this.descripcion = "";
        this.estado = 0;
    }

    // Constructor con parámetros
    public Categoria(int idCategoria, String descripcion, int estado) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Métodos de acceso y modificación
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
