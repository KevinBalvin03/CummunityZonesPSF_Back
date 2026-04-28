package com.example.CommunityZonesPSF.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioUsuario;

@Service
public class UsuarioServicio {

    // Inyectando una dependencia
    @Autowired
    private IRepositorioUsuario repositorioUsuario;

    public Usuario guardarUsuarioBD(Usuario datos) {
        // Validar que datos me envian y si estos cumplen las reglas del negocio
        // Guardar los datos en BD con ayuda del repositorio
        // condiones logicas para validar datos a guardar
        // 1. validar que el correo a guardar no este guardado previamente

        if (repositorioUsuario.findByEmail(datos.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este correo ya se encuentra registrado");
        }

        if (datos.getName().isEmpty() || datos.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio o blanco");
        }
        ;

        return this.repositorioUsuario.save(datos);
    }

    public Usuario modificarUsuarioBD(Usuario datos, UUID id) {
        // validar que datos me envian y si estos cumplen las reglas del negocio
        // Modificar los datos en BD con ayuda del repositorio

        // 1.Buscar si el suario existe en BD
        Optional<Usuario> usuario_que_busco = this.repositorioUsuario.findById(id);
        if (usuario_que_busco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El usuario que quieres editar, no existe en BD");
        }

        Usuario usuario_encontrado = usuario_que_busco.get();
        // 2.Validar la informacion nueva que me manda el cliente
        if (datos.getName().isEmpty() || datos.getName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Revisa el nombre ingresado, esta vacio o es nulo");
        }

        usuario_encontrado.setName(datos.getName());
        return this.repositorioUsuario.save(usuario_encontrado);
    }

        public boolean eliminarUsuarioEnBD(UUID id){
        //Buscar y validar si el ID que me envian existe
        //Elimino el registro en BD
        Optional<Usuario>usuario_que_estoy_buscando=this.repositorioUsuario.findById(id);
        if(usuario_que_estoy_buscando.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El usuario que quieres eliminar, no existe en BD"
            );
        }
        Usuario usuario_que_encontre=usuario_que_estoy_buscando.get();
        this.repositorioUsuario.deleteById(id);
        return true;
    }

    public List<Usuario> buscarUsuariosBD() {
        // ***Dependiendo del parametro de busqueda debo implementar validaciones
        // Devuelvo os usuarios o usuario que se encuentren en la BD */
        List<Usuario> usuriosEncontrados = this.repositorioUsuario.findAll();

        return usuriosEncontrados;
    }
}