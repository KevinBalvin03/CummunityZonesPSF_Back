package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.UUID;

import com.example.CommunityZonesPSF.dtos.ReservaDTO;
import com.example.CommunityZonesPSF.modelos.Reserva;

public interface IReservaServicio {

    ReservaDTO guardarReservaEnBD(Reserva datos);

    ReservaDTO modificarReservaEnBD(Reserva datos, UUID id);

    List<ReservaDTO> buscarReservasEnBD();

    ReservaDTO buscarReservaPorId(UUID id);

    List<ReservaDTO> buscarReservasPorUsuario(UUID usuarioId);

    List<ReservaDTO> buscarReservasPorEspacio(UUID espacioId);

    void eliminarReservaEnBD(UUID id);
}
