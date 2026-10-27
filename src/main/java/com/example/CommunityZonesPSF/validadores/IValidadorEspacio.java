package com.example.CommunityZonesPSF.validadores;

import java.util.UUID;

import com.example.CommunityZonesPSF.modelos.Espacio;

public interface IValidadorEspacio {

    void validarNuevoEspacio(Espacio datos);

    void validarDatosModificacion(Espacio datos, UUID id);

    void validarEliminacion(UUID id);
}
