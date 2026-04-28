package com.example.CommunityZonesPSF.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.servicios.UsuarioServicio;

@RestController
@RequestMapping("/paseoapi/v1/Usuarios")
public class UsuarioControlador {
    //Por cada servicio ofrecido configuro 1 funcion controlador
    @Autowired
    UsuarioServicio servicio;

    //Funcion para controlar el guardado
@PostMapping
public ResponseEntity<Usuario> controlarGuardado(@RequestBody Usuario datos){
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(this.servicio.guardarUsuarioBD(datos));
}

    @PutMapping("/{id}")
    public ResponseEntity<Usuario>controlarModificado(@RequestBody Usuario datos, @PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificarUsuarioBD(datos,id));
    }

    //Funcion para controlar el borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<?>controlarBorrado(@PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.eliminarUsuarioEnBD(id));
    }
    //Funcoion para controlar el listar 
    //Dentro de las <> deberia ir los DTO 
    @GetMapping
    public ResponseEntity<?>controlarListado(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarUsuariosBD());
    }
}
