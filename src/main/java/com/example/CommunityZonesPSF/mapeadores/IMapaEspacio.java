package com.example.CommunityZonesPSF.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.CommunityZonesPSF.dtos.EspacioDTO;
import com.example.CommunityZonesPSF.modelos.Espacio;

@Mapper(componentModel = "spring")
public interface IMapaEspacio {

    EspacioDTO convertir_modelo_a_dto(Espacio espacio);

    List<EspacioDTO> convertir_lista_de_modelo_a_lista_de_dto(List<Espacio> lista);
}
