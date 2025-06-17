package utez.edu.mx.Aplicacion.de.principios.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Aplicacion.de.principios.DTOS.CedeDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Cede;
import utez.edu.mx.Aplicacion.de.principios.Services.CedeService;

import java.util.List;
@RestController
@RequestMapping("/api/cedes")
@CrossOrigin(origins = "*")
public class CedeController {
    private final CedeService cedeService;

    public CedeController(CedeService cedeService) {
        this.cedeService = cedeService;
    }

    @GetMapping
    public List<Cede> obtenerTodas() {
        return cedeService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> obtenerPorId(@PathVariable Long id) {
        return cedeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cede> crear(@RequestBody CedeDTO dto) {
        return ResponseEntity.ok(cedeService.crearCede(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cede> actualizar(@PathVariable Long id, @RequestBody CedeDTO dto) {
        return cedeService.actualizarCede(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cedeService.eliminarCede(id);
        return ResponseEntity.noContent().build();
    }

}
