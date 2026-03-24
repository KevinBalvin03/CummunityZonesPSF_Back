package com.example.CommunityZonesPSF.servicios;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioReserva;


@Service
public class ReservaServicio {

    private IRepositorioReserva repositorioReserva;

    public boolean guardarReservaBD(Reserva UUID){}

    public boolean modificaReservaBD(Reserva datos, UUID id){}

    public boolean eliminaReservaBD(UUID id){}

    public boolean buscarReservasBD(){}

}
