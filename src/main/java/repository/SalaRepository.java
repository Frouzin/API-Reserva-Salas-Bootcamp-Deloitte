package repository;

import entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByNome(String nome);
}
