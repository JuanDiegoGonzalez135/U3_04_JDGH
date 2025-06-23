package utez.edu.mx.Aplicacion.de.principios.Services;

import org.springframework.stereotype.Service;
import utez.edu.mx.Aplicacion.de.principios.DTOS.AlmacenDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Almacen;
import utez.edu.mx.Aplicacion.de.principios.Models.Cede;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;
import utez.edu.mx.Aplicacion.de.principios.Models.Estados;
import utez.edu.mx.Aplicacion.de.principios.Repositories.AlmacenRepository;
import utez.edu.mx.Aplicacion.de.principios.Repositories.CedeRepository;
import utez.edu.mx.Aplicacion.de.principios.Repositories.ClienteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class AlmacenService {
    private final AlmacenRepository almacenRepository;
    private final ClienteRepository clienteRepository;
    private final CedeRepository cedeRepository;

    public AlmacenService(AlmacenRepository almacenRepository,
                          ClienteRepository clienteRepository,
                          CedeRepository cedeRepository) {
        this.almacenRepository = almacenRepository;
        this.clienteRepository = clienteRepository;
        this.cedeRepository = cedeRepository;
    }

    public List<Almacen> obtenerTodos() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> buscarPorId(Long id) {
        return almacenRepository.findById(id);
    }

    public Almacen crearAlmacen(AlmacenDTO dto) {
        Cliente cliente = null;
        if (dto.getClienteId() != null) {
            cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        }
        Cede cede = cedeRepository.findById(dto.getCedeId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));

        Almacen almacen = new Almacen();
        almacen.setPrecioVenta(dto.getPrecioVenta());
        almacen.setTamaño(dto.getTamaño());
        almacen.setFechaRegistro(LocalDate.now());
        almacen.setCede(cede);

        if (cliente != null) {
            almacen.setCliente(cliente);
            almacen.setStatus(utez.edu.mx.Aplicacion.de.principios.Models.Estados.VENDIDO);
        } else {
            almacen.setStatus(utez.edu.mx.Aplicacion.de.principios.Models.Estados.DISPONIBLE);
        }

        Almacen guardado = almacenRepository.save(almacen);
        String claveAlmacen = cede.getClave() + "-A" + guardado.getId();
        guardado.setClave(claveAlmacen);

        return almacenRepository.save(guardado);
    }

    public Optional<Almacen> actualizarAlmacen(Long id, AlmacenDTO dto) {
        return almacenRepository.findById(id)
                .map(almacenExistente -> {

                    // Validar si quieren cambiar cliente
                    if (almacenExistente.getCliente().getId() != dto.getClienteId()) {
                        Cliente nuevoCliente = clienteRepository.findById(dto.getClienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

                        if (almacenExistente.getStatus() == Estados.VENDIDO) {
                            throw new RuntimeException("Este almacén ya está vendido, no se puede reasignar");
                        }

                        almacenExistente.setCliente(nuevoCliente);
                        almacenExistente.setStatus(Estados.VENDIDO);
                    }

                    // Validar si quieren cambiar cede
                    if (almacenExistente.getCede().getId() != dto.getCedeId()) {
                        Cede nuevaCede = cedeRepository.findById(dto.getCedeId())
                                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
                        almacenExistente.setCede(nuevaCede);
                    }

                    // Controlar cambios de status manuales (opcional)
                    if (dto.getStatus() != null) {
                        if (almacenExistente.getStatus() == Estados.VENDIDO && dto.getStatus() == Estados.DISPONIBLE) {
                            throw new RuntimeException("No puedes cambiar un almacén vendido a disponible");
                        }

                        almacenExistente.setStatus(dto.getStatus());
                    }

                    almacenExistente.setPrecioVenta(dto.getPrecioVenta());
                    almacenExistente.setTamaño(dto.getTamaño());

                    return almacenRepository.save(almacenExistente);
                });
    }

    public Almacen asignarAlmacenACliente(Long idAlmacen, Long idCliente) {
        Almacen almacen = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        if (almacen.getStatus() == Estados.VENDIDO) {
            throw new RuntimeException("Este almacén ya está vendido y no se puede reasignar");
        }

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        almacen.setCliente(cliente);
        almacen.setStatus(Estados.VENDIDO);

        return almacenRepository.save(almacen);
    }

    public void eliminarAlmacen(Long id) {
        almacenRepository.deleteById(id);
    }
}
