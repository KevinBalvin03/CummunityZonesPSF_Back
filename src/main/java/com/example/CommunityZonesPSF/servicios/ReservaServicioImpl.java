package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.dtos.ReservaDTO;
import com.example.CommunityZonesPSF.mapeadores.IMapaReserva;
import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioEspacio;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;
import com.example.CommunityZonesPSF.repositories.IRepositorioUsuario;
import com.example.CommunityZonesPSF.validadores.IValidadorReserva;

@Service
public class ReservaServicioImpl implements IReservaServicio {

    private final IMapaReserva mapaReserva;
    private final IRepositorioReserva repositorioReserva;
    private final IRepositorioUsuario repositorioUsuario;
    private final IRepositorioEspacio repositorioEspacio;
    private final IValidadorReserva validadorReserva;

    public ReservaServicioImpl(IMapaReserva mapaReserva,
                               IRepositorioReserva repositorioReserva,
                               IRepositorioUsuario repositorioUsuario,
                               IRepositorioEspacio repositorioEspacio,
                               IValidadorReserva validadorReserva) {
        this.mapaReserva = mapaReserva;
        this.repositorioReserva = repositorioReserva;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioEspacio = repositorioEspacio;
        this.validadorReserva = validadorReserva;
    }

    @Override
    public ReservaDTO guardarReservaEnBD(Reserva datos) {
        // 1. Datos obligatorios (fecha, hora, usuario y espacio)
        this.validadorReserva.validarNuevaReserva(datos);

        // 2. El usuario y el espacio deben existir en BD
        Usuario usuario = buscarUsuarioOFallar(datos.getUsuario().getId());
        Espacio espacio = buscarEspacioOFallar(datos.getEspacio().getId());

        // 3. El espacio no puede estar ocupado a esa fecha y hora
        this.validadorReserva.validarDisponibilidad(espacio.getId(), datos.getDate(), datos.getTime(), null);

        // 4. Guardar con las entidades completas (no solo con el id que llegó en el JSON)
        Reserva nueva = new Reserva();
        nueva.setDate(datos.getDate());
        nueva.setTime(datos.getTime());
        nueva.setUsuario(usuario);
        nueva.setEspacio(espacio);

        return this.mapaReserva.convertir_modelo_a_dto(this.repositorioReserva.save(nueva));
    }

    @Override
    public ReservaDTO modificarReservaEnBD(Reserva datos, UUID id) {
        Reserva reservaQueEncontre = buscarReservaOFallar(id);

        this.validadorReserva.validarDatosModificacion(datos);

        if (datos.getDate() != null) {
            reservaQueEncontre.setDate(datos.getDate());
        }
        if (datos.getTime() != null) {
            reservaQueEncontre.setTime(datos.getTime());
        }

        // Con la fecha/hora nuevas, revisar que el espacio siga libre
        this.validadorReserva.validarDisponibilidad(
                reservaQueEncontre.getEspacio().getId(),
                reservaQueEncontre.getDate(),
                reservaQueEncontre.getTime(),
                id);

        return this.mapaReserva.convertir_modelo_a_dto(this.repositorioReserva.save(reservaQueEncontre));
    }

    @Override
    public List<ReservaDTO> buscarReservasEnBD() {
        return this.mapaReserva.convertir_lista_de_modelo_a_lista_de_dto(
                this.repositorioReserva.findAll());
    }

    @Override
    public ReservaDTO buscarReservaPorId(UUID id) {
        return this.mapaReserva.convertir_modelo_a_dto(buscarReservaOFallar(id));
    }

    @Override
    public List<ReservaDTO> buscarReservasPorUsuario(UUID usuarioId) {
        buscarUsuarioOFallar(usuarioId);
        return this.mapaReserva.convertir_lista_de_modelo_a_lista_de_dto(
                this.repositorioReserva.findByUsuario_Id(usuarioId));
    }

    @Override
    public List<ReservaDTO> buscarReservasPorEspacio(UUID espacioId) {
        buscarEspacioOFallar(espacioId);
        return this.mapaReserva.convertir_lista_de_modelo_a_lista_de_dto(
                this.repositorioReserva.findByEspacio_Id(espacioId));
    }

    @Override
    public void eliminarReservaEnBD(UUID id) {
        buscarReservaOFallar(id);
        this.repositorioReserva.deleteById(id);
    }

    private Reserva buscarReservaOFallar(UUID id) {
        return this.repositorioReserva.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Reserva no encontrada"));
    }

    private Usuario buscarUsuarioOFallar(UUID id) {
        return this.repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El usuario de la reserva no existe"));
    }

    private Espacio buscarEspacioOFallar(UUID id) {
        return this.repositorioEspacio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El espacio de la reserva no existe"));
    }
}
