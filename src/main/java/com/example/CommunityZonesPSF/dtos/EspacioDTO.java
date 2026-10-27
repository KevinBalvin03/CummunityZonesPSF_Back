package com.example.CommunityZonesPSF.dtos;

import java.util.UUID;

// Lo que la API devuelve de un espacio
public record EspacioDTO(
        UUID id,
        String name,
        String description,
        String photo,
        Integer aforo
) {}
