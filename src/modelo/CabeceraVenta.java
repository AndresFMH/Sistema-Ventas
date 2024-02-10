package modelo;

/**
 * Clase que representa la cabecera de una venta.
 * Contiene atributos y métodos para acceder y modificar la información de la cabecera de venta.
 * 
 * @author andre
 */
public class CabeceraVenta {

    //Atributos
    private int idCabeceraventa;
    private int idCliente;
    private double valorPagar;
    private String fechaVenta;
    private int estado;

    //Constructor
    public CabeceraVenta() {
        this.idCabeceraventa = 0;
        this.idCliente = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    //Constructor con parámetros
    public CabeceraVenta(int idCabeceraventa, int idCliente, double valorPagar, String fechaVenta, int estado) {
        this.idCabeceraventa = idCabeceraventa;
        this.idCliente = idCliente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    //Métodos de acceso y modificación
    public int getIdCabeceraventa() {
        return idCabeceraventa;
    }

    public void setIdCabeceraventa(int idCabeceraventa) {
        this.idCabeceraventa = idCabeceraventa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    //Método toString para obtener una representación de la instancia en forma de cadena
    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idCabeceraventa + ", idCliente=" + idCliente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }

}
