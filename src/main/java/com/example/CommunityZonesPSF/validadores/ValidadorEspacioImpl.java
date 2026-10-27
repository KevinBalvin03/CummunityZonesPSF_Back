package com.example.CommunityZonesPSF.validadores;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.repositories.IRepositorioEspacio;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;

@Component
public class ValidadorEspacioImpl implements IValidadorEspacio {

    private final IRepositorioEspacio repositorioEspacio;
    private final IRepositorioReserva repositorioReserva;

    public ValidadorEspacioImpl(IRepositorioEspacio repositorioEspacio,
                                IRepositorioReserva repositorioReserva) {
        this.repositorioEspacio = repositorioEspacio;
        this.repositorioReserva = repositorioReserva;
    }

    @Override
    public void validarNuevoEspacio(Espacio datos) {
        validarNombreVacio(datos.getName());
        validarNombreNoRepetido(datos.getName(), null);
        validarAforo(datos.getAforo());
    }

    @Override
    public void validarDatosModificacion(Espacio datos, UUID id) {
        validarNombreVacio(datos.getName());
        validarNombreNoRepetido(datos.getName(), id);
        // El aforo es opcional al modificar, pero si viene debe ser válido
        if (datos.getAforo() != null) {
            validarAforo(datos.getAforo());
        }
    }

    // No se puede borrar un espacio que tiene reservas: quedarían reservas huérfanas
    @Override
    public void validarEliminacion(UUID id) {
        if (repositorioReserva.existsByEspacio_Id(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se puede eliminar el espacio porque tiene reservas");
        }
    }

    // Reglas de validación individuales

    public void validarNombreVacio(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del espacio es obligatorio");
        }
    }

    // idActual: al modificar, el espacio puede conservar su propio nombre
    public void validarNombreNoRepetido(String name, UUID idActual) {
        repositorioEspacio.findByName(name).ifPresent(existente -> {
            if (!existente.getId().equals(idActual)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Ya existe un espacio con ese nombre");
            }
        });
    }

    public void validarAforo(Integer aforo) {
        if (aforo == null || aforo <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El aforo es obligatorio y debe ser mayor a 0");
        }
    }
}
