package com.example.CommunityZonesPSF.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.CommunityZonesPSF.dtos.ReservaDTO;
import com.example.CommunityZonesPSF.modelos.Reserva;

@Mapper(componentModel = "spring")
public interface IMapaReserva {

    // Los campos con nombre distinto se indican a mano: origen -> destino
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.name", target = "usuarioName")
    @Mapping(source = "espacio.id", target = "espacioId")
    @Mapping(source = "espacio.name", target = "espacioName")
    ReservaDTO convertir_modelo_a_dto(Reserva reserva);

    List<ReservaDTO> convertir_lista_de_modelo_a_lista_de_dto(List<Reserva> lista);
}
