package utez.edu.mx.Aplicacion.de.principios.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Aplicacion.de.principios.DTOS.AlmacenDTO;
import utez.edu.mx.Aplicacion.de.principios.Models.Almacen;
import utez.edu.mx.Aplicacion.de.principios.Services.AlmacenService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {
    private  AlmacenService almacenService;

    public AlmacenController(AlmacenService almacenService) {
        this.almacenService = almacenService;
    }


    @GetMapping
    public List<AlmacenDTO> obtenerTodos() {
        return almacenService.obtenerTodos().stream()
                .map(AlmacenDTO::new)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtenerPorId(@PathVariable Long id) {
        return almacenService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Almacen> crear(@RequestBody AlmacenDTO dto) {
        try {
            Almacen creado = almacenService.crearAlmacen(dto);
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Long id, @RequestBody AlmacenDTO dto) {
        return almacenService.actualizarAlmacen(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.eliminarAlmacen(id);
        return ResponseEntity.noContent().build();
    }
}
