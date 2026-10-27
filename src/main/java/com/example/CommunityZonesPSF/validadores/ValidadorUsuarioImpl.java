package com.example.CommunityZonesPSF.validadores;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;
import com.example.CommunityZonesPSF.repositories.IRepositorioUsuario;

@Component
public class ValidadorUsuarioImpl implements IValidadorUsuario {

    private final IRepositorioUsuario repositorioUsuario;
    private final IRepositorioReserva repositorioReserva;

    public ValidadorUsuarioImpl(IRepositorioUsuario repositorioUsuario,
                                IRepositorioReserva repositorioReserva) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioReserva = repositorioReserva;
    }

    @Override
    public void validarNuevoUsuario(Usuario datos) {
        validarNombreVacio(datos.getName());
        validarCorreoVacio(datos.getEmail());
        validarCorreoNoRepetido(datos.getEmail());
        validarLongitudPassword(datos.getPassword());
        validarRol(datos);
    }

    @Override
    public void validarDatosModificacion(Usuario datos) {
        validarNombreVacio(datos.getName());
    }

    @Override
    public void validarDatosLogin(Usuario datos) {
        validarCorreoVacio(datos.getEmail());
        if (datos.getPassword() == null || datos.getPassword().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña es obligatoria");
        }
    }

    // No se puede borrar un usuario que tiene reservas
    @Override
    public void validarEliminacion(UUID id) {
        if (repositorioReserva.existsByUsuario_Id(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se puede eliminar el usuario porque tiene reservas");
        }
    }

    // Reglas de validación individuales
    // Se revisa null primero: si no, .isBlank() lanza un error 500

    public void validarNombreVacio(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre es obligatorio, ingresalo por favor");
        }
    }

    public void validarCorreoVacio(String email) {
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El correo es obligatorio");
        }
    }

    public void validarCorreoNoRepetido(String email) {
        if (repositorioUsuario.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un usuario con el correo ingresado");
        }
    }

    public void validarLongitudPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña debe tener mínimo 6 caracteres");
        }
    }

    public void validarRol(Usuario datos) {
        if (datos.getRol() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El rol es obligatorio: ADMINISTRADOR o USUARIO");
        }
    }
}
