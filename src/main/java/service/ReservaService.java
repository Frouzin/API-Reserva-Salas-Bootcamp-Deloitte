package service;

import entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ReservaRepository;
import repository.SalaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    public Reserva criarReserva(Reserva reserva) {
        if (reserva.getInicio().isAfter(reserva.getFim())) {
            throw new RuntimeException("Data de início deve ser antes da data de fim");
        }
        if (reserva.getInicio().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível reservar para o passado");
        }
        List<Reserva> conflitos = reservaRepository.findReservasConflitantes(
                reserva.getSala().getId(), reserva.getInicio(), reserva.getFim());

        if (!conflitos.isEmpty()) {
            throw new RuntimeException("Conflito de reserva para essa sala e horário");
        }

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservasFuturas() {
        return reservaRepository.findAll().stream()
                .filter(r -> r.getInicio().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public List<Reserva> listarPorSala(Long salaId) {
        return reservaRepository.findBySalaId(salaId);
    }

    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
