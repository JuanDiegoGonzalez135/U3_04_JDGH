package utez.edu.mx.Aplicacion.de.principios.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Almacen{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;
    private LocalDate fechaRegistro;
    private Double precioVenta;
    private Tamaños tamaño;

    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cedes cede;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;


    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cedes cedes;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes clientes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Tamaños getTamaño() {
        return tamaño;
    }

    public void setTamaño(Tamaños tamaño) {
        this.tamaño = tamaño;
    }

    public Cedes getCede() {
        return cede;
    }

    public void setCede(Cedes cede) {
        this.cede = cede;
    }

}
