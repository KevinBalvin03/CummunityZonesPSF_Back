package com.example.CommunityZonesPSF.validadores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.example.CommunityZonesPSF.modelos.Reserva;

public interface IValidadorReserva {

    void validarNuevaReserva(Reserva datos);

    void validarDatosModificacion(Reserva datos);

    void validarDisponibilidad(UUID espacioId, LocalDate date, LocalTime time, UUID idReservaActual);
}
