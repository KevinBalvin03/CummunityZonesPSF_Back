package com.example.CommunityZonesPSF.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CommunityZonesPSF.dtos.UsuarioDTO;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.servicios.IUsuarioServicio;

@RestController
@RequestMapping("/paseoapi/v1/usuarios")
public class UsuarioControlador {

    // Se depende de la interfaz, no de la clase concreta
    private final IUsuarioServicio servicio;

    public UsuarioControlador(IUsuarioServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> controlarGuardado(@RequestBody Usuario datos) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarUsuarioEnBD(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> controlarModificado(@RequestBody Usuario datos, @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificarUsuarioEnBD(datos, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id) {
        this.servicio.eliminarUsuarioEnBD(id);
        return ResponseEntity.noContent().build(); // 204, sin cuerpo
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> controlarListado() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarUsuariosEnBD());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> controlarBusquedaPorId(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarUsuarioPorId(id));
    }

    // Login: recibe {"email": "...", "password": "..."}
    // 200 con el usuario (sin contraseña) o 401 si no coinciden
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> controlarLogin(@RequestBody Usuario datos) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.iniciarSesion(datos));
    }
}
