package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;

@Service
public class ReservaServicio {

    @Autowired
    private IRepositorioReserva repositorioReserva;

    public Reserva guardarReservaBD(Reserva informacion) {

        if (repositorioReserva.findById(informacion.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta reserva ya existe");
        }

        if (informacion.getDate() == null || informacion.getTime() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha u hora no pueden ser nulas");
        }

        return this.repositorioReserva.save(informacion);
    }

    public Reserva modificaReservaBD(Reserva informacion, UUID id) {

        Optional<Reserva> reserva_a_buscar = this.repositorioReserva.findById(id);
        if (reserva_a_buscar.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "La reserva que quieres editar, no existe en BD");
        }

        Reserva reserva_a_modificar = reserva_a_buscar.get();
        if (informacion.getDate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha no puede ser nula");
        }

        reserva_a_modificar.setDate(informacion.getDate());
        return this.repositorioReserva.save(reserva_a_modificar);
    }

    public boolean eliminaReservaBD(UUID id) {

        Optional<Reserva> reservaBuscada = repositorioReserva.findById(id);
        if(reservaBuscada.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encuentra la reserva");
        }

        Reserva reservaEncontrada = reservaBuscada.get();
        this.repositorioReserva.deleteById(id);
        return true;
    }

    
    public List<Reserva> buscarReservasBD(){
        List<Reserva> reservasEncontradas = this.repositorioReserva.findAll();
        return reservasEncontradas; 
    }
}