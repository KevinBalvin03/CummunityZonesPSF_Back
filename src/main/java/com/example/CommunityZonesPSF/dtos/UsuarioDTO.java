package com.example.CommunityZonesPSF.dtos;

import java.util.UUID;

import com.example.CommunityZonesPSF.enums.Rol;

// Lo que la API devuelve de un usuario. No incluye la contraseña.
public record UsuarioDTO(
        UUID id,
        String name,
        String email,
        Rol rol
) {}
