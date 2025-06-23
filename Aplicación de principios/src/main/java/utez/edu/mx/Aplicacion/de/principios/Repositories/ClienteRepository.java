package utez.edu.mx.Aplicacion.de.principios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {
    Optional<Cliente> findByCorreo(String correo);

}
