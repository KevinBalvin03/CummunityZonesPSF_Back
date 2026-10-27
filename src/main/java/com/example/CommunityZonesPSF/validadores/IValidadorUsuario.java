package com.example.CommunityZonesPSF.validadores;

import java.util.UUID;

import com.example.CommunityZonesPSF.modelos.Usuario;

public interface IValidadorUsuario {

    void validarNuevoUsuario(Usuario datos);

    void validarDatosModificacion(Usuario datos);

    void validarDatosLogin(Usuario datos);

    void validarEliminacion(UUID id);
}
