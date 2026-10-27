package com.example.CommunityZonesPSF.validadores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;

@Component
public class ValidadorReservaImpl implements IValidadorReserva {

    private final IRepositorioReserva repositorioReserva;

    public ValidadorReservaImpl(IRepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    @Override
    public void validarNuevaReserva(Reserva datos) {
        validarFecha(datos.getDate());
        validarHoraVacia(datos.getTime());
        validarUsuarioEnviado(datos);
        validarEspacioEnviado(datos);
    }

    @Override
    public void validarDatosModificacion(Reserva datos) {
        // Al modificar se puede mandar solo la fecha, solo la hora o ambas
        if (datos.getDate() == null && datos.getTime() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Envía al menos la fecha o la hora a modificar");
        }
        if (datos.getDate() != null) {
            validarFecha(datos.getDate());
        }
    }

    // idReservaActual es null al crear; al modificar es el id de la reserva editada,
    // para que no choque consigo misma
    @Override
    public void validarDisponibilidad(UUID espacioId, LocalDate date, LocalTime time, UUID idReservaActual) {
        boolean ocupado = (idReservaActual == null)
                ? repositorioReserva.existsByEspacio_IdAndDateAndTime(espacioId, date, time)
                : repositorioReserva.existsByEspacio_IdAndDateAndTimeAndIdNot(espacioId, date, time, idReservaActual);

        if (ocupado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El espacio ya está reservado en esa fecha y hora");
        }
    }

    // Reglas de validación individuales

    public void validarFecha(LocalDate date) {
        if (date == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha es obligatoria (formato AAAA-MM-DD)");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede reservar en una fecha pasada");
        }
    }

    public void validarHoraVacia(LocalTime time) {
        if (time == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La hora es obligatoria (formato HH:mm:ss)");
        }
    }

    public void validarUsuarioEnviado(Reserva datos) {
        if (datos.getUsuario() == null || datos.getUsuario().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debes indicar el usuario: \"usuario\": {\"id\": \"...\"}");
        }
    }

    public void validarEspacioEnviado(Reserva datos) {
        if (datos.getEspacio() == null || datos.getEspacio().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debes indicar el espacio: \"espacio\": {\"id\": \"...\"}");
        }
    }
}
