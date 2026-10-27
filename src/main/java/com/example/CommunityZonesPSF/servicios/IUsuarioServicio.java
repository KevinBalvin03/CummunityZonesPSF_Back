package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.UUID;

import com.example.CommunityZonesPSF.dtos.UsuarioDTO;
import com.example.CommunityZonesPSF.modelos.Usuario;

// Contrato: describe qué se puede hacer con usuarios, sin decir cómo
public interface IUsuarioServicio {

    UsuarioDTO guardarUsuarioEnBD(Usuario datos);

    UsuarioDTO modificarUsuarioEnBD(Usuario datos, UUID id);

    List<UsuarioDTO> buscarUsuariosEnBD();

    UsuarioDTO buscarUsuarioPorId(UUID id);

    UsuarioDTO iniciarSesion(Usuario datos);

    void eliminarUsuarioEnBD(UUID id);
}
