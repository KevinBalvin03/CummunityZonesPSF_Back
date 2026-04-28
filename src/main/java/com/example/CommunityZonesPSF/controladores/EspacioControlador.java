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

import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.servicios.EspacioServicio;

@RestController
@RequestMapping("/paseoapi/v1/Espacio")
public class EspacioControlador {

    @Autowired
    EspacioServicio servicio;

    @PostMapping
    public ResponseEntity<Espacio> controlarGuardado(@RequestBody Espacio informacion) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarEspacioBD(informacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espacio>controlarModificado(@RequestBody Espacio informacion, @PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificaEspacioBD(informacion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>controlarBorrado(@PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.eliminaEspacioBD(id));
    }
    //Funcoion para controlar el listar 
    //Dentro de las <> deberia ir los DTO 
    @GetMapping
    public ResponseEntity<?>controlarListado(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarEspaciosBD());
    }
}
