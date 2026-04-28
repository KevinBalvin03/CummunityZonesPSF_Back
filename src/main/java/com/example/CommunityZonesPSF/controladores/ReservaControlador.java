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

import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.servicios.ReservaServicio;

@RestController
@RequestMapping("/paseoapi/v1/Reservas")
public class ReservaControlador {

    @Autowired
    ReservaServicio servicio;

    @PostMapping
    public ResponseEntity<Reserva> contraoldorGuardar(@RequestBody Reserva informacion) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarReservaBD(informacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> controlarModificado(@RequestBody Reserva informacion, @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificaReservaBD(informacion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controlarBorrado(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.eliminaReservaBD(id));
    }

    @GetMapping
    public ResponseEntity<?> controlarListado() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservasBD());
    }
}
