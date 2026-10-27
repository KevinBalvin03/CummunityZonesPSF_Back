package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.CommunityZonesPSF.dtos.UsuarioDTO;
import com.example.CommunityZonesPSF.mapeadores.IMapaUsuario;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioUsuario;
import com.example.CommunityZonesPSF.validadores.IValidadorUsuario;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    // Dependencias que necesita el servicio (inyectadas por constructor)
    private final IMapaUsuario mapaUsuario;
    private final IRepositorioUsuario repositorioUsuario;
    private final IValidadorUsuario validadorUsuario;

    public UsuarioServicioImpl(IMapaUsuario mapaUsuario,
                               IRepositorioUsuario repositorioUsuario,
                               IValidadorUsuario validadorUsuario) {
        this.mapaUsuario = mapaUsuario;
        this.repositorioUsuario = repositorioUsuario;
        this.validadorUsuario = validadorUsuario;
    }

    @Override
    public UsuarioDTO guardarUsuarioEnBD(Usuario datos) {
        this.validadorUsuario.validarNuevoUsuario(datos);
        Usuario usuarioGuardado = this.repositorioUsuario.save(datos);
        return this.mapaUsuario.convertir_modelo_a_dto(usuarioGuardado);
    }

    @Override
    public UsuarioDTO modificarUsuarioEnBD(Usuario datos, UUID id) {
        Usuario usuarioQueEncontre = buscarPorIdOFallar(id);

        this.validadorUsuario.validarDatosModificacion(datos);

        usuarioQueEncontre.setName(datos.getName());
        // El rol solo se cambia si lo mandan
        if (datos.getRol() != null) {
            usuarioQueEncontre.setRol(datos.getRol());
        }

        Usuario usuarioModificado = this.repositorioUsuario.save(usuarioQueEncontre);
        return this.mapaUsuario.convertir_modelo_a_dto(usuarioModificado);
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosEnBD() {
        List<Usuario> usuariosEncontrados = this.repositorioUsuario.findAll();
        return this.mapaUsuario.convertir_lista_de_modelo_a_lista_de_dto(usuariosEncontrados);
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(UUID id) {
        return this.mapaUsuario.convertir_modelo_a_dto(buscarPorIdOFallar(id));
    }

    @Override
    public UsuarioDTO iniciarSesion(Usuario datos) {
        this.validadorUsuario.validarDatosLogin(datos);

        // Mismo mensaje si el correo no existe o si la contraseña no coincide,
        // para no revelar qué correos están registrados
        Usuario usuario = this.repositorioUsuario.findByEmail(datos.getEmail())
                .filter(u -> datos.getPassword().equals(u.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        return this.mapaUsuario.convertir_modelo_a_dto(usuario);
    }

    @Override
    public void eliminarUsuarioEnBD(UUID id) {
        buscarPorIdOFallar(id);
        this.validadorUsuario.validarEliminacion(id);
        this.repositorioUsuario.deleteById(id);
    }

    private Usuario buscarPorIdOFallar(UUID id) {
        Optional<Usuario> usuarioQueEstoyBuscando = this.repositorioUsuario.findById(id);
        if (usuarioQueEstoyBuscando.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return usuarioQueEstoyBuscando.get();
    }
}
