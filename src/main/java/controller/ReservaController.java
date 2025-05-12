package controller;

import entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.criarReserva(reserva));
    }

    @GetMapping("/futuras")
    public ResponseEntity<List<Reserva>> futuras() {
        return ResponseEntity.ok(reservaService.listarReservasFuturas());
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<Reserva>> porSala(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.listarPorSala(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
