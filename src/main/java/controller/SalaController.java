package controller;

import entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SalaService;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> criar(@RequestBody Sala sala) {
        return ResponseEntity.ok(salaService.criarSala(sala));
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return ResponseEntity.ok(salaService.listarSalas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
