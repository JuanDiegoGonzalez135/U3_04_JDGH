package utez.edu.mx.Aplicacion.de.principios.Services;

import org.springframework.stereotype.Service;
import utez.edu.mx.Aplicacion.de.principios.DTOS.AlmacenDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Almacen;
import utez.edu.mx.Aplicacion.de.principios.Models.Cede;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;
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
        // Buscar Cliente y Cede por id desde el DTO
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Cede cede = cedeRepository.findById(dto.getCedeId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));

        Almacen almacen = new Almacen();
        almacen.setPrecioVenta(dto.getPrecioVenta());
        almacen.setTamaño(dto.getTamaño());
        almacen.setFechaRegistro(LocalDate.now());
        almacen.setCliente(cliente);
        almacen.setCede(cede);

        // Guardar para generar id y luego clave
        Almacen guardado = almacenRepository.save(almacen);

        String claveAlmacen = cede.getClave() + "-A" + guardado.getId();
        guardado.setClave(claveAlmacen);

        return almacenRepository.save(guardado);
    }

    public Optional<Almacen> actualizarAlmacen(Long id, AlmacenDTO dto) {
        return almacenRepository.findById(id)
                .map(almacenExistente -> {
                    almacenExistente.setPrecioVenta(dto.getPrecioVenta());
                    almacenExistente.setTamaño(dto.getTamaño());

                    if (almacenExistente.getCliente().getId() != dto.getClienteId()) {
                        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                        almacenExistente.setCliente(cliente);
                    }

                    if (almacenExistente.getCede().getId() != dto.getCedeId()) {
                        Cede cede = cedeRepository.findById(dto.getCedeId())
                                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
                        almacenExistente.setCede(cede);
                    }

                    return almacenRepository.save(almacenExistente);
                });
    }

    public void eliminarAlmacen(Long id) {
        almacenRepository.deleteById(id);
    }
}
