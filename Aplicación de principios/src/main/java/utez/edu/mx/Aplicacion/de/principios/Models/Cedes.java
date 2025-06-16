package utez.edu.mx.Aplicacion.de.principios.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cedes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String clave;
    private String estado;
    private String municipio;

    @OneToMany(mappedBy = "cede", cascade = CascadeType.ALL)
    private List<Almacen> almacenes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    /*@PrePersist
    public void generarClave() {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        int aleatorio = new Random().nextInt(9000) + 1000;
        this.clave = "C" + id + "-" + fecha + "-" + aleatorio;
    }*/
}
