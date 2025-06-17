package utez.edu.mx.Aplicacion.de.principios.DTOS;

import utez.edu.mx.Aplicacion.de.principios.Models.Almacen;
import utez.edu.mx.Aplicacion.de.principios.Models.Tamaños;

import java.time.LocalDate;

public class AlmacenDTO {
    private Long id;
    private String clave;
    private LocalDate fechaRegistro;
    private Double precioVenta;
    private  Tamaños tamaño;

    private Long cedeId;
    private String cedeClave;
    private String estado;
    private String municipio;

    private Long clienteId;
    private String nombreCliente;

    // Constructor desde Almacen:
    public AlmacenDTO() {
    }
    public AlmacenDTO(Almacen almacen) {
        this.id = almacen.getId();
        this.clave = almacen.getClave();
        this.fechaRegistro = almacen.getFechaRegistro();
        this.precioVenta = almacen.getPrecioVenta();
        this.tamaño = almacen.getTamaño(); // O .name() si es enum en texto

        this.cedeId = almacen.getCede().getId();
        this.cedeClave = almacen.getCede().getClave();
        this.estado = almacen.getCede().getEstado();
        this.municipio = almacen.getCede().getMunicipio();

        this.clienteId = almacen.getCliente().getId();
        this.nombreCliente = almacen.getCliente().getNombreCompleto();
    }

    public AlmacenDTO(Long id, String clave, LocalDate fechaRegistro, Double precioVenta, Tamaños tamaño, Long cedeId, String cedeClave, String estado, String municipio, Long clienteId, String nombreCliente) {
        this.id = id;
        this.clave = clave;
        this.fechaRegistro = fechaRegistro;
        this.precioVenta = precioVenta;
        this.tamaño = tamaño;
        this.cedeId = cedeId;
        this.cedeClave = cedeClave;
        this.estado = estado;
        this.municipio = municipio;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
    }

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

    public Long getCedeId() {
        return cedeId;
    }

    public void setCedeId(Long cedeId) {
        this.cedeId = cedeId;
    }

    public String getCedeClave() {
        return cedeClave;
    }

    public void setCedeClave(String cedeClave) {
        this.cedeClave = cedeClave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
