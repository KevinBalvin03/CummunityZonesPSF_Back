package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.UUID;

import com.example.CommunityZonesPSF.dtos.EspacioDTO;
import com.example.CommunityZonesPSF.modelos.Espacio;

public interface IEspacioServicio {

    EspacioDTO guardarEspacioEnBD(Espacio datos);

    EspacioDTO modificarEspacioEnBD(Espacio datos, UUID id);

    List<EspacioDTO> buscarEspaciosEnBD();

    EspacioDTO buscarEspacioPorId(UUID id);

    void eliminarEspacioEnBD(UUID id);
}
