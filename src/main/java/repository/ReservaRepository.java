package repository;

import entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findBySalaId(Long salaId);

    @Query("SELECT r FROM Reserva r WHERE r.sala.id = :salaId AND ((r.inicio < :fim AND r.fim > :inicio))")
    List<Reserva> findReservasConflitantes(@Param("salaId") Long salaId, @Param("inicio")LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
