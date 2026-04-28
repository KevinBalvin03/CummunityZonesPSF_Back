package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.repositories.IRepositorioEspacio;

@Service
public class EspacioServicio {

    @Autowired
    private IRepositorioEspacio repositorioEspacio;

    public Espacio guardarEspacioBD(Espacio informacion) {

        if (repositorioEspacio.findByName(informacion.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una reserva con este nombre");
        }

        if (informacion.getName().isEmpty() || informacion.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El nombre del espacio no puede estar vacio o nulo");
        }
        return this.repositorioEspacio.save(informacion);
    }

    public Espacio modificaEspacioBD(Espacio informacion, UUID id) {

        Optional<Espacio> espacio_a_buscar = this.repositorioEspacio.findById(id);

        if (espacio_a_buscar.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El espacio que quieres editar no existe en BD");
        }

        Espacio espacio_a_modificar = espacio_a_buscar.get();

        if (informacion.getName() == null || informacion.getName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre no puede estar vacío o nulo");
        }

        espacio_a_modificar.setName(informacion.getName());
        return this.repositorioEspacio.save(espacio_a_modificar);
    }

    public boolean eliminaEspacioBD(UUID id) {

        Optional<Espacio> espacioEliminar = repositorioEspacio.findById(id);
        if (espacioEliminar.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El espacio no existe");
        }

        Espacio EspacioEncontrado=espacioEliminar.get();
        this.repositorioEspacio.deleteById(id);
        return true;
    }

    public List<Espacio> buscarEspaciosBD() {
        List<Espacio> espaciosEncontrados = this.repositorioEspacio.findAll();
        return espaciosEncontrados;
    }
}