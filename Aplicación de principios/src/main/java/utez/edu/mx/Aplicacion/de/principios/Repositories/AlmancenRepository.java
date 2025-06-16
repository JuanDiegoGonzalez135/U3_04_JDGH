package utez.edu.mx.Aplicacion.de.principios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Aplicacion.de.principios.Models.Almacen;
import utez.edu.mx.Aplicacion.de.principios.Models.Clientes;

public interface AlmancenRepository extends JpaRepository <Almacen,Long> {

}
