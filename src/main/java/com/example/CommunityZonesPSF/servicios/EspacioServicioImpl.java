package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.dtos.EspacioDTO;
import com.example.CommunityZonesPSF.mapeadores.IMapaEspacio;
import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.repositories.IRepositorioEspacio;
import com.example.CommunityZonesPSF.validadores.IValidadorEspacio;

@Service
public class EspacioServicioImpl implements IEspacioServicio {

    private final IMapaEspacio mapaEspacio;
    private final IRepositorioEspacio repositorioEspacio;
    private final IValidadorEspacio validadorEspacio;

    public EspacioServicioImpl(IMapaEspacio mapaEspacio,
                               IRepositorioEspacio repositorioEspacio,
                               IValidadorEspacio validadorEspacio) {
        this.mapaEspacio = mapaEspacio;
        this.repositorioEspacio = repositorioEspacio;
        this.validadorEspacio = validadorEspacio;
    }

    @Override
    public EspacioDTO guardarEspacioEnBD(Espacio datos) {
        this.validadorEspacio.validarNuevoEspacio(datos);
        datos.setId(null); // el id siempre lo genera la BD
        Espacio espacioGuardado = this.repositorioEspacio.save(datos);
        return this.mapaEspacio.convertir_modelo_a_dto(espacioGuardado);
    }

    @Override
    public EspacioDTO modificarEspacioEnBD(Espacio datos, UUID id) {
        Espacio espacioQueEncontre = buscarPorIdOFallar(id);

        this.validadorEspacio.validarDatosModificacion(datos, id);

        espacioQueEncontre.setName(datos.getName());
        // Los demás campos solo se cambian si los mandan
        if (datos.getDescription() != null) {
            espacioQueEncontre.setDescription(datos.getDescription());
        }
        if (datos.getPhoto() != null) {
            espacioQueEncontre.setPhoto(datos.getPhoto());
        }
        if (datos.getAforo() != null) {
            espacioQueEncontre.setAforo(datos.getAforo());
        }

        Espacio espacioModificado = this.repositorioEspacio.save(espacioQueEncontre);
        return this.mapaEspacio.convertir_modelo_a_dto(espacioModificado);
    }

    @Override
    public List<EspacioDTO> buscarEspaciosEnBD() {
        return this.mapaEspacio.convertir_lista_de_modelo_a_lista_de_dto(
                this.repositorioEspacio.findAll());
    }

    @Override
    public EspacioDTO buscarEspacioPorId(UUID id) {
        return this.mapaEspacio.convertir_modelo_a_dto(buscarPorIdOFallar(id));
    }

    @Override
    public void eliminarEspacioEnBD(UUID id) {
        buscarPorIdOFallar(id);
        this.validadorEspacio.validarEliminacion(id);
        this.repositorioEspacio.deleteById(id);
    }

    private Espacio buscarPorIdOFallar(UUID id) {
        return this.repositorioEspacio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Espacio no encontrado"));
    }
}
