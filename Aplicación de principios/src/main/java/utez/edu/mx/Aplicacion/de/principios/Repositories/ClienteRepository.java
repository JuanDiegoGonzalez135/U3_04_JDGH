package utez.edu.mx.Aplicacion.de.principios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Aplicacion.de.principios.Models.Clientes;

public interface ClienteRepository extends JpaRepository <Clientes,Long> {

}
