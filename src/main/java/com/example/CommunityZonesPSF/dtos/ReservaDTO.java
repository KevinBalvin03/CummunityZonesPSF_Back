package com.example.CommunityZonesPSF.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

// Lo que la API devuelve de una reserva.
// Incluye id y nombre del usuario y del espacio para que el front los muestre
// sin hacer otra petición (y sin exponer la contraseña del usuario).
public record ReservaDTO(
        UUID id,
        LocalDate date,
        LocalTime time,
        UUID usuarioId,
        String usuarioName,
        UUID espacioId,
        String espacioName
) {}
