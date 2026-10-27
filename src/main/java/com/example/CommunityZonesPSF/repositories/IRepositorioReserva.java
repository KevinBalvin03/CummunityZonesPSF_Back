package com.example.CommunityZonesPSF.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CommunityZonesPSF.modelos.Reserva;

@Repository
public interface IRepositorioReserva extends JpaRepository<Reserva, UUID> {

    // Spring arma la consulta a partir del nombre del método.
    // El "_" indica que se entra a un campo de la relación (espacio.id, usuario.id).

    List<Reserva> findByUsuario_Id(UUID usuarioId);

    List<Reserva> findByEspacio_Id(UUID espacioId);

    boolean existsByUsuario_Id(UUID usuarioId);

    boolean existsByEspacio_Id(UUID espacioId);

    // ¿Ya hay una reserva de ese espacio en esa fecha y hora?
    boolean existsByEspacio_IdAndDateAndTime(UUID espacioId, LocalDate date, LocalTime time);

    // Igual, pero sin contar la reserva que se está modificando
    boolean existsByEspacio_IdAndDateAndTimeAndIdNot(UUID espacioId, LocalDate date, LocalTime time, UUID id);
}
