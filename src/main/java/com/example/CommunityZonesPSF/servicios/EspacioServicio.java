package com.example.CommunityZonesPSF.servicios;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.repositories.IRepositorioEspacio;

@Service
public class EspacioServicio {

    private IRepositorioEspacio repositorioEspacio;

    public boolean guardarEspacioBD(Espacio UUID){}

    public boolean modificaEspacioBD(Espacio datos, UUID id){}

    public boolean eliminaEspacioBD(UUID id){}

    public boolean buscarEspaciosBD(){}
}
