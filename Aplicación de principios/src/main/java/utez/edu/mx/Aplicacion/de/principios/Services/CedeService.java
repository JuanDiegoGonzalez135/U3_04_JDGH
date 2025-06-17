package utez.edu.mx.Aplicacion.de.principios.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.Aplicacion.de.principios.DTOS.CedeDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Cede;
import utez.edu.mx.Aplicacion.de.principios.Repositories.CedeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class CedeService {
    @Autowired
    private CedeRepository cedeRepository;

    public List<Cede> obtenerTodas() {
        return cedeRepository.findAll();
    }

    public Optional<Cede> buscarPorId(Long id) {
        return cedeRepository.findById(id);
    }

    public Cede crearCede(CedeDTO dto) {
        Cede cede = new Cede();
        cede.setEstado(dto.getEstado());
        cede.setMunicipio(dto.getMunicipio());

        Cede guardado = cedeRepository.save(cede);
        String clave = generarClaveCede(guardado.getId());
        guardado.setClave(clave);

        return cedeRepository.save(guardado);
    }

    public Optional<Cede> actualizarCede(Long id, CedeDTO dto) {
        return cedeRepository.findById(id)
                .map(cede -> {
                    cede.setEstado(dto.getEstado());
                    cede.setMunicipio(dto.getMunicipio());
                    return cedeRepository.save(cede);
                });
    }

    public void eliminarCede(Long id) {
        cedeRepository.deleteById(id);
    }

    private String generarClaveCede(Long id) {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        int random = new Random().nextInt(9000) + 1000; // 4 dígitos
        return "C" + id + "-" + fecha + "-" + random;
    }
}
