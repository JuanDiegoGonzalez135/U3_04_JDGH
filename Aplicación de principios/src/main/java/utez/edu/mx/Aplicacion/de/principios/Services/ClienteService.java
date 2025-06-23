package utez.edu.mx.Aplicacion.de.principios.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.Aplicacion.de.principios.DTOS.ClienteDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;
import utez.edu.mx.Aplicacion.de.principios.Repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setNumerotel(dto.getNumeroTel());
        cliente.setCorreo(dto.getCorreo());
        cliente.setPassword(passwordEncoder.encode(dto.getPassword()));
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> actualizarCliente(Long id, ClienteDTO dto) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombreCompleto(dto.getNombreCompleto());
                    cliente.setNumerotel(dto.getNumeroTel());
                    cliente.setCorreo(dto.getCorreo());
                    return clienteRepository.save(cliente);
                });
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
