package com.example.CommunityZonesPSF.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.CommunityZonesPSF.dtos.UsuarioDTO;
import com.example.CommunityZonesPSF.modelos.Usuario;

// MapStruct genera la implementación al compilar.
// Copia los campos que se llaman igual en Usuario y UsuarioDTO (id, name, email, rol).
@Mapper(componentModel = "spring")
public interface IMapaUsuario {

    UsuarioDTO convertir_modelo_a_dto(Usuario usuario);

    List<UsuarioDTO> convertir_lista_de_modelo_a_lista_de_dto(List<Usuario> lista);
}
