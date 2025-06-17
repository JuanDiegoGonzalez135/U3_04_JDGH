package utez.edu.mx.Aplicacion.de.principios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {

}
